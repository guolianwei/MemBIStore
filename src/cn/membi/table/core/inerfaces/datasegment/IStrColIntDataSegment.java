package cn.membi.table.core.inerfaces.datasegment;

public interface IStrColIntDataSegment extends IStrColDataSegment {
	/**
	 * ����¼���ֽ���ʽ����д��ö���
	 * 
	 * @param record
	 * @return
	 */
	public boolean append(int record);
}
