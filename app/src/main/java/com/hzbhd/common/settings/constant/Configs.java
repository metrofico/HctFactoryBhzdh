package com.hzbhd.common.settings.constant;

import com.hzbhd.common.settings.constant.data.audio.AudioBean;
import com.hzbhd.common.settings.constant.data.audio.AudioDeviceBean;
import com.hzbhd.common.settings.constant.data.audio.AudioGainBean;
import com.hzbhd.common.settings.constant.data.audio.AudioInputGainBean;
import com.hzbhd.common.settings.constant.data.audio.AudioMuteBean;
import com.hzbhd.common.settings.constant.data.audio.AudioNaviBean;
import com.hzbhd.common.settings.constant.data.audio.AudioReverseAudioBean;
import com.hzbhd.common.settings.constant.data.audio.AudioSoundFieldBean;
import com.hzbhd.common.settings.constant.data.audio.AudioSourceVolumeBean;
import com.hzbhd.common.settings.constant.data.audio.AudioSubwooferBean;
import com.hzbhd.common.settings.constant.data.avm.AvmBean;
import com.hzbhd.common.settings.constant.data.bt.BtSettingsBean;
import com.hzbhd.common.settings.constant.data.camera.CameraAc8257Bean;
import com.hzbhd.common.settings.constant.data.camera.CameraSourceBean;
import com.hzbhd.common.settings.constant.data.canbus.CanSettingsBean;
import com.hzbhd.common.settings.constant.data.dab.DabBean;
import com.hzbhd.common.settings.constant.data.disc.DiscSettingsBean;
import com.hzbhd.common.settings.constant.data.eq.EqBaseSettingsBean;
import com.hzbhd.common.settings.constant.data.hardware.HardWareRadioBean;
import com.hzbhd.common.settings.constant.data.hardware.HardwareReverseDetectBean;
import com.hzbhd.common.settings.constant.data.mcu.McuBrightnessBean;
import com.hzbhd.common.settings.constant.data.mcu.McuColorLedBean;
import com.hzbhd.common.settings.constant.data.mcu.McuFSettingBean;
import com.hzbhd.common.settings.constant.data.mcu.McuGeneralBean;
import com.hzbhd.common.settings.constant.data.mcu.McuPowerBean;
import com.hzbhd.common.settings.constant.data.mcu.McuQuickChargeBean;
import com.hzbhd.common.settings.constant.data.mcu.McuSwcLearnLevelBean;
import com.hzbhd.common.settings.constant.data.mcu.McuVcomBean;
import com.hzbhd.common.settings.constant.data.mcu.McuVideoBean;
import com.hzbhd.common.settings.constant.data.media.MediaBean;
import com.hzbhd.common.settings.constant.data.mediascanner.MediaScannerBean;
import com.hzbhd.common.settings.constant.data.misc.MiscReverseBean;
import com.hzbhd.common.settings.constant.data.setting.SettingCameraBean;
import com.hzbhd.common.settings.constant.data.setting.SettingColorLED;
import com.hzbhd.common.settings.constant.data.setting.SettingFAppBean;
import com.hzbhd.common.settings.constant.data.setting.SettingFModuleBean;
import com.hzbhd.common.settings.constant.data.setting.SettingFactoryBean;
import com.hzbhd.common.settings.constant.data.setting.SettingInfoBean;
import com.hzbhd.common.settings.constant.data.setting.SettingInfoDeviceBean;
import com.hzbhd.common.settings.constant.data.setting.SettingInitBean;
import com.hzbhd.common.settings.constant.data.setting.SettingItemHideBean;
import com.hzbhd.common.settings.constant.data.setting.SettingMainBean;
import com.hzbhd.common.settings.constant.data.source.SourceHideAppsBean;
import com.hzbhd.common.settings.constant.data.source.SourceNaviBean;
import com.hzbhd.common.settings.constant.data.system.SystemStatusBean;
import com.hzbhd.common.settings.constant.data.systemui.SystemUiBean;
import com.hzbhd.common.settings.constant.data.video.VideoAvmBean;
import com.hzbhd.common.settings.constant.data.video.VideoCamerasBean;
import com.hzbhd.common.settings.constant.data.video.VideoHardwareBean;
import com.hzbhd.common.settings.constant.data.video.VideoReverseBean;

public class Configs {
   public static enum Module {
      private static final Module[] $VALUES;
      AVM_avm,
      Audio_audio,
      Audio_bu32107_subwoofer,
      Audio_bu37534_subwoofer,
      Audio_device,
      Audio_gain,
      Audio_input_gain_bu32107,
      Audio_input_gain_bu37534,
      Audio_input_gain_tda7719,
      Audio_mute,
      Audio_navi_audio,
      Audio_reverse_audio,
      Audio_sound_field,
      Audio_source_volume,
      Audio_tda7719_subwoofer,
      Bt_settings,
      Camera_Ac8257,
      Camera_Source,
      Can_settings,
      Dab_dab,
      Disc_settings,
      Eq_basesettings,
      Hardware_radio,
      Hardware_reversedetect,
      Mcu_brightness,
      Mcu_color_led,
      Mcu_f_setting,
      Mcu_general,
      Mcu_power,
      Mcu_quick_charge,
      Mcu_swc_learn_level,
      Mcu_vcom,
      Mcu_video,
      MediaScanner_config,
      Media_media,
      Misc_Reverse,
      Setting_camera,
      Setting_colorled,
      Setting_customized,
      Setting_f_app,
      Setting_f_module,
      Setting_factory,
      Setting_info,
      Setting_info_device,
      Setting_init,
      Setting_item_hide,
      Setting_main,
      Source_hideapps,
      Source_navi,
      SystemUI_config,
      System_status,
      Video_avm,
      Video_cameras,
      Video_reverse,
      Video_videohardware;

      private Class clzz;
      private String dirPath;
      private String fileName;

      static {
         Module var34 = new Module("Setting_main", 0, "/bhd/appconfig/Settings/", "setting_main.json", SettingMainBean.class);
         Setting_main = var34;
         Module var51 = new Module("Setting_item_hide", 1, "/bhd/appconfig/Settings/", "setting_hide_item.json", SettingItemHideBean.class);
         Setting_item_hide = var51;
         Module var49 = new Module("Setting_f_module", 2, "/bhd/appconfig/Settings/", "setting_f_module.json", SettingFModuleBean.class);
         Setting_f_module = var49;
         Module var46 = new Module("Setting_f_app", 3, "/bhd/appconfig/Settings/", "setting_f_app.json", SettingFAppBean.class);
         Setting_f_app = var46;
         Module var40 = new Module("Setting_customized", 4, "/bhd/appconfig/Settings/", "setting_customized.json", (Class)null);
         Setting_customized = var40;
         Module var48 = new Module("Setting_info", 5, "/bhd/appconfig/Settings/", "setting_info.json", SettingInfoBean.class);
         Setting_info = var48;
         Module var47 = new Module("Setting_info_device", 6, "/bhd/appconfig/Settings/", "setting_info_device.json", SettingInfoDeviceBean.class);
         Setting_info_device = var47;
         Module var53 = new Module("Setting_colorled", 7, "/bhd/appconfig/Settings/", "setting_colorled.json", SettingColorLED.class);
         Setting_colorled = var53;
         Module var43 = new Module("Setting_init", 8, "/bhd/appconfig/Settings/", "setting_init.json", SettingInitBean.class);
         Setting_init = var43;
         Module var2 = new Module("Setting_factory", 9, "/bhd/appconfig/Settings/", "setting_factory.json", SettingFactoryBean.class);
         Setting_factory = var2;
         Module var26 = new Module("Setting_camera", 10, "/bhd/appconfig/Settings/", "setting_camera.json5", SettingCameraBean.class);
         Setting_camera = var26;
         Module var35 = new Module("Video_videohardware", 11, "/bhd/appconfig/Video/", "video_hardware.json", VideoHardwareBean.class);
         Video_videohardware = var35;
         Module var23 = new Module("Video_cameras", 12, "/bhd/appconfig/Video/", "Cameras.json", VideoCamerasBean.class);
         Video_cameras = var23;
         Module var4 = new Module("Video_reverse", 13, "/bhd/appconfig/Video/", "Reverse.json", VideoReverseBean.class);
         Video_reverse = var4;
         Module var45 = new Module("Video_avm", 14, "/bhd/appconfig/Video/", "AVM.json", VideoAvmBean.class);
         Video_avm = var45;
         Module var10 = new Module("Mcu_color_led", 15, "/bhd/appconfig/McuService/", "ColorLed.json", McuColorLedBean.class);
         Mcu_color_led = var10;
         Module var41 = new Module("Mcu_swc_learn_level", 16, "/bhd/appconfig/McuService/", "SwcLearning.json", McuSwcLearnLevelBean.class);
         Mcu_swc_learn_level = var41;
         Module var38 = new Module("Mcu_video", 17, "/bhd/appconfig/McuService/", "Video.json", McuVideoBean.class);
         Mcu_video = var38;
         Module var27 = new Module("Mcu_brightness", 18, "/bhd/appconfig/McuService/", "Brightness.json", McuBrightnessBean.class);
         Mcu_brightness = var27;
         Module var5 = new Module("Mcu_power", 19, "/bhd/appconfig/McuService/", "Power.json", McuPowerBean.class);
         Mcu_power = var5;
         Module var37 = new Module("Mcu_vcom", 20, "/bhd/appconfig/McuService/", "Vcom.json", McuVcomBean.class);
         Mcu_vcom = var37;
         Module var20 = new Module("Mcu_f_setting", 21, "/bhd/appconfig/McuService/", "FactorySettings.json", McuFSettingBean.class);
         Mcu_f_setting = var20;
         Module var6 = new Module("Mcu_quick_charge", 22, "/bhd/appconfig/McuService/", "QuickCharge.json", McuQuickChargeBean.class);
         Mcu_quick_charge = var6;
         Module var29 = new Module("Mcu_general", 23, "/bhd/appconfig/McuService/", "General.json", McuGeneralBean.class);
         Mcu_general = var29;
         Module var9 = new Module("System_status", 24, "/bhd/appconfig/System/", "SystemStatus.json", SystemStatusBean.class);
         System_status = var9;
         Module var44 = new Module("Audio_audio", 25, "/bhd/appconfig/AudioService/", "Audio.json", AudioBean.class);
         Audio_audio = var44;
         Module var13 = new Module("Audio_gain", 26, "/bhd/appconfig/AudioService/", "AudioGain.json", AudioGainBean.class);
         Audio_gain = var13;
         Module var11 = new Module("Audio_input_gain_bu32107", 27, "/bhd/appconfig/AudioService/", "Bu32107_InputGain.json", AudioInputGainBean.class);
         Audio_input_gain_bu32107 = var11;
         Module var33 = new Module("Audio_input_gain_tda7719", 28, "/bhd/appconfig/AudioService/", "Tda7719_InputGain.json", AudioInputGainBean.class);
         Audio_input_gain_tda7719 = var33;
         Module var15 = new Module("Audio_input_gain_bu37534", 29, "/bhd/appconfig/AudioService/", "Bu37534_InputGain.json", AudioInputGainBean.class);
         Audio_input_gain_bu37534 = var15;
         Module var3 = new Module("Audio_device", 30, "/bhd/appconfig/AudioService/", "AudioDevice.json", AudioDeviceBean.class);
         Audio_device = var3;
         Module var1 = new Module("Audio_navi_audio", 31, "/bhd/appconfig/AudioService/", "NaviAudio.json", AudioNaviBean.class);
         Audio_navi_audio = var1;
         Module var22 = new Module("Audio_mute", 32, "/bhd/appconfig/AudioService/", "Mute.json", AudioMuteBean.class);
         Audio_mute = var22;
         Module var52 = new Module("Audio_source_volume", 33, "/bhd/appconfig/AudioService/", "SourceVolume.json", AudioSourceVolumeBean.class);
         Audio_source_volume = var52;
         Module var24 = new Module("Audio_reverse_audio", 34, "/bhd/appconfig/AudioService/", "ReverseAudio.json", AudioReverseAudioBean.class);
         Audio_reverse_audio = var24;
         Module var39 = new Module("Audio_sound_field", 35, "/bhd/appconfig/AudioService/", "SoundField.json", AudioSoundFieldBean.class);
         Audio_sound_field = var39;
         Module var32 = new Module("Audio_bu32107_subwoofer", 36, "/bhd/appconfig/AudioService/", "Bu32107_Subwoofer.json", AudioSubwooferBean.class);
         Audio_bu32107_subwoofer = var32;
         Module var50 = new Module("Audio_tda7719_subwoofer", 37, "/bhd/appconfig/AudioService/", "Tda7719_Subwoofer.json", AudioSubwooferBean.class);
         Audio_tda7719_subwoofer = var50;
         Module var14 = new Module("Audio_bu37534_subwoofer", 38, "/bhd/appconfig/AudioService/", "Bu37534_Subwoofer.json", AudioSubwooferBean.class);
         Audio_bu37534_subwoofer = var14;
         Module var16 = new Module("Hardware_radio", 39, "/bhd/appconfig/HardwareService/", "Radio.json", HardWareRadioBean.class);
         Hardware_radio = var16;
         Module var36 = new Module("Hardware_reversedetect", 40, "/bhd/appconfig/HardwareService/", "ReverseDetect.json", HardwareReverseDetectBean.class);
         Hardware_reversedetect = var36;
         Module var7 = new Module("Can_settings", 41, "/bhd/appconfig/CanBus/", "can_settings.json", CanSettingsBean.class);
         Can_settings = var7;
         Module var54 = new Module("Disc_settings", 42, "/bhd/appconfig/Disc/", "disc_setting.json", DiscSettingsBean.class);
         Disc_settings = var54;
         Module var30 = new Module("Camera_Ac8257", 43, "/bhd/appconfig/CameraService/", "CameraAc8257.json", CameraAc8257Bean.class);
         Camera_Ac8257 = var30;
         Module var8 = new Module("Camera_Source", 44, "/bhd/appconfig/CameraService/", "SourceCamera.json", CameraSourceBean.class);
         Camera_Source = var8;
         Module var18 = new Module("AVM_avm", 45, "/bhd/appconfig/AVM/", "avm.json", AvmBean.class);
         AVM_avm = var18;
         Module var19 = new Module("SystemUI_config", 46, "/bhd/appconfig/SystemUI/", "systemui.json", SystemUiBean.class);
         SystemUI_config = var19;
         Module var31 = new Module("Source_navi", 47, "/bhd/appconfig/SourceService/", "NaviSource.json", SourceNaviBean.class);
         Source_navi = var31;
         Module var21 = new Module("Source_hideapps", 48, "/bhd/appconfig/SourceService/", "HideApps.json", SourceHideAppsBean.class);
         Source_hideapps = var21;
         Module var28 = new Module("Misc_Reverse", 49, "/bhd/appconfig/Misc/", "Reverse.json", MiscReverseBean.class);
         Misc_Reverse = var28;
         Module var17 = new Module("MediaScanner_config", 50, "/bhd/appconfig/MediaScanner/", "mediascanner.json", MediaScannerBean.class);
         MediaScanner_config = var17;
         Module var42 = new Module("Bt_settings", 51, "/bhd/appconfig/Bt/", "btSettings.json", BtSettingsBean.class);
         Bt_settings = var42;
         Module var12 = new Module("Eq_basesettings", 52, "/bhd/appconfig/EqApp/", "BaseSettings.json", EqBaseSettingsBean.class);
         Eq_basesettings = var12;
         Module var0 = new Module("Media_media", 53, "/bhd/appconfig/Media/", "media.json", MediaBean.class);
         Media_media = var0;
         Module var25 = new Module("Dab_dab", 54, "/bhd/appconfig/DAB/", "Dab.json", DabBean.class);
         Dab_dab = var25;
         $VALUES = new Module[]{var34, var51, var49, var46, var40, var48, var47, var53, var43, var2, var26, var35, var23, var4, var45, var10, var41, var38, var27, var5, var37, var20, var6, var29, var9, var44, var13, var11, var33, var15, var3, var1, var22, var52, var24, var39, var32, var50, var14, var16, var36, var7, var54, var30, var8, var18, var19, var31, var21, var28, var17, var42, var12, var0, var25};
      }

      private Module(String var3, String var4, Class var5) {
         this.dirPath = var3;
         this.fileName = var4;
         this.clzz = var5;
      }

      public Class getClazz() {
         return this.clzz;
      }

      public String getDirPath() {
         return this.dirPath;
      }

      public String getFileName() {
         return this.fileName;
      }
   }
}
