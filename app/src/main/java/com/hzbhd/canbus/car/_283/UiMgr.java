package com.hzbhd.canbus.car._283;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private String[] mAirBtnListRearBottom;
   private Context mContext;
   private CusPanoramicView mMyPanoramicView;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         UiMgr.subWind();
      }

      public void onClickRight() {
         UiMgr.addWind();
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      void lambda$onClickDown$1$com_hzbhd_canbus_car__283_UiMgr$6() {
         this.this$0.subTemp((byte)20, GeneralDzData.air_front_left_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }

         this.this$0.subTemp((byte)21, GeneralDzData.air_front_right_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }

         this.this$0.subTemp((byte)22, GeneralDzData.air_rear_temp);
      }

      // $FF: synthetic method
      void lambda$onClickUp$0$com_hzbhd_canbus_car__283_UiMgr$6() {
         this.this$0.addTemp((byte)20, GeneralDzData.air_front_left_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }

         this.this$0.addTemp((byte)21, GeneralDzData.air_front_right_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }

         this.this$0.addTemp((byte)22, GeneralDzData.air_rear_temp);
      }

      public void onClickDown() {
         (new Thread(new UiMgr$6$$ExternalSyntheticLambda0(this))).start();
      }

      public void onClickUp() {
         (new Thread(new UiMgr$6$$ExternalSyntheticLambda1(this))).start();
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      void lambda$onClickDown$1$com_hzbhd_canbus_car__283_UiMgr$7() {
         this.this$0.subTemp((byte)20, GeneralDzData.air_front_left_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }

         this.this$0.subTemp((byte)21, GeneralDzData.air_front_right_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }

         this.this$0.subTemp((byte)22, GeneralDzData.air_rear_temp);
      }

      // $FF: synthetic method
      void lambda$onClickUp$0$com_hzbhd_canbus_car__283_UiMgr$7() {
         this.this$0.addTemp((byte)20, GeneralDzData.air_front_left_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }

         this.this$0.addTemp((byte)21, GeneralDzData.air_front_right_temp);

         try {
            Thread.sleep(100L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }

         this.this$0.addTemp((byte)22, GeneralDzData.air_rear_temp);
      }

      public void onClickDown() {
         (new Thread(new UiMgr$7$$ExternalSyntheticLambda1(this))).start();
      }

      public void onClickUp() {
         (new Thread(new UiMgr$7$$ExternalSyntheticLambda0(this))).start();
      }
   };
   private float tempInterval = 0.5F;

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      String[][] var2 = var3.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var2[0];
      this.mAirBtnListFrontLeft = var2[1];
      this.mAirBtnListFrontRight = var2[2];
      this.mAirBtnListFrontBottom = var2[3];
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListener);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
   }

   private void addTemp(byte var1, String var2) {
      this.getTempInterval();

      Exception var10000;
      label98: {
         boolean var10001;
         try {
            if (var2.equals("HI")) {
               return;
            }
         } catch (Exception var15) {
            var10000 = var15;
            var10001 = false;
            break label98;
         }

         boolean var4;
         try {
            var4 = GeneralDzData.fahrenheit_celsius;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
            break label98;
         }

         float var3;
         if (var4) {
            label99: {
               label71: {
                  label70: {
                     try {
                        if (!var2.equals("LO")) {
                           break label70;
                        }
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label99;
                     }

                     var3 = 16.0F;
                     break label71;
                  }

                  try {
                     var3 = Float.valueOf(var2.replace(this.mContext.getString(2131770016), ""));
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label99;
                  }
               }

               try {
                  var3 = (var3 + this.tempInterval) * 2.0F;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label99;
               }

               if (var3 >= 59.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -1});
                     return;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                  }
               }
            }
         } else {
            label86: {
               label85: {
                  label84: {
                     try {
                        if (var2.equals("LO")) {
                           break label84;
                        }
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label86;
                     }

                     try {
                        var3 = Float.valueOf(var2.replace(this.mContext.getString(2131770017), ""));
                        break label85;
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label86;
                     }
                  }

                  var3 = 61.0F;
               }

               var3 = var3 * 2.0F + 2.0F;
               if (var3 >= 170.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -1});
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                  }
               }
            }
         }
      }

      Exception var16 = var10000;
      var16.printStackTrace();
   }

   public static void addWind() {
      int var1 = GeneralAirData.front_wind_level;
      if (var1 >= 0 && var1 < 7) {
         byte var0 = (byte)(var1 + 1);
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, var0});
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, var0});
      }

   }

   private void getTempInterval() {
      try {
         this.tempInterval = Float.valueOf(SharePreUtil.getStringValue(this.mContext, "_283_temp_interval", "0.5"));
      } catch (Exception var2) {
         this.tempInterval = 0.5F;
         var2.printStackTrace();
      }

   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      if (!var1.equals("ac")) {
         if (var1.equals("in_out_cycle")) {
            this.sendMsg((byte)19, GeneralAirData.in_out_cycle);
         }
      } else {
         this.sendMsg((byte)15, GeneralAirData.ac);
      }

   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)(var2 ^ 1)});
   }

   private void subTemp(byte var1, String var2) {
      this.getTempInterval();

      Exception var10000;
      label98: {
         boolean var10001;
         try {
            if (var2.equals("LO")) {
               return;
            }
         } catch (Exception var15) {
            var10000 = var15;
            var10001 = false;
            break label98;
         }

         boolean var4;
         try {
            var4 = GeneralDzData.fahrenheit_celsius;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
            break label98;
         }

         float var3;
         if (var4) {
            label99: {
               label71: {
                  label70: {
                     try {
                        if (!var2.equals("HI")) {
                           break label70;
                        }
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label99;
                     }

                     var3 = 29.5F;
                     break label71;
                  }

                  try {
                     var3 = Float.valueOf(var2.replace(this.mContext.getString(2131770016), ""));
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label99;
                  }
               }

               try {
                  var3 = (var3 - this.tempInterval) * 2.0F;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label99;
               }

               if (var3 <= 32.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -2});
                     return;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                  }
               }
            }
         } else {
            label86: {
               label85: {
                  label84: {
                     try {
                        if (var2.equals("HI")) {
                           break label84;
                        }
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label86;
                     }

                     try {
                        var3 = Float.valueOf(var2.replace(this.mContext.getString(2131770017), ""));
                        break label85;
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label86;
                     }
                  }

                  var3 = 85.0F;
               }

               var3 = var3 * 2.0F - 2.0F;
               if (var3 <= 122.0F) {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, -2});
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               } else {
                  try {
                     MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                  }
               }
            }
         }
      }

      Exception var16 = var10000;
      var16.printStackTrace();
   }

   public static void subWind() {
      int var1 = GeneralAirData.front_wind_level;
      if (var1 > 0 && var1 <= 7) {
         byte var0 = (byte)(var1 - 1);
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, var0});
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, var0});
      }

   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new CusPanoramicView(var1);
      }

      this.mMyPanoramicView.refreshUiLine();
      return this.mMyPanoramicView;
   }
}
