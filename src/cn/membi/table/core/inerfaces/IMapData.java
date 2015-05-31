package cn.membi.table.core.inerfaces;

/**
 * 每一个非数值列在内存中只存储数字，不存储具体字符信息，<br>
 * 具体的字符信息值通过该数据映射到整形。并将字符信息存储到本地磁盘中。
 * @author guolw
 *
 */
public interface IMapData {
     public void put(int key,String value);
}
