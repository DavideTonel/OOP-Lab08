package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    /** The Constant HOME. */
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = System.getProperty("output.txt");

    private File file = new File(HOME + SEPARATOR + DEFAULT_FILE);

    /**
     * 
     * @return the current file
     */
    public File getCurrentFile() {
        return this.file;
    }

    /**
     * 
     * @return the path of the current file
     */
    public String getCurrentFilePath() {
        return this.file.getPath();
    }

    /**
     * 
     * @param dst
     *            the new file
     */
    public void setCurrentFile(final File dst) {
        final File parent = dst.getParentFile();
        if (parent.exists()) {
            this.file = dst;
        } else {
            throw new IllegalArgumentException("Folder does not extist.");
        }
    }

    /**
     * 
     * @param text
     *            the text to save
     * @throws IOException
     */
    public void save(final String text) throws IOException {
        try (PrintStream ps = new PrintStream(this.file)) {
            ps.println(text);
        }
    }
}
