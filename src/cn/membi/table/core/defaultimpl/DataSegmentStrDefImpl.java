package cn.membi.table.core.defaultimpl;

import java.nio.ByteBuffer;
import java.util.Iterator;

import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.datasegment.IDataSegment;
import cn.membi.table.core.inerfaces.datasegment.IStrColShortDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * �ַ������ݶε�Ĭ��ʵ�֣�ʵ�����ݵ�ѹ���洢,ѹ���㷨��<br>
 * 1.������ͬ����ʹ�����ӱ��ʵ�֡�<br>
 * 2.���ӱ��ʹ�ø�����ʾ�����磺 ���ĳ���ֽڴ洢ֵΪ-3<br>
 * ���ʾ��ǰ��¼λ����һλ��ֵ��������3����¼��<br>
 * 3.�ڵ���ʱ�Զ����ص�ǰ��¼λ��ʵ���ֽ�ֵ��<br>
 * */
public class DataSegmentStrDefImpl implements IStrColShortDataSegment {
	public static final int[] lens = new int[4];
	static {
		lens[1] = Byte.MIN_VALUE;
		lens[2] = Short.MIN_VALUE;
		lens[4] = Integer.MIN_VALUE;
	}
	private int colRecLength = -1;
	/**
	 * ���ݶεĶ��������������������������ܳ�����ֵ
	 */
	private int defRowCount = -1;
	private byte[] data = null;
	private boolean zip = true;

	/**
	 * �Ƿ���ѹ����Ĭ��true
	 * 
	 * @param zip
	 *            �Ƿ���ѹ����Ĭ��true
	 */
	public DataSegmentStrDefImpl(boolean zip) {
		super();
		this.zip = zip;
	}

	/**
	 * ��ǰ��������������
	 */
	private int rowCount;
	/**
	 * ����������������ֽڵ��������е���ʼ����
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
	 * @see cn.membi.table.core.inerfaces.datasegment.IDataSegment#init(int, int)
	 */
	@Override
	public boolean init(int colRecLength, int defRowCount) {
		this.colRecLength = colRecLength;
		this.defRowCount = defRowCount;
		data = new byte[this.colRecLength * defRowCount];
		return true;
	}

	int valueCurrent = -1;

	@Override
	public boolean append(byte record) {
		if (checkBoundary()) {
			data[curValueByteIndex] = record;
			curValueByteIndex += colRecLength;
			valueCurrent = (int) record;
			rowCount++;
			return true;
		} else {
			return false;
		}
	}

	private boolean checkBoundary() {
		if (rowCount >= this.defRowCount) {
			return false;
		}
		return true;
	}

	@Override
	public boolean append(short record) {
		if (checkBoundary()) {
			if (zip(record)) {
				return true;
			}
			byte[] bytes = ByteUtil.shorToByte(record);
			System.arraycopy(bytes, 0, data, curValueByteIndex, colRecLength);
			curValueByteIndex += colRecLength;
			rowCount++;
			return true;
		}
		return false;
	}

	private boolean zip(short record) {
		if (this.zip && zipCaculate(record)) {
			// ����ǰ��ѹ��ֵд�뵱ǰλ��
			byte[] bytes = ByteUtil.shorToByte((short) cuZipV);
			System.arraycopy(bytes, 0, data, curValueByteIndex, colRecLength);
			rowCount++;
			return true;
		}
		return false;
	}

	private int cuZipV = 0;

	/**
	 * Ŀǰ��ѹ���������Ϸ���true��
	 * 
	 * @param record
	 * @return
	 */
	private boolean zipCaculate(int record) {
		// ����ѱ���ֵ�͵�ǰ��Ҫ���ӵ�ֵ��ȡ�
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
	public boolean append(int record) {
		if (this.checkBoundary()) {
			byte[] bytes = ByteUtil.intToByte(record);
			System.arraycopy(bytes, 0, data, curValueByteIndex, colRecLength);
			curValueByteIndex += colRecLength;
			rowCount++;
			return true;
		}
		return false;
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
