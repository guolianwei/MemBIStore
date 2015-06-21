package cn.membi.table.core.defaultimpl.datasegment;

import java.nio.ByteBuffer;
import java.util.Iterator;

import cn.membi.table.core.defaultimpl.ColRecordImpl;
import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.datasegment.IDataSegment;
import cn.membi.table.core.inerfaces.datasegment.IStrColDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * 字符串数据段的默认实现，实现数据的压缩存储,压缩算法：<br>
 * 1.相邻相同数据使用下延标记实现。<br>
 * 2.下延标记使用负数表示，例如： 如果某个字节存储值为-3<br>
 * 则表示当前记录位的上一位的值向下延伸3个记录。<br>
 * 3.在迭代时自动返回当前记录位的实际字节值。<br>
 * */
public abstract class AbsStrColDataSegment implements IStrColDataSegment {
	public static final int[] lens = new int[4];
	static {
		lens[1] = Byte.MIN_VALUE;
		lens[2] = Short.MIN_VALUE;
		lens[4] = Integer.MIN_VALUE;
	}
	protected int colRecLength = -1;
	/**
	 * 数据段的定义数据行数，所包含函数不能超过该值
	 */
	protected int defRowCount = -1;
	protected byte[] data = null;
	protected boolean zip = true;
	private int cuZipV = 0;
	int valueCurrent = -1;
	/**
	 * 当前包含的数据行数
	 */
	protected int rowCount;
	/**
	 * 如果新增，则新增字节的在数据中的起始索引
	 * */
	protected int curValueByteIndex = 0;

	public class SegmentIterator implements Iterator<IColRecord> {
		private int curIndex = 0;
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

		@Override
		public boolean hasNext() {
			return curIndex < rowCount();
		}

	}

	protected boolean zip(short record) {
		if (this.zip && zipCaculate(record)) {
			// 将当前的压缩值写入当前位置
			byte[] bytes = ByteUtil.shorToByte((short) cuZipV);
			System.arraycopy(bytes, 0, data, curValueByteIndex, colRecLength);
			rowCount++;
			return true;
		}
		return false;
	}

	/**
	 * 目前的压缩条件符合返回true。
	 * 
	 * @param record
	 * @returns
	 */
	private boolean zipCaculate(int record) {
		// 如果已保存值和当前需要增加的值相等。
		if (record == valueCurrent && cuZipV > lens[colRecLength]) {
			cuZipV = ByteBuffer.wrap(data, curValueByteIndex, colRecLength)
					.getInt();
			cuZipV = cuZipV - 1;
			return true;
		} else {
			valueCurrent = record;
			cuZipV = 0;
			return false;
		}
	}

	@Override
	public int colRecLength() {
		return colRecLength;
	}

	public int defRowCount() {
		return defRowCount;
	}

	/**
	 * 
	 * @see cn.membi.table.core.inerfaces.datasegment.IDataSegment#init(int,
	 *      int)
	 */
	@Override
	public boolean init(int colRecLength, int defRowCount) {
		this.colRecLength = colRecLength;
		this.defRowCount = defRowCount;
		data = new byte[this.colRecLength * defRowCount];
		return true;
	}

	protected boolean checkBoundary() {
		if (rowCount >= this.defRowCount) {
			return false;
		}
		return true;
	}

	@Override
	public int rowCount() {
		return this.rowCount;
	}

	public Iterator<IColRecord> iterator() {
		return (Iterator<IColRecord>) new SegmentIterator();
	}
}
