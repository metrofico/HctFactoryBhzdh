package com.hzbhd.canbus.car._25;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.CommUtil;
import java.util.Timer;
import java.util.TimerTask;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private static Timer mTimer;
   private static TimerTask mTimerTask;
   private Button mBtnCamera;
   private Context mContext;
   private View[] mCurrentPage;
   int mDifferent;
   private ImageButton mIbDown;
   boolean mIbDownStatus;
   private ImageButton mIbLeft;
   private ImageButton mIbLeftDown;
   boolean mIbLeftDownStatus;
   boolean mIbLeftStatus;
   private ImageButton mIbPa;
   private ImageButton mIbParaPark;
   private ImageButton mIbRight;
   private ImageButton mIbRightDown;
   boolean mIbRightDownStatus;
   boolean mIbRightStatus;
   private ImageButton mIbUp;
   private ImageButton mIbUpDown;
   boolean mIbUpStatus;
   private ImageButton mIbVertPark;
   private ImageView mIvCamera;
   int mIvCameraStatus;
   private View[] mPage1;
   private View[] mPage2;
   private View[] mPage3;
   private View[] mPage4;
   private View[] mPage5;
   private View[] mPage6;
   private View[][] mPageArrays;
   int mPageNumber;
   private LinearLayout mRlBottomBtn;
   private RelativeLayout mRlBtnBack;
   private RelativeLayout mRlBtnCancel;
   private RelativeLayout mRlBtnStart;
   private RelativeLayout mRlSixBtn;
   private LinearLayout mTvParkAssistIntroduce;
   private TextView mTvParkAssistTips;
   String mTvTips;

   public MyPanoramicView(Context var1, int var2) {
      super(var1);
      this.mContext = var1;
      this.mDifferent = var2;
      View var9 = LayoutInflater.from(var1).inflate(2131558795, this);
      this.mRlBottomBtn = (LinearLayout)var9.findViewById(2131363182);
      this.mIbPa = (ImageButton)var9.findViewById(2131362038);
      this.mRlBtnCancel = (RelativeLayout)var9.findViewById(2131363185);
      this.mRlBtnStart = (RelativeLayout)var9.findViewById(2131363186);
      this.mIbParaPark = (ImageButton)var9.findViewById(2131362040);
      this.mIbVertPark = (ImageButton)var9.findViewById(2131362063);
      this.mIbUpDown = (ImageButton)var9.findViewById(2131362062);
      this.mRlBtnBack = (RelativeLayout)var9.findViewById(2131363184);
      this.mIbPa.setOnClickListener(this);
      this.mRlBtnCancel.setOnClickListener(this);
      this.mRlBtnStart.setOnClickListener(this);
      this.mIbParaPark.setOnClickListener(this);
      this.mIbVertPark.setOnClickListener(this);
      this.mIbUpDown.setOnClickListener(this);
      this.mRlBtnBack.setOnClickListener(this);
      this.mIbLeftDown = (ImageButton)var9.findViewById(2131362403);
      this.mIbRightDown = (ImageButton)var9.findViewById(2131362421);
      this.mIbRight = (ImageButton)var9.findViewById(2131362420);
      this.mIbLeft = (ImageButton)var9.findViewById(2131362402);
      this.mIbDown = (ImageButton)var9.findViewById(2131362387);
      this.mIbUp = (ImageButton)var9.findViewById(2131362425);
      this.mIbLeftDown.setOnClickListener(this);
      this.mIbRightDown.setOnClickListener(this);
      this.mIbRight.setOnClickListener(this);
      this.mIbLeft.setOnClickListener(this);
      this.mIbDown.setOnClickListener(this);
      this.mIbUp.setOnClickListener(this);
      this.mRlSixBtn = (RelativeLayout)var9.findViewById(2131362708);
      this.mTvParkAssistTips = (TextView)var9.findViewById(2131363664);
      this.mTvParkAssistIntroduce = (LinearLayout)var9.findViewById(2131362791);
      ImageView var3 = (ImageView)var9.findViewById(2131362551);
      this.mIvCamera = var3;
      var3.setOnClickListener(this);
      View[] var4 = new View[]{this.mIbPa};
      this.mPage1 = var4;
      View[] var11 = new View[2];
      RelativeLayout var8 = this.mRlBtnCancel;
      var11[0] = var8;
      var11[1] = this.mIbVertPark;
      this.mPage2 = var11;
      View[] var5 = new View[4];
      var5[0] = var8;
      RelativeLayout var7 = this.mRlBtnStart;
      var5[1] = var7;
      var5[2] = this.mIbParaPark;
      var5[3] = this.mIbUpDown;
      this.mPage3 = var5;
      View[] var6 = new View[]{var8, var7, this.mRlBtnBack, this.mRlSixBtn};
      this.mPage4 = var6;
      View[] var12 = new View[]{var8};
      this.mPage5 = var12;
      View[] var13 = new View[]{this.mTvParkAssistIntroduce};
      this.mPage6 = var13;
      this.mPageArrays = new View[][]{var4, var11, var5, var6, var12, var13};
      Button var10 = (Button)var9.findViewById(2131362409);
      this.mBtnCamera = var10;
      var10.setOnClickListener(this);
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   private void setCameraBtnStatus(View var1, boolean var2) {
      float var3;
      if (var2) {
         var3 = 1.0F;
      } else {
         var3 = 0.5F;
      }

      var1.setAlpha(var3);
      var1.setEnabled(var2);
   }

   private void setCurrentPage(int var1) {
      LinearLayout var4 = this.mRlBottomBtn;
      int var2;
      if (var1 == 0) {
         var2 = 4;
      } else {
         var2 = 0;
      }

      var4.setVisibility(var2);

      View[] var7;
      label46: {
         Exception var10000;
         label50: {
            boolean var10001;
            int var3;
            try {
               var7 = this.mCurrentPage;
               var3 = var7.length;
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break label50;
            }

            var2 = 0;

            while(true) {
               if (var2 >= var3) {
                  break label46;
               }

               try {
                  var7[var2].setVisibility(4);
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }

               ++var2;
            }
         }

         Exception var8 = var10000;
         var8.printStackTrace();
      }

      if (var1 != 0) {
         var7 = this.mPageArrays[var1 - 1];
         this.mCurrentPage = var7;
         var2 = var7.length;

         for(var1 = 0; var1 < var2; ++var1) {
            var7[var1].setVisibility(0);
         }

      }
   }

   private void setParkAssistTips(String var1) {
      TextView var3 = this.mTvParkAssistTips;
      byte var2;
      if (CommUtil.getStrByResId(this.mContext, "null_value").equals(var1)) {
         var2 = 4;
      } else {
         var2 = 0;
      }

      var3.setVisibility(var2);
      this.mTvParkAssistTips.setText(var1);
   }

   private void startTimer(TimerTask var1, long var2) {
      if (var1 != null) {
         if (mTimer == null) {
            mTimer = new Timer();
         }

         mTimerTask = var1;
         mTimer.schedule(var1, var2);
      }
   }

   private void startTimer(TimerTask var1, long var2, int var4) {
      if (var1 != null) {
         if (mTimer == null) {
            mTimer = new Timer();
         }

         mTimerTask = var1;
         mTimer.schedule(var1, var2, (long)var4);
      }
   }

   private void stopTimer() {
      TimerTask var1 = mTimerTask;
      if (var1 != null) {
         var1.cancel();
         mTimerTask = null;
      }

      Timer var2 = mTimer;
      if (var2 != null) {
         var2.cancel();
         mTimer = null;
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362038:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 65, 1});
            break;
         case 2131362040:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 67, 1});
            break;
         case 2131362062:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 71, 1});
            break;
         case 2131362063:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 66, 1});
            break;
         case 2131362387:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 73, 1});
            break;
         case 2131362402:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 74, 1});
            break;
         case 2131362403:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 79, 1});
            break;
         case 2131362409:
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
            break;
         case 2131362420:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 75, 1});
            break;
         case 2131362421:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 78, 1});
            break;
         case 2131362425:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 72, 1});
            break;
         case 2131363184:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 70, 1});
            break;
         case 2131363185:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 68, 1});
            break;
         case 2131363186:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 69, 1});
      }

   }

   void updatePanoramicView() {
      this.setCameraBtnStatus(this.mIbLeftDown, this.mIbLeftDownStatus);
      this.setCameraBtnStatus(this.mIbRightDown, this.mIbRightDownStatus);
      this.setCameraBtnStatus(this.mIbRight, this.mIbRightStatus);
      this.setCameraBtnStatus(this.mIbLeft, this.mIbLeftStatus);
      this.setCameraBtnStatus(this.mIbDown, this.mIbDownStatus);
      this.setCameraBtnStatus(this.mIbUp, this.mIbUpStatus);
      int var1 = this.mIvCameraStatus;
      if (var1 == 128) {
         this.mIvCamera.setImageDrawable(this.mContext.getDrawable(2131234437));
      } else if (var1 == 0) {
         this.mIvCamera.setImageDrawable(this.mContext.getDrawable(2131234436));
      }

      this.mIvCamera.setVisibility(0);
      this.setParkAssistTips(this.mTvTips);
      this.setCurrentPage(this.mPageNumber);
   }
}
