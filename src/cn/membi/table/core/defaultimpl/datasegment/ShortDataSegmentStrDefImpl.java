package cn.membi.table.core.defaultimpl.datasegment;

import cn.membi.table.core.inerfaces.datasegment.IStrColShortDataSegment;
import cn.membi.table.core.util.ByteUtil;


public class ShortDataSegmentStrDefImpl extends AbsStrColDataSegment implements
		IStrColShortDataSegment {
	/**
	 * �Ƿ���ѹ����Ĭ��true
	 * 
	 * @param zip
	 *            �Ƿ���ѹ����Ĭ��true
	 */
	public ShortDataSegmentStrDefImpl(boolean zip) {
		super();
		this.zip = zip;
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

}
