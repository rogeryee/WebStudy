package com.yee.webstudy.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest
{
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		int numberOfParticipant = 5;
		final ConferenceWithCountDownLatch conference = new ConferenceWithCountDownLatch(numberOfParticipant);
		new Thread(conference).start();

		for (int i = 0; i < numberOfParticipant; i++)
		{
			new Thread("Participant-" + (i + 1))
			{
				public void run()
				{
					conference.enterConference(this.getName());
				}
			}.start();
		}
	}
}

class ConferenceWithCountDownLatch implements Runnable
{
	private final CountDownLatch count;

	public ConferenceWithCountDownLatch(int numberOfParticipant)
	{
		this.count = new CountDownLatch(numberOfParticipant);
	}

	public void enterConference(String name)
	{
		System.out.println(name + " enters the conference now!");
		this.count.countDown();
	}

	public void run()
	{
		System.out.println("Conference is ready now and waiting for the participants!");
		try
		{
			this.count.await();
			System.out.println("All the participant are ready, let's get started!");
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
