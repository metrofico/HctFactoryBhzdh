package com.hzbhd.canbus.car._2;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final int CAR_Golf_7 = 1;
   static final String _2_PARK_VOICE = "_2_park_voice";
   private final int DATA_TYPE = 1;
   private final String TAG = "_2_MsgMgr";
   private final String _2_AIR_INFO = "_2_air_info";
   private final String _2_AIR_STATE = "_2_air_state";
   private final String _2_AIR_TEMP = "_2_air_temp";
   private final String _2_OFF_INDIVIDUAL = "_2_off_individual";
   private final String _2_TIRE_UNIT = "_2_tire_unit";
   DecimalFormat df_2Integer = new DecimalFormat("00");
   private int frontRadarStatus;
   private boolean frontRadarTag = false;
   private int isRearInfoChange;
   private int isRearStateChange;
   private int isRearTempChange;
   private boolean leftRadarTag = false;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x32Data;
   private int[] m0x33Data;
   private List m0x60Msgs = new ArrayList();
   private List m0x61Msgs = new ArrayList();
   private List m0x62Msgs = new ArrayList();
   private int[] m0x65Data;
   private int[] m0x66Data;
   private boolean mAirClean;
   private String mAirUnit;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private String[] mConvTips = new String[18];
   private DecimalFormat mDecimalFormat;
   private int mDifferentId;
   private String mDistance;
   private String mElectric;
   private String mEndurance;
   private boolean mFrontStatus;
   private String mFuelUnit;
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private String mSpeedUnit;
   private String[] mStartStopMsgs = new String[9];
   private String[] mTireInfoArray;
   private String[] mTireInfoArray2;
   private List mTireResolveList;
   private List mTireUpdateEntityList;
   private String[] mTireWarningArray;
   private UiMgr mUiMgr;
   private String[] mVehicleReports = new String[155];
   private String[] mVehicleTips = new String[34];
   private boolean rearRadarTag = false;
   private boolean rightRadarTag = false;

   private int getBandDate(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 3;
      } else if ("AM1".equals(var1)) {
         return 16;
      } else {
         return "AM2".equals(var1) ? 18 : 0;
      }
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         SettingUpdateEntity var2 = new SettingUpdateEntity(-1, -1, (Object)null);
         this.mSettingItemIndeHashMap.put(var1, var2);
         return var2;
      }
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private TireUpdateEntity getTireEntity(int var1, String var2) {
      boolean var4 = TextUtils.isEmpty(var2);
      byte var3 = 0;
      String[] var5;
      if (var4) {
         var5 = new String[]{"tire_pressure_content", ""};
      } else {
         var5 = new String[]{"tire_pressure_content", var2};
         var3 = 1;
      }

      return new TireUpdateEntity(var1, var3, var5);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initMessages(Context var1) {
      String[] var2 = this.mVehicleTips;
      var2[0] = "_2_hinit_0x60_1_00";
      var2[1] = "_2_hinit_0x60_1_01";
      var2[2] = "_2_hinit_0x60_1_02";
      var2[3] = "_2_hinit_0x60_1_03";
      var2[4] = "_2_hinit_0x60_1_04";
      var2[5] = "_2_hinit_0x60_1_05";
      var2[6] = "_2_hinit_0x60_1_06";
      var2[7] = "_2_hinit_0x60_1_07";
      var2[8] = "_2_hinit_0x60_1_08";
      var2[9] = "_2_hinit_0x60_1_09";
      var2[10] = "_2_hinit_0x60_1_0A";
      var2[11] = "_2_hinit_0x60_1_0B";
      var2[12] = "_2_hinit_0x60_1_0C";
      var2[13] = "_2_hinit_0x60_1_0D";
      var2[14] = "_2_hinit_0x60_1_0E";
      var2[15] = "_2_hinit_0x60_1_0F";
      var2[16] = "_2_hinit_0x60_1_10";
      var2[17] = "_2_hinit_0x60_1_11";
      var2[18] = "_2_hinit_0x60_1_12";
      var2[19] = "_2_hinit_0x60_1_13";
      var2[20] = "_2_hinit_0x60_1_14";
      var2[21] = "_2_hinit_0x60_1_15";
      var2[22] = "_2_hinit_0x60_1_16";
      var2[23] = "_2_hinit_0x60_1_17";
      var2[24] = "_2_hinit_0x60_1_18";
      var2[25] = "_2_hinit_0x60_1_19";
      var2[26] = "_2_hinit_0x60_1_1A";
      var2[27] = "_2_hinit_0x60_1_1B";
      var2[28] = "_2_hinit_0x60_1_1C";
      var2[29] = "_2_hinit_0x60_1_1D";
      var2[30] = "_2_hinit_0x60_1_1E";
      var2[31] = "_2_hinit_0x60_1_1F";
      var2[32] = "_2_hinit_0x60_1_20";
      var2[33] = "_2_hinit_0x60_1_21";
      var2 = this.mStartStopMsgs;
      var2[0] = "_2_hinit_0x60_7_00";
      var2[1] = "_2_hinit_0x60_7_01";
      var2[2] = "_2_hinit_0x60_7_02";
      var2[3] = "_2_hinit_0x60_7_03";
      var2[4] = "_2_hinit_0x60_7_04";
      var2[5] = "_2_hinit_0x60_7_05";
      var2[6] = "_2_hinit_0x60_7_06";
      var2[7] = "_2_hinit_0x60_7_07";
      var2[8] = "_2_hinit_0x60_7_08";
      var2 = this.mVehicleReports;
      var2[0] = "_2_car_report_0";
      var2[1] = "_2_car_report_1";
      var2[2] = "_2_car_report_2";
      var2[3] = "_2_car_report_3";
      var2[4] = "_2_car_report_4";
      var2[5] = "_2_car_report_5";
      var2[6] = "_2_car_report_6";
      var2[7] = "_2_car_report_7";
      var2[8] = "_2_car_report_8";
      var2[9] = "_2_car_report_9";
      var2[10] = "_2_car_report_10";
      var2[11] = "_2_car_report_11";
      var2[12] = "_2_car_report_12";
      var2[13] = "_2_car_report_13";
      var2[14] = "_2_car_report_14";
      var2[15] = "_2_car_report_15";
      var2[16] = "_2_car_report_16";
      var2[17] = "_2_car_report_17";
      var2[18] = "_2_car_report_18";
      var2[19] = "_2_car_report_19";
      var2[20] = "_2_car_report_20";
      var2[21] = "_2_car_report_21";
      var2[22] = "_2_car_report_22";
      var2[23] = "_2_car_report_23";
      var2[24] = "_2_car_report_24";
      var2[25] = "_2_car_report_25";
      var2[26] = "_2_car_report_26";
      var2[27] = "_2_car_report_27";
      var2[28] = "_2_car_report_28";
      var2[29] = "_2_car_report_29";
      var2[30] = "_2_car_report_30";
      var2[31] = "_2_car_report_31";
      var2[32] = "_2_car_report_32";
      var2[33] = "_2_car_report_33";
      var2[34] = "_2_car_report_34";
      var2[35] = "_2_car_report_35";
      var2[36] = "_2_car_report_36";
      var2[37] = "_2_car_report_37";
      var2[38] = "_2_car_report_38";
      var2[39] = "_2_car_report_39";
      var2[40] = "_2_car_report_40";
      var2[41] = "_2_car_report_41";
      var2[42] = "_2_car_report_42";
      var2[43] = "_2_car_report_43";
      var2[44] = "_2_car_report_44";
      var2[45] = "_2_car_report_45";
      var2[46] = "_2_car_report_46";
      var2[47] = "_2_car_report_47";
      var2[48] = "_2_car_report_48";
      var2[49] = "_2_car_report_49";
      var2[50] = "_2_car_report_50";
      var2[51] = "_2_car_report_51";
      var2[52] = "_2_car_report_52";
      var2[53] = "_2_car_report_53";
      var2[54] = "_2_car_report_54";
      var2[55] = "_2_car_report_55";
      var2[56] = "_2_car_report_56";
      var2[57] = "_2_car_report_57";
      var2[58] = "_2_car_report_58";
      var2[59] = "_2_car_report_59";
      var2[60] = "_2_car_report_60";
      var2[61] = "_2_car_report_61";
      var2[62] = "_2_car_report_62";
      var2[63] = "_2_car_report_63";
      var2[64] = "_2_car_report_64";
      var2[65] = "_2_car_report_65";
      var2[66] = "_2_car_report_66";
      var2[67] = "_2_car_report_67";
      var2[68] = "_2_car_report_68";
      var2[69] = "_2_car_report_69";
      var2[70] = "_2_car_report_70";
      var2[71] = "_2_car_report_71";
      var2[72] = "_2_car_report_72";
      var2[73] = "_2_car_report_73";
      var2[74] = "_2_car_report_74";
      var2[75] = "_2_car_report_75";
      var2[76] = "_2_car_report_76";
      var2[77] = "_2_car_report_77";
      var2[78] = "_2_car_report_78";
      var2[79] = "_2_car_report_79";
      var2[80] = "_2_car_report_80";
      var2[81] = "_2_car_report_81";
      var2[82] = "_2_car_report_82";
      var2[83] = "_2_car_report_83";
      var2[84] = "_2_car_report_84";
      var2[85] = "_2_car_report_85";
      var2[86] = "_2_car_report_86";
      var2[87] = "_2_car_report_87";
      var2[88] = "_2_car_report_88";
      var2[89] = "_2_car_report_89";
      var2[90] = "_2_car_report_90";
      var2[91] = "_2_car_report_91";
      var2[92] = "_2_car_report_92";
      var2[93] = "_2_car_report_93";
      var2[94] = "_2_car_report_94";
      var2[95] = "_2_car_report_95";
      var2[96] = "_2_car_report_96";
      var2[97] = "_2_car_report_97";
      var2[98] = "_2_car_report_98";
      var2[99] = "_2_car_report_99";
      var2[100] = "_2_car_report_100";
      var2[101] = "_2_car_report_101";
      var2[102] = "_2_car_report_102";
      var2[103] = "_2_car_report_103";
      var2[104] = "_2_car_report_104";
      var2[105] = "_2_car_report_105";
      var2[106] = "_2_car_report_106";
      var2[107] = "_2_car_report_107";
      var2[108] = "_2_car_report_108";
      var2[109] = "_2_car_report_109";
      var2[110] = "_2_car_report_110";
      var2[111] = "_2_car_report_111";
      var2[112] = "_2_car_report_112";
      var2[113] = "_2_car_report_113";
      var2[114] = "_2_car_report_114";
      var2[115] = "_2_car_report_115";
      var2[116] = "_2_car_report_116";
      var2[117] = "_2_car_report_117";
      var2[118] = "_2_car_report_118";
      var2[119] = "_2_car_report_119";
      var2[120] = "_2_car_report_120";
      var2[121] = "_2_car_report_121";
      var2[122] = "_2_car_report_122";
      var2[123] = "_2_car_report_123";
      var2[124] = "_2_car_report_124";
      var2[125] = "_2_car_report_125";
      var2[126] = "_2_car_report_126";
      var2[127] = "_2_car_report_127";
      var2[128] = "_2_car_report_128";
      var2[129] = "_2_car_report_129";
      var2[130] = "_2_car_report_130";
      var2[131] = "_2_car_report_131";
      var2[132] = "_2_car_report_132";
      var2[133] = "_2_car_report_133";
      var2[134] = "_2_car_report_134";
      var2[135] = "_2_car_report_135";
      var2[136] = "_2_car_report_136";
      var2[137] = "_2_car_report_137";
      var2[138] = "_2_car_report_138";
      var2[139] = "_2_car_report_139";
      var2[140] = "_2_car_report_140";
      var2[141] = "_2_car_report_141";
      var2[142] = "_2_car_report_142";
      var2[143] = "_2_car_report_143";
      var2[144] = "_2_car_report_144";
      var2[145] = "_2_car_report_145";
      var2[146] = "_2_car_report_146";
      var2[147] = "_2_car_report_147";
      var2[148] = "_2_car_report_148";
      var2[149] = "_2_car_report_149";
      var2[150] = "_2_car_report_150";
      var2[151] = "_2_car_report_151";
      var2[152] = "_2_car_report_152";
      var2[153] = "_2_car_report_153";
      var2[154] = "_2_car_report_154";
      var2 = this.mConvTips;
      var2[0] = "_2_Conv_trips_01";
      var2[1] = "_2_Conv_trips_02";
      var2[2] = "_2_Conv_trips_03";
      var2[3] = "_2_Conv_trips_04";
      var2[4] = "_2_Conv_trips_05";
      var2[5] = "_2_Conv_trips_06";
      var2[6] = "_2_Conv_trips_07";
      var2[7] = "_2_Conv_trips_08";
      var2[8] = "_2_Conv_trips_09";
      var2[9] = "_2_Conv_trips_0A";
      var2[10] = "_2_Conv_trips_0B";
      var2[11] = "_2_Conv_trips_0C";
      var2[12] = "_2_Conv_trips_0D";
      var2[13] = "_2_Conv_trips_0E";
      var2[14] = "_2_Conv_trips_0F";
      var2[15] = "_2_Conv_trips_10";
      var2[16] = "_2_Conv_trips_11";
      var2[17] = "_2_Conv_trips_12";
   }

   private void initSettingsItem(Context var1) {
      Log.i("_2_MsgMgr", "initSettingsItem: ");
      this.mSettingItemIndeHashMap = new HashMap();
      SettingPageUiSet var6 = UiMgrFactory.getCanUiMgr(var1).getSettingUiSet(var1);

      for(int var2 = 0; var2 < var6.getList().size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var6.getList().get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)var5.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var4, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private void initTireResolveTool() {
      this.mTireUpdateEntityList = new ArrayList();

      for(int var1 = 0; var1 < 4; ++var1) {
         this.mTireUpdateEntityList.add(new TireUpdateEntity(var1, 0, new String[0]));
      }

      ArrayList var2 = new ArrayList();
      this.mTireResolveList = var2;
      var2.add(new TireResolve(this, "bar", 0.1F));
      this.mTireResolveList.add(new TireResolve(this, "psi", 0.5F));
      this.mTireResolveList.add(new TireResolve(this, "kPa", 10.0F));
      this.mTireInfoArray = new String[4];
      this.mTireInfoArray2 = new String[4];
      this.mTireWarningArray = new String[4];
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x32DataChange() {
      if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x32Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x33DataChange() {
      if (Arrays.equals(this.m0x33Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x33Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x65DataChange() {
      if (Arrays.equals(this.m0x65Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x65Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x66DataChange() {
      if (Arrays.equals(this.m0x66Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x66Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isOnlyRearUpdate() {
      if (SharePreUtil.getIntValue(this.mContext, "_2_air_temp", 0) != this.isRearTempChange) {
         return true;
      } else if (SharePreUtil.getIntValue(this.mContext, "_2_air_state", 0) != this.isRearStateChange) {
         return true;
      } else {
         return SharePreUtil.getIntValue(this.mContext, "_2_air_info", 0) != this.isRearInfoChange;
      }
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 31) {
         return "HI";
      } else if (var1 >= 1 && var1 <= 28) {
         float var2 = (float)var1;
         return (this.mCanBusInfoInt[6] & 1) == 1 ? var2 + 59.0F + this.mAirUnit : (var2 + 31.0F) * 0.5F + this.mAirUnit;
      } else {
         return "";
      }
   }

   private String resolveOutDoorTemp() {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[4];
      float var1 = (float)(var2 << 8 | var3[3]) * 0.1F;
      String var4;
      if ((var3[2] & 1) == 1) {
         if (DataHandleUtils.getBoolBit7(var2)) {
            var4 = "- " + (new DecimalFormat("0")).format((double)(6554.0F - var1)) + this.getTempUnitF(this.mContext);
         } else {
            var4 = (new DecimalFormat("0")).format((double)var1) + this.getTempUnitF(this.mContext);
         }
      } else if (DataHandleUtils.getBoolBit7(var2)) {
         var4 = "- " + (new DecimalFormat("0.0")).format(6553.6 - (double)var1) + this.getTempUnitC(this.mContext);
      } else {
         var4 = (new DecimalFormat("0.0")).format((double)var1) + this.getTempUnitC(this.mContext);
      }

      return var4;
   }

   private void set0x16SpeedInfo() {
      int[] var2 = this.mCanBusInfoInt;
      float var1 = (float)(var2[3] << 8 | var2[2]) / 16.0F;
      if ((var2[4] & 1) == 1) {
         this.mSpeedUnit = " MPH";
         this.updateSpeedInfo(Integer.parseInt(this.df_2Integer.format((double)var1 * 1.6)));
      } else {
         this.mSpeedUnit = " KM/H";
         this.updateSpeedInfo(Integer.parseInt(this.df_2Integer.format((double)var1)));
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, (new DecimalFormat("00")).format((double)var1) + this.mSpeedUnit));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x20WheelKey(Context var1) {
      int var3 = this.mKeyStatus;
      int var2 = this.mCanBusInfoInt[2];
      if (var3 != var2) {
         this.mKeyStatus = var2;
      }

      switch (var2) {
         case 0:
            this.realKeyLongClick1(var1, 0);
            break;
         case 1:
            this.realKeyLongClick1(var1, 7);
            break;
         case 2:
            this.realKeyLongClick1(var1, 8);
            break;
         case 3:
            this.realKeyLongClick1(var1, 46);
            break;
         case 4:
            this.realKeyLongClick1(var1, 45);
            break;
         case 5:
            this.realKeyLongClick1(var1, 14);
            break;
         case 6:
            this.realKeyLongClick1(var1, 3);
            break;
         case 7:
            this.realKeyLongClick1(var1, 2);
            break;
         case 8:
            this.realKeyLongClick1(var1, 187);
      }

   }

   private void set0x21AirInfo(Context var1) {
      if ((this.mCanBusInfoInt[6] & 1) == 1) {
         this.mAirUnit = this.getTempUnitF(var1);
      } else {
         this.mAirUnit = this.getTempUnitC(var1);
      }

      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         byte[] var2 = this.mCanBusInfoByte;
         var2[3] = (byte)(var2[3] & 239);
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
         this.mAirClean = GeneralAirData.clean_air;
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
         GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2);
         GeneralAirData.rear_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
         GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 3);
         GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 3);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]);
         GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
         if (this.mCanBusInfoByte.length > 6) {
            int[] var3 = this.mCanBusInfoInt;
            this.isRearTempChange = var3[9];
            this.isRearStateChange = var3[10];
            this.isRearInfoChange = var3[11];
         }

         if (this.isOnlyRearUpdate()) {
            SharePreUtil.setIntValue(var1, "_2_air_temp", this.isRearTempChange);
            SharePreUtil.setIntValue(var1, "_2_air_state", this.isRearStateChange);
            SharePreUtil.setIntValue(var1, "_2_air_info", this.isRearInfoChange);
            this.updateAirActivity(var1, 1003);
         } else {
            this.updateAirActivity(var1, 1004);
         }

      }
   }

   private void set0x22RearRadar(Context var1) {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationDataType2(60, var2[2], 165, var2[3], 165, var2[4], 60, var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x23FrontRadar(Context var1) {
      if (this.is0x23DataChange()) {
         int[] var3;
         label40: {
            label45: {
               int var2 = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
               this.frontRadarStatus = var2;
               if (var2 == 1) {
                  var2 = this.mCanBusInfoInt[2];
                  if (var2 <= 12 && var2 != 0) {
                     break label45;
                  }
               }

               var3 = this.mCanBusInfoInt;
               var2 = var3[5];
               if (var2 > 12 || var2 == 0) {
                  var2 = var3[3];
                  if (var2 > 24 || var2 == 0) {
                     var2 = var3[4];
                     if (var2 > 24 || var2 == 0) {
                        this.frontRadarTag = false;
                        if (!this.rightRadarTag && !this.leftRadarTag && !this.rearRadarTag) {
                           this.forceReverse(this.mContext, false);
                        }
                        break label40;
                     }
                  }
               }
            }

            this.forceReverse(this.mContext, true);
            this.frontRadarTag = true;
         }

         RadarInfoUtil.mMinIsClose = true;
         var3 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationDataType2(60, var3[2], 120, var3[3], 120, var3[4], 60, var3[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24DoorData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x25ParkingAssist(Context var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var2.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      SharePreUtil.setBoolValue(var1, "_2_park_voice", DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      GeneralParkData.dataList = var2;
      this.updateParkUi((Bundle)null, var1);
      GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      this.updatePKeyRadar();
   }

   private void set0x27OutDoorTemp(Context var1) {
      this.updateOutDoorTemp(var1, this.resolveOutDoorTemp());
   }

   private void set0x29TrackData(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 10000, 16);
      this.updateParkUi((Bundle)null, var1);
   }

   private void set0x2FWheelCommand(Context var1) {
      int var2 = this.mKeyStatus;
      int var3 = this.mCanBusInfoInt[2];
      if (var2 != var3) {
         this.mKeyStatus = var3;
      }

      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 4) {
                  if (var3 != 5) {
                     switch (var3) {
                        case 17:
                           this.realKeyClick4(var1, 14);
                           break;
                        case 18:
                           this.realKeyClick4(var1, 15);
                           break;
                        case 19:
                           this.realKeyClick4(var1, 39);
                           break;
                        case 20:
                           this.realKeyClick4(var1, 40);
                           break;
                        case 21:
                           this.realKeyClick4(var1, 41);
                           break;
                        case 22:
                           this.realKeyClick4(var1, 135);
                           break;
                        case 23:
                           this.realKeyClick4(var1, 136);
                           break;
                        case 24:
                           if (this.mDifferentId == 1) {
                              this.realKeyClick4(var1, 137);
                           }
                           break;
                        case 25:
                           if (this.mDifferentId == 1) {
                              this.realKeyClick4(var1, 138);
                           }
                     }
                  } else {
                     this.realKeyClick4(var1, 17);
                  }
               } else {
                  this.realKeyClick4(var1, 23);
               }
            } else {
               this.realKeyClick4(var1, 22);
            }
         } else {
            this.realKeyClick4(var1, 46);
         }
      } else {
         this.realKeyClick4(var1, 45);
      }

   }

   private void set0x30VersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x31AirCleaningState() {
      if (this.mAirClean) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 != 15) {
            switch (var1) {
               case 1:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761365);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 2:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761368);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 3:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761369);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 4:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761370);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 5:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761371);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 6:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761372);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 7:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761373);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 8:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761374);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 9:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761375);
                  this.sendDisplayMsgView(this.mContext);
                  break;
               case 10:
                  GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761366);
                  this.sendDisplayMsgView(this.mContext);
            }
         } else {
            GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131761367);
            this.sendDisplayMsgView(this.mContext);
         }
      }

   }

   private void set0x32LeftRadar(Context var1) {
      if (this.is0x32DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setLeftRadarLocationDataType2(60, var2[2], 120, var2[3], 120, var2[4], 60, var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x33RightRadar(Context var1) {
      if (this.is0x33DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRightRadarLocationDataType2(60, var2[2], 165, var2[3], 165, var2[4], 60, var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x40CarState() {
      int[] var7 = this.mCanBusInfoInt;
      int var2 = var7[2];
      boolean var6 = false;
      int var1 = 0;
      boolean var4 = false;
      boolean var5 = true;
      boolean var3;
      SettingUpdateEntity var8;
      ArrayList var9;
      switch (var2) {
         case 0:
            var2 = var7[3];
            if (var2 <= 11) {
               var1 = var2;
            } else if (var2 != 13) {
               if (var2 != 22) {
                  if (var2 != 23) {
                     if (var2 != 29) {
                        if (var2 != 30) {
                           switch (var2) {
                              case 16:
                                 var1 = 13;
                                 break;
                              case 17:
                                 var1 = 14;
                                 break;
                              case 18:
                                 var1 = 15;
                           }
                        } else {
                           var1 = 19;
                        }
                     } else {
                        var1 = 18;
                     }
                  } else {
                     var1 = 17;
                  }
               } else {
                  var1 = 16;
               }
            } else {
               var1 = 12;
            }

            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_set_language").setValue(var1));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 16:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_0_0").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 32:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_1_0").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1) == 1) {
               var9.add(this.getSettingUpdateEntity("_2_setting_1_3").setValue("KM/H").setValueStr(true));
            } else {
               var9.add(this.getSettingUpdateEntity("_2_setting_1_3").setValue("MPH").setValueStr(true));
            }

            var9.add(this.getSettingUpdateEntity("_2_setting_1_1").setValue(this.mCanBusInfoInt[4]).setProgress(this.mCanBusInfoInt[4]));
            var9.add(this.getSettingUpdateEntity("_2_setting_1_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 48:
            var9 = new ArrayList();
            var8 = this.getSettingUpdateEntity("_2_setting_2_0").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1));
            if ((this.mCanBusInfoInt[4] & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var9.add(this.getSettingUpdateEntity("_2_setting_2_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_2_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_2_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 49:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_3_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_3_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var8 = this.getSettingUpdateEntity("_2_setting_3_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1));
            if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_3_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1));
            if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var9.add(this.getSettingUpdateEntity("_2_setting_3_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)));
            var8 = this.getSettingUpdateEntity("_2_setting_3_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
            var3 = var6;
            if ((this.mCanBusInfoInt[3] & 1) == 0) {
               var3 = true;
            }

            var9.add(var8.setEnable(var3));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 64:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_4_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_4_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_4_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + 1));
            var9.add(this.getSettingUpdateEntity("_2_setting_4_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + 1));
            var9.add(this.getSettingUpdateEntity("_2_setting_4_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + 1));
            var9.add(this.getSettingUpdateEntity("_2_setting_4_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) + 1));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 80:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_5_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
            var9.add(this.getSettingUpdateEntity("_2_setting_5_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_5_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_5_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_5_5").setValue(this.mCanBusInfoInt[4]).setProgress(this.mCanBusInfoInt[4] / 10));
            var9.add(this.getSettingUpdateEntity("_2_setting_5_6").setValue(this.mCanBusInfoInt[5]).setProgress(this.mCanBusInfoInt[5] / 5));
            var9.add(this.getSettingUpdateEntity("_2_setting_5_7").setValue(this.mCanBusInfoInt[6]).setProgress(this.mCanBusInfoInt[6] / 5));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 81:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_6_0").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_6_1").setValue(this.mCanBusInfoInt[4]));
            var9.add(this.getSettingUpdateEntity("_2_setting_6_2").setValue(this.mCanBusInfoInt[5]));
            var9.add(this.getSettingUpdateEntity("_2_setting_6_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3)));
            var9.add(this.getSettingUpdateEntity("_2_setting_6_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_6_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 82:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_7_1").setValue(this.mCanBusInfoInt[3]));
            var9.add(this.getSettingUpdateEntity("_2_setting_7_2").setValue(this.mCanBusInfoInt[4]));
            var9.add(this.getSettingUpdateEntity("_2_setting_7_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)));
            var9.add(this.getSettingUpdateEntity("_2_setting_7_4").setValue(this.mCanBusInfoInt[6]));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 96:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_8_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_8_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_8_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_8_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_8_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 112:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_9_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
            var9.add(this.getSettingUpdateEntity("_2_setting_9_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4)));
            var9.add(this.getSettingUpdateEntity("_2_setting_9_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_9_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_9_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_9_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 128:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_10_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_7").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_8").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_9").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_10_10").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 144:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_11_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_11_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_11_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_11_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4)));
            var9.add(this.getSettingUpdateEntity("_2_setting_11_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)));
            var9.add(this.getSettingUpdateEntity("_2_setting_11_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)));
            SharePreUtil.setIntValue(this.mContext, "_2_tire_unit", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 160:
            var9 = new ArrayList();
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1) == 1) {
               var9.add(this.getSettingUpdateEntity("_2_setting_12_2").setValue(this.mContext.getString(2131761662)).setValueStr(true));
            } else {
               var9.add(this.getSettingUpdateEntity("_2_setting_12_2").setValue(this.mContext.getString(2131761661)).setValueStr(true));
            }

            var9.add(this.getSettingUpdateEntity("_2_setting_12_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
            var8 = this.getSettingUpdateEntity("_2_setting_12_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_12_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_12_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
            var1 = var2;
            if (var2 == 2) {
               var1 = 1;
            }

            var8 = this.getSettingUpdateEntity("_2_setting_12_7").setValue(var1);
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3) {
               var3 = var5;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            SharePreUtil.setIntValue(this.mContext, "_2_off_individual", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4));
            break;
         case 161:
            var9 = new ArrayList();
            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            var1 = var2;
            if (var2 == 3) {
               var1 = 1;
            }

            var8 = this.getSettingUpdateEntity("_2_setting_13_1").setValue(var1);
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_13_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4));
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_13_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
            var1 = var2;
            if (var2 == 2) {
               var1 = 1;
            }

            var8 = this.getSettingUpdateEntity("_2_setting_13_4").setValue(var1);
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_13_5").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1));
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_13_6").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1));
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var8 = this.getSettingUpdateEntity("_2_setting_13_7").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1));
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var9.add(var8.setEnable(var3));
            var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
            var1 = var2;
            if (var2 == 3) {
               var1 = 1;
            }

            var8 = this.getSettingUpdateEntity("_2_setting_13_8").setValue(var1);
            var3 = var4;
            if (SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6) {
               var3 = true;
            }

            var9.add(var8.setEnable(var3));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 176:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_14_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 192:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_15_1").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
            var9.add(this.getSettingUpdateEntity("_2_setting_15_2").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_15_3").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1)));
            var9.add(this.getSettingUpdateEntity("_2_setting_15_4").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1)));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
            break;
         case 208:
            var9 = new ArrayList();
            var9.add(this.getSettingUpdateEntity("_2_setting_16_1").setValue(this.mCanBusInfoInt[3]));
            var9.add(this.getSettingUpdateEntity("_2_setting_16_2").setValue(this.mCanBusInfoInt[4]).setProgress(this.mCanBusInfoInt[4] - 30));
            var9.add(this.getSettingUpdateEntity("_2_setting_16_3").setValue(this.mCanBusInfoInt[5]).setProgress(this.mCanBusInfoInt[5] - 30));
            var9.add(this.getSettingUpdateEntity("_2_setting_16_4").setValue(this.mCanBusInfoInt[6]).setProgress(this.mCanBusInfoInt[6] - 30));
            this.updateGeneralSettingData(var9);
            this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x41CarEnable() {
      int var1 = this.mCanBusInfoInt[2];
      boolean var4 = false;
      boolean var5 = false;
      boolean var9 = false;
      boolean var13 = false;
      boolean var7 = false;
      boolean var3 = false;
      boolean var11 = false;
      boolean var8 = false;
      boolean var6 = false;
      boolean var12 = false;
      boolean var2 = false;
      boolean var10 = false;
      SettingUpdateEntity var16;
      ArrayList var17;
      if (var1 != 16) {
         ArrayList var14;
         SettingUpdateEntity var15;
         if (var1 != 32) {
            if (var1 != 64) {
               if (var1 != 96) {
                  if (var1 != 112) {
                     if (var1 != 128) {
                        if (var1 != 144) {
                           if (var1 != 176) {
                              if (var1 != 48) {
                                 if (var1 != 49) {
                                    if (var1 != 80) {
                                       if (var1 == 81) {
                                          var14 = new ArrayList();
                                          var15 = this.getSettingUpdateEntity("_2_setting_6_0");
                                          if ((this.mCanBusInfoInt[3] & 1) == 1) {
                                             var2 = true;
                                          } else {
                                             var2 = false;
                                          }

                                          var14.add(var15.setEnable(var2));
                                          var15 = this.getSettingUpdateEntity("_2_setting_6_1");
                                          if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                                             var2 = true;
                                          } else {
                                             var2 = false;
                                          }

                                          var14.add(var15.setEnable(var2));
                                          var15 = this.getSettingUpdateEntity("_2_setting_6_2");
                                          var2 = var10;
                                          if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                                             var2 = true;
                                          }

                                          var14.add(var15.setEnable(var2));
                                          this.updateGeneralSettingData(var14);
                                          this.updateSettingActivity((Bundle)null);
                                       }
                                    } else {
                                       var14 = new ArrayList();
                                       var15 = this.getSettingUpdateEntity("_2_setting_5_1");
                                       if ((this.mCanBusInfoInt[3] & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var14.add(var15.setEnable(var2));
                                       var15 = this.getSettingUpdateEntity("_2_setting_5_2");
                                       if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var14.add(var15.setEnable(var2));
                                       var15 = this.getSettingUpdateEntity("_2_setting_5_3");
                                       if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var14.add(var15.setEnable(var2));
                                       var15 = this.getSettingUpdateEntity("_2_setting_5_5");
                                       if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var14.add(var15.setEnable(var2));
                                       var15 = this.getSettingUpdateEntity("_2_setting_5_6");
                                       if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
                                          var2 = true;
                                       } else {
                                          var2 = false;
                                       }

                                       var14.add(var15.setEnable(var2));
                                       var15 = this.getSettingUpdateEntity("_2_setting_5_7");
                                       var2 = var4;
                                       if ((this.mCanBusInfoInt[3] >> 5 & 1) == 1) {
                                          var2 = true;
                                       }

                                       var14.add(var15.setEnable(var2));
                                       this.updateGeneralSettingData(var14);
                                       this.updateSettingActivity((Bundle)null);
                                    }
                                 } else {
                                    var14 = new ArrayList();
                                    var15 = this.getSettingUpdateEntity("_2_setting_3_1");
                                    if ((this.mCanBusInfoInt[3] & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var14.add(var15.setEnable(var2));
                                    var15 = this.getSettingUpdateEntity("_2_setting_3_2");
                                    if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var14.add(var15.setEnable(var2));
                                    var15 = this.getSettingUpdateEntity("_2_setting_3_3");
                                    if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var14.add(var15.setEnable(var2));
                                    var15 = this.getSettingUpdateEntity("_2_setting_3_4");
                                    if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var14.add(var15.setEnable(var2));
                                    var15 = this.getSettingUpdateEntity("_2_setting_3_5");
                                    if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
                                       var2 = true;
                                    } else {
                                       var2 = false;
                                    }

                                    var14.add(var15.setEnable(var2));
                                    var15 = this.getSettingUpdateEntity("_2_setting_3_6");
                                    var2 = var5;
                                    if ((this.mCanBusInfoInt[3] >> 5 & 1) == 1) {
                                       var2 = true;
                                    }

                                    var14.add(var15.setEnable(var2));
                                    this.updateGeneralSettingData(var14);
                                    this.updateSettingActivity((Bundle)null);
                                 }
                              } else {
                                 var14 = new ArrayList();
                                 var15 = this.getSettingUpdateEntity("_2_setting_2_0");
                                 if ((this.mCanBusInfoInt[3] & 1) == 1) {
                                    var2 = true;
                                 } else {
                                    var2 = false;
                                 }

                                 var14.add(var15.setEnable(var2));
                                 var15 = this.getSettingUpdateEntity("_2_setting_2_1");
                                 if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                                    var2 = true;
                                 } else {
                                    var2 = false;
                                 }

                                 var14.add(var15.setEnable(var2));
                                 var15 = this.getSettingUpdateEntity("_2_setting_2_3");
                                 var2 = var9;
                                 if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                                    var2 = true;
                                 }

                                 var14.add(var15.setEnable(var2));
                                 this.updateGeneralSettingData(var14);
                                 this.updateSettingActivity((Bundle)null);
                              }
                           } else {
                              var17 = new ArrayList();
                              var16 = this.getSettingUpdateEntity("_2_setting_14_1");
                              var2 = var13;
                              if ((this.mCanBusInfoInt[3] & 1) == 1) {
                                 var2 = true;
                              }

                              var17.add(var16.setEnable(var2));
                              this.updateGeneralSettingData(var17);
                              this.updateSettingActivity((Bundle)null);
                           }
                        } else {
                           var14 = new ArrayList();
                           var15 = this.getSettingUpdateEntity("_2_setting_11_1");
                           if ((this.mCanBusInfoInt[3] & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var14.add(var15.setEnable(var2));
                           var15 = this.getSettingUpdateEntity("_2_setting_11_2");
                           if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var14.add(var15.setEnable(var2));
                           var15 = this.getSettingUpdateEntity("_2_setting_11_3");
                           if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var14.add(var15.setEnable(var2));
                           var15 = this.getSettingUpdateEntity("_2_setting_11_4");
                           if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var14.add(var15.setEnable(var2));
                           var15 = this.getSettingUpdateEntity("_2_setting_11_5");
                           if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
                              var2 = true;
                           } else {
                              var2 = false;
                           }

                           var14.add(var15.setEnable(var2));
                           var15 = this.getSettingUpdateEntity("_2_setting_11_6");
                           var2 = var7;
                           if ((this.mCanBusInfoInt[3] >> 5 & 1) == 1) {
                              var2 = true;
                           }

                           var14.add(var15.setEnable(var2));
                           this.updateGeneralSettingData(var14);
                           this.updateSettingActivity((Bundle)null);
                        }
                     } else {
                        var14 = new ArrayList();
                        var15 = this.getSettingUpdateEntity("_2_setting_10_1");
                        if ((this.mCanBusInfoInt[3] & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_2");
                        if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_3");
                        if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_4");
                        if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_5");
                        if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_6");
                        if ((this.mCanBusInfoInt[3] >> 5 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_7");
                        if ((this.mCanBusInfoInt[3] >> 6 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_8");
                        if ((this.mCanBusInfoInt[3] >> 7 & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_9");
                        if ((this.mCanBusInfoInt[4] & 1) == 1) {
                           var2 = true;
                        } else {
                           var2 = false;
                        }

                        var14.add(var15.setEnable(var2));
                        var15 = this.getSettingUpdateEntity("_2_setting_10_10");
                        var2 = var3;
                        if ((this.mCanBusInfoInt[4] >> 1 & 1) == 1) {
                           var2 = true;
                        }

                        var14.add(var15.setEnable(var2));
                        this.updateGeneralSettingData(var14);
                        this.updateSettingActivity((Bundle)null);
                     }
                  } else {
                     var14 = new ArrayList();
                     var15 = this.getSettingUpdateEntity("_2_setting_9_1");
                     if ((this.mCanBusInfoInt[3] & 1) == 1) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     var14.add(var15.setEnable(var2));
                     var15 = this.getSettingUpdateEntity("_2_setting_9_2");
                     if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     var14.add(var15.setEnable(var2));
                     var15 = this.getSettingUpdateEntity("_2_setting_9_3");
                     var2 = var11;
                     if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                        var2 = true;
                     }

                     var14.add(var15.setEnable(var2));
                     this.updateGeneralSettingData(var14);
                     this.updateSettingActivity((Bundle)null);
                  }
               } else {
                  var14 = new ArrayList();
                  var15 = this.getSettingUpdateEntity("_2_setting_8_1");
                  if ((this.mCanBusInfoInt[3] & 1) == 1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  var14.add(var15.setEnable(var2));
                  var15 = this.getSettingUpdateEntity("_2_setting_8_2");
                  if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  var14.add(var15.setEnable(var2));
                  var15 = this.getSettingUpdateEntity("_2_setting_8_3");
                  if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  var14.add(var15.setEnable(var2));
                  var15 = this.getSettingUpdateEntity("_2_setting_8_4");
                  if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  var14.add(var15.setEnable(var2));
                  var15 = this.getSettingUpdateEntity("_2_setting_8_5");
                  var2 = var8;
                  if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
                     var2 = true;
                  }

                  var14.add(var15.setEnable(var2));
                  this.updateGeneralSettingData(var14);
                  this.updateSettingActivity((Bundle)null);
               }
            } else {
               var14 = new ArrayList();
               var15 = this.getSettingUpdateEntity("_2_setting_4_1");
               if ((this.mCanBusInfoInt[3] & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var14.add(var15.setEnable(var2));
               var15 = this.getSettingUpdateEntity("_2_setting_4_2");
               if ((this.mCanBusInfoInt[3] >> 6 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var14.add(var15.setEnable(var2));
               var15 = this.getSettingUpdateEntity("_2_setting_4_3");
               if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var14.add(var15.setEnable(var2));
               var15 = this.getSettingUpdateEntity("_2_setting_4_4");
               if ((this.mCanBusInfoInt[3] >> 2 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var14.add(var15.setEnable(var2));
               var15 = this.getSettingUpdateEntity("_2_setting_4_5");
               if ((this.mCanBusInfoInt[3] >> 3 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var14.add(var15.setEnable(var2));
               var15 = this.getSettingUpdateEntity("_2_setting_4_6");
               var2 = var6;
               if ((this.mCanBusInfoInt[3] >> 4 & 1) == 1) {
                  var2 = true;
               }

               var14.add(var15.setEnable(var2));
               this.updateGeneralSettingData(var14);
               this.updateSettingActivity((Bundle)null);
            }
         } else {
            var14 = new ArrayList();
            var15 = this.getSettingUpdateEntity("_2_setting_1_0");
            var2 = var12;
            if ((this.mCanBusInfoInt[3] >> 1 & 1) == 1) {
               var2 = true;
            }

            var14.add(var15.setEnable(var2));
            this.updateGeneralSettingData(var14);
            this.updateSettingActivity((Bundle)null);
         }
      } else {
         var17 = new ArrayList();
         var16 = this.getSettingUpdateEntity("_2_setting_0_0");
         if ((this.mCanBusInfoInt[3] & 1) == 1) {
            var2 = true;
         }

         var17.add(var16.setEnable(var2));
         this.updateGeneralSettingData(var17);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x50DriverData() {
      int[] var5 = this.mCanBusInfoInt;
      double var1;
      ArrayList var6;
      switch (var5[2]) {
         case 16:
            int var3 = var5[5];
            int var4 = var5[4];
            if ((var5[3] & 1) == 1) {
               this.mEndurance = " MI";
            } else {
               this.mEndurance = " KM";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 1, (var3 << 8 | var4) + this.mEndurance));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 32:
            var1 = (double)((float)(var5[4] + (var5[5] << 8) + (var5[6] << 16) + (var5[7] << 24)) / 10.0F);
            if ((var5[3] & 1) == 1) {
               this.mDistance = " MI";
            } else {
               this.mDistance = " KM";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 2, (new DecimalFormat("00")).format(var1) + this.mDistance));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 33:
            var1 = (double)((float)(var5[4] + (var5[5] << 8) + (var5[6] << 16) + (var5[7] << 24)) / 10.0F);
            if ((var5[3] & 1) == 1) {
               this.mDistance = " MI";
            } else {
               this.mDistance = " KM";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 3, (new DecimalFormat("00")).format(var1) + this.mDistance));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 34:
            var1 = (double)((float)(var5[4] + (var5[5] << 8) + (var5[6] << 16) + (var5[7] << 24)) / 10.0F);
            if ((var5[3] & 1) == 1) {
               this.mDistance = " MI";
            } else {
               this.mDistance = " KM";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 4, (new DecimalFormat("00")).format(var1) + this.mDistance));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 48:
            var1 = (double)((float)(var5[4] + var5[5] * 256) * 0.1F);
            if (DataHandleUtils.getIntFromByteWithBit(var5[3], 0, 2) == 0) {
               this.mFuelUnit = " L/100KM";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
               this.mFuelUnit = " KM/L";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
               this.mFuelUnit = " mpg(UK)";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
               this.mFuelUnit = " mpg(US)";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 5, (new DecimalFormat("00")).format(var1) + this.mFuelUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 49:
            var1 = (double)((float)(var5[4] + var5[5] * 256) / 10.0F);
            if (DataHandleUtils.getIntFromByteWithBit(var5[3], 0, 2) == 0) {
               this.mFuelUnit = " L/100KM";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
               this.mFuelUnit = " KM/L";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
               this.mFuelUnit = " mpg(UK)";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
               this.mFuelUnit = " mpg(US)";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 6, (new DecimalFormat("00")).format(var1) + this.mFuelUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 50:
            var1 = (double)((float)(var5[4] + var5[5] * 256) / 10.0F);
            if (DataHandleUtils.getIntFromByteWithBit(var5[3], 0, 2) == 0) {
               this.mFuelUnit = " L/100KM";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
               this.mFuelUnit = " KM/L";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
               this.mFuelUnit = " mpg(UK)";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
               this.mFuelUnit = " mpg(US)";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 7, (new DecimalFormat("00")).format(var1) + this.mFuelUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 64:
            var1 = (double)((float)(var5[4] + var5[5] * 256) / 10.0F);
            if ((var5[3] & 1) == 1) {
               this.mSpeedUnit = " MPH";
            } else {
               this.mSpeedUnit = " KM/H";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 8, (new DecimalFormat("00")).format(var1) + this.mSpeedUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 65:
            var1 = (double)((float)(var5[4] + var5[5] * 256) / 10.0F);
            if ((var5[3] & 1) == 1) {
               this.mSpeedUnit = " MPH";
            } else {
               this.mSpeedUnit = " KM/H";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 9, (new DecimalFormat("00")).format(var1) + this.mSpeedUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 66:
            var1 = (double)((float)(var5[4] + var5[5] * 256) / 10.0F);
            if ((var5[3] & 1) == 1) {
               this.mSpeedUnit = " MPH";
            } else {
               this.mSpeedUnit = " KM/H";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 10, (new DecimalFormat("00")).format(var1) + this.mSpeedUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 80:
            var1 = (double)(var5[3] + (var5[4] << 8) + (var5[5] << 16));
            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 11, (new DecimalFormat("00")).format(var1) + " MIN"));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 81:
            var1 = (double)(var5[3] + (var5[4] << 8) + (var5[5] << 16));
            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 12, (new DecimalFormat("00")).format(var1) + " MIN"));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 82:
            var1 = (double)(var5[3] + (var5[4] << 8) + (var5[5] << 16));
            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 13, (new DecimalFormat("00")).format(var1) + " MIN"));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 96:
            var1 = (double)(var5[4] + var5[5] * 256);
            if ((var5[3] & 1) == 1) {
               this.mElectric = " I/H";
            } else {
               this.mElectric = " GAI/H";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 14, (new DecimalFormat("00")).format(var1) + this.mElectric));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
            break;
         case 97:
            var1 = (double)((float)(var5[4] + var5[5] * 256) / 10.0F);
            if (DataHandleUtils.getIntFromByteWithBit(var5[3], 0, 2) == 0) {
               this.mFuelUnit = " L/100KM";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
               this.mFuelUnit = " KM/L";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
               this.mFuelUnit = " mpg(UK)";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
               this.mFuelUnit = " mpg(US)";
            }

            var6 = new ArrayList();
            var6.add(new DriverUpdateEntity(0, 15, (new DecimalFormat("00")).format(var1) + this.mFuelUnit));
            this.updateGeneralDriveData(var6);
            this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void set0x60Data(Context var1) {
      this.m0x60Msgs.clear();
      int[] var4 = this.mCanBusInfoInt;
      if (var4[2] <= 0) {
         if (var4[9] > 9) {
            return;
         }

         this.m0x60Msgs.add(new WarningEntity("\t" + CommUtil.getStrByResId(var1, this.mStartStopMsgs[this.mCanBusInfoInt[9]])));
         if (this.m0x60Msgs.size() != 0) {
            this.m0x60Msgs.add(0, new WarningEntity(CommUtil.getStrByResId(var1, "_2_information_title_2")));
         }
      } else {
         int var2 = 0;

         while(true) {
            var4 = this.mCanBusInfoInt;
            if (var2 < var4[2] && var2 < 6) {
               int var3 = var2 + 3;
               if (var3 < var4.length && var4[var3] < 34) {
                  this.m0x60Msgs.add(new WarningEntity("\t" + CommUtil.getStrByResId(var1, this.mVehicleTips[this.mCanBusInfoInt[var3]])));
                  ++var2;
                  continue;
               }

               return;
            }

            if (this.m0x60Msgs.size() != 0) {
               this.m0x60Msgs.add(0, new WarningEntity(CommUtil.getStrByResId(var1, "_2_information_title_1")));
            }
            break;
         }
      }

      this.updateWarningActivity();
   }

   private void set0x61Data(Context var1) {
      this.m0x61Msgs.clear();
      int[] var7 = this.mCanBusInfoInt;
      int var3 = var7[3];
      int var4 = var7[2];
      int var2 = 0;

      while(true) {
         if (var2 >= (var4 | var3 << 8)) {
            if (this.m0x61Msgs.size() != 0) {
               this.m0x61Msgs.add(0, new WarningEntity(CommUtil.getStrByResId(var1, "_2_information_title_3")));
            }

            this.updateWarningActivity();
            return;
         }

         int var5 = var2 * 2;
         int var6 = var5 + 5;
         var7 = this.mCanBusInfoInt;
         if (var6 >= var7.length) {
            break;
         }

         var5 += 4;
         if (var5 >= var7.length) {
            break;
         }

         var6 = var7[var6];
         var5 = var7[var5] | var6;
         if (var5 < 155) {
            this.m0x61Msgs.add(new WarningEntity("\t" + CommUtil.getStrByResId(var1, this.mVehicleReports[var5])));
         }

         ++var2;
      }

   }

   private void set0x62Data(Context var1) {
      this.m0x62Msgs.clear();
      int var2 = 0;

      while(true) {
         int[] var4 = this.mCanBusInfoInt;
         if (var2 < var4[2] && var2 < 3) {
            int var3 = var2 + 3;
            if (var3 < var4.length && var4[var3] < 18) {
               this.m0x62Msgs.add(new WarningEntity("\t" + CommUtil.getStrByResId(var1, this.mConvTips[this.mCanBusInfoInt[var3]])));
               ++var2;
               continue;
            }

            return;
         }

         if (this.m0x62Msgs.size() != 0) {
            this.m0x62Msgs.add(0, new WarningEntity(CommUtil.getStrByResId(var1, "_2_information_title_4")));
         }

         this.updateWarningActivity();
         return;
      }
   }

   private void set0x65TireWarning() {
      if (this.is0x65DataChange()) {
         for(int var1 = 0; var1 < this.mTireUpdateEntityList.size(); ++var1) {
            int var3 = this.mCanBusInfoInt[2];
            byte var2 = 1;
            if ((var3 & 1 << var1) == 0) {
               var2 = 0;
            }

            ArrayList var5 = new ArrayList();
            var5.add(this.mTireInfoArray[var1]);
            var5.add(this.mTireInfoArray2[var1]);
            String[] var4 = this.mTireWarningArray;
            var4[var1] = "";
            if (var2 != 0) {
               var3 = this.mCanBusInfoInt[3];
               if (var3 >= 2 && var3 <= 4) {
                  var4[var1] = CommUtil.getStrByResId(this.mContext, "_2_tire_warning_" + this.mCanBusInfoInt[3]);
               }
            }

            var5.add(this.mTireWarningArray[var1]);
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setList(var5);
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setTireStatus(var2);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void set0x66TireInfo() {
      if (this.is0x66DataChange()) {
         Context var3 = this.mContext;
         int var1 = 0;
         int var2 = SharePreUtil.getIntValue(var3, "_2_tire_unit", 0);
         if (var2 >= this.mTireResolveList.size()) {
            return;
         }

         for(TireResolve var5 = (TireResolve)this.mTireResolveList.get(var2); var1 < this.mTireUpdateEntityList.size(); ++var1) {
            ArrayList var6 = new ArrayList();
            String var4 = this.mDecimalFormat.format((double)((float)this.mCanBusInfoInt[var1 + 3] * var5.calculate)) + var5.unit;
            if (this.mCanBusInfoInt[2] == 0) {
               this.mTireInfoArray[var1] = var4;
            } else {
               this.mTireInfoArray2[var1] = var4;
            }

            var6.add(this.mTireInfoArray[var1]);
            var6.add(this.mTireInfoArray2[var1]);
            var6.add(this.mTireWarningArray[var1]);
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setList(var6);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void set0x95ERAInfo() {
      ArrayList var1 = new ArrayList();
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 8) == 0) {
         var1.add(this.getSettingUpdateEntity("_2_ERA_").setValue("OFF"));
      } else {
         var1.add(this.getSettingUpdateEntity("_2_ERA_").setValue("ON"));
      }

      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void updateSetUi(String var1) {
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_2_setting_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_2_setting_1", "_2_setting_1_4"), var1)).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void updateWarningActivity() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.m0x60Msgs);
      var1.addAll(this.m0x61Msgs);
      var1.addAll(this.m0x62Msgs);
      GeneralWarningDataData.dataList = var1;
      this.updateWarningActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mDecimalFormat = new DecimalFormat("0.0");
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.frontRadarStatus = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 34, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 18, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);

      Exception var10000;
      label179: {
         boolean var10001;
         int[] var29;
         try {
            this.mCanBusInfoByte = var2;
            var29 = this.getByteArrayToIntArray(var2);
            this.mCanBusInfoInt = var29;
         } catch (Exception var27) {
            var10000 = var27;
            var10001 = false;
            break label179;
         }

         int var3 = var29[1];
         if (var3 != 22) {
            if (var3 != 39) {
               if (var3 != 41) {
                  if (var3 != 80) {
                     if (var3 != 149) {
                        if (var3 != 64) {
                           if (var3 != 65) {
                              if (var3 != 101) {
                                 if (var3 != 102) {
                                    switch (var3) {
                                       case 32:
                                          try {
                                             this.set0x20WheelKey(var1);
                                             return;
                                          } catch (Exception var9) {
                                             var10000 = var9;
                                             var10001 = false;
                                             break;
                                          }
                                       case 33:
                                          try {
                                             this.set0x21AirInfo(var1);
                                             return;
                                          } catch (Exception var8) {
                                             var10000 = var8;
                                             var10001 = false;
                                             break;
                                          }
                                       case 34:
                                          try {
                                             this.set0x22RearRadar(var1);
                                             return;
                                          } catch (Exception var7) {
                                             var10000 = var7;
                                             var10001 = false;
                                             break;
                                          }
                                       case 35:
                                          try {
                                             this.set0x23FrontRadar(var1);
                                             return;
                                          } catch (Exception var6) {
                                             var10000 = var6;
                                             var10001 = false;
                                             break;
                                          }
                                       case 36:
                                          try {
                                             this.set0x24DoorData(var1);
                                             return;
                                          } catch (Exception var5) {
                                             var10000 = var5;
                                             var10001 = false;
                                             break;
                                          }
                                       case 37:
                                          try {
                                             this.set0x25ParkingAssist(var1);
                                             return;
                                          } catch (Exception var4) {
                                             var10000 = var4;
                                             var10001 = false;
                                             break;
                                          }
                                       default:
                                          switch (var3) {
                                             case 47:
                                                try {
                                                   this.set0x2FWheelCommand(var1);
                                                   return;
                                                } catch (Exception var14) {
                                                   var10000 = var14;
                                                   var10001 = false;
                                                   break;
                                                }
                                             case 48:
                                                try {
                                                   this.set0x30VersionData(var1);
                                                   return;
                                                } catch (Exception var13) {
                                                   var10000 = var13;
                                                   var10001 = false;
                                                   break;
                                                }
                                             case 49:
                                                try {
                                                   this.set0x31AirCleaningState();
                                                   return;
                                                } catch (Exception var12) {
                                                   var10000 = var12;
                                                   var10001 = false;
                                                   break;
                                                }
                                             case 50:
                                                try {
                                                   this.set0x32LeftRadar(var1);
                                                   return;
                                                } catch (Exception var11) {
                                                   var10000 = var11;
                                                   var10001 = false;
                                                   break;
                                                }
                                             case 51:
                                                try {
                                                   this.set0x33RightRadar(var1);
                                                   return;
                                                } catch (Exception var10) {
                                                   var10000 = var10;
                                                   var10001 = false;
                                                   break;
                                                }
                                             default:
                                                switch (var3) {
                                                   case 96:
                                                      try {
                                                         this.set0x60Data(var1);
                                                         return;
                                                      } catch (Exception var17) {
                                                         var10000 = var17;
                                                         var10001 = false;
                                                         break;
                                                      }
                                                   case 97:
                                                      try {
                                                         this.set0x61Data(var1);
                                                         return;
                                                      } catch (Exception var16) {
                                                         var10000 = var16;
                                                         var10001 = false;
                                                         break;
                                                      }
                                                   case 98:
                                                      try {
                                                         this.set0x62Data(var1);
                                                         return;
                                                      } catch (Exception var15) {
                                                         var10000 = var15;
                                                         var10001 = false;
                                                         break;
                                                      }
                                                   default:
                                                      return;
                                                }
                                          }
                                    }
                                 } else {
                                    try {
                                       this.set0x66TireInfo();
                                       return;
                                    } catch (Exception var18) {
                                       var10000 = var18;
                                       var10001 = false;
                                    }
                                 }
                              } else {
                                 try {
                                    this.set0x65TireWarning();
                                    return;
                                 } catch (Exception var19) {
                                    var10000 = var19;
                                    var10001 = false;
                                 }
                              }
                           } else {
                              try {
                                 this.set0x41CarEnable();
                                 return;
                              } catch (Exception var20) {
                                 var10000 = var20;
                                 var10001 = false;
                              }
                           }
                        } else {
                           try {
                              this.set0x40CarState();
                              return;
                           } catch (Exception var21) {
                              var10000 = var21;
                              var10001 = false;
                           }
                        }
                     } else {
                        try {
                           this.set0x95ERAInfo();
                           return;
                        } catch (Exception var22) {
                           var10000 = var22;
                           var10001 = false;
                        }
                     }
                  } else {
                     try {
                        this.set0x50DriverData();
                        return;
                     } catch (Exception var23) {
                        var10000 = var23;
                        var10001 = false;
                     }
                  }
               } else {
                  try {
                     this.set0x29TrackData(var1);
                     return;
                  } catch (Exception var24) {
                     var10000 = var24;
                     var10001 = false;
                  }
               }
            } else {
               try {
                  this.set0x27OutDoorTemp(var1);
                  return;
               } catch (Exception var25) {
                  var10000 = var25;
                  var10001 = false;
               }
            }
         } else {
            try {
               this.set0x16SpeedInfo();
               return;
            } catch (Exception var26) {
               var10000 = var26;
               var10001 = false;
            }
         }
      }

      Exception var28 = var10000;
      Log.e("CanBusError", var28.toString());
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      short var3;
      if (var2) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(DataHandleUtils.rangeNumber(var1, 0, 30) | var3)});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      short var14;
      if (var10) {
         var14 = 0;
      } else {
         var14 = 128;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var2, (byte)var3, (byte)var4, (byte)(var5 | var14), (byte)var6, (byte)var7, (byte)(var9 - 1)});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      int[] var18 = this.getTime(var2);
      byte var17 = (byte)var4;
      byte var16 = (byte)var6;
      byte var15 = (byte)var18[0];
      byte var14 = (byte)var18[1];
      var1 = (byte)var18[2];
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, var17, var16, var15, var14, var1});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -64, 10, 0, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 64, -1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 65, -1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 80, -1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 99, -1});
      GeneralTireData.isHaveSpareTire = false;
      this.initSettingsItem(var1);
      this.initMessages(var1);
      this.initTireResolveTool();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 9;
      } else {
         var25 = 8;
      }

      var6 = (byte)var25;
      var2 = (byte)var3;
      var5 = (byte)(var4 & 255);
      var1 = (byte)(var4 >> 8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, var6, 18, var2, var9, var5, var1, 0, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = this.getBandDate(var2);
      int[] var10 = this.getFreqByteHiLo(var2, var3);
      byte var8 = (byte)var5;
      byte var9 = (byte)var10[1];
      byte var6 = (byte)var10[0];
      byte var7 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var8, var9, var6, var7, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void tireSetting() {
      (new CountDownTimer(this, 3000L, 500L) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            this.this$0.updateSetUi("  ");
         }

         public void onTick(long var1) {
            StringBuilder var3 = (new StringBuilder()).append("时间：");
            var1 /= 500L;
            MyLog.temporaryTracking(var3.append(var1).toString());
            if (var1 == 6L) {
               this.this$0.updateSetUi("Resetting");
            } else if (var1 == 5L) {
               this.this$0.updateSetUi("Resetting.");
            } else if (var1 == 4L) {
               this.this$0.updateSetUi("Resetting..");
            } else if (var1 == 3L) {
               this.this$0.updateSetUi("Resetting...");
            } else if (var1 == 2L) {
               this.this$0.updateSetUi("Resetting....");
            } else if (var1 == 1L) {
               this.this$0.updateSetUi("SUCCESS");
            }

         }
      }).start();
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 9;
      } else {
         var18 = 8;
      }

      var1 = (byte)var18;
      var2 = (byte)var3;
      var6 = (byte)(var4 & 255);
      var5 = (byte)(var4 >> 8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, var1, 18, var2, var9, var6, var5, 0, 0});
   }

   private class TireResolve {
      float calculate;
      final MsgMgr this$0;
      String unit;

      public TireResolve(MsgMgr var1, String var2, float var3) {
         this.this$0 = var1;
         this.unit = var2;
         this.calculate = var3;
      }
   }
}
