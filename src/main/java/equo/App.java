package equo;

import java.util.ArrayList;

public class App {

  private Plateau plateau;
  private ArrayList<Dron> squat;

  App(ArrayList<String> list) throws Error {
    this.plateau = new Plateau(list.remove(0));
    this.squat = new ArrayList<>();

    for (int i = 0; i < list.size(); i++) {
      String position = list.get(i);
      String instructions = null;
      if (i < list.size() - 1) {
        instructions = list.get(++i);
      }
      this.squat.add(new Dron(position, instructions));
    }
  }

  public void resolve() throws Error {
    for (Dron dron : squat) {
      dron.explore(plateau);
      System.out.printf("%s ", dron);
    }
    System.out.println();
  }
}
