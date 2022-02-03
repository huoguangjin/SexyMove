package com.github.huoguangjin

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorActionHandler
import com.intellij.openapi.editor.actions.EditorActionUtil
import com.intellij.openapi.editor.actions.InactiveEditorAction
import kotlin.math.roundToInt

class SexyScrollRightHandler(private val multiplier: Int) : EditorActionHandler() {

  private val settings = SexyMoveSettings.getInstance()

  override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
    val columnShift = multiplier * settings.minScrollColumn
    EditorActionUtil.scrollRelatively(editor, 0, columnShift, false)
  }
}

class SexyScrollRightAction : InactiveEditorAction(SexyScrollRightHandler(1))

class SexyScrollLeftAction : InactiveEditorAction(SexyScrollRightHandler(-1))


class SexyScrollDownHandler(private val multiplier: Int) : EditorActionHandler() {

  private val settings = SexyMoveSettings.getInstance()

  override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
    val lineShift = multiplier * settings.minScrollLine
    EditorActionUtil.scrollRelatively(editor, lineShift, 0, false)
  }
}

class SexyScrollDownAction : InactiveEditorAction(SexyScrollDownHandler(1))

class SexyScrollUpAction : InactiveEditorAction(SexyScrollDownHandler(-1))


class SexyScrollPageDownHandler(private val multiplier: Int) : EditorActionHandler() {

  private val settings = SexyMoveSettings.getInstance()

  override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext?) {
    val pagePercent = multiplier * settings.scrollPagePercent
    val visibleArea = editor.scrollingModel.visibleArea
    val pageShift = (visibleArea.height * pagePercent / editor.lineHeight).roundToInt()
    EditorActionUtil.scrollRelatively(editor, pageShift, 0, false)
  }
}

class SexyScrollPageDownAction : InactiveEditorAction(SexyScrollPageDownHandler(1))

class SexyScrollPageUpAction : InactiveEditorAction(SexyScrollPageDownHandler(-1))
