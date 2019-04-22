package xyz.ctrltab.datastructure.queue;

//数据元素 测试专用类
class ElemType1{
	private int value;
	public ElemType1 (int input){
		this.value = input;
	}
	public int GetValue() {
		return this.value;
	};
}
/**
 * @author JeffLiu
 * @date 2019/2/15
 * @描述 基于数组的顺序队列（之循环队列）泛类 
 */
public class SequenQueue <ElemType> {

	private int rear = 0;
	private int front = 0;
	private int MAXSIZE = 10;
	private Object [] data;
	
	//顺序队列(循环队列)的初始化 和定制队列大小的构造函数
	public SequenQueue(){
		this.data = new Object [this.MAXSIZE];
	}
	public SequenQueue(int size){
		this.MAXSIZE = size;
		this.data = new Object [this.MAXSIZE];
	}
	//判断队列空
	public boolean IsEmpty() {
		return this.front==this.rear?true:false;
	}
	//判断队列满	本类使用空一元素的方法   区别队列的“空”或“满”
	public boolean IsFull() {
		return (this.rear+1)%this.MAXSIZE==this.front?true:false;
	}
	//顺序队列长度
	public int SequenQueue_Length() {
		return (this.rear-this.front+this.MAXSIZE)%this.MAXSIZE;
	}
	//入队
	public boolean Enter(ElemType e) {
		if(this.IsFull())
			return false;
		else {
			this.data[this.rear] = e;
			this.rear=(this.rear+1)%this.MAXSIZE;
			return true;
		}
	}
	//出队
	public boolean Delete () {
		if(this.IsEmpty())
			return false;
		else {
			this.front = (this.front+1)%this.MAXSIZE;
			return true;
		}
	}
	//取队头数据
	@SuppressWarnings("unchecked")
	public ElemType GetFrontData() {
		if(this.IsEmpty())
			return null;
		else {
			return (ElemType)this.data[this.front];
		}
	}
	
	public static void main(String[] args) {
		SequenQueue <ElemType1> sq = new SequenQueue <ElemType1> ();
		sq.Enter(new ElemType1(1));
		sq.Enter(new ElemType1(2));
		sq.Enter(new ElemType1(3));
		sq.Enter(new ElemType1(4));
		sq.Enter(new ElemType1(5));
		//此处已经超过容量
		sq.Enter(new ElemType1(6));
		sq.Enter(new ElemType1(7));
		System.out.println("队列长度："+sq.SequenQueue_Length());
		//取出队列元素
		while(sq.GetFrontData()!=null) {
			System.out.println("Ele is:"+sq.GetFrontData().GetValue()+" ");
			sq.Delete();
		}
		System.out.println("end!finally length:"+sq.SequenQueue_Length());
	}

}
