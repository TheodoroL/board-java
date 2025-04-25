CREATE TABLE BOARD_COLUMNS (
    id_board_column SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    "order" INTEGER NOT NULL,
    kind VARCHAR(7) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT boards__boards_columns_fk FOREIGN KEY (board_id)
        REFERENCES BOARD(id_board) ON DELETE CASCADE,
    CONSTRAINT id_order_uk UNIQUE (board_id, "order")
);
