package com.yee.webstudy.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerTest3
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Creates an event storage
		Storage storage = new StorageWithLock();

		// Creates a Producer and a Thread to run it
		Thread p1 = new Thread(new Producer(storage), "Producer1");
		Thread p2 = new Thread(new Producer(storage), "Producer2");

		// Creates a Consumer and a Thread to run it
		Thread c1 = new Thread(new Consumer(storage), "Consumer1");
		Thread c2 = new Thread(new Consumer(storage), "Consumer2");

		// Starts the thread
		c1.start();
		c2.start();
		p1.start();
		p2.start();
	}
}

class StorageWithLock extends Storage
{
	private static final int MAX_SIZE = 5;

	private List<Object> products;

	private final Lock lock = new ReentrantLock();
	private Condition canProduce = lock.newCondition();
	private Condition canConsume = lock.newCondition();

	public StorageWithLock()
	{
		this.products = new ArrayList<Object>(MAX_SIZE);
	}

	public void produce(Product product)
	{
		lock.lock();
		try
		{
			while (this.products.size() >= MAX_SIZE)
				this.canProduce.await();

			// System.out.println("Before adding, size = " + this.products.size());
			this.products.add(product);
			// System.out.println("After adding, size = " + this.products.size());

			System.out.println(Thread.currentThread().getName() + " produce: current size = " + this.products.size());

			this.canConsume.signal();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}

	public void consume()
	{
		lock.lock();
		try
		{
			while (this.products.size() <= 0)
				this.canConsume.await();

			this.products.remove(this.products.size() - 1);
			System.out.println(Thread.currentThread().getName() + " consume: current size = " + this.products.size());

			this.canProduce.signal();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}
}
