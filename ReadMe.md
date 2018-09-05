# 说明文档

## appcore
框架核心部分
## commonui
公共 ui 部分如全局的 titlebar 全局的弹窗风格等
## thirdlibs
常用功能的第三方集成
- browser x5浏览服务相关封装,包含 JS 交互等
- mobilepay 移动支付相关集成 `因微信支付 SDK 路径及类名限制使用微信支付必须在主 App java源码根目录新
建包 wxapi 再创建 WXPayEntryActivity.java 类继承 WeChatPayActivity manifest 中`