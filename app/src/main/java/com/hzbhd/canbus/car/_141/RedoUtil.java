package com.hzbhd.canbus.car._141;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.interfaces.ActionDo;
import java.util.List;

public class RedoUtil {
   private final int ACTION_TAG = 255;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final RedoUtil this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 255 && this.this$0.redoTag) {
            this.this$0.thisActionDo.todo((List)null);
         }

      }
   };
   private boolean redoTag = false;
   private ActionDo thisActionDo;

   public RedoUtil(ActionDo var1) {
      this.thisActionDo = var1;
   }

   public void startTimer(long var1) {
      this.redoTag = true;
      this.mHandler.obtainMessage().what = 255;
      this.mHandler.sendEmptyMessageDelayed(255, var1);
   }

   public void stopTimer() {
      this.mHandler.removeMessages(255);
      this.redoTag = false;
   }
}
