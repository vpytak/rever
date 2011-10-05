package com.vovka.rever;

public class CrossPuzzleController implements CellClickListener {

  private final GameActivity context;
  private int[][] cells;
  private final int scale;
  private CellsUpdatedListener listener = null;
  private BingoListener bingoListener = null;
  private int stepsDone;
  
  public CrossPuzzleController(GameActivity context, int scale, int[][] cells) {
    this.context = context;
    this.scale = scale;
    this.cells = cells;
  }
  @Override
  public void clickCell(int i, int j) {
    stepsDone++;
    
    cells[i][j] *= -1;
    if(i>0) cells[i-1][j] *= -1;
    if(i<scale-1) cells[i+1][j] *= -1;
    if(j>0) cells[i][j-1] *= -1;
    if(j<scale-1) cells[i][j+1] *= -1;
    
    if (checkDone()) {
      bingoListener.bingo(stepsDone);
    }
  }
  
  private boolean checkDone() {
    int sum = 0;
    for (int i = 0; i < scale; i++) {
      for (int j = 0; j < scale; j++) {
        sum += cells[i][j];
      }
    }
    
    return (Math.abs(sum) == scale * scale);
  }
  public void setCellsUpdatedListener(CellsUpdatedListener listener) {
    this.listener = listener;
    listener.onCellsUpdated(cells);
  }
  
  public void setBingoListener(BingoListener listener) {
    this.bingoListener = listener;
  }

}
