package br.com.theodorol.percistence.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static br.com.theodorol.percistence.entity.BoardColumnKindEnum.CANCEL;
import static br.com.theodorol.percistence.entity.BoardColumnKindEnum.INITIAL;

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

    public BoardColumnsEntity getInitialColumn(){
        return boardColumns.stream()
                .filter(bc -> bc.getKind().equals(INITIAL))
                .findFirst().orElseThrow();
    }

    public BoardColumnsEntity getCancelColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(CANCEL));
    }

    private BoardColumnsEntity getFilteredColumn(Predicate<BoardColumnsEntity> filter){
        return boardColumns.stream()
                .filter(filter)
                .findFirst().orElseThrow();
    }
}
