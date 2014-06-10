package com.yee.webstudy.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest
{
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		final TaskWithCondition condition = new TaskWithCondition();

		// 创建3个线程，需要保证3个线程按顺序执行：
		// firstThread/secondThread/thirdThread一次输出，如此反复循环10次
		Thread first = new Thread()
		{
			public void run()
			{
				try
				{
					for (int i = 0; i < 10; i++)
						condition.first();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};

		Thread second = new Thread()
		{
			public void run()
			{
				try
				{
					for (int i = 0; i < 10; i++)
						condition.second();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};

		Thread third = new Thread()
		{
			public void run()
			{
				try
				{
					for (int i = 0; i < 10; i++)
						condition.third();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};

		// 启动线程
		first.start();
		second.start();
		third.start();
	}
}

class TaskWithCondition
{
	private final Lock lock = new ReentrantLock();
	private Condition firstThread = lock.newCondition();
	private Condition secondThread = lock.newCondition();
	private Condition thirdThread = lock.newCondition();

	private boolean isFirst = true;
	private boolean isSecond = false;
	private boolean isThird = false;

	public void first() throws InterruptedException
	{
		lock.lock();

		try
		{
			while (!isFirst)
				firstThread.await();

			System.out.println("1");
			isFirst = false;
			isSecond = true;

			secondThread.signal();
		}
		finally
		{
			lock.unlock();
		}
	}

	public void second() throws InterruptedException
	{
		lock.lock();

		try
		{
			while (!isSecond)
				secondThread.await();

			System.out.println("2");
			isSecond = false;
			isThird = true;

			thirdThread.signal();
		}
		finally
		{
			lock.unlock();
		}
	}

	public void third() throws InterruptedException
	{
		lock.lock();

		try
		{
			while (!isThird)
				thirdThread.await();

			System.out.println("3");
			isThird = false;
			isFirst = true;
			firstThread.signal();
		}
		finally
		{
			lock.unlock();
		}
	}
}
