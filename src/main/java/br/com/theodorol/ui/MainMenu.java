package br.com.theodorol.ui;

import br.com.theodorol.percistence.entity.BoardColumnKindEnum;
import br.com.theodorol.percistence.entity.BoardColumnsEntity;
import br.com.theodorol.percistence.entity.BoardEntity;
import br.com.theodorol.service.BoardQueryService;
import br.com.theodorol.service.BoardService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.theodorol.percistence.config.ConnectionConfig.getConnection;
import static br.com.theodorol.percistence.entity.BoardColumnKindEnum.*;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);

    public void execute() throws SQLException {
        System.out.println("Seja bem-vindo ao gerenciador de boards!");
        int option = -1;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Criar uma nova board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, informe uma opção do menu.");
            }
        }
    }

    private void createBoard() throws SQLException {
        var entity = new BoardEntity();
        System.out.println("Informe o nome do seu board:");
        entity.setName(scanner.next());

        System.out.println("Seu board terá colunas além das 3 padrões? Se sim, informe quantas. Se não, digite '0':");
        var additionalColumns = scanner.nextInt();

        List<BoardColumnsEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board:");
        var initialColumnName = scanner.next();
        var initialColumn = createColumn(initialColumnName, INITIAL, 0);
        columns.add(initialColumn);

        for (int i = 0; i < additionalColumns; i++) {
            System.out.println("Informe o nome da coluna de tarefa pendente do board:");
            var pendingColumnName = scanner.next();
            var pendingColumn = createColumn(pendingColumnName, PENDING, i + 1);
            columns.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna final:");
        var finalColumnName = scanner.next();
        var finalColumn = createColumn(finalColumnName, FINAL, additionalColumns + 1);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do board:");
        var cancelColumnName = scanner.next();
        var cancelColumn = createColumn(cancelColumnName, CANCEL, additionalColumns + 2);
        columns.add(cancelColumn);

        entity.setBoardColumns(columns);

        try (var connection = getConnection()) {
            var service = new BoardService(connection);
            service.insert(entity);
            System.out.println("Board criado com sucesso!");
        }
    }

    private void selectBoard() throws SQLException {
        System.out.println("informe o id do board que você deseja selecionar");
        var id = scanner.nextLong();
        try(var connection = getConnection()){
            var queryService = new BoardQueryService(connection);
            var optional = queryService.findById(id);
            if(optional.isEmpty()) {
                System.out.printf("Não foi encontrado o board com ID %d.%n", id);
                return;
            }
            var boardMenu = new BoardMenu(optional.get());
            boardMenu.execute();
        }
    }

    private void deleteBoard() throws SQLException {
        System.out.println("Digite o ID do board que será deletado:");
        var id = scanner.nextLong();

        try (var connection = getConnection()) {
            var service = new BoardService(connection);
            if (!service.delete(id)) {
                System.out.printf("Não foi encontrado o board com ID %d.%n", id);
                return;
            }
            System.out.printf("O board %d foi deletado com sucesso.%n", id);
        }
    }

    private BoardColumnsEntity createColumn(final String name, final BoardColumnKindEnum kind, final int order) {
        var boardColumn = new BoardColumnsEntity();
        boardColumn.setName(name);
        boardColumn.setKind(kind);
        boardColumn.setOrder(order);
        return boardColumn;
    }
}
