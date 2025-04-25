CREATE TABLE CARDS(
    id_card SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    board__column_id BIGINT NOT NULL,
    CONSTRAINT board_columns__cards_fk FOREIGN KEY (board__column_id)
        REFERENCES BOARD_COLUMNS(id_board_column) ON DELETE CASCADE
);
