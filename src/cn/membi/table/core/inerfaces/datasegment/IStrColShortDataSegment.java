package cn.membi.table.core.inerfaces.datasegment;

public interface IStrColShortDataSegment extends IStrColDataSegment {
	/**
	 * ����¼���ֽ���ʽ����д��ö���
	 * 
	 * @param record
	 * @return
	 */
	public boolean append(short record);
}
