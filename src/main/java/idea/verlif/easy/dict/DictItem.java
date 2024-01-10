package idea.verlif.easy.dict;

/**
 * 字典接口，用于查询文本值
 */
public interface DictItem {

    /**
     * 查询文本值
     *
     * @param key 文本key
     * @return 查询到的文本
     */
    String query(String key);
}
