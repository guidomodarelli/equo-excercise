package equo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

public class Dron {
  public static final String LIMIT_EXCEDED = "El dron está fuera de los limites de la meceta!";
  public static final String INVALID_ORIENTATION = "La orientación del dron debe contener alguno de los siguientes valores: N, W, E ó S";
  public static final String INVALID_INSTRUCTION = "Instrucción invalida";
  public static final String INVALID_POSITION = "La posición del dron está formada por dos numeros naturales y alguna de las siguientes letras N, W, S ó E separadas por espacios";
  public static final String INSTRUCTIONS_NOT_DEFINED = "Instrucciones no definidas";
  private int x;
  private int y;
  private char orientation;
  private char[] instructions;

  Dron(int x, int y, char orientation, String instructions) {
    this.setPosition(x, y, orientation);
    this.setInstructions(instructions.toCharArray());
  }

  Dron(String positionString, String instructions) throws Error {
    if (instructions == null) {
      throw new Error(INSTRUCTIONS_NOT_DEFINED);
    }
    setPosition(positionString);
    setInstructions(instructions.toCharArray());
  }

  public void setPosition(int x, int y, char orientation) {
    if (x < 0 || y < 0) {
      throw new Error(LIMIT_EXCEDED);
    }
    this.setX(x);
    this.setY(y);
    this.setOrientation(orientation);
  }

  public void setPosition(String positionString) throws Error {
    Pattern pattern = Pattern.compile("^[0-9]+ [0-9]+ [NWSE]$");
    Matcher matcher = pattern.matcher(positionString);
    if (matcher.matches()) {
      String[] values = positionString.split(" ");
      int x = parseInt(values[0]);
      int y = parseInt(values[1]);
      char orientation = values[2].charAt(0);
      this.setPosition(x, y, orientation);
    } else {
      throw new Error(INVALID_POSITION);
    }
  }

  public int getX() {
    return y;
  }

  public int getY() {
    return x;
  }

  public char getOrientation() {
    return orientation;
  }

  public char[] getInstructions() {
    return instructions;
  }

  public void setX(int x) {
    this.y = x;
  }

  public void setY(int y) {
    this.x = y;
  }

  public void setOrientation(char orientation) {
    if (!(orientation == 'N' || orientation == 'W' || orientation == 'E' || orientation == 'S')) {
      throw new Error(INVALID_ORIENTATION);
    }
    this.orientation = orientation;
  }

  public void setInstructions(char[] instructions) throws Error {
    for (char c : instructions) {
      if (!(c == 'M' || c == 'L' || c == 'R')) {
        throw new Error(INVALID_INSTRUCTION);
      }
    }
    this.instructions = instructions;
  }

  private void turnLeft() {
    switch (this.orientation) {
      case 'N':
        this.orientation = 'W';
        break;
      case 'W':
        this.orientation = 'S';
        break;
      case 'S':
        this.orientation = 'E';
        break;
      default:
        this.orientation = 'N';
        break;
    }
  }

  private void turnRight() {
    switch (this.orientation) {
      case 'N':
        this.orientation = 'E';
        break;
      case 'W':
        this.orientation = 'N';
        break;
      case 'S':
        this.orientation = 'W';
        break;
      default:
        this.orientation = 'S';
        break;
    }
  }

  private void goForward() {
    switch (this.orientation) {
      case 'N':
        this.setY(this.getY() + 1);
        break;
      case 'W':
        this.setX(this.getX() - 1);
        break;
      case 'S':
        this.setY(this.getY() - 1);
        break;
      default:
        this.setX(this.getX() + 1);
        break;
    }
  }

  private void executeInstruction(char instruction) {
    switch (instruction) {
      case 'M':
        this.goForward();
        break;
      case 'L':
        this.turnLeft();
        break;
      default:
        this.turnRight();
        break;
    }
  }

  private boolean isWithinTheLimits(int width, int heigth) throws Error {
    if (this.x >= width || this.x < 0 || this.y >= heigth || this.y < 0) {
      throw new Error(LIMIT_EXCEDED);
    }
    return true;
  }

  public void explore(int widthMax, int heigthMax) throws Error {
    for (int i = 0; i < this.instructions.length; i++) {
      char instruction = this.instructions[i];
      executeInstruction(instruction);
      if (!isWithinTheLimits(widthMax + 1, heigthMax + 1))
        return;
    }
  }

  @Override
  public String toString() {
    return String.format("%d %d %c", getX(), getY(), getOrientation());
  }
}
