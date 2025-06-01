package com.hzbhd.canbus.car._274;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_274_LEFT_CAMERA_INPUT = "share_274_left_camera_input";
   static final String SHARE_274_RIGHT_CAMERA_INPUT = "share_274_right_camera_input";
   private static boolean is0xE8First;
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private final String _274_IS_BACK_OPEN = "_274_is_back_open";
   private final String _274_IS_LEFT_FRONT_DOOR_OPEN = "_274_is_left_front_door_open";
   private final String _274_IS_LEFT_REAR_DOOR_OPEN = "_274_is_left_rear_door_open";
   private final String _274_IS_RIGHT_FRONT_DOOR_OPEN = "_274_is_right_front_door_open";
   private final String _274_IS_RIGHT_REAR_DOOR_OPEN = "_274_is_right_rear_door_open";
   private byte[] mCanBus0xE8InfoCopy;
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private CameraHelper mLeftCameraHelper;
   private int mLeftCameraStatus;
   private CameraHelper mRightCameraHelper;
   private int mRightCameraStatus;

   private void driveData0x1e() {
      ArrayList var3 = new ArrayList();
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 0, var4.append(var5[5] * 256 * 256 + var5[6] * 256 + var5[7]).append("Km").toString()));
      int[] var6 = this.mCanBusInfoInt;
      int var2 = var6[8];
      int var1 = var6[9];
      var3.add(new DriverUpdateEntity(0, 1, (var2 * 256 + var1) % 10000 + "Km"));
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 2, var4.append(var5[10] * 256 + var5[11]).append("Km").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private ComponentName getCameraInput(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? null : Constant.FCameraActivity;
      } else {
         return Constant.AuxInActivity;
      }
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private void initFrontCamera(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "share_274_left_camera_input", 0);
      int var3 = SharePreUtil.getIntValue(var1, "share_274_right_camera_input", 0);
      CameraHelper var4 = new CameraHelper(this, var1);
      this.mLeftCameraHelper = var4;
      var4.setInput(this.getCameraInput(var2));
      CameraHelper var5 = new CameraHelper(this, var1);
      this.mRightCameraHelper = var5;
      var5.setInput(this.getCameraInput(var3));
   }

   private boolean is0xE8MsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBus0xE8InfoCopy)) {
         return true;
      } else {
         this.mCanBus0xE8InfoCopy = Arrays.copyOf(var1, var1.length);
         if (is0xE8First) {
            is0xE8First = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "_274_is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_274_is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_274_is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_274_is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "_274_is_back_open", false) != GeneralDoorData.isBackOpen;
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 11) {
                  this.realKeyClick1(2);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[4], var3[5]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void senMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac = var2;
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[6];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      if (var1 != 3) {
         if (var1 != 12) {
            if (var1 != 5) {
               if (var1 == 6) {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarSetData() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, this.getIntByBoolean(DataHandleUtils.getBoolBit(0, this.mCanBusInfoInt[9]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorData() {
      GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      if (GeneralDoorData.isShowCarDoor && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "_274_is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_274_is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_274_is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_274_is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_274_is_back_open", GeneralDoorData.isBackOpen);
         this.updateDoorView(this.mContext);
      }

      boolean var2 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      String var6 = "close";
      String var3;
      if (!var2) {
         var3 = "close";
      } else {
         var3 = "open";
      }

      String var4;
      if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         var4 = "close";
      } else {
         var4 = "open";
      }

      String var5;
      if (!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
         var5 = "close";
      } else {
         var5 = "open";
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
         var6 = "open";
      }

      ArrayList var7 = new ArrayList();

      for(int var1 = 0; var1 < 4; ++var1) {
         var7.add(new DriverUpdateEntity(1, var1, (new String[]{var3, var4, var5, var6})[var1]));
      }

      StringBuilder var8 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var7.add(new DriverUpdateEntity(1, 4, var8.append(var10[11] * 256 + var10[12]).append(" rpm").toString()));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
      byte[] var9 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var9[9], var9[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setOriginalCameraStatusInfo() {
      int var1 = this.mRightCameraStatus;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 != var2) {
         this.mRightCameraStatus = var2;
         if (var2 == 1) {
            this.mRightCameraHelper.open();
         } else if (var2 == 0) {
            this.mRightCameraHelper.close();
         }
      }

      var2 = this.mLeftCameraStatus;
      var1 = this.mCanBusInfoInt[6];
      if (var2 != var1) {
         this.mLeftCameraStatus = var1;
         if (var1 == 1) {
            this.mLeftCameraHelper.open();
         } else if (var1 == 0) {
            this.mLeftCameraHelper.close();
         }
      }

   }

   private void setPanelButton() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 2) {
         if (var1 != 3) {
            if (var1 != 6) {
               if (var1 != 9) {
                  if (var1 != 47) {
                     if (var1 != 43) {
                        if (var1 != 44) {
                           if (var1 != 69) {
                              if (var1 == 70) {
                                 this.realKeyClick4(8);
                              }
                           } else {
                              this.realKeyClick4(7);
                           }
                        } else {
                           this.realKeyClick4(2);
                        }
                     } else {
                        this.realKeyClick4(52);
                     }
                  } else {
                     this.realKeyClick4(151);
                  }
               } else {
                  this.realKeyClick4(3);
               }
            } else {
               this.realKeyClick4(50);
            }
         } else {
            this.realKeyClick4(20);
         }
      } else {
         this.realKeyClick4(21);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 26) {
            if (var3 != 30) {
               if (var3 != 33) {
                  if (var3 != 49) {
                     if (var3 != 120) {
                        if (var3 != 232) {
                           if (var3 == 240) {
                              this.setVersionInfo();
                           }
                        } else {
                           this.setOriginalCameraStatusInfo();
                        }
                     } else {
                        this.setCarSetData();
                     }
                  } else {
                     if (this.isAirMsgReturn(var2)) {
                        return;
                     }

                     this.setAirData();
                  }
               } else {
                  this.setPanelButton();
               }
            } else {
               this.driveData0x1e();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData();
         }
      } else {
         this.keyControl0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.senMsg(17);
      this.senMsg(18);
      this.senMsg(49);
      this.senMsg(50);
      this.senMsg(65);
      this.senMsg(240);
      this.senMsg(97);
      this.initFrontCamera(var1);
   }

   void setCameraInput(String var1, int var2) {
      var1.hashCode();
      if (!var1.equals("share_274_right_camera_input")) {
         if (var1.equals("share_274_left_camera_input")) {
            this.mLeftCameraHelper.setInput(this.getCameraInput(var2));
         }
      } else {
         this.mRightCameraHelper.setInput(this.getCameraInput(var2));
      }

      SharePreUtil.setIntValue(this.mContext, var1, var2);
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private class CameraHelper {
      private static final String TAG = "CameraHelper";
      private ComponentName mComponentName;
      private Context mContext;
      private Intent mIntent;
      final MsgMgr this$0;

      public CameraHelper(MsgMgr var1, Context var2) {
         this.this$0 = var1;
         this.mContext = var2;
         this.mIntent = new Intent();
      }

      private boolean isForeground(Context var1, String var2) {
         if (var1 != null && !TextUtils.isEmpty(var2)) {
            List var3 = ((ActivityManager)var1.getSystemService("activity")).getRunningTasks(1);
            if (!var3.isEmpty() && var2.equals(((ActivityManager.RunningTaskInfo)var3.get(0)).topActivity.getClassName())) {
               return true;
            }
         }

         return false;
      }

      private boolean isReverse() {
         return CommUtil.isMiscReverse();
      }

      public void close() {
         if (this.isForeground(this.mContext, this.mComponentName.getClassName())) {
            this.this$0.realKeyClick(this.mContext, 50);
         }

      }

      public void open() {
         if (this.isReverse()) {
            Log.w("CameraHelper", "open: isReverse");
         } else {
            ComponentName var1 = this.mComponentName;
            if (var1 == null) {
               Log.w("CameraHelper", "open: mComponentName is null");
            } else {
               this.mIntent.setComponent(var1);
               this.mIntent.setFlags(268435456);
               this.mContext.startActivity(this.mIntent);
            }
         }
      }

      public void setInput(ComponentName var1) {
         this.mComponentName = var1;
      }
   }
}
