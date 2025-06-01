package com.hct.factory;

import com.hct.RkSystemProp;

public class Factory_Util {
   public static final String CANBUS_ALLCNT = "canbus.allcnt";
   public static final String CANBUS_INDEX = "canbus.index";
   public static final String CANBUS_ITEM = "canbus.item";
   public static final String CANBUS_LISTEND = "canbus.listend";
   public static final String CANBUS_LISTTYPE = "canbus.listtype";
   public static final boolean DBG = false;
   public static final String[] DevPath = new String[]{"/mnt/sdcard", "/mnt/external_sd", "/mnt/external_sdio", "/mnt/usb_storage", "/mnt/usb_storage2", "/mnt/usb_storage3", "/mnt/usb_storage4"};
   public static final String[] Devicestype = new String[]{"Nand flash", "Gps card", "Sd card", "Usb1", "Usb2", "Usb3", "Usb4"};
   public static final String KEY_CODE = "keyCode";
   public static final String MSG_ACTION_HCTREBOOT = "com.microntek.hctreboot";
   public static final String MSG_MTC_CANBUSLIST_REPORT = "com.hct.canbuslist.report";
   public static final String MSG_MTC_KEY_STUDY_DOWN = "com.hct.key.study";
   public static final String MSG_MTC_SW_STUDY_DOWN = "com.hct.swkey.study";
   public static final String MSG_MTC_TOUCH_REPORT = "com.hct.touch.report";
   public static final String MSG_MTC_TVLIST_REPORT = "com.hct.tvlist.report";
   public static final String TAG = "Factory";
   public static final String TOUCH_KEY_INDEX = "touch.key";
   public static final String TOUCH_POINT = "touch.point";
   public static final String TV_ALLCNT = "tv.allcnt";
   public static final String TV_INDEX = "tv.index";
   public static final String TV_ITEM = "tv.item";
   public static final String[] avintype = new String[]{"YES", "NO"};
   public static final String[] bluetoothtype = new String[]{"NO", "MD725", "WQ_GT", "WQ_BC6", "WQ_BC8", "Parrot_FC6000T", "SD-968", "SD-BC6", "SD-GT936", "BARROT-i145", "SD-916", "WQ_RF210", "SD-816", "FSC-BW124", "SDIO-AUTO", "BARROT-i1107e"};
   public static final int[] canbussecond = new int[]{2131296294, 2131296297, 2131296281, 2131296291, 2131296299, 2131296296, 2131296289, 2131296280, 2131296282, 2131296293, 2131296298, 2131296287, 2131296292, 2131296292, 2131296292, 2131296292, 2131296292};
   public static final String[] carplaytype = new String[]{"NO", "ZLink", "MultiPlay"};
   public static final String[] dtvtype = new String[]{"NO", "TV_01 ISDB-T(Rishta)", "TV_02 DTV-T(Lontac)", "TV_03 ISDB-T(Infospace)", "TV_04 QSD-MT-S77(QSD)", "TV_05 BLH-MN2085E", "TV_06 KX-BOX", "TV_07 DTV-JYT", "TV_08 Car DVB-T", "TV_09 Car DVB-T", "TV_10 TW-CIR DVB-T", "TV_11 DTV-EONON", "TV_12 DTV-ISDBT", "TV_13 CMMB(...)", "TV_14 CDT-6EPN22-SD00"};
   public static final String[] dvdtype = new String[]{"NO", "YES"};
   public static final String[] dvrtype = new String[]{"USB", "CVBS", "USB+CVBS", "NO"};
   public static final String[] easyconnectiontype = new String[]{"NO", "YES"};
   public static final String[] hdmitype = new String[]{"NO", "YES"};
   public static final String[] hicartype = new String[]{"NO", "YES"};
   public static final String[] ipodtype = new String[]{"NO", "YES"};
   public static final String[] packageNameAvin = new String[]{"com.microntek.avin"};
   public static final String[] packageNameBT = new String[]{"com.microntek.bluetooth", "com.microntek.btmusic"};
   public static final String[] packageNameCvbsDVR = new String[]{"com.microntek.dvr"};
   public static final String[] packageNameDVD = new String[]{"com.microntek.dvd"};
   public static final String[] packageNameDVR = new String[]{"com.microntek.travel"};
   public static final String[] packageNameFrontView = new String[]{"com.microntek.frontview"};
   public static final String[] packageNameHdmi = new String[]{"com.microntek.hdmi"};
   public static final String[] packageNameIPOD = new String[]{"com.microntek.ipod"};
   public static final String[] packageNameRGBkey = new String[]{"com.microntek.rgbkey"};
   public static final String[] packageNameRadio = new String[]{"com.microntek.radio"};
   public static final String[] packageNameTV = new String[]{"com.microntek.tv"};
   public static final String[] packageNameTpms = new String[]{"com.microntek.tpms"};
   public static final String[] packageNameWheelStudy = new String[]{"com.hct.wheelstudy"};
   public static final String[] radiotype = new String[]{"YES", "NO"};
   public static final String[] rearpaneltype = new String[]{"NO", "YES"};
   public static int[][] studykeyvaules;
   public static int[][] touchvaules;
   public static final String[] tpmstype = new String[]{"NO", "TPMS_01 HCT(YunTu)", "TPMS_02 KLD", "TPMS_03 HZC", "TPMS_04 HCT", "TPMS_05 HCT(TianCheng)", "TPMS_06 HCT(KaMeiTe)", "TPMS_07 HCT(JiTeXing)"};

   static {
      int[] var10 = new int[]{-1, -1};
      int[] var23 = new int[]{2131034165, 310};
      int[] var18 = new int[]{2131034147, 308};
      int[] var21 = new int[]{2131034172, 309};
      int[] var27 = new int[]{2131034222, 273};
      int[] var34 = new int[]{2131034221, 281};
      int[] var15 = new int[]{2131034158, 295};
      int[] var17 = new int[]{2131034139, 800};
      int[] var31 = new int[]{2131034157, 786};
      int[] var7 = new int[]{2131034223, 787};
      int[] var3 = new int[]{2131034150, 788};
      int[] var20 = new int[]{2131034155, 296};
      int[] var12 = new int[]{2131034198, 297};
      int[] var25 = new int[]{2131034188, 305};
      int[] var24 = new int[]{2131034176, 258};
      int[] var16 = new int[]{2131034149, 304};
      int[] var22 = new int[]{2131034142, 316};
      int[] var11 = new int[]{2131034164, 317};
      int[] var32 = new int[]{2131034153, 332};
      int[] var5 = new int[]{2131034137, 13};
      int[] var1 = new int[]{2131034138, 519};
      int[] var29 = new int[]{2131034210, 384};
      int[] var4 = new int[]{2131034195, 257};
      int[] var28 = new int[]{2131034197, 299};
      int[] var8 = new int[]{2131034189, 300};
      int[] var30 = new int[]{2131034200, 365};
      int[] var6 = new int[]{2131034162, 333};
      int[] var14 = new int[]{2131034205, 278};
      int[] var33 = new int[]{2131034204, 276};
      int[] var0 = new int[]{2131034146, 342};
      int[] var26 = new int[]{2131034160, 303};
      int[] var19 = new int[]{2131034175, 331};
      int[] var9 = new int[]{2131034209, 516};
      int[] var13 = new int[]{2131034207, 321};
      int[] var2 = new int[]{2131034219, 336};
      touchvaules = new int[][]{var10, {2131034196, 2048}, var23, var18, var21, var27, var34, {2131034174, 256}, var15, var17, var31, var7, var3, var20, var12, var25, var24, var16, var22, var11, var32, var5, var1, var29, var4, var28, var8, var30, var6, var14, var33, var0, var26, var19, var9, var13, var2};
      var17 = new int[]{2131034165, 310};
      var23 = new int[]{2131034147, 308};
      var10 = new int[]{2131034222, 273};
      var21 = new int[]{2131034221, 281};
      var30 = new int[]{2131034174, 256};
      var25 = new int[]{2131034158, 295};
      var27 = new int[]{2131034198, 297};
      var1 = new int[]{2131034149, 304};
      var3 = new int[]{2131034153, 332};
      var12 = new int[]{2131034138, 519};
      var15 = new int[]{2131034210, 384};
      var20 = new int[]{2131034213, 267};
      var28 = new int[]{2131034197, 299};
      var18 = new int[]{2131034171, 298};
      var19 = new int[]{2131034206, 277};
      var31 = new int[]{2131034145, 319};
      var32 = new int[]{2131034175, 331};
      var0 = new int[]{2131034160, 303};
      var29 = new int[]{2131034141, 338};
      var6 = new int[]{2131034195, 257};
      var4 = new int[]{2131034166, 337};
      var26 = new int[]{2131034148, 349};
      var5 = new int[]{2131034178, 283};
      var16 = new int[]{2131034180, 284};
      var8 = new int[]{2131034186, 290};
      var11 = new int[]{2131034179, 293};
      var7 = new int[]{2131034192, 345};
      var9 = new int[]{2131034200, 365};
      var2 = new int[]{2131034162, 333};
      var13 = new int[]{2131034205, 278};
      var24 = new int[]{2131034204, 276};
      var14 = new int[]{2131034154, 268};
      var22 = new int[]{2131034140, 794};
      studykeyvaules = new int[][]{{-1, -1}, {2131034196, 2048}, var17, var23, {2131034172, 309}, var10, var21, var30, var25, {2131034139, 800}, {2131034157, 786}, {2131034223, 787}, {2131034150, 788}, {2131034155, 296}, {2131034188, 305}, var27, {2131034176, 258}, var1, {2131034142, 316}, {2131034164, 317}, var3, {2131034137, 13}, var12, var15, var20, var28, {2131034189, 300}, {2131034215, 302}, {2131034216, 301}, var18, {2131034217, 320}, var19, {2131034207, 321}, var31, var32, var0, {2131034143, 306}, {2131034161, 301}, {2131034163, 302}, var29, var6, var4, var26, {2131034177, 292}, var5, var16, {2131034181, 285}, {2131034182, 286}, {2131034183, 287}, {2131034184, 288}, {2131034185, 289}, var8, {2131034187, 291}, var11, var7, {2131034191, 346}, var9, var2, var13, var24, {2131034146, 342}, {2131034209, 516}, {2131034218, 260}, var14, {2131034167, 263}, {2131034201, 265}, {2131034159, 264}, {2131034219, 336}, var22, {2131034151, 795}, {2131034152, 796}};
   }

   public static boolean isSofia() {
      boolean var0 = false;
      if (RkSystemProp.get("ro.product.name", "").equals("sofia3gr_car")) {
         var0 = true;
      }

      return var0;
   }
}
