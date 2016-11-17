package app.com.thetechnocafe.sqlinjection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mVulnerableButton;
    private Button mNonVulnerableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVulnerableButton = (Button) findViewById(R.id.vulnerable_button);
        mNonVulnerableButton = (Button) findViewById(R.id.non_vulnerable_button);

        mVulnerableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VulnerableActivity.class);
                startActivity(intent);
            }
        });

        mNonVulnerableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NonVulnerableActivity.class);
                startActivity(intent);
            }
        });
    }
}
