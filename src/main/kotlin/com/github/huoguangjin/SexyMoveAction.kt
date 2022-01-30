package com.github.huoguangjin

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.actionSystem.ex.ActionUtil
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.VisualPosition
import com.intellij.openapi.editor.actionSystem.*
import com.intellij.openapi.editor.actions.EditorActionUtil
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.util.Key
import com.intellij.util.ui.UIUtil
import java.awt.event.KeyEvent
import javax.swing.KeyStroke
import kotlin.math.roundToInt

class SexyMoveAction : DumbAwareAction() {

  private var enable = false

  fun toggle(editor: Editor) {
    enable = !enable

    editor.settings.isBlockCursor = enable
    UIUtil.putClientProperty(editor.contentComponent, ActionUtil.ALLOW_PlAIN_LETTER_SHORTCUTS, enable)

    if (enable) {
      registerCustomShortcutSet(SHORTCUT_SET, editor.contentComponent)
    } else {
      unregisterCustomShortcutSet(editor.contentComponent)
    }
  }

  override fun actionPerformed(e: AnActionEvent) {
    val editor = e.getRequiredData(CommonDataKeys.EDITOR)
    val inputEvent = e.inputEvent
    val keyCode = (inputEvent as KeyEvent).keyCode
    val keyStroke = KeyStroke.getKeyStroke(keyCode, inputEvent.modifiersEx)
    LOG.info("accept key: $editor $inputEvent")

    dispatchKey(keyStroke, editor, e.dataContext)
  }

  fun dispatchKey(keyStroke: KeyStroke, editor: Editor, dataContext: DataContext): Boolean {
    val lineShift = 12
    val columnShift = 8

    val scrollPagePercent = 0.3

    val locateCaretPagePercent = 0.25F

    when (keyStroke.keyCode) {
      KeyEvent.VK_H -> {
        EditorActionUtil.scrollRelatively(editor, 0, -columnShift, false)
        return true
      }
      KeyEvent.VK_J -> {
        EditorActionUtil.scrollRelatively(editor, lineShift, 0, false)
        return true
      }
      KeyEvent.VK_K -> {
        EditorActionUtil.scrollRelatively(editor, -lineShift, 0, false)
        return true
      }
      KeyEvent.VK_L -> {
        EditorActionUtil.scrollRelatively(editor, 0, columnShift, false)
        return true
      }

      KeyEvent.VK_U -> {
        val visibleArea = editor.scrollingModel.visibleArea
        val pageShift = (visibleArea.height * scrollPagePercent / editor.lineHeight).roundToInt()
        EditorActionUtil.scrollRelatively(editor, -pageShift, 0, false)
        return true
      }
      KeyEvent.VK_D -> {
        val visibleArea = editor.scrollingModel.visibleArea
        val pageShift = (visibleArea.height * scrollPagePercent / editor.lineHeight).roundToInt()
        EditorActionUtil.scrollRelatively(editor, pageShift, 0, false)
        return true
      }

      in KeyEvent.VK_0..KeyEvent.VK_9 -> {
        // TODO: 2022/1/16 record number
        return true
      }
      KeyEvent.VK_BACK_SPACE -> {
        // TODO: 2022/1/16 delete last typed number
        return true
      }
      KeyEvent.VK_G -> {
        // TODO: 2022/1/16 go to line
        return true
      }

      KeyEvent.VK_I -> {
        toggle(editor)
        return true
      }

      KeyEvent.VK_B -> {
        return executeAction("EditorPreviousWord", editor, dataContext)
      }
      KeyEvent.VK_E -> {
        return executeAction("EditorNextWord", editor, dataContext)
      }

      KeyEvent.VK_Z -> {
        return executeAction("EditorScrollToCenter", editor, dataContext)
      }

      KeyEvent.VK_A -> {
        return executeAction("PreviousTab", editor, dataContext)
      }
      KeyEvent.VK_S -> {
        return executeAction("NextTab", editor, dataContext)
      }

      KeyEvent.VK_OPEN_BRACKET -> {
        return executeAction("Back", editor, dataContext)
      }
      KeyEvent.VK_CLOSE_BRACKET -> {
        return executeAction("Forward", editor, dataContext)
      }

      KeyEvent.VK_SLASH -> {
        return executeAction("Find", editor, dataContext)
      }

      KeyEvent.VK_TAB -> {
        // TODO: 2022/1/16 repeat last action
        return true
      }

      KeyEvent.VK_ENTER -> {
        locateCaret(editor, locateCaretPagePercent)
        return true
      }

      KeyEvent.VK_ESCAPE -> {
        toggle(editor)
        return true
      }

      else -> return false
    }
  }

  private fun locateCaret(editor: Editor, pagePercent: Float) {
    val lineHeight = editor.lineHeight
    val visibleArea = editor.scrollingModel.visibleArea
    val locationY = visibleArea.y + visibleArea.height * pagePercent
    val line = (locationY / lineHeight).roundToInt().coerceAtLeast(0)
    val column = editor.caretModel.visualPosition.column
    val pos = VisualPosition(line, column)
    editor.caretModel.moveToVisualPosition(pos)
  }

  fun executeAction(actionId: String, editor: Editor, dataContext: DataContext): Boolean {
    val action = ActionManager.getInstance().getAction(actionId) ?: return false

    val context = CaretSpecificDataContext(dataContext, editor.caretModel.currentCaret)
    val event = AnActionEvent(
      null,
      context,
      ActionPlaces.KEYBOARD_SHORTCUT,
      action.templatePresentation,
      ActionManager.getInstance(),
      0
    )

    ActionUtil.performActionDumbAwareWithCallbacks(action, event)
    return true
  }

  companion object {

    private val LOG = logger<SexyMoveAction>()

    private val KEY: Key<SexyMoveAction> = Key.create("SexyMoveAction")

    private val SHORTCUT_SET = CustomShortcutSet(
      KeyEvent.VK_0,
      KeyEvent.VK_1,
      KeyEvent.VK_2,
      KeyEvent.VK_3,
      KeyEvent.VK_4,
      KeyEvent.VK_5,
      KeyEvent.VK_6,
      KeyEvent.VK_7,
      KeyEvent.VK_8,
      KeyEvent.VK_9,

      KeyEvent.VK_G, // go to line

      KeyEvent.VK_H, // scroll left
      KeyEvent.VK_J, // scroll down
      KeyEvent.VK_K, // scroll up
      KeyEvent.VK_L, // scroll right

      KeyEvent.VK_I, // exit SexyMove

      KeyEvent.VK_U, // scroll page up
      KeyEvent.VK_D, // scroll page down

      KeyEvent.VK_A, // action: PreviousTab
      KeyEvent.VK_S, // action: NextTab

      KeyEvent.VK_B, // action: EditorPreviousWord
      KeyEvent.VK_E, // action: EditorNextWord

      KeyEvent.VK_Z, // action: EditorScrollToCenter

      KeyEvent.VK_OPEN_BRACKET, // action: Back
      KeyEvent.VK_CLOSE_BRACKET, // action: Forward

      KeyEvent.VK_SLASH, // action: Find

      KeyEvent.VK_BACK_SPACE, // delete
      KeyEvent.VK_TAB, // repeat last action
      KeyEvent.VK_ENTER, // locate caret

      KeyEvent.VK_ESCAPE, // exit SexyMove
    )

    fun isEnabled(editor: Editor): Boolean {
      val action = UIUtil.getClientProperty(editor.contentComponent, KEY)
      return action?.enable ?: false
    }

    fun toggle(editor: Editor) {
      var action = UIUtil.getClientProperty(editor.contentComponent, KEY)
      if (action == null) {
        action = SexyMoveAction()
        UIUtil.putClientProperty(editor.contentComponent, KEY, action)
      }

      action.toggle(editor)
    }
  }
}
