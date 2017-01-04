/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    static Persistence instance;
    public PersistenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            instance = new Persistence();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
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
        LineStat exStat = new LineStat("Longet", "shortest", 34, 6.4424);
        instance.saveLineStat(exStat);
        List<LineStat> ls = instance.selectLineStat();
        assertFalse(ls.isEmpty());
        assertTrue(ls.contains(exStat));
        System.out.println(ls);
    }

    /**
     * Test of saveLineStats method, of class Persistence.
     */
    @Test
    public void testSaveLineStats() throws Exception {
        System.out.println("saveLineStats");
        Collection<LineStat> col = Arrays.asList(new LineStat("loooooooooooong", "srt", 34, 7.28), new LineStat("BIIIIIGWORD", "o", 225, 10.5));
        instance.saveLineStats(col);
        List<LineStat> selectLineStat = instance.selectLineStat();
        assertTrue(selectLineStat.containsAll(col));
    }

}
