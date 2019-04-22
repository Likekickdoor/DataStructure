package xyz.ctrltab.datastructure.tree;
/**
 * @author JeffLiu
 * @���� �������������Ŷ�������������Ҷ�ӽ���Ȩ·������WPLֵ��С 
 * @date 2019/3/6
 */
public class HuffmanTree {
	int weight;
	HuffmanTree parent,lchild,rchild;
	//������������
	public HuffmanTree CreateHuffmanTree(int [] arrs,HuffmanTree [] HFTreeLeaves) {
		HuffmanTree root = null;
		HuffmanTree [] leaves =  new HuffmanTree[arrs.length];
		
		for(int i=0;i<arrs.length;i++) {
			leaves[i] = new HuffmanTree();//�����е�Ԫ���¿��ٿռ�
			leaves[i].weight = arrs[i];
		}
		
		for(int i=1,j=0;i<arrs.length;i++) {
			//�������С��������	����������	Min_Pre(HuffmanTree [] arr,HuffmanTree min,HuffmanTree pre);
			HuffmanTree pre,min;
			int [] indexs = this.Min_Pre(leaves);
			min = leaves[indexs[0]];
			pre = leaves[indexs[1]];
			//������������Ҫʹ��
			if(min.lchild==null && j<arrs.length)
				HFTreeLeaves[j++] = min;
			if(pre.lchild==null && j<arrs.length)
				HFTreeLeaves[j++] = pre;
			
			HuffmanTree father = new HuffmanTree();
			min.parent = father;
			pre.parent = father;
			father.lchild = min;//��һ����С��
			father.rchild = pre;//�ڶ�����С��
			father.weight = min.weight+pre.weight;
			//�µĽ��father	����	  ��ʣ��Ҷ�ӽ��������
			leaves[indexs[0]]=father;
			leaves[indexs[1]]=null;
			root = father;
		}
		return root;
	}
	//�� min��pre��Ϊָ�룬�洢Ȩ�ؽ�С������������������min���±�ȴ��µ�˫�׸����ռ��
	int [] Min_Pre(HuffmanTree [] arr){
		int m,n;//����¶������ĸ����ռ��min�Ľ��
		int i=0;
		while(i<arr.length) {
			if(arr[i]!=null)
				break;
			i++;
		}
		m = i;//�õ������е�һ���ǿ�Ԫ���±�
		//��һС
		for(i=0;i<arr.length;i++) {
        	if(arr[i]!=null) {
        		if(arr[m].weight>arr[i].weight)
        			m = i;
        	}
        }
		//�ڶ�С
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
	//����������(����)
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
	//��������
	public static void main(String[] args) {
		int [] arrs = {5,29,7,8,14,23,3,11};
		HuffmanTree [] HFTreeLeaves = new HuffmanTree[arrs.length];//Ҷ�ӽ��
		new HuffmanTree().CreateHuffmanTree(arrs,HFTreeLeaves);
		
		String [] codes = HuffmanTree.HuffmanEncode(HFTreeLeaves);
		int i=0;
		for(String code:codes) {
			System.out.println(HFTreeLeaves[i].weight+"(������):"+code);
			i++;
		}
	}
}
