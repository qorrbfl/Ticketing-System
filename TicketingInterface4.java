import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;

public class TicketingInterface4 extends JFrame {
    private JTextArea summaryTextArea;
    private JTable discountTable;
    private JTable couponTable;
    private JButton couponButton;
    private JTextField couponTextField;
    private ButtonGroup seatGroup;
    private JCheckBox firstSeatCheckBox,secondSeatCheckBox;
    private String selectedDate;
    private String selectedPeopleCount;
    private String selectedSeat;
    private String selectedDifficulty;
    private int getPriceBySeatType(String seatType) {
        if (seatType.startsWith("V")) {
            return 132000;
        } else if (seatType.startsWith("R")) {
            return 112000;
        } else if (seatType.startsWith("S")) {
            return 88000;
        } else if (seatType.startsWith("A")) {
            return 66000;
        } else {
            return 0; // 잘못된 좌석 유형인 경우
        }
    }


    public TicketingInterface4(String selectedDate, String selectedPeopleCount,String selectedSeat, String selectedDifficulty) {
        setTitle("티켓팅 시스템");
        setSize(1074, 706);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.selectedDate = selectedDate;
        this.selectedPeopleCount = selectedPeopleCount;
        this.selectedSeat = selectedSeat;
        this.selectedDifficulty = selectedDifficulty;

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
        step1Label.setBackground(greyColor);
        step2Label.setBackground(greyColor);
        step3Label.setBackground(Color.WHITE);// step3 is selected
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
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Selection Panel
        JLabel promoLabel = new JLabel("티켓팅 성공 기원 30000원 티켓 모든 구매자에게 제공!(쿠폰번호 : oop2024001)", SwingConstants.CENTER);
        promoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Font labelFont = promoLabel.getFont();
        float newSize = labelFont.getSize() * 1.5f;
        promoLabel.setFont(labelFont.deriveFont(newSize));
        leftPanel.add(promoLabel);

        int pricePerTicket = getPriceBySeatType(selectedSeat);
        int totalTicketPrice = pricePerTicket * Integer.parseInt(selectedPeopleCount);

        // 요약섹션
        summaryTextArea = new JTextArea();
        summaryTextArea.setEditable(false);
        summaryTextArea.setText("선택 내역\n날짜:"+selectedDate+" \n시간: 20:00\n매수: " + selectedPeopleCount + "장\n좌석: " + selectedSeat + "\n" +
                "----------------\n" +
                "결제 내역\n티켓 금액:"+totalTicketPrice+"\n예매 수수료: \n배송료: \n총금액(+): \n" +
                "----------------\n" +
                "할인 내역\n할인금액: \n할인 쿠폰: \n모바일 머니: \n총할인금액(-): \n");

        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        summaryScrollPane.setPreferredSize(new Dimension(200, 400));

        //총금액
        JLabel totalAmountLabel = new JLabel("총금액(+) ");
        totalAmountLabel.setForeground(Color.RED);
        JTextField totalPaymentField = new JTextField();
        totalPaymentField.setEditable(false);
        JPanel totalAmountPanel = new JPanel(new BorderLayout());
        totalAmountPanel.add(totalAmountLabel, BorderLayout.NORTH);
        totalAmountPanel.add(totalPaymentField, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(summaryScrollPane, BorderLayout.CENTER);
        rightPanel.add(totalAmountPanel, BorderLayout.SOUTH);
        centerPanel.add(rightPanel, BorderLayout.EAST);
        // 쿠폰등록
        couponButton = new JButton("쿠폰 등록");
        couponButton.setBackground(new Color(204,204,255));
        couponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String couponCode = JOptionPane.showInputDialog(TicketingInterface4.this, "쿠폰 번호 10자리를 입력하세요.");
                if (couponCode != null) {
                    DefaultTableModel model = (DefaultTableModel) couponTable.getModel();
                    if ("oop2024001".equals(couponCode)) {
                        // Update coupon table
                        model.setRowCount(0); // Clear existing rows
                        model.addRow(new Object[]{"oop2024001", "1", "30000원", "O"});

                        // Update summary text area
                        summaryTextArea.setText("선택 내역\n날짜:"+selectedDate+" \n시간:20:00 \n매수:"+selectedPeopleCount+ "장\n좌석:"+selectedSeat+ "\n" +
                                "----------------\n" +
                                "결제 내역\n티켓 금액: "+totalTicketPrice+"원\n예매 수수료: 0원\n배송료: 0원\n총금액(+): "+totalTicketPrice+"원\n" +
                                "----------------\n" +
                                "할인 내역\n할인금액: 30000원\n할인 쿠폰: oop2024001\n모바일 머니: 0원\n총할인금액(-): 30000원\n");

                        // Update total payment field
                        int totalAmount = totalTicketPrice - 30000;
                        totalPaymentField.setText(String.valueOf(totalAmount) + "원");
                    } else {
                        JOptionPane.showMessageDialog(TicketingInterface4.this, "존재하지 않는 쿠폰입니다.");
                    }
                }
            }
        });

        // 할인 표
        String[] discountColumnNames = {"할인명", "할인금액", "매수", "설명"};
        Object[][] discountData = {{"", "", "", ""}};
        discountTable = new JTable(new DefaultTableModel(discountData, discountColumnNames));
        JScrollPane discountScrollPane = new JScrollPane(discountTable);
        leftPanel.add(discountScrollPane);

        // 쿠폰 표
        String[] couponColumnNames = {"쿠폰명", "등급", "할인 금액", "사용"};
        Object[][] couponData = {{"", "", "", ""}};
        couponTable = new JTable(new DefaultTableModel(couponData, couponColumnNames));
        JScrollPane couponScrollPane = new JScrollPane(couponTable);
        leftPanel.add(couponScrollPane);

        leftPanel.add(couponButton);
        centerPanel.add(leftPanel, BorderLayout.CENTER);
        //다음으로 버튼
        JPanel bottomPanel = new JPanel();
        JButton nextButton = new JButton("다음으로");
        nextButton.setBackground(new Color(204,204,255));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketingInterface5(selectedDate, selectedPeopleCount, selectedSeat, totalTicketPrice, selectedDifficulty);
                dispose();
            }
        });
        bottomPanel.add(nextButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}