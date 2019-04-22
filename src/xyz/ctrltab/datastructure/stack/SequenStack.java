package xyz.ctrltab.datastructure.stack;

//ջ��Ԫ����	����ʹ��
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
 *@ԭ��	��������ʵ��˳��ջ���Ӳ�������ջ�ǲ������޵�����
 *@author JeffLiu
 *@date	2019/2/09 
 */
public class SequenStack<ElemType> {

	public int MAXSIZE = 10;//��ջ����
	
	public Object [] data;
	protected int top = -1;
	
	public SequenStack() {
		this.data = new Object[this.MAXSIZE];//���뿪��Ĭ�ϴ�С�ռ�
	}
	public SequenStack(int size) {
		this.MAXSIZE = size;
		this.data = new Object[this.MAXSIZE];//���뿪��Ĭ�ϴ�С�ռ�
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
			System.out.println("ջ��"+i+"��ֵ�ǣ�"+ss.GetTopEle());
			ss.Pop();
		}
		
	}

}
