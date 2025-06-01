package com.hzbhd.common.settings.constant.data.hardware;

public class HardWareRadioBean {
   public static enum Key {
      private static final Key[] $VALUES;
      af_match_in_low_byte,
      auto_seek,
      current_area_id,
      i2c_addr,
      i2c_port,
      mpu_am_level,
      mpu_am_local_level,
      mpu_fm_level,
      mpu_fm_local_level,
      radio_antenna_power,
      radiochip_autocheck,
      rds_default,
      rds_disable,
      scan_type,
      seek_thtype,
      tuner_id,
      tuner_type,
      ui_af,
      ui_rds,
      ui_reg,
      use_i2s;

      static {
         Key var20 = new Key("tuner_type", 0);
         tuner_type = var20;
         Key var14 = new Key("tuner_id", 1);
         tuner_id = var14;
         Key var16 = new Key("i2c_port", 2);
         i2c_port = var16;
         Key var17 = new Key("i2c_addr", 3);
         i2c_addr = var17;
         Key var3 = new Key("rds_disable", 4);
         rds_disable = var3;
         Key var18 = new Key("current_area_id", 5);
         current_area_id = var18;
         Key var9 = new Key("mpu_fm_local_level", 6);
         mpu_fm_local_level = var9;
         Key var4 = new Key("mpu_fm_level", 7);
         mpu_fm_level = var4;
         Key var12 = new Key("mpu_am_local_level", 8);
         mpu_am_local_level = var12;
         Key var5 = new Key("mpu_am_level", 9);
         mpu_am_level = var5;
         Key var0 = new Key("scan_type", 10);
         scan_type = var0;
         Key var10 = new Key("auto_seek", 11);
         auto_seek = var10;
         Key var1 = new Key("seek_thtype", 12);
         seek_thtype = var1;
         Key var19 = new Key("use_i2s", 13);
         use_i2s = var19;
         Key var15 = new Key("radio_antenna_power", 14);
         radio_antenna_power = var15;
         Key var6 = new Key("rds_default", 15);
         rds_default = var6;
         Key var2 = new Key("af_match_in_low_byte", 16);
         af_match_in_low_byte = var2;
         Key var7 = new Key("radiochip_autocheck", 17);
         radiochip_autocheck = var7;
         Key var8 = new Key("ui_rds", 18);
         ui_rds = var8;
         Key var11 = new Key("ui_af", 19);
         ui_af = var11;
         Key var13 = new Key("ui_reg", 20);
         ui_reg = var13;
         $VALUES = new Key[]{var20, var14, var16, var17, var3, var18, var9, var4, var12, var5, var0, var10, var1, var19, var15, var6, var2, var7, var8, var11, var13};
      }
   }
}
