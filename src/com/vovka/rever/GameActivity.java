package com.vovka.rever;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameActivity extends Activity implements CellsUpdatedListener, BingoListener {
  private final int DIALOG_BINGO = 1;

  private FieldView gameView;
  private Button btnGoPuzzle;
  private int stepsDone = 0;

  private SwitchSingleCellController singleCellController;
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.game);
      
      gameView = (FieldView) findViewById(R.id.game_view);
      btnGoPuzzle = (Button) findViewById(R.id.btn_gopuzzle);
      btnGoPuzzle.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
          goPuzzle();
        }
      });
      
      singleCellController = new SwitchSingleCellController(this, 4);
      gameView.setCellClickListener(singleCellController);
      singleCellController.setCellsUpdatedListener(this);
  }
  
  public void onCellsUpdated(int[][] cells) {
    gameView.onCellsUpdated(cells);
    gameView.invalidate();
  }

  @Override
  public void bingo(int stepsDone) {
    this.stepsDone = stepsDone;
    showDialog(DIALOG_BINGO);
  }
  
  @Override
  protected Dialog onCreateDialog(int id) {
    Builder builder = new AlertDialog.Builder(this);
    AlertDialog dialog = builder.create();
    
    switch (id) {
    case DIALOG_BINGO:
      dialog.setTitle("BINGO!!! You've done in " + stepsDone + " steps.");
      return dialog;

    default:
      break;
    }
    return super.onCreateDialog(id);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.game_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.mn_resume:
      break;
    case R.id.mn_restart:
      restartLevel();
      break;
    case R.id.mn_menu:
      gotoMainMenu();
      break;
    default:
      return false;
    }
    return true;
  }

  private void gotoMainMenu() {
    // TODO Auto-generated method stub
    
  }

  private void restartLevel() {
    // TODO Auto-generated method stub
    
  }

  private void goPuzzle() {
    btnGoPuzzle.setVisibility(View.GONE);
    CrossPuzzleController puzzleController = new CrossPuzzleController(this, 4, 
        singleCellController.getCells());
    gameView.setCellClickListener(puzzleController);
    puzzleController.setBingoListener(this);
    puzzleController.setCellsUpdatedListener(this);
  }
}