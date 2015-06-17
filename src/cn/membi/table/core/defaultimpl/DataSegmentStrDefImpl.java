package cn.membi.table.core.defaultimpl;

import java.nio.ByteBuffer;
import java.util.Iterator;

import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.IDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * �ַ������ݶε�Ĭ��ʵ�֣�ʵ�����ݵ�ѹ���洢,ѹ���㷨��<br>
 * 1.������ͬ����ʹ�����ӱ��ʵ�֡�<br>
 * 2.���ӱ��ʹ�ø�����ʾ�����磺 ���ĳ���ֽڴ洢ֵΪ-3<br>
 * ���ʾ��ǰ��¼λ����һλ��ֵ��������3����¼��<br>
 * 3.�ڵ���ʱ�Զ����ص�ǰ��¼λ��ʵ���ֽ�ֵ��<br>
 * */
public class DataSegmentStrDefImpl implements IDataSegment {
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
		// ����ѱ���ֵ�͵�ǰ��Ҫ���ӵ�ֵ��ȡ�
		if (record == valueCurrent) {
			// �����ǰ���ۼƳ���2��ֵ��ȡ�
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
