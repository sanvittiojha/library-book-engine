package hub.experiment.strategy;

import java.util.Map;
import java.util.HashMap;

public class OrderCategoryBulkLimitRule implements LibraryRestrictionRule {

  private Map<TextBookCategory, Integer> categoryLimits; // Map of category to its maximum allowed items

  public OrderCategoryBulkLimitRule(Map<TextBookCategory, Integer> categoryLimits) {
    this.categoryLimits = categoryLimits;
  }

  @Override
  public boolean isValid(LibraryOrder order) {
    if (order == null || order.getLibraryItems() == null) {
      return false; // Invalid order or no items
    }
    // Create a map to count items per category
    Map<TextBookCategory, Integer> categoryCountMap = new HashMap<>();
    // Iterate through each library item in the order
    for (LibraryItem item : order.getLibraryItems()) {
      TextBookCategory category = item.getCategory();
      int itemQuantity = item.getQuantity();

      // Update the count for the category
      categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + itemQuantity);
    }
    // Check if any category exceeds its limit
    for (Map.Entry<TextBookCategory, Integer> entry : categoryCountMap.entrySet()) {
      TextBookCategory category = entry.getKey();
      int totalQuantity = entry.getValue();

      // If the total quantity for this category exceeds the limit, return false
      if (totalQuantity > categoryLimits.getOrDefault(category, Integer.MAX_VALUE)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String getRuleName() {
    return "OrderCategoryBulkLimitRule";
  }
}
