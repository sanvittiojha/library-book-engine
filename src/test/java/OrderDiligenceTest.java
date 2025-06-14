import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

import hub.experiment.strategy.*;

class OrderDiligenceTest {

  @Test
  void rulesBrokenListIsEmptyWhenAllRulesPass() {
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    LibraryRestrictionRule rule = new LibraryRestrictionRule() {
      public boolean isValid(LibraryOrder o) { return true; }
      public String getRuleName() { return "AlwaysPass"; }
    };

    OrderDiligence diligence = new OrderDiligence(order, Collections.singletonList(rule));
    diligence.verify();
    Assertions.assertTrue(diligence.getRulesBroken().isEmpty());
  }

  @Test
  void rulesBrokenListContainsFailedRuleNames() {
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    LibraryRestrictionRule rule1 = new LibraryRestrictionRule() {
      public boolean isValid(LibraryOrder o) { return false; }
      public String getRuleName() { return "RuleOne"; }
    };
    LibraryRestrictionRule rule2 = new LibraryRestrictionRule() {
      public boolean isValid(LibraryOrder o) { return false; }
      public String getRuleName() { return "RuleTwo"; }
    };
    OrderDiligence diligence = new OrderDiligence(order, Arrays.asList(rule1, rule2));
    diligence.verify();
    Assertions.assertEquals(Arrays.asList("RuleOne", "RuleTwo"), diligence.getRulesBroken());
  }

  @Test
  void verifyReturnsTrueWhenNoRulesProvided() {
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    OrderDiligence diligence = new OrderDiligence(order, Collections.emptyList());
    Assertions.assertTrue(diligence.verify());
    Assertions.assertTrue(diligence.getRulesBroken().isEmpty());
  }

  @Test
  void verifyReturnsFalseWhenAtLeastOneRuleFails() {
    LibraryOrder order = new LibraryOrder(Collections.emptyList());
    LibraryRestrictionRule rule1 = new LibraryRestrictionRule() {
      public boolean isValid(LibraryOrder o) { return true; }
      public String getRuleName() { return "Pass"; }
    };
    LibraryRestrictionRule rule2 = new LibraryRestrictionRule() {
      public boolean isValid(LibraryOrder o) { return false; }
      public String getRuleName() { return "Fail"; }
    };
    OrderDiligence diligence = new OrderDiligence(order, Arrays.asList(rule1, rule2));
    Assertions.assertFalse(diligence.verify());
    Assertions.assertEquals(Collections.singletonList("Fail"), diligence.getRulesBroken());
  }
}