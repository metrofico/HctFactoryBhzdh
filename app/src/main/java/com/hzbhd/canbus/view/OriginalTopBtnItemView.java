package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class OriginalTopBtnItemView extends RelativeLayout {
   private Button mBtn;
   private OnItemClickListener mOnItemClickListener;

   public OriginalTopBtnItemView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public OriginalTopBtnItemView(Context var1, String var2) {
      super(var1);
      this.initViews(var1, var2);
   }

   private void initViews(Context var1, String var2) {
      Button var5 = (Button)LayoutInflater.from(var1).inflate(2131558862, this).findViewById(2131362060);
      this.mBtn = var5;
      var5.setOnClickListener(new View.OnClickListener(this) {
         final OriginalTopBtnItemView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnItemClickListener != null) {
               this.this$0.mOnItemClickListener.onClick();
            }

         }
      });
      var2.hashCode();
      int var4 = var2.hashCode();
      byte var3 = -1;
      switch (var4) {
         case -1780016412:
            if (var2.equals("rear_cdc_right")) {
               var3 = 0;
            }
            break;
         case -1715975548:
            if (var2.equals("select_cd")) {
               var3 = 1;
            }
            break;
         case -1415634215:
            if (var2.equals("rdm_disc")) {
               var3 = 2;
            }
            break;
         case -1415569083:
            if (var2.equals("rdm_fold")) {
               var3 = 3;
            }
            break;
         case -1406322208:
            if (var2.equals("auto_p")) {
               var3 = 4;
            }
            break;
         case -1315390686:
            if (var2.equals("exit_cd")) {
               var3 = 5;
            }
            break;
         case -1268966290:
            if (var2.equals("folder")) {
               var3 = 6;
            }
            break;
         case -1183792455:
            if (var2.equals("insert")) {
               var3 = 7;
            }
            break;
         case -854288633:
            if (var2.equals("rear_cdc")) {
               var3 = 8;
            }
            break;
         case -150466416:
            if (var2.equals("usb_box")) {
               var3 = 9;
            }
            break;
         case -136075545:
            if (var2.equals("disc_scan")) {
               var3 = 10;
            }
            break;
         case -57830694:
            if (var2.equals("rear_cdc_down")) {
               var3 = 11;
            }
            break;
         case -57602497:
            if (var2.equals("rear_cdc_left")) {
               var3 = 12;
            }
            break;
         case -57572457:
            if (var2.equals("rear_cdc_menu")) {
               var3 = 13;
            }
            break;
         case 3116:
            if (var2.equals("am")) {
               var3 = 14;
            }
            break;
         case 3169:
            if (var2.equals("cd")) {
               var3 = 15;
            }
            break;
         case 3271:
            if (var2.equals("fm")) {
               var3 = 16;
            }
            break;
         case 3681:
            if (var2.equals("st")) {
               var3 = 17;
            }
            break;
         case 96964:
            if (var2.equals("aux")) {
               var3 = 18;
            }
            break;
         case 98338:
            if (var2.equals("cdc")) {
               var3 = 19;
            }
            break;
         case 99858:
            if (var2.equals("dvd")) {
               var3 = 20;
            }
            break;
         case 101450:
            if (var2.equals("fm1")) {
               var3 = 21;
            }
            break;
         case 101451:
            if (var2.equals("fm2")) {
               var3 = 22;
            }
            break;
         case 108272:
            if (var2.equals("mp3")) {
               var3 = 23;
            }
            break;
         case 112763:
            if (var2.equals("rdm")) {
               var3 = 24;
            }
            break;
         case 112769:
            if (var2.equals("rds")) {
               var3 = 25;
            }
            break;
         case 113142:
            if (var2.equals("rpt")) {
               var3 = 26;
            }
            break;
         case 117835:
            if (var2.equals("wma")) {
               var3 = 27;
            }
            break;
         case 3327275:
            if (var2.equals("lock")) {
               var3 = 28;
            }
            break;
         case 3373990:
            if (var2.equals("navi")) {
               var3 = 29;
            }
            break;
         case 3524221:
            if (var2.equals("scan")) {
               var3 = 30;
            }
            break;
         case 92923732:
            if (var2.equals("am_st")) {
               var3 = 31;
            }
            break;
         case 106858757:
            if (var2.equals("power")) {
               var3 = 32;
            }
            break;
         case 108270587:
            if (var2.equals("radio")) {
               var3 = 33;
            }
            break;
         case 109250952:
            if (var2.equals("scane")) {
               var3 = 34;
            }
            break;
         case 293915491:
            if (var2.equals("fold_add")) {
               var3 = 35;
            }
            break;
         case 293933314:
            if (var2.equals("fold_sub")) {
               var3 = 36;
            }
            break;
         case 436485570:
            if (var2.equals("rpt_track")) {
               var3 = 37;
            }
            break;
         case 844879422:
            if (var2.equals("rpt_disc")) {
               var3 = 38;
            }
            break;
         case 844944554:
            if (var2.equals("rpt_fold")) {
               var3 = 39;
            }
            break;
         case 1042163292:
            if (var2.equals("preset_select")) {
               var3 = 40;
            }
            break;
         case 1062723499:
            if (var2.equals("rdm_off")) {
               var3 = 41;
            }
            break;
         case 1085444827:
            if (var2.equals("refresh")) {
               var3 = 42;
            }
            break;
         case 1142446977:
            if (var2.equals("preset_store")) {
               var3 = 43;
            }
            break;
         case 1412737958:
            if (var2.equals("rpt_off")) {
               var3 = 44;
            }
            break;
         case 1602773140:
            if (var2.equals("aux_insert")) {
               var3 = 45;
            }
            break;
         case 1863625236:
            if (var2.equals("rear_cdc_ok")) {
               var3 = 46;
            }
            break;
         case 1863625427:
            if (var2.equals("rear_cdc_up")) {
               var3 = 47;
            }
      }

      switch (var3) {
         case 0:
            this.mBtn.setText(2131769780);
            break;
         case 1:
            this.mBtn.setText(2131769897);
            break;
         case 2:
            this.mBtn.setText(2131769764);
            break;
         case 3:
            this.mBtn.setText(2131769765);
            break;
         case 4:
            this.mBtn.setText(2131767825);
            break;
         case 5:
            this.mBtn.setText(2131768231);
            break;
         case 6:
            this.mBtn.setText(2131768269);
            break;
         case 7:
            this.mBtn.setText(2131768902);
            break;
         case 8:
            this.mBtn.setText(2131769775);
            break;
         case 9:
            this.mBtn.setText(2131770257);
            break;
         case 10:
            this.mBtn.setText(2131768135);
            break;
         case 11:
            this.mBtn.setText(2131769776);
            break;
         case 12:
            this.mBtn.setText(2131769777);
            break;
         case 13:
            this.mBtn.setText(2131769778);
            break;
         case 14:
            this.mBtn.setText(2131767785);
            break;
         case 15:
            this.mBtn.setText(2131767994);
            break;
         case 16:
            this.mBtn.setText(2131768264);
            break;
         case 17:
            this.mBtn.setText(2131769990);
            break;
         case 18:
            this.mBtn.setText(2131767840);
            break;
         case 19:
            this.mBtn.setText(2131768001);
            break;
         case 20:
            this.mBtn.setText(2131768184);
            break;
         case 21:
            this.mBtn.setText(2131768265);
            break;
         case 22:
            this.mBtn.setText(2131768266);
            break;
         case 23:
            this.mBtn.setText(2131769321);
            break;
         case 24:
            this.mBtn.setText(2131769763);
            break;
         case 25:
            this.mBtn.setText(2131769768);
            break;
         case 26:
            this.mBtn.setText(2131769858);
            break;
         case 27:
            this.mBtn.setText(2131770867);
            break;
         case 28:
            this.mBtn.setText(2131769515);
            break;
         case 29:
            this.mBtn.setText(2131769333);
            break;
         case 30:
            this.mBtn.setText(2131769873);
            break;
         case 31:
            this.mBtn.setText(2131767786);
            break;
         case 32:
            this.mBtn.setText(2131766864);
            break;
         case 33:
            this.mBtn.setText(2131769755);
            break;
         case 34:
            this.mBtn.setText(2131769874);
            break;
         case 35:
            this.mBtn.setText(2131768267);
            break;
         case 36:
            this.mBtn.setText(2131768268);
            break;
         case 37:
            this.mBtn.setText(2131769863);
            break;
         case 38:
            this.mBtn.setText(2131769859);
            break;
         case 39:
            this.mBtn.setText(2131769860);
            break;
         case 40:
            this.mBtn.setText(2131769652);
            break;
         case 41:
            this.mBtn.setText(2131769767);
            break;
         case 42:
            this.mBtn.setText(2131769793);
            break;
         case 43:
            this.mBtn.setText(2131769653);
            break;
         case 44:
            this.mBtn.setText(2131769862);
            break;
         case 45:
            this.mBtn.setText(2131767841);
            break;
         case 46:
            this.mBtn.setText(2131769779);
            break;
         case 47:
            this.mBtn.setText(2131769781);
      }

   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void turn(boolean var1) {
      if (var1) {
         this.mBtn.setBackgroundResource(2131234415);
      } else {
         this.mBtn.setBackgroundResource(2131234460);
      }

   }

   public interface OnItemClickListener {
      void onClick();
   }
}
