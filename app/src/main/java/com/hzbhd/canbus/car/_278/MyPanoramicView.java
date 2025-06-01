package com.hzbhd.canbus.car._278;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private Context mContext;
   boolean mListBtns = false;
   private ImageButton m_back_rev_guide;
   private ImageButton m_broadside_rev_guide;
   private ImageButton m_h2_exit;
   private ImageButton m_h2_hide;
   private LinearLayout m_ll_haval_h2;
   private LinearLayout m_ll_haval_h2_right_list_btns;
   private ImageButton m_vertical_rev_guide;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      View var2 = LayoutInflater.from(var1).inflate(2131558799, this);
      this.m_ll_haval_h2 = (LinearLayout)var2.findViewById(2131362778);
      this.m_ll_haval_h2_right_list_btns = (LinearLayout)var2.findViewById(2131362781);
      this.hideH2Btns(this.mListBtns);
      this.m_h2_exit = (ImageButton)var2.findViewById(2131362364);
      this.m_h2_hide = (ImageButton)var2.findViewById(2131362365);
      this.m_vertical_rev_guide = (ImageButton)var2.findViewById(2131363753);
      this.m_broadside_rev_guide = (ImageButton)var2.findViewById(2131361990);
      this.m_back_rev_guide = (ImageButton)var2.findViewById(2131361959);
      this.m_h2_exit.setOnClickListener(this);
      this.m_h2_hide.setOnClickListener(this);
      this.m_vertical_rev_guide.setOnClickListener(this);
      this.m_broadside_rev_guide.setOnClickListener(this);
      this.m_back_rev_guide.setOnClickListener(this);
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   private void hideH2Btns(boolean var1) {
      if (var1) {
         this.m_ll_haval_h2_right_list_btns.setVisibility(0);
      } else {
         this.m_ll_haval_h2_right_list_btns.setVisibility(8);
      }

   }

   private void resetVisual() {
      this.setCameraBtnStatus(this.m_vertical_rev_guide, true);
      this.setCameraBtnStatus(this.m_broadside_rev_guide, true);
      this.setCameraBtnStatus(this.m_back_rev_guide, true);
   }

   private void sendCameraButtonCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var1});
   }

   private void setCameraBtnStatus(View var1, boolean var2) {
      float var3;
      if (var2) {
         var3 = 1.0F;
      } else {
         var3 = 0.0F;
      }

      var1.setAlpha(var3);
      var1.setEnabled(var2);
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131361959:
            this.resetVisual();
            this.setCameraBtnStatus(this.m_back_rev_guide, false);
            this.sendCameraButtonCommand(3);
            break;
         case 2131361990:
            this.resetVisual();
            this.setCameraBtnStatus(this.m_broadside_rev_guide, false);
            this.sendCameraButtonCommand(2);
            break;
         case 2131362364:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 0});
            break;
         case 2131362365:
            boolean var2 = this.mListBtns ^ true;
            this.mListBtns = var2;
            this.hideH2Btns(var2);
            break;
         case 2131363753:
            this.resetVisual();
            this.setCameraBtnStatus(this.m_vertical_rev_guide, false);
            this.sendCameraButtonCommand(1);
      }

   }
}
