package idea.verlif.easy.dict;

import idea.verlif.easy.dict.map.HashMapDictItem;

public class CachedEasyDict extends EasyDict {

    private final HashMapDictItem cacheDict;

    public CachedEasyDict() {
        super();
        cacheDict = new HashMapDictItem();
    }

    @Override
    public String query(String tag, String key) {
        String cacheKey = tag + "." + key;
        String value = cacheDict.query(cacheKey);
        if (value == null) {
            value = super.query(tag, key);
            cacheDict.put(cacheKey, value);
        }
        return value;
    }
}
