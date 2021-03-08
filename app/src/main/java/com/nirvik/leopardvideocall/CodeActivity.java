package com.nirvik.leopardvideocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.URL;

public class CodeActivity extends AppCompatActivity {
TextView code;
Button enterbtn,sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        code=findViewById(R.id.code);
        enterbtn=findViewById(R.id.enterroom);
        sharebtn=findViewById(R.id.sharecode);
        URL url;
        try{
        url=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultoptions=
                    new JitsiMeetConferenceOptions.Builder().setServerURL(url)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultoptions);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        enterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
                        .setRoom(code.getText().toString()).setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(CodeActivity.this,options);
            }
        });
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Secrate Code");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "This is My secrate Code : "+code.getText().toString());
                startActivity(Intent.createChooser(i,"Share via"));
            }
        });

    }
}