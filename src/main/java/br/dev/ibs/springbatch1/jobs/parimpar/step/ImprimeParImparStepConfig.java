package br.dev.ibs.springbatch1.jobs.parimpar.step;

import br.dev.ibs.springbatch1.jobs.parimpar.writer.ImprimeWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparStepConfig {
	
	private final StepBuilderFactory stepBuilderFactory;
	
	public ImprimeParImparStepConfig(final StepBuilderFactory stepBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	/**
	 * O tamanho do chuck define quantos registros serão processados por transação.
	 * Experimente executar valores diferentes aqui, como 1 vs 10, e depois consulte a quantidade de transações usadas na excução do job.
	 * <p>
	 * É possível consultar as transações assim:
	 * select * from BATCH_STEP_EXECUTION WHERE STEP_NAME = 'imprimeParImparStep';
	 * Verifique a quantidade de commits na coluna COMMIT_COUNT.
	 * <p>
	 * Criar uma transação é custoso, logo, é ideal para o desempenho manter o tamanho do chuck maior.
	 * <p>
	 * Em contrapartida, quanto maior o tamanho do chuck mais memória será usada na aplicação e na base,
	 * além disso, quanto maior o chunk, mais registros terão que ser reprocessados em caso de falha na transação.
	 * <p>
	 * Então é necessário encontrar o equilíbrio entre esses dois lados.
	 */
	@Bean
	public Step imprimeParImparStep(
			@Qualifier("contaAteDezReader") ItemReader<Integer> contaAteDezReader,
			@Qualifier("imprimeParImparProcessor") ItemProcessor<Integer, String> imprimeParImparProcessor,
			ImprimeWriter imprimeWriter
			) {
		return stepBuilderFactory
				       .get("imprimeParImparStep")
				       .<Integer, String>chunk(10)
				       .reader(contaAteDezReader)
				       .processor(imprimeParImparProcessor)
				       .writer(imprimeWriter)
				       .build();
	}
	
}
