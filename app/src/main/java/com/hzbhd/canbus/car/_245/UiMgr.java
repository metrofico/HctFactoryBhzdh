package com.hzbhd.canbus.car._245;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UiMgr extends AbstractUiMgr {
   static final boolean $assertionsDisabled = false;
   private MsgMgr msgMgr;
   OnSettingItemSelectListener onSettingItemSelectListener = new UiMgr$$ExternalSyntheticLambda0(this);
   public final Map settingPageIndex = new HashMap();
   public SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(64, 0);
         }
      });
      if (this.getCurrentCarId() != 4 && this.getCurrentCarId() != 5 && this.getCurrentCarId() != 7 && this.getCurrentCarId() != 10) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      this.getAirUiSet(var1).getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(33, 0);
         }
      });
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(38, 0);
            this.this$0.activeRequest(80, 0);
         }
      });
   }

   private void activeRequest(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, (byte)var1, (byte)var2});
   }

   private void sendSettingData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -128, (byte)var1, (byte)var2});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
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

   public Map initSettingPageIndex(Context var1) {
      List var4 = this.getSettingUiSet(var1).getList();
      Iterator var8 = var4.iterator();

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         SettingPageUiSet.ListBean var5 = (SettingPageUiSet.ListBean)var8.next();
         this.settingPageIndex.put(var5.getTitleSrn(), var2);
         List var9 = var5.getItemList();
         Iterator var6 = var9.iterator();

         for(int var3 = 0; var3 < var9.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var6.next();
            this.settingPageIndex.put(var7.getTitleSrn(), var3);
         }
      }

      return this.settingPageIndex;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__245_UiMgr(int var1, int var2, int var3) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        if (var2 != 0) {
                           if (var2 != 1) {
                              if (var2 != 2) {
                                 if (var2 != 3) {
                                    if (var2 != 4) {
                                       if (var2 == 5) {
                                          this.sendSettingData(57, var3);
                                       }
                                    } else {
                                       this.sendSettingData(244, var3);
                                    }
                                 } else {
                                    this.sendSettingData(243, var3);
                                 }
                              } else {
                                 this.sendSettingData(242, var3);
                              }
                           } else {
                              this.sendSettingData(241, var3);
                           }
                        } else {
                           this.sendSettingData(240, var3);
                        }
                     }
                  } else {
                     switch (var2) {
                        case 0:
                           this.sendSettingData(48, var3);
                           break;
                        case 1:
                           this.sendSettingData(49, var3);
                           break;
                        case 2:
                           this.sendSettingData(50, var3);
                           break;
                        case 3:
                           this.sendSettingData(51, var3);
                           break;
                        case 4:
                           this.sendSettingData(52, var3);
                           break;
                        case 5:
                           this.sendSettingData(53, var3);
                           break;
                        case 6:
                           this.sendSettingData(54, var3);
                           break;
                        case 7:
                           this.sendSettingData(55, var3);
                     }
                  }
               } else if (var2 != 0) {
                  if (var2 == 1) {
                     this.sendSettingData(33, var3);
                  }
               } else {
                  this.sendSettingData(32, var3);
               }
            } else if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 == 5) {
                              this.sendSettingData(21, var3);
                           }
                        } else {
                           this.sendSettingData(20, var3);
                        }
                     } else {
                        this.sendSettingData(19, var3);
                     }
                  } else {
                     this.sendSettingData(18, var3);
                  }
               } else {
                  this.sendSettingData(17, var3);
               }
            } else {
               this.sendSettingData(16, var3);
            }
         } else if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3) {
                     this.sendSettingData(4, var3);
                  }
               } else {
                  this.sendSettingData(3, var3);
               }
            } else {
               this.sendSettingData(2, var3);
            }
         } else {
            this.sendSettingData(1, var3);
         }
      } else if (var2 == 0) {
         this.sendSettingData(0, var3);
      }

   }
}
