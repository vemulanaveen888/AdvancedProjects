package com.pratap;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@StepScope
	public Tasklet restartTasklet() {
		return new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				Map<String, Object> stepExecutionContext = chunkContext.getStepContext().getStepExecutionContext();
				if(stepExecutionContext.containsKey("ran")) {
					System.out.println("This time we'll let it go");
					return RepeatStatus.FINISHED;
				}else {
					System.out.println("i don't think so ");
					chunkContext.getStepContext().getStepExecution().getExecutionContext().put("ran", true);
					throw new RuntimeException("not this time");
				}
				
			}
		};
	}
	
	@Bean
	public Step step1()  {
		return stepBuilderFactory.get("step1")
				.tasklet(restartTasklet()).build();
	}
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.tasklet(restartTasklet()).build();
	}

	@Bean
	public Job job1() throws Exception {
		return jobBuilderFactory.get("myjob45894474")
				.start(step1())
				.next(step2())
				.build();
	}
}
