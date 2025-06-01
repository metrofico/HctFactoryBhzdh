package com.hzbhd.canbus.car._332;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class UiMgr extends AbstractUiMgr {
   private static int fullScreenState;
   int bPercentage = 1;
   int bStartHour = 0;
   int bStartMinute = 0;
   int bWeek = 1;
   DecimalFormat df2 = new DecimalFormat("00");
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_332_tachograph_settings_2")) {
            if (var2 != 4) {
               if (var2 == 7) {
                  this.this$0.sendVideoState(0);
                  this.this$0.sendVideoState(15);
                  Toast.makeText(this.this$0.mContext, "恢复出厂设置成功", 0).show();
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).updateSettings(var1, 0, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).updateSettings(var1, 1, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).updateSettings(var1, 2, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).updateSettings(var1, 3, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).updateSettings(var1, 5, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).updateSettings(var1, 6, 0);
               }
            } else {
               this.this$0.sendVideoState(0);
               this.this$0.sendVideoState(10);
               Toast.makeText(this.this$0.mContext, "格式化成功", 0).show();
            }
         }

         var3 = this.this$0;
         Context var5;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_332_Timed_charging_1") && var2 == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, (byte)this.this$0.bWeek, (byte)this.this$0.bStartHour, (byte)this.this$0.bStartMinute, (byte)this.this$0.bPercentage});
            var5 = this.this$0.mContext;
            StringBuilder var7 = (new StringBuilder()).append("定时成功！");
            UiMgr var4 = this.this$0;
            Toast.makeText(var5, var7.append(var4.getWeek(var4.bWeek)).append(this.this$0.df2.format((long)this.this$0.bStartHour)).append(":").append(this.this$0.df2.format((long)this.this$0.bStartMinute)).append(" 百分比：").append(this.this$0.bPercentage).append("%").toString(), 0).show();
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_332_Timed_charging_2") && var2 == 5) {
            if (Integer.parseInt(this.this$0.tsStartHour + "" + this.this$0.tsStartMinute) < Integer.parseInt(this.this$0.tsEndHour + "" + this.this$0.tsEndMinute)) {
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, (byte)this.this$0.tsWeek, (byte)this.this$0.tsStartHour, (byte)this.this$0.tsStartHour, (byte)this.this$0.tsEndHour, (byte)this.this$0.tsEndMinute});
               var5 = this.this$0.mContext;
               StringBuilder var6 = (new StringBuilder()).append("定时成功！");
               var3 = this.this$0;
               Toast.makeText(var5, var6.append(var3.getWeek(var3.tsWeek)).append(this.this$0.df2.format((long)this.this$0.tsStartHour)).append(":").append(this.this$0.df2.format((long)this.this$0.tsStartMinute)).append("至").append(this.this$0.df2.format((long)this.this$0.tsEndHour)).append(":").append(this.this$0.df2.format((long)this.this$0.tsEndMinute)).toString(), 0).show();
            } else {
               Toast.makeText(this.this$0.mContext, "警告：结束时间必须在开始时间之后", 0).show();
            }
         }

         var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_332_Timed_charging_3") && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, (byte)this.this$0.getNowWeekDay(), (byte)this.this$0.getNowHourTime(), (byte)(this.this$0.getNowMinuteTime() + 1)});
            Toast.makeText(this.this$0.mContext, "已开始充电！", 0).show();
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getLeftIndexes(var3.mContext, "_327_360_camera") && var2 == 1) {
            Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131762950), 0).show();
            this.this$0.sendPanoramicMsg(9);
            this.this$0.sendPanoramicMsg(10);
         }

         var3 = this.this$0;
         if (var3.getLeftIndexes(var3.mContext, "_332_tachograph_settings_3") == var1 && var2 == 0) {
            this.this$0.sendPhotoSettingState(0);
            this.this$0.sendPhotoSettingState(1);
            Toast.makeText(this.this$0.mContext, "拍照成功", 0).show();
         }

      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_setting_1") && var2 == 9) {
            this.this$0.sendSettingsMsg(10, var3);
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_Timed_charging_1")) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3) {
                     this.this$0.bPercentage = var3;
                  }
               } else {
                  this.this$0.bStartMinute = var3;
               }
            } else {
               this.this$0.bStartHour = var3;
            }

            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_Timed_charging_2")) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4) {
                        this.this$0.tsEndMinute = var3;
                     }
                  } else {
                     this.this$0.tsEndHour = var3;
                  }
               } else {
                  this.this$0.tsStartMinute = var3;
               }
            } else {
               this.this$0.tsStartHour = var3;
            }

            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_setting_1")) {
            switch (var2) {
               case 0:
                  this.this$0.sendSettingsMsg(1, var3);
                  break;
               case 1:
                  this.this$0.sendSettingsMsg(2, var3);
                  break;
               case 2:
                  this.this$0.sendSettingsMsg(3, var3);
                  break;
               case 3:
                  this.this$0.sendSettingsMsg(4, var3);
                  break;
               case 4:
                  this.this$0.sendSettingsMsg(5, var3);
                  break;
               case 5:
                  this.this$0.sendSettingsMsg(6, var3);
                  break;
               case 6:
                  this.this$0.sendSettingsMsg(7, var3);
                  break;
               case 7:
                  this.this$0.sendSettingsMsg(8, var3);
                  break;
               case 8:
                  this.this$0.sendSettingsMsg(9, var3);
               case 9:
               default:
                  break;
               case 10:
                  this.this$0.sendSettingsMsg(11, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_327_360_camera") && var2 == 0) {
            if (var3 == 1) {
               this.this$0.sendPanoramicMsg(10);
            } else {
               this.this$0.sendPanoramicMsg(0);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_charge") && var2 == 0) {
            var4 = this.this$0;
            var4.sendChargeData(var4.getChargeMode(var3));
            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_tachograph_settings_2")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 5) {
                           if (var2 == 6) {
                              if (var3 == 0) {
                                 this.this$0.sendVideoState(0);
                                 this.this$0.sendVideoState(14);
                                 var4 = this.this$0;
                                 var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                              } else {
                                 this.this$0.sendVideoState(0);
                                 this.this$0.sendVideoState(15);
                                 var4 = this.this$0;
                                 var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                              }
                           }
                        } else if (var3 == 0) {
                           this.this$0.sendVideoState(0);
                           this.this$0.sendVideoState(11);
                           var4 = this.this$0;
                           var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                        } else {
                           this.this$0.sendVideoState(0);
                           this.this$0.sendVideoState(12);
                           var4 = this.this$0;
                           var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                        }
                     } else if (var3 == 0) {
                        this.this$0.sendVideoState(0);
                        this.this$0.sendVideoState(7);
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                     } else if (var3 == 1) {
                        this.this$0.sendVideoState(0);
                        this.this$0.sendVideoState(8);
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                     } else {
                        this.this$0.sendVideoState(0);
                        this.this$0.sendVideoState(9);
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 2);
                     }
                  } else if (var3 == 0) {
                     this.this$0.sendVideoState(0);
                     this.this$0.sendVideoState(5);
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                  } else {
                     this.this$0.sendVideoState(0);
                     this.this$0.sendVideoState(6);
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                  }
               } else if (var3 == 0) {
                  this.this$0.sendVideoState(0);
                  this.this$0.sendVideoState(3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
               } else {
                  this.this$0.sendVideoState(0);
                  this.this$0.sendVideoState(4);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
               }
            } else if (var3 == 0) {
               this.this$0.sendTime();
               this.this$0.sendVideoState(0);
               this.this$0.sendVideoState(1);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
               this.this$0.sendTime();
            } else {
               this.this$0.sendTime();
               this.this$0.sendVideoState(0);
               this.this$0.sendVideoState(2);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
               this.this$0.sendTime();
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_tachograph_settings_4")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           if (var3 == 0) {
                              this.this$0.sendTime();
                              this.this$0.sendPlayBackState(0);
                              this.this$0.sendPlayBackState(9);
                              var4 = this.this$0;
                              var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                              this.this$0.sendTime();
                           } else {
                              this.this$0.sendTime();
                              this.this$0.sendPlayBackState(0);
                              this.this$0.sendPlayBackState(10);
                              var4 = this.this$0;
                              var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                              this.this$0.sendTime();
                           }
                        }
                     } else if (var3 == 0) {
                        this.this$0.sendPlayBackState(0);
                        this.this$0.sendPlayBackState(7);
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                     } else {
                        this.this$0.sendPlayBackState(0);
                        this.this$0.sendPlayBackState(8);
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                     }
                  } else if (var3 == 0) {
                     this.this$0.sendPlayBackState(0);
                     this.this$0.sendPlayBackState(5);
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
                  } else {
                     this.this$0.sendPlayBackState(0);
                     this.this$0.sendPlayBackState(6);
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
                  }
               } else if (var3 == 0) {
                  this.this$0.sendPlayBackState(0);
                  this.this$0.sendPlayBackState(4);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
               } else {
                  this.this$0.sendPlayBackState(0);
                  this.this$0.sendPlayBackState(3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
               }
            } else if (var3 == 0) {
               this.this$0.sendTime();
               this.this$0.sendPlayBackState(0);
               this.this$0.sendPlayBackState(2);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 0);
               this.this$0.sendTime();
            } else {
               this.this$0.sendTime();
               this.this$0.sendPlayBackState(0);
               this.this$0.sendPlayBackState(1);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, 1);
               this.this$0.sendTime();
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_Timed_charging_1") && var2 == 0) {
            switch (var3) {
               case 0:
                  this.this$0.bWeek = 1;
                  break;
               case 1:
                  this.this$0.bWeek = 2;
                  break;
               case 2:
                  this.this$0.bWeek = 4;
                  break;
               case 3:
                  this.this$0.bWeek = 8;
                  break;
               case 4:
                  this.this$0.bWeek = 16;
                  break;
               case 5:
                  this.this$0.bWeek = 32;
                  break;
               case 6:
                  this.this$0.bWeek = 64;
            }

            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         }

         var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_332_Timed_charging_2") && var2 == 0) {
            switch (var3) {
               case 0:
                  this.this$0.tsWeek = 1;
                  break;
               case 1:
                  this.this$0.tsWeek = 2;
                  break;
               case 2:
                  this.this$0.tsWeek = 4;
                  break;
               case 3:
                  this.this$0.tsWeek = 8;
                  break;
               case 4:
                  this.this$0.tsWeek = 16;
                  break;
               case 5:
                  this.this$0.tsWeek = 32;
                  break;
               case 6:
                  this.this$0.tsWeek = 64;
            }

            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         }

      }
   };
   OnPanoramicItemClickListener panoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         switch (var1) {
            case 0:
               this.this$0.sendPanoramicMsg(10);
               break;
            case 1:
               this.this$0.sendPanoramicMsg(5);
               break;
            case 2:
               this.this$0.sendPanoramicMsg(6);
               break;
            case 3:
               this.this$0.sendPanoramicMsg(7);
               break;
            case 4:
               this.this$0.sendPanoramicMsg(8);
               break;
            case 5:
               this.this$0.sendPanoramicMsg(2);
               break;
            case 6:
               this.this$0.sendPanoramicMsg(1);
               break;
            case 7:
               if (UiMgr.fullScreenState == 0) {
                  UiMgr.fullScreenState = 1;
                  this.this$0.sendPanoramicMsg(3);
               } else {
                  UiMgr.fullScreenState = 0;
                  this.this$0.sendPanoramicMsg(4);
               }
               break;
            case 8:
               this.this$0.sendPanoramicMsg(0);
         }

      }
   };
   int tsEndHour = 0;
   int tsEndMinute = 0;
   int tsStartHour = 0;
   int tsStartMinute = 0;
   int tsWeek = 1;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.initUi();
   }

   private int getChargeMode(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 3 ? 1 : 8;
         } else {
            return 4;
         }
      } else {
         return 2;
      }
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private int getVolLevel() {
      byte var1;
      if (MediaShareData.Volume.INSTANCE.getVolume() == 0) {
         var1 = 2;
      } else {
         var1 = 1;
      }

      MyLog.temporaryTracking("获取到的音量状态：" + var1);
      return var1 == 0 ? 1 : var1;
   }

   private String getWeek(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               if (var1 != 8) {
                  if (var1 != 16) {
                     return var1 != 32 ? this.mContext.getString(2131762894) : this.mContext.getString(2131762893);
                  } else {
                     return this.mContext.getString(2131762892);
                  }
               } else {
                  return this.mContext.getString(2131762891);
               }
            } else {
               return this.mContext.getString(2131762890);
            }
         } else {
            return this.mContext.getString(2131762889);
         }
      } else {
         return this.mContext.getString(2131762910);
      }
   }

   private void initUi() {
      if (this.eachId != 7) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_332_setting_1", "_327_360_camera"});
         this.getParkPageUiSet(this.mContext).setShowPanoramic(false);
      }

      if (this.differentId != 4) {
         this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"_332_driverData_1", "_332_Energy_information"});
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_332_charge", "_332_Timed_charging_1", "_332_Timed_charging_2", "_332_Timed_charging_3"});
      }

   }

   private void sendChargeData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)var1});
   }

   private void sendPanoramicMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1});
   }

   private void sendPhotoSettingState(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, (byte)var1});
   }

   private void sendPlayBackState(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, (byte)var1});
   }

   private void sendSettingsMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   private void sendVideoState(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte)var1});
   }

   public void disConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
   }

   public int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   public int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   protected int getLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   public int getNowHourTime() {
      Date var1 = new Date();
      return Integer.parseInt((new SimpleDateFormat("yyyyMMddHHmmss")).format(var1).substring(8, 10));
   }

   public int getNowMinuteTime() {
      Date var1 = new Date();
      return Integer.parseInt((new SimpleDateFormat("yyyyMMddHHmmss")).format(var1).substring(10, 12));
   }

   public int getNowWeekDay() {
      Calendar var1 = Calendar.getInstance();
      var1.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
      String var2 = String.valueOf(var1.get(7));
      if ("1".equals(var2)) {
         return 64;
      } else if ("7".equals(var2)) {
         return 32;
      } else if ("3".equals(var2)) {
         return 2;
      } else if ("4".equals(var2)) {
         return 4;
      } else if ("5".equals(var2)) {
         return 8;
      } else {
         return "6".equals(var2) ? 16 : 1;
      }
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void sendCarType() {
      if (this.getEachId() == 8) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 83, 1});
      } else if (this.getEachId() == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 83, 2});
      }

   }

   public void sendMediaSourcesAux() {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 5, 0, 0, 0, 0, 0, 0, (byte)this.getVolLevel()});
      }

   }

   public void sendMediaSourcesBluetooth() {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 6, 0, 0, 0, 0, 0, 0, (byte)this.getVolLevel()});
      }

   }

   public void sendMediaSourcesCD(int var1, int var2) {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 2, 0, 0, 0, 0, (byte)var1, (byte)var2, (byte)this.getVolLevel()});
      }

   }

   public void sendMediaSourcesMusic(int var1, int var2) {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 4, 0, 0, 0, 0, (byte)var1, (byte)var2, (byte)this.getVolLevel()});
      }

   }

   public void sendMediaSourcesNoSRC() {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 0, 0, 0, 0, 0, 0, 0, (byte)this.getVolLevel()});
      }

   }

   public void sendMediaSourcesPower() {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 7, 0, 0, 0, 0, 0, 0, (byte)this.getVolLevel()});
      }

   }

   public void sendMediaSourcesRadio(String var1) {
      if (this.getEachId() == 6) {
         if (var1.contains("AM")) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 1, 3, 0, 0, 0, 0, 0, (byte)this.getVolLevel()});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 1, 1, 0, 0, 0, 0, 0, (byte)this.getVolLevel()});
         }
      }

   }

   public void sendMediaSourcesUsb(int var1, int var2) {
      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, 117, 3, 0, 0, 0, 0, (byte)var1, (byte)var2, (byte)this.getVolLevel()});
      }

   }

   public void sendNowTime(int var1, int var2, int var3, int var4, int var5) {
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5});
   }

   public void sendTime() {
      Date var7 = new Date();
      String var8 = (new SimpleDateFormat("yyyyMMddHHmmss")).format(var7);
      int var4 = Integer.parseInt(var8.substring(0, 4)) - 2000;
      int var6 = Integer.parseInt(var8.substring(4, 6));
      int var1 = Integer.parseInt(var8.substring(6, 8));
      int var5 = Integer.parseInt(var8.substring(8, 10));
      int var2 = Integer.parseInt(var8.substring(10, 12));
      int var3 = Integer.parseInt(var8.substring(12, 14));
      MyLog.d("行车记录仪时间信息", var4 + ":" + var6 + ":" + var1 + ":" + var5 + ":" + var2 + ":" + var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte)var4, (byte)var6, (byte)var1, (byte)var5, (byte)var2, (byte)var3});
   }

   public void sendVoiceControlInfo0x01(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, 1, (byte)var1});
   }

   public void sendVoiceControlInfo0x02(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, 2, (byte)var1, (byte)var2});
   }

   public void sendVoiceControlInfo0x03To0x3F(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, (byte)var1, (byte)var2, (byte)var3});
   }

   public void sendVoiceControlInfoAir(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, (byte)var1, (byte)var2});
   }

   public void sendVoiceControlInfoAir(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, (byte)var1, (byte)var2, (byte)var3});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var2.setOnSettingItemClickListener(this.onSettingItemClickListener);
      var2.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(this.panoramicItemClickListener);
   }
}
