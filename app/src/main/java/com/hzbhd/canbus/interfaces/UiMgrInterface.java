package com.hzbhd.canbus.interfaces;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;

public interface UiMgrInterface {
   AirPageUiSet getAirUiSet(Context var1);

   AmplifierPageUiSet getAmplifierPageUiSet(Context var1);

   BubbleUiSet getBubbleUiSet(Context var1);

   View getCusPanoramicView(Context var1);

   DriverDataPageUiSet getDriverDataPageUiSet(Context var1);

   HybirdPageUiSet getHybirdPageUiSet(Context var1);

   MainPageUiSet getMainUiSet(Context var1);

   OnStartPageUiSet getOnStartPageUiSet(Context var1);

   OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1);

   PageUiSet getPageUiSet(Context var1);

   PanelKeyPageUiSet getPanelKeyPageUiSet(Context var1);

   ParkPageUiSet getParkPageUiSet(Context var1);

   SettingPageUiSet getSettingUiSet(Context var1);

   SyncPageUiSet getSyncPageUiSet(Context var1);

   TirePageUiSet getTireUiSet(Context var1);

   WarningPageUiSet getWarningPageUiSet(Context var1);

   void updateUiByDifferentCar(Context var1);
}
