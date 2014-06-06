package com.yee.webstudy.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerTest2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Creates an event storage
		Storage storage = new BlockQueueStorage();

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

class BlockQueueStorage extends Storage
{
	private static final int MAX_SIZE = 1;

	private LinkedBlockingQueue<Object> products;

	public BlockQueueStorage()
	{
		this.products = new LinkedBlockingQueue<Object>(MAX_SIZE);
	}

	public void produce(Product product)
	{
		try
		{
			this.products.put(product);
			System.out.println(Thread.currentThread().getName() + " produce: current size = " + this.products.size());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void consume()
	{
		try
		{
			this.products.take();
			System.out.println(Thread.currentThread().getName() + " consume: current size = " + this.products.size());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
