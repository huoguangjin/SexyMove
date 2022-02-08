package com.github.huoguangjin

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener

class SexyMoveManager : EditorFactoryListener {

  private val settings = SexyMoveSettings.getInstance()

  override fun editorCreated(event: EditorFactoryEvent) {
    val editor = event.editor
    if (editor.isOneLineMode) {
      return
    }

    if (shouldActivate(editor)) {
      // it must be disabled
      SexyMoveAction.toggle(editor)
    }
  }

  private fun shouldActivate(editor: Editor): Boolean {
    if (settings.activeAlways) {
      return true
    }

    if (settings.activeInReadonlyFiles) {
      return editor.isViewer || !editor.document.isWritable
    }

    return false
  }
}
