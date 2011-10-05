package com.vovka.rever;

public class SwitchSingleCellController implements CellClickListener {

  private final GameActivity context;
  private int[][] cells;
  private final int scale;
  private CellsUpdatedListener listener = null;
  
  public SwitchSingleCellController(GameActivity context, int scale) {
    this.context = context;
    this.scale = scale;
    initCells();
  }
  
  private void initCells() {
    cells = new int[scale][scale];
    for (int i = 0; i < scale; i++) {
      for (int j = 0; j < scale; j++) {
        cells[i][j] = 1;
      }
    }
  }

  @Override
  public void clickCell(int i, int j) {
    cells[i][j] *= -1;
    
    if (listener != null) {
      listener.onCellsUpdated(cells);
    }
  }
  
  public void setCellsUpdatedListener(CellsUpdatedListener listener) {
    this.listener = listener;
    listener.onCellsUpdated(cells);
  }
  
  public int[][] getCells() {
    return cells;
  }

}
