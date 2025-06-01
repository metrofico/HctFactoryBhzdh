package com.hzbhd.canbus.car._458;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.util.LogUtil;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final int MESSAGE_ADD_DATA = 1;
   private static final int MESSAGE_PARSE_DATA = 0;
   private static final int MSG_BUFFER_SIZE = 2048;
   private String airJsonStr;
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
                  byte[] var4 = (byte[])var1.obj;
                  System.arraycopy(var4, 1, this.this$0.mMsgUnHandledData, this.this$0.mUnHandledDataLen, var4.length - 1);
                  MsgMgr var3 = this.this$0;
                  var3.mUnHandledDataLen = var3.mUnHandledDataLen + (var4.length - 1);
                  this.this$0.mHandler.sendEmptyMessageDelayed(0, 10L);
                  Log.d("fxHou2", "mUnHandledDataLen : " + this.this$0.mUnHandledDataLen);
               }
            } else if (this.this$0.parseSerialDataContinue()) {
               this.this$0.mHandler.sendEmptyMessageDelayed(0, 10L);
            }

         }
      };
   }

   private void RadarInfoChange(byte[] var1) {
      int[] var2 = this.getByteArrayToIntArray(var1);
      if (var2[0] == 204) {
         this.setRadarInfo(var2);
      }

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

   private String intArrayToJsonStr(int[] var1) {
      this.airJsonStr = "{";

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var2 == var1.length - 1) {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + "}";
         } else {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + ",";
         }
      }

      return this.airJsonStr;
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

   private void setRadarInfo(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         MdRadarData.left_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 4));
         MdRadarData.mid_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4));
         MdRadarData.right_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[1], 0, 4));
         this.getUiMgr(this.mContext).refreshRadar();
      }

   }

   protected void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportAllCanBusData(this.intArrayToJsonStr(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.ShareBasicInfo(this.getByteArrayToIntArray(var2));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mMsgUnHandledData = new byte[4096];
      this.mUnHandledDataLen = 0;
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
