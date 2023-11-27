package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File currFile = new File(System.getProperty("user.home") + File.separator + "output.txt");

    public File getFile() {
        return currFile;
    }

    public void setFile(final File newFile) {
        final File p = newFile.getParentFile();
        if(p.exists()) {
            currFile = newFile;
        } else{
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    public String getPathAsString() {
        return currFile.getPath();
    }

    public void saveFile(final String s) throws IOException {
        try (PrintStream out = new PrintStream(currFile)) {
            out.println(s);
        }
    }

}
