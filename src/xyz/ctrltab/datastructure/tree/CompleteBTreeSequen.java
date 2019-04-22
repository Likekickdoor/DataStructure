package xyz.ctrltab.datastructure.tree;
/**
 * ��ȫ�������Ĵ洢�ṹ
 * @author JeffLiu
 * @date 2019/3/1
 * @���� ��ȫ��������˳��洢�ṹ
 * 
 */
class ElemType{
	String v;
	int index;
	public ElemType(String v){
		this.v = v;
	}
}
public class CompleteBTreeSequen {
	
	Object [] data;	//�洢����
	int n = 0;	//���
	int MAXSIZE = 31;	//���洢2^5-1����5���������������
	
	CompleteBTreeSequen(){
		this.data = new Object[this.MAXSIZE+1];//��һ��Ԫ��Ϊ��
	}
	
	void Assign (int i,ElemType e) {
		if(i<this.data.length && i!=0) {
			if(this.data[i]==null) {
				n++;			
			}
			this.data [i] = e; 
			e.index = i;
		}
	}
	
	ElemType LeftChild(ElemType e) {
		if(e.index*2<this.data.length)
			return (ElemType)this.data[e.index*2];
		return null;
	}
	
	ElemType RightChild(ElemType e) {
		if(e.index*2+1<this.data.length)
			return (ElemType)this.data[e.index*2+1];
		return null;
	}
	//���ظ����
	ElemType root() {
		return (ElemType)this.data[1];
	}
	//ǰ�����
	void DLR(ElemType e) {
		if(e!=null) {
			visit(e);
			DLR((ElemType)this.data[e.index*2]);
			DLR((ElemType)this.data[e.index*2+1]);
		}
	}
	
	private void visit(ElemType e) {
		System.out.print(e.index+":"+e.v+"\t");
	}

	public static void main(String[] args) {
		CompleteBTreeSequen tree = new CompleteBTreeSequen();
		tree.Assign(1, new ElemType("A"));
		tree.Assign(2, new ElemType("B"));
		tree.Assign(3, new ElemType("C"));
		tree.Assign(4, new ElemType("D"));
		tree.Assign(5, new ElemType("E"));
		tree.Assign(6, new ElemType("F"));
		tree.Assign(7, new ElemType("G"));
		tree.Assign(8, new ElemType("H"));
		
//		System.out.println(((ElemType)tree.data[3]).v);
		
		System.out.println("�����ܽ������"+tree.n);
		tree.DLR(tree.root());
	}

}