package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * �ڴ��ӿ�
 * 
 * @author guolw
 *
 */
public interface ITable {
	/**
	 * ׷�Ӽ�¼
	 * 
	 * @param record
	 */
	public void append(IRowRecords colRecords);

	public Iterator<IRowRecords> iterator();

	public IColumn[] getColumns();

	public void addColumn(IColumn column);		
	
}
