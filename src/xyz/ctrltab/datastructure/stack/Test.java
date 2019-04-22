package xyz.ctrltab.datastructure.stack;

public class Test {

	private static void build() {
		
		return;
	}
	public static void main(String[] args) {
		/**
		 * 逆波兰式 
		 * a*(b+c)-d/e	=>	abc+*de/-
		 */
		
		//输入表达式
		String eg = "a*(b+c)-d/e#";
		char [] chs = new char[eg.length()];
		for(int i=0; i<eg.length() ;i++)
			chs[i] = eg.charAt(i);
		//读取到第一个操作符，开辟新栈，进栈。若是界限符跳过。
		int i=0;//字符数组指针
		int k=0;//栈指针
		while(i<eg.length()) {
			if((chs[i]>='A'&&chs[i]<='Z')||(chs[i]>='a'&&chs[i]<='z'))
				break;
			else {
				i++;
				continue;
			}
		}
		char [] sk = new char[eg.length()];
		sk[k] = chs[i];
		//读取下一个字符
		char next = chs[++i];
		//a* ^ b的情况
		if(next=='*'||next=='/') {
			if((chs[i+1]>='A'&&chs[i+1]<='Z')||(chs[i+1]>='a'&&chs[i+1]<='z')) {
				if(chs[i+2]=='#'||chs[i+2]==')'||(chs[i+2]>='A'&&chs[i+2]<='Z')||(chs[i+2]>='a'&&chs[i+2]<='z')) {
					//置换位置
					sk[++k] = chs[i+1];
					sk[++k] = chs[i];
					i++;//指针指向下个位置
					return;
				}
				else {
					System.out.println("error");
					return;
				}
			}
			if(chs[i+1]=='(');
				//继续下一个新开始 Test.build();
		}
		//a- ^ b*c的情况 还要观察b后边的符号情况
		if(next=='+'||next=='-') {
			if((chs[i+1]>='A'&&chs[i+1]<='Z')||(chs[i+1]>='a'&&chs[i+1]<='z')) {
				if(chs[i+2]=='#'||chs[i+2]==')'||chs[i+2]=='+'||chs[i+2]=='-') {
					//置换位置
					sk[++k] = chs[i+1];
					sk[++k] = chs[i];
					i++;//指针指向下个位置
					return;
				}
			}
			if(chs[i+1]=='(');
				//继续下一个新开始 Test.build();
		}
	}

}
