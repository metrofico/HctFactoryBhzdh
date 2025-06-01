package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialogAlert;

public class DvrStateView extends LinearLayout implements View.OnClickListener, MdNotifyListener {
   TextView connect_txt;
   Button format_sd_btn;
   TextView lock_txt;
   Context mContext;
   TextView sd_txt;
   TextView tag_txt;
   TextView time_txt;
   TextView version_txt;
   View view;

   public DvrStateView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DvrStateView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DvrStateView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.view = LayoutInflater.from(var1).inflate(2131558530, this, true);
      this.initData();
   }

   private void initData() {
      this.sd_txt = (TextView)this.view.findViewById(2131363259);
      Button var1 = (Button)this.view.findViewById(2131362261);
      this.format_sd_btn = var1;
      var1.setOnClickListener(new DvrStateView$$ExternalSyntheticLambda0(this));
      this.connect_txt = (TextView)this.view.findViewById(2131362149);
      this.lock_txt = (TextView)this.view.findViewById(2131362817);
      this.tag_txt = (TextView)this.view.findViewById(2131363501);
      this.time_txt = (TextView)this.view.findViewById(2131363533);
      this.version_txt = (TextView)this.view.findViewById(2131363752);
      this.refreshUi();
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362261) {
         ItemDialogAlert var2 = new ItemDialogAlert();
         Context var3 = this.mContext;
         var2.showDialog(var3, var3.getString(2131765889), new MdOnClickListener(this) {
            final DvrStateView this$0;

            {
               this.this$0 = var1;
            }

            public void clickPosition(int var1) {
               this.this$0.format_sd_btn.setVisibility(8);
               DvrSender.send(new byte[]{91, 0});
            }
         });
      }

   }

   public void refreshUi() {
      if (GeneralDvrState.sd == 0) {
         this.sd_txt.setText(this.mContext.getString(2131765884) + this.mContext.getString(2131765885));
         this.format_sd_btn.setVisibility(8);
      } else if (GeneralDvrState.sd == 1) {
         this.sd_txt.setText(this.mContext.getString(2131765884) + this.mContext.getString(2131765886));
         this.format_sd_btn.setVisibility(8);
      } else if (GeneralDvrState.sd == 2) {
         this.sd_txt.setText(this.mContext.getString(2131765884) + this.mContext.getString(2131765887));
         this.format_sd_btn.setVisibility(8);
      } else if (GeneralDvrState.sd == 3) {
         this.sd_txt.setText(this.mContext.getString(2131765884) + this.mContext.getString(2131765888));
         this.format_sd_btn.setVisibility(0);
      }

      if (GeneralDvrState.formatSdFail) {
         this.format_sd_btn.setVisibility(0);
      }

      if (GeneralDvrState.lock) {
         this.lock_txt.setText(this.mContext.getString(2131765881) + this.mContext.getString(2131765882));
      } else {
         this.lock_txt.setText(this.mContext.getString(2131765881) + this.mContext.getString(2131765883));
      }

      this.tag_txt.setText(this.mContext.getString(2131765893) + GeneralDvrState.tag);
      this.time_txt.setText(this.mContext.getString(2131765898) + GeneralDvrState.date + "  " + GeneralDvrState.time);
      this.version_txt.setText(this.mContext.getString(2131765899) + GeneralDvrState.version);
   }

   public void updateUi() {
      this.refreshUi();
   }
}
