import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class TicketingInterface2 extends JFrame {
    private static StartInterface startInterface;
    private String selectedDate;
    private String selectedSeat;
    private String selectedPeopleCount;
    private String selectedDifficulty;

    //버튼 크기 조정
    private static JButton createButton(String path, String path_over) {
        ImageIcon img = new ImageIcon(path);
        ImageIcon img_over = new ImageIcon(path_over);



        int originalWidth = img.getIconWidth();
        int originalHeight = img.getIconHeight();

        int scaledWidth = (int)(originalWidth * 0.3);
        int scaledHeight = (int)(originalHeight * 0.3);

        Image scaledImage = img.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        Image scaledImageOver = img_over.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        ImageIcon scaledIconOver = new ImageIcon(scaledImageOver);

        JButton button = new JButton(scaledIcon);
        button.setRolloverIcon(scaledIconOver);
        button.setBorderPainted(false);

        return button;
    }

    public TicketingInterface2(String selectedDate, String selectedPeopleCount,String selectedSeat, StartInterface startInterface, String selectedDifficulty) {
        this.startInterface = startInterface;
        this.selectedDifficulty = selectedDifficulty;

        setTitle("티켓팅 시스템");
        setSize(1074, 706);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 스텝 섹션
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("티켓팅 시스템"));

        // Initialize all step labels with grey background
        JLabel step1Label = new JLabel(" step1 관람일/회차 ");
        JLabel step2Label = new JLabel(" step2 좌석선택 ");
        JLabel step3Label = new JLabel(" step3 할인/쿠폰 ");
        JLabel step4Label = new JLabel(" step4 수령방법 ");
        JLabel step5Label = new JLabel(" step5 결제방법 ");

        // Set background color and make it opaque to show the background
        step1Label.setOpaque(true);
        step2Label.setOpaque(true);
        step3Label.setOpaque(true);
        step4Label.setOpaque(true);
        step5Label.setOpaque(true);

        // Set white background for step1 and grey for others
        Color greyColor = new Color(200, 200, 200); // RGB for grey color
        step1Label.setBackground(greyColor); // step1 is selected
        step2Label.setBackground(Color.WHITE);
        step3Label.setBackground(greyColor);
        step4Label.setBackground(greyColor);
        step5Label.setBackground(greyColor);

        // Set border if needed
        Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        step1Label.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
        step2Label.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
        step3Label.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
        step4Label.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
        step5Label.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));

        // Add the labels to the panel
        topPanel.add(step1Label);
        topPanel.add(step2Label);
        topPanel.add(step3Label);
        topPanel.add(step4Label);
        topPanel.add(step5Label);

        topPanel.setMaximumSize(new Dimension(800, 15));


        //메인 패널 선언
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(234,234,234));

        mainPanel.add(topPanel);

        // 관람일*회차 변경 패널
        JPanel topPanel2 = new JPanel();
        topPanel2.setBackground(greyColor);
        topPanel2.setMaximumSize(new Dimension(800, 65));
        topPanel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel label_DateChange = new JLabel("관람일 변경 ");
        Choice DateChange = new Choice();
        DateChange.add(" 2024.01.14 SUN ");
        DateChange.add(" 2024.01.15 MON ");
        DateChange.add(" 2024.01.16 TUE ");
        Component horizontalStrut = Box.createHorizontalStrut(30);
        JLabel label_SessionChange = new JLabel("회차 변경 ");
        Choice SessionChange = new Choice();
        SessionChange.add(" 20:00 ");

        topPanel2.add(label_DateChange);
        topPanel2.add(DateChange);
        topPanel2.add(horizontalStrut);
        topPanel2.add(label_SessionChange);
        topPanel2.add(SessionChange);


        //전체 맵 - STAGE, 좌석맵 담는 패널 ------------------------------------------
        JPanel full_map = new JPanel();
        full_map.setLayout(new BoxLayout(full_map, BoxLayout.Y_AXIS));
        full_map.setBackground(Color.WHITE);

        //STAGE 이미지
        ImageIcon originalIcon = new ImageIcon("image/stage.png");
        Image originalImage = originalIcon.getImage();
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();
        int scaledWidth = (int)(originalWidth * 0.3);
        int scaledHeight = (int)(originalHeight * 0.3);
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel stage_label = new JLabel(scaledIcon);
        stage_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        stage_label.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));

        //좌측 2*2 좌석 미니맵 패널
        JPanel minimap = new JPanel();
        minimap.setBorder(BorderFactory.createEmptyBorder(0, 40, 60, 40));
        minimap.setLayout(new GridLayout(2,2));
        minimap.setBackground(Color.WHITE);

        JButton btn_Ga = createButton("image/ga.png", "image/ga_over.png");
        JButton btn_Na = createButton("image/na.png", "image/na_over.png");
        JButton btn_Da = createButton("image/da.png", "image/da_over.png");
        JButton btn_La = createButton("image/la.png", "image/la_over.png");

        // 생성한 버튼들에 동일한 배경색과 ActionListener 적용
        JButton[] buttons = {btn_Ga, btn_Na, btn_Da, btn_La};
        for (JButton button : buttons) {
            button.setBackground(Color.WHITE);
            minimap.add(button);
        }



        //우측 패널 선언----------------------------------------------------------
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //공연장 전체보기 버튼
        ImageIcon img_full_map = new ImageIcon("image/full_map.png");
        JButton full_map_btn = new JButton(img_full_map);
        full_map_btn.setBorderPainted(false);
        full_map_btn.setBackground(new Color(234,234,234));

        //좌석등급/가격
        JLabel label2 = new JLabel("🔽좌석등급/가격");
        label2.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));
        JLabel selection = new JLabel("<html>V석 132,000원<br/>R석 112,000원<br/>S석 88,000원<br/>A석 66,000원<br/></html>");

        selection.setOpaque(true);
        selection.setBackground(Color.WHITE);
        selection.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 40));

        //선택한 좌석
        JLabel label3 = new JLabel("🔽선택한 좌석");
        label3.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 0));
        JLabel selection2 = new JLabel("<html>선택한 좌석</html>");
        selection2.setOpaque(true);
        selection2.setBackground(Color.WHITE);
        selection2.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 40));


        //___________버튼에 따라 바뀌게_______________
        // '가 구역' 버튼 액션 리스너
        btn_Ga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface3("가 구역",selectedDate, selectedPeopleCount, selectedSeat, startInterface, selectedDifficulty);
                dispose();
            }
        });

// '나 구역' 버튼 액션 리스너
        btn_Na.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface3("나 구역",selectedDate,selectedPeopleCount,selectedSeat, startInterface, selectedDifficulty);
                dispose();
            }
        });

// '다 구역' 버튼 액션 리스너
        btn_Da.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface3("다 구역",selectedDate,selectedPeopleCount,selectedSeat, startInterface, selectedDifficulty);
                dispose();
            }
        });

// '라 구역' 버튼 액션 리스너
        btn_La.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface3("라 구역",selectedDate,selectedPeopleCount,selectedSeat, startInterface, selectedDifficulty);
                dispose();
            }
        });


        // 전체 맵에 추가
        full_map.add(stage_label);
        full_map.add(minimap);
        //_________________________________________




        //이전화면, 좌석 다시선택
        JButton z_button = new JButton("이전화면");
        z_button.setBorder(BorderFactory.createEmptyBorder(5, 110, 5, 110));
        z_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface1(selectedPeopleCount,selectedSeat,startInterface, selectedDifficulty);
                dispose();
            }
        });


        JButton re_button = new JButton("🔄️좌석 다시선택");
        re_button.setBorder(BorderFactory.createEmptyBorder(5, 90, 5, 90));

        //좌석선택 완료 버튼
        ImageIcon img_done = new ImageIcon("image/done.png");
        JButton doneButton = new JButton(img_done);
        doneButton.setBorderPainted(false);
        doneButton.setBounds(360,350,300,40);
        doneButton.setBackground(new Color(234,234,234));
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "좌석선택을 완료해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            }
        });



        //rightPanel 목록
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        rightPanel.add(full_map_btn);
        rightPanel.add(label2);
        rightPanel.add(selection);
        rightPanel.add(label3);
        rightPanel.add(selection2);
        //rightPanel.add(miniButtonPanel);
        rightPanel.add(z_button);
        rightPanel.add(re_button);
        rightPanel.add(doneButton);


        //우측 패널--------------------------------------------------------------


        //메인패널에 추가

        mainPanel.add(topPanel2);
        mainPanel.add(full_map);

        //프레임 추가
        add(mainPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);




        // 프레임 창 띄우기
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
