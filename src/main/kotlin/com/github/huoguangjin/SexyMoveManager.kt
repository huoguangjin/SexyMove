package com.github.huoguangjin

import com.intellij.openapi.Disposable
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener

class SexyMoveManager : Disposable, EditorFactoryListener {

  init {
    EditorFactory.getInstance().addEditorFactoryListener(this, this)
  }

  override fun editorCreated(event: EditorFactoryEvent) {
    val editor = event.editor
    if (editor.isOneLineMode) {
      return
    }

    val document = editor.document
    if (!document.isWritable) {
      SexyMoveAction.toggle(editor)
    }
  }

  override fun dispose() {
  }
}
