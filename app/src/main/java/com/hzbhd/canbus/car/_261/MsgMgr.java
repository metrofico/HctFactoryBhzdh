package com.hzbhd.canbus.car._261;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private int FreqInt;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_FRONT_OPEN = "is_front_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private byte freqHi;
   private byte freqLo;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private String getCloseOpenStr(int var1) {
      if (var1 == 0) {
         return this.mContext.getResources().getString(2131768042);
      } else {
         return var1 == 1 ? this.mContext.getResources().getString(2131769504) : "set_default";
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) != GeneralDoorData.isFrontOpen;
      }
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyControl() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           if (var1 != 21) {
                              if (var1 != 22) {
                                 switch (var1) {
                                    case 9:
                                       this.realKeyClick(14);
                                       break;
                                    case 10:
                                       this.realKeyClick(188);
                                       break;
                                    case 11:
                                       this.realKeyClick(187);
                                 }
                              } else {
                                 this.realKeyClick(49);
                              }
                           } else {
                              this.realKeyClick(50);
                           }
                        } else {
                           this.realKeyClick(2);
                        }
                     } else {
                        this.realKeyClick(3);
                     }
                  } else {
                     this.realKeyClick3(this.mContext, 46, var1, var2[3]);
                  }
               } else {
                  this.realKeyClick3(this.mContext, 45, var1, var2[3]);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      if (var1 == 0) {
         (new StringBuilder()).append(this.mContext.getResources().getString(2131769794)).append(" 4").toString();
      } else if (255 == var1) {
         (new StringBuilder()).append(this.mContext.getResources().getString(2131768712)).append(" 4").toString();
      }

      String var2;
      if (var1 != 0) {
         if (var1 != 38) {
            if (var1 != 40) {
               if (var1 != 42) {
                  if (var1 != 44) {
                     if (var1 != 46) {
                        if (var1 != 48) {
                           if (var1 != 255) {
                              var2 = " - -";
                           } else {
                              var2 = this.mContext.getResources().getString(2131768712) + " 4";
                           }
                        } else {
                           var2 = this.mContext.getResources().getString(2131768712) + " 3";
                        }
                     } else {
                        var2 = this.mContext.getResources().getString(2131768712) + " 2";
                     }
                  } else {
                     var2 = this.mContext.getResources().getString(2131768712) + " 1";
                  }
               } else {
                  var2 = this.mContext.getResources().getString(2131769794) + " 1";
               }
            } else {
               var2 = this.mContext.getResources().getString(2131769794) + " 2";
            }
         } else {
            var2 = this.mContext.getResources().getString(2131769794) + " 3";
         }
      } else {
         var2 = this.mContext.getResources().getString(2131769794) + " 4";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[2];
      String var2 = "";
      if (1 <= var1 && var1 <= 254) {
         var2 = (double)((float)this.mCanBusInfoInt[2] * 0.5F) - 39.5 + "" + this.getTempUnitC(this.mContext);
      } else if (var1 == 0) {
         var2 = "--";
      }

      return var2;
   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.updateAirActivity(this.mContext, 1001);
      }

      new ArrayList();
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarSetData0x14() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769315);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769174);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 2, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (GeneralDoorData.isShowCarDoor && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
         this.updateDoorView(this.mContext);
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = "D";
            }
         } else {
            var2 = "R";
         }
      } else {
         var2 = "P";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var2 = this.mContext.getResources().getString(2131769504);
      } else {
         var2 = this.mContext.getResources().getString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 1, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x28() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 3, var2.append(var3[2] + var3[3] * 256).append("Km/h").toString()));
      StringBuilder var6 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 4, var6.append(var5[4] + var5[5] * 256).append("RPM").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(var4[2] + var4[3] * 256);
   }

   private void setRadarInfo() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5376, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11});
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      byte var10;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               var10 = 0;
            } else {
               var10 = 2;
            }
         } else {
            var10 = 3;
         }
      } else {
         var10 = 1;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, var10, 0, 0, 0, 0, 0});
      if (var4) {
         var2 = DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, var2);
      } else {
         var2 = DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, new byte[]{0});
      }

      CanbusMsgSender.sendMsg(var2);
      if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 36) {
            if (var3 != 48) {
               switch (var3) {
                  case 32:
                     this.realKeyControl();
                     break;
                  case 33:
                     if (this.isAirMsgReturn(var2)) {
                        return;
                     }

                     this.setAirData0x21();
                     break;
                  case 34:
                     this.setRadarInfo();
                     break;
                  default:
                     switch (var3) {
                        case 39:
                           this.setAirData0x27();
                           break;
                        case 40:
                           this.setDriveData0x28();
                           break;
                        case 41:
                           this.setTrackInfo();
                     }
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData0x24();
         }
      } else {
         this.setCarSetData0x14();
      }

   }

   public void musicDestroy() {
      super.musicDestroy();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -53, 2}, new byte[]{0}));
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var26 = new byte[]{22, -64, 8, 19, (byte)var3, var9, 0, 0, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(var26, var26));

      try {
         var26 = Util.exceptBOMHead(var21.getBytes("unicode"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -53, 2}, var26));
      } catch (UnsupportedEncodingException var25) {
         var25.printStackTrace();
      }

   }

   public void radioDestroy() {
      super.radioDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      int var7 = var2.hashCode();
      byte var8 = -1;
      switch (var7) {
         case 64901:
            if (var2.equals("AM1")) {
               var8 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var8 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var8 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var8 = 3;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var8 = 4;
            }
      }

      byte var6;
      switch (var8) {
         case 0:
         case 1:
            var6 = 16;
            break;
         case 2:
         default:
            var6 = 1;
            break;
         case 3:
            var6 = 2;
            break;
         case 4:
            var6 = 3;
      }

      this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, var6, this.freqLo, this.freqHi, (byte)var1, 0, 0});
   }

   public void videoDestroy() {
      super.videoDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = new byte[]{22, -64, 8, 19, (byte)var3, var9, 0, 0, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(var18, var18));
   }
}
