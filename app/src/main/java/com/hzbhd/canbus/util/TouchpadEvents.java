package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class TouchpadEvents {
   private static int ContinuousValue;
   private static final String ORIGIN_TAG = "ORIGIN";
   private static final String SLIDE_TAG = "SLIDE_TAG";
   private static final String TIME_TAG = "TIME_TAG";
   private static int carDownX;
   private static int carDownY;
   private static CountDownTimer countDownTimer;
   private static int firstAction;
   private static int originTag;
   private static int slideTag;
   private static int spareOriginTag;
   private static int spareSlideTag;
   private static int spareTimeTag;
   private static int timeTag;
   private LinearLayout alert_layout;
   private Button cancel;
   private SeekBar data_seekbar;
   private TextView data_textview;
   private Button develop_ok;
   AlertDialog dialog;
   private Button isNot_develop;
   private TextView left_bottom;
   private TextView left_top;
   private Button ok_set;
   private SeekBar operation_seekbar;
   private TextView operation_textview;
   private TextView right_bottom;
   private TextView right_top;

   private void initSpareData() {
      spareTimeTag = timeTag;
      spareSlideTag = slideTag;
      spareOriginTag = originTag;
   }

   private static void initValueAdjust(Context var0) {
      timeTag = SharePreUtil.getIntValue(var0, "TIME_TAG", 500);
      slideTag = SharePreUtil.getIntValue(var0, "SLIDE_TAG", 5);
      originTag = SharePreUtil.getIntValue(var0, "ORIGIN", 0);
   }

   private static void initValueLogic() {
      carDownX = 0;
      carDownY = 0;
      ContinuousValue = 0;
      countDownTimer.cancel();
   }

   private static void leftBottom(int var0, int var1, Events var2) {
      if (ContinuousValue > slideTag) {
         var0 = carDownX - var0;
         var1 = carDownY - var1;
         if (Math.abs(var0) > Math.abs(var1)) {
            if (var0 < 0) {
               var2.goRight();
            } else {
               var2.goLeft();
            }
         } else if (var1 < 0) {
            var2.goUp();
         } else {
            var2.goDown();
         }
      } else {
         var2.enter();
      }

   }

   private static void leftTop(int var0, int var1, Events var2) {
      if (ContinuousValue > slideTag) {
         var0 = carDownX - var0;
         var1 = carDownY - var1;
         if (Math.abs(var0) > Math.abs(var1)) {
            if (var0 < 0) {
               var2.goRight();
            } else {
               var2.goLeft();
            }
         } else if (var1 < 0) {
            var2.goDown();
         } else {
            var2.goUp();
         }
      } else {
         var2.enter();
      }

   }

   private void onClick(Context var1, View var2) {
      if (this.develop_ok == null) {
         this.develop_ok = (Button)var2.findViewById(2131362189);
      }

      if (this.isNot_develop == null) {
         this.isNot_develop = (Button)var2.findViewById(2131362507);
      }

      if (this.alert_layout == null) {
         this.alert_layout = (LinearLayout)var2.findViewById(2131361916);
      }

      if (this.left_top == null) {
         this.left_top = (TextView)var2.findViewById(2131362748);
      }

      if (this.right_top == null) {
         this.right_top = (TextView)var2.findViewById(2131363177);
      }

      if (this.left_bottom == null) {
         this.left_bottom = (TextView)var2.findViewById(2131362727);
      }

      if (this.right_bottom == null) {
         this.right_bottom = (TextView)var2.findViewById(2131363155);
      }

      if (this.data_seekbar == null) {
         this.data_seekbar = (SeekBar)var2.findViewById(2131362175);
      }

      if (this.operation_seekbar == null) {
         this.operation_seekbar = (SeekBar)var2.findViewById(2131362928);
      }

      if (this.data_textview == null) {
         this.data_textview = (TextView)var2.findViewById(2131362176);
      }

      if (this.operation_textview == null) {
         this.operation_textview = (TextView)var2.findViewById(2131362929);
      }

      if (this.ok_set == null) {
         this.ok_set = (Button)var2.findViewById(2131362921);
      }

      if (this.cancel == null) {
         this.cancel = (Button)var2.findViewById(2131362092);
      }

      int var3 = originTag;
      if (var3 == 0) {
         this.left_top.setBackgroundResource(2131099974);
         this.right_top.setBackgroundResource(2131100046);
         this.left_bottom.setBackgroundResource(2131100046);
         this.right_bottom.setBackgroundResource(2131100046);
      } else if (var3 == 1) {
         this.left_top.setBackgroundResource(2131100046);
         this.right_top.setBackgroundResource(2131099974);
         this.left_bottom.setBackgroundResource(2131100046);
         this.right_bottom.setBackgroundResource(2131100046);
      } else if (var3 == 2) {
         this.left_top.setBackgroundResource(2131100046);
         this.right_top.setBackgroundResource(2131100046);
         this.left_bottom.setBackgroundResource(2131099974);
         this.right_bottom.setBackgroundResource(2131100046);
      } else if (var3 == 3) {
         this.left_top.setBackgroundResource(2131100046);
         this.right_top.setBackgroundResource(2131100046);
         this.left_bottom.setBackgroundResource(2131100046);
         this.right_bottom.setBackgroundResource(2131099974);
      }

      this.ok_set.setOnClickListener(new View.OnClickListener(this, var1) {
         final TouchpadEvents this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClick(View var1) {
            SharePreUtil.setIntValue(this.val$context, "TIME_TAG", TouchpadEvents.spareTimeTag);
            SharePreUtil.setIntValue(this.val$context, "SLIDE_TAG", TouchpadEvents.spareSlideTag);
            SharePreUtil.setIntValue(this.val$context, "ORIGIN", TouchpadEvents.spareOriginTag);
            TouchpadEvents.initValueAdjust(this.val$context);
            this.this$0.initSpareData();
            this.this$0.dialog.dismiss();
         }
      });
      this.cancel.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.initSpareData();
            this.this$0.dialog.dismiss();
         }
      });
      this.data_seekbar.setMax(1000);
      this.data_seekbar.setProgress(timeTag);
      this.data_textview.setText(String.valueOf(timeTag));
      this.data_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.this$0.data_textview.setText(String.valueOf(var1.getProgress()));
         }

         public void onStartTrackingTouch(SeekBar var1) {
            this.this$0.data_textview.setText(String.valueOf(var1.getProgress()));
         }

         public void onStopTrackingTouch(SeekBar var1) {
            this.this$0.data_textview.setText(String.valueOf(var1.getProgress()));
            TouchpadEvents.spareTimeTag = var1.getProgress();
         }
      });
      this.operation_seekbar.setMax(100);
      this.operation_seekbar.setProgress(slideTag);
      this.operation_textview.setText(String.valueOf(slideTag));
      this.operation_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.this$0.operation_textview.setText(String.valueOf(var1.getProgress()));
         }

         public void onStartTrackingTouch(SeekBar var1) {
            this.this$0.operation_textview.setText(String.valueOf(var1.getProgress()));
         }

         public void onStopTrackingTouch(SeekBar var1) {
            this.this$0.operation_textview.setText(String.valueOf(var1.getProgress()));
            TouchpadEvents.spareSlideTag = var1.getProgress();
         }
      });
      this.develop_ok.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.alert_layout.setVisibility(8);
         }
      });
      this.isNot_develop.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.dialog.dismiss();
         }
      });
      this.left_top.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.left_top.setBackgroundResource(2131099974);
            this.this$0.right_top.setBackgroundResource(2131100046);
            this.this$0.left_bottom.setBackgroundResource(2131100046);
            this.this$0.right_bottom.setBackgroundResource(2131100046);
            TouchpadEvents.spareOriginTag = 0;
         }
      });
      this.right_top.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.left_top.setBackgroundResource(2131100046);
            this.this$0.right_top.setBackgroundResource(2131099974);
            this.this$0.left_bottom.setBackgroundResource(2131100046);
            this.this$0.right_bottom.setBackgroundResource(2131100046);
            TouchpadEvents.spareOriginTag = 1;
         }
      });
      this.left_bottom.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.left_top.setBackgroundResource(2131100046);
            this.this$0.right_top.setBackgroundResource(2131100046);
            this.this$0.left_bottom.setBackgroundResource(2131099974);
            this.this$0.right_bottom.setBackgroundResource(2131100046);
            TouchpadEvents.spareOriginTag = 2;
         }
      });
      this.right_bottom.setOnClickListener(new View.OnClickListener(this) {
         final TouchpadEvents this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.left_top.setBackgroundResource(2131100046);
            this.this$0.right_top.setBackgroundResource(2131100046);
            this.this$0.left_bottom.setBackgroundResource(2131100046);
            this.this$0.right_bottom.setBackgroundResource(2131099974);
            TouchpadEvents.spareOriginTag = 3;
         }
      });
   }

   public static void putXY(Context var0, int var1, int var2, Events var3) {
      if (firstAction == 0) {
         firstAction = 1;
         initValueAdjust(var0);
      }

      if (ContinuousValue == 0) {
         carDownX = var1;
         carDownY = var2;
         MyLog.temporaryTracking("按下");
      }

      CountDownTimer var5 = countDownTimer;
      if (var5 != null) {
         var5.cancel();
         ++ContinuousValue;
         MyLog.temporaryTracking("坐标连续次数+1");
      }

      int var4 = timeTag;
      var5 = new CountDownTimer((long)var4, (long)var4, var1, var2, var3) {
         final Events val$events;
         final int val$x;
         final int val$y;

         {
            this.val$x = var5;
            this.val$y = var6;
            this.val$events = var7;
         }

         public void onFinish() {
            if (TouchpadEvents.originTag == 0) {
               TouchpadEvents.leftTop(this.val$x, this.val$y, this.val$events);
            } else if (TouchpadEvents.originTag == 1) {
               TouchpadEvents.rightTop(this.val$x, this.val$y, this.val$events);
            } else if (TouchpadEvents.originTag == 2) {
               TouchpadEvents.leftBottom(this.val$x, this.val$y, this.val$events);
            } else if (TouchpadEvents.originTag == 3) {
               TouchpadEvents.rightBottom(this.val$x, this.val$y, this.val$events);
            }

            TouchpadEvents.initValueLogic();
         }

         public void onTick(long var1) {
         }
      };
      countDownTimer = var5;
      var5.start();
   }

   private static void rightBottom(int var0, int var1, Events var2) {
      if (ContinuousValue > slideTag) {
         var0 = carDownX - var0;
         var1 = carDownY - var1;
         if (Math.abs(var0) > Math.abs(var1)) {
            if (var0 < 0) {
               var2.goLeft();
            } else {
               var2.goRight();
            }
         } else if (var1 < 0) {
            var2.goUp();
         } else {
            var2.goDown();
         }
      } else {
         var2.enter();
      }

   }

   private static void rightTop(int var0, int var1, Events var2) {
      if (ContinuousValue > slideTag) {
         var0 = carDownX - var0;
         var1 = carDownY - var1;
         if (Math.abs(var0) > Math.abs(var1)) {
            if (var0 < 0) {
               var2.goLeft();
            } else {
               var2.goRight();
            }
         } else if (var1 < 0) {
            var2.goDown();
         } else {
            var2.goUp();
         }
      } else {
         var2.enter();
      }

   }

   public void showAdjustView(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558705, (ViewGroup)null, true);
      this.dialog = (new AlertDialog.Builder(var1)).setView(var2).create();
      initValueAdjust(var1);
      this.initSpareData();
      this.onClick(var1, var2);
      this.dialog.setCancelable(false);
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.show();
   }

   public interface Events {
      void enter();

      void goDown();

      void goLeft();

      void goRight();

      void goUp();
   }
}
