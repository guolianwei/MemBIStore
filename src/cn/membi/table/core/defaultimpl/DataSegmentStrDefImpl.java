package cn.membi.table.core.defaultimpl;

import java.nio.ByteBuffer;
import java.util.Iterator;

import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.IDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * 字符串数据段的默认实现，实现数据的压缩存储,压缩算法：<br>
 * 1.相邻相同数据使用下延标记实现。<br>
 * 2.下延标记使用负数表示，例如： 如果某个字节存储值为-3<br>
 * 则表示当前记录位的上一位的值向下延伸3个记录。<br>
 * 3.在迭代时自动返回当前记录位的实际字节值。<br>
 * */
public class DataSegmentStrDefImpl implements IDataSegment {
	private int colRecLength = -1;
	/**
	 * 数据段的定义数据行数，所包含函数不能超过该值
	 */
	private int defRowCount = -1;
	private byte[] data = null;
	private boolean zip = true;

	/**
	 * 是否安列压缩，默认true
	 * 
	 * @param zip
	 *            是否按列压缩，默认true
	 */
	public DataSegmentStrDefImpl(boolean zip) {
		super();
		this.zip = zip;
	}

	/**
	 * 当前包含的数据行数
	 */
	private int rowCount;
	/**
	 * 如果新增，则新增字节的在数据中的起始索引
	 * */
	private int curValueByteIndex = 0;

	@Override
	public int colRecLength() {
		return colRecLength;
	}

	public int defRowCount() {
		return defRowCount;
	}

	/**
	 * 
	 * @see cn.membi.table.core.inerfaces.IDataSegment#init(int, int)
	 */
	@Override
	public boolean init(int colRecLength, int defRowCount) {
		this.colRecLength = colRecLength;
		this.defRowCount = defRowCount;
		data = new byte[this.colRecLength * defRowCount];
		return true;
	}

	@Override
	public boolean growth(int colRecLength) {
		return false;
	}

	int valueCurrent = -1;

	@Override
	public boolean append(byte record) {
		if (rowCount >= this.defRowCount) {
			return false;
		}
		data[curValueByteIndex] = record;
		curValueByteIndex += colRecLength;
		valueCurrent = (int) record;
		rowCount++;
		return true;
	}

	@Override
	public boolean append(short record) {
		if (rowCount >= this.defRowCount) {
			return false;
		}
		byte[] bytes = ByteUtil.shorToByte(record);
		System.arraycopy(bytes, 0, data, curValueByteIndex, colRecLength);
		curValueByteIndex += colRecLength;
		rowCount++;
		return true;
	}

	private boolean zipCaculate(int record) {
		// 如果已保存值和当前需要增加的值相等。
		if (record == valueCurrent) {
			// 如果当前已累计超过2个值相等。
			int cuV = ByteBuffer.wrap(data, curValueByteIndex, colRecLength)
					.getInt();
			if (cuV < 0) {
				cuV = cuV - 1;
			}

			return false;
		} else {
			valueCurrent = record;

			return true;
		}
	}

	@Override
	public boolean append(int record) {
		if (rowCount >= this.defRowCount) {
			return false;
		}
		byte[] bytes = ByteUtil.intToByte(record);
		System.arraycopy(bytes, 0, data, curValueByteIndex, colRecLength);
		curValueByteIndex += colRecLength;
		rowCount++;
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

	@Override
	public int rowCount() {
		return this.rowCount;
	}
}
