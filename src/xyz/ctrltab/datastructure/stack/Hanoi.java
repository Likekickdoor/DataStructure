package xyz.ctrltab.datastructure.stack;

/**
 * 
 * @author JeffLiu
 * @描述 hanoi塔是经典的递归实例，而且还是栈的应用。不过此栈是操作系统自己维持的   递归工作栈 
 * 递归：即函数自身调用自己	形成一个栈式结构结合计算机操作系统的中断等思考
 *
 */
public class Hanoi {

	private void move(char x,int n,char y) {
		System.out.println("把盘"+n+":"+x+"-->"+y);
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
