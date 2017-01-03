/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.util.Collection;
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
public class PersistenceTest {

    public PersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass");
//        Persistence.DBdown();
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
     * Test of saveLineStat method, of class Persistence.
     */
    @Test
    public void testSaveLineStat() throws Exception {
        System.out.println("saveLineStat");
        Persistence instance = new Persistence();
        List<LineStat> ls = instance.selectLineStat();
        System.out.println(ls);
        instance.saveLineStat(new LineStat("Longet", "shortest", 34, 6.4424));
    }

    /**
     * Test of saveLineStats method, of class Persistence.
     */
//    @Test
    public void testSaveLineStats() throws Exception {
        System.out.println("saveLineStats");
        Collection<LineStat> col = null;
        Persistence instance = new Persistence();
        instance.saveLineStats(col);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
