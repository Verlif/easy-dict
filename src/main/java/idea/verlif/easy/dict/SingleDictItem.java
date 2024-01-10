package idea.verlif.easy.dict;

/**
 * 单体字典数据，不区分tag标签。
 */
public interface SingleDictItem extends DictItem, DictProvider {

    @Override
    default DictItem get(String tag) {
        return this;
    }
}
