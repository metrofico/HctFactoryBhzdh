package com.hzbhd.canbus.config;

import android.app.DefaultSharedUtil;
import android.util.Log;
import com.hzbhd.config.use.CanBus;
import com.softwinner.SystemMix;
import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"},
   d2 = {"Lcom/hzbhd/canbus/config/CanbusConfig;", "", "()V", "TAG", "", "getCameraConfiguration", "", "getCanBaud_Rate", "", "getCanPackType", "getCanType", "getDifferentId", "getEachId", "getSelectCarPosition", "isShowApp", "setBaud_Rate", "", "baud_rate", "setCameraConfiguration", "isHighConfiguration", "setCanPackType", "packtype", "setCanType", "canType", "setDifferentId", "id", "setEachId", "setIsShowApp", "setSelectCarPosition", "position", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CanbusConfig {
   public static final CanbusConfig INSTANCE = new CanbusConfig();
   private static final String TAG = "CanbusConfig";

   private CanbusConfig() {
   }

   public final boolean getCameraConfiguration() {
      return CanBus.INSTANCE.getCameraConfiguration();
   }

   public final int getCanBaud_Rate() {
      return CanBus.INSTANCE.getCanBaud_Rate();
   }

   public final int getCanPackType() {
      return CanBus.INSTANCE.getCanPackType();
   }

   public final int getCanType() {
      return CanBus.INSTANCE.getCanType();
   }

   public final int getDifferentId() {
      return CanBus.INSTANCE.getDifferentId();
   }

   public final int getEachId() {
      return CanBus.INSTANCE.getEachId();
   }

   public final int getSelectCarPosition() {
      return CanBus.INSTANCE.getSelectCarPosition();
   }

   public final boolean isShowApp() {
      return CanBus.INSTANCE.isShowApp();
   }

   public final void setBaud_Rate(int var1) {
      CanBus.INSTANCE.setBaud_Rate(var1);
   }

   public final void setCameraConfiguration(boolean var1) {
      Log.i("CanbusConfig", "setCameraConfiguration: " + var1);
      CanBus.INSTANCE.setCameraConfiguration(var1);
   }

   public final void setCanPackType(int var1) {
      CanBus.INSTANCE.setCanPackType(var1);
   }

   public final void setCanType(int var1) {
      Log.i("CanbusConfig", "setCanType: commit and sync  " + var1);
      CanBus.INSTANCE.setCanType(var1);
      DefaultSharedUtil.commit();
      SystemMix.bhd_sync();
   }

   public final void setDifferentId(int var1) {
      Log.i("CanbusConfig", "setDifferentId: " + var1);
      CanBus.INSTANCE.setDifferentId(var1);
   }

   public final void setEachId(int var1) {
      Log.i("CanbusConfig", "setEachId: " + var1);
      CanBus.INSTANCE.setEachId(var1);
   }

   public final void setIsShowApp(boolean var1) {
      Log.i("CanbusConfig", "setIsShowApp: " + var1);
      CanBus.INSTANCE.setIsShowApp(var1);
   }

   public final void setSelectCarPosition(int var1) {
      Log.i("CanbusConfig", "setSelectCarPosition: " + var1);
      CanBus.INSTANCE.setSelectCarPosition(var1);
   }
}
