package com.hzbhd.canbus.car._188;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private byte freqHi;
   private byte freqLo;
   private int[] lastArrayAirCondition = new int[]{0, 0, 0, 0, 0, 0, 0};
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferentId;
   private MyPanoramicView mPanoramicView;
   private int mWheelKeyStatus;
   private int mWheelKeyWhat;
   private int textNumberFive;
   private int textNumberFour;
   private int textNumberOne;
   private int textNumberThree;
   private int textNumberTwo;

   private void airConditionInfo(Context var1) {
      // $FF: Couldn't be decompiled
   }

   private byte getAllBandTypeData(String var1) {
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
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 0;
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
         this.FreqInt = Integer.parseInt(var2);
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private boolean isOnlyChanged(int[] var1, int var2) {
      if (var2 < var1.length && var2 >= 0) {
         for(int var3 = 0; var3 < var2; ++var3) {
            if (var1[var3] != this.lastArrayAirCondition[var3]) {
               return false;
            }
         }

         ++var2;

         while(var2 < var1.length) {
            if (var1[var2] != this.lastArrayAirCondition[var2]) {
               return false;
            }

            ++var2;
         }

         return true;
      } else {
         return false;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private void realKeyControl0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 9) {
                        if (var1 != 10) {
                           if (var1 != 21) {
                              if (var1 != 22) {
                                 if (var1 != 80) {
                                    if (var1 != 81) {
                                       switch (var1) {
                                          case 7:
                                             this.wheelKeyClick(2);
                                             break;
                                          case 64:
                                             this.wheelKeyClick(141);
                                             break;
                                          case 66:
                                             this.wheelKeyClick(30);
                                             break;
                                          case 68:
                                             this.wheelKeyClick(182);
                                             break;
                                          case 74:
                                             this.wheelKeyClick(221);
                                             break;
                                          case 78:
                                             this.wheelKeyClick(222);
                                             break;
                                          case 96:
                                             this.wheelKeyClick(45);
                                             break;
                                          case 98:
                                             this.wheelKeyClick(48);
                                             break;
                                          case 100:
                                             this.wheelKeyClick(46);
                                             break;
                                          case 102:
                                             this.wheelKeyClick(47);
                                             break;
                                          case 135:
                                             this.wheelKeyClick(134);
                                             break;
                                          default:
                                             switch (var1) {
                                                case 32:
                                                   this.wheelKeyClick(183);
                                                   break;
                                                case 33:
                                                   this.wheelKeyClick(183);
                                                   break;
                                                case 34:
                                                   this.wheelKeyClick(128);
                                                   break;
                                                case 35:
                                                   this.wheelKeyClick(128);
                                                   break;
                                                case 36:
                                                   this.wheelKeyClick(76);
                                                   break;
                                                case 37:
                                                   this.wheelKeyClick(130);
                                                   break;
                                                case 38:
                                                   this.wheelKeyClick(77);
                                                   break;
                                                case 39:
                                                   this.wheelKeyClick(33);
                                                   break;
                                                case 40:
                                                   this.wheelKeyClick(34);
                                                   break;
                                                case 41:
                                                   this.wheelKeyClick(35);
                                                   break;
                                                case 42:
                                                   this.wheelKeyClick(36);
                                                   break;
                                                case 43:
                                                   this.wheelKeyClick(37);
                                                   break;
                                                case 44:
                                                   this.wheelKeyClick(38);
                                                   break;
                                                case 45:
                                                   this.sendKnob_1(7);
                                                   break;
                                                case 46:
                                                   this.sendKnob_1(8);
                                                   break;
                                                case 47:
                                                   this.sendKnob_2(47);
                                                   break;
                                                case 48:
                                                   this.sendKnob_2(48);
                                                   break;
                                                case 49:
                                                   this.wheelKeyClick(45);
                                                   break;
                                                case 50:
                                                   this.wheelKeyClick(46);
                                                   break;
                                                case 51:
                                                   this.wheelKeyClick(17);
                                                   break;
                                                default:
                                                   switch (var1) {
                                                      case 70:
                                                         this.wheelKeyClick(56);
                                                         break;
                                                      case 71:
                                                         this.wheelKeyClick(151);
                                                         break;
                                                      case 72:
                                                         this.wheelKeyClick(56);
                                                         break;
                                                      default:
                                                         switch (var1) {
                                                            case 83:
                                                               this.wheelKeyClick(143);
                                                               break;
                                                            case 84:
                                                               this.wheelKeyClick(141);
                                                               break;
                                                            case 85:
                                                               this.wheelKeyClick(27);
                                                               break;
                                                            default:
                                                               switch (var1) {
                                                                  case 112:
                                                                     this.wheelKeyClick(49);
                                                                     break;
                                                                  case 113:
                                                                     this.wheelKeyClick(50);
                                                                     break;
                                                                  case 114:
                                                                     this.wheelKeyClick(128);
                                                                     break;
                                                                  case 115:
                                                                     this.wheelKeyClick(52);
                                                                     break;
                                                                  case 116:
                                                                     this.sendKnob_1(7);
                                                                     break;
                                                                  case 117:
                                                                     this.sendKnob_1(8);
                                                               }
                                                         }
                                                   }
                                             }
                                       }
                                    } else {
                                       this.wheelKeyClick(31);
                                    }
                                 } else {
                                    this.wheelKeyClick(53);
                                 }
                              } else {
                                 this.wheelKeyClick(49);
                              }
                           } else {
                              this.wheelKeyClick(50);
                           }
                        } else {
                           this.wheelKeyClick(15);
                        }
                     } else {
                        this.wheelKeyClick(14);
                     }
                  } else {
                     this.wheelKeyClick(45);
                  }
               } else {
                  this.wheelKeyClick(46);
               }
            } else {
               this.wheelKeyClick(8);
            }
         } else {
            this.wheelKeyClick(7);
         }
      } else {
         this.wheelKeyClick(0);
      }

   }

   private void rptCmdFilter(Context var1, int[] var2, int[] var3) {
      if (!this.contentCompare(var2, var3)) {
         this.airConditionInfo(var1);
      }

      if (var2.length == var3.length) {
         this.transTo(var2, var3);
      }

   }

   private void sendKnob_1(int var1) {
      this.realKeyClick3_1(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   private void sendKnob_2(int var1) {
      this.realKeyClick3_2(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   private void sendPanoramicDispCommand(Context var1) {
      if (SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0) == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
      }

   }

   private void setCarSpeed0x6A() {
      int[] var1 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[3], var1[2]));
   }

   private void setDrivingAids0x95() {
      if (this.mDifferentId == 3) {
         ArrayList var4 = new ArrayList();
         boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         String var3 = "open";
         String var2;
         if (var1) {
            var2 = "open";
         } else {
            var2 = "close";
         }

         var4.add(new SettingUpdateEntity(0, 0, var2));
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            var2 = "open";
         } else {
            var2 = "close";
         }

         var4.add(new SettingUpdateEntity(0, 1, var2));
         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            var2 = var3;
         } else {
            var2 = "close";
         }

         var4.add(new SettingUpdateEntity(0, 2, var2));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setFrontRadar0x23() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setMediaSwitchCommand0x40() {
      try {
         switch (this.mCanBusInfoInt[2]) {
            case 0:
               this.enterNoSource();
               this.realKeyClick(52);
               break;
            case 1:
               this.changeBandAm1();
               break;
            case 2:
               this.changeBandFm1();
               break;
            case 3:
               this.changeBandFm2();
               break;
            case 4:
               this.realKeyClick(130);
               break;
            case 5:
               this.realKeyClick(59);
               break;
            case 6:
               this.realKeyClick(61);
               break;
            case 7:
               this.realKeyClick(140);
               break;
            case 8:
               this.realKeyClick(141);
               break;
            case 9:
               this.realKeyClick(129);
               break;
            case 10:
               this.realKeyClick(139);
               break;
            case 11:
               this.realKeyClick(52);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private void setPanelInfo0x60() {
      if (this.mCanBusInfoInt[2] == 49) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
         GeneralSettingData.dataList = var1;
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void setParkAssistance0x94() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      this.mPanoramicView.mPageNumber = this.mCanBusInfoInt[2];
      this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      this.mPanoramicView.mTvTipsStatus = this.mCanBusInfoInt[4];
      this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[5];
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mPanoramicView.updatePanoramicView(this.this$0.mContext);
         }
      });
   }

   private void setPhoneCommand0x50() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               this.realKeyClick(15);
            }
         } else {
            this.realKeyClick(15);
         }
      } else {
         this.realKeyClick(14);
      }

   }

   private void setRearRadar0x22() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 5448, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void wheelKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mDifferentId = this.getCurrentCanDifferentId();
      boolean var3 = false;
      RadarInfoUtil.mMinIsClose = false;
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      int var2 = this.mDifferentId;
      if (var2 == 1 || var2 == 2 || var2 == 4) {
         if (SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0) == 1) {
            var3 = true;
         }

         this.updateBubble(var1, var3);
      }

   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, -1, 1, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3 && var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1, 32});
      } else if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 32});
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 41) {
         if (var3 != 48) {
            if (var3 != 64) {
               if (var3 != 80) {
                  if (var3 != 96) {
                     if (var3 != 106) {
                        if (var3 != 148) {
                           if (var3 != 149) {
                              switch (var3) {
                                 case 32:
                                    this.realKeyControl0x20();
                                    break;
                                 case 33:
                                    this.rptCmdFilter(var1, var4, this.lastArrayAirCondition);
                                    break;
                                 case 34:
                                    this.setRearRadar0x22();
                                    break;
                                 case 35:
                                    this.setFrontRadar0x23();
                              }
                           } else {
                              this.setDrivingAids0x95();
                           }
                        } else {
                           this.setParkAssistance0x94();
                        }
                     } else {
                        this.setCarSpeed0x6A();
                     }
                  } else {
                     this.setPanelInfo0x60();
                  }
               } else {
                  this.setPhoneCommand0x50();
               }
            } else {
               this.setMediaSwitchCommand0x40();
            }
         } else {
            this.setVersionInfo0x30();
         }
      } else {
         this.setTrackData0x29();
      }

   }

   public boolean contentCompare(int[] var1, int[] var2) {
      return Arrays.equals(var1, var2);
   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 2 && var2 != 49) {
         return false;
      } else {
         this.sendPanoramicDispCommand(var1);
         return true;
      }
   }

   public boolean customShortClick(Context var1, int var2) {
      if (!CommUtil.isMiscReverse() || var2 != 2 && var2 != 49) {
         return false;
      } else {
         MyLog.temporaryTracking("切换视频");
         this.sendPanoramicDispCommand(var1);
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var6, (byte)var8, (byte)var10});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var10 == 240) {
         this.sendDiscEjectMsg(this.mContext);
      } else if (var10 == 32) {
         var10 = this.getHour(var2);
         var3 = this.getMinute(var2);
         var2 = this.getSecond(var2);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         var1 = (byte)var4;
         byte var17 = (byte)var6;
         byte var15 = (byte)var10;
         byte var16 = (byte)var3;
         byte var14 = (byte)var2;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, var1, var17, var15, var16, var14});
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var2 = (byte)(var4 & 255);
         var1 = (byte)(var4 >> 8 & 255);
         var5 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, var2, var1, var5, var9, var6, var7});
      }
   }

   public void onKeyEvent(int var1, int var2, int var3, Bundle var4) {
      if (var1 == 182) {
         this.sendPanoramicDispCommand(this.mContext);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var10 = this.getAllBandTypeData(var2);
      this.getFreqByteHiLo(var2, var3);
      byte var6 = (byte)var10;
      byte var9 = this.freqLo;
      byte var8 = this.freqHi;
      byte var7 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var6, var9, var8, var7, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public int[] transTo(int[] var1, int[] var2) {
      System.arraycopy(var1, 0, var2, 0, var2.length);
      return var2;
   }

   protected void updateBubble(Context var1, boolean var2) {
      GeneralBubbleData.isShowBubble = var2;
      this.updateBubble(var1);
   }

   public void updateSettingActivity(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettingActivity(int var1, int var2, Object var3, boolean var4) {
      ArrayList var5 = new ArrayList();
      var5.add((new SettingUpdateEntity(var1, var2, var3)).setEnable(var4));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         var1 = (byte)(var4 & 255);
         var5 = (byte)(var4 >> 8 & 255);
         var2 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, var1, var5, var2, var9, var6, var7});
      }
   }
}
