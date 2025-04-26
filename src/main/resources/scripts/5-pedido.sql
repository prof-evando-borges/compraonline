-- Remover tabelas e sequências se existirem (para evitar conflitos)
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE itens_pedido CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE pedidos CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_pedidos';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_itens_pedido';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- Criar sequências
CREATE SEQUENCE seq_pedidos START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE seq_itens_pedido START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- Tabela PEDIDOS
CREATE TABLE pedidos (
    id NUMBER DEFAULT seq_pedidos.NEXTVAL PRIMARY KEY,
    cliente_id VARCHAR2(50) NOT NULL,
    status VARCHAR2(20) NOT NULL CHECK (status IN ('EM_PREPARO', 'PRONTO', 'ENTREGUE', 'CANCELADO')),
    data TIMESTAMP DEFAULT SYSDATE NOT NULL
);

-- Tabela ITENS_PEDIDO
CREATE TABLE itens_pedido (
    id NUMBER DEFAULT seq_itens_pedido.NEXTVAL PRIMARY KEY,
    pedido_id NUMBER NOT NULL,
    produto_id VARCHAR2(50) NOT NULL,
    quantidade NUMBER NOT NULL CHECK (quantidade > 0),
    preco_unitario NUMBER(10, 2) NOT NULL CHECK (preco_unitario > 0),
    CONSTRAINT fk_pedido 
        FOREIGN KEY (pedido_id) 
        REFERENCES pedidos(id)
        ON DELETE CASCADE
);

-- Índices para otimização (opcional, mas recomendado)
CREATE INDEX idx_pedidos_cliente ON pedidos(cliente_id);
CREATE INDEX idx_itens_pedido_pedido ON itens_pedido(pedido_id);