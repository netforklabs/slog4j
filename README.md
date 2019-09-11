# slog4j
slog4j 全称为Java智子日志记录框架,英文为 sophon logger for java

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
@Alone("/test/test.log")
class Main{
  static final SophonLogger alone = new LoggerFactory.getLogger(Main.class);
  public static void main(String[] args){
    int count = 2;
    alone.info("count:{}",count);
  }
}
```
这样写的话只要是alone调用日志打印都会单独输出到一个文件下，日志文件名默认为类名。

### 生成策略

假设你配置了文件大小到达1024kb后重新生成一个文件,当系统运行到一半挂掉了或者是其他。
slog4j会在你下一次启动时继续接着上次的那个文件继续输出日志。

**如果你尝试使用一下slog4j,我相信你会爱上它的!**
