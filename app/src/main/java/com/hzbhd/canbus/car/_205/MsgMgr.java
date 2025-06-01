package com.hzbhd.canbus.car._205;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private static final int SEND_NORMAL_MESSAGE = 2;
   private int FreqInt;
   private byte freqHi;
   private byte freqLo;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private Handler mHandler;
   private HandlerThread mHandlerThread;
   private ID3[] mMusicId3s;
   private long realtime;
   private String songAlbumNow = "";
   private String songArtistNow = "";
   private String songTitleNow = "";

   private byte getAllBandTypeData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
         case 1:
            return 16;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 0;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.FreqInt = Integer.parseInt(var2);
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private void initHandler(Context var1) {
      HandlerThread var2 = new HandlerThread("MyApplication");
      this.mHandlerThread = var2;
      var2.start();
      this.mHandler = new Handler(this, this.mHandlerThread.getLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 2) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private void initID3() {
      this.mMusicId3s = new ID3[]{new ID3(this, 2), new ID3(this, 3), new ID3(this, 4)};
   }

   private void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   private void realKeyControl() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           switch (var1) {
                              case 129:
                                 this.realKeyClick(15);
                                 break;
                              case 130:
                                 this.realKeyClick(137);
                                 break;
                              case 131:
                                 this.realKeyClick(15);
                           }
                        } else {
                           this.realKeyClick(184);
                        }
                     } else {
                        this.realKeyClick(201);
                     }
                  } else {
                     this.realKeyClick(46);
                  }
               } else {
                  this.realKeyClick(45);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void reportID3Info(ID3[] var1) {
      int var4 = var1.length;
      byte var3 = 0;

      for(int var2 = 0; var2 < var4; ++var2) {
         if (var1[var2].isId3Change()) {
            var4 = var1.length;

            for(var2 = var3; var2 < var4; ++var2) {
               ID3 var5 = var1[var2];
               var5.recordId3Info();
               this.reportID3InfoFinal(var5.command, var5.info);
            }

            return;
         }
      }

   }

   private void reportID3InfoFinal(int var1, String var2) {
      try {
         byte[] var4 = DataHandleUtils.exceptBOMHead(var2.getBytes("utf-8"));
         this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, (byte)var1}, var4), 35), (long)(var1 * 80));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private void reportID3InfoSimple(byte[] var1, byte var2, byte var3, byte var4, String var5, String var6, String var7, boolean var8, String var9) {
      (new Thread(this, var8, var5, var6, var7, var1, var2, var9, var3, var4) {
         final MsgMgr this$0;
         final String val$album;
         final byte val$albumCmd;
         final String val$artist;
         final byte val$artistCmd;
         final String val$charsetName;
         final byte[] val$haed;
         final boolean val$isClean;
         final String val$title;
         final byte val$titleCmd;

         {
            this.this$0 = var1;
            this.val$isClean = var2;
            this.val$title = var3;
            this.val$album = var4;
            this.val$artist = var5;
            this.val$haed = var6;
            this.val$titleCmd = var7;
            this.val$charsetName = var8;
            this.val$albumCmd = var9;
            this.val$artistCmd = var10;
         }

         public void run() {
            super.run();

            try {
               if (this.val$isClean) {
                  sleep(1000L);
               }

               if (!this.val$title.equals(this.this$0.songTitleNow) || !this.val$album.equals(this.this$0.songAlbumNow) || !this.val$artist.equals(this.this$0.songArtistNow)) {
                  this.this$0.songTitleNow = this.val$title;
                  this.this$0.songAlbumNow = this.val$album;
                  this.this$0.songArtistNow = this.val$artist;
                  sleep(100L);
                  this.this$0.reportID3InfoSimpleFinal(this.val$haed, this.val$titleCmd, this.val$title, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoSimpleFinal(this.val$haed, this.val$albumCmd, this.val$album, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoSimpleFinal(this.val$haed, this.val$artistCmd, this.val$artist, this.val$charsetName);
               }
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void reportID3InfoSimpleFinal(byte[] var1, byte var2, String var3, String var4) {
      try {
         Util.reportCanbusInfo(Util.byteMerger(Util.addBytes(var1, var2), Util.exceptBOMHead(var3.getBytes(var4))));
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = Message.obtain();
         var5.what = 1;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void setMostStatus() {
      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var1 = "switch_on";
      } else {
         var1 = "switch_off";
      }

      var2.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 18, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 4, 0, 0, 0, 0, 0});
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -53, 4, 0});
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 1, 0, 0, 0, 0, 0});
      (new Thread(this, var1) {
         final MsgMgr this$0;
         final byte[] val$phoneNumber;

         {
            this.this$0 = var1;
            this.val$phoneNumber = var2;
         }

         public void run() {
            super.run();

            try {
               sleep(100L);
               byte[] var1 = this.val$phoneNumber;
               CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -53, 4}, var1));
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 3, 0, 0, 0, 0, 0});
      (new Thread(this, var1) {
         final MsgMgr this$0;
         final byte[] val$phoneNumber;

         {
            this.this$0 = var1;
            this.val$phoneNumber = var2;
         }

         public void run() {
            super.run();

            try {
               sleep(100L);
               byte[] var1 = this.val$phoneNumber;
               CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -53, 4}, var1));
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0});
      }

   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      int var5 = this.getMinute(var4);
      var4 = this.getSecond(var4);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 2, (byte)var5, (byte)var4, 0, 0, 0});
      (new Thread(this, var1) {
         final MsgMgr this$0;
         final byte[] val$phoneNumber;

         {
            this.this$0 = var1;
            this.val$phoneNumber = var2;
         }

         public void run() {
            super.run();

            try {
               sleep(100L);
               byte[] var1 = this.val$phoneNumber;
               CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -53, 4}, var1));
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 == 207) {
            this.setMostStatus();
         }
      } else {
         this.realKeyControl();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      short var3;
      if (var2) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(var3 + (var1 & 127))});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.i("_205_MsgMgr", "discInfoChange: mContext = " + this.mContext);
      if (this.mContext != null) {
         int var18 = this.getHour(var2);
         var3 = this.getMinute(var2);
         var2 = this.getSecond(var2);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         if (var10 == 240) {
            if (SystemClock.elapsedRealtime() - this.realtime < 1000L) {
               return;
            }

            var11 = System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
            if (!TextUtils.isEmpty(var11)) {
               this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Base64.decode(var11, 0));
               this.realtime = SystemClock.elapsedRealtime();
            }
         } else if (var10 == 32) {
            var1 = (byte)var4;
            byte var16 = (byte)var6;
            byte var15 = (byte)var18;
            byte var17 = (byte)var3;
            byte var14 = (byte)var2;
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -64, 2, 16, var1, var16, 0, var15, var17, var14});
         }

      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.initID3();
      this.initHandler(var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mMusicId3s[0].info = " ";
      this.mMusicId3s[1].info = " ";
      this.mMusicId3s[2].info = " ";
      this.reportID3Info(this.mMusicId3s);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var1 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 19, var1, var9, 0, var5, var6, var7});
      }

      this.mMusicId3s[0].info = var21;
      this.mMusicId3s[1].info = var22;
      this.mMusicId3s[2].info = var23;
      this.reportID3Info(this.mMusicId3s);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var10 = this.getAllBandTypeData(var2);
      this.getFreqByteHiLo(var2, var3);
      byte var9 = (byte)var10;
      byte var6 = this.freqLo;
      byte var7 = this.freqHi;
      byte var8 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -62, var9, var6, var7, var8});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
      this.reportID3InfoSimple(new byte[]{22, -53}, (byte)2, (byte)3, (byte)4, " ", " ", " ", true, "utf-8");
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         var1 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, 8, 19, var1, var9, 0, var5, var6, var7});
      }

   }

   private class ID3 {
      private int command;
      private String info;
      private String record;
      final MsgMgr this$0;

      private ID3(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.command = var2;
         this.info = "";
         this.record = "";
      }

      // $FF: synthetic method
      ID3(MsgMgr var1, int var2, Object var3) {
         this(var1, var2);
      }

      private boolean isId3Change() {
         if (this.record.equals(this.info)) {
            return false;
         } else {
            this.recordId3Info();
            return true;
         }
      }

      private void recordId3Info() {
         this.record = this.info;
      }
   }
}
