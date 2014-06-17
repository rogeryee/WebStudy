package com.yee.webstudy.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest
{
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Future<Integer> result = forkJoinPool.submit(new CalculatorWithForkJoin(0, 10000));

		System.out.println("result = " + result.get());
		System.out.println("Processors = " + Runtime.getRuntime().availableProcessors());
	}
}

class CalculatorWithForkJoin extends RecursiveTask<Integer>
{
	private static final int THRESHOLD = 100;
	private int start;
	private int end;

	public CalculatorWithForkJoin(int start, int end)
	{
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute()
	{
		int sum = 0;
		if ((start - end) < THRESHOLD)
		{
			for (int i = start; i < end; i++)
			{
				sum += i;
			}
		}
		else
		{
			int middle = (start + end) / 2;

			CalculatorWithForkJoin left = new CalculatorWithForkJoin(start, middle);
			CalculatorWithForkJoin right = new CalculatorWithForkJoin(middle + 1, end);

			left.fork();
			right.fork();

			sum = left.join() + right.join();
		}
		return sum;
	}

}