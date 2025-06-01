package com.hzbhd.canbus.car._339;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   public byte[] mCanBusInfoByte;
   public int[] mCanBusInfoInt;

   private void airConditionInfo(Context var1) {
      // $FF: Couldn't be decompiled
   }

   private void clickBand() {
      String var3 = this.getCurrentBand();
      var3.hashCode();
      int var2 = var3.hashCode();
      byte var1 = -1;
      switch (var2) {
         case 64901:
            if (var3.equals("AM1")) {
               var1 = 0;
            }
            break;
         case 64902:
            if (var3.equals("AM2")) {
               var1 = 1;
            }
            break;
         case 69706:
            if (var3.equals("FM1")) {
               var1 = 2;
            }
            break;
         case 69707:
            if (var3.equals("FM2")) {
               var1 = 3;
            }
            break;
         case 69708:
            if (var3.equals("FM3")) {
               var1 = 4;
            }
      }

      switch (var1) {
         case 0:
            this.changeBandAm2();
            break;
         case 1:
            this.changeBandFm1();
            break;
         case 2:
            this.changeBandFm2();
            break;
         case 3:
            this.changeBandFm3();
            break;
         case 4:
            this.changeBandAm1();
      }

   }

   private void generalInfo(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(var1);
   }

   private void panelKeyInfo(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 5) {
                     if (var2 != 7) {
                        if (var2 != 20) {
                           switch (var2) {
                              case 9:
                                 this.realKeyLongClick1(var1, 3, var3[3]);
                                 break;
                              case 10:
                                 this.realKeyLongClick1(var1, 33, var3[3]);
                                 break;
                              case 11:
                                 this.realKeyLongClick1(var1, 34, var3[3]);
                                 break;
                              case 12:
                                 this.realKeyLongClick1(var1, 35, var3[3]);
                                 break;
                              case 13:
                                 this.realKeyLongClick1(var1, 36, var3[3]);
                                 break;
                              case 14:
                                 this.realKeyLongClick1(var1, 37, var3[3]);
                                 break;
                              case 15:
                                 this.realKeyLongClick1(var1, 38, var3[3]);
                                 break;
                              default:
                                 switch (var2) {
                                    case 22:
                                       this.realKeyLongClick1(var1, 49, var3[3]);
                                       break;
                                    case 23:
                                       this.volumeKnob(var1, 7, var3[3]);
                                       break;
                                    case 24:
                                       this.volumeKnob(var1, 8, var3[3]);
                                       break;
                                    default:
                                       switch (var2) {
                                          case 32:
                                             this.realKeyLongClick1(var1, 128, var3[3]);
                                             break;
                                          case 33:
                                             this.realKeyLongClick1(var1, 2, var3[3]);
                                             break;
                                          case 34:
                                             this.realKeyLongClick1(var1, 30, var3[3]);
                                       }
                                 }
                           }
                        } else {
                           this.realKeyLongClick1(var1, 31, var3[3]);
                        }
                     } else {
                        this.clickBand();
                     }
                  } else {
                     this.realKeyLongClick1(var1, 4, var3[3]);
                  }
               } else {
                  this.realKeyLongClick1(var1, 20, var3[3]);
               }
            } else {
               this.realKeyLongClick1(var1, 21, var3[3]);
            }
         } else {
            this.realKeyLongClick1(var1, 134, var3[3]);
         }
      } else {
         this.realKeyLongClick1(var1, 0, var3[3]);
      }

   }

   private void radarDataInfo(Context var1) {
      byte var3 = 1;
      RadarInfoUtil.mMinIsClose = true;
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      byte var6;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 255) {
                  throw new IllegalStateException("Unexpected value: " + this.mCanBusInfoInt[2]);
               }

               var6 = 0;
            } else {
               var6 = 1;
            }
         } else {
            var6 = 2;
         }
      } else {
         var6 = 3;
      }

      int var4 = var5[3];
      if (var4 != 1) {
         if (var4 != 2) {
            if (var4 != 3) {
               if (var4 != 255) {
                  throw new IllegalStateException("Unexpected value: " + this.mCanBusInfoInt[3]);
               }

               var3 = 0;
            }
         } else {
            var3 = 2;
         }
      } else {
         var3 = 3;
      }

      RadarInfoUtil.setRearRadarLocationData(3, var6, 0, 0, var3);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void radarSwitchInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void realKeyInfo(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 5) {
                  if (var2 != 7) {
                     switch (var2) {
                        case 9:
                           this.realKeyLongClick2(var1, 14);
                           break;
                        case 10:
                           this.realKeyLongClick2(var1, 15);
                           break;
                        case 11:
                           this.realKeyLongClick2(var1, 45);
                           break;
                        case 12:
                           this.realKeyLongClick2(var1, 46);
                     }
                  } else {
                     this.realKeyLongClick2(var1, 2);
                  }
               } else {
                  this.realKeyLongClick2(var1, 3);
               }
            } else {
               this.realKeyLongClick2(var1, 8);
            }
         } else {
            this.realKeyLongClick2(var1, 7);
         }
      } else {
         this.realKeyLongClick2(var1, 0);
      }

   }

   private void reversingVideoInfo(Context var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(1, 0, this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void steeringWheelAngleInfo(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 550, 16);
      this.updateParkUi((Bundle)null, var1);
   }

   private void volumeKnob(Context var1, int var2, int var3) {
      for(int var4 = 0; var4 < var3; ++var4) {
         this.realKeyClick4(var1, var2);
      }

   }

   private void warningSoundState(Context var1) {
      ArrayList var3 = new ArrayList();
      byte var2;
      if (this.mCanBusInfoInt[2] == 0) {
         var2 = 0;
      } else {
         var2 = 1;
      }

      var3.add(new SettingUpdateEntity(2, 0, Integer.valueOf(var2)));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 2) {
         if (var3 != 3) {
            if (var3 != 7) {
               if (var3 != 9) {
                  if (var3 != 13) {
                     if (var3 != 40) {
                        if (var3 != 48) {
                           if (var3 != 33) {
                              if (var3 == 34) {
                                 this.radarDataInfo(var1);
                              }
                           } else {
                              this.realKeyInfo(var1);
                           }
                        } else {
                           this.steeringWheelAngleInfo(var1);
                        }
                     } else {
                        this.generalInfo(var1);
                     }
                  } else {
                     this.warningSoundState(var1);
                  }
               } else {
                  this.reversingVideoInfo(var1);
               }
            } else {
               this.radarSwitchInfo();
            }
         } else {
            this.airConditionInfo(var1);
         }
      } else {
         this.panelKeyInfo(var1);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }
}
