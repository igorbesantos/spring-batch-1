package br.dev.ibs.springbatch1.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class ImprimeOlaTasklet implements Tasklet {
	
    @Value("#{jobParameters['nome']}")
    String nome;
	
	@Override
	public RepeatStatus execute(final StepContribution stepContribution, final ChunkContext chunkContext) {
		System.out.printf("### Olá, %s!%n", nome);
		return RepeatStatus.FINISHED;
	}
}
