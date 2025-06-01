package com.hzbhd.canbus.car._123;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_123_COMPASS_DIRECT = "share_123_compass_direct";
   private static int mBlowMode;
   private OnAirBtnClickListener RearBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 66, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 66, 0});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener RearTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 1});
      }
   };
   int i = 1;
   private AirActivity mActivity;
   private Context mContext;
   private int mDifferentID;
   private int mEachID;
   private MsgMgr mMsgMgr;
   private MsgMgr msgMgr;
   int nowOtherCarMode = 0;
   int nowZhiNanZheMode = 0;
   private OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 21, 1});
         }

      }
   };
   private OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 1});
         }

      }
   };
   private OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
         }

      }
   };
   private OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
         }

      }
   };
   private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener onUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
      }

      public void onClickUp() {
      }
   };
   private OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.mDifferentID = this.getCurrentCarId();
      this.mEachID = this.getEachId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      if (this.mEachID != 1) {
         var2.setHaveRearArea(false);
      }

      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, this.onUpDownClickListenerCenter, this.onUpDownClickListenerRight});
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 12, 1});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
         }
      });
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.turnWindMode();
         }

         public void onRightSeatClick() {
            this.this$0.turnWindMode();
         }
      });
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.RearTempListener, null});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 1});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 1});
         }
      });
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.RearBottomBtnListener});
      var2.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.i == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 81, 1});
               this.this$0.i = 2;
            } else if (this.this$0.i == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 82, 1});
               this.this$0.i = 3;
            } else if (this.this$0.i == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 83, 1});
               this.this$0.i = 1;
            }

         }

         public void onRightSeatClick() {
            UiMgr var1;
            if (this.this$0.i == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 81, 1});
               var1 = this.this$0;
               ++var1.i;
            } else if (this.this$0.i == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 82, 1});
               var1 = this.this$0;
               ++var1.i;
            } else if (this.this$0.i == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 83, 1});
               this.this$0.i = 1;
            }

         }
      });
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, (byte)var3});
            }

         }
      });
      var4.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 0) {
               if (var2 == 13) {
                  Toast.makeText(this.this$0.mContext, 2131769907, 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, 68, 16, 1});
               }
            } else if (var1 == 5 && var2 == 2) {
               Toast.makeText(this.this$0.mContext, 2131768065, 0).show();
               CanbusMsgSender.sendMsg(new byte[]{22, -99, 2, 1});
            }

         }
      });
      var4.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            UiMgr var3 = this.this$0;
            if (var1 == var3.getSettingLeftIndexes(var3.mContext, "hiworld_jeep_123_0x62_title")) {
               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "hiworld_jeep_123_0x62_title", "_123_rear_mirror")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 0});
               }
            }

            if (var1 == 7 && (var2 == 0 || var2 == 1)) {
               CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.this$0.getCurrentCarId(), 0});
            }

         }
      });
      var4.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 1) {
               if (var2 == 5) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 99, 11, (byte)var3});
               }
            } else if (var1 == 5 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -101, (byte)var3});
            }

         }
      });
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var4, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var13 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var13.hashCode();
            int var5 = var13.hashCode();
            byte var4 = -1;
            switch (var5) {
               case 1687672278:
                  if (var13.equals("hiworld_jeep_123_item_46")) {
                     var4 = 0;
                  }
                  break;
               case 1687672279:
                  if (var13.equals("hiworld_jeep_123_item_47")) {
                     var4 = 1;
                  }
                  break;
               case 1687672280:
                  if (var13.equals("hiworld_jeep_123_item_48")) {
                     var4 = 2;
                  }
                  break;
               case 1687672281:
                  if (var13.equals("hiworld_jeep_123_item_49")) {
                     var4 = 3;
                  }
            }

            short var18;
            label180: {
               switch (var4) {
                  case 0:
                     var4 = 19;
                     break;
                  case 1:
                     var4 = 20;
                     break;
                  case 2:
                     var4 = 21;
                     break;
                  case 3:
                     var4 = 22;
                     break;
                  default:
                     var4 = 0;
                     var18 = 0;
                     break label180;
               }

               var18 = 68;
            }

            byte var15;
            short var16;
            label191: {
               short var6;
               int var7;
               label174: {
                  short var11;
                  label173: {
                     short var12;
                     label172: {
                        short var10;
                        label171: {
                           short var8;
                           label192: {
                              short var9;
                              label193: {
                                 label163: {
                                    short var14;
                                    byte var17;
                                    label162: {
                                       label159:
                                       switch (var1) {
                                          case 0:
                                             switch (var2) {
                                                case 0:
                                                   var15 = 18;
                                                   break;
                                                case 1:
                                                   var15 = 5;
                                                   break;
                                                case 2:
                                                   var15 = 4;
                                                   break;
                                                case 3:
                                                   var15 = 3;
                                                   break;
                                                case 4:
                                                   var15 = 2;
                                                   break;
                                                case 5:
                                                   var15 = 1;
                                                   break;
                                                case 6:
                                                   var15 = 12;
                                                   break;
                                                case 7:
                                                   var15 = 11;
                                                   break;
                                                case 8:
                                                   var15 = 10;
                                                   break;
                                                case 9:
                                                   var15 = 7;
                                                   break;
                                                case 10:
                                                   var15 = 8;
                                                   break;
                                                case 11:
                                                   var15 = 6;
                                                   break;
                                                case 12:
                                                   var15 = 17;
                                                   break;
                                                case 13:
                                                   var15 = 16;
                                                   break;
                                                case 14:
                                                   var15 = 15;
                                                   break;
                                                case 15:
                                                   var15 = 14;
                                                   break;
                                                case 16:
                                                   var15 = 13;
                                                   break;
                                                case 17:
                                                   var15 = 9;
                                                   break;
                                                default:
                                                   var15 = var4;
                                             }

                                             var16 = 68;
                                             var5 = var3;
                                             break label191;
                                          case 1:
                                             var14 = 99;
                                             var18 = var14;
                                             var6 = var14;
                                             var7 = var3;
                                             var11 = var14;
                                             var12 = var14;
                                             var10 = var14;
                                             var8 = var14;
                                             var9 = var14;
                                             switch (var2) {
                                                case 0:
                                                   var4 = 10;
                                                   var16 = var14;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                                case 1:
                                                   var5 = var3 + 1;
                                                   break label162;
                                                case 2:
                                                   break label159;
                                                case 3:
                                                   break label173;
                                                case 4:
                                                   break label174;
                                                case 5:
                                                default:
                                                   var16 = var14;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                                case 6:
                                                   break label193;
                                                case 7:
                                                   break label192;
                                                case 8:
                                                   var4 = 9;
                                                   var16 = var14;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                                case 9:
                                                   break label171;
                                                case 10:
                                                   break label172;
                                                case 11:
                                                   var4 = 13;
                                                   var16 = var14;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                                case 12:
                                                   var4 = 12;
                                                   var16 = var14;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                             }
                                          case 2:
                                             var15 = 97;
                                             var18 = var15;
                                             var6 = var15;
                                             var7 = var3;
                                             var11 = var15;
                                             var12 = var15;
                                             var10 = var15;
                                             var8 = var15;
                                             var9 = var15;
                                             switch (var2) {
                                                case 0:
                                                   break label193;
                                                case 1:
                                                   break label192;
                                                case 2:
                                                   break label171;
                                                case 3:
                                                   break label172;
                                                case 4:
                                                   break label173;
                                                case 5:
                                                   break label174;
                                                case 6:
                                                   break label159;
                                                default:
                                                   var16 = var15;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                             }
                                          case 3:
                                             var18 = 202;
                                             if (var2 != 0) {
                                                if (var2 != 1) {
                                                   if (var2 != 2) {
                                                      if (var2 != 3) {
                                                         if (var2 != 4) {
                                                            var1 = var3;
                                                            var17 = var4;
                                                         } else {
                                                            var1 = var3 + 1;
                                                            var17 = 1;
                                                         }
                                                      } else {
                                                         var1 = var3 + 1;
                                                         var17 = 7;
                                                      }
                                                   } else {
                                                      var1 = var3 + 1;
                                                      var17 = 5;
                                                   }
                                                } else {
                                                   var1 = var3 + 1;
                                                   var17 = 3;
                                                }
                                             } else {
                                                var1 = var3;
                                                var17 = 6;
                                             }

                                             var16 = var18;
                                             var5 = var1;
                                             var15 = var17;
                                             break label191;
                                          case 4:
                                             this.this$0.mMsgMgr.updateSettingActivity(var1, var2, var3);
                                             var7 = this.this$0.resolveSelectPos(var3);
                                             var6 = 154;
                                             break label174;
                                          case 5:
                                             var18 = 157;
                                             if (var2 != 0) {
                                                var15 = var4;
                                             } else {
                                                this.this$0.mMsgMgr.updateSettingActivity(var1, var2, var3);
                                                SharePreUtil.setIntValue(this.val$context, "share_123_compass_direct", var3);
                                                var15 = 1;
                                             }
                                             break label163;
                                          case 6:
                                             var6 = 173;
                                             var18 = var6;
                                             if (var2 != 0) {
                                                var14 = var6;
                                                var5 = var3;
                                                if (var2 != 1) {
                                                   var16 = var6;
                                                   var5 = var3;
                                                   var15 = var4;
                                                   break label191;
                                                }
                                                break label162;
                                             }
                                             break;
                                          default:
                                             var15 = var4;
                                             break label163;
                                       }

                                       var15 = 7;
                                       var16 = var18;
                                       var5 = var3;
                                       break label191;
                                    }

                                    var17 = 8;
                                    var16 = var14;
                                    var15 = var17;
                                    break label191;
                                 }

                                 var16 = var18;
                                 var5 = var3;
                                 break label191;
                              }

                              var15 = 6;
                              var16 = var9;
                              var5 = var3;
                              break label191;
                           }

                           var15 = 5;
                           var16 = var8;
                           var5 = var3;
                           break label191;
                        }

                        var15 = 4;
                        var16 = var10;
                        var5 = var3;
                        break label191;
                     }

                     var15 = 3;
                     var16 = var12;
                     var5 = var3;
                     break label191;
                  }

                  var15 = 2;
                  var16 = var11;
                  var5 = var3;
                  break label191;
               }

               var15 = 1;
               var16 = var6;
               var5 = var7;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, (byte)var16, (byte)var15, (byte)var5});
         }
      });
      var4.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case 1687672303:
                  if (var4.equals("hiworld_jeep_123_item_50")) {
                     var5 = 0;
                  }
                  break;
               case 1687672304:
                  if (var4.equals("hiworld_jeep_123_item_51")) {
                     var5 = 1;
                  }
                  break;
               case 1687672305:
                  if (var4.equals("hiworld_jeep_123_item_52")) {
                     var5 = 2;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 68, 23, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 68, 24, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 68, 25, (byte)var3});
            }

         }
      });
      OriginalCarDevicePageUiSet var5 = this.getOriginalCarDevicePageUiSet(var1);
      var5.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
         }
      });
      var5.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickTopBtnItem(int var1) {
            UiMgr var2;
            if (var1 != 0) {
               if (var1 == 1) {
                  var2 = this.this$0;
                  if (var2.getmMsgMgr(var2.mContext).rdm) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -81, 6, 0, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -81, 6, 1, 0});
                  }
               }
            } else {
               var2 = this.this$0;
               if (var2.getmMsgMgr(var2.mContext).rpt) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 0, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 1, 0});
               }
            }

         }
      });
      var5.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickBottomBtnItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -81, 4, 0, 0});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -81, 2, 0, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -81, 1, 0, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -81, 3, 0, 0});
            }

         }
      });
      var5.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -81, 2, 0, 0});
         }
      });
      var5.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSongItemClick(int var1) {
            ++var1;
            CanbusMsgSender.sendMsg(new byte[]{22, -81, 7, (byte)('\uff00' & var1), (byte)(var1 & 255)});
         }

         public void onSongItemLongClick(int var1) {
         }
      });
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierBand.VOLUME) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var2});
            } else if (var1 == AmplifierActivity.AmplifierBand.BASS) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(var2 - 9)});
            } else if (var1 == AmplifierActivity.AmplifierBand.MIDDLE) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(var2 - 9)});
            } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(var2 - 9)});
            }

         }
      });
      var3.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)var2});
            } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)var2});
            }

         }
      });
   }

   private void blowModeCirculation() {
      int var1;
      if (this.getCurrentCarId() != 5) {
         var1 = mBlowMode;
         if (var1 == 10) {
            mBlowMode = 23;
         } else if (var1 == 24) {
            mBlowMode = 8;
         } else {
            mBlowMode = var1 + 1;
         }
      } else {
         var1 = mBlowMode;
         if (var1 != 29) {
            mBlowMode = var1 + 1;
         }

         if (mBlowMode == 29) {
            mBlowMode = 26;
         }
      }

   }

   private MsgMgr getmMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private int resolveSelectPos(int var1) {
      return (new int[]{1, 2, 3, 4, 5, 7, 8, 9, 16, 21})[var1];
   }

   private void turnWindMode() {
      int var1;
      if (this.getCurrentCarId() == 5) {
         var1 = this.nowZhiNanZheMode;
         if (var1 == 0) {
            mBlowMode = 26;
            this.nowZhiNanZheMode = 1;
         } else if (var1 == 1) {
            mBlowMode = 27;
            this.nowZhiNanZheMode = 2;
         } else if (var1 == 2) {
            mBlowMode = 28;
            this.nowZhiNanZheMode = 3;
         } else if (var1 == 3) {
            mBlowMode = 29;
            this.nowZhiNanZheMode = 0;
         }
      } else {
         var1 = this.nowOtherCarMode;
         if (var1 == 0) {
            mBlowMode = 8;
            this.nowOtherCarMode = 1;
         } else if (var1 == 1) {
            mBlowMode = 9;
            this.nowOtherCarMode = 2;
         } else if (var1 == 2) {
            mBlowMode = 10;
            this.nowOtherCarMode = 3;
         } else if (var1 == 3) {
            mBlowMode = 23;
            this.nowOtherCarMode = 4;
         } else if (var1 == 4) {
            mBlowMode = 24;
            this.nowOtherCarMode = 0;
         }
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)mBlowMode, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)mBlowMode, 0});
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCarId(), 0});
      if (this.getCurrentCarId() == 5) {
         mBlowMode = 26;
      }

      if (this.mEachID == 1) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_76"});
      }

      if (this.mEachID != 1) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_76s"});
      }

      int var2 = this.mDifferentID;
      if (var2 != 6 && var2 != 7) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_item_53"});
      }

   }
}
