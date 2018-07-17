package com.example.stck6.dcms_admin;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.io.IOException;
import java.net.Socket;

public class notice extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.notice_up:
                AsyncTask asyncTask = new Async_Task();
                asyncTask.execute();
                break;
        }
    }
}

class Async_Task extends AsyncTask <Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket("10.80.161.69", 8002);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
