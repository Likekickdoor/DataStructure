package xyz.ctrltab.datastructure.tree;
import xyz.ctrltab.datastructure.queue.SequenQueue;
import xyz.ctrltab.datastructure.stack.SequenStack;
/**
 * 
 * @author JeffLiu
 * @描述 利用二叉链表的结构实现二叉树
 * @date 2019/3/3
 * 
 */
public class BinaryTree {

	ElemType data;//该结点数据 -- 同包下，CompleteBTree.java中含有ElemType类
	BinaryTree lchild,rchild;//该结点的左右孩子
	
	//利用扩展先序遍历法，创建二叉树		如："ABC..D.E.FG..." 转换成二叉树
	public static BinaryTree CreateBTree_ExtendPre(SString s) {
		if(s.str.equals(""))
			return null;//抛出异常
		BinaryTree tree;
		String tp = s.str.substring(0, 1);//第一个字符串
		s.str = s.str.substring(1);//除去首字符串后的串
		if(tp.equals(".")) {
			return null;
		}
		else {
			tree = new BinaryTree();
			tree.data = new ElemType(tp);//存储的元素
			tree.lchild = BinaryTree.CreateBTree_ExtendPre(s);
			tree.rchild = BinaryTree.CreateBTree_ExtendPre(s);
		}
		return tree;
	}
	//先序序列遍历
	public void DLR(BinaryTree bt) {
		if(bt!=null) {
			this.visit(bt.data);
			this.DLR(bt.lchild);
			this.DLR(bt.rchild);
		}
	}
	//中序序列遍历	
	public void LDR(BinaryTree bt) {
		if(bt!=null) {
			this.LDR(bt.lchild);
			this.visit(bt.data);
			this.LDR(bt.rchild);
		}
	}
	//访问结点
	private void visit(ElemType data) {
		System.out.print(data.v+"\t");
	}
	//统计二叉树叶子结点的点数
	public static int BTreeLeaf(BinaryTree bt) {
		if (bt==null)
			return 0;
		else if(bt.lchild==null&&bt.rchild==null)
			return 1;
		else
			return BinaryTree.BTreeLeaf(bt.lchild)+BinaryTree.BTreeLeaf(bt.rchild);
	}
	//计算二叉树的深度
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
		return max+1;//加上本结点深度
	}
	//二叉树的非递归层序遍历	利用队列实现
	public void LevelOrder() {
		BinaryTree tp;
		SequenQueue<BinaryTree> queue = new SequenQueue<BinaryTree>();//默认大小为10，使用链队列不用考虑此问题
		queue.Enter(this);
		while(!queue.IsEmpty()) {
			tp = queue.GetFrontData();
			queue.Delete();//取该结点后删除
			this.visit(tp.data);
			if(tp.lchild!=null) 
				queue.Enter(tp.lchild);
			if(tp.rchild!=null)
				queue.Enter(tp.rchild);
		}
	}
	//二叉树的非递归中序序列遍历	利用栈实现
	public void InOrder() {
		BinaryTree p = this;
		SequenStack<BinaryTree> st = new SequenStack<BinaryTree>();//栈容量10,使用链栈不用考虑此问题
		do {
			while(p!=null) {
				st.Push(p);
				p=p.lchild;
			}
			if(st.IsEmpty())
				return;
			else {
				p = st.GetTopEle();
				st.Pop();//获取后弹出栈
				this.visit(p.data);
				p = p.rchild;
			}
		}while(p!=null || !st.IsEmpty());
	}
	//由先序和中序参考共同建立二叉树
	public static BinaryTree CreateBTree_PreInOrder(String pre,String in) {
		if(pre.equals("") || in.equals(""))
			return null;
		//存储元素
		String tp = pre.charAt(0)+"";//取首字符串，但是得到的SL中字符串为空时，要注意，可能抛出异常
		BinaryTree node = new BinaryTree();
		node.data = new ElemType(tp);
		//找到tp在in中的位置 i ，并分割中序序列字符串
		int i = in.indexOf(tp);
		/*以 tp对象 切割pre后 新的 pre 和 in*/
		//分割字符串 左为空时 即当分割符在首位
		if(i==0) {
			if(in.length()==1) {//两边为空
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
		//分割字符串 右为空 即当分割符在最后
		else if(i==in.length()-1 && i!=0) {
			pre = pre.substring(1);
			in = in.substring(0,i);
			node.lchild = BinaryTree.CreateBTree_PreInOrder(pre, in);
			node.rchild = null;
		}
		//分割字符串 左右有 即当分割符在中间
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
		System.out.println("递归中序序列遍历：");
		btree.LDR(btree);
		System.out.println("\n非递归中序序列遍历：");
		btree.InOrder();
		System.out.println("\n叶子结点数为（i+1）："+BinaryTree.BTreeLeaf(btree)+"\n深度："+BinaryTree.BTreeDepth(btree));
		System.out.println("\n层次遍历：");
		btree.LevelOrder();
		
		BinaryTree btree2 = BinaryTree.CreateBTree_PreInOrder("ABCDEFG","CBDAEGF");
		System.out.println("\n中序先序构造法之递归中序序序列遍历：");
		btree2.LDR(btree2);
	}

}

class SString{
	String str = "";
	public SString(String s){
		this.str = s;
	}
}