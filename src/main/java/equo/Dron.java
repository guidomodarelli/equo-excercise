package equo;

public class Dron {
  public static final String INVALID_ORIENTATION = "La orientación del dron debe contener alguno de los siguientes valores: N, W, E ó S";
  public static final String INVALID_INSTRUCTION = "Instrucción invalida";
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
