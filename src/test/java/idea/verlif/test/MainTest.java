package idea.verlif.test;

import idea.verlif.easy.dict.CachedEasyDict;
import idea.verlif.easy.dict.EasyDict;
import idea.verlif.easy.dict.map.HashMapDictProvider;
import idea.verlif.easy.dict.properties.PropertiesDictProvider;
import org.junit.Test;

public class MainTest {

    public static void main(String[] args) {
    }

    /**
     * 简单并发测试
     */
    @Test
    public void concurrencyTest() {
        EasyDict dict = new CachedEasyDict();
        for (int i = 0; i < 100; i++) {
            dict.addProvider(new HashMapDictProvider());
        }
        for (int i = 0; i < 100; i++) {
            new Thread(() -> dict.addProvider(new HashMapDictProvider())).start();
            new Thread(() -> dict.query("zh", "nihao")).start();
        }
    }

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

    @Test
    public void cachedEasyDictTest() {
        PropertiesDictProvider propertiesDictProvider = new PropertiesDictProvider();
        propertiesDictProvider.load("properties");
        HashMapDictProvider hashMapDictProvider = new HashMapDictProvider();
        hashMapDictProvider.put("zh", "nihao", "你好呀");
        hashMapDictProvider.put("zh", "tahao", "他好");
        EasyDict dict = new CachedEasyDict();
        dict.addProvider(hashMapDictProvider);
        dict.addProvider(propertiesDictProvider);
        System.out.println(dict.query("zh", "nihao"));
        System.out.println(dict.query("zh", "tahao"));
        System.out.println(dict.query("en", "nihao"));
    }

    @Test
    public void PropertiesDictTest() {
        PropertiesDictProvider provider = new PropertiesDictProvider();
        provider.load("properties\\zh2.properties");
        EasyDict dict = new EasyDict();
        dict.addProvider(provider);
        System.out.println(dict.query("zh", "nihao"));
        System.out.println(dict.query("en", "nihao"));
    }

}
