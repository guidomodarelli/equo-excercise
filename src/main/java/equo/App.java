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

  public void getPlateauCoordinates(String dimensionesString) {
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

  public void resolve() throws Error {
    for (Dron dron : squat) {
      dron.explore(this.width, this.heigth);
      System.out.printf("%s ", dron);
    }
    System.out.println();
  }
}
