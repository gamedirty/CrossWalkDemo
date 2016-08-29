# CrossWalkDemo
一个简单的混合开发框架，有js和Java native的交互，并结合了crosswalk引擎。
crosswalk引擎主要是为了适配Android4.2到Android4.3保证性能问题。
使用了开源的js and native通信的框架[JsBridge](https://github.com/lzyzsd/JsBridge)，主要实现了将通信框架和crosswalk引擎进行适配。工程中的library模组和libraryforwebview分别是适配crosswalk和Android  webview的JsBridge依赖。