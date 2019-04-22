package xyz.ctrltab.datastructure.graph;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xyz.ctrltab.datastructure.queue.LinkedQueue;
import xyz.ctrltab.datastructure.stack.LinkedStack;


/**
 * @author JeffLiu
 * @���� ͼ��һ�ֲ��ƽ��ǰ���ͺ����ķ����Խṹ
 * @date 2019/3/15
 */
/**
 * ��Ӿ�����༯��
 */
class VertexType {
	Object elm;
	public VertexType(Object input) {
		this.elm = input;
	}
}
class MGraph {	
	VertexType [] vexs;//�����V
	int [][] edges;//��Ӿ���E�����߱�
	int n,e;//�������ͱ���
	
	public MGraph(int n,int e){
		this.vexs = new VertexType[n];
		this.edges = new int [n][n];
		this.n = n;//����ʱ���붥����
		this.e = e;//����ʱ�������
	}
}
/**
 * ��ӱ���༯��
 */
class EdgeNode {
	int adjvex;
	EdgeNode next;
	int info;//Ȩ��
	public EdgeNode(int adjvex,int info) {
		this.adjvex = adjvex;
		this.info = info;
	}
}
class VertexNode {
	VertexType vertex;//�������
	EdgeNode firstedge;//��һ���ߵ�ָ����
	public VertexNode(Object in) {
		this.vertex = new VertexType(in);
		this.firstedge = null;
	}
}
class ALGraph {
	VertexNode [] adjlist;//��������
	int n,e;//�������ͱ���
	public ALGraph(int n,int e){
		this.adjlist = new VertexNode[n];
		this.n = n;
		this.e = e;
	}
}
/**
 * Kruskal�㷨�У���������E��˳����ͼ�ı� 
 **/
class KEdge {
	int vex1;//�ߵ���ʼ����(�±�)
	int vex2;//�ߵ���ֹ����(�±�)
	int weight;//�ߵ�Ȩ��
	public KEdge(int v1,int v2,int w) {
		this.vex1 = v1;
		this.vex2 = v2;
		this.weight = w;
	}
}
/**
 * Prim�㷨�У���¼U��V-U������СȨ�ص����� ���˴����������Է��ڷ�����
 **/
class Closedge{
	int adjvex;//������U�еĶ���
	int lowcost;//�ñ�Ȩ
	public Closedge(int a,int lwt) {
		this.adjvex = a;
		this.lowcost = lwt;
	}
}
public class Graph {

	boolean [] visited;//�����ʱ������
	//ͼ�������������������Ӿ���	 n:������		e:����
	static MGraph CreateMGraph(int n,int e,boolean isDirect) {
		MGraph G = new MGraph(n,e);
		Scanner read = new Scanner(System.in);
		System.out.println("�����붥����Ϣ��");
		for(int i=0;i<G.n;i++)//���붥����Ϣ�����������
			G.vexs[i] = new VertexType(read.next());
		System.out.println("������ÿ����Ŷ�Ӧ�������������ţ�(�����ʽ(i,j)��(i,j,weight))");
		int weight = 1;
		for(int i=0;i<G.e;i++) {
			String tp = read.next();
		    Pattern p = Pattern.compile("([\\d]+)");
		    Matcher m = p.matcher(tp);
		    int j = 0,row = 0,col = 0;
		    while (m.find()) {
		    	if(j==0)
		    		row = Integer.parseInt(m.group());
		    	if(j==1)
		    		col = Integer.parseInt(m.group());
		    	if(j==2)
		    		weight = Integer.parseInt(m.group());
		    	j++;
		    }
			G.edges[row-1][col-1] = weight;
			if(!isDirect)
				G.edges[col-1][row-1] = weight;
		}
		read.close();
		return G;
	}
	//ͼ�������������������ӱ�	 n:������		e:����
	static ALGraph CreateALGraph(int n,int e) {
		ALGraph G = new ALGraph(n,e);
		Scanner read = new Scanner(System.in);
		System.out.println("�����붥����Ϣ��");
		for(int i=0;i<G.n;i++)//���붥����Ϣ�����������
			G.adjlist[i] = new VertexNode(read.next());
		System.out.println("������ÿ����Ŷ�Ӧ�������������ţ�(�����ʽ(Vi,Vj)��(Vi,Vj,weight))");
		int weight = 1;
		for(int i=0;i<G.e;i++) {
			String tp = read.next();
		    Pattern p = Pattern.compile("([\\d]+)");
		    Matcher m = p.matcher(tp);
		    int j = 0,vi = 0,vj = 0;//<vi,vj>�ǻ�β�±�ͻ�ͷ�±�
		    while (m.find()) {
		    	if(j==0)
		    		vi = Integer.parseInt(m.group());
		    	if(j==1)
		    		vj = Integer.parseInt(m.group());
		    	if(j==2)
		    		weight = Integer.parseInt(m.group());
		    	j++;
		    }
			EdgeNode s = new EdgeNode(vj-1,weight);
			s.next = G.adjlist[vi-1].firstedge;
			G.adjlist[vi-1].firstedge = s;
		}
		read.close();
		return G;
	}
	//����������ұ���ALGraph
	void DFSTraverseALL(ALGraph G) {
		this.visited = new boolean[G.n];//���������ʼ����ȫΪfalse
		for(int i=0;i<G.n;i++)
			if(!this.visited[i])
				this.DFSAL(G,i);
	}
	void DFSAL(ALGraph G,int i) {
		//���ʽڵ�
		System.out.print("���ʽ�㣺"+G.adjlist[i].vertex.elm+"\n");
		//��Ǹý���ѱ�����
		this.visited[i] = true;
		//ȡvi�߱��ͷָ��
		EdgeNode p = G.adjlist[i].firstedge;
		while(p!=null) {
			if(!this.visited[p.adjvex])
				this.DFSAL(G, p.adjvex);//����ӵ�δ�����ʹ�
			p = p.next;//��vi����һ����ӵ�
		}
	}
	//����������ұ���
	void BFSTraverseALL(MGraph G) {
		this.visited = new boolean[G.n];
		for(int i=0;i<G.n;i++)
			if(!this.visited[i])
				this.BFSM(G,i);
	}
	void BFSM(MGraph G,int i) {
		LinkedQueue <Integer> Q = new LinkedQueue<Integer>();
		//���ʽڵ�
		System.out.print("���ʽ�㣺"+G.vexs[i].elm+"\n");
		//��Ǹý���ѱ�����
		this.visited[i] = true;
		Q.Enter(i);//�����±������
		while(!Q.IsEmpty()) {
			int r = Q.GetFrontData();
			Q.Delete();//������
			for(int j=0;j<G.n;j++) 
				if(G.edges[r][j]>0 && !this.visited[j]) {//��vjδ����
					System.out.print("���ʽ�㣺"+G.vexs[j].elm+"\n");
					this.visited[j] = true;
					Q.Enter(j);//���ʹ���vj�����
				}
		}
	}
	
	//��³˹�����㷨��Kruskal������С����������������E�� ������n������e
	static void Kruskal(KEdge [] E,int n,int e) {
		int [] vset = new int[n];//������¼�����Ƿ�����ͬ���ϵĸ�������
		for(int i=0;i<n;i++)//��ʼ����������
			vset[i] = i;
		int k = 0;//��¼��ǰ������С�������ĵ�k���ߣ���ʼֵΪ0
		int m1,m2;
		int sn1,sn2;
		for(int j=0;j<e;j++) {
			m1 = E[j].vex1;
			m2 = E[j].vex2;
			sn1 = vset[m1];
			sn2 = vset[m2];
			if(sn1!=sn2)
			{
				System.out.println(String.format("ѡȡ��(V%s,V%s)����ֵΪ:%s",m1,m2,E[j].weight));
				k++;
				if(k == n-1)
					break;
				for(int i=0;i<n;i++)
					if(vset[i]==sn1)
						vset[i] = sn2;
			}
		}
	}
	//����ķ�㷨��Prim������С��������v�ǵ�һ�����뼯��U�еĶ�������
	static void Prim(MGraph G,int v) {
		int Infinity = 999;//�����
		Closedge [] closedge = new Closedge[G.n];
		closedge[v] = new Closedge(v,0);
		//��ʼ��closedge����
		for(int j=0;j<G.n;j++) {
			if(j!=v) {
				int cost = G.edges[v][j];
				if(cost==0)
					cost = Infinity;
				closedge[j] = new Closedge(v,cost);
			}
		}
		int k=-1;
		int j=-1;
		int minCost;
		//����������������㶼����U
		for(int i=1;i<G.n;i++) {
			//��λ��һ��û�м���U�еĶ�����Ϊ��СȨ�أ���Ϊ���²���   ����̨������СȨ�أ�
			for(j=0;j<G.n;j++) {
				if(closedge[j].lowcost!=0 && closedge[j].lowcost!=Infinity) {
					k = j;
					break;
				}
			}
			minCost = closedge[k].lowcost;
			//��V-U���ҵ���СȨ�ر߼��뵽U��	
			for(j=0;j<G.n;j++) {
				if(minCost>closedge[j].lowcost && closedge[j].lowcost!=0) {
					minCost = closedge[j].lowcost;
					k = j;//Ȩ����С�ıߵ��±�
				}
			}

			System.out.println(String.format("ȡ��(V%s,%s)�߳�:%s",closedge[k].adjvex,G.vexs[k].elm,minCost));//����¼��뵽���еı�
			closedge[k].lowcost = 0;//������뵽U��
			for(j=0;j<G.n;j++) {
				if(G.edges[k][j]<closedge[j].lowcost && closedge[j].lowcost!=0 && G.edges[k][j]!=0)//G.edges[k][j]!=0��Ϊ�ڴ�������Ӿ����ж����붥��֮���޷����ɱߵ�Ĭ��Ϊ0
				{
					closedge[j].adjvex = k;//ͬ��closedge[j].adjvex = Integer.parseInt(String.valueOf(G.vexs[k].elm));
					closedge[j].lowcost= G.edges[k][j];
					
				}
			}
		}
	}
	
	//Dijkstra�㷨�����ĳԴ����������е�����·�� G����Ӿ��� v��Դ������
	static void Dijkstra(MGraph G,int v) {
		int Infinity = 999;//���������
		int [] dist = new int[G.n];
		int [] s = new int[G.n];//����S���㼯��
		int [] path = new int[G.n];
		int i;
		for(i=0;i<G.n;i++) 
			for(int m=0;m<G.n;m++)
				if(G.edges[i][m]==0 && i!=m)
					G.edges[i][m] = Infinity;
		for(i=0;i<G.n;i++) {
			dist[i] = G.edges[v][i];//Դ�㵽��������ľ��룬��û�����������
			s[i]=0;//s�����ʼ����0��ʾδ�ҵ����·����1��ʾ����s��
			if(G.edges[v][i]<Infinity)
				path[i] = v;//·����ʼ�����洢v0��vi�����·����vi��ǰһ������ı��
			else
				path[i] = -1;
		}
		s[v] = 1;//��Դ��v����S������
		int mindist;
		int k = v;//��С����Ķ����±�
		for(i=0;i<G.n;i++) {
			mindist = Infinity;
			for(int j=0;j<G.n;j++) {//��V-S��ѡȡ������С����Ķ���Vk
				if(mindist>dist[j] && s[j]==0) {
					k = j;
					mindist = dist[j];
				}
			}
			s[k] = 1;//������k����S��
			for(int j=0;j<G.n;j++) {//�޸�V-S�ж���ľ���dist[j]
				if(G.edges[k][j]<Infinity && s[j]==0 && dist[k]+G.edges[k][j]<dist[j]) {
					dist[j] = dist[k]+G.edges[k][j];
					path[j] = k;
				}
			}
		}
		Graph.Dispath(dist,path,s,G.n,v);
	}
	static void Dispath(int[] dist,int[] path,int[] s,int n,int v) {
		for(int i=0;i<n;i++) {
			if(s[i]==1) {
				int k = i;
				System.out.print(String.format("V%s��V%s�����·��Ϊ��", v,i));
				while(k!=v) {
					System.out.print(String.format("V%s<--", k));
					k = path[k];
				}
				System.out.print(String.format("V%s,·������Ϊ��%s\n", v,dist[i]));
			}
			else 
				System.out.print(String.format("V%s<--V%s������·��\n", i,v));
		}
	}
	
	//��������AOV
	static void TopSort(ALGraph G) {
		int [] indegree = new int[G.n];
		for(int i=0;i<G.n;i++) {
			int count = 0;
			for(int j=0;j<G.n;j++) {
				if(i!=j) {
					EdgeNode p = G.adjlist[j].firstedge;
					while(p!=null) {
						if(p.adjvex==i)
							++count;
						p=p.next;
					}
				}
			}
			indegree[i] = count;
		}

		//��ʼ��һ��ջ
		LinkedStack<Integer> stk = new LinkedStack<Integer>();
		//���Ϊ��Ķ�����ջ���Ҽ�¼���±�
		for(int i=0;i<indegree.length;i++)
			if(indegree[i]==0)
				stk.Push(i);
		//��ջ
		while(!stk.IsEmpty()) {
			int i = stk.GetTopEle();
			stk.Pop();
			System.out.print("->"+G.adjlist[i].vertex.elm);
			EdgeNode p = G.adjlist[i].firstedge;
			while(p!=null) {
				int j = p.adjvex;
				if((--indegree[j])==0)
					stk.Push(j);
				p=p.next;
			}
		}
	}
	//�ؼ�·��AOE
	static void CriticalPath(ALGraph G) {
		int[] Ve = new int[G.n];
		//��������������ȡ�������������ķ���ʱ��Ve
		for(int i=0;i<G.n;i++) {
			EdgeNode p = G.adjlist[i].firstedge;
			while(p!=null) {
				int k = p.adjvex;//��һ��Ŀ�Ķ���
				if(Ve[i]+p.info > Ve[k])
					Ve[k] = Ve[i]+p.info;
				p = p.next;
			}
		}
		int[] Vl = new int[G.n];
		for(int i=0;i<G.n;i++) 
			Vl[i] = Ve[G.n-1];//��ʼ��ȫ����ٷ���ʱ�䣬�����������緢��ʱ��
		//������ʱ��Vl
		for(int i=G.n-2;i>=0;i--) {
			EdgeNode p = G.adjlist[i].firstedge;
			while(p!=null) {
				int k = p.adjvex;
				if(Vl[k]-p.info < Vl[i])
					Vl[i] = Vl[k]-p.info;
				p = p.next;
			}
		}
		//���ݸ��������Ve��Vl�����ÿ�����ϻ�Ŀ�ʼʱ��Ae����ٿ�ʼʱ��Al
		for(int i=0;i<G.n;i++) {
			EdgeNode p = G.adjlist[i].firstedge;
			int Ae = Ve[i];
			while(p!=null) {
				int k = p.adjvex;
				int Al = Vl[k]-p.info;
				if(Ae == Al)
					System.out.println(String.format("(%s,%s),Ae=%s,Al=%s", G.adjlist[i].vertex.elm,G.adjlist[k].vertex.elm,Ae,Al));
				p = p.next;
			}
		}
	}
	public static void main(String[] args) {
/**
 * ��֤��һ�ִ�������
 * ���£�
�����붥����Ϣ��
A B
������ÿ����Ŷ�Ӧ�������������ţ�(�����ʽ(i,j)��(i,j,weight))
(1,2)
0	1	
1	0	
 */
//		MGraph G = Graph.CreateMGraph(2, 1, false);
//		for(int i=0;i<G.n;i++) {
//			for(int j=0;j<G.n;j++) 
//				System.out.print(G.edges[i][j]+"\t");
//			System.out.println();
//		}

/**
 * ����ڶ��ִ�������
 * ���£� 
�����붥����Ϣ��
v1 v2 v3 v4
������ÿ����Ŷ�Ӧ�������������ţ�(�����ʽ(Vi,Vj)��(Vi,Vj,weight))
(1,2)
(2,1)
(2,4)
(3,1)
(3,4)
v1--->	v2	
v2--->	v4	v1	
v3--->	v4	v1	
v4--->
 */
//		ALGraph G = Graph.CreateALGraph(4,5);
//		for(int i=0;i<G.n;i++) {
//			System.out.print(G.adjlist[i].vertex.elm+"--->\t");
//			EdgeNode temp = G.adjlist[i].firstedge;
//			while(temp!=null) {
//				int index = temp.adjvex;
//				System.out.print(G.adjlist[index].vertex.elm+"\t");
//				temp = temp.next;
//			}
//			System.out.println();
//		}
		
//		�����������������������ʱ�紴����������������
//		ALGraph Ag = Graph.CreateALGraph(4, 5);
//		new Graph().DFSTraverseALL(Ag);

//		��֤��³˹�����㷨����С������
//		KEdge [] E = new KEdge[12];
//		E[0] = new KEdge(0,1,2);
//		E[1] = new KEdge(1,4,10);
//		E[2] = new KEdge(4,6,6);
//		E[3] = new KEdge(6,5,1);
//		E[4] = new KEdge(5,2,5);
//		E[5] = new KEdge(2,0,4);
//		E[6] = new KEdge(0,3,1);
//		E[7] = new KEdge(1,3,3);
//		E[8] = new KEdge(4,3,7);
//		E[9] = new KEdge(6,3,4);
//		E[10] = new KEdge(5,3,8);
//		E[11] = new KEdge(2,3,2);
//		//�Աߵ�Ȩ�أ���С��������
//        for(int i =0;i<E.length-1;i++) {
//            for(int j=0;j<E.length-i-1;j++) {//-1Ϊ�˷�ֹ���
//                if(E[j].weight>E[j+1].weight) {
//                    KEdge temp = E[j];
//                    E[j]=E[j+1];
//                    E[j+1]=temp;
//                }
//            }    
//        }
//		Graph.Kruskal(E, 7, 12);
		
//		��֤����ķ�㷨
/**
 * �����������룺
�����붥����Ϣ��
V0 V1 V2 V3 V4 V5 V6
������ÿ����Ŷ�Ӧ�������������ţ�(�����ʽ(i,j)��(i,j,weight))
(1,2,2)
(1,3,4)
(1,4,1)
(2,4,3)
(2,5,10)
(3,4,2)
(3,6,5)
(4,5,7)
(4,6,8)
(4,7,4)
(5,7,6)
(6,7,1)
ȡ��(V0,V3)�߳�:1
ȡ��(V0,V1)�߳�:2
ȡ��(V3,V2)�߳�:2
ȡ��(V3,V6)�߳�:4
ȡ��(V6,V5)�߳�:1
ȡ��(V6,V4)�߳�:6
*/
//		MGraph G = Graph.CreateMGraph(7, 12, false);
//		Graph.Prim(G, 0);//����G����С����������V0��ʼ
		
//		���Եϼ�˹�����㷨
/**
 �����붥����Ϣ��
V0 v1 V2 V3 V4
������ÿ����Ŷ�Ӧ�������������ţ�(�����ʽ(i,j)��(i,j,weight))
(1,2,3)
(1,5,30)
(2,3,25)
(2,4,8)
(3,5,10)
(4,3,4)
(4,1,20)
(4,5,12)
(5,1,5)
 */
//		MGraph G = Graph.CreateMGraph(5, 9, true);
//		Graph.Dijkstra(G, 0);
		
//		������������
/**
v1 V2 V3 V4 V5 V6 V7
(1,3)
(2,4)
(2,5)
(2,7)
(3,4)
(4,5)
(4,6)
(7,6)
�����
->V2->V7->v1->V3->V4->V5->V6
 */
//		ALGraph G = Graph.CreateALGraph(7, 8);
//		Graph.TopSort(G);
		
//		��֤AOE
		ALGraph G = Graph.CreateALGraph(9, 11);
		Graph.CriticalPath(G);
		/*
V0 V1 V2 V3 V4 V5 V6 V7 V8
(1,2,6)
(1,3,4)
(1,4,5)
(2,5,1)
(3,5,1)
(4,6,2)
(5,7,9)
(5,8,7)
(6,8,4)
(7,9,2)
(8,9,4)
		*/
	}

}
