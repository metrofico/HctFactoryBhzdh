package com.hzbhd.constant.bluetooth;

public class BtConstants {
   public static enum BT_ACTION {
      private static final BT_ACTION[] $VALUES;
      A2DP_NEXT,
      A2DP_PAUSE,
      A2DP_PLAY,
      A2DP_PREV,
      A2DP_REQUEST,
      A2DP_STOP,
      ANSWER,
      AS_KEY,
      AUTOCONN,
      AUTO_ANSWER,
      CONN_A2DP,
      CONN_HFP,
      DISABLE_A2DP,
      DISABLE_BT,
      DISABLE_HFP,
      DISCONN_A2DP,
      DISCONN_HFP,
      ENABLE_A2DP,
      ENABLE_BT,
      ENABLE_HFP,
      HANDUP,
      MIC_CAR,
      MIC_IN,
      MIC_OUT,
      MIC_PHONE,
      MUTE_MIC,
      NOT_AUTOCONN,
      NOT_AUTO_ANSWER,
      PAIR,
      SCAN,
      SET_AA_DEVICE,
      SET_CP_DEVICE,
      STOP_SCAN,
      SYNC_PHONE_BOOK,
      UNMUTE_MIC,
      UNPAIR;

      static {
         BT_ACTION var2 = new BT_ACTION("SCAN", 0);
         SCAN = var2;
         BT_ACTION var24 = new BT_ACTION("STOP_SCAN", 1);
         STOP_SCAN = var24;
         BT_ACTION var34 = new BT_ACTION("A2DP_PLAY", 2);
         A2DP_PLAY = var34;
         BT_ACTION var3 = new BT_ACTION("A2DP_PAUSE", 3);
         A2DP_PAUSE = var3;
         BT_ACTION var27 = new BT_ACTION("A2DP_PREV", 4);
         A2DP_PREV = var27;
         BT_ACTION var18 = new BT_ACTION("A2DP_NEXT", 5);
         A2DP_NEXT = var18;
         BT_ACTION var22 = new BT_ACTION("A2DP_REQUEST", 6);
         A2DP_REQUEST = var22;
         BT_ACTION var14 = new BT_ACTION("A2DP_STOP", 7);
         A2DP_STOP = var14;
         BT_ACTION var15 = new BT_ACTION("ANSWER", 8);
         ANSWER = var15;
         BT_ACTION var9 = new BT_ACTION("HANDUP", 9);
         HANDUP = var9;
         BT_ACTION var19 = new BT_ACTION("AUTOCONN", 10);
         AUTOCONN = var19;
         BT_ACTION var30 = new BT_ACTION("NOT_AUTOCONN", 11);
         NOT_AUTOCONN = var30;
         BT_ACTION var10 = new BT_ACTION("MUTE_MIC", 12);
         MUTE_MIC = var10;
         BT_ACTION var1 = new BT_ACTION("UNMUTE_MIC", 13);
         UNMUTE_MIC = var1;
         BT_ACTION var17 = new BT_ACTION("MIC_OUT", 14);
         MIC_OUT = var17;
         BT_ACTION var21 = new BT_ACTION("MIC_IN", 15);
         MIC_IN = var21;
         BT_ACTION var7 = new BT_ACTION("MIC_CAR", 16);
         MIC_CAR = var7;
         BT_ACTION var35 = new BT_ACTION("MIC_PHONE", 17);
         MIC_PHONE = var35;
         BT_ACTION var20 = new BT_ACTION("SYNC_PHONE_BOOK", 18);
         SYNC_PHONE_BOOK = var20;
         BT_ACTION var25 = new BT_ACTION("CONN_HFP", 19);
         CONN_HFP = var25;
         BT_ACTION var8 = new BT_ACTION("DISCONN_HFP", 20);
         DISCONN_HFP = var8;
         BT_ACTION var16 = new BT_ACTION("CONN_A2DP", 21);
         CONN_A2DP = var16;
         BT_ACTION var4 = new BT_ACTION("DISCONN_A2DP", 22);
         DISCONN_A2DP = var4;
         BT_ACTION var32 = new BT_ACTION("PAIR", 23);
         PAIR = var32;
         BT_ACTION var33 = new BT_ACTION("UNPAIR", 24);
         UNPAIR = var33;
         BT_ACTION var29 = new BT_ACTION("AS_KEY", 25);
         AS_KEY = var29;
         BT_ACTION var26 = new BT_ACTION("ENABLE_BT", 26);
         ENABLE_BT = var26;
         BT_ACTION var28 = new BT_ACTION("DISABLE_BT", 27);
         DISABLE_BT = var28;
         BT_ACTION var12 = new BT_ACTION("ENABLE_A2DP", 28);
         ENABLE_A2DP = var12;
         BT_ACTION var31 = new BT_ACTION("DISABLE_A2DP", 29);
         DISABLE_A2DP = var31;
         BT_ACTION var11 = new BT_ACTION("ENABLE_HFP", 30);
         ENABLE_HFP = var11;
         BT_ACTION var13 = new BT_ACTION("DISABLE_HFP", 31);
         DISABLE_HFP = var13;
         BT_ACTION var23 = new BT_ACTION("SET_AA_DEVICE", 32);
         SET_AA_DEVICE = var23;
         BT_ACTION var0 = new BT_ACTION("SET_CP_DEVICE", 33);
         SET_CP_DEVICE = var0;
         BT_ACTION var5 = new BT_ACTION("AUTO_ANSWER", 34);
         AUTO_ANSWER = var5;
         BT_ACTION var6 = new BT_ACTION("NOT_AUTO_ANSWER", 35);
         NOT_AUTO_ANSWER = var6;
         $VALUES = new BT_ACTION[]{var2, var24, var34, var3, var27, var18, var22, var14, var15, var9, var19, var30, var10, var1, var17, var21, var7, var35, var20, var25, var8, var16, var4, var32, var33, var29, var26, var28, var12, var31, var11, var13, var23, var0, var5, var6};
      }
   }

   public static enum BT_STATUS {
      private static final BT_STATUS[] $VALUES;
      A2DP_CONN,
      A2DP_PLAYING,
      CALLING,
      CONNING,
      DISABLE,
      ENABLE,
      HFP_CONN,
      INCOMING,
      IN_A2DP,
      IN_HFP,
      OUTGOING,
      PAIRING,
      SCANNING,
      SYNC_PB_FINISH,
      SYNC_PB_ING;

      static {
         BT_STATUS var14 = new BT_STATUS("ENABLE", 0);
         ENABLE = var14;
         BT_STATUS var5 = new BT_STATUS("HFP_CONN", 1);
         HFP_CONN = var5;
         BT_STATUS var4 = new BT_STATUS("A2DP_CONN", 2);
         A2DP_CONN = var4;
         BT_STATUS var12 = new BT_STATUS("SCANNING", 3);
         SCANNING = var12;
         BT_STATUS var11 = new BT_STATUS("PAIRING", 4);
         PAIRING = var11;
         BT_STATUS var1 = new BT_STATUS("CONNING", 5);
         CONNING = var1;
         BT_STATUS var2 = new BT_STATUS("IN_HFP", 6);
         IN_HFP = var2;
         BT_STATUS var8 = new BT_STATUS("IN_A2DP", 7);
         IN_A2DP = var8;
         BT_STATUS var3 = new BT_STATUS("A2DP_PLAYING", 8);
         A2DP_PLAYING = var3;
         BT_STATUS var9 = new BT_STATUS("CALLING", 9);
         CALLING = var9;
         BT_STATUS var10 = new BT_STATUS("INCOMING", 10);
         INCOMING = var10;
         BT_STATUS var6 = new BT_STATUS("OUTGOING", 11);
         OUTGOING = var6;
         BT_STATUS var7 = new BT_STATUS("SYNC_PB_ING", 12);
         SYNC_PB_ING = var7;
         BT_STATUS var13 = new BT_STATUS("SYNC_PB_FINISH", 13);
         SYNC_PB_FINISH = var13;
         BT_STATUS var0 = new BT_STATUS("DISABLE", 14);
         DISABLE = var0;
         $VALUES = new BT_STATUS[]{var14, var5, var4, var12, var11, var1, var2, var8, var3, var9, var10, var6, var7, var13, var0};
      }
   }

   public static enum CallDevice {
      private static final CallDevice[] $VALUES;
      Car,
      Phone,
      SWITCHING;

      static {
         CallDevice var0 = new CallDevice("Phone", 0);
         Phone = var0;
         CallDevice var2 = new CallDevice("SWITCHING", 1);
         SWITCHING = var2;
         CallDevice var1 = new CallDevice("Car", 2);
         Car = var1;
         $VALUES = new CallDevice[]{var0, var2, var1};
      }
   }

   public static enum CallState {
      private static final CallState[] $VALUES;
      CALLING,
      END_CALLING,
      IN_CALLING,
      NORMAL,
      OUT_CALLING;

      static {
         CallState var3 = new CallState("NORMAL", 0);
         NORMAL = var3;
         CallState var1 = new CallState("OUT_CALLING", 1);
         OUT_CALLING = var1;
         CallState var4 = new CallState("IN_CALLING", 2);
         IN_CALLING = var4;
         CallState var2 = new CallState("CALLING", 3);
         CALLING = var2;
         CallState var0 = new CallState("END_CALLING", 4);
         END_CALLING = var0;
         $VALUES = new CallState[]{var3, var1, var4, var2, var0};
      }
   }

   public static enum SETTING_GET {
      private static final SETTING_GET[] $VALUES;
      getBtPhoneBook;

      static {
         SETTING_GET var0 = new SETTING_GET("getBtPhoneBook", 0);
         getBtPhoneBook = var0;
         $VALUES = new SETTING_GET[]{var0};
      }
   }

   public static enum SETTING_SET {
      private static final SETTING_SET[] $VALUES;
      setBtCall;

      static {
         SETTING_SET var0 = new SETTING_SET("setBtCall", 0);
         setBtCall = var0;
         $VALUES = new SETTING_SET[]{var0};
      }
   }
}
