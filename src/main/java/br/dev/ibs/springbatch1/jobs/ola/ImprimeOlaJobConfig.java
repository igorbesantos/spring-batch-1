package br.dev.ibs.springbatch1.jobs.ola;

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
public class ImprimeOlaJobConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	
	public ImprimeOlaJobConfig(final JobBuilderFactory jobBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
	}
	
	/**
	 * Usar o incrementador ".incrementer(new RunIdIncrementer())" pode:
	 * - Permitir que o mesmo job seja executado novamente com os mesmos parâmetros;*
	 * * Normalmente, ao gravar os metadados do spring batch,
	 * o mesmo job não pode ser executado mais de uma vez com os mesmos parâmetros.
	 * - Impedir que um job seja reexecutado após um erro, por exemplo, pois sempre será criado um novo job.
	 * Logo, essa configuração deve ser utilizada com CUIDADO!!!
	 * Aqui, ela foi adicionada por aprendizado e praticidade na execução de testes.
	 *
	 * @see RunIdIncrementer
	 * @see org.springframework.batch.core.job.builder.JobBuilderHelper
	 */
	@Bean
	public Job imprimeOlaJob(@Qualifier("imprimeOlaStep") Step imprimeOlaStep) {
		return jobBuilderFactory
				       .get("imprimeOlaJob")
				       .start(imprimeOlaStep)
				       .incrementer(new RunIdIncrementer())
				       .build();
	}
}
