package xyz.ctrltab.datastructure.stack;

public class Test {

	private static void build() {
		
		return;
	}
	public static void main(String[] args) {
		/**
		 * �沨��ʽ 
		 * a*(b+c)-d/e	=>	abc+*de/-
		 */
		
		//������ʽ
		String eg = "a*(b+c)-d/e#";
		char [] chs = new char[eg.length()];
		for(int i=0; i<eg.length() ;i++)
			chs[i] = eg.charAt(i);
		//��ȡ����һ����������������ջ����ջ�����ǽ��޷�������
		int i=0;//�ַ�����ָ��
		int k=0;//ջָ��
		while(i<eg.length()) {
			if((chs[i]>='A'&&chs[i]<='Z')||(chs[i]>='a'&&chs[i]<='z'))
				break;
			else {
				i++;
				continue;
			}
		}
		char [] sk = new char[eg.length()];
		sk[k] = chs[i];
		//��ȡ��һ���ַ�
		char next = chs[++i];
		//a* ^ b�����
		if(next=='*'||next=='/') {
			if((chs[i+1]>='A'&&chs[i+1]<='Z')||(chs[i+1]>='a'&&chs[i+1]<='z')) {
				if(chs[i+2]=='#'||chs[i+2]==')'||(chs[i+2]>='A'&&chs[i+2]<='Z')||(chs[i+2]>='a'&&chs[i+2]<='z')) {
					//�û�λ��
					sk[++k] = chs[i+1];
					sk[++k] = chs[i];
					i++;//ָ��ָ���¸�λ��
					return;
				}
				else {
					System.out.println("error");
					return;
				}
			}
			if(chs[i+1]=='(');
				//������һ���¿�ʼ Test.build();
		}
		//a- ^ b*c����� ��Ҫ�۲�b��ߵķ������
		if(next=='+'||next=='-') {
			if((chs[i+1]>='A'&&chs[i+1]<='Z')||(chs[i+1]>='a'&&chs[i+1]<='z')) {
				if(chs[i+2]=='#'||chs[i+2]==')'||chs[i+2]=='+'||chs[i+2]=='-') {
					//�û�λ��
					sk[++k] = chs[i+1];
					sk[++k] = chs[i];
					i++;//ָ��ָ���¸�λ��
					return;
				}
			}
			if(chs[i+1]=='(');
				//������һ���¿�ʼ Test.build();
		}
	}

}
