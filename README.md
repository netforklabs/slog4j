# slog4j
slog4j 全称为Java智子日志记录框架,英文为 sophon logger for java

# slog4j的优点

### 1.不用在每个类中都加上下面这段代码

```java
private static final Logger log = LoggerFactory.getLogger(Main.class);
```

### 2.可以直接在类中使用,并且不用声明任何对象
```java
slog.info("xxx");
slog.debug("xxx");
slog.error("xxx");
slog.warn("xxx");
slog.exception(e);
```

### 3.slog4j可单独为一个类创建独立的日志输出文件

例如有些类的日志输出你想单独写出到一个文件中，你可以这样：

**使用对象创建的方式**
```java
class Main{
  static final SophonLogger alone = new LoggerFactory.getLogger(Main.class);
  public static void main(String[] args){
    int count = 2;
    alone.info("count:{}",count);
  }
}
```
这样写的话只要是alone调用日志打印都会单独输出到一个文件下，日志文件名默认为类名。

**使用注解方式**
当然了，我还提供了注解类型的方法供使用，但注解类型对于上面的例子来说效率相对较低。
```java
@Alone
class Main{
  public static void main(String[] args){
    int count = 2;
    slog.info("count:{}",count);
  }
}
```
使用注解相对来说会较方便一点，但是效率也会相对较低，不过也低不了多少。

# 为什么我会写 slog4j?
我这个人其实是非常偷懒的,先开始使用日志的时候需要在每个类中都加上private...LoggerFactory.getLogger(xxx.class);

到后来我发现这样写起来太繁琐了,而且很没必要,然后我开始使用lombok的@Slf4j注解,用久了也觉得不舒服,这个只是比声明对象少了几个字罢了

再到后来我就在想怎么做一款不用声明就能输出的日志框架,于是就诞生出了slog4j
