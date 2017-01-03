/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.io.IOException;

/**
 * 
 * @author Рома
 */
public interface LinesSource {
    
    
    /**
     * Get new line if exists
     * @return line or null if there is not any lines
     * @throws java.io.IOException
     */
    public String getNewLine() throws IOException;
    
}
