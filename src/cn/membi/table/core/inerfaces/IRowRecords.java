package cn.membi.table.core.inerfaces;

/**
 * 一行数据中的所有列记录
 * @author guolw
 *
 */
public interface IRowRecords {
    /**
     * 获得所有列数据
     * @return
     */
    IColRecord[] records();
}
