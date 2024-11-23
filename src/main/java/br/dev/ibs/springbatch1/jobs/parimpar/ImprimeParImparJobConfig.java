package br.dev.ibs.springbatch1.jobs.parimpar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ImprimeParImparJobConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	
	public ImprimeParImparJobConfig(final JobBuilderFactory jobBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
	}
	
	@Bean
	public Job imprimeParImparJob(@Qualifier("imprimeParImparStep") Step imprimeParImparStep) {
		return jobBuilderFactory
				       .get("imprimeParImparJob")
				       .start(imprimeParImparStep)
				       .incrementer(new RunIdIncrementer())
				       .build();
	}
}
