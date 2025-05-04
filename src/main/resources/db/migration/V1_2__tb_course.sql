-- Criação da tb_employee_role
CREATE TABLE tb_course (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    syllabus VARCHAR(255) NOT NULL,
    goal_points int NOT NULL,
    make_date DATE NOT NULL,
    course_status VARCHAR(50) NOT NULL,
    position_level VARCHAR(50) NOT NULL,
    employee_knowledge_advisor_id UUID NOT NULL,
    status varchar(255) NOT NULL,
    create_by varchar(255) NOT NULL DEFAULT 'system_user',
    created_date timestamp DEFAULT CURRENT_DATE,
    last_modified_by varchar(255),
    last_modified_date timestamp DEFAULT CURRENT_DATE,
    FOREIGN KEY (employee_knowledge_advisor_id) REFERENCES tb_employee_knowledge_advisor(id) ON DELETE CASCADE
);
