package com.hzbhd.canbus.park.radar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.HashMap;
import java.util.Iterator;

public class RadarView extends RelativeLayout {
   private final String TAG = "RadarView";
   private TextView b_l_m_txt;
   private TextView b_l_txt;
   private TextView b_r_m_txt;
   private TextView b_r_txt;
   private TextView back_radar_left_mid_small_txt;
   private TextView back_radar_left_small_txt;
   private TextView back_radar_right_mid_small_txt;
   private TextView back_radar_right_small_txt;
   private View bottom_radar;
   private TextView f_l_m_txt;
   private TextView f_l_txt;
   private TextView f_r_m_txt;
   private TextView f_r_txt;
   private TextView front_radar_left_mid_small_txt;
   private TextView front_radar_left_small_txt;
   private TextView front_radar_right_mid_small_txt;
   private TextView front_radar_right_small_txt;
   private ImageButton full_screen_btn;
   private ImageButton hide_btn;
   private TextView left_radar_front_mid_small_txt;
   private TextView left_radar_front_small_txt;
   private TextView left_radar_rear_mid_small_txt;
   private TextView left_radar_rear_small_txt;
   private Context mContext;
   private TextView mFullOneDistanceTv;
   private TextView mFullUnitTv;
   private TextView mLeftTopTisTv;
   private View.OnClickListener mOnClickListener = new View.OnClickListener(this) {
      final RadarView this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         switch (var1.getId()) {
            case 2131362295:
               this.this$0.fullScreenRadarView();
               break;
            case 2131362370:
               this.this$0.hideRadarView();
               break;
            case 2131362999:
            case 2131363010:
               this.this$0.showRadarView();
         }

      }
   };
   private TextView mOneDistanceTv;
   private SparseArray mRadarDataArray;
   private TextView mSmallUnitTv;
   private View mView;
   private TextView mWarningTisTv;
   private ImageButton pull_btn;
   private TextView r_l_m_txt;
   private TextView r_l_txt;
   private TextView r_r_m_txt;
   private TextView r_r_txt;
   private ImageButton radar_camera_sw;
   private View radar_distan_front;
   private View radar_distan_rear;
   private View radar_distance_front_small;
   private View radar_distance_left_small;
   private View radar_distance_rear_small;
   private View radar_distance_right_small;
   private View radar_full_screen;
   private View radar_small_screen;
   private TextView right_radar_front_mid_small_txt;
   private TextView right_radar_front_small_txt;
   private TextView right_radar_rear_mid_small_txt;
   private TextView right_radar_rear_small_txt;
   private TextView t_l_m_txt;
   private TextView t_l_txt;
   private TextView t_r_m_txt;
   private TextView t_r_txt;
   private View top_radar;

   public RadarView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558866, this);
      this.initViews();
      this.initData();
   }

   private void fullScreenRadarView() {
      LogUtil.showLog("RadarView", "fullScreenRadarView");
      this.pull_btn.setVisibility(8);
      this.hide_btn.setVisibility(8);
      this.radar_small_screen.setVisibility(8);
      this.full_screen_btn.setVisibility(8);
      this.radar_full_screen.setVisibility(0);
   }

   private void hideDistanceView() {
      this.radar_distan_rear.setVisibility(8);
      this.radar_distan_front.setVisibility(8);
      this.radar_distance_left_small.setVisibility(8);
      this.radar_distance_right_small.setVisibility(8);
      this.top_radar.setVisibility(8);
      this.bottom_radar.setVisibility(8);
      this.front_radar_left_small_txt.setVisibility(4);
      this.front_radar_left_mid_small_txt.setVisibility(4);
      this.front_radar_right_mid_small_txt.setVisibility(4);
      this.front_radar_right_small_txt.setVisibility(4);
      this.back_radar_left_small_txt.setVisibility(4);
      this.back_radar_left_mid_small_txt.setVisibility(4);
      this.back_radar_right_mid_small_txt.setVisibility(4);
      this.back_radar_right_small_txt.setVisibility(4);
      this.left_radar_front_small_txt.setVisibility(4);
      this.left_radar_front_mid_small_txt.setVisibility(4);
      this.left_radar_rear_mid_small_txt.setVisibility(4);
      this.left_radar_rear_small_txt.setVisibility(4);
      this.right_radar_front_mid_small_txt.setVisibility(4);
      this.right_radar_front_small_txt.setVisibility(4);
      this.right_radar_rear_mid_small_txt.setVisibility(4);
      this.right_radar_rear_small_txt.setVisibility(4);
      this.mSmallUnitTv.setVisibility(4);
      this.mFullUnitTv.setVisibility(4);
   }

   private void initData() {
      SparseArray var1 = new SparseArray();
      this.mRadarDataArray = var1;
      var1.put(Constant.RadarLocation.FRONT_LEFT.ordinal(), new RadarData(this, "front_radar_left_", "front_radar_left_small_", (ImageView)this.mView.findViewById(2131362273), (ImageView)this.mView.findViewById(2131362278)));
      this.mRadarDataArray.append(Constant.RadarLocation.FRONT_MID_LEFT.ordinal(), new RadarData(this, "front_radar_left_mid_", "front_radar_left_mid_small_", (ImageView)this.mView.findViewById(2131362274), (ImageView)this.mView.findViewById(2131362275)));
      this.mRadarDataArray.append(Constant.RadarLocation.FRONT_MID_RIGHT.ordinal(), new RadarData(this, "front_radar_right_mid_", "front_radar_right_mid_small_", (ImageView)this.mView.findViewById(2131362282), (ImageView)this.mView.findViewById(2131362283)));
      this.mRadarDataArray.append(Constant.RadarLocation.FRONT_RIGHT.ordinal(), new RadarData(this, "front_radar_right_", "front_radar_right_small_", (ImageView)this.mView.findViewById(2131362281), (ImageView)this.mView.findViewById(2131362286)));
      this.mRadarDataArray.append(Constant.RadarLocation.FRONT_LEFT_PROBE.ordinal(), new RadarData(this, "_01_zc_zqx_", "_01_zcs_zqx_", (ImageView)this.mView.findViewById(2131362277), (ImageView)this.mView.findViewById(2131362279)));
      this.mRadarDataArray.append(Constant.RadarLocation.FRONT_RIGHT_PROBE.ordinal(), new RadarData(this, "_01_zc_zq_", "_01_zcs_zq_", (ImageView)this.mView.findViewById(2131362285), (ImageView)this.mView.findViewById(2131362287)));
      this.mRadarDataArray.append(Constant.RadarLocation.REAR_LEFT.ordinal(), new RadarData(this, "back_radar_left_", "back_radar_left_small_", (ImageView)this.mView.findViewById(2131361943), (ImageView)this.mView.findViewById(2131361949)));
      this.mRadarDataArray.append(Constant.RadarLocation.REAR_MID_LEFT.ordinal(), new RadarData(this, "back_radar_left_mid_", "back_radar_left_mid_small_", (ImageView)this.mView.findViewById(2131361944), (ImageView)this.mView.findViewById(2131361945)));
      this.mRadarDataArray.append(Constant.RadarLocation.REAR_MID_RIGHT.ordinal(), new RadarData(this, "back_radar_right_mid_", "back_radar_right_mid_small_", (ImageView)this.mView.findViewById(2131361952), (ImageView)this.mView.findViewById(2131361953)));
      this.mRadarDataArray.append(Constant.RadarLocation.REAR_RIGHT.ordinal(), new RadarData(this, "back_radar_right_", "back_radar_right_small_", (ImageView)this.mView.findViewById(2131361951), (ImageView)this.mView.findViewById(2131361957)));
      this.mRadarDataArray.append(Constant.RadarLocation.REAR_LEFT_PROBE.ordinal(), new RadarData(this, "_01_zcs_yhx_", "_01_zc_yhx_", (ImageView)this.mView.findViewById(2131361947), (ImageView)this.mView.findViewById(2131361948)));
      this.mRadarDataArray.append(Constant.RadarLocation.REAR_RIGHT_PROBE.ordinal(), new RadarData(this, "_01_zcs_yh_", "_01_zc_yh_", (ImageView)this.mView.findViewById(2131361955), (ImageView)this.mView.findViewById(2131361956)));
      this.mRadarDataArray.append(Constant.RadarLocation.LEFT_FRONT.ordinal(), new RadarData(this, "left_radar_front_", "left_radar_front_small_", (ImageView)this.mView.findViewById(2131362730), (ImageView)this.mView.findViewById(2131362734)));
      this.mRadarDataArray.append(Constant.RadarLocation.LEFT_MID_FRONT.ordinal(), new RadarData(this, "left_radar_front_mid_", "left_radar_front_mid_small_", (ImageView)this.mView.findViewById(2131362731), (ImageView)this.mView.findViewById(2131362732)));
      this.mRadarDataArray.append(Constant.RadarLocation.LEFT_MID_REAR.ordinal(), new RadarData(this, "left_radar_rear_mid_", "left_radar_rear_mid_small_", (ImageView)this.mView.findViewById(2131362739), (ImageView)this.mView.findViewById(2131362740)));
      this.mRadarDataArray.append(Constant.RadarLocation.LEFT_REAR.ordinal(), new RadarData(this, "left_radar_rear_", "left_radar_rear_small_", (ImageView)this.mView.findViewById(2131362738), (ImageView)this.mView.findViewById(2131362742)));
      this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_FRONT.ordinal(), new RadarData(this, "right_radar_front_", "right_radar_front_small_", (ImageView)this.mView.findViewById(2131363160), (ImageView)this.mView.findViewById(2131363162)));
      this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_MID_FRONT.ordinal(), new RadarData(this, "right_radar_front_mid_", "right_radar_front_mid_small_", (ImageView)this.mView.findViewById(2131363161), (ImageView)this.mView.findViewById(2131363164)));
      this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_MID_REAR.ordinal(), new RadarData(this, "right_radar_rear_mid_", "right_radar_rear_mid_small_", (ImageView)this.mView.findViewById(2131363169), (ImageView)this.mView.findViewById(2131363170)));
      this.mRadarDataArray.append(Constant.RadarLocation.RIGHT_REAR.ordinal(), new RadarData(this, "right_radar_rear_", "right_radar_rear_small_", (ImageView)this.mView.findViewById(2131363168), (ImageView)this.mView.findViewById(2131363172)));
   }

   private void initViews() {
      this.radar_small_screen = this.mView.findViewById(2131363021);
      this.radar_full_screen = this.mView.findViewById(2131363017);
      this.hide_btn = (ImageButton)this.mView.findViewById(2131362370);
      this.pull_btn = (ImageButton)this.mView.findViewById(2131362999);
      this.full_screen_btn = (ImageButton)this.mView.findViewById(2131362295);
      this.radar_camera_sw = (ImageButton)this.mView.findViewById(2131363010);
      this.hide_btn.setOnClickListener(this.mOnClickListener);
      this.pull_btn.setOnClickListener(this.mOnClickListener);
      this.full_screen_btn.setOnClickListener(this.mOnClickListener);
      this.radar_camera_sw.setOnClickListener(this.mOnClickListener);
      this.hide_btn.setFocusable(false);
      this.pull_btn.setFocusable(false);
      this.full_screen_btn.setFocusable(false);
      this.radar_camera_sw.setFocusable(false);
      this.front_radar_left_small_txt = (TextView)this.mView.findViewById(2131362280);
      this.front_radar_left_mid_small_txt = (TextView)this.mView.findViewById(2131362276);
      this.front_radar_right_mid_small_txt = (TextView)this.mView.findViewById(2131362284);
      this.front_radar_right_small_txt = (TextView)this.mView.findViewById(2131362288);
      this.back_radar_left_small_txt = (TextView)this.mView.findViewById(2131361950);
      this.back_radar_left_mid_small_txt = (TextView)this.mView.findViewById(2131361946);
      this.back_radar_right_mid_small_txt = (TextView)this.mView.findViewById(2131361954);
      this.back_radar_right_small_txt = (TextView)this.mView.findViewById(2131361958);
      this.left_radar_front_small_txt = (TextView)this.mView.findViewById(2131362735);
      this.left_radar_front_mid_small_txt = (TextView)this.mView.findViewById(2131362733);
      this.left_radar_rear_mid_small_txt = (TextView)this.mView.findViewById(2131362741);
      this.left_radar_rear_small_txt = (TextView)this.mView.findViewById(2131362743);
      this.right_radar_front_mid_small_txt = (TextView)this.mView.findViewById(2131363163);
      this.right_radar_front_small_txt = (TextView)this.mView.findViewById(2131363165);
      this.right_radar_rear_mid_small_txt = (TextView)this.mView.findViewById(2131363171);
      this.right_radar_rear_small_txt = (TextView)this.mView.findViewById(2131363173);
      this.f_l_txt = (TextView)this.mView.findViewById(2131362223);
      this.f_l_m_txt = (TextView)this.mView.findViewById(2131362222);
      this.f_r_m_txt = (TextView)this.mView.findViewById(2131362224);
      this.f_r_txt = (TextView)this.mView.findViewById(2131362225);
      this.r_l_txt = (TextView)this.mView.findViewById(2131363002);
      this.r_l_m_txt = (TextView)this.mView.findViewById(2131363001);
      this.r_r_m_txt = (TextView)this.mView.findViewById(2131363003);
      this.r_r_txt = (TextView)this.mView.findViewById(2131363004);
      this.b_l_txt = (TextView)this.mView.findViewById(2131361938);
      this.b_l_m_txt = (TextView)this.mView.findViewById(2131361937);
      this.b_r_m_txt = (TextView)this.mView.findViewById(2131361939);
      this.b_r_txt = (TextView)this.mView.findViewById(2131361940);
      this.t_l_m_txt = (TextView)this.mView.findViewById(2131363486);
      this.t_l_txt = (TextView)this.mView.findViewById(2131363487);
      this.t_r_m_txt = (TextView)this.mView.findViewById(2131363488);
      this.t_r_txt = (TextView)this.mView.findViewById(2131363489);
      this.radar_distan_rear = this.mView.findViewById(2131363013);
      this.radar_distan_front = this.mView.findViewById(2131363012);
      this.top_radar = this.mView.findViewById(2131363554);
      this.bottom_radar = this.mView.findViewById(2131361988);
      this.radar_distance_left_small = this.mView.findViewById(2131363014);
      this.radar_distance_right_small = this.mView.findViewById(2131363015);
      this.mSmallUnitTv = (TextView)this.mView.findViewById(2131363712);
      this.mOneDistanceTv = (TextView)this.mView.findViewById(2131363657);
      this.mFullUnitTv = (TextView)this.mView.findViewById(2131363616);
      this.mFullOneDistanceTv = (TextView)this.mView.findViewById(2131363658);
      this.mLeftTopTisTv = (TextView)this.mView.findViewById(2131363646);
      this.mWarningTisTv = (TextView)this.mView.findViewById(2131363593);
   }

   private boolean isDistanceVisible(int var1) {
      if (GeneralParkData.radar_distance_disable != null) {
         int[] var4 = GeneralParkData.radar_distance_disable;
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            if (var4[var2] == var1) {
               return true;
            }
         }
      }

      return false;
   }

   private void setRadarDistanceNum(Constant.RadarLocation var1, int var2) {
      switch (null.$SwitchMap$com$hzbhd$canbus$comm$Constant$RadarLocation[var1.ordinal()]) {
         case 1:
            this.setViewVisibleOrGone(this.front_radar_left_small_txt, this.f_l_txt, var2);
            break;
         case 2:
            this.setViewVisibleOrGone(this.front_radar_left_mid_small_txt, this.f_l_m_txt, var2);
            break;
         case 3:
            this.setViewVisibleOrGone(this.front_radar_right_mid_small_txt, this.f_r_m_txt, var2);
            break;
         case 4:
            this.setViewVisibleOrGone(this.front_radar_right_small_txt, this.f_r_txt, var2);
            break;
         case 5:
            this.setViewVisibleOrGone(this.back_radar_left_small_txt, this.r_l_txt, var2);
            break;
         case 6:
            this.setViewVisibleOrGone(this.back_radar_left_mid_small_txt, this.r_l_m_txt, var2);
            break;
         case 7:
            this.setViewVisibleOrGone(this.back_radar_right_mid_small_txt, this.r_r_m_txt, var2);
            break;
         case 8:
            this.setViewVisibleOrGone(this.back_radar_right_small_txt, this.r_r_txt, var2);
            break;
         case 9:
            this.setViewVisibleOrGone(this.left_radar_front_small_txt, this.b_l_txt, var2);
            break;
         case 10:
            this.setViewVisibleOrGone(this.left_radar_front_mid_small_txt, this.b_l_m_txt, var2);
            break;
         case 11:
            this.setViewVisibleOrGone(this.left_radar_rear_mid_small_txt, this.b_r_m_txt, var2);
            break;
         case 12:
            this.setViewVisibleOrGone(this.left_radar_rear_small_txt, this.b_r_txt, var2);
            break;
         case 13:
            this.setViewVisibleOrGone(this.right_radar_front_mid_small_txt, this.t_l_m_txt, var2);
            break;
         case 14:
            this.setViewVisibleOrGone(this.right_radar_front_small_txt, this.t_l_txt, var2);
            break;
         case 15:
            this.setViewVisibleOrGone(this.right_radar_rear_mid_small_txt, this.t_r_m_txt, var2);
            break;
         case 16:
            this.setViewVisibleOrGone(this.right_radar_rear_small_txt, this.t_r_txt, var2);
      }

   }

   private void setViewVisibleOrGone(TextView var1, TextView var2, int var3) {
      if (this.isDistanceVisible(var3)) {
         var1.setVisibility(4);
         var2.setVisibility(4);
      } else {
         var1.setVisibility(0);
         var2.setVisibility(0);
         var2.setText(String.valueOf(var3));
         var1.setText(String.valueOf(var3));
      }

   }

   public void hideOneRadarDistance() {
      TextView var1 = this.mOneDistanceTv;
      if (var1 != null) {
         var1.setText("");
      }

      var1 = this.mFullOneDistanceTv;
      if (var1 != null) {
         var1.setText("");
      }

   }

   public void hideRadarView() {
      LogUtil.showLog("RadarView", "hideRadarView");
      this.pull_btn.setVisibility(0);
      this.hide_btn.setVisibility(8);
      this.radar_small_screen.setVisibility(8);
      this.full_screen_btn.setVisibility(8);
      this.radar_full_screen.setVisibility(8);
      SharePreUtil.setBoolValue(this.mContext, "is_show_radar", false);
   }

   public void refreshDistance(HashMap var1) {
      if (var1 == null) {
         LogUtil.showLog("refreshDistance distMap==null");
      } else {
         this.showDistanceView();
         Iterator var4 = var1.keySet().iterator();

         while(var4.hasNext()) {
            Constant.RadarLocation var5 = (Constant.RadarLocation)var4.next();
            ((RadarData)this.mRadarDataArray.get(var5.ordinal())).setRadarImage(1);
         }

         Constant.RadarLocation[] var6 = Constant.RadarLocation.values();
         int var3 = var6.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            Constant.RadarLocation var7 = var6[var2];
            if (var1.get(var7) != null) {
               this.setRadarDistanceNum(var7, (Integer)var1.get(var7));
            }
         }

      }
   }

   public void refreshLocation(HashMap var1) {
      LogUtil.showLog("RadarView", "refreshLocation");
      if (var1 == null) {
         LogUtil.showLog("refreshDistance locationMap==null");
      } else {
         Constant.RadarLocation[] var5 = Constant.RadarLocation.values();
         int var3 = var5.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            Constant.RadarLocation var6 = var5[var2];
            if (var1.containsKey(var6)) {
               RadarData var4 = (RadarData)this.mRadarDataArray.get(var6.ordinal());
               if (var4.isDataChange(((int[])var1.get(var6))[0])) {
                  var4.setRadarImage();
               }
            }
         }

         this.hideDistanceView();
      }
   }

   public void refreshText() {
      TextView var1 = this.mLeftTopTisTv;
      if (var1 != null) {
         var1.setText(2131770015);
      }

      var1 = this.mWarningTisTv;
      if (var1 != null) {
         var1.setText(2131770018);
      }

   }

   public void setOneRadarDitance(String var1) {
      TextView var2 = this.mOneDistanceTv;
      if (var2 != null) {
         var2.setText(var1);
      }

      var2 = this.mFullOneDistanceTv;
      if (var2 != null) {
         var2.setText(var1);
      }

   }

   public void setSmallRadarViewWidth() {
      int var1 = (int)this.getResources().getDimension(2131178314);
      ViewGroup.LayoutParams var2 = this.radar_small_screen.getLayoutParams();
      var2.width = var1;
      this.radar_small_screen.setLayoutParams(var2);
   }

   public void showDistanceView() {
      this.radar_distan_rear.setVisibility(0);
      this.radar_distan_front.setVisibility(0);
      this.radar_distance_left_small.setVisibility(0);
      this.radar_distance_right_small.setVisibility(0);
      this.top_radar.setVisibility(0);
      this.bottom_radar.setVisibility(0);
      this.mSmallUnitTv.setVisibility(0);
      this.mFullUnitTv.setVisibility(0);
   }

   public void showRadarView() {
      LogUtil.showLog("RadarView", "showRadarView");
      this.pull_btn.setVisibility(8);
      this.radar_full_screen.setVisibility(8);
      this.hide_btn.setVisibility(0);
      this.radar_small_screen.setVisibility(0);
      this.full_screen_btn.setVisibility(0);
      SharePreUtil.setBoolValue(this.mContext, "is_show_radar", true);
   }

   private class RadarData {
      private Drawable[] fullImages;
      private ImageView ivFull;
      private ImageView ivSmall;
      private Drawable[] smallImages;
      final RadarView this$0;
      private int value;

      public RadarData(RadarView var1, String var2, String var3, ImageView var4, ImageView var5) {
         this.this$0 = var1;
         Drawable[] var8 = new Drawable[11];
         this.fullImages = var8;
         var8[0] = var1.mContext.getDrawable(2131100046);
         byte var7 = 1;
         int var6 = 1;

         while(true) {
            var8 = this.fullImages;
            if (var6 >= var8.length) {
               Drawable[] var9 = new Drawable[11];
               this.smallImages = var9;
               var9[0] = var1.mContext.getDrawable(2131100046);
               var6 = var7;

               while(true) {
                  var9 = this.smallImages;
                  if (var6 >= var9.length) {
                     this.ivFull = var4;
                     this.ivSmall = var5;
                     return;
                  }

                  var9[var6] = var1.mContext.getDrawable(CommUtil.getImgIdByResId(var1.mContext, var3 + var6));
                  ++var6;
               }
            }

            var8[var6] = var1.mContext.getDrawable(CommUtil.getImgIdByResId(var1.mContext, var2 + var6));
            ++var6;
         }
      }

      public boolean isDataChange(int var1) {
         if (this.value == var1) {
            return false;
         } else {
            this.value = var1;
            return true;
         }
      }

      public void setRadarImage() {
         this.setRadarImage(this.value);
      }

      public void setRadarImage(int var1) {
         this.ivFull.setImageDrawable(this.fullImages[var1]);
         this.ivSmall.setImageDrawable(this.smallImages[var1]);
      }
   }
}
