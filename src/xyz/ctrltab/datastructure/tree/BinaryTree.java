package xyz.ctrltab.datastructure.tree;
import xyz.ctrltab.datastructure.queue.SequenQueue;
import xyz.ctrltab.datastructure.stack.SequenStack;
/**
 * 
 * @author JeffLiu
 * @���� ���ö�������Ľṹʵ�ֶ�����
 * @date 2019/3/3
 * 
 */
public class BinaryTree {

	ElemType data;//�ý������ -- ͬ���£�CompleteBTree.java�к���ElemType��
	BinaryTree lchild,rchild;//�ý������Һ���
	
	//������չ���������������������		�磺"ABC..D.E.FG..." ת���ɶ�����
	public static BinaryTree CreateBTree_ExtendPre(SString s) {
		if(s.str.equals(""))
			return null;//�׳��쳣
		BinaryTree tree;
		String tp = s.str.substring(0, 1);//��һ���ַ���
		s.str = s.str.substring(1);//��ȥ���ַ�����Ĵ�
		if(tp.equals(".")) {
			return null;
		}
		else {
			tree = new BinaryTree();
			tree.data = new ElemType(tp);//�洢��Ԫ��
			tree.lchild = BinaryTree.CreateBTree_ExtendPre(s);
			tree.rchild = BinaryTree.CreateBTree_ExtendPre(s);
		}
		return tree;
	}
	//�������б���
	public void DLR(BinaryTree bt) {
		if(bt!=null) {
			this.visit(bt.data);
			this.DLR(bt.lchild);
			this.DLR(bt.rchild);
		}
	}
	//�������б���	
	public void LDR(BinaryTree bt) {
		if(bt!=null) {
			this.LDR(bt.lchild);
			this.visit(bt.data);
			this.LDR(bt.rchild);
		}
	}
	//���ʽ��
	private void visit(ElemType data) {
		System.out.print(data.v+"\t");
	}
	//ͳ�ƶ�����Ҷ�ӽ��ĵ���
	public static int BTreeLeaf(BinaryTree bt) {
		if (bt==null)
			return 0;
		else if(bt.lchild==null&&bt.rchild==null)
			return 1;
		else
			return BinaryTree.BTreeLeaf(bt.lchild)+BinaryTree.BTreeLeaf(bt.rchild);
	}
	//��������������
	public static int BTreeDepth(BinaryTree bt) {
		if(bt==null)
			return 0;
		if(bt.lchild==null && bt.rchild==null)
			return 1;
		int max = 0;
		int ldepth = BinaryTree.BTreeDepth(bt.lchild);
		int rdepth = BinaryTree.BTreeDepth(bt.rchild);
		if(ldepth>rdepth)
			max = ldepth;
		else
			max = rdepth;
		return max+1;//���ϱ�������
	}
	//�������ķǵݹ�������	���ö���ʵ��
	public void LevelOrder() {
		BinaryTree tp;
		SequenQueue<BinaryTree> queue = new SequenQueue<BinaryTree>();//Ĭ�ϴ�СΪ10��ʹ�������в��ÿ��Ǵ�����
		queue.Enter(this);
		while(!queue.IsEmpty()) {
			tp = queue.GetFrontData();
			queue.Delete();//ȡ�ý���ɾ��
			this.visit(tp.data);
			if(tp.lchild!=null) 
				queue.Enter(tp.lchild);
			if(tp.rchild!=null)
				queue.Enter(tp.rchild);
		}
	}
	//�������ķǵݹ��������б���	����ջʵ��
	public void InOrder() {
		BinaryTree p = this;
		SequenStack<BinaryTree> st = new SequenStack<BinaryTree>();//ջ����10,ʹ����ջ���ÿ��Ǵ�����
		do {
			while(p!=null) {
				st.Push(p);
				p=p.lchild;
			}
			if(st.IsEmpty())
				return;
			else {
				p = st.GetTopEle();
				st.Pop();//��ȡ�󵯳�ջ
				this.visit(p.data);
				p = p.rchild;
			}
		}while(p!=null || !st.IsEmpty());
	}
	//�����������ο���ͬ����������
	public static BinaryTree CreateBTree_PreInOrder(String pre,String in) {
		if(pre.equals("") || in.equals(""))
			return null;
		//�洢Ԫ��
		String tp = pre.charAt(0)+"";//ȡ���ַ��������ǵõ���SL���ַ���Ϊ��ʱ��Ҫע�⣬�����׳��쳣
		BinaryTree node = new BinaryTree();
		node.data = new ElemType(tp);
		//�ҵ�tp��in�е�λ�� i �����ָ����������ַ���
		int i = in.indexOf(tp);
		/*�� tp���� �и�pre�� �µ� pre �� in*/
		//�ָ��ַ��� ��Ϊ��ʱ �����ָ������λ
		if(i==0) {
			if(in.length()==1) {//����Ϊ��
				node.lchild = null;
				node.rchild = null;
			}
			else {
				node.lchild = null;
				pre = pre.substring(1);
				in = in.substring(1);
				node.rchild = BinaryTree.CreateBTree_PreInOrder(pre, in);
			}
		}
		//�ָ��ַ��� ��Ϊ�� �����ָ�������
		else if(i==in.length()-1 && i!=0) {
			pre = pre.substring(1);
			in = in.substring(0,i);
			node.lchild = BinaryTree.CreateBTree_PreInOrder(pre, in);
			node.rchild = null;
		}
		//�ָ��ַ��� ������ �����ָ�����м�
		else {
			String pre1 = pre.substring(1, i+1);
			String in1 = in.substring(0, i);
			
			String pre2 = pre.substring(i+1);
			String in2 = in.substring(i+1);
			
			node.lchild = BinaryTree.CreateBTree_PreInOrder(pre1, in1);
			node.rchild = BinaryTree.CreateBTree_PreInOrder(pre2, in2);
		}
		return node;
	}
	public static void main(String[] args) {	
		BinaryTree btree = BinaryTree.CreateBTree_ExtendPre(new SString("ABC..D..E.FG..."));
		System.out.println("�ݹ��������б�����");
		btree.LDR(btree);
		System.out.println("\n�ǵݹ��������б�����");
		btree.InOrder();
		System.out.println("\nҶ�ӽ����Ϊ��i+1����"+BinaryTree.BTreeLeaf(btree)+"\n��ȣ�"+BinaryTree.BTreeDepth(btree));
		System.out.println("\n��α�����");
		btree.LevelOrder();
		
		BinaryTree btree2 = BinaryTree.CreateBTree_PreInOrder("ABCDEFG","CBDAEGF");
		System.out.println("\n���������취֮�ݹ����������б�����");
		btree2.LDR(btree2);
	}

}

class SString{
	String str = "";
	public SString(String s){
		this.str = s;
	}
}