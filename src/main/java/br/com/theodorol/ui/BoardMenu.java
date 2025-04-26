package br.com.theodorol.ui;

import br.com.theodorol.percistence.entity.BoardEntity;
import br.com.theodorol.service.BoardQueryService;

import java.sql.SQLException;
import java.util.Scanner;

import static br.com.theodorol.percistence.config.ConnectionConfig.getConnection;

public class BoardMenu {
    private  final BoardEntity entity;
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    public BoardMenu(BoardEntity entity) {
        this.entity = entity;
    }
    public void execute(){
        try {
            System.out.printf("Bem vindo ao board %s, selecione a operação desejada", entity.getBoardId());
            var option = -1;
            while (option != 9) {
                System.out.println("1 - Criar um card");
                System.out.println("2 - Mover um card");
                System.out.println("3 - Bloquear um card");
                System.out.println("4 - Desbloquear um card");
                System.out.println("5 - Cancelar um card");
                System.out.println("6 - Ver board");
                System.out.println("7 - Ver coluna com cards");
                System.out.println("8 - Ver card");
                System.out.println("9 - Voltar para o menu anterior um card");
                System.out.println("10 - Sair");
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> createCard();
                    case 2 -> moveCardToNextColumn();
                    case 3 -> blockCard();
                    case 4 -> unblockCard();
                    case 5 -> cancelCard();
                    case 6 -> showBoard();
                    case 7 -> showColumn();
                    case 8 -> showCard();
                    case 9 -> System.out.println("Voltando para o menu anterior");
                    case 10 -> System.exit(0);
                    default -> System.out.println("Opção inválida, informe uma opção do menu");
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }

    private void unblockCard() {
    }

    private void createCard() {
    }

    private void moveCardToNextColumn() {
    }

    private void blockCard() {
    }

    private void cancelCard() {
    }

    private void showBoard() throws SQLException {
        try(var connection = getConnection()){
           var optional =  new BoardQueryService(connection)
                    .showBoardDetails(entity.getBoardId());
           optional.ifPresent(b-> {
               System.out.printf("Board [%s, %s]\n", b.id(), b.name());
                b.columns().forEach(c->{
                    System.out.printf("coluna [%s] tipo : %s cards ", c.name(), c.kind());
                });
           });
        }
    }

    private void showColumn() {
    }

    private void showCard() {
    }
}
