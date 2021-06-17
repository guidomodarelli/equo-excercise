package equo;

public class App {
  public static void main(String[] args) {
    Dron dron = new Dron(3, 3, 'E', "MMRMMRMRRM");
    Plateau plateau = new Plateau(5, 5);
    dron.explore(plateau);

    System.out.println(dron);
  }
}
