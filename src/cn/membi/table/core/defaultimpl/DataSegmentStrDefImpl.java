package cn.membi.table.core.defaultimpl;

import java.util.Iterator;

import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.IDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * �ַ������ݶε�Ĭ��ʵ�֣�ʵ�����ݵ�ѹ���洢,ѹ���㷨��<br>
 * 1.������ͬ����ʹ�����ӱ��ʵ�֡�<br>
 * 2.���ӱ��ʹ�ø���ʵ�֡����� ���ĳ���ֽڴ洢ֵΪ-3,���ҵ�ǰ���ݶ����洢����ΪString���͡�<br>
 * ���ʾ��ǰ��¼λ����һλ��ֵ��������3����¼��<br>
 * 3.�ڵ���ʱ�Զ����ص�ǰ��¼λ��ʵ���ֽ�ֵ��<br>
 * */
public class DataSegmentStrDefImpl implements IDataSegment {
	private int colRecLength = -1;
	private int rowCount = -1;
	private byte[] data = null;
	/**
	 * ����������������ֽڵ��������е���ʼ����
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
