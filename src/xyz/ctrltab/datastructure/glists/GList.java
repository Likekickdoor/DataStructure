package xyz.ctrltab.datastructure.glists;
/**
 * @author JeffLiu
 * @���� �������һ�����Ա���ƹ㣬�����˶Ա�Ԫ�ص�ԭ�����ƣ����ǵݹ�ı��������Ҳ��������������ӱ� 
 * @�ο� https://blog.csdn.net/haiboself/article/details/51501431
 * @date 2019/2/27
 */
public class GList {
	public enum ElemTag {
		ATOM,
		LIST
	}
	ElemTag tag;	//����־
	Object atom;	//tag = 0 ԭ�ӽ��
	GList hp,tp;	//tag = 1 ����
	
	public static GList CreatGList(GList L,String s) {
		
		if(s.equals("()"))	//�����ձ�
			return null;
		else {
			//if(L==null)
				//System.exit(0);//����ռ�ʧ�ܣ��˳����˴�Ӧ���׳��쳣
			if(s.length()==1) {
				L.tag = GList.ElemTag.ATOM;
				L.atom = s.charAt(0);
			}
			else {
				L.tag = GList.ElemTag.LIST;
				GList p = L,q;
				String sub = s.substring(1,s.length()-1);	//��ȥ�������
				/*�ظ������ӱ�*/
				do {
					SString temp = new SString(sub);
					String hsub = GList.sever(temp);	//��sub�ԡ� , ���иǰ������Ϊhsub��ͷ���󲿷���Ϊsub����β
					sub = temp.str;
					p.hp = GList.CreatGList(new GList(),hsub);	//ͷ���ֵ��ӱ�
					q = p;
					if(!sub.equals("")) {
						p = new GList();
						//if(p==null)	  //�ռ�����ʧ��
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
				//����L.hp��һ��������T.hp
				T.hp=this.hp.Copy();
				//����L.tp��һ��������T.tp
				T.tp=this.tp.Copy();
			}
		}
		return T;
	}
	
	static int Depth(GList L) {
		//�ձ�
		if(L==null)
			return 1;
		//ԭ�����Ϊ0
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
		System.out.println("��ȣ�"+GList.Depth(gl));
	}

}
//Java String �ǻ������ͣ�����ʱ��ֵ���ݣ���ʵ���и��ַ���ʱ����Ҫ�����ͣ������Ҫ����ʵ�֡�
class SString{
    String str = "";
    public SString(String s){
        this.str = s;
    }
}