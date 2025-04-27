package br.com.theodorol.percistence.entity;


public class CardEntity {

   private Long idCard;
   private String name;
   private String title;
   private String description;
   private BoardColumnsEntity boardColumn  = new BoardColumnsEntity();
   public Long getIdCard() {
      return idCard;
   }

   public void setidCard(Long id_card) {
      this.idCard = id_card;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public BoardColumnsEntity getBoardColumn() {
      return boardColumn;
   }

   public void setBoardColumn(BoardColumnsEntity boardColumn) {
      this.boardColumn = boardColumn;
   }

   public void setIdCard(Long idCard) {
      this.idCard = idCard;
   }
}
