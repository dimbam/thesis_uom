class BellLabsException extends RuntimeException
{
	String msg;
	BellLabsException(String msg)
	{
		super(msg);
		this.msg=msg;
	}
	public String toString()//override
	{
		return "it is Testing";
	}
}
class Calc
{
	static int add(int a,int b)
	{
		if(a<0||b<0)
		{
			BellLabsException e=new BellLabsException("Negative value is Invalid");
			throw e;
		}
		int c=a+b;
		return c;
	}
}
class Add_toString_unCheckedUserDefinedCustomException
{
	public static void main(String arg[])
	{
		try
		{
			int a=Calc.add(10,5);
			System.out.println("a:"+a);
			int b=Calc.add(100,50);
			System.out.println("b:"+b);
			int c=Calc.add(1000,-500);
			System.out.println("c:"+c);
			int d=Calc.add(2000,1500);
			System.out.println("d:"+d);
		}
		catch(BellLabsException e)
		{
			System.out.println(e);
		}
		System.out.println("Good Bye");
	}
}