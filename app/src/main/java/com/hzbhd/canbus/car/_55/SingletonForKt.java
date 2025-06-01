package com.hzbhd.canbus.car._55;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0005J&\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006J\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010 \u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010#\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010$\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010%\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010'\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006("},
   d2 = {"Lcom/hzbhd/canbus/car/_55/SingletonForKt;", "", "()V", "carMap", "", "", "", "mMsgMgr", "Lcom/hzbhd/canbus/car/_55/MsgMgr;", "getMMsgMgr", "()Lcom/hzbhd/canbus/car/_55/MsgMgr;", "setMMsgMgr", "(Lcom/hzbhd/canbus/car/_55/MsgMgr;)V", "init", "", "context", "Landroid/content/Context;", "uiMgr", "Lcom/hzbhd/canbus/car/_55/UiMgr;", "sendAirBtnData", "btn", "sendSettingsData", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "leftPos", "rightPos", "value", "setAmplifierData", "data", "", "setDrivingAssistance", "setElectricDoor", "setHistoryOilConsumptionData", "setLightingData", "setLockData", "setOilConsumptionData", "setOriginalCarData", "setOther2Data", "setOtherData", "setRemoteControlData", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SingletonForKt {
   public static final SingletonForKt INSTANCE = new SingletonForKt();
   private static final Map carMap = MapsKt.mapOf(new Pair[]{TuplesKt.to("S55_Car_1", 26), TuplesKt.to("S55_Car_2", 27), TuplesKt.to("S55_Car_3", 30), TuplesKt.to("S55_Car_4", 32), TuplesKt.to("S55_Car_5", 33), TuplesKt.to("S55_Car_6", 29), TuplesKt.to("S55_Car_7", 31), TuplesKt.to("S55_Car_8", 34), TuplesKt.to("S55_Car_9", 35), TuplesKt.to("S55_Car_10", 36), TuplesKt.to("S55_Car_11", 37), TuplesKt.to("S55_Car_12", 38), TuplesKt.to("S55_Car_13", 39), TuplesKt.to("S55_Car_14", 40), TuplesKt.to("S55_Car_15", 41), TuplesKt.to("S55_Car_16", 42), TuplesKt.to("S55_Car_17", 43), TuplesKt.to("S55_Car_18", 44), TuplesKt.to("S55_Car_19", 45), TuplesKt.to("S55_Car_20", 46), TuplesKt.to("S55_Car_21", 47), TuplesKt.to("S55_Car_22", 48), TuplesKt.to("S55_Car_23", 1)});
   public static MsgMgr mMsgMgr;

   private SingletonForKt() {
   }

   private static final void sendAirBtnData$sendAirBtnCmd(int var0) {
      byte var1 = (byte)var0;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
   }

   private static final void sendSettingsData$sendAmplifierData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendDrivingAssistanceData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendElectricDoorData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 122, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendLanguageData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendLightingCarData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendLockData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendOriginalCarData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendOtherData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var0, (byte)var1});
   }

   private static final void sendSettingsData$sendRemoteControlCarData(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte)var0, (byte)var1});
   }

   private static final String setOilConsumptionData$unit1(int var0) {
      String var1;
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 != 2) {
               var1 = " --";
            } else {
               var1 = " L/100Km";
            }
         } else {
            var1 = " Km/L";
         }
      } else {
         var1 = " MPG";
      }

      return var1;
   }

   private static final String setOilConsumptionData$unit2(boolean var0) {
      String var1;
      if (var0) {
         var1 = " Mile";
      } else {
         var1 = " Km";
      }

      return var1;
   }

   public final MsgMgr getMMsgMgr() {
      MsgMgr var1 = mMsgMgr;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mMsgMgr");
         return null;
      }
   }

   public final void init(Context var1, UiMgr var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "uiMgr");
      MsgMgrInterface var3 = MsgMgrFactory.getCanMsgMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.MsgMgr");
      this.setMMsgMgr((MsgMgr)var3);
      AbstractUiMgr var4 = (AbstractUiMgr)var2;
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, var4, (HashMap)null, 4, (Object)null);
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, var4, (HashMap)null, 4, (Object)null);
   }

   public final void sendAirBtnData(String var1) {
      Intrinsics.checkNotNullParameter(var1, "btn");
      switch (var1) {
         case "front_defog":
            sendAirBtnData$sendAirBtnCmd(5);
            break;
         case "rear_defog":
            sendAirBtnData$sendAirBtnCmd(6);
            break;
         case "ac":
            if (!GeneralAirData.ac) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
            }
            break;
         case "auto":
            sendAirBtnData$sendAirBtnCmd(4);
            break;
         case "sync":
            sendAirBtnData$sendAirBtnCmd(3);
            break;
         case "power":
            sendAirBtnData$sendAirBtnCmd(1);
            break;
         case "in_out_cycle":
            sendAirBtnData$sendAirBtnCmd(7);
      }

   }

   public final void sendSettingsData(SettingPageUiSet var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "settingPageUiSet");
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (var5 != null) {
         var2 = var5.hashCode();
         switch (var2) {
            case -1862005303:
               if (var5.equals("S55_Lock_1")) {
                  sendSettingsData$sendLockData(5, var4);
               }
               break;
            case -1862005302:
               if (var5.equals("S55_Lock_2")) {
                  sendSettingsData$sendLockData(4, var4);
               }
               break;
            case -1862005301:
               if (var5.equals("S55_Lock_3")) {
                  sendSettingsData$sendLockData(3, var4);
               }
               break;
            case -1862005300:
               if (var5.equals("S55_Lock_4")) {
                  sendSettingsData$sendLockData(2, var4 + 1);
               }
               break;
            case -1862005299:
               if (var5.equals("S55_Lock_5")) {
                  sendSettingsData$sendLockData(1, var4);
               }
               break;
            case -1862005298:
               if (var5.equals("S55_Lock_6")) {
                  sendSettingsData$sendLockData(6, var4);
               }
               break;
            default:
               SettingPageUiSet.ListBean.ItemListBean var7;
               switch (var2) {
                  case -1638072122:
                     if (var5.equals("S55_Tire_1")) {
                        var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Tire_1");
                        if (var7 != null) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte)var4});
                           var7.setValue(var4);
                        }

                        MsgMgrInterface var8 = MsgMgrFactory.getCanMsgMgr(InitUtilsKt.getMContext());
                        Intrinsics.checkNotNull(var8, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.MsgMgr");
                        ((MsgMgr)var8).updateSettingActivity((Bundle)null);
                     }
                     break;
                  case -1466512577:
                     if (var5.equals("S55_Other1_1")) {
                        sendSettingsData$sendOtherData(16, var4);
                     }
                     break;
                  case -1466512576:
                     if (var5.equals("S55_Other1_2")) {
                        sendSettingsData$sendOtherData(15, var4);
                     }
                     break;
                  case -1466512575:
                     if (var5.equals("S55_Other1_3")) {
                        sendSettingsData$sendOtherData(14, var4);
                     }
                     break;
                  case -1466512574:
                     if (var5.equals("S55_Other1_4")) {
                        sendSettingsData$sendOtherData(11, var4);
                     }
                     break;
                  case -1466512573:
                     if (var5.equals("S55_Other1_5")) {
                        sendSettingsData$sendOtherData(12, var4);
                     }
                     break;
                  case -1466512572:
                     if (var5.equals("S55_Other1_6")) {
                        sendSettingsData$sendOtherData(18, var4);
                     }
                     break;
                  case -1466512571:
                     if (var5.equals("S55_Other1_7")) {
                        sendSettingsData$sendOtherData(20, var4);
                     }
                     break;
                  case -1466512570:
                     if (var5.equals("S55_Other1_8")) {
                        sendSettingsData$sendOtherData(17, var4);
                     }
                     break;
                  case -1466512569:
                     if (var5.equals("S55_Other1_9")) {
                        sendSettingsData$sendOtherData(19, var4);
                     }
                     break;
                  case 102584022:
                     if (var5.equals("language_setup")) {
                        sendSettingsData$sendLanguageData(1, var4 + 1);
                        var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("language_setup");
                        if (var7 != null) {
                           var7.setValue(var7.getValueSrnArray().get(var4));
                        }
                     }
                     break;
                  case 748741536:
                     if (var5.equals("S18_Car_0")) {
                        SettingPageUiSet.ListBean.ItemListBean var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S18_Car_0");
                        if (var10 != null) {
                           Integer var6 = (Integer)carMap.get(var10.getValueSrnArray().get(var4));
                           if (var6 == null) {
                              return;
                           }

                           CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)var6, 2});
                           var10.setValue(var10.getValueSrnArray().get(var4));
                        }
                     }
                     break;
                  case 1782780208:
                     if (var5.equals("S55_Other2_10")) {
                        sendSettingsData$sendOtherData(2, var4);
                     }
                     break;
                  case 1782780209:
                     if (var5.equals("S55_Other2_11")) {
                        sendSettingsData$sendOtherData(1, var4 + 4);
                     }
                     break;
                  default:
                     switch (var2) {
                        case -1466511616:
                           if (var5.equals("S55_Other2_1")) {
                              sendSettingsData$sendOtherData(10, var4);
                           }
                           break;
                        case -1466511615:
                           if (var5.equals("S55_Other2_2")) {
                              sendSettingsData$sendOtherData(9, var4);
                           }
                           break;
                        case -1466511614:
                           if (var5.equals("S55_Other2_3")) {
                              sendSettingsData$sendOtherData(6, var4);
                           }
                           break;
                        case -1466511613:
                           if (var5.equals("S55_Other2_4")) {
                              sendSettingsData$sendOtherData(7, var4);
                           }
                           break;
                        case -1466511612:
                           if (var5.equals("S55_Other2_5")) {
                              sendSettingsData$sendOtherData(8, var4);
                           }
                           break;
                        case -1466511611:
                           if (var5.equals("S55_Other2_6")) {
                              sendSettingsData$sendOtherData(5, var4);
                           }
                           break;
                        case -1466511610:
                           if (var5.equals("S55_Other2_7")) {
                              sendSettingsData$sendOtherData(4, var4);
                           }
                           break;
                        case -1466511609:
                           if (var5.equals("S55_Other2_8")) {
                              sendSettingsData$sendOtherData(13, var4);
                           }
                           break;
                        case -1466511608:
                           if (var5.equals("S55_Other2_9")) {
                              sendSettingsData$sendOtherData(3, var4);
                           }
                           break;
                        default:
                           switch (var2) {
                              case -949707653:
                                 if (var5.equals("S55_OriginalCarScreen_10")) {
                                    sendSettingsData$sendOriginalCarData(14, var4);
                                 }
                                 break;
                              case -949707652:
                                 if (var5.equals("S55_OriginalCarScreen_11")) {
                                    sendSettingsData$sendOriginalCarData(17, var4);
                                 }
                                 break;
                              case -949707651:
                                 if (var5.equals("S55_OriginalCarScreen_12")) {
                                    sendSettingsData$sendOriginalCarData(16, var4);
                                 }
                                 break;
                              case -949707650:
                                 if (var5.equals("S55_OriginalCarScreen_13")) {
                                    sendSettingsData$sendOriginalCarData(13, var4 + 6);
                                 }
                                 break;
                              case -949707649:
                                 if (var5.equals("S55_OriginalCarScreen_14")) {
                                    sendSettingsData$sendOriginalCarData(13, var4 + 4);
                                 }
                                 break;
                              case -949707648:
                                 if (var5.equals("S55_OriginalCarScreen_15")) {
                                    sendSettingsData$sendOriginalCarData(13, var4 + 2);
                                 }
                                 break;
                              case -949707647:
                                 if (var5.equals("S55_OriginalCarScreen_16")) {
                                    sendSettingsData$sendOriginalCarData(13, var4);
                                 }
                                 break;
                              default:
                                 switch (var2) {
                                    case -908645891:
                                       if (var5.equals("S55_RemoteControl_1")) {
                                          sendSettingsData$sendRemoteControlCarData(4, var4);
                                       }
                                       break;
                                    case -908645890:
                                       if (var5.equals("S55_RemoteControl_2")) {
                                          sendSettingsData$sendRemoteControlCarData(3, var4);
                                       }
                                       break;
                                    case -908645889:
                                       if (var5.equals("S55_RemoteControl_3")) {
                                          sendSettingsData$sendRemoteControlCarData(2, var4);
                                       }
                                       break;
                                    case -908645888:
                                       if (var5.equals("S55_RemoteControl_4")) {
                                          sendSettingsData$sendRemoteControlCarData(1, var4);
                                       }
                                       break;
                                    default:
                                       switch (var2) {
                                          case -634715383:
                                             if (var5.equals("S55_ElectricDoor_1")) {
                                                sendSettingsData$sendElectricDoorData(2, var4);
                                             }
                                             break;
                                          case -634715382:
                                             if (var5.equals("S55_ElectricDoor_2")) {
                                                sendSettingsData$sendElectricDoorData(1, var4);
                                             }
                                             break;
                                          case -634715381:
                                             if (var5.equals("S55_ElectricDoor_3")) {
                                                sendSettingsData$sendElectricDoorData(4, var4);
                                             }
                                             break;
                                          case -634715380:
                                             if (var5.equals("S55_ElectricDoor_4")) {
                                                sendSettingsData$sendElectricDoorData(3, var4);
                                             }
                                             break;
                                          default:
                                             switch (var2) {
                                                case -543432342:
                                                   if (var5.equals("S55_Lighting_1")) {
                                                      sendSettingsData$sendLightingCarData(5, var4);
                                                   }
                                                   break;
                                                case -543432341:
                                                   if (var5.equals("S55_Lighting_2")) {
                                                      sendSettingsData$sendLightingCarData(4, var4);
                                                   }
                                                   break;
                                                case -543432340:
                                                   if (var5.equals("S55_Lighting_3")) {
                                                      sendSettingsData$sendLightingCarData(3, var4);
                                                   }
                                                   break;
                                                case -543432339:
                                                   if (var5.equals("S55_Lighting_4")) {
                                                      sendSettingsData$sendLightingCarData(2, var4);
                                                   }
                                                   break;
                                                case -543432338:
                                                   if (var5.equals("S55_Lighting_5")) {
                                                      sendSettingsData$sendLightingCarData(1, var4 + 1);
                                                   }
                                                   break;
                                                default:
                                                   switch (var2) {
                                                      case 164943287:
                                                         if (var5.equals("S55_Amplifier_1")) {
                                                            sendSettingsData$sendAmplifierData(7, var4);
                                                         }
                                                         break;
                                                      case 164943288:
                                                         if (var5.equals("S55_Amplifier_2")) {
                                                            sendSettingsData$sendAmplifierData(8, var4);
                                                         }
                                                         break;
                                                      default:
                                                         switch (var2) {
                                                            case 1120198181:
                                                               if (var5.equals("S55_DrivingAssistance_1")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(9, var4 + 1);
                                                               }
                                                               break;
                                                            case 1120198182:
                                                               if (var5.equals("S55_DrivingAssistance_2")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(8, var4);
                                                               }
                                                               break;
                                                            case 1120198183:
                                                               if (var5.equals("S55_DrivingAssistance_3")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(7, var4);
                                                               }
                                                               break;
                                                            case 1120198184:
                                                               if (var5.equals("S55_DrivingAssistance_4")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(4, var4 + 1);
                                                               }
                                                               break;
                                                            case 1120198185:
                                                               if (var5.equals("S55_DrivingAssistance_5")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(3, var4);
                                                               }
                                                               break;
                                                            case 1120198186:
                                                               if (var5.equals("S55_DrivingAssistance_6")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(2, var4);
                                                               }
                                                               break;
                                                            case 1120198187:
                                                               if (var5.equals("S55_DrivingAssistance_7")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(1, var4);
                                                               }
                                                               break;
                                                            case 1120198188:
                                                               if (var5.equals("S55_DrivingAssistance_8")) {
                                                                  sendSettingsData$sendDrivingAssistanceData(10, var4);
                                                               }
                                                               break;
                                                            default:
                                                               String var9;
                                                               switch (var2) {
                                                                  case 1354837589:
                                                                     if (var5.equals("S55_OriginalCarScreen_1")) {
                                                                        sendSettingsData$sendOriginalCarData(21, var4);
                                                                     }

                                                                     return;
                                                                  case 1354837590:
                                                                     if (var5.equals("S55_OriginalCarScreen_2")) {
                                                                        sendSettingsData$sendOriginalCarData(20, var4);
                                                                     }

                                                                     return;
                                                                  case 1354837591:
                                                                     if (var5.equals("S55_OriginalCarScreen_3")) {
                                                                        sendSettingsData$sendOriginalCarData(19, var4);
                                                                     }

                                                                     return;
                                                                  case 1354837592:
                                                                     if (var5.equals("S55_OriginalCarScreen_4")) {
                                                                        sendSettingsData$sendOriginalCarData(18, var4);
                                                                     }

                                                                     return;
                                                                  case 1354837593:
                                                                     var9 = "S55_OriginalCarScreen_5";
                                                                     break;
                                                                  case 1354837594:
                                                                     if (var5.equals("S55_OriginalCarScreen_6")) {
                                                                        sendSettingsData$sendOriginalCarData(7, var4);
                                                                     }

                                                                     return;
                                                                  case 1354837595:
                                                                     if (var5.equals("S55_OriginalCarScreen_7")) {
                                                                        sendSettingsData$sendOriginalCarData(12, var4 + 4);
                                                                     }

                                                                     return;
                                                                  case 1354837596:
                                                                     if (var5.equals("S55_OriginalCarScreen_8")) {
                                                                        sendSettingsData$sendOriginalCarData(12, var4);
                                                                     }

                                                                     return;
                                                                  case 1354837597:
                                                                     var9 = "S55_OriginalCarScreen_9";
                                                                     break;
                                                                  default:
                                                                     return;
                                                               }

                                                               var5.equals(var9);
                                                         }
                                                   }
                                             }
                                       }
                                 }
                           }
                     }
               }
         }
      }

   }

   public final void setAmplifierData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[8], 1, 2);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 1);
      SettingPageUiSet.ListBean.ItemListBean var4 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Amplifier_1");
      if (var4 != null) {
         var4.setValue(var4.getValueSrnArray().get(var3));
      }

      var4 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Amplifier_2");
      if (var4 != null) {
         var4.setValue(var2);
      }

   }

   public final void setDrivingAssistance(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[2], 1, 3) - 1;
      int var7 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(var1[3], 6, 2);
      int var9 = DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 2, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 2);
      SettingPageUiSet.ListBean.ItemListBean var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_1");
      String var11;
      if (var10 != null) {
         if (var3 == -1) {
            var11 = "S55_DrivingAssistance_1_0";
         } else {
            var11 = (String)var10.getValueSrnArray().get(var3);
         }

         var10.setValue(var11);
      }

      SettingPageUiSet.ListBean.ItemListBean var12 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_2");
      if (var12 != null) {
         var12.setValue(var7);
      }

      var12 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_3");
      if (var12 != null) {
         var12.setValue(var12.getValueSrnArray().get(var8));
      }

      var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_4");
      if (var10 != null) {
         if (var9 == 0) {
            var11 = "S55_DrivingAssistance_4_0";
         } else {
            var11 = (String)var10.getValueSrnArray().get(var9 - 1);
         }

         var10.setValue(var11);
      }

      var12 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_5");
      if (var12 != null) {
         var12.setValue(var5);
      }

      var12 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_6");
      if (var12 != null) {
         var12.setValue(var2);
      }

      var12 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_7");
      if (var12 != null) {
         var12.setValue(var12.getValueSrnArray().get(var6));
      }

      var12 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_8");
      if (var12 != null) {
         var12.setValue(var12.getValueSrnArray().get(var4));
      }

   }

   public final void setElectricDoor(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[3], 2, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[3], 1, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 1);
      SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_3");
      if (var6 != null) {
         var6.setValue(var2);
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_4");
      if (var6 != null) {
         var6.setValue(var4);
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_1");
      if (var6 != null) {
         var6.setValue(var3);
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_2");
      if (var6 != null) {
         var6.setValue(var6.getValueSrnArray().get(var5));
      }

   }

   public final void setHistoryOilConsumptionData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_1");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(var1[5], var1[6]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_2");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(var1[10], var1[11]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_3");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)DataHandleUtils.getMsbLsbResult(var1[15], var1[16]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_4");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)MsgMgrKt.getAnotherMsbLsbResult(var1[2], var1[3], var1[4]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_5");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)MsgMgrKt.getAnotherMsbLsbResult(var1[7], var1[8], var1[9]) / 10.0F));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_6");
      if (var2 != null) {
         var2.setValue(String.valueOf((float)MsgMgrKt.getAnotherMsbLsbResult(var1[12], var1[13], var1[14]) / 10.0F));
      }

   }

   public final void setLightingData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[2], 3, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 3);
      int var6 = DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 3);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[3], 2, 2);
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 2);
      SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_1");
      if (var8 != null) {
         var8.setValue(var5);
      }

      var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_2");
      if (var8 != null) {
         var8.setValue(var8.getValueSrnArray().get(var2));
      }

      var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_3");
      if (var8 != null) {
         var8.setValue(var8.getValueSrnArray().get(var6));
      }

      var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_4");
      if (var8 != null) {
         var8.setValue(var8.getValueSrnArray().get(var3));
      }

      SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_5");
      if (var7 != null) {
         String var9;
         if (var4 == 0) {
            var9 = "S55_DrivingAssistance_4_0";
         } else {
            var9 = (String)var7.getValueSrnArray().get(var4 - 1);
         }

         var7.setValue(var9);
      }

   }

   public final void setLockData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[3], 6, 2);
      int var7 = DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(var1[3], 1, 2) - 1;
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 1);
      SettingPageUiSet.ListBean.ItemListBean var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lock_1");
      if (var9 != null) {
         var9.setValue(var9.getValueSrnArray().get(var5));
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lock_2");
      if (var9 != null) {
         var9.setValue(var9.getValueSrnArray().get(var7));
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lock_3");
      if (var9 != null) {
         var9.setValue(var3);
      }

      SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lock_4");
      if (var8 != null) {
         String var10;
         if (var6 == -1) {
            var10 = "S55_Lock_4_1";
         } else {
            var10 = (String)var8.getValueSrnArray().get(var6);
         }

         var8.setValue(var10);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lock_5");
      if (var9 != null) {
         var9.setValue(var2);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Lock_6");
      if (var9 != null) {
         var9.setValue(var4);
      }

   }

   public final void setMMsgMgr(MsgMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      mMsgMgr = var1;
   }

   public final void setOilConsumptionData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_1");
      if (var2 != null) {
         var2.setValue("Level " + var1[2]);
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_1");
      if (var2 != null) {
         var2.setValue((float)DataHandleUtils.getMsbLsbResult(var1[3], var1[4]) / 10.0F + setOilConsumptionData$unit1(DataHandleUtils.getIntFromByteWithBit(var1[14], 2, 2)));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_2");
      if (var2 != null) {
         var2.setValue((float)DataHandleUtils.getMsbLsbResult(var1[5], var1[6]) / 10.0F + setOilConsumptionData$unit1(DataHandleUtils.getIntFromByteWithBit(var1[14], 2, 2)));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_3");
      if (var2 != null) {
         var2.setValue((float)DataHandleUtils.getMsbLsbResult(var1[7], var1[8]) / 10.0F + setOilConsumptionData$unit1(DataHandleUtils.getIntFromByteWithBit(var1[14], 4, 2)));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_4");
      if (var2 != null) {
         var2.setValue((float)MsgMgrKt.getAnotherMsbLsbResult(var1[9], var1[10], var1[11]) / 10.0F + setOilConsumptionData$unit2(DataHandleUtils.getBoolBit6(var1[14])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_5");
      if (var2 != null) {
         var2.setValue(DataHandleUtils.getMsbLsbResult(var1[12], var1[13]) + setOilConsumptionData$unit2(DataHandleUtils.getBoolBit7(var1[14])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_6");
      if (var2 != null) {
         var2.setValue(var1[16] + " Km/H");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_7");
      if (var2 != null) {
         var2.setValue(var1[17] + " : " + var1[18]);
      }

   }

   public final void setOriginalCarData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 1);
      int var16 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1);
      int var15 = DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 2);
      int var17 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 2);
      int var18 = var1[3];
      int var14 = var1[4];
      int var13 = DataHandleUtils.getIntFromByteWithBit(var1[5], 7, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(var1[5], 2, 2);
      int var8 = DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 1);
      int var7 = var1[6];
      int var12 = var1[7];
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[8], 5, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[8], 4, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(var1[8], 3, 1);
      int var11 = DataHandleUtils.getIntFromByteWithBit(var1[8], 2, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[8], 1, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(var1[8], 0, 1);
      SettingPageUiSet.ListBean.ItemListBean var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_1");
      if (var23 != null) {
         var23.setValue(var23.getValueSrnArray().get(var2));
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_2");
      if (var23 != null) {
         var23.setValue(var16);
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_3");
      if (var23 != null) {
         var23.setValue(var23.getValueSrnArray().get(var15));
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_4");
      if (var23 != null) {
         var23.setValue(var23.getValueSrnArray().get(var17));
      }

      SettingPageUiSet.ListBean.ItemListBean var21 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_5");
      String var20 = "set_default";
      String var25;
      if (var21 != null) {
         if (var18 != 1) {
            if (var18 != 2) {
               if (var18 != 3) {
                  var25 = "set_default";
               } else {
                  var25 = "S55_OriginalCarScreen_5_3";
               }
            } else {
               var25 = "S55_OriginalCarScreen_5_2";
            }
         } else {
            var25 = "S55_OriginalCarScreen_5_1";
         }

         var21.setValue(var25);
      }

      byte var24 = 2;
      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_6");
      if (var23 != null) {
         Integer var22 = var14;
         ((Number)var22).intValue();
         MsgMgr var27 = INSTANCE.getMMsgMgr();
         boolean var19;
         if (var14 == 1) {
            var19 = true;
         } else {
            var19 = false;
         }

         var27.openRightCamera(var19);
         var23.setValue(var22);
      }

      var21 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Camera_1");
      if (var21 != null) {
         List var26 = var21.getValueSrnArray();
         if (var13 == 1) {
            INSTANCE.getMMsgMgr().mEnterOrExitAux(true);
            var24 = 1;
         } else {
            INSTANCE.getMMsgMgr().mEnterOrExitAux(false);
         }

         var21.setValue(var26.get(var24));
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_7");
      if (var23 != null) {
         var23.setValue(var23.getValueSrnArray().get(var6));
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_8");
      if (var23 != null) {
         var23.setValue(var8);
      }

      var21 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_9");
      if (var21 != null) {
         if (var7 != 0) {
            if (var7 != 1) {
               var25 = var20;
            } else {
               var25 = "_336_state4";
            }
         } else {
            var25 = "_336_state3";
         }

         var21.setValue(var25);
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_10");
      if (var23 != null) {
         var23.setValue(var12);
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_11");
      if (var23 != null) {
         var23.setValue(var23.getValueSrnArray().get(var3));
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_12");
      if (var23 != null) {
         var23.setValue(var5);
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_13");
      if (var23 != null) {
         var23.setValue(var23.getValueSrnArray().get(var9));
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_14");
      if (var23 != null) {
         var23.setValue(var11);
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_15");
      if (var23 != null) {
         var23.setValue(var4);
      }

      var23 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_16");
      if (var23 != null) {
         var23.setValue(var10);
      }

   }

   public final void setOther2Data(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[2], 7, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(var1[2], 6, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[2], 3, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[2], 2, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 2);
      SettingPageUiSet.ListBean.ItemListBean var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_1");
      if (var9 != null) {
         var9.setValue(var5);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_2");
      if (var9 != null) {
         var9.setValue(var7);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_3");
      if (var9 != null) {
         var9.setValue(var6);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_4");
      if (var9 != null) {
         var9.setValue(var8);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_5");
      if (var9 != null) {
         var9.setValue(var3);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_6");
      if (var9 != null) {
         var9.setValue(var4);
      }

      var9 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_7");
      if (var9 != null) {
         var9.setValue(var9.getValueSrnArray().get(var2));
      }

      var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 7, 1);
      var5 = DataHandleUtils.getIntFromByteWithBit(var1[3], 5, 2);
      var4 = DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 2);
      var3 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 3);
      SettingPageUiSet.ListBean.ItemListBean var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_8");
      if (var10 != null) {
         var10.setValue(var2);
      }

      var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_9");
      if (var10 != null) {
         var10.setValue(var10.getValueSrnArray().get(var5));
      }

      var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_10");
      if (var10 != null) {
         var10.setValue(var10.getValueSrnArray().get(var4));
      }

      var10 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other2_11");
      if (var10 != null) {
         if (var3 != 0) {
            var10.setProgress(var3 - 1);
            var10.setValue(String.valueOf(var3 - 4));
         } else {
            var10.setValue("无效");
         }
      }

   }

   public final void setOtherData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var3 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(var1[2]));
      int var4 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(var1[3]));
      int var2 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(var1[3]));
      int var7 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(var1[3]));
      int var10 = DataHandleUtils.getIntFromByteWithBit(var1[2], 5, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(var1[2], 4, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[2], 0, 4);
      int var6 = DataHandleUtils.getIntFromByteWithBit(var1[4], 1, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(var1[4], 0, 1);
      SettingPageUiSet.ListBean.ItemListBean var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_1");
      if (var11 != null) {
         var11.setValue(var10);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_2");
      if (var11 != null) {
         var11.setValue(var8);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_3");
      if (var11 != null) {
         var11.setValue(var11.getValueSrnArray().get(var5));
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_4");
      if (var11 != null) {
         var11.setValue(var6);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_5");
      if (var11 != null) {
         var11.setValue(var9);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_6");
      if (var11 != null) {
         var11.setValue(var3);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_7");
      if (var11 != null) {
         var11.setValue(var4);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_8");
      if (var11 != null) {
         var11.setValue(var2);
      }

      var11 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_Other1_9");
      if (var11 != null) {
         var11.setValue(var7);
      }

   }

   public final void setRemoteControlData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "data");
      int var5 = DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 2, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(var1[3], 1, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 1);
      SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_1");
      if (var6 != null) {
         var6.setValue(var5);
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_2");
      if (var6 != null) {
         var6.setValue(var2);
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_3");
      if (var6 != null) {
         var6.setValue(var6.getValueSrnArray().get(var4));
      }

      var6 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_4");
      if (var6 != null) {
         var6.setValue(var3);
      }

   }
}
