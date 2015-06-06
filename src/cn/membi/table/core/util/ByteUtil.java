package cn.membi.table.core.util;

import java.nio.ByteBuffer;

public class ByteUtil {
	// ˫���ȸ��㵽�ֽ�ת��
	public static byte[] doubleToByte(double d) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(d);
		return bytes;
	}

	// �ֽڵ� ˫���ȸ���ת��
	public static double byteToDouble(byte[] b) {
		return ByteBuffer.wrap(b).getDouble();
	}

	// �ֽڵ� ˫���ȸ���ת��
	public static double byteToDouble(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getDouble();
	}

	// �ֽڵ� �����ȸ���ת��
	public static float byteToFloat(byte[] b) {
		return ByteBuffer.wrap(b).getFloat();
	}

	// �ֽڵ� �����ȸ���ת��
	public static float byteToFloat(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getFloat();
	}

	// �����ȸ��㵽�ֽ�ת��
	public static byte[] floatToByte(float d) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putFloat(d);
		return bytes;
	}

	// �������ֽ������ת��
	public static byte[] intToByte(int number) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putInt(number);
		return bytes;
	}

	// �ֽ����鵽������ת��
	public static int byteToInt(byte[] b, int offset, int length) {
		return ByteBuffer.wrap(b, offset, length).getInt();
	}

	// �ֽ����鵽������ת��
	public static int byteToInt(byte[] b) {
		return ByteBuffer.wrap(b).getInt();
	}

	// ���������ֽ������ת��
	public static byte[] longToByte(long number) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putLong(number);
		return bytes;
	}

	// �ֽ����鵽��������ת��
	public static long byteToLong(byte[] b) {
		return ByteBuffer.wrap(b).getLong();
	}

	/**
	 * �ֽ����鵽��������ת��
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
