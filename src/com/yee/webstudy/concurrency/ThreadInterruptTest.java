package com.yee.webstudy.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptTest
{
	public static void main(String[] args)
	{
		MyThread thread = new MyThread();
		thread.start();
		System.out.println("main thread");
		
		try
		{
			// Main thread will sleep for 2 seconds.
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// After 2 seconds, interrupt the thread
		thread.interrupt();
	}
}

class MyThread extends Thread
{
	public void run()
	{
		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			// Verify the state of the thread
			if (Thread.currentThread().isInterrupted())
			{
				System.out.println("Thread is interrupted!");
				return;
			}
			else
			{
				System.out.println(i);
			}
		}

	}
}
