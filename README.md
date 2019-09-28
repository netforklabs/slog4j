# slog4j

![image](https://github.com/torocket/slog4j/blob/master/label/java.svg)
![image](https://github.com/torocket/slog4j/blob/master/label/codesize.svg)
![image](https://github.com/torocket/slog4j/blob/master/label/codeline.svg)
![image](https://github.com/torocket/slog4j/blob/master/label/version.svg)

slog4j(sophon logger for java)，这是一款轻量级的日志记录框架，它减少了那些烦人的配置，秒上手。

## 为什么要选择slog4j?

- 上手难度低，配置少
- 引入即可用
- 支持maven
- 自动捕捉系统异常
- 美化异常信息输出
- 框架很轻

**如果你已经准备好使用slog4j了，那么我们就开始吧！**

# 下载slog4j

**slog4j可通过jar包配置也可通过maven配置**

## 1.通过jar包的方式引入slog4j

如果您的需求是需要用jar引入的话，那么您可以访问当前项目目录的version文件夹，里面包含了源码包以及使用的jar包和doc

## 2.通过maven的方式引入slog4j

maven引入，当前项目还不能在 https://mvnrepository.com 搜索到，只能在 https://search.maven.org 中所有。搜索slog4j即可。

```xml
<dependency>
  <groupId>io.github.torocket</groupId>
  <artifactId>slog4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

# 使用教程

**简介**
slog4j有3个独立的日志对象分别为：**Logger、SystemLogger、ExceptionLogger**，那么它们有什么用呢？

    Logger对象是开发者调用的对象，用于对开发中的日志信息做打印和输出。
    SystemLogger是slog4j框架使用的异常记录对象，在平常的开发中不必使用它。
    ExceptionLogger是为异常做记录的一个对象，它对异常信息做了一些处理，不会影响到堆栈的调用顺序。
    
**日志打印**

很简单，你不必去创建一个类似这样的对象声明：
```java
private static final Logger log = LoggerFactory.getLogger(Example.class);
```
slog4j可以直接调用
```java
Logger.info("im sb");
```

slog4j支持4种输出（本来还有exception的，被我去掉了...），分别为：info、error、debug、warn。

这些都是直接调用即可。
```java
Logger.info("im sb");
Logger.error("im sb");
Logger.debug("im sb");
Logger.warn("im sb");
```
文件的输出路径在slog4j.properties配置即可（properties有注释说明）。

**异常捕获**

假设在你系统的运行过程中出现了异常，但是如果你要去查日志的话会去一大堆日志中去找异常信息，并且异常信息还是你手动记录的，而slog4j帮你解决了这个问题。

如果说我们在系统中运行出现了一个空指针异常，那么slog4j会捕捉到这个异常，并且单独记录在一个日志文件中。

```java
// 我们故意制造一个异常来看下
public static void main(String[] args) {
  Logger.info("system start...");
  ArrayList list = null;
  list.add("乌拉");
}
```

当发生异常时，控制台打印：

```
--- com.sophon.Example3: main ---
--- 2019-09-25 05:40:27 ---
  
  java.lang.NullPointerException
at com.sophon.Example3.main(Example3.java:20)
```
这个异常信息会被记录到slog4j系统文件夹中，如果说系统在linux运行过程中出现了这个问题，那么开发者就可以在**system_exception.log**文件中找到它，并kill it！

**日志分离**

假设说我们当前想在一个类中打印两个或N个不同的信息到独立的日志文件中怎么办？很简单，加个日志声明即可。

我这边模拟一个即时通讯服务的消息推送的场景。我们分别有ios和安卓的推送方法，如果说这时我想把ios的推送记录输出到一个日志文件中，把安卓的推送记录输出到另一个日志文件中怎么弄呢？如下：

```java
class Example{

  //
  // 定义两个日志对象，并加上@Separation注解。
  // 注解中传入日志文件的输出路径即可。
  // 注：classpath: 代表的是当前工程的根目录，和src同一个目录。
  // 如果说你想放到其他盘符或者是其他文件下，去掉classpath:改为E:或者/var/log/push..即可
  //
  
  // ios推送
  @Separation("classpath:/push/ios/ios_push.log")
  private static SophonLogger iosPush;
  
  // android推送
  @Separation("classpath:/push/android/android_push.log")
  private static SophonLogger androidPush;
  
  // 对象注入，这个注入只存在于1.0版本，后续会去掉。
  static {
    LoggerFactory.injection();
  }
  
  public static void main(String[] args){
    ios()；
    android();
  }
  
  public static void ios(){
    iosPush.info("ios 推送...");
  }
  
  public static void android(){
    androidPush.info("ios 推送...");
  }
  
}
```

那么上面这段代码执行过程中就会在项目根目录创建一个叫做**push**的文件夹，push文件夹中又包含了**ios**和**android**文件夹，这两个文件夹下分别就是**iosPush**日志的输出文件和**androidPush**日志的输出文件，两个不同的日志对象就完全独立开了。

**有问题，找作者：keyboardman@foxmail.com**

