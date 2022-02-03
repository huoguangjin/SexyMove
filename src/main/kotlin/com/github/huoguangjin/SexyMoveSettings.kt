package com.github.huoguangjin;

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "SexyMove", storages = [Storage("SexyMove.xml")])
data class SexyMoveSettings(
  var minScrollLine: Int = 12,
  var minScrollColumn: Int = 16,
  var scrollPagePercent: Int = 50,
  var moveCaretPagePercent: Int = 25,
  var activeInReadonlyFiles: Boolean = true,
  var activeAlways: Boolean = false,
) : PersistentStateComponent<SexyMoveSettings> {

  override fun getState() = this

  override fun loadState(state: SexyMoveSettings) {
    XmlSerializerUtil.copyBean(state, this)
  }

  companion object {
    fun getInstance(): SexyMoveSettings {
      return ApplicationManager.getApplication().getService(SexyMoveSettings::class.java)
    }
  }
}
