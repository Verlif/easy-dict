package idea.verlif.easy.dict.map;

import idea.verlif.easy.dict.DictItem;
import idea.verlif.easy.dict.DictProvider;

import java.util.HashMap;
import java.util.Map;

public class HashMapDictProvider implements DictProvider {

    private final Map<String, HashMapDictItem> dictItemMap;

    public HashMapDictProvider() {
        this.dictItemMap = new HashMap<>();
    }

    public void put(String tag, String key, String value) {
        HashMapDictItem dictItem = getDictItem(tag);
        dictItem.put(key, value);
    }

    public void put(String tag, Map<String, String> dictMap) {
        HashMapDictItem dictItem = getDictItem(tag);
        dictItem.putAll(dictMap);
    }

    public HashMapDictItem getDictItem(String tag) {
        HashMapDictItem dictItem = dictItemMap.get(tag);
        if (dictItem == null) {
            dictItem = new HashMapDictItem();
            dictItemMap.put(tag, dictItem);
        }
        return dictItem;
    }

    @Override
    public void reload() {
    }

    @Override
    public DictItem get(String tag) {
        return dictItemMap.get(tag);
    }
}
