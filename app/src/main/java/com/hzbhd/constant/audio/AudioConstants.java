package com.hzbhd.constant.audio;

public class AudioConstants {
   public static int EQTYPE_USER0;
   public static final int FS = 44100;
   public static final int SourceVolumeDef_Switch_AllVolDef = 3;
   public static final int SourceVolumeDef_Switch_BigVolDef = 1;
   public static final int SourceVolumeDef_Switch_Off = 0;
   public static final int SourceVolumeDef_Switch_SmallVolDef = 2;

   public static int getShareVolume(int var0, int var1, boolean var2, boolean var3) {
      short var5 = 0;
      int var4;
      if (var3) {
         var4 = 1048576;
      } else {
         var4 = 0;
      }

      if (var2) {
         var5 = 128;
      }

      return var4 + (var0 << 8) + (var1 & 127) + var5;
   }

   public static int getSource(int var0) {
      return var0 >> 8 & 4095;
   }

   public static int getVolume(int var0) {
      return var0 & 127;
   }

   public static boolean isMute(int var0) {
      boolean var1;
      if ((var0 & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean needShow(int var0) {
      boolean var1;
      if ((var0 & 1048576) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static enum BEEP_TYPE {
      private static final BEEP_TYPE[] $VALUES = $values();
      AudioChipBEEP,
      MCUBEEP;

      // $FF: synthetic method
      private static BEEP_TYPE[] $values() {
         return new BEEP_TYPE[]{MCUBEEP, AudioChipBEEP};
      }
   }

   public static enum DEVICE_TYPE {
      private static final DEVICE_TYPE[] $VALUES = $values();
      AKM7738,
      AKM7739,
      AKM7739_HORN10,
      BU32107,
      BU37534,
      TDA7719;

      // $FF: synthetic method
      private static DEVICE_TYPE[] $values() {
         return new DEVICE_TYPE[]{AKM7738, BU32107, TDA7719, BU37534, AKM7739, AKM7739_HORN10};
      }
   }

   public static enum GEQ {
      private static final GEQ[] $VALUES = $values();
      FREQ,
      GAIN,
      Q;

      // $FF: synthetic method
      private static GEQ[] $values() {
         return new GEQ[]{FREQ, Q, GAIN};
      }
   }

   public static enum HORN_TYPE {
      private static final HORN_TYPE[] $VALUES = $values();
      AMP_CH1,
      AMP_CH2,
      AMP_CH3,
      AMP_CH4,
      AMP_CH5,
      AMP_CH6,
      CENTER,
      FL,
      FR,
      RL,
      RR,
      SUB;

      // $FF: synthetic method
      private static HORN_TYPE[] $values() {
         return new HORN_TYPE[]{FL, FR, RL, RR, SUB, CENTER, AMP_CH1, AMP_CH2, AMP_CH3, AMP_CH4, AMP_CH5, AMP_CH6};
      }
   }

   public static enum SETTING_GET {
      private static final SETTING_GET[] $VALUES = $values();
      getDelay,
      getFaderBalance,
      getHpf,
      getLoudness,
      getLpf,
      getReverseVolume,
      getSourceVolume,
      getSub,
      getVolume;

      // $FF: synthetic method
      private static SETTING_GET[] $values() {
         return new SETTING_GET[]{getSourceVolume, getVolume, getReverseVolume, getDelay, getSub, getFaderBalance, getLoudness, getLpf, getHpf};
      }
   }

   public static enum SETTING_SET {
      private static final SETTING_SET[] $VALUES = $values();
      enableSubwoof,
      saveSourceVolume,
      setBootMute,
      setGain,
      setHpf,
      setLoundness,
      setLpf,
      setMicrophoneInputChannel,
      setReverseMute,
      setReverseVolume,
      setSourceVolume,
      setSourceVolumeNotTip,
      setTempMute,
      setVolume,
      sourceSetVolume,
      testAudioChanel,
      updateFaderBalance,
      updateGainConfig,
      updateHornEq,
      updateNaviAudioConfig,
      updateSourceEq,
      updateSpeakerDelay,
      updateSubwoof;

      // $FF: synthetic method
      private static SETTING_SET[] $values() {
         return new SETTING_SET[]{setGain, updateGainConfig, updateSourceEq, updateFaderBalance, setLoundness, enableSubwoof, updateSubwoof, setVolume, updateSpeakerDelay, updateNaviAudioConfig, setReverseMute, setSourceVolume, setReverseVolume, saveSourceVolume, sourceSetVolume, testAudioChanel, setTempMute, setSourceVolumeNotTip, setMicrophoneInputChannel, setBootMute, updateHornEq, setLpf, setHpf};
      }
   }

   public static enum STD_EQ {
      private static final STD_EQ[] $VALUES = $values();
      CLASSIC,
      DANCE,
      EASY,
      FLAT,
      JAZZ,
      MAX,
      POP,
      ROCK,
      SITE;

      // $FF: synthetic method
      private static STD_EQ[] $values() {
         return new STD_EQ[]{FLAT, POP, EASY, ROCK, CLASSIC, JAZZ, SITE, DANCE, MAX};
      }
   }

   public static enum USER_EQ {
      private static final USER_EQ[] $VALUES = $values();
      USER1,
      USER2,
      USER3,
      USER4;

      // $FF: synthetic method
      private static USER_EQ[] $values() {
         return new USER_EQ[]{USER1, USER2, USER3, USER4};
      }

      public int getId() {
         return this.ordinal() + AudioConstants.EQTYPE_USER0;
      }
   }
}
