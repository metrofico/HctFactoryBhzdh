package com.hzbhd.canbus.util;

import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.ui.util.BaseUtil;
import java.util.HashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.json.JSONException;
import org.json.JSONObject;

public class RadarInfoUtil {
   private static byte mBackLeftMidRadarInfo = -1;
   private static byte mBackLeftRadarInfo = -1;
   private static byte mBackRightMidRadarInfo = -1;
   private static byte mBackRightRadarInfo = -1;
   public static int mDisableData = 0;
   public static int mDisableData2 = 0;
   public static HashMap mDistanceMap = new HashMap();
   private static byte mFrontLeftMidRadarInfo = -1;
   private static byte mFrontLeftProbeInfo = -1;
   private static byte mFrontLeftRadarInfo = -1;
   private static byte mFrontRightMidRadarInfo = -1;
   private static byte mFrontRightProbeInfo = -1;
   private static byte mLeftFrontMidRadarInfo = -1;
   private static byte mLeftFrontRadarInfo = -1;
   private static byte mLeftRearMidRadarInfo = -1;
   private static byte mLeftRearRadarInfo = -1;
   public static HashMap mLocationMap = new HashMap();
   public static boolean mMinIsClose = false;
   private static byte[] mRadarData = new byte[Constant.RadarLocation.values().length];
   private static String mRadarFrontShareInfo = null;
   private static String mRadarLeftShareInfo = null;
   private static String mRadarRearShareInfo = null;
   private static String mRadarRightShareInfo = null;
   private static byte mRearLeftProbeInfo = -1;
   private static byte mRearRightProbeInfo = -1;
   private static byte mRightFrontMidRadarInfo = -1;
   private static byte mRightFrontRadarInfo = -1;
   private static byte mRightRearMidRadarInfo = -1;
   private static byte mRightRearRadarInfo = -1;
   private static byte mfrontRightRadarInfo = -1;

   public static String getRadarFrontShareInfo() {
      return mRadarFrontShareInfo;
   }

   public static String getRadarLeftShareInfo() {
      return mRadarLeftShareInfo;
   }

   public static String getRadarRearShareInfo() {
      return mRadarRearShareInfo;
   }

   public static String getRadarRightShareInfo() {
      return mRadarRightShareInfo;
   }

   private static void sendRadarShareInfoFR(String var0, int var1, int var2, int var3, int var4, int var5) throws JSONException {
      JSONObject var6 = new JSONObject();
      var6.put("mMinIsClose", mMinIsClose);
      var6.put("disableData", mDisableData);
      var6.put("range", var1);
      var6.put("left", var2);
      var6.put("left_mid", var3);
      var6.put("right_mid", var4);
      var6.put("right", var5);
      String var7 = var6.toString();
      if (com.hzbhd.util.LogUtil.log5()) {
         com.hzbhd.util.LogUtil.d("<sendRadarShareInfo> " + var0 + ": " + var7);
      }

      if ("canbus.RadarFront".equals(var0)) {
         mRadarFrontShareInfo = var7;
      } else {
         mRadarRearShareInfo = var7;
      }

      ShareDataServiceImpl.setString(var0, var7);
   }

   private static void sendRadarShareInfoLR(String var0, int var1, int var2, int var3, int var4, int var5) throws JSONException {
      JSONObject var6 = new JSONObject();
      var6.put("mMinIsClose", mMinIsClose);
      var6.put("disableData", mDisableData);
      var6.put("range", var1);
      var6.put("front", var2);
      var6.put("front_mid", var3);
      var6.put("rear_mid", var4);
      var6.put("rear", var5);
      String var7 = var6.toString();
      if (com.hzbhd.util.LogUtil.log5()) {
         com.hzbhd.util.LogUtil.d("<sendRadarShareInfo> " + var0 + ": " + var7);
      }

      if ("canbus.radarLeft".equals(var0)) {
         mRadarLeftShareInfo = var7;
      } else {
         mRadarRightShareInfo = var7;
      }

      ShareDataServiceImpl.setString(var0, var7);
   }

   private static void setFordImageResourceByName(Constant.RadarLocation var0, int var1, int var2) {
      mLocationMap.put(var0, new int[]{var1, var2});
   }

   public static void setFrontRadarDistanceData(int var0, int var1, int var2, int var3) {
      mDistanceMap.put(Constant.RadarLocation.FRONT_LEFT, var0);
      mDistanceMap.put(Constant.RadarLocation.FRONT_MID_LEFT, var1);
      mDistanceMap.put(Constant.RadarLocation.FRONT_MID_RIGHT, var2);
      mDistanceMap.put(Constant.RadarLocation.FRONT_RIGHT, var3);
   }

   public static void setFrontRadarLocationData(int var0, int var1, int var2, int var3, int var4) {
      setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, var2, var0);
      setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, var3, var0);
      setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, var4, var0);

      try {
         sendRadarShareInfoFR("canbus.RadarFront", var0, var1, var2, var3, var4);
      } catch (JSONException var6) {
         var6.printStackTrace();
      }

      if (GeneralParkData.pKeyRadarState) {
         BaseUtil.INSTANCE.runUi(new Function0(var0, var1, var2, var3, var4) {
            final int val$leftData;
            final int val$leftMidData;
            final int val$radarRange;
            final int val$rightData;
            final int val$rightMidData;

            {
               this.val$radarRange = var1;
               this.val$leftData = var2;
               this.val$leftMidData = var3;
               this.val$rightMidData = var4;
               this.val$rightData = var5;
            }

            public Unit invoke() {
               PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).refreshFrontRadar(this.val$radarRange, this.val$leftData, this.val$leftMidData, this.val$rightMidData, this.val$rightData);
               return null;
            }
         });
      }

   }

   public static void setFrontRadarLocationDataType2(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, var3, var2);
      setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, var5, var4);
      setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, var7, var6);
   }

   public static void setGeneralRadarData(Constant.RadarLocation var0, int var1, int var2) {
      int var6 = mRadarData[var0.ordinal()];
      byte var3 = (byte)var1;
      if (var6 != var3) {
         mRadarData[var0.ordinal()] = var3;
         var6 = var1 & 255;
         if (var6 != mDisableData && var6 != mDisableData2) {
            double var4;
            if (mMinIsClose) {
               var4 = Math.ceil((double)((((float)var1 - 1.0F) * 10.0F / (float)var2 + 1.0F) * 1.0F));
            } else {
               var4 = Math.ceil((double)((float)(10 - var1 * 10 / var2 + 1)));
            }

            var6 = (int)var4;
            var1 = var6;
            if (var2 < 10) {
               var1 = var6;
               if (var6 >= 9) {
                  var1 = var6;
                  if (var2 != 7) {
                     var1 = 10;
                  }
               }
            }

            setFordImageResourceByName(var0, (byte)DataHandleUtils.rangeNumber(var1, 10), 10);
         } else {
            setFordImageResourceByName(var0, 0, 10);
         }
      }

   }

   public static void setLeftRadarDistanceData(int var0, int var1, int var2, int var3) {
      mDistanceMap.put(Constant.RadarLocation.LEFT_FRONT, var0);
      mDistanceMap.put(Constant.RadarLocation.LEFT_MID_FRONT, var1);
      mDistanceMap.put(Constant.RadarLocation.LEFT_MID_REAR, var2);
      mDistanceMap.put(Constant.RadarLocation.LEFT_REAR, var3);
   }

   public static void setLeftRadarLocationData(int var0, int var1, int var2, int var3, int var4) {
      setGeneralRadarData(Constant.RadarLocation.LEFT_FRONT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.LEFT_MID_FRONT, var2, var0);
      setGeneralRadarData(Constant.RadarLocation.LEFT_MID_REAR, var3, var0);
      setGeneralRadarData(Constant.RadarLocation.LEFT_REAR, var4, var0);

      try {
         sendRadarShareInfoLR("canbus.radarLeft", var0, var1, var2, var3, var4);
      } catch (JSONException var6) {
         var6.printStackTrace();
      }

   }

   public static void setLeftRadarLocationDataType2(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      setGeneralRadarData(Constant.RadarLocation.LEFT_FRONT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.LEFT_MID_FRONT, var3, var2);
      setGeneralRadarData(Constant.RadarLocation.LEFT_MID_REAR, var5, var4);
      setGeneralRadarData(Constant.RadarLocation.LEFT_REAR, var7, var6);
   }

   public static void setProbeRadarLocationData(int var0, int var1, int var2, int var3, int var4) {
      setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT_PROBE, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT_PROBE, var2, var0);
      setGeneralRadarData(Constant.RadarLocation.REAR_LEFT_PROBE, var3, var0);
      setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT_PROBE, var4, var0);
   }

   public static void setRearRadarDistanceData(int var0, int var1, int var2, int var3) {
      mDistanceMap.put(Constant.RadarLocation.REAR_LEFT, var0);
      mDistanceMap.put(Constant.RadarLocation.REAR_MID_LEFT, var1);
      mDistanceMap.put(Constant.RadarLocation.REAR_MID_RIGHT, var2);
      mDistanceMap.put(Constant.RadarLocation.REAR_RIGHT, var3);
   }

   public static void setRearRadarLocationData(int var0, int var1, int var2, int var3, int var4) {
      setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, var2, var0);
      setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, var3, var0);
      setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, var4, var0);

      try {
         sendRadarShareInfoFR("canbus.RadarRear", var0, var1, var2, var3, var4);
      } catch (JSONException var6) {
         var6.printStackTrace();
      }

      if (GeneralParkData.pKeyRadarState) {
         BaseUtil.INSTANCE.runUi(new Function0(var0, var1, var2, var3, var4) {
            final int val$leftData;
            final int val$leftMidData;
            final int val$radarRange;
            final int val$rightData;
            final int val$rightMidData;

            {
               this.val$radarRange = var1;
               this.val$leftData = var2;
               this.val$leftMidData = var3;
               this.val$rightMidData = var4;
               this.val$rightData = var5;
            }

            public Unit invoke() {
               PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).refreshRearRadar(this.val$radarRange, this.val$leftData, this.val$leftMidData, this.val$rightMidData, this.val$rightData);
               return null;
            }
         });
      }

   }

   public static void setRearRadarLocationDataType2(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, var3, var2);
      setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, var5, var4);
      setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, var7, var6);
   }

   public static void setRightRadarDistanceData(int var0, int var1, int var2, int var3) {
      mDistanceMap.put(Constant.RadarLocation.RIGHT_FRONT, var0);
      mDistanceMap.put(Constant.RadarLocation.RIGHT_MID_FRONT, var1);
      mDistanceMap.put(Constant.RadarLocation.RIGHT_MID_REAR, var2);
      mDistanceMap.put(Constant.RadarLocation.RIGHT_REAR, var3);
   }

   public static void setRightRadarLocationData(int var0, int var1, int var2, int var3, int var4) {
      setGeneralRadarData(Constant.RadarLocation.RIGHT_FRONT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, var2, var0);
      setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_REAR, var3, var0);
      setGeneralRadarData(Constant.RadarLocation.RIGHT_REAR, var4, var0);

      try {
         sendRadarShareInfoLR("canbus.radarRight", var0, var1, var2, var3, var4);
      } catch (JSONException var6) {
         var6.printStackTrace();
      }

   }

   public static void setRightRadarLocationDataType2(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      setGeneralRadarData(Constant.RadarLocation.RIGHT_FRONT, var1, var0);
      setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, var3, var2);
      setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_REAR, var5, var4);
      setGeneralRadarData(Constant.RadarLocation.RIGHT_REAR, var7, var6);
   }
}
