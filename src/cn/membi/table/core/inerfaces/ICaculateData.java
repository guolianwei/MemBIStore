package cn.membi.table.core.inerfaces;

/**
 * 参与统计的数据
 * @author guolw
 *
 */
public interface ICaculateData {
   public IDataSegment[] blocks();
   /**
    * 获得该数据块在表中的起始行号
    * @return
    */
   public int beginRowIndex();
   
   
}
