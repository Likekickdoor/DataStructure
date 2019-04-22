package xyz.ctrltab.datastructure.stack;

/**
 * 
 * @author JeffLiu
 * @���� hanoi���Ǿ���ĵݹ�ʵ�������һ���ջ��Ӧ�á�������ջ�ǲ���ϵͳ�Լ�ά�ֵ�   �ݹ鹤��ջ 
 * �ݹ飺��������������Լ�	�γ�һ��ջʽ�ṹ��ϼ��������ϵͳ���жϵ�˼��
 *
 */
public class Hanoi {

	private void move(char x,int n,char y) {
		System.out.println("����"+n+":"+x+"-->"+y);
	}
	private void hanoi(int n,char x,char y,char z) {
		if(n==1)
			this.move(x,1,z);
		else {
			hanoi(n-1,x,z,y);
			
			this.move(x,n,z);
			
			hanoi(n-1,y,x,z);
		}
	}
	public static void main(String[] args) {
		Hanoi one = new Hanoi();
		one.hanoi(3, 'a', 'b', 'c');
	}

}
