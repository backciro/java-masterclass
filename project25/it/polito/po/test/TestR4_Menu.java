package it.polito.po.test;
import dieta.*;
import junit.framework.TestCase;

public class TestR4_Menu extends TestCase {

  public TestR4_Menu(String arg0) {
    super(arg0);
  }

  private Alimenti alimenti;
  private Ricetta r;

  public void setUp() throws Exception {
    alimenti = new Alimenti();
    alimenti.definisciMateriaPrima("Zucchero", 400, 0, 100, 0);
    alimenti.definisciMateriaPrima("Mais", 70, 2.7, 12, 1.3);
    alimenti.definisciMateriaPrima("Pasta", 350, 12, 72.2, 1.5);
    alimenti.definisciMateriaPrima("Olio", 900, 0, 0, 100);
    alimenti.definisciMateriaPrima("Nutella", 530, 6.8, 56, 31);
	alimenti.definisciProdotto("Cracker", 111, 2.6, 17.2, 3.5);
    r = new Ricetta("Pasta e Nutella", alimenti);
    r.aggiungiIngrediente("Pasta", 70);
    r.aggiungiIngrediente("Nutella", 30);
  }

  public void testMenuConRicetta() {
    Menu menu = new Menu("M1", alimenti);

    menu.aggiungiRicetta("Pasta e Nutella", 100);

    assertEquals(350 * 0.7 + 530 * 0.3, r.getCalorie(), 0.001);
    assertEquals(12 * 0.7 + 6.8 * 0.3, r.getProteine(), 0.001);
    assertEquals(72.2 * 0.7 + 56 * 0.3, r.getCarboidrati(), 0.001);
    assertEquals(1.5 * 0.7 + 31 * 0.3, r.getGrassi(), 0.001);
  }

  public void testMenu() {
    Menu menu = new Menu("M1", alimenti);

    menu.aggiungiRicetta("Pasta e Nutella", 50);
    menu.aggiungiProdotto("Cracker");

    assertEquals(350 * 0.35 + 530 * 0.15 + 111, menu.getCalorie(), 0.001);
    assertEquals(12 * 0.35 + 6.8 * 0.15 + 2.6, menu.getProteine(), 0.001);
    assertEquals(72.2 * 0.35 + 56 * 0.15 + 17.2, menu.getCarboidrati(), 0.001);
    assertEquals(1.5 * 0.35 + 31 * 0.15 + 3.5, menu.getGrassi(), 0.001);
  }

}
