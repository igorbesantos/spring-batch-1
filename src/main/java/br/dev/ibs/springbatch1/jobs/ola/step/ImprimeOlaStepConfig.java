package br.dev.ibs.springbatch1.jobs.ola.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeOlaStepConfig {
	
	private final StepBuilderFactory stepBuilderFactory;
	
	public ImprimeOlaStepConfig(final StepBuilderFactory stepBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@Bean
	public Step imprimeOlaStep(Tasklet imprimeOlaTasklet) {
		return stepBuilderFactory
				       .get("imprimeOlaStep")
				       .tasklet(imprimeOlaTasklet)
				       .build();
	}
}
