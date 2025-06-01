package com.hzbhd.config.use;

import android.app.DefaultSharedUtil;
import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002!\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\u0007J\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\u0007J\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0007J\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0007¨\u0006#"},
   d2 = {"Lcom/hzbhd/config/use/CanBus;", "", "()V", "getCameraChannelSelect", "", "getCameraConfiguration", "getCanBaud_Rate", "", "getCanPackType", "getCanType", "getDifferentId", "getEachId", "getMsK9New160Config", "getSelectCarPosition", "isShowApp", "setBaud_Rate", "", "baud_rate", "setCameraConfiguration", "isHighConfiguration", "setCanPackType", "packtype", "setCanType", "canType", "setDifferentId", "id", "setEachId", "setIsShowApp", "value", "setMsK9New160Config", "isNew160", "setSelectCarPosition", "position", "CanBusKey", "MsKey", "CanBus-config_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CanBus {
   public static final CanBus INSTANCE = new CanBus();

   private CanBus() {
   }

   public final boolean getCameraChannelSelect() {
      return CanBusSpecialConfig.INSTANCE.cameraSupportHighConfigurationID(this.getCanType()) ? this.getCameraConfiguration() : false;
   }

   public final boolean getCameraConfiguration() {
      return DefaultSharedUtil.getBool(CanBus.CanBusKey.canbus_camera_configuration.name(), CanBusDefault.INSTANCE.getCameraConfiguration());
   }

   public final int getCanBaud_Rate() {
      return DefaultSharedUtil.getInt(CanBus.CanBusKey.canbus_i_canbus_baud_rate.name(), CanBusDefault.INSTANCE.getCanBaud_Rate());
   }

   public final int getCanPackType() {
      return DefaultSharedUtil.getInt(CanBus.CanBusKey.canbus_i_canbus_packtype.name(), CanBusDefault.INSTANCE.getCanPackType());
   }

   public final int getCanType() {
      return DefaultSharedUtil.getInt(CanBus.CanBusKey.canbus_i_cantype.name(), CanBusDefault.INSTANCE.getCanType());
   }

   public final int getDifferentId() {
      return DefaultSharedUtil.getInt(CanBus.CanBusKey.canbus_i_different_id.name(), CanBusDefault.INSTANCE.getDifferentId());
   }

   public final int getEachId() {
      return DefaultSharedUtil.getInt(CanBus.CanBusKey.canbus_i_each_id.name(), CanBusDefault.INSTANCE.getEachId());
   }

   public final boolean getMsK9New160Config() {
      return DefaultSharedUtil.getBool(CanBus.MsKey.canbus_b_ms_k9_new160configuration.name(), CanBusDefault.INSTANCE.isMsK9New160Configuration());
   }

   public final int getSelectCarPosition() {
      return DefaultSharedUtil.getInt(CanBus.CanBusKey.canbus_i_select_car_position.name(), CanBusDefault.INSTANCE.getSelectCarPosition());
   }

   public final boolean isShowApp() {
      return DefaultSharedUtil.getBool(CanBus.CanBusKey.canbus_b_showapp.name(), CanBusDefault.INSTANCE.isShowApp());
   }

   public final void setBaud_Rate(int var1) {
      DefaultSharedUtil.setInt(CanBus.CanBusKey.canbus_i_canbus_baud_rate.name(), var1);
   }

   public final void setCameraConfiguration(boolean var1) {
      DefaultSharedUtil.setBool(CanBus.CanBusKey.canbus_camera_configuration.name(), var1);
   }

   public final void setCanPackType(int var1) {
      DefaultSharedUtil.setInt(CanBus.CanBusKey.canbus_i_canbus_packtype.name(), var1);
   }

   public final void setCanType(int var1) {
      DefaultSharedUtil.setInt(CanBus.CanBusKey.canbus_i_cantype.name(), var1);
   }

   public final void setDifferentId(int var1) {
      DefaultSharedUtil.setInt(CanBus.CanBusKey.canbus_i_different_id.name(), var1);
   }

   public final void setEachId(int var1) {
      DefaultSharedUtil.setInt(CanBus.CanBusKey.canbus_i_each_id.name(), var1);
   }

   public final void setIsShowApp(boolean var1) {
      DefaultSharedUtil.setBool(CanBus.CanBusKey.canbus_b_showapp.name(), var1);
   }

   public final void setMsK9New160Config(boolean var1) {
      DefaultSharedUtil.setBool(CanBus.MsKey.canbus_b_ms_k9_new160configuration.name(), var1);
   }

   public final void setSelectCarPosition(int var1) {
      DefaultSharedUtil.setInt(CanBus.CanBusKey.canbus_i_select_car_position.name(), var1);
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"},
      d2 = {"Lcom/hzbhd/config/use/CanBus$CanBusKey;", "", "(Ljava/lang/String;I)V", "canbus_b_showapp", "canbus_i_cantype", "canbus_i_each_id", "canbus_i_different_id", "canbus_i_select_car_position", "canbus_b_setting_show", "canbus_i_canbus_baud_rate", "canbus_i_canbus_packtype", "canbus_camera_configuration", "CanBus-config_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum CanBusKey {
      private static final CanBusKey[] $VALUES = $values();
      canbus_b_setting_show,
      canbus_b_showapp,
      canbus_camera_configuration,
      canbus_i_canbus_baud_rate,
      canbus_i_canbus_packtype,
      canbus_i_cantype,
      canbus_i_different_id,
      canbus_i_each_id,
      canbus_i_select_car_position;

      // $FF: synthetic method
      private static final CanBusKey[] $values() {
         return new CanBusKey[]{canbus_b_showapp, canbus_i_cantype, canbus_i_each_id, canbus_i_different_id, canbus_i_select_car_position, canbus_b_setting_show, canbus_i_canbus_baud_rate, canbus_i_canbus_packtype, canbus_camera_configuration};
      }
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003¨\u0006\u0004"},
      d2 = {"Lcom/hzbhd/config/use/CanBus$MsKey;", "", "(Ljava/lang/String;I)V", "canbus_b_ms_k9_new160configuration", "CanBus-config_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum MsKey {
      private static final MsKey[] $VALUES = $values();
      canbus_b_ms_k9_new160configuration;

      // $FF: synthetic method
      private static final MsKey[] $values() {
         return new MsKey[]{canbus_b_ms_k9_new160configuration};
      }
   }
}
