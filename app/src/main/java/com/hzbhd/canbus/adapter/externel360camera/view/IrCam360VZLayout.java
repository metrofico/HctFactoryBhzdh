package com.hzbhd.canbus.adapter.externel360camera.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.adapter.externel360camera.VZ360Constance;
import com.hzbhd.midware.proxy.R;

public class IrCam360VZLayout extends LinearLayout implements View.OnClickListener {
   boolean showKeyBoard;
   View view;
   private int[] views;

   public IrCam360VZLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public IrCam360VZLayout(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public IrCam360VZLayout(Context var1, AttributeSet var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public IrCam360VZLayout(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.views = new int[]{R.id.vz_irkey_button, R.id.vz_irkey_button_keybroad, R.id.id_selector_bottom_vz_1, R.id.id_selector_bottom_vz_2, R.id.id_selector_bottom_vz_3, R.id.id_selector_bottom_vz_4, R.id.id_selector_bottom_vz_5, R.id.id_selector_bottom_vz_6, R.id.id_selector_bottom_vz_7, R.id.id_selector_bottom_vz_8, R.id.id_selector_bottom_vz_9, R.id.id_selector_bottom_vz_0, R.id.id_selector_bottom_vz_add, R.id.id_selector_bottom_vz_minusr, R.id.id_selector_bottom_vz_left, R.id.id_selector_bottom_vz_right, R.id.id_selector_bottom_vz_up, R.id.id_selector_bottom_vz_down, R.id.id_selector_bottom_vz_ok, R.id.id_selector_bottom_vz_back, R.id.id_selector_bottom_vz_login, R.id.id_selector_bottom_vz_info, R.id.id_selector_bottom_vz_prev, R.id.id_selector_bottom_vz_playpause, R.id.id_selector_bottom_vz_next, R.id.id_selector_bottom_vz_stop, R.id.id_selector_bottom_vz_keybroad};
      this.showKeyBoard = false;
      View var5 = LayoutInflater.from(var1).inflate(R.layout.vz_ir_key_rl, (ViewGroup)null);
      this.view = var5;
      this.addView(var5);
      var3 = 0;

      while(true) {
         int[] var6 = this.views;
         if (var3 >= var6.length) {
            this.hideKeyBoard();
            this.show_vz_irkey_button();
            this.showKeyBoard = false;
            return;
         }

         this.view.findViewById(var6[var3]).setOnClickListener(this);
         ++var3;
      }
   }

   public static void addVIew(Context var0, View var1) {
      if (VZ360Constance.isVZ360Camera()) {
         IrCam360VZLayout var2 = new IrCam360VZLayout(var0);
         var2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
         var2.setGravity(80);
         ((RelativeLayout)var1).addView(var2);
      }

   }

   private boolean isTouchPointInView(View var1, int var2, int var3) {
      if (var1 == null) {
         return false;
      } else {
         int[] var8 = new int[2];
         var1.getLocationOnScreen(var8);
         int var5 = var8[0];
         int var7 = var8[1];
         int var4 = var1.getMeasuredWidth();
         int var6 = var1.getMeasuredHeight();
         return var3 >= var7 && var3 <= var6 + var7 && var2 >= var5 && var2 <= var4 + var5;
      }
   }

   private void setVisual(View var1, int var2) {
      var1.setVisibility(var2);
   }

   public boolean dispatchTouchEvent(MotionEvent var1) {
      int var3 = (int)var1.getRawX();
      int var2 = (int)var1.getRawY();
      if (!this.isTouchPointInView(this.view.findViewById(R.id.vz_irkey_button_keybroad), var3, var2) && !this.isTouchPointInView(this.view.findViewById(R.id.bottom_control), var3, var2) && !this.isTouchPointInView(this.view.findViewById(R.id.left_control), var3, var2)) {
         if (this.is_visible_vz_irkey_button()) {
            this.hideKeyBoard();
            this.hide_vz_irkey_button();
         } else {
            this.showKeyBoard();
            this.show_vz_irkey_button();
         }
      }

      return super.dispatchTouchEvent(var1);
   }

   public void hideKeyBoard() {
      this.setVisual(this.view.findViewById(R.id.vz_irkey_button_keybroad), 8);
      this.showKeyBoard = false;
   }

   public void hide_vz_irkey_button() {
      this.setVisual(this.view.findViewById(R.id.vz_irkey_button), 8);
   }

   public boolean is_visible_vz_irkey_button() {
      boolean var1;
      if (this.view.findViewById(R.id.vz_irkey_button).getVisibility() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != R.id.vz_irkey_button && var2 != R.id.vz_irkey_button_keybroad) {
         if (var2 == R.id.id_selector_bottom_vz_1) {
            VZ360Constance.sendMDs(VZ360Constance.comds_1);
         } else if (var2 == R.id.id_selector_bottom_vz_2) {
            VZ360Constance.sendMDs(VZ360Constance.comds_2);
         } else if (var2 == R.id.id_selector_bottom_vz_3) {
            VZ360Constance.sendMDs(VZ360Constance.comds_3);
         } else if (var2 == R.id.id_selector_bottom_vz_4) {
            VZ360Constance.sendMDs(VZ360Constance.comds_4);
         } else if (var2 == R.id.id_selector_bottom_vz_5) {
            VZ360Constance.sendMDs(VZ360Constance.comds_5);
         } else if (var2 == R.id.id_selector_bottom_vz_6) {
            VZ360Constance.sendMDs(VZ360Constance.comds_6);
         } else if (var2 == R.id.id_selector_bottom_vz_7) {
            VZ360Constance.sendMDs(VZ360Constance.comds_7);
         } else if (var2 == R.id.id_selector_bottom_vz_8) {
            VZ360Constance.sendMDs(VZ360Constance.comds_8);
         } else if (var2 == R.id.id_selector_bottom_vz_9) {
            VZ360Constance.sendMDs(VZ360Constance.comds_9);
         } else if (var2 == R.id.id_selector_bottom_vz_0) {
            VZ360Constance.sendMDs(VZ360Constance.comds_0);
         } else if (var2 == R.id.id_selector_bottom_vz_add) {
            VZ360Constance.sendMDs(VZ360Constance.comds_add);
         } else if (var2 == R.id.id_selector_bottom_vz_minusr) {
            VZ360Constance.sendMDs(VZ360Constance.comds_minus);
         } else if (var2 == R.id.id_selector_bottom_vz_left) {
            VZ360Constance.sendMDs(VZ360Constance.LEFT);
         } else if (var2 == R.id.id_selector_bottom_vz_right) {
            VZ360Constance.sendMDs(VZ360Constance.RIGHT);
         } else if (var2 == R.id.id_selector_bottom_vz_up) {
            VZ360Constance.sendMDs(VZ360Constance.UP);
         } else if (var2 == R.id.id_selector_bottom_vz_down) {
            VZ360Constance.sendMDs(VZ360Constance.DOWN);
         } else if (var2 == R.id.id_selector_bottom_vz_ok) {
            VZ360Constance.sendMDs(VZ360Constance.ENTER);
         } else if (var2 == R.id.id_selector_bottom_vz_back) {
            VZ360Constance.sendMDs(VZ360Constance.RETURN);
         } else if (var2 == R.id.id_selector_bottom_vz_login) {
            VZ360Constance.sendMDs(VZ360Constance.LOGIN);
         } else if (var2 == R.id.id_selector_bottom_vz_info) {
            VZ360Constance.sendMDs(VZ360Constance.INFO);
         } else if (var2 == R.id.id_selector_bottom_vz_prev) {
            VZ360Constance.sendMDs(VZ360Constance.PREV);
         } else if (var2 == R.id.id_selector_bottom_vz_playpause) {
            VZ360Constance.sendMDs(VZ360Constance.PLAY);
         } else if (var2 == R.id.id_selector_bottom_vz_next) {
            VZ360Constance.sendMDs(VZ360Constance.NEXT);
         } else if (var2 == R.id.id_selector_bottom_vz_stop) {
            VZ360Constance.sendMDs(VZ360Constance.STOP);
         } else if (var2 == R.id.id_selector_bottom_vz_keybroad) {
            if (!this.showKeyBoard) {
               this.showKeyBoard = true;
               this.showKeyBoard();
            } else {
               this.showKeyBoard = false;
               this.hideKeyBoard();
            }
         }
      }

   }

   public void setLayoutParams(ViewGroup.LayoutParams var1) {
      super.setLayoutParams(var1);
   }

   public void showKeyBoard() {
      if (this.showKeyBoard) {
         this.setVisual(this.view.findViewById(R.id.vz_irkey_button_keybroad), 0);
      }

   }

   public void show_vz_irkey_button() {
      this.setVisual(this.view.findViewById(R.id.vz_irkey_button), 0);
   }
}
