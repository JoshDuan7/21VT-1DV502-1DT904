package recipe;

/** This class is for ingredient object. */
public class Ingredient {
  private String name;
  private String unit;
  private Double price;
  private boolean isDivisable;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public boolean isDivisable() {
    return isDivisable;
  }

  public void setDivisable(boolean isDivisable) {
    this.isDivisable = isDivisable;
  }

}
