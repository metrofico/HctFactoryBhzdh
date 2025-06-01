package com.hzbhd.canbus.adapter.constant;

public interface RadioConstantsDef {
   public static enum BandType {
      private static final BandType[] $VALUES;
      BAND_AM1,
      BAND_AM2,
      BAND_FM1,
      BAND_FM2,
      BAND_FM3,
      BAND_LW,
      BAND_MW,
      BAND_OIRT;

      static {
         BandType var4 = new BandType("BAND_FM1", 0);
         BAND_FM1 = var4;
         BandType var5 = new BandType("BAND_FM2", 1);
         BAND_FM2 = var5;
         BandType var3 = new BandType("BAND_FM3", 2);
         BAND_FM3 = var3;
         BandType var2 = new BandType("BAND_AM1", 3);
         BAND_AM1 = var2;
         BandType var7 = new BandType("BAND_AM2", 4);
         BAND_AM2 = var7;
         BandType var0 = new BandType("BAND_OIRT", 5);
         BAND_OIRT = var0;
         BandType var6 = new BandType("BAND_MW", 6);
         BAND_MW = var6;
         BandType var1 = new BandType("BAND_LW", 7);
         BAND_LW = var1;
         $VALUES = new BandType[]{var4, var5, var3, var2, var7, var0, var6, var1};
      }
   }

   public static enum PTYType {
      private static final PTYType[] $VALUES;
      AFFAIRS,
      ALARM,
      CHILDREN,
      CLASSICS,
      COUNTRY,
      CULTURE,
      DOCUMENT,
      DRAMA,
      EASY_M,
      EDUCATE,
      FINANCE,
      FOLK_M,
      INFO,
      JAZZ,
      LEISURE,
      LIGHT_M,
      NATION_M,
      NEWS,
      NONE,
      OLDIES,
      OTHER_M,
      PHONE_IN,
      POP_M,
      RELIGION,
      ROCK_M,
      SCIENCE,
      SOCIAL,
      SPORT,
      TEST,
      TRAVEL,
      VARIED,
      WEATHER;

      static {
         PTYType var21 = new PTYType("NONE", 0);
         NONE = var21;
         PTYType var28 = new PTYType("NEWS", 1);
         NEWS = var28;
         PTYType var31 = new PTYType("AFFAIRS", 2);
         AFFAIRS = var31;
         PTYType var27 = new PTYType("INFO", 3);
         INFO = var27;
         PTYType var5 = new PTYType("SPORT", 4);
         SPORT = var5;
         PTYType var15 = new PTYType("EDUCATE", 5);
         EDUCATE = var15;
         PTYType var30 = new PTYType("DRAMA", 6);
         DRAMA = var30;
         PTYType var19 = new PTYType("CULTURE", 7);
         CULTURE = var19;
         PTYType var10 = new PTYType("SCIENCE", 8);
         SCIENCE = var10;
         PTYType var8 = new PTYType("VARIED", 9);
         VARIED = var8;
         PTYType var2 = new PTYType("POP_M", 10);
         POP_M = var2;
         PTYType var9 = new PTYType("ROCK_M", 11);
         ROCK_M = var9;
         PTYType var0 = new PTYType("EASY_M", 12);
         EASY_M = var0;
         PTYType var1 = new PTYType("LIGHT_M", 13);
         LIGHT_M = var1;
         PTYType var3 = new PTYType("CLASSICS", 14);
         CLASSICS = var3;
         PTYType var4 = new PTYType("OTHER_M", 15);
         OTHER_M = var4;
         PTYType var11 = new PTYType("WEATHER", 16);
         WEATHER = var11;
         PTYType var18 = new PTYType("FINANCE", 17);
         FINANCE = var18;
         PTYType var12 = new PTYType("CHILDREN", 18);
         CHILDREN = var12;
         PTYType var7 = new PTYType("SOCIAL", 19);
         SOCIAL = var7;
         PTYType var22 = new PTYType("RELIGION", 20);
         RELIGION = var22;
         PTYType var23 = new PTYType("PHONE_IN", 21);
         PHONE_IN = var23;
         PTYType var16 = new PTYType("TRAVEL", 22);
         TRAVEL = var16;
         PTYType var6 = new PTYType("LEISURE", 23);
         LEISURE = var6;
         PTYType var17 = new PTYType("JAZZ", 24);
         JAZZ = var17;
         PTYType var29 = new PTYType("COUNTRY", 25);
         COUNTRY = var29;
         PTYType var14 = new PTYType("NATION_M", 26);
         NATION_M = var14;
         PTYType var20 = new PTYType("OLDIES", 27);
         OLDIES = var20;
         PTYType var25 = new PTYType("FOLK_M", 28);
         FOLK_M = var25;
         PTYType var13 = new PTYType("DOCUMENT", 29);
         DOCUMENT = var13;
         PTYType var26 = new PTYType("TEST", 30);
         TEST = var26;
         PTYType var24 = new PTYType("ALARM", 31);
         ALARM = var24;
         $VALUES = new PTYType[]{var21, var28, var31, var27, var5, var15, var30, var19, var10, var8, var2, var9, var0, var1, var3, var4, var11, var18, var12, var7, var22, var23, var16, var6, var17, var29, var14, var20, var25, var13, var26, var24};
      }
   }

   public static enum RADIO_FAR_TYPE {
      private static final RADIO_FAR_TYPE[] $VALUES;
      LOCALE,
      REMOTE;

      static {
         RADIO_FAR_TYPE var1 = new RADIO_FAR_TYPE("LOCALE", 0);
         LOCALE = var1;
         RADIO_FAR_TYPE var0 = new RADIO_FAR_TYPE("REMOTE", 1);
         REMOTE = var0;
         $VALUES = new RADIO_FAR_TYPE[]{var1, var0};
      }
   }

   public static enum RADIO_TYPE {
      private static final RADIO_TYPE[] $VALUES;
      AM,
      FM;

      static {
         RADIO_TYPE var0 = new RADIO_TYPE("FM", 0);
         FM = var0;
         RADIO_TYPE var1 = new RADIO_TYPE("AM", 1);
         AM = var1;
         $VALUES = new RADIO_TYPE[]{var0, var1};
      }
   }

   public static enum RDSType {
      private static final RDSType[] $VALUES;
      NORDS,
      RBDS,
      RDS;

      static {
         RDSType var0 = new RDSType("NORDS", 0);
         NORDS = var0;
         RDSType var1 = new RDSType("RDS", 1);
         RDS = var1;
         RDSType var2 = new RDSType("RBDS", 2);
         RBDS = var2;
         $VALUES = new RDSType[]{var0, var1, var2};
      }
   }

   public static enum RFMode {
      private static final RFMode[] $VALUES;
      RF_MODE_DX,
      RF_MODE_LOC;

      static {
         RFMode var0 = new RFMode("RF_MODE_LOC", 0);
         RF_MODE_LOC = var0;
         RFMode var1 = new RFMode("RF_MODE_DX", 1);
         RF_MODE_DX = var1;
         $VALUES = new RFMode[]{var0, var1};
      }
   }

   public static enum RadioArea {
      private static final RadioArea[] $VALUES;
      ASIA,
      AUSTRALIA,
      BRAZIL,
      EUROPE,
      EUROPE_LW,
      JAPAN,
      KOREA,
      OIRT,
      SOUTHEAST_ASIA,
      S_AMER1,
      S_AMER2,
      USA;

      static {
         RadioArea var10 = new RadioArea("USA", 0);
         USA = var10;
         RadioArea var1 = new RadioArea("ASIA", 1);
         ASIA = var1;
         RadioArea var8 = new RadioArea("JAPAN", 2);
         JAPAN = var8;
         RadioArea var2 = new RadioArea("EUROPE", 3);
         EUROPE = var2;
         RadioArea var9 = new RadioArea("EUROPE_LW", 4);
         EUROPE_LW = var9;
         RadioArea var5 = new RadioArea("OIRT", 5);
         OIRT = var5;
         RadioArea var6 = new RadioArea("S_AMER1", 6);
         S_AMER1 = var6;
         RadioArea var0 = new RadioArea("S_AMER2", 7);
         S_AMER2 = var0;
         RadioArea var7 = new RadioArea("KOREA", 8);
         KOREA = var7;
         RadioArea var3 = new RadioArea("AUSTRALIA", 9);
         AUSTRALIA = var3;
         RadioArea var11 = new RadioArea("SOUTHEAST_ASIA", 10);
         SOUTHEAST_ASIA = var11;
         RadioArea var4 = new RadioArea("BRAZIL", 11);
         BRAZIL = var4;
         $VALUES = new RadioArea[]{var10, var1, var8, var2, var9, var5, var6, var0, var7, var3, var11, var4};
      }
   }

   public static enum SearchType {
      private static final SearchType[] $VALUES;
      SEARCH_ALL_BAND_FROM_MIN_FREQ,
      SEARCH_CURRENT_BAND_FROM_CURRENT_FREQ,
      SEARCH_CURRENT_BAND_FROM_MIN_FREQ,
      SEARCH_DEFAULT;

      static {
         SearchType var0 = new SearchType("SEARCH_DEFAULT", 0);
         SEARCH_DEFAULT = var0;
         SearchType var3 = new SearchType("SEARCH_ALL_BAND_FROM_MIN_FREQ", 1);
         SEARCH_ALL_BAND_FROM_MIN_FREQ = var3;
         SearchType var1 = new SearchType("SEARCH_CURRENT_BAND_FROM_CURRENT_FREQ", 2);
         SEARCH_CURRENT_BAND_FROM_CURRENT_FREQ = var1;
         SearchType var2 = new SearchType("SEARCH_CURRENT_BAND_FROM_MIN_FREQ", 3);
         SEARCH_CURRENT_BAND_FROM_MIN_FREQ = var2;
         $VALUES = new SearchType[]{var0, var3, var1, var2};
      }
   }

   public static enum StepType {
      private static final StepType[] $VALUES;
      SETP_MANUAL,
      STEP_AUTO,
      STEP_DEFAULT;

      static {
         StepType var2 = new StepType("STEP_DEFAULT", 0);
         STEP_DEFAULT = var2;
         StepType var1 = new StepType("STEP_AUTO", 1);
         STEP_AUTO = var1;
         StepType var0 = new StepType("SETP_MANUAL", 2);
         SETP_MANUAL = var0;
         $VALUES = new StepType[]{var2, var1, var0};
      }
   }

   public static enum VoiceChannelType {
      private static final VoiceChannelType[] $VALUES;
      MONO,
      STEREO;

      static {
         VoiceChannelType var0 = new VoiceChannelType("STEREO", 0);
         STEREO = var0;
         VoiceChannelType var1 = new VoiceChannelType("MONO", 1);
         MONO = var1;
         $VALUES = new VoiceChannelType[]{var0, var1};
      }
   }
}
