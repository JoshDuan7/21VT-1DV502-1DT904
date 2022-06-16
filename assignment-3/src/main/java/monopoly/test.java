package monopoly;

public class test {
  public static void main(String[] args) {
    System.out.println(isInteger(3.00));
  }
  private static boolean isInteger(double n){
    int ni = (int) n;
    double nd = (double) ni;
    if (nd==n)
        return true;
    return false;
}
}
