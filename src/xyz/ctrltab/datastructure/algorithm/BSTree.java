package xyz.ctrltab.datastructure.algorithm;
//������������
public class BSTree {
	//�����������Ľ��
	class BSTNode{
		int data;
		BSTNode lchild;
		BSTNode rchild;
	}
	//�����������
	BSTNode root;
	
	//�����������Ĳ����
	BSTNode InsertBSTPoint(int key) {
		BSTNode parent = null;
		BSTNode keynode = this.root;//����������㿪ʼѰ
		while(keynode!=null) {
			if(key>keynode.data) {
				parent = keynode;
				keynode = keynode.rchild;
			}
			else if(key<keynode.data) {
				parent = keynode;
				keynode = keynode.lchild;
			}
			else {
				return keynode;//key==keynode.data�ҵ��õ㣬���ظõ�ĵ�ַ
			}
		}
		keynode = parent;//û��Ѱ��������·���ϲ�Ϊ�յ����һ�����
		return keynode;//û��Ѱ�ҵ�������null������Ϊ����
	}
	//ɾ��ʱʹ�õĲ�ѯɾ�����˫�׽��͸ý��
	BSTNode[] SearchPoint(int key) {
		BSTNode[] res = new BSTNode[2];
		BSTNode parent = null;
		BSTNode keynode = this.root;//����������㿪ʼѰ
		while(keynode!=null) {
			if(key>keynode.data) {
				parent = keynode;
				keynode = keynode.rchild;
			}
			else if(key<keynode.data) {
				parent = keynode;
				keynode = keynode.lchild;
			}
			else {
				res[0] = keynode;
				res[1] = parent;
				return res;//key==keynode.data�ҵ��õ㣬���ظõ����˫�׵ĵ�ַ
			}
		}
		return res;
	}
	//��������������
	boolean InsertBST(int key) {
		BSTNode node = InsertBSTPoint(key);
		//����ùؼ����Ѿ������У������ʧ��
		if(node!=null && node.data==key)
			return false;
		//����ùؼ��ֲ�������
		BSTNode addNode = new BSTNode();
		addNode.data = key;
		
		if(node == null) 
			this.root = addNode;
		else if(node.data < key) 
			node.rchild = addNode;
		else 
			node.lchild = addNode;
		return true;
	}	
	//�������
	static void DLR(BSTNode node) {
		if(node!=null) {
			System.out.print(node.data+"\t");
			BSTree.DLR(node.lchild);
			BSTree.DLR(node.rchild);
		}
	}
	//����������ɾ�����
	boolean DelNode(int key) {
		BSTNode[] res = SearchPoint(key);
		if(res!=null) {
			BSTNode p  = res[0];
			if(p!=null && p.data==key) {//ȷ���Ǹý�㣬������������������������˫�׽��
				BSTNode par = res[1];
				if(p.lchild==null && p.rchild!=null) {
					if(par.rchild==p) 
						par.rchild = p.rchild;
					else
						par.lchild = p.rchild; 
				}
				else if(p.rchild==null && p.lchild!=null) {
					if(par.lchild==p) 
						par.lchild = p.lchild;
					else
						par.rchild = p.lchild; 
				}
				else if(p.rchild==null && p.lchild==null) {
					if(par.lchild==p) 
						par.lchild = null;
					else
						par.rchild = null;
				}
				else {//������������Ϊ��
					BSTNode farRd = p.lchild.rchild;
					if(farRd==null)
						farRd = p.lchild;
					else {
						while(farRd.rchild!=null)
							farRd = farRd.rchild;
					}
					p.lchild.rchild = farRd.lchild;
					p.data = farRd.data;
					farRd = null;				
				}
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	//���Ժ���
	public static void main(String[] args) {
		BSTree tree= new BSTree();
		int[] key = {4,2,3,5,8,0,1};
		for(int i=0;i<key.length;i++) {
			tree.InsertBST(key[i]);
		}
		System.out.println();
		tree.DelNode(4);
		BSTree.DLR(tree.root);
	}

}
