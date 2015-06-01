package cn.membi.table.core.inerfaces;

public interface IHead {
    /**
     * 列名称
     * @return
     */
    public String name();
    
    /**
     * 列别名
     * @return
     */
    public String aliasName();
    
    public ColumnType type();
    /**
     * 列在表格中的数组下标
     * @return
     */
    public int cIndex();
}
