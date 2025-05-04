-- Criação da tabela funcionario
CREATE TABLE tb_employee_knowledge_advisor (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    bio TEXT,
    employee_id UUID NOT NULL,
    start_date DATE,
    status varchar(255) NOT NULL,
    create_by varchar(255) NOT NULL DEFAULT 'system_user',
    created_date timestamp DEFAULT CURRENT_DATE,
    last_modified_by varchar(255),
    last_modified_date timestamp DEFAULT CURRENT_DATE
);