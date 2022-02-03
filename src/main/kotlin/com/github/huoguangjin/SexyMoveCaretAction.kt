package com.github.huoguangjin

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.VisualPosition
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actions.InactiveEditorAction
import kotlin.math.roundToInt

class SexyMoveCaretAction : InactiveEditorAction(Handler()) {

  class Handler : EditorActionHandler() {

    private val settings = SexyMoveSettings.getInstance()

    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
      val pagePercent = settings.moveCaretPagePercent
      val visibleArea = editor.scrollingModel.visibleArea
      val locationY = visibleArea.y + visibleArea.height * pagePercent
      val lineHeight = editor.lineHeight
      val line = (locationY / lineHeight).roundToInt().coerceAtLeast(0)
      val column = editor.caretModel.visualPosition.column
      val pos = VisualPosition(line, column)
      editor.caretModel.moveToVisualPosition(pos)
    }
  }
}
