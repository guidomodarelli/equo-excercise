package equo;

import java.util.ArrayList;

public class Plateau {
  private ArrayList<Dron> squad;
  private int width;
  private int heigth;

  Plateau(int width, int heigth) {
    this.setWidth(width);
    this.setHeigth(heigth);
  }

  public int getHeigth() {
    return heigth;
  }

  public int getWidth() {
    return width;
  }

  public void setHeigth(int heigth) {
    if (heigth <= 0) {
      throw new Error("La altura de la meseta debe ser mayor a cero");
    }
    this.heigth = heigth;
  }

  public void setWidth(int width) {
    if (width <= 0) {
      throw new Error("El ancho de la meseta debe ser mayor a cero");
    }
    this.width = width;
  }

  public void addDron(Dron dron) {
    this.squad.add(dron);
  }

}
