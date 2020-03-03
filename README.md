# 说明文档
组件化思路参考文章
## app 不包含任何业务代码的壳工程
## sdk_core
基础 SDK
## uires
公共 ui 部分如全局的 titlebar 全局的弹窗风格等
## functionlibs
功能独立的功能组件库
- pay 移动支付相关集成 `因微信支付 SDK 路径及类名限制使用微信支付必须在主 App java源码根目录新
建包 wxapi 再创建 WXPayEntryActivity.java 类继承 WeChatPayActivity manifest 中`
## components_app_build.gradle
组件 App 的公共 build.gradle 配置部分
## config.gradle
使用到的各种版本信息及依赖库信息统一管理配置文件
## gradle.properties
自定义 gradle 参数
