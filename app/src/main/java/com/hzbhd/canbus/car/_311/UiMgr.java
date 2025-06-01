package com.hzbhd.canbus.car._311;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         if (this.this$0.getCurrentCarId() == 0) {
            switch (var1) {
               case 0:
                  this.this$0.SendMsg(8);
                  break;
               case 1:
                  this.this$0.SendMsg(9);
                  break;
               case 2:
                  this.this$0.SendMsg(10);
                  break;
               case 3:
                  this.this$0.SendMsg(11);
                  break;
               case 4:
                  this.this$0.SendMsg(12);
                  break;
               case 5:
                  this.this$0.SendMsg(13);
                  break;
               case 6:
                  this.this$0.SendMsg(14);
                  break;
               case 7:
                  this.this$0.SendMsg(15);
                  break;
               case 8:
                  this.this$0.SendMsg(16);
                  break;
               case 9:
                  this.this$0.SendMsg(4);
                  break;
               case 10:
                  this.this$0.SendMsg(1);
                  break;
               case 11:
                  this.this$0.SendMsg(5);
                  break;
               case 12:
                  this.this$0.SendMsg(6);
                  break;
               case 13:
                  this.this$0.SendMsg(7);
                  break;
               case 14:
                  this.this$0.SendMsg(3);
                  break;
               case 15:
                  this.this$0.SendMsg(2);
            }
         } else if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendMsg(21);
            }
         } else {
            this.this$0.SendMsg(22);
         }

      }
   };
   private OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new OnPanelKeyPositionTouchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onTouch(int var1, MotionEvent var2) {
         if (this.this$0.getCurrentCarId() == 0) {
            switch (var1) {
               case 0:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 8});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 1:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 9});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 2:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 10});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 3:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 11});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 4:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 12});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 5:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 13});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 6:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 14});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 7:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 15});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 8:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 16});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 9:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 4});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 10:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 1});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 11:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 5});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 12:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 6});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 13:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 7});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 14:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 3});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
                  break;
               case 15:
                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 2});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
                  }
            }
         } else if (var1 != 0) {
            if (var1 == 1) {
               if (var2.getAction() == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 6, 21});
               }

               if (var2.getAction() == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
               }
            }
         } else {
            if (var2.getAction() == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, 6, 22});
            }

            if (var2.getAction() == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
            }
         }

      }
   };
   private String[] str = new String[]{"panel_btn_num1", "panel_btn_num2", "panel_btn_num3", "panel_btn_num4", "panel_btn_num5", "panel_btn_num6", "panel_btn_num7", "panel_btn_num8", "panel_btn_num9", "panel_btn_left", "panel_btn_ok", "panel_btn_right", "panel_btn_fmam", "panel_btn_cdmp3", "panel_btn_bc"};

   public UiMgr(Context var1) {
      PanelKeyPageUiSet var2 = this.getPanelKeyPageUiSet(var1);
      var2.setOnPanelKeyPositionListener(this.mOnPanelKeyPositionListener);
      var2.setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
      if (this.getCurrentCarId() == 1) {
         var2.setCount(2);
      }

   }

   private void SendMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)var1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            var2 = 0;

            while(true) {
               String[] var3 = this.str;
               if (var2 >= var3.length) {
                  break;
               }

               this.removePanelBtnKeyByName(var1, var3[var2]);
               ++var2;
            }
         }
      } else {
         this.removePanelBtnKeyByName(var1, "panel_btn_clock");
      }

      this.removeMainPrjBtnByName(var1, "drive_data");
   }
}
