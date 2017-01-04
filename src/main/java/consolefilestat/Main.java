/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Рома
 */
public class Main {

    static final String usageTip = "Usage: java -jar ConsoleFileStat.jar <full_path_to_analized_text_file> [showall]";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Not enough comand-line params. " + usageTip);
            return;
        }
        String fileName = args[1];
        try {
            FileSource fs = new FileSource(new FileReader(fileName));
            Analizator analizator = new Analizator(fs);

            analizator.analizeWholeLines();

            List<LineStat> stats = analizator.getStats();
            if (args.length > 2 && "showall".equalsIgnoreCase(args[2])) {
                for (LineStat ls : stats) {
                    System.out.println(ls.prettyToString());
                }
            }
            Persistence pers = new Persistence();
            pers.saveLineStats(stats);

            LineStat sumStat = analizator.getSummarizedStat();
            System.out.println("File statistic: " + sumStat.prettyToString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            System.out.println("File " + fileName + " not found");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
    
}
