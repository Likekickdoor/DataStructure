package xyz.ctrltab.datastructure.array;

//十字链表 实现 稀疏矩阵的三元表 存储
public class MLink{
	
	public int row,col;
	public Object v_next;
	public MLink right;
	public MLink  down;
	
	public MLink() {
		this.row = 0;
		this.col = 0;
		this.v_next = null;
	}
	
	public MLink(int row,int col,Object v) {
		this.row = row;
		this.col = col;
		this.v_next = v;
	}
	
	public void SetV_NEXT(Object v) {
		this.v_next = v;
	}
	
//	public void SetRight(MLink right) {
//		this.right = right;
//	}	
//	public void SetDown(MLink down) {
//		this.down = down;
//	}
	
	
	
	static MLink CreatMLink() {
		MLink HA,tail;
		int m=5,n=4,t=5,s=5;//s=max(m,n)  m：行、n：列     t：非零个数
		HA = new MLink (m,n,null);
		tail = HA;
		MLink [] H = new MLink[s+1];
		H[0] = HA;
		//建立头结点循环链表
		for(int i=0;i<t;i++) {
			MLink node = new MLink();
			H[i+1] = node;
			tail.SetV_NEXT(node);
			tail = node;
			if(i==t-1)
				node.SetV_NEXT(HA);
		}
		//输入三元表的元素
		for(int k=1;k<=t;k++) {
			int row = 2,col = 2;
			Object v = 1;
			MLink node = new MLink( row, col, v);
			//将新节点 node 插入到第row行
			tail = H[row];
			while(tail.right!=H[row]&&tail.right.col<col)
				tail = tail.right;
			node.right = tail.right;
			tail.right = node;
			//将新节点node 插入到第col列
			tail = H[col];
			while(tail.down!=H[col]&&tail.down.row<row)
				tail = tail.down;
			node.down = tail.down;
			tail.down = node;
		}
		return HA;
	}
	
	public static void main(String[] args) {

	}

}
