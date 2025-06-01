package com.hzbhd.canbus.car._357;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;

public class MsgMgr extends AbstractMsgMgr {
   private int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private byte mFreqHi;
   private byte mFreqLo;
   private UiMgr uiMgr;

   private void VersionInfo0x7F() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

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
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 16;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.mFreqHi = (byte)(var3 >> 8);
            this.mFreqLo = (byte)(var3 & 255);
         }
      } else {
         this.mFreqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.mFreqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void sendMusic(String var1, String var2) {
      try {
         byte[] var4 = DataHandleUtils.exceptBOMHead(var1.getBytes("UTF8"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 18}, var4));
         var4 = DataHandleUtils.exceptBOMHead(var2.getBytes("UTF8"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 113, 18}, var4));
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   private void setWheelKeyInfo0x01() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 21) {
                                    if (var1 != 22) {
                                       if (var1 == 135) {
                                          this.buttonKey(134);
                                       }
                                    } else {
                                       this.buttonKey(49);
                                    }
                                 } else {
                                    this.buttonKey(50);
                                 }
                              } else {
                                 this.buttonKey(15);
                              }
                           } else {
                              this.buttonKey(14);
                           }
                        } else {
                           this.buttonKey(2);
                        }
                     } else {
                        this.buttonKey(3);
                     }
                  } else {
                     this.buttonKey(21);
                  }
               } else {
                  this.buttonKey(20);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void tokingNowTime(int var1) {
      int var2 = var1 % 3600;
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)(var2 % 60), (byte)(var2 / 60), (byte)(var1 / 3600), 0});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -126, 7, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -126, 5, 0});
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -126, 11, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 1});
      this.tokingNowTime(0);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, 1, 1});
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -122, 2});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.tokingNowTime(var4);
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 == 127) {
            this.VersionInfo0x7F();
         }
      } else {
         this.setWheelKeyInfo0x01();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var1 = (byte)(var4 & 255);
         var5 = (byte)(var4 >> 8 & 255);
         var2 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -126, 8, 17, var1, var5, var2, var9, var6, var7});
         this.sendMusic(var21, var23);
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.mContext != null) {
         byte var6 = this.getAllBandTypeData(var2);
         this.getFreqByteHiLo(var2, var3);
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -126, 1, 1, (byte)var6, this.mFreqLo, this.mFreqHi, (byte)var1, 0, 0});
      }
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -126, 8, 0, 0, 0, 0, 0, 0, 0});
      }
   }
}
