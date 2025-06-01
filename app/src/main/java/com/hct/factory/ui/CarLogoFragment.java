package com.hct.factory.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.hct.factory.ChooseLogoMenu;
import com.hct.factory.FactoryFun;
import com.hct.factory.FileNameComarator;
import com.hct.factory.Hct_Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class CarLogoFragment extends Fragment {
   private static final String[] logotype = new String[]{"Car ico", "Green_droid animation", "Dancing_droids animation"};
   private Spinner Logomodegroup;
   private boolean isCHS6 = SystemProperties.get("ro.product.customer.sub", "HCT").equals("CHS6");
   private boolean logoDisable = true;
   private TextView logoType_id;
   private Activity mActivity;
   private final String mBook_logo = "/cache/logo/customer.png";
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private LinearLayout mCusLogo;
   private boolean mDefaultLogoFile = false;
   private FactoryFun mFactoryFun = null;
   private ArrayList mLogoBitmaps;
   private Button mLogoButton;
   private ArrayList mLogoFileNames;
   private Gallery mLogoGallery;
   private Button mLogoGalleryButton;
   private ImageView mLogoImageView;
   private int mLogoSel;
   private CheckBox mLogocheckbox;
   private final String mSavepath = "/cache/logo/customer.png";
   private final String mSavepath1 = "/cache/logo";
   private int mlogoid;

   private void InitCarLogo() {
      if (this.Logomodegroup != null) {
         ArrayAdapter var1 = new ArrayAdapter(this.getActivity(), 17367048, logotype);
         var1.setDropDownViewResource(17367049);
         this.Logomodegroup.setAdapter(var1);
         this.Logomodegroup.setSelection(this.mConfig.mLogoType);
         this.Logomodegroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
            final CarLogoFragment this$0;

            {
               this.this$0 = var1;
            }

            public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
               this.this$0.mConfig.mLogoType = (byte)var3;
            }

            public void onNothingSelected(AdapterView var1) {
            }
         });
      }

      Gallery var3 = this.mLogoGallery;
      if (var3 != null) {
         var3.setAdapter(new LogoAdapter(this, this.getActivity()));
         this.mLogoGallery.setCallbackDuringFling(false);
         this.mLogoGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
            final CarLogoFragment this$0;

            {
               this.this$0 = var1;
            }

            public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
               if (!this.this$0.mLogocheckbox.isChecked()) {
                  this.this$0.selectLogo(var3);
                  this.this$0.mLogoSel = var3;
               }

            }

            public void onNothingSelected(AdapterView var1) {
            }
         });
         if (this.mlogoid == -1) {
            this.mLogoGallery.setVisibility(4);
            this.mLogocheckbox.setChecked(true);
            this.mLogoButton.setEnabled(true);
            this.mLogoGalleryButton.setEnabled(true);

            try {
               this.mLogoImageView.setImageBitmap(BitmapFactory.decodeFile("/cache/logo/customer.png"));
               this.setLogoName("customer");
            } catch (Exception var2) {
            }
         } else {
            if (this.logoDisable) {
               this.mLogoGallery.setVisibility(0);
            }

            this.mLogoGallery.setSelection(this.mlogoid);
            this.mLogocheckbox.setChecked(false);
            this.mLogoButton.setEnabled(false);
            this.mLogoGalleryButton.setEnabled(false);
         }

         this.mLogocheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
            final CarLogoFragment this$0;

            {
               this.this$0 = var1;
            }

            public void onCheckedChanged(CompoundButton var1, boolean var2) {
               if (var2) {
                  this.this$0.mLogoGallery.setVisibility(4);
                  this.this$0.mLogoButton.setEnabled(true);
                  this.this$0.mLogoGalleryButton.setEnabled(true);
                  this.this$0.mLogoImageView.setImageResource(2131034135);
               } else {
                  if (this.this$0.logoDisable) {
                     this.this$0.mLogoGallery.setVisibility(0);
                  }

                  this.this$0.mLogoButton.setEnabled(false);
                  this.this$0.mLogoGalleryButton.setEnabled(false);
                  this.this$0.mLogoGallery.setSelection(this.this$0.mLogoSel);
                  CarLogoFragment var3 = this.this$0;
                  var3.selectLogo(var3.mLogoSel);
               }

            }
         });
         this.mLogoButton.setOnClickListener(new View.OnClickListener(this) {
            final CarLogoFragment this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.StartLogoMenu();
            }
         });
         this.mLogoGalleryButton.setOnClickListener(new View.OnClickListener(this) {
            final CarLogoFragment this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.StartGalleryImg();
            }
         });
      }

   }

   private void StartGalleryImg() {
      Intent var1 = new Intent();
      var1.setType("image/*");
      var1.setAction("android.intent.action.GET_CONTENT");
      this.startActivityForResult(var1, 2);
   }

   private void StartLogoMenu() {
      Intent var1 = new Intent();
      var1.setClass(this.mActivity, ChooseLogoMenu.class);
      this.startActivityForResult(var1, 1);
   }

   private int findLogos() {
      this.mLogoBitmaps = new ArrayList();
      this.mLogoFileNames = new ArrayList();
      int var3 = -1;
      int var6 = 0;
      byte[] var12 = this.mConfig.mLogo;

      int var1;
      for(var1 = 0; var1 < this.mConfig.mLogo.length && var12[var1] != 0; ++var1) {
      }

      StringBuilder var11 = new StringBuilder();
      var11.append((new String(var12)).substring(0, var1));
      var11.append(".png");
      String var19 = var11.toString();
      File var13 = new File("/system/media/defaultlogo.png");
      if (var13.exists()) {
         this.mDefaultLogoFile = true;
      }

      int var8 = 0;
      File var16 = new File("/system/media/ext_logo");
      int var2 = var3;
      int var4;
      String var14;
      if (var16.exists()) {
         File[] var17 = var16.listFiles();
         var4 = var17.length;
         var1 = 0;

         while(true) {
            var2 = var3;
            var8 = var4;
            if (var1 >= var17.length) {
               break;
            }

            var2 = var3;
            if (var17[var1].isFile()) {
               var14 = var17[var1].getName();
               Bitmap var15 = this.getLocalBitmap(var17[var1]);
               var2 = var3;
               if (var15 != null) {
                  this.mLogoBitmaps.add(var15);
                  this.mLogoFileNames.add(var14);
                  var2 = var3;
                  if (var19.equalsIgnoreCase(var14)) {
                     var2 = var1;
                  }
               }
            }

            ++var1;
            var3 = var2;
         }
      }

      int var5 = 0;
      File[] var20 = (new File("/system/media/logo")).listFiles();
      Arrays.sort(var20, new FileNameComarator());
      var4 = 0;

      for(var1 = var6; var4 < var20.length; var5 = var6) {
         int var9 = var2;
         int var10 = var1;
         var6 = var5;
         if (var20[var4].isFile()) {
            var9 = var2;
            var10 = var1;
            var6 = var5;
            if (var20[var4].exists()) {
               label108: {
                  var14 = var20[var4].getName();
                  Bitmap var18;
                  if (var14.equals("Android.png") && this.mDefaultLogoFile) {
                     var18 = this.getLocalBitmap(var13);
                  } else {
                     var18 = this.getLocalBitmap(var20[var4]);
                  }

                  if (this.isCHS6) {
                     var3 = var5;
                     if (!var14.contains("chslogo")) {
                        var9 = var2;
                        var10 = var1;
                        var6 = var5;
                        break label108;
                     }
                  } else {
                     var3 = var4;
                  }

                  var9 = var2;
                  var10 = var1;
                  var6 = var3;
                  if (var18 != null) {
                     this.mLogoBitmaps.add(var18);
                     this.mLogoFileNames.add(var14);
                     int var7;
                     if (var19.equalsIgnoreCase(var14)) {
                        var5 = var3 + var8;
                        var7 = var1;
                     } else {
                        var5 = var2;
                        var7 = var1;
                        if (var14.equalsIgnoreCase("Android.png")) {
                           var5 = var2;
                           var7 = var1;
                           if (var2 == -1) {
                              var7 = var3;
                              var5 = var2;
                           }
                        }
                     }

                     var9 = var5;
                     var10 = var7;
                     var6 = var3;
                     if (this.isCHS6) {
                        var6 = var3 + 1;
                        var10 = var7;
                        var9 = var5;
                     }
                  }
               }
            }
         }

         ++var4;
         var2 = var9;
         var1 = var10;
      }

      var3 = var19.lastIndexOf(".");
      if (var3 > 0) {
         this.setLogoName(var19.substring(0, var3));
      }

      if (var19.equalsIgnoreCase("customer.png")) {
         if (!(new File("/cache/logo/customer.png")).exists()) {
            this.setLogoName("Android");
            var3 = var1;
         } else {
            var3 = -1;
         }
      } else {
         var3 = var2;
         if (var2 == -1) {
            var3 = var1;
         }
      }

      return var3;
   }

   private Bitmap getLocalBitmap(File var1) {
      Object var3 = null;
      Bitmap var2 = (Bitmap)var3;
      if (var1.exists()) {
         try {
            BitmapFactory.Options var5 = new BitmapFactory.Options();
            var5.inSampleSize = 2;
            var5.inPurgeable = true;
            var5.inInputShareable = true;
            var2 = BitmapFactory.decodeFile(var1.getPath(), var5);
         } catch (Exception var4) {
            var4.printStackTrace();
            var2 = (Bitmap)var3;
         }
      }

      return var2;
   }

   private void selectLogo(int var1) {
      if (var1 < 0) {
         try {
            this.mLogoImageView.setImageBitmap(BitmapFactory.decodeFile("/cache/logo/customer.png"));
            this.setLogoName("other");
         } catch (Exception var4) {
         }

      } else {
         String var3 = (String)this.mLogoFileNames.get(var1);
         int var2 = var3.lastIndexOf(".");
         if (var2 > 0) {
            this.setLogoName(var3.substring(0, var2));
         }

         this.mLogoImageView.setImageBitmap((Bitmap)this.mLogoBitmaps.get(var1));
      }
   }

   private void setLogoName(String var1) {
      for(int var2 = 0; var2 < this.mConfig.mLogo.length; ++var2) {
         if (var2 < var1.length()) {
            this.mConfig.mLogo[var2] = (byte)var1.charAt(var2);
         } else {
            this.mConfig.mLogo[var2] = 0;
         }
      }

   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      this.mConfig = this.mFactoryFun.getFactoryConfig();
      this.mlogoid = this.findLogos();
      this.InitCarLogo();
      if (this.mFactoryFun.getParameters("sta_function=27").equals("1") || SystemProperties.get("ro.product.carlogodisable", "false").equals("true")) {
         this.logoDisable = false;
         this.mLogoGallery.setVisibility(4);
      }

      if (this.isCHS6) {
         this.mCusLogo.setVisibility(8);
      }

   }

   public void onActivityResult(int var1, int var2, Intent var3) {
      if (var2 != 1) {
         if (var2 != 2) {
         }
      } else {
         String var6 = var3.getStringExtra("logopath");
         int var4 = var6.lastIndexOf("/");
         int var5 = var6.lastIndexOf(".");
         if (var4 > 0 && var5 > 0 && var4 < var5) {
            this.setLogoName(var6.substring(var4 + 1, var5));
         }

         this.mLogoImageView.setImageBitmap(BitmapFactory.decodeFile(var6));
      }

      if (var1 == 2 && var3 != null) {
         Uri var7 = var3.getData();
         ContentResolver var9 = this.getActivity().getContentResolver();

         try {
            Bitmap var10 = BitmapFactory.decodeStream(var9.openInputStream(var7));
            if (this.saveBitmap(var10)) {
               this.setLogoName("customer");
               this.mLogoImageView.setImageBitmap(var10);
            }
         } catch (FileNotFoundException var8) {
         }
      }

      super.onActivityResult(var1, var2, var3);
   }

   public void onAttach(Activity var1) {
      super.onAttach(var1);
      this.mActivity = var1;

      try {
         this.mFactoryFun = (FactoryFun)var1;
      } catch (Exception var2) {
      }

   }

   public void onCreate(@Nullable Bundle var1) {
      super.onCreate(var1);
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      View var4 = var1.inflate(2131230730, var2, false);
      this.Logomodegroup = (Spinner)var4.findViewById(2131099802);
      this.mLogoImageView = (ImageView)var4.findViewById(2131099804);
      this.mLogoGallery = (Gallery)var4.findViewById(2131099801);
      this.mLogocheckbox = (CheckBox)var4.findViewById(2131099797);
      this.mLogoButton = (Button)var4.findViewById(2131099798);
      this.mLogoGalleryButton = (Button)var4.findViewById(2131099799);
      this.logoType_id = (TextView)var4.findViewById(2131099803);
      this.mCusLogo = (LinearLayout)var4.findViewById(2131099739);
      return var4;
   }

   public void onDestroy() {
      super.onDestroy();
   }

   public void onDetach() {
      super.onDetach();
   }

   public void onResume() {
      super.onResume();
   }

   public boolean saveBitmap(Bitmap var1) {
      try {
         Runtime.getRuntime().exec("chmod 777 /cache/logo/customer.png");
         File var2 = new File("/cache/logo/customer.png");
         if (var2.exists()) {
            var2.delete();
         }

         FileOutputStream var3 = new FileOutputStream(var2);
         var1.compress(CompressFormat.PNG, 90, var3);
         var3.flush();
         var3.getFD().sync();
         var3.close();
         Runtime.getRuntime().exec("chmod 777 /cache/logo/customer.png");
         return true;
      } catch (Exception var4) {
         return false;
      }
   }

   private class LogoAdapter extends BaseAdapter {
      private LayoutInflater mLayoutInflater;
      final CarLogoFragment this$0;

      public LogoAdapter(CarLogoFragment var1, Context var2) {
         this.this$0 = var1;
         this.mLayoutInflater = LayoutInflater.from(var2);
      }

      public int getCount() {
         return this.this$0.mLogoBitmaps.size();
      }

      public Object getItem(int var1) {
         return var1;
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public View getView(int var1, View var2, ViewGroup var3) {
         ImageView var4;
         if (var2 == null) {
            var4 = (ImageView)this.mLayoutInflater.inflate(2131230735, var3, false);
         } else {
            var4 = (ImageView)var2;
         }

         var4.setImageBitmap((Bitmap)this.this$0.mLogoBitmaps.get(var1));
         return var4;
      }
   }
}
