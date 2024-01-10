package idea.verlif.easy.dict;

/**
 * 字典提供者
 */
public interface DictProvider {

    /**
     * 重新加载字典，常用于刷新带缓存的字典
     */
    void reload();

    /**
     * 通过序号获取字典
     *
     * @param tag 字典标签
     * @return 字典接口
     */
    DictItem get(String tag);
}
