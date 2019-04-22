package xyz.ctrltab.datastructure.string;
/**
 * @author JeffLiu
 * @�����������Ƕ�String���ݽṹ�ļ�ʵ�֣���ʵ�����塣Java���Ѿ���String������Ϊ��������
 * ����	���Ķ���˳��洢��������
 * @date 2019/2/23
 */
public class StringMe {
	
	char [] data;
	int curlen = -1;//ָ���ַ��������һ���ַ����±�
	int MaxSize = 256;
	
	//û�г���Ҫ���ߣ������256��ֵ����ռ�
	public StringMe() {
		this.data = new char [this.MaxSize];
	}
	//��Ҫ���ߣ���Ҫ��ֵ����ռ�
	public StringMe(int size){
		this.MaxSize = size;
		this.data = new char [this.MaxSize];
	}
	//�ַ�����ת�ַ���
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
	//�����ʵ����Ч����
	private int Length() {
		return this.curlen+1;
	}
	//������
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
	//���Ӵ�	��abcdefg���Ӵ�cde pos=3 len=3
	public StringMe SubString(int pos,int len) {
		if(pos>this.Length()||pos<1||len<1)
			return null;//��Ӧ�׳�����
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
	//���Ƚ� s1>s2 ����>0,�񷵻�<0 �� =0
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
	//ģʽƥ��		������abcdefg --> ƥ�䴮��cde ����ƥ���λ��	�� 1����
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
//		System.out.println(StringMe.StrConcat(s1, s2).data);//���Է���StrConcat(s1, s2)
//		System.out.println(s1.SubString(2, 10).data);//���ַ���s1�и����Ӵ�
//		System.out.println(StringMe.StrComp(s1, s2));//�����ַ����Ƚϴ�С
		System.out.println(s1.Index_BF(s2, 4));
	}
	
}
