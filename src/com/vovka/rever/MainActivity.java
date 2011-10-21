package com.vovka.rever;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.vovka.rever.game.GameActivity;
import com.vovka.rever.utils.Global;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
  private static final int LEVEL_REQUEST = 1;

  private Button btnResume;
  private Button btnStart;
  private Button btnExit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
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
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
    case LEVEL_REQUEST:
      //TODO do magick
      Intent i = new Intent(MainActivity.this, GameActivity.class);
      i.putExtra("cells", Global.instance.getLevels().get(resultCode).getData());
      startActivity(i);
      break;
    default:
      super.onActivityResult(requestCode, resultCode, data);
      break;
    }
  }



  private void startGame() {
    Intent i = new Intent(MainActivity.this, LevelsActivity.class);
    startActivityForResult(i, LEVEL_REQUEST);
  }  
  
  private void resumeGame() {
    //TODO do smth
  }
  
  private void exitGame() {
    //TODO do smth
  }
}