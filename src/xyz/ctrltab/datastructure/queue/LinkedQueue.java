package xyz.ctrltab.datastructure.queue;

import xyz.ctrltab.datastructure.list.LinkList;
/**
 * @author JeffLiu
 * @date 2019/2/15
 * @描述 基于单链表的链队列泛类 
 */
public class LinkedQueue <ElemType> {

	private LinkList <ElemType> front;
	private LinkList <ElemType> rear;
	private int Length = 0;
	
	public LinkedQueue () {
		LinkList <ElemType> head = new LinkList <ElemType>();
		this.front = head;
		this.rear = head;
	}
	//判断队列空
	public boolean IsEmpty() {
		return this.front==this.rear?true:false;
	}
	//返回长度
	public int LinkedQueue_Length() {
		return this.Length;
	}
	//入队
	public void Enter(ElemType e) {
		LinkList <ElemType> node = new LinkList <ElemType>(e);
		node.SetNext(null);
		this.rear.SetNext(node);
		this.rear = node;
		this.Length++;
	}
	//出队
	public boolean Delete() {
		if(this.IsEmpty())
			return false;
		LinkList <ElemType> tp = this.front.GetNext();
		this.front.SetNext(tp.GetNext());
		this.Length--;
		tp = null;
		return true;
	}
	//取队列头数据元素
	public ElemType GetFrontData() {
		if(this.IsEmpty())
			return null;
		else if(this.front.GetNext()==null) 
			return null;
		else
			return this.front.GetNext().GetData();
	}
	
	public static void main(String[] args) {
		LinkedQueue <Integer> lq = new LinkedQueue <Integer>();
		lq.Enter(1);
		lq.Enter(2);
		lq.Enter(3);
		lq.Enter(4);
		System.out.println("队列长度(无长度限制)："+lq.LinkedQueue_Length());
		while(lq.GetFrontData()!=null) {
			System.out.println("Ele is:"+lq.GetFrontData()+" ");
			lq.Delete();
		}
		System.out.println("end!finally length:"+lq.LinkedQueue_Length());
	}

}
