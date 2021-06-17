package equo;

public class Dron {
  private int x;
  private int y;
  private char orientation;

  Dron(int x, int y, char orientation) {
    this.setX(x);
    this.setY(y);
    this.orientation = orientation;
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

  public void setX(int x) {
    this.y = x;
  }

  public void setY(int y) {
    this.x = y;
  }

  public void setOrientation(char orientation) {
    this.orientation = orientation;
  }

  public void turnLeft() {
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

  public void turnRight() {
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

  public void goForward() {
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

  @Override
  public String toString() {
    return String.format("%d %d %c", getX(), getY(), getOrientation());
  }
}
