package bubtjobs.com.project;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Exam extends AppCompatActivity {

    DataBaseManager dataBaseManager;
    ListView listView;
    TextView timeTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        timeTv=(TextView)findViewById(R.id.time);
        listView=(ListView)findViewById(R.id.listView);

        ArrayList<QuestionSetMake> questions=new ArrayList<>();
        dataBaseManager=new DataBaseManager(this);
        questions=dataBaseManager.getAllQuestion();
        if(questions!=null)
        {
            AdapterForExam adapter=new AdapterForExam(getApplicationContext(),questions);
            listView.setAdapter(adapter);
        }
        else{
            Toast.makeText(Exam.this,"Error",Toast.LENGTH_SHORT).show();
        }
        new CounterClass(15000,1000).start();

    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NowApi")

    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @SuppressLint("NowApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            long millis=millisUntilFinished;

            String hms=String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),TimeUnit.MILLISECONDS.toMinutes(millis)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),

                    TimeUnit.MILLISECONDS.toSeconds(millis)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );
            timeTv.setText(hms);

        }

        @Override
        public void onFinish() {
            timeTv.setText("finish: ");
            startActivity(new Intent(Exam.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        }
    }
}
