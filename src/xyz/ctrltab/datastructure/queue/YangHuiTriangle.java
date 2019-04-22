package xyz.ctrltab.datastructure.queue;
/**
 * 杨辉三角是使用队列的数据结构的实例 
 */
public class YangHuiTriangle {

	public static void main(String[] args) {
/*
		int [][] arr= new int[4][4];
		// i = 0
		System.out.println(1);
		arr[0][0] = 1;
		// 4 > i >= 1
		for(int i=1;i<4;i++) {
			System.out.print(1+" ");
			arr[i][0] = 1;
			for(int j=1;j<=i;j++) {
				if(j==i) {
					System.out.print(1+" ");
					arr[i][j] = 1;
				}
				else {
					arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
					System.out.print(arr[i][j]+" ");
				}
			}
			System.out.println();
		}
*/
		int n=9,i,k;
		SequenQueue <Integer> sq = new SequenQueue <Integer>();
		System.out.println(1);
		sq.Enter(1);
		sq.Enter(1);
		for(i=2;i<=n;i++) {
			sq.Enter(1);
			for(k=0;k<=i-2;k++)
			{
				int a1 = sq.GetFrontData();
				sq.Delete();
				int a2 = sq.GetFrontData();
				System.out.print(a1+" ");
				sq.Enter(a1+a2);
			}
			int a2 = sq.GetFrontData();
			sq.Delete();
			System.out.println(a2);
			sq.Enter(1);
		}
	}
}
