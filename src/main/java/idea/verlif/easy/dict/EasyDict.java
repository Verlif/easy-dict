package idea.verlif.easy.dict;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 简单字典
 */
public class EasyDict implements DictProvider {

    private final List<DictProvider> providerList;

    public EasyDict() {
        providerList = new CopyOnWriteArrayList<>();
    }

    /**
     * 添加字典提供者。<br/>
     * 添加顺序表示了查询优先级，先添加的提供者会优先查询。
     *
     * @param dictProvider 字典提供者
     */
    public void addProvider(DictProvider dictProvider) {
        providerList.add(dictProvider);
    }

    /**
     * 添加优先的字典提供者。<br/>
     * 添加比已添加的提供者更有查询优先级的字典提供者。
     *
     * @param dictProvider 字典提供者
     */
    public void topProvider(DictProvider dictProvider) {
        providerList.add(0, dictProvider);
    }

    /**
     * 清空字典提供者
     */
    public void clearProvider() {
        providerList.clear();
    }

    @Override
    public void reload() {
        for (DictProvider provider : providerList) {
            provider.reload();
        }
    }

    @Override
    public DictItem get(String tag) {
        DictItem dictItem = null;
        for (DictProvider provider : providerList) {
            dictItem = provider.get(tag);
            if (dictItem != null) {
                break;
            }
        }
        return dictItem;
    }

    /**
     * 获取文本值
     *
     * @param tag 字典标签
     * @param key 文本key
     * @return 对应字典中的文本值
     */
    public String query(String tag, String key) {
        String text = null;
        for (DictProvider provider : providerList) {
            DictItem dictItem = provider.get(tag);
            if (dictItem != null) {
                text = dictItem.query(key);
                if (text != null) {
                    break;
                }
            }
        }
        return text == null ? key : text;
    }
}
