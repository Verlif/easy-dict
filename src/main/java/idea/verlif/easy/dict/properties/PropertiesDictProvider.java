package idea.verlif.easy.dict.properties;

import idea.verlif.easy.dict.DictProvider;
import idea.verlif.easy.dict.EasyDictException;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PropertiesDictProvider implements DictProvider {

    private static final String SPLIT = "_";
    private static final String END = ".properties";

    private final Map<String, PropertiesDictItem> dictMap;
    private final List<PropertiesRecord> recordList;

    public PropertiesDictProvider() {
        dictMap = new HashMap<>();
        recordList = new ArrayList<>();
    }

    /**
     * 添加资源文件。当设定字典已存在时，进行替换与补充。
     *
     * @param tag  资源文件设定的字典标签。置为null则添加或替换到默认文本资源
     * @param file 资源文件对象
     */
    public void load(String tag, File file) {
        if (!file.exists()) {
            throw new EasyDictException("file doesn't found - " + file.getAbsolutePath());
        }
        if (file.isDirectory()) {
            throw new EasyDictException(file.getAbsolutePath() + " is not a file");
        }
        Properties properties = new Properties();
        PropertiesDictItem dict = get(tag);
        try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new EasyDictException(e);
        }
        recordList.add(new PropertiesRecord(tag, file.getAbsolutePath()));
        if (dict == null) {
            put(tag, new PropertiesDictItem(properties));
        } else {
            dict.append(properties);
        }
    }

    /**
     * 添加资源文件
     *
     * @param path 资源文件或文件夹路径
     */
    public void load(String path) {
        load(new File(path));
    }

    /**
     * 添加资源文件
     *
     * @param file 资源文件或文件夹对象
     */
    public void load(File file) {
        File[] files = file.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(END));
        if (files != null) {
            for (File f : files) {
                handleResource(f);
            }
        } else {
            handleResource(file);
        }
    }

    /**
     * 添加资源文件。当设定字典已存在时，进行替换与补充。
     *
     * @param tag  资源文件设定的字典标签。置为null则添加或替换到默认文本资源
     * @param path 资源文件或文件夹路径
     */
    public void load(String tag, String path) {
        load(tag, new File(path));
    }

    /**
     * 添加资源文件
     *
     * @param path   资源文件或文件夹路径
     * @param filter 资源文件过滤器
     */
    public void load(String path, FileFilter filter) {
        load(new File(path), filter);
    }

    /**
     * 添加资源文件
     *
     * @param dir    资源文件或文件夹对象
     * @param filter 资源文件过滤器
     */
    public void load(File dir, FileFilter filter) {
        if (!dir.exists()) {
            throw new EasyDictException("file doesn't found - " + dir.getAbsolutePath());
        }
        File[] files = dir.listFiles(filter);
        if (files != null) {
            for (File file : files) {
                handleResource(file);
            }
        } else {
            handleResource(dir);
        }
    }

    private void handleResource(File resource) {
        if (resource.isFile()) {
            String filename = resource.getName();
            String tag;
            if (filename.length() > END.length()) {
                String[] parts = filename.substring(0, filename.length() - END.length()).split(SPLIT, 2);
                if (parts.length == 1) {
                    tag = parts[0];
                } else {
                    tag = parts[1];
                }
            } else {
                int i = filename.indexOf('i');
                if (i > -1) {
                    tag = filename.substring(i + 1);
                } else {
                    tag = filename;
                }
            }
            load(tag, resource);
        }
    }

    private void put(String tag, PropertiesDictItem dict) {
        this.dictMap.put(tag, dict);
    }

    @Override
    public void reload() {
        for (PropertiesDictItem dictItem : dictMap.values()) {
            dictItem.clear();
        }
        List<PropertiesRecord> tmp = new ArrayList<>(recordList);
        recordList.clear();
        for (PropertiesRecord record : tmp) {
            load(record.tag, record.path);
        }
    }

    @Override
    public PropertiesDictItem get(String tag) {
        return dictMap.get(tag);
    }

    private final static class PropertiesRecord {
        private final String tag;
        private final String path;

        private PropertiesRecord(String tag, String path) {
            this.tag = tag;
            this.path = path;
        }

        public String getKey() {
            return tag;
        }

        public String getPath() {
            return path;
        }
    }
}
