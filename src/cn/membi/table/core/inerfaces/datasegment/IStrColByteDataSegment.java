package cn.membi.table.core.inerfaces.datasegment;

public interface IStrColByteDataSegment extends IStrColDataSegment {
	/**
	 * ����¼���ֽ���ʽ����д��ö���
	 * 
	 * @param record
	 * @return
	 */
	public boolean append(byte record);
}
