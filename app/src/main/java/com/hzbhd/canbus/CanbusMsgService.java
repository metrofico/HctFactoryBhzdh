package com.hzbhd.canbus;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.receiver.DateTimeReceiver;
import com.hzbhd.canbus.receiver.SpeechReceiver;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.ActionControlUtil;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.ContextUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.commontools.SystemStatusDef;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.proxy.mcu.core.IMCUCanBoxControlListener;
import com.hzbhd.proxy.mcu.core.MCUMainManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CanbusMsgService extends Service {
    private static final int ENOUGH_LENGTH = 20;
    private static final int INITCOMMAND_DELAY = 1000;
    private static final int MSG_CANBOX_DATA = 1;
    private static final int MSG_LIN_DATA = 5;
    private static final int MSG_MCU_ERROR_DATA = 7;
    private static final int MSG_MEDIAN_CALIBRATION = 6;
    private static final int MSG_SERIAL_DATA = 4;
    private static final int MSG_STATUS_REVERSE = 2;
    private static final int ON_MCU_CONECT = 3;
    private static final String TAG = "CanbusMsgService";
    public static DecimalFormat df = new DecimalFormat("00");
    public static boolean mIsHaveDealWithReportCanTypeId;
    public static boolean mIsNeedSystemExit;
    byte[] lengthEnoughArray = new byte[20];
    private final IMCUCanBoxControlListener mCanBoxControlListener = new IMCUCanBoxControlListener(this) {
        final CanbusMsgService this$0;

        {
            this.this$0 = var1;
        }

        public void notifyCanboxData(int var1, byte[] var2) {
            Log.d(CanbusMsgService.TAG, "<notifyCanboxData> " + var1 + ": " + FgeString.bytes2HexString(var2, var2.length));
            Message var3;
            if (161 == var1) {
                var3 = new Message();
                var3.what = 7;
                var3.obj = var2;
                this.this$0.mHandler.sendMessage(var3);
            }

            if (167 == var1) {
                var3 = new Message();
                var3.what = 1;
                var3.obj = var2;
                this.this$0.mHandler.sendMessage(var3);
            }

            if (167 == var1 && var2[0] == 91) {
                var3 = new Message();
                var3.what = 4;
                var3.obj = var2;
                this.this$0.mHandler.sendMessage(var3);
            }

            if (167 == var1 && var2[0] == 92) {
                var3 = new Message();
                var3.what = 5;
                var3.obj = var2;
                this.this$0.mHandler.sendMessage(var3);
            }

            if (167 == var1 && var2[0] == 93) {
                var3 = new Message();
                var3.what = 6;
                var3.obj = var2;
                this.this$0.mHandler.sendMessage(var3);
            }

        }

        public void onMcuConn() {
            Log.d("CAN_STATE", "onMcuConn");
            Message var1 = new Message();
            var1.what = 3;
            this.this$0.mHandler.sendMessage(var1);
        }
    };
    private DateTimeReceiver mCanbusTimeReceiver;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private SpeechReceiver mSpeechReceiver;
    private final Binder myBinder = new Binder();

    public CanbusMsgService() {
        HandlerThread var1 = new HandlerThread(this, "hzbhd.canbusinfo.msgservice") {
            final CanbusMsgService this$0;

            {
                this.this$0 = var1;
                this.start();
            }
        };
        this.mHandlerThread = var1;
        this.mHandler = new Handler(this, var1.getLooper()) {
            final CanbusMsgService this$0;

            {
                this.this$0 = var1;
            }

            public void handleMessage(Message var1) {
                switch (var1.what) {
                    case 1:
                        this.this$0.canbusInfoChange((byte[]) var1.obj);
                        break;
                    case 2:
                        int var2 = var1.arg1;
                        break;
                    case 3:
                        if (this.this$0.getMsgMgrInterface() != null) {
                            Log.d("CAN_STATE", "Time to shake hands");
                            this.this$0.getMsgMgrInterface().onHandshake(this.this$0);
                        }
                        break;
                    case 4:
                        this.this$0.serialDataChange((byte[]) var1.obj);
                        break;
                    case 5:
                        if (this.this$0.getMsgMgrInterface() != null) {
                            Log.d("Lin_Bus", "Lin bus data change");
                            this.this$0.getMsgMgrInterface().linInfoChange(this.this$0, (byte[]) var1.obj);
                        }
                        break;
                    case 6:
                        if (this.this$0.getMsgMgrInterface() != null) {
                            Log.d("MedianCalibration", "Median calibration data change!");
                            this.this$0.getMsgMgrInterface().medianCalibration(this.this$0, (byte[]) var1.obj);
                        }
                        break;
                    case 7:
                        if (this.this$0.getMsgMgrInterface() != null) {
                            this.this$0.getMsgMgrInterface().mcuErrorState(this.this$0, (byte[]) var1.obj);
                        }
                }

            }
        };
    }

    private void bindBackCameraUiService(Context var1) {
        var1.bindService(new Intent(var1, BackCameraUiService.class), new ServiceConnection(this, var1) {
            final CanbusMsgService this$0;
            final Context val$context;

            {
                this.this$0 = var1;
                this.val$context = var2;
            }

            public void onServiceConnected(ComponentName var1, IBinder var2) {
                if (var1 != null && var2 != null) {
                    LogUtil.showLog("CanbusMsgService bindBackCameraUiService success");
                    BackCameraUiService var3 = ((BackCameraUiService.MyBinder) var2).getService();
                    MsgMgrInterface var4 = this.this$0.getMsgMgrInterface();
                    if (var4 != null) {
                        var4.getBackCameraUiService(var3);
                    } else {
                        LogUtil.showLog("canbusMsgInterface==null");
                    }
                } else {
                    LogUtil.showLog("CanbusMsgService bindBackCameraUiService empty");
                }

            }

            public void onServiceDisconnected(ComponentName var1) {
                this.this$0.bindBackCameraUiService(this.val$context);
            }
        }, 1);
    }

    private CanTypeAllEntity getDbCanTypeAllEntity(int var1) {
        ArrayList var2 = CanTypeUtil.INSTANCE.getCanType(var1).getList();
        if (ArrayUtils.isEmpty(var2)) {
            Log.i(TAG, "getDbCanTypeAllEntity: canTypeId:" + var1);
            CanbusConfig.INSTANCE.setCanType(0);
            return this.getDbCanTypeAllEntity(0);
        } else {
            var1 = CanbusConfig.INSTANCE.getSelectCarPosition();
            LogUtil.showLog("getDbCanTypeAllEntity selectPosition:" + var1);
            return var1 < var2.size() ? (CanTypeAllEntity) var2.get(var1) : (CanTypeAllEntity) var2.get(0);
        }
    }

    private MsgMgrInterface getMsgMgrInterface() {
        return MsgMgrFactory.getCanMsgMgrUtil(this);
    }

    private boolean isDataBaseReady() {
        int var1 = Integer.parseInt(SharePreUtil.getStringValue(this, "share_pre_last_version_code", "0"));
        LogUtil.showLog("isDataBaseReady -> lastVersionCode:" + var1);
        if (var1 <= 2024031119) {
            SharePreUtil.setStringValue(this, "share_pre_last_version_code", String.valueOf(2024031119));
        }

        return true;
    }

    private void logCanData(byte[] var1) {
        if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getCanDataLogSwith()) {
            String var3 = "A7 55————>";

            for (int var2 = 1; var2 < var1.length; ++var2) {
                String var5 = Integer.toHexString(CanbusMsgSender$$ExternalSyntheticBackport0.m(var1[var2]));
                String var4 = var5;
                if (var5.length() == 1) {
                    var4 = "0" + var5;
                }

                var3 = var3 + var4 + " ";
            }

            Log.d("CAN_RX", var3);
        }

    }

    private void normalProgress(int var1) {
        LogUtil.showLog("normalProgress");
        CanTypeAllEntity var5 = this.getDbCanTypeAllEntity(var1);
        if (var5 == null) {
            LogUtil.showLog("normalProgress entity==null");
        } else {
            LogUtil.showLog("CanbusMsgService, current can type:[" + var5.getCar_model() + "] [" + var5.getCan_type_id() + "] isShowApp:[" + var5.getIs_show_app() + "] is_use_new_camera:[" + var5.getIs_use_new_camera() + "] is_use_new_app:[" + var5.getIs_use_new_app() + "]");
            CanbusConfig var6 = CanbusConfig.INSTANCE;
            int var2 = var5.getIs_show_app();
            boolean var4 = false;
            boolean var3;
            if (var2 == 1) {
                var3 = true;
            } else {
                var3 = false;
            }

            var6.setIsShowApp(var3);
            var3 = var4;
            if (var5.getIs_use_new_app() == 1) {
                var3 = true;
            }

            SharePreUtil.setBoolValue(this, "share_pre_is_use_new_app", var3);
            this.bindBackCameraUiService(this);
            AppEnableUtil.disableAux2Activity(this);
            AppEnableUtil.isShowApp(this, var5.getIs_show_app());
            AppEnableUtil.cusProjectSetting(this, var1);
            if (this.getMsgMgrInterface() != null) {
                this.getMsgMgrInterface().afterServiceNormalSetting(this);
            }
        }

    }

    private void registerAccStateListener() {
        ShareDataManager.getInstance().registerShareIntListener("sys.Power", new IShareIntListener(this) {
            final CanbusMsgService this$0;

            {
                this.this$0 = var1;
            }

            public void onInt(int var1) {
                SystemStatusDef.POWER_STATUS var2 = SystemStatusDef.POWER_STATUS.values()[var1];
                var1 = null.$SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS[var2.ordinal()];
                if (var1 != 1) {
                    if (var1 != 2) {
                        if (var1 != 3) {
                            if (var1 == 4) {
                                this.this$0.getMsgMgrInterface().onPowerOff();
                            }
                        } else {
                            this.this$0.getMsgMgrInterface().onSleep();
                        }
                    } else {
                        this.this$0.getMsgMgrInterface().onAccOff();
                    }
                } else {
                    this.this$0.getMsgMgrInterface().onAccOn();
                }

            }
        });
    }

    private void registerCanboxListener() {
        MCUMainManager.getInstance().registerMCUCanboxListener(this.mCanBoxControlListener);
    }

    private void registerReverseListener() {
        ShareDataManager.getInstance().registerShareIntListener("misc.Reverse", new IShareIntListener(this) {
            final CanbusMsgService this$0;

            {
                this.this$0 = var1;
            }

            public void onInt(int var1) {
                Message var2 = new Message();
                var2.what = 2;
                var2.arg1 = var1;
                this.this$0.mHandler.sendMessage(var2);
            }
        });
    }

    private void settingProgress(int var1) {
    }

    private void unRegisterCanboxListener() {
        MCUMainManager.getInstance().unregisterMCUCanboxListener(this.mCanBoxControlListener);
    }

    public void actionControl(String var1) {
        ActionControlUtil.acControl(this, var1);
    }

    public void cameraDestroy() {
        if (this.getMsgMgrInterface() != null) {
            this.getMsgMgrInterface().cameraDestroy();
        }

    }

    public void cameraInfoChange() {
        if (this.getMsgMgrInterface() != null) {
            this.getMsgMgrInterface().cameraInfoChange();
        }

    }

    public void canbusInfoChange(byte[] var1) {
        if (var1 != null && var1.length > 1) {
            if (this.getMsgMgrInterface() != null) {
                this.logCanData(var1);
                if (var1.length < 20) {
                    if (CanIdSpecialConfig.isCanNotSupplement0x00InCanDataEnd(CanbusConfig.INSTANCE.getCanType())) {
                        this.getMsgMgrInterface().canbusInfoChange(this, var1);
                    } else {
                        Arrays.fill(this.lengthEnoughArray, (byte) 0);
                        System.arraycopy(var1, 0, this.lengthEnoughArray, 0, var1.length);
                        this.getMsgMgrInterface().canbusInfoChange(this, this.lengthEnoughArray);
                    }
                } else {
                    this.getMsgMgrInterface().canbusInfoChange(this, var1);
                }
            } else {
                LogUtil.showLog("getMsgMgrInterface() == null");
            }
        }

    }

    public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
        if (this.getMsgMgrInterface() != null) {
            this.getMsgMgrInterface().deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
        }

    }

    public String getAirInfo() {
        return GeneralAirData.getAirInfo();
    }

    public AirPageUiSet getAirPageUiSet() {
        return UiMgrFactory.getCanUiMgr(this) == null ? null : UiMgrFactory.getCanUiMgr(this).getAirUiSet(this);
    }

    public CanbusMsgService getService() {
        return this;
    }

    public void musicLrcInfoChange(String var1) {
        if (this.getMsgMgrInterface() != null) {
            this.getMsgMgrInterface().musicLrcInfoChange(var1);
        }

    }

    public IBinder onBind(Intent var1) {
        return this.myBinder;
    }

    public void onCreate() {
        super.onCreate();
        ContextUtil.getInstance().setContext(this);
        CanbusInfoChangeListener.getInstance();
        Log.i(TAG, "onCreate: branches/hy");
        this.mCanbusTimeReceiver = new DateTimeReceiver();
        IntentFilter var1 = new IntentFilter();
        var1.addAction("android.intent.action.TIME_TICK");
        var1.addAction("android.intent.action.TIME_SET");
        this.getBaseContext().registerReceiver(this.mCanbusTimeReceiver, var1);
        SpeechReceiver var2 = new SpeechReceiver();
        this.mSpeechReceiver = var2;
        var2.registerReceiver(this);
        if (this.isDataBaseReady()) {
            this.normalProgress(CanbusConfig.INSTANCE.getCanType());
            this.getMsgMgrInterface().initCommand(this);
        }

        this.registerCanboxListener();
        MediaShareData.INSTANCE.registerModuleListener(this);
        ActionControlUtil.registerHotKeyListener(this);
        this.registerAccStateListener();
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        this.unRegisterCanboxListener();
        if (this.getMsgMgrInterface() != null) {
            this.getMsgMgrInterface().destroyCommand();
        }

        DateTimeReceiver var1 = this.mCanbusTimeReceiver;
        if (var1 != null) {
            this.unregisterReceiver(var1);
        }

        SpeechReceiver var2 = this.mSpeechReceiver;
        if (var2 != null) {
            var2.unregisterReceiver(this);
        }

    }

    public void reportCanTypeId(int var1) {
    }

    public void saveCanTypeId(int var1) {
    }

    public void serialDataChange(byte[] var1) {
        if (var1 != null && var1.length > 1 && this.getMsgMgrInterface() != null) {
            this.getMsgMgrInterface().serialDataChange(this, var1);
        }

    }

    public void testCanBusInfo(Context var1, byte[] var2) {
    }
}
