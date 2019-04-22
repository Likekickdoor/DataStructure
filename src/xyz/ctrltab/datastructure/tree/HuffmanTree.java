package xyz.ctrltab.datastructure.tree;
/**
 * @author JeffLiu
 * @描述 哈弗曼树（最优二叉树）即所有叶子结点带权路径长度WPL值最小 
 * @date 2019/3/6
 */
public class HuffmanTree {
	int weight;
	HuffmanTree parent,lchild,rchild;
	//创建哈弗曼树
	public HuffmanTree CreateHuffmanTree(int [] arrs,HuffmanTree [] HFTreeLeaves) {
		HuffmanTree root = null;
		HuffmanTree [] leaves =  new HuffmanTree[arrs.length];
		
		for(int i=0;i<arrs.length;i++) {
			leaves[i] = new HuffmanTree();//数组中的元素新开辟空间
			leaves[i].weight = arrs[i];
		}
		
		for(int i=1,j=0;i<arrs.length;i++) {
			//将数组从小到大排列	引用型数组	Min_Pre(HuffmanTree [] arr,HuffmanTree min,HuffmanTree pre);
			HuffmanTree pre,min;
			int [] indexs = this.Min_Pre(leaves);
			min = leaves[indexs[0]];
			pre = leaves[indexs[1]];
			//哈弗曼编码需要使用
			if(min.lchild==null && j<arrs.length)
				HFTreeLeaves[j++] = min;
			if(pre.lchild==null && j<arrs.length)
				HFTreeLeaves[j++] = pre;
			
			HuffmanTree father = new HuffmanTree();
			min.parent = father;
			pre.parent = father;
			father.lchild = min;//第一个最小的
			father.rchild = pre;//第二个最小的
			father.weight = min.weight+pre.weight;
			//新的结点father	进入	  到剩余叶子结点数组中
			leaves[indexs[0]]=father;
			leaves[indexs[1]]=null;
			root = father;
		}
		return root;
	}
	//将 min和pre作为指针，存储权重较小的两个二叉树并返回min的下标等待新的双亲根结点占用
	int [] Min_Pre(HuffmanTree [] arr){
		int m,n;//存放新二叉树的根结点占用min的结点
		int i=0;
		while(i<arr.length) {
			if(arr[i]!=null)
				break;
			i++;
		}
		m = i;//得到数组中第一个非空元素下标
		//第一小
		for(i=0;i<arr.length;i++) {
        	if(arr[i]!=null) {
        		if(arr[m].weight>arr[i].weight)
        			m = i;
        	}
        }
		//第二小
		i=0;
		while(i<arr.length) {
			if(arr[i]!=null && i!=m)
				break;
			i++;
		}
		n=i;
		for(i=0;i<arr.length;i++) {
        	if(arr[i]!=null && i!=m) {
        		if(arr[n].weight>arr[i].weight)
        			n = i;
        	}
        }

		int [] indexs = {m,n};
//		System.out.println("min:"+min.weight+"\t"+"pre:"+pre.weight);
//		System.exit(0);
		return indexs;
	}
	//哈夫曼编码(倒置)
	static String [] HuffmanEncode(HuffmanTree [] leaves) {
		int len = leaves.length;
		String [] codes = new String[len];
		for(int i=0;i<len;i++) {
			String tp = "";
			HuffmanTree node = leaves[i].parent;
			HuffmanTree child = leaves[i];
			do{
				if(node.lchild == child)
					tp=tp+"0";
				else
					tp=tp+"1";
				child = node;
				node = node.parent;
			}while(node!=null);
			codes[i] = tp;
		}
		return codes;
	}
	//测试用例
	public static void main(String[] args) {
		int [] arrs = {5,29,7,8,14,23,3,11};
		HuffmanTree [] HFTreeLeaves = new HuffmanTree[arrs.length];//叶子结点
		new HuffmanTree().CreateHuffmanTree(arrs,HFTreeLeaves);
		
		String [] codes = HuffmanTree.HuffmanEncode(HFTreeLeaves);
		int i=0;
		for(String code:codes) {
			System.out.println(HFTreeLeaves[i].weight+"(倒置码):"+code);
			i++;
		}
	}
}
