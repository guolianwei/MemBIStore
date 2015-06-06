package cn.membi.table.core.util;

import java.nio.ByteBuffer;

public class ByteUtil {
	// 双精度浮点到字节转换
	public static byte[] doubleToByte(double d) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(d);
		return bytes;
	}

	// 字节到 双精度浮点转换
	public static double byteToDouble(byte[] b) {
		return ByteBuffer.wrap(b).getDouble();
	}

	// 字节到 双精度浮点转换
	public static double byteToDouble(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getDouble();
	}

	// 字节到 单精度浮点转换
	public static float byteToFloat(byte[] b) {
		return ByteBuffer.wrap(b).getFloat();
	}

	// 字节到 单精度浮点转换
	public static float byteToFloat(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getFloat();
	}

	// 单精度浮点到字节转换
	public static byte[] floatToByte(float d) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putFloat(d);
		return bytes;
	}

	// 整数到字节数组的转换
	public static byte[] intToByte(int number) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putInt(number);
		return bytes;
	}

	// 字节数组到整数的转换
	public static int byteToInt(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getInt();
	}

	// 字节数组到整数的转换
	public static int byteToInt(byte[] b) {
		return ByteBuffer.wrap(b).getInt();
	}

	// 长整数到字节数组的转换
	public static byte[] longToByte(long number) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putLong(number);
		return bytes;
	}

	// 字节数组到长整数的转换
	public static long byteToLong(byte[] b) {
		return ByteBuffer.wrap(b).getLong();
	}

	/**
	 * 字节数组到长整数的转换
	 * 
	 * @param array
	 *            The array that will back the new buffer
	 * 
	 * @param offset
	 *            The offset of the subarray to be used; must be non-negative
	 *            and no larger than <tt>array.length</tt>. The new buffer's
	 *            position will be set to this value.
	 * 
	 * @param length
	 *            The length of the subarray to be used; must be non-negative
	 *            and no larger than <tt>array.length - offset</tt>. The new
	 *            buffer's limit will be set to <tt>offset + length</tt>.
	 * 
	 */
	public static long byteToLong(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getLong();
	}
}
