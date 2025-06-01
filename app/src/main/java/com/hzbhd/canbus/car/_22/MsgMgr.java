package com.hzbhd.canbus.car._22;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseIntArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_22_LANGUAGE = "share_22_language";
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private int mEachId;
   private int[] mFrontRadarData;
   private boolean mFrontStatus;
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private boolean mIsMute;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private MyPanoramicView mPanoramicView;
   private int[] mRearRadarData;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private SparseIntArray mTipsArray;
   private int mWheelKeyStatus;

   private void getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

   }

   private void initSparseArray() {
      SparseIntArray var1 = new SparseIntArray(39);
      this.mTipsArray = var1;
      var1.put(0, 2131769421);
      this.mTipsArray.append(1, 2131769356);
      this.mTipsArray.append(2, 2131769364);
      this.mTipsArray.append(3, 2131769382);
      this.mTipsArray.append(4, 2131769375);
      this.mTipsArray.append(5, 2131769374);
      this.mTipsArray.append(6, 2131769377);
      this.mTipsArray.append(7, 2131769365);
      this.mTipsArray.append(8, 2131769346);
      this.mTipsArray.append(9, 2131769361);
      this.mTipsArray.append(10, 2131769351);
      this.mTipsArray.append(11, 2131769371);
      this.mTipsArray.append(12, 2131769353);
      this.mTipsArray.append(13, 2131769370);
      this.mTipsArray.append(14, 2131769362);
      this.mTipsArray.append(18, 2131769350);
      this.mTipsArray.append(19, 2131769368);
      this.mTipsArray.append(20, 2131769384);
      this.mTipsArray.append(21, 2131769347);
      this.mTipsArray.append(22, 2131769373);
      this.mTipsArray.append(23, 2131769363);
      this.mTipsArray.append(24, 2131769360);
      this.mTipsArray.append(25, 2131769376);
      this.mTipsArray.append(26, 2131769380);
      this.mTipsArray.append(27, 2131769383);
      this.mTipsArray.append(28, 2131769355);
      this.mTipsArray.append(29, 2131769381);
      this.mTipsArray.append(34, 2131769357);
      this.mTipsArray.append(35, 2131769358);
      this.mTipsArray.append(36, 2131769359);
      this.mTipsArray.append(37, 2131769366);
      this.mTipsArray.append(39, 2131769369);
      this.mTipsArray.append(40, 2131769379);
      this.mTipsArray.append(66, 2131769349);
      this.mTipsArray.append(67, 2131769372);
      this.mTipsArray.append(69, 2131769367);
      this.mTipsArray.append(76, 2131769378);
      this.mTipsArray.append(77, 2131769354);
      this.mTipsArray.append(78, 2131769352);
      this.mTipsArray.append(79, 2131769348);
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private String resolveAirTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private int resolveSpeedData(int var1, int var2) {
      return var2 * 256 + var1;
   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setBaseInfo0x28() {
      Log.w("ljq", "setBaseInfo0x28: " + Arrays.toString(this.mCanBusInfoInt));

      Exception var10000;
      label66: {
         boolean var10001;
         try {
            boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mLeftFrontRec = var1;
            GeneralDoorData.isRightFrontDoorOpen = var1;
            var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            this.mRightFrontRec = var1;
            GeneralDoorData.isLeftFrontDoorOpen = var1;
            var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            this.mLeftRearRec = var1;
            GeneralDoorData.isRightRearDoorOpen = var1;
            var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mRightRearRec = var1;
            GeneralDoorData.isLeftRearDoorOpen = var1;
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            if (this.isDoorDataChange()) {
               this.updateDoorView(this.mContext);
            }
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label66;
         }

         StringBuilder var2;
         StringBuilder var3;
         String var9;
         label57: {
            label56: {
               try {
                  var2 = new StringBuilder();
                  var3 = var2.append("setBaseInfo0x28: ");
                  if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
                     break label56;
                  }
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label66;
               }

               var9 = "非倒挡";
               break label57;
            }

            var9 = "倒挡";
         }

         label49: {
            label48: {
               try {
                  Log.w("ljq", var3.append(var9).toString());
                  var2 = new StringBuilder();
                  var3 = var2.append("setBaseInfo0x28: ");
                  if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
                     break label48;
                  }
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label66;
               }

               var9 = "P挡";
               break label49;
            }

            var9 = "非P挡";
         }

         label41: {
            label40: {
               try {
                  Log.w("ljq", var3.append(var9).toString());
                  var2 = new StringBuilder();
                  var3 = var2.append("setBaseInfo0x28: ");
                  if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
                     break label40;
                  }
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break label66;
               }

               var9 = "小灯关";
               break label41;
            }

            var9 = "小灯开";
         }

         try {
            Log.w("ljq", var3.append(var9).toString());
            return;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var10 = var10000;
      var10.printStackTrace();
   }

   private void setFrontRadarData0x23() {
      if (this.isFrontRadarDataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setGlonassData0x95() {
      boolean var1;
      if (this.getVolume() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (this.mCanBusInfoInt[2] == 1) {
         if (!var1) {
            this.realKeyClick4(3);
         }
      } else if (var1) {
         this.realKeyClick4(3);
      }

   }

   private void setPanoramicUiData0x94() {
      this.getMyPanoramicView();
      int var2 = this.mCanBusInfoInt[2];
      int var1 = var2;
      if (var2 == 255) {
         var1 = 6;
      }

      this.mPanoramicView.mPageNumber = var1;
      this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ^ true;
      this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) ^ true;
      this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]) ^ true;
      this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) ^ true;
      this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ^ true;
      this.mPanoramicView.mTvTips = this.mContext.getString(this.mTipsArray.get(this.mCanBusInfoInt[4]));
      this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[5];
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mPanoramicView.updatePanoramicView();
         }
      });
   }

   private void setRearRadarData0x22() {
      if (this.isRearRadarDataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSetupStatusFeedback0xD0() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      StringBuilder var3;
      if (var1 != 1) {
         if (var1 == 2) {
            var3 = (new StringBuilder()).append("setSetupStatusFeedback0xD0: DISP 按键");
            if (this.mCanBusInfoInt[3] == 0) {
               var2 = "释放";
            } else {
               var2 = "按下";
            }

            Log.w("ljq", var3.append(var2).toString());
         }
      } else {
         var3 = (new StringBuilder()).append("setSetupStatusFeedback0xD0: 时钟设置 ");
         if (this.mCanBusInfoInt[3] == 0) {
            var2 = "成功";
         } else {
            var2 = "失败";
         }

         Log.w("ljq", var3.append(var2).toString());
      }

   }

   private void setSpeedData0x50() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var2.append(this.resolveSpeedData(var3[2], var3[3])).append(" Km/h").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var2.append(this.resolveSpeedData(var3[4], var3[5])).append(" RPM").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.resolveSpeedData(var4[2], var4[3]));
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVehicleTypeFeedback0xCB() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 == 1) {
            Log.w("ljq", "setVehicleTypeFeedback: 15途乐低配");
         }
      } else {
         Log.w("ljq", "setVehicleTypeFeedback: 15款奇骏自动空调");
      }

   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelCommand0x2F() {
      try {
         switch (this.mCanBusInfoInt[2]) {
            case 1:
               this.realKeyClick4(77);
               break;
            case 2:
               this.changeBandFm1();
               break;
            case 3:
               this.changeBandFm2();
               break;
            case 4:
               this.realKeyClick4(130);
               break;
            case 5:
               this.realKeyClick4(59);
               break;
            case 6:
               this.realKeyClick4(61);
               break;
            case 7:
               this.realKeyClick4(140);
               break;
            case 8:
               this.realKeyClick4(141);
               break;
            case 9:
               this.realKeyClick4(76);
               break;
            case 10:
               this.realKeyClick4(139);
               break;
            case 11:
               this.realKeyClick4(33);
               break;
            case 12:
               this.realKeyClick4(34);
               break;
            case 13:
               this.realKeyClick4(35);
               break;
            case 14:
               this.realKeyClick4(142);
               break;
            case 15:
               this.realKeyClick4(36);
               break;
            case 16:
               this.startMainActivity(this.mContext);
               break;
            case 17:
               this.realKeyClick4(14);
               break;
            case 18:
               this.realKeyClick4(15);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 135) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 21) {
                                    if (var1 == 22) {
                                       this.realKeyClick(49);
                                    }
                                 } else {
                                    this.realKeyClick(50);
                                 }
                              } else {
                                 this.realKeyClick(15);
                              }
                           } else {
                              this.realKeyClick(14);
                           }
                        } else {
                           this.realKeyClick(134);
                        }
                     } else {
                        this.realKeyClick(2);
                     }
                  } else {
                     this.realKeyClick(46);
                  }
               } else {
                  this.realKeyClick(45);
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

   private void turnCamera() {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var17 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var17;
      int var3 = var17[1];
      Exception var10000;
      boolean var10001;
      if (var3 != 40) {
         if (var3 != 41) {
            label102: {
               if (var3 != 47) {
                  if (var3 != 48) {
                     if (var3 != 80) {
                        if (var3 != 203) {
                           if (var3 != 208) {
                              if (var3 != 148) {
                                 if (var3 != 149) {
                                    switch (var3) {
                                       case 32:
                                          try {
                                             this.setWheelKey0x20();
                                             return;
                                          } catch (Exception var7) {
                                             var10000 = var7;
                                             var10001 = false;
                                             break label102;
                                          }
                                       case 33:
                                          try {
                                             this.setAirData0x21();
                                             return;
                                          } catch (Exception var6) {
                                             var10000 = var6;
                                             var10001 = false;
                                             break label102;
                                          }
                                       case 34:
                                          try {
                                             this.setRearRadarData0x22();
                                             return;
                                          } catch (Exception var5) {
                                             var10000 = var5;
                                             var10001 = false;
                                             break label102;
                                          }
                                       case 35:
                                          try {
                                             this.setFrontRadarData0x23();
                                             return;
                                          } catch (Exception var4) {
                                             var10000 = var4;
                                             var10001 = false;
                                             break label102;
                                          }
                                       default:
                                          return;
                                    }
                                 } else {
                                    try {
                                       this.setGlonassData0x95();
                                       return;
                                    } catch (Exception var8) {
                                       var10000 = var8;
                                       var10001 = false;
                                       break label102;
                                    }
                                 }
                              } else {
                                 try {
                                    this.setPanoramicUiData0x94();
                                    return;
                                 } catch (Exception var9) {
                                    var10000 = var9;
                                    var10001 = false;
                                    break label102;
                                 }
                              }
                           } else {
                              try {
                                 this.setSetupStatusFeedback0xD0();
                                 return;
                              } catch (Exception var10) {
                                 var10000 = var10;
                                 var10001 = false;
                                 break label102;
                              }
                           }
                        } else {
                           try {
                              this.setVehicleTypeFeedback0xCB();
                              return;
                           } catch (Exception var11) {
                              var10000 = var11;
                              var10001 = false;
                              break label102;
                           }
                        }
                     } else {
                        try {
                           this.setSpeedData0x50();
                           return;
                        } catch (Exception var12) {
                           var10000 = var12;
                           var10001 = false;
                           break label102;
                        }
                     }
                  }
               } else {
                  try {
                     this.setWheelCommand0x2F();
                  } catch (Exception var14) {
                     var10000 = var14;
                     var10001 = false;
                     break label102;
                  }
               }

               try {
                  this.setVersionInfo0x30();
                  return;
               } catch (Exception var13) {
                  var10000 = var13;
                  var10001 = false;
               }
            }
         } else {
            try {
               this.setTrackData0x29();
               return;
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
            }
         }
      } else {
         try {
            this.setBaseInfo0x28();
            return;
         } catch (Exception var16) {
            var10000 = var16;
            var10001 = false;
         }
      }

      Exception var18 = var10000;
      Log.e("CanBusError", var18.toString());
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.mIsMute = var2;
   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 2 && var2 != 49) {
         return false;
      } else {
         this.turnCamera();
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var1 = var5;
      if (!var10) {
         var1 = var5;
         if (var5 == 0) {
            var1 = 12;
         }
      }

      var2 = DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var11), 6, var10 ^ true);
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var1, (byte)var6, (byte)var2});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      int var2 = this.mEachId;
      if (var2 != 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)(var2 - 1)});
      }

      this.initSparseArray();
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
   }

   public void onKeyEvent(int var1, int var2, int var3, Bundle var4) {
      if (var1 == 182) {
         this.turnCamera();
      }

   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var5 = new ArrayList();
      var5.add(new SettingUpdateEntity(var1, var2, var3));
      boolean var4 = true;
      if (var1 == 1 && var2 == 0) {
         SettingUpdateEntity var6 = new SettingUpdateEntity(1, 1, (Object)null);
         if (var3 != 1) {
            var4 = false;
         }

         var5.add(var6.setEnable(var4));
      }

      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }
}
