package com.hzbhd.canbus.car_cus._283;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingActivity;
import com.hzbhd.canbus.car_cus._283.bean.MainChooseBean;
import com.hzbhd.canbus.car_cus._283.bean.MainMediaBean;
import com.hzbhd.canbus.car_cus._283.view.DataView;
import com.hzbhd.canbus.car_cus._283.view.DrivingPatternView;
import com.hzbhd.canbus.car_cus._283.view.HybridDataView1;
import com.hzbhd.canbus.car_cus._283.view.HybridDataView2;
import com.hzbhd.canbus.car_cus._283.view.HybridModeView;
import com.hzbhd.canbus.car_cus._283.view.TmpsView;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.keydispatcher.constants.KeyDispatcherConstant;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbstractBaseActivity implements View.OnClickListener {
   public static final String BUNDLE_DEZHONG_WHAT = "bundle_dezhong_what";
   public static final int BUNDLE_DEZHONG_WHAT_DrivingPattern = 4;
   public static final int BUNDLE_DEZHONG_WHAT_HYBRID_DATA_1 = 7;
   public static final int BUNDLE_DEZHONG_WHAT_HYBRID_DATA_2 = 8;
   public static final int BUNDLE_DEZHONG_WHAT_HYBRID_MODE = 9;
   public static final int BUNDLE_DEZHONG_WHAT_Launch = 1;
   public static final int BUNDLE_DEZHONG_WHAT_LongTime = 2;
   public static final int BUNDLE_DEZHONG_WHAT_Refuel = 3;
   public static final int BUNDLE_DEZHONG_WHAT_TMPS = 5;
   public static final int BUNDLE_DEZHONG_WHAT_TMPS_Data = 6;
   public static final int BUNDLE_DEZHONG_WHAT_personal = 10;
   private DataView dataView_Launch;
   private DataView dataView_LongTime;
   private DataView dataView_Refuel;
   private DrivingPatternView drivingPatternView;
   private HybridDataView1 hybridDataView1;
   private HybridDataView2 hybridDataView2;
   private HybridModeView hybridModeView;
   private int[] imageMedia = new int[]{2131233303, 2131233304, 2131233306, 2131233305};
   private ImageView leftImage;
   private LinearLayout linearMainChoose;
   private LinearLayout linearMainMedia;
   private LinearLayout linearMainSetting;
   private List listChoose = new ArrayList();
   private List listMedia = new ArrayList();
   private List listViews = new ArrayList();
   private int mWhat;
   private ImageView rightImage;
   private int showView = 0;
   private int[] titleMedia = new int[]{2131760674, 2131760675, 2131760676, 2131760677};
   private TextView titleText;
   private int[] titles = new int[]{2131760774, 2131760766, 2131760767, 2131760764, 2131760765};
   private int[] titlesHybrid = new int[]{2131760768, 2131760769, 2131760770, 2131760765};
   private TmpsView tmpsView;

   private void initView() {
      this.leftImage = (ImageView)this.findViewById(2131362721);
      this.rightImage = (ImageView)this.findViewById(2131363149);
      this.titleText = (TextView)this.findViewById(2131363548);
      this.dataView_Launch = (DataView)this.findViewById(2131363740);
      this.dataView_Refuel = (DataView)this.findViewById(2131363741);
      this.dataView_LongTime = (DataView)this.findViewById(2131363742);
      this.drivingPatternView = (DrivingPatternView)this.findViewById(2131363743);
      this.hybridDataView1 = (HybridDataView1)this.findViewById(2131362376);
      this.hybridDataView2 = (HybridDataView2)this.findViewById(2131362377);
      this.hybridModeView = (HybridModeView)this.findViewById(2131362378);
      this.tmpsView = (TmpsView)this.findViewById(2131363749);
      this.linearMainChoose = (LinearLayout)this.findViewById(2131362762);
      this.linearMainMedia = (LinearLayout)this.findViewById(2131362763);
      this.linearMainSetting = (LinearLayout)this.findViewById(2131362764);
      this.leftImage.setOnClickListener(this);
      this.rightImage.setOnClickListener(this);
      this.linearMainChoose.setOnClickListener(this);
      this.linearMainMedia.setOnClickListener(this);
      this.linearMainSetting.setOnClickListener(this);
      if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
         this.titles = this.titlesHybrid;
         this.listViews.add(this.hybridDataView1);
      } else {
         this.listViews.add(this.dataView_Launch);
      }

      if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
         this.listViews.add(this.hybridDataView2);
         this.listViews.add(this.hybridModeView);
      } else {
         this.listViews.add(this.dataView_Refuel);
         this.listViews.add(this.dataView_LongTime);
         this.listViews.add(this.drivingPatternView);
      }

      this.listViews.add(this.tmpsView);
      this.listChoose.add(new MainChooseBean(this.getString(this.titles[0])));
      if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
         this.listChoose.add(new MainChooseBean(this.getString(this.titles[1])));
         this.listChoose.add(new MainChooseBean(this.getString(this.titles[2])));
      } else {
         this.listChoose.add(new MainChooseBean(this.getString(this.titles[3])));
         this.listChoose.add(new MainChooseBean(this.getString(this.titles[4])));
      }

      this.listMedia.add(new MainMediaBean(this.imageMedia[0], this.getString(this.titleMedia[0]), HzbhdComponentName.MediaMusic));
      this.listMedia.add(new MainMediaBean(this.imageMedia[1], this.getString(this.titleMedia[1]), HzbhdComponentName.MediaMusic));
      this.listMedia.add(new MainMediaBean(this.imageMedia[2], this.getString(this.titleMedia[2]), HzbhdComponentName.MediaMusic));
      this.listMedia.add(new MainMediaBean(this.imageMedia[3], this.getString(this.titleMedia[3]), HzbhdComponentName.MediaMusic));
   }

   private void intentStartView(Intent var1) {
      if (!TextUtils.isEmpty(var1.getStringExtra("type")) && var1.getStringExtra("type").equals("4")) {
         this.showView = 3;
         this.showThisView();
      }

   }

   // $FF: synthetic method
   static void lambda$onClick$1(int var0, ComponentName var1) {
      if (var0 == 0) {
         SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.BTAUDIO));
      } else if (var0 == 1) {
         SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.MUSIC));
      } else if (var0 == 2) {
         SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.VIDEO));
      } else if (var0 == 3) {
         SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.FM));
      }

   }

   private void showThisView() {
      int var1 = this.showView;
      if (var1 >= 0) {
         int[] var2 = this.titles;
         if (var1 < var2.length) {
            this.titleText.setText(var2[var1]);
         }
      }

      for(var1 = 0; var1 < this.listViews.size(); ++var1) {
         if (this.showView == var1) {
            ((View)this.listViews.get(var1)).setVisibility(0);
         } else {
            ((View)this.listViews.get(var1)).setVisibility(8);
         }
      }

   }

   public boolean dispatchTouchEvent(MotionEvent var1) {
      return super.dispatchTouchEvent(var1);
   }

   // $FF: synthetic method
   void lambda$onClick$0$com_hzbhd_canbus_car_cus__283_MainActivity(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
                  this.showView = 2;
               } else {
                  this.showView = 4;
               }

               this.showThisView();
            }
         } else {
            if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
               this.showView = 1;
            } else {
               this.showView = 3;
            }

            this.showThisView();
         }
      } else {
         this.showView = 0;
         this.showThisView();
      }

   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362721) {
         if (var2 != 2131363149) {
            switch (var2) {
               case 2131362762:
                  DialogUtils.mainChoose(this, this.linearMainChoose, this.listChoose, new MainActivity$$ExternalSyntheticLambda0(this));
                  break;
               case 2131362763:
                  DialogUtils.mainMedia(this, this.linearMainMedia, this.listMedia, new MainActivity$$ExternalSyntheticLambda1());
                  break;
               case 2131362764:
                  this.startActivity(new Intent(this, MainSettingActivity.class));
            }
         } else {
            var2 = this.showView;
            if (var2 < this.titles.length - 1) {
               this.showView = var2 + 1;
            } else {
               this.showView = 0;
            }

            this.showThisView();
         }
      } else {
         var2 = this.showView;
         if (var2 > 0) {
            this.showView = var2 - 1;
         } else {
            this.showView = this.titles.length - 1;
         }

         this.showThisView();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558430);
      this.initView();
      this.showView = 0;
      this.showThisView();
      this.intentStartView(this.getIntent());
      MessageSender.getMsg(134);
      MessageSender.getMsg(70);
   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      this.intentStartView(var1);
   }

   protected void onResume() {
      super.onResume();
      this.dataView_Launch.refreshUi(1);
      this.dataView_LongTime.refreshUi(2);
      this.dataView_Refuel.refreshUi(3);
      this.drivingPatternView.refreshUi();
      this.tmpsView.refreshUi();
      this.tmpsView.refreshUiData();
      this.hybridDataView1.refreshUi();
      this.hybridDataView2.refreshUi();
      this.hybridModeView.refreshUi();
   }

   public void refreshUi(Bundle var1) {
      LogUtil.showLog("283 refreshUi");
      if (var1 != null) {
         int var2 = var1.getInt("bundle_dezhong_what");
         this.mWhat = var2;
         switch (var2) {
            case 1:
               this.dataView_Launch.refreshUi(1);
               break;
            case 2:
               this.dataView_LongTime.refreshUi(2);
               break;
            case 3:
               this.dataView_Refuel.refreshUi(3);
               break;
            case 4:
               this.drivingPatternView.refreshUi();
               break;
            case 5:
               this.tmpsView.refreshUi();
               break;
            case 6:
               this.tmpsView.refreshUiData();
               break;
            case 7:
               this.hybridDataView1.refreshUi();
               break;
            case 8:
               this.hybridDataView2.refreshUi();
               break;
            case 9:
               this.hybridModeView.refreshUi();
         }

      }
   }
}
