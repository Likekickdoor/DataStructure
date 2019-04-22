package xyz.ctrltab.datastructure.algorithm;


public class Search {
	
	/**
	 * 静态查找表 
	 **/
	//顺序查找
	static int SequenSearch(int[] arr,int g) {
		int i = arr.length;
		while(--i>=0){
			if(arr[i]==g)
				return i;
		}
		return 0;
	}
	//有序表折半查找
	static int BinarySearch(int[] arr,int key) {
		int low = 0;
		int high = arr.length-1;
		while(low<=high) {
			int middle = (low+high)/2;
			if(key==arr[middle])
				return middle;
			if(key<arr[middle])
				high = middle-1;
			else
				low = middle+1;
		}
		return -1;
	}
	/**
	 * 动态查找表 
	 **/
	//二叉排列树
	static BSTree.BSTNode SearchBST(BSTree.BSTNode root,int key){	
		if(root != null) {
			int ndata = root.data;
			if(key==ndata)
				return root;
			else if(key<ndata) 
				return Search.SearchBST(root.lchild, key);
			else 
				return Search.SearchBST(root.rchild, key);
		}
		else {
			return null;
		}
	}
	
	public static void main(String[] args) {
//		int[] arr = {1,2,3,4,5,6,7};
//		int index = Search.SequenSearch(arr, 3);
		
//		int index = Search.BinarySearch(arr, 5);
//		System.out.println("index:\t"+index+"\tvalue:\t"+arr[index]);
	
//		BSTree tree= new BSTree();
//		int[] key = {0,1,3,4,5,8};
//		for(int i=0;i<6;i++) {
//			tree.InsertBST(key[i]);
//		}
//		BSTree.BSTNode obj = Search.SearchBST(tree.root, 9);
//		if(obj!=null)
//			System.out.println(obj.data);
//		else
//			System.out.println("null");
		
	}

}
