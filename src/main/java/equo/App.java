package equo;

public class App {
  public static void main(String[] args) {
    Dron dron = new Dron(3, 3, 'E');
    Plateau plateau = new Plateau(5, 5);

    plateau.explore(dron, "MMRMMRMRRM");

    System.out.println(dron);
  }
}
