package com.hzbhd.canbus.car._322;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
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
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0012\u0018\u0000 V2\u00020\u0001:\u0003VWXB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\"H\u0016J\u001a\u0010:\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\"2\u0006\u0010;\u001a\u00020\u0018H\u0016Jp\u0010<\u001a\u0002082\u0006\u0010=\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020\u001d2\u0006\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020G2\u0006\u0010J\u001a\u00020\u001dH\u0016J\u000e\u0010K\u001a\u00020\u00162\u0006\u00109\u001a\u00020\"J\u0012\u0010L\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\"H\u0002J\u0012\u0010M\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010N\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\"H\u0002J\u0010\u0010O\u001a\u0002082\u0006\u00109\u001a\u00020\"H\u0002J\b\u0010P\u001a\u000208H\u0002J\u0012\u0010Q\u001a\u0002082\b\u0010R\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010S\u001a\u0002082\u0006\u0010T\u001a\u00020GH\u0016J\u0010\u0010U\u001a\u0002082\u0006\u0010T\u001a\u00020GH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020%0$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010(\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001e\u0010+\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0018\u0010-\u001a\f\u0012\b\u0012\u00060/R\u00020\u00000.X\u0082\u0004¢\u0006\u0002\n\u0000R \u00100\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u000202010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.¢\u0006\u0002\n\u0000R\u0011\u00105\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0014¨\u0006Y"},
   d2 = {"Lcom/hzbhd/canbus/car/_322/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "AMP_BASS_TAG_322", "", "getAMP_BASS_TAG_322", "()Ljava/lang/String;", "AMP_FR_TAG_322", "getAMP_FR_TAG_322", "AMP_LR_TAG_322", "getAMP_LR_TAG_322", "AMP_MID_TAG_322", "getAMP_MID_TAG_322", "AMP_TREBLE_TAG_322", "getAMP_TREBLE_TAG_322", "AMP_VOL_TAG_322", "getAMP_VOL_TAG_322", "driver", "Lcom/hzbhd/canbus/car/_322/MsgMgr$SeatData;", "getDriver", "()Lcom/hzbhd/canbus/car/_322/MsgMgr$SeatData;", "mActivePark", "Lcom/hzbhd/canbus/car/_322/ActivePark;", "<set-?>", "", "mAmplifierData", "getMAmplifierData", "()[B", "mCanId", "", "mCanbusInfoByte", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mOriginalDeviceStatus", "getMOriginalDeviceStatus", "()I", "mOriginalRadioBand", "getMOriginalRadioBand", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_322/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_322/UiMgr;", "passenger", "getPassenger", "afterServiceNormalSetting", "", "context", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "getActivePark", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "resetAfterMute", "sourceSwitchChange", "source", "sourceSwitchNoMediaInfoChange", "isPowerOff", "toMuteAmp", "Companion", "Parser", "SeatData", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public static final int AMPLIFIER_DATA_MID = 7;
   public static final int AMPLIFIER_DATA_MIN = 3;
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final int ORIGINAL_DEVICE_STATUS_CD = 2;
   public static final int ORIGINAL_DEVICE_STATUS_FM = 1;
   public static final int ORIGINAL_DEVICE_STATUS_NAVI = 32;
   public static final int ORIGINAL_DEVICE_STATUS_OFF = 0;
   private static final int RADAR_RANGE = 7;
   private static final String SHARE_322_AMPLIFIER_DATAS = "sahre_322_amplifier_datas";
   private static final String TAG = "_322_MsgMgr";
   private final String AMP_BASS_TAG_322 = "AMP_BASS_TAG_322";
   private final String AMP_FR_TAG_322 = "AMP_FR_TAG_322";
   private final String AMP_LR_TAG_322 = "AMP_LR_TAG_322";
   private final String AMP_MID_TAG_322 = "AMP_MID_TAG_322";
   private final String AMP_TREBLE_TAG_322 = "AMP_TREBLE_TAG_322";
   private final String AMP_VOL_TAG_322 = "AMP_VOL_TAG_322";
   private final SeatData driver = new SeatData(0, 0, 0, 0, 0, 31, (DefaultConstructorMarker)null);
   private ActivePark mActivePark;
   private byte[] mAmplifierData = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
   private int mCanId = -1;
   private byte[] mCanbusInfoByte = new byte[0];
   private int[] mCanbusInfoInt = new int[0];
   private Context mContext;
   private HashMap mDriveItemIndexHashMap = new HashMap();
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private int mOriginalDeviceStatus;
   private String mOriginalRadioBand = "";
   private final SparseArray mParserArray = new SparseArray();
   private HashMap mSettingItemIndexHashMap = new HashMap();
   private UiMgr mUiMgr;
   private final SeatData passenger = new SeatData(0, 0, 0, 0, 0, 31, (DefaultConstructorMarker)null);

   // $FF: synthetic method
   public static void $r8$lambda$zGHCu8zMT3d18JLDEjK80_9ekGU() {
      dateTimeRepCanbus$lambda_6();
   }

   private static final void dateTimeRepCanbus$lambda_6() {
      CanbusMsgSender.sendMsg(new byte[]{22, -15, 113});
   }

   private final void initAmplifier(Context var1) {
      String var3 = null;
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         var3 = SharePreUtil.getStringValue(var1, "sahre_322_amplifier_datas", (String)null);
      }

      byte[] var4;
      byte[] var6;
      label24: {
         int var2 = 2;
         var6 = new byte[]{22, -127, 1};
         var4 = new byte[]{22, -109, 0, 0, 0, 0, 0, 0, 0};
         if (var3 != null) {
            List var5 = StringsKt.split$default((CharSequence)var3, new String[]{","}, false, 0, 6, (Object)null);
            if (var5 != null && var5.size() > 8) {
               while(var2 < 9) {
                  var4[var2] = Byte.parseByte((String)var5.get(var2));
                  ++var2;
               }

               var4[8] = (byte)(var4[8] + 4);
               break label24;
            }
         }

         Log.i("_322_MsgMgr", "initAmplifier: ampData[" + var3 + ']');
         var4[2] = (byte)GeneralAmplifierData.volume;
         var4[3] = (byte)(GeneralAmplifierData.leftRight + 7 + 3);
         var4[4] = (byte)(GeneralAmplifierData.frontRear + 7 + 3);
         var4[5] = (byte)(GeneralAmplifierData.bandBass + 3);
         var4[6] = (byte)(GeneralAmplifierData.bandMiddle + 3);
         var4[7] = (byte)(GeneralAmplifierData.bandTreble + 3);
         var4[8] = 4;
      }

      Unit var8 = Unit.INSTANCE;
      Iterator var7 = ArrayIteratorKt.iterator((Object[])(new byte[][]{var6, var4}));
      TimerUtil var9 = new TimerUtil();
      var9.startTimer((TimerTask)(new TimerTask(var7, var9) {
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
      Log.i("_322_MsgMgr", "initItems: ");
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
            String var15 = var8.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var15, "itemListBean.titleSrn");
            var6.put(var15, new SettingUpdateEntity(var2, var3));
         }
      }

      Iterator var9 = var4.getDriverDataPageUiSet(var1).getList().iterator();

      for(var2 = 0; var9.hasNext(); ++var2) {
         Iterator var10 = ((DriverDataPageUiSet.Page)var9.next()).getItemList().iterator();

         for(var3 = 0; var10.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var13 = (DriverDataPageUiSet.Page.Item)var10.next();
            Map var12 = (Map)this.mDriveItemIndexHashMap;
            String var14 = var13.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var14, "item.titleSrn");
            var12.put(var14, new DriverUpdateEntity(var2, var3, "null_value"));
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

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         public void parse(int var1) {
            switch (this.this$0.mCanbusInfoInt[2]) {
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
                  this.realKeyLongClick1(466);
                  break;
               case 4:
                  this.realKeyLongClick1(465);
                  break;
               case 5:
                  this.realKeyLongClick1(2);
                  break;
               case 6:
                  this.realKeyLongClick1(187);
                  break;
               case 7:
                  this.realKeyLongClick1(3);
                  break;
               case 8:
                  this.realKeyLongClick1(188);
                  break;
               case 9:
                  this.realKeyLongClick1(45);
                  break;
               case 10:
                  this.realKeyLongClick1(46);
                  break;
               case 11:
                  this.realKeyLongClick1(47);
                  break;
               case 12:
                  this.realKeyLongClick1(48);
                  break;
               case 13:
                  this.realKeyLongClick1(49);
            }

         }
      });
      var2.put(2, new Parser(this, var1) {
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

         private final void realKeyLongClick1(int var1) {
            MsgMgr var2 = this.this$0;
            var2.realKeyLongClick1(this.$context, var1, var2.mCanbusInfoInt[3]);
         }

         public void parse(int var1) {
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
                                       this.realKeyLongClick1(152);
                                       break;
                                    case 48:
                                       this.realKeyLongClick1(4);
                                       break;
                                    case 49:
                                       this.realKeyLongClick1(48);
                                       break;
                                    case 50:
                                       this.realKeyLongClick1(47);
                                       break;
                                    case 51:
                                       this.realKeyLongClick1(31);
                                 }
                              } else {
                                 this.realKeyClick31(8);
                              }
                           } else {
                              this.realKeyClick31(7);
                           }
                        } else {
                           this.realKeyLongClick1(46);
                        }
                     } else {
                        this.realKeyLongClick1(45);
                     }
                  } else {
                     this.realKeyLongClick1(2);
                  }
               } else {
                  this.realKeyLongClick1(134);
               }
            } else {
               this.realKeyLongClick1(0);
            }

         }
      });
      var2.append(3, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var6 = this.this$0;
               ArrayList var3 = new ArrayList();
               MsgMgr var4 = this.this$0;
               DriverUpdateEntity var5 = (DriverUpdateEntity)var4.mDriveItemIndexHashMap.get("_283_meter_value_2");
               if (var5 != null) {
                  StringBuilder var2 = new StringBuilder();
                  var1 = var4.mCanbusInfoInt[2];
                  var3.add(var5.setValue(var2.append(var4.mCanbusInfoInt[3] | var1 << 8).append(" km/h").toString()));
               }

               var6.updateGeneralDriveData((List)var3);
               this.this$0.updateDriveDataActivity((Bundle)null);
               MsgMgr var7 = this.this$0;
               var7.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var7.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[2]));
            }

         }
      });
      var2.append(4, new Parser(this, var1) {
         final Context $context;
         private int doorStatus;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
            GeneralDoorData.isShowSeatBelt = true;
         }

         private final boolean isDoorStatusChange() {
            int var1 = this.this$0.mCanbusInfoInt[3] | this.this$0.mCanbusInfoInt[2];
            if (Integer.valueOf(var1).equals(this.doorStatus)) {
               return false;
            } else {
               this.doorStatus = var1;
               return true;
            }
         }

         public void parse(int var1) {
            if (this.isDoorStatusChange()) {
               var1 = this.this$0.mCanbusInfoInt[3];
               boolean var3 = true;
               boolean var2;
               if ((var1 >> 7 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 6 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightFrontDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 5 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isLeftRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 4 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isRightRearDoorOpen = var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 3 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isBackOpen = var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 2 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isFrontOpen = var2;
               if ((this.this$0.mCanbusInfoInt[2] & 1) == 1) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               GeneralDoorData.isSeatBeltTie = var2;
               this.this$0.updateDoorView(this.$context);
            }

         }
      });
      var2.append(5, new Parser(this, var1) {
         final Context $context;
         private int outDoorTemperature;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$4rQj8_x57LhctoGri3sC3SrcbCM(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$HZVpq5sO88gmL_c6Y8ElOEZwPOY(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$RWpk87Qm5RAbDyYWb_GkYdiVg3w(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$cIQm003VB1vj_Ai8Ew5HuRvzVYc(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$fKxpkiHnfRpyW21rbIas2bMCVlU(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$hezy6NFVkd95MUzYqYPIGHa0j2I() {
            setOnParseListeners$lambda_8();
         }

         // $FF: synthetic method
         public static void $r8$lambda$nqsZseH3TFl02krn9_AT2_IUS_U(Object var0, MsgMgr var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String resolveTemperature(int var1) {
            String var5 = "";
            String var4;
            if (var1 == 0) {
               var4 = "LO";
            } else if (var1 == 253) {
               var4 = "HI";
            } else if (var1 == 254) {
               var4 = "OFF";
            } else if (var1 == 255) {
               var4 = var5;
            } else {
               boolean var3 = true;
               boolean var2;
               if (28 <= var1 && var1 < 65) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2 && !GeneralAirData.fahrenheit_celsius) {
                  var4 = (float)var1 / (float)2 + this.this$0.getTempUnitC(this.$context);
               } else {
                  if (57 <= var1 && var1 < 91) {
                     var2 = var3;
                  } else {
                     var2 = false;
                  }

                  var4 = var5;
                  if (var2) {
                     var4 = var5;
                     if (GeneralAirData.fahrenheit_celsius) {
                        var4 = var1 + this.this$0.getTempUnitF(this.$context);
                     }
                  }
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
            if ((var0.mCanbusInfoInt[2] >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.dual = var2;
            if ((var0.mCanbusInfoInt[2] >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.auto = var2;
            if ((var0.mCanbusInfoInt[2] >> 2 & 3) == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.in_out_cycle = var2;
            if ((var0.mCanbusInfoInt[2] >> 1 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_defog = var2;
            if ((var0.mCanbusInfoInt[2] & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_defog = var2;
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

            GeneralAirData.max_front = var2;
            if ((var0.mCanbusInfoInt[3] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac_max = var2;
            if ((var0.mCanbusInfoInt[3] >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.steering_wheel_heating = var2;
            if ((var0.mCanbusInfoInt[3] >> 4 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_auto_wind_speed = var2;
            if (!GeneralAirData.front_auto_wind_speed) {
               GeneralAirData.front_wind_level = var0.mCanbusInfoInt[3] & 15;
            }

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

            GeneralAirData.front_left_auto_wind = var2;
            if ((var0.mCanbusInfoInt[4] >> 6 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_window = var2;
            if ((var0.mCanbusInfoInt[4] >> 5 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_foot = var2;
            if ((var0.mCanbusInfoInt[4] >> 4 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.front_left_blow_head = var2;
            if ((var0.mCanbusInfoInt[4] >> 3 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.front_window_heat = var2;
            GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         }

         private static final void setOnParseListeners$lambda_3(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_left_temperature = var0.resolveTemperature(var1.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(Object var0, MsgMgr var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            GeneralAirData.front_right_temperature = var0.resolveTemperature(var1.mCanbusInfoInt[6]);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[7] >> 5 & 7;
            if ((var0.mCanbusInfoInt[7] >> 1 & 1) == 0) {
               GeneralAirData.front_left_seat_heat_level = var1;
            } else {
               GeneralAirData.front_left_seat_cold_level = var1;
            }

            var1 = var0.mCanbusInfoInt[7] >> 2 & 7;
            if ((var0.mCanbusInfoInt[7] & 1) == 0) {
               GeneralAirData.front_right_seat_heat_level = var1;
            } else {
               GeneralAirData.front_right_seat_cold_level = var1;
            }

         }

         private static final void setOnParseListeners$lambda_8() {
         }

         public void parse(int var1) {
            label83: {
               Exception var10000;
               label86: {
                  int var3;
                  boolean var10001;
                  try {
                     var3 = this.this$0.mCanbusInfoInt[4];
                  } catch (Exception var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label86;
                  }

                  boolean var2 = true;
                  boolean var4;
                  if ((var3 & 1) == 1) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  label93: {
                     Context var6;
                     MsgMgr var7;
                     try {
                        GeneralAirData.fahrenheit_celsius = var4;
                        if (this.outDoorTemperature == this.this$0.mCanbusInfoInt[8]) {
                           break label93;
                        }

                        this.outDoorTemperature = this.this$0.mCanbusInfoInt[8];
                        var7 = this.this$0;
                        var6 = this.$context;
                        var3 = var7.mCanbusInfoInt[8];
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label86;
                     }

                     String var5 = " ";
                     if (var3 == 254) {
                        var5 = "--";
                     } else if (var3 != 255) {
                        if (var3 < 0 || var3 >= 254) {
                           var2 = false;
                        }

                        if (var2) {
                           label92: {
                              StringBuilder var14;
                              try {
                                 if (GeneralAirData.fahrenheit_celsius) {
                                    var14 = new StringBuilder();
                                    var5 = var14.append(this.this$0.mCanbusInfoInt[8] - 40).append(this.this$0.getTempUnitF(this.$context)).toString();
                                    break label92;
                                 }
                              } catch (Exception var11) {
                                 var10000 = var11;
                                 var10001 = false;
                                 break label86;
                              }

                              try {
                                 var14 = new StringBuilder();
                                 var5 = var14.append((float)this.this$0.mCanbusInfoInt[8] / (float)2 - (float)40).append(this.this$0.getTempUnitC(this.$context)).toString();
                              } catch (Exception var10) {
                                 var10000 = var10;
                                 var10001 = false;
                                 break label86;
                              }
                           }
                        }
                     }

                     try {
                        var7.updateOutDoorTemp(var6, var5);
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label86;
                     }
                  }

                  try {
                     this.this$0.mCanbusInfoInt[8] = 0;
                     break label83;
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                  }
               }

               Exception var15 = var10000;
               var15.printStackTrace();
            }

            if (this.isDataChange()) {
               super.parse(var1);
               this.this$0.updateAirActivity(this.$context, 1001);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda3(this, this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda4(this, this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$5$$ExternalSyntheticLambda6()};
         }
      });
      var2.append(6, var1.new Parser(var1, this) {
         final Context $context;
         private final String close;
         private final String level;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$BkkJ4eSojh_WOnnX70w1dkD9cxE(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Kl5wXS_0f_x11txE9hnsSTBD5AU(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$eJ3dHATwpGTYMigzSIGqG8XoYFM(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         {
            this.$context = var1;
            this.this$0 = var2;
            String var4 = CommUtil.getStrByResId(var1, "_318_level");
            Intrinsics.checkNotNullExpressionValue(var4, "getStrByResId(context, \"_318_level\")");
            this.level = var4;
            String var3 = CommUtil.getStrByResId(var1, "close");
            Intrinsics.checkNotNullExpressionValue(var3, "getStrByResId(context, \"close\")");
            this.close = var3;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var2 = var0.mCanbusInfoInt[2];
            boolean var4 = true;
            int var1 = 0;
            boolean var3;
            if ((var2 >> 7 & 1) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            GeneralAirData.rear_power = var3;
            if ((var0.mCanbusInfoInt[2] >> 5 & 1) == 1) {
               var3 = var4;
            } else {
               var3 = false;
            }

            GeneralAirData.rear_lock = var3;
            var2 = var0.mCanbusInfoInt[2] & 15;
            if (!Integer.valueOf(var2).equals(15)) {
               var1 = var2;
            }

            GeneralAirData.rear_wind_level = var1;
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            int var1 = var0.mCanbusInfoInt[3];
            boolean var3 = true;
            boolean var2;
            if ((var1 >> 7 & 1) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_foot = var2;
            if ((var0.mCanbusInfoInt[3] >> 6 & 1) == 1) {
               var2 = var3;
            } else {
               var2 = false;
            }

            GeneralAirData.rear_left_blow_head = var2;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            int var2 = var0.mCanbusInfoInt[4];
            int var4 = var0.mCanbusInfoInt[4] & 15;
            boolean var3 = false;
            boolean var6 = var3;
            if (1 <= var4) {
               var6 = var3;
               if (var4 < 10) {
                  var6 = true;
               }
            }

            String var5;
            if (var6) {
               var5 = var1.level + ' ' + var4;
            } else if (var4 == 0) {
               var5 = var1.close;
            } else {
               var5 = " ";
            }

            GeneralAirData.rear_temperature = var5;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               this.this$0.updateAirActivity(this.$context, 1002);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2(this.this$0, this)};
         }
      });
      var2.append(7, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$FkvyuCUQScpwVHB2bxf_krqctmU(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_2(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$k63E_5U0uYhcEtL2jPQkZdcjuQk(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_4(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$vWVO9K957Ro_vEbOfw80096aw_o(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_6(var0, var1);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_temperature_unit");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 7 & 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("ford_range_unit");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 6 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("language_setup");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[3] >> 3 & 1));
            }

         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_304_atoms_lamp_setup");
            if (var2 != null) {
               var1.list.add(var2.setValue((var0.mCanbusInfoInt[4] & 15) - 1));
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
            return new OnParseListener[]{new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$7$$ExternalSyntheticLambda2(this.this$0, this)};
         }
      });
      var2.append(9, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], (byte)0, 128, 255, 16);
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }
      });
      var2.append(33, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            if (this.this$0.getMOriginalDeviceStatus() == 1 && this.isDataChange()) {
               var1 = this.this$0.mCanbusInfoInt[2];
               String var3 = "";
               String var2;
               if ((var1 >> 7 & 1) == 1) {
                  var2 = "SCAN";
               } else {
                  var2 = "";
               }

               GeneralOriginalCarDeviceData.runningState = var2;
               ArrayList var4 = new ArrayList();
               MsgMgr var5 = this.this$0;
               var1 = var5.mCanbusInfoInt[2] & 7;
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           if (var1 != 5) {
                              var2 = var3;
                           } else {
                              var2 = "FM-AST";
                           }
                        } else {
                           var2 = "FM2";
                        }
                     } else {
                        var2 = "FM1";
                     }
                  } else {
                     var2 = "AM2";
                  }
               } else {
                  var2 = "AM1";
               }

               var5.mOriginalRadioBand = var2;
               var4.add(new OriginalCarDeviceUpdateEntity(0, var5.getMOriginalRadioBand()));
               var4.add(new OriginalCarDeviceUpdateEntity(1, MsgMgr.initParsers$lambda_5$getFreq(var5, var5.mCanbusInfoInt[3] << 8 | var5.mCanbusInfoInt[4])));
               GeneralOriginalCarDeviceData.mList = (List)var4;
               if (StringsKt.contains$default((CharSequence)this.this$0.getMOriginalRadioBand(), (CharSequence)"FM", false, 2, (Object)null)) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 1, 0});
               } else if (StringsKt.contains$default((CharSequence)this.this$0.getMOriginalRadioBand(), (CharSequence)"AM", false, 2, (Object)null)) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 2, 0});
               }

               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(34, new Parser(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void parse(int var1) {
            int var2 = this.this$0.getMOriginalDeviceStatus();
            var1 = 1;
            if (var2 == 1 && this.isDataChange()) {
               ArrayList var7 = new ArrayList();
               MsgMgr var4 = this.this$0;

               for(var2 = var4.mCanbusInfoInt[2]; var1 < 7; ++var1) {
                  StringBuilder var5 = (new StringBuilder()).append('P').append(var1).append(" - ");
                  int[] var6 = var4.mCanbusInfoInt;
                  int var3 = var1 * 2;
                  var7.add((new SongListEntity(var5.append(MsgMgr.initParsers$lambda_5$getFreq(var4, var6[var3 + 1] << 8 | var4.mCanbusInfoInt[var3 + 2])).toString())).setSelected(Integer.valueOf(var2 & 7).equals(var1)));
               }

               GeneralOriginalCarDeviceData.songList = (List)var7;
               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      var2.append(35, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private final String getTime(int var1) {
            StringCompanionObject var3 = StringCompanionObject.INSTANCE;
            int var2 = var1 / 60;
            String var4 = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{var2 / 60, var2 % 60, var1 % 60}, 3));
            Intrinsics.checkNotNullExpressionValue(var4, "format(format, *args)");
            return var4;
         }

         public void parse(int var1) {
            if (this.this$0.getMOriginalDeviceStatus() == 2 && this.isDataChange()) {
               Context var4 = this.$context;
               var1 = this.this$0.mCanbusInfoInt[2];
               String var3;
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 128) {
                           switch (var1) {
                              case 5:
                                 var3 = "_143_0xA2_status6";
                                 break;
                              case 6:
                                 var3 = "_118_setting_value_91";
                                 break;
                              case 7:
                                 var3 = "_118_setting_value_92";
                                 break;
                              case 8:
                                 var3 = "_322_eject";
                                 break;
                              case 9:
                                 var3 = "_123_divice_status_9";
                                 break;
                              default:
                                 var3 = "null_value";
                           }
                        } else {
                           var3 = "_118_setting_value_89";
                        }
                     } else {
                        var3 = "_16_value_32";
                     }
                  } else {
                     var3 = "device_run_status_5";
                  }
               } else {
                  var3 = "device_run_status_4";
               }

               GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(var4, var3);
               boolean var2;
               if ((this.this$0.mCanbusInfoInt[3] >> 1 & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralOriginalCarDeviceData.rdm = var2;
               if ((this.this$0.mCanbusInfoInt[3] & 1) == 1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               GeneralOriginalCarDeviceData.rpt = var2;
               int[] var6 = new int[]{this.this$0.mCanbusInfoInt[4] << 8 | this.this$0.mCanbusInfoInt[5], this.this$0.mCanbusInfoInt[6] << 8 | this.this$0.mCanbusInfoInt[7]};
               GeneralOriginalCarDeviceData.startTime = this.getTime(var6[0]);
               GeneralOriginalCarDeviceData.endTime = this.getTime(var6[1]);
               var1 = var6[1];
               if (var1 == 0) {
                  GeneralOriginalCarDeviceData.progress = 0;
               } else {
                  GeneralOriginalCarDeviceData.progress = (int)((float)var6[0] * 100.0F / (float)var1);
               }

               ArrayList var5 = new ArrayList();
               MsgMgr var7 = this.this$0;
               var5.add(new OriginalCarDeviceUpdateEntity(0, String.valueOf(var7.mCanbusInfoInt[8] << 8 | var7.mCanbusInfoInt[9])));
               var1 = var7.mCanbusInfoInt[10];
               var5.add(new OriginalCarDeviceUpdateEntity(1, String.valueOf(var7.mCanbusInfoInt[11] | var1 << 8)));
               GeneralOriginalCarDeviceData.mList = (List)var5;
               this.this$0.updateOriginalCarDeviceActivity((Bundle)null);
            }

         }
      });
      RadarInfoUtil.mMinIsClose = true;
      var2.append(42, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$4EPf4pstxmc0AuNmfnZyFBSsEOg(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$BKxPiGr92Q53znvja6rMjaeK87w(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$GSQOP6iCaIPR0fyBOEnmtFDSNs4(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$ce87N_pqvrwOq_ZjGdbCn1OnLz4(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$e7sK0a2HV5BO7PSjKkMooNPInBU(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$hvpiXuvk9CHu0_5FjNEl5_9tWV8(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$vfjSS0LtRHOw_XMGXPqUCRe8Rxg(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$z6hJFjZlgsUlrEPGmErm630r4TA(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.LEFT_MID_REAR, var0.mCanbusInfoInt[2]);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.LEFT_REAR, var0.mCanbusInfoInt[3]);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.REAR_LEFT, var0.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.REAR_MID_LEFT, var0.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.RIGHT_MID_REAR, var0.mCanbusInfoInt[6]);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.RIGHT_REAR, var0.mCanbusInfoInt[7]);
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.REAR_RIGHT, var0.mCanbusInfoInt[8]);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.REAR_MID_RIGHT, var0.mCanbusInfoInt[9]);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$12$$ExternalSyntheticLambda7(this.this$0)};
         }
      });
      var2.append(43, new Parser(this, var1) {
         final Context $context;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$06zQySPisf4DSQ9pVBICnTNIelo(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Ge_CNkdw0Yce37WWZfszRQqUhUE(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$HYbC3vMH5UC0_RyiFscpRpnR8vU(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$PoxRoWpVDSxOu83t4ULrzTyrmEA(MsgMgr var0) {
            setOnParseListeners$lambda_7(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$YbF1jLHmncMb5SmkSZeKeJV2MPY(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$b__u7ANw6vH7nxGFAu0FnJd90EE(MsgMgr var0) {
            setOnParseListeners$lambda_0(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$t5AtRG43xTmx38Z_6v1MBlrhqGg(MsgMgr var0) {
            setOnParseListeners$lambda_1(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$yI9Q0_mi8tVBiYLg7yViKg0pK18(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
         }

         private static final void setOnParseListeners$lambda_0(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.LEFT_MID_FRONT, var0.mCanbusInfoInt[2]);
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.LEFT_FRONT, var0.mCanbusInfoInt[3]);
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.FRONT_LEFT, var0.mCanbusInfoInt[4]);
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.FRONT_MID_LEFT, var0.mCanbusInfoInt[5]);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, var0.mCanbusInfoInt[6]);
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.RIGHT_FRONT, var0.mCanbusInfoInt[7]);
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.FRONT_RIGHT, var0.mCanbusInfoInt[8]);
         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            MsgMgr.initParsers$lambda_5$setRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, var0.mCanbusInfoInt[9]);
         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               super.parse(var1);
               GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
               this.this$0.updateParkUi((Bundle)null, this.$context);
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda0(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda6(this.this$0), new MsgMgr$initParsers$1$13$$ExternalSyntheticLambda7(this.this$0)};
         }
      });
      var2.append(64, new Parser(this) {
         private final ArrayList list;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$_tfzGFHJMnfcdX50xkWrvL__z3A(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_17(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$1A1wrJbW_gQqTbbgBTFKib_fGIc(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_9(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$7EqJLFvvCrzS1uipk27bqv8stlw(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$7I50m3W1Yw3t_g7gd4c0M3gnWtk(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_11(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$DciDTsftuS_Ik_eip_qwUqQ7XhE(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_5(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$_ADsbdufqraHFiqN9Wo5665t3U4(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_14(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$h0lWahnvG1FtzBFjOysbCC_z8BM(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_3(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$pm4Aw3pY2mSSweqZN8mxt3BLA4M(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_7(var0, var1);
         }

         {
            this.this$0 = var1;
            this.list = new ArrayList();
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getDriver().setHigh(var0.mCanbusInfoInt[3]);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_driver_high");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getDriver().getHigh()).setProgress(var0.getDriver().getHigh() - 1));
            }

         }

         private static final void setOnParseListeners$lambda_11(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getPassenger().setLow(var0.mCanbusInfoInt[8]);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_passenger_low");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getPassenger().getLow()).setProgress(var0.getPassenger().getLow() - 1));
            }

         }

         private static final void setOnParseListeners$lambda_14(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getDriver().setBackseat(var0.mCanbusInfoInt[9] >> 2 & 3);
            var0.getDriver().setCushion(var0.mCanbusInfoInt[9] & 3);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_driver_backrest");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getDriver().getBackseat()));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_driver_cushion");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getDriver().getCushion()));
            }

         }

         private static final void setOnParseListeners$lambda_17(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getPassenger().setBackseat(var0.mCanbusInfoInt[10] >> 2 & 3);
            var0.getPassenger().setCushion(var0.mCanbusInfoInt[10] & 3);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_passenger_backrest");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getPassenger().getBackseat()));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_passenger_cushion");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getPassenger().getCushion()));
            }

         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getDriver().setMiddle(var0.mCanbusInfoInt[4]);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_driver_mid");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getDriver().getMiddle()).setProgress(var0.getDriver().getMiddle() - 1));
            }

         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getDriver().setLow(var0.mCanbusInfoInt[5]);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_driver_low");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getDriver().getLow()).setProgress(var0.getDriver().getLow() - 1));
            }

         }

         private static final void setOnParseListeners$lambda_7(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getPassenger().setHigh(var0.mCanbusInfoInt[6]);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_passenger_high");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getPassenger().getHigh()).setProgress(var0.getPassenger().getHigh() - 1));
            }

         }

         private static final void setOnParseListeners$lambda_9(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            var0.getPassenger().setMiddle(var0.mCanbusInfoInt[7]);
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_94_passenger_mid");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.getPassenger().getMiddle()).setProgress(var0.getPassenger().getMiddle() - 1));
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
            return new OnParseListener[]{null, new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda1(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda2(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda3(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda4(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda5(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda6(this.this$0, this), new MsgMgr$initParsers$1$14$$ExternalSyntheticLambda7(this.this$0, this)};
         }
      });
      var2.append(80, new Parser(this, var1) {
         final Context $context;
         private final Map mUpdateBeamArray;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$nMhGjZ1hFq3j6N1eCQmECBWyszg(ActivePark var0, MsgMgr var1, Object var2, Context var3) {
            parse$lambda_2$lambda_1(var0, var1, var2, var3);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.mUpdateBeamArray = MapsKt.mapOf(new Pair[]{TuplesKt.to(2, new ActivePark.UpdateBeam(2131767286, 2131762424)), TuplesKt.to(3, new ActivePark.UpdateBeam(2131767017, 2131767019, ActivePark.ActiveParkElement.WARNING)), TuplesKt.to(4, new ActivePark.UpdateBeam(2131767376, 2131762424)), TuplesKt.to(5, new ActivePark.UpdateBeam(2131767017, 2131762425, ActivePark.ActiveParkElement.FLAG)), TuplesKt.to(6, new ActivePark.UpdateBeam(2131767017, 2131768616, 2131762426, ActivePark.ActiveParkElement.WARNING)), TuplesKt.to(8, new ActivePark.UpdateBeam(2131767017, 2131762427, 2131762428, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(9, new ActivePark.UpdateBeam(2131767017, 2131768374, 2131762426, ActivePark.ActiveParkElement.WARNING)), TuplesKt.to(10, new ActivePark.UpdateBeam(2131767286, 2131762430, ActivePark.ActiveParkElement.FLAG)), TuplesKt.to(11, new ActivePark.UpdateBeam(2131767376, 2131762430, ActivePark.ActiveParkElement.FLAG)), TuplesKt.to(12, new ActivePark.UpdateBeam(2131767285, 2131762431, ActivePark.ActiveParkElement.FLAG)), TuplesKt.to(13, new ActivePark.UpdateBeam(2131767017, 2131762432, ActivePark.ActiveParkElement.WARNING)), TuplesKt.to(14, new ActivePark.UpdateBeam(2131767017, 2131762433, ActivePark.ActiveParkElement.WARNING)), TuplesKt.to(15, new ActivePark.UpdateBeam(2131767286, 2131762434, 2131762435, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(16, new ActivePark.UpdateBeam(2131767286, 2131762436, 2131762437, ActivePark.ActiveParkElement.ARROW_FRONT)), TuplesKt.to(17, new ActivePark.UpdateBeam(2131767286, 2131762434, 2131762438, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(18, new ActivePark.UpdateBeam(2131767286, 2131762439, 2131762437, ActivePark.ActiveParkElement.ARROW_BACK)), TuplesKt.to(19, new ActivePark.UpdateBeam(2131767376, 2131762434, 2131762435, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(20, new ActivePark.UpdateBeam(2131767376, 2131762436, 2131762437, ActivePark.ActiveParkElement.ARROW_FRONT)), TuplesKt.to(21, new ActivePark.UpdateBeam(2131767376, 2131762434, 2131762438, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(22, new ActivePark.UpdateBeam(2131767376, 2131762439, 2131762437, ActivePark.ActiveParkElement.ARROW_BACK)), TuplesKt.to(23, new ActivePark.UpdateBeam(2131767285, 2131762434, 2131762435, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(24, new ActivePark.UpdateBeam(2131767285, 2131762436, 2131762437, ActivePark.ActiveParkElement.ARROW_FRONT)), TuplesKt.to(25, new ActivePark.UpdateBeam(2131767285, 2131762434, 2131762438, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(26, new ActivePark.UpdateBeam(2131767285, 2131762439, 2131762437, ActivePark.ActiveParkElement.ARROW_BACK)), TuplesKt.to(27, new ActivePark.UpdateBeam(2131767285, 2131762440, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_RIGHT, ActivePark.ActiveParkElement.ARROW_LEFTT})), TuplesKt.to(28, new ActivePark.UpdateBeam(2131767285, 2131762441, 2131762438, ActivePark.ActiveParkElement.ARROW_RIGHT)), TuplesKt.to(29, new ActivePark.UpdateBeam(2131767285, 2131762441, 2131762438, ActivePark.ActiveParkElement.ARROW_LEFTT)), TuplesKt.to(30, new ActivePark.UpdateBeam(2131767285, 2131762441, 2131762435, ActivePark.ActiveParkElement.ARROW_RIGHT)), TuplesKt.to(31, new ActivePark.UpdateBeam(2131767285, 2131762441, 2131762435, ActivePark.ActiveParkElement.ARROW_LEFTT)), TuplesKt.to(32, new ActivePark.UpdateBeam(2131767286, 2131762442, 2131762442, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_RIGHT})), TuplesKt.to(33, new ActivePark.UpdateBeam(2131767286, 2131762444, 2131762445, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_LEFTT})), TuplesKt.to(50, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762447, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_RIGHT, ActivePark.ActiveParkElement.WALL_PARALLEL_RIGHT, ActivePark.ActiveParkElement.SPACE_PARALLEL_RIGHT, ActivePark.ActiveParkElement.FIND_RIGHT})), TuplesKt.to(51, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762447, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_LEFTT, ActivePark.ActiveParkElement.WALL_PARALLEL_LEFTT, ActivePark.ActiveParkElement.SPACE_PARALLEL_LEFTT, ActivePark.ActiveParkElement.FIND_LEFTT})), TuplesKt.to(66, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762434, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.RADAR_RIGHT, ActivePark.ActiveParkElement.WALL_PARALLEL_RIGHT, ActivePark.ActiveParkElement.SPACE_PARALLEL_RIGHT, ActivePark.ActiveParkElement.FIND_RIGHT})), TuplesKt.to(67, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762434, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.RADAR_LEFTT, ActivePark.ActiveParkElement.WALL_PARALLEL_LEFTT, ActivePark.ActiveParkElement.SPACE_PARALLEL_LEFTT, ActivePark.ActiveParkElement.FIND_LEFTT})), TuplesKt.to(80, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762448, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(82, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762448, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.WALL_PARALLEL_RIGHT, ActivePark.ActiveParkElement.SPACE_PARALLEL_RIGHT, ActivePark.ActiveParkElement.LINE_PARALLEL_RIGHT})), TuplesKt.to(83, new ActivePark.UpdateBeam(2131767286, 2131762446, 2131762448, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.WALL_PARALLEL_LEFTT, ActivePark.ActiveParkElement.SPACE_PARALLEL_LEFTT, ActivePark.ActiveParkElement.LINE_PARALLEL_LEFTT})), TuplesKt.to(96, new ActivePark.UpdateBeam(2131767376, 2131762442, 2131762443, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_RIGHT})), TuplesKt.to(97, new ActivePark.UpdateBeam(2131767376, 2131762444, 2131762445, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_LEFTT})), TuplesKt.to(130, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762447, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_RIGHT, ActivePark.ActiveParkElement.WALL_VERTICAL_RIGHT_FORWARD, ActivePark.ActiveParkElement.SPACE_VERTICAL_RIGHT, ActivePark.ActiveParkElement.FIND_RIGHT})), TuplesKt.to(131, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762447, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.ARROW_FRONT, ActivePark.ActiveParkElement.RADAR_LEFTT, ActivePark.ActiveParkElement.WALL_VERTICAL_LEFTT_FORWARD, ActivePark.ActiveParkElement.SPACE_VERTICAL_LEFTT, ActivePark.ActiveParkElement.FIND_LEFTT})), TuplesKt.to(162, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762434, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.RADAR_RIGHT, ActivePark.ActiveParkElement.WALL_VERTICAL_RIGHT_STOP_LINE, ActivePark.ActiveParkElement.SPACE_VERTICAL_RIGHT, ActivePark.ActiveParkElement.FIND_RIGHT})), TuplesKt.to(163, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762434, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.RADAR_LEFTT, ActivePark.ActiveParkElement.WALL_VERTICAL_LEFTT_STOP_LINE, ActivePark.ActiveParkElement.SPACE_VERTICAL_LEFTT, ActivePark.ActiveParkElement.FIND_LEFTT})), TuplesKt.to(192, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762448, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(194, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762448, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.WALL_VERTICAL_RIGHT_STOP_LINE, ActivePark.ActiveParkElement.SPACE_VERTICAL_RIGHT, ActivePark.ActiveParkElement.LINE_VERTICAL_RIGHT})), TuplesKt.to(195, new ActivePark.UpdateBeam(2131767376, 2131762446, 2131762448, new ActivePark.ActiveParkElement[]{ActivePark.ActiveParkElement.STOP, ActivePark.ActiveParkElement.WALL_VERTICAL_LEFTT_STOP_LINE, ActivePark.ActiveParkElement.SPACE_VERTICAL_LEFTT, ActivePark.ActiveParkElement.LINE_VERTICAL_LEFTT})), TuplesKt.to(240, new ActivePark.UpdateBeam(2131767285, 2131761341, ActivePark.ActiveParkElement.ARROW_RIGHT)), TuplesKt.to(241, new ActivePark.UpdateBeam(2131767285, 2131761332, ActivePark.ActiveParkElement.ARROW_LEFTT)), TuplesKt.to(246, new ActivePark.UpdateBeam(2131767286, 2131762449, ActivePark.ActiveParkElement.STOP)), TuplesKt.to(247, new ActivePark.UpdateBeam(2131767376, 2131762449, ActivePark.ActiveParkElement.STOP))});
         }

         private static final void parse$lambda_2$lambda_1(ActivePark var0, MsgMgr var1, Object var2, Context var3) {
            Intrinsics.checkNotNullParameter(var0, "$this_run");
            Intrinsics.checkNotNullParameter(var1, "this$0");
            Intrinsics.checkNotNullParameter(var2, "this$1");
            Intrinsics.checkNotNullParameter(var3, "$context");
            boolean var4;
            if (var1.mCanbusInfoInt[3] != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var0.setActiveParkActive(var4);
            ActivePark.UpdateBeam var5 = (ActivePark.UpdateBeam)var2.mUpdateBeamArray.get(var1.mCanbusInfoInt[3]);
            if (var5 != null) {
               var0.updateMessageAndImage(var5);
            }

            var0.updateParkTypeStatus(var1.mCanbusInfoInt[2]);
            if (CommUtil.isMiscReverse()) {
               var1.updateParkUi((Bundle)null, var3);
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               Log.i("_322_MsgMgr", "parse: 0x50");
               ActivePark var2 = this.this$0.getActivePark(this.$context);
               MsgMgr var3 = this.this$0;
               Context var4 = this.$context;
               Log.i("_322_MsgMgr", "parse: active park");
               var3.runOnUi(new MsgMgr$initParsers$1$15$$ExternalSyntheticLambda0(var2, var3, this, var4));
            }

         }
      });
      var2.append(96, new Parser(this, var1) {
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
            var6.setHaveSongList(true);
            var6.setRowTopBtnAction(new String[]{"fm", "am", "band"});
            var6.setRowBottomBtnAction(new String[]{"up", "prev_disc", "next_disc", "down"});
            ArrayList var7 = new ArrayList();
            var7.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
            var7.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", ""));
            var6.setItems((List)var7);
            Unit var8 = Unit.INSTANCE;
            var5.put(1, var6);
            var6 = new OriginalCarDevicePageUiSet();
            var6.setHavePlayTimeSeekBar(true);
            var6.setHaveSongList(false);
            var6.setRowTopBtnAction(new String[]{"rdm", "rpt"});
            var6.setRowBottomBtnAction(new String[]{"left", "up", "play", "pause", "down", "right"});
            var7 = new ArrayList();
            var7.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_track", ""));
            var7.add(new OriginalCarDevicePageUiSet.Item("music_list", "total_file", ""));
            var6.setItems((List)var7);
            var8 = Unit.INSTANCE;
            var5.append(2, var6);
            var6 = new OriginalCarDevicePageUiSet();
            var6.setHavePlayTimeSeekBar(false);
            var6.setHaveSongList(false);
            var6.setRowTopBtnAction((String[])null);
            var6.setRowBottomBtnAction((String[])null);
            var6.setItems(CollectionsKt.emptyList());
            var5.append(0, var6);
            var5.append(3, var6);
            this.originalPageArray = var5;
         }

         public void parse(int var1) {
            int var3 = this.this$0.mCanbusInfoInt[2] & 7;
            MsgMgr var7 = this.this$0;
            Context var6 = this.$context;
            byte var2 = 0;
            boolean var8;
            if (var3 >= 0 && var3 < 4) {
               var8 = true;
            } else {
               var8 = false;
            }

            if (var8 && !Integer.valueOf(var3).equals(var7.getMOriginalDeviceStatus())) {
               var7.mOriginalDeviceStatus = var3;
               String var5;
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 != 3) {
                           var5 = " ";
                        } else {
                           var5 = "NAVI";
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 3, 0});
                        var5 = "CD";
                     }
                  } else {
                     var5 = "RADIO";
                  }
               } else {
                  var5 = "OFF";
               }

               GeneralOriginalCarDeviceData.cdStatus = var5;
               OriginalCarDevicePageUiSet var9 = (OriginalCarDevicePageUiSet)this.originalPageArray.get(var3);
               if (var9 != null) {
                  Intrinsics.checkNotNullExpressionValue(var9, "originalPageArray[this]");
                  this.pageUiSet.setHavePlayTimeSeekBar(var9.isHavePlayTimeSeekBar());
                  this.pageUiSet.setHaveSongList(var9.isHaveSongList());
                  this.pageUiSet.setRowTopBtnAction(var9.getRowTopBtnAction());
                  this.pageUiSet.setRowBottomBtnAction(var9.getRowBottomBtnAction());
                  this.pageUiSet.setItems(var9.getItems());
               }

               GeneralOriginalCarDeviceData.runningState = " ";
               ArrayList var10 = new ArrayList();
               int var4 = this.pageUiSet.getItems().size();

               for(var1 = var2; var1 < var4; ++var1) {
                  var10.add(new OriginalCarDeviceUpdateEntity(var1, ""));
               }

               GeneralOriginalCarDeviceData.mList = (List)var10;
               Bundle var11 = new Bundle();
               var11.putBoolean("bundle_key_orinal_init_view", true);
               var7.updateOriginalCarDeviceActivity(var11);
               if (var3 != 0) {
                  if (var3 == 1 || var3 == 2) {
                     var7.enterAuxIn2(var6, Constant.OriginalDeviceActivity);
                     return;
                  }

                  if (var3 != 3) {
                     return;
                  }
               }

               var7.exitAuxIn2();
            }

         }
      });
      var2.append(112, new Parser(this, var1) {
         final Context $context;
         private int[] amplifierData;
         private final ArrayList list;
         private int[] settingData;
         final MsgMgr this$0;

         // $FF: synthetic method
         public static void $r8$lambda$67qtg_uiJ92S9Iv_PUI_yDjPFsM(MsgMgr var0) {
            setOnParseListeners$lambda_5(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$Ac9vy_4zkpbQ30mCQnLroyp1hkU(MsgMgr var0) {
            setOnParseListeners$lambda_6(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$K_Sanse4CSQo73CgGEPB5SBnSV0(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_9(var0, var1);
         }

         // $FF: synthetic method
         public static void $r8$lambda$R750UMiERclT1_5whHLsKUiu17E(MsgMgr var0) {
            setOnParseListeners$lambda_4(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$VPAGBa4oUBiWTOQtRJCPqy4vAJc(MsgMgr var0) {
            setOnParseListeners$lambda_2(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$qrRwhJ1v1dgHgaA5yMmoikv_oDw(MsgMgr var0) {
            setOnParseListeners$lambda_3(var0);
         }

         // $FF: synthetic method
         public static void $r8$lambda$r9Iv7j1xgZvvd1rzMYCeePfc1og(MsgMgr var0, Object var1) {
            setOnParseListeners$lambda_1(var0, var1);
         }

         {
            this.this$0 = var1;
            this.$context = var2;
            this.list = new ArrayList();
         }

         private final boolean isAmplifierChange() {
            int[] var2 = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 2, 8);
            boolean var1 = false;
            var2[0] &= 127;
            if (!Arrays.equals(this.amplifierData, var2)) {
               this.amplifierData = (int[])var2.clone();
               var1 = true;
            }

            return var1;
         }

         private final boolean isSettingChange() {
            int[] var3 = new int[2];
            int var1 = this.this$0.mCanbusInfoInt[2];
            boolean var2 = false;
            var3[0] = var1 & 128;
            var3[1] = this.this$0.mCanbusInfoInt[8];
            if (!Arrays.equals(this.settingData, var3)) {
               this.settingData = (int[])var3.clone();
               var2 = true;
            }

            return var2;
         }

         private static final void setOnParseListeners$lambda_1(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_197_amplifier_mute");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[2] >> 7 & 1));
            }

            GeneralAmplifierData.volume = var0.mCanbusInfoInt[2] & 127;
         }

         private static final void setOnParseListeners$lambda_2(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.leftRight = var0.mCanbusInfoInt[3] - 3 - 7;
         }

         private static final void setOnParseListeners$lambda_3(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.frontRear = -(var0.mCanbusInfoInt[4] - 3 - 7);
         }

         private static final void setOnParseListeners$lambda_4(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandBass = var0.mCanbusInfoInt[5] - 3;
         }

         private static final void setOnParseListeners$lambda_5(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandMiddle = var0.mCanbusInfoInt[6] - 3;
         }

         private static final void setOnParseListeners$lambda_6(MsgMgr var0) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            GeneralAmplifierData.bandTreble = var0.mCanbusInfoInt[7] - 3;
         }

         private static final void setOnParseListeners$lambda_9(MsgMgr var0, Object var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            Intrinsics.checkNotNullParameter(var1, "this$1");
            SettingUpdateEntity var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_279_sound");
            if (var2 != null) {
               var1.list.add(var2.setValue((var0.mCanbusInfoInt[8] >> 2 & 3) + 1));
            }

            var2 = (SettingUpdateEntity)var0.mSettingItemIndexHashMap.get("_118_setting_title_97");
            if (var2 != null) {
               var1.list.add(var2.setValue(var0.mCanbusInfoInt[8] & 3));
            }

         }

         public void parse(int var1) {
            if (this.isDataChange()) {
               MsgMgr var6 = this.this$0;
               byte[] var5 = Arrays.copyOf(var6.mCanbusInfoByte, 9);
               Intrinsics.checkNotNullExpressionValue(var5, "copyOf(this, newSize)");
               var6.mAmplifierData = var5;
               Context var10 = this.$context;
               StringBuffer var8 = new StringBuffer();
               byte[] var7 = this.this$0.getMAmplifierData();
               int var2 = 0;

               for(int var3 = var7.length; var2 < var3; ++var2) {
                  byte var4 = var7[var2];
                  var8.append("" + var4 + ',');
               }

               Unit var11 = Unit.INSTANCE;
               SharePreUtil.setStringValue(var10, "sahre_322_amplifier_datas", var8.toString());
               this.list.clear();
               super.parse(var1);
               if (this.isAmplifierChange()) {
                  MsgMgr var9 = this.this$0;
                  var9.saveAmplifierData(this.$context, var9.mCanId);
                  this.this$0.updateAmplifierActivity((Bundle)null);
               }

               if (this.isSettingChange()) {
                  this.this$0.updateGeneralSettingData((List)this.list);
                  this.this$0.updateSettingActivity((Bundle)null);
               }
            }

         }

         public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda0(this.this$0, this), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda1(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda2(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda3(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda4(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda5(this.this$0), new MsgMgr$initParsers$1$17$$ExternalSyntheticLambda6(this.this$0, this)};
         }
      });
      var2.append(113, new Parser(this, var1) {
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
   }

   private static final String initParsers$lambda_5$getFreq(MsgMgr var0, int var1) {
      String var2;
      if (StringsKt.contains$default((CharSequence)var0.mOriginalRadioBand, (CharSequence)"FM", false, 2, (Object)null)) {
         var2 = (float)var1 / (float)100 + " MHz";
      } else if (StringsKt.contains$default((CharSequence)var0.mOriginalRadioBand, (CharSequence)"AM", false, 2, (Object)null)) {
         var2 = var1 + " KHz";
      } else {
         var2 = "";
      }

      return var2;
   }

   private static final void initParsers$lambda_5$setRadarData(Constant.RadarLocation var0, int var1) {
      RadarInfoUtil.setGeneralRadarData(var0, var1, 7);
   }

   private final void resetAfterMute() {
      CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte)SharePreUtil.getIntValue(this.mContext, this.AMP_VOL_TAG_322, 15), (byte)SharePreUtil.getIntValue(this.mContext, this.AMP_LR_TAG_322, 10), (byte)SharePreUtil.getIntValue(this.mContext, this.AMP_FR_TAG_322, 10), (byte)SharePreUtil.getIntValue(this.mContext, this.AMP_BASS_TAG_322, 10), (byte)SharePreUtil.getIntValue(this.mContext, this.AMP_MID_TAG_322, 10), (byte)SharePreUtil.getIntValue(this.mContext, this.AMP_TREBLE_TAG_322, 10), 8});
   }

   private final void toMuteAmp(boolean var1) {
      if (var1) {
         SharePreUtil.setIntValue(this.mContext, this.AMP_VOL_TAG_322, GeneralAmplifierData.volume);
         SharePreUtil.setIntValue(this.mContext, this.AMP_LR_TAG_322, GeneralAmplifierData.leftRight);
         SharePreUtil.setIntValue(this.mContext, this.AMP_FR_TAG_322, GeneralAmplifierData.frontRear);
         SharePreUtil.setIntValue(this.mContext, this.AMP_BASS_TAG_322, GeneralAmplifierData.bandBass);
         SharePreUtil.setIntValue(this.mContext, this.AMP_MID_TAG_322, GeneralAmplifierData.bandMiddle);
         SharePreUtil.setIntValue(this.mContext, this.AMP_TREBLE_TAG_322, GeneralAmplifierData.bandTreble);
         CanbusMsgSender.sendMsg(new byte[]{22, -109, 0, (byte)(GeneralAmplifierData.leftRight + 10), (byte)(GeneralAmplifierData.frontRear + 10), (byte)(GeneralAmplifierData.bandBass + 3), (byte)(GeneralAmplifierData.bandMiddle + 3), (byte)(GeneralAmplifierData.bandTreble + 3), 8});
      } else {
         this.resetAfterMute();
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      GeneralTireData.isHaveSpareTire = false;
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._322.UiMgr");
      this.mUiMgr = (UiMgr)var2;
      this.initItemsIndexHashMap(var1);
      this.initParsers(var1);
      this.mActivePark = this.getActivePark(var1);
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
      CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte)var10, (byte)var8, (byte)var6, 0});
      this.mHandler.postDelayed(new MsgMgr$$ExternalSyntheticLambda0(), 100L);
   }

   public final String getAMP_BASS_TAG_322() {
      return this.AMP_BASS_TAG_322;
   }

   public final String getAMP_FR_TAG_322() {
      return this.AMP_FR_TAG_322;
   }

   public final String getAMP_LR_TAG_322() {
      return this.AMP_LR_TAG_322;
   }

   public final String getAMP_MID_TAG_322() {
      return this.AMP_MID_TAG_322;
   }

   public final String getAMP_TREBLE_TAG_322() {
      return this.AMP_TREBLE_TAG_322;
   }

   public final String getAMP_VOL_TAG_322() {
      return this.AMP_VOL_TAG_322;
   }

   public final ActivePark getActivePark(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      if (this.mActivePark == null) {
         this.mActivePark = new ActivePark(var1);
      }

      ActivePark var2 = this.mActivePark;
      Intrinsics.checkNotNull(var2);
      return var2;
   }

   public final SeatData getDriver() {
      return this.driver;
   }

   public final byte[] getMAmplifierData() {
      return this.mAmplifierData;
   }

   public final int getMOriginalDeviceStatus() {
      return this.mOriginalDeviceStatus;
   }

   public final String getMOriginalRadioBand() {
      return this.mOriginalRadioBand;
   }

   public final SeatData getPassenger() {
      return this.passenger;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
      this.resetAfterMute();
   }

   public void sourceSwitchChange(String var1) {
      Log.i("_322_MsgMgr", "sourceSwitchChange: " + var1);
      if (!Intrinsics.areEqual((Object)"AUX2", (Object)var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -112, 4, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

      this.toMuteAmp(var1);
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/MsgMgr$Companion;", "", "()V", "AMPLIFIER_DATA_MID", "", "AMPLIFIER_DATA_MIN", "ORIGINAL_DEVICE_STATUS_CD", "ORIGINAL_DEVICE_STATUS_FM", "ORIGINAL_DEVICE_STATUS_NAVI", "ORIGINAL_DEVICE_STATUS_OFF", "RADAR_RANGE", "SHARE_322_AMPLIFIER_DATAS", "", "TAG", "CanBusInfo_release"},
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
      d2 = {"Lcom/hzbhd/canbus/car/_322/MsgMgr$Parser;", "", "msg", "", "(Lcom/hzbhd/canbus/car/_322/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"},
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
         Log.i("_322_MsgMgr", "Parser: " + var2 + " length " + var3);
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

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006!"},
      d2 = {"Lcom/hzbhd/canbus/car/_322/MsgMgr$SeatData;", "", "high", "", "middle", "low", "backseat", "cushion", "(IIIII)V", "getBackseat", "()I", "setBackseat", "(I)V", "getCushion", "setCushion", "getHigh", "setHigh", "getLow", "setLow", "getMiddle", "setMiddle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SeatData {
      private int backseat;
      private int cushion;
      private int high;
      private int low;
      private int middle;

      public SeatData() {
         this(0, 0, 0, 0, 0, 31, (DefaultConstructorMarker)null);
      }

      public SeatData(int var1, int var2, int var3, int var4, int var5) {
         this.high = var1;
         this.middle = var2;
         this.low = var3;
         this.backseat = var4;
         this.cushion = var5;
      }

      // $FF: synthetic method
      public SeatData(int var1, int var2, int var3, int var4, int var5, int var6, DefaultConstructorMarker var7) {
         if ((var6 & 1) != 0) {
            var1 = 0;
         }

         if ((var6 & 2) != 0) {
            var2 = 0;
         }

         if ((var6 & 4) != 0) {
            var3 = 0;
         }

         if ((var6 & 8) != 0) {
            var4 = 0;
         }

         if ((var6 & 16) != 0) {
            var5 = 0;
         }

         this(var1, var2, var3, var4, var5);
      }

      // $FF: synthetic method
      public static SeatData copy$default(SeatData var0, int var1, int var2, int var3, int var4, int var5, int var6, Object var7) {
         if ((var6 & 1) != 0) {
            var1 = var0.high;
         }

         if ((var6 & 2) != 0) {
            var2 = var0.middle;
         }

         if ((var6 & 4) != 0) {
            var3 = var0.low;
         }

         if ((var6 & 8) != 0) {
            var4 = var0.backseat;
         }

         if ((var6 & 16) != 0) {
            var5 = var0.cushion;
         }

         return var0.copy(var1, var2, var3, var4, var5);
      }

      public final int component1() {
         return this.high;
      }

      public final int component2() {
         return this.middle;
      }

      public final int component3() {
         return this.low;
      }

      public final int component4() {
         return this.backseat;
      }

      public final int component5() {
         return this.cushion;
      }

      public final SeatData copy(int var1, int var2, int var3, int var4, int var5) {
         return new SeatData(var1, var2, var3, var4, var5);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof SeatData)) {
            return false;
         } else {
            SeatData var2 = (SeatData)var1;
            if (this.high != var2.high) {
               return false;
            } else if (this.middle != var2.middle) {
               return false;
            } else if (this.low != var2.low) {
               return false;
            } else if (this.backseat != var2.backseat) {
               return false;
            } else {
               return this.cushion == var2.cushion;
            }
         }
      }

      public final int getBackseat() {
         return this.backseat;
      }

      public final int getCushion() {
         return this.cushion;
      }

      public final int getHigh() {
         return this.high;
      }

      public final int getLow() {
         return this.low;
      }

      public final int getMiddle() {
         return this.middle;
      }

      public int hashCode() {
         return (((Integer.hashCode(this.high) * 31 + Integer.hashCode(this.middle)) * 31 + Integer.hashCode(this.low)) * 31 + Integer.hashCode(this.backseat)) * 31 + Integer.hashCode(this.cushion);
      }

      public final void setBackseat(int var1) {
         this.backseat = var1;
      }

      public final void setCushion(int var1) {
         this.cushion = var1;
      }

      public final void setHigh(int var1) {
         this.high = var1;
      }

      public final void setLow(int var1) {
         this.low = var1;
      }

      public final void setMiddle(int var1) {
         this.middle = var1;
      }

      public String toString() {
         return "SeatData(high=" + this.high + ", middle=" + this.middle + ", low=" + this.low + ", backseat=" + this.backseat + ", cushion=" + this.cushion + ')';
      }
   }
}
