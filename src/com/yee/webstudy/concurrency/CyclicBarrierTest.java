package com.yee.webstudy.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int numberOfRunner = 5;
		CyclicBarrier cb = new CyclicBarrier(numberOfRunner, new Runnable()
		{
			public void run()
			{
				System.out.println("All Runners are ready!");
			}
		});

		for (int i = 0; i < numberOfRunner; i++)
		{
			new Thread(new RunnerWithCyclicBarrier(cb), "Player-" + (i + 1)).start();
		}
	}

}

class RunnerWithCyclicBarrier implements Runnable
{
	private CyclicBarrier cb;

	public RunnerWithCyclicBarrier(CyclicBarrier cb)
	{
		this.cb = cb;
	}

	public void run()
	{
		try
		{
			System.out.println(Thread.currentThread().getName() + " is ready!");
			cb.await();
			System.out.println(Thread.currentThread().getName() + " starts running!");
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (BrokenBarrierException e)
		{
			e.printStackTrace();
		}
	}
}
