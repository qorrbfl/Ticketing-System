// TimerControlApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerControlApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartInterface());
    }
}