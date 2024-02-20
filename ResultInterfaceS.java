import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultInterfaceS extends JFrame {
    private long startTime;
    private long endTime;

    public ResultInterfaceS(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

        Font myFont1 = new Font(null, Font.BOLD, 30);
        LineBorder lb = new LineBorder(Color.BLACK);

        setTitle("티켓팅 시스템");
        setSize(1074, 706);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.white);

        JLabel startlb = new JLabel("연습 결과");
        startlb.setFont(myFont1);
        startlb.setBounds(445, 100, 300, 40);
        mainPanel.add(startlb);

        JPanel Level = new JPanel();
        Level.setBackground(Color.WHITE);


        mainPanel.add(Level);
        Level.setBounds(360, 350, 300, 40);


        JLabel centerLabel = new JLabel("성공");
        centerLabel.setFont(new Font(null, Font.PLAIN, 40));
        centerLabel.setBounds(460, 280, 100, 40);
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(centerLabel);



        JButton start_btn = new JButton("다시 시작");
        start_btn.setBackground(new Color(204,204,255));
        start_btn.setBounds(460, 440, 100, 40);
        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartInterface();
                dispose();
            }
        });

        mainPanel.add(start_btn);
        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResultInterfaceS(System.currentTimeMillis(), System.currentTimeMillis() + 5000));
    }
}