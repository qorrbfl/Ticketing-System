import javax.swing.SwingUtilities;

public class ThreadTimer extends Thread {
    private long startTime;
    private long endTime;
    private String selectedDifficulty;

    public ThreadTimer(long startTime, long endTime, String selectedDifficulty) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.selectedDifficulty = selectedDifficulty;
        System.out.println("난이도: " + selectedDifficulty);
    }

    @Override
    public void run() {
        // 작업 수행

        double totalTime = (double) (endTime - startTime) / 1000; // 초 단위로 변환

        // 난이도에 따라 제한 시간 설정
        int timeLimit;
        switch (selectedDifficulty) {
            case "easy":
                timeLimit = 30; // easy의 경우 60초로 설정
                break;
            case "normal":
                timeLimit = 20; // normal의 경우 40초로 설정
                break;
            case "hard":
                timeLimit = 10; // hard의 경우 20초로 설정
                break;
            default:
                timeLimit = 30; // 기본적으로 60초로 설정
        }

        if (totalTime > timeLimit) {
            SwingUtilities.invokeLater(() -> new ResultInterfaceF(startTime, endTime));
        } else {
            SwingUtilities.invokeLater(() -> new ResultInterfaceS(startTime, endTime));
        }
    }
}