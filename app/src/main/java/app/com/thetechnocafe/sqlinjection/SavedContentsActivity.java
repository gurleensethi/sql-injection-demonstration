package app.com.thetechnocafe.sqlinjection;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.thetechnocafe.sqlinjection.Databases.DatabaseHelper;

public class SavedContentsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mStringList;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private RecyclerAdapter mRecyclerAdapter;
    private List<ContactsModel> mContactsModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_contents);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        mContactsModels = new ArrayList<>();

        loadData();
    }

    private void loadData() {
        mSQLiteDatabase = mDatabaseHelper.getReadableDatabase();

        mStringList = new ArrayList<>();

        String sql = "SELECT * FROM " + DatabaseHelper.TABLE_NAME + ";";

        Cursor cursor = mSQLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            ContactsModel model = new ContactsModel();
            model.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
            model.setType(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TYPE)));
            model.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));
            model.setContact(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));

            mContactsModels.add(model);
        }

        //Refresh recycler view
        mRecyclerAdapter.notifyDataSetChanged();
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
        class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView mNameTextView;
            private int position;

            RecyclerViewHolder(View view) {
                super(view);
                mNameTextView = (TextView) view.findViewById(R.id.name_text_view);

                view.setOnClickListener(this);
            }

            public void bindData(int position) {
                this.position = position;
                mNameTextView.setText(mContactsModels.get(position).getName());
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mContactsModels.get(position).getContact()));
                startActivity(intent);
            }
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplication()).inflate(R.layout.item_recycler_view, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return mContactsModels.size();
        }
    }
}
