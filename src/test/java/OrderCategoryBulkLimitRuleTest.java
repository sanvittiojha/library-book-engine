import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

import hub.experiment.strategy.*;

class OrderCategoryBulkLimitRuleTest {

  @Test
  void returnsTrueWhenAllCategoryLimitsAreRespected() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    limits.put(TextBookCategory.SCIENCE, 3);
    limits.put(TextBookCategory.ART, 2);
    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);

    List<LibraryItem> items = Arrays.asList(
      new LibraryItem(new TextBook(1, "Physics"), TextBookCategory.SCIENCE, 2),
      new LibraryItem(new TextBook(2, "Painting"), TextBookCategory.ART, 2)
    );
    LibraryOrder order = new LibraryOrder(items);

    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsFalseWhenAnyCategoryLimitIsExceeded() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    limits.put(TextBookCategory.SCIENCE, 2);
    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);

    List<LibraryItem> items = Arrays.asList(
      new LibraryItem(new TextBook(1, "Physics"), TextBookCategory.SCIENCE, 3)
    );
    LibraryOrder order = new LibraryOrder(items);

    Assertions.assertFalse(rule.isValid(order));
  }

  @Test
  void returnsTrueWhenCategoryHasNoDefinedLimit() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    limits.put(TextBookCategory.ART, 1);

    List<LibraryItem> items = Arrays.asList(
      new LibraryItem(new TextBook(1, "Math"), TextBookCategory.SCIENCE, 100)
    );
    LibraryOrder order = new LibraryOrder(items);

    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);
    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsFalseWhenOrderIsNull() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);
    Assertions.assertFalse(rule.isValid(null));
  }

  @Test
  void returnsFalseWhenOrderItemsIsNull() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    LibraryOrder order = new LibraryOrder(null);
    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);
    Assertions.assertFalse(rule.isValid(order));
  }

  @Test
  void returnsTrueWhenOrderHasNoItems() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);
    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsTrueWhenCategoryLimitIsExactlyMet() {
    Map<TextBookCategory, Integer> limits = new HashMap<>();
    limits.put(TextBookCategory.SCIENCE, 5);

    List<LibraryItem> items = Arrays.asList(
      new LibraryItem(new TextBook(1, "Physics"), TextBookCategory.SCIENCE, 2),
      new LibraryItem(new TextBook(2, "Chemistry"), TextBookCategory.SCIENCE, 3)
    );
    LibraryOrder order = new LibraryOrder(items);

    OrderCategoryBulkLimitRule rule = new OrderCategoryBulkLimitRule(limits);
    Assertions.assertTrue(rule.isValid(order));
  }
}