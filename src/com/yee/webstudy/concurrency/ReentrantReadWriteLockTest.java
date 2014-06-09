package com.yee.webstudy.concurrency;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest
{
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		// Create 6 threads and 3 of them are to read the data and 3 of them are to write the data
		Thread[] threads = new Thread[6];
		final DataWithLock data = new DataWithLock();
		for (int i = 0; i < 3; i++)
		{
			threads[i] = new Thread(new Runnable()
			{
				public void run()
				{
					for (int j = 0; j < 5; j++)
					{
						data.set(new Random().nextInt(30));
					}
				}
			});
		}

		for (int i = 3; i < 6; i++)
		{
			threads[i] = new Thread(new Runnable()
			{
				public void run()
				{
					for (int j = 0; j < 5; j++)
					{
						data.get();
					}
				}
			});
		}

		for (int i = 0; i < 6; i++)
		{
			threads[i].start();
		}

		for (int i = 0; i < 6; i++)
		{
			threads[i].join();
		}
	}
}

class DataWithLock
{
	private int data;// Share Data
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public void set(int data)
	{
		rwl.writeLock().lock();// Acquire the WriteLock
		try
		{
			System.out.println(Thread.currentThread().getName() + " is ready to write data");
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " write " + this.data);
		}
		finally
		{
			rwl.writeLock().unlock();// Release the WriteLock
		}
	}

	public void get()
	{
		rwl.readLock().lock();// Acquire the ReadLock
		try
		{
			System.out.println(Thread.currentThread().getName() + " is ready to read data");
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " read " + this.data);
		}
		finally
		{
			rwl.readLock().unlock();// Release the ReadLock
		}
	}
}
