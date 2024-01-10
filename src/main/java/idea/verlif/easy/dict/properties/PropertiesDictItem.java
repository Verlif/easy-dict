package idea.verlif.easy.dict.properties;

import idea.verlif.easy.dict.DictItem;

import java.util.Properties;

public class PropertiesDictItem implements DictItem {

    private final Properties properties;

    public PropertiesDictItem(Properties properties) {
        this.properties = properties;
    }

    public void append(Properties properties) {
        this.properties.putAll(properties);
    }

    public void clear() {
        this.properties.clear();
    }

    @Override
    public String query(String key) {
        return properties.getProperty(key);
    }

}
