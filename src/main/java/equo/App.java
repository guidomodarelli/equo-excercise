package equo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class App {

  public static final String MESSAGE_PLATEAU = "Las coordenadas de la meseta deben ser dos numeros naturales separados por espacios";
  private int width;
  private int heigth;
  private ArrayList<Dron> squat;

  /**
   * Obtiene las coordenadas superior derecha de la meceta, y crea los drones con
   * su respectiva posicion e instrucciones
   * 
   * @param list La primer posición incluye las coordenadas superior derecha de la
   *             meceta, y el resto es información perteneciente al dron que ha
   *             sido desplegado. A cada dron, le pertenecen dos nodos, el primero
   *             que indica su posicion y el segundo, las instrucciones.
   * @throws Error
   */
  App(ArrayList<String> list) throws Error {
    getPlateauCoordinates(list.remove(0));
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

  /**
   * Obtiene las coordenadas superior derecha de la meceta
   * @param dimensionesString coordenadas superior derecha de la meceta, los
   *                          cuales son dos numero naturales separados por un
   *                          espacio
   * @throws Error
   */
  public void getPlateauCoordinates(String dimensionesString) throws Error {
    Pattern pattern = Pattern.compile("^[0-9]+ [0-9]+$");
    Matcher matcher = pattern.matcher(dimensionesString);
    if (matcher.matches()) {
      String[] dimensions = dimensionesString.split(" ");
      this.width = parseInt(dimensions[0]);
      this.heigth = parseInt(dimensions[1]);
    } else {
      throw new Error(MESSAGE_PLATEAU);
    }
  }

  /**
   * Por cada dron, explora la meceta en busca de aceite
   * 
   * @throws Error
   */
  public void resolve() throws Error {
    for (Dron dron : squat) {
      dron.explore(this.width, this.heigth);
      System.out.printf("%s ", dron);
    }
    System.out.println();
  }
}
