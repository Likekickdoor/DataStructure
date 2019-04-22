package xyz.ctrltab.datastructure.string;
/**
 * @author JeffLiu
 * @描述：该类是对String数据结构的简单实现，无实际意义。Java中已经将String类型最为基本类型
 * 且是	串的定长顺序存储及其运算
 * @date 2019/2/23
 */
public class StringMe {
	
	char [] data;
	int curlen = -1;//指明字符数组最后一个字符的下标
	int MaxSize = 256;
	
	//没有长度要求者，按最大256的值申请空间
	public StringMe() {
		this.data = new char [this.MaxSize];
	}
	//按要求者，按要求值申请空间
	public StringMe(int size){
		this.MaxSize = size;
		this.data = new char [this.MaxSize];
	}
	//字符数组转字符串
	public StringMe(char [] input){
		int i = 0;
		while(i<input.length) {
			if(input[i]=='\0')
				break;
			i++;
		}
		this.curlen = i-1;
		this.data = input;
	}
	//数组的实际有效长度
	private int Length() {
		return this.curlen+1;
	}
	//串连接
	public static StringMe StrConcat (StringMe s1,StringMe s2) {
		int len = s1.Length() + s2.Length();
		StringMe s = new StringMe(len);
		int j=0,k=0;
		for(int i=0;i<s1.Length();i++) {
			s.data[k++] = s1.data[j++];
			s.curlen++;
		}
		j = 0;
		for(int i=0;i<s2.Length();i++) {
			s.data[k++] = s2.data[j++];
			s.curlen++;
		}
		return s;
	}
	//求子串	如abcdefg的子串cde pos=3 len=3
	public StringMe SubString(int pos,int len) {
		if(pos>this.Length()||pos<1||len<1)
			return null;//理应抛出错误
		else {
			if(this.Length()-pos+1<len) {
				len = this.Length()-pos+1;
			}
			StringMe sub = new StringMe(len);
			for(int i=0;i<len;i++)
				sub.data[i] = this.data[pos-1+i];
			return sub;
		}
	}
	//串比较 s1>s2 返回>0,否返回<0 或 =0
	public static int StrComp(StringMe s1,StringMe s2) {
		int i = 0;
		while(s1.data[i]==s2.data[i]) {
			i++;
			if(s1.Length()<=i || s2.Length()<=i) {
				i--;
				break;
			}
		}
		return s1.data[i]-s2.data[i];
	}
	//模式匹配		主串：abcdefg --> 匹配串：cde 返回匹配的位置	从 1算起
	public int Index_BF(StringMe T,int pos) {
		if(pos<1||pos>this.Length())
			return -1;
		int i = pos-1,j = 0;
		while(this.Length()>i && T.Length()>j)
			if(this.data[i+j]==T.data[j])
				j++;
			else{
				i++;
				j=0;
			}
		if(j>=T.Length())
			return i+1;
		else {
			return -1;
		}
	}
	
	public static void  main(String [] args) {
		char [] s1data = {'a','b','c','d','e','f','g'};
		char [] s2data = {'e','g'};
		StringMe s1 = new StringMe(s1data);
		StringMe s2 = new StringMe(s2data);
//		System.out.println(StringMe.StrConcat(s1, s2).data);//测试方法StrConcat(s1, s2)
//		System.out.println(s1.SubString(2, 10).data);//将字符串s1切割后的子串
//		System.out.println(StringMe.StrComp(s1, s2));//两个字符串比较大小
		System.out.println(s1.Index_BF(s2, 4));
	}
	
}
