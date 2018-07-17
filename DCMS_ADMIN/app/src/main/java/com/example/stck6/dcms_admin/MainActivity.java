package com.example.stck6.dcms_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.notice_bt:
                intent = new Intent(this, notice.class);
                startActivity(intent);
                break;
            case R.id.rp_bt:
                intent = new Intent(this, rp.class);
                startActivity(intent);
                break;
            case R.id.light_bt:
                intent = new Intent(this,light.class);
                startActivity(intent);
                break;
            case R.id.close_bt:
                finish();
                break;
        }
    }
}
