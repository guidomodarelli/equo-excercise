package equo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void test01() {
    Dron dron = new Dron(1, 2, 'N', "LMLMLMLMM");

    dron.explore(5, 5);

    assertEquals("1 3 N", dron.toString());
  }

  @Test
  public void test02() {
    Dron dron = new Dron(3, 3, 'E', "MMRMMRMRRM");

    dron.explore(5, 5);

    assertEquals("5 1 E", dron.toString());
  }

  @Test
  public void test03() {
    Dron dron = new Dron(0, 0, 'N', "MMMRM");

    dron.explore(5, 5);

    assertEquals("1 3 E", dron.toString());
  }

  @Test
  public void test04() {
    Dron dron = new Dron(0, 0, 'N', "MMR");

    dron.explore(5, 5);

    assertEquals("0 2 E", dron.toString());
  }

  @Test
  @DisplayName("Instrucción invalida")
  public void invalidInstruction() {
    try {
      new Dron(0, 0, 'N', "MLRE");
      // ~~~~~~~~~~~~~~~~~~~~~^
    } catch (Error err) {
      assertEquals(Dron.INVALID_INSTRUCTION, err.getMessage());
    }
  }

  @Test
  @DisplayName("Orientación invalida")
  public void invalidOrientation() {
    try {
      new Dron(0, 0, 'T', "MLR");
      // ~~~~~~~~~~~~~^
    } catch (Error err) {
      assertEquals(Dron.INVALID_ORIENTATION, err.getMessage());
    }
  }

  @Test
  @DisplayName("El dron está fuera de los limites de la meceta!")
  public void limitExceded() {
    try {
      Dron dron = new Dron(2, 2, 'N', "MMMM");

      dron.explore(1, 1);
    } catch (Error err) {
      assertEquals(Dron.LIMIT_EXCEDED, err.getMessage());
    }
  }
}
