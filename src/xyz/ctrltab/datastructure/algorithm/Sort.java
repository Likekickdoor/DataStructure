package xyz.ctrltab.datastructure.algorithm;

import xyz.ctrltab.datastructure.list.LinkList;

/**
 * @author JeffLiu
 * @���� ���ݽṹ/����/��������ʽ/������Ԫ�أ�������Ϊ������б�д�� 
 * @data 2019/3/18
 **/
//class Group {
//	LinkList<Integer> hd;
//	public Group(int i) {
//		this.hd = new LinkList<Integer>(i);
//	}
//}
public class Sort {
	//��Ҫ�����ڲ��࣬��Ҫʹ��	�ⲿ���ʵ�� x.new �ڲ��� ǰ��
	public class Group {
		LinkList<Integer> hd;
		public Group(int i) {
			this.hd = new LinkList<Integer>(i);
		}
	}
	/**
	 * �������� 
	*/
	//1. ֱ�Ӳ�������
	static void SimpleInsertSort(int[] arr) {
		int i,j;
		for(i=1;i<arr.length;i++) {//��Ԥ�������
			j=i-1;//ע��������˾�ŵ�for�ĵ�һ���ֺ��ڣ���ִ��һ��
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
	//2. ϣ������(ShellSort)
	static void ShellSort(int[] arr,int interval) {
		if(interval==0)
			return;
		int i,j;
		for(i=interval;i<arr.length;i++) {//��Ԥ�������
			j=i-interval;//ע��������˾�ŵ�for�ĵ�һ���ֺ��ڣ���ִ��һ��
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
	 * ��������
	 */
	//1. ð������
	static void BubbleSort(int[] arr) {
		int flag = 1;//��ʾð�ݹ����Ƿ���ڽ����ı�־
		while(flag>0) {
			for(int i=1;i<arr.length;i++) {	//����������n���ؼ��֣������Ҫn-1��ð��
				flag = 0;
				for(int j=1;j<=arr.length-i;j++) {	//���ƱȽ���������n���ؼ��֣��ڵ�i���У�����n-i�αȽ�
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
	//2. ��������
	static void QuickSort(int[] arr,int i,int j) {
		if(i<j) {
			int k = Sort.partition(arr, i, j);//�����˷������ʹarr�����仯
			Sort.QuickSort(arr, i, k-1);
			Sort.QuickSort(arr, k+1, j);
		}
	}
	static int partition(int[] arr,int i,int j) {
		int temp = arr[i];
		while(i<j) {//i=jʱ�����ֽ�����i��j��ָ���˷ָ����ģ���˷���i��jһ��
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
		arr[i] = temp; //�ָ����λ�м�
		return i;
	}
	
	/**
	 * ѡ������
	 **/
	//1. ��ѡ������
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
	//2. ������
	static void BuildDown(int[] arr,int n,int rootIndex) {
		int root = arr[rootIndex];//ȡ�������
		int childIndex = 2*rootIndex+1;//ȡ�����ӵ��±�
		while(childIndex<n) {//childIndexС��n��ʾ������δ����
			if(childIndex!=n-1 && arr[childIndex+1]>arr[childIndex])//�������Ľ�㣬��ʾroot�����Һ��Ӳ���������
				childIndex++;//ʹ��childIndexָ��Ƚϴ�ĺ���
			if(root<arr[childIndex]) {
				arr[rootIndex] = arr[childIndex];//�ϴ�����������ƶ�
				rootIndex = childIndex;//�����µĸ�����±�
				childIndex = 2*rootIndex+1;//ȷ���µ������±�
			}
			else
				break;
		}
		arr[rootIndex] = root;//��ԭ����rootֵ�������û�λ��
	}
	static void HeapSort(int[] arr,int n) {
		for(int rootIndex=(n-2)/2;rootIndex>=0;rootIndex--) //�ӵ�һ����Ҷ�ӽ�㿪ʼ�����ѽṹ
			Sort.BuildDown(arr, n, rootIndex);
		for(int i=n-1;i>=0;i--) {
			int temp = arr[0];
			arr[0] = arr[i];//�Ѹ���㣨���ֵ��������������ĩβ
			arr[i] = temp;
			Sort.BuildDown(arr,i,0);//Ȼ��������µ����������ѽṹ
		}
	}
	
	/**
	 * �鲢����
	 */
	static void Merge(int[] arr,int s1,int e1,int s2,int e2,int[] b) {
		int k = s1;
		int i = s1;
		while((s1<=e1) && (s2<=e2)) {//2�����鶼��Ϊ��
			if(arr[s1]<=arr[s2])
				b[k++] = arr[s1++];
			else
				b[k++] = arr[s2++];
		}
		while(s1<=e1)//��s1������ʣ�����ݣ���ֱ���ƶ�����������
			b[k++] = arr[s1++];
		while(s2<=e2)//��s2������ʣ�����ݣ���ֱ���ƶ�����������
			b[k++] = arr[s2++];
		k--;//����ӵĲ��ּ�ȥ
		while(k>=i) {//�Ѹ����ռ������ݿ���ԭ����
			arr[k] = b[k];
			k--;
		}
	}
	static void MergeSort(int[] arr,int i,int j) {
		int[] b = new int[arr.length];
		if(i<j) {
			int k = (i+j)/2;//�ҵ������е�
			Sort.MergeSort(arr, i, k);
			Sort.MergeSort(arr, k+1, j);
			Sort.Merge(arr, i, k, k+1, j, b);
		}
	}
	
	/**
	 * ��������
	 **/
	//��������ԭʼ�ؼ��ַֽ�����ӹؼ���
	private static int resolve(int key,int level) {
		switch(level) {
			case 1: key = key%10;break;//ȡ��λ
			case 2: key = (key%100)/10;break;//ȡʮλ
		}
		return key;
	}
	//�������������
	private static void Arrange(LinkList<Integer> node,Group[] group,int level) {
		int index = Sort.resolve(node.GetData(), level);//���ָ���λ���ϵ���ֵ
		LinkList<Integer> head= group[index].hd;
		if(head.GetNext()==null) {
			head.SetNext(node);
		}
		else {
			while(head.GetNext()!=null) //β�巨��ֱ�����һ����㣬����node��Ϊ��β���ӵ��˽��
				head = head.GetNext();
			head.SetNext(node);
		}
	}
	//�������ռ�����
	private static LinkList<Integer> Collect(Group[] group) {
		LinkList<Integer> head = new LinkList<Integer>();
		for(int i=group.length-1;i>=0;i--) {
			LinkList<Integer> tail = group[i].hd;
			if(tail.GetNext()!=null) {
				while(tail.GetNext()!=null)
					tail = tail.GetNext();//Ѱ���÷������һ�����
				tail.SetNext(head.GetNext());
				head.SetNext(group[i].hd.GetNext());
				//�ж����е�����
				group[i].hd.SetNext(null);
			}
		}
		return head;
	}
	//����
	static void RadixSort(int[] arr) {
		Sort sort = new Sort();
		Group[] gps = new Group[10];
		for(int i=0;i<10;i++) {
			gps[i] = sort.new Group(i);//��ʼ��group����
		}
		
		//ͷ�巨������������鹹����������ʽ������ÿ��������
		LinkList<Integer> head = new LinkList<Integer>();
		for(int i=arr.length-1;i>=0;i--) {
			LinkList<Integer> node = new LinkList<Integer>(arr[i]);
			node.SetNext(head.GetNext());
			head.SetNext(node);
		}
		for(int level=1;level<=2;level++) {
			//����
			LinkList<Integer> tp = head.GetNext();
			while(tp!=null) {
//				System.out.println(1);
				Sort.Arrange(tp, gps, level);
				LinkList<Integer> node = tp;
				tp = tp.GetNext();
				node.SetNext(null);//��������������п�
			}
			//�ռ�
			head = Sort.Collect(gps);
		}
		head.Display_LinkList();//��ӡ��������
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
