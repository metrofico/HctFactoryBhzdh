package com.hzbhd.commontools;

public class KeyConstantsDef {
   public static enum KeyState {
      private static final KeyState[] $VALUES;
      LONG_EVENT,
      LONG_UP,
      NONE,
      PRESS_DOWN,
      PRESS_UP;

      static {
         KeyState var2 = new KeyState("NONE", 0);
         NONE = var2;
         KeyState var1 = new KeyState("PRESS_DOWN", 1);
         PRESS_DOWN = var1;
         KeyState var4 = new KeyState("PRESS_UP", 2);
         PRESS_UP = var4;
         KeyState var3 = new KeyState("LONG_EVENT", 3);
         LONG_EVENT = var3;
         KeyState var0 = new KeyState("LONG_UP", 4);
         LONG_UP = var0;
         $VALUES = new KeyState[]{var2, var1, var4, var3, var0};
      }
   }

   public static enum KeyType {
      private static final KeyType[] $VALUES;
      AC_AC,
      AC_AIR_OFF,
      AC_AIR_ON,
      AC_AUTO,
      AC_BLOW_MIN,
      AC_BLOW_PLUS,
      AC_F_DEF,
      AC_HEAT,
      AC_LEFT_TEMP_MIN,
      AC_LEFT_TEMP_PLUS,
      AC_MODE,
      AC_MODE_FACE,
      AC_MODE_FOOT,
      AC_OFF,
      AC_ON,
      AC_RECIR,
      AC_RECIRCULATIO,
      AC_R_DEF,
      AC_TEMP,
      AC_TEMP_DEC,
      AC_TEMP_INC,
      AC_WIND,
      AC_WIND_DEC,
      AC_WIND_IND,
      ANGLE,
      ANSWER,
      APP,
      AUDIO,
      AUX,
      AVM,
      BAND,
      BK_LIGHT,
      BT,
      BT_A2DP,
      BT_TEL,
      CAMERA,
      CAN_AC,
      CAR,
      CARLIFE,
      CLEAR,
      CLIMATE,
      CLOCK,
      COMPASS,
      DBB,
      DEST,
      DISC,
      DISPLAY,
      DOWN,
      DVD,
      EIGHT,
      EJECT,
      ENCODER_ANTICLOCK,
      ENCODER_CLOCKWISE,
      ENTER,
      EQ,
      ESP,
      FAD_BAL,
      FASTB,
      FASTF,
      FIVE,
      FOUR,
      GESTURE_DN,
      GESTURE_UP,
      GOTO,
      HANGUP,
      HOME,
      HOST,
      IC_MODE,
      INFO,
      KNOB_DOWN,
      KNOB_UP,
      KOLDEL_DN,
      KOLDEL_UP,
      LEFT,
      LINKER,
      LOCK,
      MEDIA,
      MENU,
      MUSIC,
      MUTE,
      MUTE_LONG,
      NAVI,
      NAVIGATION,
      NEXT,
      NEXT_REAR,
      NINE,
      NONE,
      NO_KEY,
      NUMBER,
      ONE,
      OPEN_CLOSE,
      OPTION,
      PAGE_DOWN,
      PAGE_UP,
      PAUSE,
      PAUSE_REAR,
      PHONE,
      PHONE_OFF,
      PHONE_ON,
      PHOTO,
      PIC,
      PLAY,
      PLAY_PAUSE,
      POWER,
      PRESET_DOWN,
      PRESET_UP,
      PREV,
      PREV_REAR,
      PTT,
      RADAR,
      RADIO,
      RADIO_AS,
      RADIO_MUTE,
      RADIO_SEEK_DOWN,
      RADIO_SEEK_UP,
      REAR_TRUCK,
      REDIAL,
      RELEASE,
      REPEAT,
      REP_AB,
      RETURN,
      REVERSEMUTE,
      RIGHT,
      ROTATE,
      SCAN,
      SD_MEDIA,
      SEARCH,
      SETING,
      SETTING,
      SETUP,
      SEVEN,
      SHUFFLE,
      SIRI,
      SIX,
      SLEEP,
      SLOWB,
      SLOWF,
      SOURCE,
      SOURCE_LONG,
      SOURCE_MENU,
      SOURCE_REAR,
      SRS,
      STANBY_OFF,
      STANBY_ON,
      STAR,
      STEP,
      STEP_DOWN,
      STEP_UP,
      STOP,
      SUBTITLE,
      SVC,
      TEN,
      THREE,
      TILT_DN,
      TILT_UP,
      TITLE,
      TRAFFIC,
      TRIP,
      TUNER,
      TUNER_AM,
      TUNER_FM,
      TWO,
      UNLOCK,
      UP,
      USB1_MEDIA,
      USB2_MEDIA,
      VIDEO,
      VOICE,
      VOICE_STOP,
      VOL_ADD,
      VOL_LOSS,
      WIDE,
      ZERO,
      ZOOM,
      ZOOM_IN,
      ZOOM_OUT;

      static {
         KeyType var113 = new KeyType("NONE", 0);
         NONE = var113;
         KeyType var64 = new KeyType("POWER", 1);
         POWER = var64;
         KeyType var125 = new KeyType("SOURCE", 2);
         SOURCE = var125;
         KeyType var124 = new KeyType("MUTE", 3);
         MUTE = var124;
         KeyType var161 = new KeyType("EQ", 4);
         EQ = var161;
         KeyType var103 = new KeyType("FAD_BAL", 5);
         FAD_BAL = var103;
         KeyType var169 = new KeyType("DBB", 6);
         DBB = var169;
         KeyType var86 = new KeyType("VOL_ADD", 7);
         VOL_ADD = var86;
         KeyType var12 = new KeyType("VOL_LOSS", 8);
         VOL_LOSS = var12;
         KeyType var175 = new KeyType("REVERSEMUTE", 9);
         REVERSEMUTE = var175;
         KeyType var140 = new KeyType("TILT_UP", 10);
         TILT_UP = var140;
         KeyType var70 = new KeyType("TILT_DN", 11);
         TILT_DN = var70;
         KeyType var1 = new KeyType("MENU", 12);
         MENU = var1;
         KeyType var10 = new KeyType("OPEN_CLOSE", 13);
         OPEN_CLOSE = var10;
         KeyType var166 = new KeyType("PIC", 14);
         PIC = var166;
         KeyType var146 = new KeyType("WIDE", 15);
         WIDE = var146;
         KeyType var163 = new KeyType("ANSWER", 16);
         ANSWER = var163;
         KeyType var116 = new KeyType("HANGUP", 17);
         HANGUP = var116;
         KeyType var47 = new KeyType("SOURCE_MENU", 18);
         SOURCE_MENU = var47;
         KeyType var159 = new KeyType("PLAY", 19);
         PLAY = var159;
         KeyType var170 = new KeyType("PAUSE", 20);
         PAUSE = var170;
         KeyType var105 = new KeyType("STOP", 21);
         STOP = var105;
         KeyType var117 = new KeyType("NEXT", 22);
         NEXT = var117;
         KeyType var173 = new KeyType("PREV", 23);
         PREV = var173;
         KeyType var141 = new KeyType("FASTF", 24);
         FASTF = var141;
         KeyType var87 = new KeyType("FASTB", 25);
         FASTB = var87;
         KeyType var62 = new KeyType("SLOWF", 26);
         SLOWF = var62;
         KeyType var57 = new KeyType("SLOWB", 27);
         SLOWB = var57;
         KeyType var123 = new KeyType("STEP", 28);
         STEP = var123;
         KeyType var83 = new KeyType("REPEAT", 29);
         REPEAT = var83;
         KeyType var28 = new KeyType("REP_AB", 30);
         REP_AB = var28;
         KeyType var154 = new KeyType("SHUFFLE", 31);
         SHUFFLE = var154;
         KeyType var144 = new KeyType("SCAN", 32);
         SCAN = var144;
         KeyType var60 = new KeyType("EJECT", 33);
         EJECT = var60;
         KeyType var109 = new KeyType("ZERO", 34);
         ZERO = var109;
         KeyType var58 = new KeyType("ONE", 35);
         ONE = var58;
         KeyType var107 = new KeyType("TWO", 36);
         TWO = var107;
         KeyType var53 = new KeyType("THREE", 37);
         THREE = var53;
         KeyType var145 = new KeyType("FOUR", 38);
         FOUR = var145;
         KeyType var45 = new KeyType("FIVE", 39);
         FIVE = var45;
         KeyType var111 = new KeyType("SIX", 40);
         SIX = var111;
         KeyType var32 = new KeyType("SEVEN", 41);
         SEVEN = var32;
         KeyType var114 = new KeyType("EIGHT", 42);
         EIGHT = var114;
         KeyType var6 = new KeyType("NINE", 43);
         NINE = var6;
         KeyType var46 = new KeyType("TEN", 44);
         TEN = var46;
         KeyType var148 = new KeyType("CLEAR", 45);
         CLEAR = var148;
         KeyType var25 = new KeyType("GOTO", 46);
         GOTO = var25;
         KeyType var75 = new KeyType("UP", 47);
         UP = var75;
         KeyType var126 = new KeyType("DOWN", 48);
         DOWN = var126;
         KeyType var40 = new KeyType("LEFT", 49);
         LEFT = var40;
         KeyType var19 = new KeyType("RIGHT", 50);
         RIGHT = var19;
         KeyType var149 = new KeyType("ENTER", 51);
         ENTER = var149;
         KeyType var4 = new KeyType("RETURN", 52);
         RETURN = var4;
         KeyType var153 = new KeyType("TITLE", 53);
         TITLE = var153;
         KeyType var63 = new KeyType("HOME", 54);
         HOME = var63;
         KeyType var71 = new KeyType("AUDIO", 55);
         AUDIO = var71;
         KeyType var89 = new KeyType("SUBTITLE", 56);
         SUBTITLE = var89;
         KeyType var97 = new KeyType("ANGLE", 57);
         ANGLE = var97;
         KeyType var115 = new KeyType("ZOOM", 58);
         ZOOM = var115;
         KeyType var21 = new KeyType("ZOOM_IN", 59);
         ZOOM_IN = var21;
         KeyType var80 = new KeyType("ZOOM_OUT", 60);
         ZOOM_OUT = var80;
         KeyType var127 = new KeyType("DISPLAY", 61);
         DISPLAY = var127;
         KeyType var152 = new KeyType("SRS", 62);
         SRS = var152;
         KeyType var157 = new KeyType("VOICE_STOP", 63);
         VOICE_STOP = var157;
         KeyType var8 = new KeyType("SETUP", 64);
         SETUP = var8;
         KeyType var41 = new KeyType("MUSIC", 65);
         MUSIC = var41;
         KeyType var135 = new KeyType("PHOTO", 66);
         PHOTO = var135;
         KeyType var49 = new KeyType("VIDEO", 67);
         VIDEO = var49;
         KeyType var136 = new KeyType("BAND", 68);
         BAND = var136;
         KeyType var162 = new KeyType("STAR", 69);
         STAR = var162;
         KeyType var110 = new KeyType("NUMBER", 70);
         NUMBER = var110;
         KeyType var36 = new KeyType("PRESET_UP", 71);
         PRESET_UP = var36;
         KeyType var130 = new KeyType("PRESET_DOWN", 72);
         PRESET_DOWN = var130;
         KeyType var11 = new KeyType("PTT", 73);
         PTT = var11;
         KeyType var61 = new KeyType("BT", 74);
         BT = var61;
         KeyType var37 = new KeyType("SOURCE_REAR", 75);
         SOURCE_REAR = var37;
         KeyType var155 = new KeyType("PAUSE_REAR", 76);
         PAUSE_REAR = var155;
         KeyType var30 = new KeyType("PREV_REAR", 77);
         PREV_REAR = var30;
         KeyType var98 = new KeyType("NEXT_REAR", 78);
         NEXT_REAR = var98;
         KeyType var147 = new KeyType("OPTION", 79);
         OPTION = var147;
         KeyType var48 = new KeyType("INFO", 80);
         INFO = var48;
         KeyType var59 = new KeyType("SIRI", 81);
         SIRI = var59;
         KeyType var38 = new KeyType("NAVIGATION", 82);
         NAVIGATION = var38;
         KeyType var122 = new KeyType("TUNER", 83);
         TUNER = var122;
         KeyType var33 = new KeyType("DISC", 84);
         DISC = var33;
         KeyType var156 = new KeyType("SEARCH", 85);
         SEARCH = var156;
         KeyType var106 = new KeyType("HOST", 86);
         HOST = var106;
         KeyType var31 = new KeyType("BK_LIGHT", 87);
         BK_LIGHT = var31;
         KeyType var42 = new KeyType("SLEEP", 88);
         SLEEP = var42;
         KeyType var138 = new KeyType("APP", 89);
         APP = var138;
         KeyType var2 = new KeyType("ENCODER_CLOCKWISE", 90);
         ENCODER_CLOCKWISE = var2;
         KeyType var66 = new KeyType("ENCODER_ANTICLOCK", 91);
         ENCODER_ANTICLOCK = var66;
         KeyType var72 = new KeyType("CLIMATE", 92);
         CLIMATE = var72;
         KeyType var142 = new KeyType("CARLIFE", 93);
         CARLIFE = var142;
         KeyType var50 = new KeyType("AC_WIND", 94);
         AC_WIND = var50;
         KeyType var20 = new KeyType("AC_TEMP", 95);
         AC_TEMP = var20;
         KeyType var92 = new KeyType("KNOB_DOWN", 96);
         KNOB_DOWN = var92;
         KeyType var51 = new KeyType("KNOB_UP", 97);
         KNOB_UP = var51;
         KeyType var133 = new KeyType("GESTURE_UP", 98);
         GESTURE_UP = var133;
         KeyType var3 = new KeyType("GESTURE_DN", 99);
         GESTURE_DN = var3;
         KeyType var118 = new KeyType("MEDIA", 100);
         MEDIA = var118;
         KeyType var26 = new KeyType("SD_MEDIA", 101);
         SD_MEDIA = var26;
         KeyType var35 = new KeyType("USB1_MEDIA", 102);
         USB1_MEDIA = var35;
         KeyType var108 = new KeyType("USB2_MEDIA", 103);
         USB2_MEDIA = var108;
         KeyType var104 = new KeyType("AUX", 104);
         AUX = var104;
         KeyType var112 = new KeyType("DVD", 105);
         DVD = var112;
         KeyType var13 = new KeyType("RADIO", 106);
         RADIO = var13;
         KeyType var91 = new KeyType("TUNER_FM", 107);
         TUNER_FM = var91;
         KeyType var77 = new KeyType("TUNER_AM", 108);
         TUNER_AM = var77;
         KeyType var78 = new KeyType("NAVI", 109);
         NAVI = var78;
         KeyType var151 = new KeyType("DEST", 110);
         DEST = var151;
         KeyType var120 = new KeyType("BT_TEL", 111);
         BT_TEL = var120;
         KeyType var84 = new KeyType("BT_A2DP", 112);
         BT_A2DP = var84;
         KeyType var55 = new KeyType("SETING", 113);
         SETING = var55;
         KeyType var164 = new KeyType("LINKER", 114);
         LINKER = var164;
         KeyType var139 = new KeyType("COMPASS", 115);
         COMPASS = var139;
         KeyType var43 = new KeyType("CLOCK", 116);
         CLOCK = var43;
         KeyType var99 = new KeyType("AVM", 117);
         AVM = var99;
         KeyType var67 = new KeyType("CAN_AC", 118);
         CAN_AC = var67;
         KeyType var56 = new KeyType("IC_MODE", 119);
         IC_MODE = var56;
         KeyType var174 = new KeyType("SVC", 120);
         SVC = var174;
         KeyType var119 = new KeyType("RADIO_AS", 121);
         RADIO_AS = var119;
         KeyType var101 = new KeyType("RADIO_SEEK_UP", 122);
         RADIO_SEEK_UP = var101;
         KeyType var17 = new KeyType("RADIO_SEEK_DOWN", 123);
         RADIO_SEEK_DOWN = var17;
         KeyType var0 = new KeyType("PHONE", 124);
         PHONE = var0;
         KeyType var100 = new KeyType("PHONE_ON", 125);
         PHONE_ON = var100;
         KeyType var65 = new KeyType("PHONE_OFF", 126);
         PHONE_OFF = var65;
         KeyType var9 = new KeyType("REDIAL", 127);
         REDIAL = var9;
         KeyType var167 = new KeyType("KOLDEL_UP", 128);
         KOLDEL_UP = var167;
         KeyType var134 = new KeyType("KOLDEL_DN", 129);
         KOLDEL_DN = var134;
         KeyType var150 = new KeyType("TRIP", 130);
         TRIP = var150;
         KeyType var95 = new KeyType("ROTATE", 131);
         ROTATE = var95;
         KeyType var18 = new KeyType("PLAY_PAUSE", 132);
         PLAY_PAUSE = var18;
         KeyType var34 = new KeyType("AC_ON", 133);
         AC_ON = var34;
         KeyType var93 = new KeyType("AC_OFF", 134);
         AC_OFF = var93;
         KeyType var160 = new KeyType("AC_MODE", 135);
         AC_MODE = var160;
         KeyType var131 = new KeyType("AC_RECIR", 136);
         AC_RECIR = var131;
         KeyType var172 = new KeyType("AC_F_DEF", 137);
         AC_F_DEF = var172;
         KeyType var102 = new KeyType("AC_R_DEF", 138);
         AC_R_DEF = var102;
         KeyType var165 = new KeyType("AC_BLOW_PLUS", 139);
         AC_BLOW_PLUS = var165;
         KeyType var96 = new KeyType("AC_BLOW_MIN", 140);
         AC_BLOW_MIN = var96;
         KeyType var85 = new KeyType("AC_LEFT_TEMP_PLUS", 141);
         AC_LEFT_TEMP_PLUS = var85;
         KeyType var74 = new KeyType("AC_LEFT_TEMP_MIN", 142);
         AC_LEFT_TEMP_MIN = var74;
         KeyType var15 = new KeyType("AC_AUTO", 143);
         AC_AUTO = var15;
         KeyType var88 = new KeyType("AC_HEAT", 144);
         AC_HEAT = var88;
         KeyType var54 = new KeyType("AC_AIR_OFF", 145);
         AC_AIR_OFF = var54;
         KeyType var137 = new KeyType("AC_AIR_ON", 146);
         AC_AIR_ON = var137;
         KeyType var24 = new KeyType("AC_MODE_FACE", 147);
         AC_MODE_FACE = var24;
         KeyType var68 = new KeyType("AC_MODE_FOOT", 148);
         AC_MODE_FOOT = var68;
         KeyType var171 = new KeyType("UNLOCK", 149);
         UNLOCK = var171;
         KeyType var129 = new KeyType("LOCK", 150);
         LOCK = var129;
         KeyType var29 = new KeyType("ESP", 151);
         ESP = var29;
         KeyType var16 = new KeyType("REAR_TRUCK", 152);
         REAR_TRUCK = var16;
         KeyType var27 = new KeyType("RADAR", 153);
         RADAR = var27;
         KeyType var5 = new KeyType("RELEASE", 154);
         RELEASE = var5;
         KeyType var90 = new KeyType("TRAFFIC", 155);
         TRAFFIC = var90;
         KeyType var14 = new KeyType("CAR", 156);
         CAR = var14;
         KeyType var158 = new KeyType("SETTING", 157);
         SETTING = var158;
         KeyType var22 = new KeyType("NO_KEY", 158);
         NO_KEY = var22;
         KeyType var121 = new KeyType("SOURCE_LONG", 159);
         SOURCE_LONG = var121;
         KeyType var39 = new KeyType("CAMERA", 160);
         CAMERA = var39;
         KeyType var81 = new KeyType("PAGE_UP", 161);
         PAGE_UP = var81;
         KeyType var94 = new KeyType("PAGE_DOWN", 162);
         PAGE_DOWN = var94;
         KeyType var168 = new KeyType("STANBY_ON", 163);
         STANBY_ON = var168;
         KeyType var69 = new KeyType("STANBY_OFF", 164);
         STANBY_OFF = var69;
         KeyType var52 = new KeyType("STEP_UP", 165);
         STEP_UP = var52;
         KeyType var132 = new KeyType("STEP_DOWN", 166);
         STEP_DOWN = var132;
         KeyType var76 = new KeyType("RADIO_MUTE", 167);
         RADIO_MUTE = var76;
         KeyType var23 = new KeyType("AC_AC", 168);
         AC_AC = var23;
         KeyType var44 = new KeyType("AC_TEMP_INC", 169);
         AC_TEMP_INC = var44;
         KeyType var79 = new KeyType("AC_TEMP_DEC", 170);
         AC_TEMP_DEC = var79;
         KeyType var73 = new KeyType("AC_RECIRCULATIO", 171);
         AC_RECIRCULATIO = var73;
         KeyType var143 = new KeyType("AC_WIND_IND", 172);
         AC_WIND_IND = var143;
         KeyType var128 = new KeyType("AC_WIND_DEC", 173);
         AC_WIND_DEC = var128;
         KeyType var82 = new KeyType("VOICE", 174);
         VOICE = var82;
         KeyType var7 = new KeyType("MUTE_LONG", 175);
         MUTE_LONG = var7;
         $VALUES = new KeyType[]{var113, var64, var125, var124, var161, var103, var169, var86, var12, var175, var140, var70, var1, var10, var166, var146, var163, var116, var47, var159, var170, var105, var117, var173, var141, var87, var62, var57, var123, var83, var28, var154, var144, var60, var109, var58, var107, var53, var145, var45, var111, var32, var114, var6, var46, var148, var25, var75, var126, var40, var19, var149, var4, var153, var63, var71, var89, var97, var115, var21, var80, var127, var152, var157, var8, var41, var135, var49, var136, var162, var110, var36, var130, var11, var61, var37, var155, var30, var98, var147, var48, var59, var38, var122, var33, var156, var106, var31, var42, var138, var2, var66, var72, var142, var50, var20, var92, var51, var133, var3, var118, var26, var35, var108, var104, var112, var13, var91, var77, var78, var151, var120, var84, var55, var164, var139, var43, var99, var67, var56, var174, var119, var101, var17, var0, var100, var65, var9, var167, var134, var150, var95, var18, var34, var93, var160, var131, var172, var102, var165, var96, var85, var74, var15, var88, var54, var137, var24, var68, var171, var129, var29, var16, var27, var5, var90, var14, var158, var22, var121, var39, var81, var94, var168, var69, var52, var132, var76, var23, var44, var79, var73, var143, var128, var82, var7};
      }
   }
}
