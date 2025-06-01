package com.hzbhd.canbus.car._329;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 12\u00020\u0001:\u000212B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u0006H\u0016Jp\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020\u0004H\u0016J\u0012\u0010*\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0016\u0010,\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010-\u001a\u00020&J\u0016\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\u00000\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u00063"},
   d2 = {"Lcom/hzbhd/canbus/car/_329/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mCanId", "", "mCanbusInfoByte", "", "mCanbusInfoInt", "", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_329/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_329/UiMgr;", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "initItemsIndexHashMap", "initParsers", "updateBubble", "isShowBubble", "updateSettingItem", "title", "value", "Companion", "Parser", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_329_MsgMgr";
   private int mCanId = -1;
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private final SparseArray mParserArray = new SparseArray();
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private UiMgr mUiMgr;

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_329_MsgMgr", "initItems: ");
      UiMgr var5 = this.mUiMgr;
      UiMgr var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      Iterator var6 = var4.getSettingUiSet(var1).getList().iterator();

      int var2;
      int var3;
      Iterator var11;
      for(var2 = 0; var6.hasNext(); ++var2) {
         var11 = ((SettingPageUiSet.ListBean)var6.next()).getItemList().iterator();

         for(var3 = 0; var11.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var11.next();
            Map var7 = (Map)this.mSettingItemIndexHashMap;
            String var14 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "itemListBean.titleSrn");
            var7.put(var14, new SettingUpdateEntity(var2, var3));
         }
      }

      Iterator var10 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var10.hasNext(); ++var2) {
         var11 = ((DriverDataPageUiSet.Page)var10.next()).getItemList().iterator();

         for(var3 = 0; var11.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var12 = (DriverDataPageUiSet.Page.Item)var11.next();
            Map var9 = (Map)this.mDriveItemIndexHashMap;
            String var13 = var12.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var13, "item.titleSrn");
            var9.put(var13, new DriverUpdateEntity(var2, var3, "null_value"));
         }
      }

   }

   private final void initParsers(Context var1) {
      SparseArray var3 = this.mParserArray;
      var3.put(17, new Parser(this, var1) {
         final Context $context;
         private int mKeyValue;
         private int mTrackValue;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[5]);
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            var2.updateSpeedInfo(var2.mCanbusInfoInt[3]);
            if (this.mKeyValue != this.this$0.mCanbusInfoInt[4]) {
               this.mKeyValue = this.this$0.mCanbusInfoInt[4];
               switch (this.this$0.mCanbusInfoInt[4]) {
                  case 0:
                     this.realKeyLongClick1(0);
                     break;
                  case 1:
                     this.realKeyLongClick1(7);
                     break;
                  case 2:
                     this.realKeyLongClick1(8);
                     break;
                  case 3:
                     this.realKeyLongClick1(3);
                  case 4:
                  case 7:
                  case 10:
                  default:
                     break;
                  case 5:
                     this.realKeyLongClick1(201);
                     break;
                  case 6:
                     this.realKeyLongClick1(184);
                     break;
                  case 8:
                     this.realKeyLongClick1(45);
                     break;
                  case 9:
                     this.realKeyLongClick1(46);
                     break;
                  case 11:
                     this.realKeyLongClick1(2);
                     break;
                  case 12:
                     this.realKeyLongClick1(52);
               }
            }

            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[9], this.this$0.mCanbusInfoByte[8], 0, 540, 16);
            if (this.mTrackValue != GeneralParkData.trackAngle) {
               this.mTrackValue = GeneralParkData.trackAngle;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var3.append(18, new Parser(this, var1) {
         final Context $context;
         private int mDoorStatus;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            GeneralDoorData.isShowSeatBelt = true;
         }

         public void parse(int var1) {
            if (this.mDoorStatus != this.this$0.mCanbusInfoInt[4]) {
               this.mDoorStatus = this.this$0.mCanbusInfoInt[4];
               var1 = this.this$0.mCanbusInfoInt[4];
               boolean var3 = true;
               boolean var2;
               if ((var1 >> 7 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[4] >> 6 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[4] >> 5 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[4] >> 4 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[4] >> 3 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isBackOpen = var2;
               if ((this.this$0.mCanbusInfoInt[4] >> 1 & 1) == 1) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isSeatBeltTie = var2;
               this.this$0.updateDoorView(this.$context);
            }

         }
      });
      var3.append(33, new Parser(this, var1) {
         final Context $context;
         private int mKeyValue;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         public void parse(int var1) {
            if (this.mKeyValue != this.this$0.mCanbusInfoInt[2]) {
               this.mKeyValue = this.this$0.mCanbusInfoInt[2];
               var1 = this.this$0.mCanbusInfoInt[2];
               if (var1 != 0) {
                  if (var1 != 1) {
                     if (var1 != 2) {
                        if (var1 != 3) {
                           if (var1 != 6) {
                              if (var1 != 9) {
                                 if (var1 != 32) {
                                    if (var1 != 40) {
                                       if (var1 != 47) {
                                          if (var1 != 51) {
                                             if (var1 != 55) {
                                                if (var1 != 57) {
                                                   if (var1 != 36) {
                                                      if (var1 == 37) {
                                                         this.realKeyLongClick1(128);
                                                      }
                                                   } else {
                                                      this.realKeyLongClick1(59);
                                                   }
                                                } else {
                                                   this.realKeyLongClick1(152);
                                                }
                                             } else {
                                                this.realKeyLongClick1(58);
                                             }
                                          } else {
                                             this.realKeyLongClick1(129);
                                          }
                                       } else {
                                          this.realKeyLongClick1(52);
                                       }
                                    } else {
                                       this.realKeyLongClick1(188);
                                    }
                                 } else {
                                    this.realKeyLongClick1(128);
                                 }
                              } else {
                                 this.realKeyLongClick1(3);
                              }
                           } else {
                              this.realKeyLongClick1(50);
                           }
                        } else {
                           this.realKeyLongClick1(46);
                        }
                     } else {
                        this.realKeyLongClick1(45);
                     }
                  } else {
                     this.realKeyLongClick1(134);
                  }
               } else {
                  this.realKeyLongClick1(0);
               }
            }

         }
      });
      var3.append(34, new Parser(this, var1) {
         final Context $context;
         private byte mSelectValue;
         private byte mVolumeValue;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[2];
            int var2;
            byte var5;
            if (var1 != 1) {
               if (var1 != 2) {
                  return;
               }

               var2 = this.mSelectValue - this.this$0.mCanbusInfoByte[3];
               MsgMgr var3 = this.this$0;
               Context var4 = this.$context;
               if (var2 > 0) {
                  var5 = 47;
               } else {
                  if (var2 >= 0) {
                     return;
                  }

                  var5 = 48;
               }

               var3.realKeyClick3_2(var4, var5, 0, Math.abs(var2));
               this.mSelectValue = var3.mCanbusInfoByte[3];
            } else {
               var2 = this.mVolumeValue - this.this$0.mCanbusInfoByte[3];
               MsgMgr var7 = this.this$0;
               Context var6 = this.$context;
               if (var2 > 0) {
                  var5 = 8;
               } else {
                  if (var2 >= 0) {
                     return;
                  }

                  var5 = 7;
               }

               var7.realKeyClick3_1(var6, var5, 0, Math.abs(var2));
               this.mVolumeValue = var7.mCanbusInfoByte[3];
            }

         }
      });
      var3.append(240, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            MsgMgr var2 = this.this$0;
            var2.updateVersionInfo(this.$context, var2.getVersionStr(var2.mCanbusInfoByte));
         }
      });
      var3.append(65, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$FkqDQkYMm24HYHWTIRygTpNs6rg(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$HEfn0nVAHOig2R1QMXYMCy_3OaU(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$UQK43NZ8l7c1W1qPkGHl0IxFT_4(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$b_smx5cwe6Bb4jQDSp_TknwgY1Q(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$gmHqJqq9cxEnOO9IIgkf_dw4yR8(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$n_7b8LorjNnXWEYY6xv0Joc5yUU(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$vTfjU8OjiFiEF4S17YgiQxTK9bk(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$vtN2cAVStfMfHFL9bRms1RR2gHE(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mDisableData2 = 255;
            RadarInfoUtil.mMinIsClose = true;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, var0.mCanbusInfoInt[2], 4);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, var0.mCanbusInfoInt[3], 10);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, var0.mCanbusInfoInt[4], 10);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, var0.mCanbusInfoInt[5], 4);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, var0.mCanbusInfoInt[6], 4);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, var0.mCanbusInfoInt[7], 10);
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, var0.mCanbusInfoInt[8], 10);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, var0.mCanbusInfoInt[9], 4);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda7(this.this$0)};
         }
      });
      var3.append(49, new Parser(this, var1) {
         final Context $context;
         private int mOutDoorTemperature;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$60JZZZK_x19IhDW5PTXzQPbdxSg(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$7Urs6kNDdZxIXMhPfVn8jbYaFlM(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_6(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Wwcnfj1ksiz9B9PbohA6IUfBt64(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$aG8u8WEy16gsRfZpcPJS160BimY(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$aJXVGC6ww5KSloOdqKWpBNFVIQ4(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$cEqXegORq7G7Z71uN312fZDxcdY(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$n96DtinhbL6AMWx5LgWXlt_iWtc(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String getTemperature(int var1) {
            String var2;
            if (var1 != 254) {
               if (var1 != 255) {
                  var2 = MsgMgr.initParsers$getTemperature((float)var1 / 2.0F, this.this$0, this.$context);
               } else {
                  var2 = "High";
               }
            } else {
               var2 = "Low";
            }

            return var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.power = var2;
            if ((var0.mCanbusInfoInt[2] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.dual = var2;
            if ((var0.mCanbusInfoInt[2] & 3) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 4 & 1) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.in_out_cycle = var2;
            if ((var0.mCanbusInfoInt[3] >> 3 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.auto = var2;
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[4];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_defog = var2;
            if ((var0.mCanbusInfoInt[4] >> 4 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_defog = var2;
            GeneralAirData.front_right_seat_heat_level = var0.mCanbusInfoInt[4] >> 2 & 3;
            GeneralAirData.front_left_seat_heat_level = var0.mCanbusInfoInt[4] & 3;
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[6];
            boolean var2 = true;
            if (var1 != 1) {
               var2 = false;
            }

            GeneralAirData.front_left_auto_wind = var2;
            var1 = var0.mCanbusInfoInt[6];
            GeneralAirData.front_left_blow_foot = ArraysKt.contains(new int[]{3, 5, 12, 14}, var1);
            var1 = var0.mCanbusInfoInt[6];
            GeneralAirData.front_left_blow_head = ArraysKt.contains(new int[]{5, 6, 13, 14}, var1);
            var1 = var0.mCanbusInfoInt[6];
            GeneralAirData.front_left_blow_window = ArraysKt.contains(new int[]{11, 12, 13, 14}, var1);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAirData.front_wind_level = var0.mCanbusInfoInt[7];
         }

         private static final void setOnParseListeners$lambda_5(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_left_temperature = var0.getTemperature(var1.mCanbusInfoInt[8]);
         }

         private static final void setOnParseListeners$lambda_6(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_right_temperature = var0.getTemperature(var1.mCanbusInfoInt[9]);
         }

         public void parse(int var1) {
            if (this.this$0.mCanbusInfoInt.length > 13 && this.this$0.mCanbusInfoInt[13] < 255 && this.mOutDoorTemperature != this.this$0.mCanbusInfoInt[13]) {
               int var2 = this.this$0.mCanbusInfoInt[13];
               this.mOutDoorTemperature = var2;
               MsgMgr var3 = this.this$0;
               Context var4 = this.$context;
               var3.updateOutDoorTemp(var4, MsgMgr.initParsers$getTemperature((float)var2 / 2.0F - (float)40, var3, var4));
            }

            this.this$0.mCanbusInfoInt[13] = 0;
            this.this$0.mCanbusInfoInt[2] &= 71;
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
               this.this$0.updateAirActivity(this.$context, 1001);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda2(this.this$0), null, new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda5(this, this.this$0), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda6(this, this.this$0)};
         }
      });
      ArrayList var4 = new ArrayList();
      var4.add(new TireUpdateEntity(0, 0, new String[2]));
      var4.add(new TireUpdateEntity(1, 0, new String[2]));
      var4.add(new TireUpdateEntity(2, 0, new String[2]));
      var4.add(new TireUpdateEntity(3, 0, new String[2]));
      Ref.BooleanRef var2 = new Ref.BooleanRef();
      var3.append(72, new Parser(this, var1, var4, var2) {
         final Ref.BooleanRef $isTirePressureLow;
         final ArrayList $tireInfoList;
         private final String mTemperatureUnit;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$tireInfoList = var3;
            this.$isTirePressureLow = var4;
            this.mTemperatureUnit = var1.getTempUnitC(var2);
            GeneralTireData.isHaveSpareTire = false;
         }

         private final String getTemperature(int var1) {
            String var2;
            if (Integer.valueOf(var1).equals(255)) {
               var2 = "--";
            } else {
               var2 = "" + var1 + ' ' + this.mTemperatureUnit;
            }

            return var2;
         }

         private final String getTirePressure(int var1) {
            String var2;
            if (Integer.valueOf(var1).equals(255)) {
               var2 = "-.-";
            } else {
               var2 = (float)var1 / 10.0F + " bar";
            }

            return var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               Exception var10000;
               label61: {
                  MsgMgr var3;
                  Ref.BooleanRef var4;
                  Iterator var15;
                  boolean var10001;
                  try {
                     Iterable var5 = (Iterable)this.$tireInfoList;
                     var3 = this.this$0;
                     var4 = this.$isTirePressureLow;
                     var15 = var5.iterator();
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                     break label61;
                  }

                  while(true) {
                     List var6;
                     TireUpdateEntity var7;
                     label58: {
                        try {
                           if (var15.hasNext()) {
                              var7 = (TireUpdateEntity)var15.next();
                              var7.getList().set(0, this.getTemperature(var3.mCanbusInfoInt[var7.getWhichTire() + 2]));
                              var6 = var7.getList();
                              var1 = var3.mCanbusInfoInt[var7.getWhichTire() + 2 + 4];
                              break label58;
                           }
                        } catch (Exception var13) {
                           var10000 = var13;
                           var10001 = false;
                           break;
                        }

                        try {
                           GeneralTireData.dataList = (List)this.$tireInfoList;
                           this.this$0.updateTirePressureActivity((Bundle)null);
                           return;
                        } catch (Exception var8) {
                           var10000 = var8;
                           var10001 = false;
                           break;
                        }
                     }

                     boolean var2;
                     if (var1 < 18) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     label66: {
                        try {
                           var4.element = var2;
                           if (var4.element) {
                              var7.setTireStatus(1);
                              break label66;
                           }
                        } catch (Exception var12) {
                           var10000 = var12;
                           var10001 = false;
                           break;
                        }

                        try {
                           var7.setTireStatus(0);
                        } catch (Exception var10) {
                           var10000 = var10;
                           var10001 = false;
                           break;
                        }
                     }

                     try {
                        Unit var16 = Unit.INSTANCE;
                        var6.set(1, this.getTirePressure(var1));
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break;
                     }
                  }
               }

               Exception var14 = var10000;
               Log.i("_329_MsgMgr", "parse: " + var14);
            }

         }
      });
      var3.append(73, new Parser(this, var2, var1) {
         final Context $context;
         final Ref.BooleanRef $isTirePressureLow;
         final MsgMgr this$0;
         private final SparseArray tirePositions;

         {
            this.this$0 = var1;
            this.$isTirePressureLow = var2;
            this.$context = var3;
            SparseArray var4 = new SparseArray();
            var4.put(3, "_304_left_front");
            var4.append(5, "_304_right_front");
            var4.append(7, "_304_left_rear");
            var4.append(9, "_304_right_rear");
            this.tirePositions = var4;
         }

         public void parse(int var1) {
            if (!this.$isTirePressureLow.element && this.isDataChange()) {
               ArrayList var5 = new ArrayList();
               MsgMgr var4 = this.this$0;
               Context var6 = this.$context;

               for(var1 = 0; var1 < 3; ++var1) {
                  if ((var4.mCanbusInfoInt[2] >> var1 & 1) == 1) {
                     var5.add(new WarningEntity(CommUtil.getStrByResId(var6, "_329_tire_warning_0" + var1)));
                  }
               }

               for(var1 = 0; var1 < 4; ++var1) {
                  int var3 = (new int[]{3, 5, 7, 9})[var1];

                  for(int var2 = 0; var2 < 8; ++var2) {
                     if ((var4.mCanbusInfoInt[var3] >> var2 & 1) == 1) {
                        var5.add(new WarningEntity(CommUtil.getStrByResId(var6, (String)this.tirePositions.get(var3)) + CommUtil.getStrByResId(var6, "_329_tire_warning_" + var2)));
                     }
                  }
               }

               GeneralWarningDataData.dataList = (List)var5;
               if (GeneralWarningDataData.dataList.size() > 0) {
                  this.this$0.startWarningActivity(this.$context);
               }

               this.this$0.updateWarningActivity((Bundle)null);
            }

         }
      });
      var3.append(62, new Parser(this) {
      });
      var3.append(63, new Parser(this) {
      });
      var3.append(50, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               DriverUpdateEntity var4 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_250_engine_speed");
               String var3 = "--";
               MsgMgr var2;
               String var6;
               if (var4 != null) {
                  var2 = this.this$0;
                  ArrayList var5 = this.list;
                  var1 = var2.mCanbusInfoInt[4];
                  var1 = var2.mCanbusInfoInt[5] | var1 << 8;
                  if (Integer.valueOf(var1).equals(65535)) {
                     var6 = "--";
                  } else {
                     var6 = var1 + " rpm";
                  }

                  var5.add(var4.setValue(var6));
               }

               DriverUpdateEntity var8 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_303_speed");
               if (var8 != null) {
                  var2 = this.this$0;
                  ArrayList var7 = this.list;
                  var1 = var2.mCanbusInfoInt[6];
                  var1 = var2.mCanbusInfoInt[7] | var1 << 8;
                  if (Integer.valueOf(var1).equals(65535)) {
                     var6 = var3;
                  } else {
                     var6 = var1 + " km/h";
                  }

                  var7.add(var8.setValue(var6));
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var3.append(135, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$77E4VtI69peFYdFlXdzkJZQ9m9o(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_12(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$MGsNdV91ZiDu6zjLVRLfYfWrY_o(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_16(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$d8X3HE0vulJ2XwE8rNd9fop2zYk(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_14(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ic9j1HP__elvkZfKjJmzFiCSS4o(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$speZQaypUurb7mlkeBN0M95k3jY(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_9(var0, var1);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_12(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_9");
            if (var2 != null) {
               var1.list.add(var2.setValue((var0.mCanbusInfoInt[7] >> 4 & 15) - 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_10");
            if (var2 != null) {
               var1.list.add(var2.setValue((var0.mCanbusInfoInt[7] & 15) - 1));
            }

         }

         private static final void setOnParseListeners$lambda_14(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_11");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[8]).setProgress(var0.mCanbusInfoInt[8] - 1));
            }

         }

         private static final void setOnParseListeners$lambda_16(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_12");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[9]).setProgress(var0.mCanbusInfoInt[9] - 1));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_1");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 5 & 7));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_2");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 2 & 7));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_3");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] & 3));
            }

         }

         private static final void setOnParseListeners$lambda_9(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_4");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[3] >> 7 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_5");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[3] >> 5 & 3));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_6");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[3] >> 4 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_7");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[3] >> 3 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_329_setting_8");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[3] >> 2 & 1));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               super.parse(var1);
               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda1(this.this$0, this), null, null, null, new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda4(this.this$0, this)};
         }
      });
      var3.append(232, new Parser(this, var1) {
         final Context $context;
         private int avmStatus;
         private final int[] cameraStatus;
         private final ArrayList list;
         final MsgMgr this$0;
         private int warningType;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
            this.cameraStatus = new int[6];
         }

         public void parse(int var1) {
            var1 = this.avmStatus;
            int var3 = this.this$0.mCanbusInfoInt[5];
            boolean var2 = false;
            boolean var4;
            MsgMgr var5;
            Context var6;
            if (var1 != var3) {
               var1 = this.this$0.mCanbusInfoInt[5];
               this.avmStatus = var1;
               var5 = this.this$0;
               var6 = this.$context;
               if (var1 == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var5.forceReverse(var6, var4);
            }

            this.this$0.mCanbusInfoInt[5] = 0;
            int[] var7 = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 3, 9);
            var5 = this.this$0;
            var6 = this.$context;
            if (!Arrays.equals(var7, this.cameraStatus)) {
               ArraysKt.copyInto$default(var7, this.cameraStatus, 0, 0, 0, 14, (Object)null);
               this.list.clear();
               ArrayList var11 = this.list;
               if (var5.mCanbusInfoInt[6] == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(0, var4));
               var11 = this.list;
               if (var5.mCanbusInfoInt[7] == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(1, var4));
               var11 = this.list;
               if (var5.mCanbusInfoInt[4] == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(2, var4));
               var11 = this.list;
               if (var5.mCanbusInfoInt[8] == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(3, var4));
               var11 = this.list;
               if (var5.mCanbusInfoInt[3] == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(4, var4));
               var11 = this.list;
               if (var5.mCanbusInfoInt[3] == 2) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var11.add(new PanoramicBtnUpdateEntity(5, var4));
               GeneralParkData.dataList = (List)this.list;
               var5.updateParkUi((Bundle)null, var6);
            }

            var3 = this.this$0.mCanbusInfoInt[2] >> 4 & 255;
            Context var9 = this.$context;
            MsgMgr var10 = this.this$0;
            boolean var8 = var2;
            if (1 <= var3) {
               var8 = var2;
               if (var3 < 5) {
                  var8 = true;
               }
            }

            if (var8 && this.warningType != var3) {
               this.warningType = var3;
               GeneralDisplayMsgData.displayMsg = CommUtil.getStrByResId(var9, "_329_pano_warn_" + var3);
               var10.sendDisplayMsgView(var9);
            }

         }
      });
   }

   private static final String initParsers$getTemperature(float var0, MsgMgr var1, Context var2) {
      String var4;
      if (GeneralAirData.fahrenheit_celsius) {
         StringCompanionObject var3 = StringCompanionObject.INSTANCE;
         var4 = String.format("%.1f%s", Arrays.copyOf(new Object[]{var0 * (float)9 / (float)5 + (float)32, var1.getTempUnitF(var2)}, 2));
         Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
      } else {
         var4 = var0 + var1.getTempUnitC(var2);
      }

      return var4;
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      UiMgrInterface var3 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type com.hzbhd.canbus.car._329.UiMgr");
      this.mUiMgr = (UiMgr)var3;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
      int var2 = SharePreUtil.getIntValue(var1, "share_329_is_support_panoramic", 0);
      this.updateSettingItem("support_panorama", var2);
      this.updateBubble(var1, Integer.valueOf(var2).equals(1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "getByteArrayToIntArray(canbusInfo)");
      this.mCanbusInfoInt = var3;
      this.mCanbusInfoByte = var2;
      Parser var4 = (Parser)this.mParserArray.get(var3[1]);
      if (var4 != null) {
         var4.parse(this.mCanbusInfoInt.length - 2);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, var12, 0, (byte)var2, (byte)var3, (byte)var4, 0});
   }

   public final void updateBubble(Context var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      GeneralBubbleData.isShowBubble = var2;
      this.updateBubble(var1);
   }

   public final void updateSettingItem(String var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "value");
      SettingUpdateEntity var4 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get(var1);
      if (var4 != null) {
         ArrayList var3 = new ArrayList();
         var3.add(var4.setValue(var2));
         if (Intrinsics.areEqual((Object)var1, (Object)"support_panorama")) {
            SettingUpdateEntity var5 = (SettingUpdateEntity)this.mSettingItemIndexHashMap.get("_55_0xE8_data4");
            if (var5 != null) {
               var3.add(var5.setEnable(var2.equals(1)));
            }
         }

         this.updateGeneralSettingData((List)var3);
         this.updateSettingActivity((Bundle)null);
      }

   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/canbus/car/_329/MsgMgr$Companion;", "", "()V", "TAG", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }

   @Metadata(
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/car/_329/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_329/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private abstract class Parser {
      private final int PARSE_LISTENERS_LENGTH;
      private int[] canbusInfo;
      private final OnParseListener[] onParseListeners;
      final MsgMgr this$0;

      public Parser(MsgMgr var1, String var2) {
         Intrinsics.checkNotNullParameter(var2, "msg");
         this.this$0 = var1;
         super();
         OnParseListener[] var4 = this.setOnParseListeners();
         this.onParseListeners = var4;
         int var3 = var4.length;
         this.PARSE_LISTENERS_LENGTH = var3;
         Log.i("_329_MsgMgr", "Parser: " + var2 + " length " + var3);
      }

      public final int[] getCanbusInfo() {
         return this.canbusInfo;
      }

      public final boolean isDataChange() {
         boolean var1;
         if (Arrays.equals(this.canbusInfo, this.this$0.mCanbusInfoInt)) {
            var1 = false;
         } else {
            int[] var2 = this.this$0.mCanbusInfoInt;
            var2 = Arrays.copyOf(var2, var2.length);
            Intrinsics.checkNotNullExpressionValue(var2, "copyOf(this, size)");
            this.canbusInfo = var2;
            var1 = true;
         }

         return var1;
      }

      public void parse(int var1) {
         for(var1 = Math.min(var1, this.PARSE_LISTENERS_LENGTH); var1 > 0; --var1) {
            OnParseListener var2 = this.onParseListeners[var1 - 1];
            if (var2 != null) {
               var2.onParse();
            }
         }

      }

      public final void setCanbusInfo(int[] var1) {
         this.canbusInfo = var1;
      }

      public OnParseListener[] setOnParseListeners() {
         return (OnParseListener[])((Object[])(new OnParseListener[0]));
      }
   }
}
