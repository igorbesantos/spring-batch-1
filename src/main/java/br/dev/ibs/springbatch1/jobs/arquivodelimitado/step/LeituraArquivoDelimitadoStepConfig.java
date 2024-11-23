package br.dev.ibs.springbatch1.jobs.arquivodelimitado.step;

import br.dev.ibs.springbatch1.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoDelimitadoStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public LeituraArquivoDelimitadoStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step leituraArquivoDelimitadoStep(ItemReader<Cliente> leituraArquivoDelimitadoReader,
                                             ItemWriter<Cliente> leituraArquivoDelimitadoWriter) {
        return stepBuilderFactory
                .get("leituraArquivoDelimitadoStep")
                .<Cliente, Cliente>chunk(1)
                .reader(leituraArquivoDelimitadoReader)
                .writer(leituraArquivoDelimitadoWriter)
                .build();
    }
}
