package com.hct.factory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.microntek.CarManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemProperties;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.hct.RkSystemProp;
import com.hct.factory.ui.AppBookFragment;
import com.hct.factory.ui.CanBusFragment;
import com.hct.factory.ui.CarLogoFragment;
import com.hct.factory.ui.KeyStudyFragment;
import com.hct.factory.ui.OtherFragment;
import com.hct.factory.ui.RadioAreaFragment;
import com.hct.factory.ui.ScreenFragment;
import com.hct.factory.ui.VoiceFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Factory_Settings extends FragmentActivity implements FactoryFun {
   public static final String TYPE = "type";
   public static final String VALUE = "value";
   private String hctcommon = "";
   private String hctcommon2 = "";
   private boolean isMcuG48Ver = false;
   private CarManager mCarManager = null;
   private int[] mChannelText = new int[]{2131296260, 2131296355, 2131296442, 2131296491, 2131296300, 2131296387, 2131296433};
   private int[] mChannelTextAsuka = new int[]{2131296491, 2131296300, 2131296433};
   private int[] mChannelTextKgl = new int[]{2131296260, 2131296442, 2131296491, 2131296300, 2131296387, 2131296433};
   private int[] mChannelTextMcuG = new int[]{2131296260, 2131296355, 2131296442, 2131296491, 2131296300, 2131296387, 2131296474, 2131296433};
   private int[] mChannelTextPsd = new int[]{2131296260, 2131296355, 2131296491, 2131296433};
   private Hct_Config mConfig = null;
   private int mDev = 0;
   private List mDevices;
   private Hct_Config.ST_FACTORY_CONFIG mFactory = null;
   private Hct_Config.ST_FACTORY_EXT_CONFIG mFactoryExt = null;
   private FragmentManager mFragmentManager = null;
   private HorizontalScrollView mHorizontalScrollView;
   private int mPanelCtl = 0;
   private RadioGroup mRadioGroup = null;
   private boolean mSaveconfig = false;
   private LinearLayout mSettings;
   private Toast mToast = null;
   private MyViewPager mViewPager;

   private void ExitDialog() {
      (new AlertDialog.Builder(this)).setIcon(17301659).setTitle(this.getString(2131296371)).setView((View)null).setPositiveButton(17039370, new DialogInterface.OnClickListener(this) {
         final Factory_Settings this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.findViewById(2131099827).setVisibility(0);
            (new Thread(new Runnable(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void run() {
                  try {
                     Thread.sleep(10000L);
                  } catch (InterruptedException var2) {
                     var2.printStackTrace();
                  }

                  CmdUtils.execCmd("sync");
                  if ("BOX".equals(RkSystemProp.get("ro.product.project", ""))) {
                     CmdUtils.execCmd("reboot");
                  } else {
                     this.this$1.this$0.sendBroadcast(new Intent("com.microntek.hctreboot"));
                     this.this$1.this$0.finish();
                  }

               }
            })).start();
         }
      }).setNegativeButton(17039360, new DialogInterface.OnClickListener(this) {
         final Factory_Settings this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.finish();
         }
      }).show();
   }

   private void SaveData() {
      String var3 = this.mConfig.mFactory_config.ToString();
      String var4 = this.mFactory.ToString();
      String var2 = this.mConfig.mFactory_ext_config.ToString();
      String var1 = this.mFactoryExt.ToString();
      if (!var4.equals(var3) || !var1.equals(var2)) {
         this.mConfig.mFactory_config = this.mFactory.Toclone();
         this.mConfig.SaveFactory_config();
         this.mConfig.mFactory_ext_config = this.mFactoryExt.Toclone();
         this.mConfig.SaveFactory_ext_config();
         this.saveapp();
         this.mSaveconfig = true;
      }

   }

   private void ShowMsg(String var1) {
      if (this.mToast == null) {
         this.mToast = Toast.makeText(this, var1, 0);
         this.mToast.setGravity(17, 0, 0);
      }

      this.mToast.setText(var1);
      this.mToast.show();
   }

   private void exportConfig() {
      View var5 = LayoutInflater.from(this).inflate(2131230727, (ViewGroup)null);
      EditText var3 = (EditText)var5.findViewById(2131099756);
      Spinner var4 = (Spinner)var5.findViewById(2131099746);
      this.refreshVolume();
      int var2 = this.mDevices.size();
      String[] var6 = new String[var2];
      String[] var7 = new String[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var6[var1] = ((Device)this.mDevices.get(var1)).mTitle;
      }

      ArrayAdapter var8 = new ArrayAdapter(this, 17367048, var6);
      var8.setDropDownViewResource(17367049);
      var4.setAdapter(var8);
      this.mDev = 0;
      var4.setSelection(this.mDev);
      var4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final Factory_Settings this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mDev = var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var3.setText("dmcu.cfg");
      (new AlertDialog.Builder(this)).setIcon(17301659).setTitle(this.getString(2131296373)).setView(var5).setPositiveButton(17039370, new DialogInterface.OnClickListener(this, var3) {
         final Factory_Settings this$0;
         final EditText val$editcontent;

         {
            this.this$0 = var1;
            this.val$editcontent = var2;
         }

         public void onClick(DialogInterface var1, int var2) {
            String var4 = this.val$editcontent.getEditableText().toString();
            Hct_Config var5 = this.this$0.mConfig;
            StringBuilder var3 = new StringBuilder();
            var3.append(((Device)this.this$0.mDevices.get(this.this$0.mDev)).mPath);
            var3.append("/");
            var3.append(var4);
            Factory_Settings var6;
            if (var5.Export_config(var3.toString())) {
               var6 = this.this$0;
               var6.ShowMsg(var6.getString(2131296473));
            } else {
               var6 = this.this$0;
               var6.ShowMsg(var6.getString(2131296472));
            }

         }
      }).setNegativeButton(17039360, (DialogInterface.OnClickListener)null).show();
   }

   private String getCustomer() {
      return RkSystemProp.get("ro.product.customer.sub", "HCT");
   }

   private boolean getShowCarLogo() {
      return RkSystemProp.get("ro.product.showcarlogo", "true").equals("true");
   }

   private void initPager() {
      ArrayList var1 = new ArrayList();
      if (!this.isASUKALowPWDMode()) {
         var1.add(new AppBookFragment());
      }

      if (!this.isKGLCustomerSub() && !this.isASUKALowPWDMode() && this.getShowCarLogo()) {
         var1.add(new CarLogoFragment());
      }

      if (!this.isASUKALowPWDMode() && !this.getCustomer().equals("PSD")) {
         var1.add(new RadioAreaFragment());
      }

      var1.add(new VoiceFragment());
      if (!this.getCustomer().equals("PSD")) {
         var1.add(new CanBusFragment());
      }

      if (!this.isASUKALowPWDMode() && !this.getCustomer().equals("PSD")) {
         var1.add(new KeyStudyFragment());
      }

      if (this.isScreenMenu() && !this.getCustomer().equals("PSD")) {
         var1.add(new ScreenFragment());
      }

      var1.add(new OtherFragment());
      this.mViewPager.setAdapter(new MyAdapter(this, this.mFragmentManager, var1));
   }

   private void initView() {
      this.mFragmentManager = super.getSupportFragmentManager();
      this.mViewPager = (MyViewPager)this.findViewById(2131099816);
      this.mViewPager.setOnPageChangeListener(new ViewPagerListener(this));
      this.mSettings = (LinearLayout)this.findViewById(2131099853);
      this.mRadioGroup = (RadioGroup)super.findViewById(2131099839);
      this.mHorizontalScrollView = (HorizontalScrollView)super.findViewById(2131099778);
      this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) {
         final Factory_Settings this$0;

         {
            this.this$0 = var1;
         }

         public void onCheckedChanged(RadioGroup var1, int var2) {
            this.this$0.mViewPager.setCurrentItem(var2);
         }
      });
      DisplayMetrics var3 = new DisplayMetrics();
      super.getWindowManager().getDefaultDisplay().getMetrics(var3);
      if (this.isKGLCustomerSub() || !this.getShowCarLogo()) {
         this.mChannelText = this.mChannelTextKgl;
      }

      if (this.isASUKALowPWDMode()) {
         this.mChannelText = this.mChannelTextAsuka;
      }

      if (this.isScreenMenu()) {
         this.mChannelText = this.mChannelTextMcuG;
      }

      if (this.getCustomer().equals("PSD")) {
         this.mChannelText = this.mChannelTextPsd;
      }

      int var2 = var3.widthPixels / this.mChannelText.length;

      for(int var1 = 0; var1 < this.mChannelText.length; ++var1) {
         RadioButton var4 = new RadioButton(this);
         var4.setBackgroundResource(2131034242);
         var4.setButtonDrawable((Drawable)null);
         var4.setPadding(0, 15, 0, 15);
         var4.setId(var1);
         var4.setGravity(17);
         var4.setText(this.getString(this.mChannelText[var1]));
         var4.setTextSize(this.getResources().getDimension(2130968609));
         var4.setMaxLines(1);
         this.mRadioGroup.addView(var4, var2, -2);
      }

      this.mRadioGroup.check(0);
   }

   private boolean isASUKALowPWDMode() {
      boolean var2 = "BOX".equals(SystemProperties.get("ro.product.project"));
      boolean var1 = false;
      if (var2) {
         return false;
      } else {
         if (System.getInt(this.getContentResolver(), "asuka_pwd_mode", 0) == 1) {
            var1 = true;
         }

         return var1;
      }
   }

   private boolean isKGLCustomerSub() {
      boolean var1;
      if (!RkSystemProp.get("ro.product.customer.sub", "HCT").equals("KGLMMR") && !RkSystemProp.get("ro.product.customer.sub", "HCT").equals("KGL1") && !RkSystemProp.get("ro.product.customer.sub", "HCT").equals("CHS7")) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private void reboot() {
      if (this.mSaveconfig) {
         this.ExitDialog();
      } else {
         this.finish();
      }

   }

   private void refreshVolume() {
      StorageManager var1 = (StorageManager)this.getSystemService(StorageManager.class);
      this.mDevices = new ArrayList();
      Device var2 = new Device(this, Environment.getExternalStorageDirectory().getPath().toString(), "FLASH", "flash", (String)null);
      this.mDevices.add(var2);
      Iterator var5 = var1.getVolumes().iterator();

      while(var5.hasNext()) {
         VolumeInfo var3 = (VolumeInfo)var5.next();
         if (var3.getType() == 0 && var3.isMountedReadable()) {
            DiskInfo var4 = var3.getDisk();
            Device var6;
            String var7;
            if (var4.isSd()) {
               var7 = var1.getBestVolumeDescription(var3);
               var6 = new Device(this, var3.getPath().getPath(), var7, "sd", var3.getId());
               this.mDevices.add(var6);
            } else if (var4.isUsb()) {
               var7 = var1.getBestVolumeDescription(var3);
               var6 = new Device(this, var3.getPath().getPath(), var7, "usb", var3.getId());
               this.mDevices.add(var6);
            }
         }
      }

   }

   private void saveapp() {
      Global.putInt(this.getContentResolver(), "install_non_market_apps", this.mFactory.mAppDisable);
      System.putInt(this.getContentResolver(), "hctapkupdata", 1);
      System.putInt(this.getContentResolver(), "canbus_updata", 1);
   }

   private void setRadioGroupTab(int var1) {
      RadioButton var4 = (RadioButton)this.mRadioGroup.getChildAt(var1);
      var4.setChecked(true);
      var1 = var4.getLeft();
      int var2 = var4.getMeasuredWidth();
      DisplayMetrics var5 = new DisplayMetrics();
      super.getWindowManager().getDefaultDisplay().getMetrics(var5);
      int var3 = var5.widthPixels;
      var2 /= 2;
      var3 /= 2;
      this.mHorizontalScrollView.smoothScrollTo(var2 + var1 - var3, 0);
   }

   public void SysetmClick(View var1) {
      switch (var1.getId()) {
         case 2131099864:
            this.reboot();
            break;
         case 2131099865:
            this.exportConfig();
            break;
         case 2131099866:
            this.SaveData();
      }

   }

   public Hct_Config.ST_FACTORY_CONFIG getFactoryConfig() {
      return this.mFactory;
   }

   public Hct_Config.ST_FACTORY_EXT_CONFIG getFactoryExtConfig() {
      return this.mFactoryExt;
   }

   public int getPanelCtl() {
      return this.mPanelCtl;
   }

   public String getParameters(String var1) {
      return this.mCarManager.getParameters(var1);
   }

   public boolean isMcuG48Ver() {
      return this.isMcuG48Ver;
   }

   public boolean isScreenMenu() {
      boolean var2 = "BOX".equals(SystemProperties.get("ro.product.project"));
      boolean var1 = false;
      if (var2) {
         return false;
      } else {
         if (this.isMcuG48Ver() || this.getPanelCtl() > 0) {
            var1 = true;
         }

         return var1;
      }
   }

   public void onConfigurationChanged(Configuration var1) {
      super.onConfigurationChanged(var1);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mCarManager = new CarManager();
      if (var1 != null) {
         this.hctcommon = var1.getString("hctcommon");
         this.hctcommon2 = var1.getString("hctcommon2");
      } else {
         Intent var3 = this.getIntent();
         if (var3.hasExtra("common")) {
            this.hctcommon = var3.getStringExtra("common");
         }

         if (var3.hasExtra("common2")) {
            this.hctcommon2 = var3.getStringExtra("common2");
         }
      }

      this.setParameters("rpt_key_mode=normal");
      if (this.hctcommon.equals("hct")) {
         this.setContentView(2131230731);
         this.mConfig = new Hct_Config(this);
         this.mFactory = this.mConfig.mFactory_config.Toclone();
         this.mFactoryExt = this.mConfig.mFactory_ext_config.Toclone();
         this.isMcuG48Ver = "1".equals(this.getParameters("sta_mcu_type="));

         try {
            this.mPanelCtl = Integer.parseInt(this.getParameters("sta_panel_ctl="));
            if (!this.isMcuG48Ver) {
               this.mPanelCtl &= -13;
            }
         } catch (Exception var2) {
            this.mPanelCtl = 0;
         }

         this.initView();
         this.initPager();
         if (this.hctcommon2.equals("hctlogo")) {
            this.mRadioGroup.setVisibility(8);
            this.mViewPager.setNoScroll(true);
            this.mViewPager.setCurrentItem(1);
         }
      } else {
         this.finish();
      }

      if ("2".equals(SystemProperties.get("hct.appwm.ploy", "0"))) {
         this.getWindow().clearFlags(1024);
      }

   }

   protected void onDestroy() {
      System.putInt(this.getContentResolver(), "asuka_pwd_mode", 0);
      super.onDestroy();
   }

   protected void onPause() {
      System.putInt(this.getContentResolver(), "asuka_pwd_mode", 0);
      super.onPause();
   }

   public void onRestoreInstanceState(Bundle var1) {
      super.onRestoreInstanceState(var1);
      this.hctcommon = var1.getString("hctcommon");
      this.hctcommon2 = var1.getString("hctcommon2");
   }

   public void onSaveInstanceState(Bundle var1) {
      var1.putString("hctcommon", this.hctcommon);
      var1.putString("hctcommon2", this.hctcommon2);
      super.onSaveInstanceState(var1);
   }

   public void setParameters(String var1) {
      this.mCarManager.setParameters(var1);
   }

   public class Device {
      public String mKey;
      public String mPath;
      public String mTitle;
      public String mType;
      final Factory_Settings this$0;

      public Device(Factory_Settings var1, String var2, String var3, String var4, String var5) {
         this.this$0 = var1;
         this.mPath = var2;
         this.mTitle = var3;
         this.mType = var4;
         this.mKey = var5;
      }
   }

   private class MyAdapter extends FragmentPagerAdapter {
      private List fragmentList;
      final Factory_Settings this$0;

      public MyAdapter(Factory_Settings var1, FragmentManager var2, List var3) {
         super(var2);
         this.this$0 = var1;
         this.fragmentList = var3;
      }

      public int getCount() {
         return this.fragmentList.size();
      }

      public Fragment getItem(int var1) {
         List var2 = this.fragmentList;
         return (Fragment)var2.get(var1 % var2.size());
      }

      public int getItemPosition(Object var1) {
         return -2;
      }
   }

   private class ViewPagerListener implements ViewPager.OnPageChangeListener {
      final Factory_Settings this$0;

      private ViewPagerListener(Factory_Settings var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      ViewPagerListener(Factory_Settings var1, Object var2) {
         this(var1);
      }

      public void onPageScrollStateChanged(int var1) {
      }

      public void onPageScrolled(int var1, float var2, int var3) {
      }

      public void onPageSelected(int var1) {
         this.this$0.setRadioGroupTab(var1);
      }
   }
}
