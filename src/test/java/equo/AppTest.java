package equo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void test1() {
    Dron dron = new Dron(1, 2, 'N', "LMLMLMLMM");
    Plateau plateau = new Plateau(5, 5);
    dron.explore(plateau);

    assertEquals("1 3 N", dron.toString());
  }

  @Test
  public void test2() {
    Dron dron = new Dron(3, 3, 'E', "MMRMMRMRRM");
    Plateau plateau = new Plateau(5, 5);

    dron.explore(plateau);

    assertEquals("5 1 E", dron.toString());
  }

  @Test
  public void test3() {
    Dron dron = new Dron(0, 0, 'N', "MMMRM");
    Plateau plateau = new Plateau(5, 5);

    dron.explore(plateau);

    assertEquals("1 3 E", dron.toString());
  }

  @Test
  public void test9() {
    Dron dron = new Dron(0, 0, 'N', "MMR");
    Plateau plateau = new Plateau(5, 5);

    dron.explore(plateau);

    assertEquals("0 2 E", dron.toString());
  }

  @Test
  @DisplayName("Colision entre drones")
  public void testIntegration1() {
    ArrayList<Dron> squat = new ArrayList<Dron>() {
      {
        add(new Dron(1, 2, 'N', "LMLMLMLMM"));
        add(new Dron(0, 0, 'N', "MMMRM"));
      }
    };
    Plateau plateau = new Plateau(5, 5, squat);

    try {
      Dron dron = squat.get(0);
      dron.explore(plateau);
      dron = squat.get(1);
      dron.explore(plateau);
      throw new Error("No hay colision!");
    } catch (Error err) {
      assertEquals(Plateau.COLISION, err.getMessage());
    }
  }

  @Test
  @DisplayName("Instrucción invalida")
  public void test4() {
    try {
      new Dron(0, 0, 'N', "MLRE");
    } catch (Error err) {
      assertEquals(Dron.INVALID_INSTRUCTION, err.getMessage());
    }
  }

  @Test
  @DisplayName("Orientación invalida")
  public void test5() {
    try {
      new Dron(0, 0, 'T', "MLR");
    } catch (Error err) {
      assertEquals(Dron.INVALID_ORIENTATION, err.getMessage());
    }
  }

  @Test
  @DisplayName("Ancho de la meseta mal definido")
  public void test6() {
    try {
      new Plateau(0, 1);
    } catch (Error err) {
      assertEquals(Plateau.INVALID_WIDTH, err.getMessage());
    }
  }

  @Test
  @DisplayName("Altura de la meseta mal definido")
  public void test7() {
    try {
      new Plateau(1, 0);
    } catch (Error err) {
      assertEquals(Plateau.INVALID_HEIGTH, err.getMessage());
    }
  }

  @Test
  @DisplayName("El dron excedió el limite de la meseta")
  public void test8() {
    try {
      ArrayList<Dron> squat = new ArrayList<Dron>() {{add(new Dron(0, 0, 'N', "MMMRM"));}};
      new Plateau(2, 1, squat);
    } catch (Error err) {
      assertEquals(Plateau.LIMIT_EXCEDED, err.getMessage());
    }
  }
}
