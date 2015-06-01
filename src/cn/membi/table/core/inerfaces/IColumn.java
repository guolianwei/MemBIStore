package cn.membi.table.core.inerfaces;

import java.util.Iterator;

public interface IColumn {
    /**
     * �����ͷ����
     * @return
     */
    public IHead head();
    /**
     * ��õ�i����¼���ֽ�ֵ
     * @param i
     * @return
     */
    public IColRecord record(int i);
    
    /**
     * ������������
     * @return
     */
    public ICacData cacData();
    
    /**
     * �ַ��е�ӳ������
     * @return
     */
    public IMapData mapData();
    
    /**
     * ��õ�ǰ�еĵ�����ֵ
     * @return
     */
    public Iterator<IColRecord> iterator();
    
    /**
     * ׷���ַ����������ݴ洢��
     * �����ַ�������ͨ��IMapdataӳ��Ϊ���ֱ��롣
     * @param strValue
     * @return
     */
    public boolean  appendStr(String strValue);
    
    /**
     * ׷����ֵ���д洢�����һ�С�
     * @param number
     * @return
     */
    public boolean appendNumber(byte[] number);
    
    
    
}