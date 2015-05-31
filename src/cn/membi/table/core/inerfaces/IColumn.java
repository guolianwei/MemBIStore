package cn.membi.table.core.inerfaces;

import java.util.Iterator;

public interface IColumn {
    /**
     * 获得列头定义
     * @return
     */
    public IHead head();
    /**
     * 获得第i条记录的字节值
     * @param i
     * @return
     */
    public byte[] record(int i);
    
    /**
     * 获得当前列的迭代器值
     * @return
     */
    public Iterator<byte[]> iterator();
    
    /**
     * 列在表格中的数组下表索引
     * @return
     */
    public int cIndex();
    
}