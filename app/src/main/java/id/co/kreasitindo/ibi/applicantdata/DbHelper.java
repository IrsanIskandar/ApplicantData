package id.co.kreasitindo.ibi.applicantdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static String DB_NAME = "CRUD";
    public static int DB_VERSION = 1;
    private static String TABLE_NAME = "Member";
    private String KEY_ID = "ID";
    private String KEY_NAME = "NamaLengkap";
    private String KEY_JNSKELAMIN = "JenisKelamin";
    private String KEY_TMPLAHIR = "TempatLahir";
    private String KEY_TGLLAHIR = "TanggalLahir";
    private String KEY_ALAMATLENG = "AlamatLengkap";
    private String KEY_ISACTIVE = "IsActive";
    public Cursor cursor;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ "("+
                                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                KEY_NAME+" TEXT,"+KEY_JNSKELAMIN+" TEXT,"+
                                KEY_TMPLAHIR+" TEXT,"+KEY_TGLLAHIR +" TEXT,"+
                                KEY_ALAMATLENG+" TEXT,"+ KEY_ISACTIVE + " tinyint DEFAULT(1)"+");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(db);
    }


    public void CreateMembers (String nama, String jnsKel, String tmpLahir, String tglLahir, String alamat){
        SQLiteDatabase sql = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, nama);
        contentValues.put(KEY_JNSKELAMIN, jnsKel);
        contentValues.put(KEY_TMPLAHIR, tmpLahir);
        contentValues.put(KEY_TGLLAHIR, tglLahir);
        contentValues.put(KEY_ALAMATLENG, alamat);

        long insertRecord = sql.insert(TABLE_NAME,null, contentValues);
        sql.close();
    }

    public ArrayList<HashMap<String, String>> GetMembers(){
        SQLiteDatabase sql = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> memberList = new ArrayList<>();

        String queryGet = "SELECT NamaLengkap, JenisKelamin, TempatLahir, TanggalLahir, AlamatLengkap FROM "+TABLE_NAME;
        cursor = sql.rawQuery(queryGet, null);
        while (cursor.moveToNext()){
            HashMap<String, String> member = new HashMap<>();
            member.put("NamaLengkap", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            member.put("JenisKelamin", cursor.getString(cursor.getColumnIndex(KEY_JNSKELAMIN)));
            member.put("TempatLahir", cursor.getString(cursor.getColumnIndex(KEY_TMPLAHIR)));
            member.put("TanggalLahir", cursor.getString(cursor.getColumnIndex(KEY_TGLLAHIR)));
            member.put("AlamatLengkap", cursor.getString(cursor.getColumnIndex(KEY_ALAMATLENG)));
            memberList.add(member);
        }
        return memberList;
    }

    public ArrayList<HashMap<String, String>> GetMembersByMemberID(int memberID){
        SQLiteDatabase sql = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> memberList = new ArrayList<>();
        String queryGetByMEmberID = "SELECT NamaLengkap, JenisKelamin, TempatLahir, TanggalLahir, AlamatLengkap FROM "+TABLE_NAME;
        cursor = sql.query(TABLE_NAME,
                new String[]{KEY_NAME, KEY_JNSKELAMIN, KEY_TMPLAHIR, KEY_TGLLAHIR, KEY_ALAMATLENG},
                KEY_ID+" =?",
                new String[]{String.valueOf(memberID)}, null,null,null,null);
        if (cursor.moveToNext()){
            HashMap<String, String> member = new HashMap<>();
            member.put("NamaLengkap", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            member.put("JenisKelamin", cursor.getString(cursor.getColumnIndex(KEY_JNSKELAMIN)));
            member.put("TempatLahir", cursor.getString(cursor.getColumnIndex(KEY_TMPLAHIR)));
            member.put("TanggalLahir", cursor.getString(cursor.getColumnIndex(KEY_TGLLAHIR)));
            member.put("AlamatLengkap", cursor.getString(cursor.getColumnIndex(KEY_ALAMATLENG)));
            memberList.add(member);
        }
        return memberList;
    }

    public void DeleteMembers(int memberID){
        SQLiteDatabase sql = this.getWritableDatabase();
        sql.delete(TABLE_NAME, KEY_ID + " =?", new String[]{String.valueOf(memberID)});
        sql.close();
    }

    public void UpdateMembers(String nama, String jnsKel, String tmpLahir, String tglLahir, String alamat, int memberID){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, nama);
        contentValues.put(KEY_TMPLAHIR, jnsKel);
        contentValues.put(KEY_TMPLAHIR, tmpLahir);
        contentValues.put(KEY_TGLLAHIR, tglLahir);
        contentValues.put(KEY_ALAMATLENG, alamat);

        int cont = sql.update(TABLE_NAME, contentValues, KEY_ID + " =?", new String[]{String.valueOf(memberID)});
    }
}
