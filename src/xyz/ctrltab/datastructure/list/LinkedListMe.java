package xyz.ctrltab.datastructure.list;

/**
 * @描述： 和我的代码很像，并提出了问题
 * https://blog.csdn.net/weixin_39471249/article/details/79729065
 * 仿造Java的容器类LinkedList
 * @date：2019年7月24日16:44:26
 * */
public class LinkedListMe<T>{
	// 定义结点类
	private class Node<E extends T> {
		E date;
		Node<E> next;
		Node(E e){
			this.date = e;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size = 0;
	
	public int size() {
		return this.size;
	}
	LinkedListMe(){
		this.head = new Node<T>(null);
		this.tail = head;
	}
	
	// 尾插法实现
	public void add(T e) {
		Node<T> p = new Node<T>(e);
		tail.next = p;
		tail = p;
		this.size++;
	}
	// 打印
	public void display() {
		Node<T> tp = head.next;// 跳过头结点
		while(tp != null) {
			System.out.print(tp.date+"\t");
			tp = tp.next;
		}
	}
	// 删除
	public void delete(int i) {
		// 检查界限
		if(i<0 || i>this.size-1) { 
			throw new ArrayIndexOutOfBoundsException("位置不合法");
		}
		else if( i==0 ){
			this.head.next=null; // 删除首结点
		}
		else {
			Node<T> pre = this.getNode(i-1);
			pre.next = pre.next.next;
			if( i==this.size-1 ) {
				tail = pre;
			}
		}
		this.size--;
	}
	// 获得结点
	private Node<T> getNode(int i) {
		// 检查界限
		if(i<0 || i>this.size-1) { 
			throw new ArrayIndexOutOfBoundsException("位置不合法");
		}
		else {
			int count = 0;
			Node<T> tp = head;
			if(tp != null)
				tp = tp.next; // 跳过头结点
			while(tp != null) {
				if(count == i) {
					return tp;
				}
				tp = tp.next;
				count++;
			}
		}
		return null;
	}
	// 获得结点元素值	
	public T get(int i) {
		return this.getNode(i).date;
	}
	
	public static void main(String []args){
    	LinkedListMe<Character> linkedList = new LinkedListMe<Character>();
    	
    	linkedList.add('B');
    	linkedList.add('C');
    	linkedList.add('D');
    	linkedList.add('E');
    	linkedList.add('F');
    	
    	linkedList.delete(2);
    	linkedList.display();
    	
    	// System.out.println(linkedList.get(0));
    }
    
}
