-- Tabelas
show tables;

-- Quantas execuções lógicas - completas
select * from BATCH_JOB_INSTANCE;

-- Quantas execuções no total - incluindo inompletas/falha
select * from BATCH_JOB_EXECUTION;

-- Mostrar propriedades do contexto do JOB
select * from BATCH_JOB_EXECUTION_CONTEXT;

-- Mostrar parâmetros de execução do JOB
select * from BATCH_JOB_EXECUTION_PARAMS;

-- Quais steps executaram
select * from BATCH_STEP_EXECUTION;

-- Mostrar propriedades do contexto do STEP
select * from BATCH_STEP_EXECUTION_CONTEXT;
