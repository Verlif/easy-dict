# EasyDict

简单字典，用于做文本转义映射等场景，例如文本多语言、关键词别名等。

## 简单说明

q. 为什么不直接用`Map`作为映射载体？  
a. Map作为内存数据有很大的拓展局限，例如不支持多源、不支持本地载入。

q. 字典提供者的作用是什么？  
a. 字典提供者类似于字典渠道，例如文件渠道、接口渠道、数据库渠道。

q. 字典标签的使用场景是什么？  
a. 字典标签常用于多语言下区分语言，使用不用的tag来标记不同的地区语言。例如**zh**、**en**。

q. `DictItem`是什么？  
a. `DictItem`是字典查询载体，专注于做文本转义。在语言字典场景下，`DictItem`就表示了对应语言的字典。

## 使用

使用步骤一般分为三步：

1. 创建字典提供者
2. 将字典提供者添加到字典使用者中
3. 通过字典使用者查询字典值

```java
@Test
public void easyDictTest() {
    // 创建properties字典提供者
    PropertiesDictProvider propertiesDictProvider = new PropertiesDictProvider();
    // 加载properties文件夹下的所有字典文件数据
    propertiesDictProvider.load("properties");
    // 创建hashMap字典提供者
    HashMapDictProvider hashMapDictProvider = new HashMapDictProvider();
    // 手动添加字典数据
    hashMapDictProvider.put("zh", "nihao", "你好呀");
    hashMapDictProvider.put("zh", "tahao", "他好");
    // 创建字典使用对象
    EasyDict dict = new EasyDict();
    // 将hashMap字典作为优先字典添加，后添加properties字典作为补充
    dict.addProvider(hashMapDictProvider);
    dict.addProvider(propertiesDictProvider);
    // 字典查询
    System.out.println(dict.query("zh", "nihao"));
    System.out.println(dict.query("zh", "tahao"));
    System.out.println(dict.query("en", "nihao"));
}
```

## 依赖

1. 添加Jitpack仓库源

   maven

    ```xml
    <repositories>
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
    </repositories>
    ```

   Gradle

    ```text
    allprojects {
      repositories {
          maven { url 'https://jitpack.io' }
      }
    }
    ```

2. 添加依赖

   __lastVersion__ [![](https://jitpack.io/v/Verlif/easy-dict.svg)](https://jitpack.io/#Verlif/easy-dict)

   maven

   ```xml
      <dependencies>
          <dependency>
              <groupId>idea.verlif</groupId>
              <artifactId>easy-dict</artifactId>
              <version>lastVersion</version>
          </dependency>
      </dependencies>
   ```

   Gradle

   ```text
   dependencies {
     implementation 'com.github.Verlif:easy-dict:lastVersion'
   }
   ```
