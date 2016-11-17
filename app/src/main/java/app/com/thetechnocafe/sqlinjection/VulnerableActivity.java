package app.com.thetechnocafe.sqlinjection;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.com.thetechnocafe.sqlinjection.Databases.DatabaseHelper;

public class VulnerableActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mTypeEditText;
    private Button mInsertButton;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerable);

        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mPhoneEditText = (EditText) findViewById(R.id.phone_edit_text);
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mTypeEditText = (EditText) findViewById(R.id.type_edit_text);
        mInsertButton = (Button) findViewById(R.id.insert_button);

        //Prepare database
        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    insertIntoDB();
                }
            }
        });
    }

    //Validate form
    private boolean checkForm() {
        if (mNameEditText.getText().toString().equals("")) {
            mNameEditText.requestFocus();
            mNameEditText.setError("This field is required");
            return false;
        }
        if (mPhoneEditText.getText().toString().equals("")) {
            mPhoneEditText.requestFocus();
            mPhoneEditText.setError("This field is required");
            return false;
        }
        if (mEmailEditText.getText().toString().equals("")) {
            mEmailEditText.requestFocus();
            mEmailEditText.setError("This field is required");
            return false;
        }
        if (mTypeEditText.getText().toString().equals("")) {
            mTypeEditText.requestFocus();
            mTypeEditText.setError("This field is required");
            return false;
        }
        return true;
    }

    //Inser into database
    private void insertIntoDB() {
        //Get database
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();

        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String phone = mPhoneEditText.getText().toString();
        String type = mTypeEditText.getText().toString();

        String insertSql = "INSERT INTO " + DatabaseHelper.TABLE_NAME + " VALUES(" + "'" + name + "', " + "'" + email + "', " + "'" + phone + "', " + "'" + type + "');";

        mSQLiteDatabase.execSQL(insertSql);
    }
}
