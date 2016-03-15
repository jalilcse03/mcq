package bubtjobs.com.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    QuestionSetMake questionSetMake;
    DataBaseManager dataBaseManager;
    AnswerSetMaker answerSetMaker;
    TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv=(TextView)findViewById(R.id.resultTv);

        dataBaseManager=new DataBaseManager(this);
    }
    public void addquestion(View view)
    {
        questionSetMake=new QuestionSetMake();
        ArrayList<QuestionSetMake> questionSet=new ArrayList<>();

        questionSetMake=new QuestionSetMake("Which of the following is a legal identifier in java ?","2variable","#myvar","+@$var","$_myvar","$_myvar");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which of these is NOT valid keyword or reserved word in Java ?","default","#null","String","String","#null");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which operator will always evaluate all the operands ?","||","&&","?:","%","?:");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("What is my name?","jalilur","rahman","murtuza","ali","murtuza");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("What is your favorit color?","black","red","olive","pink","black");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("your best friend?","a","t","p","k","p");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("your best book?","quran","math","english","java","quran");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("your hobby?","swing","codding","dancing","visiting","codding");
        questionSet.add(questionSetMake);

        String isinsert=dataBaseManager.addQuestion(questionSet);
        Toast.makeText(this,""+isinsert,Toast.LENGTH_SHORT).show();

//        if(isinsert)
//        {
//            Toast.makeText(this,"Question add",Toast.LENGTH_SHORT).show();
//        }
//        else
//            Toast.makeText(this,"Question add error",Toast.LENGTH_SHORT).show();

    }

    public void answerreset(View view)
    {
        dataBaseManager=new DataBaseManager(this);

//        HashMap<String,String> hashMap=new HashMap<>();
//
//        hashMap.put("$_myvar","de");
//        hashMap.put("#null","de");
//        hashMap.put("?:","de");
//        hashMap.put("murtuza","de");
//        hashMap.put("black","de");
//        hashMap.put("p","de");
//        hashMap.put("quran","de");
//        hashMap.put("codding","de");
        ArrayList<Answer> answersset=new ArrayList<>();

        Answer answer=new Answer("$_myvar","de");
        answersset.add(answer);
        answer=new Answer("#null","de");
        answersset.add(answer);
        answer=new Answer("?:","de");
        answersset.add(answer);
        answer=new Answer("murtuza","de");
        answersset.add(answer);
        answer=new Answer("black","de");
        answersset.add(answer);
        answer=new Answer("p","de");
        answersset.add(answer);
        answer=new Answer("quran","de");
        answersset.add(answer);
        answer=new Answer("codding","de");
        answersset.add(answer);






        boolean isanswerRest=dataBaseManager.addAnswer(answersset);

      //  Toast.makeText(this,""+isanswerRest,Toast.LENGTH_SHORT).show();
        if(isanswerRest)
        {
            Toast.makeText(this,"Answer Rest ",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Answer Rest error",Toast.LENGTH_SHORT).show();
    }

    public void examstart(View view)
    {
        startActivity(new Intent(MainActivity.this,Exam.class));
//        ArrayList<QuestionSetMake> questions=new ArrayList<>();
//        dataBaseManager=new DataBaseManager(this);
//        questions=dataBaseManager.getAllQuestion();
    }

    public void result(View view)
    {
        dataBaseManager=new DataBaseManager(this);
        int reult=dataBaseManager.result();
        resultTv.setText(""+reult);
    }
}
