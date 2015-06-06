package cn.membi.table.core.defaultimpl;

import java.util.Iterator;

import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.IDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * 字符串数据段的默认实现，实现数据的压缩存储,压缩算法：<br>
 * 1.相邻相同数据使用下延标记实现。<br>
 * 2.下延标记使用负数实现。例如 如果某个字节存储值为-3,并且当前数据段所存储数据为String类型。<br>
 * 则表示当前记录位的上一位的值向下延伸3个记录。<br>
 * 3.在迭代时自动返回当前记录位的实际字节值。<br>
 * */
public class DataSegmentStrDefImpl implements IDataSegment {
	private int colRecLength = -1;
	private int rowCount = -1;
	private byte[] data = null;
	/**
	 * 如果新增，则新增字节的在数据中的起始索引
	 * */
	private int curValueByteIndex = 0;

	@Override
	public int colRecLength() {
		return colRecLength;
	}

	@Override
	public int rowCount() {
		return rowCount;
	}

	/**
	 * @see cn.membi.table.core.inerfaces.IDataSegment#init(int, int)
	 */
	@Override
	public boolean init(int colRecLength, int rowCount) {
		this.colRecLength = colRecLength;
		this.rowCount = rowCount;
		data = new byte[this.colRecLength * rowCount];
		return true;
	}

	@Override
	public boolean growth(int colRecLength) {
		return false;
	}

	@Override
	public boolean append(byte[] record) {
		System.arraycopy(record, 0, data, curValueByteIndex, colRecLength);
		curValueByteIndex += colRecLength;
		return true;
	}

	@Override
	public Iterator<IColRecord> iterator() {
		return (Iterator<IColRecord>) new SegmentIterator();
	}

	public class SegmentIterator implements Iterator<IColRecord> {
		private int curIndex = 0;

		@Override
		public boolean hasNext() {
			return curIndex < rowCount();
		}

		int curZipSign = 0;
		byte[] bufferZipRelay;
		int indexReal = 0;

		@Override
		public IColRecord next() {
			curIndex++;
			byte[] buffer = new byte[colRecLength];
			System.arraycopy(data, indexReal * colRecLength, buffer, 0,
					colRecLength);
			int value = ByteUtil.byteToInt(buffer);
			if (value < 0) {
				curZipSign = value;
				curZipSign++;
				return new ColRecordImpl(bufferZipRelay);
			}
			bufferZipRelay = buffer;
			indexReal++;
			return new ColRecordImpl(buffer);
		}

		@Override
		public void remove() {

		}

	}
}
