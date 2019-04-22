package xyz.ctrltab.datastructure.tree;

public class ThreadBTree {

	Object data;
	ThreadBTree lchild,rchild;
	int ltag,rtag;
	static ThreadBTree PRE;//ȫ�ֱ���PREʼ��ָ��ղŷ��ʵĽ��
	//������չ���������������������		�磺"ABC..D.E.FG..." ת���ɶ�����
	public static ThreadBTree CreateBTree_ExtendPre(SString s) {
		if(s.str.equals(""))
			return null;//�׳��쳣
		ThreadBTree tree;
		String tp = s.str.substring(0, 1);//��һ���ַ���
		s.str = s.str.substring(1);//��ȥ���ַ�����Ĵ�
		if(tp.equals(".")) {
			return null;
		}
		else {
			tree = new ThreadBTree();
			tree.data = tp;//�洢��Ԫ��
			tree.lchild = ThreadBTree.CreateBTree_ExtendPre(s);
			tree.rchild = ThreadBTree.CreateBTree_ExtendPre(s);
		}
		return tree;
	}
	//�������б���
	public void DLR(ThreadBTree bt) {
		if(bt!=null) {
			this.visit((String)bt.data);
			this.DLR(bt.lchild);
			this.DLR(bt.rchild);
		}
	}
	//���ʽ��
	private void visit(String data) {
		System.out.print(data+"\t");
	}
	//����������
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
	//��������
	boolean InOrderThread(ThreadBTree bt) {
		ThreadBTree head = new ThreadBTree();
		head.ltag = 0;
		head.rtag = 1;
		head.rchild = head; //��ָ���ָ
		if(bt == null)
			head.lchild = head;//�ն���������ָ���ָ
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
	//���������������н��p������ǰ�����
	ThreadBTree InPreNode(ThreadBTree p) {
		ThreadBTree pre;
		pre = p.lchild;
		if(p.ltag!=1)//���p������
			while(pre.rtag==0)
				pre=pre.rchild;//���������ĸ���㿪ʼ������ָ�������²��ң�ֱ��û���Һ���Ϊֹ
		return pre;
	}
	//���������������н��p�������̽��
	ThreadBTree InPostNode(ThreadBTree p) {
		ThreadBTree post;
		post = p.rchild;
		if(p.rtag!=1)//���p���Һ���
			while(post.ltag==0)
				post = post.lchild;//���������ĸ���㿪ʼ������ָ�������²��ң�ֱ��û������Ϊֹ
		return post;
	}
	public static void main(String[] args) {
		ThreadBTree tbtree= CreateBTree_ExtendPre(new SString("ABC..D.E.FG..."));
		tbtree.DLR(tbtree);
	}

}
