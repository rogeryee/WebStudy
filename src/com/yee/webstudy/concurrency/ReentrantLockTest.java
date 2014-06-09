package com.yee.webstudy.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class will test 2 ways to do the lock mechanism 1. Using "sychronized" keyword 2. Using ReentrantLock
 * 
 * @author Roger.Yee
 * 
 */
public class ReentrantLockTest
{
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		ReentrantLockTest test = new ReentrantLockTest();
		test.testSynchronized();
		// test.testReentrantLock();
	}

	private void testSynchronized() throws InterruptedException
	{
		final OutputterWithReentrantLock outputter = new OutputterWithReentrantLock();

		// Create 2 threads
		Thread t1 = new Thread("Thread1")
		{
			public void run()
			{
				outputter.outputWithSynchronized("Shanghai");
			}
		};

		Thread t2 = new Thread("Thread2")
		{
			public void run()
			{
				outputter.outputWithSynchronized("Beijing");
			}
		};

		t1.start();
		t2.start();
	}

	private void testReentrantLock() throws InterruptedException
	{
		final OutputterWithReentrantLock outputter = new OutputterWithReentrantLock();

		// Create 2 threads
		Thread t1 = new Thread("Thread1")
		{
			public void run()
			{
				outputter.outputWithLock("Shanghai");
			}
		};

		Thread t2 = new Thread("Thread2")
		{
			public void run()
			{
				outputter.outputWithLock("Beijing");
			}
		};

		t1.start();
		t2.start();
	}
}

class OutputterWithReentrantLock
{
	private Lock lock = new ReentrantLock();// Default to unfairness lock

	/**
	 * Using "Lock" object
	 * 
	 * @param name
	 */
	public void outputWithLock(String name)
	{
		lock.lock();// Acuquire the lock
		try
		{
			System.out.println(Thread.currentThread().getName() + " gets the lock and running!");
			for (int i = 0; i < name.length(); i++)
			{
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		finally
		{
			lock.unlock();// Release the lock
			System.out.println(Thread.currentThread().getName() + " releases the lock!");
		}
	}

	/**
	 * Using "synchronized" keyword
	 * 
	 * @param name
	 */
	public synchronized void outputWithSynchronized(String name)
	{
		System.out.println(Thread.currentThread().getName() + " is running!");
		for (int i = 0; i < name.length(); i++)
		{
			System.out.print(name.charAt(i));
		}
		System.out.println();
		System.out.println(Thread.currentThread().getName() + " completes!");
	}
}
