package xyz.ctrltab.datastructure.list;

/**
 * @������ ���ҵĴ�����񣬲����������
 * https://blog.csdn.net/weixin_39471249/article/details/79729065
 * ����Java��������LinkedList
 * @date��2019��7��24��16:44:26
 * */
public class LinkedListMe<T>{
	// ��������
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
	
	// β�巨ʵ��
	public void add(T e) {
		Node<T> p = new Node<T>(e);
		tail.next = p;
		tail = p;
		this.size++;
	}
	// ��ӡ
	public void display() {
		Node<T> tp = head.next;// ����ͷ���
		while(tp != null) {
			System.out.print(tp.date+"\t");
			tp = tp.next;
		}
	}
	// ɾ��
	public void delete(int i) {
		// ������
		if(i<0 || i>this.size-1) { 
			throw new ArrayIndexOutOfBoundsException("λ�ò��Ϸ�");
		}
		else if( i==0 ){
			this.head.next=null; // ɾ���׽��
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
	// ��ý��
	private Node<T> getNode(int i) {
		// ������
		if(i<0 || i>this.size-1) { 
			throw new ArrayIndexOutOfBoundsException("λ�ò��Ϸ�");
		}
		else {
			int count = 0;
			Node<T> tp = head;
			if(tp != null)
				tp = tp.next; // ����ͷ���
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
	// ��ý��Ԫ��ֵ	
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
