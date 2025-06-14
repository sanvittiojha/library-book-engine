package hub.experiment.strategy;

public class OrderOverallBulkLimitRule implements  LibraryRestrictionRule {

    private final int maxLibraryItems; // Default limit for library items

    public OrderOverallBulkLimitRule(int maxLibraryItems) {
        this.maxLibraryItems = maxLibraryItems;
    }

    @Override
    public boolean isValid(LibraryOrder order) {
        // Check if the total number of library items in the order exceeds the maximum allowed
        if (order == null || order.getLibraryItems() == null) {
            return false; // Invalid order or no items
        }
        // Check if the number of library items in the order is within the limit
        int totalItems = order.getLibraryItems().stream()
            .mapToInt(LibraryItem::getQuantity)
            .sum();

        /*int totalItems = 0;
        for (LibraryItem item : order.getLibraryItems()) {
            if (item != null) {
                totalItems += item.getQuantity();
            }
        }*/

        return totalItems <= maxLibraryItems;
    }

    public String getRuleName() {
        return "OrderOverallBulkLimitRule";
    }

}
