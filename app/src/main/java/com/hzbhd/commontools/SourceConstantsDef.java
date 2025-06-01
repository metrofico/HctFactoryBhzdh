package com.hzbhd.commontools;

public class SourceConstantsDef {
   public static final int NOT_DUCK = 128;

   public static int getNaviStream(int var0) {
      return var0 & -129;
   }

   public static boolean isNotDuckNavi(int var0) {
      boolean var1;
      if ((var0 & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static int setNaviStream(int var0, boolean var1) {
      int var2 = var0;
      if (var1) {
         var2 = var0 | 128;
      }

      return var2;
   }

   public static enum CAMERA_STATE {
      private static final CAMERA_STATE[] $VALUES;
      CLOSED,
      CLOSING,
      DISCONNECTED,
      ERROR,
      OPENED,
      OPENING,
      OPEN_REQUEST;

      static {
         CAMERA_STATE var3 = new CAMERA_STATE("CLOSED", 0);
         CLOSED = var3;
         CAMERA_STATE var4 = new CAMERA_STATE("OPEN_REQUEST", 1);
         OPEN_REQUEST = var4;
         CAMERA_STATE var2 = new CAMERA_STATE("OPENING", 2);
         OPENING = var2;
         CAMERA_STATE var5 = new CAMERA_STATE("OPENED", 3);
         OPENED = var5;
         CAMERA_STATE var0 = new CAMERA_STATE("CLOSING", 4);
         CLOSING = var0;
         CAMERA_STATE var1 = new CAMERA_STATE("DISCONNECTED", 5);
         DISCONNECTED = var1;
         CAMERA_STATE var6 = new CAMERA_STATE("ERROR", 6);
         ERROR = var6;
         $VALUES = new CAMERA_STATE[]{var3, var4, var2, var5, var0, var1, var6};
      }
   }

   public static enum DISP_ID {
      private static final DISP_ID[] $VALUES;
      disp_ext1,
      disp_ext2,
      disp_main;

      static {
         DISP_ID var1 = new DISP_ID("disp_main", 0);
         disp_main = var1;
         DISP_ID var0 = new DISP_ID("disp_ext1", 1);
         disp_ext1 = var0;
         DISP_ID var2 = new DISP_ID("disp_ext2", 2);
         disp_ext2 = var2;
         $VALUES = new DISP_ID[]{var1, var0, var2};
      }
   }

   public static enum MODULE_ID {
      private static final MODULE_ID[] $VALUES;
      ANDROIDAUTO,
      AUDIO,
      AVM,
      BHD_LYRA,
      BHD_MS_TXZ,
      BLUETOOTH,
      BTMUSIC,
      BTPHONE,
      CAMERA,
      CANBUS,
      CARPLAY,
      DAB,
      DAEMON,
      DISC,
      DVR,
      FRAMEWORK,
      GYROSCOPE,
      HARDWARE,
      INIT,
      KEY_DISPATCHER,
      LCD,
      LOG,
      MCU,
      MEDIA,
      MEDIA_SCANNER,
      MIDWARE,
      MISC,
      MUSIC,
      NAVI,
      RADIO,
      REVERSE_DETECT,
      SETTING,
      SETTINGAPP,
      SHARE_DATA,
      SOURCE_MANAGER,
      SPEECH,
      SXM,
      SYSTEMUI,
      UPGRADE,
      VIDEO;

      private int mValue;

      static {
         MODULE_ID var8 = new MODULE_ID("DAEMON", 0, 0);
         DAEMON = var8;
         MODULE_ID var13 = new MODULE_ID("MIDWARE", 1, 1);
         MIDWARE = var13;
         MODULE_ID var14 = new MODULE_ID("SETTING", 2, 2);
         SETTING = var14;
         MODULE_ID var26 = new MODULE_ID("SHARE_DATA", 3, 3);
         SHARE_DATA = var26;
         MODULE_ID var20 = new MODULE_ID("KEY_DISPATCHER", 4, 4);
         KEY_DISPATCHER = var20;
         MODULE_ID var35 = new MODULE_ID("SOURCE_MANAGER", 5, 5);
         SOURCE_MANAGER = var35;
         MODULE_ID var37 = new MODULE_ID("HARDWARE", 6, 6);
         HARDWARE = var37;
         MODULE_ID var32 = new MODULE_ID("FRAMEWORK", 7, 7);
         FRAMEWORK = var32;
         MODULE_ID var28 = new MODULE_ID("MCU", 8, 8);
         MCU = var28;
         MODULE_ID var33 = new MODULE_ID("AUDIO", 9, 9);
         AUDIO = var33;
         MODULE_ID var3 = new MODULE_ID("CAMERA", 10, 10);
         CAMERA = var3;
         MODULE_ID var5 = new MODULE_ID("MEDIA_SCANNER", 11, 11);
         MEDIA_SCANNER = var5;
         MODULE_ID var29 = new MODULE_ID("MEDIA", 12, 12);
         MEDIA = var29;
         MODULE_ID var27 = new MODULE_ID("BLUETOOTH", 13, 13);
         BLUETOOTH = var27;
         MODULE_ID var7 = new MODULE_ID("RADIO", 14, 14);
         RADIO = var7;
         MODULE_ID var12 = new MODULE_ID("NAVI", 15, 15);
         NAVI = var12;
         MODULE_ID var34 = new MODULE_ID("MISC", 16, 16);
         MISC = var34;
         MODULE_ID var15 = new MODULE_ID("CANBUS", 17, 17);
         CANBUS = var15;
         MODULE_ID var9 = new MODULE_ID("AVM", 18, 18);
         AVM = var9;
         MODULE_ID var22 = new MODULE_ID("SYSTEMUI", 19, 19);
         SYSTEMUI = var22;
         MODULE_ID var19 = new MODULE_ID("CARPLAY", 20, 20);
         CARPLAY = var19;
         MODULE_ID var38 = new MODULE_ID("ANDROIDAUTO", 21, 21);
         ANDROIDAUTO = var38;
         MODULE_ID var24 = new MODULE_ID("SETTINGAPP", 22, 22);
         SETTINGAPP = var24;
         MODULE_ID var0 = new MODULE_ID("LOG", 23, 23);
         LOG = var0;
         MODULE_ID var18 = new MODULE_ID("BTMUSIC", 24, 24);
         BTMUSIC = var18;
         MODULE_ID var2 = new MODULE_ID("VIDEO", 25, 25);
         VIDEO = var2;
         MODULE_ID var16 = new MODULE_ID("MUSIC", 26, 26);
         MUSIC = var16;
         MODULE_ID var4 = new MODULE_ID("BTPHONE", 27, 27);
         BTPHONE = var4;
         MODULE_ID var30 = new MODULE_ID("SPEECH", 28, 28);
         SPEECH = var30;
         MODULE_ID var23 = new MODULE_ID("INIT", 29, 29);
         INIT = var23;
         MODULE_ID var36 = new MODULE_ID("REVERSE_DETECT", 30, 30);
         REVERSE_DETECT = var36;
         MODULE_ID var6 = new MODULE_ID("DAB", 31, 31);
         DAB = var6;
         MODULE_ID var31 = new MODULE_ID("DISC", 32, 32);
         DISC = var31;
         MODULE_ID var39 = new MODULE_ID("BHD_LYRA", 33, 34);
         BHD_LYRA = var39;
         MODULE_ID var25 = new MODULE_ID("BHD_MS_TXZ", 34, 35);
         BHD_MS_TXZ = var25;
         MODULE_ID var1 = new MODULE_ID("LCD", 35, 36);
         LCD = var1;
         MODULE_ID var10 = new MODULE_ID("DVR", 36, 37);
         DVR = var10;
         MODULE_ID var11 = new MODULE_ID("GYROSCOPE", 37, 38);
         GYROSCOPE = var11;
         MODULE_ID var21 = new MODULE_ID("UPGRADE", 38, 39);
         UPGRADE = var21;
         MODULE_ID var17 = new MODULE_ID("SXM", 39, 40);
         SXM = var17;
         $VALUES = new MODULE_ID[]{var8, var13, var14, var26, var20, var35, var37, var32, var28, var33, var3, var5, var29, var27, var7, var12, var34, var15, var9, var22, var19, var38, var24, var0, var18, var2, var16, var4, var30, var23, var36, var6, var31, var39, var25, var1, var10, var11, var21, var17};
      }

      private MODULE_ID(int var3) {
         this.mValue = var3;
      }

      public static MODULE_ID getType(int var0) {
         MODULE_ID[] var4 = values();
         int var2 = var4.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            MODULE_ID var3 = var4[var1];
            if (var3.getValue() == var0) {
               return var3;
            }
         }

         return null;
      }

      public int getValue() {
         return this.mValue;
      }
   }

   public static enum NAVI_START_MODE {
      private static final NAVI_START_MODE[] $VALUES;
      ALLWAYS_START,
      LAST_BACKGROUND,
      LAST_FOREGROUND,
      NO_START;

      static {
         NAVI_START_MODE var3 = new NAVI_START_MODE("NO_START", 0);
         NO_START = var3;
         NAVI_START_MODE var2 = new NAVI_START_MODE("LAST_FOREGROUND", 1);
         LAST_FOREGROUND = var2;
         NAVI_START_MODE var1 = new NAVI_START_MODE("LAST_BACKGROUND", 2);
         LAST_BACKGROUND = var1;
         NAVI_START_MODE var0 = new NAVI_START_MODE("ALLWAYS_START", 3);
         ALLWAYS_START = var0;
         $VALUES = new NAVI_START_MODE[]{var3, var2, var1, var0};
      }
   }

   public static enum SOURCE_ACTION {
      private static final SOURCE_ACTION[] $VALUES;
      PAUSE,
      PLAY,
      STOP;

      static {
         SOURCE_ACTION var0 = new SOURCE_ACTION("PLAY", 0);
         PLAY = var0;
         SOURCE_ACTION var1 = new SOURCE_ACTION("PAUSE", 1);
         PAUSE = var1;
         SOURCE_ACTION var2 = new SOURCE_ACTION("STOP", 2);
         STOP = var2;
         $VALUES = new SOURCE_ACTION[]{var0, var1, var2};
      }
   }

   public static enum SOURCE_ID {
      private static final SOURCE_ID[] $VALUES;
      AC,
      AM,
      ANDROIDAUTO,
      ANDROIDAUTONAVI,
      ANDROIDAUTOPHONE,
      ANDROIDPLAYER,
      APP,
      ATV,
      AUDIOPREVIEW,
      AUTOTEST,
      AUX,
      AUX1,
      AUX2,
      AVM,
      AVOFF,
      BACKCAMERA,
      BCM,
      BLACKOUT,
      BLUETOOTH,
      BTAUDIO,
      BTCONNECTION,
      BTPHONE,
      BTSETTING,
      BTUIAUDIO,
      BTUIPHONE,
      CAM,
      CAN,
      CANOFF,
      CARLIFE,
      CARLINK,
      CARPLAY,
      CARPLAYMUSIC,
      CARPLAYPHONE,
      CARPLAYRING,
      CARPLAYTTS,
      CDC,
      CLOCK,
      CLOSESOURCE,
      CMMB,
      CONTROLCENTER,
      CarMonitor,
      CarSettings,
      DAB,
      DABTA,
      DABTOFM,
      DAEMON,
      DTV,
      DVBT,
      DVR,
      ECAR,
      EC_ANDROID,
      EC_IPHONE,
      ECall,
      EQ,
      FIBERIN,
      FM,
      FRONTCAMERA,
      G3PHONE,
      HDMI,
      HVAC,
      ICall,
      IMODE,
      IPODUI,
      ISDB,
      KEYBEEP,
      KEYDISPATCHER,
      KUWO,
      LAUNCHER,
      LINKAUDIO,
      LINKBTPHONE,
      LINKBTUI,
      LINKNAVI,
      LINKRECORD,
      LINKTTS,
      LINKUI,
      MCU,
      MEDIA,
      MEDIASCANNER,
      MPEG,
      MPEGTEST,
      MPU,
      MUSIC,
      MUSICUI,
      NAVIAUDIO,
      NAVISource,
      NORMAL_SOURCE,
      NULL,
      ONLINE_MUSIC,
      ONLINE_MUSIC_UI,
      ONLINE_RADIO,
      ONLINE_VIDEO,
      ORIGAUX,
      ORIGPHONE,
      ORIGUIPHONE,
      ORIGUSB,
      PHOTO,
      PTT,
      ProjectAdapter,
      QRCODE,
      RADIO,
      RADIOUI,
      RDS,
      RDVR,
      REAR,
      REARUSB,
      SD,
      SECURITY,
      SETTINGS,
      SHAREINFO,
      SLEEP,
      SOURCEMANAGER,
      SWC,
      SXM,
      SYSTEMCOMMMON,
      THIRD,
      TPMS,
      TTS,
      UICARPLAY,
      UIMUSIC,
      UITPMS,
      UIVIDEO,
      UPGRADE,
      UPGRADEUI,
      USB,
      USB1,
      USB2,
      USB3,
      USB4,
      USB5,
      VIDEO,
      VIDEOUI,
      VOICE,
      XIMALAYA;

      private int mValue;

      static {
         SOURCE_ID var74 = new SOURCE_ID("NULL", 0, 0);
         NULL = var74;
         SOURCE_ID var131 = new SOURCE_ID("SD", 1, 1);
         SD = var131;
         SOURCE_ID var86 = new SOURCE_ID("USB", 2, 2);
         USB = var86;
         SOURCE_ID var51 = new SOURCE_ID("USB1", 3, 3);
         USB1 = var51;
         SOURCE_ID var71 = new SOURCE_ID("USB2", 4, 4);
         USB2 = var71;
         SOURCE_ID var0 = new SOURCE_ID("USB3", 5, 5);
         USB3 = var0;
         SOURCE_ID var122 = new SOURCE_ID("USB4", 6, 6);
         USB4 = var122;
         SOURCE_ID var62 = new SOURCE_ID("USB5", 7, 7);
         USB5 = var62;
         SOURCE_ID var41 = new SOURCE_ID("MPEG", 8, 8);
         MPEG = var41;
         SOURCE_ID var49 = new SOURCE_ID("CDC", 9, 9);
         CDC = var49;
         SOURCE_ID var124 = new SOURCE_ID("AUX", 10, 10);
         AUX = var124;
         SOURCE_ID var127 = new SOURCE_ID("AUX1", 11, 11);
         AUX1 = var127;
         SOURCE_ID var91 = new SOURCE_ID("AUX2", 12, 12);
         AUX2 = var91;
         SOURCE_ID var93 = new SOURCE_ID("CARLINK", 13, 13);
         CARLINK = var93;
         SOURCE_ID var11 = new SOURCE_ID("VOICE", 14, 14);
         VOICE = var11;
         SOURCE_ID var128 = new SOURCE_ID("NAVIAUDIO", 15, 15);
         NAVIAUDIO = var128;
         SOURCE_ID var54 = new SOURCE_ID("APP", 16, 16);
         APP = var54;
         SOURCE_ID var129 = new SOURCE_ID("TTS", 17, 17);
         TTS = var129;
         SOURCE_ID var80 = new SOURCE_ID("FM", 18, 18);
         FM = var80;
         SOURCE_ID var95 = new SOURCE_ID("TPMS", 19, 19);
         TPMS = var95;
         SOURCE_ID var98 = new SOURCE_ID("UITPMS", 20, 20);
         UITPMS = var98;
         SOURCE_ID var61 = new SOURCE_ID("BTPHONE", 21, 21);
         BTPHONE = var61;
         SOURCE_ID var27 = new SOURCE_ID("BTAUDIO", 22, 22);
         BTAUDIO = var27;
         SOURCE_ID var92 = new SOURCE_ID("BTUIPHONE", 23, 23);
         BTUIPHONE = var92;
         SOURCE_ID var110 = new SOURCE_ID("BTUIAUDIO", 24, 24);
         BTUIAUDIO = var110;
         SOURCE_ID var111 = new SOURCE_ID("BTCONNECTION", 25, 25);
         BTCONNECTION = var111;
         SOURCE_ID var130 = new SOURCE_ID("IMODE", 26, 26);
         IMODE = var130;
         SOURCE_ID var15 = new SOURCE_ID("CMMB", 27, 27);
         CMMB = var15;
         SOURCE_ID var42 = new SOURCE_ID("G3PHONE", 28, 28);
         G3PHONE = var42;
         SOURCE_ID var22 = new SOURCE_ID("BACKCAMERA", 29, 29);
         BACKCAMERA = var22;
         SOURCE_ID var21 = new SOURCE_ID("FRONTCAMERA", 30, 30);
         FRONTCAMERA = var21;
         SOURCE_ID var96 = new SOURCE_ID("DVR", 31, 31);
         DVR = var96;
         SOURCE_ID var114 = new SOURCE_ID("RDVR", 32, 32);
         RDVR = var114;
         SOURCE_ID var78 = new SOURCE_ID("DVBT", 33, 33);
         DVBT = var78;
         SOURCE_ID var65 = new SOURCE_ID("ISDB", 34, 34);
         ISDB = var65;
         SOURCE_ID var10 = new SOURCE_ID("ATV", 35, 35);
         ATV = var10;
         SOURCE_ID var103 = new SOURCE_ID("EQ", 36, 36);
         EQ = var103;
         SOURCE_ID var24 = new SOURCE_ID("SETTINGS", 37, 37);
         SETTINGS = var24;
         SOURCE_ID var68 = new SOURCE_ID("UPGRADE", 38, 38);
         UPGRADE = var68;
         SOURCE_ID var57 = new SOURCE_ID("DAB", 39, 39);
         DAB = var57;
         SOURCE_ID var72 = new SOURCE_ID("DABTA", 40, 40);
         DABTA = var72;
         SOURCE_ID var90 = new SOURCE_ID("SWC", 41, 41);
         SWC = var90;
         SOURCE_ID var32 = new SOURCE_ID("REAR", 42, 42);
         REAR = var32;
         SOURCE_ID var125 = new SOURCE_ID("MUSIC", 43, 43);
         MUSIC = var125;
         SOURCE_ID var7 = new SOURCE_ID("VIDEO", 44, 44);
         VIDEO = var7;
         SOURCE_ID var28 = new SOURCE_ID("PHOTO", 45, 45);
         PHOTO = var28;
         SOURCE_ID var121 = new SOURCE_ID("HVAC", 46, 46);
         HVAC = var121;
         SOURCE_ID var18 = new SOURCE_ID("SXM", 47, 47);
         SXM = var18;
         SOURCE_ID var33 = new SOURCE_ID("CLOSESOURCE", 48, 48);
         CLOSESOURCE = var33;
         SOURCE_ID var50 = new SOURCE_ID("BLACKOUT", 49, 49);
         BLACKOUT = var50;
         SOURCE_ID var12 = new SOURCE_ID("AUDIOPREVIEW", 50, 50);
         AUDIOPREVIEW = var12;
         SOURCE_ID var82 = new SOURCE_ID("RDS", 51, 51);
         RDS = var82;
         SOURCE_ID var104 = new SOURCE_ID("AVOFF", 52, 52);
         AVOFF = var104;
         SOURCE_ID var37 = new SOURCE_ID("REARUSB", 53, 53);
         REARUSB = var37;
         SOURCE_ID var101 = new SOURCE_ID("ANDROIDPLAYER", 54, 54);
         ANDROIDPLAYER = var101;
         SOURCE_ID var64 = new SOURCE_ID("SLEEP", 55, 55);
         SLEEP = var64;
         SOURCE_ID var119 = new SOURCE_ID("MPEGTEST", 56, 56);
         MPEGTEST = var119;
         SOURCE_ID var63 = new SOURCE_ID("PTT", 57, 57);
         PTT = var63;
         SOURCE_ID var115 = new SOURCE_ID("QRCODE", 58, 58);
         QRCODE = var115;
         SOURCE_ID var8 = new SOURCE_ID("AC", 59, 59);
         AC = var8;
         SOURCE_ID var117 = new SOURCE_ID("CARPLAY", 60, 60);
         CARPLAY = var117;
         SOURCE_ID var77 = new SOURCE_ID("CARPLAYMUSIC", 61, 61);
         CARPLAYMUSIC = var77;
         SOURCE_ID var108 = new SOURCE_ID("UICARPLAY", 62, 62);
         UICARPLAY = var108;
         SOURCE_ID var118 = new SOURCE_ID("HDMI", 63, 63);
         HDMI = var118;
         SOURCE_ID var76 = new SOURCE_ID("CARPLAYPHONE", 64, 64);
         CARPLAYPHONE = var76;
         SOURCE_ID var84 = new SOURCE_ID("CARLIFE", 65, 65);
         CARLIFE = var84;
         SOURCE_ID var25 = new SOURCE_ID("ORIGAUX", 66, 66);
         ORIGAUX = var25;
         SOURCE_ID var112 = new SOURCE_ID("ORIGUSB", 67, 67);
         ORIGUSB = var112;
         SOURCE_ID var26 = new SOURCE_ID("ORIGPHONE", 68, 68);
         ORIGPHONE = var26;
         SOURCE_ID var69 = new SOURCE_ID("ORIGUIPHONE", 69, 69);
         ORIGUIPHONE = var69;
         SOURCE_ID var66 = new SOURCE_ID("FIBERIN", 70, 70);
         FIBERIN = var66;
         SOURCE_ID var40 = new SOURCE_ID("AVM", 71, 71);
         AVM = var40;
         SOURCE_ID var102 = new SOURCE_ID("MPU", 72, 72);
         MPU = var102;
         SOURCE_ID var73 = new SOURCE_ID("SECURITY", 73, 73);
         SECURITY = var73;
         SOURCE_ID var13 = new SOURCE_ID("ECall", 74, 74);
         ECall = var13;
         SOURCE_ID var3 = new SOURCE_ID("ICall", 75, 75);
         ICall = var3;
         SOURCE_ID var107 = new SOURCE_ID("ECAR", 76, 76);
         ECAR = var107;
         SOURCE_ID var116 = new SOURCE_ID("ONLINE_MUSIC_UI", 77, 77);
         ONLINE_MUSIC_UI = var116;
         SOURCE_ID var83 = new SOURCE_ID("ONLINE_MUSIC", 78, 78);
         ONLINE_MUSIC = var83;
         SOURCE_ID var5 = new SOURCE_ID("LINKUI", 79, 79);
         LINKUI = var5;
         SOURCE_ID var30 = new SOURCE_ID("LINKNAVI", 80, 80);
         LINKNAVI = var30;
         SOURCE_ID var39 = new SOURCE_ID("LINKTTS", 81, 81);
         LINKTTS = var39;
         SOURCE_ID var56 = new SOURCE_ID("LINKAUDIO", 82, 82);
         LINKAUDIO = var56;
         SOURCE_ID var126 = new SOURCE_ID("LINKBTUI", 83, 83);
         LINKBTUI = var126;
         SOURCE_ID var44 = new SOURCE_ID("LINKBTPHONE", 84, 84);
         LINKBTPHONE = var44;
         SOURCE_ID var81 = new SOURCE_ID("NAVISource", 85, 85);
         NAVISource = var81;
         SOURCE_ID var105 = new SOURCE_ID("UIMUSIC", 86, 86);
         UIMUSIC = var105;
         SOURCE_ID var29 = new SOURCE_ID("UIVIDEO", 87, 87);
         UIVIDEO = var29;
         SOURCE_ID var99 = new SOURCE_ID("UPGRADEUI", 88, 88);
         UPGRADEUI = var99;
         SOURCE_ID var16 = new SOURCE_ID("CAN", 89, 89);
         CAN = var16;
         SOURCE_ID var89 = new SOURCE_ID("MEDIA", 90, 90);
         MEDIA = var89;
         SOURCE_ID var47 = new SOURCE_ID("AUTOTEST", 91, 91);
         AUTOTEST = var47;
         SOURCE_ID var31 = new SOURCE_ID("BTSETTING", 92, 92);
         BTSETTING = var31;
         SOURCE_ID var97 = new SOURCE_ID("ONLINE_RADIO", 93, 93);
         ONLINE_RADIO = var97;
         SOURCE_ID var88 = new SOURCE_ID("ONLINE_VIDEO", 94, 94);
         ONLINE_VIDEO = var88;
         SOURCE_ID var14 = new SOURCE_ID("RADIO", 95, 95);
         RADIO = var14;
         SOURCE_ID var45 = new SOURCE_ID("AM", 96, 96);
         AM = var45;
         SOURCE_ID var23 = new SOURCE_ID("CLOCK", 97, 97);
         CLOCK = var23;
         SOURCE_ID var123 = new SOURCE_ID("LAUNCHER", 98, 98);
         LAUNCHER = var123;
         SOURCE_ID var55 = new SOURCE_ID("THIRD", 99, 99);
         THIRD = var55;
         SOURCE_ID var9 = new SOURCE_ID("DAEMON", 100, 100);
         DAEMON = var9;
         SOURCE_ID var58 = new SOURCE_ID("CONTROLCENTER", 101, 101);
         CONTROLCENTER = var58;
         SOURCE_ID var1 = new SOURCE_ID("SHAREINFO", 102, 102);
         SHAREINFO = var1;
         SOURCE_ID var43 = new SOURCE_ID("MEDIASCANNER", 103, 103);
         MEDIASCANNER = var43;
         SOURCE_ID var34 = new SOURCE_ID("MCU", 104, 104);
         MCU = var34;
         SOURCE_ID var52 = new SOURCE_ID("KEYDISPATCHER", 105, 105);
         KEYDISPATCHER = var52;
         SOURCE_ID var59 = new SOURCE_ID("BLUETOOTH", 106, 106);
         BLUETOOTH = var59;
         SOURCE_ID var46 = new SOURCE_ID("CAM", 107, 107);
         CAM = var46;
         SOURCE_ID var79 = new SOURCE_ID("SOURCEMANAGER", 108, 108);
         SOURCEMANAGER = var79;
         SOURCE_ID var132 = new SOURCE_ID("SYSTEMCOMMMON", 109, 109);
         SYSTEMCOMMMON = var132;
         SOURCE_ID var60 = new SOURCE_ID("BCM", 110, 110);
         BCM = var60;
         SOURCE_ID var100 = new SOURCE_ID("CarSettings", 111, 111);
         CarSettings = var100;
         SOURCE_ID var17 = new SOURCE_ID("CarMonitor", 112, 112);
         CarMonitor = var17;
         SOURCE_ID var94 = new SOURCE_ID("ProjectAdapter", 113, 113);
         ProjectAdapter = var94;
         SOURCE_ID var87 = new SOURCE_ID("ANDROIDAUTONAVI", 114, 114);
         ANDROIDAUTONAVI = var87;
         SOURCE_ID var36 = new SOURCE_ID("ANDROIDAUTOPHONE", 115, 115);
         ANDROIDAUTOPHONE = var36;
         SOURCE_ID var120 = new SOURCE_ID("ANDROIDAUTO", 116, 116);
         ANDROIDAUTO = var120;
         SOURCE_ID var67 = new SOURCE_ID("KEYBEEP", 117, 117);
         KEYBEEP = var67;
         SOURCE_ID var38 = new SOURCE_ID("LINKRECORD", 118, 118);
         LINKRECORD = var38;
         SOURCE_ID var4 = new SOURCE_ID("MUSICUI", 119, 119);
         MUSICUI = var4;
         SOURCE_ID var35 = new SOURCE_ID("VIDEOUI", 120, 120);
         VIDEOUI = var35;
         SOURCE_ID var109 = new SOURCE_ID("RADIOUI", 121, 121);
         RADIOUI = var109;
         SOURCE_ID var19 = new SOURCE_ID("IPODUI", 122, 122);
         IPODUI = var19;
         SOURCE_ID var113 = new SOURCE_ID("DTV", 123, 123);
         DTV = var113;
         SOURCE_ID var6 = new SOURCE_ID("NORMAL_SOURCE", 124, 124);
         NORMAL_SOURCE = var6;
         SOURCE_ID var2 = new SOURCE_ID("EC_ANDROID", 125, 125);
         EC_ANDROID = var2;
         SOURCE_ID var70 = new SOURCE_ID("EC_IPHONE", 126, 126);
         EC_IPHONE = var70;
         SOURCE_ID var20 = new SOURCE_ID("CANOFF", 127, 127);
         CANOFF = var20;
         SOURCE_ID var48 = new SOURCE_ID("DABTOFM", 128, 128);
         DABTOFM = var48;
         SOURCE_ID var106 = new SOURCE_ID("XIMALAYA", 129, 129);
         XIMALAYA = var106;
         SOURCE_ID var85 = new SOURCE_ID("KUWO", 130, 130);
         KUWO = var85;
         SOURCE_ID var75 = new SOURCE_ID("CARPLAYRING", 131, 131);
         CARPLAYRING = var75;
         SOURCE_ID var53 = new SOURCE_ID("CARPLAYTTS", 132, 132);
         CARPLAYTTS = var53;
         $VALUES = new SOURCE_ID[]{var74, var131, var86, var51, var71, var0, var122, var62, var41, var49, var124, var127, var91, var93, var11, var128, var54, var129, var80, var95, var98, var61, var27, var92, var110, var111, var130, var15, var42, var22, var21, var96, var114, var78, var65, var10, var103, var24, var68, var57, var72, var90, var32, var125, var7, var28, var121, var18, var33, var50, var12, var82, var104, var37, var101, var64, var119, var63, var115, var8, var117, var77, var108, var118, var76, var84, var25, var112, var26, var69, var66, var40, var102, var73, var13, var3, var107, var116, var83, var5, var30, var39, var56, var126, var44, var81, var105, var29, var99, var16, var89, var47, var31, var97, var88, var14, var45, var23, var123, var55, var9, var58, var1, var43, var34, var52, var59, var46, var79, var132, var60, var100, var17, var94, var87, var36, var120, var67, var38, var4, var35, var109, var19, var113, var6, var2, var70, var20, var48, var106, var85, var75, var53};
      }

      private SOURCE_ID(int var3) {
         this.mValue = var3;
      }

      public static SOURCE_ID getType(int var0) {
         SOURCE_ID[] var3 = values();
         int var2 = var3.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            SOURCE_ID var4 = var3[var1];
            if (var4.getValue() == var0) {
               return var4;
            }
         }

         return null;
      }

      public static SOURCE_ID getType(String var0) {
         SOURCE_ID[] var3 = values();
         int var2 = var3.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            SOURCE_ID var4 = var3[var1];
            if (var4.name().equals(var0)) {
               return var4;
            }
         }

         return null;
      }

      public static boolean isNaviSource(int var0) {
         boolean var1;
         if (var0 != NAVIAUDIO.getValue() && var0 != ANDROIDAUTONAVI.getValue()) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      public int getValue() {
         return this.mValue;
      }
   }
}
