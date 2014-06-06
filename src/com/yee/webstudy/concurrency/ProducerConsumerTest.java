package com.yee.webstudy.concurrency;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Creates an event storage
		Storage storage = new Storage();

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

class Product
{
}

class Storage
{
	private static final int MAX_SIZE = 1;

	private List<Product> products = new LinkedList<Product>();

	public Storage()
	{

	}

	public void produce(Product product)
	{
		synchronized (this.products)
		{
			while (this.products.size() == MAX_SIZE)
			{
				try
				{
					System.out.println(Thread.currentThread().getName() + " needs to wait, current size = "
							+ this.products.size());
					this.products.wait();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			this.products.add(product);
			System.out.println(Thread.currentThread().getName() + " produce a product to storage, current size = "
					+ this.products.size());
			this.products.notifyAll();
		}
	}

	public void consume()
	{
		synchronized (this.products)
		{
			while (this.products.size() == 0)
			{
				try
				{
					System.out.println(Thread.currentThread().getName() + " needs to wait, current size = "
							+ this.products.size());
					this.products.wait();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			try
			{
				this.products.remove(this.products.size() - 1);
			}
			catch (Exception e)
			{
				System.out.println(Thread.currentThread().getName() + " has an consume error, current size = "
						+ this.products.size());
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " consume a product from storage, current size = "
					+ this.products.size());
			this.products.notifyAll();
		}
	}
}

class Producer implements Runnable
{
	private Storage storage;

	public Producer(Storage storage)
	{
		this.storage = storage;
	}

	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			this.storage.produce(new Product());
		}
	}
}

class Consumer implements Runnable
{
	private Storage storage;

	public Consumer(Storage storage)
	{
		this.storage = storage;
	}

	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			this.storage.consume();
		}
	}
}
