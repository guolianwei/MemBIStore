package cn.membi.table.core.defaultimpl;

import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.util.ByteUtil;

public class ColRecordImpl implements IColRecord {
	byte[] bytesValue;
	public ColRecordImpl(byte[] bytesValue_){
		bytesValue=bytesValue_;
	}
	
	@Override
	public byte[] bytesValue() {
		// TODO Auto-generated method stub
		return bytesValue;
	}

	@Override
	public int intValue() {
		return ByteUtil.byteToInt(bytesValue);
	}

}
