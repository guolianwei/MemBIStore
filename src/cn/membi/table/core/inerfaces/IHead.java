package cn.membi.table.core.inerfaces;

public interface IHead {
    /**
     * ������
     * @return
     */
    public String name();
    
    /**
     * �б���
     * @return
     */
    public String aliasName();
    
    public ColumnType type();
    /**
     * ���ڱ���е������±�
     * @return
     */
    public int cIndex();
}
