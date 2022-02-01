package com.github.huoguangjin

import com.intellij.openapi.Disposable
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener

class SexyMoveManager : Disposable, EditorFactoryListener {

  private val settings = SexyMoveSettings.getInstance()

  init {
    EditorFactory.getInstance().addEditorFactoryListener(this, this)
  }

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
      return !editor.document.isWritable
    }

    return false
  }

  override fun dispose() {
  }
}
