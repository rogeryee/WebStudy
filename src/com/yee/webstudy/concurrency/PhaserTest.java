package com.yee.webstudy.concurrency;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest
{
	public static void main(String[] args)
	{
		final String[] taskList = new String[] { "Food", "Drink", "dessert" };
		final Phaser phaser = new Phaser(4); // 3 worker thread + 1 main thread

		System.out.println("Starting to process the tasks.");

		new Thread(new WorkerWithPhaser("Cooker", phaser, taskList)).start();
		new Thread(new WorkerWithPhaser("Waiter", phaser, taskList)).start();
		new Thread(new WorkerWithPhaser("Attendent", phaser, taskList)).start();

		for (String task : taskList)
		{
			phaser.arriveAndAwaitAdvance();
			System.out.println(task + " is completed.");
		}
		phaser.arriveAndDeregister();
		System.out.println("All tasks completes!");
	}
}

class WorkerWithPhaser implements Runnable
{
	private String name;
	private Phaser phaser;
	private String[] taskList;

	public WorkerWithPhaser(String name, Phaser phaser, String[] taskList)
	{
		this.name = name;
		this.phaser = phaser;
		this.taskList = taskList;
	}

	public void run()
	{
		try
		{
			for (String task : taskList)
			{
				System.out.println(this.name + " is doing for " + task);
				this.phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(3);
			}

			this.phaser.arriveAndDeregister();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
