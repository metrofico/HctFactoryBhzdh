package com.hzbhd.common.settings.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BodaSysContant {
   public static final String AudioWhiteIntent = "KEY_FORCE_BACKPLAYAUDIO_THRIDAPP_WHITE_LIST";
   public static final String BrowserIntent = "car_browser_intent";
   public static final String BubbleState = "bubble_state";
   public static final String DisableHDMI = "HZBHD_DISABLE_HDMI";
   public static final String DisableRotation = "HZBHD_DISABLE_ROTATION";
   public static final String DvrIntent = "car_dvr_intent";
   public static final String FullScreenIntent = "KEY_FORCE_FULL_SCREEN_THRIDAPP_WHITE_LIST";
   public static final String IRCurVersion = "IRCurVersion";
   public static final String OriginalScreenTest = "ORIGINAL_SCREEN_TEST";
   public static final String ScreenshotLocation = "hzbhd_screenshot_location";
   public static final String ShowVirtualKey = "VIRTUAL_KEY_SHOW";
   public static final String SwitchSeat = "switch_seat";
   public static final String unknown = "unknown";

   public static final class BT {
      public static final String VerInfo = "hzbhd_bt_version_info";

      public static enum Module {
         private static final Module[] $VALUES;
         BLINK,
         BLINK161,
         BLINKNETWORK,
         GOC3008,
         GOC806A,
         GOCQD041,
         GOCRF210,
         GOCWA020,
         IVT140,
         IVTI159,
         IVTI35,
         SUD,
         SUD828,
         SUD851;

         private int code;
         private String name;

         static {
            Module var0 = new Module("GOC806A", 0, "GOC", 0);
            GOC806A = var0;
            Module var5 = new Module("IVT140", 1, "IVT", 1);
            IVT140 = var5;
            Module var1 = new Module("SUD", 2, "SUD", 2);
            SUD = var1;
            Module var8 = new Module("SUD851", 3, "SUD-851", 3);
            SUD851 = var8;
            Module var7 = new Module("BLINK", 4, "BLINK", 4);
            BLINK = var7;
            Module var2 = new Module("IVTI35", 5, "IVT-I35", 5);
            IVTI35 = var2;
            Module var10 = new Module("SUD828", 6, "SUD-828", 6);
            SUD828 = var10;
            Module var11 = new Module("IVTI159", 7, "IVT-I159", 7);
            IVTI159 = var11;
            Module var12 = new Module("BLINKNETWORK", 8, "BLINK_NETWORK", 8);
            BLINKNETWORK = var12;
            Module var13 = new Module("GOCWA020", 9, "GOC-WA020", 9);
            GOCWA020 = var13;
            Module var4 = new Module("BLINK161", 10, "BLINK161", 10);
            BLINK161 = var4;
            Module var6 = new Module("GOCQD041", 11, "GOC-QD041", 11);
            GOCQD041 = var6;
            Module var9 = new Module("GOCRF210", 12, "GOC-RF210", 12);
            GOCRF210 = var9;
            Module var3 = new Module("GOC3008", 13, "GOC-3008", 13);
            GOC3008 = var3;
            $VALUES = new Module[]{var0, var5, var1, var8, var7, var2, var10, var11, var12, var13, var4, var6, var9, var3};
         }

         private Module(String var3, int var4) {
            this.name = var3;
            this.code = var4;
         }

         public static List toList() {
            ArrayList var3 = new ArrayList();
            Module[] var2 = values();
            int var1 = var2.length;

            for(int var0 = 0; var0 < var1; ++var0) {
               Module var4 = var2[var0];
               HashMap var5 = new HashMap();
               var5.put("code", var4.getCode());
               var5.put("name", var4.getName());
               var3.add(var5);
            }

            return var3;
         }

         public int getCode() {
            return this.code;
         }

         public String getName() {
            return this.name;
         }
      }
   }

   public static final class Brightness {
      public static final String ActionKeyCurrentStatus = "currentBrightness";
      public static final String ActionStatus = "com.hzbhd.action.brightnesschange";
      public static final int MAX = 5;
      public static final int MIN = 1;
   }

   public static final class Can {
      public static final String AirDisplaySetup = "hzbhd_air_display_setup";
      public static final String CanEQ = "factory_module_amplifier";
      public static final String CanSystemVersion = "can_system_version";
      public static final String CarType = "hzbhd_car_type";
      public static final String HzbhdRefreshCanVersion = "hzbhdrefreshCanVersion";
      public static final String RadarDisp = "RADAR_DISP_CHECK";
      public static final String RefreshCanVersion = "refreshCanVersion";
      public static final String SwitchAcTemperature = "hzbhd_switch_ac_temperature";
   }

   public static final class CarSettings {
      public static final class Sign {
         public static final String Click = "click";
         public static final String DvrSelect = "dvrselect";
         public static final String Factory = "factory";
         public static final String GeneralFragment = "generalfragment";
         public static final String LedFragment = "ledsetfragment";
         public static final String LogoFragment = "carlogoselectfragment";
         public static final String Main = "main";
         public static final String NaviFragment = "navifragment";
         public static final String SwcFragment = "swcfragment";
         public static final String Tips = "tips";
         public static final String Tips_Click = "tips&click";
         public static final String VolFragment = "volsetfragment";
      }

      public static final class To {
         public static final String KEY = "car.settings.action";
         public static final String NaviValue = "main.navifragment.tips.stvradertrack";
         public static final String NaviValue2 = "main.navifragment.click.stvradertrack";
         public static final String NaviValue3 = "factory.navifragment.tips&click.stvradertrack";
         public static final String SelectCarLogo = "main.carlogoselectfragment";
         public static final String SelectDvr = "main.generalfragment.tips.dvrselect";
         public static final String SelectLedColor = "main.ledsetfragment";
         public static final String SelectNaviApp = "main.navifragment";
         public static final String SelectSwc = "main.swcfragment";
         public static final String SelectVol = "main.volsetfragment";
      }
   }

   public static final class Carema {
      public static final String AvmDelayFinish = "carsettings.avm.delay.finsh";
      public static final String SwitchFrontCamera = "hzbhd_switch_front_camera";
   }

   public static final class Navi {
      public static final String NaviChangedAction = "com.hzbhd.action.NAVI_CHANGED";
      public static final String NaviIntent = "car_navi_intent";
      public static final String NaviPackageNameExtra = "naviPackageName";
   }

   public static final class Radio {
      public static final class Module {
         public static final int MCU_Control = 0;
         public static final int MPU_6686 = 4;
         public static final int MPU_7708 = 2;
         public static final int MPU_7786 = 3;
         public static final int MPU_ST7707 = 1;
      }
   }

   public static enum SETTING_SET {
      private static final SETTING_SET[] $VALUES;
      none,
      reset;

      static {
         SETTING_SET var0 = new SETTING_SET("none", 0);
         none = var0;
         SETTING_SET var1 = new SETTING_SET("reset", 1);
         reset = var1;
         $VALUES = new SETTING_SET[]{var0, var1};
      }
   }

   public static final class SplitScreen {
      public static final String ActionToN = "com.hzbhd.split_screen";
      public static final String OpenKey = "split_screen";
      public static final String SwitchSplit = "SWITCH_SPLIT";
   }

   public static final class System {
      public static final class Attributes {
         public static final String _701F = "701F";
         public static final String _N3 = "N3";
         public static final String _NVP6158C = "NVP6158C";
         public static final String _STM32 = "STM32";
         public static final String _STM8 = "STM8";
      }

      public static final class BUILD {
         public static final String BspVersionName = "ro.bhd.bsp.version";
         public static final String BuildVersion = "build.version";
         public static final String FrameworkVersionName = "ro.bhd.framework.version";
         public static final String HardwardBoard = "persist.bhd.hardward_board";
         public static final String SerialNumber = "null";
      }
   }

   public static enum TypeDef {
      private static final TypeDef[] $VALUES;
      GET,
      SETTING;

      static {
         TypeDef var0 = new TypeDef("SETTING", 0);
         SETTING = var0;
         TypeDef var1 = new TypeDef("GET", 1);
         GET = var1;
         $VALUES = new TypeDef[]{var0, var1};
      }
   }
}
