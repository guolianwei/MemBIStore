package cn.membi.table.core.inerfaces;

import java.util.Iterator;
public interface ITable {
   public void addRecord(IRecord record);
   public Iterator<IRecord> iterator();
   public IColumn[] getColumns();
}
