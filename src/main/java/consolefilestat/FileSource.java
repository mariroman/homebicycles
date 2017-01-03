/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Рома
 */
public class FileSource implements LinesSource {

    public FileSource(FileReader fr) {
        this.fr = new BufferedReader(fr);
    }

    private BufferedReader fr;
    @Override
    public String getNewLine() throws IOException {
        return fr.readLine();
    }
    
}
