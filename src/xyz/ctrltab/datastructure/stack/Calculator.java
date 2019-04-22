package xyz.ctrltab.datastructure.stack;
/**
 * 
 * @author JeffLiu
 * @描述 将逆波兰式进行运算，是栈的实际运用。与之前的 转逆波兰式类 结合
 * @date 2019/2/12
 *
 */
public class Calculator {
	//四则运算操作
	private float arithmeticalOperate(float op1,float op2,String operator) {
		float res = 0.0f;
		switch(operator) {
			case "+":
				res = op1+op2;break;
			case "-":
				res = op1-op2;break;
			case "*":
				res = op1*op2;break;
			case "/":
				res = op1/op2;break;
		}
		return res;
	}
	//计算 后缀表达式的值
	float doCalculate(String eg) throws Exception {
		LinkedStack <String> stk = new LinkedStack <String> ();
		ToPostfixExpression pfe = new ToPostfixExpression();
		char[] pfeArr = pfe.postfixExpression(eg);
		for(char t: pfeArr) {
			if(t=='+'||t=='-'||t=='*'||t=='/') {
				String op2 = stk.GetTopEle();
				stk.Pop();
				String op1 = stk.GetTopEle();
				stk.Pop();
				float res= this.arithmeticalOperate(Float.parseFloat(op1),Float.parseFloat(op2),Character.toString(t));
				stk.Push(Float.toString(res));
			}
			else {
				stk.Push(Character.toString(t));
			}
		}
		
		float res = Float.parseFloat(stk.GetTopEle());
		stk = null;
		pfe = null;
		return res;
	}
	
	public static void main(String[] args) {
		Calculator one = new Calculator();
		try {
			System.out.println("计算结果："+one.doCalculate("1*(1+1)-1/1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
