package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.*;

public class Controller {
    @FXML private Label labelnumber;
    @FXML private Label labelnumber1;
    @FXML private Label labelnumber2;
    @FXML private Label labelnumber3;
    @FXML private Label labelnumber4;
    @FXML private Label labelnumber5;
    @FXML private Button btn;
    private Random r = new Random();
    private Timer t;
    private Set<Integer> numbers = new HashSet<>();
    private boolean done = false;
    private int generatedNumber;
    private int times = 0;

    public void lottery(){
        if (!done) {
            t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {lot();});
                } 
            }, 0, 200);
        }
        else {
            order();
        }
    }

    public void order(){
        numbers.sort(Comparator.naturalOrder());
        labelnumber1.setText(numbers.get(0));
        labelnumber2.setText(numbers.get(1));
        labelnumber3.setText(numbers.get(2));
        labelnumber4.setText(numbers.get(3));
        labelnumber5.setText(numbers.get(4));
        done = false;
        numbers.clear();
        times = 0;
        btn.setText("Sorsolás");
    }
    
    public void lot() {
        if (times < 50) {
            times++;
            generatedNumber = r.nextInt(90)+1;
            labelnumber.setText(""+generatedNumber);
            if (times % 10 == 0) {
                while (!(numbers.add(generatedNumber))) {
                    generatedNumber = r.nextInt(90)+1;
                    labelnumber.setText(""+generatedNumber);
                }
                switch (numbers.size()) {
                    case 1: labelnumber1.setText(Integer.toString(generatedNumber)); break;
                    case 2: labelnumber2.setText(Integer.toString(generatedNumber)); break;
                    case 3: labelnumber3.setText(Integer.toString(generatedNumber)); break;
                    case 4: labelnumber4.setText(Integer.toString(generatedNumber)); break;
                    case 5: labelnumber5.setText(Integer.toString(generatedNumber)); break;
                }
            }
        } 
        else {
            t.cancel();
            done = true;
            btn.setText("Rendezés");
        }
    }
}