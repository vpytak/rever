package com.vovka.rever;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

  private Button btnResume;
  private Button btnStart;
  private Button btnExit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InputStream openRawResource = getResources().openRawResource(R.raw.levels);
    btnResume = (Button) findViewById(R.id.btn_resume);
    btnResume.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        resumeGame();
      }
    });
    
    btnStart = (Button) findViewById(R.id.btn_start);
    btnStart.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        startGame();
      }
    });
    
    btnExit = (Button) findViewById(R.id.btn_exit);
    btnExit.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        exitGame();
      }
    });
  }
  
  private void startGame() {
    //TODO do smth
  }
  
  private void resumeGame() {
    //TODO do smth
  }
  
  private void exitGame() {
    //TODO do smth
  }
}