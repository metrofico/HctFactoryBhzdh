package com.hzbhd.canbus.car._299;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._299.MessageSender;
import com.hzbhd.canbus.car_cus._299.RadarView;
import com.hzbhd.canbus.car_cus._299.air.AirPopupView;
import com.hzbhd.canbus.car_cus._299.instrument.InstrumentActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private static final String SHARE_299_SMARTPOWER_DATA3 = "_299_smart_data3";
   private static final String SHARE_299_SMARTPOWER_DATA4 = "_299_smart_data4";
   private static final String SHARE_299_SMARTPOWER_DATA5 = "_299_smart_data5";
   private static final String SHARE_299_SMARTPOWER_DATA6 = "_299_smart_data6";
   private static final String SHARE_299_SMARTPOWER_MODE = "_299_smart_mode";
   private int data3;
   private int data4;
   private int data5;
   private int data6;
   private int data7;
   private int[] drivingMode = new int[]{2131760622, 2131760623, 2131760612, 2131760617, 2131760613};
   private AirPopupView mAirPopupView;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private CusPanoramicView mPanoramicView;
   private RadarView mRadarView;
   private int mode;
   private int mode_int;
   private int version;

   private void canBusInfo0x55(byte[] var1) {
      int var2 = this.mCanBusInfoInt[1];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 4) {
               if (var2 != 5) {
                  if (var2 != 33) {
                     if (var2 != 34) {
                        if (var2 != 36) {
                           if (var2 != 38) {
                              if (var2 == 70) {
                                 this.setCarInfo0x46();
                              }
                           } else {
                              this.setSpeedInfo0x26();
                           }
                        } else {
                           this.setTrackInfo0x24();
                        }
                     } else {
                        if (this.isDoorMsgRepeat(var1)) {
                           return;
                        }

                        this.setDoorData0x22();
                     }
                  } else {
                     if (this.isAirMsgRepeat(var1)) {
                        return;
                     }

                     this.setAirInfo0x21();
                  }
               } else {
                  this.setFrontRadarInfo0x04();
               }
            } else {
               this.setRearRadarInfo0x05();
            }
         } else {
            this.setKey0x02();
         }
      } else {
         this.setKey0x01();
      }

   }

   private String getBandUnit(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
         case 1:
            return "KHz";
         case 2:
         case 3:
         case 4:
            return "MHz";
         default:
            return "";
      }
   }

   private int getModeInt(int var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var1 == 3 || var1 == 4) {
         var2 = var3[3];
      }

      byte var4;
      if (var1 != 3 && var1 != 0) {
         var4 = 0;
      } else {
         var4 = 4;
      }

      return DataHandleUtils.getIntFromByteWithBit(var2, var4, 4);
   }

   private CusPanoramicView getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (CusPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      return this.mPanoramicView;
   }

   // $FF: synthetic method
   static void lambda$initCommand$0(Context var0) {
      try {
         GeneralDzSmartData.mode = SharePreUtil.getIntValue(var0, "_299_smart_mode", 1);
         GeneralDzSmartData.data3 = SharePreUtil.getIntValue(var0, "_299_smart_data3", 0);
         GeneralDzSmartData.data4 = SharePreUtil.getIntValue(var0, "_299_smart_data4", 0);
         GeneralDzSmartData.data5 = SharePreUtil.getIntValue(var0, "_299_smart_data5", 0);
         GeneralDzSmartData.data6 = SharePreUtil.getIntValue(var0, "_299_smart_data6", 0);
         MessageSender.sendDzMsg(GeneralDzSmartData.mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      } catch (Exception var1) {
         MessageSender.sendDzMsg(1, 0, 0, 0, 0);
         var1.printStackTrace();
      }

   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (var1 >= 30 && var1 <= 64) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void sendSourceMsg1(String var1) {
      byte[] var2 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, 112, 3}, var2), 67));
   }

   private void sendSourceMsg2(String var1) {
      byte[] var2 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, 113, 3}, var2), 67));
   }

   private void sendSourceMsg3(String var1) {
      byte[] var2 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, 114, 3}, var2), 67));
   }

   private void setAirInfo0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.rest = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
         this.updateAirActivity(this.mContext, 1001);
      }

      this.updateAirPopupView(this.mContext);
   }

   private void setCarInfo0x46() {
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.updateInstrumentActivity();
   }

   private void setDoorData0x22() {
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadarInfo0x04() {
      GeneralDzData.radar_front_l = this.mCanBusInfoInt[2];
      GeneralDzData.radar_front_ml = this.mCanBusInfoInt[3];
      GeneralDzData.radar_front_mr = this.mCanBusInfoInt[4];
      GeneralDzData.radar_front_r = this.mCanBusInfoInt[5];
      this.updateRadar();
   }

   private void setKey0x01() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 80) {
            if (var1 != 81) {
               switch (var1) {
                  case 18:
                     this.realKeyLongClick2(this.mContext, 48);
                     break;
                  case 19:
                     this.realKeyLongClick2(this.mContext, 47);
                     break;
                  case 20:
                     this.realKeyLongClick2(this.mContext, 7);
                     break;
                  case 21:
                     this.realKeyLongClick2(this.mContext, 8);
                     break;
                  case 22:
                     this.realKeyLongClick2(this.mContext, 3);
                     break;
                  case 23:
                     this.realKeyLongClick2(this.mContext, 187);
               }
            } else {
               this.realKeyLongClick2(this.mContext, 188);
            }
         } else {
            this.realKeyLongClick2(this.mContext, 14);
         }
      } else {
         this.realKeyLongClick2(this.mContext, 0);
      }

   }

   private void setKey0x02() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         switch (var1) {
            case 11:
               this.realKeyClick(50);
               break;
            case 13:
               this.realKeyClick(45);
               break;
            case 14:
               this.realKeyClick(46);
               break;
            default:
               switch (var1) {
                  case 16:
                     this.realKeyClick(76);
                     return;
                  case 17:
                     this.realKeyClick(128);
                     return;
                  case 18:
                     break;
                  case 19:
                     this.realKeyClick(21);
                     return;
                  case 20:
                     this.realKeyClick(20);
                     return;
                  default:
                     switch (var1) {
                        case 26:
                           this.realKeyClick(7);
                           return;
                        case 27:
                           this.realKeyClick(8);
                           return;
                        case 28:
                           this.realKeyClick(59);
                           return;
                        case 29:
                           this.realKeyClick(151);
                           return;
                        case 30:
                           this.realKeyClick(14);
                           return;
                        case 31:
                           this.realKeyClick(182);
                           return;
                        case 32:
                           this.realKeyClick(58);
                           return;
                        case 33:
                           this.realKeyClick(47);
                           return;
                        case 34:
                           this.realKeyClick(48);
                           return;
                        case 35:
                           this.realKeyClick(1);
                           return;
                        case 36:
                           this.realKeyClick(31);
                           return;
                        default:
                           switch (var1) {
                              case 48:
                                 this.realKeyClick(32);
                                 return;
                              case 49:
                                 this.realKeyClick(33);
                                 return;
                              case 50:
                                 this.realKeyClick(34);
                                 return;
                              case 51:
                                 this.realKeyClick(35);
                                 return;
                              case 52:
                                 this.realKeyClick(36);
                                 return;
                              case 53:
                                 this.realKeyClick(37);
                                 return;
                              case 54:
                                 this.realKeyClick(38);
                                 return;
                              case 55:
                                 this.realKeyClick(39);
                                 return;
                              case 56:
                                 this.realKeyClick(40);
                                 return;
                              case 57:
                                 this.realKeyClick(41);
                                 return;
                              default:
                                 switch (var1) {
                                    case 60:
                                       this.realKeyClick(15);
                                       return;
                                    case 61:
                                       this.realKeyClick(14);
                                       return;
                                    case 62:
                                       this.realKeyClick(43);
                                       return;
                                    default:
                                       return;
                                 }
                           }
                     }
               }
            case 12:
               this.realKeyClick(49);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setRearRadarInfo0x05() {
      GeneralDzData.radar_rear_l = this.mCanBusInfoInt[2];
      GeneralDzData.radar_rear_ml = this.mCanBusInfoInt[3];
      GeneralDzData.radar_rear_mr = this.mCanBusInfoInt[4];
      GeneralDzData.radar_rear_r = this.mCanBusInfoInt[5];
      this.updateRadar();
   }

   private void setSpeedInfo0x26() {
      int[] var5 = this.mCanBusInfoInt;
      int var1 = var5[2];
      int var3 = var5[3];
      var5 = this.mCanBusInfoInt;
      int var4 = var5[4];
      int var2 = var5[5];
      String var8 = this.mCanBusInfoInt[6] / 2 - 40 + this.getTempUnitC(this.mContext);
      String var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1) + "";
      String var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1) + "";
      String var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1) + "";
      String var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4) + "";
      ArrayList var11 = new ArrayList();
      var11.add(new DriverUpdateEntity(0, 0, String.valueOf(var1 * 256 + var3)));
      var11.add(new DriverUpdateEntity(0, 1, String.valueOf(var4 * 256 + var2)));
      var11.add(new DriverUpdateEntity(0, 2, var8));
      var11.add(new DriverUpdateEntity(0, 3, var6));
      var11.add(new DriverUpdateEntity(0, 4, var7));
      var11.add(new DriverUpdateEntity(0, 5, var10));
      var11.add(new DriverUpdateEntity(0, 6, var9));
      this.updateGeneralDriveData(var11);
      this.updateInstrumentActivity();
   }

   private void setTrackInfo0x24() {
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[2], (byte)0, 0, 50, 8);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void updateInstrumentActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof InstrumentActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateRadar() {
      this.updateRadarView(this.mContext);
      this.getMyPanoramicView().refreshRadarUi();
   }

   private void updateSystemUIDrivingPattern(int var1) {
      if (var1 >= 0 && var1 <= 4) {
         Intent var2 = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
         var2.putExtra("_283_car_mode", String.valueOf(var1));
         this.mContext.sendBroadcast(var2);
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendSourceMsg1("AUX");
      this.sendSourceMsg2(" ");
      this.sendSourceMsg3(" ");
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      this.sendSourceMsg1(var1);
      this.sendSourceMsg2(var2);
      this.sendSourceMsg3(var3);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, 122, 3}, 67));
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, 123, 3}, 67));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      String var5 = new String(var1);
      var1 = new byte[0];

      byte[] var8;
      label23: {
         try {
            var8 = var5.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            break label23;
         }

         var1 = var8;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, 122, 3}, var1), 67));
      var5 = var4 / 60 + ":" + var4 % 60;
      var1 = new byte[0];

      label18: {
         try {
            var8 = var5.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
            break label18;
         }

         var1 = var8;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, 123, 3}, var1), 67));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      this.mContext = var1;
      if (var3[0] == 85) {
         this.canBusInfo0x55(var2);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      DataHandleUtils.setIntByteWithBit(0, 7, var11);
      DataHandleUtils.setIntByteWithBit(0, 6, var10 ^ true);
      CanbusMsgSender.sendMsg(new byte[]{22, 127, (byte)var5, (byte)var6, (byte)0, (byte)var2, (byte)var3, (byte)var4});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.sendSourceMsg1(var11);
      this.sendSourceMsg2(var13);
      this.sendSourceMsg3(var12);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendSourceMsg1("DTV");
      this.sendSourceMsg2(" ");
      this.sendSourceMsg3(" ");
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      MessageSender.sendMsg(new byte[]{22, -127, 1});
      (new Thread(new MsgMgr$$ExternalSyntheticLambda2(var1))).start();
   }

   // $FF: synthetic method
   void lambda$updateAirPopupView$1$com_hzbhd_canbus_car__299_MsgMgr() {
      this.mAirPopupView.refreshUi();
   }

   // $FF: synthetic method
   void lambda$updateRadarView$2$com_hzbhd_canbus_car__299_MsgMgr() {
      this.mRadarView.refreshUiOut();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var28 = new byte[0];

      byte[] var29;
      label33: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var21.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var27) {
            var27.printStackTrace();
            break label33;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 112, 3}, var28), 67);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label28: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var23.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var26) {
            var26.printStackTrace();
            break label28;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 113, 3}, var28), 67);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label23: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var22.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var25) {
            var25.printStackTrace();
            break label23;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 114, 3}, var28), 67);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.sendSourceMsg1(this.getBandUnit(var2));
      this.sendSourceMsg2(this.getBandUnit(var3));
      this.sendSourceMsg3(" ");
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendSourceMsg1("OFF");
      this.sendSourceMsg2(" ");
      this.sendSourceMsg3(" ");
   }

   protected void updateAirPopupView(Context var1) {
      if (this.mAirPopupView == null) {
         this.mAirPopupView = new AirPopupView(var1);
      }

      this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this));
   }

   protected void updateRadarView(Context var1) {
      if (this.mRadarView == null) {
         this.mRadarView = new RadarView(var1);
      }

      this.runOnUi(new MsgMgr$$ExternalSyntheticLambda1(this));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = (var6 + ":" + var7 + "   ").getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, 112, 3}, var18), 67);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      var18 = ((var9 & 255) * 256 + (var3 & 255) + "/" + (var4 & 255)).getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, 113, 3}, var18), 67);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      this.sendSourceMsg3(" ");
   }
}
