package com.hzbhd.canbus.car._433;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   String KEY_BT_MEDIA_SWITCH = "KEY_BT_MEDIA_SWITCH";
   String KEY_BT_SWITCH = "KEY_BT_SWITCH";
   int byteLength = 35;
   int differentId;
   int eachId;
   Context mContext;
   byte[] mMiscInfoCopy0x02;
   byte[] mMiscInfoCopy0x03;
   byte[] mMiscInfoCopy0x04;
   MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.getSettingUiSet(this.mContext).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_433_BT_Switch")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_433_BT_Switch", "_433_BT_Switch1")) {
                  if (var3 == 0) {
                     this.this$0.send0xCABTCmd(1, 2);
                  }

                  if (var3 == 1) {
                     this.this$0.send0xCABTCmd(1, 1);
                  }

                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_BT_SWITCH, var3);
                  return;
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_433_BT_Switch", "_433_BT_Switch2")) {
                  if (var3 == 0) {
                     this.this$0.send0xCABTCmd(2, 0);
                  }

                  if (var3 == 1) {
                     this.this$0.send0xCABTCmd(2, 1);
                  }

                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_BT_MEDIA_SWITCH, var3);
                  return;
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_433_BT_Switch", "_433_BT_Switch3")) {
                  if (var3 == 0) {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).startPanel(false);
                  } else {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).startPanel(true);
                  }

                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }
            }

         }
      });
      this.getOriginalCarDevicePageUiSet(this.mContext).setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this) {
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
                              this.this$0.send0xC5UsbControl(6);
                           }
                        } else {
                           this.this$0.send0xC5UsbControl(4);
                        }
                     } else {
                        this.this$0.send0xC5UsbControl(2);
                     }
                  } else {
                     this.this$0.send0xC5UsbControl(1);
                  }
               } else {
                  this.this$0.send0xC5UsbControl(3);
               }
            } else {
               this.this$0.send0xC5UsbControl(5);
            }

         }
      });
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
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

   protected boolean isMusicMsgRepeat0x02(byte[] var1) {
      if (Arrays.equals(var1, this.mMiscInfoCopy0x02)) {
         return true;
      } else {
         this.mMiscInfoCopy0x02 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected boolean isMusicMsgRepeat0x03(byte[] var1) {
      if (Arrays.equals(var1, this.mMiscInfoCopy0x03)) {
         return true;
      } else {
         this.mMiscInfoCopy0x03 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected boolean isMusicMsgRepeat0x04(byte[] var1) {
      if (Arrays.equals(var1, this.mMiscInfoCopy0x04)) {
         return true;
      } else {
         this.mMiscInfoCopy0x04 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   public void send0xC0SourceType(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void send0xC2RadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void send0xC3MediaInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void send0xC4VolInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
   }

   public void send0xC5UsbControl(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte)var1, 0});
   }

   public void send0xC8TimeInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void send0xCABTCmd(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)var1, (byte)var2});
   }

   public void send0xCBSendString(int var1, byte[] var2) {
      Throwable var10000;
      label802: {
         synchronized(this){}
         boolean var4;
         boolean var10001;
         if (var1 == 2) {
            try {
               var4 = this.isMusicMsgRepeat0x02(var2);
            } catch (Throwable var75) {
               var10000 = var75;
               var10001 = false;
               break label802;
            }

            if (var4) {
               return;
            }
         } else if (var1 == 3) {
            try {
               var4 = this.isMusicMsgRepeat0x03(var2);
            } catch (Throwable var76) {
               var10000 = var76;
               var10001 = false;
               break label802;
            }

            if (var4) {
               return;
            }
         } else if (var1 == 4) {
            try {
               var4 = this.isMusicMsgRepeat0x04(var2);
            } catch (Throwable var77) {
               var10000 = var77;
               var10001 = false;
               break label802;
            }

            if (var4) {
               return;
            }
         }

         int var3;
         byte[] var5;
         try {
            var3 = var2.length;
            this.byteLength = var3;
            var5 = new byte[35];
         } catch (Throwable var74) {
            var10000 = var74;
            var10001 = false;
            break label802;
         }

         if (var3 == 53) {
            try {
               CanbusMsgSender.sendMsg(var2);
            } catch (Throwable var70) {
               var10000 = var70;
               var10001 = false;
               break label802;
            }
         } else {
            var1 = 0;
            if (var3 <= 53) {
               if (var3 >= 53) {
                  return;
               }

               var1 = 0;

               while(true) {
                  try {
                     if (var1 >= 53 - this.byteLength) {
                        break;
                     }

                     var2 = this.SplicingByte(var2, new byte[]{0});
                  } catch (Throwable var73) {
                     var10000 = var73;
                     var10001 = false;
                     break label802;
                  }

                  ++var1;
               }

               try {
                  CanbusMsgSender.sendMsg(var2);
               } catch (Throwable var72) {
                  var10000 = var72;
                  var10001 = false;
                  break label802;
               }
            } else {
               while(var1 < 53) {
                  var5[var1] = var2[var1];
                  ++var1;
               }

               try {
                  CanbusMsgSender.sendMsg(var5);
               } catch (Throwable var71) {
                  var10000 = var71;
                  var10001 = false;
                  break label802;
               }
            }

            return;
         }

         return;
      }

      Throwable var78 = var10000;
      throw var78;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(var1, "_433_BT_Switch"), this.getSettingRightIndex(var1, "_433_BT_Switch", "_433_BT_Switch1"), SharePreUtil.getIntValue(var1, this.KEY_BT_SWITCH, 0));
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(var1, "_433_BT_Switch"), this.getSettingRightIndex(var1, "_433_BT_Switch", "_433_BT_Switch2"), SharePreUtil.getIntValue(var1, this.KEY_BT_MEDIA_SWITCH, 0));
   }
}
