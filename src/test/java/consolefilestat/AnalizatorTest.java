/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Рома
 */
public class AnalizatorTest {

    StringReader sr = new StringReader("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. \n"
            + "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis\n"
            + " dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, \n"
            + "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, \n"
            + "fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, \n"
            + "venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. \n"
            + "Cras dapibus. Vivamus elementum semper nisi.");
    Analizator instance = new Analizator(new CharStreamSource(sr));

    public AnalizatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of analizeWholeLines method, of class Analizator.
     */
    @Test
    public void testAnalizeWholeLines() throws IOException {
        System.out.println("analizeWholeLines");
        instance.analizeWholeLines();
        List<LineStat> stats = instance.getStats();
        assertEquals(7, stats.size());
        assertEquals(stats.get(3).getLongest(), "pellentesque");

        List<LineStat> old = new ArrayList<>(stats);
        instance.analizeWholeLines();
        stats = instance.getStats();
        assertEquals(old, stats);

        Analizator inst = new Analizator(new FileSource(new FileReader("src\\test\\resources\\text")));
        inst.analizeWholeLines();
        stats = inst.getStats();
        assertEquals(old, stats);
    }

    /**
     * Test of analizeLine method, of class Analizator.
     */
    @Test
    public void testAnalize() {
        try {
            instance.analizeLine(null);
            fail("IllegalArgumentException should be here");
        } catch (IllegalArgumentException e) {
        }

        assertEquals("Empty string", new LineStat(null, null, 0, 0), instance.analizeLine(""));
        assertEquals("One space", new LineStat(null, null, 1, 0), instance.analizeLine(" "));
        assertEquals("even spaces", new LineStat(null, null, 2, 0), instance.analizeLine("  "));
        assertEquals("odd spaces", new LineStat(null, null, 5, 0), instance.analizeLine("     "));
        assertEquals("letter", new LineStat("q", "q", 1, 1), instance.analizeLine("q"));
        assertEquals("letter left space", new LineStat("q", "q", 2, 1), instance.analizeLine(" q"));
        assertEquals("letter right space", new LineStat("q", "q", 2, 1), instance.analizeLine("q "));
        assertEquals("letter + tab is one word", new LineStat("q\t", "q\t", 2, 2), instance.analizeLine("q\t"));

        assertEquals(new LineStat("tree", "dog", 8, 3.5), instance.analizeLine("dog tree"));
        assertEquals(new LineStat("tree", "dog", 11, 3.5), instance.analizeLine("   dog tree"));
        assertEquals(new LineStat("tree", "dog", 9, 3.5), instance.analizeLine("dog tree "));
        assertEquals(new LineStat("tree", "dog", 10, 3.5), instance.analizeLine("dog  tree "));

        LineStat ls = instance.analizeLine("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi.");
        System.out.println(ls);
    }

}
