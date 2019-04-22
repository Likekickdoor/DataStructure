package xyz.ctrltab.datastructure.algorithm;

import xyz.ctrltab.datastructure.list.LinkList;

/**
 * @author JeffLiu
 * @描述 数据结构/排序/各种排序方式/以数组元素（整数）为对象进行编写的 
 * @data 2019/3/18
 **/
//class Group {
//	LinkList<Integer> hd;
//	public Group(int i) {
//		this.hd = new LinkList<Integer>(i);
//	}
//}
public class Sort {
	//想要声明内部类，需要使用	外部类的实例 x.new 内部类 前面
	public class Group {
		LinkList<Integer> hd;
		public Group(int i) {
			this.hd = new LinkList<Integer>(i);
		}
	}
	/**
	 * 插入排序 
	*/
	//1. 直接插入排序
	static void SimpleInsertSort(int[] arr) {
		int i,j;
		for(i=1;i<arr.length;i++) {//后方预排序个数
			j=i-1;//注意如果将此句放到for的第一个分号内，仅执行一次
			int temp = arr[i];
			while(j>=0 && temp<arr[j]) {
				arr[j+1] = arr[j];
				j--;
			}
//			for(j=i-1;j>=0&&temp<arr[j];j--)
//				arr[j+1] = arr[j];
			arr[j+1] = temp;
		}
	}
	//2. 希尔排序(ShellSort)
	static void ShellSort(int[] arr,int interval) {
		if(interval==0)
			return;
		int i,j;
		for(i=interval;i<arr.length;i++) {//后方预排序个数
			j=i-interval;//注意如果将此句放到for的第一个分号内，仅执行一次
			int temp = arr[i];
			while(j>=0 && temp<arr[j]) {
				arr[j+interval] = arr[j];
				j = j-interval;
			}
			arr[j+interval] = temp;
		}
		Sort.ShellSort(arr, interval-1);
	}
	
	/**
	 * 交换排序
	 */
	//1. 冒泡排序
	static void BubbleSort(int[] arr) {
		int flag = 1;//表示冒泡过程是否存在交换的标志
		while(flag>0) {
			for(int i=1;i<arr.length;i++) {	//控制趟数，n个关键字，最多需要n-1此冒泡
				flag = 0;
				for(int j=1;j<=arr.length-i;j++) {	//控制比较数，对于n个关键字，在第i趟中，进行n-i次比较
					if(arr[j-1]>arr[j]) {
						int temp = arr[j-1];
						arr[j-1] = arr[j];
						arr[j] = temp;
						flag++;
					}
				}
			}
		}
	}
	//2. 快速排序
	static void QuickSort(int[] arr,int i,int j) {
		if(i<j) {
			int k = Sort.partition(arr, i, j);//经过此方法后会使arr发生变化
			Sort.QuickSort(arr, i, k-1);
			Sort.QuickSort(arr, k+1, j);
		}
	}
	static int partition(int[] arr,int i,int j) {
		int temp = arr[i];
		while(i<j) {//i=j时，划分结束，i、j都指向了分割中心，因此返回i或j一样
			while(j>i) {
				if(arr[j]<temp) {
					arr[i] = arr[j];
					i++;
					break;
				}
				j--;
			}
			
			while(i<j) {
				if(arr[i]>temp) {
					arr[j] = arr[i];
					j--;
					break;
				}
				i++;
			}
		}
		arr[i] = temp; //分割符归位中间
		return i;
	}
	
	/**
	 * 选择排序
	 **/
	//1. 简单选择排序
	static void SimpleSelectSort(int[] arr) {
		for(int i=0;i<=arr.length-1;i++) {
			int min = i;
			for(int j=i+1;j<=arr.length-1;j++) {
				if(arr[min]>arr[j]) 
					min = j;
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
	//2. 堆排序
	static void BuildDown(int[] arr,int n,int rootIndex) {
		int root = arr[rootIndex];//取出根结点
		int childIndex = 2*rootIndex+1;//取出左孩子的下标
		while(childIndex<n) {//childIndex小于n表示调整并未结束
			if(childIndex!=n-1 && arr[childIndex+1]>arr[childIndex])//不是最后的结点，表示root还有右孩子并大于左孩子
				childIndex++;//使得childIndex指向比较大的孩子
			if(root<arr[childIndex]) {
				arr[rootIndex] = arr[childIndex];//较大的数据往上移动
				rootIndex = childIndex;//设置新的根结点下标
				childIndex = 2*rootIndex+1;//确定新的左孩子下标
			}
			else
				break;
		}
		arr[rootIndex] = root;//将原本的root值，至于置换位置
	}
	static void HeapSort(int[] arr,int n) {
		for(int rootIndex=(n-2)/2;rootIndex>=0;rootIndex--) //从第一个非叶子结点开始构建堆结构
			Sort.BuildDown(arr, n, rootIndex);
		for(int i=n-1;i>=0;i--) {
			int temp = arr[0];
			arr[0] = arr[i];//把根结点（最大值）交换到子序列末尾
			arr[i] = temp;
			Sort.BuildDown(arr,i,0);//然后进行重新调整，构建堆结构
		}
	}
	
	/**
	 * 归并排序
	 */
	static void Merge(int[] arr,int s1,int e1,int s2,int e2,int[] b) {
		int k = s1;
		int i = s1;
		while((s1<=e1) && (s2<=e2)) {//2个分组都不为空
			if(arr[s1]<=arr[s2])
				b[k++] = arr[s1++];
			else
				b[k++] = arr[s2++];
		}
		while(s1<=e1)//若s1分组有剩余数据，则直接移动到辅助数组
			b[k++] = arr[s1++];
		while(s2<=e2)//若s2分组有剩余数据，则直接移动到辅助数组
			b[k++] = arr[s2++];
		k--;//将多加的部分减去
		while(k>=i) {//把辅助空间内数据拷到原序列
			arr[k] = b[k];
			k--;
		}
	}
	static void MergeSort(int[] arr,int i,int j) {
		int[] b = new int[arr.length];
		if(i<j) {
			int k = (i+j)/2;//找到分组中点
			Sort.MergeSort(arr, i, k);
			Sort.MergeSort(arr, k+1, j);
			Sort.Merge(arr, i, k, k+1, j, b);
		}
	}
	
	/**
	 * 基数排序
	 **/
	//描述：把原始关键字分解出主从关键字
	private static int resolve(int key,int level) {
		switch(level) {
			case 1: key = key%10;break;//取个位
			case 2: key = (key%100)/10;break;//取十位
		}
		return key;
	}
	//描述：分配操作
	private static void Arrange(LinkList<Integer> node,Group[] group,int level) {
		int index = Sort.resolve(node.GetData(), level);//数字各个位置上的数值
		LinkList<Integer> head= group[index].hd;
		if(head.GetNext()==null) {
			head.SetNext(node);
		}
		else {
			while(head.GetNext()!=null) //尾插法，直到最后一个结点，将新node最为新尾结点加到此结点
				head = head.GetNext();
			head.SetNext(node);
		}
	}
	//描述：收集操作
	private static LinkList<Integer> Collect(Group[] group) {
		LinkList<Integer> head = new LinkList<Integer>();
		for(int i=group.length-1;i>=0;i--) {
			LinkList<Integer> tail = group[i].hd;
			if(tail.GetNext()!=null) {
				while(tail.GetNext()!=null)
					tail = tail.GetNext();//寻到该分组最后一个结点
				tail.SetNext(head.GetNext());
				head.SetNext(group[i].hd.GetNext());
				//切断所有的链组
				group[i].hd.SetNext(null);
			}
		}
		return head;
	}
	//排序
	static void RadixSort(int[] arr) {
		Sort sort = new Sort();
		Group[] gps = new Group[10];
		for(int i=0;i<10;i++) {
			gps[i] = sort.new Group(i);//初始化group数组
		}
		
		//头插法，将输入的数组构建成链表形式，待将每个结点分配
		LinkList<Integer> head = new LinkList<Integer>();
		for(int i=arr.length-1;i>=0;i--) {
			LinkList<Integer> node = new LinkList<Integer>(arr[i]);
			node.SetNext(head.GetNext());
			head.SetNext(node);
		}
		for(int level=1;level<=2;level++) {
			//分配
			LinkList<Integer> tp = head.GetNext();
			while(tp!=null) {
//				System.out.println(1);
				Sort.Arrange(tp, gps, level);
				LinkList<Integer> node = tp;
				tp = tp.GetNext();
				node.SetNext(null);//将本结点与链表切开
			}
			//收集
			head = Sort.Collect(gps);
		}
		head.Display_LinkList();//打印出结点对象
//		System.exit(0);	
	}
	public static void main(String[] args) {
//		int[] arr1 = {4,1,2,5,3};
//		int[] arr2 = {3,7,8,2,4,1,5,3};
//		int[] arr3 = {4,3,7,1,2,8,6,5};
//		int[] arr4 = {3,1,8,4,5,7,6,2};
		
//		Sort.SimpleInsertSort(arr1);
//		int[] arr = arr1;
		
//		Sort.ShellSort(arr2, 3);
//		int[] arr = arr2;
		
//		Sort.BubbleSort(arr1);
//		int[] arr = arr1;
		
//		Sort.QuickSort(arr3, 0, arr3.length-1);
//		int[] arr = arr3;
		
//		Sort.SimpleSelectSort(arr1);
//		int[] arr = arr1;
		
//		Sort.HeapSort(arr4, arr4.length);
//		int[] arr = arr4;
		
//		Sort.MergeSort(arr1, 0, 4);
//		int[] arr = arr1;
//		for(int tp:arr)
//			System.out.print(tp+"\t");
		
//		int[] arr5 = {25,37,98,12,50,24,89,65,42,17}; 
//		Sort.RadixSort(arr5);
	}

}
