/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.util.Objects;

/**
 *
 * @author Рома
 */
public class LineStat {

    //longest word(symbols between 2 spaces)
    private String longest;
    //shortest word
    private String shortest;
    //line length
    private int lehgth;
    //average word length
    private double avgWordLen;

    public LineStat(String longest, String shortest, int lehgth, double avgWordLen) {
        this.longest = longest;
        this.shortest = shortest;
        this.lehgth = lehgth;
        this.avgWordLen = avgWordLen;
    }

    public String getLongest() {
        return longest;
    }

    public void setLongest(String longest) {
        this.longest = longest;
    }

    public String getShortest() {
        return shortest;
    }

    public void setShortest(String shortest) {
        this.shortest = shortest;
    }

    public int getLehgth() {
        return lehgth;
    }

    public void setLehgth(int lehgth) {
        this.lehgth = lehgth;
    }

    public double getAvgWordLen() {
        return avgWordLen;
    }

    public void setAvgWordLen(double avgWordLen) {
        this.avgWordLen = avgWordLen;
    }

    @Override
    public String toString() {
        return "LineStat{" + "longest=" + longest + ", shortest=" + shortest + ", lehgth=" + lehgth + ", avgWordLen=" + avgWordLen + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.longest);
        hash = 43 * hash + Objects.hashCode(this.shortest);
        hash = 43 * hash + this.lehgth;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.avgWordLen) ^ (Double.doubleToLongBits(this.avgWordLen) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineStat other = (LineStat) obj;
        if (this.lehgth != other.lehgth) {
            return false;
        }
        if (Double.doubleToLongBits(this.avgWordLen) != Double.doubleToLongBits(other.avgWordLen)) {
            return false;
        }
        if (!Objects.equals(this.longest, other.longest)) {
            return false;
        }
        if (!Objects.equals(this.shortest, other.shortest)) {
            return false;
        }
        return true;
    }

    public String prettyToString() {
        return "Longest word(" + longest + "), shortest word(" + shortest + "), whole length(" + lehgth + "), average word length(" + avgWordLen + ")";
    }
}
