package com.hzbhd.canbus.car._193;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int a = 0;
   int b = 0;
   int c = 0;
   private int eachId;
   private AirPageUiSet mAirPageUiSet;
   private FrontArea mFrontArea;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 0});
         }

         if (var1 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 17, 0});
         }

         if (var1 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 0});
         }

         if (var1 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (this.this$0.b == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, 0});
            this.this$0.b = 1;
         } else if (this.this$0.b == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, 0});
            this.this$0.b = 0;
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, 0});
         }

         if (var1 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, 0});
         }

         if (var1 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, 0});
         }

         if (var1 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 0});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 13, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 13, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 16, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 16, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 15, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -88, 15, 0});
      }
   };
   private ParkPageUiSet mParkPageUiSet;
   private MsgMgr msgMgr;
   private UiMgr uiMgr;

   public UiMgr(Context var1) {
      int var2 = this.getEachId();
      this.eachId = var2;
      if (var2 == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 1});
      }

      if (this.eachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 2});
      }

      if (this.eachId == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 3});
      }

      if (this.eachId == 8) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 4});
      }

      if (this.eachId == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 5});
      }

      if (this.eachId == 10) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 6});
      }

      if (this.eachId == 11) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 7});
      }

      if (this.eachId == 13) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 8});
      }

      if (this.eachId == 14) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 9});
      }

      if (this.eachId == 16) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 10});
      }

      if (this.eachId == 17) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 11});
      }

      if (this.eachId == 18) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 12});
      }

      AirPageUiSet var3 = this.getAirUiSet(var1);
      this.mAirPageUiSet = var3;
      FrontArea var4 = var3.getFrontArea();
      this.mFrontArea = var4;
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
      this.mFrontArea.setWindMaxLevel(7);
      this.mFrontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 12, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 12, 0});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 11, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 11, 0});
         }
      });
      this.mFrontArea.setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.a == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 7, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 7, 0});
               this.this$0.a = 1;
            } else if (this.this$0.a == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 0});
               this.this$0.a = 2;
            } else if (this.this$0.a == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 9, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 9, 0});
               this.this$0.a = 3;
            } else if (this.this$0.a == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 10, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 10, 0});
               this.this$0.a = 0;
            }

         }

         public void onRightSeatClick() {
            if (this.this$0.a == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 7, 1});
               this.this$0.a = 1;
            } else if (this.this$0.a == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 1});
               this.this$0.a = 2;
            } else if (this.this$0.a == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 9, 1});
               this.this$0.a = 3;
            } else if (this.this$0.a == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -88, 10, 1});
               this.this$0.a = 0;
            }

         }
      });
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            byte var5;
            byte var6;
            byte var7;
            String var8;
            byte var10;
            label376: {
               var8 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
               var8.hashCode();
               int var4 = var8.hashCode();
               var7 = 8;
               var6 = 6;
               var5 = -1;
               switch (var4) {
                  case -1658358948:
                     if (var8.equals("_1193_setting_1")) {
                        var10 = 0;
                        break label376;
                     }
                     break;
                  case -1658358947:
                     if (var8.equals("_1193_setting_2")) {
                        var10 = 1;
                        break label376;
                     }
                     break;
                  case -1658358946:
                     if (var8.equals("_1193_setting_3")) {
                        var10 = 2;
                        break label376;
                     }
                     break;
                  case -1658358945:
                     if (var8.equals("_1193_setting_4")) {
                        var10 = 3;
                        break label376;
                     }
                     break;
                  case -1658358944:
                     if (var8.equals("_1193_setting_5")) {
                        var10 = 4;
                        break label376;
                     }
                     break;
                  case -1658358943:
                     if (var8.equals("_1193_setting_6")) {
                        var10 = 5;
                        break label376;
                     }
                     break;
                  case -1658358942:
                     if (var8.equals("_1193_setting_7")) {
                        var10 = 6;
                        break label376;
                     }
                     break;
                  case -1658358941:
                     if (var8.equals("_1193_setting_8")) {
                        var10 = 7;
                        break label376;
                     }
                     break;
                  case 130480212:
                     if (var8.equals("_1193_setting_10")) {
                        var10 = 8;
                        break label376;
                     }
               }

               var10 = -1;
            }

            byte var9;
            switch (var10) {
               case 0:
                  label357: {
                     var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var8.hashCode();
                     switch (var8.hashCode()) {
                        case -250079219:
                           if (var8.equals("_1193_setting_1_0")) {
                              var9 = 0;
                              break label357;
                           }
                           break;
                        case -250079218:
                           if (var8.equals("_1193_setting_1_1")) {
                              var9 = 1;
                              break label357;
                           }
                           break;
                        case -250079217:
                           if (var8.equals("_1193_setting_1_2")) {
                              var9 = 2;
                              break label357;
                           }
                           break;
                        case -250079216:
                           if (var8.equals("_1193_setting_1_3")) {
                              var9 = 3;
                              break label357;
                           }
                           break;
                        case -250079215:
                           if (var8.equals("_1193_setting_1_4")) {
                              var9 = 4;
                              break label357;
                           }
                           break;
                        case -250079214:
                           if (var8.equals("_1193_setting_1_5")) {
                              var9 = 5;
                              break label357;
                           }
                           break;
                        case -250079213:
                           if (var8.equals("_1193_setting_1_6")) {
                              var9 = 6;
                              break label357;
                           }
                           break;
                        case -250079212:
                           if (var8.equals("_1193_setting_1_7")) {
                              var9 = 7;
                              break label357;
                           }
                           break;
                        case -250079211:
                           var9 = var7;
                           if (var8.equals("_1193_setting_1_8")) {
                              break label357;
                           }
                           break;
                        case -250079210:
                           if (var8.equals("_1193_setting_1_9")) {
                              var9 = 9;
                              break label357;
                           }
                        case -250079209:
                        case -250079208:
                        case -250079207:
                        case -250079206:
                        case -250079205:
                        case -250079204:
                        case -250079203:
                        case -250079200:
                        default:
                           break;
                        case -250079202:
                           if (var8.equals("_1193_setting_1_A")) {
                              var9 = 10;
                              break label357;
                           }
                           break;
                        case -250079201:
                           if (var8.equals("_1193_setting_1_B")) {
                              var9 = 11;
                              break label357;
                           }
                           break;
                        case -250079199:
                           if (var8.equals("_1193_setting_1_D")) {
                              var9 = 12;
                              break label357;
                           }
                           break;
                        case -250079198:
                           if (var8.equals("_1193_setting_1_E")) {
                              var9 = 13;
                              break label357;
                           }
                     }

                     var9 = -1;
                  }

                  switch (var9) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte)var3});
                        return;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, (byte)var3});
                        return;
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte)var3});
                        return;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, (byte)var3});
                        return;
                     case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                        return;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                        return;
                     case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 23, (byte)var3});
                        return;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 1, (byte)var3});
                        return;
                     case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 1:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  switch (var8.hashCode()) {
                     case -250078258:
                        if (!var8.equals("_1193_setting_2_0")) {
                           var9 = var5;
                        } else {
                           var9 = 0;
                        }
                        break;
                     case -250078257:
                        if (!var8.equals("_1193_setting_2_1")) {
                           var9 = var5;
                        } else {
                           var9 = 1;
                        }
                        break;
                     case -250078256:
                        if (!var8.equals("_1193_setting_2_2")) {
                           var9 = var5;
                        } else {
                           var9 = 2;
                        }
                        break;
                     case -250078255:
                        if (!var8.equals("_1193_setting_2_3")) {
                           var9 = var5;
                        } else {
                           var9 = 3;
                        }
                        break;
                     case -250078254:
                        if (!var8.equals("_1193_setting_2_4")) {
                           var9 = var5;
                        } else {
                           var9 = 4;
                        }
                        break;
                     case -250078253:
                        if (!var8.equals("_1193_setting_2_5")) {
                           var9 = var5;
                        } else {
                           var9 = 5;
                        }
                        break;
                     default:
                        var9 = var5;
                  }

                  switch (var9) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)var3});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte)var3});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte)var3});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 2:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  switch (var8) {
                     case "_1193_setting_3_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                        return;
                     case "_1193_setting_3_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
                        return;
                     case "_1193_setting_3_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 3:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  switch (var8) {
                     case "_1193_setting_0_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3});
                        return;
                     case "_1193_setting_4_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                        return;
                     case "_1193_setting_4_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 4, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 4:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  switch (var8) {
                     case "_1193_setting_5_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte)var3});
                        return;
                     case "_1193_setting_5_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                        return;
                     case "_1193_setting_5_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte)var3});
                        return;
                     case "_1193_setting_5_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)((byte)var3 + 1)});
                        return;
                     default:
                        return;
                  }
               case 5:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  switch (var8.hashCode()) {
                     case -250074414:
                        if (!var8.equals("_1193_setting_6_0")) {
                           var9 = var5;
                        } else {
                           var9 = 0;
                        }
                        break;
                     case -250074413:
                        if (!var8.equals("_1193_setting_6_1")) {
                           var9 = var5;
                        } else {
                           var9 = 1;
                        }
                        break;
                     case -250074412:
                        if (!var8.equals("_1193_setting_6_2")) {
                           var9 = var5;
                        } else {
                           var9 = 2;
                        }
                        break;
                     case -250074411:
                        if (!var8.equals("_1193_setting_6_3")) {
                           var9 = var5;
                        } else {
                           var9 = 3;
                        }
                        break;
                     case -250074410:
                        if (!var8.equals("_1193_setting_6_4")) {
                           var9 = var5;
                        } else {
                           var9 = 4;
                        }
                        break;
                     case -250074409:
                        if (!var8.equals("_1193_setting_6_5")) {
                           var9 = var5;
                        } else {
                           var9 = 5;
                        }
                        break;
                     default:
                        var9 = var5;
                  }

                  switch (var9) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 25, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte)var3});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -2, (byte)var3});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 5, (byte)var3});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 6, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 6:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  switch (var8) {
                     case "_1193_setting_7_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte)var3});
                        return;
                     case "_1193_setting_7_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                        return;
                     case "_1193_setting_7_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 7:
                  label337: {
                     var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var8.hashCode();
                     switch (var8.hashCode()) {
                        case -250072492:
                           if (var8.equals("_1193_setting_8_0")) {
                              var9 = 0;
                              break label337;
                           }
                           break;
                        case -250072491:
                           if (var8.equals("_1193_setting_8_1")) {
                              var9 = 1;
                              break label337;
                           }
                           break;
                        case -250072490:
                           if (var8.equals("_1193_setting_8_2")) {
                              var9 = 2;
                              break label337;
                           }
                           break;
                        case -250072489:
                           if (var8.equals("_1193_setting_8_3")) {
                              var9 = 3;
                              break label337;
                           }
                           break;
                        case -250072488:
                           if (var8.equals("_1193_setting_8_4")) {
                              var9 = 4;
                              break label337;
                           }
                           break;
                        case -250072487:
                           if (var8.equals("_1193_setting_8_5")) {
                              var9 = 5;
                              break label337;
                           }
                           break;
                        case 198511565:
                           var9 = var6;
                           if (var8.equals("_1193_setting_8_5_3")) {
                              break label337;
                           }
                     }

                     var9 = -1;
                  }

                  switch (var9) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var3});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte)var3});
                        return;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 8:
                  var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var8.hashCode();
                  if (!var8.equals("_1193_setting_10_0")) {
                     if (var8.equals("_1193_setting_10_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 8, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 7, (byte)var3});
                  }
            }

         }
      });
      var5.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var6 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var6.hashCode();
            int var5 = var6.hashCode();
            byte var4 = -1;
            switch (var5) {
               case -1658358948:
                  if (var6.equals("_1193_setting_1")) {
                     var4 = 0;
                  }
               case -1658358947:
               case -1658358946:
               default:
                  break;
               case -1658358945:
                  if (var6.equals("_1193_setting_4")) {
                     var4 = 1;
                  }
                  break;
               case -1658358944:
                  if (var6.equals("_1193_setting_5")) {
                     var4 = 2;
                  }
            }

            switch (var4) {
               case 0:
                  var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var6.hashCode();
                  if (!var6.equals("_1193_setting_1_C")) {
                     if (var6.equals("_1193_setting_1_F")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 3, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte)var3});
                  }
                  break;
               case 1:
                  var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var6.hashCode();
                  if (var6.equals("_1193_setting_4_1")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)((byte)var3 + 1)});
                  }
                  break;
               case 2:
                  var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var6.hashCode();
                  if (var6.equals("_1193_setting_5_3")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  }
            }

         }
      });
      var5.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("enter_panorama")) {
               var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var3.hashCode();
               if (var3.equals("_253_click_into_the_panorama")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 17, 1});
               }
            }

         }
      });
      var5.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_1193_setting_9")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, -1, 1});
            }

         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var6;
      var6.setShowRadar(true);
      this.mParkPageUiSet.setShowTrack(true);
      this.mParkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void addViewToWindows() {
            this.this$0.getMsgMgr(this.val$context).initRadar();
         }
      });
      this.mParkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1) {
            this.this$0.getMsgMgr(this.val$context).updateParkingBtn(var1);
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -89, 16});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 4});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -89, 3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -89, 1});
            }

         }
      });
      this.mParkPageUiSet.setShowRadar(true);
      this.mParkPageUiSet.setShowPanoramic(true);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var5 = var6.iterator();

         while(var5.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.eachId;
      if (var2 == 1 || var2 == 4 || var2 == 12 || var2 == 15) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      var2 = this.eachId;
      if (var2 == 10 || var2 == 15) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      var2 = this.eachId;
      if (var2 == 3 || var2 == 4 || var2 == 7 || var2 == 10 || var2 == 14 || var2 == 15 || var2 == 18) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_0"});
      }

      var2 = this.eachId;
      if (var2 != 7 && var2 != 1) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_1"});
      }

      var2 = this.eachId;
      if (var2 == 1 || var2 == 3 || var2 == 4 || var2 == 10 || var2 == 14 || var2 == 15 || var2 == 18) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_2"});
      }

      var2 = this.eachId;
      if (var2 != 2 && var2 != 5 && var2 != 8 && var2 != 9 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_2_0"});
      }

      var2 = this.eachId;
      if (var2 == 1 || var2 == 3 || var2 == 4 || var2 == 7 || var2 == 10 || var2 == 14 || var2 == 15 || var2 == 18) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_2_1"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_4_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_4_1"});
      }

      var2 = this.eachId;
      if (var2 != 2 && var2 != 5 && var2 != 6 && var2 != 8 && var2 != 9) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_3_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_3_1"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_3_2"});
      }

      if (this.eachId != 4) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_2_2"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_4"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_5"});
      }

      var2 = this.eachId;
      if (var2 != 4 && var2 != 7) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_3"});
      }

      var2 = this.eachId;
      if (var2 != 5 && var2 != 7 && var2 != 8 && var2 != 9) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_6"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_7"});
      }

      var2 = this.eachId;
      if (var2 != 5 && var2 != 7 && var2 != 8 && var2 != 9 && var2 != 6 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_8"});
      }

      if (this.eachId != 6) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_9"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_A"});
      }

      if (this.eachId != 7) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_2_3"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_2_4"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_2_5"});
      }

      var2 = this.eachId;
      if (var2 != 7 && var2 != 9) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_B"});
      }

      var2 = this.eachId;
      if (var2 != 7 && var2 != 11 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_5_0"});
      }

      var2 = this.eachId;
      if (var2 != 9 && var2 != 13 && var2 != 16 && var2 != 17) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_6_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_6_1"});
      }

      var2 = this.eachId;
      if (var2 != 9 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_C"});
      }

      var2 = this.eachId;
      if (var2 != 11 && var2 != 13 && var2 != 16 && var2 != 17) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_7_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_7_1"});
      }

      var2 = this.eachId;
      if (var2 != 11 && var2 != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_5_1"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_5_2"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_5_3"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_5_4"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_1"});
      }

      if (this.eachId != 13) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_2"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_3"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_4"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_5"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_8_5_3"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_6_2"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_7_2"});
      }

      var2 = this.eachId;
      if (var2 == 1 || var2 == 4 || var2 == 7 || var2 == 10 || var2 == 12 || var2 == 14 || var2 == 15 || var2 == 18) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_6_3"});
      }

      var2 = this.eachId;
      if (var2 != 5 && var2 != 6 && var2 != 8 && var2 != 9 && var2 != 11 && var2 != 13 && var2 != 16 && var2 != 17) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_9"});
      }

      var2 = this.eachId;
      if (var2 != 2 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 8 && var2 != 9) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_253_click_into_the_panorama"});
      }

      var2 = this.eachId;
      if (var2 != 1 && var2 != 14) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_0_0"});
      }

      var2 = this.eachId;
      if (var2 != 18 && var2 != 18) {
         this.removeMainPrjBtnByName(var1, "drive_data");
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_D"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_E"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_1_F"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_4_2"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_1193_setting_10"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_6_4"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_1193_setting_6_5"});
      }

      if (this.getSettingUiSet(var1).getList().size() > 0 && "car_set".equals(((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getTitleSrn())) {
         for(var2 = 0; var2 < ((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getItemList().size(); ++var2) {
            if ("_253_front_radar_switch".equals(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getItemList().get(var2)).getTitleSrn())) {
               this.getMsgMgr(var1).updateSetting(0, var2, SharePreUtil.getBoolValue(var1, "share_key_253_front_radar_enable", true));
            } else if ("_253_rear_radar_switch".equals(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.getSettingUiSet(var1).getList().get(0)).getItemList().get(var2)).getTitleSrn())) {
               this.getMsgMgr(var1).updateSetting(0, var2, SharePreUtil.getBoolValue(var1, "share_key_253_rear_radar_enable", true));
            }
         }
      }

   }
}
