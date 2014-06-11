package com.yee.webstudy.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		// ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

		for (int i = 0; i < 100; i++)
		{
			TaskWithExecutor task = new TaskWithExecutor("Task " + i);
			executor.execute(task);

			executor.submit(new Callable<String>()
			{
				@Override
				public String call() throws Exception
				{
					return "Hello";
				}

			});
		}

		executor.shutdown();
	}

}

class TaskWithExecutor implements Runnable
{
	private String name;

	public TaskWithExecutor(String name)
	{
		this.name = name;
	}

	public void run()
	{
		try
		{
			Long duration = (long) (Math.random() * 10);
			System.out.println(this.name + ": Doing a task during " + duration + "seconds.");
			TimeUnit.SECONDS.sleep(duration);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}
}
