package hub.experiment.strategy;

import java.util.ArrayList;
import java.util.List;

public class OrderDiligence {
  private final LibraryOrder order;
  private final List<LibraryRestrictionRule> rules;
  private List<String> rulesBroken;

  public List<String> getRulesBroken() {
    return rulesBroken;
  }

  public OrderDiligence(LibraryOrder order, List<LibraryRestrictionRule> rules) {
    this.order = order;
    this.rules = rules;
    this.rulesBroken = new ArrayList<>();
  }

  public boolean verify() {
    boolean result = true;
    for (LibraryRestrictionRule rule : rules) {
      if (!rule.isValid(order)) {
        result = false;
        rulesBroken.add(rule.getRuleName());
      }
    }
    return result;
  }
}
