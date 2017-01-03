/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Рома
 */
public class CharStreamSource implements LinesSource {

    BufferedReader reader;

    public CharStreamSource(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public String getNewLine() throws IOException {
        return reader.readLine();
    }

}
