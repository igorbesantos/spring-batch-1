package br.dev.ibs.springbatch1.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparProcessorConfig {
	
	@Bean
	public FunctionItemProcessor<Integer, String> imprimeParImparProcessor() {
		return new FunctionItemProcessor<>(
				item -> item % 2 == 0 ? String.format("Item %s é Par.", item) : String.format("Item %s é Ímpar.", item)
		);
	}
	
}
