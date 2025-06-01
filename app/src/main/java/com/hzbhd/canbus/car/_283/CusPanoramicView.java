package com.hzbhd.canbus.car._283;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.utils._283_SharedPreferencesUtils;
import com.hzbhd.canbus.car_cus._283.view.RadarView;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.SharePreUtil;

public class CusPanoramicView extends RelativeLayout {
   private ImageView baselineImage;
   private int[] leftImageTracks = new int[]{2131233222, 2131233238, 2131233240, 2131233242, 2131233244, 2131233246, 2131233248, 2131233250, 2131233252, 2131233202, 2131233204, 2131233206, 2131233208, 2131233210, 2131233212, 2131233214, 2131233216, 2131233218, 2131233220, 2131233224, 2131233226, 2131233228, 2131233230, 2131233232, 2131233234, 2131233236};
   private Context mContext;
   private RadarView mRadarView;
   private int[] righttImageTracks = new int[]{2131233223, 2131233239, 2131233241, 2131233243, 2131233245, 2131233247, 2131233249, 2131233251, 2131233253, 2131233203, 2131233205, 2131233207, 2131233209, 2131233211, 2131233213, 2131233215, 2131233217, 2131233219, 2131233221, 2131233225, 2131233227, 2131233229, 2131233231, 2131233233, 2131233235, 2131233237};
   private ImageView trackImage;
   private int tracks0 = 2131233201;

   public CusPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      View var2 = LayoutInflater.from(var1).inflate(2131558800, this);
      this.mRadarView = (RadarView)var2.findViewById(2131363007);
      this.trackImage = (ImageView)var2.findViewById(2131363557);
      this.baselineImage = (ImageView)var2.findViewById(2131361968);
      this.mRadarView.setRadarBackgroundColor(0);
   }

   public CusPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public CusPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   private int getImageResource(int var1) {
      if (var1 > 0) {
         return this.getResDrawable(var1, this.righttImageTracks);
      } else {
         return var1 < 0 ? this.getResDrawable(-var1, this.leftImageTracks) : this.tracks0;
      }
   }

   private boolean getJZX() {
      return SharePreUtil.getBoolValue(this.mContext, _283_SharedPreferencesUtils.KEY_JZX_SWITCH, true);
   }

   private boolean getLD() {
      return SharePreUtil.getBoolValue(this.mContext, _283_SharedPreferencesUtils.KEY_LD_SWITCH, true);
   }

   private int getResDrawable(int var1, int[] var2) {
      switch (var1) {
         case 1:
            return var2[1];
         case 2:
            return var2[2];
         case 3:
            return var2[3];
         case 4:
            return var2[4];
         case 5:
            return var2[5];
         case 6:
            return var2[6];
         case 7:
            return var2[7];
         case 8:
            return var2[8];
         case 9:
            return var2[9];
         case 10:
            return var2[10];
         case 11:
         case 12:
            return var2[11];
         case 13:
         case 14:
            return var2[12];
         case 15:
         case 16:
            return var2[13];
         case 17:
         case 18:
            return var2[14];
         case 19:
         case 20:
            return var2[15];
         case 21:
         case 22:
            return var2[16];
         case 23:
         case 24:
            return var2[17];
         case 25:
         case 26:
            return var2[18];
         case 27:
         case 28:
            return var2[19];
         case 29:
         case 30:
            return var2[20];
         case 31:
         case 32:
            return var2[21];
         case 33:
         case 34:
            return var2[22];
         case 35:
         case 36:
            return var2[23];
         case 37:
         case 38:
            return var2[24];
         case 39:
         case 40:
            return var2[25];
         default:
            return this.tracks0;
      }
   }

   private boolean getZJX() {
      return SharePreUtil.getBoolValue(this.mContext, _283_SharedPreferencesUtils.KEY_ZJX_SWITCH, true);
   }

   public void refreRadarUi() {
      this.mRadarView.updateUi();
   }

   public void refreshUiLine() {
      if (this.getZJX()) {
         this.trackImage.setVisibility(0);
      } else {
         this.trackImage.setVisibility(8);
      }

      if (this.getJZX()) {
         this.baselineImage.setVisibility(0);
      } else {
         this.baselineImage.setVisibility(8);
      }

      if (this.getLD()) {
         this.mRadarView.showRadar();
      } else {
         this.mRadarView.goneRadar();
      }

   }

   public void updateParkUi() {
      this.trackImage.setImageResource(this.getImageResource(GeneralParkData.trackAngle));
   }
}
