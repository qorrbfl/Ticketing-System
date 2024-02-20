import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.*;

public class TicketingInterface5 extends JFrame {
    private JTextArea summaryTextArea;
    private JRadioButton getRadioButton, plusgetRadioButton;
    private ButtonGroup getGroup;
    private JPanel leftPanel;
    private JTextField totalPaymentField;
    private int deliveryFee;
    private int totalAmount;
    private int ticketdelivery;
    private String selectedDifficulty;
    public TicketingInterface5(String selectedDate, String selectedPeopleCount, String selectedSeat, int totalTicketPrice, String selectedDifficulty) {
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
        step2Label.setBackground(greyColor);
        step3Label.setBackground(greyColor);
        step4Label.setBackground(Color.WHITE);
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

        // 중앙 패널 생성 및 설정
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Selection Panel
        JPanel selectionPanel = new JPanel(new FlowLayout());
        getRadioButton = new JRadioButton("현장수령");
        plusgetRadioButton = new JRadioButton("일반우편수령(+2500원)");
        getGroup = new ButtonGroup();
        getGroup.add(getRadioButton);
        getGroup.add(plusgetRadioButton);
        selectionPanel.add(new JLabel("수령방법 선택: "));
        selectionPanel.add(getRadioButton);
        selectionPanel.add(plusgetRadioButton);
        plusgetRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFees(selectedDate, selectedPeopleCount, selectedSeat, totalTicketPrice, 2500);
            }
        });
        getRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFees(selectedDate, selectedPeopleCount, selectedSeat, totalTicketPrice, 0);
            }
        });


        leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(selectionPanel, BorderLayout.CENTER);
        centerPanel.add(leftPanel,BorderLayout.WEST);

        // 주문자 확인 섹션
        JPanel ordererInfoPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // 0은 가변 행을 의미합니다.
        ordererInfoPanel.setBorder(BorderFactory.createTitledBorder("주문자 확인"));

        // 이름 입력 필드
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("이름"));
        JTextField nameField = new JTextField(20);
        namePanel.add(nameField);

        // 긴급 연락처 입력 필드
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phonePanel.add(new JLabel("긴급연락처"));
        JTextField phoneField1 = new JTextField(3);
        JTextField phoneField2 = new JTextField(4);
        JTextField phoneField3 = new JTextField(4);
        phonePanel.add(phoneField1);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(phoneField2);
        phonePanel.add(new JLabel("-"));
        phonePanel.add(phoneField3);

        // 이메일 입력 필드
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("e-mail"));
        JTextField emailField1 = new JTextField(10);
        JTextField emailField2 = new JTextField(10);
        emailPanel.add(emailField1);
        emailPanel.add(new JLabel("@"));
        emailPanel.add(emailField2);

        // 주문자 정보 패널에 위의 세 패널을 추가
        ordererInfoPanel.add(namePanel);
        ordererInfoPanel.add(phonePanel);
        ordererInfoPanel.add(emailPanel);

        // 주의사항
        JTextArea cautionTextArea = new JTextArea(4, 10);
        cautionTextArea.setText("주의사항\n*입력정보는 티켓팅시뮬레이션을 위함이므로 가상의 정보도 가능합니다.\n"
                + "*사이트에 따라 주문자확인을 하지 않는 경우도 있습니다.");
        cautionTextArea.setEditable(false); // 편집 불가능하게 설정
        cautionTextArea.setBackground(new Color(200, 200, 200)); // 배경색을 회색으로 설정
        cautionTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // 내부 여백 설정

        // 센터 패널에 주문자 확인 섹션 추가
        centerPanel.add(ordererInfoPanel, BorderLayout.NORTH);
        centerPanel.add(cautionTextArea, BorderLayout.CENTER);
        JPanel topSectionPanel = new JPanel();
        topSectionPanel.setLayout(new BoxLayout(topSectionPanel, BoxLayout.Y_AXIS));
        topSectionPanel.add(selectionPanel);
        topSectionPanel.add(ordererInfoPanel);
        centerPanel.add(topSectionPanel, BorderLayout.NORTH);
        centerPanel.add(cautionTextArea, BorderLayout.CENTER);

        // 요약부분
        summaryTextArea = new JTextArea();
        summaryTextArea.setEditable(false);
        summaryTextArea.setText("선택 내역\n날짜:"+selectedDate+"\n시간:20:00 \n매수:"+selectedPeopleCount+"장\n좌석:"+selectedSeat+"\n" +
                "----------------\n" +
                "결제 내역\n티켓 금액:"+totalTicketPrice+"원\n예매 수수료:0원 \n배송료: \n총금액(+): \n" +
                "----------------\n" +
                "할인 내역\n할인금액:30000 \n할인 쿠폰:oop2024001 \n모바일 머니: 0원\n총할인금액(-):30000원 \n");
        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        summaryScrollPane.setPreferredSize(new Dimension(200, 400));

        // 총금액
        JLabel totalAmountLabel = new JLabel("총금액(+) ");
        totalAmountLabel.setForeground(Color.RED);
        totalPaymentField = new JTextField();
        totalPaymentField.setEditable(false);
        JPanel totalAmountPanel = new JPanel(new BorderLayout());
        totalAmountPanel.add(totalAmountLabel, BorderLayout.NORTH);
        totalAmountPanel.add(totalPaymentField, BorderLayout.CENTER);


        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(summaryScrollPane, BorderLayout.CENTER);
        rightPanel.add(totalAmountPanel, BorderLayout.SOUTH);
        centerPanel.add(rightPanel, BorderLayout.EAST);

        // 다음으로 버튼
        JPanel bottomPanel = new JPanel();
        JButton nextButton = new JButton("다음으로");
        nextButton.setBackground(new Color(204,204,255));
        bottomPanel.add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface6(selectedDate, selectedPeopleCount, selectedSeat, totalTicketPrice, deliveryFee, totalAmount,ticketdelivery, selectedDifficulty);
                dispose();
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void updateSummaryAndTotal(String selectedDate, String selectedPeopleCount, String selectedSeat, int totalTicketPrice,int deliveryFee,int ticketdelivery, int totalAmount) {
        summaryTextArea.setText("선택 내역\n날짜:"+selectedDate+" \n시간:20:00 \n매수:"+selectedPeopleCount+"장\n좌석:"+selectedSeat+"\n" +
                "----------------\n" +
                "결제 내역\n티켓 금액:"+totalTicketPrice+" \n예매 수수료:0원 \n배송료: " + deliveryFee + "원\n총금액(+): " + ticketdelivery+ "원\n" +
                "----------------\n" +
                "할인 내역\n할인금액:30000 \n할인 쿠폰:oop2024001 \n모바일 머니: 0원\n총할인금액(-):30000원 \n");

        // Update total payment field
        totalPaymentField.setText(String.valueOf(totalAmount) + "원");
    }
    private void updateFees(String selectedDate, String selectedPeopleCount, String selectedSeat, int totalTicketPrice, int newDeliveryFee) {
        this.deliveryFee = newDeliveryFee;
        int discount = 30000;
        this.totalAmount = totalTicketPrice + this.deliveryFee - discount;
        this.ticketdelivery = totalTicketPrice+deliveryFee;
        updateSummaryAndTotal(selectedDate,selectedPeopleCount,selectedSeat,totalTicketPrice,deliveryFee,ticketdelivery,totalAmount);  // 필요한 인자를 넣어 호출
    }
}