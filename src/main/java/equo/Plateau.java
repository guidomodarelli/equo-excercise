package equo;

import java.util.ArrayList;

public class Plateau {
  private ArrayList<Dron> squad;
  private int width;
  private int heigth;

  Plateau(int width, int heigth) {
    this.setHeigth(heigth);
    this.setWidth(width);
  }

  public int getHeigth() {
    return heigth;
  }

  public int getWidth() {
    return width;
  }

  public void setHeigth(int heigth) {
    this.heigth = heigth;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void addDron(Dron dron) {
    this.squad.add(dron);
  }

  public void explore(Dron dron, char[] instructions) {
    for (char inst : instructions) {
      switch (inst) {
        case 'M':
          dron.goForward();
          break;
        case 'L':
          dron.turnLeft();
          break;
        default:
          dron.turnRight();
          break;
      }
    }
  }

  public void explore(Dron dron, String instructions) {
    explore(dron, instructions.toCharArray());
  }

}
