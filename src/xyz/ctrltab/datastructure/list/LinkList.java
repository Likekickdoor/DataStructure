package xyz.ctrltab.datastructure.list;

//import java.util.Scanner;
/**
 * @参考：https://www.cnblogs.com/whgk/p/6589920.html
 * @author JeffLiu
 * @date 2019/2/3
 * */
//数据元素
class ElemType2{
	public int onetype;
	public ElemType2(int inonetype){
		this.onetype = inonetype;
	}
}
public class LinkList <ElemType>{

	//数据对象
	private ElemType data;
	private LinkList <ElemType> next;
	
	public LinkList(ElemType indata) {
		this.data = indata;
	}
	public LinkList() {
		//do nothing
	}
	public LinkList <ElemType> GetNext() {
		return this.next; 
	}
	public void SetNext(LinkList <ElemType> innext) {
		this.next = innext; 
	}
	public ElemType GetData() {
		return this.data; 
	}
	public void SetData(ElemType indata) {
		this.data = indata;
	}
	
	//遍历链表
	public void Display_LinkList(){
		LinkList <ElemType> temp = this.GetNext();
		while(temp!=null) {

			this.PrintWay(temp.GetData());//自身对象的PrintWay方法,!!!!此处this在子类改写了PrintWay方法后就是子类自身
			temp = temp.GetNext();
		}
	}
	//在继承后可以更改此成员方法，默认以字符串形式打印对象
	public void PrintWay(ElemType temp) {
		System.out.print(temp.toString()+"\t");
	}
	//链表长度统计
	public int LinkList_Length() {
		LinkList <ElemType> temp = this.GetNext();
		int count = 0;
		while(temp!=null) {
			count++;
			temp = temp.GetNext();
		}
		return count;
	}
	//按序号查找节点，参数至少>=1（计数是从首节点开始）
	public LinkList <ElemType> GetData_LinkList(int i) {
		int j=1;
		if(i<1)
			return null;
		LinkList <ElemType> temp = this.GetNext();
		while(temp!=null&&j<=i) {
			if(j==i)
				return temp;
			j++;
			temp = temp.GetNext();
		}
		return null;
	}
	//按值查找 0为无	值应该>=1
	public int Search_LinkList(ElemType key) {
		LinkList <ElemType> temp = this.GetNext();
		int index = 0;
		while (temp!=null) {
			index++;
			if(temp.GetData() != key) {
				temp = temp.GetNext();	
			}
			else {
				return index;
			}
		}
		return 0;
	}
	//后插运算：在某个节点p后面插入一个值为x的新节点
	public void InsertAfter_LinkList(LinkList <ElemType> p,LinkList <ElemType> node) {
		node.SetNext(p.GetNext());
		p.SetNext(node);
	}
	//前插运算：在某个节点p前面插入一个值为x的新节点  必须要考虑到	首节点
	public void InsertBefore_LinkList(LinkList <ElemType> p,LinkList <ElemType> node) {
		LinkList <ElemType> q = this;
		while(q.GetNext()!=p) 
			q=q.GetNext();
		node.SetNext(p);
		q.SetNext(node);
	}
	//在指定序号位置前插入
	public boolean InsertNo_LinkList(LinkList <ElemType> node,int i) {
		LinkList <ElemType> p;
		if(i<1)
			return false;
		else if(i==1)
			p = this;
		else
			p = this.GetData_LinkList(i-1);
		if(p==null)
			return false;
		else {
			this.InsertAfter_LinkList(p, node);
			return true;
		}
	}
	//删除后继节点：删除了指定节点  后面的	节点
	public boolean DeleteAfter_LinkList(LinkList <ElemType> p) {
		if(p==null)
			return false;
		LinkList <ElemType> r = p.GetNext();
		if(r!=null) {
			p.SetNext(r.GetNext());
			r=null;
			return true;
		}
		else {
			return false;
		}
	}
	//删除指定节点本身
	public boolean DeleteNode_LinkList(LinkList <ElemType> p) {
		if(p==null)
			return false;
		LinkList <ElemType> temp = p.GetNext();
		if(temp!=null) {//有后继节点
			p.SetData(temp.GetData());
			return this.DeleteAfter_LinkList(p);
		}
		else {//无后继节点，寻前p节点
			temp = this;
			while(temp.GetNext()!=p)
				temp = temp.GetNext();
			return this.DeleteAfter_LinkList(temp);
		}
	}
	//删除指定序号节点（以首节点开始）
	public boolean DeleteNo_LinkList(int i) {
		if(i<1)
			return false;
		LinkList <ElemType> temp = this.GetData_LinkList(i);
		if(temp == null)
			return false;
		return this.DeleteNode_LinkList(temp);
	}
	//置空表 返回删除节点数
	public int SetNull_LinkList() {
		int count = 0;
		while(this.GetNext()!=null) {
			this.DeleteAfter_LinkList(this);
			count++;
		}
		return count;
	}
//	public static void main(String[] args) {
//		//此过程是头插法
//		LinkList head = new LinkList();
//		head.SetNext(null);//设置地址为空，实际可不用操作此步
//		
//		System.out.println("输入的数据为0时停止");//接下来理应设置循环  输入数据  0时停止
//		Scanner input = new Scanner(System.in);
//		for(int i=0;i<3;i++) {//为方便验证，设置每次都输入同样的数据：2 3 1
//			System.out.println("请输入一个整数：");
//			LinkList p = new LinkList(new ElemType(input.nextInt()));//申请并设置新的节点，把元素传入赋予data值
//			p.SetNext(head.GetNext());
//			head.SetNext(p);
//		}
//		input.close();
//		
//		System.out.println("请打印出单向链表：");
//		head.Display_LinkList();
//		System.out.println("统计的链表长度："+head.LinkList_Length());
//		
//		LinkList scendNode= head.GetData_LinkList(2);
//		System.out.println("按索引2获得链表的某个元素的值："+scendNode.GetData().onetype);
//		System.out.println("--> 在第2个节点后面插入新节点值为：-->"+100);
//		head.InsertAfter_LinkList(scendNode, new LinkList(new ElemType(100)));
//		System.out.println("--> 在第2个节点前面插入新节点值为：-->"+99);
//		head.InsertBefore_LinkList(scendNode, new LinkList(new ElemType(99)));
//		System.out.println("--> 在指定序号前面插入新节点值为：-->"+88);
//		head.InsertNo_LinkList(new LinkList(new ElemType(88)), 3);
//		head.Display_LinkList();
//		
//		System.out.println("\n删除scendnode节点后继节点：");
//		head.DeleteAfter_LinkList(scendNode);
//		System.out.println("再删除scendnode节点本身：");
//		head.DeleteNode_LinkList(scendNode);
//		System.out.println("按序号删除某个节点：");
//		head.DeleteNo_LinkList(1);
//		head.Display_LinkList();
//		System.out.println("\n按值在链表中查找所得位置(若没有99应为 0)："+head.Search_LinkList(new ElemType(99)));//因为对象是引用型变量，因此并非和前面是同一个，若换成整型应该没问题
//		System.out.println("置空表："+head.SetNull_LinkList());
//	}
	
	public static void main(String[] args) {
		LinkList <Integer> head = new LinkList <Integer>();
		head.SetNext(null);//设置地址为空，实际可不用操作此步
		//插入数据
		LinkList <Integer> node1 = new LinkList <Integer>(102);
		LinkList <Integer> node2 = new LinkList <Integer>(99);
		LinkList <Integer> node3 = new LinkList <Integer>(15);
		LinkList <Integer> node4 = new LinkList <Integer>(15);
 		head.InsertAfter_LinkList(head, node1);
		head.InsertAfter_LinkList(head, node2);
		head.InsertAfter_LinkList(head, node3);
		head.InsertAfter_LinkList(head, node4);
		System.out.println("现在打印出单向链表：");
		head.Display_LinkList();
		head.DeleteNode_LinkList(node3);
		System.out.println("现在打印出删掉元素的单向链表：");
		head.Display_LinkList();
	}
}
