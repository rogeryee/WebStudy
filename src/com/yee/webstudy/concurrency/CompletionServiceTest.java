package com.yee.webstudy.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest
{
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec);

		for (int i = 0; i < 10; i++)
		{
			completionService.submit(new Callable<Integer>()
			{
				@Override
				public Integer call() throws Exception
				{
					int ran = new Random().nextInt(1000);
					Thread.sleep(ran);
					System.out.println(Thread.currentThread().getName() + " 休息了 " + ran);
					return ran;
				}
			});
		}

		for (int i = 0; i < 10; i++)
		{
			try
			{
				// 谁最先执行完成，直接返回
				Future<Integer> f = completionService.take();
				System.out.println("Result:" + (f == null ? "null" : f.get()));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				e.printStackTrace();
			}
		}

		exec.shutdown();
	}

}
