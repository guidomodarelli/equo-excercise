package equo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {

  @Test
  public void testDron1() {
    Dron dron = new Dron(3, 3, 'E');
    Plateau plateau = new Plateau(5, 5);

    plateau.explore(dron, "MMRMMRMRRM");

    assertEquals("5 1 E", dron.toString());
  }

  @Test
  public void testDron2() {
    Dron dron = new Dron(1, 2, 'N');
    Plateau plateau = new Plateau(5, 5);

    plateau.explore(dron, "LMLMLMLMM");

    assertEquals("1 3 N", dron.toString());
  }
}
