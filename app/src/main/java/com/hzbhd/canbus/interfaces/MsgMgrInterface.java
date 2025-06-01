package com.hzbhd.canbus.interfaces;

import android.content.Context;
import android.os.Bundle;

import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.util.amap.AMapEntity;

public interface MsgMgrInterface {
    void aMapCallBack(Bundle var1);

    void afterServiceNormalSetting(Context var1);

    void atvDestdroy();

    void atvInfoChange();

    void auxInDestdroy();

    void auxInInfoChange();

    void btMusicId3InfoChange(String var1, String var2, String var3);

    void btMusicInfoChange();

    void btMusiceDestdroy();

    void btPhoneCallLogInfoChange(int var1, int var2, String var3);

    void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3);

    void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3);

    void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3);

    void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9);

    void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3);

    void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4);

    void cameraDestroy();

    void cameraInfoChange();

    void canbusInfoChange(Context var1, byte[] var2);

    void currentVolumeInfoChange(int var1, boolean var2);

    boolean customLongClick(Context var1, int var2);

    boolean customShortClick(Context var1, int var2);

    void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13);

    void destroyCommand();

    void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12);

    void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13);

    void dtvInfoChange();

    void getBackCameraUiService(BackCameraUiService var1);

    default MsgMgrInterface getInstance() {
        return this;
    }

    void initCommand(Context var1);

    void linInfoChange(Context var1, byte[] var2);

    void mcuErrorState(Context var1, byte[] var2);

    void medianCalibration(Context var1, byte[] var2);

    void musicDestroy();

    void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24);

    void musicLrcInfoChange(String var1);

    void notifyBtStatusChange();

    void onAMapCallBack(AMapEntity var1);

    void onAccOff();

    void onAccOn();

    void onHandshake(Context var1);

    void onKeyEvent(int var1, int var2, int var3, Bundle var4);

    void onPowerOff();

    void onSleep();

    void radioDestroy();

    void radioInfoChange(int var1, String var2, String var3, String var4, int var5);

    void serialDataChange(Context var1, byte[] var2);

    void setMgrRefreshUiInterface(AbstractBaseActivity var1);

    void sourceSwitchChange(String var1);

    void sourceSwitchNoMediaInfoChange(boolean var1);

    void videoDestroy();

    void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17);

    void voiceControlInfo(String var1);
}
