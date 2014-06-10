package com.yee.webstudy.concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		final TaskWithSemaphore task = new TaskWithSemaphore();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++)
		{
			threads[i] = new Thread()
			{
				public void run()
				{
					task.test();
				}
			};
		}

		for (int i = 0; i < 10; i++)
		{
			threads[i].start();
		}
	}
}

class TaskWithSemaphore
{
	private final Semaphore semaphore;

	public TaskWithSemaphore()
	{
		this.semaphore = new Semaphore(3);// Only 3 thread can access at the same time.
	}

	public void test()
	{
		try
		{
			this.semaphore.acquire();

			System.out.println(Thread.currentThread().getName()
					+ " acquire the semaphore and is doing the task! And current permits number is "
					+ this.semaphore.availablePermits());

			Thread.sleep(50);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.semaphore.release();
		}
	}
}
