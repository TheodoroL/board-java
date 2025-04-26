package br.com.theodorol.percistence.entity;

import java.util.ArrayList;
import java.util.List;

public class BoardColumnsEntity {
    private  Long idBoardColum;
    private  String name;
    private Integer order;
    private BoardColumnKindEnum kind;
    private BoardEntity board = new BoardEntity();

    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }

    private List<CardEntity> cards = new ArrayList<>();
    public BoardColumnKindEnum getKind() {
        return kind;
    }

    public void setKind(BoardColumnKindEnum kind) {
        this.kind = kind;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdBoardColum() {
        return idBoardColum;
    }

    public void setIdBoardColum(Long idBoardColum) {
        this.idBoardColum = idBoardColum;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }
}
