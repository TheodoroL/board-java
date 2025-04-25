package br.com.theodorol.percistence.entity;


import java.util.ArrayList;
import java.util.List;

public class BoardEntity {
    private Long boardId;
    private  String name;
    private List<BoardColumnsEntity> boardColumns = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public List<BoardColumnsEntity> getBoardColumns() {
        return boardColumns;
    }

    public void setBoardColumns(List<BoardColumnsEntity> boardColumns) {
        this.boardColumns = boardColumns;
    }
}
