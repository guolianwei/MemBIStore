package cn.membi.table.core.inerfaces;

/**
 * ����ͳ�Ƶ�����
 * @author guolw
 *
 */
public interface ICaculateData {
   public IDataSegment[] blocks();
   /**
    * ��ø����ݿ��ڱ��е���ʼ�к�
    * @return
    */
   public int beginRowIndex();
   
   
}
