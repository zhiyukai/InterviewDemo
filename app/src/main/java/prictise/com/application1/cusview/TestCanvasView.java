package prictise.com.application1.cusview;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author zhisiyi
 * @Date 2019.08.21 10:14
 * @Comment
 */
public class TestCanvasView extends View {

  public final static String TAG = "Example_05_03_GameView";
  // 声明Paint对象
  private Paint mPaint;

  public TestCanvasView(Context context, Paint mPaint) {
    super(context);
    this.mPaint = mPaint;
  }

  public TestCanvasView(Context context, AttributeSet attrs) {
    super(context);
  }

  public TestCanvasView(Context context, @Nullable AttributeSet attrs, Paint mPaint) {
    super(context, attrs);
    this.mPaint = mPaint;
  }

  public TestCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Paint mPaint) {
    super(context, attrs, defStyleAttr);
    this.mPaint = mPaint;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint background = new Paint();
    Paint line = new Paint();

    background.setColor(getResources().getColor(color.darker_gray));
    line.setColor(Color.RED);

    int px = getMeasuredWidth();

    int py = getMeasuredWidth();

    // Draw background
    canvas.drawRect(0, 0, px, py, background);
    canvas.save();
    canvas.rotate(90, px / 2, py / 2);

    //画一个向上的箭头
    canvas.drawLine(px / 2, 0, 0, py / 2, line); //左边的斜杠
    canvas.drawLine(px / 2, 0, px, py / 2, line);//右边的斜杠
    canvas.drawLine(px / 2, 0, px / 2, py, line);//垂直的竖杠

    canvas.restore();
    // Draw circle
    canvas.drawCircle(px - 10, py - 10, 10, line);
  }
}
