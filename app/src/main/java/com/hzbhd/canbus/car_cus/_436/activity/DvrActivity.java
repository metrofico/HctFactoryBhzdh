package com.hzbhd.canbus.car_cus._436.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrStateView;
import java.util.List;

public class DvrActivity extends AppCompatActivity implements View.OnClickListener {
   private int Modular_TAG = 0;
   ScrollView control_view_scr;
   DvrFileView dvrFileView;
   DvrPlayView dvrPlayView;
   DvrSettingView dvrSettingView;
   DvrStateView dvrStateView;
   LinearLayout emergency_backup_lin;
   TextView file1_txt;
   TextView file2_txt;
   TextView file3_txt;
   TextView file4_txt;
   TextView file5_txt;
   TextView file6_txt;
   FrameLayout frmContent;
   LinearLayout modular1_lin;
   LinearLayout modular2_lin;
   LinearLayout modular3_lin;
   LinearLayout modular4_lin;
   Button play1;
   Button play2;
   Button play3;
   Button play4;
   Button play5;
   Button play6;
   ImageView show_hide_img;
   private Thread threadTimer;
   View viewMode1;
   View viewMode2;
   View viewMode3;
   View viewMode4;

   private void initData() {
      this.dvrPlayView.refreshUi();
      this.dvrSettingView.refreshUi();
      this.dvrStateView.refreshUi();
      this.dvrFileView.refreshUi();
   }

   private void initView() {
      DvrSender.send(new byte[]{35, 0});
      DvrSender.send(new byte[]{35, 8});
      this.frmContent = (FrameLayout)this.findViewById(2131362206);
      LayoutInflater var1 = LayoutInflater.from(this);
      this.viewMode1 = var1.inflate(2131558524, (ViewGroup)null);
      this.viewMode2 = var1.inflate(2131558525, (ViewGroup)null);
      this.viewMode3 = var1.inflate(2131558526, (ViewGroup)null);
      this.viewMode4 = var1.inflate(2131558527, (ViewGroup)null);
      this.modular1_lin = (LinearLayout)this.findViewById(2131362868);
      this.modular2_lin = (LinearLayout)this.findViewById(2131362869);
      this.modular3_lin = (LinearLayout)this.findViewById(2131362870);
      this.modular4_lin = (LinearLayout)this.findViewById(2131362871);
      this.emergency_backup_lin = (LinearLayout)this.findViewById(2131362208);
      this.modular1_lin.setOnClickListener(this);
      this.modular2_lin.setOnClickListener(this);
      this.modular3_lin.setOnClickListener(this);
      this.modular4_lin.setOnClickListener(this);
      this.emergency_backup_lin.setOnClickListener(this);
      this.dvrPlayView = (DvrPlayView)this.viewMode1.findViewById(2131362986);
      this.dvrSettingView = (DvrSettingView)this.viewMode2.findViewById(2131363322);
      this.dvrStateView = (DvrStateView)this.viewMode3.findViewById(2131363426);
      this.dvrFileView = (DvrFileView)this.viewMode4.findViewById(2131362244);
      ImageView var2 = (ImageView)this.viewMode1.findViewById(2131363332);
      this.show_hide_img = var2;
      var2.setOnClickListener(this);
      this.control_view_scr = (ScrollView)this.findViewById(2131362159);
      this.file1_txt = (TextView)this.viewMode4.findViewById(2131362230);
      this.file2_txt = (TextView)this.viewMode4.findViewById(2131362231);
      this.file3_txt = (TextView)this.viewMode4.findViewById(2131362232);
      this.file4_txt = (TextView)this.viewMode4.findViewById(2131362233);
      this.file5_txt = (TextView)this.viewMode4.findViewById(2131362234);
      this.file6_txt = (TextView)this.viewMode4.findViewById(2131362235);
      this.play1 = (Button)this.viewMode4.findViewById(2131362978);
      this.play2 = (Button)this.viewMode4.findViewById(2131362979);
      this.play3 = (Button)this.viewMode4.findViewById(2131362980);
      this.play4 = (Button)this.viewMode4.findViewById(2131362981);
      this.play5 = (Button)this.viewMode4.findViewById(2131362982);
      this.play6 = (Button)this.viewMode4.findViewById(2131362983);
      this.file1_txt.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.file2_txt.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.file3_txt.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.file4_txt.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.file5_txt.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.file6_txt.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.play1.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.play2.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.play3.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.play4.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.play5.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
      this.play6.setOnClickListener(new DvrActivity$$ExternalSyntheticLambda0(this));
   }

   private void playVideo(List var1) {
      if (var1.size() > 25) {
         DvrSender.send(new byte[]{114, -1, (byte)(Integer)var1.get(7), (byte)(Integer)var1.get(8), (byte)(Integer)var1.get(9), (byte)(Integer)var1.get(10), (byte)(Integer)var1.get(11), (byte)(Integer)var1.get(12), (byte)(Integer)var1.get(13), (byte)(Integer)var1.get(14), (byte)(Integer)var1.get(15), (byte)(Integer)var1.get(16), (byte)(Integer)var1.get(17), (byte)(Integer)var1.get(18), (byte)(Integer)var1.get(19), (byte)(Integer)var1.get(20), (byte)(Integer)var1.get(21), (byte)(Integer)var1.get(22), (byte)(Integer)var1.get(23), (byte)(Integer)var1.get(24), (byte)(Integer)var1.get(25)});
      }

   }

   private void registerDataListener() {
      NotifyBuilding.getInstance().register(this.dvrPlayView);
      NotifyBuilding.getInstance().register(this.dvrSettingView);
      NotifyBuilding.getInstance().register(this.dvrStateView);
      NotifyBuilding.getInstance().register(this.dvrFileView);
   }

   private void showOrHideControlView() {
      if (this.control_view_scr.getVisibility() == 0) {
         this.control_view_scr.setVisibility(8);
         this.show_hide_img.setBackgroundResource(2131231197);
      } else {
         this.control_view_scr.setVisibility(0);
         this.show_hide_img.setBackgroundResource(2131231196);
      }

   }

   private void startRequestTime() {
      if (this.threadTimer == null) {
         Thread var1 = new Thread(new Runnable(this) {
            final DvrActivity this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               for(int var1 = 0; var1 < 60000; ++var1) {
                  try {
                     Thread.sleep(1000L);
                     DvrSender.send(new byte[]{35, 0});
                     DvrSender.send(new byte[]{35, 8});
                  } catch (InterruptedException var3) {
                     var3.printStackTrace();
                     this.this$0.threadTimer = null;
                     break;
                  }
               }

            }
         });
         this.threadTimer = var1;
         var1.start();
      }

   }

   private void stopRequestTime() {
      if (this.threadTimer == null) {
         Log.d("soface", "线程对象NULL");
      } else {
         if (!Thread.currentThread().isInterrupted()) {
            Log.d("soface", "线程执行终止");
            this.threadTimer.interrupt();
         }

      }
   }

   private void unRegisterDataListener() {
      NotifyBuilding.getInstance().unRegister(this.dvrPlayView);
      NotifyBuilding.getInstance().unRegister(this.dvrSettingView);
      NotifyBuilding.getInstance().unRegister(this.dvrStateView);
      NotifyBuilding.getInstance().unRegister(this.dvrFileView);
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362208) {
         if (var2 != 2131363332) {
            switch (var2) {
               case 2131362230:
                  this.file1_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                  this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.playVideo(GeneralDvrFile.getInstance().item1);
                  break;
               case 2131362231:
                  this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file2_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                  this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.playVideo(GeneralDvrFile.getInstance().item2);
                  break;
               case 2131362232:
                  this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file3_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                  this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.playVideo(GeneralDvrFile.getInstance().item3);
                  break;
               case 2131362233:
                  this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file4_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                  this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.playVideo(GeneralDvrFile.getInstance().item4);
                  break;
               case 2131362234:
                  this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file5_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                  this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.playVideo(GeneralDvrFile.getInstance().item5);
                  break;
               case 2131362235:
                  this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                  this.file6_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                  this.playVideo(GeneralDvrFile.getInstance().item6);
                  break;
               default:
                  switch (var2) {
                     case 2131362868:
                        this.selectModular1();
                        break;
                     case 2131362869:
                        this.selectModular2();
                        break;
                     case 2131362870:
                        this.selectModular3();
                        break;
                     case 2131362871:
                        this.selectModular4();
                        break;
                     default:
                        switch (var2) {
                           case 2131362978:
                              this.selectModular1();
                              this.playVideo(GeneralDvrFile.getInstance().item1);
                              break;
                           case 2131362979:
                              this.selectModular1();
                              this.playVideo(GeneralDvrFile.getInstance().item2);
                              break;
                           case 2131362980:
                              this.selectModular1();
                              this.playVideo(GeneralDvrFile.getInstance().item3);
                              break;
                           case 2131362981:
                              this.selectModular1();
                              this.playVideo(GeneralDvrFile.getInstance().item4);
                              break;
                           case 2131362982:
                              this.selectModular1();
                              this.playVideo(GeneralDvrFile.getInstance().item5);
                              break;
                           case 2131362983:
                              this.selectModular1();
                              this.playVideo(GeneralDvrFile.getInstance().item6);
                        }
                  }
            }
         } else {
            this.showOrHideControlView();
         }
      } else {
         DvrSender.send(new byte[]{64, 33});
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558694);
      this.initView();
      this.startRequestTime();
   }

   protected void onDestroy() {
      super.onDestroy();
      this.unRegisterDataListener();
      this.dvrPlayView.stopVideoView();
      this.stopRequestTime();
   }

   protected void onPause() {
      super.onPause();
      this.dvrPlayView.stopVideoView();
   }

   protected void onRestart() {
      super.onRestart();
      this.dvrPlayView.stopVideoView();
      this.selectModular1();
   }

   protected void onResume() {
      super.onResume();
      this.selectModular1();
      this.initData();
      this.registerDataListener();
   }

   public void selectModular1() {
      DvrSender.send(new byte[]{35, 0});
      DvrSender.send(new byte[]{35, 8});
      if (this.viewMode1 != null && this.frmContent != null && this.Modular_TAG != 1) {
         this.Modular_TAG = 1;
         this.dvrPlayView.startVideoView();
         this.frmContent.removeAllViews();
         this.frmContent.addView(this.viewMode1);
         this.modular1_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
         this.modular2_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular3_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular4_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
      }

   }

   public void selectModular2() {
      DvrSender.send(new byte[]{35, 0});
      DvrSender.send(new byte[]{35, 8});
      if (this.viewMode2 != null && this.frmContent != null && this.Modular_TAG != 2) {
         this.Modular_TAG = 2;
         this.dvrPlayView.stopVideoView();
         this.frmContent.removeAllViews();
         this.frmContent.addView(this.viewMode2);
         this.modular1_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular2_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
         this.modular3_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular4_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
      }

   }

   public void selectModular3() {
      DvrSender.send(new byte[]{89, 0, 0, 0, 0});
      DvrSender.send(new byte[]{90, 0, 0, 0, 0});
      DvrSender.send(new byte[]{35, 0});
      DvrSender.send(new byte[]{35, 8});
      if (this.viewMode3 != null && this.frmContent != null && this.Modular_TAG != 3) {
         this.Modular_TAG = 3;
         this.dvrPlayView.stopVideoView();
         this.frmContent.removeAllViews();
         this.frmContent.addView(this.viewMode3);
         this.modular1_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular2_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular3_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
         this.modular4_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
      }

   }

   public void selectModular4() {
      DvrSender.send(new byte[]{35, 0});
      DvrSender.send(new byte[]{35, 8});
      if (GeneralDvrPlayPage.pageState != 2 && GeneralDvrPlayPage.pageState != 3) {
         DvrSender.send(new byte[]{64, 35});
      }

      DvrSender.send(new byte[]{64, 37});
      if (this.viewMode4 != null && this.frmContent != null && this.Modular_TAG != 4) {
         this.Modular_TAG = 4;
         this.dvrPlayView.stopVideoView();
         this.frmContent.removeAllViews();
         this.frmContent.addView(this.viewMode4);
         this.modular1_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular2_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular3_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
         this.modular4_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
      }

   }
}
