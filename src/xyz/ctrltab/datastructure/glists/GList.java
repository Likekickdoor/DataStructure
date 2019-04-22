package xyz.ctrltab.datastructure.glists;
/**
 * @author JeffLiu
 * @描述 广义表是一种线性表的推广，放松了对表元素的原子限制，且是递归的表（即广义表也可以是其自身的子表） 
 * @参考 https://blog.csdn.net/haiboself/article/details/51501431
 * @date 2019/2/27
 */
public class GList {
	public enum ElemTag {
		ATOM,
		LIST
	}
	ElemTag tag;	//结点标志
	Object atom;	//tag = 0 原子结点
	GList hp,tp;	//tag = 1 表结点
	
	public static GList CreatGList(GList L,String s) {
		
		if(s.equals("()"))	//创建空表
			return null;
		else {
			//if(L==null)
				//System.exit(0);//申请空间失败，退出，此处应该抛出异常
			if(s.length()==1) {
				L.tag = GList.ElemTag.ATOM;
				L.atom = s.charAt(0);
			}
			else {
				L.tag = GList.ElemTag.LIST;
				GList p = L,q;
				String sub = s.substring(1,s.length()-1);	//脱去外层括号
				/*重复建立子表*/
				do {
					SString temp = new SString(sub);
					String hsub = GList.sever(temp);	//将sub以‘ , ’切割，前部分作为hsub表头，后部分作为sub即表尾
					sub = temp.str;
					p.hp = GList.CreatGList(new GList(),hsub);	//头部分的子表
					q = p;
					if(!sub.equals("")) {
						p = new GList();
						//if(p==null)	  //空间申请失败
							//System.exit(0);
						p.tag = GList.ElemTag.LIST;
						q.tp = p;
					}
				}while(!sub.equals(""));
				q.tp = null;
			}
		}
		return L;
	}
	
	public static String sever(SString s) {
		String str = s.str;
		char tmp;
		int i=0,k=0;
		int n = str.length();
		String hsub = null;
		
		do {
			tmp = str.charAt(i);
			if(tmp=='(')
				k++;
			if(tmp==')')
				k--;
			i++;
		}while(i<n && (tmp!=','||k!=0));
		
		if(i<n) {
			hsub = str.substring(0, i-1);
			str = str.substring(i);
		}else {
			hsub = str;
			str = "";
		}
		s.str = str;
		return hsub;
	}
	
	public static void Traverse_GL(GList L) {
		if(L!=null) {
			if(L.tag==GList.ElemTag.ATOM)
				System.out.print(L.atom+"\t");
			else {
				GList.Traverse_GL(L.hp);
				GList.Traverse_GL(L.tp);
			}
		}
	}
	
	public GList Copy() {
		GList T=null;
		if(this!=null){
			T = new GList();
			T.tag = this.tag;
			if(this.tag == GList.ElemTag.ATOM)
				T.atom = this.atom;
			else {
				//复制L.hp的一个副本到T.hp
				T.hp=this.hp.Copy();
				//复制L.tp的一个副本到T.tp
				T.tp=this.tp.Copy();
			}
		}
		return T;
	}
	
	static int Depth(GList L) {
		//空表
		if(L==null)
			return 1;
		//原子深度为0
		if(L.tag == GList.ElemTag.ATOM)
			return 0;
		int max = 0,dep;
		GList p = L;
		do {
			dep = GList.Depth(p.hp);
			p = p.tp;
			if(dep>max)
				max = dep;
		}while(p!=null);
		
		return max+1;
	}
	
	GList GetHead() {
		GList p,h;
		p = this.tp;
		this.tp = null;
		h = p.Copy();
		this.tp = p;
		return h;
	}
	
	GList GetTail() {
		return this.tp.Copy();
	}
	public static void main(String[] args) {
//		SString sub = new SString("e,(f,g),h");
//		GList.sever(sub);
//		System.out.println(sub.str);
		GList gl = GList.CreatGList(new GList(), "((a,(c)),b)");
		GList.Traverse_GL(gl);
		System.out.println("深度："+GList.Depth(gl));
	}

}
//Java String 是基本类型，传递时是值传递，在实现切割字符串时，需要引用型，因此需要对象实现。
class SString{
    String str = "";
    public SString(String s){
        this.str = s;
    }
}