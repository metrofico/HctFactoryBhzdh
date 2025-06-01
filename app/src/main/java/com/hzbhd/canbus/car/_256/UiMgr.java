package com.hzbhd.canbus.car._256;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;

public class UiMgr extends AbstractUiMgr {
   public static byte m0x06Byte;

   public UiMgr(Context var1) {
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte)var3});
         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 == 1) {
                  LogUtil.showLog("click 1");
               }
            } else {
               LogUtil.showLog("click 0");
            }

         }
      });
      PanelKeyPageUiSet var2 = this.getPanelKeyPageUiSet(var1);
      var2.setOnPanelLongKeyPositionListener(new OnPanelLongKeyPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void longClick(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte)DataHandleUtils.setIntByteWithBit(0, 7, true)});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 5, true)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 6, true)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 7, true)});
            }

         }
      });
      var2.setOnPanelKeyPositionTouchListener(new OnPanelKeyPositionTouchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onTouch(int var1, MotionEvent var2) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        if (var2.getAction() == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte)DataHandleUtils.setIntByteWithBit(0, 7, true)});
                        }

                        if (var2.getAction() == 1) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte)DataHandleUtils.setIntByteWithBit(0, 7, false)});
                        }
                     }
                  } else {
                     if (var2.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 5, true)});
                     }

                     if (var2.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 5, false)});
                     }
                  }
               } else {
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 6, true)});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 6, false)});
                  }
               }
            } else {
               if (var2.getAction() == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 7, true)});
               }

               if (var2.getAction() == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 7, false)});
               }
            }

         }
      });
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 16)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var2 + 16)});
            }

         }
      });
      var3.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 10)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 10)});
            }

         }
      });
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() == 0) {
         this.removeSettingLeftItem(var1, 1, 1);
         this.removeMainPrjBtnByName(var1, "amplifier");
      }

   }
}
