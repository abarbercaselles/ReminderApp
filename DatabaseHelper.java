package estudios.com.myapplication;



import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DAYOFMONTH";
    public static final String COL_3 = "MONTH";
    public static final String COL_4 = "YEAR";
    public static final String COL_5 = "MINUTES";
    public static final String COL_6 = "HOUR";
    public static final String COL_7 = "REMINDER";
    public static final String COL_8 = "HOURDATE";
    public static final String COL_9 = "REPEAT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY,DAYOFMONTH TEXT,MONTH TEXT,YEAR TEXT,MINUTES TEXT,HOUR TEXT,REMINDER TEXT,HOURDATE TEXT,REPEAT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int id,int dayofmonth,int month,int year,int minutes,int hour,String reminder,String hourdate,String repeat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,dayofmonth);
        contentValues.put(COL_3,month);
        contentValues.put(COL_4,year);
        contentValues.put(COL_5,minutes);
        contentValues.put(COL_6,hour);
        contentValues.put(COL_7,reminder);
        contentValues.put(COL_8,hourdate);
        contentValues.put(COL_9,repeat);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id='" + id + "'",null);
        return res;
    }

    public boolean updateData(String id,String dayofmonth,String month,String year,String minutes,String hour,String reminder,String hourdate,String repeat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,dayofmonth);
        contentValues.put(COL_3,month);
        contentValues.put(COL_4,year);
        contentValues.put(COL_5,minutes);
        contentValues.put(COL_6,hour);
        contentValues.put(COL_7,reminder);
        contentValues.put(COL_8,hourdate);
        contentValues.put(COL_9,repeat);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor Count() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT COUNT (*) FROM " + TABLE_NAME,null);
        if(cursor!=null && cursor.getCount()!=0)
            cursor.moveToNext();
        return cursor;
    }

}