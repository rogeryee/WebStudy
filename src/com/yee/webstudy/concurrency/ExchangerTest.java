package com.yee.webstudy.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest
{
	public static void main(String[] args) throws InterruptedException
	{
		final Exchanger<String> exchanger = new Exchanger<String>();

		// Creates a Producer and a Thread to run it
		ProducerWithExchanger pe1 = new ProducerWithExchanger(exchanger);
		ProducerWithExchanger pe2 = new ProducerWithExchanger(exchanger);
		Thread p1 = new Thread(pe1, "Producer1");
		Thread p2 = new Thread(pe2, "Producer2");

		// Starts the thread
		p1.start();
		p2.start();

		p1.join();
		p2.join();

		System.out.println("Producer1:" + pe1.data);
		System.out.println("Producer2:" + pe2.data);
	}
}

class ProducerWithExchanger implements Runnable
{
	public Exchanger<String> exchanger;
	public String data;

	public ProducerWithExchanger(Exchanger<String> exchanger)
	{
		this.exchanger = exchanger;
	}

	public void run()
	{
		try
		{
			this.data = Thread.currentThread().getName() + " Product1";
			this.data = this.exchanger.exchange(this.data);
			TimeUnit.SECONDS.sleep(3);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
