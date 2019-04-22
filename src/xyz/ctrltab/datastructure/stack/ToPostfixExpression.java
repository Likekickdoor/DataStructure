package xyz.ctrltab.datastructure.stack;

/**
 * @描述 该类是实现	中序表达式 转 逆波兰式  也是栈的一种实际应用
 * @例子 "a*(b+c)-d/e#"	=>	"abc+*de/-#"
 * @author JeffLiu
 * @date 2019/2/11
 */
public class ToPostfixExpression {
	//判断字符是什么 操作数 还是 运算符
	private int judageChar(char ch) {
		if(ch>='0'&&ch<='9')
			return 1;
		else if(ch=='*'||ch=='/'||ch=='-'||ch=='+')
			return 2;
		else if(ch=='(')
			return 3;
		else if(ch==')')
			return 4;
		else if(ch=='#')
			return 0;
		else
			return -1;
	}
	//判断等级
	private int judageRank(char ch) {
		if(ch=='*'||ch=='/')
			return 2;
		else if(ch=='+'||ch=='-')
			return 1;
		else
			return 0;
	}
	//转换成逆波兰式
	public char [] postfixExpression(String eg) throws Exception {
		//一个作为临时存储运算符的栈S1（含一个结束符号），一个作为输入逆波兰式的栈S2（空栈）
		LinkedStack <Character> stk1 = new LinkedStack <Character>();
		stk1.Push('#');
		LinkedStack <Character> stk2 = new LinkedStack <Character>();
		//输入表达式
		//String eg = "2+1*5-2/(2+2)#";
		eg=eg+"#";
		for(int i=0; i<eg.length(); i++) {
			char tp = eg.charAt(i);
			switch(this.judageChar(tp)) {
				case 1:
					stk2.Push(tp);
					break;
				case 2:
					while(this.judageRank(tp) <= this.judageRank(stk1.GetTopEle())) {
						stk2.Push(stk1.GetTopEle());
						stk1.Pop();
					}
					stk1.Push(tp);
					break;
				case 3:
					stk1.Push(tp);
					break;
				case 4:
					while(stk1.GetTopEle()!='(') {
						stk2.Push(stk1.GetTopEle());
						stk1.Pop();
					}
					stk1.Pop();
					break;
				case 0:
					while(stk1.GetTopEle()!='#') {
						stk2.Push(stk1.GetTopEle());
						stk1.Pop();
					}
					break;
				default:
					throw new Exception("表达式错误！");
			}
		}
		
		int i = stk2.LinkList_Length();
		char [] c = new char[i];
		while(--i>=0) {
			c[i] = stk2.GetTopEle();
			stk2.Pop();
		}
		stk1 = null;
		return c;
	}
	public static void main(String[] args) throws Exception {
		//实例化本类
		ToPostfixExpression one = new ToPostfixExpression();
		char [] get =one.postfixExpression("1*(2+3)-4/5");//一定必须是一个完整正确的算数表达式 结束符# 在方法体中加上
		for(int i=0;i<get.length;i++)
			System.out.print(get[i]+" ");
	}
}
