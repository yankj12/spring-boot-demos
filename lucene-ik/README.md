# Lucene-IK

SpringBoot结合lucene和ik分词的demo

## 常见问题

### 提示food_name找不到对应的列，但是我们表和实体类中都是使用的foodName

Hibernate 5.0命名策略使用naming-strategy不起作用

SpringBoot jpa 1.3.3版本以上，Hibernate5.0的新特性就是其中一个坑，我们会发现我们配置的naming-strategy不生效了

上面这么信息，其实就是想表达如果想升级Hibernate到5.1的话，那么之前的

hibernate.ejb.naming_strategy将不再被支持，大概应该改为如下配置：

```
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

hibernate5之后如何和SpringBoot结合，可以参考如下链接

[神の笔记 Spring Boot 整合 Hibernate5 ](http://www.cnblogs.com/xiongjiajia/p/7553287.html)

## 参考资料

[lucene+springboot+ik Analyzer实现mysql数据库高亮查询](https://blog.csdn.net/biaoge0310/article/details/79115536)

[spring boot+全文搜索框架lucene](https://blog.csdn.net/weixin_38336658/article/details/80304326)

[Spring Boot 中使用 Java API 调用 lucene](https://segmentfault.com/a/1190000011916639)

lucene增量索引，可以参考下面的链接

[【手把手教你全文检索】Lucene索引的【增、删、改、查】](https://www.cnblogs.com/xing901022/p/3940243.html)

[Lucene5学习之增量索引(Zoie)](https://yq.aliyun.com/articles/45394)

[lucene 4.6 为数据库建立增量索引](https://blog.csdn.net/th676759829/article/details/17753803)

也有网友建议查看lucene in action

[SpringBoot系列(6)---SpringBoot-JPA](https://blog.csdn.net/tony308001970/article/details/74999680)

### lucene索引excel等文件内容

使用poi等解析word或者excel中内容，进行索引

[使用Lucene索引和检索POI数据](https://www.cnblogs.com/luxiaoxun/p/5020247.html)

使用tika

[【Lucene4.8教程之二】索引](https://www.cnblogs.com/jinhong-lu/p/4559446.html)

[Tika常见格式文件抽取内容并做预处理](https://www.cnblogs.com/baiboy/p/tika.html)

[【Tika基础教程之一】Tika基础教程](https://www.cnblogs.com/eaglegeek/p/4557910.html)

[TIKA提取MS Office文件 ](https://www.yiibai.com/tika/tika_extracting_ms_office_files.html)

使用日志

[SpringBoot默认日志框架配置](https://blog.csdn.net/flysun3344/article/details/80555746)

[SpringBoot的日志管理](https://www.cnblogs.com/zhangzhen894095789/p/6640808.html)

打印日志的时候，将对象转成json字符串打印到日志中

[Jackson数据绑定 ](https://www.yiibai.com/jackson/jackson_data_binding.html)