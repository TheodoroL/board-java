package br.com.theodorol.percistence.entity;

public class BoardColumnsEntity {
    private  Long idBoardColum;
    private  String name;
    private Integer order;
    private BoardColumnKindEnum kind;
    private BoardEntity board = new BoardEntity();

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
