package xyz.ctrltab.datastructure.queue;

//����Ԫ�� ����ר����
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
 * @���� ���������˳����У�֮ѭ�����У����� 
 */
public class SequenQueue <ElemType> {

	private int rear = 0;
	private int front = 0;
	private int MAXSIZE = 10;
	private Object [] data;
	
	//˳�����(ѭ������)�ĳ�ʼ�� �Ͷ��ƶ��д�С�Ĺ��캯��
	public SequenQueue(){
		this.data = new Object [this.MAXSIZE];
	}
	public SequenQueue(int size){
		this.MAXSIZE = size;
		this.data = new Object [this.MAXSIZE];
	}
	//�ж϶��п�
	public boolean IsEmpty() {
		return this.front==this.rear?true:false;
	}
	//�ж϶�����	����ʹ�ÿ�һԪ�صķ���   ������еġ��ա�������
	public boolean IsFull() {
		return (this.rear+1)%this.MAXSIZE==this.front?true:false;
	}
	//˳����г���
	public int SequenQueue_Length() {
		return (this.rear-this.front+this.MAXSIZE)%this.MAXSIZE;
	}
	//���
	public boolean Enter(ElemType e) {
		if(this.IsFull())
			return false;
		else {
			this.data[this.rear] = e;
			this.rear=(this.rear+1)%this.MAXSIZE;
			return true;
		}
	}
	//����
	public boolean Delete () {
		if(this.IsEmpty())
			return false;
		else {
			this.front = (this.front+1)%this.MAXSIZE;
			return true;
		}
	}
	//ȡ��ͷ����
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
		//�˴��Ѿ���������
		sq.Enter(new ElemType1(6));
		sq.Enter(new ElemType1(7));
		System.out.println("���г��ȣ�"+sq.SequenQueue_Length());
		//ȡ������Ԫ��
		while(sq.GetFrontData()!=null) {
			System.out.println("Ele is:"+sq.GetFrontData().GetValue()+" ");
			sq.Delete();
		}
		System.out.println("end!finally length:"+sq.SequenQueue_Length());
	}

}
