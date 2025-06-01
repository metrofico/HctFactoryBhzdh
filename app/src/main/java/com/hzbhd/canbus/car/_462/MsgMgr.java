package com.hzbhd.canbus.car._462;

import android.content.Context;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgMgr extends AbstractMsgMgr {
   CanDocking docking;
   private UiMgr mUiMgr;
   private MyPanoramicView panoramicView;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String intArrayToJsonStr(int[] var1) {
      JSONObject var3 = new JSONObject();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         try {
            StringBuilder var4 = new StringBuilder();
            var3.put(var4.append("Data").append(var2).toString(), var1[var2]);
         } catch (JSONException var5) {
            var5.printStackTrace();
         }
      }

      return var3.toString();
   }

   protected void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportAllCanBusData(this.intArrayToJsonStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.afterServiceNormalSetting(var1);
      this.getUiMgr(var1).registerEvent(var1);
      (new ArrayList()).add("");
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.docking.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      if (this.getMsDataType(var4) == -1431655766 || this.getMsDataType(var4) == -1145324613) {
         this.ShareBasicInfo(var4);
      }

      if (this.getMsDataType(var4) == 418326593) {
         int var3 = var4[7];
         if (var3 == 1) {
            this.get360View(var1).openAuto();
         } else if (var3 == 2) {
            this.get360View(var1).closeAuto();
         }
      }

   }

   public MyPanoramicView get360View(Context var1) {
      if (this.panoramicView == null) {
         this.panoramicView = (MyPanoramicView)this.getUiMgr(var1).getCusPanoramicView(var1);
      }

      return this.panoramicView;
   }

   protected int getMsDataType(int[] var1) {
      int var4 = var1[2];
      int var2 = var1[3];
      int var3 = var1[4];
      return var1[5] & 255 | (var4 & 255) << 24 | (var2 & 255) << 16 | (var3 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (this.docking == null) {
         this.docking = CanVm.Companion.getVm().getCanDocking();
      }

      this.docking.initCommand(var1);
   }
}
