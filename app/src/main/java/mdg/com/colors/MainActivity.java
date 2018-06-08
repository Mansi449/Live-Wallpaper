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
    Calendar rightNow;


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
                   rightNow = Calendar.getInstance();
                    int hours = rightNow.get(Calendar.HOUR);
                    int minutes = rightNow.get(Calendar.MINUTE);
                    int seconds = rightNow.get(Calendar.SECOND);
                   String hrs = modifyDigit(hours);
                   String mins = modifyDigit(minutes);
                   String secs = modifyDigit(seconds);
                    String curTime = hrs + ":" + mins + ":" + secs;
                    txtclk.setText(curTime);
                    String color = "#" + hrs + mins+ secs;
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

    public String modifyDigit(int value){
        String value2 = Integer.toString(value);
        if(value<10){
            String hr1 = Integer.toString(value);
            value2 = "0" + hr1;
        }
        Log.e("modified time:", value2);
        return value2;
    }
}
