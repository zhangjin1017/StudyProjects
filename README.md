# 实习学习项目案例

## 1、**RestTemplate**

###### **Get**

**getForObject(url,xxx.class,param对象/Map)**   返回值为xxx对象/Map

Map<String,Long> paramMap=new HashMap<>();

**getForEntity(url,xxx.class,param对象/Map)**   返回值为ResponseEntity   包含状态码、请求头等

ResponseEntity.getBody()得到Map

###### **Post**

**postForObject(url,param对象/MultiValueMap,xxx.class)**   返回值为xxx对象

1、**基本类型和实体传参**

使用MultiValueMap<String,Object> paramMap=new LinkedMultiValueMap<>();

**postForObject(url,param对象/HasMap,xxx.class)**   返回值为xxx对象

2、**@RequestBody传参，需要使用HttpEntity形式包装传参**

不可以使用MultiValueMap   可以使用HashMap或者xxx对象
## 2、Spring-Retry

spring-retry和aspectjweaver依赖，依托于aop，常与restTemplate配合使用

不能在接口实现类里写，aspect增强会失效，retry也是

###### 注解方式

在启动类上@EnableRetry
###### RetryTemplate方式

在启动类中加入一个Bean

创建AppRetryListenerSupport监听器

使用retryTemplate.execute()调用需要自动重试的方法

Object result=retryTemplate.execute(arg->xxxService.XXX())

## 3、Elasticsearch

集群：

存 ：路由计算：hash（id）% 主分片数量

取 ：访问任何一个节点都可以获取数据 因为每个都有备份

写：1、客户端请求任意集群节点   2、协调节点将请求转发到指定的节点  3、主分片保存数据。4、主分片将数据发给副本

5、副本保存数据，发送反馈给主分片   6、主分片反馈给客户端  7、客户端获取反馈

延时：主分片的延时+并行写入副本的最大延时

consistency 值为one 只要主分片状态ok就允许执行写操作   all 必须主分片和副本分片的状态都没问题才允许执行。 Quorum。大多数副本没问题就可以

查：1、客户端发送查询请求给协调节点。2、协调节点计算数据所在分片和全部的副本位置。3、为了负载均衡，可以轮询所有节点

4、将请求转发给具体的节点。 5、节点返回查询结果，将结果反馈给客户端

## 4、OAuth2

1. **授权码：正宗的 OAuth 认证，推荐**

   请求：标识 响应类型(code) 权限范围 跳转地址

   登录：账号 密码

   跳转到指定地址

   授权：是(得到授权码code) 否

   使用：标识 密钥 授权类型 <u>授权码</u> 跳转地址

2. **简化模式：为 Web 浏览器设计**

   请求：标识 响应类型(token) 权限范围 跳转地址

   登录：账号 密码

   跳转到指定地址

   授权：是(得到令牌access_token) 否

3. **密码模式：为遗留项目设计**

   请求：标识 密钥 授权类型(password) 用户名 密码 跳转地址

4. **客户端模式：为后台 API 服务消费者设计**

   请求：标识 密钥 授权类型(client_credentials)


JWT令牌

包含头部 内容 签名

## 5、Stream

   学习jdk8的stream用法

1. 集合处理数据的弊端

   当我们在需要对集合中的元素进行操作的时候，除了必须的添加、删除、获取外，最典型的操作就是集合的遍历

   list.stream().filter().foreach()

   筛选、切片、映射、查找、去重、统计、匹配、归约

2. 获取方法：

   **根据collection获取**

   list set vector

   但是map不行，获取对应的key value的集合

   **根据stream的of方法**

   数组

   #####  中间使用了拼接方法，最后必须使用终结方法，不然不会生效
    
    forEach：遍历数据
    
    count：计数
    
    filter：过滤数据
    
    limit：可以截取前n个数据
    
    skip：跳过前n个数据
    
    map：将流中元素映射到另一个流。数据类型转换
    
    sorted：排序
    
    distinct：去重 只适用于基本数据类型 自定义对象等需要重写hashcode和equals
    
    match：看看list中是否有匹配项。返回结果为boolean
    
    find：看看list中是否有匹配项 返回结果为该项

## 6、CAS

   学习CAS的概念和用法

#####    cas-overlay-template: cas单点登录服务端

#####    cas-client1: cas单点登录客户端1

#####    cas-client2: cas单点登录客户端2