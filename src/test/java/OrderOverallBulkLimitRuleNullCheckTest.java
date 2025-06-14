import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import hub.experiment.strategy.*;

class OrderOverallBulkLimitRuleTest {

  @Test
  void returnsTrueWhenTotalItemsIsLessThanLimit() {
    LibraryItem item1 = new LibraryItem(new TextBook(1, "Math"), TextBookCategory.SCIENCE, 2);
    LibraryItem item2 = new LibraryItem(new TextBook(2, "Art"), TextBookCategory.ART, 1);
    LibraryOrder order = new LibraryOrder(Arrays.asList(item1, item2));
    OrderOverallBulkLimitRule rule = new OrderOverallBulkLimitRule(5);
    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsTrueWhenTotalItemsIsExactlyAtLimit() {
    LibraryItem item1 = new LibraryItem(new TextBook(1, "Math"), TextBookCategory.SCIENCE, 2);
    LibraryItem item2 = new LibraryItem(new TextBook(2, "Art"), TextBookCategory.ART, 3);
    LibraryOrder order = new LibraryOrder(Arrays.asList(item1, item2));
    OrderOverallBulkLimitRule rule = new OrderOverallBulkLimitRule(5);
    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsFalseWhenTotalItemsExceedsLimit() {
    LibraryItem item1 = new LibraryItem(new TextBook(1, "Math"), TextBookCategory.SCIENCE, 4);
    LibraryItem item2 = new LibraryItem(new TextBook(2, "Art"), TextBookCategory.ART, 3);
    LibraryOrder order = new LibraryOrder(Arrays.asList(item1, item2));
    OrderOverallBulkLimitRule rule = new OrderOverallBulkLimitRule(5);
    Assertions.assertFalse(rule.isValid(order));
  }

  @Test
  void returnsTrueWhenOrderHasNoItems() {
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    OrderOverallBulkLimitRule rule = new OrderOverallBulkLimitRule(5);
    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsTrueWhenLimitIsZeroAndOrderHasNoItems() {
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    OrderOverallBulkLimitRule rule = new OrderOverallBulkLimitRule(0);
    Assertions.assertTrue(rule.isValid(order));
  }

  @Test
  void returnsFalseWhenLimitIsZeroAndOrderHasItems() {
    LibraryItem item = new LibraryItem(new TextBook(1, "Math"), TextBookCategory.SCIENCE, 1);
    LibraryOrder order = new LibraryOrder(Collections.singletonList(item));
    OrderOverallBulkLimitRule rule = new OrderOverallBulkLimitRule(0);
    Assertions.assertFalse(rule.isValid(order));
  }
}