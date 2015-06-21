package cn.membi.table.core.defaultimpl;

import java.util.Iterator;

import cn.membi.table.core.inerfaces.ICacData;
import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.IColumn;
import cn.membi.table.core.inerfaces.IHead;
import cn.membi.table.core.inerfaces.IMapData;

public class ColumnStrDefImpl implements IColumn {
    
	@Override
	public IHead head() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IColRecord record(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICacData cacData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMapData mapData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<IColRecord> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean appendStr(String strValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appendNumber(byte[] number) {
		// TODO Auto-generated method stub
		return false;
	}

}
