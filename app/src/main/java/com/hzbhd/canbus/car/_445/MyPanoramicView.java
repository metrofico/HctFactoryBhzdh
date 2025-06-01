package com.hzbhd.canbus.car._445;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private LinearLayout avm_alert_view;
   private ImageView d2_360_View;
   private ImageView d3_360_View;
   private ImageView front_360_View;
   private ImageView left_360_View;
   private View lineView;
   private Context mContext;
   private ImageView out_360_View;
   private ImageView rear_360_View;
   private RelativeLayout relativeLayout;
   private ImageView right_360_View;
   private ScrollView scrollView;
   private View view;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      View var2 = LayoutInflater.from(var1).inflate(2131558804, this);
      this.view = var2;
      this.scrollView = (ScrollView)var2.findViewById(2131362882);
      if (SharePreUtil.getIntValue(this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 0) == 1) {
         this.scrollView.setVisibility(8);
         MyLog.temporaryTracking("隐藏360");
      } else {
         this.scrollView.setVisibility(0);
         MyLog.temporaryTracking("显示360");
      }

      this.relativeLayout = (RelativeLayout)this.view.findViewById(2131363090);
      this.front_360_View = (ImageView)this.view.findViewById(2131362265);
      this.rear_360_View = (ImageView)this.view.findViewById(2131363043);
      this.left_360_View = (ImageView)this.view.findViewById(2131362725);
      this.right_360_View = (ImageView)this.view.findViewById(2131363153);
      this.d2_360_View = (ImageView)this.view.findViewById(2131362166);
      this.d3_360_View = (ImageView)this.view.findViewById(2131362168);
      this.out_360_View = (ImageView)this.view.findViewById(2131362930);
      this.lineView = this.view.findViewById(2131362757);
      this.avm_alert_view = (LinearLayout)this.view.findViewById(2131361930);
      this.front_360_View.setOnClickListener(this);
      this.rear_360_View.setOnClickListener(this);
      this.left_360_View.setOnClickListener(this);
      this.right_360_View.setOnClickListener(this);
      this.d2_360_View.setOnClickListener(this);
      this.d3_360_View.setOnClickListener(this);
      this.out_360_View.setOnClickListener(this);
      if (SelectCanTypeUtil.getCurrentCanDiffId() == 2) {
         this.relativeLayout.setVisibility(8);
      } else if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
         this.relativeLayout.setVisibility(0);
         this.d2_360_View.setImageResource(2131231181);
         this.lineView.setVisibility(0);
         this.d2_360_View.setVisibility(0);
         this.d3_360_View.setVisibility(0);
         this.out_360_View.setVisibility(8);
      } else {
         this.relativeLayout.setVisibility(0);
         this.d3_360_View.setVisibility(0);
         this.out_360_View.setVisibility(8);
         this.out_360_View.setImageResource(-1);
         this.out_360_View.setOnClickListener((View.OnClickListener)null);
         this.lineView.setVisibility(8);
      }

   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private void sendCmd(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1});
   }

   public void avmCanBusInfoChange(Context var1, int[] var2) {
      if (var2[1] == 56) {
         LinearLayout var3;
         if (var2[2] == 1) {
            var3 = this.avm_alert_view;
            if (var3 == null) {
               return;
            }

            var3.setVisibility(0);
         } else {
            var3 = this.avm_alert_view;
            if (var3 != null) {
               var3.setVisibility(8);
            }
         }
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362166:
            this.sendCmd(5);
            break;
         case 2131362168:
            this.sendCmd(6);
            break;
         case 2131362265:
            this.sendCmd(1);
            break;
         case 2131362725:
            this.sendCmd(3);
            break;
         case 2131362930:
            this.sendCmd(0);
            break;
         case 2131363043:
            this.sendCmd(2);
            break;
         case 2131363153:
            this.sendCmd(4);
      }

   }

   public void refreshUi(int var1) {
      ImageView var4 = this.front_360_View;
      boolean var3 = false;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setSelected(var2);
      var4 = this.rear_360_View;
      if (var1 == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setSelected(var2);
      var4 = this.left_360_View;
      if (var1 == 3) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setSelected(var2);
      var4 = this.right_360_View;
      if (var1 == 4) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setSelected(var2);
      var4 = this.d2_360_View;
      if (var1 == 5) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setSelected(var2);
      var4 = this.d3_360_View;
      var2 = var3;
      if (var1 == 6) {
         var2 = true;
      }

      var4.setSelected(var2);
   }
}
