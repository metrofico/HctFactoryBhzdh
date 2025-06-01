package com.hzbhd.canbus.car._373;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.util.SharePreUtil;

public class AmpUtil {
   private static String _373_AMP_BASS;
   private static String _373_AMP_DATA6;
   private static String _373_AMP_FR;
   private static String _373_AMP_LR;
   private static String _373_AMP_MIDDLE;
   private static String _373_AMP_TREBLE;
   private static String _373_AMP_UI_BASS;
   private static String _373_AMP_UI_FR;
   private static String _373_AMP_UI_LR;
   private static String _373_AMP_UI_MIDDLE;
   private static String _373_AMP_UI_TREBLE;
   private static String _373_AMP_UI_VOL;
   private static String _373_AMP_VOL;
   MsgMgr mMsgMgr;

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   public static void saveAmpSendValue(Context var0, int var1, int var2) {
      switch (var1) {
         case 1:
            SharePreUtil.setIntValue(var0, _373_AMP_VOL, var2);
            break;
         case 2:
            SharePreUtil.setIntValue(var0, _373_AMP_TREBLE, var2);
            break;
         case 3:
            SharePreUtil.setIntValue(var0, _373_AMP_MIDDLE, var2);
            break;
         case 4:
            SharePreUtil.setIntValue(var0, _373_AMP_BASS, var2);
            break;
         case 5:
            SharePreUtil.setIntValue(var0, _373_AMP_FR, var2);
            break;
         case 6:
            SharePreUtil.setIntValue(var0, _373_AMP_LR, var2);
            break;
         case 7:
            SharePreUtil.setIntValue(var0, _373_AMP_DATA6, var2);
      }

   }

   public static void saveAmpUIValue(Context var0) {
      SharePreUtil.setIntValue(var0, _373_AMP_UI_VOL, GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(var0, _373_AMP_UI_TREBLE, GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(var0, _373_AMP_UI_MIDDLE, GeneralAmplifierData.bandMiddle);
      SharePreUtil.setIntValue(var0, _373_AMP_UI_BASS, GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(var0, _373_AMP_UI_FR, GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(var0, _373_AMP_UI_LR, GeneralAmplifierData.leftRight);
   }

   private void sendAmplifierInfo(Context var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte)SharePreUtil.getIntValue(var1, _373_AMP_VOL, 0), (byte)SharePreUtil.getIntValue(var1, _373_AMP_LR, 0), (byte)SharePreUtil.getIntValue(var1, _373_AMP_FR, 0), (byte)SharePreUtil.getIntValue(var1, _373_AMP_BASS, 0), (byte)SharePreUtil.getIntValue(var1, _373_AMP_MIDDLE, 0), (byte)SharePreUtil.getIntValue(var1, _373_AMP_TREBLE, 0), (byte)SharePreUtil.getIntValue(var1, _373_AMP_DATA6, 0)});
   }

   public void initAmpData(Context var1) {
      this.sendAmplifierInfo(var1);
   }

   public void intiAmpUi(Context var1) {
      GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, _373_AMP_UI_VOL, 0);
      GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, _373_AMP_UI_TREBLE, 0);
      GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, _373_AMP_UI_MIDDLE, 0);
      GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, _373_AMP_UI_BASS, 0);
      GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, _373_AMP_UI_FR, 0);
      GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, _373_AMP_UI_LR, 0);
      this.getMsgMgr(var1).updateAmp();
   }
}
