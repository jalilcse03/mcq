package bubtjobs.com.project;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class DataBaseManager {
    private DataBaseHelper helper;
    private QuestionSetMake questionSetMake;
    private SQLiteDatabase database;


    public DataBaseManager(Context context){
        helper=new DataBaseHelper(context);
    }

    private void open(){
        database=helper.getWritableDatabase();
    }

    private void close(){
        helper.close();
    }

    public String addQuestion(ArrayList<QuestionSetMake> questionSetMakeArrayList)
    {
        try {


            this.open();
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_QUESTION);

            database.execSQL(DataBaseHelper.CREATE_TABLE_QUESTION_SET);

            for (QuestionSetMake obj : questionSetMakeArrayList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseHelper.COL_QUESTION, obj.getQuestion());
                contentValues.put(DataBaseHelper.COL_OP1, obj.getOp1());
                contentValues.put(DataBaseHelper.COL_OP2, obj.getOp2());
                contentValues.put(DataBaseHelper.COL_OP3, obj.getOp3());
                contentValues.put(DataBaseHelper.COL_OP4, obj.getOp4());
                contentValues.put(DataBaseHelper.ANSWER, obj.getAnswer());
                long inserted = database.insert(DataBaseHelper.TABLE_QUESTION, null, contentValues);

            }
            this.close();
            return "yes";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    public boolean addAnswer(ArrayList<Answer> arrayList){
        try{
            this.open();
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_ANSWER);

            database.execSQL(DataBaseHelper.CREATE_TABLE_ANSWER);

            for(Answer obj:arrayList)
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseHelper.ANS_KEY,obj.getKey());
                contentValues.put(DataBaseHelper.ANS_VALUE,obj.getValue());

                long inserted = database.insert(DataBaseHelper.TABLE_ANSWER, null, contentValues);

            }


            this.close();
            return true;
        }
        catch (Exception e){
            return  false;
        }

    }
    public ArrayList<QuestionSetMake> getAllQuestion(){
        this.open();
        ArrayList<QuestionSetMake> questions=new ArrayList<>();
        String query="SELECT * FROM "+DataBaseHelper.TABLE_QUESTION;
        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();


        if(cursor!=null && cursor.getCount()>0){
            for(int i=0;i<cursor.getCount();i++)
            {
                String question=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_QUESTION));
                String answer=cursor.getString(cursor.getColumnIndex(DataBaseHelper.ANSWER));
                String op1=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP1));
                String op2=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP2));
                String op3=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP3));
                String op4=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP4));
                String qusId=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                questionSetMake=new QuestionSetMake(question,answer,op1,op2,op3,op4,qusId);
                questions.add(questionSetMake);
                cursor.moveToNext();
            }
        }
        this.close();
        return questions;
    }

    public String updateAnswer(String id,String answer){
        try {
            this.open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseHelper.ANS_VALUE,answer);

            int updated = database.update(DataBaseHelper.TABLE_ANSWER, contentValues, DataBaseHelper.COL_ID + " = " + Integer.parseInt(id), null);
            this.close();
            return "yes";
        }
        catch (Exception e)
        {
            return  e.toString();
        }
    }

    public int result(){
        int cnt=0;
        try{
            this.open();

            String query="SELECT * FROM "+DataBaseHelper.TABLE_ANSWER;
            Cursor cursor=database.rawQuery(query, null);
            cursor.moveToFirst();

            if(cursor!=null && cursor.getCount()>0){
                for(int i=0;i<cursor.getCount();i++)
                {
                    String key=cursor.getString(cursor.getColumnIndex(DataBaseHelper.ANS_KEY));
                    String value=cursor.getString(cursor.getColumnIndex(DataBaseHelper.ANS_VALUE));
                    if(key.equals(value))
                        cnt++;
                        cursor.moveToNext();
                }
            }

            this.close();
            return cnt;
        }
        catch (Exception e)
        {
            return cnt;
        }
        //return  0;
    }

}
