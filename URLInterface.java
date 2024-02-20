import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class URLInterface {

    public URLInterface() {
        createAndShowGUI();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new URLInterface();  // createAndShowGUI() 호출 대신 생성자 호출
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("티켓팅 시스템");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1074, 706);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(null);

        JButton yes24Button = createWebButton("Yes24티켓", "http://ticket.yes24.com/New/Main.aspx", 360, 140, 300, 60);
        JButton interparkButton = createWebButton("인터파크", "https://tickets.interpark.com/", 360, 240, 300, 60);
        JButton melonButton = createWebButton("멜론티켓", "https://ticket.melon.com/main/index.htm", 360, 340, 300, 60);
        JButton startButton = createStartButton("처음으로", frame, 360, 500, 300, 60);

        yes24Button.setBackground(new Color(153,204,255));
        interparkButton.setBackground(new Color(255,102,102));
        melonButton.setBackground(new Color(153,255,102));
        startButton.setBackground(new Color(204,204,255));

        panel.add(yes24Button);
        panel.add(interparkButton);
        panel.add(melonButton);
        panel.add(startButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }



    private static JButton createWebButton(String label, final String url, int x, int y, int width, int height) {
        JButton button = new JButton(label);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage(url);
            }
        });
        return button;
    }

    private static JButton createStartButton(String label, JFrame frame, int x, int y, int width, int height) {
        JButton button = new JButton(label);
        button.setBounds(360, 440, 300, 60);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartInterface();
                frame.dispose();
            }
        });
        return button;
    }

    private static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}