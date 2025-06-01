package com.hzbhd.constant.media;

public class MeidaConstants {
   public static enum ACTION {
      private static final ACTION[] $VALUES;
      favour,
      favour_list_close,
      favour_list_open,
      favourplay,
      play_list_close,
      play_list_open,
      unfavour;

      static {
         ACTION var4 = new ACTION("favour", 0);
         favour = var4;
         ACTION var2 = new ACTION("unfavour", 1);
         unfavour = var2;
         ACTION var3 = new ACTION("favourplay", 2);
         favourplay = var3;
         ACTION var5 = new ACTION("play_list_open", 3);
         play_list_open = var5;
         ACTION var1 = new ACTION("play_list_close", 4);
         play_list_close = var1;
         ACTION var6 = new ACTION("favour_list_open", 5);
         favour_list_open = var6;
         ACTION var0 = new ACTION("favour_list_close", 6);
         favour_list_close = var0;
         $VALUES = new ACTION[]{var4, var2, var3, var5, var1, var6, var0};
      }
   }

   public static enum CLOSE_REASON {
      private static final CLOSE_REASON[] $VALUES;
      SOURCE_RELEASE,
      VOICE;

      static {
         CLOSE_REASON var0 = new CLOSE_REASON("VOICE", 0);
         VOICE = var0;
         CLOSE_REASON var1 = new CLOSE_REASON("SOURCE_RELEASE", 1);
         SOURCE_RELEASE = var1;
         $VALUES = new CLOSE_REASON[]{var0, var1};
      }
   }

   public static enum MEDIA_TYPE {
      private static final MEDIA_TYPE[] $VALUES;
      IMAGE,
      MUSIC,
      VIDEO;

      static {
         MEDIA_TYPE var1 = new MEDIA_TYPE("MUSIC", 0);
         MUSIC = var1;
         MEDIA_TYPE var0 = new MEDIA_TYPE("VIDEO", 1);
         VIDEO = var0;
         MEDIA_TYPE var2 = new MEDIA_TYPE("IMAGE", 2);
         IMAGE = var2;
         $VALUES = new MEDIA_TYPE[]{var1, var0, var2};
      }
   }

   public static enum PLAY_MODE {
      private static final PLAY_MODE[] $VALUES;
      REPEAT_ALL,
      REPEAT_FOLDER,
      REPEAT_ONE,
      SHUFFLE;

      static {
         PLAY_MODE var2 = new PLAY_MODE("REPEAT_ALL", 0);
         REPEAT_ALL = var2;
         PLAY_MODE var0 = new PLAY_MODE("SHUFFLE", 1);
         SHUFFLE = var0;
         PLAY_MODE var3 = new PLAY_MODE("REPEAT_ONE", 2);
         REPEAT_ONE = var3;
         PLAY_MODE var1 = new PLAY_MODE("REPEAT_FOLDER", 3);
         REPEAT_FOLDER = var1;
         $VALUES = new PLAY_MODE[]{var2, var0, var3, var1};
      }
   }

   public static enum PLAY_STATE {
      private static final PLAY_STATE[] $VALUES;
      COMPLETED,
      ERROR,
      IDLE,
      PAUSED,
      PREPARED,
      PREPARING,
      RELEASED,
      STARTED,
      STOPPED;

      static {
         PLAY_STATE var3 = new PLAY_STATE("IDLE", 0);
         IDLE = var3;
         PLAY_STATE var1 = new PLAY_STATE("PREPARING", 1);
         PREPARING = var1;
         PLAY_STATE var2 = new PLAY_STATE("PREPARED", 2);
         PREPARED = var2;
         PLAY_STATE var7 = new PLAY_STATE("STARTED", 3);
         STARTED = var7;
         PLAY_STATE var6 = new PLAY_STATE("PAUSED", 4);
         PAUSED = var6;
         PLAY_STATE var0 = new PLAY_STATE("STOPPED", 5);
         STOPPED = var0;
         PLAY_STATE var4 = new PLAY_STATE("COMPLETED", 6);
         COMPLETED = var4;
         PLAY_STATE var5 = new PLAY_STATE("ERROR", 7);
         ERROR = var5;
         PLAY_STATE var8 = new PLAY_STATE("RELEASED", 8);
         RELEASED = var8;
         $VALUES = new PLAY_STATE[]{var3, var1, var2, var7, var6, var0, var4, var5, var8};
      }
   }
}
