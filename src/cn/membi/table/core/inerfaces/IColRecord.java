package cn.membi.table.core.inerfaces;

/**
 * 一个数据表中的某一列的一条记录
 * 
 * @author guolw
 *
 */
public interface IColRecord {
	/**
	 * 一行记录中某一列数据的字节值
	 * 
	 * @param colIndex
	 * @return
	 */
	public byte[] bytesValue();
}
