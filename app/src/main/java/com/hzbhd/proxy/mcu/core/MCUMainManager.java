package com.hzbhd.proxy.mcu.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.mcu.aidl.IMCUCanBoxControlCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMainCallback;
import com.hzbhd.proxy.mcu.aidl.IMCUMainService;
import com.hzbhd.proxy.mcu.aidl.IMCUMsgCallback;
import com.hzbhd.proxy.mcu.constant.MCU_MESSAGE_PEER;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MCUMainManager implements IMCUMainManager {
   private static MCUMainManager instance;
   private IMCUCanBoxControlCallback mMCUCanBoxControlCallback = new IMCUCanBoxControlCallback.Stub(this) {
      final MCUMainManager this$0;

      {
         this.this$0 = var1;
      }

      public void notifyCanboxData(int var1, byte[] var2) throws RemoteException {
         Iterator var3 = this.this$0.mMCUCanList.iterator();

         while(var3.hasNext()) {
            ((IMCUCanBoxControlListener)var3.next()).notifyCanboxData(var1, var2);
         }

      }
   };
   private final HashSet mMCUCanList = new HashSet();
   private IMCUMainCallback mMCUImcuMainCallback = new MCUMainCallback(this);
   private final Set mMCUMainList = new HashSet();
   private IMCUMainService mMCUMainService;
   private Handler mMainHandler = new Handler(this) {
      final MCUMainManager this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         MSG_ID var2 = MSG_ID.values()[var1.what];
         Iterator var7;
         Iterator var8;
         String var9;
         switch (null.$SwitchMap$com$hzbhd$proxy$mcu$core$MCUMainManager$MSG_ID[var2.ordinal()]) {
            case 1:
               Bundle var10 = (Bundle)var1.obj;
               var7 = this.this$0.mMCUMainList.iterator();

               while(var7.hasNext()) {
                  ((IMCUMainListener)var7.next()).mcuInit(var10.getByte("procotolVersion"), var10.getBoolean("powerOnType"), var10.getBoolean("hardwareReset"));
               }

               return;
            case 2:
               var9 = ((Bundle)var1.obj).getString("McuVersion");
               var7 = this.this$0.mMCUMainList.iterator();

               while(var7.hasNext()) {
                  ((IMCUMainListener)var7.next()).notifyMCUVersion(var9, (String)null, (String)null);
               }

               return;
            case 3:
               var8 = this.this$0.mMCUMainList.iterator();

               while(var8.hasNext()) {
                  ((IMCUMainListener)var8.next()).notifyPowerStatus((Integer)var1.obj);
               }

               return;
            case 4:
               Bundle var4 = (Bundle)var1.obj;
               String var6 = var4.getString("modelName");
               var9 = var4.getString("hardwareVersion");
               String var3 = var4.getString("serialNum");
               String var5 = var4.getString("data");
               Iterator var11 = this.this$0.mMCUMainList.iterator();

               while(var11.hasNext()) {
                  ((IMCUMainListener)var11.next()).notifyHardwareVersion(var6, var9, var3, var5);
               }

               return;
            case 5:
               var8 = this.this$0.mMCUMainList.iterator();

               while(var8.hasNext()) {
                  ((IMCUMainListener)var8.next()).notifyScreenVersion((String)var1.obj);
               }

               return;
            case 6:
               var8 = this.this$0.mMCUMainList.iterator();

               while(var8.hasNext()) {
                  ((IMCUMainListener)var8.next()).notifyCanboxVersion((String)var1.obj);
               }
         }

      }
   };

   // $FF: synthetic method
   static IMCUMainService access$300(MCUMainManager var0) {
      return var0.getMCUMainService();
   }

   // $FF: synthetic method
   static IMCUCanBoxControlCallback access$400(MCUMainManager var0) {
      return var0.mMCUCanBoxControlCallback;
   }

   // $FF: synthetic method
   static void access$500(MCUMainManager var0) {
      var0.notifyMcuConn();
   }

   public static MCUMainManager getInstance() {
      if (instance == null) {
         synchronized(MCUMainManager.class){}

         Throwable var10000;
         boolean var10001;
         label144: {
            try {
               if (instance == null) {
                  MCUMainManager var0 = new MCUMainManager();
                  instance = var0;
               }
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label144;
            }

            label141:
            try {
               return instance;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               break label141;
            }
         }

         while(true) {
            Throwable var13 = var10000;

            try {
               throw var13;
            } catch (Throwable var10) {
               var10000 = var10;
               var10001 = false;
               continue;
            }
         }
      } else {
         return instance;
      }
   }

   private IMCUMainService getMCUMainService() {
      if (this.mMCUMainService == null) {
         this.mMCUMainService = IMCUMainService.Stub.asInterface(ServiceManager.getService("bhd.mcu.service"));
         this.onServiceConn();
      }

      return IMCUMainService.Stub.asInterface(ServiceManager.getService("bhd.mcu.service"));
   }

   private void notifyMcuConn() {
      Iterator var1 = this.mMCUCanList.iterator();

      while(var1.hasNext()) {
         ((IMCUCanBoxControlListener)var1.next()).onMcuConn();
      }

   }

   private void onServiceConn() {
      ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.MCU.ordinal(), new IServiceConnectListener(this) {
         final MCUMainManager this$0;

         {
            this.this$0 = var1;
         }

         public void onConnected() {
            // $FF: Couldn't be decompiled
         }
      });
   }

   public void clearMessage(MCU_MESSAGE_PEER var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.clearMessage(var1.ordinal());
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void closeDevice() {
      IMCUMainService var1 = this.getMCUMainService();
      if (var1 != null) {
         try {
            var1.closeDevice();
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }
      }

   }

   public int getCarBackState() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            int var1 = var2.getCarBackState();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return -1;
   }

   public int getHardwareReset() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            int var1 = var2.getHardwareReset();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return -1;
   }

   public int getMcuhardware() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            int var1 = var2.getMcuHardware();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return -1;
   }

   public int getPowerOnType() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            int var1 = var2.getPowerOnType();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return -1;
   }

   public int getPowerStatus() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            int var1 = var2.getPowerStatus();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return -1;
   }

   public byte getProcotolVersion() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            byte var1 = var2.getProcotolVersion();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return 0;
   }

   public void getVersion() {
      IMCUMainService var1 = this.getMCUMainService();
      if (var1 != null) {
         try {
            var1.getVersion();
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }
      }

   }

   public void init() {
   }

   public Bundle initMCU() {
      if (LogUtil.log5()) {
         LogUtil.d("initMCU() called");
      }

      IMCUMainService var1 = this.getMCUMainService();
      if (var1 != null) {
         try {
            Bundle var3 = var1.initMCU();
            return var3;
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }
      } else if (LogUtil.log5()) {
         LogUtil.d("initMCU() called mMCUMainService == null");
      }

      return null;
   }

   public void initSystemReady() {
      IMCUMainService var1 = this.getMCUMainService();
      if (var1 != null) {
         try {
            var1.initSystemReady();
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }
      }

   }

   public boolean isInitMCU() {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            boolean var1 = var2.isInitMCU();
            return var1;
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

      return false;
   }

   public void registerMCUCanboxListener(IMCUCanBoxControlListener var1) {
      if (!this.mMCUCanList.contains(var1)) {
         this.mMCUCanList.add(var1);
      }

      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.registerMCUCanboxCallback(this.mMCUCanBoxControlCallback);
            var1.onMcuConn();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   public void registerMCUMainListener(IMCUMainListener var1) {
      this.mMCUMainList.add(var1);
   }

   public void registerMcuMsgListener(IMCUMsgCallback var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.registerMcuMsgListener(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void requestPowerStatus(int var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.requestPowerStatus(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void sendMCUCanboxData(int var1, byte[] var2) {
      IMCUMainService var3 = this.getMCUMainService();
      if (var3 != null) {
         try {
            var3.sendMCUCanboxData(var1, var2);
         } catch (RemoteException var4) {
            var4.printStackTrace();
         }
      }

   }

   public void sendMCUDebugData(byte[] var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.sendMCUDebugData(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void sendTestCmd(byte[] var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.sendTestCmd(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void setSendSleepStatus(boolean var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.setSendSleepStatus(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void setUpgradeStatus(boolean var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.setUpgradeStatus(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public void startSendHeartBeatMsg() {
      IMCUMainService var1 = this.getMCUMainService();
      if (var1 != null) {
         try {
            var1.startSendHeartBeatMsg();
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }
      }

   }

   public void stopSendHeartBeatMsg() {
      IMCUMainService var1 = this.getMCUMainService();
      if (var1 != null) {
         try {
            var1.stopSendHeartBeatMsg();
         } catch (RemoteException var2) {
            var2.printStackTrace();
         }
      }

   }

   public void unRegisterMCUMainListener(IMCUMainListener var1) {
      if (this.mMCUMainList.contains(var1)) {
         this.mMCUMainList.remove(var1);
      }

   }

   public void unregisterMCUCanboxListener(IMCUCanBoxControlListener var1) {
      if (this.mMCUCanList.contains(var1)) {
         this.mMCUCanList.remove(var1);
      }

      if (this.mMCUCanList.isEmpty()) {
         RemoteException var10000;
         label32: {
            boolean var10001;
            IMCUMainService var4;
            try {
               var4 = this.getMCUMainService();
            } catch (RemoteException var3) {
               var10000 = var3;
               var10001 = false;
               break label32;
            }

            if (var4 == null) {
               return;
            }

            try {
               var4.unregisterMCUCanboxCallback(this.mMCUCanBoxControlCallback);
               return;
            } catch (RemoteException var2) {
               var10000 = var2;
               var10001 = false;
            }
         }

         RemoteException var5 = var10000;
         var5.printStackTrace();
      }

   }

   public void unregisterMcuMsgListener(IMCUMsgCallback var1) {
      IMCUMainService var2 = this.getMCUMainService();
      if (var2 != null) {
         try {
            var2.unregisterMcuMsgListener(var1);
         } catch (RemoteException var3) {
            var3.printStackTrace();
         }
      }

   }

   public byte[] updateFlashData(byte[] var1) {
      IMCUMainService var2 = this.getMCUMainService();
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

   public class MCUMainCallback extends IMCUMainCallback.Stub {
      final MCUMainManager this$0;

      public MCUMainCallback(MCUMainManager var1) {
         this.this$0 = var1;
      }

      public void mcuInit(byte var1, boolean var2, boolean var3) throws RemoteException {
         Bundle var4 = new Bundle();
         var4.putByte("procotolVersion", var1);
         var4.putBoolean("powerOnType", var2);
         var4.putBoolean("hardwareReset", var3);
         this.this$0.mMainHandler.obtainMessage(MSG_ID.mcuInit.ordinal(), var4).sendToTarget();
      }

      public void notifyCanboxVersion(String var1) throws RemoteException {
         this.this$0.mMainHandler.obtainMessage(MSG_ID.notifyCanboxVersion.ordinal(), var1).sendToTarget();
      }

      public void notifyHardwareVersion(String var1, String var2, String var3, String var4) throws RemoteException {
         Bundle var5 = new Bundle();
         var5.putString("modelName", var1);
         var5.putString("hardwareVersion", var2);
         var5.putString("serialNum", var3);
         var5.putString("data", var4);
         this.this$0.mMainHandler.obtainMessage(MSG_ID.notifyHardwareVersion.ordinal(), var5).sendToTarget();
      }

      public void notifyMCUVersion(String var1, String var2, String var3) throws RemoteException {
         Bundle var4 = new Bundle();
         var4.putString("softVerion", var1);
         var4.putString("softDate", var2);
         var4.putString("hardwareConfig", var3);
         this.this$0.mMainHandler.obtainMessage(MSG_ID.notifyMCUVersion.ordinal(), var4).sendToTarget();
      }

      public void notifyPowerStatus(int var1) throws RemoteException {
         this.this$0.mMainHandler.obtainMessage(MSG_ID.notifyPowerStatus.ordinal(), var1).sendToTarget();
      }

      public void notifyScreenVersion(String var1) throws RemoteException {
         this.this$0.mMainHandler.obtainMessage(MSG_ID.notifyScreenVersion.ordinal(), var1).sendToTarget();
      }
   }

   private static enum MSG_ID {
      private static final MSG_ID[] $VALUES;
      mcuInit,
      notifyCanboxData,
      notifyCanboxVersion,
      notifyHardwareVersion,
      notifyMCUVersion,
      notifyPowerStatus,
      notifyScreenVersion;

      static {
         MSG_ID var4 = new MSG_ID("notifyCanboxData", 0);
         notifyCanboxData = var4;
         MSG_ID var6 = new MSG_ID("mcuInit", 1);
         mcuInit = var6;
         MSG_ID var5 = new MSG_ID("notifyMCUVersion", 2);
         notifyMCUVersion = var5;
         MSG_ID var1 = new MSG_ID("notifyHardwareVersion", 3);
         notifyHardwareVersion = var1;
         MSG_ID var2 = new MSG_ID("notifyCanboxVersion", 4);
         notifyCanboxVersion = var2;
         MSG_ID var0 = new MSG_ID("notifyScreenVersion", 5);
         notifyScreenVersion = var0;
         MSG_ID var3 = new MSG_ID("notifyPowerStatus", 6);
         notifyPowerStatus = var3;
         $VALUES = new MSG_ID[]{var4, var6, var5, var1, var2, var0, var3};
      }
   }
}
