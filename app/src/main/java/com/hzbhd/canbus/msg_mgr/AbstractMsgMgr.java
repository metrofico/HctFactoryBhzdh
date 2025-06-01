package com.hzbhd.canbus.msg_mgr;

import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.activity.HybirdActivity;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.activity.OriginalCarDeviceActivity;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.activity.TirePressureActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.constant.RadioConstantsDef;
import com.hzbhd.canbus.adapter.util.CrashReportUtils;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.adapter.util.SystemUtils;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.receiver.AMapBroadcastReceiver;
import com.hzbhd.canbus.receiver.DateTimeReceiver;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.ContextUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.PKeyUtil;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.view.AirPageWindowView;
import com.hzbhd.canbus.view.AirSmallView;
import com.hzbhd.canbus.view.Bubble;
import com.hzbhd.canbus.view.DisplayMsgView;
import com.hzbhd.canbus.view.DoorView;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMsgMgr implements MsgMgrInterface {
   private static final String SHARE_AMPLIFIER_BALANCE = "share_amplifier_balance_";
   private static final String SHARE_AMPLIFIER_BASS = "share_amplifier_bass_";
   private static final String SHARE_AMPLIFIER_FADE = "share_amplifier_fade_";
   private static final String SHARE_AMPLIFIER_HEAVY_BASS = "share_amplifier_heavy_bass_";
   private static final String SHARE_AMPLIFIER_MIDDLE = "share_amplifier_middle_";
   private static final String SHARE_AMPLIFIER_TREBLE = "share_amplifier_treble_";
   private static final String SHARE_AMPLIFIER_VOLUME = "share_amplifier_volume_";
   private static final String TAG = "AbstractMsgMgr";
   public final int DATA_TYPE = 1;
   private DecimalFormat df = new DecimalFormat("00");
   private boolean isReverse = false;
   private AMapBroadcastReceiver mAMapBroadcastReceiver;
   private AbstractBaseActivity mActivity;
   private AirSmallView mAirSmallView;
   private BackCameraUiService mBackCameraUiService;
   private Bubble mBubble;
   private CallBackInterface mCallBackInterface;
   private byte[] mCanbusAirInfoCopy;
   private final SparseArray mCanbusDataArray = new SparseArray();
   private byte[] mCanbusDoorInfoCopy;
   private DisplayMsgView mDisplayMsgView;
   private DoorView mDoorView;
   private Handler mHandler;
   private long mRealTime;
   private Map mSettingMsgMap;
   private String mTempUnitC;
   private String mTempUnitF;
   int nowLightLevel = 5;

   public AbstractMsgMgr() {
      this.registerReverse();
   }

   private void bindBackCameraUiService(Context var1) {
      var1.bindService(new Intent(var1, BackCameraUiService.class), new ServiceConnection(this, var1) {
         final AbstractMsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onServiceConnected(ComponentName var1, IBinder var2) {
            if (var1 != null && var2 != null) {
               LogUtil.showLog("CanbusMsgService bindBackCameraUiService success");
               BackCameraUiService var3 = ((BackCameraUiService.MyBinder)var2).getService();
               this.this$0.getBackCameraUiService(var3);
            } else {
               LogUtil.showLog("CanbusMsgService bindBackCameraUiService empty");
            }

         }

         public void onServiceDisconnected(ComponentName var1) {
            this.this$0.bindBackCameraUiService(this.val$context);
         }
      }, 1);
   }

   private boolean canSupportMPEG() {
      return FutureUtil.instance.supportDisc();
   }

   private void checkIsAppHandleKey(Context var1) {
      int var2 = FutureUtil.instance.getCanIsAppHandleKey();
      String var4 = TAG;
      Log.i(var4, "checkIsAppHandleKey: ljq, handle key from setting: " + var2);
      CanTypeAllEntity var5 = (CanTypeAllEntity)CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(var1)).getList().get(0);
      if (var5 != null) {
         int var3 = var5.getIs_app_handle_key();
         Log.i(var4, "checkIsAppHandleKey: ljq, handle key front entity: " + var3);
         if (var2 != var3) {
            FutureUtil.instance.setCanIsAppHandleKey(var3);
         }
      }

   }

   // $FF: synthetic method
   static void lambda$switchFCamera$0() {
      (new Instrumentation()).sendKeyDownUpSync(4);
   }

   private void registerReverse() {
      int var1 = ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", new IShareIntListener(this) {
         final AbstractMsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onInt(int var1) {
            AbstractMsgMgr var4 = this.this$0;
            boolean var3 = true;
            boolean var2 = var3;
            if (var1 != 1) {
               if (var1 == 2) {
                  var2 = var3;
               } else {
                  var2 = false;
               }
            }

            var4.isReverse = var2;
            var4 = this.this$0;
            var4.reverseStateChange(var4.isReverse);
         }
      });
      boolean var3 = true;
      boolean var2 = var3;
      if (var1 != 1) {
         if (var1 == 2) {
            var2 = var3;
         } else {
            var2 = false;
         }
      }

      this.isReverse = var2;
   }

   private void reportDateAndTime(Context var1) {
      (new DateTimeReceiver()).reportDateAndTime(var1);
   }

   private int[] subBytes(int[] var1, int var2) {
      int[] var3 = new int[var2];
      System.arraycopy(var1, 2, var3, 0, var2 + 2 - 2);
      return var3;
   }

   public void aMapCallBack(Bundle var1) {
   }

   public void afterServiceNormalSetting(Context var1) {
      CrashReportUtils.openCanBusBugly(var1, true);
   }

   public void atvDestdroy() {
   }

   public void atvInfoChange() {
   }

   public void auxInDestdroy() {
   }

   public void auxInInfoChange() {
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
   }

   public void btMusicInfoChange() {
   }

   public void btMusiceDestdroy() {
   }

   public void btPhoneCallLogInfoChange(int var1, int var2, String var3) {
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
   }

   public void cameraDestroy() {
   }

   public void cameraInfoChange() {
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
   }

   protected void changeBandAm1() {
      if (!"AM1".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
         Bundle var1 = new Bundle();
         var1.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_AM1.ordinal());
         SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, var1);
      }

   }

   protected void changeBandAm2() {
      if (!"AM2".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
         Bundle var1 = new Bundle();
         var1.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_AM2.ordinal());
         SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, var1);
      }

   }

   protected void changeBandFm1() {
      if (!"FM1".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
         Bundle var1 = new Bundle();
         var1.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_FM1.ordinal());
         SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, var1);
      }

   }

   protected void changeBandFm2() {
      if (!"FM2".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
         Bundle var1 = new Bundle();
         var1.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_FM2.ordinal());
         SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, var1);
      }

   }

   protected void changeBandFm3() {
      if (!"FM3".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
         Bundle var1 = new Bundle();
         var1.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_FM3.ordinal());
         SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, var1);
      }

   }

   public void clearCanbusAirInfoCopy() {
      this.mCanbusAirInfoCopy = null;
   }

   protected void compoundKey(Context var1, int[] var2, int var3) {
      RealKeyUtil.compoundKey(var1, var2, var3);
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
   }

   public boolean customLongClick(Context var1, int var2) {
      return false;
   }

   public boolean customShortClick(Context var1, int var2) {
      return false;
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
   }

   public void destroyCommand() {
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
   }

   public void dtvInfoChange() {
   }

   protected void enterAuxIn2() {
      CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
   }

   protected void enterAuxIn2(Context var1, ComponentName var2) {
      Intent var3 = new Intent();
      var3.setFlags(268435456);
      var3.setComponent(var2);
      var1.startActivity(var3);
   }

   protected void enterNoSource() {
      CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CANOFF, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
   }

   protected void exitAuxIn2() {
      CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
   }

   protected void finishActivity() {
      AbstractBaseActivity var1 = this.mActivity;
      if (var1 != null) {
         var1.finish();
      }

   }

   protected void forceReverse(Context var1, boolean var2) {
      SharePreUtil.setBoolValue(var1, "share_is_panoramic", var2);
      SendKeyManager.getInstance().forceReverse(var2);
   }

   protected AbstractBaseActivity getActivity() {
      return this.mActivity;
   }

   protected void getAmplifierData(Context var1, int var2, AmplifierPageUiSet var3) {
      try {
         StringBuilder var4 = new StringBuilder();
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_volume_").append(var2).toString(), var3.getSeekBarVolumeMax() * 7 / 10);
         var4 = new StringBuilder();
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_fade_").append(var2).toString(), 0);
         var4 = new StringBuilder();
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_balance_").append(var2).toString(), 0);
         var4 = new StringBuilder();
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_bass_").append(var2).toString(), var3.getBandRange());
         var4 = new StringBuilder();
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_middle_").append(var2).toString(), var3.getBandRange());
         var4 = new StringBuilder();
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_treble_").append(var2).toString(), var3.getBandRange());
         var4 = new StringBuilder();
         GeneralAmplifierData.megaBass = SharePreUtil.getIntValue(var1, var4.append("share_amplifier_heavy_bass_").append(var2).toString(), var3.getBandRange());
      } catch (NullPointerException var5) {
         Log.i("ljq", "getAmplifierData: context[" + var1 + "], canId[" + var2 + "], set[" + var3 + "]");
         var5.printStackTrace();
      }

   }

   public void getBackCameraUiService(BackCameraUiService var1) {
      this.mBackCameraUiService = var1;
   }

   protected int getBrightness() {
      return MediaShareData.System.INSTANCE.getBackLight();
   }

   protected int[] getByteArrayToIntArray(byte[] var1) {
      int[] var4 = new int[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         byte var3 = var1[var2];
         if ((var3 & 128) == 0) {
            var4[var2] = var3;
         } else {
            var4[var2] = var3 & 255;
         }
      }

      return var4;
   }

   protected int getCanId() {
      return CanbusConfig.INSTANCE.getCanType();
   }

   protected String getCurrentBand() {
      return MediaShareData.Radio.INSTANCE.getMBand();
   }

   protected int getCurrentCanDifferentId() {
      return SelectCanTypeUtil.getCurrentCanDiffId();
   }

   protected int getCurrentEachCanId() {
      return CanbusConfig.INSTANCE.getEachId();
   }

   public MsgMgrInterface getInstance() {
      return this;
   }

   protected int getPmValue(int var1) {
      if (var1 >= 0 && var1 <= 49) {
         return 1;
      } else if (50 <= var1 && var1 <= 99) {
         return 2;
      } else if (100 <= var1 && var1 <= 149) {
         return 3;
      } else if (150 <= var1 && var1 <= 199) {
         return 4;
      } else if (200 <= var1 && var1 <= 299) {
         return 5;
      } else {
         return 300 <= var1 && var1 <= 999 ? 6 : 0;
      }
   }

   public boolean getReverseState() {
      return this.isReverse;
   }

   protected SourceConstantsDef.SOURCE_ID getSourceId() {
      return SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId());
   }

   protected String getTempUnitC(Context var1) {
      if (this.mTempUnitC == null) {
         this.mTempUnitC = var1.getString(2131770016);
      }

      return this.mTempUnitC;
   }

   protected String getTempUnitF(Context var1) {
      if (this.mTempUnitF == null) {
         this.mTempUnitF = var1.getString(2131770017);
      }

      return this.mTempUnitF;
   }

   public String getVersionStr(byte[] var1) {
      CommUtil.printHexString("getVersionStr: ", var1);
      int var3 = var1.length - 2;
      byte[] var4 = new byte[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         var4[var2] = var1[var2 + 2];
      }

      return new String(var4);
   }

   protected int getVolume() {
      return MediaShareData.Volume.INSTANCE.getVolume();
   }

   public void initCommand(Context var1) {
      this.reportDateAndTime(var1);
   }

   protected boolean isActivityFront() {
      return this.mActivity.isShouldRefreshUi();
   }

   protected boolean isAirMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected boolean isBandAm(String var1) {
      return var1.equals("AM1") || var1.equals("LW") || var1.equals("AM2") || var1.equals("MW");
   }

   protected boolean isBandFm(String var1) {
      return "FM1".equals(var1) || "FM2".equals(var1) || "FM3".equals(var1) || "OIRT".equals(var1);
   }

   protected boolean isDataChange(int[] var1) {
      if (Arrays.equals((int[])this.mCanbusDataArray.get(var1[1]), var1)) {
         return false;
      } else {
         this.mCanbusDataArray.append(var1[1], (int[])var1.clone());
         return true;
      }
   }

   protected boolean isDoorMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected boolean isSettingMsgRepeat2(int[] var1) {
      boolean var2 = true;
      String var3 = String.valueOf(var1[1]);
      var1 = this.subBytes(var1, var1.length - 2);
      if (this.mSettingMsgMap == null) {
         this.mSettingMsgMap = new HashMap();
      }

      if (this.mSettingMsgMap.get(var3) == null) {
         this.mSettingMsgMap.put(var3, var1);
      } else {
         if (Arrays.equals((int[])this.mSettingMsgMap.get(var3), var1)) {
            return var2;
         }

         this.mSettingMsgMap.put(var3, var1);
      }

      var2 = false;
      return var2;
   }

   // $FF: synthetic method
   void lambda$updateAirActivity$1$com_hzbhd_canbus_msg_mgr_AbstractMsgMgr(Context var1) {
      if (!SystemUtil.isForeground(var1, AirActivity.class.getName())) {
         if (this.mAirSmallView == null) {
            this.mAirSmallView = new AirSmallView(var1);
         }

         this.mAirSmallView.refreshUi();
      }

   }

   public void linInfoChange(Context var1, byte[] var2) {
   }

   public void mcuErrorState(Context var1, byte[] var2) {
   }

   public void medianCalibration(Context var1, byte[] var2) {
   }

   protected void mpegDiscGoto(Context var1, MpegConstantsDef.K_SELECT var2, int var3) {
      FutureUtil.instance.discGoto(var2, var3);
   }

   protected void mpegNext(Context var1) {
      FutureUtil.instance.nextMpeg();
   }

   protected void mpegPlay(Context var1) {
      FutureUtil.instance.playMpeg();
   }

   protected void mpegPrev(Context var1) {
      FutureUtil.instance.prevMpeg();
   }

   protected void mpegRepeat(Context var1) {
      FutureUtil.instance.repeatMpeg();
   }

   protected void mpegShuffle(Context var1) {
      FutureUtil.instance.shuffleMpeg();
   }

   public void musicDestroy() {
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
   }

   public void musicLrcInfoChange(String var1) {
   }

   public void notifyBtStatusChange() {
   }

   public void onAMapCallBack(AMapEntity var1) {
   }

   public void onAccOff() {
   }

   public void onAccOn() {
   }

   public void onHandshake(Context var1) {
   }

   public void onKeyEvent(int var1, int var2, int var3, Bundle var4) {
   }

   public void onPowerOff() {
   }

   public void onSleep() {
   }

   protected void playBeep() {
      CommUtil.playBeep();
   }

   protected void playPresetFreq(int var1) {
      if (var1 != MediaShareData.Radio.INSTANCE.getMPresetIndex()) {
         Bundle var2 = new Bundle();
         var2.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.index.name(), var1 - 1);
         SendKeyManager.getInstance().setKeyEvent(4112, HotKeyConstant.ACTION_RADIO_TYPE.preset.ordinal(), 0, var2);
      }

   }

   public void radioDestroy() {
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
   }

   protected void realKeyClick(Context var1, int var2) {
      RealKeyUtil.realKeyClick(var1, var2);
   }

   protected void realKeyClick1(Context var1, int var2, int var3, int var4) {
      this.realKeyLongClick1(var1, var2, var4);
   }

   protected void realKeyClick2(Context var1, int var2, int var3, int var4) {
      this.realKeyLongClick1(var1, var2, var4);
   }

   protected void realKeyClick3(Context var1, int var2, int var3, int var4) {
      RealKeyUtil.realKeyClick3(var1, var2, var3, var4);
   }

   protected void realKeyClick3_1(Context var1, int var2, int var3, int var4) {
      RealKeyUtil.realKeyClick3(var1, var2, var3, var4);
   }

   protected void realKeyClick3_2(Context var1, int var2, int var3, int var4) {
      RealKeyUtil.realKeyClick3(var1, var2, var3, var4);
   }

   protected void realKeyClick4(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, 1);
      this.realKeyLongClick1(var1, var2, 0);
   }

   protected void realKeyClick5(Context var1, int var2, int var3, int var4) {
      this.realKeyLongClick1(var1, var2, var4);
   }

   protected void realKeyLongClick1(Context var1, int var2, int var3) {
      if (!RealKeyUtil.isCompoundKey(var1, var2, var3)) {
         RealKeyUtil.realKeyLongClick(var1, var2, var3);
      }

   }

   protected void realKeyLongClick2(Context var1, int var2) {
      byte var3;
      if (var2 == 0) {
         var3 = 0;
      } else {
         var3 = 1;
      }

      this.realKeyLongClick1(var1, var2, var3);
   }

   public void registerAMap(Context var1) {
      if (this.mAMapBroadcastReceiver == null) {
         Log.d(TAG, "-------->高德地图开启广播监听");
         this.mAMapBroadcastReceiver = new AMapBroadcastReceiver();
         IntentFilter var2 = new IntentFilter();
         var2.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
         var1.registerReceiver(this.mAMapBroadcastReceiver, var2);
      }

   }

   protected void requestMpeg() {
      if (this.canSupportMPEG()) {
         FutureUtil.instance.audioChannelRequest(SourceConstantsDef.SOURCE_ID.MPEG);
      }

   }

   public void reverseStateChange(boolean var1) {
   }

   protected void runOnUi(CallBackInterface var1) {
      this.mCallBackInterface = var1;
      if (this.mHandler == null) {
         this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback(this) {
            final AbstractMsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public boolean handleMessage(Message var1) {
               this.this$0.mCallBackInterface.callback();
               return true;
            }
         });
      }

      this.mHandler.sendEmptyMessage(0);
   }

   protected void saveAmplifierData(Context var1, int var2) {
      if (var1 == null) {
         Log.i("ljq", "saveAmplifierData: context is null");
      } else {
         SharePreUtil.setIntValue(var1, "share_amplifier_fade_" + var2, GeneralAmplifierData.frontRear);
         SharePreUtil.setIntValue(var1, "share_amplifier_balance_" + var2, GeneralAmplifierData.leftRight);
         SharePreUtil.setIntValue(var1, "share_amplifier_bass_" + var2, GeneralAmplifierData.bandBass);
         SharePreUtil.setIntValue(var1, "share_amplifier_middle_" + var2, GeneralAmplifierData.bandMiddle);
         SharePreUtil.setIntValue(var1, "share_amplifier_treble_" + var2, GeneralAmplifierData.bandTreble);
         SharePreUtil.setIntValue(var1, "share_amplifier_volume_" + var2, GeneralAmplifierData.volume);
         SharePreUtil.setIntValue(var1, "share_amplifier_heavy_bass_" + var2, GeneralAmplifierData.megaBass);
      }
   }

   protected void sendAfterHangUpMsg(Context var1) {
      try {
         String var3 = android.provider.Settings.System.getString(var1.getContentResolver(), "reportAfterHangUp");
         if (!TextUtils.isEmpty(var3)) {
            CanbusMsgSender.sendMsg(Base64.decode(var3, 0));
         }
      } catch (Exception var2) {
      }

   }

   protected void sendDiscEjectMsg(Context var1) {
      if (var1 != null) {
         if (SystemClock.elapsedRealtime() - this.mRealTime >= 1000L) {
            String var2 = android.provider.Settings.System.getString(var1.getContentResolver(), "reportForDiscEject");
            if (!TextUtils.isEmpty(var2)) {
               this.sendMediaMsg(var1, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Base64.decode(var2, 0));
               this.mRealTime = SystemClock.elapsedRealtime();
            }

         }
      }
   }

   protected void sendDisplayMsgView(Context var1) {
      if (this.mDisplayMsgView == null) {
         this.mDisplayMsgView = new DisplayMsgView(var1);
      }

      this.runOnUi(new CallBackInterface(this) {
         final AbstractMsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mDisplayMsgView.refreshUi();
         }
      });
   }

   protected void sendMediaMsg(Context var1, String var2, byte[] var3) {
      String var5 = TAG;
      Log.i(var5, "sendMediaMsg: context: " + var1 + ", media: " + var2);
      if (var1 != null) {
         String var4 = SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()).name();
         Log.i(var5, "sendMediaMsg: frontSeat:" + var4);
         CanbusMsgSender.sendMsg(var3);
         if (!SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var2)) {
            android.provider.Settings.System.putString(var1.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(var3, 0));
            if (!SourceConstantsDef.SOURCE_ID.MPEG.name().equals(var4)) {
               android.provider.Settings.System.putString(var1.getContentResolver(), "reportForDiscEject", Base64.encodeToString(var3, 0));
            }
         }

      }
   }

   public void serialDataChange(Context var1, byte[] var2) {
   }

   public void setBacklightLevel(int var1) {
      if (this.nowLightLevel != var1) {
         this.nowLightLevel = var1;
         SendKeyManager.getInstance().setBacklightLevel(var1);
      }
   }

   protected void setCurrentFreqAm(String var1) {
      Bundle var2 = new Bundle();
      var2.putString(HotKeyConstant.ACTION_RADIO_BUNDLE.frequency.name(), var1);
      SendKeyManager.getInstance().setKeyEvent(4112, HotKeyConstant.ACTION_RADIO_TYPE.amto.ordinal(), 0, var2);
   }

   protected void setCurrentFreqFm(String var1) {
      Bundle var2 = new Bundle();
      var2.putString(HotKeyConstant.ACTION_RADIO_BUNDLE.frequency.name(), var1);
      SendKeyManager.getInstance().setKeyEvent(4112, HotKeyConstant.ACTION_RADIO_TYPE.fmto.ordinal(), 0, var2);
   }

   public void setMgrRefreshUiInterface(AbstractBaseActivity var1) {
      this.mActivity = var1;
   }

   public void sourceSwitchChange(String var1) {
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
   }

   protected void startDrivingDataActivity(Context var1, int var2) {
      Intent var3 = new Intent(var1, DriveDataActivity.class);
      var3.setAction("drive_data_open_target_page");
      var3.setFlags(268435456);
      var3.putExtra("current_item", var2);
      var1.startActivity(var3);
   }

   protected String startEndTimeMethod(int var1) {
      return var1 < 3600 ? this.df.format((long)(var1 % 3600 / 60)) + ":" + this.df.format((long)(var1 % 60)) : this.df.format((long)(var1 / 60 / 60)) + ":" + this.df.format((long)(var1 % 3600 / 60)) + ":" + this.df.format((long)(var1 % 60));
   }

   protected void startMainActivity(Context var1) {
      if (var1 != null) {
         Intent var2 = new Intent();
         var2.setComponent(HzbhdComponentName.NewCanBusMainActivity);
         var2.setFlags(268435456);
         var1.startActivity(var2);
      }
   }

   protected void startOtherActivity(Context var1, ComponentName var2) {
      Intent var3 = new Intent();
      var3.setComponent(var2);
      var3.setFlags(268435456);
      var1.startActivity(var3);
   }

   protected void startSettingActivity(Context var1, int var2, int var3) {
      Intent var4 = new Intent(var1, SettingActivity.class);
      var4.setAction("setting_open_target_page");
      var4.setFlags(268435456);
      var4.putExtra("left_index ", var2);
      var4.putExtra("right_index", var3);
      var1.startActivity(var4);
   }

   protected void startWarningActivity(Context var1) {
      Intent var2 = new Intent(var1, WarningActivity.class);
      var2.setFlags(268435456);
      var1.startActivity(var2);
   }

   protected void switchFCamera(Context var1, boolean var2) {
      if (SharePreUtil.getBoolValue(var1, "share_is_open_F.Camera", false)) {
         Exception var10000;
         boolean var10001;
         if (var2) {
            try {
               Intent var3 = new Intent();
               var3.addFlags(268435456);
               var3.setComponent(Constant.FCameraActivity);
               var1.startActivity(var3);
               return;
            } catch (Exception var4) {
               var10000 = var4;
               var10001 = false;
            }
         } else {
            try {
               if (SystemUtils.isForeground(var1, Constant.FCameraActivity.getClassName())) {
                  AbstractMsgMgr$$ExternalSyntheticLambda0 var8 = new AbstractMsgMgr$$ExternalSyntheticLambda0();
                  Thread var7 = new Thread(var8);
                  var7.start();
               }

               return;
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         Exception var6 = var10000;
         Log.i(TAG, "switchFCamera: " + var6.getMessage());
      }

   }

   public void turnLeftLamp(boolean var1) {
      CanbusInfoChangeListener.getInstance().reportLeftLampInfo(var1);
   }

   public void turnRightLamp(boolean var1) {
      CanbusInfoChangeListener.getInstance().reportRightLampInfo(var1);
   }

   public void unRegisterAMap(Context var1) {
      AMapBroadcastReceiver var2 = this.mAMapBroadcastReceiver;
      if (var2 != null && var1 != null) {
         var1.unregisterReceiver(var2);
      }

   }

   protected void updateActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null) {
         var2.runOnUiThread(new Runnable(this, var1) {
            final AbstractMsgMgr this$0;
            final Bundle val$bundle;

            {
               this.this$0 = var1;
               this.val$bundle = var2;
            }

            public void run() {
               try {
                  this.this$0.mActivity.refreshUi(this.val$bundle);
               } catch (Exception var2) {
                  LogUtil.showLog("updateActivity:" + var2.toString());
               }

            }
         });
      }

   }

   protected void updateAirActivity(Context var1, int var2) {
      Bundle var3 = new Bundle();
      var3.putInt("bundle_air_what", var2);
      if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getAirDisplaySetup() == 1) {
         this.runOnUi(new AbstractMsgMgr$$ExternalSyntheticLambda1(this, var1));
      } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getAirDisplaySetup() == 0 && !((ActivityManager.RunningTaskInfo)((ActivityManager)var1.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getClassName().equals("com.hzbhd.canbus.activity.AirActivity") && !this.getReverseState()) {
         AirPageWindowView.getInstance(var1).show(var3);
      }

      AbstractBaseActivity var4 = this.mActivity;
      if (var4 != null && var4 instanceof AirActivity) {
         this.updateActivity(var3);
      }

      CanbusInfoChangeListener.getInstance().reportAirInfo(var1);
   }

   protected void updateAmplifierActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof AmplifierActivity) {
         this.updateActivity(var1);
      }

   }

   protected void updateBubble(Context var1) {
      if (this.mBubble == null) {
         this.mBubble = new Bubble(var1);
      }

      this.mBubble.updateBubble();
   }

   public void updateDoorView(Context var1) {
      if (!"com.autochips.avmplayer".equals(SystemUtil.getTopActivityPackageName(var1))) {
         if (this.mDoorView == null) {
            this.mDoorView = new DoorView(var1);
         }

         this.runOnUi(new CallBackInterface(this) {
            final AbstractMsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               this.this$0.mDoorView.refreshUi();
            }
         });
      }

      CanbusInfoChangeListener.getInstance().reportDoorInfo(var1);
   }

   protected void updateDriveDataActivity(Bundle var1) {
      LogUtil.showLog("updateDriveDataActivity");
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof DriveDataActivity) {
         this.updateActivity(var1);
      }

   }

   protected void updateGeneralDriveData(List var1) {
      if (var1 != null) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            for(int var3 = 0; var3 < GeneralDriveData.dataList.size(); ++var3) {
               if (((DriverUpdateEntity)GeneralDriveData.dataList.get(var3)).getPage() == ((DriverUpdateEntity)var1.get(var2)).getPage() && ((DriverUpdateEntity)GeneralDriveData.dataList.get(var3)).getIndex() == ((DriverUpdateEntity)var1.get(var2)).getIndex()) {
                  GeneralDriveData.dataList.remove(var3);
               }
            }

            GeneralDriveData.dataList.add((DriverUpdateEntity)var1.get(var2));
         }

      }
   }

   protected void updateGeneralSettingData(List var1) {
      if (var1 != null) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            for(int var3 = 0; var3 < GeneralSettingData.dataList.size(); ++var3) {
               if (((SettingUpdateEntity)GeneralSettingData.dataList.get(var3)).getLeftListIndex() == ((SettingUpdateEntity)var1.get(var2)).getLeftListIndex() && ((SettingUpdateEntity)GeneralSettingData.dataList.get(var3)).getRightListIndex() == ((SettingUpdateEntity)var1.get(var2)).getRightListIndex()) {
                  GeneralSettingData.dataList.remove(var3);
               }
            }

            GeneralSettingData.dataList.add((SettingUpdateEntity)var1.get(var2));
         }

      }
   }

   public void updateHandbrakeState(boolean var1) {
   }

   protected void updateHybirdActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof HybirdActivity) {
         this.updateActivity(var1);
      }

   }

   protected void updateOnStarActivity(int var1) {
      Bundle var3 = new Bundle();
      var3.putInt("bundle_onstar_what", var1);
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof OnStarActivity) {
         this.updateActivity(var3);
      }

   }

   protected void updateOriginalCarDeviceActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof OriginalCarDeviceActivity) {
         this.updateActivity(var1);
      }

   }

   protected void updateOutDoorTemp(Context var1, String var2) {
      GeneralAirData.outdoorTemperature = var2;
      if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getShowOutdoorTemperature()) {
         CanbusInfoChangeListener.getInstance().reportOutdoorTemperature();
      }

   }

   protected void updatePKeyRadar() {
      if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getPKeyRadarDispCheck()) {
         if (GeneralParkData.pKeyRadarState) {
            PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).show();
         } else {
            PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).hide();
         }

      }
   }

   public void updateParkUi(Bundle var1, Context var2) {
      this.updateTrack();
      if (this.mBackCameraUiService == null) {
         this.bindBackCameraUiService(var2);
         LogUtil.showLog("BackCameraUiService mBackCameraUiService==null");
      } else {
         Message var3 = Message.obtain();
         var3.setData(var1);
         var3.what = 1;
         this.mBackCameraUiService.getHandler().sendMessage(var3);
      }

   }

   public void updateReverseGear(boolean var1) {
   }

   public void updateSettingActivity(Bundle var1) {
      LogUtil.showLog("updateSettingActivity");
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 == null) {
         LogUtil.showLog("SettingActivity mMgrRefreshUiInterface==null");
      } else if (var2 instanceof SettingActivity) {
         this.updateActivity(var1);
      }

   }

   public void updateSpeedInfo(int var1) {
      CanbusInfoChangeListener.getInstance().reportSpeedInfo(var1);
   }

   protected void updateSyncActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof SyncActivity) {
         this.updateActivity(var1);
      }

   }

   protected void updateTirePressureActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof TirePressureActivity) {
         this.updateActivity(var1);
      }

   }

   protected void updateTrack() {
      if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getBackTrajectiryDispCheck()) {
         if (GeneralParkData.trackAngleRecord != GeneralParkData.trackAngle) {
            GeneralParkData.trackAngleRecord = GeneralParkData.trackAngle;
            ShareDataServiceImpl.setInt("canbus.Angle", -GeneralParkData.trackAngle);
         }
      } else if (GeneralParkData.trackAngleRecord != GeneralParkData.trackAngle) {
         GeneralParkData.trackAngleRecord = GeneralParkData.trackAngle;
         ShareDataServiceImpl.setInt("canbus.Angle", GeneralParkData.trackAngle);
      }

   }

   public void updateVersionInfo(Context var1, String var2) {
      Log.i(TAG, "updateVersionInfo: " + var2);
      ShareDataServiceImpl.setString("canbus.canVersion", var2);
      GeneralData.INSTANCE.setCanVersion(var2);
   }

   protected void updateVoiceBroadcast(Bundle var1, String var2) {
      Log.i(TAG, "updateVoiceBroadcast: info <--> " + var2);
      CanbusInfoChangeListener.getInstance().reportVoiceBroadcast(var2);
   }

   protected void updateWarningActivity(Bundle var1) {
      AbstractBaseActivity var2 = this.mActivity;
      if (var2 != null && var2 instanceof WarningActivity) {
         this.updateActivity(var1);
      }

   }

   public void videoDestroy() {
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
   }

   public void voiceControlInfo(String var1) {
   }

   public interface CallBackInterface {
      void callback();
   }
}
