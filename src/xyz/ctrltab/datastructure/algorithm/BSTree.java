package xyz.ctrltab.datastructure.algorithm;
//二叉排列树类
public class BSTree {
	//二叉排序树的结点
	class BSTNode{
		int data;
		BSTNode lchild;
		BSTNode rchild;
	}
	//排序树根结点
	BSTNode root;
	
	//二叉排序树的插入点
	BSTNode InsertBSTPoint(int key) {
		BSTNode parent = null;
		BSTNode keynode = this.root;//从子树根结点开始寻
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
				return keynode;//key==keynode.data找到该点，返回该点的地址
			}
		}
		keynode = parent;//没有寻到，将该路径上不为空的最后一个结点
		return keynode;//没有寻找到，返回null，该树为空树
	}
	//删除时使用的查询删除点的双亲结点和该结点
	BSTNode[] SearchPoint(int key) {
		BSTNode[] res = new BSTNode[2];
		BSTNode parent = null;
		BSTNode keynode = this.root;//从子树根结点开始寻
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
				return res;//key==keynode.data找到该点，返回该点和其双亲的地址
			}
		}
		return res;
	}
	//建立二叉排列树
	boolean InsertBST(int key) {
		BSTNode node = InsertBSTPoint(key);
		//如果该关键字已经在树中，则插入失败
		if(node!=null && node.data==key)
			return false;
		//如果该关键字不在树中
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
	//先序遍历
	static void DLR(BSTNode node) {
		if(node!=null) {
			System.out.print(node.data+"\t");
			BSTree.DLR(node.lchild);
			BSTree.DLR(node.rchild);
		}
	}
	//二叉排列树删除结点
	boolean DelNode(int key) {
		BSTNode[] res = SearchPoint(key);
		if(res!=null) {
			BSTNode p  = res[0];
			if(p!=null && p.data==key) {//确定是该结点，而非特殊情况：空树、最近的双亲结点
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
				else {//左右子树都不为空
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
	//测试函数
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
