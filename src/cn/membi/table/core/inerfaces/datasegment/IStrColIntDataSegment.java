package cn.membi.table.core.inerfaces.datasegment;

public interface IStrColIntDataSegment extends IStrColDataSegment {
	/**
	 * 将记录的字节形式数据写入该段中
	 * 
	 * @param record
	 * @return
	 */
	public boolean append(int record);
}
