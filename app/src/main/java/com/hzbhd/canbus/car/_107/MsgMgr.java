package com.hzbhd.canbus.car._107;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgMgr extends AbstractMsgMgr {
   private final int VEHICLE_TYPE_DEFAULT = 0;
   private final int VEHICLE_TYPE_LADA_VESTA = 1;
   private final int VEHICLE_TYPE_RENAULT_FLUENCE = 2;
   private int VolTag = 0;
   private String callState;
   DecimalFormat df_2Integer = new DecimalFormat("00");
   private boolean isCalling = false;
   private boolean isMute = false;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private SparseArray mKeyMapArray;
   private UiMgr mUiMgr;
   private int nowCarDoor = 0;

   private boolean getCallState() {
      String var1 = ShareDataManager.getInstance().registerShareStringListener("bluetooth.CallState", new IShareStringListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onString(String var1) {
            MsgMgr var2 = this.this$0;
            var2.isCalling = var2.setCallState(var1);
         }
      });
      this.callState = var1;
      if (var1 != null) {
         this.isCalling = this.setCallState(var1);
      }

      return this.isCalling;
   }

   private String getDriveData(int var1, int var2) {
      var1 = var1 * 256 + var2;
      return var1 == 65535 ? "--" : String.format("%.1f", (float)var1 * 0.1F);
   }

   private String getDriveData(int var1, int var2, int var3) {
      var1 = var1 * 65536 + var2 * 256 + var3;
      return var1 == 16777215 ? "--" : String.format("%.1f", (float)var1 * 0.1F);
   }

   private String getFuelUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " L/100KM";
      } else if (var1 == 1) {
         var2 = " KM/L";
      } else if (var1 == 2) {
         var2 = " MPG（US）";
      } else if (var1 == 3) {
         var2 = " MGP（UK）";
      } else {
         var2 = "--";
      }

      return var2;
   }

   private KeyMap getKeyMap(int var1) {
      KeyMap var3 = (KeyMap)this.mKeyMapArray.get(var1);
      KeyMap var2 = var3;
      if (var3 == null) {
         var2 = (KeyMap)this.mKeyMapArray.get(0);
      }

      return var2;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initKeyMap() {
      SparseArray var1 = new SparseArray();
      this.mKeyMapArray = var1;
      var1.put(0, new KeyMap(this));
      this.mKeyMapArray.put(1, new KeyMap(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public int getKey2() {
            return 3;
         }

         public int getKey5() {
            return 466;
         }

         public int getKey6() {
            return 465;
         }
      });
      this.mKeyMapArray.put(2, new KeyMap(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public int getKey6() {
            return 464;
         }
      });
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[2];
      String var2 = "";
      if (1 <= var1 && var1 <= 254) {
         var2 = (float)this.mCanBusInfoInt[2] * 0.5F + "" + this.getTempUnitC(this.mContext);
      } else if (var1 == 0) {
         var2 = "--";
      }

      return var2;
   }

   private void selectWheelKey() {
      switch (this.getCurrentEachCanId()) {
         case 1:
            this.setSwcMode1();
            break;
         case 2:
            this.setSwcMode2();
            break;
         case 3:
            this.setSwcMode3();
            break;
         case 4:
            this.setSwcMode4();
            break;
         case 5:
            this.setSwcMode5();
            break;
         case 6:
            this.setSwcMode6();
            break;
         case 7:
            this.setSwcMode7();
            break;
         default:
            this.set0x20WheelKeyData(this.mContext);
      }

   }

   private void set0x20WheelKeyData(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 != 19) {
                        if (var2 != 20) {
                           if (var2 != 22) {
                              switch (var2) {
                                 case 6:
                                    this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey6());
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey1());
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey9());
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey2());
                              }
                           } else {
                              this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey5());
                           }
                        } else {
                           this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey8());
                        }
                     } else {
                        this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey7());
                     }
                  } else {
                     this.realKeyClick4(var1, this.getKeyMap(this.mDifferent).getKey11());
                  }
               } else {
                  this.realKeyClick4(var1, this.getKeyMap(this.mDifferent).getKey10());
               }
            } else {
               this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey4());
            }
         } else {
            this.realKeyLongClick1(var1, this.getKeyMap(this.mDifferent).getKey3());
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private void set0x33CarInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_107_car_language"), this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x94PanoramaInfo() {
      if (this.mCanBusInfoInt[2] == 1) {
         this.forceReverse(this.mContext, true);
         ArrayList var3 = new ArrayList();
         int[] var2 = this.mCanBusInfoInt;
         if (var2[3] == 0) {
            var3.add(new PanoramicBtnUpdateEntity(0, false));
            var3.add(new PanoramicBtnUpdateEntity(1, false));
            var3.add(new PanoramicBtnUpdateEntity(2, false));
            var3.add(new PanoramicBtnUpdateEntity(3, false));
         } else {
            int var1 = var2[4];
            if (var1 == 1) {
               var3.add(new PanoramicBtnUpdateEntity(0, true));
               var3.add(new PanoramicBtnUpdateEntity(1, false));
               var3.add(new PanoramicBtnUpdateEntity(2, false));
               var3.add(new PanoramicBtnUpdateEntity(3, false));
            } else if (var1 == 2) {
               var3.add(new PanoramicBtnUpdateEntity(0, false));
               var3.add(new PanoramicBtnUpdateEntity(1, true));
               var3.add(new PanoramicBtnUpdateEntity(2, false));
               var3.add(new PanoramicBtnUpdateEntity(3, false));
            } else if (var1 == 3) {
               var3.add(new PanoramicBtnUpdateEntity(0, false));
               var3.add(new PanoramicBtnUpdateEntity(1, false));
               var3.add(new PanoramicBtnUpdateEntity(2, true));
               var3.add(new PanoramicBtnUpdateEntity(3, false));
            } else if (var1 == 4) {
               var3.add(new PanoramicBtnUpdateEntity(0, false));
               var3.add(new PanoramicBtnUpdateEntity(1, false));
               var3.add(new PanoramicBtnUpdateEntity(2, false));
               var3.add(new PanoramicBtnUpdateEntity(3, true));
            }
         }

         GeneralParkData.dataList = var3;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         this.forceReverse(this.mContext, false);
      }
   }

   private void set0x95Info() {
      if (this.VolTag == 0) {
         this.VolTag = 1;
         this.realKeyClick(this.mContext, 7);
      }

      if (this.mCanBusInfoInt[2] == 1 && !this.isMute) {
         this.realKeyClick(this.mContext, 3);
      }

   }

   private void setAirData0x21() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private boolean setCallState(String var1) {
      boolean var2;
      try {
         JSONObject var3 = new JSONObject(var1);
         var2 = (Boolean)var3.get("isCall");
      } catch (JSONException var4) {
         MyLog.temporaryTracking(var4.toString());
         var4.printStackTrace();
         return false;
      }

      return var2;
   }

   private void setCarRangeInfo() {
      String var4 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
      String var1;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var1 = "MILE";
      } else {
         var1 = "KM";
      }

      String var2;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var2 = "Gal";
      } else {
         var2 = "L";
      }

      ArrayList var3 = new ArrayList();
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 4, var5.append(this.getDriveData(var6[3], var6[4])).append(var4).toString()));
      var5 = new StringBuilder();
      int[] var11 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 5, var5.append(this.getDriveData(var11[5], var11[6], var11[7])).append(var1).toString()));
      StringBuilder var12 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 6, var12.append(this.getDriveData(var7[8], var7[9])).append(var2).toString()));
      StringBuilder var8 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 7, var8.append(this.getDriveData(var10[10], var10[11])).append("KM/H").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      DecimalFormat var9 = this.df_2Integer;
      var10 = this.mCanBusInfoInt;
      this.updateSpeedInfo(Integer.parseInt(var9.format((double)((float)(var10[10] * 256 + var10[11]) / 10.0F))));
   }

   private void setCarSetData0x14() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769222);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769223);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 2, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      int var2 = this.nowCarDoor;
      int var1 = this.mCanBusInfoInt[2];
      if (var2 != var1) {
         this.nowCarDoor = var1;
         this.updateDoorView(this.mContext);
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var3 = "";
            } else {
               var3 = "D";
            }
         } else {
            var3 = "R";
         }
      } else {
         var3 = "P";
      }

      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 0, var3));
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         var3 = this.mContext.getResources().getString(2131769504);
      } else {
         var3 = this.mContext.getResources().getString(2131768042);
      }

      var4.add(new DriverUpdateEntity(0, 1, var3));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setParkingState() {
      ArrayList var3 = new ArrayList();
      int var1;
      Resources var2;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var2 = this.mContext.getResources();
         var1 = 2131768216;
      } else {
         var2 = this.mContext.getResources();
         var1 = 2131768215;
      }

      var3.add(new DriverUpdateEntity(0, 3, var2.getString(var1)));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRadarInfo() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSwcMode1() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    this.realKeyLongClick1(this.mContext, 3);
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(this.mContext, 2);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 50);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 46);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 45);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setSwcMode2() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    this.realKeyLongClick1(this.mContext, 3);
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(this.mContext, 62);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 49);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 46);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 45);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 45);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 46);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setSwcMode3() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    this.realKeyLongClick1(this.mContext, 3);
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(this.mContext, 188);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 49);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 46);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 45);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setSwcMode4() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    this.realKeyLongClick1(this.mContext, 3);
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(this.mContext, 188);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 49);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 46);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 45);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 45);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 46);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setSwcMode5() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    this.realKeyLongClick1(this.mContext, 14);
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(this.mContext, 3);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 15);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 46);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 45);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 45);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 46);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setSwcMode6() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    if (this.getCallState()) {
                                       this.realKeyLongClick1(this.mContext, 188);
                                    } else {
                                       this.realKeyLongClick1(this.mContext, 3);
                                    }
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    this.realKeyLongClick1(this.mContext, 14);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 49);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 45);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 46);
                     }
                  } else {
                     if (var2[3] == 0) {
                        return;
                     }

                     this.realKeyClick4(this.mContext, 46);
                  }
               } else {
                  if (var2[3] == 0) {
                     return;
                  }

                  this.realKeyClick4(this.mContext, 45);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 7);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setSwcMode7() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 19) {
                        if (var1 != 20) {
                           if (var1 != 22) {
                              switch (var1) {
                                 case 6:
                                    if (this.getCallState()) {
                                       this.realKeyLongClick1(this.mContext, 188);
                                    } else {
                                       this.realKeyLongClick1(this.mContext, 3);
                                    }
                                    break;
                                 case 7:
                                    this.realKeyLongClick1(this.mContext, 2);
                                    break;
                                 case 8:
                                    this.realKeyLongClick1(this.mContext, 187);
                                    break;
                                 case 9:
                                    if (!this.getCallState()) {
                                       this.realKeyLongClick1(this.mContext, 14);
                                    }
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 62);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 45);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 46);
                     }
                  } else {
                     if (var2[3] == 0) {
                        return;
                     }

                     this.realKeyClick4(this.mContext, 45);
                  }
               } else {
                  if (var2[3] == 0) {
                     return;
                  }

                  this.realKeyClick4(this.mContext, 46);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0);
      }

   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5888, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.initKeyMap();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 39) {
            if (var3 != 41) {
               if (var3 != 48) {
                  if (var3 != 51) {
                     if (var3 != 56) {
                        if (var3 != 36) {
                           if (var3 != 37) {
                              if (var3 != 148) {
                                 if (var3 != 149) {
                                    switch (var3) {
                                       case 32:
                                          this.selectWheelKey();
                                          break;
                                       case 33:
                                          if (this.isAirMsgRepeat(var2)) {
                                             return;
                                          }

                                          this.setAirData0x21();
                                          break;
                                       case 34:
                                          this.setRadarInfo();
                                    }
                                 } else {
                                    this.set0x95Info();
                                 }
                              } else {
                                 this.set0x94PanoramaInfo();
                              }
                           } else {
                              this.setParkingState();
                           }
                        } else {
                           if (this.isDoorMsgRepeat(var2)) {
                              return;
                           }

                           this.setDoorData0x24();
                        }
                     } else {
                        this.setCarRangeInfo();
                     }
                  } else {
                     this.set0x33CarInfo();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setTrackInfo();
            }
         } else {
            this.setAirData0x27();
         }
      } else {
         this.setCarSetData0x14();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
   }

   private interface IKeyMapInterface {
      int getKey1();

      int getKey10();

      int getKey11();

      int getKey2();

      int getKey3();

      int getKey4();

      int getKey5();

      int getKey6();

      int getKey7();

      int getKey8();

      int getKey9();
   }

   private class KeyMap implements IKeyMapInterface {
      final MsgMgr this$0;

      private KeyMap(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      KeyMap(MsgMgr var1, Object var2) {
         this(var1);
      }

      public int getKey1() {
         return 2;
      }

      public int getKey10() {
         return 45;
      }

      public int getKey11() {
         return 46;
      }

      public int getKey2() {
         return 188;
      }

      public int getKey3() {
         return 7;
      }

      public int getKey4() {
         return 8;
      }

      public int getKey5() {
         return 49;
      }

      public int getKey6() {
         return 3;
      }

      public int getKey7() {
         return 20;
      }

      public int getKey8() {
         return 21;
      }

      public int getKey9() {
         return 187;
      }
   }
}
