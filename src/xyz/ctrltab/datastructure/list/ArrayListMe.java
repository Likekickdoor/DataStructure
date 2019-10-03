package xyz.ctrltab.datastructure.list;

import java.util.Arrays;

/**
 * @描述：模拟ArrayLsit，实现顺序表。
 * @date：2019年7月26日14:31:14
 * @author JeffLiu
 * */
public class ArrayListMe<T> {
	
	static int MAXSIZE = 5;
	private Object[] data = new Object[MAXSIZE];
	private int length = 0;
	
	public int getLength() {
		return length;
	}
	
	// 删除顺序表的某行
	boolean delete(int index) throws Exception{
		if(index<1 || index>length) 
			throw new Exception("超过范围");
		
		for(int i=index;i<length;i++)
			data[i-1] = data[i];
		//if(length-1<0)
		//	length = 0 ;
		length--;
		return true;
	}
	
	// index [1,length+1]
	boolean add(int index,T e) throws Exception{
		if(index<1 || index>length+1)
			throw new Exception("插入位置错误");
		if(length+1 > MAXSIZE) // 预加1查其是否大于最大容量
			throw new Exception("空间已满");
		for(int j=length; j>=index; j--) 
			data[j] = data[j-1];
		data[index-1] = e;
		length++;
		return true;
	}
	
	// 获取某个值
	@SuppressWarnings("unchecked")
	T get(int i) throws Exception {
		if(i<1 || i>length)
			throw new Exception("超过边界");
		return (T)data[i-1];
	}
	
	
	public static void main(String[] args) throws Exception {
		ArrayListMe<Integer> list = new ArrayListMe<>();
		list.add(1, 1);
		list.add(2, 2);
		list.add(2, 3);
		list.add(2, 4);
		list.add(2, 5);
		list.delete(2);
		
		System.out.println(Arrays.toString(list.data));
		
		list = null;
	}

}
