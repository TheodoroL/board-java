package br.com.theodorol.percistence.entity;


public class CardEntity {

   private Long id_card;
   private String name;
   private String title;
   private String description;

   public Long getId_card() {
      return id_card;
   }

   public void setId_card(Long id_card) {
      this.id_card = id_card;
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
}
