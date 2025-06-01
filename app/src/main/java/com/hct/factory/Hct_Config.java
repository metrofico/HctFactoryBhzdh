package com.hct.factory;

import android.content.Context;
import android.microntek.CarManager;

public class Hct_Config {
   public CarManager am = null;
   public ST_FACTORY_CONFIG mFactory_config = null;
   public ST_FACTORY_EXT_CONFIG mFactory_ext_config = null;
   public ST_KEY_CONFIG mKey_config = null;
   public ST_TOUCH_CONFIG mTouch_config = null;
   public ST_USER_CONFIG mUser_config = null;

   public Hct_Config(Context var1) {
      this.am = new CarManager();
      this.mFactory_config = new ST_FACTORY_CONFIG();
      this.mUser_config = new ST_USER_CONFIG();
      this.mTouch_config = new ST_TOUCH_CONFIG();
      this.mKey_config = new ST_KEY_CONFIG();
      this.mFactory_ext_config = new ST_FACTORY_EXT_CONFIG();
      this.LoadFactory_config();
      this.LoadUser_config();
      this.LoadFactory_ext_config();
   }

   private boolean saveConfigPath(byte[] param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   public boolean Export_config(String var1) {
      byte[] var5 = new byte[128];
      String var6 = this.am.getParameters("cfg_factory=");
      int var2;
      int var3;
      if (var6 != null) {
         String[] var19 = var6.split(",");
         var3 = var19.length;

         for(var2 = 0; var2 < var5.length && var2 <= var3; ++var2) {
            try {
               var5[var2] = (byte)(Integer.parseInt(var19[var2]) & 255);
            } catch (NumberFormatException var18) {
            }
         }
      }

      byte[] var20 = new byte[64];
      String var7 = this.am.getParameters("cfg_user=");
      String[] var8;
      if (var7 != null) {
         var8 = var7.split(",");
         var3 = var8.length;

         for(var2 = 0; var2 < var20.length && var2 <= var3; ++var2) {
            try {
               var20[var2] = (byte)(Integer.parseInt(var8[var2]) & 255);
            } catch (NumberFormatException var17) {
            }
         }
      }

      byte[] var21 = new byte[90];
      String var22 = this.am.getParameters("cfg_touch=");
      if (var22 != null) {
         var8 = var22.split(",");
         var3 = var8.length;

         for(var2 = 0; var2 < 90 && var2 <= var3; ++var2) {
            try {
               var21[var2] = (byte)(Integer.parseInt(var8[var2]) & 255);
            } catch (NumberFormatException var16) {
            }
         }
      }

      int[] var23 = new int[120];
      String var9 = this.am.getParameters("cfg_key=");
      if (var9 != null) {
         String[] var24 = var9.split(",");
         var3 = var24.length;

         for(var2 = 0; var2 < var23.length && var2 <= var3; ++var2) {
            try {
               var23[var2] = Integer.parseInt(var24[var2]);
            } catch (NumberFormatException var15) {
            }
         }
      }

      byte[] var25 = new byte[32];
      if ("yes".equals(this.am.getParameters("sta_mcu_o="))) {
         String var10 = this.am.getParameters("cfg_org=");
         if (var10 != null && var10.contains(",")) {
            String[] var11 = var10.split(",");
            var3 = var11.length;

            for(var2 = 0; var2 < var25.length && var2 <= var3; ++var2) {
               try {
                  var25[var2] = (byte)(Integer.parseInt(var11[var2]) & 255);
               } catch (NumberFormatException var14) {
               }
            }
         }
      }

      byte[] var26 = new byte[48];
      String var27 = this.am.getParameters("cfg_factory_ext=");
      if (var27 != null && var27.contains(",")) {
         String[] var12 = var27.split(",");
         var3 = var12.length;

         for(var2 = 0; var2 < var26.length && var2 < var3; ++var2) {
            try {
               var26[var2] = (byte)(Integer.parseInt(var12[var2]) & 255);
            } catch (NumberFormatException var13) {
            }
         }
      }

      byte[] var28 = new byte[605];
      var2 = 0 + 1;
      var28[0] = 77;
      var3 = var2 + 1;
      var28[var2] = 84;
      var2 = var3 + 1;
      var28[var3] = 67;

      for(var3 = 0; var3 < var5.length; ++var2) {
         var28[var2] = var5[var3];
         ++var3;
      }

      for(var3 = 0; var3 < var20.length; ++var2) {
         var28[var2] = var20[var3];
         ++var3;
      }

      for(var3 = 0; var3 < var21.length; ++var2) {
         var28[var2] = var21[var3];
         ++var3;
      }

      for(var3 = 0; var3 < var23.length; ++var3) {
         int var4 = var2 + 1;
         var28[var2] = (byte)(var23[var3] & 255);
         var2 = var4 + 1;
         var28[var4] = (byte)(var23[var3] >> 8 & 255);
      }

      for(var3 = 0; var3 < var25.length; ++var2) {
         var28[var2] = var25[var3];
         ++var3;
      }

      for(var3 = 0; var3 < var26.length; ++var2) {
         var28[var2] = var26[var3];
         ++var3;
      }

      return this.saveConfigPath(var28, var1);
   }

   public void LoadFactory_config() {
      byte[] var4 = new byte[128];
      String var5 = this.am.getParameters("cfg_factory=");
      int var1;
      int var2;
      if (var5 != null) {
         String[] var6 = var5.split(",");
         var2 = var6.length;

         for(var1 = 0; var1 < var4.length && var1 <= var2; ++var1) {
            try {
               var4[var1] = (byte)(Integer.parseInt(var6[var1]) & 255);
            } catch (NumberFormatException var7) {
            }
         }
      }

      var1 = 0;
      this.mFactory_config = new ST_FACTORY_CONFIG();

      for(var2 = 0; var2 < this.mFactory_config.mIdentify.length; ++var1) {
         this.mFactory_config.mIdentify[var2] = var4[var1];
         ++var2;
      }

      for(var2 = 0; var2 < this.mFactory_config.mCustomerId.length; ++var1) {
         this.mFactory_config.mCustomerId[var2] = var4[var1];
         ++var2;
      }

      for(var2 = 0; var2 < this.mFactory_config.mCustomerSubId.length; ++var1) {
         this.mFactory_config.mCustomerSubId[var2] = var4[var1];
         ++var2;
      }

      for(var2 = 0; var2 < this.mFactory_config.mModel.length; ++var1) {
         this.mFactory_config.mModel[var2] = var4[var1];
         ++var2;
      }

      for(var2 = 0; var2 < this.mFactory_config.mLogo.length; ++var1) {
         this.mFactory_config.mLogo[var2] = var4[var1];
         ++var2;
      }

      ST_FACTORY_CONFIG var8 = this.mFactory_config;
      var2 = var1 + 1;
      var8.mLogoType = var4[var1];
      int var3 = var2 + 1;
      var8.mCanbus = var4[var2];
      var1 = var3 + 1;
      var8.mCanbusCfg = var4[var3];
      var2 = var1 + 1;
      var8.mRudder = var4[var1];
      var1 = var2 + 1;
      var8.mDtv = var4[var2];
      var2 = var1 + 1;
      var8.mIpod = var4[var1];
      var1 = var2 + 1;
      var8.mDvd = var4[var2];
      var2 = var1 + 1;
      var8.mBt = var4[var1];
      var1 = var2 + 1;
      var8.mRadio = var4[var2];
      var2 = var1 + 1;
      var8.mRadioArea = var4[var1];
      var1 = var2 + 1;
      var8.mRds = var4[var2];
      var2 = var1 + 1;
      var8.mLauncher = var4[var1];
      var1 = var2 + 1;
      var8.mLedType = var4[var2];
      var3 = var1 + 1;
      var8.mKeyLedMode = var4[var1];
      var2 = var3 + 1;
      var8.mFrontView = var4[var3];
      var1 = var2 + 1;
      var8.mKnobMode = var4[var2];

      for(var2 = 0; var2 < this.mFactory_config.mChannelGain.length; ++var1) {
         this.mFactory_config.mChannelGain[var2] = var4[var1];
         ++var2;
      }

      var8 = this.mFactory_config;
      var2 = var1 + 1;
      var8.mAppDisable = var4[var1];
      var1 = var2 + 1;
      var8.mBacklightMode = var4[var2];
      var2 = var1 + 1;
      var8.mWheelstudyMenu = var4[var1];
      var1 = var2 + 1;
      var8.mIllBrightness = var4[var2];
      var2 = var1 + 1;
      var8.mDvr = var4[var1];
      var1 = var2 + 1;
      var8.mFrontviewDelay = var4[var2];
      var2 = var1 + 1;
      var8.mDefaultPowerState = var4[var1];
      var1 = var2 + 1;
      var8.mSubVersion = var4[var2];
      var2 = var1 + 1;
      var8.mCvbsBackview = var4[var1];
      var1 = var2 + 1;
      var8.mBrightness = var4[var2];
      var2 = var1 + 1;
      var8.mContrast = var4[var1];
      var1 = var2 + 1;
      var8.mSaturation = var4[var2];
      var2 = var1 + 1;
      var8.mBackviewBrightness = var4[var1];
      var3 = var2 + 1;
      var8.mVolMaxDefault = var4[var2];
      var1 = var3 + 1;
      var8.mBackviewDisable = var4[var3];
      var2 = var1 + 1;
      var8.mDab = var4[var1];
      var1 = var2 + 1;
      var8.mAmpVolume = var4[var2];
      var2 = var1 + 1;
      var8.mTpms = var4[var1];
      var1 = var2 + 1;
      var8.mHdmi = var4[var2];
      var2 = var1 + 1;
      var8.mRadioGain = var4[var1];
      var1 = var2 + 1;
      var8.mCarPlay = var4[var2];
      var2 = var1 + 1;
      var8.mScreenIps = var4[var1];
      var1 = var2 + 1;
      var8.m3PartyCall = var4[var2];
      var2 = var1 + 1;
      var8.mBattVol = var4[var1];
      var3 = var2 + 1;
      var8.mAvin = var4[var2];
      var1 = var3 + 1;
      var8.mRearPanel = var4[var3];
      var2 = var1 + 1;
      var8.mExtCfg = var4[var1];
      var1 = var2 + 1;
      var8.mVcom = var4[var2];
      var2 = var1 + 1;
      var8.mAvdd = var4[var1];
      var1 = var2 + 1;
      var8.mBrightnessMax = var4[var2];
      var2 = var1 + 1;
      var8.mBrightnessMin = var4[var1];
      var1 = var2 + 1;
      var8.mHue = var4[var2];

      for(var2 = 0; var2 < this.mFactory_config.mRev.length; ++var1) {
         this.mFactory_config.mRev[var2] = var4[var1];
         ++var2;
      }

      for(var2 = 0; var2 < this.mFactory_config.mCanRev.length; ++var1) {
         this.mFactory_config.mCanRev[var2] = var4[var1];
         ++var2;
      }

   }

   public void LoadFactory_ext_config() {
      byte[] var3 = new byte[48];
      String var4 = this.am.getParameters("cfg_factory_ext=");
      int var1;
      int var2;
      if (var4 != null && var4.contains(",")) {
         String[] var7 = var4.split(",");
         var2 = var7.length;

         for(var1 = 0; var1 < var3.length && var1 <= var2; ++var1) {
            try {
               var3[var1] = (byte)(Integer.parseInt(var7[var1]) & 255);
            } catch (NumberFormatException var6) {
            }
         }
      }

      this.mFactory_ext_config = new ST_FACTORY_EXT_CONFIG();
      ST_FACTORY_EXT_CONFIG var8 = this.mFactory_ext_config;
      var1 = 0 + 1;
      var8.mHiCar = var3[0];
      var2 = var1 + 1;
      var8.mEasyConnection = var3[var1];
      var1 = var2 + 1;
      var8.mTef6686_EXT = var3[var2];
      var2 = var1 + 1;
      var8.mBoxLed = var3[var1];

      for(var1 = 0; var1 < this.mFactory_ext_config.mRev.length; ++var2) {
         this.mFactory_ext_config.mRev[var1] = var3[var2];
         ++var1;
      }

   }

   public void LoadKeyconfig() {
      this.mKey_config = new ST_KEY_CONFIG();
      int[] var4 = new int[120];
      String var5 = this.am.getParameters("cfg_key=");
      int var1;
      int var2;
      if (var5 != null) {
         String[] var8 = var5.split(",");
         var2 = var8.length;

         for(var1 = 0; var1 < var4.length && var1 <= var2; ++var1) {
            try {
               var4[var1] = Integer.parseInt(var8[var1]);
            } catch (NumberFormatException var7) {
            }
         }
      }

      var2 = 0;

      for(var1 = 0; var1 < 40; ++var2) {
         this.mKey_config.mKey[var1] = new ST_KEY();
         ST_KEY var9 = this.mKey_config.mKey[var1];
         int var3 = var2 + 1;
         var9.mValue = var4[var2];
         var9 = this.mKey_config.mKey[var1];
         var2 = var3 + 1;
         var9.mShort = var4[var3];
         this.mKey_config.mKey[var1].mLong = var4[var2];
         ++var1;
      }

   }

   public void LoadTouch_config() {
      this.mTouch_config = new ST_TOUCH_CONFIG();
      byte[] var5 = new byte[90];
      String var6 = this.am.getParameters("cfg_touch=");
      int var1;
      int var2;
      if (var6 != null) {
         String[] var7 = var6.split(",");
         var2 = var7.length;

         for(var1 = 0; var1 < 90 && var1 <= var2; ++var1) {
            try {
               var5[var1] = (byte)(Integer.parseInt(var7[var1]) & 255);
            } catch (NumberFormatException var8) {
            }
         }
      }

      ST_TOUCH_CONFIG var11 = this.mTouch_config;
      var2 = 0 + 1;
      var11.mConfigId = var5[0];
      var1 = var2 + 1;
      var11.mVendorId = var5[var2];
      var2 = var1 + 1;
      int var3 = var5[var1];
      var1 = var2 + 1;
      var11.mX = (var5[var2] & 255) << 8 | var3 & 255;
      var2 = var1 + 1;
      var3 = var5[var1];
      var1 = var2 + 1;
      var11.mY = (var5[var2] & 255) << 8 | var3 & 255;
      var2 = var1 + 1;
      var3 = var5[var1];
      var1 = var2 + 1;
      var11.mXStart = (var5[var2] & 255) << 8 | var3 & 255;
      var2 = var1 + 1;
      var3 = var5[var1];
      var1 = var2 + 1;
      var11.mXEnd = (var5[var2] & 255) << 8 | var3 & 255;
      var2 = var1 + 1;
      var3 = var5[var1];
      var1 = var2 + 1;
      var11.mYStart = (var5[var2] & 255) << 8 | var3 & 255;
      var2 = var1 + 1;
      var3 = var5[var1];
      var1 = var2 + 1;
      var11.mYEnd = (var5[var2] & 255) << 8 | var3 & 255;

      for(var2 = 0; var2 < this.mTouch_config.mRev.length; ++var1) {
         this.mTouch_config.mRev[var2] = var5[var1];
         ++var2;
      }

      for(var2 = 0; var2 < 12; var1 = var3 + 1) {
         this.mTouch_config.mKey[var2] = new ST_TOUCH_KEY();
         var3 = var1 + 1;
         int var4 = var5[var1];
         var1 = var3 + 1;
         byte var10 = var5[var3];
         this.mTouch_config.mKey[var2].mShort = (var10 & 255) << 8 | var4 & 255;
         var4 = var1 + 1;
         var10 = var5[var1];
         var1 = var4 + 1;
         byte var9 = var5[var4];
         this.mTouch_config.mKey[var2].mLong = (var9 & 255) << 8 | var10 & 255;
         ST_TOUCH_KEY var12 = this.mTouch_config.mKey[var2];
         var3 = var1 + 1;
         var12.mX = var5[var1];
         this.mTouch_config.mKey[var2].mY = var5[var3];
         ++var2;
      }

   }

   public void LoadUser_config() {
      this.mUser_config = new ST_USER_CONFIG();
      byte[] var4 = new byte[64];
      String var5 = this.am.getParameters("cfg_user=");
      int var1;
      int var2;
      if (var5 != null) {
         String[] var8 = var5.split(",");
         var2 = var8.length;

         for(var1 = 0; var1 < var4.length && var1 <= var2; ++var1) {
            try {
               var4[var1] = (byte)(Integer.parseInt(var8[var1]) & 255);
            } catch (NumberFormatException var7) {
            }
         }
      }

      var1 = 0;

      for(var2 = 0; var2 < this.mUser_config.mColor.length; ++var1) {
         this.mUser_config.mColor[var2] = var4[var1];
         ++var2;
      }

      ST_USER_CONFIG var9 = this.mUser_config;
      int var3 = var1 + 1;
      var9.mPowerDelay = var4[var1];
      var2 = var3 + 1;
      var9.mBacklight = var4[var3];
      var1 = var2 + 1;
      var9.mDim = var4[var2];
      var3 = var1 + 1;
      var9.mBeep = var4[var1];
      var2 = var3 + 1;
      var9.mR = var4[var3];
      var1 = var2 + 1;
      var9.mG = var4[var2];
      var3 = var1 + 1;
      var9.mB = var4[var1];
      var2 = var3 + 1;
      var9.mMirror = var4[var3];
      var1 = var2 + 1;
      var9.mMultiColor = var4[var2];
      var2 = var1 + 1;
      var9.mBlMode = var4[var1];
      var3 = var2 + 1;
      var9.mVideoSafe = var4[var2];
      var1 = var3 + 1;
      var9.mBookLogo = var4[var3];

      for(var2 = 0; var2 < this.mUser_config.mRev.length; ++var1) {
         this.mUser_config.mRev[var2] = var4[var1];
         ++var2;
      }

   }

   public void SaveFactory_config() {
      if (this.am != null) {
         byte[] var5 = new byte[128];
         int var1 = 0;

         int var2;
         for(var2 = 0; var2 < this.mFactory_config.mIdentify.length; ++var1) {
            var5[var1] = this.mFactory_config.mIdentify[var2];
            ++var2;
         }

         for(var2 = 0; var2 < this.mFactory_config.mCustomerId.length; ++var1) {
            var5[var1] = this.mFactory_config.mCustomerId[var2];
            ++var2;
         }

         for(var2 = 0; var2 < this.mFactory_config.mCustomerSubId.length; ++var1) {
            var5[var1] = this.mFactory_config.mCustomerSubId[var2];
            ++var2;
         }

         for(var2 = 0; var2 < this.mFactory_config.mModel.length; ++var1) {
            var5[var1] = this.mFactory_config.mModel[var2];
            ++var2;
         }

         for(var2 = 0; var2 < this.mFactory_config.mLogo.length; ++var1) {
            var5[var1] = this.mFactory_config.mLogo[var2];
            ++var2;
         }

         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mLogoType;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mCanbus;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mCanbusCfg;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mRudder;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mDtv;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mIpod;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mDvd;
         int var3 = var2 + 1;
         var5[var2] = this.mFactory_config.mBt;
         var1 = var3 + 1;
         var5[var3] = this.mFactory_config.mRadio;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mRadioArea;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mRds;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mLauncher;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mLedType;
         var3 = var1 + 1;
         var5[var1] = this.mFactory_config.mKeyLedMode;
         var2 = var3 + 1;
         var5[var3] = this.mFactory_config.mFrontView;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mKnobMode;

         for(var2 = 0; var2 < this.mFactory_config.mChannelGain.length; ++var1) {
            var5[var1] = this.mFactory_config.mChannelGain[var2];
            ++var2;
         }

         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mAppDisable;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mBacklightMode;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mWheelstudyMenu;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mIllBrightness;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mDvr;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mFrontviewDelay;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mDefaultPowerState;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mSubVersion;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mCvbsBackview;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mBrightness;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mContrast;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mSaturation;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mBackviewBrightness;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mVolMaxDefault;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mBackviewDisable;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mDab;
         var3 = var1 + 1;
         var5[var1] = this.mFactory_config.mAmpVolume;
         var2 = var3 + 1;
         var5[var3] = this.mFactory_config.mTpms;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mHdmi;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mRadioGain;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mCarPlay;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mScreenIps;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.m3PartyCall;
         var3 = var1 + 1;
         var5[var1] = this.mFactory_config.mBattVol;
         var2 = var3 + 1;
         var5[var3] = this.mFactory_config.mAvin;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mRearPanel;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mExtCfg;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mVcom;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mAvdd;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mBrightnessMax;
         var2 = var1 + 1;
         var5[var1] = this.mFactory_config.mBrightnessMin;
         var1 = var2 + 1;
         var5[var2] = this.mFactory_config.mHue;

         for(var2 = 0; var2 < this.mFactory_config.mRev.length; ++var1) {
            var5[var1] = this.mFactory_config.mRev[var2];
            ++var2;
         }

         for(var2 = 0; var2 < this.mFactory_config.mCanRev.length; ++var1) {
            var5[var1] = this.mFactory_config.mCanRev[var2];
            ++var2;
         }

         StringBuffer var4 = new StringBuffer();

         for(var1 = 0; var1 < var5.length; ++var1) {
            var4.append(var5[var1] & 255);
            if (var1 != var5.length - 1) {
               var4.append(",");
            }
         }

         CarManager var7 = this.am;
         StringBuilder var6 = new StringBuilder();
         var6.append("cfg_factory=");
         var6.append(var4.toString());
         var7.setParameters(var6.toString());
      }
   }

   public void SaveFactory_ext_config() {
      if (this.am != null) {
         byte[] var5 = new byte[48];
         int var2 = 0 + 1;
         var5[0] = this.mFactory_ext_config.mHiCar;
         int var1 = var2 + 1;
         var5[var2] = this.mFactory_ext_config.mEasyConnection;
         int var3 = var1 + 1;
         var5[var1] = this.mFactory_ext_config.mTef6686_EXT;
         var2 = var3 + 1;
         var5[var3] = this.mFactory_ext_config.mBoxLed;

         for(var1 = 0; var1 < this.mFactory_ext_config.mRev.length; ++var2) {
            var5[var2] = this.mFactory_ext_config.mRev[var1];
            ++var1;
         }

         StringBuffer var4 = new StringBuffer();

         for(var1 = 0; var1 < var5.length; ++var1) {
            var4.append(var5[var1] & 255);
            if (var1 != var5.length - 1) {
               var4.append(",");
            }
         }

         CarManager var7 = this.am;
         StringBuilder var6 = new StringBuilder();
         var6.append("cfg_factory_ext=");
         var6.append(var4.toString());
         var7.setParameters(var6.toString());
      }
   }

   public void SaveKey_config() {
      if (this.am != null) {
         int[] var5 = new int[120];
         int var2 = 0;

         int var1;
         for(var1 = 0; var1 < 40; ++var2) {
            int var3 = var2 + 1;
            var5[var2] = this.mKey_config.mKey[var1].mValue;
            var2 = var3 + 1;
            var5[var3] = this.mKey_config.mKey[var1].mShort;
            var5[var2] = this.mKey_config.mKey[var1].mLong;
            ++var1;
         }

         StringBuffer var4 = new StringBuffer();

         for(var1 = 0; var1 < var5.length; ++var1) {
            var4.append(var5[var1]);
            if (var1 != var5.length - 1) {
               var4.append(",");
            }
         }

         CarManager var6 = this.am;
         StringBuilder var7 = new StringBuilder();
         var7.append("cfg_key=");
         var7.append(var4.toString());
         var6.setParameters(var7.toString());
      }
   }

   public void SaveTouch_config() {
      if (this.am != null) {
         byte[] var6 = new byte[90];
         int var1 = 0 + 1;
         var6[0] = this.mTouch_config.mConfigId;
         int var2 = var1 + 1;
         var6[var1] = this.mTouch_config.mVendorId;
         var1 = var2 + 1;
         var6[var2] = (byte)(this.mTouch_config.mX & 255);
         int var3 = var1 + 1;
         var6[var1] = (byte)(this.mTouch_config.mX >> 8 & 255);
         var2 = var3 + 1;
         var6[var3] = (byte)(this.mTouch_config.mY & 255);
         var1 = var2 + 1;
         var6[var2] = (byte)(this.mTouch_config.mY >> 8 & 255);
         var2 = var1 + 1;
         var6[var1] = (byte)(this.mTouch_config.mXStart & 255);
         var3 = var2 + 1;
         var6[var2] = (byte)(this.mTouch_config.mXStart >> 8 & 255);
         var1 = var3 + 1;
         var6[var3] = (byte)(this.mTouch_config.mXEnd & 255);
         var2 = var1 + 1;
         var6[var1] = (byte)(this.mTouch_config.mXEnd >> 8 & 255);
         var1 = var2 + 1;
         var6[var2] = (byte)(this.mTouch_config.mYStart & 255);
         var2 = var1 + 1;
         var6[var1] = (byte)(this.mTouch_config.mYStart >> 8 & 255);
         var3 = var2 + 1;
         var6[var2] = (byte)(this.mTouch_config.mYEnd & 255);
         var1 = var3 + 1;
         var6[var3] = (byte)(this.mTouch_config.mYEnd >> 8 & 255);

         for(var2 = 0; var2 < this.mTouch_config.mRev.length; ++var1) {
            var6[var1] = this.mTouch_config.mRev[var2];
            ++var2;
         }

         for(var2 = 0; var2 < 12; ++var2) {
            var3 = var1 + 1;
            var6[var1] = (byte)(this.mTouch_config.mKey[var2].mShort & 255);
            int var4 = var3 + 1;
            var6[var3] = (byte)(this.mTouch_config.mKey[var2].mShort >> 8 & 255);
            var1 = var4 + 1;
            var6[var4] = (byte)(this.mTouch_config.mKey[var2].mLong & 255);
            var3 = var1 + 1;
            var6[var1] = (byte)(this.mTouch_config.mKey[var2].mLong >> 8 & 255);
            var4 = var3 + 1;
            var6[var3] = this.mTouch_config.mKey[var2].mX;
            var1 = var4 + 1;
            var6[var4] = this.mTouch_config.mKey[var2].mY;
         }

         StringBuffer var5 = new StringBuffer();

         for(var1 = 0; var1 < var6.length; ++var1) {
            var5.append(var6[var1] & 255);
            if (var1 != var6.length - 1) {
               var5.append(",");
            }
         }

         CarManager var7 = this.am;
         StringBuilder var8 = new StringBuilder();
         var8.append("cfg_touch=");
         var8.append(var5.toString());
         var7.setParameters(var8.toString());
      }
   }

   public void SaveUser_config() {
      if (this.am != null) {
         byte[] var5 = new byte[64];
         int var1 = 0;

         int var2;
         for(var2 = 0; var2 < this.mUser_config.mColor.length; ++var1) {
            var5[var1] = this.mUser_config.mColor[var2];
            ++var2;
         }

         var2 = var1 + 1;
         var5[var1] = this.mUser_config.mPowerDelay;
         var1 = var2 + 1;
         var5[var2] = this.mUser_config.mBacklight;
         var2 = var1 + 1;
         var5[var1] = this.mUser_config.mDim;
         var1 = var2 + 1;
         var5[var2] = this.mUser_config.mBeep;
         int var3 = var1 + 1;
         var5[var1] = this.mUser_config.mR;
         var2 = var3 + 1;
         var5[var3] = this.mUser_config.mG;
         var1 = var2 + 1;
         var5[var2] = this.mUser_config.mB;
         var3 = var1 + 1;
         var5[var1] = this.mUser_config.mMirror;
         var2 = var3 + 1;
         var5[var3] = this.mUser_config.mMultiColor;
         var1 = var2 + 1;
         var5[var2] = this.mUser_config.mBlMode;
         var3 = var1 + 1;
         var5[var1] = this.mUser_config.mVideoSafe;
         var2 = var3 + 1;
         var5[var3] = this.mUser_config.mBookLogo;

         for(var1 = 0; var1 < this.mUser_config.mRev.length; ++var2) {
            var5[var2] = this.mUser_config.mRev[var1];
            ++var1;
         }

         StringBuffer var4 = new StringBuffer();

         for(var1 = 0; var1 < var5.length; ++var1) {
            var4.append(var5[var1] & 255);
            if (var1 != var5.length - 1) {
               var4.append(",");
            }
         }

         CarManager var7 = this.am;
         StringBuilder var6 = new StringBuilder();
         var6.append("cfg_user=");
         var6.append(var4.toString());
         var7.setParameters(var6.toString());
      }
   }

   public static class ST_FACTORY_CONFIG {
      public byte m3PartyCall;
      public byte mAmpVolume;
      public byte mAppDisable;
      public byte mAvdd;
      public byte mAvin;
      public byte mBacklightMode;
      public byte mBackviewBrightness;
      public byte mBackviewDisable;
      public byte mBattVol;
      public byte mBrightness;
      public byte mBrightnessMax;
      public byte mBrightnessMin;
      public byte mBt;
      public byte[] mCanRev = new byte[16];
      public byte mCanbus;
      public byte mCanbusCfg;
      public byte mCarPlay;
      public byte[] mChannelGain = new byte[12];
      public byte mContrast;
      public byte[] mCustomerId = new byte[4];
      public byte[] mCustomerSubId = new byte[4];
      public byte mCvbsBackview;
      public byte mDab;
      public byte mDefaultPowerState;
      public byte mDtv;
      public byte mDvd;
      public byte mDvr;
      public byte mExtCfg;
      public byte mFrontView;
      public byte mFrontviewDelay;
      public byte mHdmi;
      public byte mHue;
      public byte[] mIdentify = new byte[4];
      public byte mIllBrightness;
      public byte mIpod;
      public byte mKeyLedMode;
      public byte mKnobMode;
      public byte mLauncher;
      public byte mLedType;
      public byte[] mLogo = new byte[16];
      public byte mLogoType;
      public byte[] mModel = new byte[24];
      public byte mRadio;
      public byte mRadioArea;
      public byte mRadioGain;
      public byte mRds;
      public byte mRearPanel;
      public byte[] mRev = new byte[0];
      public byte mRudder;
      public byte mSaturation;
      public byte mScreenIps;
      public byte mSubVersion;
      public byte mTpms;
      public byte mVcom;
      public byte mVolMaxDefault;
      public byte mWheelstudyMenu;

      public String ToString() {
         byte[] var4 = new byte[128];
         int var1 = 0;
         int var2 = 0;

         while(true) {
            byte[] var5 = this.mIdentify;
            if (var2 >= var5.length) {
               var2 = 0;

               while(true) {
                  var5 = this.mCustomerId;
                  if (var2 >= var5.length) {
                     var2 = 0;

                     while(true) {
                        var5 = this.mCustomerSubId;
                        if (var2 >= var5.length) {
                           var2 = 0;

                           while(true) {
                              var5 = this.mModel;
                              if (var2 >= var5.length) {
                                 var2 = 0;

                                 while(true) {
                                    var5 = this.mLogo;
                                    if (var2 >= var5.length) {
                                       var2 = var1 + 1;
                                       var4[var1] = this.mLogoType;
                                       var1 = var2 + 1;
                                       var4[var2] = this.mCanbus;
                                       var2 = var1 + 1;
                                       var4[var1] = this.mCanbusCfg;
                                       var1 = var2 + 1;
                                       var4[var2] = this.mRudder;
                                       var2 = var1 + 1;
                                       var4[var1] = this.mDtv;
                                       var1 = var2 + 1;
                                       var4[var2] = this.mIpod;
                                       var2 = var1 + 1;
                                       var4[var1] = this.mDvd;
                                       var1 = var2 + 1;
                                       var4[var2] = this.mBt;
                                       var2 = var1 + 1;
                                       var4[var1] = this.mRadio;
                                       var1 = var2 + 1;
                                       var4[var2] = this.mRadioArea;
                                       var2 = var1 + 1;
                                       var4[var1] = this.mRds;
                                       int var3 = var2 + 1;
                                       var4[var2] = this.mLauncher;
                                       var1 = var3 + 1;
                                       var4[var3] = this.mLedType;
                                       var2 = var1 + 1;
                                       var4[var1] = this.mKeyLedMode;
                                       var3 = var2 + 1;
                                       var4[var2] = this.mFrontView;
                                       var1 = var3 + 1;
                                       var4[var3] = this.mKnobMode;
                                       var2 = 0;

                                       while(true) {
                                          var5 = this.mChannelGain;
                                          if (var2 >= var5.length) {
                                             var2 = var1 + 1;
                                             var4[var1] = this.mAppDisable;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mBacklightMode;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mWheelstudyMenu;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mIllBrightness;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mDvr;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mFrontviewDelay;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mDefaultPowerState;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mSubVersion;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mCvbsBackview;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mBrightness;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mContrast;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mSaturation;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mBackviewBrightness;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mVolMaxDefault;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mBackviewDisable;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mDab;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mAmpVolume;
                                             var3 = var2 + 1;
                                             var4[var2] = this.mTpms;
                                             var1 = var3 + 1;
                                             var4[var3] = this.mHdmi;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mRadioGain;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mCarPlay;
                                             var3 = var1 + 1;
                                             var4[var1] = this.mScreenIps;
                                             var2 = var3 + 1;
                                             var4[var3] = this.m3PartyCall;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mBattVol;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mAvin;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mRearPanel;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mExtCfg;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mVcom;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mAvdd;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mBrightnessMax;
                                             var2 = var1 + 1;
                                             var4[var1] = this.mBrightnessMin;
                                             var1 = var2 + 1;
                                             var4[var2] = this.mHue;
                                             var2 = 0;

                                             while(true) {
                                                var5 = this.mRev;
                                                if (var2 >= var5.length) {
                                                   var2 = 0;

                                                   while(true) {
                                                      var5 = this.mCanRev;
                                                      if (var2 >= var5.length) {
                                                         StringBuffer var6 = new StringBuffer();

                                                         for(var1 = 0; var1 < var4.length; ++var1) {
                                                            var6.append(var4[var1] & 255);
                                                            if (var1 != var4.length - 1) {
                                                               var6.append(",");
                                                            }
                                                         }

                                                         return var6.toString();
                                                      }

                                                      var4[var1] = var5[var2];
                                                      ++var2;
                                                      ++var1;
                                                   }
                                                }

                                                var4[var1] = var5[var2];
                                                ++var2;
                                                ++var1;
                                             }
                                          }

                                          var4[var1] = var5[var2];
                                          ++var2;
                                          ++var1;
                                       }
                                    }

                                    var4[var1] = var5[var2];
                                    ++var2;
                                    ++var1;
                                 }
                              }

                              var4[var1] = var5[var2];
                              ++var2;
                              ++var1;
                           }
                        }

                        var4[var1] = var5[var2];
                        ++var2;
                        ++var1;
                     }
                  }

                  var4[var1] = var5[var2];
                  ++var2;
                  ++var1;
               }
            }

            var4[var1] = var5[var2];
            ++var2;
            ++var1;
         }
      }

      protected ST_FACTORY_CONFIG Toclone() {
         ST_FACTORY_CONFIG var2 = new ST_FACTORY_CONFIG();
         int var1 = 0;

         while(true) {
            byte[] var3 = this.mIdentify;
            if (var1 >= var3.length) {
               var1 = 0;

               while(true) {
                  var3 = this.mCustomerId;
                  if (var1 >= var3.length) {
                     var1 = 0;

                     while(true) {
                        var3 = this.mCustomerSubId;
                        if (var1 >= var3.length) {
                           var1 = 0;

                           while(true) {
                              var3 = this.mModel;
                              if (var1 >= var3.length) {
                                 var1 = 0;

                                 while(true) {
                                    var3 = this.mLogo;
                                    if (var1 >= var3.length) {
                                       var2.mLogoType = this.mLogoType;
                                       var2.mCanbus = this.mCanbus;
                                       var2.mCanbusCfg = this.mCanbusCfg;
                                       var2.mRudder = this.mRudder;
                                       var2.mDtv = this.mDtv;
                                       var2.mIpod = this.mIpod;
                                       var2.mDvd = this.mDvd;
                                       var2.mBt = this.mBt;
                                       var2.mRadio = this.mRadio;
                                       var2.mRadioArea = this.mRadioArea;
                                       var2.mRds = this.mRds;
                                       var2.mLauncher = this.mLauncher;
                                       var2.mLedType = this.mLedType;
                                       var2.mKeyLedMode = this.mKeyLedMode;
                                       var2.mFrontView = this.mFrontView;
                                       var2.mKnobMode = this.mKnobMode;
                                       var1 = 0;

                                       while(true) {
                                          var3 = this.mChannelGain;
                                          if (var1 >= var3.length) {
                                             var2.mAppDisable = this.mAppDisable;
                                             var2.mBacklightMode = this.mBacklightMode;
                                             var2.mWheelstudyMenu = this.mWheelstudyMenu;
                                             var2.mIllBrightness = this.mIllBrightness;
                                             var2.mDvr = this.mDvr;
                                             var2.mFrontviewDelay = this.mFrontviewDelay;
                                             var2.mDefaultPowerState = this.mDefaultPowerState;
                                             var2.mSubVersion = this.mSubVersion;
                                             var2.mCvbsBackview = this.mCvbsBackview;
                                             var2.mBrightness = this.mBrightness;
                                             var2.mContrast = this.mContrast;
                                             var2.mSaturation = this.mSaturation;
                                             var2.mBackviewBrightness = this.mBackviewBrightness;
                                             var2.mVolMaxDefault = this.mVolMaxDefault;
                                             var2.mBackviewDisable = this.mBackviewDisable;
                                             var2.mDab = this.mDab;
                                             var2.mAmpVolume = this.mAmpVolume;
                                             var2.mTpms = this.mTpms;
                                             var2.mHdmi = this.mHdmi;
                                             var2.mRadioGain = this.mRadioGain;
                                             var2.mCarPlay = this.mCarPlay;
                                             var2.mScreenIps = this.mScreenIps;
                                             var2.m3PartyCall = this.m3PartyCall;
                                             var2.mBattVol = this.mBattVol;
                                             var2.mAvin = this.mAvin;
                                             var2.mRearPanel = this.mRearPanel;
                                             var2.mExtCfg = this.mExtCfg;
                                             var2.mVcom = this.mVcom;
                                             var2.mAvdd = this.mAvdd;
                                             var2.mBrightnessMax = this.mBrightnessMax;
                                             var2.mBrightnessMin = this.mBrightnessMin;
                                             var2.mHue = this.mHue;
                                             var1 = 0;

                                             while(true) {
                                                var3 = this.mRev;
                                                if (var1 >= var3.length) {
                                                   var1 = 0;

                                                   while(true) {
                                                      var3 = this.mCanRev;
                                                      if (var1 >= var3.length) {
                                                         return var2;
                                                      }

                                                      var2.mCanRev[var1] = var3[var1];
                                                      ++var1;
                                                   }
                                                }

                                                var2.mRev[var1] = var3[var1];
                                                ++var1;
                                             }
                                          }

                                          var2.mChannelGain[var1] = var3[var1];
                                          ++var1;
                                       }
                                    }

                                    var2.mLogo[var1] = var3[var1];
                                    ++var1;
                                 }
                              }

                              var2.mModel[var1] = var3[var1];
                              ++var1;
                           }
                        }

                        var2.mCustomerSubId[var1] = var3[var1];
                        ++var1;
                     }
                  }

                  var2.mCustomerId[var1] = var3[var1];
                  ++var1;
               }
            }

            var2.mIdentify[var1] = var3[var1];
            ++var1;
         }
      }
   }

   public static class ST_FACTORY_EXT_CONFIG {
      public byte mBoxLed;
      public byte mEasyConnection;
      public byte mHiCar;
      public byte[] mRev = new byte[44];
      public byte mTef6686_EXT;

      public String ToString() {
         byte[] var3 = new byte[48];
         int var2 = 0 + 1;
         var3[0] = this.mHiCar;
         int var1 = var2 + 1;
         var3[var2] = this.mEasyConnection;
         var2 = var1 + 1;
         var3[var1] = this.mTef6686_EXT;
         var1 = var2 + 1;
         var3[var2] = this.mBoxLed;
         var2 = 0;

         while(true) {
            byte[] var4 = this.mRev;
            if (var2 >= var4.length) {
               StringBuffer var5 = new StringBuffer();

               for(var1 = 0; var1 < var3.length; ++var1) {
                  var5.append(var3[var1] & 255);
                  if (var1 != var3.length - 1) {
                     var5.append(",");
                  }
               }

               return var5.toString();
            }

            var3[var1] = var4[var2];
            ++var2;
            ++var1;
         }
      }

      protected ST_FACTORY_EXT_CONFIG Toclone() {
         ST_FACTORY_EXT_CONFIG var3 = new ST_FACTORY_EXT_CONFIG();
         var3.mHiCar = this.mHiCar;
         var3.mEasyConnection = this.mEasyConnection;
         var3.mTef6686_EXT = this.mTef6686_EXT;
         var3.mBoxLed = this.mBoxLed;
         int var1 = 0;

         while(true) {
            byte[] var2 = this.mRev;
            if (var1 >= var2.length) {
               return var3;
            }

            var3.mRev[var1] = var2[var1];
            ++var1;
         }
      }
   }

   public static class ST_KEY {
      public int mLong;
      public int mShort;
      public int mValue;
   }

   public static class ST_KEY_CONFIG {
      public ST_KEY[] mKey = new ST_KEY[40];
   }

   public static class ST_TOUCH_CONFIG {
      public byte mConfigId = 0;
      public ST_TOUCH_KEY[] mKey = new ST_TOUCH_KEY[12];
      public byte[] mRev = new byte[4];
      public byte mVendorId = 0;
      public int mX = 0;
      public int mXEnd = -1;
      public int mXStart = -1;
      public int mY = 0;
      public int mYEnd = -1;
      public int mYStart = -1;
   }

   public static class ST_TOUCH_KEY {
      public int mLong;
      public int mShort;
      public byte mX;
      public byte mY;
   }

   public static class ST_USER_CONFIG {
      public byte mB;
      public byte mBackScreenMode;
      public byte mBacklight;
      public byte mBeep;
      public byte mBlMode;
      public byte mBookLogo;
      public byte[] mColor = new byte[2];
      public byte mDim;
      public byte mG;
      public byte mMirror;
      public byte mMultiColor;
      public byte mNightMode;
      public byte mPowerDelay;
      public byte mR;
      public byte[] mRev = new byte[48];
      public byte mVideoSafe;
   }
}
