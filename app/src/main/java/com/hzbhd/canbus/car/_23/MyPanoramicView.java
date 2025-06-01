package com.hzbhd.canbus.car._23;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private Button mBtnCamera;
   boolean mBtnStartStatus;
   private Context mContext;
   private View[] mCurrentPage;
   private ImageButton mIbDown;
   boolean mIbDownStatus;
   private ImageButton mIbExit;
   private ImageButton mIbLeft;
   private ImageButton mIbLeftDown;
   boolean mIbLeftDownStatus;
   boolean mIbLeftStatus;
   private ImageButton mIbPa;
   boolean mIbPaStatus;
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
   private SparseIntArray mTipsArray;
   private LinearLayout mTvParkAssistIntroduce;
   private TextView mTvParkAssistTips;
   int mTvTipsStatus;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      View var8 = LayoutInflater.from(var1).inflate(2131558797, this);
      this.mRlBottomBtn = (LinearLayout)var8.findViewById(2131363182);
      this.mIbPa = (ImageButton)var8.findViewById(2131362038);
      this.mRlBtnCancel = (RelativeLayout)var8.findViewById(2131363185);
      this.mRlBtnStart = (RelativeLayout)var8.findViewById(2131363186);
      this.mIbParaPark = (ImageButton)var8.findViewById(2131362040);
      this.mIbVertPark = (ImageButton)var8.findViewById(2131362063);
      this.mIbUpDown = (ImageButton)var8.findViewById(2131362062);
      this.mRlBtnBack = (RelativeLayout)var8.findViewById(2131363184);
      this.mIbPa.setOnClickListener(this);
      this.mRlBtnCancel.setOnClickListener(this);
      this.mRlBtnStart.setOnClickListener(this);
      this.mIbParaPark.setOnClickListener(this);
      this.mIbVertPark.setOnClickListener(this);
      this.mIbUpDown.setOnClickListener(this);
      this.mRlBtnBack.setOnClickListener(this);
      this.mIbLeftDown = (ImageButton)var8.findViewById(2131362403);
      this.mIbRightDown = (ImageButton)var8.findViewById(2131362421);
      this.mIbRight = (ImageButton)var8.findViewById(2131362420);
      this.mIbLeft = (ImageButton)var8.findViewById(2131362402);
      this.mIbDown = (ImageButton)var8.findViewById(2131362387);
      this.mIbUp = (ImageButton)var8.findViewById(2131362425);
      this.mIbLeftDown.setOnClickListener(this);
      this.mIbRightDown.setOnClickListener(this);
      this.mIbRight.setOnClickListener(this);
      this.mIbLeft.setOnClickListener(this);
      this.mIbDown.setOnClickListener(this);
      this.mIbUp.setOnClickListener(this);
      this.mRlSixBtn = (RelativeLayout)var8.findViewById(2131362708);
      this.mTvParkAssistTips = (TextView)var8.findViewById(2131363664);
      this.mTvParkAssistIntroduce = (LinearLayout)var8.findViewById(2131362791);
      ImageView var2 = (ImageView)var8.findViewById(2131362551);
      this.mIvCamera = var2;
      var2.setOnClickListener(this);
      View[] var4 = new View[]{this.mIbPa};
      this.mPage1 = var4;
      View[] var10 = new View[4];
      RelativeLayout var6 = this.mRlBtnCancel;
      var10[0] = var6;
      RelativeLayout var7 = this.mRlBtnStart;
      var10[1] = var7;
      var10[2] = this.mIbParaPark;
      var10[3] = this.mIbUpDown;
      this.mPage2 = var10;
      View[] var5 = new View[]{var6, this.mIbVertPark};
      this.mPage3 = var5;
      View[] var3 = new View[]{var6, var7, this.mRlBtnBack, this.mRlSixBtn};
      this.mPage4 = var3;
      View[] var13 = new View[]{var6};
      this.mPage5 = var13;
      View[] var12 = new View[]{this.mTvParkAssistIntroduce};
      this.mPage6 = var12;
      this.mPageArrays = new View[][]{var4, var10, var5, var3, var13, var12};
      ImageButton var11 = (ImageButton)var8.findViewById(2131362410);
      this.mIbExit = var11;
      var11.setOnClickListener(this);
      Button var9 = (Button)var8.findViewById(2131362409);
      this.mBtnCamera = var9;
      var9.setOnClickListener(this);
      this.initHashMap();
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   private void initHashMap() {
      SparseIntArray var1 = new SparseIntArray(38);
      this.mTipsArray = var1;
      var1.put(1, 2131769356);
      this.mTipsArray.append(2, 2131769372);
      this.mTipsArray.append(3, 2131769371);
      this.mTipsArray.append(4, 2131769370);
      this.mTipsArray.append(5, 2131769377);
      this.mTipsArray.append(6, 2131769349);
      this.mTipsArray.append(7, 2131769353);
      this.mTipsArray.append(8, 2131769351);
      this.mTipsArray.append(9, 2131769354);
      this.mTipsArray.append(10, 2131769352);
      this.mTipsArray.append(11, 2131769367);
      this.mTipsArray.append(12, 2131769365);
      this.mTipsArray.append(13, 2131769346);
      this.mTipsArray.append(14, 2131769378);
      this.mTipsArray.append(15, 2131769348);
      this.mTipsArray.append(17, 2131769375);
      this.mTipsArray.append(18, 2131769368);
      this.mTipsArray.append(19, 2131769373);
      this.mTipsArray.append(20, 2131769363);
      this.mTipsArray.append(21, 2131769376);
      this.mTipsArray.append(22, 2131769350);
      this.mTipsArray.append(23, 2131769384);
      this.mTipsArray.append(24, 2131769360);
      this.mTipsArray.append(25, 2131769381);
      this.mTipsArray.append(27, 2131769347);
      this.mTipsArray.append(28, 2131769380);
      this.mTipsArray.append(29, 2131769383);
      this.mTipsArray.append(30, 2131769355);
      this.mTipsArray.append(32, 2131769362);
      this.mTipsArray.append(33, 2131769364);
      this.mTipsArray.append(34, 2131769358);
      this.mTipsArray.append(35, 2131769374);
      this.mTipsArray.append(36, 2131769369);
      this.mTipsArray.append(38, 2131769357);
      this.mTipsArray.append(39, 2131769359);
      this.mTipsArray.append(40, 2131769379);
      this.mTipsArray.append(43, 2131769366);
      this.mTipsArray.append(46, 2131769361);
      this.mTipsArray.append(48, 2131769382);
   }

   private void sendCameraButtonCommand(int var1) {
      (new Thread(this, var1) {
         final MyPanoramicView this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -3, (byte)this.val$cmd, 1});

            try {
               sleep(50L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -3, (byte)this.val$cmd, 0});
         }
      }).start();
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

   private void setCurrentPage(int param1) {
      // $FF: Couldn't be decompiled
   }

   private void setParkAssistTips(Context var1, int var2) {
      if (var2 == 0) {
         this.mTvParkAssistTips.setVisibility(4);
      } else {
         this.mTvParkAssistTips.setVisibility(0);
         this.mTvParkAssistTips.setText(var1.getString(this.mTipsArray.get(var2, 2131769421)));
      }
   }

   void initPanoramicView(int var1) {
      Log.i("ljq", "initPanoramicView: different: " + var1);
      if (var1 != 13 && var1 != 16) {
         this.mBtnCamera.setVisibility(0);
         this.mIbExit.setVisibility(8);
      } else {
         this.mBtnCamera.setVisibility(8);
         this.mIbExit.setVisibility(0);
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362038:
            this.sendCameraButtonCommand(17);
            break;
         case 2131362040:
            this.sendCameraButtonCommand(19);
            break;
         case 2131362062:
            this.sendCameraButtonCommand(23);
            break;
         case 2131362063:
            this.sendCameraButtonCommand(18);
            break;
         case 2131362387:
            this.sendCameraButtonCommand(25);
            break;
         case 2131362402:
            this.sendCameraButtonCommand(26);
            break;
         case 2131362403:
            this.sendCameraButtonCommand(29);
            break;
         case 2131362409:
            CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
            break;
         case 2131362410:
            CanbusMsgSender.sendMsg(new byte[]{22, -3, 2, 0});
            break;
         case 2131362420:
            this.sendCameraButtonCommand(27);
            break;
         case 2131362421:
            this.sendCameraButtonCommand(28);
            break;
         case 2131362425:
            this.sendCameraButtonCommand(24);
            break;
         case 2131363184:
            this.sendCameraButtonCommand(22);
            break;
         case 2131363185:
            this.sendCameraButtonCommand(21);
            break;
         case 2131363186:
            this.sendCameraButtonCommand(20);
      }

   }

   void updatePanoramicView(Context var1) {
      this.setCameraBtnStatus(this.mIbPa, this.mIbPaStatus);
      this.setCameraBtnStatus(this.mRlBtnStart, this.mBtnStartStatus);
      this.setCameraBtnStatus(this.mIbLeftDown, this.mIbLeftDownStatus);
      this.setCameraBtnStatus(this.mIbRightDown, this.mIbRightDownStatus);
      this.setCameraBtnStatus(this.mIbRight, this.mIbRightStatus);
      this.setCameraBtnStatus(this.mIbLeft, this.mIbLeftStatus);
      this.setCameraBtnStatus(this.mIbDown, this.mIbDownStatus);
      this.setCameraBtnStatus(this.mIbUp, this.mIbUpStatus);
      int var2 = this.mIvCameraStatus;
      if (var2 == 1) {
         this.mIvCamera.setImageDrawable(this.mContext.getDrawable(2131234437));
      } else if (var2 == 2) {
         this.mIvCamera.setImageDrawable(this.mContext.getDrawable(2131234436));
      }

      this.mIvCamera.setVisibility(0);
      this.setParkAssistTips(var1, this.mTvTipsStatus);
      this.setCurrentPage(this.mPageNumber);
   }
}
