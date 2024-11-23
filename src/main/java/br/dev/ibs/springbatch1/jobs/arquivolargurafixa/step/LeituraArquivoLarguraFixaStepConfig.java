package br.dev.ibs.springbatch1.jobs.arquivolargurafixa.step;

import br.dev.ibs.springbatch1.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaStepConfig {

	public final StepBuilderFactory stepBuilderFactory;

	public LeituraArquivoLarguraFixaStepConfig(StepBuilderFactory stepBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Step leituraArquivoLarguraFixaStep(ItemReader<Cliente> leituraArquivoLarguraFixaReader,
			ItemWriter<Cliente> leituraArquivoLarguraFixaWriter) {
		return stepBuilderFactory
				.get("leituraArquivoLarguraFixaStep")
				.<Cliente, Cliente>chunk(1)
				.reader(leituraArquivoLarguraFixaReader)
				.writer(leituraArquivoLarguraFixaWriter)
				.build();
	}
}
