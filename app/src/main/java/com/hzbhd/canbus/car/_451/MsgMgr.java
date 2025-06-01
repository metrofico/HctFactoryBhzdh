package com.hzbhd.canbus.car._451;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._451.data.EquipData;
import com.hzbhd.canbus.car_cus._451.observer.ObserverBuilding451;
import com.hzbhd.canbus.car_cus._451.util.CycleRequestRadar;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.media.aux2.action.ActionTag;
import com.hzbhd.canbus.media.aux2.manager.Aux2Manager;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.ISourceListener;
import com.hzbhd.proxy.sourcemanager.SourceManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mInfo1;
   int[] mInfo2;
   int[] mInfo3;
   int[] mInfo4;
   int[] mInfo5;
   int[] mInfo6;
   int[] mInfo7;
   int[] mPanoramicInfo;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private int getRadarRange(int var1) {
      if (var1 > 10) {
         return 0;
      } else {
         int var2 = var1;
         if (var1 == 0) {
            var2 = 1;
         }

         return var2;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is0x61NoChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return true;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = var1;
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFun1NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo1, var1)) {
         return true;
      } else {
         this.mInfo1 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFun2NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo2, var1)) {
         return true;
      } else {
         this.mInfo2 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFun3NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo3, var1)) {
         return true;
      } else {
         this.mInfo3 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFun4NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo4, var1)) {
         return true;
      } else {
         this.mInfo4 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFun5NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo5, var1)) {
         return true;
      } else {
         this.mInfo5 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFun6NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo6, var1)) {
         return true;
      } else {
         this.mInfo6 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isFun7NoChange(int[] var1) {
      if (Arrays.equals(this.mInfo7, var1)) {
         return true;
      } else {
         this.mInfo7 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void registerAux2() {
      SourceManager.getInstance().registerSourceListener(SourceConstantsDef.SOURCE_ID.AUX2, SourceConstantsDef.DISP_ID.disp_main, new ISourceListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onAction(SourceConstantsDef.SOURCE_ACTION var1, Bundle var2) {
            if (var1.equals(SourceConstantsDef.SOURCE_ACTION.PLAY)) {
               this.this$0.requestToOriginalMode(true);
            }

            if (var1.equals(SourceConstantsDef.SOURCE_ACTION.PAUSE)) {
               this.this$0.requestToOriginalMode(false);
            }

            if (var1.equals(SourceConstantsDef.SOURCE_ACTION.STOP)) {
               this.this$0.requestToOriginalMode(false);
            }

         }
      });
   }

   private void requestToOriginalMode(boolean var1) {
      if (var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0});
      }

   }

   private void set0x16(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_current_vehicle_speed"), String.valueOf(this.getMsbLsbResult(var1[2], var1[3]))));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_current_speed"), String.valueOf(this.getMsbLsbResult(var1[4], var1[5]))));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x22(int[] var1) {
      int var2 = var1[2];
      if (var2 != 9) {
         if (var2 != 129) {
            if (var2 != 130) {
               if (var2 != 145) {
                  if (var2 != 146) {
                     switch (var2) {
                        case 0:
                           this.realKeyLongClick1(this.mContext, 0, var1[3]);
                           break;
                        case 1:
                           this.realKeyLongClick1(this.mContext, 1, var1[3]);
                           break;
                        case 2:
                           this.realKeyLongClick1(this.mContext, 45, var1[3]);
                           break;
                        case 3:
                           this.realKeyLongClick1(this.mContext, 46, var1[3]);
                           break;
                        case 4:
                           this.realKeyLongClick1(this.mContext, 8, var1[3]);
                           break;
                        case 5:
                           this.realKeyLongClick1(this.mContext, 7, var1[3]);
                           break;
                        case 6:
                           this.realKeyLongClick1(this.mContext, 3, var1[3]);
                           break;
                        default:
                           switch (var2) {
                              case 16:
                                 this.realKeyLongClick1(this.mContext, 128, var1[3]);
                                 break;
                              case 17:
                                 this.realKeyLongClick1(this.mContext, 187, var1[3]);
                                 break;
                              case 18:
                                 this.realKeyLongClick1(this.mContext, 14, var1[3]);
                                 break;
                              case 19:
                                 this.realKeyLongClick1(this.mContext, 4112, var1[3]);
                                 break;
                              case 20:
                                 this.realKeyLongClick1(this.mContext, 4113, var1[3]);
                                 break;
                              case 21:
                                 this.startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusMainActivity);
                                 break;
                              case 22:
                                 this.realKeyLongClick1(this.mContext, 4, var1[3]);
                                 break;
                              case 23:
                                 this.realKeyLongClick1(this.mContext, 58, var1[3]);
                                 break;
                              case 24:
                                 this.startOtherActivity(this.mContext, HzbhdComponentName.CanBusDriveDataActivity);
                                 break;
                              case 25:
                                 this.realKeyLongClick1(this.mContext, 15, var1[3]);
                                 break;
                              default:
                                 switch (var2) {
                                    case 32:
                                       this.realKeyLongClick1(this.mContext, 151, var1[3]);
                                       break;
                                    case 33:
                                       this.realKeyLongClick1(this.mContext, 21, var1[3]);
                                       break;
                                    case 34:
                                       this.realKeyLongClick1(this.mContext, 20, var1[3]);
                                       break;
                                    case 35:
                                       this.realKeyLongClick1(this.mContext, 47, var1[3]);
                                       break;
                                    case 36:
                                       this.realKeyLongClick1(this.mContext, 48, var1[3]);
                                       break;
                                    case 37:
                                       this.realKeyLongClick1(this.mContext, 49, var1[3]);
                                       break;
                                    case 38:
                                       this.realKeyLongClick1(this.mContext, 50, var1[3]);
                                       break;
                                    case 39:
                                       this.realKeyLongClick1(this.mContext, 50, var1[3]);
                                 }
                           }
                     }
                  } else {
                     this.knobKey(8);
                  }
               } else {
                  this.knobKey(7);
               }
            } else {
               this.realKeyClick4(this.mContext, 47);
            }
         } else {
            this.realKeyClick4(this.mContext, 48);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 2, var1[3]);
      }

   }

   private void set0x47() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_451_resolving_power"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_resolving_power_1"), this.mCanBusInfoInt[2] + 1)).setProgress(this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x52(int[] var1) {
      // $FF: Couldn't be decompiled
   }

   private void set0x61() {
      if (!this.is0x61NoChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarRange(this.mCanBusInfoInt[4]), this.getRadarRange(this.mCanBusInfoInt[5]), this.getRadarRange(this.mCanBusInfoInt[6]), this.getRadarRange(this.mCanBusInfoInt[7]));
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.getRadarRange(this.mCanBusInfoInt[8]), this.getRadarRange(this.mCanBusInfoInt[9]), this.getRadarRange(this.mCanBusInfoInt[10]), this.getRadarRange(this.mCanBusInfoInt[11]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         int[] var1;
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])) {
            var1 = this.mCanBusInfoInt;
            var1[12] = DataHandleUtils.blockBit(var1[12], 7);
            var1 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = -(DataHandleUtils.getMsbLsbResult(var1[12], var1[13]) / 31 + 1);
         } else {
            var1 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = DataHandleUtils.getMsbLsbResult(var1[12], var1[13]) / 31 + 1;
         }

         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x62() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 1) {
         Aux2Manager.getInstance().openAux2(this.mContext, 1280, 720);
      } else if (var1 == 0) {
         Aux2Manager.getInstance().exitAux2(ActionTag.TAG.EXIT, "null");
      }

   }

   private void setAir0x24(int[] var1) {
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit2(var1[2]);
         boolean var2;
         if (DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 2) == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralAirData.sync = var2;
         if (DataHandleUtils.getBoolBit3(var1[2])) {
            GeneralAirData.front_left_temperature = var1[3] + this.getTempUnitF(this.mContext);
         } else {
            GeneralAirData.front_left_temperature = (double)var1[3] * 0.5 + this.getTempUnitC(this.mContext);
         }

         if (DataHandleUtils.getBoolBit3(var1[2])) {
            GeneralAirData.front_right_temperature = var1[4] + this.getTempUnitF(this.mContext);
         } else {
            GeneralAirData.front_right_temperature = (double)var1[4] * 0.5 + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[5]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit5(var1[5]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(var1[5]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 4);
         GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit7(var1[7]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[7]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[8], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[9], 4, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(var1[9], 0, 2);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[10]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit5(var1[10]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(var1[10]);
         GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[11], 0, 4);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setAmpl(int[] var1) {
      if (!this.isFun6NoChange(var1)) {
         int var2 = var1[11];
         boolean var3 = false;
         if (var2 == 16) {
            EquipData.bas = 0;
         } else if (var2 >= 11 && var2 <= 15) {
            EquipData.bas = var2 - 16;
         } else if (var2 < 11) {
            EquipData.bas = -5;
         } else if (var2 >= 17 && var2 <= 21) {
            EquipData.bas = var2 - 16;
         } else if (var2 > 21) {
            EquipData.bas = 5;
         }

         var2 = var1[12];
         if (var2 == 16) {
            EquipData.mid = 0;
         } else if (var2 >= 11 && var2 <= 15) {
            EquipData.mid = var2 - 16;
         } else if (var2 < 11) {
            EquipData.mid = -5;
         } else if (var2 >= 17 && var2 <= 21) {
            EquipData.mid = var2 - 16;
         } else if (var2 > 21) {
            EquipData.mid = 5;
         }

         var2 = var1[13];
         if (var2 == 16) {
            EquipData.tre = 0;
         } else if (var2 >= 11 && var2 <= 15) {
            EquipData.tre = var2 - 16;
         } else if (var2 < 11) {
            EquipData.tre = -5;
         } else if (var2 >= 17 && var2 <= 21) {
            EquipData.tre = var2 - 16;
         } else if (var2 > 21) {
            EquipData.tre = 5;
         }

         var2 = var1[14];
         if (var2 == 16) {
            EquipData.fad = 0;
         } else if (var2 >= 9 && var2 <= 15) {
            EquipData.fad = var2 - 16;
         } else if (var2 < 9) {
            EquipData.fad = -7;
         } else if (var2 >= 17 && var2 <= 23) {
            EquipData.fad = var2 - 16;
         } else if (var2 > 23) {
            EquipData.fad = -7;
         }

         var2 = var1[15];
         if (var2 == 16) {
            EquipData.bal = 0;
         } else if (var2 >= 9 && var2 <= 15) {
            EquipData.bal = var2 - 16;
         } else if (var2 < 9) {
            EquipData.bal = -7;
         } else if (var2 >= 17 && var2 <= 23) {
            EquipData.bal = var2 - 16;
         } else if (var2 > 23) {
            EquipData.bal = -7;
         }

         if (var1[16] == 1) {
            var3 = true;
         }

         EquipData.asl = var3;
         this.updateEquip();
      }
   }

   private void setBasicInfo(int[] var1) {
      if (!this.isFun7NoChange(var1)) {
         int var2 = var1[3];
         if (var2 == 1) {
            EquipData.band = "FM1";
         } else if (var2 == 2) {
            EquipData.band = "FM2";
         } else if (var2 == 16) {
            EquipData.band = "AM";
         }

         var2 = var1[4];
         if (var2 == 0) {
            EquipData.isShowAslIcon = false;
         } else if (var2 == 1) {
            EquipData.isShowAslIcon = false;
         }

         EquipData.disc_in = DataHandleUtils.getIntFromByteWithBit(var1[5], 6, 2);
         EquipData.disc1 = DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 2);
         EquipData.disc2 = DataHandleUtils.getIntFromByteWithBit(var1[5], 2, 2);
         EquipData.disc3 = DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 2);
         EquipData.disc4 = DataHandleUtils.getIntFromByteWithBit(var1[6], 6, 2);
         EquipData.disc5 = DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 2);
         EquipData.disc6 = DataHandleUtils.getIntFromByteWithBit(var1[6], 2, 2);
         EquipData.isShowBtIcon = DataHandleUtils.getBoolBit7(var1[7]);
         EquipData.isShowPhoneIcon = DataHandleUtils.getBoolBit6(var1[7]);
         EquipData.signal = DataHandleUtils.getIntFromByteWithBit(var1[7], 0, 4);
         EquipData.scan = DataHandleUtils.getIntFromByteWithBit(var1[8], 6, 2);
         EquipData.rpt = DataHandleUtils.getIntFromByteWithBit(var1[8], 4, 2);
         EquipData.rand = DataHandleUtils.getIntFromByteWithBit(var1[8], 2, 2);
         this.updateEquip();
      }
   }

   private void setBtInfo(int[] var1) {
      if (!this.isFun2NoChange(var1)) {
         String var2;
         if (var1[12] == 255) {
            var2 = "";
         } else {
            var2 = this.mContext.getString(2131766260) + "：" + var1[12];
         }

         EquipData.btSongNumber = var2;
         EquipData.btTimeStr = this.mContext.getString(2131766261) + "：" + var1[14] + ":" + var1[15];
         this.updateEquip();
      }
   }

   private void setCdInfo(int[] var1) {
      if (!this.isFun3NoChange(var1)) {
         EquipData.cdNumber = this.mContext.getString(2131766242) + "：" + var1[10];
         EquipData.cdSongNumber = this.mContext.getString(2131766260) + "：" + var1[12];
         EquipData.cdTimeStr = this.mContext.getString(2131766261) + "：" + var1[14] + ":" + var1[15];
         this.updateEquip();
      }
   }

   private void setDoorInfo0x12(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[6]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(var1[6]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[6]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(var1[8]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setDriveInfo0x12(int[] var1) {
      ArrayList var5 = new ArrayList();
      int var2 = var1[2];
      String var3;
      if (var2 == 1) {
         var3 = this.mContext.getString(2131766203);
      } else if (var2 == 2) {
         var3 = this.mContext.getString(2131766252);
      } else if (var2 == 3) {
         var3 = this.mContext.getString(2131766251);
      } else {
         var3 = this.mContext.getString(2131766202);
      }

      var5.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_acc_status"), var3));
      var2 = var1[3];
      if (var2 == 1) {
         var3 = "R";
      } else if (var2 == 2) {
         var3 = "N";
      } else if (var2 == 3) {
         var3 = "D";
      } else {
         var3 = "P";
      }

      var5.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_gear_status"), var3));
      var2 = var1[4];
      String var4 = "----";
      if (var2 == 0) {
         var3 = "unLock";
      } else if (var2 == 1) {
         var3 = "lock";
      } else {
         var3 = "----";
      }

      var5.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_central_door_lock"), var3));
      var2 = var1[5];
      if (var2 == 0) {
         var3 = "OFF";
      } else if (var2 == 1) {
         var3 = "ON";
      } else {
         var3 = "----";
      }

      var5.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_hand_brake_status"), var3));
      var2 = var1[7];
      if (var2 > 0 && var2 < 127) {
         var3 = String.valueOf(var2);
      } else {
         var3 = "----";
      }

      var5.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_current_oil_quantity"), var3));
      var2 = var1[10];
      String var6;
      if (var2 == 0) {
         var6 = "OFF";
      } else {
         var6 = var4;
         if (var2 == 1) {
            var6 = "ON";
         }
      }

      var5.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_small_lamp_status"), var6));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRadioInfo(int[] var1) {
      if (!this.isFun4NoChange(var1)) {
         int var2 = var1[10];
         if (var2 == 0) {
            EquipData.presetStr = "";
         } else {
            EquipData.presetStr = String.valueOf(var2);
         }

         if (!EquipData.band.equals("FM1") && !EquipData.band.equals("FM2")) {
            if (EquipData.band.equals("AM")) {
               EquipData.frequency = this.getMsbLsbResult(var1[11], var1[12]) + "KHz";
            }
         } else {
            EquipData.frequency = this.df_2Decimal.format((double)((float)this.getMsbLsbResult(var1[11], var1[12]) / 100.0F)) + "MHz";
         }

         EquipData.isShowSt = DataHandleUtils.getBoolBit7(var1[13]);
         this.updateEquip();
      }
   }

   private void setTextInfo(int[] var1) {
      if (!this.isFun5NoChange(var1)) {
         byte[] var3 = new byte[22];

         for(int var2 = 11; var2 <= 33; ++var2) {
            var3[var2 - 11] = (byte)var1[var2];
         }

         EquipData.txtInfo = this.getVersionStr(var3);
         this.updateEquip();
      }
   }

   private void setUsbInfo(int[] var1) {
      if (!this.isFun1NoChange(var1)) {
         EquipData.usbFileNumber = this.mContext.getString(2131766244) + "：" + var1[11];
         EquipData.usbSongNumber = this.mContext.getString(2131766243) + "：" + var1[13];
         EquipData.usbTimeStr = this.mContext.getString(2131766261) + "：" + var1[14] + ":" + var1[15];
         this.updateEquip();
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 18) {
         if (var3 != 22) {
            if (var3 != 34) {
               if (var3 != 36) {
                  if (var3 != 48) {
                     if (var3 != 71) {
                        if (var3 != 82) {
                           if (var3 != 97) {
                              if (var3 == 98) {
                                 this.set0x62();
                              }
                           } else {
                              this.set0x61();
                           }
                        } else {
                           this.set0x52(var4);
                        }
                     } else {
                        this.set0x47();
                     }
                  } else {
                     this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
                  }
               } else {
                  this.setAir0x24(var4);
               }
            } else {
               this.set0x22(var4);
            }
         } else {
            this.set0x16(var4);
         }
      } else {
         this.setDriveInfo0x12(var4);
         this.setDoorInfo0x12(this.mCanBusInfoInt);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.registerAux2();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void reverseStateChange(boolean var1) {
      super.reverseStateChange(var1);
      if (var1) {
         CycleRequestRadar.getInstance().start(10, new ActionCallback(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void toDo(Object var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -112, 97});
               CycleRequestRadar.getInstance().reset(500);
            }
         });
      } else {
         CycleRequestRadar.getInstance().stop();
      }

   }

   public void updateEquip() {
      ObserverBuilding451.getInstance().dataChange();
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettingsUi(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }
}
