package xyz.ctrltab.datastructure.list;

/**
 * 
 * @author JeffLiu
 * @date 2019/1/29
 * @version 1.0
 * @describe 数据结构/线性表/顺序表
 * @参考：https://blog.csdn.net/iteye_71/article/details/82677191 但是可能是object强制转换为其他的类型是错误的
 */
//数据元素
class ElemType1{
	public String stuName;
	public String stuNum;
	public int [] stuSorce = {0,0,0};
}

@SuppressWarnings("hiding")
public class SequenList <ElemType> {

	private int MAXSIZE = 50; 
	//数据对象
//	public ElemType [] data;
	public Object [] data;
	public int last;
	
	/*
	 * 基本操作
	 */
	//初始化顺序表类的对象（构造方法）,可变长度
	public SequenList(){
//		this.data = new ElemType[MAXSIZE];//不能使用泛类创建对象
		this.data = new Object[this.MAXSIZE];
		this.last = -1;
	}
	public SequenList(int size){
		this.MAXSIZE = size;
		this.data = new Object[this.MAXSIZE];
		this.last = -1;
	}
	//顺序表的长度
	public int SequenList_Length() {
		return this.last+1;
	}
	//插入数据
	public boolean Insert_SequenList(ElemType e,int i) {
		if(this.last>=this.MAXSIZE-1) {
			return false;
		}
		if(i<1||i>this.last+2) {
			return false;
		}
		for(int j=this.last;j>=i-1;j--) {
			this.data[j+1] = this.data[j];
		}
		this.data[i-1] = e;
		this.last++;	//顺序表长度+1
		return true;
	}
	//删除数据
	public boolean Delete_SequenList(int d) {
		if(d<1||d>this.last+1) {
			return false;
		}
		else {
			for(int start=d;start<=this.last;start++) {
				this.data[start-1]=this.data[start];
			}
			this.last--;
			return true;
		}
	}
	//取节点n处数据的函数
	@SuppressWarnings("unchecked")
	public ElemType GetData_SequenList(int n) {
		if(n<1||n>this.last+1)
			return null;
		else
			return (ElemType)this.data[n-1];
	}
	//查找某个元素
	public int Search_SequenList(ElemType key) {
		for(int i=0;i<=this.last;i++) {
			if(this.data[i]==key)
				return i;//寻找成功返回其顺序表中位置
		}
		return -1;
	}
	//遍历打印顺序表(建议继承后自定义)
	public boolean Print_SequenList() {
		if(this.last==-1)
			return false;
		for(int i=0;i<=this.last;i++)
//			System.out.println("顺序表序号"+(i+1)+"\t姓名"+this.data[i].stuName+"\t学号"+this.data[i].stuNum+"\t成绩（语数外和）"+(this.data[i].stuSorce[0]+this.data[i].stuSorce[1]+this.data[i].stuSorce[2]));
			System.out.println("顺序表序号"+(i+1)+"\t数据元素"+this.data[i].toString());
		return true;
	}
	public static void main(String[] args) {
		//改为泛型类后
		ElemType1 elmT1 = new ElemType1();
		elmT1.stuName = "小A";
		elmT1.stuNum = "201641842301";
		elmT1.stuSorce[0] = 1;
		elmT1.stuSorce[1] = 1;
		elmT1.stuSorce[2] = 1;
		ElemType1 elmT2 = new ElemType1();
		elmT2.stuName = "小B";
		elmT2.stuNum = "201641842302";
		elmT2.stuSorce[0] = 2;
		elmT2.stuSorce[1] = 2;
		elmT2.stuSorce[2] = 2;
		ElemType1 elmT3 = new ElemType1();
		elmT3.stuName = "小C";
		elmT3.stuNum = "201641842303";
		elmT3.stuSorce[0] = 3;
		elmT3.stuSorce[1] = 3;
		elmT3.stuSorce[2] = 3;
		
		SequenList <ElemType1> iSeqList = new SequenList <ElemType1>();
//		iSeqList.data = new ElemType1[iSeqList.MAXSIZE];
		
		iSeqList.Insert_SequenList(elmT1, 1);
		iSeqList.Insert_SequenList(elmT2, 2);
		iSeqList.Insert_SequenList(elmT3, 3);
		
		iSeqList.Delete_SequenList(2);
		
//		iSeqList.Print_SequenList();
		for(int i=0;i<=iSeqList.last;i++)
		{
			ElemType1 one = (ElemType1) iSeqList.data[i];
			System.out.println(one.stuName);
		}
//		System.exit(0);
		System.out.println("iSeqList的最终长度:"+iSeqList.SequenList_Length());
		
		System.out.println("获取第1个元素:"+iSeqList.GetData_SequenList(1).stuName+"\t"+iSeqList.GetData_SequenList(1).stuNum);
		
		int index = iSeqList.Search_SequenList(elmT2);
		System.out.println("搜索到的下标index："+index);
	}

}