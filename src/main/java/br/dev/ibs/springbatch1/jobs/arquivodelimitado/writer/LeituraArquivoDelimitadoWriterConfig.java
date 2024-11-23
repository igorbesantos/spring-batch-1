package br.dev.ibs.springbatch1.jobs.arquivodelimitado.writer;

import br.dev.ibs.springbatch1.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoDelimitadoWriterConfig {

    @Bean
    public ItemWriter<Cliente> leituraArquivoDelimitadoWriter() {
        return clientes -> clientes.forEach(cliente -> System.out.printf("[DELIMITADO] %s%n", cliente));
    }
}
