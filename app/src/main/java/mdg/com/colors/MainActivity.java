package mdg.com.colors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relLayout;
    TextView txtclk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relLayout = findViewById(R.id.relLayout);
        txtclk = findViewById(R.id.clock);

        Time runnable = new Time();
       Thread myThread= new Thread(runnable);
        myThread.start();
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    Date dt = new Date();
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    int seconds = dt.getSeconds();
                    String curTime = hours + ":" + minutes + ":" + seconds;
                    txtclk.setText(curTime);
                    String color = "#" + Integer.toString(hours) + Integer.toString(minutes) + Integer.toString(seconds);
                    relLayout.setBackgroundColor(Color.parseColor(color));
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class Time implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
