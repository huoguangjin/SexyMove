package com.github.huoguangjin

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CustomShortcutSet
import com.intellij.openapi.actionSystem.ex.ActionUtil
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.util.Key
import java.awt.event.KeyEvent

class SexyMoveAction : DumbAwareAction() {

  private var enable = false

  fun toggle(editor: Editor) {
    enable = !enable

    editor.settings.isBlockCursor = enable
      // see com.intellij.openapi.keymap.impl.IdeKeyEventDispatcher.dispatchKeyEvent
      editor.contentComponent.putClientProperty(ActionUtil.ALLOW_PlAIN_LETTER_SHORTCUTS, enable)

    if (enable) {
      val shortcutSet = CustomShortcutSet(*keyCodeToActionId.keys.toTypedArray())
      registerCustomShortcutSet(shortcutSet, editor.contentComponent)
    } else {
      unregisterCustomShortcutSet(editor.contentComponent)
    }
  }

  override fun actionPerformed(e: AnActionEvent) {
    val inputEvent = e.inputEvent
    val keyCode = (inputEvent as KeyEvent).keyCode

    val actionId = keyCodeToActionId[keyCode] ?: return
    val action = ActionManager.getInstance().getAction(actionId) ?: return

    ActionUtil.performActionDumbAwareWithCallbacks(action, e)
  }

  companion object {

    private val KEY: Key<SexyMoveAction> = Key.create("SexyMoveAction")

    private val keyCodeToActionId = mapOf(
      KeyEvent.VK_H to "SexyScrollLeft",
      KeyEvent.VK_J to "SexyScrollDown",
      KeyEvent.VK_K to "SexyScrollUp",
      KeyEvent.VK_L to "SexyScrollRight",

      KeyEvent.VK_U to "SexyScrollPageUp",
      KeyEvent.VK_D to "SexyScrollPageDown",

      KeyEvent.VK_I to "SexyMoveToggle",

      KeyEvent.VK_B to "EditorPreviousWord",
      KeyEvent.VK_E to "EditorNextWord",

      KeyEvent.VK_Z to "EditorScrollToCenter",

      KeyEvent.VK_A to "PreviousTab",
      KeyEvent.VK_S to "NextTab",

      KeyEvent.VK_OPEN_BRACKET to "Back",
      KeyEvent.VK_CLOSE_BRACKET to "Forward",

      KeyEvent.VK_SLASH to "Find",

      KeyEvent.VK_ENTER to "SexyMoveCaret",

      KeyEvent.VK_ESCAPE to "SexyMoveToggle",
    )

    fun isEnabled(editor: Editor): Boolean {
      val action = editor.getUserData(KEY) ?: return false
      return action.enable
    }

    fun toggle(editor: Editor) {
      var action = editor.getUserData(KEY)
      if (action == null) {
        action = SexyMoveAction()
        editor.putUserData(KEY, action)
      }

      action.toggle(editor)
    }
  }
}
