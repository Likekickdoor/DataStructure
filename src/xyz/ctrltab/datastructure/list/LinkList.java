package xyz.ctrltab.datastructure.list;

//import java.util.Scanner;
/**
 * @�ο���https://www.cnblogs.com/whgk/p/6589920.html
 * @author JeffLiu
 * @date 2019/2/3
 * */
//����Ԫ��
class ElemType2{
	public int onetype;
	public ElemType2(int inonetype){
		this.onetype = inonetype;
	}
}
public class LinkList <ElemType>{

	//���ݶ���
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
	
	//��������
	public void Display_LinkList(){
		LinkList <ElemType> temp = this.GetNext();
		while(temp!=null) {

			this.PrintWay(temp.GetData());//��������PrintWay����,!!!!�˴�this�������д��PrintWay�����������������
			temp = temp.GetNext();
		}
	}
	//�ڼ̳к���Ը��Ĵ˳�Ա������Ĭ�����ַ�����ʽ��ӡ����
	public void PrintWay(ElemType temp) {
		System.out.print(temp.toString()+"\t");
	}
	//������ͳ��
	public int LinkList_Length() {
		LinkList <ElemType> temp = this.GetNext();
		int count = 0;
		while(temp!=null) {
			count++;
			temp = temp.GetNext();
		}
		return count;
	}
	//����Ų��ҽڵ㣬��������>=1�������Ǵ��׽ڵ㿪ʼ��
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
	//��ֵ���� 0Ϊ��	ֵӦ��>=1
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
	//������㣺��ĳ���ڵ�p�������һ��ֵΪx���½ڵ�
	public void InsertAfter_LinkList(LinkList <ElemType> p,LinkList <ElemType> node) {
		node.SetNext(p.GetNext());
		p.SetNext(node);
	}
	//ǰ�����㣺��ĳ���ڵ�pǰ�����һ��ֵΪx���½ڵ�  ����Ҫ���ǵ�	�׽ڵ�
	public void InsertBefore_LinkList(LinkList <ElemType> p,LinkList <ElemType> node) {
		LinkList <ElemType> q = this;
		while(q.GetNext()!=p) 
			q=q.GetNext();
		node.SetNext(p);
		q.SetNext(node);
	}
	//��ָ�����λ��ǰ����
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
	//ɾ����̽ڵ㣺ɾ����ָ���ڵ�  �����	�ڵ�
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
	//ɾ��ָ���ڵ㱾��
	public boolean DeleteNode_LinkList(LinkList <ElemType> p) {
		if(p==null)
			return false;
		LinkList <ElemType> temp = p.GetNext();
		if(temp!=null) {//�к�̽ڵ�
			p.SetData(temp.GetData());
			return this.DeleteAfter_LinkList(p);
		}
		else {//�޺�̽ڵ㣬Ѱǰp�ڵ�
			temp = this;
			while(temp.GetNext()!=p)
				temp = temp.GetNext();
			return this.DeleteAfter_LinkList(temp);
		}
	}
	//ɾ��ָ����Žڵ㣨���׽ڵ㿪ʼ��
	public boolean DeleteNo_LinkList(int i) {
		if(i<1)
			return false;
		LinkList <ElemType> temp = this.GetData_LinkList(i);
		if(temp == null)
			return false;
		return this.DeleteNode_LinkList(temp);
	}
	//�ÿձ� ����ɾ���ڵ���
	public int SetNull_LinkList() {
		int count = 0;
		while(this.GetNext()!=null) {
			this.DeleteAfter_LinkList(this);
			count++;
		}
		return count;
	}
//	public static void main(String[] args) {
//		//�˹�����ͷ�巨
//		LinkList head = new LinkList();
//		head.SetNext(null);//���õ�ַΪ�գ�ʵ�ʿɲ��ò����˲�
//		
//		System.out.println("���������Ϊ0ʱֹͣ");//��������Ӧ����ѭ��  ��������  0ʱֹͣ
//		Scanner input = new Scanner(System.in);
//		for(int i=0;i<3;i++) {//Ϊ������֤������ÿ�ζ�����ͬ�������ݣ�2 3 1
//			System.out.println("������һ��������");
//			LinkList p = new LinkList(new ElemType(input.nextInt()));//���벢�����µĽڵ㣬��Ԫ�ش��븳��dataֵ
//			p.SetNext(head.GetNext());
//			head.SetNext(p);
//		}
//		input.close();
//		
//		System.out.println("���ӡ����������");
//		head.Display_LinkList();
//		System.out.println("ͳ�Ƶ������ȣ�"+head.LinkList_Length());
//		
//		LinkList scendNode= head.GetData_LinkList(2);
//		System.out.println("������2��������ĳ��Ԫ�ص�ֵ��"+scendNode.GetData().onetype);
//		System.out.println("--> �ڵ�2���ڵ��������½ڵ�ֵΪ��-->"+100);
//		head.InsertAfter_LinkList(scendNode, new LinkList(new ElemType(100)));
//		System.out.println("--> �ڵ�2���ڵ�ǰ������½ڵ�ֵΪ��-->"+99);
//		head.InsertBefore_LinkList(scendNode, new LinkList(new ElemType(99)));
//		System.out.println("--> ��ָ�����ǰ������½ڵ�ֵΪ��-->"+88);
//		head.InsertNo_LinkList(new LinkList(new ElemType(88)), 3);
//		head.Display_LinkList();
//		
//		System.out.println("\nɾ��scendnode�ڵ��̽ڵ㣺");
//		head.DeleteAfter_LinkList(scendNode);
//		System.out.println("��ɾ��scendnode�ڵ㱾��");
//		head.DeleteNode_LinkList(scendNode);
//		System.out.println("�����ɾ��ĳ���ڵ㣺");
//		head.DeleteNo_LinkList(1);
//		head.Display_LinkList();
//		System.out.println("\n��ֵ�������в�������λ��(��û��99ӦΪ 0)��"+head.Search_LinkList(new ElemType(99)));//��Ϊ�����������ͱ�������˲��Ǻ�ǰ����ͬһ��������������Ӧ��û����
//		System.out.println("�ÿձ�"+head.SetNull_LinkList());
//	}
	
	public static void main(String[] args) {
		LinkList <Integer> head = new LinkList <Integer>();
		head.SetNext(null);//���õ�ַΪ�գ�ʵ�ʿɲ��ò����˲�
		//��������
		LinkList <Integer> node1 = new LinkList <Integer>(102);
		LinkList <Integer> node2 = new LinkList <Integer>(99);
		LinkList <Integer> node3 = new LinkList <Integer>(15);
		LinkList <Integer> node4 = new LinkList <Integer>(15);
 		head.InsertAfter_LinkList(head, node1);
		head.InsertAfter_LinkList(head, node2);
		head.InsertAfter_LinkList(head, node3);
		head.InsertAfter_LinkList(head, node4);
		System.out.println("���ڴ�ӡ����������");
		head.Display_LinkList();
		head.DeleteNode_LinkList(node3);
		System.out.println("���ڴ�ӡ��ɾ��Ԫ�صĵ�������");
		head.Display_LinkList();
	}
}
