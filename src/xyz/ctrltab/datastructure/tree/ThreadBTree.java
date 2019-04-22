package xyz.ctrltab.datastructure.tree;

public class ThreadBTree {

	Object data;
	ThreadBTree lchild,rchild;
	int ltag,rtag;
	static ThreadBTree PRE;//全局变量PRE始终指向刚才访问的结点
	//利用扩展先序遍历法，创建二叉树		如："ABC..D.E.FG..." 转换成二叉树
	public static ThreadBTree CreateBTree_ExtendPre(SString s) {
		if(s.str.equals(""))
			return null;//抛出异常
		ThreadBTree tree;
		String tp = s.str.substring(0, 1);//第一个字符串
		s.str = s.str.substring(1);//除去首字符串后的串
		if(tp.equals(".")) {
			return null;
		}
		else {
			tree = new ThreadBTree();
			tree.data = tp;//存储的元素
			tree.lchild = ThreadBTree.CreateBTree_ExtendPre(s);
			tree.rchild = ThreadBTree.CreateBTree_ExtendPre(s);
		}
		return tree;
	}
	//先序序列遍历
	public void DLR(ThreadBTree bt) {
		if(bt!=null) {
			this.visit((String)bt.data);
			this.DLR(bt.lchild);
			this.DLR(bt.rchild);
		}
	}
	//访问结点
	private void visit(String data) {
		System.out.print(data+"\t");
	}
	//中序线索化
	void InThreading(ThreadBTree p) {
		if(p!=null) {
			InThreading(p.lchild);
			if(p.lchild == null)
			{
				p.ltag = 1;
				p.lchild = ThreadBTree.PRE;
			}
			if(p.rchild == null)
			{
				p.rtag = 1;
				ThreadBTree.PRE.rchild = p;
			}
			ThreadBTree.PRE = p;
			InThreading(p.rchild);
		}
	}
	//中序线索
	boolean InOrderThread(ThreadBTree bt) {
		ThreadBTree head = new ThreadBTree();
		head.ltag = 0;
		head.rtag = 1;
		head.rchild = head; //右指针回指
		if(bt == null)
			head.lchild = head;//空二叉树，左指针回指
		else {
			head.lchild = bt;
			ThreadBTree.PRE = head;
			this.InThreading(bt);
			ThreadBTree.PRE.rchild = head;
			ThreadBTree.PRE.rtag = 1;
			head.rchild = ThreadBTree.PRE;
		}
		return true;
	}
	//中序线索二叉树中结点p的中序前驱结点
	ThreadBTree InPreNode(ThreadBTree p) {
		ThreadBTree pre;
		pre = p.lchild;
		if(p.ltag!=1)//结点p有左孩子
			while(pre.rtag==0)
				pre=pre.rchild;//从左子树的根结点开始，沿右指针域往下查找，直到没有右孩子为止
		return pre;
	}
	//中序线索二叉树中结点p的中序后继结点
	ThreadBTree InPostNode(ThreadBTree p) {
		ThreadBTree post;
		post = p.rchild;
		if(p.rtag!=1)//结点p有右孩子
			while(post.ltag==0)
				post = post.lchild;//从右子树的根结点开始，沿左指针域往下查找，直到没有左孩子为止
		return post;
	}
	public static void main(String[] args) {
		ThreadBTree tbtree= CreateBTree_ExtendPre(new SString("ABC..D.E.FG..."));
		tbtree.DLR(tbtree);
	}

}
