package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_204_LANGUAGE = "share_204_language";
   static final String SHARE_204_REAR_MIRROR_FOLD = "share_204_rear_mirror_fold";
   static final String SHARE_204_SURROUND_SOUND_TYPE = "share_204_surround_sound_type";
   static final String SHARE_204_VOL_WITH_SPEED = "share_204_vol_with_speed";
   static final String SHARE_IS_SUPPORTED_FRONT_VIEW = "share_is_supported_front_view";
   static final String SHARE_IS_SUPPORTED_PANORAMIC = "share_is_supported_panoramic";
   private final String TAG = "_204_UiMgr";
   private Context mContext;
   private View mCusPanoramicView;
   private MsgMgr mMsgMgr;
   private ParkPageUiSet mParkPageUiSet;

   public UiMgr(Context var1) {
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.mContext = var1;
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 != 4) {
                        if (var3 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, -1});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, 1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)var2});
            }

         }
      });
      var2.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 10)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 10)});
            }

         }
      });
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
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
            boolean var5;
            boolean var6;
            byte var8;
            label80: {
               String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var7.hashCode();
               int var4 = var7.hashCode();
               var6 = false;
               var5 = false;
               switch (var4) {
                  case -2057154824:
                     if (var7.equals("_1204_rainfall_light_sensor")) {
                        var8 = 0;
                        break label80;
                     }
                     break;
                  case -1857175370:
                     if (var7.equals("_1204_follow_home_delay")) {
                        var8 = 1;
                        break label80;
                     }
                     break;
                  case -1438897752:
                     if (var7.equals("_284_support_right_view")) {
                        var8 = 2;
                        break label80;
                     }
                     break;
                  case 309382301:
                     if (var7.equals("_1204_ceiling_light_delay")) {
                        var8 = 3;
                        break label80;
                     }
                     break;
                  case 712683749:
                     if (var7.equals("support_panorama")) {
                        var8 = 4;
                        break label80;
                     }
                     break;
                  case 1235727081:
                     if (var7.equals("_250_language")) {
                        var8 = 5;
                        break label80;
                     }
                     break;
                  case 1434103567:
                     if (var7.equals("_1204_power_saving")) {
                        var8 = 6;
                        break label80;
                     }
                     break;
                  case 1446444519:
                     if (var7.equals("_1204_rear_view_mirror_fold")) {
                        var8 = 7;
                        break label80;
                     }
                     break;
                  case 1490148421:
                     if (var7.equals("_278_vol_with_speed")) {
                        var8 = 8;
                        break label80;
                     }
                     break;
                  case 1806178504:
                     if (var7.equals("vm_golf7_language_setup")) {
                        var8 = 9;
                        break label80;
                     }
                     break;
                  case 1902729116:
                     if (var7.equals("outlander_simple_car_set_8")) {
                        var8 = 10;
                        break label80;
                     }
               }

               var8 = -1;
            }

            Context var9;
            switch (var8) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 19, (byte)var3, -1, -1});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 17, (byte)var3, -1, -1});
                  break;
               case 2:
                  var9 = this.val$context;
                  var5 = var6;
                  if (var3 == 1) {
                     var5 = true;
                  }

                  SharePreUtil.setBoolValue(var9, "share_is_supported_front_view", var5);
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 16, (byte)var3, -1, -1});
                  break;
               case 4:
                  var9 = this.val$context;
                  if (var3 == 1) {
                     var5 = true;
                  }

                  SharePreUtil.setBoolValue(var9, "share_is_supported_panoramic", var5);
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
                  this.this$0.mMsgMgr.updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_204_language", var3);
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 18, (byte)var3, -1, -1});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 0, (byte)var3, -1, -1});
                  this.this$0.mMsgMgr.updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_204_rear_mirror_fold", var3);
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte)var3});
                  this.this$0.mMsgMgr.updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_204_vol_with_speed", var3);
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 8, (byte)var3});
                  this.this$0.mMsgMgr.updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.val$context, "share_204_surround_sound_type", var3);
            }

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
            if (var4.equals("_1204_trailer_setup")) {
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 20, (byte)var3, -1, -1});
            }

         }
      });
      var4.getOnSettingItemSelectListener().onClickItem(1, 0, SharePreUtil.getIntValue(var1, "share_204_language", 0));
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var3;
      var3.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
            }

         }
      });
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mCusPanoramicView == null) {
         this.mCusPanoramicView = new MyPanoramicView(var1);
      }

      return this.mCusPanoramicView;
   }
}
