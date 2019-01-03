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