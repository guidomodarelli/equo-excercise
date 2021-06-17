package equo;

public class Dron {
  private int x;
  private int y;
  private char orientation;
  private char[] instructions;

  Dron(int x, int y, char orientation, String instructions) {
    this.setX(x);
    this.setY(y);
    this.orientation = orientation;
    this.instructions = instructions.toCharArray();
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
    this.orientation = orientation;
  }

  private void turnLeft() {
    if (this.orientation == 'N') {
      this.orientation = 'W';
    } else if (this.orientation == 'W') {
      this.orientation = 'S';
    } else if (this.orientation == 'S') {
      this.orientation = 'E';
    } else {
      this.orientation = 'N';
    }
  }

  private void turnRight() {
    if (this.orientation == 'N') {
      this.orientation = 'E';
    } else if (this.orientation == 'W') {
      this.orientation = 'N';
    } else if (this.orientation == 'S') {
      this.orientation = 'W';
    } else {
      this.orientation = 'S';
    }
  }

  private void goForward() {
    if (this.orientation == 'N') {
      this.setY(this.getY() + 1);
    } else if (this.orientation == 'W') {
      this.setX(this.getX() - 1);
    } else if (this.orientation == 'S') {
      this.setY(this.getY() - 1);
    } else {
      this.setX(this.getX() + 1);
    }
  }

  private void checkRange(Plateau planteau) {
    if (this.getX() < 0 || 
        this.getY() < 0 || 
        this.getX() > planteau.getWidth() || 
        this.getY() > planteau.getHeigth()) {
      throw new Error("El dron excedió los limites de la meceta");
    }
  }

  private void executeInstruction(char instruction, Plateau p) {
    switch (instruction) {
      case 'M':
        this.goForward();
        checkRange(p);
        break;
      case 'L':
        this.turnLeft();
        break;
      default:
        this.turnRight();
        break;
    }
  }

  public void explore(Plateau planteau) {
    for (int i = 0; i < this.instructions.length; i++) {
      executeInstruction(this.instructions[i], planteau);
    }
  }

  @Override
  public String toString() {
    return String.format("%d %d %c", getX(), getY(), getOrientation());
  }
}
