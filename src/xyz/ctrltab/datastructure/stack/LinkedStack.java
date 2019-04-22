package xyz.ctrltab.datastructure.stack;

import xyz.ctrltab.datastructure.list.LinkList;
/**
 *@ԭ��	��������ʵ����ջ���Ӳ�������ջ�ǲ������޵�����
 *@author JeffLiu
 *@date	2019/2/10 
 */
public class LinkedStack <ElemType> extends LinkList <ElemType>{
	
	//�Ƿ�Ϊ��
	public boolean IsEmpty() {
		if(super.LinkList_Length()==0)
			return true;
		return false;
	}
	//��ջ
	public boolean Push(ElemType e) {
		super.InsertAfter_LinkList(this,new LinkList <ElemType> (e));
		return false;
	}
	//��ջ
	public boolean Pop() {
		return super.DeleteAfter_LinkList(this);
	}
	//��ȡջ��Ԫ��
	public ElemType GetTopEle() {
		LinkList<ElemType> tp = super.GetData_LinkList(1);
		if(tp!=null) {
			return tp.GetData();
		}
		return null;
	}
	//�������ʾ����
	public void PrintWay(ElemType temp) {
		System.out.print(temp.toString());
	}
	
	public static void main(String[] args) {
		//ʵ���� ��ջ������
		LinkedStack <Integer> ss = new LinkedStack <Integer>();
		System.out.println("�Ƿ�Ϊ�գ�"+ss.IsEmpty());
		ss.Push(5);
		ss.Push(6);
		ss.Pop();
		ss.Push(4);
		ss.Push(7);
		ss.Push(2);
		
		ss.Display_LinkList();
		
		System.out.println("���ȣ�"+ss.LinkList_Length());
		System.out.println("ջ��Ԫ�أ�"+ss.GetTopEle());
		ss.Pop();
		System.out.println("ջ��Ԫ�أ�"+ss.GetTopEle());
		ss.Pop();
		System.out.println("ջ��Ԫ�أ�"+ss.GetTopEle());
		ss.Pop();
		System.out.println("ջ��Ԫ�أ�"+ss.GetTopEle());
	}

}
