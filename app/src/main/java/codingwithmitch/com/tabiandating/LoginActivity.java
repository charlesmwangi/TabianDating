package codingwithmitch.com.tabiandating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
//widgets
    private Button mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: started");
        //attach button to id and attach onclick listener
        mLogin = findViewById(R.id.btn_login);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_login);
        Log.d(TAG, "onClick: logging in the user");
        //create an itent
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //change animation when changing from one activity to another
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //kill the activity to avoid going back to log in page
        finish();
    }
}
