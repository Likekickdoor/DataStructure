package xyz.ctrltab.datastructure.stack;

import xyz.ctrltab.datastructure.list.LinkList;
/**
 *@原理	基于链表实现链栈。从操作讲，栈是操作受限的链表
 *@author JeffLiu
 *@date	2019/2/10 
 */
public class LinkedStack <ElemType> extends LinkList <ElemType>{
	
	//是否为空
	public boolean IsEmpty() {
		if(super.LinkList_Length()==0)
			return true;
		return false;
	}
	//入栈
	public boolean Push(ElemType e) {
		super.InsertAfter_LinkList(this,new LinkList <ElemType> (e));
		return false;
	}
	//出栈
	public boolean Pop() {
		return super.DeleteAfter_LinkList(this);
	}
	//获取栈顶元素
	public ElemType GetTopEle() {
		LinkList<ElemType> tp = super.GetData_LinkList(1);
		if(tp!=null) {
			return tp.GetData();
		}
		return null;
	}
	//自身的显示方法
	public void PrintWay(ElemType temp) {
		System.out.print(temp.toString());
	}
	
	public static void main(String[] args) {
		//实例化 链栈泛型类
		LinkedStack <Integer> ss = new LinkedStack <Integer>();
		System.out.println("是否为空："+ss.IsEmpty());
		ss.Push(5);
		ss.Push(6);
		ss.Pop();
		ss.Push(4);
		ss.Push(7);
		ss.Push(2);
		
		ss.Display_LinkList();
		
		System.out.println("长度："+ss.LinkList_Length());
		System.out.println("栈顶元素："+ss.GetTopEle());
		ss.Pop();
		System.out.println("栈顶元素："+ss.GetTopEle());
		ss.Pop();
		System.out.println("栈顶元素："+ss.GetTopEle());
		ss.Pop();
		System.out.println("栈顶元素："+ss.GetTopEle());
	}

}
