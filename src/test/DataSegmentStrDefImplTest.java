package test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.membi.table.core.defaultimpl.ColRecordImpl;
import cn.membi.table.core.defaultimpl.datasegment.ShortDataSegmentStrDefImpl;
import cn.membi.table.core.inerfaces.IColRecord;
import cn.membi.table.core.inerfaces.datasegment.IDataSegment;
import cn.membi.table.core.util.ByteUtil;

/**
 * 对于字符串列的数据段测试。<br>
 * 1.测试增加方法。<br>
 * 2.测试迭代方法。<br>
 * 3.测试压缩功能。<br>
 * 4.测试边界运算<br>
 * 
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
		int dataL = 1000;
		testdata = new int[1000];
		for (int i = 0; i < dataL; i++) {
			testdata[i] = i;
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ShortDataSegmentStrDefImpl dsd = new ShortDataSegmentStrDefImpl(true);
		dsd.init(4, testdata.length);
		for (int i = 0; i < testdata.length; i++) {
			byte[] bv = ByteUtil.intToByte(testdata[i]);
			dsd.append((short) i);
		}
		// 第j个值取出后应当是i
		Iterator<IColRecord> iter = dsd.iterator();
		int j = 0;
		while (iter.hasNext()) {
			IColRecord record = (IColRecord) iter.next();
			assertEquals(j, record.intValue());
			j++;
		}
	}

}
