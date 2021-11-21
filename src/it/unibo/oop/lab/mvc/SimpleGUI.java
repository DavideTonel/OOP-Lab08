package it.unibo.oop.lab.mvc;

import java.util.List;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller; 
    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     * @param controller
     *                  the controller instance
     */
    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        // Canvas
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        // Lower panel
        final JPanel lower = new JPanel();
        lower.setLayout(new BoxLayout(lower, BoxLayout.LINE_AXIS)); 
        // Text field
        final JTextField field = new JTextField();
        field.setBackground(Color.lightGray);
        // Text area
        final JTextArea text = new JTextArea();
        text.setEditable(false);
        // Print button
        final JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.controller.setNextStringToPrint(field.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
        });
        // Show history button
        final JButton history = new JButton("Show History");
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder sb = new StringBuilder();
                final List<String> list = SimpleGUI.this.controller.getHistoryPrintedString();
                for (final String print : list) {
                    sb.append(print);
                    sb.append('\n');
                }
                if (!list.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                text.setText(sb.toString());
            }
        });
        // Assemble
        lower.add(print);
        lower.add(history);
        canvas.add(lower, BorderLayout.SOUTH);
        canvas.add(field, BorderLayout.NORTH);
        canvas.add(text, BorderLayout.CENTER);
        frame.setContentPane(canvas);
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }

    public void display() {
        frame.setVisible(true);
    }

    /**
     * 
     * @param arg
     *           ignored
     */
    public static void main(final String...arg) {
        new SimpleGUI(new SimpleController()).display();
    }

}
