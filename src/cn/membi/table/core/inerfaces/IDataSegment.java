package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * 数据段<br>
 *1.一个数据段是一列中的一段数据。<br>
 *2.它是定长的，长度由所在数据表决定。
 *3.
 * @author guolw
 *
 */
public interface IDataSegment {
	/**
	 * 每个记录的字节长度
	 * @return
	 */
	public int rlength();
    /**
     *包含的行个数
     * @return
     */
    public int rowCount();
    
    public boolean init(int rLength,int rowCount);
    
    public boolean growth(int rLength);
    
    /**
     * 将记录的字节形式数据写入该段中
     * @param record
     * @return
     */
    public boolean putRecord(byte[] record);
    
    /**
     * 迭代取出所有记录
     * @return
     */
    public Iterator<byte[]> iterator();
}
