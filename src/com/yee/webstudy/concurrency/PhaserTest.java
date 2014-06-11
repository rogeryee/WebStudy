package com.yee.webstudy.concurrency;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 本例定义了3个线程5个任务，每个线程要同时完成第一个任务，才能进行下一个。 同时本例又复写了Phaser的onAdvance方法，在该方法内只要求完成3个任务。
 * 
 * @author Roger.Yee
 * 
 */
public class PhaserTest
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("----Starting to process the tasks!-----");

		final String[] taskList = new String[] { "Appetizer", "Soup", "Main dish", "Dessert", "Cafe" };
		final Phaser phaser = new Phaser(3)
		{
			protected boolean onAdvance(int phase, int registeredParties)
			{
				switch (phase)
				{
				case 0:
					return phase0();
				case 1:
					return phase1();
				case 2:
					return phase2();
				default:
					return true;
				}
			}

			private boolean phase0()
			{
				System.out.println("Phase 0 completed!");
				return false;
			}

			private boolean phase1()
			{
				System.out.println("Phase 1 completed!");
				return false;
			}

			private boolean phase2()
			{
				System.out.println("Phase 2 completed!");
				return false;
			}
		};

		Thread[] threads = new Thread[3];
		threads[0] = new Thread(new WorkerWithPhaser("Cooker", phaser, taskList));
		threads[1] = new Thread(new WorkerWithPhaser("Waiter", phaser, taskList));
		threads[2] = new Thread(new WorkerWithPhaser("Attendent", phaser, taskList));

		for (Thread t : threads)
		{
			t.start();
		}

		for (Thread t : threads)
		{
			t.join();
		}

		System.out.println("----All tasks completed!-----");
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
			int i = 0;
			while (!this.phaser.isTerminated())
			{
				System.out.println(this.name + " is doing for " + this.taskList[i++] + ", phase = "
						+ this.phaser.getPhase());
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
