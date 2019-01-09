# springboot-bootstrap

bootstrap做前端，SpringBoot做后端的一个增删改查的demo

## 安装bootstrap

### npm安装

国内npm速度不够，配置淘宝镜像

参考

[淘宝 NPM 镜像](http://npm.taobao.org/)

[npm配置国内镜像资源+淘宝镜像](https://blog.csdn.net/qq_39207948/article/details/79449633)

查询到淘宝NPM镜像地址

```CMD
https://registry.npm.taobao.org
```

命令行配置npm淘宝镜像

```CMD
持久使用 
npm config set registry https://registry.npm.taobao.org
 
// 配置后可通过下面方式来验证是否成功
npm config get registry

// 或
npm info express
```

### 使用编译后的文件

## 常见问题

springboot 使用thymeleaf 模板引擎时报错org.xml.sax.SAXParseException: 元素类型 "link" 必须由匹配的结束标记 "" 终止

当springboot 使用thymeleaf 模板引擎时报错org.xml.sax.SAXParseException: 元素类型 “link” 必须由匹配的结束标记 “” 终止，org.xml.sax.SAXParseException: 元素类型 “meta” 必须由匹配的结束标记 “” 终止。等等问题。

解决方法是在pom.xml 文件中指定具体的thymeleaf 版本 具体如下

```XML
<properties>
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding> 	<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	<java.version>1.8</java.version>
	<!--以下两项需要如果不配置，解析themleaft 会有问题-->
	<thymeleaf.version>3.0.2.RELEASE</thymeleaf.version> 	<thymeleaf-layout-dialect.version>2.0.5</thymeleaf-layout-dialect.version>
</properties>
 
```

参考资料 [springboot 使用thymeleaf 模板引擎时报错org.xml.sax.SAXParseException: 元素类型 "link" 必须由匹配的结束标记 "" 终止](https://blog.csdn.net/github_38151745/article/details/78191970)