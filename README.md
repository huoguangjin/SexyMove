# SexyMove

![Build](https://github.com/huoguangjin/SexyMove/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
Toggle SexyMove mode to scroll and move your editor in a sexy way (like `Vim` but much simpler).

- <kbd>Ctrl</kbd>+<kbd>'</kbd>(<kbd>⌘</kbd>+<kbd>'</kbd> on mac): Enter SexyMove mode
- <kbd>Escape</kbd> or <kbd>i</kbd>: Exit SexyMove mode
- <kbd>h</kbd>/<kbd>j</kbd>/<kbd>k</kbd>/<kbd>l</kbd>: Scroll left/down/up/right
- <kbd>u</kbd>/<kbd>d</kbd>: Scroll page up/down
- <kbd>b</kbd>/<kbd>e</kbd>: Move the caret to previous/next word
- <kbd>[</kbd>/<kbd>]</kbd>: Navigate to previous/next location
- <kbd>a</kbd>/<kbd>s</kbd>: Switch to left/right tab
- <kbd>z</kbd>: Scroll to the caret
- <kbd>/</kbd>: Find in current file
- <kbd>Enter</kbd>: Move the caret to center
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "SexyMove"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/huoguangjin/SexyMove/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
