package com.github.huoguangjin

import com.intellij.openapi.options.ConfigurableUi
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindIntValue
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent
import kotlin.math.roundToInt

class SexyMoveSettingsUI : ConfigurableUi<SexyMoveSettings> {

  private val settings = SexyMoveSettings.getInstance()

  private val ui: DialogPanel by lazy(LazyThreadSafetyMode.NONE) {
    panel {
      row("Min scroll line:") {
        spinner(1..1000).bindIntValue(settings::minScrollLine)
      }

      row("Min scroll column:") {
        spinner(1..1000).bindIntValue(settings::minScrollColumn)
      }

      row("Scroll by page percent:") {
        spinner(0..1000).bindIntValue(
          { (settings.scrollPagePercent * 100).roundToInt() },
          { settings.scrollPagePercent = it / 100f })
        label("%")
      }

      row("Move caret to page percent:") {
        spinner(0..1000).bindIntValue(
          { (settings.moveCaretPagePercent * 100).roundToInt() },
          { settings.moveCaretPagePercent = it / 100f })
        label("%")
      }

      separator()

      row {
        checkBox("Active in readonly files").bindSelected(settings::activeInReadonlyFiles)
      }

      row {
        checkBox("Active always").bindSelected(settings::activeAlways)
      }
    }
  }

  override fun getComponent(): JComponent = ui

  override fun isModified(settings: SexyMoveSettings): Boolean {
    return ui.isModified()
  }

  override fun apply(settings: SexyMoveSettings) {
    ui.apply()
  }

  override fun reset(settings: SexyMoveSettings) {
    ui.reset()
  }
}
