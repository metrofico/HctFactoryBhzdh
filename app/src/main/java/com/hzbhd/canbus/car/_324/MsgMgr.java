package com.hzbhd.canbus.car._324;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\"\u0018\u0000 M2\u00020\u0001:\u0004MNOPB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0013H\u0016J\"\u0010,\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u000f2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0016J\"\u00101\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u000f2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0016J\u001a\u00102\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u00132\u0006\u00103\u001a\u00020\u000fH\u0016J\u0018\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020/H\u0016Jp\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020/2\u0006\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020/2\u0006\u0010D\u001a\u00020\u0004H\u0016J\u0012\u0010E\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010F\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010G\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010H\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0013H\u0002J\u0012\u0010I\u001a\u00020*2\b\u0010J\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020/H\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0018\u0010!\u001a\f\u0012\b\u0012\u00060#R\u00020\u00000\"X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010$\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0%0\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000¨\u0006Q"},
   d2 = {"Lcom/hzbhd/canbus/car/_324/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "lastClickKey", "", "getLastClickKey", "()Ljava/lang/Integer;", "setLastClickKey", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "lastClickState", "getLastClickState", "setLastClickState", "mCanId", "mCanbusInfoByte", "", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "<set-?>", "Lcom/hzbhd/canbus/car/_324/MsgMgr$OrigiRadioBand;", "mOrigiRadioBand", "getMOrigiRadioBand", "()Lcom/hzbhd/canbus/car/_324/MsgMgr$OrigiRadioBand;", "mOrigiRadioPreset", "mOrigiSource", "getMOrigiSource", "()I", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_324/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_324/UiMgr;", "afterServiceNormalSetting", "", "context", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "sourceSwitchChange", "source", "sourceSwitchNoMediaInfoChange", "isPowerOff", "Companion", "OrigiRadioBand", "OriginalSource", "Parser", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_POSITION_MID = 7;
   public static final int AMPLIFIER_PROGRESS_OFFSET = 2;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final String TAG = "_324_MsgMgr";
   private Integer lastClickKey;
   private Integer lastClickState;
   private int mCanId = -1;
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private Context mContext;
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private OrigiRadioBand mOrigiRadioBand;
   private int mOrigiRadioPreset;
   private int mOrigiSource = -1;
   private final SparseArray mParserArray = new SparseArray();
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private UiMgr mUiMgr;

   public MsgMgr() {
      this.mOrigiRadioBand = MsgMgr.OrigiRadioBand.FM;
   }

   private final void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte[] var7 = new byte[]{22, -127, 1};
      byte[] var4 = new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)};
      byte[] var8 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandMiddle + 2)};
      byte var2 = (byte)(GeneralAmplifierData.bandTreble + 2);
      byte[] var5 = new byte[]{22, -124, 7, (byte)GeneralAmplifierData.volume};
      byte var3 = (byte)(7 - GeneralAmplifierData.frontRear);
      byte[] var6 = new byte[]{22, -124, 2, (byte)(GeneralAmplifierData.leftRight + 7)};
      Iterator var10 = ArrayIteratorKt.iterator((Object[])(new byte[][]{var7, var4, var8, {22, -124, 6, var2}, var5, {22, -124, 1, var3}, var6}));
      TimerUtil var9 = new TimerUtil();
      var9.startTimer((TimerTask)(new TimerTask(var10, var9) {
         final Iterator $this_run;
         final TimerUtil $this_run$1;

         {
            this.$this_run = var1;
            this.$this_run$1 = var2;
         }

         public void run() {
            if (this.$this_run.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.$this_run.next());
            } else {
               this.$this_run$1.stopTimer();
            }

         }
      }), 0L, 100L);
   }

   private final void initItemsIndexHashMap(Context var1) {
      Log.i("_324_MsgMgr", "initItems: ");
      UiMgr var5 = this.mUiMgr;
      UiMgr var4 = var5;
      if (var5 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      Iterator var11 = var4.getSettingUiSet(var1).getList().iterator();

      int var2;
      int var3;
      for(var2 = 0; var11.hasNext(); ++var2) {
         Iterator var7 = ((SettingPageUiSet.ListBean)var11.next()).getItemList().iterator();

         for(var3 = 0; var7.hasNext(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var8 = (SettingPageUiSet.ListBean.ItemListBean)var7.next();
            Map var6 = (Map)this.mSettingItemIndexHashMap;
            String var14 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "itemListBean.titleSrn");
            var6.put(var14, new SettingUpdateEntity(var2, var3));
         }
      }

      var11 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var11.hasNext(); ++var2) {
         Iterator var10 = ((DriverDataPageUiSet.Page)var11.next()).getItemList().iterator();

         for(var3 = 0; var10.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var12 = (DriverDataPageUiSet.Page.Item)var10.next();
            Map var9 = (Map)this.mDriveItemIndexHashMap;
            String var13 = var12.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var13, "item.titleSrn");
            var9.put(var13, new DriverUpdateEntity(var2, var3, "null_value"));
         }
      }

   }

   private final void initParsers(Context var1) {
      SparseArray var2 = this.mParserArray;
      var2.put(1, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               switch (this.this$0.mCanbusInfoInt[2]) {
                  case 0:
                     this.this$0.realKeyClick4(this.$context, 0);
                     break;
                  case 1:
                     this.this$0.realKeyClick4(this.$context, 7);
                     break;
                  case 2:
                     this.this$0.realKeyClick4(this.$context, 8);
                     break;
                  case 3:
                     this.this$0.realKeyClick4(this.$context, 46);
                     break;
                  case 4:
                     this.this$0.realKeyClick4(this.$context, 45);
                     break;
                  case 5:
                     this.this$0.realKeyClick4(this.$context, 2);
                     break;
                  case 6:
                     this.this$0.realKeyClick4(this.$context, 14);
                     break;
                  case 7:
                     this.this$0.realKeyClick4(this.$context, 15);
                     break;
                  case 8:
                     this.this$0.realKeyClick4(this.$context, 187);
                     break;
                  case 9:
                     this.this$0.realKeyClick4(this.$context, 3);
                     break;
                  case 10:
                     this.this$0.realKeyClick4(this.$context, 50);
                     break;
                  case 11:
                     this.this$0.realKeyClick4(this.$context, 151);
                     break;
                  case 12:
                     this.this$0.realKeyClick4(this.$context, 49);
                     break;
                  case 13:
                     this.this$0.realKeyClick4(this.$context, 188);
               }
            }

         }
      });
      var2.append(16, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.this$0.getMOrigiSource() == MsgMgr.OriginalSource.TUNER.getValue() && this.isDataChange()) {
               var1 = this.this$0.mCanbusInfoInt[2];
               boolean var9;
               if (var1 != 1 && var1 != 2) {
                  if (var1 != 3) {
                     return;
                  }

                  var9 = false;
               } else {
                  var9 = true;
               }

               MsgMgr var6 = this.this$0;
               ArrayList var7 = new ArrayList();

               SongListEntity var11;
               for(int var2 = 1; var2 < 7; ++var2) {
                  StringBuilder var8 = (new StringBuilder()).append('P').append(var2).append("  ");
                  int[] var5 = var6.mCanbusInfoInt;
                  int var3 = var2 * 2;
                  var3 = var5[var3 + 1] << 8 | var6.mCanbusInfoInt[var3 + 2];
                  String var10;
                  if (var9) {
                     var10 = (float)var3 / (float)10 + "kHz";
                  } else {
                     var10 = var3 + "MHz";
                  }

                  var11 = new SongListEntity(var8.append(var10).toString());
                  boolean var4;
                  if (var6.mOrigiRadioPreset == var2) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  var7.add(var11.setSelected(var4));
               }

               GeneralOriginalCarDeviceData.songList = (List)var7;
               Log.i("_324_MsgMgr", "parse: " + GeneralOriginalCarDeviceData.songList.size());
               List var12 = GeneralOriginalCarDeviceData.songList;
               Intrinsics.checkNotNullExpressionValue(var12, "songList");
               Iterator var13 = ((Iterable)var12).iterator();

               while(var13.hasNext()) {
                  var11 = (SongListEntity)var13.next();
                  Log.i("_324_MsgMgr", "parse: " + var11.getTitle());
               }

               var6.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(17, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void enterOriginalDevice() {
            this.this$0.enterAuxIn2(this.$context, Constant.OriginalDeviceActivity);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var2;
               if (this.this$0.mCanbusInfoInt[3] != 0) {
                  var1 = this.this$0.mCanbusInfoInt[2];
                  if (var1 != 48) {
                     switch (var1) {
                        case 0:
                           this.this$0.realKeyClick4(this.$context, 0);
                           break;
                        case 1:
                           this.this$0.realKeyClick4(this.$context, 134);
                           break;
                        case 2:
                           this.this$0.realKeyClick4(this.$context, 7);
                           break;
                        case 3:
                           this.this$0.realKeyClick4(this.$context, 8);
                           break;
                        case 4:
                           this.enterOriginalDevice();
                           break;
                        case 5:
                           this.enterOriginalDevice();
                           break;
                        case 6:
                           this.this$0.realKeyClick4(this.$context, 2);
                           break;
                        case 7:
                           this.enterOriginalDevice();
                           break;
                        case 8:
                           this.this$0.realKeyClick4(this.$context, 48);
                           break;
                        case 9:
                           this.this$0.realKeyClick4(this.$context, 47);
                           break;
                        default:
                           switch (var1) {
                              case 16:
                                 this.this$0.realKeyClick4(this.$context, 46);
                                 break;
                              case 17:
                                 this.this$0.realKeyClick4(this.$context, 45);
                                 break;
                              case 18:
                                 this.this$0.realKeyClick4(this.$context, 30);
                                 break;
                              default:
                                 switch (var1) {
                                    case 32:
                                       this.this$0.realKeyClick4(this.$context, 52);
                                       break;
                                    case 33:
                                       this.this$0.realKeyClick4(this.$context, 128);
                                       break;
                                    case 34:
                                       this.this$0.realKeyClick4(this.$context, 50);
                                       break;
                                    case 35:
                                       this.this$0.realKeyClick4(this.$context, 151);
                                       break;
                                    case 36:
                                       this.this$0.realKeyClick4(this.$context, 49);
                                       break;
                                    case 37:
                                       this.this$0.realKeyClick4(this.$context, 45);
                                       break;
                                    case 38:
                                       this.this$0.realKeyClick4(this.$context, 46);
                                       break;
                                    case 39:
                                       this.this$0.realKeyClick4(this.$context, 47);
                                       break;
                                    case 40:
                                       this.this$0.realKeyClick4(this.$context, 48);
                                       break;
                                    case 41:
                                       this.this$0.realKeyClick4(this.$context, 53);
                                       break;
                                    case 42:
                                       var2 = this.this$0;
                                       var2.startDrivingDataActivity(var2.mContext, 0);
                                       break;
                                    case 43:
                                       this.this$0.realKeyClick4(this.$context, 58);
                                 }
                           }
                     }
                  } else {
                     var2 = this.this$0;
                     var2.startOtherActivity(var2.mContext, HzbhdComponentName.CanBusAirActivity);
                  }
               }

               var2 = this.this$0;
               var2.setLastClickKey(var2.mCanbusInfoInt[2]);
               var2 = this.this$0;
               var2.setLastClickState(var2.mCanbusInfoInt[3]);
            }

         }
      });
      var2.append(26, new Parser(this, var1) {
         final Context $context;
         private int outDoorTemperature;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$ac57OI43gucff18KnF4wS7ocndc(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 3 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.clean_air = var2;
            if ((var0.mCanbusInfoInt[2] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.swing = var2;
            if ((var0.mCanbusInfoInt[2] >> 1 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.negative_ion = var2;
            if ((var0.mCanbusInfoInt[2] & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_window_heat = var2;
         }

         public void parse(int var1) {
            if (this.outDoorTemperature != this.this$0.mCanbusInfoInt[3]) {
               this.this$0.updateOutDoorTemp(this.$context, this.this$0.mCanbusInfoByte[3] + this.this$0.getTempUnitC(this.$context));
            }

            this.this$0.mCanbusInfoInt[3] = 0;
            if (this.isDataChange()) {
               super.parse(var1);
               this.this$0.updateAirActivity(this.$context, 1001);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$4$$ExternalSyntheticLambda0(this.this$0), null};
         }
      });
      var2.append(27, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$8H2T2wjyxj5hVsVsKyU85EnUxp8(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$VrogaIzPGe0Mh1NTufrgtXDN7gM(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$m1n8iO6NZ8U4ehd9WmyZg_T8m5M(MsgMgr var0, Context var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_ac = var2;
            if ((var0.mCanbusInfoInt[2] >> 4 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_auto = var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Context var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "$context");
            int var4 = var0.mCanbusInfoInt[3];
            String var5;
            if (var4 == 0) {
               var5 = "LO";
            } else if (var4 == 31) {
               var5 = "HI";
            } else {
               boolean var3 = false;
               boolean var2 = var3;
               if (1 <= var4) {
                  var2 = var3;
                  if (var4 < 30) {
                     var2 = true;
                  }
               }

               if (var2) {
                  var5 = (float)(var0.mCanbusInfoInt[3] + 35) / 2.0F + var0.getTempUnitC(var1);
               } else {
                  var5 = "";
               }
            }

            GeneralAirData.rear_temperature = var5;
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[4];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_window = var2;
            if ((var0.mCanbusInfoInt[4] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[4] >> 5 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_foot = var2;
            GeneralAirData.rear_wind_level = var0.mCanbusInfoInt[4] & 15;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               boolean var2;
               if (GeneralAirData.rear_wind_level > 0) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralAirData.rear_power = var2;
               GeneralAirData.rear_right_blow_window = GeneralAirData.rear_left_blow_window;
               GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
               GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
               this.this$0.updateAirActivity(this.$context, 1002);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1(this.this$0, this.$context), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2(this.this$0)};
         }
      });
      var2.append(28, new Parser(this, var1) {
         final Context $context;
         private final SparseArray originalPageArray;
         private OriginalCarDevicePageUiSet pageUiSet;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            UiMgr var3 = var1.mUiMgr;
            UiMgr var4 = var3;
            if (var3 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
               var4 = null;
            }

            this.pageUiSet = var4.getOriginalCarDevicePageUiSet(var2);
            SparseArray var5 = new SparseArray();
            OriginalCarDevicePageUiSet var6 = new OriginalCarDevicePageUiSet();
            var6.setHavePlayTimeSeekBar(false);
            var6.setHaveSongList(false);
            var6.setRowTopBtnAction((String[])null);
            var6.setRowBottomBtnAction((String[])null);
            var6.setItems(CollectionsKt.emptyList());
            Unit var7 = Unit.INSTANCE;
            var5.put(0, var6);
            var6 = new OriginalCarDevicePageUiSet();
            var6.setHavePlayTimeSeekBar(false);
            var6.setHaveSongList(true);
            var6.setRowTopBtnAction(new String[0]);
            var6.setRowBottomBtnAction(new String[0]);
            ArrayList var8 = new ArrayList();
            var8.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_band", ""));
            var8.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_frequency", ""));
            var8.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_preset", ""));
            var6.setItems((List)var8);
            var7 = Unit.INSTANCE;
            var5.append(1, var6);
            var6 = new OriginalCarDevicePageUiSet();
            var6.setHavePlayTimeSeekBar(false);
            var6.setHaveSongList(true);
            var6.setRowTopBtnAction(new String[]{"disc_scan", "rdm_disc", "rpt_disc", "scan", "rdm", "rpt"});
            var6.setRowBottomBtnAction(new String[]{"up", "down"});
            var8 = new ArrayList();
            var8.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_current_disc", ""));
            var8.add(new OriginalCarDevicePageUiSet.Item("default_image", "_324_track_info", ""));
            var8.add(new OriginalCarDevicePageUiSet.Item("default_image", "_308_title_20", ""));
            var6.setItems((List)var8);
            var7 = Unit.INSTANCE;
            var5.append(2, var6);
            this.originalPageArray = var5;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               var1 = this.this$0.getMOrigiSource();
               int var2 = this.this$0.mCanbusInfoInt[2];
               String var5 = "";
               boolean var4 = false;
               ArrayList var10;
               if (var1 != var2) {
                  OriginalCarDevicePageUiSet var6 = (OriginalCarDevicePageUiSet)this.originalPageArray.get(this.this$0.mCanbusInfoInt[2]);
                  if (var6 != null) {
                     this.pageUiSet.setHaveSongList(var6.isHaveSongList());
                     this.pageUiSet.setRowTopBtnAction(var6.getRowTopBtnAction());
                     this.pageUiSet.setRowBottomBtnAction(var6.getRowBottomBtnAction());
                     this.pageUiSet.setItems(var6.getItems());
                  }

                  GeneralOriginalCarDeviceData.runningState = " ";
                  var10 = new ArrayList();
                  var2 = this.pageUiSet.getItems().size();

                  for(var1 = 0; var1 < var2; ++var1) {
                     var10.add(new OriginalCarDeviceUpdateEntity(var1, ""));
                  }

                  GeneralOriginalCarDeviceData.mList = (List)var10;
                  GeneralOriginalCarDeviceData.songList = CollectionsKt.emptyList();
               }

               var1 = this.this$0.mCanbusInfoInt[2];
               MsgMgr var12;
               if (var1 != 0) {
                  if (var1 != 1) {
                     if (var1 == 2) {
                        GeneralOriginalCarDeviceData.cdStatus = "DISC";
                        var10 = new ArrayList();
                        Context var8 = this.$context;
                        MsgMgr var7 = this.this$0;
                        StringBuilder var15 = (new StringBuilder()).append(CommUtil.getStrByResId(var8, "_279_disc")).append(' ');
                        if (var7.mCanbusInfoInt[4] < 1) {
                           var1 = 1;
                        } else {
                           var1 = var7.mCanbusInfoInt[4];
                        }

                        var10.add(new OriginalCarDeviceUpdateEntity(0, var15.append(var1).toString()));
                        if (var7.mCanbusInfoInt[6] != 0) {
                           var5 = var7.mCanbusInfoInt[5] + " / " + var7.mCanbusInfoInt[6];
                        }

                        var10.add(new OriginalCarDeviceUpdateEntity(1, var5));
                        StringCompanionObject var9 = StringCompanionObject.INSTANCE;
                        var5 = String.format("%02d:%02d", Arrays.copyOf(new Object[]{var7.mCanbusInfoInt[8], var7.mCanbusInfoInt[9]}, 2));
                        Intrinsics.checkNotNullExpressionValue(var5, "format(format, *args)");
                        var10.add(new OriginalCarDeviceUpdateEntity(2, var5));
                        GeneralOriginalCarDeviceData.mList = (List)var10;
                        boolean var3;
                        if ((this.this$0.mCanbusInfoInt[7] >> 6 & 1) == 1) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        GeneralOriginalCarDeviceData.disc_scan = var3;
                        if ((this.this$0.mCanbusInfoInt[7] >> 5 & 1) == 1) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        GeneralOriginalCarDeviceData.rdm_disc = var3;
                        if ((this.this$0.mCanbusInfoInt[7] >> 4 & 1) == 1) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        GeneralOriginalCarDeviceData.rpt_disc = var3;
                        if ((this.this$0.mCanbusInfoInt[7] >> 2 & 1) == 1) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        GeneralOriginalCarDeviceData.scan = var3;
                        if ((this.this$0.mCanbusInfoInt[7] >> 1 & 1) == 1) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        GeneralOriginalCarDeviceData.rdm = var3;
                        var3 = var4;
                        if ((this.this$0.mCanbusInfoInt[7] & 1) == 1) {
                           var3 = true;
                        }

                        GeneralOriginalCarDeviceData.rpt = var3;
                     }
                  } else {
                     GeneralOriginalCarDeviceData.cdStatus = "TUNER";
                     var1 = this.this$0.mCanbusInfoInt[4];
                     OrigiRadioBand var11;
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 != 17) {
                              return;
                           }

                           var11 = MsgMgr.OrigiRadioBand.AM;
                        } else {
                           var11 = MsgMgr.OrigiRadioBand.FM2;
                        }
                     } else {
                        var11 = MsgMgr.OrigiRadioBand.FM;
                     }

                     var12 = this.this$0;
                     var12.mOrigiRadioBand = var11;
                     ArrayList var13 = new ArrayList();
                     var13.add(new OriginalCarDeviceUpdateEntity(0, var11.name()));
                     var1 = var12.mCanbusInfoInt[5] << 8 | var12.mCanbusInfoInt[6];
                     if (var11.isFm()) {
                        var5 = (float)var1 / (float)10 + "kHz";
                     } else {
                        var5 = var1 + "MHz";
                     }

                     var13.add(new OriginalCarDeviceUpdateEntity(1, var5));
                     var12.mOrigiRadioPreset = var12.mCanbusInfoInt[7];
                     var13.add(new OriginalCarDeviceUpdateEntity(2, "" + 'P' + var12.mCanbusInfoInt[7]));
                     GeneralOriginalCarDeviceData.mList = (List)var13;
                     Context var14 = this.$context;
                     var1 = this.this$0.mCanbusInfoInt[8];
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 != 2) {
                              var5 = "null_value";
                           } else {
                              var5 = "_324_searching";
                           }
                        } else {
                           var5 = "ford_original_status2";
                        }
                     } else {
                        var5 = "ford_original_status1";
                     }

                     GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var14, var5);
                  }
               } else {
                  GeneralOriginalCarDeviceData.cdStatus = "OFF";
               }

               var12 = this.this$0;
               Bundle var17;
               if (var12.getMOrigiSource() != this.this$0.mCanbusInfoInt[2]) {
                  MsgMgr var16 = this.this$0;
                  var16.mOrigiSource = var16.mCanbusInfoInt[2];
                  var17 = new Bundle();
                  var17.putBoolean("bundle_key_orinal_init_view", true);
               } else {
                  var17 = null;
               }

               var12.updateOriginalCarDeviceActivity(var17);
            }

         }
      });
      var2.append(29, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.this$0.getMOrigiSource() == MsgMgr.OriginalSource.DISC.getValue() && this.isDataChange()) {
               ArrayList var11 = new ArrayList();
               MsgMgr var10 = this.this$0;
               Context var12 = this.$context;
               int var5 = var10.mCanbusInfoInt[2];
               var1 = 0;
               int var7 = var10.mCanbusInfoInt[2];
               int var8 = var10.mCanbusInfoInt[2];
               int var3 = var10.mCanbusInfoInt[2];
               int var6 = var10.mCanbusInfoInt[3];
               int var4 = var10.mCanbusInfoInt[3];

               for(int var2 = 0; var1 < 6; ++var1) {
                  int var9 = (new int[]{var5 >> 6 & 3, var7 >> 4 & 3, var8 >> 2 & 3, var3 & 3, var6 >> 6 & 3, var4 >> 4 & 3})[var1];
                  ++var2;
                  StringBuilder var13 = (new StringBuilder()).append(var2).append(" - ");
                  String var14;
                  if (var9 != 0) {
                     if (var9 != 1) {
                        if (var9 != 2) {
                           var14 = "";
                        } else {
                           var14 = "DVD";
                        }
                     } else {
                        var14 = "CD";
                     }
                  } else {
                     var14 = CommUtil.getStrByResId(var12, "_123_divice_status_0");
                  }

                  var11.add(new SongListEntity(var13.append(var14).toString()));
               }

               GeneralOriginalCarDeviceData.songList = (List)var11;
               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(36, new Parser(this, var1) {
         final Context $context;
         private int doorStatus;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.doorStatus != this.this$0.mCanbusInfoInt[2]) {
               this.doorStatus = this.this$0.mCanbusInfoInt[2];
               var1 = this.this$0.mCanbusInfoInt[2];
               boolean var3 = true;
               boolean var2;
               if ((var1 >> 7 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 6 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 5 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] >> 4 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftRearDoorOpen = var2;
               var2 = var3;
               if ((this.this$0.mCanbusInfoInt[2] >> 3 & 1) != 1) {
                  if ((this.this$0.mCanbusInfoInt[2] >> 2 & 1) == 1 && (this.this$0.mCanbusInfoInt[2] >> 1 & 1) == 0) {
                     var2 = var3;
                  } else {
                     var2 = false;
                  }
               }

               GeneralDoorData.isBackOpen = var2;
               this.this$0.updateDoorView(this.$context);
            }

         }
      });
      var2.append(40, new Parser(this, var1) {
         final Context $context;
         private int rearLock;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$3xBoKHolNUaPLL14tz4VBVedH88(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$MEVrN4xiq_NLBjcnQIDfkfzzW3s(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$VN4UDdWG6O9cFXYaeDsYPGs0l6g(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_2(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$js_CJLOMp4C821AhU3h00SEyPkY(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String getTemperature(int var1) {
            String var4;
            if (var1 == 0) {
               var4 = "LO";
            } else if (var1 == 31) {
               var4 = "HI";
            } else {
               boolean var3 = false;
               boolean var2 = var3;
               if (1 <= var1) {
                  var2 = var3;
                  if (var1 < 30) {
                     var2 = true;
                  }
               }

               if (var2) {
                  var4 = (float)(var1 + 35) / 2.0F + this.this$0.getTempUnitC(this.$context);
               } else {
                  var4 = "";
               }
            }

            return var4;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[2];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.power = var2;
            if ((var0.mCanbusInfoInt[2] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
            if ((var0.mCanbusInfoInt[2] >> 5 & 1) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.in_out_cycle = var2;
            if ((var0.mCanbusInfoInt[2] >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.auto = var2;
            if ((var0.mCanbusInfoInt[2] >> 2 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.dual = var2;
            if ((var0.mCanbusInfoInt[2] >> 1 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.max_front = var2;
            if ((var0.mCanbusInfoInt[2] & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear = var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_window = var2;
            if ((var0.mCanbusInfoInt[3] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[3] >> 5 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_foot = var2;
            GeneralAirData.front_wind_level = var0.mCanbusInfoInt[3] & 15;
         }

         private static final void setOnParseListeners$lambda_2(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_left_temperature = var0.getTemperature(var1.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_right_temperature = var0.getTemperature(var1.mCanbusInfoInt[5]);
         }

         public void parse(int var1) {
            short var3;
            label20: {
               if (this.this$0.mCanbusInfoInt.length > 6) {
                  int var2 = this.this$0.mCanbusInfoInt[6] >> 2 & 1;
                  if (!Integer.valueOf(var2).equals(this.rearLock)) {
                     this.rearLock = var2;
                     GeneralAirData.rear_lock = Integer.valueOf(var2).equals(1);
                     var3 = 1002;
                     break label20;
                  }
               }

               var3 = 0;
            }

            this.this$0.mCanbusInfoInt[6] = 0;
            this.this$0.mCanbusInfoInt[3] &= 239;
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
               GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
               GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
               var3 = 1001;
            }

            if (var3 != 0) {
               this.this$0.updateAirActivity(this.$context, var3);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda2(this, this.this$0), new MsgMgr$initParsers$1$9$$ExternalSyntheticLambda3(this, this.this$0), null};
         }
      });
      var2.append(41, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 380, 12) * -1;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

            super.parse(var1);
         }
      });
      var2.append(49, new Parser(this, var1) {
         final Context $context;
         private int asl;
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            var1 = this.this$0.mCanbusInfoInt[6] & 1;
            MsgMgr var3 = this.this$0;
            if (!Integer.valueOf(var1).equals(this.asl)) {
               this.asl = var1;
               this.list.clear();
               SettingUpdateEntity var2 = (SettingUpdateEntity)var3.mSettingItemIndexHashMap.get("_186_asl");
               if (var2 != null) {
                  this.list.add(var2.setValue(this.asl));
               }

               var3.updateGeneralSettingData((List)this.list);
               var3.updateSettingActivity((Bundle)null);
            }

            this.this$0.mCanbusInfoInt[6] = 0;
            if (this.isDataChange()) {
               GeneralAmplifierData.frontRear = 7 - (this.this$0.mCanbusInfoInt[2] >> 4 & 15);
               GeneralAmplifierData.leftRight = (this.this$0.mCanbusInfoInt[2] & 15) - 7;
               GeneralAmplifierData.bandBass = (this.this$0.mCanbusInfoInt[3] >> 4 & 15) - 2;
               GeneralAmplifierData.bandTreble = (this.this$0.mCanbusInfoInt[3] & 15) - 2;
               GeneralAmplifierData.bandMiddle = (this.this$0.mCanbusInfoInt[4] >> 4 & 15) - 2;
               GeneralAmplifierData.volume = this.this$0.mCanbusInfoInt[5];
               this.this$0.updateAmplifierActivity((Bundle)null);
               MsgMgr var4 = this.this$0;
               var4.saveAmplifierData(this.$context, var4.mCanId);
            }

         }
      });
      var2.append(50, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               SettingUpdateEntity var3 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_143_0xAD_setting7");
               if (var3 != null) {
                  MsgMgr var2 = this.this$0;
                  this.list.add(var3.setValue(var2.mCanbusInfoInt[2]));
               }

               SettingUpdateEntity var4 = (SettingUpdateEntity)this.this$0.mSettingItemIndexHashMap.get("_143_0xAD_setting4");
               if (var4 != null) {
                  MsgMgr var5 = this.this$0;
                  this.list.add(var4.setValue(var5.mCanbusInfoInt[3]));
               }

               this.this$0.updateGeneralSettingData((List)this.list);
               this.this$0.updateSettingActivity((Bundle)null);
            }

         }
      });
      var2.append(90, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private final String getResult(int[] var1, int var2, Function1 var3) {
            int var6 = var1.length;
            boolean var5 = false;

            for(int var4 = 0; var4 < var6; ++var4) {
               if (var1[var4] != 255) {
                  var6 = this.toInteger(var1);
                  boolean var8 = var5;
                  if (var6 >= 0) {
                     var8 = var5;
                     if (var6 <= var2) {
                        var8 = true;
                     }
                  }

                  String var7;
                  if (var8) {
                     var7 = (String)var3.invoke(var6);
                  } else {
                     var7 = null;
                  }

                  return var7;
               }
            }

            return "";
         }

         private final int toInteger(int[] var1) {
            int var3 = var1.length;
            int var2 = 0;
            if (var3 > 0) {
               if (var1.length > 1) {
                  var2 = ArraysKt.first(var1) << 8 | var1[1];
                  if (var1.length > 2) {
                     var1 = ArraysKt.copyOfRange(var1, 2, var1.length);
                     var2 = this.toInteger(ArraysKt.plus(new int[]{var2}, var1));
                  }
               } else {
                  var2 = ArraysKt.first(var1);
               }
            }

            return var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var2 = this.this$0;
               var2.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var2.mCanbusInfoInt[8], this.this$0.mCanbusInfoInt[9]));
               this.list.clear();
               int[] var6 = this.this$0.mCanbusInfoInt;
               MsgMgr var3 = this.this$0;
               DriverUpdateEntity var4 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_18_current_fuel");
               String var5;
               if (var4 != null) {
                  var5 = this.getResult(ArraysKt.copyOfRange(var6, 2, 4), 300, (Function1)(new Function1(var6) {
                     final int[] $this_run;

                     {
                        this.$this_run = var1;
                     }

                     public final String invoke(int var1) {
                        StringCompanionObject var3 = StringCompanionObject.INSTANCE;
                        float var2 = (float)var1 / 10.0F;
                        String var4;
                        if ((this.$this_run[12] >> 6 & 3) == 1) {
                           var4 = "km/l";
                        } else {
                           var4 = "l/100km";
                        }

                        var4 = String.format("%.1f %s", Arrays.copyOf(new Object[]{var2, var4}, 2));
                        Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
                        return var4;
                     }
                  }));
                  if (var5 != null) {
                     ((Collection)this.list).add(var4.setValue(var5));
                  }
               }

               var4 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_324_mean_after_oil");
               if (var4 != null) {
                  var5 = this.getResult(ArraysKt.copyOfRange(var6, 4, 6), 999, (Function1)(new Function1(var6) {
                     final int[] $this_run;

                     {
                        this.$this_run = var1;
                     }

                     public final String invoke(int var1) {
                        StringCompanionObject var3 = StringCompanionObject.INSTANCE;
                        float var2 = (float)var1 / 10.0F;
                        var1 = this.$this_run[12] >> 4 & 3;
                        String var4;
                        if (var1 != 1) {
                           if (var1 != 2) {
                              var4 = "l/100km";
                           } else {
                              var4 = "MPG";
                           }
                        } else {
                           var4 = "km/l";
                        }

                        var4 = String.format("%.1f %s", Arrays.copyOf(new Object[]{var2, var4}, 2));
                        Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
                        return var4;
                     }
                  }));
                  if (var5 != null) {
                     ((Collection)this.list).add(var4.setValue(var5));
                  }
               }

               DriverUpdateEntity var10 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_112_available_mileage");
               if (var10 != null) {
                  String var9 = this.getResult(ArraysKt.copyOfRange(var6, 6, 8), 9999, (Function1)(new Function1(var6) {
                     final int[] $this_run;

                     {
                        this.$this_run = var1;
                     }

                     public final String invoke(int var1) {
                        StringCompanionObject var2 = StringCompanionObject.INSTANCE;
                        String var3;
                        if ((this.$this_run[12] >> 3 & 1) == 1) {
                           var3 = "miles";
                        } else {
                           var3 = "km";
                        }

                        var3 = String.format("%d %s", Arrays.copyOf(new Object[]{var1, var3}, 2));
                        Intrinsics.checkNotNullExpressionValue(var3, "format(format, *args)");
                        return var3;
                     }
                  }));
                  if (var9 != null) {
                     ((Collection)this.list).add(var10.setValue(var9));
                  }
               }

               var4 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("a_average_speed");
               if (var4 != null) {
                  var5 = this.getResult(ArraysKt.copyOfRange(var6, 8, 10), 99999, (Function1)(new Function1(var6) {
                     final int[] $this_run;

                     {
                        this.$this_run = var1;
                     }

                     public final String invoke(int var1) {
                        StringCompanionObject var3 = StringCompanionObject.INSTANCE;
                        float var2 = (float)var1 / 10.0F;
                        String var4;
                        if ((this.$this_run[12] >> 2 & 1) == 1) {
                           var4 = "MPH";
                        } else {
                           var4 = "km/h";
                        }

                        var4 = String.format("%.1f %s", Arrays.copyOf(new Object[]{var2, var4}, 2));
                        Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
                        return var4;
                     }
                  }));
                  if (var5 != null) {
                     ((Collection)this.list).add(var4.setValue(var5));
                  }
               }

               DriverUpdateEntity var8 = (DriverUpdateEntity)var3.mDriveItemIndexHashMap.get("_306_value_20");
               if (var8 != null) {
                  String var7 = this.getResult(ArraysKt.copyOfRange(var6, 10, 12), 65533, (Function1)(new Function1(var6) {
                     final int[] $this_run;

                     {
                        this.$this_run = var1;
                     }

                     public final String invoke(int var1) {
                        StringCompanionObject var3 = StringCompanionObject.INSTANCE;
                        float var2 = (float)var1 / 10.0F;
                        String var4;
                        if ((this.$this_run[12] >> 1 & 1) == 1) {
                           var4 = "miles";
                        } else {
                           var4 = "km";
                        }

                        var4 = String.format("%.1f %s", Arrays.copyOf(new Object[]{var2, var4}, 2));
                        Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
                        return var4;
                     }
                  }));
                  if (var7 != null) {
                     ((Collection)this.list).add(var8.setValue(var7));
                  }
               }

               this.this$0.updateGeneralDriveData((List)this.list);
               this.this$0.updateDriveDataActivity((Bundle)null);
            }

         }
      });
      var2.append(101, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               this.list.clear();
               DriverUpdateEntity var2 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_308_title_6");
               ArrayList var3;
               MsgMgr var5;
               if (var2 != null) {
                  var5 = this.this$0;
                  var3 = this.list;
                  StringBuilder var4 = new StringBuilder();
                  var1 = var5.mCanbusInfoInt[2];
                  var3.add(var2.setValue(var4.append(var5.mCanbusInfoInt[3] | var1 << 8).append(" r/min").toString()));
               }

               DriverUpdateEntity var7 = (DriverUpdateEntity)this.this$0.mDriveItemIndexHashMap.get("_308_title_7");
               if (var7 != null) {
                  var5 = this.this$0;
                  var3 = this.list;
                  StringBuilder var6 = new StringBuilder();
                  var1 = var5.mCanbusInfoInt[4];
                  var3.add(var7.setValue(var6.append(var5.mCanbusInfoInt[5] | var1 << 8).append(" Km/h").toString()));
               }
            }

         }
      });
      var2.append(48, new Parser(this, var1) {
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
      var2.append(2, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final void realKeyClick31(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               MsgMgr var2 = this.this$0;
               var2.realKeyClick3_1(this.$context, var1, var2.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }
         }

         public void parse(int var1) {
            if (this.this$0.mCanbusInfoInt[3] != 0) {
               var1 = this.this$0.mCanbusInfoInt[2];
               if (var1 != 0) {
                  if (var1 != 9) {
                     if (var1 != 10) {
                        if (var1 != 14) {
                           if (var1 != 15) {
                              if (var1 != 80) {
                                 if (var1 != 81) {
                                    switch (var1) {
                                       case 47:
                                          this.this$0.realKeyClick4(this.$context, 152);
                                          break;
                                       case 48:
                                          this.this$0.realKeyClick4(this.$context, 4);
                                          break;
                                       case 49:
                                          this.this$0.realKeyClick4(this.$context, 48);
                                          break;
                                       case 50:
                                          this.this$0.realKeyClick4(this.$context, 47);
                                          break;
                                       case 51:
                                          this.this$0.realKeyClick4(this.$context, 31);
                                    }
                                 } else {
                                    this.realKeyClick31(8);
                                 }
                              } else {
                                 this.realKeyClick31(7);
                              }
                           } else {
                              this.this$0.realKeyClick4(this.$context, 46);
                           }
                        } else {
                           this.this$0.realKeyClick4(this.$context, 45);
                        }
                     } else {
                        this.this$0.realKeyClick4(this.$context, 2);
                     }
                  } else {
                     this.this$0.realKeyClick4(this.$context, 134);
                  }
               } else {
                  this.this$0.realKeyClick4(this.$context, 0);
               }
            }

            MsgMgr var2 = this.this$0;
            var2.setLastClickKey(var2.mCanbusInfoInt[2]);
            var2 = this.this$0;
            var2.setLastClickState(var2.mCanbusInfoInt[3]);
         }
      });
      var2.append(25, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(4, this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.this$0.updateParkUi((Bundle)null, this.$context);
         }
      });
      var2.append(30, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(4, this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.this$0.updateParkUi((Bundle)null, this.$context);
         }
      });
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._324.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 6, 0});
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 5, 0});
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

   public void currentVolumeInfoChange(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -113, 1, (byte)var1, 0});
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public final Integer getLastClickKey() {
      return this.lastClickKey;
   }

   public final Integer getLastClickState() {
      return this.lastClickState;
   }

   public final OrigiRadioBand getMOrigiRadioBand() {
      return this.mOrigiRadioBand;
   }

   public final int getMOrigiSource() {
      return this.mOrigiSource;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public final void setLastClickKey(Integer var1) {
      this.lastClickKey = var1;
   }

   public final void setLastClickState(Integer var1) {
      this.lastClickState = var1;
   }

   public void sourceSwitchChange(String var1) {
      if (!SourceConstantsDef.SOURCE_ID.AUX2.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 4, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
      d2 = {"Lcom/hzbhd/canbus/car/_324/MsgMgr$Companion;", "", "()V", "AMPLIFIER_POSITION_MID", "", "AMPLIFIER_PROGRESS_OFFSET", "TAG", "", "CanBusInfo_release"},
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
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/car/_324/MsgMgr$OrigiRadioBand;", "", "isFm", "", "(Ljava/lang/String;IZ)V", "()Z", "FM", "FM2", "AM", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum OrigiRadioBand {
      private static final OrigiRadioBand[] $VALUES = $values();
      AM(false),
      FM(true),
      FM2(true);

      private final boolean isFm;

      // $FF: synthetic method
      private static final OrigiRadioBand[] $values() {
         return new OrigiRadioBand[]{FM, FM2, AM};
      }

      private OrigiRadioBand(boolean var3) {
         this.isFm = var3;
      }

      public final boolean isFm() {
         return this.isFm;
      }
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"},
      d2 = {"Lcom/hzbhd/canbus/car/_324/MsgMgr$OriginalSource;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "OFF", "TUNER", "DISC", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum OriginalSource {
      private static final OriginalSource[] $VALUES = $values();
      DISC(2),
      OFF(0),
      TUNER(1);

      private final int value;

      // $FF: synthetic method
      private static final OriginalSource[] $values() {
         return new OriginalSource[]{OFF, TUNER, DISC};
      }

      private OriginalSource(int var3) {
         this.value = var3;
      }

      public final int getValue() {
         return this.value;
      }
   }

   @Metadata(
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/car/_324/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_324/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
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
         Log.i("_324_MsgMgr", "Parser: " + var2 + " length " + var3);
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
