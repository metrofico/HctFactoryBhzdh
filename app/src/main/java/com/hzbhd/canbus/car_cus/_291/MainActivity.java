package com.hzbhd.canbus.car_cus._291;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.bean.MainChooseBean;
import com.hzbhd.canbus.car_cus._283.bean.MainMediaBean;
import com.hzbhd.canbus.car_cus._291.view.DataAlarmView;
import com.hzbhd.canbus.car_cus._291.view.DataView;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.keydispatcher.constants.KeyDispatcherConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AbstractBaseActivity implements View.OnClickListener {
   public static final String BUNDLE_DEZHONG_WHAT = "bundle_dezhong_what";
   public static final int BUNDLE_DEZHONG_WHAT_DataAlarmView = 2;
   public static final int BUNDLE_DEZHONG_WHAT_DataView = 1;
   private DataAlarmView dataAlarmView;
   private DataView dataView;
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
   private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
   private int[] titleMedia = new int[]{2131760674, 2131760675, 2131760676, 2131760677};
   private TextView titleText;
   private int[] titles = new int[]{2131761278, 2131761277};

   private void initView() {
      this.leftImage = (ImageView)this.findViewById(2131362721);
      this.rightImage = (ImageView)this.findViewById(2131363149);
      this.titleText = (TextView)this.findViewById(2131363548);
      this.dataAlarmView = (DataAlarmView)this.findViewById(2131363738);
      this.dataView = (DataView)this.findViewById(2131363739);
      this.linearMainChoose = (LinearLayout)this.findViewById(2131362762);
      this.linearMainMedia = (LinearLayout)this.findViewById(2131362763);
      this.linearMainSetting = (LinearLayout)this.findViewById(2131362764);
      this.leftImage.setOnClickListener(this);
      this.rightImage.setOnClickListener(this);
      this.linearMainChoose.setOnClickListener(this);
      this.linearMainMedia.setOnClickListener(this);
      this.linearMainSetting.setOnClickListener(this);
      this.listViews.add(this.dataView);
      this.threadExecutor.execute(new Runnable(this) {
         final MainActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listViews.add(this.this$0.dataAlarmView);
         }
      });
      this.threadExecutor.execute(new Runnable(this) {
         final MainActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            List var1 = this.this$0.listChoose;
            MainActivity var2 = this.this$0;
            var1.add(new MainChooseBean(var2.getString(var2.titles[0])));
            List var4 = this.this$0.listChoose;
            MainActivity var3 = this.this$0;
            var4.add(new MainChooseBean(var3.getString(var3.titles[1])));
         }
      });
      this.threadExecutor.execute(new Runnable(this) {
         final MainActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            List var3 = this.this$0.listMedia;
            int var1 = this.this$0.imageMedia[0];
            MainActivity var2 = this.this$0;
            var3.add(new MainMediaBean(var1, var2.getString(var2.titleMedia[0]), HzbhdComponentName.A2dpActivity));
            var3 = this.this$0.listMedia;
            var1 = this.this$0.imageMedia[1];
            var2 = this.this$0;
            var3.add(new MainMediaBean(var1, var2.getString(var2.titleMedia[1]), HzbhdComponentName.MediaMusic));
            List var4 = this.this$0.listMedia;
            var1 = this.this$0.imageMedia[2];
            MainActivity var5 = this.this$0;
            var4.add(new MainMediaBean(var1, var5.getString(var5.titleMedia[2]), HzbhdComponentName.MediaVideo));
            var4 = this.this$0.listMedia;
            var1 = this.this$0.imageMedia[3];
            var5 = this.this$0;
            var4.add(new MainMediaBean(var1, var5.getString(var5.titleMedia[3]), HzbhdComponentName.RadioActivity));
         }
      });
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
   void lambda$onClick$0$com_hzbhd_canbus_car_cus__291_MainActivity(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.showView = 2;
               this.showThisView();
            }
         } else {
            this.showView = 1;
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
            this.showView = 1;
         }

         this.showThisView();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558488);
      this.initView();
      this.showView = 0;
      this.showThisView();
   }

   public void refreshUi(Bundle var1) {
      LogUtil.showLog("291 refreshUi");
      if (var1 != null) {
         int var2 = var1.getInt("bundle_dezhong_what");
         this.mWhat = var2;
         if (var2 != 1) {
            if (var2 == 2) {
               this.dataAlarmView.refreshUi();
            }
         } else {
            this.dataView.refreshUi();
         }

      }
   }
}
