package br.dev.ibs.springbatch1.jobs.arquivomultiplosformatos.step;

import br.dev.ibs.springbatch1.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiplosFormatosStepConfig {
	
	private final StepBuilderFactory stepBuilderFactory;
	
	public LeituraArquivoMultiplosFormatosStepConfig(final StepBuilderFactory stepBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step leituraArquivoMultiplosFormatosStep(
			MultiResourceItemReader<Cliente> multiplosArquivosClienteTransacaoReader,
			ItemWriter leituraArquivoMultiplosFormatosWriter
	) {
		return stepBuilderFactory
				       .get("leituraArquivoMultiplosFormatosStep")
				       .chunk(1)
				       .reader(multiplosArquivosClienteTransacaoReader)
				       .writer(leituraArquivoMultiplosFormatosWriter)
				       .build();
	}
	
}
