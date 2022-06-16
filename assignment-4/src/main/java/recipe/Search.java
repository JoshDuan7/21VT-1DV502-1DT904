package recipe;

import java.util.List;

/** This search interface is for strategy pattern design. */
public interface Search {
  public List<Recipe> search(List<Recipe> recipes, String searchTarget);
}
