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
    public byte[] record(int i);
    
    /**
     * ��õ�ǰ�еĵ�����ֵ
     * @return
     */
    public Iterator<byte[]> iterator();
    
    /**
     * ���ڱ���е������±�����
     * @return
     */
    public int cIndex();
    
}