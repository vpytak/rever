package com.vovka.rever;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FieldView extends View implements CellsUpdatedListener {

  private Paint redCirclePaint;
  private Paint greenCirclePaint;
  private Paint linePaint;
  private int steps;
  private CellClickListener listener;
  
  private int[][] cells = null;
  private int stepLength;
  
  public FieldView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public FieldView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldView(Context context) {
    super(context);
    init();
  }
  
  private void init() {
    redCirclePaint = new Paint();
    redCirclePaint.setColor(Color.RED);
    redCirclePaint.setStrokeWidth(2);
    redCirclePaint.setStyle(Style.FILL_AND_STROKE);
    
    greenCirclePaint = new Paint();
    greenCirclePaint.setColor(Color.GREEN);
    greenCirclePaint.setStrokeWidth(2);
    greenCirclePaint.setStyle(Style.FILL_AND_STROKE);
    
    linePaint = new Paint();
    linePaint.setColor(Color.BLACK);
    linePaint.setStrokeWidth(2);
    linePaint.setStyle(Style.FILL_AND_STROKE);
  }
  
  public void setCellClickListener(CellClickListener listener) {
    this.listener = listener;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    setBackgroundColor(Color.GRAY);
    steps = 4;
    stepLength = (getWidth()) / steps;
    
    for(int i = 0; i <= steps; i++) {
      canvas.drawLine(0, i * stepLength, getWidth(), i * stepLength, linePaint);
      canvas.drawLine(i * stepLength, 0, i * stepLength, getHeight(), linePaint);
    }
    if (cells == null) return;
    for (int i = 0; i < steps; i++) {
      for (int j = 0; j < steps; j++) {
        if (cells[i][j] == 1) {
          canvas.drawCircle(i * stepLength + stepLength / 2, j * stepLength + stepLength / 2, 
              stepLength / 2 - 3, redCirclePaint);
        } else if (cells[i][j] == -1) {
          canvas.drawCircle(i * stepLength + stepLength / 2, j * stepLength + stepLength / 2, 
              stepLength / 2 - 3, greenCirclePaint);
        }
      }
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    switch (action) {
    case MotionEvent.ACTION_DOWN:
      return true;
    case MotionEvent.ACTION_UP:
      if (listener != null) {
        listener.clickCell((int)event.getX() / stepLength, (int)event.getY() / stepLength);
      }
      return true;
    }
    
    return false;
  }

  @Override
  public void onCellsUpdated(int[][] cells) {
    this.cells = cells;
  }
}
