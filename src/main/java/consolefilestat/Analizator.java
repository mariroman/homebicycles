/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Рома
 */
public class Analizator {

    private LinesSource lineSource;
    private List<LineStat> stats = new ArrayList<LineStat>();
    private LineStat summarizedStat;

    public Analizator(LinesSource lineSource) {
        this.lineSource = lineSource;
    }

    public void analizeWholeLines() throws IOException {
        summarizedStat = new LineStat("", "", 0, 0);
        String line;
        while ((line = lineSource.getNewLine()) != null) {
            LineStat ls = analizeLine(line);
            stats.add(ls);
            String longest = ls.getLongest();

            if (longest != null && longest.length() > summarizedStat.getLongest().length()) {
                summarizedStat.setLongest(longest);
            }
            String shortest = ls.getShortest();
            if (shortest != null && (summarizedStat.getShortest().isEmpty() || shortest.length() < summarizedStat.getShortest().length())) {
                summarizedStat.setShortest(shortest);
            }
            summarizedStat.setLehgth(summarizedStat.getLehgth() + ls.getLehgth());
            summarizedStat.setAvgWordLen(summarizedStat.getAvgWordLen() + ls.getAvgWordLen());
        }
        if (!stats.isEmpty()) {
            summarizedStat.setAvgWordLen(summarizedStat.getAvgWordLen() / stats.size());
        }
    }

    public List<LineStat> getStats() {
        return stats;
    }

    public LineStat analizeLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line must not be null");
        }
        int len = line.length();
        int wordsCount = 0, sumOfWordLength = 0;
        String longest = null, shortest = null;
        StringBuilder word = new StringBuilder(50);
        char c;
        boolean isSpace;
        for (int i = 0; i < len; i++) {
            c = line.charAt(i);
            isSpace = Character.isSpaceChar(c);
            if (!isSpace) {
                word.append(c);
            }

            if ((isSpace || i + 1 >= len) && word.length() != 0) {
                if (longest == null || longest.length() < word.length()) {
                    longest = word.toString();
                }
                if (shortest == null || shortest.length() > word.length()) {
                    shortest = word.toString();
                }
                sumOfWordLength += word.length();
                wordsCount++;
                word.setLength(0);
            }
        }

        return new LineStat(longest, shortest, len, wordsCount == 0 ? 0 : (double) sumOfWordLength / wordsCount);
    }

    public LineStat getSummarizedStat() {
        if (summarizedStat == null) {
            throw new IllegalStateException("You should call analizeWholeLines() first");
        }
        return summarizedStat;
    }

}
