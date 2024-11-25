package br.dev.ibs.springbatch1.jobs.arquivomultiplosformatos.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoMultiplosFormatosWriterConfig {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ItemWriter leituraArquivoMultiplosFormatosWriter() {
		return lista -> lista.forEach(item -> System.out.printf("[MÚLTIPLOS FORMATOS] %s%n", item));
	}
}
