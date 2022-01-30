package com.github.huoguangjin

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAwareToggleAction

class SexyMoveToggleAction : DumbAwareToggleAction() {

  override fun isSelected(e: AnActionEvent): Boolean {
    val editor = e.getRequiredData(CommonDataKeys.EDITOR)
    return SexyMoveAction.isEnabled(editor)
  }

  override fun setSelected(e: AnActionEvent, state: Boolean) {
    val editor = e.getRequiredData(CommonDataKeys.EDITOR)
    SexyMoveAction.toggle(editor)
  }

  override fun update(e: AnActionEvent) {
    val editor = e.getData(CommonDataKeys.EDITOR)
    e.presentation.isEnabled = editor != null && !editor.isOneLineMode
  }
}
