# appcore
整套框架的基础核心库，包含一些基础框架的封装
## cache
磁盘缓存工具封装，基于 DiskLruCache 封装
## eventbus
eventbus3.0 注册工具类，避免多次调用注册、注销时抛出异常
## framework
mvp 框架包
### annotation ：注解
### app ：生命周期管理核心
### base ：mvp 基类
## http
网络请求库
## imageloader
图片加载库封装
## permission
权限请求库封装，包含：6.0+ 运行时权限、8.0+ 应用安装权限
## transmitter
简单上传下载封装
## utils
常用工具类
### code
加解密工具包
### format
格式 format 工具包
### log
日志工具包
### system
系统工具包
- AppUtils：可获取全局的 Context 及应用版本信息等等
- ContactUtils：系统联系人工具类，打电话发短息功能
- ResourceUtils：资源工具类，通过 id 直接获取资源对象
- SoftInputUtils：软键盘工具类，显示隐藏软键盘、获取软键盘显示状态