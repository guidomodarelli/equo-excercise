package equo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dron {
  public static final String INVALID_ORIENTATION = "La orientación del dron debe contener alguno de los siguientes valores: N, W, E ó S";
  public static final String INVALID_INSTRUCTION = "Instrucción invalida";
  public static final String INVALID_CREATE_DRON = "Error al crear el dron, los valores deben ser dos numeros enteros y una letra (N, W, S ó E) separados por espacios";
  public static final String INSTRUCTIONS_NOT_DEFINED = "Instrucciones no definidas";
  private int x;
  private int y;
  private char orientation;
  private char[] instructions;

  Dron(int x, int y, char orientation, String instructions) {
    this.setX(x);
    this.setY(y);
    this.setOrientation(orientation);
    this.setInstructions(instructions.toCharArray());
  }

  Dron(String positionString, String instructions) throws Error {
    setPosition(positionString);
    if (instructions == null) {
      throw new Error(INSTRUCTIONS_NOT_DEFINED);
    }
    setInstructions(instructions.toCharArray());
  }

  public void setPosition(String positionString) throws Error {
    Pattern pattern = Pattern.compile("^[0-9]+ [0-9]+ (?:N|W|S|E)$");
    Matcher matcher = pattern.matcher(positionString);
    if (matcher.matches()) {
      String[] valuesPosition = positionString.split(" ");
      int x = Integer.parseInt(valuesPosition[0]);
      int y = Integer.parseInt(valuesPosition[1]);
      char orientation = valuesPosition[2].charAt(0);
      this.setX(x);
      this.setY(y);
      this.setOrientation(orientation);
    } else {
      throw new Error(INVALID_CREATE_DRON);
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

  private void executeInstruction(char instruction, Plateau p) {
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

  public void explore(Plateau plateau) throws Error {
    for (int i = 0; i < this.instructions.length; i++) {
      int x = getX();
      int y = getY();
      executeInstruction(this.instructions[i], plateau);
      plateau.moveDron(x, y, this);
    }
  }

  @Override
  public String toString() {
    return String.format("%d %d %c", getX(), getY(), getOrientation());
  }
}
