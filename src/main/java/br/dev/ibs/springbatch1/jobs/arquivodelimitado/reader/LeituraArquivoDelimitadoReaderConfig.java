package br.dev.ibs.springbatch1.jobs.arquivodelimitado.reader;

import br.dev.ibs.springbatch1.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoDelimitadoReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(@Value("#{jobParameters['arquivoDelimitadoClientes']}") Resource arquivoDelimitadoClientes) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitadoReader")
                .resource(arquivoDelimitadoClientes)
                .delimited()
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }

}
