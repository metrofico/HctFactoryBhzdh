package com.hzbhd.canbus.car._334;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   public List cdPageList;
   public HashMap cmd_carId_Map = new HashMap();
   public List dvdPageList;
   public final boolean haveProblem = false;
   private final int id;
   private String leftTitle;
   private MsgMgr msgMgr;
   public HashMap pageMap;
   public List radioPageList;
   private String rightTitle;
   public HashMap settingPageIndex;
   private final SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.initIdMap();
      this.id = this.getCurrentCarId();
      this.msgMgr = this.getMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0(this));
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         private int day;
         private int month;
         final UiMgr this$0;
         private int year;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            byte var5;
            byte var6;
            String var7;
            label121: {
               UiMgr var4 = this.this$0;
               var4.leftTitle = ((SettingPageUiSet.ListBean)var4.settingPageUiSet.getList().get(var1)).getTitleSrn();
               var4 = this.this$0;
               var4.rightTitle = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var4.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               Log.i("lyn", "leftIndex: " + this.this$0.leftTitle + "  rightIndex: " + this.this$0.rightTitle);
               var7 = this.this$0.leftTitle;
               var7.hashCode();
               var1 = var7.hashCode();
               var6 = -1;
               switch (var1) {
                  case -364022701:
                     if (var7.equals("_334_next_service")) {
                        var5 = 0;
                        break label121;
                     }
                     break;
                  case 149338180:
                     if (var7.equals("_334_maintenance_info")) {
                        var5 = 1;
                        break label121;
                     }
                     break;
                  case 1887315661:
                     if (var7.equals("_334_hud")) {
                        var5 = 2;
                        break label121;
                     }
               }

               var5 = -1;
            }

            switch (var5) {
               case 0:
                  var7 = this.this$0.rightTitle;
                  var7.hashCode();
                  switch (var7.hashCode()) {
                     case 51415209:
                        if (!var7.equals("_334_next_service_year")) {
                           var5 = var6;
                        } else {
                           var5 = 0;
                        }
                        break;
                     case 766408926:
                        if (!var7.equals("_334_next_service_mileage")) {
                           var5 = var6;
                        } else {
                           var5 = 1;
                        }
                        break;
                     case 1583099796:
                        if (!var7.equals("_334_next_service_month")) {
                           var5 = var6;
                        } else {
                           var5 = 2;
                        }
                        break;
                     case 1664206256:
                        if (!var7.equals("_334_next_service_day")) {
                           var5 = var6;
                        } else {
                           var5 = 3;
                        }
                        break;
                     default:
                        var5 = var6;
                  }

                  switch (var5) {
                     case 0:
                        var1 = var3 - 2018;
                        this.year = var1;
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)var1, 0, 0});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)this.year, (byte)this.month, (byte)this.day, (byte)(('\uff00' & var3) >> 8), (byte)(var3 & 255)});
                        return;
                     case 2:
                        this.month = var3;
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)this.year, (byte)var3, 0});
                        ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(0)).getItemList().get(3)).setMax(this.this$0.dateDeal(this.year, this.month));
                        return;
                     case 3:
                        this.day = var3;
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)this.year, (byte)this.month, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 1:
                  var7 = this.this$0.rightTitle;
                  var7.hashCode();
                  switch (var7.hashCode()) {
                     case -1237445363:
                        if (!var7.equals("_334_oil_change_distance")) {
                           var5 = var6;
                        } else {
                           var5 = 0;
                        }
                        break;
                     case -927465379:
                        if (!var7.equals("_334_scheduled_maintenance_distance")) {
                           var5 = var6;
                        } else {
                           var5 = 1;
                        }
                        break;
                     case -329289495:
                        if (!var7.equals("_334_tire_rotation_distance")) {
                           var5 = var6;
                        } else {
                           var5 = 2;
                        }
                        break;
                     case 750205749:
                        if (!var7.equals("_334_scheduled_maintenance_time")) {
                           var5 = var6;
                        } else {
                           var5 = 3;
                        }
                        break;
                     default:
                        var5 = var6;
                  }

                  switch (var5) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, 3, (byte)(('\uff00' & var3) >> 8), (byte)(var3 & 255)});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, 3, (byte)(('\uff00' & var3) >> 8), (byte)(var3 & 255)});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, 3, (byte)(('\uff00' & var3) >> 8), (byte)(var3 & 255)});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, 2, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 2:
                  var7 = this.this$0.rightTitle;
                  var7.hashCode();
                  switch (var7.hashCode()) {
                     case -1704683815:
                        if (!var7.equals("_334_hud_height")) {
                           var5 = var6;
                        } else {
                           var5 = 0;
                        }
                        break;
                     case -1408834803:
                        if (!var7.equals("_334_hud_rotate")) {
                           var5 = var6;
                        } else {
                           var5 = 1;
                        }
                        break;
                     case -468711165:
                        if (!var7.equals("_334_hud_brightness")) {
                           var5 = var6;
                        } else {
                           var5 = 2;
                        }
                        break;
                     case 1157976952:
                        if (!var7.equals("_334_hud_calibration")) {
                           var5 = var6;
                        } else {
                           var5 = 3;
                        }
                        break;
                     case 1772887279:
                        if (!var7.equals("_334_hud_tilt")) {
                           var5 = var6;
                        } else {
                           var5 = 4;
                        }
                        break;
                     default:
                        var5 = var6;
                  }

                  switch (var5) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var3 + 15)});
                        break;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var3 + 3)});
                        break;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var3 + 20)});
                        break;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)(var3 + 2)});
                        break;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)(var3 + 5)});
                  }
            }

         }
      });
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(new UiMgr$$ExternalSyntheticLambda1());
      var3.setOnAmplifierSeekBarListener(new UiMgr$$ExternalSyntheticLambda2(this));
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
      var4.setOnOriginalCarDevicePageStatusListener(new UiMgr$$ExternalSyntheticLambda3(this, var1));
      var4.setOnOriginalTopBtnClickListeners(new UiMgr$$ExternalSyntheticLambda4(this));
      var4.setOnOriginalBottomBtnClickListeners(new UiMgr$$ExternalSyntheticLambda5(this));
      var4.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSongItemClick(int var1) {
            ++var1;
            byte var2 = (byte)('\uff00' & var1);
            byte var3 = (byte)(var1 & 255);
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 15, var2, var3});
            CanbusMsgSender.sendMsg(new byte[]{22, -96, -126, var2, var3});
         }

         public void onSongItemLongClick(int var1) {
         }
      });
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda6());
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 112});
      this.settingPageIndex = new HashMap();
      this.pageMap = new HashMap();
      this.initSettingPageIndex();
      this.initOriginalDevice();
   }

   private int dateDeal(int var1, int var2) {
      if (var2 != 2) {
         byte var3 = 31;
         byte var4 = var3;
         switch (var2) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
               break;
            case 2:
            case 4:
            case 6:
            case 9:
            case 11:
               var4 = 30;
               break;
            default:
               var4 = var3;
         }

         return var4;
      } else {
         return (var1 % 4 != 0 || var1 % 100 == 0) && var1 % 400 != 0 ? 28 : 29;
      }
   }

   private int getDownValue(int var1) {
      return var1 & 255 | 128;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void initIdMap() {
      ArrayList var12 = new ArrayList();
      Integer var6 = 1;
      var12.add(var6);
      Integer var11 = 2;
      var12.add(var11);
      Integer var1 = 3;
      var12.add(var1);
      Integer var9 = 4;
      var12.add(var9);
      Integer var8 = 11;
      var12.add(var8);
      Integer var3 = 5;
      var12.add(var3);
      Integer var4 = 6;
      var12.add(var4);
      Integer var5 = 7;
      var12.add(var5);
      Integer var2 = 8;
      var12.add(var2);
      Integer var7 = 9;
      var12.add(var7);
      Integer var10 = 12;
      var12.add(var10);
      this.cmd_carId_Map.put(33, var12);
      this.cmd_carId_Map.put(34, var12);
      this.cmd_carId_Map.put(35, var12);
      this.cmd_carId_Map.put(40, var12);
      this.cmd_carId_Map.put(41, var12);
      this.cmd_carId_Map.put(65, var12);
      this.cmd_carId_Map.put(80, var12);
      this.cmd_carId_Map.put(81, var12);
      this.cmd_carId_Map.put(82, var12);
      this.cmd_carId_Map.put(127, var12);
      this.cmd_carId_Map.put(129, var12);
      this.cmd_carId_Map.put(144, var12);
      this.cmd_carId_Map.put(131, var12);
      var12 = new ArrayList();
      var12.add(10);
      var12.add(var10);
      this.cmd_carId_Map.put(37, var12);
      this.cmd_carId_Map.put(38, var12);
      this.cmd_carId_Map.put(39, var12);
      this.cmd_carId_Map.put(48, var12);
      this.cmd_carId_Map.put(133, var12);
      this.cmd_carId_Map.put(2215942, var12);
      this.cmd_carId_Map.put(2215952, var12);
      this.cmd_carId_Map.put(33558, var12);
      this.cmd_carId_Map.put(33559, var12);
      this.cmd_carId_Map.put(33560, var12);
      this.cmd_carId_Map.put(33561, var12);
      this.cmd_carId_Map.put(33562, var12);
      this.cmd_carId_Map.put(33563, var12);
      this.cmd_carId_Map.put(10449, var12);
      var12 = new ArrayList();
      var12.add(var11);
      var12.add(var9);
      var12.add(var8);
      var12.add(var2);
      var12.add(var10);
      this.cmd_carId_Map.put(36, var12);
      ArrayList var16 = new ArrayList();
      var16.add(var3);
      var16.add(var4);
      var16.add(var5);
      var16.add(var2);
      var16.add(var7);
      var16.add(var8);
      var16.add(var10);
      this.cmd_carId_Map.put(64, var16);
      this.cmd_carId_Map.put(116, var16);
      this.cmd_carId_Map.put(132, var16);
      this.cmd_carId_Map.put(164, var16);
      ArrayList var15 = new ArrayList();
      var15.add(var6);
      var15.add(var1);
      var15.add(var9);
      var15.add(var8);
      var15.add(var3);
      var15.add(var4);
      var15.add(var5);
      var15.add(var2);
      var15.add(var7);
      this.cmd_carId_Map.put(96, var15);
      this.cmd_carId_Map.put(97, var15);
      this.cmd_carId_Map.put(98, var15);
      this.cmd_carId_Map.put(99, var15);
      this.cmd_carId_Map.put(130, var15);
      this.cmd_carId_Map.put(134, var15);
      ArrayList var14 = new ArrayList();
      var14.add(var3);
      var14.add(var4);
      var14.add(var5);
      var14.add(var2);
      var14.add(var7);
      this.cmd_carId_Map.put(112, var14);
      this.cmd_carId_Map.put(113, var14);
      this.cmd_carId_Map.put(114, var14);
      this.cmd_carId_Map.put(115, var14);
      this.cmd_carId_Map.put(160, var14);
      this.cmd_carId_Map.put(161, var14);
      this.cmd_carId_Map.put(162, var14);
      var14 = new ArrayList();
      var14.add(var8);
      this.cmd_carId_Map.put(49, var14);
      this.cmd_carId_Map.put(135, var14);
      ArrayList var13 = new ArrayList();
      var13.add(var6);
      var13.add(var1);
      var13.add(var3);
      var13.add(var4);
      var13.add(var5);
      var13.add(var2);
      var13.add(var7);
      this.cmd_carId_Map.put(33488, var13);
   }

   private void initOriginalDevice() {
      ArrayList var2 = new ArrayList();
      this.cdPageList = var2;
      var2.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_total_repertoire", (String)null));
      this.cdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_track", (String)null));
      var2 = new ArrayList();
      this.dvdPageList = var2;
      var2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_334_repetition_mode", (String)null));
      this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_334_random_mode", (String)null));
      this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_334_scan_mode", (String)null));
      this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_total_repertoire", (String)null));
      this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_track", (String)null));
      this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_total_time", (String)null));
      this.dvdPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_time", (String)null));
      var2 = new ArrayList();
      this.radioPageList = var2;
      var2.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_band", (String)null));
      this.radioPageList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_334_current_frequency_point", (String)null));
      boolean var1 = ((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(33488))).contains(this.id);
      Integer var3 = 0;
      if (var1) {
         this.pageMap.put(var3, new OriginalDeviceData(this.cdPageList, new String[]{"up", "play", "pause", "down"}, new String[]{"rpt", "rdm", "scan"}));
      } else {
         this.pageMap.put(var3, new OriginalDeviceData(this.cdPageList, new String[]{"prev_disc", "up", "play", "pause", "down", "next_disc"}, new String[]{"rpt", "rdm"}));
      }

      this.pageMap.put(1, new OriginalDeviceData(this.dvdPageList, new String[]{"prev_disc", "left", "play", "pause", "select_cd", "right", "next_disc"}));
      this.pageMap.put(2, new OriginalDeviceData(this.radioPageList, new String[]{"left", "up", "radio_scan", "stop", "down", "right"}, new String[]{"refresh"}));
   }

   // $FF: synthetic method
   static void lambda$new$1(AmplifierActivity.AmplifierPosition var0, int var1) {
      int var2 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var0.ordinal()];
      if (var2 != 1) {
         if (var2 == 2) {
            if (var1 > GeneralAmplifierData.frontRear) {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, 1});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, -127});
            }
         }
      } else if (var1 > GeneralAmplifierData.leftRight) {
         CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, -127});
      }

   }

   // $FF: synthetic method
   static void lambda$new$6(int var0) {
      if (var0 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -121, 1, 1});
      }

   }

   private byte mediumAndHigh(int var1) {
      if (var1 == 0) {
         return 0;
      } else {
         byte var2 = 2;
         if (var1 == 2) {
            var2 = 1;
         }

         return var2;
      }
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

   public Map initSettingPageIndex() {
      List var3 = this.settingPageUiSet.getList();
      Iterator var4 = var3.iterator();

      for(int var1 = 0; var1 < var3.size(); ++var1) {
         SettingPageUiSet.ListBean var5 = (SettingPageUiSet.ListBean)var4.next();
         this.settingPageIndex.put(var5.getTitleSrn(), var1);
         List var6 = var5.getItemList();
         Iterator var7 = var6.iterator();

         for(int var2 = 0; var2 < var6.size(); ++var2) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var7.next();
            this.settingPageIndex.put(var8.getTitleSrn(), var2);
         }
      }

      return this.settingPageIndex;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__334_UiMgr(int var1, int var2, int var3) {
      String var4;
      byte var5;
      byte var6;
      label501: {
         this.leftTitle = ((SettingPageUiSet.ListBean)this.settingPageUiSet.getList().get(var1)).getTitleSrn();
         this.rightTitle = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         Log.i("lyn", "leftIndex: " + this.leftTitle + "  rightIndex: " + this.rightTitle);
         var4 = this.leftTitle;
         var4.hashCode();
         var1 = var4.hashCode();
         var6 = -1;
         switch (var1) {
            case -1811716362:
               if (var4.equals("_334_lighting")) {
                  var5 = 0;
                  break label501;
               }
               break;
            case -1790788200:
               if (var4.equals("_334_other_1")) {
                  var5 = 1;
                  break label501;
               }
               break;
            case -1790788199:
               if (var4.equals("_334_other_2")) {
                  var5 = 2;
                  break label501;
               }
               break;
            case -1790788198:
               if (var4.equals("_334_other_3")) {
                  var5 = 3;
                  break label501;
               }
               break;
            case -1622398617:
               if (var4.equals("_334_turn")) {
                  var5 = 4;
                  break label501;
               }
               break;
            case -364022701:
               if (var4.equals("_334_next_service")) {
                  var5 = 5;
                  break label501;
               }
               break;
            case 149338180:
               if (var4.equals("_334_maintenance_info")) {
                  var5 = 6;
                  break label501;
               }
               break;
            case 548788439:
               if (var4.equals("_334_security_setting_status_info")) {
                  var5 = 7;
                  break label501;
               }
               break;
            case 1769035568:
               if (var4.equals("_334_environmental_lighting_title")) {
                  var5 = 8;
                  break label501;
               }
               break;
            case 1887315661:
               if (var4.equals("_334_hud")) {
                  var5 = 9;
                  break label501;
               }
               break;
            case 1890111602:
               if (var4.equals("_334_door_lock")) {
                  var5 = 10;
                  break label501;
               }
         }

         var5 = -1;
      }

      switch (var5) {
         case 0:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4) {
               case "_334_headlamp_off_timer":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                  return;
               case "_334_adaptive_headlamp":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
                  return;
               case "_334_lights_reminder":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  return;
               case "_334_1_lights_off_automatically":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
                  return;
               case "_334_2_lights_off_automatically":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
                  return;
               case "_334_headlamp_on_auto":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
                  return;
               default:
                  return;
            }
         case 1:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4) {
               case "_334_rain_sensor":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                  return;
               case "_334_rear_window_demister":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
                  return;
               case "_334_language_state":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                  return;
               case "_334_automatic_lock_time":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
                  return;
               case "_334_blind_area_prompt_volume":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                  return;
               default:
                  return;
            }
         case 2:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4) {
               case "_334_synchronized_1":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
                  return;
               case "_334_Instrument_custom_display":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)var3});
                  return;
               case "_334_notification_warning":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
                  return;
               case "_334_rear_vision_mirror":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var3});
                  return;
               default:
                  return;
            }
         case 3:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4) {
               case "_334_leaving_home_light":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, (byte)var3});
                  return;
               case "_334_temperature_unit":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -125, (byte)var3});
                  return;
               case "_334_day_light":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -123, (byte)var3});
                  return;
               case "_334_distance_unit":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -126, (byte)var3});
                  return;
               case "_334_high_beam_control":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -124, (byte)var3});
                  return;
               case "_334_coming_home_light":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -127, (byte)var3});
                  return;
               default:
                  return;
            }
         case 4:
            var4 = this.rightTitle;
            var4.hashCode();
            if (!var4.equals("_334_turn_signal_volume")) {
               if (var4.equals("_334_turn_signal")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, this.mediumAndHigh(var3)});
            }
            break;
         case 5:
            var4 = this.rightTitle;
            var4.hashCode();
            if (var4.equals("_334_manual_auto")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)var3});
            }
            break;
         case 6:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4) {
               case "_334_scheduled_maintenance_switch":
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, 1, (byte)var3});
                  return;
               case "_334_oil_change_switch":
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, 1, (byte)var3});
                  return;
               case "_334_tire_rotation_switch":
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, 1, (byte)var3});
                  return;
               default:
                  return;
            }
         case 7:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4.hashCode()) {
               case -2143450579:
                  if (!var4.equals("_334_rear_vehicle_monitoring_volume")) {
                     var5 = var6;
                  } else {
                     var5 = 0;
                  }
                  break;
               case -2076794739:
                  if (!var4.equals("_334_bind_spot_monitoring")) {
                     var5 = var6;
                  } else {
                     var5 = 1;
                  }
                  break;
               case -1907409959:
                  if (!var4.equals("_334_lane_departure_warning_system_voice")) {
                     var5 = var6;
                  } else {
                     var5 = 2;
                  }
                  break;
               case -1873514735:
                  if (!var4.equals("_334_blind_spot_monitoring")) {
                     var5 = var6;
                  } else {
                     var5 = 3;
                  }
                  break;
               case -1851536127:
                  if (!var4.equals("_334_security_alert_intersection_traffic_ahead")) {
                     var5 = var6;
                  } else {
                     var5 = 4;
                  }
                  break;
               case -1788526155:
                  if (!var4.equals("_334_rear_intersection_traffic_alert")) {
                     var5 = var6;
                  } else {
                     var5 = 5;
                  }
                  break;
               case -1691530506:
                  if (!var4.equals("_334_security_alert_lane_departure_warning")) {
                     var5 = var6;
                  } else {
                     var5 = 6;
                  }
                  break;
               case -1601652345:
                  if (!var4.equals("_334_warning_volume")) {
                     var5 = var6;
                  } else {
                     var5 = 7;
                  }
                  break;
               case -1450418466:
                  if (!var4.equals("_334_warning_threshold")) {
                     var5 = var6;
                  } else {
                     var5 = 8;
                  }
                  break;
               case -1440422119:
                  if (!var4.equals("_334_accident_avoidance_alarm_timing")) {
                     var5 = var6;
                  } else {
                     var5 = 9;
                  }
                  break;
               case -916931840:
                  if (!var4.equals("_334_rear_parking_alarm_volume")) {
                     var5 = var6;
                  } else {
                     var5 = 10;
                  }
                  break;
               case -900113033:
                  if (!var4.equals("_334_smart_city_brake")) {
                     var5 = var6;
                  } else {
                     var5 = 11;
                  }
                  break;
               case -836523008:
                  if (!var4.equals("_334_mazda_radar")) {
                     var5 = var6;
                  } else {
                     var5 = 12;
                  }
                  break;
               case -753575971:
                  if (!var4.equals("_334_distance_recognition_distance")) {
                     var5 = var6;
                  } else {
                     var5 = 13;
                  }
                  break;
               case -679400821:
                  if (!var4.equals("_334_parking_sensor_display_guide")) {
                     var5 = var6;
                  } else {
                     var5 = 14;
                  }
                  break;
               case -631467171:
                  if (!var4.equals("_334_sbs_display")) {
                     var5 = var6;
                  } else {
                     var5 = 15;
                  }
                  break;
               case 136077105:
                  if (!var4.equals("_334_pedal_misuse_alarm")) {
                     var5 = var6;
                  } else {
                     var5 = 16;
                  }
                  break;
               case 396018402:
                  if (!var4.equals("_334_smart_city_brake_volume")) {
                     var5 = var6;
                  } else {
                     var5 = 17;
                  }
                  break;
               case 420684362:
                  if (!var4.equals("_334_cruise_operation_tone")) {
                     var5 = var6;
                  } else {
                     var5 = 18;
                  }
                  break;
               case 442902262:
                  if (!var4.equals("_334_speed_limit_warning")) {
                     var5 = var6;
                  } else {
                     var5 = 19;
                  }
                  break;
               case 479827389:
                  if (!var4.equals("_334_smart_city_brake_distance")) {
                     var5 = var6;
                  } else {
                     var5 = 20;
                  }
                  break;
               case 584090850:
                  if (!var4.equals("_334_alarm_timing")) {
                     var5 = var6;
                  } else {
                     var5 = 21;
                  }
                  break;
               case 606350374:
                  if (!var4.equals("_334_lane_departure_warning_system")) {
                     var5 = var6;
                  } else {
                     var5 = 22;
                  }
                  break;
               case 769689414:
                  if (!var4.equals("_334_lane_departure_warning_system_time")) {
                     var5 = var6;
                  } else {
                     var5 = 23;
                  }
                  break;
               case 846032998:
                  if (!var4.equals("_334_front_parking_sensor_alarm_volume")) {
                     var5 = var6;
                  } else {
                     var5 = 24;
                  }
                  break;
               case 890976458:
                  if (!var4.equals("_334_lane_departure_warning_system_rumble")) {
                     var5 = var6;
                  } else {
                     var5 = 25;
                  }
                  break;
               case 926615990:
                  if (!var4.equals("_334_active_safety_assistance")) {
                     var5 = var6;
                  } else {
                     var5 = 26;
                  }
                  break;
               case 944097439:
                  if (!var4.equals("_334_dynamic_route")) {
                     var5 = var6;
                  } else {
                     var5 = 27;
                  }
                  break;
               case 990220316:
                  if (!var4.equals("_334_speed_limit_display")) {
                     var5 = var6;
                  } else {
                     var5 = 28;
                  }
                  break;
               case 1193028895:
                  if (!var4.equals("_334_sbs_volume")) {
                     var5 = var6;
                  } else {
                     var5 = 29;
                  }
                  break;
               case 1328630207:
                  if (!var4.equals("_334_front_camera_view")) {
                     var5 = var6;
                  } else {
                     var5 = 30;
                  }
                  break;
               case 1425407107:
                  if (!var4.equals("_334_lane_departure_warning_system_warning")) {
                     var5 = var6;
                  } else {
                     var5 = 31;
                  }
                  break;
               case 1638150106:
                  if (!var4.equals("_334_distance_recognition_display")) {
                     var5 = var6;
                  } else {
                     var5 = 32;
                  }
                  break;
               case 1902732474:
                  if (!var4.equals("_334_sbs_distance")) {
                     var5 = var6;
                  } else {
                     var5 = 33;
                  }
                  break;
               case 1940285906:
                  if (!var4.equals("_334_alarm_type")) {
                     var5 = var6;
                  } else {
                     var5 = 34;
                  }
                  break;
               case 1963815593:
                  if (!var4.equals("_334_lane_departure_warning_system_intervene")) {
                     var5 = var6;
                  } else {
                     var5 = 35;
                  }
                  break;
               case 2107320296:
                  if (!var4.equals("_334_auto_display_view")) {
                     var5 = var6;
                  } else {
                     var5 = 36;
                  }
                  break;
               default:
                  var5 = var6;
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 2, (byte)(var3 + 1)});
                  return;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 2, (byte)(var3 + 1)});
                  return;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 6, 3, (byte)(var3 + 1)});
                  return;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 5, 1, (byte)(var3 + 1)});
                  return;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 5, (byte)(var3 + 1)});
                  return;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 1, (byte)(var3 + 1)});
                  return;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 7, (byte)(var3 + 1)});
                  return;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 5, 2, (byte)(var3 + 1)});
                  return;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 7, 3, (byte)(var3 + 1)});
                  return;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 14, 1, (byte)(var3 + 1)});
                  return;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 9, 2, (byte)(var3 + 1)});
                  return;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 1, (byte)(var3 + 1)});
                  return;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 8, 2, (byte)(var3 + 1)});
                  return;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 3, 2, (byte)(var3 + 1)});
                  return;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 9, 1, (byte)(var3 + 1)});
                  return;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 4, 1, (byte)(var3 + 1)});
                  return;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 10, (byte)(var3 + 1)});
                  return;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 1, (byte)(var3 + 33)});
                  return;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 8, 1, (byte)(var3 + 1)});
                  return;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 7, 2, (byte)(var3 + 1)});
                  return;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 1, (byte)(var3 + 17)});
                  return;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 3, (byte)(var3 + 1)});
                  return;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 6, 7, (byte)(var3 + 1)});
                  return;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 6, 1, (byte)(var3 + 1)});
                  return;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 9, 3, (byte)(var3 + 1)});
                  return;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 6, 4, (byte)(var3 + 1)});
                  return;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 13, 1, (byte)(var3 + 1)});
                  return;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 12, 2, (byte)(var3 + 1)});
                  return;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 7, 1, (byte)(var3 + 1)});
                  return;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 4, 3, (byte)(var3 + 1)});
                  return;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 12, 3, (byte)(var3 + 1)});
                  return;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 6, 2, (byte)(var3 + 1)});
                  return;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 3, 1, (byte)(var3 + 1)});
                  return;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 4, 2, (byte)(var3 + 1)});
                  return;
               case 34:
                  if (var3 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 6, 2});
                     return;
                  } else {
                     if (var3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 6, 1});
                     } else if (var3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -92, 11, 6, 3});
                        return;
                     }

                     return;
                  }
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 6, 6, (byte)(var3 + 1)});
                  return;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, -92, 12, 1, (byte)(var3 + 1)});
                  return;
               default:
                  return;
            }
         case 8:
            var4 = this.rightTitle;
            var4.hashCode();
            if (var4.equals("_334_environmental_lighting_item")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -122, (byte)var3});
            }
            break;
         case 9:
            var4 = this.rightTitle;
            var4.hashCode();
            if (!var4.equals("_334_hud_brightness_control")) {
               if (var4.equals("_334_hud_active_display")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var3});
            }
            break;
         case 10:
            var4 = this.rightTitle;
            var4.hashCode();
            switch (var4) {
               case "_334_lock_leave_car":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
                  break;
               case "_334_unlock_mode":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
                  break;
               case "_334_tail_door":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)(var3 + 1)});
                  break;
               case "_334_auto_re_lock":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
                  break;
               case "_334_volume":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
                  break;
               case "_334_auto_lock":
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
            }
      }

   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__334_UiMgr(AmplifierActivity.AmplifierBand var1, int var2) {
      int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 == 4) {
                  var3 = var2 - GeneralAmplifierData.custom2Bass;
                  if (var2 > GeneralAmplifierData.custom2Bass) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 5, 1, (byte)var3});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 5, 1, (byte)this.getDownValue(-var3)});
                  }
               }
            } else {
               var3 = var2 - GeneralAmplifierData.bandBass;
               if (var2 > GeneralAmplifierData.bandBass) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte)var3});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte)this.getDownValue(-var3)});
               }
            }
         } else {
            var3 = var2 - GeneralAmplifierData.bandTreble;
            if (var2 > GeneralAmplifierData.bandTreble) {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte)var3});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte)this.getDownValue(-var3)});
            }
         }
      } else {
         var3 = var2 - GeneralAmplifierData.volume;
         if (var2 > GeneralAmplifierData.volume) {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte)var3});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte)this.getDownValue(-var3)});
         }
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__334_UiMgr(Context var1, int var2) {
      HashMap var5 = this.cmd_carId_Map;
      Integer var4 = 96;
      boolean var3 = ((List)Objects.requireNonNull((List)var5.get(var4))).contains(this.id);
      Integer var6 = 112;
      if (var3 && !((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(var6))).contains(this.id)) {
         this.msgMgr.setCdPage(var1);
      } else if (((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(var4))).contains(this.id) && ((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(var6))).contains(this.id)) {
         this.msgMgr.setRadioPage(var1);
      } else {
         this.msgMgr.setRadioPage(var1);
      }

   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__334_UiMgr(int var1) {
      if (this.msgMgr.pageFlag.equals("CD")) {
         if (((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(33488))).contains(this.msgMgr.currentCanDifferentId)) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     if (!GeneralOriginalCarDeviceData.scan) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -119});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -118});
                     }
                  }
               } else if (!GeneralOriginalCarDeviceData.rdm) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -123});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -122});
               }
            } else if (!GeneralOriginalCarDeviceData.rpt) {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, -121});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, -120});
            }
         } else if (var1 != 0) {
            if (var1 == 1) {
               if (!GeneralOriginalCarDeviceData.rdm) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
               }
            }
         } else if (!GeneralOriginalCarDeviceData.rpt) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
         }
      } else if (this.msgMgr.pageFlag.equals("Radio") && var1 == 0) {
         if (!GeneralOriginalCarDeviceData.refresh) {
            CanbusMsgSender.sendMsg(new byte[]{22, -94, 1});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -94, 0});
         }
      }

   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__334_UiMgr(int var1) {
      if (this.msgMgr.pageFlag.equals("CD")) {
         if (((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(33488))).contains(this.msgMgr.currentCanDifferentId)) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -124});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, -127});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -128});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, -125});
            }
         } else if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
         }
      } else if (this.msgMgr.pageFlag.equals("Radio")) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -94, 9});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -94, 6});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -94, 3});
                     CanbusMsgSender.sendMsg(new byte[]{22, -94, 5});
                     CanbusMsgSender.sendMsg(new byte[]{22, -94, 7});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -94, 2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -94, 4});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -94, 8});
         }
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.id != 11) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_334_environmental_lighting_title"});
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(38))).contains(this.id)) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_334_next_service"});
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(49))).contains(this.id)) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_334_maintenance_info"});
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(116))).contains(this.id)) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_334_security_setting_status_info"});
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(64))).contains(this.id)) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_334_hud"});
      }

      if (this.id != 12) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_334_tail_door"});
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(38))).contains(this.id)) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_334_rear_window_demister"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_334_automatic_lock_time"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_334_rear_vision_mirror"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_334_notification_warning"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_334_Instrument_custom_display"});
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(38))).contains(this.id)) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(65))).contains(this.id)) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(96))).contains(this.id) && !((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(112))).contains(this.id)) {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(112))).contains(this.id)) {
         this.removeMainPrjBtnByName(var1, "amplifier");
      }

      int var2 = this.id;
      if (var2 == 4 || var2 == 11) {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(38))).contains(this.id)) {
         this.removeDriveData(var1, "_334_update_time");
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(65))).contains(this.id)) {
         this.removeDriveData(var1, "_334_fuel_info_1");
         this.removeDriveData(var1, "_334_fuel_info_9");
      }

      if (!((List)Objects.requireNonNull((List)this.cmd_carId_Map.get(82))).contains(this.id)) {
         this.removeDriveData(var1, "_334_i_stop_information");
      }

   }
}
