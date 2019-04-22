package xyz.ctrltab.datastructure.array;

/**
 * 
 * @author JeffLiu
 * @描述 利用 #数组# 和 #三元组表# 将稀疏矩阵转置
 * @注 没有实现矩阵实现能装下数字和字母 不是泛类 可以实现将Triple类的v 改为 char
 * @data 2019/2/24
 */
class Triple{
	int i,j;
	int v;
	public Triple(int i,int j,int v) {
		this.i = i;
		this.j = j;
		this.v = v;
	}
}
public class TSMatrix {
	
	int MAXSIZE = 100;
	Triple [] data;
	int mu,nu,tu;//矩阵 行、列、非零元素的个数
	
	public TSMatrix(Triple [] data,int mu,int nu,int tu){
		this.data = data;
		this.mu = mu;
		this.nu = nu;
		this.tu = tu;
	}
	//转置
	public TSMatrix DoTransM(){
		int i,j,k;
		int [] num = new int[this.nu+1];
		int [] cpot = new int[this.nu+1];
		TSMatrix B = new TSMatrix (new Triple[this.data.length], this.nu, this.mu, this.tu);
		if(B.tu>0) {
			for(i=1;i<=this.nu;i++)
				num[i]=0;
			for(i=1;i<=this.tu;i++) {
				j = this.data[i-1].j;
				num[j]++;
			}
			cpot[1]=1;
			for(i=2;i<=this.nu;i++)
				cpot[i] = cpot[i-1]+num[i-1];
			for(i=0;i<this.tu;i++){
				j = this.data[i].j;
				k = cpot[j]-1;
				B.data[k] = new Triple(this.data[i].j,this.data[i].i,this.data[i].v);
				cpot[j]++;
			}
		}
		return B;
	}
	//打印
	public void DisPlay() {
		for(int i=0;i<this.tu;i++) {
			System.out.println(this.data[i].i+"\t"+this.data[i].j+"\t"+this.data[i].v);
		}
	}
	public static void main(String[] args) {
		Triple [] ts = {new Triple(1,3,16),new Triple(2,2,18),new Triple(3,4,6),new Triple(3,6,-15),new Triple(4,5,12),new Triple(5,1,97)};
		TSMatrix C = new TSMatrix (ts,6,6,6);
		C.DisPlay();
		System.out.println("------");
		C.DoTransM().DisPlay();
	}

}
