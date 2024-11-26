package br.dev.ibs.springbatch1.jobs.arquivomultiplosformatos.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoMultiplosFormatosReaderConfig {
	
	@StepScope
	@Bean
	public FlatFileItemReader<Object> leituraArquivoMultiplosFormatosReader(
			@Value("#{jobParameters['arquivoMultiplosFormatosClientes']}") Resource arquivoMultiplosFormatosClientes,
			LineMapper<Object> leituraArquivoMultiplosFormatosLineMapper
	) {
		return new FlatFileItemReaderBuilder<>()
				       .name("leituraArquivoMultiplosFormatosReader")
				       .resource(arquivoMultiplosFormatosClientes)
				       .lineMapper(leituraArquivoMultiplosFormatosLineMapper)
				       .build();
	}
}
