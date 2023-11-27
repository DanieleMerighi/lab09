package it.unibo.mvc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("Titolo inutile");

    public SimpleGUIWithFileChooser(Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel barPanel = new JPanel();
        barPanel.setLayout(new BorderLayout());
        final JTextField txtPath = new JTextField(controller.getPathAsString());
        txtPath.setEditable(false);
        final JButton btnBrowse = new JButton("Browse...");
        btnBrowse.addActionListener((event) -> {
            JFileChooser fileChooser = new JFileChooser("Choose where to save");
            fileChooser.setSelectedFile(controller.getFile());
            final var res = fileChooser.showSaveDialog(frame);
            switch (res) {
                case JFileChooser.APPROVE_OPTION:
                    controller.setFile(fileChooser.getSelectedFile());
                    txtPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "An error has occured", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });
        final JTextArea text = new JTextArea();
        final JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.saveFile(text.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        barPanel.add(txtPath, BorderLayout.CENTER);
        barPanel.add(btnBrowse, BorderLayout.LINE_END);
        canvas.add(barPanel, BorderLayout.NORTH);
        canvas.add(text, BorderLayout.CENTER);
        canvas.add(btnSave, BorderLayout.SOUTH);
        frame.setContentPane(canvas);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) screen.getWidth() / 2, (int) screen.getHeight() / 2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        final SimpleGUIWithFileChooser s = new SimpleGUIWithFileChooser(new Controller());
    }

}
