package br.com.theodorol.service;

import br.com.theodorol.dto.BoardDetailDTO;
import br.com.theodorol.percistence.dao.BoardColumnDAO;
import br.com.theodorol.percistence.dao.BoardDAO;
import br.com.theodorol.percistence.entity.BoardEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class BoardQueryService {
    private final Connection connection;

    public BoardQueryService(Connection connection) {
        this.connection = connection;
    }

    public Optional<BoardEntity> findById(Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findById(id);
        if (optional.isPresent()) {
            var entity = optional.get();
            entity.setBoardColumns(boardColumnDAO.findByBoardId(entity.getBoardId()));
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    public Optional<BoardDetailDTO> showBoardDetails(Long id) throws SQLException{
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findById(id);
        if(optional.isPresent()){
            var entity  = optional.get();
           var columns =  boardColumnDAO.findByBoardIdWithDetails(entity.getBoardId());
          var dto =  new BoardDetailDTO(entity.getBoardId(), entity.getName(), columns );
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
