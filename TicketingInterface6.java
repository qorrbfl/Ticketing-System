import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;

public class TicketingInterface6 extends JFrame {

    private long startTime;
    private JTextArea summaryTextArea;
    private JTextField autoOrderTextField, yMoneyTextField, depositTextField;
    private JCheckBox agreementCheckBox1, agreementCheckBox2;
    private JButton nextButton;
    private JCheckBox yMoneyCheckBox, depositCheckBox;
    private JRadioButton creditRadioButton, noBankRadioButton;
    private ButtonGroup getGroup;
    private JRadioButton getRadioButton, plusgetRadioButton;
    private JComboBox<String> bankComboBox;
    private JButton receiptButton;
    private JPanel leftPanel;
    private JTextField totalPaymentField;
    private String selectedDifficulty;

    public TicketingInterface6(String selectedDate, String selectedPeopleCount, String selectedSeat, int totalTicketPrice, int deliveryFee, int totalAmount,int ticketdelivery, String selectedDifficulty) {
        this.startTime = System.currentTimeMillis();
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
        step4Label.setBackground(greyColor);
        step5Label.setBackground(Color.WHITE);

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
        centerPanel.setPreferredSize(new Dimension(800, 600));


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        // 요약섹션
        summaryTextArea = new JTextArea();
        summaryTextArea.setEditable(false);
        summaryTextArea.setText("선택 내역\n날짜: "+selectedDate+"\n시간:20:00 \n매수:"+selectedPeopleCount+"장\n좌석:"+selectedSeat+"\n" +
                "----------------\n" +
                "결제 내역\n티켓 금액:"+totalTicketPrice+"원\n예매 수수료: 0원 \n배송료:"+deliveryFee+"원\n총금액(+):"+ticketdelivery+"\n" +
                "----------------\n" +
                "할인 내역\n할인금액:30000원 \n할인 쿠폰:oop2024001 \n모바일 머니: 0원 \n총할인금액(-): 30000원 \n");
        JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
        summaryScrollPane.setPreferredSize(new Dimension(140, 300));

        //총금액
        JLabel totalAmountLabel = new JLabel("총금액(+) ");
        totalAmountLabel.setForeground(Color.RED);
        JTextField totalPaymentField = new JTextField();
        totalPaymentField.setEditable(false);
        totalPaymentField.setText(String.valueOf(totalAmount) + "원");
        JPanel totalAmountPanel = new JPanel(new BorderLayout());
        totalAmountPanel.add(totalAmountLabel, BorderLayout.NORTH);
        totalAmountPanel.add(totalPaymentField, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(summaryScrollPane, BorderLayout.CENTER);
        rightPanel.add(totalAmountPanel, BorderLayout.SOUTH);
        //centerPanel.add(rightPanel, BorderLayout.EAST);


        //결제방법 섹션
        JPanel ypaymentMethodPanel = new JPanel(new GridBagLayout());
        JPanel dpaymentMethodPanel = new JPanel(new GridBagLayout());
        yMoneyCheckBox = new JCheckBox("전액사용");
        depositCheckBox = new JCheckBox("전액사용");
        yMoneyTextField = new JTextField("0원", 5);
        yMoneyTextField.setEditable(false);
        depositTextField = new JTextField("0원", 5);
        depositTextField.setEditable(false);


        ypaymentMethodPanel.add(new JLabel("Y머니") );
        ypaymentMethodPanel.add(yMoneyTextField);
        ypaymentMethodPanel.add(yMoneyCheckBox);

        dpaymentMethodPanel.add(new JLabel("예치금"));
        dpaymentMethodPanel.add(depositTextField);
        dpaymentMethodPanel.add(depositCheckBox);

        // 기타 결제 관련 컴포넌트
        JPanel radioMethodPanel = new JPanel(new GridBagLayout());
        creditRadioButton = new JRadioButton("신용카드");
        creditRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankComboBox.setEnabled(false);
                receiptButton.setEnabled(false);
            }
        });
        noBankRadioButton = new JRadioButton("무통장입금");
        noBankRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankComboBox.setEnabled(noBankRadioButton.isSelected());
                receiptButton.setEnabled(true);
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(creditRadioButton);
        group.add(noBankRadioButton);

        String[] banks = {"농협은행", "삼성은행", "국민은행", "우리은행"};
        bankComboBox = new JComboBox<>(banks);
        bankComboBox.setEnabled(false);
        receiptButton = new JButton("현금영수증 발행 신청");
        receiptButton.setEnabled(false);
        receiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestPhoneNumber();
            }
        });


        JPanel bankPanel = new JPanel();
        bankPanel.setLayout(new FlowLayout());
        bankPanel.add(noBankRadioButton);
        bankPanel.add(bankComboBox);
        bankPanel.add(receiptButton);
        ypaymentMethodPanel.add(yMoneyCheckBox);
        dpaymentMethodPanel.add(depositCheckBox);
        ypaymentMethodPanel.add(new JSeparator());
        dpaymentMethodPanel.add(new JSeparator());
        radioMethodPanel.add(creditRadioButton);
        radioMethodPanel.add(noBankRadioButton);
        radioMethodPanel.add(bankComboBox);
        radioMethodPanel.add(receiptButton);

        // 표 데이터
        Object[][] tableData = {
                {"예매 후 7일 이내", "없음", "*실제금액을 청구하는 것은 아니며 취소 수수료 또한 실제 부과되지 않습니다"},
                {"예매 후 8일~관람일 10일 전까지","뮤지컬,콘서트 등 장당 4000원",""},
                {"관람일 9일 전~관람일 7일 전까지","티켓금액의 10%",""},
                {"관람일 6일 전~관람일 3일 전까지","티켓금액의 20%",""},
                {"관람일 2일 전~관람일 1일 전까지","티켓금액의 30%",""}
        };

        // 컬럼 이름을 Object 배열로 정의합니다.
        Object[] columnNames = {"내용", "취소수수료", "비고"};
        JTable table = new JTable(tableData, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(500, 100));
        centerPanel.add(tableScrollPane);
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        autoOrderTextField = new JTextField(20);
        JPanel autoOrderPanel = new JPanel();
        autoOrderPanel.add(new JLabel("자동주문방지 : aufb1h"));
        autoOrderPanel.add(autoOrderTextField);

        agreementCheckBox1 = new JCheckBox("취소 수수료 및 취소 기한을 확인하였으며, 이에 동의합니다.");
        agreementCheckBox2 = new JCheckBox("제 3자 정보제공 내용에 동의합니다.");

        JPanel agreementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        agreementPanel.add(agreementCheckBox1);
        agreementPanel.add(agreementCheckBox2);

        centerPanel.add(ypaymentMethodPanel);
        centerPanel.add(dpaymentMethodPanel);
        centerPanel.add(radioMethodPanel);
        centerPanel.add(autoOrderPanel);
        centerPanel.add(tableScrollPane);
        centerPanel.add(agreementPanel);
        add(centerPanel, BorderLayout.CENTER);
        ypaymentMethodPanel.setPreferredSize(new Dimension(500, 50));
        dpaymentMethodPanel.setPreferredSize(new Dimension(500, 50));
        radioMethodPanel.setPreferredSize(new Dimension(500, 50));
        autoOrderPanel.setPreferredSize(new Dimension(500, 50));
        agreementPanel.setPreferredSize(new Dimension(500, 100));
        centerPanel.add(rightPanel,BorderLayout.EAST);

        // 다음으로 버튼
        JPanel bottomPanel = new JPanel();
        nextButton = new JButton("다음으로");
        nextButton.setBackground(new Color(204,204,255));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateInputs();
            }
        });
        bottomPanel.add(nextButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(summaryScrollPane, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void validateInputs() {
        if (!autoOrderTextField.getText().equals("aufb1h")) {
            JOptionPane.showMessageDialog(this, "제대로 입력해주세요", "오류", JOptionPane.ERROR_MESSAGE);
        } else if (!agreementCheckBox1.isSelected() || !agreementCheckBox2.isSelected()) {
            JOptionPane.showMessageDialog(this, "체크해주세요", "오류", JOptionPane.ERROR_MESSAGE);
        } else {
            long endTime = System.currentTimeMillis();
            ThreadTimer timerThread = new ThreadTimer(startTime,endTime, selectedDifficulty != null ? selectedDifficulty : "easy");
            timerThread.start();
            System.out.println("종료 시간: " + endTime);

            dispose();

        }
    }


    private void requestPhoneNumber() {
        String phoneNumber = JOptionPane.showInputDialog(this, "전화번호를 입력하세요(010-0000-0000형태로 입력하세요)");
        if (phoneNumber != null && phoneNumber.matches("010-\\d{4}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "등록되었습니다", "성공", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "잘못된 형식입니다. 올바른 형식으로 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
        }

    }

}