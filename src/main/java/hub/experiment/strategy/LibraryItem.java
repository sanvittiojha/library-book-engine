package hub.experiment.strategy;

import java.util.Date;

public class LibraryItem {

  private TextBook textBook;
  private TextBookCategory category;
  private int quantity;

  public int getQuantity() {
    return quantity;
  }

  public TextBookCategory getCategory() {
    return category;
  }

  public LibraryItem(TextBook textBook, TextBookCategory category, int quantity) {
    this.textBook = textBook;
    this.category = category;
    this.quantity = quantity;
  }
}
