package com.hzbhd.canbus.car._441;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final int MESSAGE_ADD_DATA = 1;
   private static final int MESSAGE_PARSE_DATA = 0;
   private static final int MSG_BUFFER_SIZE = 2048;
   DecimalFormat df_1Decimal;
   DecimalFormat df_2Decimal;
   DecimalFormat df_2Integer;
   int[] dvrInt;
   private Context mContext;
   int[] mFrontRadarData;
   private final Handler mHandler;
   private final HandlerThread mHandlerThread;
   private byte[] mMsgUnHandledData;
   private UiMgr mUiMgr;
   private int mUnHandledDataLen;

   public MsgMgr() {
      HandlerThread var1 = new HandlerThread(this, "hzbhd.canbusinfo.MsgMgr") {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.start();
         }
      };
      this.mHandlerThread = var1;
      this.mHandler = new Handler(this, var1.getLooper()) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            int var2 = var1.what;
            if (var2 != 0) {
               if (var2 == 1) {
                  byte[] var3 = (byte[])var1.obj;
                  System.arraycopy(var3, 1, this.this$0.mMsgUnHandledData, this.this$0.mUnHandledDataLen, var3.length - 1);
                  MsgMgr var4 = this.this$0;
                  var4.mUnHandledDataLen = var4.mUnHandledDataLen + (var3.length - 1);
                  this.this$0.mHandler.sendEmptyMessageDelayed(0, 10L);
               }
            } else if (this.this$0.parseSerialDataContinue()) {
               this.this$0.mHandler.sendEmptyMessageDelayed(0, 10L);
            }

         }
      };
      this.df_2Decimal = new DecimalFormat("###0.00");
      this.df_1Decimal = new DecimalFormat("###0.0");
      this.df_2Integer = new DecimalFormat("00");
   }

   private void DvrModular(Context var1, byte[] var2) {
      int[] var4 = this.getByteArrayToIntArray(this.SplicingByte(new byte[]{0}, var2));
      if (var4[1] == 170 && var4[2] == 77 && var4[3] == 68) {
         int var3 = var4[5];
         if (var3 != 95) {
            if (var3 != 112) {
               if (var3 != 255) {
                  switch (var3) {
                     case 32:
                        this.set0x20(var4);
                        break;
                     case 33:
                        this.set0x21(var4);
                        break;
                     case 34:
                        this.set0x22();
                        break;
                     default:
                        switch (var3) {
                           case 80:
                              this.set0x50(var4[6]);
                              break;
                           case 81:
                              this.set0x51(var4[6]);
                              break;
                           case 82:
                              this.set0x52(var4[6]);
                              break;
                           case 83:
                              this.set0x53(var4[6]);
                              break;
                           case 84:
                              this.set0x54(var4[6]);
                              break;
                           case 85:
                              this.set0x55(var4[6]);
                              break;
                           case 86:
                              this.set0x56(var4[6]);
                              break;
                           case 87:
                              this.set0x57(var4[6]);
                              break;
                           case 88:
                              this.set0x58(var4[6], var4[7], var4[8], var4[9]);
                              break;
                           case 89:
                              this.set0x59(var4[6], var4[7], var4[8], var4[9]);
                              break;
                           case 90:
                              this.set0x5A(var4);
                              break;
                           case 91:
                              this.set0x5B();
                              break;
                           case 92:
                              this.set0x5C(var4);
                        }
                  }
               } else {
                  this.set0xFF();
               }
            } else {
               this.set0x70(var4);
            }
         } else {
            this.set0x5F(var4);
         }
      }

   }

   private void RadarInfoChange(byte[] var1) {
      int[] var2 = this.getByteArrayToIntArray(var1);
      if (var2[0] == 204) {
         this.setRadarInfo(var2);
      }

   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getResult(int var1) {
      if (var1 == 1) {
         return 1;
      } else if (var1 >= 2 && var1 < 8) {
         return 2;
      } else {
         return var1 >= 8 && var1 <= 13 ? 3 : 0;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean parseSerialDataContinue() {
      Exception var10000;
      label76: {
         int var2;
         boolean var10001;
         try {
            var2 = this.mUnHandledDataLen;
         } catch (Exception var10) {
            var10000 = var10;
            var10001 = false;
            break label76;
         }

         byte var1 = 4;
         if (var2 < 4) {
            return false;
         }

         byte[] var4;
         try {
            var4 = this.mMsgUnHandledData;
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label76;
         }

         byte var3 = var4[0];
         byte[] var5;
         if ((var3 != -86 || var4[2] != -81) && (var3 != -69 || var4[2] != -65)) {
            if (var3 != -52) {
               try {
                  var5 = new byte[var2];
                  System.arraycopy(var4, 0, var5, 0, var2);
                  System.arraycopy(var5, 1, this.mMsgUnHandledData, 0, this.mUnHandledDataLen - 1);
                  --this.mUnHandledDataLen;
                  return true;
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label76;
               }
            }

            var1 = 8;
         }

         if (var1 == var2) {
            try {
               this.mUnHandledDataLen = 0;
               var5 = new byte[var1];
               System.arraycopy(var4, 0, var5, 0, var1);
               this.reportOneSerialMsg(var5);
               return false;
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
            }
         } else {
            if (var1 > var2) {
               return false;
            }

            if (var1 >= var2) {
               return false;
            }

            try {
               this.mUnHandledDataLen = var2 - var1;
               var5 = new byte[var1];
               System.arraycopy(var4, 0, var5, 0, var1);
               var4 = this.mMsgUnHandledData;
               System.arraycopy(var4, var1, var4, 0, this.mUnHandledDataLen);
               this.reportOneSerialMsg(var5);
               return true;
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
            }
         }
      }

      Exception var11 = var10000;
      var11.printStackTrace();
      this.mUnHandledDataLen = 0;
      return false;
   }

   private void reportOneSerialMsg(byte[] var1) {
      if (LogUtil.log5()) {
         LogUtil.d("reportOneSerialMsg(): " + FgeString.bytes2HexString(var1, var1.length));
      }

      if (this.serialDataCheckSum(var1)) {
         if (LogUtil.log5()) {
            LogUtil.d("reportOneSerialMsg(): check sum");
         }

         this.RadarInfoChange(var1);
      }

   }

   private boolean serialDataCheckSum(byte[] var1) {
      boolean var5 = false;
      int var3 = 0;

      byte var2;
      for(var2 = 0; var3 < var1.length - 1; ++var3) {
         var2 += var1[var3];
      }

      if (LogUtil.log5()) {
         LogUtil.d("serialDataCheckSum(): sum = " + FgeString.bytetoHexString(var2));
      }

      boolean var4 = var5;
      if (var1.length > 1) {
         if (LogUtil.log5()) {
            LogUtil.d("serialDataCheckSum(): msg end = " + var1[var1.length - 1]);
         }

         var4 = var5;
         if (var2 == var1[var1.length - 1]) {
            var4 = true;
         }
      }

      return var4;
   }

   private void set0x20(int[] var1) {
      GeneralDvrState.lock = DataHandleUtils.getBoolBit7(var1[6]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[6], 5, 2);
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  GeneralDvrState.tag = this.mContext.getString(2131765897);
               }
            } else {
               GeneralDvrState.tag = this.mContext.getString(2131765896);
            }
         } else {
            GeneralDvrState.tag = this.mContext.getString(2131765895);
         }
      } else {
         GeneralDvrState.tag = this.mContext.getString(2131765894);
      }

      GeneralDvrPlayPage.pageState = DataHandleUtils.getIntFromByteWithBit(var1[6], 3, 2);
      GeneralDvrState.sd = DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 2);
      var2 = var1[7];
      if (var2 == 0) {
         GeneralDvrPlayPage.time = this.mContext.getString(2131765909) + this.df_2Integer.format((long)var1[8]) + ":" + this.df_2Integer.format((long)var1[9]) + ":" + this.df_2Integer.format((long)var1[10]);
      } else if (var2 == 1) {
         GeneralDvrPlayPage.time = this.mContext.getString(2131765908) + this.df_2Integer.format((long)var1[8]) + ":" + this.df_2Integer.format((long)var1[9]) + ":" + this.df_2Integer.format((long)var1[10]);
      } else if (var2 == 255) {
         GeneralDvrPlayPage.time = " ";
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x21(int[] var1) {
      int var2 = var1[6];
      if (var2 != 209) {
         if (var2 != 210) {
            if (var2 != 212) {
               if (var2 != 216) {
                  if (var2 != 228) {
                     if (var2 != 232) {
                        if (var2 != 225) {
                           if (var2 != 226) {
                              if (var2 != 240) {
                                 if (var2 == 241) {
                                    GeneralDvrPlayPage.controlState = this.mContext.getString(2131765911);
                                 }
                              } else {
                                 GeneralDvrPlayPage.controlState = this.mContext.getString(2131765910);
                              }
                           } else {
                              GeneralDvrPlayPage.controlState = this.mContext.getString(2131765913);
                           }
                        } else {
                           GeneralDvrPlayPage.controlState = this.mContext.getString(2131765912);
                        }
                     } else {
                        GeneralDvrPlayPage.controlState = this.mContext.getString(2131765915);
                     }
                  } else {
                     GeneralDvrPlayPage.controlState = this.mContext.getString(2131765914);
                  }
               } else {
                  GeneralDvrPlayPage.controlState = this.mContext.getString(2131765919);
               }
            } else {
               GeneralDvrPlayPage.controlState = this.mContext.getString(2131765918);
            }
         } else {
            GeneralDvrPlayPage.controlState = this.mContext.getString(2131765917);
         }
      } else {
         GeneralDvrPlayPage.controlState = this.mContext.getString(2131765916);
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x22() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.dvrInt[6] == 1) {
               Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765880), 0).show();
            }

         }
      });
   }

   private void set0x50(int var1) {
      switch (var1) {
         case 128:
            GeneralDvrSetting.resolvingPower = 0;
            break;
         case 129:
            GeneralDvrSetting.resolvingPower = 1;
            break;
         case 130:
            GeneralDvrSetting.resolvingPower = 2;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x51(int var1) {
      switch (var1) {
         case 128:
            GeneralDvrSetting.timeTag = 0;
            break;
         case 129:
            GeneralDvrSetting.timeTag = 1;
            break;
         case 130:
            GeneralDvrSetting.timeTag = 2;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x52(int var1) {
      if (var1 != 128) {
         if (var1 != 129) {
            if (var1 != 131) {
               if (var1 == 133) {
                  GeneralDvrSetting.VideoRecordingSyncTime = 3;
               }
            } else {
               GeneralDvrSetting.VideoRecordingSyncTime = 2;
            }
         } else {
            GeneralDvrSetting.VideoRecordingSyncTime = 1;
         }
      } else {
         GeneralDvrSetting.VideoRecordingSyncTime = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x53(int var1) {
      if (var1 != 128) {
         if (var1 == 129) {
            GeneralDvrSetting.VideoRecordingVoice = 1;
         }
      } else {
         GeneralDvrSetting.VideoRecordingVoice = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x54(int var1) {
      if (var1 != 128) {
         if (var1 == 129) {
            GeneralDvrSetting.dvrLanguage = 1;
         }
      } else {
         GeneralDvrSetting.dvrLanguage = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x55(int var1) {
      if (var1 != 128) {
         if (var1 != 129) {
            if (var1 != 131) {
               if (var1 == 133) {
                  GeneralDvrSetting.gravitySensor = 3;
               }
            } else {
               GeneralDvrSetting.gravitySensor = 2;
            }
         } else {
            GeneralDvrSetting.gravitySensor = 1;
         }
      } else {
         GeneralDvrSetting.gravitySensor = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x56(int var1) {
      if (var1 != 128) {
         if (var1 == 129) {
            GeneralDvrSetting.opticalFrequency = 1;
         }
      } else {
         GeneralDvrSetting.opticalFrequency = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x57(int var1) {
      switch (var1) {
         case 128:
            GeneralDvrSetting.timeFormat = 0;
            break;
         case 129:
            GeneralDvrSetting.timeFormat = 1;
            break;
         case 130:
            GeneralDvrSetting.timeFormat = 2;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x58(int var1, int var2, int var3, int var4) {
      if (var1 == 2) {
         GeneralDvrState.date = var2 + 2000 + "-" + this.df_2Decimal.format((long)var3) + "-" + this.df_2Decimal.format((long)var4);
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x59(int var1, int var2, int var3, int var4) {
      if (var1 == 2) {
         GeneralDvrState.time = this.df_2Integer.format((long)var2) + ":" + this.df_2Integer.format((long)var3) + ":" + this.df_2Integer.format((long)var4);
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x5A(int[] var1) {
      Log.d("fxHou0x5A", "收到数据");

      try {
         StringBuilder var2 = new StringBuilder();
         GeneralDvrState.version = var2.append(var1[6] + 2000).append("-").append(this.df_2Integer.format((long)var1[7])).append("-").append(this.df_2Integer.format((long)var1[8])).append(" V").append(var1[9]).toString();
      } catch (Exception var3) {
         Log.d("fxHou0x5A", var3.toString());
      }

      Log.d("fxHou0x5A", GeneralDvrState.version);
      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x5B() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            switch (this.this$0.dvrInt[6]) {
               case 128:
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765890), 0).show();
                  GeneralDvrState.formatSdFail = true;
                  break;
               case 129:
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765891), 0).show();
                  GeneralDvrState.formatSdFail = false;
                  break;
               case 130:
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765892), 0).show();
                  GeneralDvrState.formatSdFail = false;
            }

         }
      });
      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x5C(int[] var1) {
      if (var1[6] == 128) {
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               Toast.makeText(this.this$0.mContext, "Reset Fail!", 0).show();
            }
         });
      }

   }

   private void set0x5F(int[] var1) {
      this.set0x50(var1[6]);
      this.set0x51(var1[7]);
      this.set0x52(var1[8]);
      this.set0x53(var1[9]);
      this.set0x54(var1[10]);
      this.set0x55(var1[11]);
      this.set0x56(var1[12]);
      this.set0x57(var1[13]);
      this.set0x58(2, var1[14], var1[15], var1[16]);
      this.set0x59(2, var1[17], var1[18], var1[19]);
   }

   private void set0x70(int[] var1) {
      int var8 = var1[6];
      byte var5 = 0;
      int var3 = 0;
      int var2 = 0;
      byte var7 = 0;
      byte var6 = 0;
      int var4 = 0;
      switch (var8) {
         case 1:
            GeneralDvrFile.getInstance().item1.clear();
            var3 = var1.length;

            for(var2 = var6; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item1.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 2:
            GeneralDvrFile.getInstance().item2.clear();
            var3 = var1.length;

            for(var2 = var7; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item2.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 3:
            GeneralDvrFile.getInstance().item3.clear();

            for(var3 = var1.length; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item3.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 4:
            GeneralDvrFile.getInstance().item4.clear();
            var4 = var1.length;

            for(var2 = var3; var2 < var4; ++var2) {
               var3 = var1[var2];
               GeneralDvrFile.getInstance().item4.add(var3);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 5:
            GeneralDvrFile.getInstance().item5.clear();
            var3 = var1.length;

            for(var2 = var5; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item5.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 6:
            GeneralDvrFile.getInstance().item6.clear();
            var3 = var1.length;

            for(var2 = var4; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item6.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
      }

   }

   private void set0xFF() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765903), 0).show();
         }
      });
   }

   private void setRadarInfo(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         Log.d("RADAR:Data", "前左=" + DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 4));
         Log.d("RADAR:Data", "前右=" + DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4));
         Log.d("RADAR:Data", "后左=" + DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 4));
         Log.d("RADAR:Data", "后左中=" + DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4));
         Log.d("RADAR:Data", "后右中=" + DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4));
         Log.d("RADAR:Data", "后右=" + DataHandleUtils.getIntFromByteWithBit(var1[1], 0, 4));
         MdRadarData.left_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 4));
         MdRadarData.mid_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4));
         MdRadarData.right_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[1], 0, 4));
         this.getUiMgr(this.mContext).refreshRadar();
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mMsgUnHandledData = new byte[4096];
      this.mUnHandledDataLen = 0;
      DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
   }

   // $FF: synthetic method
   void lambda$initCommand$0$com_hzbhd_canbus_car__441_MsgMgr(Context var1, byte[] var2) {
      this.DvrModular(var1, var2);
   }

   public void serialDataChange(Context var1, byte[] var2) {
      if (var2 != null && var2.length > 1 && var2[0] == 91) {
         Message var3 = new Message();
         var3.what = 1;
         var3.obj = var2;
         this.mHandler.sendMessage(var3);
      }

   }
}
