package equo;

import java.util.ArrayList;

public class Plateau {
  public static final String COLISION = "Hay una colision entre drones!";
  public static final String LIMIT_EXCEDED = "El dron excedió los limites de la meceta!";
  public static final String INVALID_WIDTH = "El ancho de la meseta debe ser mayor a cero";
  public static final String INVALID_HEIGTH = "La altura de la meseta debe ser mayor a cero";
  private Dron[][] Meseta;

  private void initialize(int width, int heigth, ArrayList<Dron> squat) {
    if (width <= 0) {
      throw new Error(INVALID_WIDTH);
    }
    if (heigth <= 0) {
      throw new Error(INVALID_HEIGTH);
    }
    this.Meseta = new Dron[width + 1][heigth + 1];
    if (squat != null) {
      for (Dron dron : squat) {
        addDron(dron);
      }
    }
  }

  Plateau(int width, int heigth, ArrayList<Dron> squat) {
    this.initialize(width, heigth, squat);
  }

  Plateau(int width, int heigth) {
    this.initialize(width, heigth, null);
  }

  public int getWidth() {
    return Meseta.length;
  }

  public int getHeigth() {
    return Meseta[0].length;
  }

  private boolean isWithinTheLimits(int x, int y) throws Error {
    int width = getWidth();
    int heigth = getHeigth();
    if (x >= width || x < 0 || y >= heigth || y < 0) {
      throw new Error(LIMIT_EXCEDED);
    }
    return true;
  }

  private boolean collisionExists(int x, int y) throws Error {
    if (Meseta[x][y] != null) {
      throw new Error(COLISION);
    }
    return false;
  }

  public void addDron(Dron dron) throws Error {
    int x = dron.getX();
    int y = dron.getY();
    if (!isWithinTheLimits(x, y)) return;
    if (collisionExists(x, y)) return;
    Meseta[x][y] = dron;
  }

  public void moveDron(int xOld, int yOld, Dron dron) throws Error {
    Meseta[xOld][yOld] = null;
    addDron(dron);
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    for (Dron[] drons : Meseta) {
      for (Dron dron : drons) {
        out.append(dron).append(" ");
      }
      out.append("\n");
    }
    return out.toString();
  }

}
