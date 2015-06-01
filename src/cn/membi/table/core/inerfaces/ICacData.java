package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * ����ͳ�Ƶ�����
 * 
 * @author guolw
 *
 */
public interface ICacData {
	public IDataSegment[] segments();

	/**
	 * ��ø����ݿ��ڱ��е���ʼ�к�
	 * 
	 * @return
	 */
	public int beginRowIndex();

	public Iterator<byte[]> iterator();

	/**
	 * ׷������
	 * 
	 * @param record
	 * @return
	 */
	public boolean append(byte[] record);

}
