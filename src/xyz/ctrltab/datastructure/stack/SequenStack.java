package xyz.ctrltab.datastructure.stack;

//栈中元素类	测试使用
class StackElem {
	private int v;
	public StackElem (int input) {
		this.v = input;
	}
	public int GetVData() {
		return this.v;
	}
}
/**
 *@原理	基于数组实现顺序栈。从操作讲，栈是操作受限的链表
 *@author JeffLiu
 *@date	2019/2/09 
 */
public class SequenStack<ElemType> {

	public int MAXSIZE = 10;//该栈容量
	
	public Object [] data;
	protected int top = -1;
	
	public SequenStack() {
		this.data = new Object[this.MAXSIZE];//申请开辟默认大小空间
	}
	public SequenStack(int size) {
		this.MAXSIZE = size;
		this.data = new Object[this.MAXSIZE];//申请开辟默认大小空间
	}
//	@SuppressWarnings("unchecked")
//	public ElemType GetData() {
//		return (ElemType) this.data;
//	}
	
	public boolean IsEmpty() {
		if(this.top == -1) {
			return true;
		}
		return false;
	}
	
	public boolean IsFull() {
		if(this.top+1 >= this.MAXSIZE)
			return true;
		return false;
	}
	
	public int SequenStackLength() {
		return this.top+1;
	}
	
	public boolean Push(ElemType e) {
		if(this.IsFull())
			return false;
		this.top++;
		this.data[this.top] = e;
		return true;
	}
	public boolean Pop() {
		if(this.IsEmpty())
			return false;
		data[top] = null;
		this.top--;
		return true;
	}

	@SuppressWarnings("unchecked")
	public ElemType GetTopEle() {
		if(this.IsEmpty())
			return null;
		return (ElemType) this.data[this.top];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SequenStack <Integer> ss = new SequenStack <Integer>();
		ss.Push(6);
		ss.Push(5);
		ss.Push(4);
		
		for(int i=0;i<3;i++){
			System.out.println("栈顶"+i+"的值是："+ss.GetTopEle());
			ss.Pop();
		}
		
	}

}
