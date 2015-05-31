package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * ���ݶ�<br>
 *1.һ�����ݶ���һ���е�һ�����ݡ�<br>
 *2.���Ƕ����ģ��������������ݱ������
 *3.
 * @author guolw
 *
 */
public interface IDataSegment {
	/**
	 * ÿ����¼���ֽڳ���
	 * @return
	 */
	public int rlength();
    /**
     *�������и���
     * @return
     */
    public int rowCount();
    
    public boolean init(int rLength,int rowCount);
    
    public boolean growth(int rLength);
    
    /**
     * ����¼���ֽ���ʽ����д��ö���
     * @param record
     * @return
     */
    public boolean putRecord(byte[] record);
    
    /**
     * ����ȡ�����м�¼
     * @return
     */
    public Iterator<byte[]> iterator();
}
