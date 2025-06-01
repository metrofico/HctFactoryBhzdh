package com.hzbhd.canbus.car._213;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private static final String SHARE_213_LANGUAGE = "share_213_language";
   private OnAmplifierSeekBarListener mAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(var2 + 10)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(var2 + 10)});
         }

      }
   };
   private Context mContext;
   private MsgMgr mMsgMgr;
   private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
      }
   };
   private OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 16)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 16)});
         }

      }
   };
   private OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -14, 2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
         }

      }
   };
   private OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onBackPressed() {
         CanbusMsgSender.sendMsg(new byte[]{22, -14, 1});
      }
   };
   private OnOriginalCarDevicePageStatusListener mOnOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (FutureUtil.instance.getCurrentValidSource() != SourceConstantsDef.SOURCE_ID.AUX2) {
            FutureUtil.instance.audioChannelRequest(SourceConstantsDef.SOURCE_ID.AUX2);
         }

         (new Thread(this) {
            final <undefinedtype> this$1;

            {
               this.this$1 = var1;
            }

            public void run() {
               super.run();

               try {
                  sleep(500L);
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 0});
                  sleep(200L);
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 1});
                  sleep(200L);
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 2});
               } catch (Exception var2) {
                  var2.printStackTrace();
               }

            }
         }).start();
      }
   };
   private OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         switch (var1) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 2});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 1});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 1});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 2});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
               break;
            case 8:
               if (GeneralOriginalCarDeviceData.cd) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 1});
               }
         }

      }
   };
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 5) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 55, (byte)(var3 + 5)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 54, (byte)(var3 + 5)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 53, (byte)(var3 + 15)});
            }
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 != 5) {
                           return;
                        }
                     } else if (var2 == 0) {
                        this.this$0.getMsgMgr().updateSettings(var1, var2, var3);
                        this.this$0.getMsgMgr().setFuelUnit(var3);
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 52, (byte)var3});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte)var3});
                  }
               } else if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 16, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 15, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 14, (byte)var3});
               }
            } else if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 25, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 24, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 23, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 22, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 21, (byte)var3});
            }
         } else {
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 9, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 1, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 4, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 5, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 6, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 3, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 7, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 8, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 2, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 10, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 13, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 17, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 18, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 19, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 12, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 20, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 11, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 45, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 27, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 46, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 28, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 29, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 30, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 31, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 32, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 33, (byte)var3});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 34, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 35, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 36, (byte)var3});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 37, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 38, (byte)var3});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 39, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 47, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 48, (byte)var3});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 50, (byte)var3});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 40, (byte)var3});
                  break;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 41, (byte)var3});
                  break;
               case 37:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 42, (byte)var3});
                  break;
               case 38:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 43, (byte)var3});
                  break;
               case 39:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 44, (byte)var3});
                  break;
               case 40:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 49, (byte)var3});
                  break;
               case 41:
                  CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 51, (byte)var3});
                  break;
               case 42:
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
                  this.this$0.getMsgMgr().updateSettings(var1, var2, var3);
                  SharePreUtil.setIntValue(this.this$0.mContext, "share_213_language", var3);
            }
         }

      }
   };
   private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == -1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 120});
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getMsgMgr().updateSettings(0, 42, SharePreUtil.getIntValue(var1, "share_213_language", 0));
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierSeekBarListener(this.mAmplifierSeekBarListener);
      var3.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      this.getAirUiSet(var1).setOnAirPageStatusListener(this.mOnAirPageStatusListener);
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 20});
               CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 21});
               CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 17});
               CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 38});
            }

         }
      });
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
      var4.setOnOriginalCarDevicePageStatusListener(this.mOnOriginalCarDevicePageStatusListener);
      var4.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
      var4.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
      var4.setOnOriginalCarDeviceBackPressedListener(this.mOnOriginalCarDeviceBackPressedListener);
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 4 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 26, 1});
               Toast.makeText(this.val$context, 2131769831, 0).show();
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            UiMgr var3 = this.this$0;
            if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_touchpad_0")) {
               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_touchpad_0", "_touchpad_0")) {
                  this.this$0.getMsgMgr().touchpadView();
               }
            }

         }
      });
   }

   private MsgMgr getMsgMgr() {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      }

      return this.mMsgMgr;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
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
}
