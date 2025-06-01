package com.hzbhd.canbus.ui_mgr;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
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
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractUiMgr implements UiMgrInterface {
    private Context mContext;
    private PageUiSet mPageUiSet;

    private String getJsonContent(Context var1) {
        return CommUtil.getAssetsString(var1, "car_ui/" + SelectCanTypeUtil.getCurrentCanTypeId(var1) + ".json");
    }

    protected void disableThisApp(Context var1) {
        SelectCanTypeUtil.disableApp(var1, HzbhdComponentName.CanbusCarInfoMainActivity);
    }

    protected void enableThisApp(Context var1) {
        SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.CanbusCarInfoMainActivity);
    }

    public AirPageUiSet getAirUiSet(Context var1) {
        return this.getPageUiSet(var1).getAirPageUiSet();
    }

    public AmplifierPageUiSet getAmplifierPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getAmplifierPageUiSet();
    }

    public BubbleUiSet getBubbleUiSet(Context var1) {
        return this.getPageUiSet(var1).getBubbleUiSet();
    }

    protected int getCurrentCarId() {
        return SelectCanTypeUtil.getCurrentCanDiffId();
    }

    public View getCusPanoramicView(Context var1) {
        return null;
    }

    public DriverDataPageUiSet getDriverDataPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getDriverDataPageUiSet();
    }

    protected int getEachId() {
        return CanbusConfig.INSTANCE.getEachId();
    }

    public HybirdPageUiSet getHybirdPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getHybirdPageUiSet();
    }

    public MainPageUiSet getMainUiSet(Context var1) {
        return this.getPageUiSet(var1).getMainPageUiSet();
    }

    public OnStartPageUiSet getOnStartPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getOnStartPageUiSet();
    }

    public OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
        return this.getPageUiSet(var1).getOriginalCarDevicePageUiSet();
    }

    public PageUiSet getPageUiSet(Context var1) {
        this.mContext = var1;
        if (this.mPageUiSet == null) {
            this.mPageUiSet = (PageUiSet) (new Gson()).fromJson(this.getJsonContent(var1), PageUiSet.class);
        }

        return this.mPageUiSet;
    }

    public PanelKeyPageUiSet getPanelKeyPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getPanelKeyPageUiSet();
    }

    public ParkPageUiSet getParkPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getParkPageUiSet();
    }

    public SettingPageUiSet getSettingUiSet(Context var1) {
        return this.getPageUiSet(var1).getSettingPageUiSet();
    }

    public SyncPageUiSet getSyncPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getSyncPageUiSet();
    }

    public TirePageUiSet getTireUiSet(Context var1) {
        return this.getPageUiSet(var1).getTirePageUiSet();
    }

    public WarningPageUiSet getWarningPageUiSet(Context var1) {
        return this.getPageUiSet(var1).getWarningPageUiSet();
    }

    protected void playBeep() {
        CommUtil.playBeep();
    }

    protected void removeDriveData(Context var1, int var2, int var3) {
        List var5 = this.getDriverDataPageUiSet(var1).getList();

        for (int var4 = 0; var4 < var5.size(); ++var4) {
            if (var5.size() > var3) {
                var5.remove(var2);
            }
        }

    }

    protected void removeDriveData(Context var1, String var2) {
        if (!TextUtils.isEmpty(var2)) {
            List var4 = this.getDriverDataPageUiSet(var1).getList();

            for (int var3 = 0; var3 < var4.size(); ++var3) {
                if (var2.equals(((DriverDataPageUiSet.Page) var4.get(var3)).getHeadTitleSrn())) {
                    var4.remove(var3);
                }
            }

        }
    }

    protected void removeDriveDataPagesByHeadTitles(Context var1, String... var2) {
        List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();
        int var5 = var2.length;

        for (int var3 = 0; var3 < var5; ++var3) {
            String var7 = var2[var3];

            for (int var4 = 0; var4 < var6.size(); ++var4) {
                if (var7.equals(((DriverDataPageUiSet.Page) var6.get(var4)).getHeadTitleSrn())) {
                    var6.remove(var4);
                    break;
                }
            }
        }

    }

    protected void removeDriveDateItemForTitles(Context var1, String[] var2) {
        List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();
        int var4 = var2.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            Iterator var7 = var6.iterator();

            while (var7.hasNext()) {
                Iterator var8 = ((DriverDataPageUiSet.Page) var7.next()).getItemList().iterator();

                while (var8.hasNext()) {
                    DriverDataPageUiSet.Page.Item var5 = (DriverDataPageUiSet.Page.Item) var8.next();
                    if (var2[var3].equals(var5.getTitleSrn())) {
                        var8.remove();
                    }
                }
            }
        }

    }

    protected void removeDrivigDateItem(Context var1, int var2, int var3, int var4) {
        List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

        for (int var5 = 0; var5 < ((DriverDataPageUiSet.Page) var6.get(var2)).getItemList().size(); ++var5) {
            if (((DriverDataPageUiSet.Page) var6.get(var2)).getItemList().size() > var4) {
                ((DriverDataPageUiSet.Page) var6.get(var2)).getItemList().remove(var3);
            }
        }

    }

    protected void removeFrontAirButton(Context var1, int var2, int var3, int var4) {
        String[][] var6 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();
        ArrayList var5 = new ArrayList();
        var5.addAll(Arrays.asList(var6[var2]));
        if (var5.size() > var4) {
            var5.remove(var3);
        }

        var6[var2] = (String[]) var5.toArray(new String[var5.size()]);
    }

    protected void removeFrontAirButtonByName(Context var1, String var2) {
        String[][] var5 = this.getAirUiSet(var1).getFrontArea().getLineBtnAction();

        for (int var3 = 0; var3 < var5.length; ++var3) {
            ArrayList var4 = new ArrayList();
            var4.addAll(Arrays.asList(var5[var3]));
            if (var4.remove(var2)) {
                var5[var3] = (String[]) var4.toArray(new String[var4.size()]);
                return;
            }
        }

    }

    protected void removeMainPrjBtn(Context var1, int var2, int var3) {
        List var4 = this.getMainUiSet(var1).getBtnAction();
        if (var4.size() > var3) {
            var4.remove(var2);
        }

    }

    protected void removeMainPrjBtnByName(Context var1, String var2) {
        this.getMainUiSet(var1).getBtnAction().remove(var2);
    }

    protected void removePanelBtnKeyByName(Context var1, String var2) {
        this.getPanelKeyPageUiSet(var1).getList().remove(var2);
    }

    protected void removeRearAirButton(Context var1, int var2, int[] var3) {
        String[][] var6 = this.getAirUiSet(var1).getRearArea().getLineBtnAction();
        ArrayList var5 = new ArrayList();
        var5.addAll(Arrays.asList(var6[var2]));

        for (int var4 = 0; var4 < var3.length; ++var4) {
            var5.remove(var3[var4] - var4);
        }

        var6[var2] = (String[]) var5.toArray(new String[var5.size()]);
    }

    protected void removeRearAirButtonByName(Context var1, String var2) {
        String[][] var4 = this.getAirUiSet(var1).getRearArea().getLineBtnAction();

        for (int var3 = 0; var3 < var4.length; ++var3) {
            ArrayList var5 = new ArrayList();
            var5.addAll(Arrays.asList(var4[var3]));
            if (var5.remove(var2)) {
                var4[var3] = (String[]) var5.toArray(new String[var5.size()]);
                return;
            }
        }

    }

    protected void removeSettingLeftItem(Context var1, int var2, int var3) {
        List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();

        for (int var4 = 0; var4 < var5.size(); ++var4) {
            if (var5.size() > var3) {
                var5.remove(var2);
            }
        }

    }

    protected void removeSettingLeftItemByIndexList(Context var1, int[] var2) {
        List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
        int var5 = var2.length;
        String[] var8 = new String[var5];

        for (int var3 = 0; var3 < var5; ++var3) {
            Iterator var7 = var6.iterator();

            for (int var4 = 0; var7.hasNext(); ++var4) {
                SettingPageUiSet.ListBean var9 = (SettingPageUiSet.ListBean) var7.next();
                if (var4 == var2[var3]) {
                    var8[var3] = var9.getTitleSrn();
                }
            }
        }

        this.removeSettingLeftItemByNameList(var1, var8);
    }

    protected void removeSettingLeftItemByIndexRange(Context var1, int var2, int var3) {
        int var4 = var3 - var2 + 1;
        int[] var5 = new int[var4];

        for (var3 = 0; var3 < var4; ++var3) {
            var5[var3] = var2++;
        }

        this.removeSettingLeftItemByIndexList(var1, var5);
    }

    protected void removeSettingLeftItemByNameList(Context var1, String[] var2) {
        List var7 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
        int var4 = var2.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            Iterator var5 = var7.iterator();

            while (var5.hasNext()) {
                SettingPageUiSet.ListBean var6 = (SettingPageUiSet.ListBean) var5.next();
                if (var2[var3].equals(var6.getTitleSrn())) {
                    var5.remove();
                }
            }
        }

    }

    protected void removeSettingRightItem(Context var1, int var2, int var3, int var4) {
        List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();

        for (int var5 = 0; var5 < ((SettingPageUiSet.ListBean) var6.get(var2)).getItemList().size(); ++var5) {
            if (((SettingPageUiSet.ListBean) var6.get(var2)).getItemList().size() > var4) {
                ((SettingPageUiSet.ListBean) var6.get(var2)).getItemList().remove(var3);
            }
        }

    }

    protected void removeSettingRightItemByNameList(Context var1, String[] var2) {
        List var8 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
        int var4 = var2.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            Iterator var5 = var8.iterator();

            while (var5.hasNext()) {
                Iterator var7 = ((SettingPageUiSet.ListBean) var5.next()).getItemList().iterator();

                while (var7.hasNext()) {
                    SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean) var7.next();
                    if (var2[var3].equals(var6.getTitleSrn())) {
                        var7.remove();
                    }
                }
            }
        }

    }

    protected void remvoeSettingLeftItemByName(Context var1, String var2) {
        if (!TextUtils.isEmpty(var2)) {
            List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();

            for (int var3 = 0; var3 < var4.size(); ++var3) {
                if (var2.equals(((SettingPageUiSet.ListBean) var4.get(var3)).getTitleSrn())) {
                    var4.remove(var3);
                }
            }

        }
    }

    protected void sendData(byte[] var1) {
        CanbusMsgSender.sendMsg(var1);
    }

    protected void showToast(int var1) {
        Toast.makeText(this.mContext, var1, 0).show();
    }

    public void updateUiByDifferentCar(Context var1) {
    }
}
