package com.hzbhd.proxy.mcu.upgrade;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.hzbhd.proxy.mcu.aidl.IMCUUpgradeCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUUpgradeService;
import com.hzbhd.proxy.service.bhdNewServiceConnection;
import com.hzbhd.util.LogUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MCUUpgradeManager extends bhdNewServiceConnection implements IMCUUpgradeManager {
   private static final String TAG = "MCUUpgradeManager";
   private static MCUUpgradeManager instance;
   private IMCUUpgradeCallback mIMCUUpgradeCallback = new MCUUpgradeCallback(this);
   private Handler mMsgHandler = new Handler(this, Looper.getMainLooper()) {
      final MCUUpgradeManager this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         UPGRADE_MSG_ID var4 = UPGRADE_MSG_ID.values()[var1.what];
         int var2 = null.$SwitchMap$com$hzbhd$proxy$mcu$upgrade$MCUUpgradeManager$UPGRADE_MSG_ID[var4.ordinal()];
         Iterator var5;
         Throwable var10000;
         boolean var10001;
         if (var2 != 1) {
            Throwable var164;
            Set var167;
            if (var2 != 2) {
               if (var2 == 3) {
                  var167 = this.this$0.mUpgradeList;
                  synchronized(var167){}

                  label1614: {
                     try {
                        var5 = this.this$0.mUpgradeList.iterator();
                     } catch (Throwable var154) {
                        var10000 = var154;
                        var10001 = false;
                        break label1614;
                     }

                     while(true) {
                        try {
                           if (!var5.hasNext()) {
                              break;
                           }

                           ((IMCUUpgradeListener)var5.next()).notifyMCUUpgradeSendDataSeq((Integer)var1.obj);
                        } catch (Throwable var155) {
                           var10000 = var155;
                           var10001 = false;
                           break label1614;
                        }
                     }

                     label1558:
                     try {
                        return;
                     } catch (Throwable var153) {
                        var10000 = var153;
                        var10001 = false;
                        break label1558;
                     }
                  }

                  while(true) {
                     var164 = var10000;

                     try {
                        throw var164;
                     } catch (Throwable var150) {
                        var10000 = var150;
                        var10001 = false;
                        continue;
                     }
                  }
               }
            } else {
               var167 = this.this$0.mUpgradeList;
               synchronized(var167){}

               label1587: {
                  try {
                     var5 = this.this$0.mUpgradeList.iterator();
                  } catch (Throwable var157) {
                     var10000 = var157;
                     var10001 = false;
                     break label1587;
                  }

                  while(true) {
                     try {
                        if (var5.hasNext()) {
                           ((IMCUUpgradeListener)var5.next()).notifyMCUUpgradeEndCheckStatus(UpgradeConstants.UpgradeStartEndStatus.valueOf((String)var1.obj));
                           continue;
                        }
                     } catch (Throwable var158) {
                        var10000 = var158;
                        var10001 = false;
                        break;
                     }

                     try {
                        return;
                     } catch (Throwable var156) {
                        var10000 = var156;
                        var10001 = false;
                        break;
                     }
                  }
               }

               while(true) {
                  var164 = var10000;

                  try {
                     throw var164;
                  } catch (Throwable var151) {
                     var10000 = var151;
                     var10001 = false;
                     continue;
                  }
               }
            }
         } else {
            Bundle var162 = (Bundle)var1.obj;
            boolean var3 = var162.getBoolean("isUpgrade");
            String var165 = var162.getString("status");
            Set var163 = this.this$0.mUpgradeList;
            synchronized(var163){}

            label1615: {
               try {
                  var5 = this.this$0.mUpgradeList.iterator();
               } catch (Throwable var160) {
                  var10000 = var160;
                  var10001 = false;
                  break label1615;
               }

               while(true) {
                  try {
                     if (!var5.hasNext()) {
                        break;
                     }

                     ((IMCUUpgradeListener)var5.next()).notifyMCUUpgradeStartCheckStatus(var3, UpgradeConstants.UpgradeStartCheckStatus.valueOf(var165));
                  } catch (Throwable var161) {
                     var10000 = var161;
                     var10001 = false;
                     break label1615;
                  }
               }

               label1594:
               try {
                  return;
               } catch (Throwable var159) {
                  var10000 = var159;
                  var10001 = false;
                  break label1594;
               }
            }

            while(true) {
               Throwable var166 = var10000;

               try {
                  throw var166;
               } catch (Throwable var152) {
                  var10000 = var152;
                  var10001 = false;
                  continue;
               }
            }
         }
      }
   };
   private final Set mUpgradeList = new HashSet();
   private IMCUUpgradeService mUpgradeMCUService;

   public MCUUpgradeManager(Context var1) {
      super(var1);
   }

   public static MCUUpgradeManager getInstance(Context var0) {
      if (instance == null) {
         synchronized(MCUUpgradeManager.class){}

         Throwable var10000;
         boolean var10001;
         label144: {
            try {
               if (instance == null) {
                  MCUUpgradeManager var1 = new MCUUpgradeManager(var0);
                  instance = var1;
               }
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label144;
            }

            label141:
            try {
               return instance;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label141;
            }
         }

         while(true) {
            Throwable var14 = var10000;

            try {
               throw var14;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               continue;
            }
         }
      } else {
         return instance;
      }
   }

   public String getServiceAction() {
      return "com.hzbhd.midware.service.mcu";
   }

   public String getServiceName() {
      return "bhd.mcu.upgrade";
   }

   public String getServicePkgName() {
      return "com.hzbhd.service.mcu";
   }

   public void init() {
   }

   public void onServiceConnectionChanged(IBinder var1, boolean var2) {
      if (LogUtil.log5()) {
         LogUtil.d("onServiceConnectionChanged,state=" + var2);
      }

      if (var2) {
         try {
            IMCUUpgradeService var4 = IMCUUpgradeService.Stub.asInterface(var1);
            this.mUpgradeMCUService = var4;
            var4.registerMCUUpgradeCallback(this.mIMCUUpgradeCallback);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      } else {
         this.mUpgradeMCUService = null;
      }

   }

   public void registerMCUUpgradeListener(IMCUUpgradeListener param1) {
      // $FF: Couldn't be decompiled
   }

   public int reqUpgrade(UpgradeConstants.DevType var1) {
      IMCUUpgradeService var3 = this.mUpgradeMCUService;
      if (var3 != null) {
         try {
            int var2 = var3.reqUpgrade(var1.name());
            return var2;
         } catch (RemoteException var4) {
            var4.printStackTrace();
         }
      }

      return -1;
   }

   public int sendUpgradeData(byte[] var1, int var2, int var3, int var4) {
      IMCUUpgradeService var5 = this.mUpgradeMCUService;
      if (var5 != null) {
         try {
            var2 = var5.sendUpgradeData(var1, var2, var3, var4);
            return var2;
         } catch (RemoteException var6) {
            var6.printStackTrace();
         }
      }

      return -1;
   }

   public void unRegisterMCUUpgradeListener(IMCUUpgradeListener param1) {
      // $FF: Couldn't be decompiled
   }

   public byte[] updateFlashData(byte[] var1) {
      if (LogUtil.log5()) {
         LogUtil.d("MCUUpgradeManager>updateFlashData(): para=" + var1);
      }

      if (LogUtil.log5()) {
         LogUtil.d("MCUUpgradeManager>updateFlashData(): mUpgradeMCUService=" + this.mUpgradeMCUService);
      }

      IMCUUpgradeService var2 = this.mUpgradeMCUService;
      if (var2 != null) {
         try {
            var1 = var2.updateFlashData(var1);
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return null;
   }

   public int upgradeEnd(int var1) {
      IMCUUpgradeService var2 = this.mUpgradeMCUService;
      if (var2 != null) {
         try {
            var1 = var2.upgradeEnd(var1);
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return -1;
   }

   public boolean upgradeStart(boolean var1, boolean var2, int var3, int var4, int var5, int var6) {
      IMCUUpgradeService var7 = this.mUpgradeMCUService;
      if (var7 != null) {
         try {
            var1 = var7.upgradeStart(var1, var2, var3, var4, var5, var6);
            return var1;
         } catch (RemoteException var8) {
            var8.printStackTrace();
         }
      }

      return false;
   }

   private class MCUUpgradeCallback extends IMCUUpgradeCallback.Stub {
      final MCUUpgradeManager this$0;

      private MCUUpgradeCallback(MCUUpgradeManager var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      MCUUpgradeCallback(MCUUpgradeManager var1, Object var2) {
         this(var1);
      }

      public void notifyMCUUpgradeEndCheckStatus(String var1) throws RemoteException {
         this.this$0.mMsgHandler.obtainMessage(UPGRADE_MSG_ID.notifyMCUUpgradeEndCheckStatus.ordinal(), var1).sendToTarget();
      }

      public void notifyMCUUpgradeSendDataSeq(int var1) throws RemoteException {
         this.this$0.mMsgHandler.obtainMessage(UPGRADE_MSG_ID.notifyMCUUpgradeSendDataSeq.ordinal(), var1).sendToTarget();
      }

      public void notifyMCUUpgradeStartCheckStatus(boolean var1, String var2) throws RemoteException {
         Bundle var3 = new Bundle();
         var3.putBoolean("isUpgrade", var1);
         var3.putString("status", var2);
         this.this$0.mMsgHandler.obtainMessage(UPGRADE_MSG_ID.notifyMCUUpgradeStartCheckStatus.ordinal(), var3).sendToTarget();
      }
   }

   public static enum UPGRADE_MSG_ID {
      private static final UPGRADE_MSG_ID[] $VALUES;
      notifyCanboxVersion,
      notifyHardwareVersion,
      notifyMCUUpgradeEndCheckStatus,
      notifyMCUUpgradeSendDataSeq,
      notifyMCUUpgradeStartCheckStatus,
      notifyMCUVersion,
      notifyScreenVersion;

      static {
         UPGRADE_MSG_ID var4 = new UPGRADE_MSG_ID("notifyMCUVersion", 0);
         notifyMCUVersion = var4;
         UPGRADE_MSG_ID var3 = new UPGRADE_MSG_ID("notifyHardwareVersion", 1);
         notifyHardwareVersion = var3;
         UPGRADE_MSG_ID var6 = new UPGRADE_MSG_ID("notifyCanboxVersion", 2);
         notifyCanboxVersion = var6;
         UPGRADE_MSG_ID var2 = new UPGRADE_MSG_ID("notifyScreenVersion", 3);
         notifyScreenVersion = var2;
         UPGRADE_MSG_ID var0 = new UPGRADE_MSG_ID("notifyMCUUpgradeStartCheckStatus", 4);
         notifyMCUUpgradeStartCheckStatus = var0;
         UPGRADE_MSG_ID var1 = new UPGRADE_MSG_ID("notifyMCUUpgradeEndCheckStatus", 5);
         notifyMCUUpgradeEndCheckStatus = var1;
         UPGRADE_MSG_ID var5 = new UPGRADE_MSG_ID("notifyMCUUpgradeSendDataSeq", 6);
         notifyMCUUpgradeSendDataSeq = var5;
         $VALUES = new UPGRADE_MSG_ID[]{var4, var3, var6, var2, var0, var1, var5};
      }
   }
}
