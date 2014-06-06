package com.yee.webstudy.concurrency;

public class WaitNotifyTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Function function = new Function();

		Thread t1 = new Thread(new MyTestRunnable1(function), "t1");
		Thread t2 = new Thread(new MyTestRunnable2(function), "t2");

		t1.start();
		t2.start();
	}

}

class MyTestRunnable1 implements Runnable
{
	private Function func;

	public MyTestRunnable1(Function func)
	{
		this.func = func;
	}

	public void run()
	{
		func.test1();
	}
}

class MyTestRunnable2 implements Runnable
{
	private Function func;

	public MyTestRunnable2(Function func)
	{
		this.func = func;
	}

	public void run()
	{
		func.test2();
	}
}

class Function
{
	private String string1 = "string1";
	private String string2 = "string2";

	public void test1()
	{
		synchronized (string1)
		{
			try
			{
				System.out.println(Thread.currentThread().getName() + " is waiting!");
				string1.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " continues!");
		}
	}

	public void test2()
	{
		synchronized (string2)
		{
			string2.notifyAll();
		}
	}
}
