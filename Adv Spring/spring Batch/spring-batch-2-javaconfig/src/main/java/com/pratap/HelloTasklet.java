package com.pratap;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloTasklet implements Tasklet {

	int i = 0;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("hello, world ");
		i++;
		if (i < 10)
			return RepeatStatus.CONTINUABLE;
		else
			return RepeatStatus.FINISHED;
	}

}
