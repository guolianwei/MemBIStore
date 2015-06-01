package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * 内存表接口
 * 
 * @author guolw
 *
 */
public interface ITable {
	/**
	 * 追加记录
	 * 
	 * @param record
	 */
	public void append(IRowRecords colRecords);

	public Iterator<IRowRecords> iterator();

	public IColumn[] getColumns();

	public void addColumn(IColumn column);		
	
}
