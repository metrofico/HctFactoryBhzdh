package com.hzbhd.canbus.park.radar.track;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.os.LocaleList;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.text.TextUtilsCompat;
import com.hzbhd.canbus.util.LogUtil;

public class TrackView extends View {
   private final int DATA_LENGTH = 200;
   private int DATA_TYPE = 20;
   private int mAngle = 0;
   private Context mContext;
   private int mCurrentXOffSet;
   private byte[] mData;
   private int mDimenCurrentXOff;
   private int mDimenXOff;
   private int mDimenYOff;
   private Paint mPaint;
   private TrackData mTrackData;
   private int mXOff;
   private int mYOff;

   public TrackView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mDimenXOff = (int)this.getResources().getDimension(2131178376);
      this.mDimenYOff = (int)this.getResources().getDimension(2131178378);
      int var3 = (int)this.getResources().getDimension(2131178375);
      this.mDimenCurrentXOff = var3;
      this.mXOff = this.mDimenXOff;
      this.mYOff = this.mDimenYOff;
      this.mCurrentXOffSet = var3;
      if (this.isRightToLeft()) {
         this.mXOff = (int)this.getResources().getDimension(2131178377);
      }

      LogUtil.showLog("mXOff:" + this.mXOff + " mYOff:" + this.mYOff + " mCurrentXOffSet:" + this.mCurrentXOffSet + " isRightToLeft():" + this.isRightToLeft());
      Paint var4 = new Paint();
      this.mPaint = var4;
      var4.setColor(-256);
      this.mPaint.setStyle(Style.STROKE);
      this.mPaint.setStrokeWidth(5.0F);
      this.mPaint.setAntiAlias(true);
      TrackData var5 = new TrackData();
      this.mTrackData = var5;
      var5.setType(this.DATA_TYPE);
      this.mTrackData.readFile(var1);
      this.mData = new byte[200];
      this.invalidate();
   }

   public int getSaveCurrentXOffSet() {
      return this.mDimenCurrentXOff;
   }

   public int getSaveXOff() {
      return this.mDimenXOff;
   }

   public int getSaveYOff() {
      return this.mDimenYOff;
   }

   public boolean isRightToLeft() {
      LocaleList var2 = this.getContext().getResources().getConfiguration().getLocales();
      boolean var1 = false;
      if (TextUtilsCompat.getLayoutDirectionFromLocale(var2.get(0)) == 1) {
         var1 = true;
      }

      return var1;
   }

   protected void onDraw(Canvas var1) {
      super.onDraw(var1);
      this.mTrackData.readAngleData(this.mAngle, this.mData);
      byte[] var8 = this.mData;
      int var3 = 1;
      byte var6 = var8[1];
      int var5 = (var8[2] << 8 | var8[3] & 255) - this.mXOff;
      int var2 = var8[4];
      int var4 = (var8[5] & 255 | var2 << 8) - this.mYOff;
      int var7 = this.mAngle;
      var2 = var5;
      if (var7 < 0) {
         var2 = this.mCurrentXOffSet - var5;
      }

      var2 -= var7;
      Path var11 = new Path();
      var11.moveTo((float)var2, (float)var4);

      while(var3 < (var6 & 255)) {
         byte[] var9;
         byte var10;
         if (this.mAngle < 0) {
            var9 = this.mData;
            var5 = var3 * 2;
            var2 += -var9[var5 + 4];
            var10 = var9[var5 + 5];
         } else {
            var9 = this.mData;
            var5 = var3 * 2;
            var2 += var9[var5 + 4];
            var10 = var9[var5 + 5];
         }

         var4 += var10;
         var11.lineTo((float)var2, (float)var4);
         ++var3;
      }

      var11.close();
      var1.drawPath(var11, this.mPaint);
   }

   public void setAngle(int var1) {
      this.mAngle = var1;
      this.invalidate();
   }

   public void setCurrentXOffSet(int var1) {
      this.mCurrentXOffSet = var1;
      this.invalidate();
   }

   public void setXOff(int var1) {
      this.mXOff = var1;
      this.invalidate();
   }

   public void setYOff(int var1) {
      this.mYOff = var1;
      this.invalidate();
   }
}
