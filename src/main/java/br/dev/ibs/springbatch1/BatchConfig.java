package br.dev.ibs.springbatch1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	/**
	 * Usar o incrementador ".incrementer(new RunIdIncrementer())" pode:
	 * - Permitir que o mesmo job seja executado novamente com os mesmos parâmetros;*
	 *      * Normalmente, ao gravar os metadados do spring batch,
	 *        o mesmo job não pode ser executdo mais de uma vez com os mesmos parâmetros.
	 * - Impedir que um job seja re-executado após um erro, por exemplo, pois sempre será criado um novo job.
	 * Logo, essa configuração deve ser utilizada com CUIDADO!!!
	 * Aqui, ela foi adicionada por aprendizado e praticidade na execução de testes.
	 * @see RunIdIncrementer
	 * @see org.springframework.batch.core.job.builder.JobBuilderHelper
	 */
	@Bean
	public Job imprimeOlaJob() {
		return jobBuilderFactory
		       .get("imprimeOlaJob")
		       .start(imprimeOlaStep())
		       .incrementer(new RunIdIncrementer())
		       .build();
	}
	
	public Step imprimeOlaStep() {
		return stepBuilderFactory
		       .get("imprimeOlaStep")
		       .tasklet(imprimeOlaTasklet(null))
		       .build();
	}
	
	@Bean
	@StepScope
	public Tasklet imprimeOlaTasklet(@Value("#{jobParameters['nome']}") String nome) {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(final StepContribution stepContribution,
			                            final ChunkContext chunkContext) throws
			                                                             Exception {
				System.out.printf("### Olá, %s!%n", nome);
				return RepeatStatus.FINISHED;
			}
		};
	}
}
