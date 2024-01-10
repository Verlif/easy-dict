package idea.verlif.easy.dict.map;

import idea.verlif.easy.dict.DictItem;

import java.util.HashMap;

public class HashMapDictItem extends HashMap<String, String> implements DictItem {

    @Override
    public String query(String key) {
        return get(key);
    }
}
