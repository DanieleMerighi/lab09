package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    public SimpleGUI(Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        final JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.saveFile(text.getText());
                } catch(IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        canvas.add(text, BorderLayout.CENTER);
        canvas.add(btnSave, BorderLayout.SOUTH);
        frame.setContentPane(canvas);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) screen.getWidth() / 2, (int) screen.getHeight() / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        final SimpleGUI s = new SimpleGUI(new Controller());
    }

}
