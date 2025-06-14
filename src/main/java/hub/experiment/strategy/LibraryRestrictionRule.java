package hub.experiment.strategy;

public interface LibraryRestrictionRule {

  public boolean isValid(LibraryOrder order);
  public String getRuleName();
}
