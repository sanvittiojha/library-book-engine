package hub.experiment.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {


    // initialize the library system
    TextBook book1 = new TextBook(1, "Effective Java");
    TextBook book2 = new TextBook(2, "Clean Code");
    TextBook book3 = new TextBook(3, "Clean Architecture");

    LibraryItem item1 = new LibraryItem(book1, TextBookCategory.CLASSICS, 3);
    LibraryItem item2 = new LibraryItem(book2, TextBookCategory.ART, 2);
    LibraryItem item3 = new LibraryItem(book3, TextBookCategory.SELF_HELP, 2);

    List<LibraryItem> items = Arrays.asList(item1, item2, item3);

    LibraryOrder order = new LibraryOrder(items);

    LibraryRestrictionRule rule1 = new OrderOverallBulkLimitRule(5);
    Map<TextBookCategory,Integer> categoryLimits = new HashMap<>();
    categoryLimits.put(TextBookCategory.CLASSICS, 2);
    categoryLimits.put(TextBookCategory.ART, 2);
    categoryLimits.put(TextBookCategory.SELF_HELP, 2);
    LibraryRestrictionRule rule2 = new OrderCategoryBulkLimitRule(categoryLimits);
    List<LibraryRestrictionRule> rules = Arrays.asList(rule1, rule2);

    OrderDiligence diligence = new OrderDiligence(order, rules);
    boolean isValid = diligence.verify();
    if (isValid) {
      System.out.println("Order is valid.");
    } else {
      System.out.println("Order is invalid.");
      System.out.println("Rules broken: " + diligence.getRulesBroken());
    }

  }
}