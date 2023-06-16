class BellLabsException extends RuntimeException
{
	BellLabsException(String msg)
	{
		super(msg);
	}
}
class Calc
{
	static int add(int a,int b)
	throws BellLabsException
	{
			if(a<0 || b<0)
			{
				BellLabsException e=new BellLabsException("Negative value is Invalid");
				throw e;
			}
			int c=a+b;
			return c;
	}
}
class Add_unCheckedUserDefinedCustomException
{
public static void main(String arg[])
{
		int a=Calc.add(10,5);
		System.out.println("a:"+a);
		int b=Calc.add(100,500);
		System.out.println("b:"+b);
		int c=Calc.add(1000,-500);
		System.out.println("c:"+c);
		int d=Calc.add(2000,1500);
		System.out.println("d:"+d);
}
}