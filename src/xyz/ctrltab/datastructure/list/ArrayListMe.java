package xyz.ctrltab.datastructure.list;

import java.util.Arrays;

/**
 * @������ģ��ArrayLsit��ʵ��˳���
 * @date��2019��7��26��14:31:14
 * @author JeffLiu
 * */
public class ArrayListMe<T> {
	
	static int MAXSIZE = 5;
	private Object[] data = new Object[MAXSIZE];
	private int length = 0;
	
	public int getLength() {
		return length;
	}
	
	// ɾ��˳����ĳ��
	boolean delete(int index) throws Exception{
		if(index<1 || index>length) 
			throw new Exception("������Χ");
		
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
			throw new Exception("����λ�ô���");
		if(length+1 > MAXSIZE) // Ԥ��1�����Ƿ�����������
			throw new Exception("�ռ�����");
		for(int j=length; j>=index; j--) 
			data[j] = data[j-1];
		data[index-1] = e;
		length++;
		return true;
	}
	
	// ��ȡĳ��ֵ
	@SuppressWarnings("unchecked")
	T get(int i) throws Exception {
		if(i<1 || i>length)
			throw new Exception("�����߽�");
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
