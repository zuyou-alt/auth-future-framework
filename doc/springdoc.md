带有 spring-boot 的 OpenAPI 3 库 作者：Badr NASS LAHSEN springdoc-openapi在开放集体上。如果您这个项目❤️考虑成为赞助商。 本项目由

![在这里插入图片描述](https://img-blog.csdnimg.cn/e1331ef5345f4bf38bf54f39bd269766.png)

## 1\. 简介

springdoc-openapi Java 库有助于使用 Spring 引导项目自动生成 API 文档。 通过在运行时检查应用程序来根据 Spring 配置、类结构和各种注释推断 API 语义。springdoc-openapi

自动生成 JSON/YAML 和 HTML 格式 API 中的文档。 本文档可以通过使用 swagger-api 注释的评论来完成。

此库支持：

-   OpenAPI 3
-   Spring-boot (v1, v2 and v3)
-   JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
-   Swagger-ui
-   OAuth 2
-   GraalVM native images

以下视频介绍了库：  
![在这里插入图片描述](https://img-blog.csdnimg.cn/81aba86445c04f61b4456b99c7111401.png)  
![在这里插入图片描述](https://img-blog.csdnimg.cn/937812cbac6d441893453415382c39d5.gif#pic_center)

这是一个基于社区的项目，不是由Spring框架贡献者（Pivotal）维护的。

## 2\. 入门

对于 spring-boot 和 swagger-ui 之间的集成，请将库添加到项目依赖项列表中（无需其他配置）

```c
<dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-ui</artifactId> <version>1.6.14</version> </dependency>
```

这将自动将 swagger-ui 部署到 spring-boot 应用程序：

-   文档将以HTML格式提供，使用官方的swagger-ui jar
-   然后，Swagger UI页面将在以下网址上提供，OpenAPI描述将在以下json格式的URL上提供：http://server:port/context-path/swagger-ui.html  
    http://server:port/context-path/v3/api-docs

o 服务器：服务器名称或 IP o 端口：服务器端口

o 上下文路径：应用程序的上下文路径

-   文档也可以以yaml格式提供，路径如下：/v3/api-docs.yaml

![在这里插入图片描述](https://img-blog.csdnimg.cn/18b2c4f0d03c4d6cbb240a383d73ba25.png)

```c
# swagger-ui custom path
springdoc.swagger-ui.path=/swagger-ui.html
```

## 3\. Springdoc-openapi 模块

### 3.1. 概述

![在这里插入图片描述](https://img-blog.csdnimg.cn/a2a93b36e8934627a069100536576820.png)

### 3.2. Spring WebMvc 支持

-   文档将在以下 json 格式的 url 中提供：http://server:port/context-path/v3/api-docs o 服务器：服务器名称或 IP o 端口：服务器端口

    o 上下文路径：应用程序的上下文路径

-   文档也将以yaml格式提供，路径如下：/v3/api-docs.yaml
-   将库添加到项目依赖项列表中。（无需其他配置）

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-webmvc-core</artifactId> <version>1.6.14</version> </dependency>
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/66802e02627d4cebaf199141c8382b8a.png)  
![在这里插入图片描述](https://img-blog.csdnimg.cn/157cb23eb74f43458c5f5bad78678c56.png)

```c
# /api-docs endpoint custom path
springdoc.api-docs.path=/api-docs
```

### 3.3. Spring WebFlux 支持

-   文档也可以以yaml格式提供，路径如下：/v3/api-docs.yaml
-   将库添加到项目依赖项列表中（无需其他配置）

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-webflux-ui</artifactId> <version>1.6.14</version> </dependency>
```

### 3.4. Spring Hateoas 支持

对Spring Hateoas的支持可以使用依赖项springdoc-openapi-hateoas获得。 使用 Spring Hateoas 的项目应该将此依赖项与 springdoc-openapi-ui 依赖项相结合。 这种依赖关系支持Spring Hateoas格式。

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-hateoas</artifactId> <version>1.6.14</version> </dependency>

```

### 3.5. Spring Data Rest 支持

使用的项目可以结合依赖项添加以下依赖项。 此依赖项支持以下类型： 和批注。spring-data-rest、springdoc-openapi-ui、spring-boot-starter-data-rest、@RepositoryRestResource、QuerydslPredicate

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-data-rest</artifactId> <version>1.6.14</version> </dependency>

```

### 3.6. Spring Security 支持

对于使用 spring-security 的项目，您应该添加以下依赖项，并结合 springdoc-openapi-ui 依赖项： 此依赖项有助于忽略@AuthenticationPrincipal以防其在 REST 控制器上使用。

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-security</artifactId> <version>1.6.14</version> </dependency>

```

### 3.7. Spring Native support

springdoc-openapi，支持开箱即用的 GraalVM 本机映像。 如果应用程序使用 spring-native，则应将以下依赖项与 （ 或 ） 依赖项结合使用： - 此依赖项有助于对 springdoc-openapi 的本机支持（仅在 之后可用）。springdoc-openapi-uispringdoc-openapi-webflux-uiv1.5.13

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-native</artifactId> <version>1.6.14</version> </dependency>
```

这是兼容性矩阵，其中显示了经过测试/验证的版本：springdoc-openapi

| springdoc-openapi Version | spring-native Version |
| --- | --- |
| `1.6.11+` | `0.12.1` |
| `1.6.7` | `0.11.4` |
| `1.6.3` | `0.11.1` |
| `1.6.0` | `0.11.0` |
| `1.5.13` | `0.11-RC1` |
| `1.5.12` | `0.10.5` |
| `1.5.11` | `0.10.3` |
| `1.5.10` | `0.10.1` |
| `1.5.9` | `0.9.2` |

![在这里插入图片描述](https://img-blog.csdnimg.cn/8a4f1310faad4623b01fe524b5828fb7.png)

### 3.8. Actuator 支持

为了显示端点，只需添加以下属性：spring-boot-actuator

```c
springdoc.show-actuator=true
```

从版本1.5.1开始，可以在执行器端口上公开 swagger-ui 和 openapi 端点。  
![在这里插入图片描述](https://img-blog.csdnimg.cn/e4dcd30be4444b05a0145fe360523b93.png)  
若要公开 swagger-ui，应在管理端口上设置

```c
springdoc.use-management-port=true # This property enables the openapi and swagger-ui endpoints to be exposed beneath the actuator base path.
management.endpoints.web.exposure.include=openapi, swagger-ui 该属性允许openapi和swagger-ui端点暴露在执行器基本路径下。
management.endpoints.web.exposure。包括= openapi swagger-ui
```

启用后，您还应该能够在以下位置看到 springdoc-openapi 端点：（主机和端口取决于您的设置） -http://serverName:managementPort/actuator

例如，如果您有以下设置：  
将有两个端点可用：

1.  保存 OpenAPI 定义的 REST API：  
    http://serverName:managementPort/actuator/openapi

2.  一个终结点，用于路由到 swagger-ui：  
    http://serverName:managementPort/actuator/swagger-ui


```c
management.server.port=9090
```

对于这个例子，你还应该能够看到springdoc-openapi端点：

-   http://serverName:9090/actuator
-   http://serverName:9090/actuator/swagger-ui
-   http://serverName:9090/actuator/openapi

所有路径属性在 时都不适用。`springdoc-openapispringdoc.use-management-port=true`  
![在这里插入图片描述](https://img-blog.csdnimg.cn/4ec619724f0e4f9c85002fa35ec66af2.png)  
此外，还可以将此属性与现有属性结合使用，以在 swagger-ui 中显示执行器终结点。

```c
springdoc.show-actuator=true
```

启用后： - 默认情况下，将添加执行器端点的专用组。 - 如果没有为应用程序定义组，则将添加一个默认组。

然后，可以通过执行器端口访问 swagger-upi：

-   http://serverName:managementPort/actuator/swagger-ui
-   如果管理端口与应用程序端口不同且未定义但设置为 true：`springdoc.use-management-portspringdoc.show-actuator`
-   然后，可以通过应用程序端口访问 swagger-ui。例如：`http://serverName:applicationPort/swagger-ui.html`
-   默认情况下，将添加执行器端点的专用组。
-   如果未为应用程序定义任何组，则将添加一个默认组。

### 3.9. Spring Cloud Function Web 支持

spring-cloud-function-web自动将 Java 函数公开为 REST 端点。 \*从v1.6.3版本开始，增加了对功能端点的支持。

-   这些启动器将显示端点的 OpenAPI 描述。spring-cloud-function-web 1.如果您使用的是 ，只需添加依赖项即可。spring-webspringdoc-openapi-ui

    2.如果您使用的是 ，只需添加依赖项即可。spring-webfluxspringdoc-openapi-webflux-ui


输出的自定义可以通过以下注释或带有注释以编程方式实现：和。 对于注释用法，您有： \* ：如果自定义与单个 REST API 相关，则可以单独使用。 使用 时，不强制要求填充路径OpenApiCustomize 、 @RouterOperations 、 @RouterOperation 、 @RouterOperation 、 @RouterOperation

-   @RouterOperation，包含批注。 如果声明了属性 beanMethod，则还可以将注释放在 Bean 方法级别。@Operation@Operation  
    ![在这里插入图片描述](https://img-blog.csdnimg.cn/33a93a12dbea45efa5ef32f3ba93b7d6.png)

```c
@Bean
@RouterOperation(operation = @Operation(description = "Say hello", operationId = "hello", tags = "persons", responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class)))))
public Supplier<PersonDTO> helloSupplier() { return () -> new PersonDTO();
}
```

一些代码示例可在演示的GITHUB上找到：  
[使用Spring Cloud Function Web 的示例应用程序](https://github.com/springdoc/springdoc-openapi-demos/tree/master/springdoc-openapi-spring-cloud-function)

### 3.10 Kotlin 支持

对于使用 Kotlin 的项目，您应该添加以下依赖项。 这种依赖关系改进了对 Kotlin 类型的支持：

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-kotlin</artifactId> <version>1.6.14</version> </dependency>

```

-   如果您使用的是 spring-web，则应将模块与 .springdoc-openapi-kotlinspringdoc-openapi-ui
-   如果您使用的是 spring-webflux，则应将模块与 .springdoc-openapi-kotlinspringdoc-openapi-webflux-ui

### 3.11 Groovy 支持

对于使用 Groovy 的项目，您应该添加以下依赖项，并结合 springdoc-openapi-ui 依赖项： 这种依赖关系改进了对 Kotlin 类型的支持：

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-groovy</artifactId> <version>1.6.14</version> </dependency>

```

### 3.12. Javadoc 支持

对于想要启用 javadoc 支持的项目，应将以下依赖项与依赖项结合使用：springdoc-openapi-ui

```c
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-javadoc</artifactId> <version>1.6.14</version> </dependency>
```

这种依赖关系改进了对javadoc标签和注释的支持：

-   方法的 javadoc 注释：解析为描述@Operation
-   @return ：解析为响应描述@Operation
-   属性的 javadoc 注释：解析为此字段的“@Schema”描述。  
    此依赖项基于 [therapi-runtime-javadoc](https://github.com/dnault/therapi-runtime-javadoc) 库

![在这里插入图片描述](https://img-blog.csdnimg.cn/d4b96611bfc34b4fb5b7d8bed35c0791.png)

```c
build> <plugins> <plugin> <groupId>org.apache.maven.plugins</groupId> <artifactId>maven-compiler-plugin</artifactId> <configuration> <annotationProcessorPaths> <path> <groupId>com.github.therapi</groupId> <artifactId>therapi-runtime-javadoc-scribe</artifactId> <version>0.15.0</version> </path> </annotationProcessorPaths> </configuration> </plugin> </plugins> </build>
```

**Tip：如果同时存在swagger-annotation描述和javadoc注释，将使用swagger注释描述的值**

## 4.Springdoc-openapi特性

### 4.1. 添加 API 信息和安全文档

该库使用 spring-boot 应用程序自动配置的软件包来扫描 spring bean 中的以下注释：OpenAPIDefinition 和 Info。 这些注释声明了 API 信息：标题、版本、许可证、安全性、服务器、标记、安全性和外部文档。 为了更好地生成文档，请在 Spring 管理的 Bean 中声明@OpenAPIDefinition和@SecurityScheme注释。

### 4.2. 使用 @ControllerAdvice 处理 REST 的错误

要自动生成文档，请确保所有方法都使用注释声明 HTTP 代码响应：@ResponseStatus

### 4.3. 禁用端点springdoc-openapi

要禁用终结点（默认情况下为 /v3/api-docs），请使用以下属性：springdoc-openapi

```c
# Disabling the /v3/api-docs endpoint
springdoc.api-docs.enabled=false
```

### 4.4. 禁用swagger用户界面

要禁用 swagger-ui，请使用以下属性：

```c
# Disabling the swagger-ui
springdoc.swagger-ui.enabled=false
```

### 4.5 Swagger-ui 的 用户界面配置

该库支持 swagger-ui 官方属性：

-   https://swagger.io/docs/open-source-tools/swagger-ui/usage/configuration/

您需要将 swagger-ui 属性声明为 spring-boot 属性。 所有这些属性都应使用以下前缀声明：springdoc.swagger-ui

### 4.6 选择要包含在文档中的其余控制器

此外，要从 swagger-annotation 中@Hidden注释，可以使用包或路径配置来限制生成的 OpenAPI 描述。  
对于要包含的包列表，请使用以下属性：

```c
# Packages to include
springdoc.packagesToScan=com.package1, com.package2

```

对于要包含的路径列表，请使用以下属性：

```c
# Paths to include
springdoc.pathsToMatch=/v1, /api/balance
```

### 4.7. 带有功能端点的 Spring-webflux/WebMvc.fn

从 v1.5.0 版本开始，由于 spring 框架中的此增强功能，引入了功能性 DSL：#25938

它是注释的替代功能 API。@RouterOperations

这是一个示例DSL，用于生成webflux / WebMvc.fn REST端点的OpenAPI描述：

```c
@Bean
RouterFunction<?> routes() { return route().GET("/foo", HANDLER_FUNCTION, ops -> ops .operationId("hello") .parameter(parameterBuilder().name("key1").description("My key1 description")) .parameter(parameterBuilder().name("key2").description("My key2 description")) .response(responseBuilder().responseCode("200").description("This is normal response description")) .response(responseBuilder().responseCode("404").description("This is another response description")) ).build();
}
```

以下是一些示例代码的链接：

-   [你好路由器](https://github.com/springdoc/springdoc-openapi/blob/master/springdoc-openapi-webflux-core/src/test/java/test/org/springdoc/api/app90/HelloRouter.java)
-   [行情路由器](https://github.com/springdoc/springdoc-openapi/blob/master/springdoc-openapi-webflux-core/src/test/java/test/org/springdoc/api/app90/quotes/QuotesRouter.java)
-   [图书路由器](https://github.com/springdoc/springdoc-openapi/blob/master/springdoc-openapi-webflux-core/src/test/java/test/org/springdoc/api/app90/book/BookRouter.java)
-   [员工路由器](https://github.com/springdoc/springdoc-openapi/blob/master/springdoc-openapi-webflux-core/src/test/java/test/org/springdoc/api/app90/employee/EmployeeRouter.java)
-   [位置路由器](https://github.com/springdoc/springdoc-openapi/blob/master/springdoc-openapi-webflux-core/src/test/java/test/org/springdoc/api/app90/position/PositionRouter.java)  
    以及使用功能终结点 DSL 的演示代码：
-   [使用函数式 DSL 的 webflux 应用程序示例](https://github.com/springdoc/springdoc-openapi-demos/tree/master/springdoc-openapi-spring-boot-2-webflux-functional)

从v1.3.8版本开始，增加了对功能端点的支持。 为此添加了两个主要注释：和 。@RouterOperations@RouterOperation

只有带有 和 的 REST API 才能显示在 swagger-ui 上。@RouterOperations @RouterOperation

-   @RouterOperation：如果路由器 Bean 包含一个与 REST API 相关的路由，则可以单独使用。 使用@RouterOperation时，不强制填充路径
-   @RouterOperation，可以直接引用 Spring Bean（beanClass 属性）和底层方法（beanMethod 属性）：Springdoc-openapi，然后将检查此方法和此方法级别的 swagger 注释。

```c
@Bean
@RouterOperation(beanClass = EmployeeService.class, beanMethod = "findAllEmployees")
RouterFunction<ServerResponse> getAllEmployeesRoute() { return route(GET("/employees").and(accept(MediaType.APPLICATION_JSON)), req -> ok().body( employeeService().findAllEmployees(), Employee.class));
}
```

-   @RouterOperation，包含批注。 如果声明了属性 beanMethod，则还可以将注释放在 Bean 方法级别。@Operation @Operation

    ![在这里插入图片描述](https://img-blog.csdnimg.cn/d979175360db408597c686af582ede68.png)


```c
@Bean
@RouterOperation(operation = @Operation(operationId = "findEmployeeById", summary = "Find purchase order by ID", tags = { "MyEmployee" }, parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Employee Id") }, responses = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Employee.class))), @ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied"), @ApiResponse(responseCode = "404", description = "Employee not found") }))
RouterFunction<ServerResponse> getEmployeeByIdRoute() { return route(GET("/employees/{id}"), req -> ok().body( employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class));
}
```

-   @RouterOperations：如果路由器 Bean 包含多个路由，则应使用此注释。 使用路由器操作时，必须填写路径属性。
-   A ，包含许多 。@RouterOperations@RouterOperation

```c
@RouterOperations({ @RouterOperation(path = "/getAllPersons", beanClass = PersonService.class, beanMethod = "getAll"), @RouterOperation(path = "/getPerson/{id}", beanClass = PersonService.class, beanMethod = "getById"), @RouterOperation(path = "/createPerson", beanClass = PersonService.class, beanMethod = "save"), @RouterOperation(path = "/deletePerson/{id}", beanClass = PersonService.class, beanMethod = "delete") })
@Bean
public RouterFunction<ServerResponse> personRoute(PersonHandler handler) { return RouterFunctions .route(GET("/getAllPersons").and(accept(MediaType.APPLICATION_JSON)), handler::findAll) .andRoute(GET("/getPerson/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById) .andRoute(POST("/createPerson").and(accept(MediaType.APPLICATION_JSON)), handler::save) .andRoute(DELETE("/deletePerson/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
}
```

所有使用@RouterOperation填写的文档，都可以由路由器功能数据来完成。 为此，字段必须帮助唯一标识相关路由。 使用以下条件扫描与注记相关的唯一路径：@RouterOperation 、springdoc-openpi @RouterOperation

-   按路径
-   按路径和请求方法
-   按路径和生产
-   按路径和消耗
-   通过路径和请求方法并生成
-   按路径和请求方法并消耗
-   按路径和生产和消费
-   按路径和请求方法生成和使用

一些代码示例可在演示的GITHUB上找到：

和一些项目测试：（从app69到app75）

### 4.8. 与WildFly集成

对于 WildFly 用户，您需要添加以下依赖项才能使 swagger-ui 正常工作：

```c
 <dependency> <groupId>org.webjars</groupId> <artifactId>webjars-locator-jboss-vfs</artifactId> <version>0.1.0</version> </dependency>

```

springdoc-openapi依赖于使用标准文件位置的标准 Spring 配置属性（YML 或属性）。

## 5\. Springdoc-openapi 属性

springdoc-openapi依赖于使用标准文件位置的标准 Spring 配置属性（YML 或属性）。

### 5.1. Springdoc-OpenAPI 核心属性

| 参数名称 | 默认值 | 描述 |
| --- | --- | --- |
| springdoc.api-docs.path | `/v3/api-docs` | `String`，用于 Json 格式的 OpenAPI 文档的自定义路径。 |
| springdoc.api-docs.enabled | `true` | `Boolean`.禁用 springdoc-openapi 端点（默认为 /v3/api-docs）。 |
| springdoc.packages-to-scan | `*` | `List of Strings`.要扫描的包列表（逗号分隔） |
| springdoc.paths-to-match | `/*` | `List of Strings`.要匹配的路径列表（逗号分隔） |
| springdoc.produces-to-match-to | `/*` | `List of Strings`.生成要匹配的媒体类型列表（逗号分隔） |
| springdoc.headers-to-match | `/*` | `List of Strings`.要匹配的标头列表（逗号分隔） |
| springdoc.consumptions-to-matchs. | `/*` | `List of Strings`.要匹配的消耗媒体类型列表（逗号分隔） |
| springdoc.paths-to-exclude |  | `List of Strings`.要排除的路径列表（逗号分隔） |
| springdoc.packages-to-exclude |  | `List of Strings`.要排除的包列表（逗号分隔） |
| springdoc.default-consumptions-media-type | `application/json` | `String`.默认使用媒体类型。 |
| springdoc.default-produces-media-type | `**/**` | `String`.默认生成媒体类型。 |
| springdoc.cache.disabled | `false` | `Boolean`.禁用计算的 OpenAPI 的 springdoc-openapi 缓存。 |
| 弹簧文档显示执行器 | `false` | `Boolean`.显示执行器端点。 |
| springdoc.auto-tag-classes | `true` | `Boolean`.禁用 springdoc-openapi 自动标记。 |
| springdoc.model-and-view-allow | `false` | `Boolean`.允许带有 ModelAndView 的 RestControllers 返回出现在 OpenAPI 描述中。 |
| springdoc.override-with-generic-response | `true` | `Boolean`.如果为 true，则自动将@ControllerAdvice响应添加到所有生成的响应中。 |
| springdoc.api-docs.groups.enabled | `true` | `Boolean`.禁用 springdoc-openapi 组。 |
| springdoc.group-configs\[0\].group |  | `String`.组名称 |
| springdoc.group-configs\[0\].displayName |  | `String`.组的显示名称。 |
| springdoc.group-configs\[0\].packages-to-scan | `*` | `List of Strings`.要扫描组的包列表（逗号分隔） |
| springdoc.group-configs\[0\].paths-to-match | `/*` | `List of Strings`.要为组匹配的路径列表（逗号分隔） |
| springdoc.group-configs\[0\].paths-to-exclude | \`\` | `List of Strings`.要为组排除的路径列表（逗号分隔） |
| springdoc.group-configs\[0\].packages-to-exclude |  | `List of Strings`.要为组排除的包列表（逗号分隔） |
| springdoc.group-configs\[0\].produces-to-match | `/*` | `List of Strings`.生成要匹配的媒体类型列表（逗号分隔） |
| springdoc.group-configs\[0\].consumes-to-match | `/*` | `List of Strings`.要匹配的消耗媒体类型列表（逗号分隔） |
| springdoc.group-configs\[0\].headers-to-match | `/*` | `List of Strings`.要匹配的标头列表（逗号分隔） |
| springdoc.webjars.prefix | `/webjars` | `String`，要更改 webjars 前缀，该前缀可见 swagger-ui 的 URL 为 spring-webflux。 |
| springdoc.api-docs.resolve-schema-properties | `false` | `Boolean`.在@Schema（名称、标题和说明）上启用属性解析程序。 |
| springdoc.remove-broken-reference-definition | `true` | `Boolean`.禁用删除损坏的引用定义。 |
| springdoc.writer-with-default-pretty-printer | `false` | `Boolean`.启用OpenApi规范的漂亮打印。 |
| springdoc.model-converters.deprecating-converter.enabled | `true` | `Boolean`.禁用弃用模型转换器。 |
| springdoc.model-converters.polymorphic-converter.enabled | `true` | `Boolean`.禁用多态模型转换器。 |
| springdoc.model-converters.pageable-converter.enabled | `true` | `Boolean`.禁用可分页模型转换器。 |
| springdoc.model-converters.sort-converter.enabled | `true` | `Boolean`.禁用排序转换器。 |
| springdoc.use-fqn | `false` | `Boolean`.启用完全限定名称。 |
| springdoc.show-login-endpoint | `false` | `Boolean`.使 Spring 安全登录端点可见。 |
| springdoc.pre-load-enabled | `false` | `Boolean`.预加载设置，用于在应用程序启动时加载 OpenAPI。 |
| springdoc.writer-with-order-by-keys | `false` | `Boolean`.启用确定性/字母顺序排序。 |
| springdoc.use-management-port | `false` | `Boolean`.在执行器管理端口上公开招摇 UI。 |
| springdoc.disable-i18n | `false` | `Boolean`.使用 i18n 禁用自动翻译。 |
| springdoc.show-spring-cloud-functions | `true` | `Boolean`.显示弹簧云函数 Web 终结点。 |
| springdoc.api-docs.version | `openapi_3_0` | `String`.选择或（使用值 ）。`OpenAPI 3.0``OpenAPI 3.1``OPENAPI_3_1` |
| springdoc.default-flat-paramObject | `false` | `Boolean`.默认平展参数。 |
| springdoc.default-support-form-data | `false` | `Boolean`.在指定 api 以接受表单数据时默认设置表单数据的参数。 |

### 5.2. swagger-ui 属性

-   上提供了对 swagger-ui 属性的支持。请参阅[官方文档](https://swagger.io/docs/open-source-tools/swagger-ui/usage/configuration/)。`springdoc-openapi`
-   您可以在文档中使用与 Spring 引导属性相同的 swagger-ui 属性。  
    ![在这里插入图片描述](https://img-blog.csdnimg.cn/86d827c97c3c44059d846bc1a3af1edc.png)

| 参数名称 | 默认值 | 描述 |
| --- | --- | --- |
| springdoc.swagger-ui.path | `/swagger-ui.html` | `String`，用于 swagger-ui HTML 文档的自定义路径。 |
| springdoc.swagger-ui.enabled | `true` | `Boolean`.禁用 swagger-ui 端点（默认情况下为 /swagger-ui.html）。 |
| springdoc.swagger-ui.configUrl | `/v3/api-docs/swagger-config` | `String`.要从中获取外部配置文档的 URL。 |
| springdoc.swagger-ui.layout | `BaseLayout` | `String`.通过插件系统提供的组件的名称，用作 Swagger UI 的顶级布局。 |
| springdoc.swagger-ui.validatorUrl | `validator.swagger.io/validator` | 默认情况下，Swagger UI 会尝试根据 swagger.io 的在线验证器验证规范。您可以使用此参数设置不同的验证程序 URL，例如，对于本地部署的验证[程序验证程序徽章](https://github.com/swagger-api/validator-badge)。将其设置为 ，或者将禁用验证。`none``127.0.0.1``localhost` |
| springdoc.swagger-ui.tryItOutEnabled | `false` | `Boolean`.控制默认情况下是否应启用“试用”部分。 |
| springdoc.swagger-ui.filter | `false` | `Boolean OR String`.如果设置，则启用筛选。顶部栏将显示一个编辑框，可用于筛选显示的标记操作。可以是用于启用或禁用的布尔值，也可以是字符串，在这种情况下，将使用该字符串作为筛选器表达式启用筛选。筛选区分大小写，与标记内任意位置的筛选器表达式匹配。 |
| springdoc.swagger-ui.operationsSorter |  | `Function=(a ⇒ a)`.对每个 API 的操作列表应用排序。它可以是“alpha”（按路径字母数字排序），“method”（按HTTP方法排序）或函数（参见Array.prototype.sort（）以了解排序函数的工作原理）。默认值为服务器返回的顺序不变。 |
| springdoc.swagger-ui.tagsSorter |  | `Function=(a ⇒ a)`.对每个 API 的标记列表应用排序。它可以是“alpha”（按路径字母数字排序）或[函数，请参阅 Array.prototype.sort（）](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort) 以学习如何编写排序函数）。每次传递时，将两个标记名称字符串传递给分拣机。默认值是由 Swagger UI 确定的顺序。 |
| springdoc.swagger-ui.oauth2RedirectUrl | `/swagger-ui/oauth2-redirect.html` | `String`.OAuth 重定向网址。 |
| springdoc.swagger-ui.displayOperationId | `false` | `Boolean`.控制操作 ID 在操作列表中的显示。缺省值为 。`false` |
| springdoc.swagger-ui.displayRequestDuration | `false` | `Boolean`.控制“试用”请求的请求持续时间（以毫秒为单位）的显示。 |
| springdoc.swagger-ui.deepLink | `false` | `Boolean`.如果设置为 ，则启用标签和操作的深层链接。有关更多信息，请参阅 \[深层链接文档\]（/docs/usage/deep-linking.md）。`true` |
| springdoc.swagger-ui.defaultModelsExpandDepth | `1` | `Number`.模型的默认扩展深度（设置为 -1 将完全隐藏模型）。 |
| springdoc.swagger-ui.defaultModelExpandDepth | `1` | `Number`.模型示例部分上模型的默认扩展深度。 |
| springdoc.swagger-ui.defaultModelRendering |  | `String=["example"*, "model"]`.控制首次呈现 API 时模型的显示方式。（用户始终可以通过单击“模型”和“示例值”链接来切换给定模型的渲染。 |
| springdoc.swagger-ui.docExpansion |  | `String=["list"*, "full", "none"]`.控制操作和标记的默认展开设置。它可以是“列表”（仅展开标签）、“完整”（展开标签和操作）或“无”（不展开任何内容）。 |
| springdoc.swagger-ui.maxDisplayTags |  | `Number`.如果设置，将显示的标记操作数限制为最多此数量。默认值为显示所有操作。 |
| springdoc.swagger-ui.showExtensions | `false` | `Boolean`.控制供应商扩展 （） 字段和操作、参数和架构的值的显示。`x-` |
| springdoc.swagger-ui.url |  | `String`.要配置，自定义 OpenAPI 文件的路径。如果使用，将被忽略。`urls` |
| springdoc.swagger-ui.showCommonExtensions | `false` | `Boolean`.控制参数的扩展 （、、、、） 字段和值的显示。`pattern``maxLength``minLength``maximum``minimum` |
| springdoc.swagger-ui.supportedSubmitMethods |  | `Array=["get", "put", "post", "delete", "options", "head", "patch", "trace"]`.启用了“试用”功能的 HTTP 方法列表。空数组禁用所有操作的“试用”。这不会从显示中过滤操作。 |
| springdoc.swagger-ui.queryConfigEnabled | `false` | `Boolean`.自 以来禁用。此参数启用（旧版）通过 URL 搜索参数覆盖配置参数。在启用此功能之前[，请参阅安全公告](https://github.com/swagger-api/swagger-ui/security/advisories/GHSA-qrmm-w75w-3wpx)。`v1.6.0` |
| springdoc.swagger-ui.oauth. additionalQueryStringParams |  | `String`.添加到授权 URL 和令牌 URL 的其他查询参数。 |
| springdoc.swagger-ui.disable-swagger-default-url | `false` | `Boolean`.禁用 swagger-ui 默认宠物商店网址。（从 v1.4.1 开始可用）。 |
| springdoc.swagger-ui.urls\[0\].url |  | `URL`.Topbar 插件使用的 swagger 组的 url。URL 在此数组中的所有项中必须是唯一的，因为它们用作标识符。 |
| springdoc.swagger-ui.urls\[0\].name |  | `String`.Topbar 插件使用的 swagger 组的名称。名称在此数组中的所有项中必须是唯一的，因为它们用作标识符。 |
| springdoc.swagger-ui.urlsPrimaryName |  | `String`.加载 Swagger UI 时将显示的招摇组的名称。 |
| springdoc.swagger-ui.oauth.clientId |  | `String`.默认客户端 ID。必须是字符串。 |
| springdoc.swagger-ui.oauth.clientSecret |  | `String`.默认客户端机密。切勿在生产环境中使用此参数。它公开了重要的安全信息。此功能仅适用于开发/测试环境。 |
| springdoc.swagger-ui.oauth.realm |  | `String`.领域查询参数（适用于 OAuth 1）已添加到授权 URL 和令牌 URL。 |
| springdoc.swagger-ui.oauth.appName |  | `String`.OAuth 应用程序名称，显示在授权弹出窗口中。 |
| springdoc.swagger-ui.oauth.scopeSeparator |  | `String`.用于传递范围的 OAuth 范围分隔符，在调用之前进行编码，默认值为空格（编码值 %20）。 |
| springdoc.swagger-ui.csrf.enabled | `false` | `Boolean`.启用 CSRF 支持 |
| springdoc.swagger-ui.csrf.use-local-storage | `false` | `Boolean`.从本地存储获取 CSRF 令牌。 |
| springdoc.swagger-ui.csrf.use-session-storage | `false` | `Boolean`.从会话存储中获取 CSRF 令牌。 |
| springdoc.swagger-ui.csrf.cookie-name | `XSRF-TOKEN` | `String`.可选的 CSRF，用于设置 CSRF cookie 名称。 |
| springdoc.swagger-ui.csrf.header-name | `X-XSRF-TOKEN` | `String`.可选的 CSRF，用于设置 CSRF 标头名称。 |
| springdoc.swagger-ui.syntaxHighlight.activated | `true` | `Boolean`.是否应激活语法突出显示。 |
| springdoc.swagger-ui.syntaxHighlight.theme | `agate` | `String`…[突出显示.js](https://highlightjs.org/static/demo/)要使用的语法着色主题。（只有这 6 种样式可用。`String=["agate"*, "arta", "monokai", "nord", "obsidian", "tomorrow-night"]` |
| springdoc.swagger-ui.oauth. useBasicAuthentication WithAccessCodeGrant | `false` | `Boolean`.仅针对访问代码流激活。在对 tokenURL 的authorization\_code请求期间，使用 HTTP 基本身份验证方案（具有基本 base64encode（client\_id + client\_secret）的授权标头）传递客户端密码。 |
| springdoc.swagger-ui.oauth. usePkceWithAuthorization CodeGrant | `false` | `Boolean`.仅适用于授权代码流。代码交换的证明密钥为 OAuth 公共客户端带来了增强的安全性。 |
| springdoc.swagger-ui.persistAuthorization | `false` | `Boolean`.如果设置为 true，它将保留授权数据，并且在浏览器关闭/刷新时不会丢失 |
| springdoc.swagger-ui.use-root-path | `false` | `Boolean`.如果设置为 true，则可以直接从应用程序根路径访问 swagger-u。 |

## 6\. Springdoc-openapi 插件

### 6.1. Maven插件

其目的是在构建时生成json和yaml OpenAPI描述。 该插件在集成测试阶段工作，并生成 OpenAPI 描述。 该插件与 spring-boot-maven 插件结合使用。springdoc-openapi-maven-plugin

您可以在集成测试阶段使用 maven 命令对其进行测试：

```c
mvn verify
```

为了使用此功能，您需要在pom的插件部分添加插件声明.xml：

```c
<plugin> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-maven-plugin</artifactId> <version>${spring-boot-maven-plugin.version}</version> <configuration> <jvmArguments>-Dspring.application.admin.enabled=true</jvmArguments> </configuration> <executions> <execution> <goals> <goal>start</goal> <goal>stop</goal> </goals> </execution> </executions>
</plugin>
<plugin> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-maven-plugin</artifactId> <version>1.4</version> <executions> <execution> <id>integration-test</id> <goals> <goal>generate</goal> </goals> </execution> </executions>
</plugin>

```

有关 springdoc-openapi-maven-plugin 的更多自定义设置，您可以查阅插件文档：  
[https://github.com/springdoc/springdoc-openapi-maven-plugin](https://github.com/springdoc/springdoc-openapi-maven-plugin)

### 6.2. Gradle 插件

此插件允许您从 Gradle 构建为 Spring Boot 应用程序生成 OpenAPI 3 规范。

```c
plugins { id("org.springframework.boot") version "2.7.0" id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
}


```

将此插件及其运行时依赖项插件添加到构建文件时，该插件将创建以下任务：

-   forkedSpringBootRun
-   生成OpenApiDocs

```c
gradle clean generateOpenApiDocs
```

有关 的更多自定义配置，可以参考插件文档：springdoc-openapi-gradle-plugin

[https://github.com/springdoc/springdoc-openapi-gradle-plugin](https://github.com/springdoc/springdoc-openapi-gradle-plugin)

## 7\. Springdoc-openapi 实例

### 7.1. springdoc 应用实例

![在这里插入图片描述](https://img-blog.csdnimg.cn/7a2f358408f14a91927ecd87729acfc9.png)

### 7.2 演示应用程序的源代码

[https://github.com/springdoc/springdoc-openapi-demos.git](https://github.com/springdoc/springdoc-openapi-demos)

### 8\. 从SpringFox迁移

-   删除 springfox 和 swagger 2 依赖项。改为添加依赖项。`springdoc-openapi-ui`

```xml
 <dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-ui</artifactId> <version>1.6.14</version> </dependency>
```

-   将 swagger 2 注释替换为 swagger 3 注释（它已包含在依赖项中）。 招摇 3 注释的包是 .`springdoc-openapi-ui``io.swagger.v3.oas.annotations`
    -   `@Api`→`@Tag`
    -   `@ApiIgnore`→或或`@Parameter(hidden = true)``@Operation(hidden = true)``@Hidden`
    -   `@ApiImplicitParam`→`@Parameter`
    -   `@ApiImplicitParams`→`@Parameters`
    -   `@ApiModel`→`@Schema`
    -   `@ApiModelProperty(hidden = true)`→`@Schema(accessMode = READ_ONLY)`
    -   `@ApiModelProperty`→`@Schema`
    -   `@ApiOperation(value = "foo", notes = "bar")`→`@Operation(summary = "foo", description = "bar")`
    -   `@ApiParam`→`@Parameter`
    -   `@ApiResponse(code = 404, message = "foo")`→`@ApiResponse(responseCode = "404", description = "foo")`
-   如果使用对象捕获多个请求查询参数，请使用`@ParameterObject`
-   此步骤是可选的：仅**当您有多个** bean 时，才将它们替换为 bean。`Docket``GroupedOpenApi`

以前：

```c
 @Bean public Docket publicApi() { return new Docket(DocumentationType.SWAGGER_2) .select() .apis(RequestHandlerSelectors.basePackage("org.github.springshop.web.public")) .paths(PathSelectors.regex("/public.*")) .build() .groupName("springshop-public") .apiInfo(apiInfo()); } @Bean public Docket adminApi() { return new Docket(DocumentationType.SWAGGER_2) .select() .apis(RequestHandlerSelectors.basePackage("org.github.springshop.web.admin")) .paths(PathSelectors.regex("/admin.*")) .apis(RequestHandlerSelectors.withMethodAnnotation(Admin.class)) .build() .groupName("springshop-admin") .apiInfo(apiInfo()); }
```

现在：

```c
 @Bean public GroupedOpenApi publicApi() { return GroupedOpenApi.builder() .group("springshop-public") .pathsToMatch("/public/**") .build(); } @Bean public GroupedOpenApi adminApi() { return GroupedOpenApi.builder() .group("springshop-admin") .pathsToMatch("/admin/**") .addMethodFilter(method -> method.isAnnotationPresent(Admin.class)) .build(); }
```

如果你**只有一个** - 删除它，而是将属性添加到你的 ：`Docket``application.properties`

```properties
springdoc.packagesToScan=package1, package2
springdoc.pathsToMatch=/v1, /api/balance/**
```

```java
 @Bean public OpenAPI springShopOpenAPI() { return new OpenAPI() .info(new Info().title("SpringShop API") .description("Spring shop sample application") .version("v0.0.1") .license(new License().name("Apache 2.0").url("http://springdoc.org"))) .externalDocs(new ExternalDocumentation() .description("SpringShop Wiki Documentation") .url("https://springshop.wiki.github.org/docs")); }
```

### 9\. 其他资源

#### 9.1. 其他入门资源

#### 9.2. 依赖仓库

这些库托管在 maven 中央存储库上。 可以在以下位置查看项目的访问权限：`springdoc-openapi`

释放：

-   https://s01.oss.sonatype.org/content/groups/public/org/springdoc/

快照：

-   https://s01.oss.sonatype.org/content/repositories/snapshots/org/springdoc/

### 10\. 赞助商

`springdoc-openapi`在[开放集体](https://opencollective.com/springdoc)上。

如果您这个项目❤️考虑成为[赞助商](https://github.com/sponsors/springdoc)。

这笔钱用于支付项目费用，您的捐款将帮助项目成功生存和发展。

感谢我们的铜牌赞助商！

[\[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-Sby13QPp-1676865635144)(null)\]](https://opensource.mercedes-benz.com/) [\[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-Fxc4aC13-1676865637746)(null)\]](https://www.dmtech.de/) [\[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-LeBYJpwS-1676865635223)(null)\]](https://www.contrastsecurity.com/)

#### 10.1. 成为铜牌赞助商的好处

铜牌赞助商每月向该项目捐赠 50 美元，并获得以下好处：

-   您将收到赞助商徽章🎖！页面中 [springdoc.org](https://springdoc.org/) 首页的可见性（55 年 000 月每月约 2022，<> 次浏览）。`welcome`
-   “谢谢”来自“springdoc团队”的推文。

#### 10.2. 成为银牌赞助商的好处

银牌赞助商每月向该项目捐赠 100 美元，并获得以下好处：

-   与铜牌赞助商相同的好处（在主页上的可见性，以及感谢推文）。
-   每月获得 2 个支持的能力，不可转让。`issues`
-   如果在月底之前未创建问题，则会丢失

#### 10.3. 成为金牌赞助商的好处

金牌赞助商每月向该项目捐赠 500 美元，并获得以下好处：

-   与银牌赞助商相同的好处（在主页上的可见性，以及感谢推文）。
-   每月获得 10 个支持的能力，不可转让。`issues`
-   所有 [springdoc.org](https://springdoc.org/) 页脚上的公司徽标
-   如果在月底之前未创建问题，则剩余问题将丢失。

### 11\. 特别鸣谢

-   感谢 [Spring 团队](https://spring.io/team)分享有关 Spring 项目的所有相关资源。
-   非常感谢 [JetBrains](https://www.jetbrains.com/?from=springdoc-openapi) 支持 springdoc-openapi 项目。

\[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-nEdJlHiv-1676865633955)(null)\]

### 12\. 常见问题

#### 12.1. 如何在一个 Spring 引导项目中定义多个 OpenAPI 定义？

您可以根据以下组合定义自己的 API 组：要扫描的 API 路径和包。每个组都应该有一个唯一的 . 默认情况下，此组的 OpenAPI 描述将在以下位置可用：`groupName`

-   `http://server:port/context-path/v3/api-docs/groupName`

要启用对多个 OpenAPI 定义的支持，需要定义一个类型的 Bean。`GroupedOpenApi`

对于以下组定义（基于包路径），OpenAPI 描述 URL 将为：/v3/api-docs/**stores**

```java
@Bean
public GroupedOpenApi storeOpenApi() { String paths[] = {"/store/**"}; return GroupedOpenApi.builder().group("stores").pathsToMatch(paths) .build();
}
```

对于以下组定义（基于包名称），OpenAPI 描述 URL 将为：/v3/api-docs/**users**

```java
@Bean
public GroupedOpenApi userOpenApi() { String packagesToscan[] = {"test.org.springdoc.api.app68.api.user"}; return GroupedOpenApi.builder().group("users").packagesToScan(packagesToscan) .build();
}
```

对于以下组定义（基于路径），OpenAPI 描述 URL 将为：/v3/api-docs/**pets**

```java
@Bean
public GroupedOpenApi petOpenApi() { String paths[] = {"/pet/**"}; return GroupedOpenApi.builder().group("pets").pathsToMatch(paths) .build();
}
```

对于以下组定义（基于包名称和路径），OpenAPI 描述 URL 将为：/v3/api-docs/**groups**

```java
@Bean
public GroupedOpenApi groupOpenApi() { String paths[] = {"/v1/**"}; String packagesToscan[] = {"test.org.springdoc.api.app68.api.user", "test.org.springdoc.api.app68.api.store"}; return GroupedOpenApi.builder().group("groups").pathsToMatch(paths).packagesToScan(packagesToscan) .build();
}
```

有关用法的更多详细信息，可以查看以下示例 Test：

-   https://github.com/springdoc/springdoc-openapi/tree/master/springdoc-openapi-webmvc-core/src/test/java/test/org/springdoc/api/app68

#### 12.2. 如何配置 Swagger UI？

-   招摇官方属性的支持可在 上找到。请参阅[官方文档](https://swagger.io/docs/open-source-tools/swagger-ui/usage/configuration/)。`springdoc-openapi`
-   您可以在文档中使用与 Spring 引导属性相同的 swagger 属性。

|  | 所有这些属性都应使用以下前缀声明：`springdoc.swagger-ui` |
| --- | --- |
|  |  |

#### 12.3. 如何按提供的组过滤输出规范中记录的资源？

```
springdoc.swagger-ui.filter=group-a
```

#### 12.4. 如何禁用/启用基于 env 变量的 Swagger UI 生成？

```
springdoc.swagger-ui.enabled=false
```

#### 12.5. 如何在 Swagger UI 中控制操作和标签的默认展开设置，

-   您可以在 application.yml 中设置此属性，例如：

```
springdoc.swagger-ui.doc-expansion= none
```

#### 12.6. 如何更改 ？`swagger-ui`

-   对于布局选项，您可以使用 swagger-ui 配置选项。例如：

```
springdoc.swagger-ui.layout=BaseLayout
```

#### 12.7. 如何按字母顺序对端点进行排序？

-   可以使用以下属性：`springdoc-openapi`

```
#For sorting endpoints alphabetically
springdoc.swagger-ui.operationsSorter=alpha
#For sorting tags alphabetically
springdoc.swagger-ui.tagsSorter=alpha
```

#### 12.8. 如何禁用试用按钮？

```
springdoc.swagger-ui.supportedSubmitMethods="get", "put", "post", "delete", "options", "head", "patch", "trace"
```

#### 12.9. 如何添加可重复使用的枚举？

-   你应该添加你的枚举。`@Schema(enumAsRef = true)`

#### 12.10. 如何申请所有枚举？`enumAsRef = true`

```
static {
    io.swagger.v3.core.jackson.ModelResolver.enumsAsRef = true;
}
```

#### 12.11. 如何明确设置要过滤的路径？

```
springdoc.pathsToMatch=/v1, /api/balance/**
```

#### 12.12. 如何明确设置要扫描的软件包？

```
springdoc.packagesToScan=package1, package2
```

#### 12.13. 如何以编程方式设置 Swagger 属性？

这些可以通过创建 Bean 来设置，如下所示：`swaggerUiConfig`

```kotlin
---
@Bean
fun swaggerUiConfig(config: SwaggerUiConfigProperties): SwaggerUiConfigProperties { config.showCommonExtensions = true config.queryConfigEnabled = true return config
}
---
```

#### 12.14. 如何忽略模型的某些字段？

-   您可以在要隐藏的字段顶部使用以下批注：
-   `@Schema(hidden = true)`

#### 12.15. 如何忽略弹簧安全中的参数？`@AuthenticationPrincipal`

-   解决方法是使用以下方法：`@Parameter(hidden = true)`
-   对于使用 的项目，应将以下依赖项与依赖项结合使用：`spring-security``springdoc-openapi-ui`

```xml
<dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-security</artifactId> <version>last.version</version>
</dependency>
```

#### 12.16. 是否有可用的 Gradle 插件？

-   是的。更多详细信息可在 [gradle 插件](https://springdoc.org/#gradle-plugin)部分找到。

#### 12.17. 如何隐藏文档中的参数？

-   您可以使用`@Parameter(hidden = true)`

#### 12.18. 是否支持注释？`@Parameters`

#### 12.19. 是否支持泽西岛？`springdoc-openapi`

-   如果您使用的是 JAX-RS 并作为实现泽西岛（例如），我们不支持它。`@Path`
-   我们只支持使用 Spring 管理的 bean 公开 Rest 端点（例如）。`@RestController`
-   你可以看看swagger-jaxrs2项目：
    -   https://github.com/swagger-api/swagger-samples/tree/2.0/java/java-jersey2-minimal

#### 12.20. 只能为 ？`springdoc-openapi``@RestController`

-   `@RestController`在类型级别等效于 +。`@Controller``@RequestMapping`
-   对于某些旧版应用，我们仍只能同时支持这两种应用。
-   如果需要在类型级别隐藏 ，在这种情况下，可以使用： 在控制器级别。`@Controller``@Hidden`
-   请注意，此注释还可用于从生成的文档中隐藏某些方法。

#### 12.21. 是否支持以下验证注释： ？`@NotEmpty``@NotBlank``@PositiveOrZero``@NegativeOrZero`

#### 12.22. 如何在 Swagger UI 中将（spring-data-commons）对象映射到正确的 URL 参数？`Pageable`

自 以来，对 spring-data-commons 的 Pageable 的支持是开箱即用的。 为此，您必须将注释与类型相结合。`springdoc-openapi v1.6.0``@ParameterObject``Pageable`

以前：`springdoc-openapi v1.6.0`

-   您也可以使用代替HTTP方法。`@ParameterObject``@PageableAsQueryParam``GET`

```java
static { getConfig().replaceParameterObjectWithClass(org.springframework.data.domain.Pageable.class, Pageable.class) .replaceParameterObjectWithClass(org.springframework.data.domain.PageRequest.class, Pageable.class);
}
```

-   另一种解决方案是手动配置可分页：
    -   您必须将可分页字段的显式映射声明为查询参数，并添加 on 可分页参数。`@Parameter(hidden = true) Pageable pageable`
    -   您还应该在方法级别声明 提供的注释，或者如果需要定义自定义描述，则声明自己的注释，defaultValue，…`@PageableAsQueryParam``springdoc-openapi`

如果要禁用对 spring 可分页类型的支持，可以使用：

```properties
springdoc.model-converters.pageable-converter.enabled=false
```

|  | 该属性仅在 v1.5.11+ 起可用`springdoc.model-converters.pageable-converter.enabled` |
| --- | --- |
|  |  |

#### 12.23. 如何在生成的描述中生成枚举？

-   可以将属性 添加到 。例如：`allowableValues``@Parameter`

```java
@GetMapping("/example")
public Object example(@Parameter(name ="json", schema = @Schema(description = "var 1",type = "string", allowableValues = {"1", "2"}))
String json) { return null;
}
```

```java
@Override
@JsonValue
public String toString() { return String.valueOf(action);
}
```

#### 12.24. 如何在反向代理后面部署？`springdoc-openapi-ui`

-   如果您的应用程序在代理、负载均衡器或云中运行，则请求信息（如主机、端口、方案等）可能会在此过程中发生变化。您的应用程序可能正在 上运行，但 HTTP 客户端应该只看到 。`10.10.10.10:8080``example.org`
-   [RFC7239](https://tools.ietf.org/html/rfc7239)“转发标头”定义了转发的HTTP标头;代理可以使用此标头提供有关原始请求的信息。您可以将应用程序配置为读取这些标头，并在创建链接并将其发送到 HTTP 302 响应、JSON 文档或 HTML 页面中的客户端时自动使用该信息。还有非标准标头，如 、、 、 和 。`X-Forwarded-Host``X-Forwarded-Port``X-Forwarded-Proto``X-Forwarded-Ssl``X-Forwarded-Prefix`
-   如果代理添加了常用的和，将 server.forward-headers-strategy 设置为 NATIVE 就足以支持这些。使用此选项，Web 服务器本身本身本身支持此功能;您可以查看他们的特定文档以了解特定行为。`X-Forwarded-For``X-Forwarded-Proto headers`
-   您需要确保在反向代理配置中设置了以下标头：`X-Forwarded-Prefix`
-   例如，使用 Apache 2，配置：

```
RequestHeader=set X-Forwarded-Prefix "/custom-path"
```

-   然后，在 Spring 引导应用程序中，确保您的应用程序处理以下标头：。有两种方法可以实现此目的：`X-Forwarded-For`

```
server.use-forward-headers=true
```

-   如果这还不够，Spring 框架提供了一个 .您可以通过将 server.forward-headers-strategy 设置为 FRAMEWORK，在应用程序中将其注册为 Servlet 过滤器。`ForwardedHeaderFilter`
-   从 Spring Boot 2.2 开始，这是处理反向代理标头的新属性：

```properties
server.forward-headers-strategy=framework
```

```java
@Bean
ForwardedHeaderFilter forwardedHeaderFilter() { return new ForwardedHeaderFilter();
}
```

#### 12.25. Spring MVC API 中的注释是否受支持？`@JsonView`

#### 12.26. 添加依赖项会破坏我的欢迎页面`springdoc-openapi-ui``public/index.html`

-   如果您的根目录上已经有静态内容，并且不希望它被配置覆盖，则可以只定义 的自定义配置，以免覆盖上下文根目录中的文件配置：`springdoc-openapi-ui``swagger-ui`
-   例如使用：

```
springdoc.swagger-ui.path= /swagger-ui/api-docs.html
```

#### 12.27. 如何测试 Swagger UI？

-   您可以查看 UI 的此示例测试：
    -   https://github.com/springdoc/springdoc-openapi/blob/master/springdoc-openapi-ui/src/test/java/test/org/springdoc/ui/app1/SpringDocApp1Test.java

#### 12.28. 如何自定义 OpenAPI 对象？

```java
@Bean
public OpenApiCustomiser consumerTypeHeaderOpenAPICustomiser() {
return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream()) .forEach(operation -> operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/myConsumerTypeHeader")));
}
```

|  | 此 Bean 将仅应用于默认的 OpenAPI。`OpenApiCustomizer` |
| --- | --- |
|  |  |

如果您还需要 应用于，请改用。`OpenApiCustomizer``GroupedOpenApi``GlobalOpenApiCustomizer`

#### 12.29. 如何返回空内容作为响应？

-   可以使用以下语法之一将空内容作为响应处理：
-   `content = @Content`
-   `content = @Content(schema = @Schema(hidden = true))`
-   例如：

```java
@Operation(summary = "Get thing", responses = { @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "404", description = "Not found", content = @Content), @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
@RequestMapping(path = "/testme", method = RequestMethod.GET)
ResponseEntity<String> testme() { return ResponseEntity.ok("Hello");
}
```

#### 12.30. 如何支持具有多种消费媒体类型的端点？

-   同一类上的重载方法，具有相同的 HTTP 方法和路径，因此只会生成一个 OpenAPI 操作。
-   此外，建议将 in 置于重载方法之一的级别。否则，如果在同一重载方法中多次声明它，则可能会重写它。`@Operation`

#### 12.31. 如何在编译时获取 yaml 和 json （OpenAPI）？

-   可用于此功能：`springdoc-openapi-maven-plugin`
    -   https://github.com/springdoc/springdoc-openapi-maven-plugin.git
-   您可以自定义输出目录（属性输出目录）：默认值为：${project.build.directory}

#### 12.32. 文档中忽略的类型是什么？

-   `Principal`、 和 Spring MVC 支持的其他可注入参数被排除在外。`Locale``HttpServletRequest``HttpServletResponse`
-   完整文档在这里：
    -   https://docs.spring.io/spring/docs/5.1.x/spring-framework-reference/web.html#mvc-ann-arguments

#### 12.33. 如何禁用忽略的类型：

如果不想忽略类型 、 、 和其他类型，请执行以下操作：`Principal``Locale``HttpServletRequest`

```java
SpringDocUtils.getConfig().removeRequestWrapperToIgnore(HttpServletRequest.class)
```

#### 12.34. 如何在请求中添加授权标头？

-   应将标记添加到受保护的 API。`@SecurityRequirement`
-   例如：

```
@Operation(security = { @SecurityRequirement(name = "bearer-key") })
```

```java
@Bean public OpenAPI customOpenAPI() { return new OpenAPI() .components(new Components() .addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
}
```

#### 12.35. 与春狐项目的差异化

-   OAS 3 于 2017 年 3 月发布，没有发布支持 OAS 2 的版本。 目前只涵盖与Spring Boot的Swagger 2018集成。最新发布日期为 <> 年 <> 月。因此，在维护方面，最近非常缺乏支持。`springfox``springfox`
-   我们决定继续前进，并与社区共享我们已经在内部项目中使用的库。
-   与 的最大区别在于，我们集成了以下未涵盖的新功能：`springfox``springfox`
-   Spring Boot 和 OpenAPI 3 标准之间的集成。
-   我们依赖并且只依赖官方图书馆。`swagger-annotations``swagger-ui`
-   我们支持 Spring 5 上的新功能，例如带注释和功能样式。`spring-webflux`
-   我们尽最大努力回答所有问题并解决所有问题或增强请求

#### 12.36. 如何使用 springdoc-openapi 迁移到 OpenAPI 3

-   和 之间没有关系。如果要迁移到 OpenAPI 3：`springdoc-openapi``springfox`
-   删除所有依赖项和相关代码到 springfox
-   添加依赖项`springdoc-openapi-ui`
-   如果不想从根路径提供 UI，或者与现有配置存在冲突，只需更改以下属性：

```
springdoc.swagger-ui.path=/you-path/swagger-ui.html
```

#### 12.37. 如何设置全局标题？

-   您可能具有具有标准 OpenAPI 描述的全局参数。
-   如果需要定义全局显示（在每个组中），无论该组是否满足 GroupedOpenApi 上指定的条件，都可以使用 OpenAPI Bean。
-   您可以在全局组件部分的参数下定义公共参数，并通过 在其他地方引用它们。您还可以定义全局标头参数。`$ref`
-   为此，您可以覆盖到 OpenAPI Bean，并在组件级别设置全局标头或参数定义。

```java
@Bean
public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) { return new OpenAPI() .components(new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")) .addParameters("myHeader1", new Parameter().in("header").schema(new StringSchema()).name("myHeader1")).addHeaders("myHeader2", new Header().description("myHeader2 header").schema(new StringSchema()))) .info(new Info() .title("Petstore API") .version(appVersion) .description("This is a sample server Petstore server. You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). For this sample, you can use the api key `special-key` to test the authorization filters.") .termsOfService("http://swagger.io/terms/") .license(new License().name("Apache 2.0").url("http://springdoc.org")));
}
```

#### 12.38. 是否支持回调？

#### 12.39. 如何定义安全方案？

-   您可以使用：注释。`@SecurityScheme`
-   或者，您可以通过覆盖OpenAPI Bean以编程方式定义它：

```java
 @Bean public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) { return new OpenAPI() .components(new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))) info(new Info().title("SpringShop API").version(appVersion) .license(new License().name("Apache 2.0").url("http://springdoc.org"))); }
```

#### 12.40. How can I hide an operation or a controller from documentation ?

-   You can use annotation at , and method level`@io.swagger.v3.oas.annotations.Hidden``@RestController``@RestControllerAdvice`
-   The annotation on exception handler methods, is considered when building generic (error) responses from exception handlers.`@Hidden``@ControllerAdvice`
-   Or use: `@Operation(hidden = true)`

#### 12.41. How to configure global security schemes?

-   For global SecurityScheme, you can add it inside your own OpenAPI definition:

```java
@Bean
public OpenAPI customOpenAPI() { return new OpenAPI().components(new Components() .addSecuritySchemes("basicScheme", new SecurityScheme() .type(SecurityScheme.Type.HTTP).scheme("basic"))).info(new Info().title("Custom API") .version("100")).addTagsItem(new Tag().name("mytag"));
}
```

#### 12.42. Can I use spring property with swagger annotations?

-   The support of spring property resolver for : `@Info``title` \* `description` \* `version` \* `termsOfService`
-   The support of spring property resolver for : `@Info.license``name` \* `url`
-   The support of spring property resolver for : `@Info.contact``name` \* `email` \* `url`
-   The support of spring property resolver for : `@Operation``description` \* `summary`
-   The support of spring property resolver for : `@Parameter``description` \* `name`
-   The support of spring property resolver for : `@ApiResponse``description`
-   Its also possible to declare security URLs for : `@OAuthFlow``openIdConnectUrl` \* `authorizationUrl` \* `refreshUrl` \* `tokenUrl`
-   The support of spring property resolver for : \* \* , by setting to `@Schema``name``title``description``springdoc.api-docs.resolve-schema-properties``true`

#### 12.43. How is server URL generated ?

-   如果文档不存在，自动生成服务器 URL 可能会很有用。
-   如果存在服务器注释，则将改用它们。

#### 12.44. 如何禁用 springdoc-openapi 缓存？

-   默认情况下，OpenAPI 描述计算一次，然后缓存。
-   有时，在内部和外部代理后面提供相同的招摇 UI。一些用户希望在每个 HTTP 请求上计算服务器 URL。
-   为了禁用 springdoc 缓存，您必须设置以下属性：

```
springdoc.cache.disabled= true
```

#### 12.45. 如何在不使用 ？`swagger-ui`

-   应仅使用依赖项：`springdoc-openapi-core`

```xml
<dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-webmvc-core</artifactId> <version>latest.version</version>
</dependency>
```

#### 12.46. 如何禁用端点？`springdoc-openapi`

```
springdoc.api-docs.enabled=false
```

#### 12.47. 如何隐藏响应的模式？

-   若要在操作级别使用注释隐藏响应元素，如下所示：`@Schema`

```
@Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(hidden = true))))
```

```
@ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(hidden = true))) })
OR
@ApiResponse(responseCode = "404", description = "Not found", content = @Content)
```

#### 12.48. 当我设置不同的上下文路径时，URL 是什么？`swagger-ui`

```
server.servlet.context-path= /foo
```

-   将在以下 URL 上提供：`swagger-ui`
    -   `http://server:port/foo/swagger-ui.html`

#### 12.49. 我可以通过编程方式自定义 OpenAPI 对象吗？

-   你可以定义自己的 OpenAPI Bean：如果你需要定义全局显示（在每个组中），无论该组是否满足 GroupedOpenApi 上指定的条件，你都可以使用 OpenAPI Bean。

```java
@Bean
public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) { return new OpenAPI() .components(new Components().addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))) .info(new Info().title("SpringShop API").version(appVersion) .license(new License().name("Apache 2.0").url("http://springdoc.org")));
}
```

-   如果需要定义出现在特定组中，并遵守在 GroupedOpenApi 上指定的条件，则可以将 OpenApiCustomiser 添加到 GroupedOpenApi 定义中。

```java
GroupedOpenApi.builder().group("users").pathsToMatch(paths).packagesToScan(packagedToMatch).addOpenApiCustomiser(customerGlobalHeaderOpenApiCustomiser()) .build() @Bean
public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() { return openApi -> openApi.path("/foo", new PathItem().get(new Operation().operationId("foo").responses(new ApiResponses() .addApiResponse("default",new ApiResponse().description("") .content(new Content().addMediaType("fatz", new MediaType()))))));
}
```

#### 12.50. 在哪里可以找到演示应用程序的源代码？

-   该应用程序的源代码可在以下 GitHub 存储库中找到：
    -   https://github.com/springdoc/springdoc-openapi-demos.git

#### 12.51. 这个库是否支持来自接口的注释？

#### 12.52. 排除的参数类型列表是什么？

-   https://docs.spring.io/spring/docs/5.1.x/spring-framework-reference/web.html#mvc-ann-arguments。

#### 12.53. 是否支持文件上传？

-   该库支持主要文件类型：、、`MultipartFile``@RequestPart``FilePart`

#### 12.54. 我可以使用内部注释吗？`@Parameter``@Operation`

#### 12.55. 为什么我的参数被标记为必填？

-   任何参数都标记为必需，即使缺少。`@GetMapping``@RequestParam`
-   如果需要不同的行为，可以添加注释。`@Parameter(required=false)`
-   具有指定的查询参数标记为必需。`defaultValue`

#### 12.56. 重载方法如何具有相同的端点，但具有不同的参数

-   `springdoc-openapi`将这些方法呈现为单个终结点。它检测重载的端点，并生成 parameters.schema.oneOf。

#### 12.57. 设置Swagger UI以使用提供的spec.yml的正确方法是什么？

-   使用此属性，将禁用所有自动配置 Bean：`springdoc-openapi`

```
springdoc.api-docs.enabled=false
```

-   然后通过添加此 Bean 来启用最小 Bean 配置：

```java
@Bean
SpringDocConfiguration springDocConfiguration(){ return new SpringDocConfiguration();
}
@Bean
SpringDocConfigProperties springDocConfigProperties() { return new SpringDocConfigProperties();
} @Bean
ObjectMapperProvider objectMapperProvider(SpringDocConfigProperties springDocConfigProperties){ return new ObjectMapperProvider(springDocConfigProperties);
}
```

```
springdoc.swagger-ui.url=/api-docs.yaml
```

#### 12.58. 有没有办法通过@Parameter标签发送授权标头？

-   OpenAPI 3 规范不允许显式添加授权标头。`Note: Header parameters named Accept, Content-Type and Authorization are not allowed. To describe these headers`
-   有关更多信息，您可以阅读：
    -   https://swagger.io/docs/specification/describing-parameters/#header-parameters

#### 12.59. 使用@Controller注释的 REST 控制器被忽略了吗？

-   这是默认行为，如果你没有注释`@Controller``@ResponseBody`
-   您可以将控制器更改为 。或添加 + 。`@RestControllers``@ResponseBody``@Controller`
-   如果不可能，您可以配置 springdoc 以使用 SpringDocUtils 扫描您的其他控制器。例如：

```java
static { SpringDocUtils.getConfig().addRestControllers(HelloController.class);
}
```

#### 12.60. 如何使用 application.yml 定义组？

-   您可以使用 spring-boot 配置文件动态装入组。
-   请注意，对于此用法，您不必声明 **GroupedOpenApi** Bean。
-   您需要在前缀 **springdoc.group-configs** 下声明以下属性。
-   例如：

```
springdoc.group-configs[0].group=users
springdoc.group-configs[0].paths-to-match=/user/**
springdoc.group-configs[0].packages-to-scan=test.org.springdoc.api
```

-   此处提供了此前缀下的属性列表：
    -   [springdoc-openapi-properties](https://springdoc.org/index.html#properties)

#### 12.61. 如何从参数对象中提取字段？

-   您可以使用 springdoc 注释@ParameterObject。
-   用 @ParameterObject 注释的请求参数将有助于将参数的每个字段添加为单独的请求参数。
-   这与映射到POJO对象的Spring MVC请求参数兼容。
-   此批注不支持嵌套参数对象。
-   POJO 对象必须包含带有强制前缀的字段的 getter。否则，招摇文档将不会显示带注释的实体的字段。`get`

#### 12.62. 如何将开放 API 3 与 Spring 项目（不是 Spring 引导）集成？

当你的应用程序使用不带 spring（spring-boot）时，你需要添加 spring-boot 中原生提供的 bean 和自动配置。

例如，假设你想在 spring-mvc 应用程序中加载 swagger-upi：

-   你主要需要添加 springdoc-openapi 模块

```xml
<dependency> <groupId>org.springdoc</groupId> <artifactId>springdoc-openapi-ui</artifactId> <version>last.version</version>
</dependency>
```

-   如果您没有 spring-boot 和 spring-boot-autoconfigure 依赖项，则需要添加它们。并注意 spring.version 和 spring-boot.version 之间的兼容性矩阵。例如，在本例中（spring.version=5.1.12.RELEASE）：

```xml
<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot</artifactId> <version>2.1.11.RELEASE</version>
</dependency>
<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-autoconfigure</artifactId> <version>2.1.11.RELEASE</version>
</dependency>
```

-   扫描 spring-boot 自动为您加载的“自动配置类”。`springdoc-openapi`
-   根据您的模块，您可以在每个模块的文件：中找到它们。`spring.factories``springdoc-openapi`

```java
@EnableWebMvc
public class AppInitializer implements WebApplicationInitializer { @Override public void onStartup(ServletContext servletContext) throws ServletException { WebApplicationContext context = getContext(); servletContext.addListener(new ContextLoaderListener(context)); ServletRegistration.Dynamic dispatcher = servletContext.addServlet("RestServlet", new DispatcherServlet(context)); dispatcher.setLoadOnStartup(1); dispatcher.addMapping("/*"); } private AnnotationConfigWebApplicationContext getContext() { AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext(); context.scan("rest"); context.register(this.getClass(), org.springdoc.webmvc.ui.SwaggerConfig.class, org.springdoc.core.SwaggerUiConfigProperties.class, org.springdoc.core.SwaggerUiOAuthProperties.class, org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class, org.springdoc.webmvc.core.MultipleOpenApiSupportConfiguration.class, org.springdoc.core.SpringDocConfiguration.class, org.springdoc.core.SpringDocConfigProperties.class, org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class); return context; }
}
```

-   根据您的模块，您可以在每个模块的文件：中找到它们。`spring.factories``springdoc-openapi`
-   对于组的使用，请确保您的 Bean 被扫描。`GroupedOpenApi`
-   此外，如果您使用的是自定义 ： 。请确保声明以下属性：`context path``/my-servlet-path`

```
spring.mvc.servlet.path=/my-servlet-path
```

#### 12.63. 如何使用最后一个快照？`springdoc-openapi`

-   仅出于测试目的，您可以使用最后一个快照临时进行测试`springdoc-openapi`
-   为此，您可以在pom.xml或设置中.xml以下部分：

```xml
 <repositories> <repository> <id>snapshots-repo</id> <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url> <releases><enabled>false</enabled></releases> <snapshots><enabled>true</enabled></snapshots> </repository> </repositories>
```

#### 12.64. 如何使用启用货币金额支持？`springdoc-openapi`

-   如果应用程序想要启用支持，它会声明：`springdoc-openapi`

```java
SpringDocUtils.getConfig().replaceWithClass(MonetaryAmount.class, org.springdoc.core.converters.models.MonetaryAmount.class);
```

-   不使用springdoc-openapi MonetaryAmount的另一个解决方案是：

```java
SpringDocUtils.getConfig().replaceWithSchema(MonetaryAmount.class, new ObjectSchema() .addProperties("amount", new NumberSchema()).example(99.96) .addProperties("currency", new StringSchema().example("USD")));
```

#### 12.65. 如何在单个应用程序中聚合外部端点（公开 OPENAPI 3 规范）？

属性 适用于配置外部 （/v3/api-docs url）。 例如，如果要在单个应用程序中聚合其他服务的所有终结点。 重要提示：不要忘记还需要启用 CORS。`springdoc.swagger-ui.urls.*`

#### 12.66. 如何使用自定义 json/yml 文件而不是生成的文件？

如果您的文件 open-api.json 包含 OpenAPI 3 格式的 OpenAPI 文档。 然后简单地声明：文件名可以是您想要的任何内容，从您的声明是一致的那一刻起 yaml 或 json OpenAPI 规范。

```properties
   springdoc.swagger-ui.url=/open-api.json
```

然后文件 open-api.json 应该位于：src/main/resources/static 无需其他配置。

#### 12.67. 如何启用 CSRF 支持？

如果您使用的是标准标头。（例如使用弹簧安全标头） 如果需要 CSRF Token，swagger-ui 会在每次 HTTP 请求期间自动发送新的 XSRF-TOKEN。

如果您的 XSRF-TOKEN 不是基于标准的，您可以使用 requestInterceptor 通过 spring 资源转换器以编程方式手动捕获最新的 xsrf 令牌并将其附加到请求中：

-   https://github.com/swagger-api/swagger-ui/blob/master/docs/usage/configuration.md#requestinterceptor

从 springdoc-openapi 的 v1.4.4 版本开始，添加了一个新属性来启用 CSRF 支持，同时使用标准标头名称：

```properties
springdoc.swagger-ui.csrf.enabled=true
```

#### 12.68. 如何禁用默认的招摇宠物店 URL？

您可以使用以下属性：

```properties
springdoc.swagger-ui.disable-swagger-default-url=true
```

#### 12.69. 是否支持@PageableDefault来增强 OpenAPI 3 文档？

是的，您可以将其与注释结合使用。 此外，从 v1.4.5 开始支持 spring-boot **和 `spring.data.rest.default. 属性。`**`@ParameterObject``spring.data.web.`

#### 12.70. 如何使 Spring 安全登录端点可见？

您可以使用以下属性：

```properties
springdoc.show-login-endpoint=true
```

#### 12.71. 即使没有引用模式，如何显示模式定义？

您可以使用以下属性：

```properties
springdoc.remove-broken-reference-definitions=false
```

#### 12.72. 如何覆盖@Deprecated？

整个想法是使您的文档最接近代码，只需最少的代码更改。 如果代码包含 ，则也会将其架构视为已弃用。 如果要将 swagger 上的字段声明为不推荐使用，即使使用 java 代码，该字段也包含 ， 您可以使用自 v1.4.3 版以来可用的以下属性：`springdoc-openapi``@Deprecated``sprindoc-openapi``@Depreacted`

```properties
springdoc.model-converters.deprecating-converter.enabled=false
```

#### 12.73. 如何显示返回模型和视图的方法？

您可以使用以下属性：

```properties
springdoc.model-and-view-allowed=true
```

#### 12.74. 如何获得 OpenAPI 规范的漂亮打印输出？

您可以使用以下属性：

```properties
springdoc.writer-with-default-pretty-printer=true
```

#### 12.75. 如何为同一类定义不同的模式？

复杂对象始终解析为对组件中定义的架构的引用。 例如，让我们考虑一个具有 and 属性的类：`Instance``workAddress``homeAddress``Address`

```java
public class PersonDTO { @JsonProperty private String email; @JsonProperty private String firstName; @JsonProperty private String lastName; @Schema(ref = "WorkAddressSchema") @JsonProperty private Address workAddress; @Schema(ref = "HomeAddressSchema") @JsonProperty private Address homeAddress; } public class Address { @JsonProperty private String addressName; }
```

如果要为此类定义两个不同的架构，可以设置 2 个不同的架构，如下所示：

```java
@Bean
public OpenAPI customOpenAPI() {
return new OpenAPI().components(new Components()
.addSchemas("WorkAddressSchema", getSchemaWithDifferentDescription(Address.class, "work Address" ))
.addSchemas("HomeAddressSchema", getSchemaWithDifferentDescription(Address.class, "home Address" )));
} private Schema getSchemaWithDifferentDescription(Class className, String description){
ResolvedSchema resolvedSchema = ModelConverters.getInstance()
.resolveAsResolvedSchema(
new AnnotatedType(className).resolveAsRef(false));
return resolvedSchema.schema.description(description);
}
```

#### 12.76. 如何根据使用情况为类属性定义不同的描述？

例如，让我们考虑一个具有属性的类：`Instance``email`

```java
public class PersonDTO { @JsonProperty private String email; @JsonProperty private String firstName; @JsonProperty private String lastName; }
```

如果要为 定义两个不同的描述，可以设置 2 个不同的架构，如下所示：`email`

```java
@Bean
public OpenAPI customOpenAPI() {
return new OpenAPI().components(new Components()
.addSchemas("PersonDTO1", getFieldSchemaWithDifferentDescription(PersonDTO.class, "work email" ))
.addSchemas("PersonDTO2", getFieldSchemaWithDifferentDescription(PersonDTO.class, "home email" )));
} private Schema getFieldSchemaWithDifferentDescription(Class className, String description){ ResolvedSchema resolvedSchema = ModelConverters.getInstance() .resolveAsResolvedSchema( new AnnotatedType(className).resolveAsRef(false)); return resolvedSchema.schema.addProperties("email", new StringSchema().description(description));
}
```

#### 12.77. 自定义大摇大摆的静态资源

您可以自定义位于 中的 swagger 文档静态资源。资源列表包括：`META-INF/resources/webjars/swagger-ui/{swagger.version}/`

-   `index.html`
-   `swagger-ui-bundle.js`
-   `swagger-ui.css`
-   `swagger-ui-standalone-preset.js`
-   `swagger-ui.css.map`
-   `swagger-ui-bundle.js.map`
-   `swagger-ui-standalone-preset.js.map`
-   `favicon-32x32.png`

为此，您需要扩展`SwaggerIndexPageTransformer`

```java
public class SwaggerCodeBlockTransformer extends SwaggerIndexPageTransformer { @Override public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformer) throws IOException { if (resource.toString().contains("swagger-ui.css")) { final InputStream is = resource.getInputStream(); final InputStreamReader isr = new InputStreamReader(is); try (BufferedReader br = new BufferedReader(isr)) { final String css = br.lines().collect(Collectors.joining()); final byte[] transformedContent = css.replace("old", "new").getBytes(); return new TransformedResource(resource, transformedContent); } } return super.transform(request, resource, transformer); } }
```

接下来，将变压器添加到您的`@Bean``@Configuration`

```java
@Configuration
public class OpenApiConfig { @Bean public SwaggerIndexTransformer swaggerIndexTransformer( SwaggerUiConfigProperties a, SwaggerUiOAuthProperties b, SwaggerUiConfigParameters c, SwaggerWelcomeCommon d) { return new SwaggerCodeBlockTransformer(a, b, c, d); }
}
```

说明性示例

\[外链图片转存失败,源站可能有防盗链机制,建议将图片保存下来直接上传(img-RwIRLOBv-1676865638807)(null)\]

#### 12.78. 与 的兼容性矩阵是什么？`springdoc-openapi``spring-boot`

```
springdoc-openapi`与 和 兼容。`spring-boot 1``spring-boot 2
```

通常，**您应该只选择今天的 1.6.14 的最后一个稳定版本。**

更准确地说，这是针对其构建的 Spring 引导版本的详尽列表：`springdoc-openapi`

| 弹簧引导版本 | 最低 springdoc-openapi 版本 |
| --- | --- |
| `3.0.x` | `2.0.x`+ |
| `2.7.x`,`1.5.x` | `1.6.11`+ |
| `2.6.x`,`1.5.x` | `1.6.0`+ |
| `2.5.x`,`1.5.x` | `1.5.9`+ |
| `2.4.x`,`1.5.x` | `1.5.0`+ |
| `2.3.x`,`1.5.x` | `1.4.0`+ |
| `2.2.x`,`1.5.x` | `1.2.1`+ |
| `2.0.x`,`1.5.x` | `1.0.0`+ |

最后更新于2022-11-25 00：15：28 +0100