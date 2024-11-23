package br.dev.ibs.springbatch1.jobs.arquivolargurafixa.writer;

import br.dev.ibs.springbatch1.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {
	@Bean
	public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
		return items -> items.forEach(item -> System.out.printf("[LARGURA FIXA] %s%n", item));
	}
}
