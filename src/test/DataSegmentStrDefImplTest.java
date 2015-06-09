package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.membi.table.core.defaultimpl.ColRecordImpl;
import cn.membi.table.core.defaultimpl.DataSegmentStrDefImpl;
import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.IDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * �����ַ����е����ݶβ��ԡ�<br>
 * 1.�������ӷ�����<br>
 * 2.���Ե���������<br>
 * 3.����ѹ�����ܡ�<br>
 * 4.���Ա߽�����<br>
 * @author guolw
 *
 */
public class DataSegmentStrDefImplTest {
    int[] testdata;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int dataL=1000;
		testdata=new int[1000];
		for(int i=0;i<dataL;i++){
			testdata[i]=i;
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		IDataSegment dsd=new DataSegmentStrDefImpl();
		dsd.init(4, testdata.length);
		for (int i=0;i<testdata.length;i++){
			byte[] bv=ByteUtil.intToByte(testdata[i]);
			dsd.append(bv);
		}
		//��j��ֵȡ����Ӧ����i
		Iterator<IColRecord> iter=dsd.iterator();
		int j=0;
		while(iter.hasNext()){
			IColRecord record=(IColRecord)iter.next();
			assertEquals(j, record.intValue());
			j++;
		}
	}

}
