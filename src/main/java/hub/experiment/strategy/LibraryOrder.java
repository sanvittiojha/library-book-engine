package hub.experiment.strategy;
import java.util.List;

public class LibraryOrder {
  private List<LibraryItem> libraryItems;

  public List<LibraryItem> getLibraryItems() {
    return libraryItems;
  }
  public LibraryOrder(List<LibraryItem> libraryItems) {
    this.libraryItems = libraryItems;
  }
}
