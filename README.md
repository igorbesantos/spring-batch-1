# spring-batch-1

## Propósito

O projeto foi criado para estudo e testes com Spring Batch.

Na descrição dos jobs, os passos mais importantes estarão marcados com um &#x2757;.

---

## Execução e testes

A aplicação espera argumentos ao ser executada, segue exemplo:


```
nome=Igor arquivoLarguraFixaClientes=file:files/clientes-largura-fixa.txt arquivoDelimitadoClientes=file:files/clientes-delimitado.txt arquivoMultiplosFormatosClientes=file:files/clientes-multiplos-formatos.txt arquivosClientes=file:files/clientes-multiplos-formatos*
```

Há um script de consultas [aqui](./consultas-metadados-spring-batch.sql), de metadados do spring batch que serão gravados no h2.

---

## Jobs executados

### imprimeOlaJob

Usa uma tasklet para imprimir no log uma saudação ao nome informado no parâmetro ``nome``.

Verificar configuração do job na classe [ImprimeOlaJobConfig.java](./src/main/java/br/dev/ibs/springbatch1/jobs/ola/ImprimeOlaJobConfig.java) &#x2757;.

Steps:
1. [imprimeOlaStep](./src/main/java/br/dev/ibs/springbatch1/jobs/ola/step/ImprimeOlaStepConfig.java)
    - [ImprimeOlaTasklet](./src/main/java/br/dev/ibs/springbatch1/jobs/ola/tasklet/ImprimeOlaTasklet.java) &#x2757;
    Lê um parâmetro da aplicação através do ``StepScope``.

### imprimeParImparJob

Lê em _chunks_ números de 1 a 10 e imprime quais são pares ou ímpares.

Steps:
1. [imprimeParImparStep](./src/main/java/br/dev/ibs/springbatch1/jobs/parimpar/step/ImprimeParImparStepConfig.java) &#x2757;
   1. [contaAteDezReader](./src/main/java/br/dev/ibs/springbatch1/jobs/parimpar/reader/ContaAteDezReaderConfig.java)
   2. [imprimeParImparProcessor](./src/main/java/br/dev/ibs/springbatch1/jobs/parimpar/processor/ImprimeParImparProcessorConfig.java)
   3. [ImprimeWriter](./src/main/java/br/dev/ibs/springbatch1/jobs/parimpar/writer/ImprimeWriter.java)

### arquivoLarguraFixaJob

Lê um arquivo de largura fixa (arquivo de exemplo em [files/clientes-largura-fixa.txt](./files/clientes-largura-fixa.txt)),
que deve ter o seu caminho informado no parâmetro ``arquivoLarguraFixaClientes`` e imprime os clientes lidos no log, 
utilizando um ``FlatFileItemReader``.

Steps:
1. [leituraArquivoLarguraFixaStep](./src/main/java/br/dev/ibs/springbatch1/jobs/arquivolargurafixa/step/LeituraArquivoLarguraFixaStepConfig.java)
   1. [leituraArquivoLarguraFixaReader](./src/main/java/br/dev/ibs/springbatch1/jobs/arquivolargurafixa/reader/LeituraArquivoLarguraFixaReaderConfig.java) &#x2757;
   Aqui um ``FlatFileItemReaderBuilder`` é usado para criar o _ItemReader_ que lê o arquivo e instancia os objetos do tipo **Cliente**. 
   2. [leituraArquivoLarguraFixaWriter](./src/main/java/br/dev/ibs/springbatch1/jobs/arquivolargurafixa/writer/LeituraArquivoLarguraFixaWriterConfig.java)

### arquivoDelimitadoJob

Lê um arquivo delimitado (arquivo de exemplo em [files/clientes-delimitado.txt](./files/clientes-delimitado.txt)),
que deve ter o seu caminho informado no parâmetro ``arquivoDelimitadoClientes`` e imprime os clientes lidos no log,
utilizando um ``FlatFileItemReader``.

Steps:
1. [leituraArquivoDelimitadoStep](./src/main/java/br/dev/ibs/springbatch1/jobs/arquivodelimitado/step/LeituraArquivoDelimitadoStepConfig.java)
   1. [leituraArquivoDelimitadoReader](./src/main/java/br/dev/ibs/springbatch1/jobs/arquivodelimitado/reader/LeituraArquivoDelimitadoReaderConfig.java) &#x2757;
      Aqui um ``FlatFileItemReaderBuilder`` é usado para criar o _ItemReader_ que lê o arquivo e instancia os objetos do tipo **Cliente**.
   2. [leituraArquivoDelimitadoWriter](./src/main/java/br/dev/ibs/springbatch1/jobs/arquivodelimitado/writer/LeituraArquivoDelimitadoWriterConfig.java)
