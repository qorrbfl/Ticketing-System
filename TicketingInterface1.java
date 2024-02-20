import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.*;

public class TicketingInterface1 extends JFrame {

    private String selectedDate;
    private JTextArea summaryTextArea;
    private JButton selectedButton;
    protected String selectionDetails = "선택 내역\n날짜: \n시간: \n매수: \n좌석: \n";
    private String paymentDetails = "결제 내역\n티켓 금액: \n예매 수수료: \n배송료: \n총금액(+): \n"; // 총금액은 빨간색으로 표시해야 하므로 별도 처리 필요
    private String discountDetails = "할인 내역\n할인금액: \n할인 쿠폰: \n모바일 머니: \n총할인금액(-): \n";
    private String totalAmount = "최종 결제 금액\n";

    private String selectedPeopleCount;
    private String selectedSeat;
    private String selectedDifficulty;
    private static StartInterface startInterface;

    public TicketingInterface1(String selectedPeopleCount,String selelctedSeat,StartInterface startInterface, String selectedDifficulty) {
        this.startInterface = startInterface;
        this.selectedDate = selectedDate;
        this.selectedPeopleCount = selectedPeopleCount;
        this.selectedSeat = selectedSeat;
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
        step1Label.setBackground(Color.WHITE); // step1 is selected
        step2Label.setBackground(greyColor);
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

        // 중앙 패널 생성 및 설정
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 달력 섹션
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7, 0, 0));
        calendarPanel.setMinimumSize(new Dimension(300, 400));

        String[] headers = {"일", "월", "화", "수", "목", "금", "토"};
        for (String header : headers) {
            calendarPanel.add(new JLabel(header, SwingConstants.CENTER));
        }

        for (int i = 1; i <= 30; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            if (i == 14 || i == 15 || i == 16) {
                // Set gray background for specific dates
                dayButton.setBackground(Color.LIGHT_GRAY);
            }else{
                dayButton.setEnabled(false);
            }
            dayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    if (selectedButton != null && selectedButton != sourceButton) {
                        selectedButton.setBackground(Color.LIGHT_GRAY);
                    }
                    selectedButton = sourceButton;
                    selectedDate = sourceButton.getText()+"일";
                    sourceButton.setBackground(Color.RED);
                }
            });
            calendarPanel.add(dayButton);
        }
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 선택 사항 섹션
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        JLabel optionLabel = new JLabel("선택사항");
        optionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(optionLabel);

        JTextField dateTextField = new JTextField("2024년 1월");
        dateTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateTextField.getPreferredSize().height));
        dateTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.add(dateTextField);

        // 가격 선택 패널
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] listData = {"V석 132,000원", "R석 112,000원", "S석 88,000원", "A석 66,000원"};
        JList<String> priceList = new JList<>(listData);
        priceList.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane listScroller = new JScrollPane(priceList);
        listScroller.setAlignmentX(Component.CENTER_ALIGNMENT);
        pricePanel.add(listScroller);

        // 전송 버튼
        JButton submitButton = new JButton("좌석선택");
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.WHITE);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedSeat = (String)priceList.getSelectedValue();
                // 선택된 좌석을 summaryTextArea에 표시
                String selectionDetails = "선택 내역\n날짜:" + (selectedDate != null ? selectedDate : "") + "\n시간: 8:00\n매수: 2장\n좌석: " + (selectedSeat != null ? selectedSeat : "") + "\n";
                summaryTextArea.setText(selectionDetails + "----------------\n" + paymentDetails + "----------------\n" + discountDetails);
            }
        });
        pricePanel.add(submitButton);

        // optionsPanel에 pricePanel 추가
        optionsPanel.add(pricePanel);

        // 요약 섹션
        summaryTextArea = new JTextArea();
        summaryTextArea.setEditable(false);

        // 선택 내역 표시를 위한 문자열 생성
        //String selectionDetails = "선택 내역\n날짜: \n시간: \n매수: \n좌석: \n";
        String paymentDetails = "결제 내역\n티켓 금액: \n예매 수수료: \n배송료: \n총금액(+): \n";
        String discountDetails = "할인 내역\n할인금액: \n할인 쿠폰: \n모바일 머니: \n총할인금액(-): \n";
        String totalAmount = "최종 결제 금액\n"; // 최종 결제 금액은 별도의 텍스트 필드에 표시할 수 있음

        // 각 섹션을 summaryTextArea에 추가
        summaryTextArea.setText(selectionDetails + "----------------\n" + paymentDetails + "----------------\n" + discountDetails);

        // 총금액을 빨간색으로 표시하기 위한 별도의 라벨 사용
        JLabel totalAmountLabel = new JLabel("총금액(+): ");
        totalAmountLabel.setForeground(Color.RED);
        JPanel totalAmountPanel = new JPanel(new BorderLayout());
        totalAmountPanel.add(totalAmountLabel, BorderLayout.NORTH);

        // 최종 결제 금액을 표시하는 텍스트 필드
        JTextField totalPaymentField = new JTextField();
        totalAmountPanel.add(totalPaymentField, BorderLayout.CENTER);


        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        summaryScrollPane.setPreferredSize(new Dimension(150,400));

        // 섹션 패널을 중앙 패널에 추가
        centerPanel.add(summaryScrollPane, BorderLayout.EAST);
        centerPanel.add(totalAmountPanel, BorderLayout.SOUTH);
        centerPanel.add(calendarPanel, BorderLayout.WEST);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);
        centerPanel.add(summaryScrollPane, BorderLayout.EAST);

        // 하단 패널 생성 및 설정
        JPanel bottomPanel = new JPanel();
        JButton nextButton = new JButton("다음으로");
        nextButton.setBackground(new Color(204,204,255));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSeat = (String)priceList.getSelectedValue();
                new TicketingInterface2(selectedDate, selectedPeopleCount, selectedSeat,startInterface, selectedDifficulty);
                dispose();
            }
        });
        bottomPanel.add(nextButton);

        // 패널들을 메인 프레임에 추가
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 인터페이스를 화면에 표시
        setLocationRelativeTo(null);
        setVisible(true);
    }

}