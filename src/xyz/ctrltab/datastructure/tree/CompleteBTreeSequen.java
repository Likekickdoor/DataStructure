package xyz.ctrltab.datastructure.tree;
/**
 * 完全二叉树的存储结构
 * @author JeffLiu
 * @date 2019/3/1
 * @描述 完全二叉树的顺序存储结构
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
	
	Object [] data;	//存储数据
	int n = 0;	//编号
	int MAXSIZE = 31;	//最多存储2^5-1，即5层满二叉树结点数
	
	CompleteBTreeSequen(){
		this.data = new Object[this.MAXSIZE+1];//第一个元素为空
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
	//返回根结点
	ElemType root() {
		return (ElemType)this.data[1];
	}
	//前序遍历
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
		
		System.out.println("树的总结点量："+tree.n);
		tree.DLR(tree.root());
	}

}