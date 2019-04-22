package xyz.ctrltab.datastructure.stack;

/**
 * @���� ������ʵ��	������ʽ ת �沨��ʽ  Ҳ��ջ��һ��ʵ��Ӧ��
 * @���� "a*(b+c)-d/e#"	=>	"abc+*de/-#"
 * @author JeffLiu
 * @date 2019/2/11
 */
public class ToPostfixExpression {
	//�ж��ַ���ʲô ������ ���� �����
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
	//�жϵȼ�
	private int judageRank(char ch) {
		if(ch=='*'||ch=='/')
			return 2;
		else if(ch=='+'||ch=='-')
			return 1;
		else
			return 0;
	}
	//ת�����沨��ʽ
	public char [] postfixExpression(String eg) throws Exception {
		//һ����Ϊ��ʱ�洢�������ջS1����һ���������ţ���һ����Ϊ�����沨��ʽ��ջS2����ջ��
		LinkedStack <Character> stk1 = new LinkedStack <Character>();
		stk1.Push('#');
		LinkedStack <Character> stk2 = new LinkedStack <Character>();
		//������ʽ
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
					throw new Exception("���ʽ����");
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
		//ʵ��������
		ToPostfixExpression one = new ToPostfixExpression();
		char [] get =one.postfixExpression("1*(2+3)-4/5");//һ��������һ��������ȷ���������ʽ ������# �ڷ������м���
		for(int i=0;i<get.length;i++)
			System.out.print(get[i]+" ");
	}
}
