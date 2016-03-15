package bubtjobs.com.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION=1;
    static final String DATABSE_NAME="bitm_registration";

    static final String TABLE_QUESTION="question_set";
    static final String TABLE_ANSWER="answer_set";

    static final String COL_ID="id";
    static final String COL_QUESTION="question";
    static final String COL_OP1="option1";
    static final String COL_OP2="option2";
    static final String COL_OP3="option3";
    static final String COL_OP4="option14";
    static final String ANSWER="answer";
    //answer table
    static final String ANS_ID="id";
    static final String ANS_KEY="key";
    static final String ANS_VALUE="value";

    static final  String CREATE_TABLE_QUESTION_SET=" CREATE TABLE "+TABLE_QUESTION+" ( "+COL_ID+" INTEGER PRIMARY KEY,"+
            COL_QUESTION+" TEXT,"+COL_OP1+" TEXT,"+COL_OP2+" TEXT,"+COL_OP3+" TEXT,"+COL_OP4+" TEXT,"+ANSWER+" TEXT )";

   static final String CREATE_TABLE_ANSWER=" CREATE TABLE "+TABLE_ANSWER+" ( "+COL_ID+" INTEGER PRIMARY KEY,"+
            ANS_KEY+" TEXT,"+ANS_VALUE+" TEXT )";


    public DataBaseHelper(Context context) {
        super(context,DATABSE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTION_SET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

