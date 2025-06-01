package com.hzbhd.canbus.car._447;

import android.util.Log;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.midware.jni.SerialPort;
import com.hzbhd.util.LogUtil;

public class RadarUartDevice {
   private static final int MSG_BUFFER_SIZE = 1024;
   private static final int MSG_MAX_SIZE = 512;
   private static final String TAG = "RadarUartDevice";
   private static IUartDataReport mIUartDataReport;
   private static byte[] mMsgUnHandledData;
   private static byte[] mReadDataBuf;
   private static int mUnHandledDataLen;
   private static RadarUartDevice sRadarUartDevice;
   private int mDev = SerialPort.jni_serial_open(3);

   public RadarUartDevice(IUartDataReport var1) {
      mIUartDataReport = var1;
      Log.i(TAG, "jni_serial_open . " + this.mDev);
      int var2 = this.mDev;
      if (var2 != -1) {
         SerialPort.jni_serial_setup(var2, 19200, 8, 'N', 1);
         mReadDataBuf = new byte[512];
         mMsgUnHandledData = new byte[1024];
      }

   }

   public static RadarUartDevice getRadarUartDevice(IUartDataReport var0) {
      synchronized(RadarUartDevice.class){}

      RadarUartDevice var4;
      try {
         if (sRadarUartDevice == null) {
            RadarUartDevice var1 = new RadarUartDevice(var0);
            sRadarUartDevice = var1;
         }

         var4 = sRadarUartDevice;
      } finally {
         ;
      }

      return var4;
   }

   private void printOneMsg(boolean var1, byte[] var2) {
      if (var2 != null) {
         if (var1) {
            if (LogUtil.log3()) {
               LogUtil.d("get :" + FgeString.bytes2HexString(var2, var2.length));
            }
         } else {
            Log.d(TAG, "send:" + FgeString.bytes2HexString(var2, var2.length));
         }

      }
   }

   private int readDABData(byte[] var1) {
      while(true) {
         boolean var10001;
         int var2;
         try {
            var2 = SerialPort.jni_serial_read_data(this.mDev, var1, var1.length, 10);
         } catch (Exception var4) {
            var10001 = false;
            break;
         }

         if (var2 > 0) {
            return var2;
         }

         try {
            Thread.sleep(10L);
         } catch (Exception var3) {
            var10001 = false;
            break;
         }
      }

      return -1;
   }

   private byte[] readMsg() {
      Exception var10000;
      label45: {
         boolean var10001;
         int var2;
         try {
            var2 = this.readDABData(mReadDataBuf);
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label45;
         }

         if (var2 < 8) {
            return null;
         }

         for(int var1 = 0; var1 <= var2 - 8; ++var1) {
            byte[] var3;
            try {
               var3 = mReadDataBuf;
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break label45;
            }

            if (var3[var1] == 85 && var3[var1 + 6] == -2 && var3[var1 + 7] == -1) {
               try {
                  byte[] var4 = new byte[8];
                  System.arraycopy(var3, var1, var4, 0, 8);
                  return var4;
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break label45;
               }
            }
         }

         return null;
      }

      Exception var8 = var10000;
      var8.printStackTrace();
      mUnHandledDataLen = 0;
      return null;
   }

   public byte[] getMsg() {
      // $FF: Couldn't be decompiled
   }

   public void start() {
      (new Thread(this) {
         final RadarUartDevice this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            while(true) {
               while(true) {
                  Exception var10000;
                  while(true) {
                     byte[] var1;
                     boolean var10001;
                     try {
                        var1 = this.this$0.getMsg();
                     } catch (Exception var4) {
                        var10000 = var4;
                        var10001 = false;
                        break;
                     }

                     if (var1 == null) {
                        try {
                           Log.e(RadarUartDevice.TAG, "msg == null");
                           sleep(50L);
                        } catch (Exception var2) {
                           var10000 = var2;
                           var10001 = false;
                           break;
                        }
                     } else {
                        try {
                           if (RadarUartDevice.mIUartDataReport != null) {
                              RadarUartDevice.mIUartDataReport.onRead(var1);
                           }
                        } catch (Exception var3) {
                           var10000 = var3;
                           var10001 = false;
                           break;
                        }
                     }
                  }

                  Exception var5 = var10000;
                  var5.printStackTrace();
               }
            }
         }
      }).start();
   }

   interface IUartDataReport {
      void onRead(byte[] var1);
   }
}
