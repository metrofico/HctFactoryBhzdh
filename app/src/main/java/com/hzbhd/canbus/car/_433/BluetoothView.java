package com.hzbhd.canbus.car._433;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BluetoothView extends RelativeLayout {
   private static final int BUTTOM = 4;
   private static final int LEFT = 0;
   private static final int RIGHT = 1;
   private static final int TOP = 3;
   private static View view;
   private Button btn_1;
   private Button btn_2;
   private Button btn_3;
   private Button btn_4;
   private Button btn_5;
   private Button btn_6;
   private Button btn_7;
   private Button btn_8;
   private Button btn_9;
   private int dpi;
   private boolean isScroll;
   private float mTouchStartX;
   private float mTouchStartY;
   private int screenHeight;
   private int screenWidth;
   private WindowManager wm;
   private WindowManager.LayoutParams wmParams;
   private float x;
   private float y;

   public BluetoothView(Activity var1, String var2, PanoramaListener var3) {
      super(var1);
      view = LayoutInflater.from(var1).inflate(2131558517, this);
      this.wm = (WindowManager)var1.getSystemService("window");
      this.buttonClick(var3);
      DisplayMetrics var5 = new DisplayMetrics();
      var1.getWindowManager().getDefaultDisplay().getMetrics(var5);
      this.dpi = this.dpi(var5.densityDpi);
      this.screenWidth = this.wm.getDefaultDisplay().getWidth();
      this.screenHeight = this.wm.getDefaultDisplay().getHeight();
      WindowManager.LayoutParams var4 = new WindowManager.LayoutParams();
      this.wmParams = var4;
      var4.type = 2003;
      this.wmParams.format = 1;
      this.wmParams.gravity = 51;
      this.wmParams.flags = 8;
      this.wmParams.width = this.dpi + 300;
      this.wmParams.height = this.dpi + 400;
      this.wmParams.y = this.screenHeight - this.dpi >> 1;
      this.wm.addView(this, this.wmParams);
   }

   private void autoView() {
      int[] var1 = new int[2];
      this.getLocationOnScreen(var1);
      if (var1[0] < this.screenWidth / 2 - this.getWidth() / 2) {
         this.updateViewPosition(0);
      } else {
         this.updateViewPosition(1);
      }

   }

   private void buttonClick(PanoramaListener var1) {
      Button var2 = (Button)view.findViewById(2131361995);
      this.btn_1 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent1();
         }
      });
      var2 = (Button)view.findViewById(2131361996);
      this.btn_2 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent2();
         }
      });
      var2 = (Button)view.findViewById(2131361997);
      this.btn_3 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent3();
         }
      });
      var2 = (Button)view.findViewById(2131361998);
      this.btn_4 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent4();
         }
      });
      var2 = (Button)view.findViewById(2131361999);
      this.btn_5 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent5();
         }
      });
      var2 = (Button)view.findViewById(2131362000);
      this.btn_6 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent6();
         }
      });
      var2 = (Button)view.findViewById(2131362001);
      this.btn_7 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent7();
         }
      });
      var2 = (Button)view.findViewById(2131362002);
      this.btn_8 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.val$listener.clickEvent8();
         }
      });
      var2 = (Button)view.findViewById(2131362003);
      this.btn_9 = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final BluetoothView this$0;
         final PanoramaListener val$listener;

         {
            this.this$0 = var1;
            this.val$listener = var2;
         }

         public void onClick(View var1) {
            this.this$0.hide();
            this.val$listener.clickEvent9();
         }
      });
   }

   private int dpi(int var1) {
      if (var1 <= 120) {
         return 36;
      } else if (var1 <= 160) {
         return 48;
      } else if (var1 <= 240) {
         return 72;
      } else {
         return var1 <= 320 ? 96 : 108;
      }
   }

   private void updateViewPosition() {
      this.wmParams.x = (int)(this.x - this.mTouchStartX);
      this.wmParams.y = (int)(this.y - this.mTouchStartY - (float)(this.screenHeight / 25));
      this.wm.updateViewLayout(this, this.wmParams);
   }

   private void updateViewPosition(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 3) {
               if (var1 == 4) {
                  this.wmParams.y = this.screenHeight - this.dpi;
               }
            } else {
               this.wmParams.y = 0;
            }
         } else {
            int var2 = this.screenWidth;
            var1 = this.dpi;
            this.wmParams.x = var2 - var1;
         }
      } else {
         this.wmParams.x = 0;
      }

      this.wm.updateViewLayout(this, this.wmParams);
   }

   public void destory() {
      this.hide();
      this.wm.removeViewImmediate(this);
   }

   public void hide() {
      this.setVisibility(8);
   }

   public boolean onTouchEvent(MotionEvent var1) {
      this.x = var1.getRawX();
      this.y = var1.getRawY();
      int var2 = var1.getAction();
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 == 2) {
               if (this.isScroll) {
                  this.updateViewPosition();
               } else {
                  if (!(Math.abs(this.mTouchStartX - var1.getX()) > (float)(this.dpi / 3)) && !(Math.abs(this.mTouchStartY - var1.getY()) > (float)(this.dpi / 3))) {
                     return super.onTouchEvent(var1);
                  }

                  this.updateViewPosition();
               }

               this.isScroll = true;
            }
         } else {
            if (this.isScroll) {
               this.autoView();
            }

            this.isScroll = false;
            this.mTouchStartY = 0.0F;
            this.mTouchStartX = 0.0F;
         }
      } else {
         this.mTouchStartX = var1.getX();
         this.mTouchStartY = var1.getY();
      }

      return super.onTouchEvent(var1);
   }

   public void show() {
      if (!this.isShown()) {
         this.setVisibility(0);
      }
   }

   public interface PanoramaListener {
      void clickEvent1();

      void clickEvent2();

      void clickEvent3();

      void clickEvent4();

      void clickEvent5();

      void clickEvent6();

      void clickEvent7();

      void clickEvent8();

      void clickEvent9();
   }
}
