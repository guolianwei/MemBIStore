package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * 参与统计的数据
 * 
 * @author guolw
 *
 */
public interface ICacData {
	public IDataSegment[] segments();

	/**
	 * 获得该数据块在表中的起始行号
	 * 
	 * @return
	 */
	public int beginRowIndex();

	public Iterator<byte[]> iterator();

	/**
	 * 追加数据
	 * 
	 * @param record
	 * @return
	 */
	public boolean append(byte[] record);

}
