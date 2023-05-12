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

## 7、RabbitMQ
#### 使用Docker安装RabbitMQ

1. 拉取镜像

   ```shell
   docker pull rabbitmq:management
   ```

2. 创建容器

   ```shell
   docker run -d -p 15672:15672 -p 5672:5672 \
   -e RABBITMQ_DEFAULT_VHOST=/  \
   -e RABBITMQ_DEFAULT_USER=admin \
   -e RABBITMQ_DEFAULT_PASS=admin \
   --name rabbitmqManager \
   rabbitmq:management
   ```



3. 开启web管理界面

   ```shell
   docker exec -it rabbitmqManager rabbitmq-plugins enable rabbitmq_management
   ```

4. 输入url访问，账号为admin，密码为admin



t1:如果出现此链接非私密链接

可以在谷歌浏览器url中输入chrome://flags

然后设置以下两项：

1）Allow invalid certificates for resources loaded from localhost.  Enable

2）Send Private Network Access preflights.  Enable


t2:如果提示只能本地登录，则添加一个用户，并赋予管理员权限



#### 解决延迟队列bug

去https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases下载相应版本的插件

将该文件放到服务器中，并且复制到rabbitmq容器的plugins下

docker cp /opt/project/rabbitmq_delayed_message_exchange-3.9.0.ez rabbitmqManager:/plugins

进入容器内部 docker exec -it rabbitmqManager /bin/bash

查看是否已经复制过来 ls

启动该插件 rabbitmq-plugins enable rabbitmq_delayed_message_exchange

退出容器 exit

重启容器 docker restart rabbitmq

容器启动成功之后，登录RabbitMQ的管理界面（ip:15672 访问web界面），找到ExchangesTab页。点击Add a new exchange，在Type里面查看是否有x-delayed-message选项，如果存在就代表插件安装成功。



##### 1、普通发送接收

生产者：

```java
public class Producer {
    //对列名称
    public static final String QUEUE_NAME="hello";

    //发消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂IP 连接RabbitMQ对列
        factory.setHost("43.140.252.215");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("admin");

        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        /**
         * 生产一个对列
         * 1.对列名称
         * 2.对列里面的消息是否持久化，默认情况下，消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费，是否进行消息共享，true可以多个消费者消费 false：只能一个消费者消费
         * 4.是否自动删除，最后一个消费者端开链接以后，该队列是否自动删除，true表示自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发消息
        String message = "Hello,world";
        /**
         * 发送一个消息
         * 1.发送到哪个交换机
         * 2.路由的key值是哪个本次是队列的名称
         * 3.其他参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }
}
```

消费者：

```java
public class Consumer {
    //队列的名称
    public static final String QUEUE_NAME="hello";
    //接受消息
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("43.140.252.215");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //声明接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };
        //取消消息时的回调
        CancelCallback cancelCallback = consumerTag ->{
            System.out.println("消息消费被中断");
        };

        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答true：代表自动应答false:代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
```

##### 2、消费者轮询

​		一个生产者生产消息，两个消费者轮询着消费消息，每次取一个消息

##### 3、手动应答

​		当一个消费者宕机后，肯定没有发送一个完成响应给队列，那么此时该条消息则回到队列，交给另一个消费者处理

​		1、channel.basicConsume的第二个参数为false，表示不自动应答

​		2、第三个参数deliverCallback中，要手动应答

​		channel.basicAck(message.getEnvelope().getDeliveryTag(),false);

```java
 DeliverCallback deliverCallback = (consumerTag, message) -> {
            SleepUtils.sleep(1);
            System.out.println("接收到消息:" + new String(message.getBody(), StandardCharsets.UTF_8));
            //手动应答
            /**
             * 1.消息的标记tag
             * 2.是否批量应答未应答消息
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        channel.basicConsume(TASK_QUEUE_NAME,false,deliverCallback,(consumerTag)->{
            System.out.println(consumerTag + "消费者1取消消费接口回调逻辑");
        });
```

##### 4、队列持久化

生产者声明队列时，channel.queueDeclare的第二个参数drable:boolean为true

切记：先删除已存在的不持久话的队列，否则会报错

```java
channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
```

##### 5、消息持久化

生产者生消息时，channel.basicPublish的第三个参数本来是null，改为MessageProperties.PERSISTENT_TEXT_PLAIN

```java
channel.basicPublish("",TASK_QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));
```

##### 6、不公平分发

之前队列发送消息是轮询分发，到那时不同场景下每个消费者的处理速度不一样，要根据机器的速度设置不公平的分发

在消费者消费消息之前添加channel.basicQos(1);

##### 7、预取值

不公平分发设置qos为1，会使吞吐量变得很低，当然这是最稳妥的

通过给不同的消费者设置不同的channel.basicQos(x); 让它们缓冲区大小不同

##### 8、发布确认

1、必须要求队列持久化

2、队列中的消息必须持久化

3、发布确认

3.1、开启发布确认   创建channel之后 channel.confirmSelect();

单个确认发布   同步的 耗时31603ms

```java
public static void publicMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            // 服务端返回 false 或超时时间内未返回，生产者可以消息重发
            boolean flag = channel.waitForConfirms();
//            if (flag) {
//                System.out.println("消息发送成功");
//            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布1000条单独确认消息，耗时" + (end - begin) + "ms");

    }
```



批量确认发布  耗时551ms

```java
public static void publicMessageBatch() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        // 批量确认消息大小
        int batchSize = 100;
        for (int i = 0; i < 1000; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            if (i % batchSize == 0) {
                // 服务端返回 false 或超时时间内未返回，生产者可以消息重发
                channel.waitForConfirms();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布1000条批量确认消息，耗时" + (end - begin) + "ms");

    }
```



异步确认发布  耗时73ms

```java
public static void publicMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();


        //消息确认回调的函数
        ConfirmCallback ackCallback = (deliveryTag,multiple) ->{
            System.out.println("确认的消息:"+deliveryTag);
        };
        /**
         * 1.消息的标记
         * 2.是否为批量确认
         */
        //消息确认失败回调函数
        ConfirmCallback nackCallback= (deliveryTag, multiple) ->{
            System.out.println("未确认的消息:"+deliveryTag);
        };

        //准备消息的监听器 监听那些消息成功了，哪些消息失败了
        /**
         * 1.监听哪些消息成功了
         * 2.监听哪些消息失败了
         */
        channel.addConfirmListener(ackCallback,nackCallback);//异步通知

        // 批量确认消息大小
        for (int i = 0; i < 1000; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());

        }

        long end = System.currentTimeMillis();
        System.out.println("发布1000条异步确认消息，耗时" + (end - begin) + "ms");

    }
```



##### 9、处理异步发布确认中未确认的消息

```java
public static void publicMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();

        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();


        //消息确认回调的函数
        ConfirmCallback ackCallback = (deliveryTag,multiple) ->{
            if (multiple) {
                //返回的是小于等于当前序列号的未确认消息 是一个map
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag, true);
                //清除该部分未确认消息
                confirmed.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息:"+deliveryTag);
        };
        /**
         * 1.消息的标记
         * 2.是否为批量确认
         */
        //消息确认失败回调函数
        ConfirmCallback nackCallback= (deliveryTag, multiple) ->{
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息:"+message+"未确认的消息标记"+deliveryTag);
        };

        //准备消息的监听器 监听那些消息成功了，哪些消息失败了
        /**
         * 1.监听哪些消息成功了
         * 2.监听哪些消息失败了
         */
        channel.addConfirmListener(ackCallback,nackCallback);//异步通知

        long begin = System.currentTimeMillis();
        // 批量确认消息大小
        for (int i = 0; i < 1000; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }

        long end = System.currentTimeMillis();
        System.out.println("发布1000条异步确认消息，耗时" + (end - begin) + "ms");

    }
```

##### 10、临时队列

未持久化，重启mq后会消失

##### 11、Fanout

channel.exchangeDeclare(*EXCHANGE_NAME*,"fanout");

所有的消费者都会收到

##### 12、Direct

生产者发布消息时，指定第二个参数发给哪个队列

channel.basicPublish(*EXCHANGE_NAME*,"error",null,message.getBytes(StandardCharsets.*UTF_8*));

消费者声明队列后，指定第一个参数指定哪个队列

channel.queueBind("console",*EXCHANGE_NAME*,"info");

##### 13、Topic

由于Direct只能发送给一个队列，该交换机可以通过routingkey的不同模糊名来匹配多个队列

例如：

```java
 *.orange.*              *.*.rabbit     lazy.#
```

当一个队列的routingkey是#，则类似于fanout了

当一个队列的routingkey中没有#和*，则类似于direct了



##### 14、死信队列

消息过期

队列满了

消息被拒绝

##### 15、延迟队列

当先发送一个时间长的延迟消息，再发送一个短的，必须等第一个消息发送完毕才能发送第二个

解决方法看上方解决延迟队列bug

