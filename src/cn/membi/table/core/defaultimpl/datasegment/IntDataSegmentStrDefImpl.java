package cn.membi.table.core.defaultimpl.datasegment;

import cn.membi.table.core.inerfaces.datasegment.IStrColIntDataSegment;
import cn.membi.table.core.util.ByteUtil;

public class IntDataSegmentStrDefImpl extends AbsStrColDataSegment implements
		IStrColIntDataSegment {
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
}
