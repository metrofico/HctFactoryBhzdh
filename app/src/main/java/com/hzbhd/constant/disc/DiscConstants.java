package com.hzbhd.constant.disc;

public class DiscConstants {
   public static enum BOOLEAN {
      private static final BOOLEAN[] $VALUES;
      FALSE,
      TEUE;

      static {
         BOOLEAN var0 = new BOOLEAN("TEUE", 0);
         TEUE = var0;
         BOOLEAN var1 = new BOOLEAN("FALSE", 1);
         FALSE = var1;
         $VALUES = new BOOLEAN[]{var0, var1};
      }
   }

   public static enum DISC_ACTION {
      private static final DISC_ACTION[] $VALUES;
      ANGEL,
      AUDIO,
      BACKWARD,
      CHAPTER_SELECT,
      DISC_GOTO,
      DISPLAY,
      EJECT,
      FORWARD,
      GOTO_CHAPTER,
      GOTO_TRACK,
      NEXT,
      PAUSE,
      PBC,
      PHOTO_ROTATE_LEFT,
      PHOTO_ROTATE_RIGHT,
      PHOTO_ZOOM_IN,
      PHOTO_ZOOM_OUT,
      PLAY,
      PLAY_ROOT_MENU,
      PREV,
      READ_DISC_FALSE,
      READ_DISC_TRUE,
      REFRESH_ID3,
      REFRESH_MPEG_STATUS,
      RELEASE_SOURCE,
      REPEAT,
      REPEAT_AB,
      REQUEST_SOURCE,
      SCAN,
      SEEK,
      SELECT_MEDIA_TYPE,
      SET_LANGUAGE,
      SET_PASSWORD,
      SHUFFLE,
      STOP,
      SUB_TITLE,
      TIME_SELECT,
      TITLE,
      TITLE_SELECT,
      TRACK_NUM_SELECT,
      TS_MOVE;

      static {
         DISC_ACTION var23 = new DISC_ACTION("REQUEST_SOURCE", 0);
         REQUEST_SOURCE = var23;
         DISC_ACTION var28 = new DISC_ACTION("RELEASE_SOURCE", 1);
         RELEASE_SOURCE = var28;
         DISC_ACTION var34 = new DISC_ACTION("PLAY", 2);
         PLAY = var34;
         DISC_ACTION var7 = new DISC_ACTION("PAUSE", 3);
         PAUSE = var7;
         DISC_ACTION var29 = new DISC_ACTION("STOP", 4);
         STOP = var29;
         DISC_ACTION var5 = new DISC_ACTION("NEXT", 5);
         NEXT = var5;
         DISC_ACTION var10 = new DISC_ACTION("PREV", 6);
         PREV = var10;
         DISC_ACTION var6 = new DISC_ACTION("BACKWARD", 7);
         BACKWARD = var6;
         DISC_ACTION var12 = new DISC_ACTION("FORWARD", 8);
         FORWARD = var12;
         DISC_ACTION var18 = new DISC_ACTION("EJECT", 9);
         EJECT = var18;
         DISC_ACTION var25 = new DISC_ACTION("SCAN", 10);
         SCAN = var25;
         DISC_ACTION var31 = new DISC_ACTION("SEEK", 11);
         SEEK = var31;
         DISC_ACTION var37 = new DISC_ACTION("GOTO_CHAPTER", 12);
         GOTO_CHAPTER = var37;
         DISC_ACTION var27 = new DISC_ACTION("GOTO_TRACK", 13);
         GOTO_TRACK = var27;
         DISC_ACTION var9 = new DISC_ACTION("TRACK_NUM_SELECT", 14);
         TRACK_NUM_SELECT = var9;
         DISC_ACTION var30 = new DISC_ACTION("TITLE_SELECT", 15);
         TITLE_SELECT = var30;
         DISC_ACTION var4 = new DISC_ACTION("CHAPTER_SELECT", 16);
         CHAPTER_SELECT = var4;
         DISC_ACTION var16 = new DISC_ACTION("TIME_SELECT", 17);
         TIME_SELECT = var16;
         DISC_ACTION var33 = new DISC_ACTION("REPEAT", 18);
         REPEAT = var33;
         DISC_ACTION var8 = new DISC_ACTION("SHUFFLE", 19);
         SHUFFLE = var8;
         DISC_ACTION var35 = new DISC_ACTION("SUB_TITLE", 20);
         SUB_TITLE = var35;
         DISC_ACTION var22 = new DISC_ACTION("TITLE", 21);
         TITLE = var22;
         DISC_ACTION var3 = new DISC_ACTION("PBC", 22);
         PBC = var3;
         DISC_ACTION var38 = new DISC_ACTION("DISPLAY", 23);
         DISPLAY = var38;
         DISC_ACTION var14 = new DISC_ACTION("PHOTO_ROTATE_LEFT", 24);
         PHOTO_ROTATE_LEFT = var14;
         DISC_ACTION var19 = new DISC_ACTION("PHOTO_ROTATE_RIGHT", 25);
         PHOTO_ROTATE_RIGHT = var19;
         DISC_ACTION var21 = new DISC_ACTION("PHOTO_ZOOM_IN", 26);
         PHOTO_ZOOM_IN = var21;
         DISC_ACTION var26 = new DISC_ACTION("PHOTO_ZOOM_OUT", 27);
         PHOTO_ZOOM_OUT = var26;
         DISC_ACTION var11 = new DISC_ACTION("PLAY_ROOT_MENU", 28);
         PLAY_ROOT_MENU = var11;
         DISC_ACTION var13 = new DISC_ACTION("ANGEL", 29);
         ANGEL = var13;
         DISC_ACTION var32 = new DISC_ACTION("AUDIO", 30);
         AUDIO = var32;
         DISC_ACTION var17 = new DISC_ACTION("REPEAT_AB", 31);
         REPEAT_AB = var17;
         DISC_ACTION var0 = new DISC_ACTION("REFRESH_ID3", 32);
         REFRESH_ID3 = var0;
         DISC_ACTION var39 = new DISC_ACTION("READ_DISC_TRUE", 33);
         READ_DISC_TRUE = var39;
         DISC_ACTION var24 = new DISC_ACTION("READ_DISC_FALSE", 34);
         READ_DISC_FALSE = var24;
         DISC_ACTION var15 = new DISC_ACTION("SET_LANGUAGE", 35);
         SET_LANGUAGE = var15;
         DISC_ACTION var1 = new DISC_ACTION("SET_PASSWORD", 36);
         SET_PASSWORD = var1;
         DISC_ACTION var36 = new DISC_ACTION("TS_MOVE", 37);
         TS_MOVE = var36;
         DISC_ACTION var40 = new DISC_ACTION("REFRESH_MPEG_STATUS", 38);
         REFRESH_MPEG_STATUS = var40;
         DISC_ACTION var2 = new DISC_ACTION("DISC_GOTO", 39);
         DISC_GOTO = var2;
         DISC_ACTION var20 = new DISC_ACTION("SELECT_MEDIA_TYPE", 40);
         SELECT_MEDIA_TYPE = var20;
         $VALUES = new DISC_ACTION[]{var23, var28, var34, var7, var29, var5, var10, var6, var12, var18, var25, var31, var37, var27, var9, var30, var4, var16, var33, var8, var35, var22, var3, var38, var14, var19, var21, var26, var11, var13, var32, var17, var0, var39, var24, var15, var1, var36, var40, var2, var20};
      }
   }

   public static enum DISC_INFO {
      private static final DISC_INFO[] $VALUES;
      DISC_STATE,
      DISC_TYPE,
      DURATION,
      IS_NAME_REQ_FINISH,
      MEDIA_TYPE,
      MPEG_VERSION,
      PLAY_STATE,
      POSITION,
      REPEAT,
      SCAN,
      SHUFFLE,
      TOTAL_NO,
      VIDEO_MODE;

      static {
         DISC_INFO var1 = new DISC_INFO("DISC_STATE", 0);
         DISC_STATE = var1;
         DISC_INFO var6 = new DISC_INFO("VIDEO_MODE", 1);
         VIDEO_MODE = var6;
         DISC_INFO var5 = new DISC_INFO("DISC_TYPE", 2);
         DISC_TYPE = var5;
         DISC_INFO var3 = new DISC_INFO("PLAY_STATE", 3);
         PLAY_STATE = var3;
         DISC_INFO var10 = new DISC_INFO("SCAN", 4);
         SCAN = var10;
         DISC_INFO var4 = new DISC_INFO("POSITION", 5);
         POSITION = var4;
         DISC_INFO var9 = new DISC_INFO("DURATION", 6);
         DURATION = var9;
         DISC_INFO var11 = new DISC_INFO("SHUFFLE", 7);
         SHUFFLE = var11;
         DISC_INFO var2 = new DISC_INFO("REPEAT", 8);
         REPEAT = var2;
         DISC_INFO var7 = new DISC_INFO("MPEG_VERSION", 9);
         MPEG_VERSION = var7;
         DISC_INFO var12 = new DISC_INFO("IS_NAME_REQ_FINISH", 10);
         IS_NAME_REQ_FINISH = var12;
         DISC_INFO var8 = new DISC_INFO("TOTAL_NO", 11);
         TOTAL_NO = var8;
         DISC_INFO var0 = new DISC_INFO("MEDIA_TYPE", 12);
         MEDIA_TYPE = var0;
         $VALUES = new DISC_INFO[]{var1, var6, var5, var3, var10, var4, var9, var11, var2, var7, var12, var8, var0};
      }
   }
}
