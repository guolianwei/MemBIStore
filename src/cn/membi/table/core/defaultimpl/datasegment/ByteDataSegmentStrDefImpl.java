package cn.membi.table.core.defaultimpl.datasegment;

import cn.membi.table.core.inerfaces.datasegment.IStrColByteDataSegment;

public class ByteDataSegmentStrDefImpl extends AbsStrColDataSegment implements
		IStrColByteDataSegment {
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

}
