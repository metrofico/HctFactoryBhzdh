package com.hzbhd.canbus.car._462;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MyPanoramicView extends LinearLayout {
   public boolean manualLock;
   private Button returnBtn;
   private View view;

   public MyPanoramicView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.manualLock = false;
      View var4 = LayoutInflater.from(var1).inflate(2131558614, this, true);
      this.view = var4;
      Button var5 = (Button)var4.findViewById(2131363101);
      this.returnBtn = var5;
      var5.setOnClickListener(new View.OnClickListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.closeManual();
         }
      });
   }

   public void closeAuto() {
      if (this.manualLock) {
         BaseUtil.INSTANCE.runUi(new Function0(this) {
            final MyPanoramicView this$0;

            {
               this.this$0 = var1;
            }

            public Unit invoke() {
               this.this$0.returnBtn.setVisibility(0);
               return null;
            }
         });
      } else {
         SendKeyManager.getInstance().forceReverse(false);
      }
   }

   public void closeManual() {
      SendKeyManager.getInstance().forceReverse(false);
      this.returnBtn.setVisibility(8);
      this.manualLock = false;
   }

   public void openAuto() {
      if (this.manualLock) {
         BaseUtil.INSTANCE.runUi(new Function0(this) {
            final MyPanoramicView this$0;

            {
               this.this$0 = var1;
            }

            public Unit invoke() {
               this.this$0.returnBtn.setVisibility(8);
               return null;
            }
         });
      } else {
         SendKeyManager.getInstance().forceReverse(true);
      }
   }

   public void openManual() {
      SendKeyManager.getInstance().forceReverse(true);
      (new CountDownTimer(this, 500L, 100L) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            BaseUtil.INSTANCE.runUi(new Function0(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public Unit invoke() {
                  this.this$1.this$0.returnBtn.setVisibility(0);
                  return null;
               }
            });
         }

         public void onTick(long var1) {
         }
      }).start();
      this.manualLock = true;
   }
}
