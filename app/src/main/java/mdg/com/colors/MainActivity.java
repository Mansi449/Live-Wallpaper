package mdg.com.colors;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Calendar;

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
        return value2;
    }

    public void openClockApp(View view){
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.deskclock");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
        else Log.e("eroro","errrrr");
    }
}
