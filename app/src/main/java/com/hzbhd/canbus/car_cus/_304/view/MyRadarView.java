package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.HashMap;

public class MyRadarView extends RelativeLayout {
   private final String TAG = "_304_RadarView";
   private Button mBtnAll;
   private ImageButton mIbFront;
   private ImageButton mIbLeft;
   private ImageButton mIbRear;
   private ImageButton mIbRight;
   private ImageButton mIbVoice;
   private boolean mIsAllView = true;
   private ImageView mIvWareFrontLeft;
   private ImageView mIvWareFrontLeftMid;
   private ImageView mIvWareFrontRight;
   private ImageView mIvWareFrontRightMid;
   private ImageView mIvWareRearLeft;
   private ImageView mIvWareRearLeftMid;
   private ImageView mIvWareRearRight;
   private ImageView mIvWareRearRightMid;
   private SparseArray mRadarUpdateHelperArray;
   private View mView;

   public MyRadarView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mView = LayoutInflater.from(var1).inflate(2131558506, this);
      this.findViews();
   }

   private void findViews() {
      this.mIbFront = (ImageButton)this.mView.findViewById(2131362392);
      this.mIbLeft = (ImageButton)this.mView.findViewById(2131362402);
      this.mIbRight = (ImageButton)this.mView.findViewById(2131362420);
      this.mIbRear = (ImageButton)this.mView.findViewById(2131362414);
      this.mBtnAll = (Button)this.mView.findViewById(2131362005);
      this.mIbVoice = (ImageButton)this.mView.findViewById(2131362426);
      this.mIvWareFrontLeft = (ImageView)this.mView.findViewById(2131362662);
      this.mIvWareFrontLeftMid = (ImageView)this.mView.findViewById(2131362663);
      this.mIvWareFrontRightMid = (ImageView)this.mView.findViewById(2131362665);
      this.mIvWareFrontRight = (ImageView)this.mView.findViewById(2131362664);
      this.mIvWareRearLeft = (ImageView)this.mView.findViewById(2131362666);
      this.mIvWareRearLeftMid = (ImageView)this.mView.findViewById(2131362667);
      this.mIvWareRearRightMid = (ImageView)this.mView.findViewById(2131362669);
      this.mIvWareRearRight = (ImageView)this.mView.findViewById(2131362668);
   }

   public void initViews(OnPanoramicItemClickListener var1) {
      MyRadarView$$ExternalSyntheticLambda0 var3 = new MyRadarView$$ExternalSyntheticLambda0(this, var1);
      this.mIbFront.setOnClickListener(var3);
      this.mIbLeft.setOnClickListener(var3);
      this.mIbRight.setOnClickListener(var3);
      this.mIbRear.setOnClickListener(var3);
      this.mBtnAll.setOnClickListener(var3);
      this.mIbVoice.setOnClickListener(var3);
      SparseArray var4 = new SparseArray();
      this.mRadarUpdateHelperArray = var4;
      var4.put(Constant.RadarLocation.FRONT_LEFT.ordinal(), new RadarUpdateHelper(this, this.mIvWareFrontLeft));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.FRONT_MID_LEFT.ordinal(), new RadarUpdateHelper(this, this.mIvWareFrontLeftMid));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.FRONT_MID_RIGHT.ordinal(), new RadarUpdateHelper(this, this.mIvWareFrontRightMid));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.FRONT_RIGHT.ordinal(), new RadarUpdateHelper(this, this.mIvWareFrontRight));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_LEFT.ordinal(), new RadarUpdateHelper(this, this.mIvWareRearLeft));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_MID_LEFT.ordinal(), new RadarUpdateHelper(this, this.mIvWareRearLeftMid));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_MID_RIGHT.ordinal(), new RadarUpdateHelper(this, this.mIvWareRearRightMid));
      this.mRadarUpdateHelperArray.put(Constant.RadarLocation.REAR_RIGHT.ordinal(), new RadarUpdateHelper(this, this.mIvWareRearRight));
      int var2 = SelectCanTypeUtil.getCurrentCanDiffId();
      Log.i("_304_RadarView", "initViews: differentId <--> " + var2);
      if (var2 == 0) {
         this.mIbFront.setVisibility(4);
         this.mIbLeft.setVisibility(4);
         this.mIbRight.setVisibility(4);
         this.mIbRear.setVisibility(4);
         this.mBtnAll.setVisibility(4);
         this.mIbVoice.setVisibility(4);
         this.mIvWareFrontLeft.setVisibility(4);
         this.mIvWareFrontRight.setVisibility(4);
      }

   }

   // $FF: synthetic method
   void lambda$initViews$0$com_hzbhd_canbus_car_cus__304_view_MyRadarView(OnPanoramicItemClickListener var1, View var2) {
      if (var2.equals(this.mIbFront)) {
         var1.onClickItem(0);
      } else if (var2.equals(this.mIbRear)) {
         var1.onClickItem(1);
      } else if (var2.equals(this.mIbLeft)) {
         var1.onClickItem(2);
      } else if (var2.equals(this.mIbRight)) {
         var1.onClickItem(3);
      } else if (var2.equals(this.mBtnAll)) {
         Log.i("_304_RadarView", "initViews: mIsAllView <--> " + this.mIsAllView);
         if (this.mIsAllView) {
            var1.onClickItem(7);
            this.mBtnAll.setText(2131761926);
            this.mIsAllView = false;
         } else {
            var1.onClickItem(4);
            this.mBtnAll.setText(2131761925);
            this.mIsAllView = true;
         }
      } else if (var2.equals(this.mIbVoice)) {
         var1.onClickItem(5);
      }

   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mBtnAll.setText(2131761925);
      this.mIsAllView = true;
   }

   public void refreshRadar() {
      HashMap var5 = MyGeneralData.mRadarLocationMap;
      Constant.RadarLocation[] var6 = Constant.RadarLocation.values();
      int var2 = var6.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         Constant.RadarLocation var4 = var6[var1];
         if (var5.containsKey(var4)) {
            RadarUpdateHelper var3 = (RadarUpdateHelper)this.mRadarUpdateHelperArray.get(var4.ordinal());
            if (var3.isDataChange((Integer)var5.get(var4))) {
               var3.updateRadar();
            }
         }
      }

   }

   private class RadarUpdateHelper {
      private ImageView radar;
      final MyRadarView this$0;
      private int value;

      public RadarUpdateHelper(MyRadarView var1, ImageView var2) {
         this.this$0 = var1;
         this.radar = var2;
      }

      public boolean isDataChange(int var1) {
         if (this.value == var1) {
            return false;
         } else {
            this.value = var1;
            return true;
         }
      }

      public void updateRadar() {
         this.radar.setImageLevel(this.value);
      }
   }
}
