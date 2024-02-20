import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class TicketingInterface3 extends JFrame {

    private Timer timer;
    private StartInterface startInterface;
    private String selectedDate;
    private String selectedPeopleCount;
    private String selectedSeat;
    private String selectedDifficulty;

    //버튼 크기 조정
    private static JButton createButton(String path, String path_over, String selectedDifficulty) {
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

    public TicketingInterface3(String selectedArea, String selectedDate, String selectedPeopleCount, String selectedSeat, StartInterface startInterface, String selectedDifficulty) {
        this.startInterface = startInterface;
        this.selectedDate = selectedDate != null ? selectedDate : "15일";
        this.selectedPeopleCount = selectedPeopleCount != null ? selectedPeopleCount : "2";
        this.selectedSeat = selectedSeat != null ? selectedSeat : "V석";
        this.selectedDifficulty = selectedDifficulty; // 추가


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

        JButton[][] buttons = new JButton[17][20];
        Random rand = new Random();

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

        //좌석
        // 메인 패널 레이아웃 변경

        // 좌석 그리드 ⭐⭐⭐⭐
        JPanel gridPanel0 = new JPanel();
        gridPanel0.setLayout(new FlowLayout());
        gridPanel0.setBackground(Color.WHITE);

        JPanel gridPanel = new JPanel(new GridLayout(17, 20));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        gridPanel.setBackground(Color.WHITE);

        // 클릭한 버튼 개수를 추적하기 위한 변수
        final int[] clickedButtonCount = {0};
        ArrayList<JButton> selectedButtons = new ArrayList<>();

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(20, 20)); // 버튼 크기 고정
                button.setBackground(new Color(180,100,250));
                LineBorder lb = new LineBorder(Color.WHITE);
                button.setBorder(lb);

                // 버튼 클릭
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton)e.getSource();
                        if(clickedButton.getBackground().equals(new Color(180, 100, 250)) && clickedButtonCount[0] < startInterface.getSelectedNumber() && !clickedButton.getText().equals("✔")){
                            clickedButton.setText("✔");
                            clickedButtonCount[0]++;
                            selectedButtons.add(clickedButton);
                        } else if(clickedButton.getBackground().equals(new Color(180, 100, 250)) && clickedButtonCount[0] >= startInterface.getSelectedNumber() && !clickedButton.getText().equals("✔")){
                            JOptionPane.showMessageDialog(null, startInterface.getSelectedNumber() + "개의 좌석만 선택할 수 있습니다.");
                        } else if(clickedButton.getText().equals("✔")){
                            JOptionPane.showMessageDialog(null, "다른 좌석을 선택해주세요.");
                        } else {
                            JOptionPane.showMessageDialog(null, "이미 선택된 좌석입니다.");
                        }
                    }
                });


                buttons[i][j] = button;
                gridPanel.add(button);

            }
        }

        timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int availableSeats = 0;
                for(int i=0; i<17; i++) {
                    for(int j=0; j<20; j++) {
                        JButton button = buttons[i][j];
                        if(!button.getBackground().equals(Color.LIGHT_GRAY) && !selectedButtons.contains(button)){
                            availableSeats++;
                        }
                    }
                }

                // 체크표시된 좌석이 하나라도 있으면 실패 팝업이 뜨지 않도록 수정
                if(availableSeats == 0 && selectedButtons.isEmpty()) {
                    timer.stop();  // 타이머 멈춤

                    // 팝업 설정
                    JDialog dialog = new JDialog();
                    dialog.setModal(true);
                    dialog.setSize(300, 100);
                    dialog.setLocationRelativeTo(null);
                    dialog.setLayout(new FlowLayout());

                    // 팝업에 레이블 추가
                    JLabel label = new JLabel("실패! 예약 가능한 좌석이 없습니다.");
                    dialog.add(label);

                    // 팝업에 버튼 추가
                    JButton retryButton = new JButton("처음으로");
                    retryButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            timer.stop(); // 타이머 멈춤
                            dialog.dispose();
                            new StartInterface(); // StartInterface로 이동
                            dispose();
                        }
                    });

                    dialog.add(retryButton);
                    dialog.setVisible(true);

                    return;
                }

                // 좌석 사라지는 개수 설정
                for(int i=0; i<20; i++) {
                    int row = rand.nextInt(17);
                    int col = rand.nextInt(20);
                    JButton button = buttons[row][col];
                    if(!button.getBackground().equals(Color.LIGHT_GRAY) && !selectedButtons.contains(button)){
                        button.setBackground(Color.LIGHT_GRAY);
                    }
                }
            }
        });

        timer.start();

        gridPanel0.add(gridPanel);
        full_map.add(stage_label);
        full_map.add(gridPanel0);
        //⭐⭐⭐⭐

        //우측 패널 선언----------------------------------------------------------
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //공연장 전체보기 버튼
        ImageIcon img_full_map = new ImageIcon("image/full_map.png");
        JButton full_map_btn = new JButton(img_full_map);
        full_map_btn.setBorderPainted(false);
        full_map_btn.setBackground(new Color(234,234,234));
        full_map_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface2(selectedDate, selectedPeopleCount, selectedSeat,startInterface, selectedDifficulty);
                dispose();
            }
        });

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


        //---------------------------------------------------


        //이전화면, 좌석 다시선택
        JButton z_button = new JButton("이전화면");
        z_button.setBorder(BorderFactory.createEmptyBorder(5, 110, 5, 110));
        z_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface2(selectedDate, selectedPeopleCount, selectedSeat,startInterface, selectedDifficulty);
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
                new TicketingInterface4(selectedDate, selectedPeopleCount, selectedSeat, selectedDifficulty);  // 수정: 난이도 추가
                dispose();
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

        //------------------------------------------------------------------

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
