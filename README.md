# slog4j

slog4j 全称为Java智子日志记录框架,英文为 sophon logger for java

![image](https://github.com/torocket/slog4j/blob/master/label/java.svg)
![image](https://github.com/torocket/slog4j/blob/master/label/codesize.svg)
![image](https://github.com/torocket/slog4j/blob/master/label/codeline.svg)
![image](https://github.com/torocket/slog4j/blob/master/label/version.svg)

# slog4j的优点

### 1.不用在每个类中都加上下面这段代码

```java
private static final Logger log = LoggerFactory.getLogger(Main.class);
```

### 2.可以直接在类中使用,并且不用声明任何对象
**因为它是静态的**
```java
Logger.info("xxx");
Logger.debug("xxx");
Logger.error("xxx");
Logger.warn("xxx");
Logger.exception(e);
```

### 3.slog4j可单独为一个类创建独立的日志输出文件

例如有些类的日志输出你想单独写出到一个文件中，你可以这样:
```java
class Main{
  @Separation("classpath:/example/example.log")
  private static SophonLogger log;
  // 手动注入
  static{
        LoggerFactory.injection();
  }
  public static void main(String[] args){
    int count = 2;
    log.info("count:{}",count);
  }
}
```
这样写就只需要加入注解和定义对象即可，唯一比较麻烦的地方就是需要手动注入，这个后续会在1.0版本中修改它。

你可以定义多个这样的log，每个log都会往你定义的不同文件输出。

### 特性

**全局异常捕获**

slog4j还有一个比较不错的特性就是说它能够对整个项目的异常进行捕捉，并且在后台会记录到文件中

**原子性**

slog4j具有**原子性**,能够保证日志不会在一些特殊情况下丢失。
比如说当一个日志文件达到指定大小后，在生成另一个文件时，中间日志会不丢失。

**日志分离**

slog4j要分离日志是非常简单的，你只要定义一个对象和一个注解即可。

### 生成策略

假设你配置了文件大小到达1024kb后重新生成一个文件,当系统运行到一半挂掉了或者是其他。
slog4j会在你下一次启动时继续接着上次的那个文件继续输出日志。
**如果你尝试使用一下slog4j,我相信你会爱上它的!**

### 问题反馈:

**作者邮箱:keyboardman@foxmail.com**
