package br.dev.ibs.springbatch1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ParImparBatchConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
	
	private final StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public ParImparBatchConfig(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@Bean
	public Job imprimeParImparJob() {
		return jobBuilderFactory
				       .get("imprimeParImparJob")
				       .start(imprimeParImparStep())
				       .incrementer(new RunIdIncrementer())
				       .build();
	}
	
	/**
	 * O tamanho do chuck define quantos registros serão processados por transação.
	 * Experimente executar valores diferentes aqui, como 1 vs 10, e depois consulte a quantidade de transações usadas na excução do job.
	 * É possível consultar as transações assim:
	 * select * from BATCH_STEP_EXECUTION WHERE STEP_NAME = 'imprimeParImparStep';
	 * Verifique a quantidade de commits na coluna COMMIT_COUNT.
	 * Criar uma transação é custoso, logo, é ideal para performance manter o tamanho do chuck maior.
	 * Em contrapartida, quanto maior o tamanho do chuck mais memória será usada na aplicação e na base.
	 * Então é necessário encontrar o equilíbrio entre esses dois lados.
	 */
	private Step imprimeParImparStep() {
		return stepBuilderFactory
				       .get("imprimeParImparStep")
				       .<Integer, String>chunk(10)
				       .reader(contaAteDezReader())
				       .processor(imprimeParImparProcessor())
				       .writer(imprimeWriter())
				       .build();
	}
	
	private IteratorItemReader<Integer> contaAteDezReader() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<>(numeros.iterator());
	}
	
	private FunctionItemProcessor<Integer, String> imprimeParImparProcessor() {
		return new FunctionItemProcessor<>(
				item -> item % 2 == 0 ? String.format("Item %s é Par.", item) : String.format("Item %s é Ímpar.", item)
		);
	}
	
	private ItemWriter<String> imprimeWriter() {
		return itens -> itens.forEach(System.out::println);
	}
}
