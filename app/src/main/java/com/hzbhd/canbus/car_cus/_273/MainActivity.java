package com.hzbhd.canbus.car_cus._273;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._273.adapter.MainLeftLvAdapter;
import com.hzbhd.canbus.car_cus._273.entity.LeftItemBean;
import com.hzbhd.canbus.car_cus._273.entity.OnOffUpdateEntity;
import com.hzbhd.canbus.car_cus._273.view.AirView;
import com.hzbhd.canbus.car_cus._273.view.CarOnOffView;
import com.hzbhd.canbus.car_cus._273.view.MonitorView;
import com.hzbhd.canbus.car_cus._273.view.OtherFunctionView;
import com.hzbhd.canbus.car_cus._273.view.ReverseMonitorView;
import com.hzbhd.canbus.car_cus._273.view.SpecialEqView;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AbstractBaseActivity implements MainLeftLvAdapter.LeftItemClickInterface {
   public static final String BUNDLE_CHENGWEI_WHAT = "bundle_chengwei_what";
   public static final int MAIN_WHAT = 1001;
   public static final String SAVE_FIRST = "_273_first_open";
   public static final String TAG = "MainActivity";
   public static final int VIEW_AC_WHAT = 1003;
   public static final int VIEW_BACK_WHAT = 1006;
   public static final int VIEW_CENTER_DOOR_WHAT = 1005;
   public static final int VIEW_ON_OFF_WHAT = 1002;
   public static final int VIEW_SPECIAL_WHAT = 1004;
   private View bgView;
   private boolean isFrontCameara = true;
   private AirView mAirView;
   private CarOnOffView mCarOnOffView;
   private Handler mHandler = new Handler(this) {
      final MainActivity this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         int var2 = var1.what;
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  this.this$0.showViewByIndex(0);
               }
            } else {
               this.this$0.mMainLeftLvAdapter.notifyDataSetChanged();
            }
         } else {
            CharSequence var3 = DateFormat.format("yyyy-MM-dd hh:mm", System.currentTimeMillis());
            this.this$0.mTimeTv.setText(var3);
         }

      }
   };
   private TextView mInCarTemTv;
   private List mLeftList;
   private RecyclerView mLeftRecyclerView;
   private MainLeftLvAdapter mMainLeftLvAdapter;
   private MonitorView mMonitorView;
   private OtherFunctionView mOtherFunctionView;
   private TextView mOutCarTemTv;
   private ReverseMonitorView mReverseMonitorView;
   private SpecialEqView mSpecialEqView;
   private SurfaceView mSurefaceView;
   private TextView mTimeTv;
   private List mViewList;
   private int mWhat;

   private void findViews() {
      this.mLeftRecyclerView = (RecyclerView)this.findViewById(2131363219);
      this.mCarOnOffView = (CarOnOffView)this.findViewById(2131363734);
      this.mAirView = (AirView)this.findViewById(2131363732);
      this.mMonitorView = (MonitorView)this.findViewById(2131363745);
      this.mSpecialEqView = (SpecialEqView)this.findViewById(2131363748);
      this.mReverseMonitorView = (ReverseMonitorView)this.findViewById(2131363747);
      this.mOtherFunctionView = (OtherFunctionView)this.findViewById(2131363746);
      this.mInCarTemTv = (TextView)this.findViewById(2131363630);
      this.mTimeTv = (TextView)this.findViewById(2131363611);
      this.mOutCarTemTv = (TextView)this.findViewById(2131363662);
      this.bgView = this.findViewById(2131361978);
      this.mSurefaceView = new SurfaceView(this);
      this.mMonitorView.setManiActivity(this);
   }

   private int[] getStrArray2IntArray(String[] var1) {
      int[] var3 = new int[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3[var2] = Integer.parseInt(var1[var2]);
      }

      return var3;
   }

   private void initRefreshAirView(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 2);
      boolean var4 = false;
      boolean var3;
      if (var2 == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_sb = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[6], 6, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_dh = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 4, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_sj = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 6, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_ck = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_a = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[6], 2, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_b = var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 2) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralCwData.air_hfw = var3;
      var3 = var4;
      if (DataHandleUtils.getIntFromByteWithBit(var1[7], 2, 2) == 1) {
         var3 = true;
      }

      GeneralCwData.air_hfn = var3;
      Bundle var5 = new Bundle();
      var5.putInt("bundle_chengwei_what", 1003);
      this.refreshUi(var5);
   }

   private void initRefreshCarOnOffView(int[] var1) {
      ArrayList var6 = new ArrayList();

      for(int var2 = 0; var2 < 4; ++var2) {
         boolean var5 = true;
         boolean var4 = true;
         int var3;
         if (var2 == 3) {
            var3 = var6.size();
            if (DataHandleUtils.getIntFromByteWithBit(var1[var2], 0, 2) != 1) {
               var4 = false;
            }

            var6.add(new OnOffUpdateEntity(var3, var4));
            var6.add(new OnOffUpdateEntity(var6.size(), false));
         } else {
            var3 = var6.size();
            if (DataHandleUtils.getIntFromByteWithBit(var1[var2], 0, 2) == 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6.add(new OnOffUpdateEntity(var3, var4));
            var3 = var6.size();
            if (DataHandleUtils.getIntFromByteWithBit(var1[var2], 2, 2) == 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6.add(new OnOffUpdateEntity(var3, var4));
            var3 = var6.size();
            if (DataHandleUtils.getIntFromByteWithBit(var1[var2], 4, 2) == 1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6.add(new OnOffUpdateEntity(var3, var4));
            var3 = var6.size();
            if (DataHandleUtils.getIntFromByteWithBit(var1[var2], 6, 2) == 1) {
               var4 = var5;
            } else {
               var4 = false;
            }

            var6.add(new OnOffUpdateEntity(var3, var4));
         }
      }

      GeneralCwData.mOnOffUpdateList = var6;
      Bundle var7 = new Bundle();
      var7.putInt("bundle_chengwei_what", 1002);
      this.refreshUi(var7);
   }

   private void initRefreshSpecialView(int[] var1) {
      ArrayList var3 = new ArrayList();
      var3.add(new OnOffUpdateEntity(0, false));
      boolean var2 = true;
      var3.add(new OnOffUpdateEntity(1, false));
      var3.add(new OnOffUpdateEntity(2, false));
      var3.add(new OnOffUpdateEntity(3, false));
      if (DataHandleUtils.getIntFromByteWithBit(var1[5], 6, 2) != 1) {
         var2 = false;
      }

      var3.add(new OnOffUpdateEntity(4, var2));
      var3.add(new OnOffUpdateEntity(5, false));
      var3.add(new OnOffUpdateEntity(6, false));
      var3.add(new OnOffUpdateEntity(7, false));
      var3.add(new OnOffUpdateEntity(8, false));
      var3.add(new OnOffUpdateEntity(9, false));
      GeneralCwData.mSpecialEqUpdateList = var3;
      Bundle var4 = new Bundle();
      var4.putInt("bundle_chengwei_what", 1004);
      this.refreshUi(var4);
   }

   private void initViews() {
      this.mLeftList = new ArrayList();
      this.mMainLeftLvAdapter = new MainLeftLvAdapter(this, this.mLeftList, this);
      LinearLayoutManager var1 = new LinearLayoutManager(this);
      this.mLeftRecyclerView.setLayoutManager(var1);
      this.mLeftRecyclerView.setAdapter(this.mMainLeftLvAdapter);
      (new Thread(this) {
         final MainActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.mLeftList.add(new LeftItemBean(2131768023, 2131232635, true));
            this.this$0.mLeftList.add(new LeftItemBean(2131768024, 2131232628, false));
            this.this$0.mLeftList.add(new LeftItemBean(2131768025, 2131232630, false));
            this.this$0.mLeftList.add(new LeftItemBean(2131768026, 2131232631, false));
            this.this$0.mLeftList.add(new LeftItemBean(2131768027, 2131232629, false));
            this.this$0.mLeftList.add(new LeftItemBean(2131768028, 2131232634, false));
            Message var1 = new Message();
            var1.what = 2;
            this.this$0.mHandler.sendMessage(var1);
         }
      }).start();
      (new Thread(this) {
         final MainActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.mViewList = new ArrayList();
            this.this$0.mViewList.add(this.this$0.mCarOnOffView);
            this.this$0.mViewList.add(this.this$0.mAirView);
            this.this$0.mViewList.add(this.this$0.mMonitorView);
            this.this$0.mViewList.add(this.this$0.mSpecialEqView);
            this.this$0.mViewList.add(this.this$0.mReverseMonitorView);
            this.this$0.mViewList.add(this.this$0.mOtherFunctionView);
            Message var1 = new Message();
            var1.what = 3;
            this.this$0.mHandler.sendMessage(var1);
         }
      }).start();
      (new Timer()).schedule(new TimerTask(this) {
         final MainActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            Message var1 = new Message();
            var1.what = 1;
            this.this$0.mHandler.sendMessage(var1);
         }
      }, 0L, 1000L);
   }

   private void showViewByIndex(int var1) {
      if (var1 <= this.mViewList.size() - 1) {
         if (var1 == 2) {
            if (this.mMonitorView.getVisibility() == 8) {
               this.bgView.setVisibility(0);
               this.bgView.postDelayed(new Runnable(this) {
                  final MainActivity this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void run() {
                     this.this$0.bgView.setVisibility(8);
                  }
               }, 500L);
               this.mReverseMonitorView.removeSurfaceView(this.mSurefaceView);
               this.mMonitorView.addSurfaceView(this.mSurefaceView);
               if (this.isFrontCameara) {
                  this.showFrontCamera();
               } else {
                  this.showCenterCamera();
               }
            }
         } else if (var1 == 4 && this.mReverseMonitorView.getVisibility() == 8) {
            this.bgView.setVisibility(0);
            this.bgView.postDelayed(new Runnable(this) {
               final MainActivity this$0;

               {
                  this.this$0 = var1;
               }

               public void run() {
                  this.this$0.bgView.setVisibility(8);
               }
            }, 500L);
            this.mMonitorView.removeSurfaceView(this.mSurefaceView);
            this.mReverseMonitorView.addSurfaceView(this.mSurefaceView);
            this.showBackCamera();
         }

         for(int var2 = 0; var2 < this.mViewList.size(); ++var2) {
            if (var2 == var1) {
               ((View)this.mViewList.get(var2)).setVisibility(0);
            } else {
               ((View)this.mViewList.get(var2)).setVisibility(8);
            }
         }

      }
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362641) {
         this.startActivity(new Intent("android.settings.SETTINGS"));
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558407);
      this.findViews();
      this.initViews();
      int[] var2;
      if (!SharePreUtil.getBoolValue(this, "_273_first_open", false)) {
         SharePreUtil.setBoolValue(this, "_273_first_open", true);
         var2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
         MessageSender.saveCommonSwitch(var2);
         MessageSender.setCommonSwitchs(var2);
      } else {
         var2 = this.getStrArray2IntArray(MessageSender.getCommonSwitch().split(","));
         MessageSender.setCommonSwitchs(var2);
         this.initRefreshCarOnOffView(var2);
         this.initRefreshAirView(var2);
         this.initRefreshSpecialView(var2);
      }

   }

   protected void onDestroy() {
      super.onDestroy();
      CarOnOffView var1 = this.mCarOnOffView;
      if (var1 != null) {
         var1.onDestroy();
      }

      AirView var2 = this.mAirView;
      if (var2 != null) {
         var2.onDestroy();
      }

      MonitorView var3 = this.mMonitorView;
      if (var3 != null) {
         var3.onDestroy();
      }

      OtherFunctionView var4 = this.mOtherFunctionView;
      if (var4 != null) {
         var4.onDestroy();
      }

      ReverseMonitorView var5 = this.mReverseMonitorView;
      if (var5 != null) {
         var5.onDestroy();
      }

      SpecialEqView var6 = this.mSpecialEqView;
      if (var6 != null) {
         var6.onDestroy();
      }

      MessageSender.saveCommonSwitch(MessageSender.getmCommonSwitchs());
   }

   public boolean onKeyUp(int var1, KeyEvent var2) {
      if (var1 == 4) {
         Intent var3 = new Intent("android.intent.action.MAIN");
         var3.setFlags(268435456);
         var3.addCategory("android.intent.category.HOME");
         this.startActivity(var3);
         return true;
      } else {
         return false;
      }
   }

   public void onLeftItemClick(int var1) {
      for(int var2 = 0; var2 < this.mLeftList.size(); ++var2) {
         LeftItemBean var4 = (LeftItemBean)this.mLeftList.get(var2);
         boolean var3;
         if (var2 == var1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var4.setSelected(var3);
      }

      this.mMainLeftLvAdapter.notifyDataSetChanged();
      this.showViewByIndex(var1);
   }

   protected void onResume() {
      super.onResume();
      FutureUtil.instance.reqMcuKey(new byte[]{-49, 1});
   }

   public void refreshUi(Bundle var1) {
      LogUtil.showLog("273 refreshUi");
      if (var1 != null) {
         if (GeneralCwData.in_car_temperature != 0) {
            this.mInCarTemTv.setText(this.getString(2131770269) + "：" + GeneralCwData.in_car_temperature + this.getString(2131770016));
         }

         if (GeneralCwData.out_car_temperature != 0) {
            this.mOutCarTemTv.setText(this.getString(2131770270) + "：" + GeneralCwData.out_car_temperature + this.getString(2131770016));
         }

         int var2 = var1.getInt("bundle_chengwei_what");
         this.mWhat = var2;
         switch (var2) {
            case 1002:
               this.mCarOnOffView.refreshUi(var1);
               break;
            case 1003:
               this.mAirView.refreshUi(var1);
               break;
            case 1004:
               this.mSpecialEqView.refreshUi(var1);
               break;
            case 1005:
               this.onLeftItemClick(2);
               break;
            case 1006:
               this.onLeftItemClick(4);
         }

      }
   }

   public void showBackCamera() {
      FutureUtil.instance.showBackCamera();
   }

   public void showCenterCamera() {
      FutureUtil.instance.showCenterCamera();
   }

   public void showFrontCamera() {
      FutureUtil.instance.showFrontCamera();
   }
}
