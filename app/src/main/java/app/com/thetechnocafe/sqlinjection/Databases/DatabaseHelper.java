package app.com.thetechnocafe.sqlinjection.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gurleensethi on 17/11/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABSE_VERSION = 1;
    private static final String DATABASE_NAME = "sample_database";
    private static final String TABLE_NAME = "contacts_table";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TYPE = "type";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME + " VARCHAR," +
                COLUMN_PHONE + " VARCHAR," +
                COLUMN_EMAIL + " VARCHAR," +
                COLUMN_TYPE + " VARCHAR);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
