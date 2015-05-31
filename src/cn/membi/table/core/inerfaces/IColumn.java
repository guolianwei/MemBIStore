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
     * 参与计算的数据
     * @return
     */
    public ICacData cacData();
    
    /**
     * 字符列的映射数据
     * @return
     */
    public IMapData mapData();
    
    /**
     * 获得当前列的迭代器值
     * @return
     */
    public Iterator<byte[]> iterator();
    
    /**
     * 追加字符串到列数据存储中
     * 所有字符串都会通过IMapdata映射为数字编码。
     * @param strValue
     * @return
     */
    public boolean  appendStr(String strValue);
    
    /**
     * 追加数值到列存储的最后一行。
     * @param number
     * @return
     */
    public boolean appendNumber(byte[] number);
    
    /**
     * 列在表格中的数组下标
     * @return
     */
    public int cIndex();
    
}