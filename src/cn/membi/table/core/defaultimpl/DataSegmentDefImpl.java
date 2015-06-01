package cn.membi.table.core.defaultimpl;

import java.util.Iterator;

import cn.membi.table.core.inerfaces.IDataSegment;

public class DataSegmentDefImpl implements IDataSegment {
    private int colRecLength=-1;
    private byte[] data=null;
	@Override
	public int colRecLength() {
		// TODO Auto-generated method stub
		return colRecLength;
	}

	@Override
	public int rowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean init(int colRecLength, int rowCount) {
		this.colRecLength=colRecLength;
		data=new byte[this.colRecLength];
		return true;
	}

	@Override
	public boolean growth(int colRecLength) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean append(byte[] record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<byte[]> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
