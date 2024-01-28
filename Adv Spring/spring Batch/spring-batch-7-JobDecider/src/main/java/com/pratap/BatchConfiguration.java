package com.pratap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration


public class BatchConfiguration {
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean 
	public Step startStep() {
		return stepBuilderFactory.get("startstep")
				.tasklet(new Tasklet() {
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("This is the start tasklet");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

	@Bean 
	public Step evenStep() {
		return stepBuilderFactory.get("evenstep")
				.tasklet(new Tasklet() {
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("This is the even tasklet");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

	@Bean 
	public Step oddStep() {
		return stepBuilderFactory.get("oddstep")
				.tasklet(new Tasklet() {
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("This is the Odd tasklet");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

	@Bean
	public JobExecutionDecider decider() {
		return new OddDecider();
	}
	
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job2")
				.start(startStep())
				.next(decider())
				.from(decider()).on("ODD").to(oddStep())
				.from(decider()).on("EVEN").to(evenStep())
				.from(decider()).on("*").to(decider())
				.from(decider()).on("ODD").to(oddStep())
				.from(decider()).on("EVEN").to(evenStep())
				.end()
				.build();
	}

	public static class OddDecider implements JobExecutionDecider{
		private int count = 0;
		@Override
		public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
			count++;
			if(count%2 == 0	) {
				return new FlowExecutionStatus("EVEN");
			}else {
				return new FlowExecutionStatus("ODD");
			}
		}
		
	}
}
