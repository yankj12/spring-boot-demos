# springboot-security

SpringBoot结合实现权限管理

## 使用说明

### 打包

```
mvn clean package spring-boot:repackage
```

## 后续改进

## 常见问题

### Maven Repositories

All GA releases (i.e. versions ending in .RELEASE) are deployed to Maven Central, so no additional Maven repositories need to be declared in your pom.

If you are using a SNAPSHOT version, you will need to ensure you have the Spring Snapshot repository defined as shown below:

```pom.xml.
<repositories>
<!-- ... possibly other repository elements ... -->
<repository>
	<id>spring-snapshot</id>
	<name>Spring Snapshot Repository</name>
	<url>https://repo.spring.io/snapshot</url>
</repository>
</repositories>
```

## 参考资料

[spring-security 5.2.0.BUILD-SNAPSHOT-doc](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/)

[spring-security 5.1.5.RELEASE-doc](https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/htmlsingle/)

[spring-security 4.2.12.RELEASE-doc](https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/reference/htmlsingle/)

[Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture)

[SpringBoot+mybatis+springsecurity实现用户角色数据库管理](https://www.jianshu.com/p/155ec4272aa4)