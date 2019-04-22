package xyz.ctrltab.datastructure.graph;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xyz.ctrltab.datastructure.queue.LinkedQueue;
import xyz.ctrltab.datastructure.stack.LinkedStack;


/**
 * @author JeffLiu
 * @描述 图是一种不计结点前驱和后驱的非线性结构
 * @date 2019/3/15
 */
/**
 * 领接矩阵的类集合
 */
class VertexType {
	Object elm;
	public VertexType(Object input) {
		this.elm = input;
	}
}
class MGraph {	
	VertexType [] vexs;//顶点表V
	int [][] edges;//领接矩阵E，即边表
	int n,e;//顶点数和边数
	
	public MGraph(int n,int e){
		this.vexs = new VertexType[n];
		this.edges = new int [n][n];
		this.n = n;//创建时输入顶点数
		this.e = e;//创建时输入边数
	}
}
/**
 * 领接表的类集合
 */
class EdgeNode {
	int adjvex;
	EdgeNode next;
	int info;//权重
	public EdgeNode(int adjvex,int info) {
		this.adjvex = adjvex;
		this.info = info;
	}
}
class VertexNode {
	VertexType vertex;//顶点对象
	EdgeNode firstedge;//第一个边的指针域
	public VertexNode(Object in) {
		this.vertex = new VertexType(in);
		this.firstedge = null;
	}
}
class ALGraph {
	VertexNode [] adjlist;//顶点数组
	int n,e;//顶点数和边数
	public ALGraph(int n,int e){
		this.adjlist = new VertexNode[n];
		this.n = n;
		this.e = e;
	}
}
/**
 * Kruskal算法中，辅助数组E，顺序存放图的边 
 **/
class KEdge {
	int vex1;//边的起始顶点(下标)
	int vex2;//边的终止顶点(下标)
	int weight;//边的权重
	public KEdge(int v1,int v2,int w) {
		this.vex1 = v1;
		this.vex2 = v2;
		this.weight = w;
	}
}
/**
 * Prim算法中，记录U到V-U具有最小权重的数组 ，此处察觉到类可以放在方法中
 **/
class Closedge{
	int adjvex;//依附在U中的顶点
	int lowcost;//该边权
	public Closedge(int a,int lwt) {
		this.adjvex = a;
		this.lowcost = lwt;
	}
}
public class Graph {

	boolean [] visited;//结点访问标记向量
	//图类的类属方法，创建领接矩阵	 n:顶点数		e:边数
	static MGraph CreateMGraph(int n,int e,boolean isDirect) {
		MGraph G = new MGraph(n,e);
		Scanner read = new Scanner(System.in);
		System.out.println("请输入顶点信息：");
		for(int i=0;i<G.n;i++)//输入顶点信息，建立顶点表
			G.vexs[i] = new VertexType(read.next());
		System.out.println("请输入每个序号对应的两个顶点的序号：(输入格式(i,j)或(i,j,weight))");
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
	//图类的类属方法，创建领接表	 n:顶点数		e:边数
	static ALGraph CreateALGraph(int n,int e) {
		ALGraph G = new ALGraph(n,e);
		Scanner read = new Scanner(System.in);
		System.out.println("请输入顶点信息：");
		for(int i=0;i<G.n;i++)//输入顶点信息，建立顶点表
			G.adjlist[i] = new VertexNode(read.next());
		System.out.println("请输入每个序号对应的两个顶点的序号：(输入格式(Vi,Vj)或(Vi,Vj,weight))");
		int weight = 1;
		for(int i=0;i<G.e;i++) {
			String tp = read.next();
		    Pattern p = Pattern.compile("([\\d]+)");
		    Matcher m = p.matcher(tp);
		    int j = 0,vi = 0,vj = 0;//<vi,vj>是弧尾下标和弧头下标
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
	//深度搜索查找遍历ALGraph
	void DFSTraverseALL(ALGraph G) {
		this.visited = new boolean[G.n];//标记向量初始化，全为false
		for(int i=0;i<G.n;i++)
			if(!this.visited[i])
				this.DFSAL(G,i);
	}
	void DFSAL(ALGraph G,int i) {
		//访问节点
		System.out.print("访问结点："+G.adjlist[i].vertex.elm+"\n");
		//标记该结点已被访问
		this.visited[i] = true;
		//取vi边表的头指针
		EdgeNode p = G.adjlist[i].firstedge;
		while(p!=null) {
			if(!this.visited[p.adjvex])
				this.DFSAL(G, p.adjvex);//该领接点未曾访问过
			p = p.next;//找vi的下一个领接点
		}
	}
	//广度搜索查找遍历
	void BFSTraverseALL(MGraph G) {
		this.visited = new boolean[G.n];
		for(int i=0;i<G.n;i++)
			if(!this.visited[i])
				this.BFSM(G,i);
	}
	void BFSM(MGraph G,int i) {
		LinkedQueue <Integer> Q = new LinkedQueue<Integer>();
		//访问节点
		System.out.print("访问结点："+G.vexs[i].elm+"\n");
		//标记该结点已被访问
		this.visited[i] = true;
		Q.Enter(i);//顶点下标进队列
		while(!Q.IsEmpty()) {
			int r = Q.GetFrontData();
			Q.Delete();//出队列
			for(int j=0;j<G.n;j++) 
				if(G.edges[r][j]>0 && !this.visited[j]) {//若vj未访问
					System.out.print("访问结点："+G.vexs[j].elm+"\n");
					this.visited[j] = true;
					Q.Enter(j);//访问过的vj入队列
				}
		}
	}
	
	//克鲁斯卡尔算法（Kruskal）求最小生成树，辅助数组E， 顶点数n，边数e
	static void Kruskal(KEdge [] E,int n,int e) {
		int [] vset = new int[n];//用来记录顶点是否属于同集合的辅助数组
		for(int i=0;i<n;i++)//初始化辅助数组
			vset[i] = i;
		int k = 0;//记录当前构造最小生成树的第k条边，初始值为0
		int m1,m2;
		int sn1,sn2;
		for(int j=0;j<e;j++) {
			m1 = E[j].vex1;
			m2 = E[j].vex2;
			sn1 = vset[m1];
			sn2 = vset[m2];
			if(sn1!=sn2)
			{
				System.out.println(String.format("选取边(V%s,V%s)，其值为:%s",m1,m2,E[j].weight));
				k++;
				if(k == n-1)
					break;
				for(int i=0;i<n;i++)
					if(vset[i]==sn1)
						vset[i] = sn2;
			}
		}
	}
	//普里姆算法（Prim）求最小生成树，v是第一个进入集合U中的顶点的序号
	static void Prim(MGraph G,int v) {
		int Infinity = 999;//无穷大
		Closedge [] closedge = new Closedge[G.n];
		closedge[v] = new Closedge(v,0);
		//初始化closedge数组
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
		//将除起点外其他顶点都送入U
		for(int i=1;i<G.n;i++) {
			//定位第一个没有加入U中的顶点作为最小权重（作为更新参照   打擂台法得最小权重）
			for(j=0;j<G.n;j++) {
				if(closedge[j].lowcost!=0 && closedge[j].lowcost!=Infinity) {
					k = j;
					break;
				}
			}
			minCost = closedge[k].lowcost;
			//在V-U中找到最小权重边加入到U中	
			for(j=0;j<G.n;j++) {
				if(minCost>closedge[j].lowcost && closedge[j].lowcost!=0) {
					minCost = closedge[j].lowcost;
					k = j;//权重最小的边的下标
				}
			}

			System.out.println(String.format("取边(V%s,%s)边长:%s",closedge[k].adjvex,G.vexs[k].elm,minCost));//输出新加入到树中的边
			closedge[k].lowcost = 0;//顶点加入到U中
			for(j=0;j<G.n;j++) {
				if(G.edges[k][j]<closedge[j].lowcost && closedge[j].lowcost!=0 && G.edges[k][j]!=0)//G.edges[k][j]!=0因为在创建的领接矩阵中顶点与顶点之间无法构成边的默认为0
				{
					closedge[j].adjvex = k;//同：closedge[j].adjvex = Integer.parseInt(String.valueOf(G.vexs[k].elm));
					closedge[j].lowcost= G.edges[k][j];
					
				}
			}
		}
	}
	
	//Dijkstra算法，求从某源点出发到所有点的最短路径 G是领接矩阵 v是源点的序号
	static void Dijkstra(MGraph G,int v) {
		int Infinity = 999;//定义无穷大
		int [] dist = new int[G.n];
		int [] s = new int[G.n];//代表S顶点集合
		int [] path = new int[G.n];
		int i;
		for(i=0;i<G.n;i++) 
			for(int m=0;m<G.n;m++)
				if(G.edges[i][m]==0 && i!=m)
					G.edges[i][m] = Infinity;
		for(i=0;i<G.n;i++) {
			dist[i] = G.edges[v][i];//源点到各个顶点的距离，若没有则是无穷大
			s[i]=0;//s数组初始化，0表示未找到最短路径，1表示放入s中
			if(G.edges[v][i]<Infinity)
				path[i] = v;//路径初始化，存储v0到vi的最短路径上vi的前一个顶点的编号
			else
				path[i] = -1;
		}
		s[v] = 1;//把源点v放入S集合中
		int mindist;
		int k = v;//最小距离的顶点下标
		for(i=0;i<G.n;i++) {
			mindist = Infinity;
			for(int j=0;j<G.n;j++) {//从V-S中选取具有最小距离的顶点Vk
				if(mindist>dist[j] && s[j]==0) {
					k = j;
					mindist = dist[j];
				}
			}
			s[k] = 1;//将顶点k加入S中
			for(int j=0;j<G.n;j++) {//修改V-S中顶点的距离dist[j]
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
				System.out.print(String.format("V%s到V%s的最短路径为：", v,i));
				while(k!=v) {
					System.out.print(String.format("V%s<--", k));
					k = path[k];
				}
				System.out.print(String.format("V%s,路径长度为：%s\n", v,dist[i]));
			}
			else 
				System.out.print(String.format("V%s<--V%s不存在路径\n", i,v));
		}
	}
	
	//拓扑排序AOV
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

		//初始化一个栈
		LinkedStack<Integer> stk = new LinkedStack<Integer>();
		//入度为零的顶点入栈，且记录其下标
		for(int i=0;i<indegree.length;i++)
			if(indegree[i]==0)
				stk.Push(i);
		//出栈
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
	//关键路径AOE
	static void CriticalPath(ALGraph G) {
		int[] Ve = new int[G.n];
		//按照拓扑排序求取各个顶点的最早的发生时间Ve
		for(int i=0;i<G.n;i++) {
			EdgeNode p = G.adjlist[i].firstedge;
			while(p!=null) {
				int k = p.adjvex;//下一个目的顶点
				if(Ve[i]+p.info > Ve[k])
					Ve[k] = Ve[i]+p.info;
				p = p.next;
			}
		}
		int[] Vl = new int[G.n];
		for(int i=0;i<G.n;i++) 
			Vl[i] = Ve[G.n-1];//初始化全部最迟发生时间，赋予最后的最早发生时间
		//最晚发生时间Vl
		for(int i=G.n-2;i>=0;i--) {
			EdgeNode p = G.adjlist[i].firstedge;
			while(p!=null) {
				int k = p.adjvex;
				if(Vl[k]-p.info < Vl[i])
					Vl[i] = Vl[k]-p.info;
				p = p.next;
			}
		}
		//根据各个顶点的Ve和Vl，求出每条边上活动的开始时间Ae和最迟开始时间Al
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
 * 验证第一种创建方法
 * 如下：
请输入顶点信息：
A B
请输入每个序号对应的两个顶点的序号：(输入格式(i,j)或(i,j,weight))
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
 * 检验第二种创建方法
 * 如下： 
请输入顶点信息：
v1 v2 v3 v4
请输入每个序号对应的两个顶点的序号：(输入格式(Vi,Vj)或(Vi,Vj,weight))
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
		
//		深度优先搜索法遍历，检验时如创建方法二进行输入
//		ALGraph Ag = Graph.CreateALGraph(4, 5);
//		new Graph().DFSTraverseALL(Ag);

//		验证卡鲁斯卡尔算法求最小生成树
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
//		//以边的权重，从小到大排序
//        for(int i =0;i<E.length-1;i++) {
//            for(int j=0;j<E.length-i-1;j++) {//-1为了防止溢出
//                if(E[j].weight>E[j+1].weight) {
//                    KEdge temp = E[j];
//                    E[j]=E[j+1];
//                    E[j+1]=temp;
//                }
//            }    
//        }
//		Graph.Kruskal(E, 7, 12);
		
//		验证普里姆算法
/**
 * 按照如下输入：
请输入顶点信息：
V0 V1 V2 V3 V4 V5 V6
请输入每个序号对应的两个顶点的序号：(输入格式(i,j)或(i,j,weight))
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
取边(V0,V3)边长:1
取边(V0,V1)边长:2
取边(V3,V2)边长:2
取边(V3,V6)边长:4
取边(V6,V5)边长:1
取边(V6,V4)边长:6
*/
//		MGraph G = Graph.CreateMGraph(7, 12, false);
//		Graph.Prim(G, 0);//对网G求最小生成树，从V0开始
		
//		测试迪吉斯卡尔算法
/**
 请输入顶点信息：
V0 v1 V2 V3 V4
请输入每个序号对应的两个顶点的序号：(输入格式(i,j)或(i,j,weight))
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
		
//		测试拓扑排序
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
结果：
->V2->V7->v1->V3->V4->V5->V6
 */
//		ALGraph G = Graph.CreateALGraph(7, 8);
//		Graph.TopSort(G);
		
//		验证AOE
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
