# 说明文档

## appcore
框架核心部分
## commonui
公共 ui 部分如全局的 titlebar 全局的弹窗风格等
## rxpermission
6.0+ 运行时权限库，支持 8.0 应用安装权限
## thirdlibs
常用功能的第三方集成
- browser x5浏览服务相关封装,包含 JS 交互等
- mobilepay 移动支付相关集成 `因微信支付 SDK 路径及类名限制使用微信支付必须在主 App java源码根目录新
建包 wxapi 再创建 WXPayEntryActivity.java 类继承 WeChatPayActivity manifest 中`
## common_sdk_build.gradle
框架基础 module 的公共 gradle 信息配置文件
## config.gradle
使用到的各种版本信息及依赖库信息统一管理配置文件
## gradle.properties
自定义 gradle 参数
## 注意事项
- 腾讯 X5 浏览服务需要将 APP 以 32 位模式运行，应用打包时只打 32 位 so 库
