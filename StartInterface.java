import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class StartInterface extends JFrame {
    Font myFont1 = new Font(null, Font.BOLD, 30);
    LineBorder lb = new LineBorder(Color.BLACK);
    private int selectedNumber = 1;
    private String selectedSeat;
    private String selectedDifficulty;
    private Choice num; // 수정: Choice를 클래스 레벨로 옮김

    public StartInterface() {
        setTitle("티켓팅 시스템");
        setSize(1074, 706);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.white);

        JLabel startlb = new JLabel("티켓팅 연습 시스템");
        startlb.setFont(myFont1);
        startlb.setBounds(380,100,300,40);
        mainPanel.add(startlb);

        JLabel warn = new JLabel("<html>유의사항<br/> - 본 시스템은 실제가 아닌 연습용 시뮬레이션입니다.<br/> - 실제 예매 시 시작 전, 로그인 및 본인인증을 완료해주세요.<br/> - 본 시스템은 개인정보를 저장하지 않습니다.</html>");
        warn.setBounds(380,200,400,100);
        mainPanel.add(warn);

        // 난이도
        JPanel Level = new JPanel();
        Level.setBackground(Color.WHITE);
        JLabel level_lb = new JLabel("난이도 선택");
        CheckboxGroup level_gp = new CheckboxGroup();
        Checkbox easy = new Checkbox("easy", level_gp, true);
        Checkbox normal = new Checkbox("normal", level_gp, false);
        Checkbox hard = new Checkbox("hard", level_gp, false);

        easy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedDifficulty = "easy";
            }
        });

        normal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedDifficulty = "normal";
            }
        });

        hard.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedDifficulty = "hard";
            }
        });

        Level.add(level_lb);
        Level.add(easy);
        Level.add(normal);
        Level.add(hard);

        // 인원수 선택
        JPanel People_num = new JPanel();
        People_num.setBackground(Color.WHITE);
        JLabel people_num = new JLabel("인원수 선택 ");
        num = new Choice();  // 수정: Choice를 클래스 레벨로 옮김
        num.add("1");
        num.add("2");
        People_num.add(people_num);
        People_num.add(num);

        num.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectedNumber = Integer.parseInt(e.getItem().toString());
                // 디버그 메시지 출력
                System.out.println("Item state changed. Selected number: " + selectedNumber);
            }
        });

        // mainPanel에 Level 패널 추가
        mainPanel.add(Level);
        Level.setBounds(360,350,300,40);
        mainPanel.add(People_num);
        People_num.setBounds(360,390,300,40);

        JButton start_btn = new JButton("시작하기");
        start_btn.setBounds(460, 440, 100, 40);
        start_btn.setBackground(new Color(204,204,255));
        start_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPeopleCount = num.getSelectedItem();
                // 수정: selectedSeat 설정 필요
                new TicketingInterface1(selectedPeopleCount, selectedSeat, StartInterface.this,selectedDifficulty);

                long startTime = System.currentTimeMillis();
                System.out.println("시작 시간: " + startTime);

                dispose();
            }
        });

        JButton url = new JButton("실전으로");
        url.setBounds(460, 500, 100, 40);
        url.setBackground(new Color(204,204,255));
        url.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new URLInterface();
                dispose();
            }
        });

        mainPanel.add(start_btn);
        mainPanel.add(url);
        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public int getSelectedNumber() {
        return this.selectedNumber;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StartInterface();
            }
        });
    }
}
