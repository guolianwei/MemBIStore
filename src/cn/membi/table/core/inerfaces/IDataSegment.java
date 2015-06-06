package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * 数据段<br>
 *1.一个数据段是一列中的一段数据。<br>
 *2.它存储的记录个数是固定的，长度由所在数据表决定。<br>
 *3.每个记录的字节长度可以扩充,通过growth方法<br>
 *4.
 * @author guolw
 *
 */
public interface IDataSegment {
	/**
	 * 每个记录的字节长度
	 * @return
	 */
	public int colRecLength();
    /**
     *包含的行个数
     * @return
     * 
     */
    public int rowCount();
    
    /**
     * 初始化当前段
     * @param rLength 段中单条记录的字节长度
     * @param rowCount 当前段所包含的记录数。
     * @return
     */
    public boolean init(int colRecLength,int rowCount);
    
    /**
     * 将当前段的单个记录字节长度增加
     * @param colRecLength 增加后的单个列记录的字节长度
     * @return
     */
    public boolean growth(int colRecLength);
    
    /**
     * 将记录的字节形式数据写入该段中
     * @param record
     * @return
     */
    public boolean append(byte[] record);
    
    /**
     * 迭代取出所有记录
     * @return
     */
    public Iterator<IColRecord> iterator();
}
