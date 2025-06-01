package com.hzbhd.canbus.car_cus._451.data;

public class EquipData {
   public static boolean asl;
   public static int bal;
   public static String band;
   public static int bas;
   public static String btSongNumber;
   public static String btTimeStr;
   public static String cdNumber;
   public static String cdSongNumber;
   public static String cdTimeStr;
   public static int disc1;
   public static int disc2;
   public static int disc3;
   public static int disc4;
   public static int disc5;
   public static int disc6;
   public static int disc_in;
   public static int fad;
   public static String frequency;
   public static boolean isMute;
   public static boolean isShowAslIcon;
   public static boolean isShowBtIcon;
   public static boolean isShowPhoneIcon;
   public static boolean isShowSt;
   public static boolean isShowVolume;
   public static int mid;
   public static Enum mode;
   public static String presetStr;
   public static int rand;
   public static int rpt;
   public static int scan;
   public static int signal;
   public static int tre;
   public static String txtInfo;
   public static String usbFileNumber;
   public static String usbSongNumber;
   public static String usbTimeStr;
   public static int volume;

   static {
      mode = EquipData.MODE.AMPL;
      isShowVolume = false;
      volume = 0;
      isMute = false;
      txtInfo = "";
      bas = -5;
      mid = -3;
      tre = 4;
      fad = -6;
      bal = 7;
      asl = false;
      band = "AM2";
      frequency = "000KHz";
      isShowSt = true;
      presetStr = "预设:";
      isShowAslIcon = true;
      isShowBtIcon = true;
      isShowPhoneIcon = true;
      scan = 2;
      rpt = 2;
      rand = 2;
      signal = 0;
      disc_in = 2;
      disc1 = 2;
      disc2 = 2;
      disc3 = 2;
      disc4 = 2;
      disc5 = 2;
      disc6 = 2;
   }

   public static enum MODE {
      private static final MODE[] $VALUES;
      AMPL,
      AUX,
      BT,
      CD,
      PHONE,
      USB;

      static {
         MODE var5 = new MODE("AMPL", 0);
         AMPL = var5;
         MODE var1 = new MODE("CD", 1);
         CD = var1;
         MODE var2 = new MODE("BT", 2);
         BT = var2;
         MODE var0 = new MODE("USB", 3);
         USB = var0;
         MODE var3 = new MODE("PHONE", 4);
         PHONE = var3;
         MODE var4 = new MODE("AUX", 5);
         AUX = var4;
         $VALUES = new MODE[]{var5, var1, var2, var0, var3, var4};
      }
   }
}
