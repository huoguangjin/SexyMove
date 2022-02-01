package com.github.huoguangjin

import com.intellij.openapi.options.ConfigurableBase

class SexyMoveConfigurable : ConfigurableBase<SexyMoveSettingsUI, SexyMoveSettings>("SexyMove", "SexyMove", null) {

  override fun getSettings() = SexyMoveSettings.getInstance()

  override fun createUi() = SexyMoveSettingsUI()
}
