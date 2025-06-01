package com.hzbhd.common.settings.constant.data.setting;

public class SettingInfoBean {
   public static enum Key {
      private static final Key[] $VALUES;
      android_version,
      bsp_version,
      bt_name,
      bt_version,
      can_version,
      core,
      ddr_type,
      dpi,
      framework_vesion,
      hardward_board,
      ir_version,
      is_6158c,
      is_701f,
      is_bt_5,
      is_ir,
      is_n3,
      is_stm32,
      is_stm8,
      platform,
      qr_code,
      ram,
      rom,
      uboot_version;

      static {
         Key var4 = new Key("android_version", 0);
         android_version = var4;
         Key var17 = new Key("platform", 1);
         platform = var17;
         Key var13 = new Key("core", 2);
         core = var13;
         Key var22 = new Key("rom", 3);
         rom = var22;
         Key var20 = new Key("ddr_type", 4);
         ddr_type = var20;
         Key var6 = new Key("ram", 5);
         ram = var6;
         Key var11 = new Key("dpi", 6);
         dpi = var11;
         Key var3 = new Key("hardward_board", 7);
         hardward_board = var3;
         Key var8 = new Key("qr_code", 8);
         qr_code = var8;
         Key var12 = new Key("uboot_version", 9);
         uboot_version = var12;
         Key var16 = new Key("bsp_version", 10);
         bsp_version = var16;
         Key var1 = new Key("framework_vesion", 11);
         framework_vesion = var1;
         Key var0 = new Key("bt_name", 12);
         bt_name = var0;
         Key var5 = new Key("bt_version", 13);
         bt_version = var5;
         Key var2 = new Key("is_bt_5", 14);
         is_bt_5 = var2;
         Key var10 = new Key("is_701f", 15);
         is_701f = var10;
         Key var18 = new Key("is_6158c", 16);
         is_6158c = var18;
         Key var9 = new Key("is_n3", 17);
         is_n3 = var9;
         Key var7 = new Key("is_stm32", 18);
         is_stm32 = var7;
         Key var19 = new Key("is_stm8", 19);
         is_stm8 = var19;
         Key var21 = new Key("can_version", 20);
         can_version = var21;
         Key var14 = new Key("is_ir", 21);
         is_ir = var14;
         Key var15 = new Key("ir_version", 22);
         ir_version = var15;
         $VALUES = new Key[]{var4, var17, var13, var22, var20, var6, var11, var3, var8, var12, var16, var1, var0, var5, var2, var10, var18, var9, var7, var19, var21, var14, var15};
      }
   }
}
