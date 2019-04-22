package xyz.ctrltab.datastructure.list;

/**
 * 
 * @author JeffLiu
 * @date 2019/1/29
 * @version 1.0
 * @describe ���ݽṹ/���Ա�/˳���
 * @�ο���https://blog.csdn.net/iteye_71/article/details/82677191 ���ǿ�����objectǿ��ת��Ϊ�����������Ǵ����
 */
//����Ԫ��
class ElemType1{
	public String stuName;
	public String stuNum;
	public int [] stuSorce = {0,0,0};
}

@SuppressWarnings("hiding")
public class SequenList <ElemType> {

	private int MAXSIZE = 50; 
	//���ݶ���
//	public ElemType [] data;
	public Object [] data;
	public int last;
	
	/*
	 * ��������
	 */
	//��ʼ��˳�����Ķ��󣨹��췽����,�ɱ䳤��
	public SequenList(){
//		this.data = new ElemType[MAXSIZE];//����ʹ�÷��ഴ������
		this.data = new Object[this.MAXSIZE];
		this.last = -1;
	}
	public SequenList(int size){
		this.MAXSIZE = size;
		this.data = new Object[this.MAXSIZE];
		this.last = -1;
	}
	//˳���ĳ���
	public int SequenList_Length() {
		return this.last+1;
	}
	//��������
	public boolean Insert_SequenList(ElemType e,int i) {
		if(this.last>=this.MAXSIZE-1) {
			return false;
		}
		if(i<1||i>this.last+2) {
			return false;
		}
		for(int j=this.last;j>=i-1;j--) {
			this.data[j+1] = this.data[j];
		}
		this.data[i-1] = e;
		this.last++;	//˳�����+1
		return true;
	}
	//ɾ������
	public boolean Delete_SequenList(int d) {
		if(d<1||d>this.last+1) {
			return false;
		}
		else {
			for(int start=d;start<=this.last;start++) {
				this.data[start-1]=this.data[start];
			}
			this.last--;
			return true;
		}
	}
	//ȡ�ڵ�n�����ݵĺ���
	@SuppressWarnings("unchecked")
	public ElemType GetData_SequenList(int n) {
		if(n<1||n>this.last+1)
			return null;
		else
			return (ElemType)this.data[n-1];
	}
	//����ĳ��Ԫ��
	public int Search_SequenList(ElemType key) {
		for(int i=0;i<=this.last;i++) {
			if(this.data[i]==key)
				return i;//Ѱ�ҳɹ�������˳�����λ��
		}
		return -1;
	}
	//������ӡ˳���(����̳к��Զ���)
	public boolean Print_SequenList() {
		if(this.last==-1)
			return false;
		for(int i=0;i<=this.last;i++)
//			System.out.println("˳������"+(i+1)+"\t����"+this.data[i].stuName+"\tѧ��"+this.data[i].stuNum+"\t�ɼ���������ͣ�"+(this.data[i].stuSorce[0]+this.data[i].stuSorce[1]+this.data[i].stuSorce[2]));
			System.out.println("˳������"+(i+1)+"\t����Ԫ��"+this.data[i].toString());
		return true;
	}
	public static void main(String[] args) {
		//��Ϊ�������
		ElemType1 elmT1 = new ElemType1();
		elmT1.stuName = "СA";
		elmT1.stuNum = "201641842301";
		elmT1.stuSorce[0] = 1;
		elmT1.stuSorce[1] = 1;
		elmT1.stuSorce[2] = 1;
		ElemType1 elmT2 = new ElemType1();
		elmT2.stuName = "СB";
		elmT2.stuNum = "201641842302";
		elmT2.stuSorce[0] = 2;
		elmT2.stuSorce[1] = 2;
		elmT2.stuSorce[2] = 2;
		ElemType1 elmT3 = new ElemType1();
		elmT3.stuName = "СC";
		elmT3.stuNum = "201641842303";
		elmT3.stuSorce[0] = 3;
		elmT3.stuSorce[1] = 3;
		elmT3.stuSorce[2] = 3;
		
		SequenList <ElemType1> iSeqList = new SequenList <ElemType1>();
//		iSeqList.data = new ElemType1[iSeqList.MAXSIZE];
		
		iSeqList.Insert_SequenList(elmT1, 1);
		iSeqList.Insert_SequenList(elmT2, 2);
		iSeqList.Insert_SequenList(elmT3, 3);
		
		iSeqList.Delete_SequenList(2);
		
//		iSeqList.Print_SequenList();
		for(int i=0;i<=iSeqList.last;i++)
		{
			ElemType1 one = (ElemType1) iSeqList.data[i];
			System.out.println(one.stuName);
		}
//		System.exit(0);
		System.out.println("iSeqList�����ճ���:"+iSeqList.SequenList_Length());
		
		System.out.println("��ȡ��1��Ԫ��:"+iSeqList.GetData_SequenList(1).stuName+"\t"+iSeqList.GetData_SequenList(1).stuNum);
		
		int index = iSeqList.Search_SequenList(elmT2);
		System.out.println("���������±�index��"+index);
	}

}