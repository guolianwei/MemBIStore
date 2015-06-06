package cn.membi.table.core.inerfaces;

import java.util.Iterator;

/**
 * ���ݶ�<br>
 *1.һ�����ݶ���һ���е�һ�����ݡ�<br>
 *2.���洢�ļ�¼�����ǹ̶��ģ��������������ݱ������<br>
 *3.ÿ����¼���ֽڳ��ȿ�������,ͨ��growth����<br>
 *4.
 * @author guolw
 *
 */
public interface IDataSegment {
	/**
	 * ÿ����¼���ֽڳ���
	 * @return
	 */
	public int colRecLength();
    /**
     *�������и���
     * @return
     * 
     */
    public int rowCount();
    
    /**
     * ��ʼ����ǰ��
     * @param rLength ���е�����¼���ֽڳ���
     * @param rowCount ��ǰ���������ļ�¼����
     * @return
     */
    public boolean init(int colRecLength,int rowCount);
    
    /**
     * ����ǰ�εĵ�����¼�ֽڳ�������
     * @param colRecLength ���Ӻ�ĵ����м�¼���ֽڳ���
     * @return
     */
    public boolean growth(int colRecLength);
    
    /**
     * ����¼���ֽ���ʽ����д��ö���
     * @param record
     * @return
     */
    public boolean append(byte[] record);
    
    /**
     * ����ȡ�����м�¼
     * @return
     */
    public Iterator<IColRecord> iterator();
}
