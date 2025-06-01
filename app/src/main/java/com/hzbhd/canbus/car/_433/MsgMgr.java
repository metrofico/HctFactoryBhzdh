package com.hzbhd.canbus.car._433;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   BluetoothView bluetoothView;
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private SparseArray mOriginalDeviceDataArray;
   private UiMgr mUiMgr;
   int nowLight = 0;
   int radioData0 = 0;
   int radioData1 = 0;
   int radioData2 = 0;
   byte[] songAlbumBytes;
   byte[] songArtistBytes;
   byte[] songNameBytes;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private List getOriginalDeviceUsbUpdateEntityList() {
      String var1 = this.df_2Integer.format((long)this.mCanBusInfoInt[4]) + ":" + this.df_2Integer.format((long)this.mCanBusInfoInt[5]);
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var2 = var2.append(this.getMsbLsbResult(var3[6], var3[7])).append("/");
      var3 = this.mCanBusInfoInt;
      String var4 = var2.append(this.getMsbLsbResult(var3[8], var3[9])).toString();
      String var6 = this.mCanBusInfoInt[11] + "%";
      ArrayList var5 = new ArrayList();
      if (var1 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(0, var1));
      }

      if (var4 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(1, var4));
      }

      if (var6 != null) {
         var5.add(new OriginalCarDeviceUpdateEntity(2, var6));
      }

      return var5;
   }

   private String getRunningState() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     return var1 != 5 ? this.mContext.getString(2131765842) : this.mContext.getString(2131765841);
                  } else {
                     return this.mContext.getString(2131765840);
                  }
               } else {
                  return this.mContext.getString(2131765839);
               }
            } else {
               return this.mContext.getString(2131765838);
            }
         } else {
            return this.mContext.getString(2131765837);
         }
      } else {
         return this.mContext.getString(2131765836);
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initOriginalCarDevice() {
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();
            this.this$0.initOriginalDeviceDataArray();
         }
      }).start();
   }

   private void initOriginalDeviceDataArray() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_433_Original_divice1", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_433_Original_divice2", (String)null));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_433_Original_divice3", (String)null));
      SparseArray var2 = new SparseArray();
      this.mOriginalDeviceDataArray = var2;
      var2.put(33, new OriginalDeviceData(var1, new String[]{"prev_disc", "left", "play", "stop", "right", "next_disc"}));
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void mToast(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            Toast.makeText(this.this$0.mContext, this.val$content, 0).show();
         }
      });
   }

   private void set0x20WheelKeyInfo() {
      int[] var1 = this.mCanBusInfoInt;
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
         case 5:
         case 6:
         default:
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 188, var1[3]);
            break;
         case 10:
            this.realKeyLongClick1(this.mContext, 189, var1[3]);
            break;
         case 11:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 12:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 189, var1[3]);
      }

   }

   private void set0x21OriginalInfo() {
      GeneralOriginalCarDeviceData.mList = this.getOriginalDeviceUsbUpdateEntityList();
      this.updateOriginalCarDeviceActivity((Bundle)null);
      String var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 1) {
         var1 = "IPOD";
      } else {
         var1 = "USB";
      }

      GeneralOriginalCarDeviceData.cdStatus = var1;
      GeneralOriginalCarDeviceData.runningState = this.getRunningState();
      OriginalDeviceData var2 = (OriginalDeviceData)this.mOriginalDeviceDataArray.get(33);
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(this.mContext);
      var3.setItems(var2.getItemList());
      var3.setRowBottomBtnAction(var2.getBottomBtns());
      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private void set0x24BasicInfo() {
      this.mCanBusInfoInt[3] = 0;
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0xD3BtInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1;
      if (var2[2] == 1) {
         var1 = var2[3];
         if (var1 != 1) {
            if (var1 == 2) {
               this.mToast(this.mContext.getString(2131765825));
            }
         } else {
            this.mToast(this.mContext.getString(2131765823));
         }
      }

      var2 = this.mCanBusInfoInt;
      if (var2[2] == 2) {
         var1 = var2[3];
         if (var1 != 1) {
            if (var1 == 2) {
               this.mToast(this.mContext.getString(2131765827));
            }
         } else {
            this.mToast(this.mContext.getString(2131765826));
         }
      }

      var2 = this.mCanBusInfoInt;
      if (var2[2] == 3) {
         var1 = var2[3];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.mToast(this.mContext.getString(2131765824));
                        }
                     } else {
                        this.mToast(this.mContext.getString(2131765832));
                     }
                  } else {
                     this.mToast(this.mContext.getString(2131765831));
                  }
               } else {
                  this.mToast(this.mContext.getString(2131765830));
               }
            } else {
               this.mToast(this.mContext.getString(2131765829));
            }
         } else {
            this.mToast(this.mContext.getString(2131765828));
         }
      }

   }

   private void setBackLight() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != this.nowLight) {
         this.nowLight = var1;
         this.setBacklightLevel(var1 / 51 + 1);
      }
   }

   private void setCanInfo() {
      int var1 = this.mCanBusInfoInt[1];
      if (var1 != 20) {
         if (var1 != 36) {
            if (var1 != 48) {
               if (var1 != 211) {
                  if (var1 != 32) {
                     if (var1 == 33) {
                        this.set0x21OriginalInfo();
                     }
                  } else {
                     this.set0x20WheelKeyInfo();
                  }
               } else {
                  this.set0xD3BtInfo();
               }
            } else {
               this.set0x30VersionInfo();
            }
         } else {
            this.set0x24BasicInfo();
         }
      } else {
         this.setBackLight();
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0xC0SourceType(7, 48);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).send0xC0SourceType(6, 48);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.getUiMgr(this.mContext).send0xC0SourceType(5, 64);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
      this.getUiMgr(this.mContext).send0xCBSendString(1, this.SplicingByte(new byte[]{22, -53, 1}, var2));
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);

      try {
         this.setCanInfo();
      } catch (Exception var3) {
         Log.e("CanBusError", var3.toString());
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      if (var2) {
         this.getUiMgr(this.mContext).send0xC4VolInfo(0);
      } else {
         this.getUiMgr(this.mContext).send0xC4VolInfo(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xC8TimeInfo(0, var10 ^ 1, var5, var6);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xC0SourceType(2, 16);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(var5, var3, 0, 0, var2 / 60, var2 % 60);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.initOriginalCarDevice();
      if (SharePreUtil.getIntValue(var1, this.getUiMgr(this.mContext).KEY_BT_SWITCH, 0) == 1) {
         this.getUiMgr(var1).send0xCABTCmd(3, 1);
      } else {
         this.getUiMgr(var1).send0xCABTCmd(3, 2);
      }

      if (SharePreUtil.getIntValue(var1, this.getUiMgr(this.mContext).KEY_BT_MEDIA_SWITCH, 0) == 1) {
         this.getUiMgr(var1).send0xCABTCmd(2, 1);
      } else {
         this.getUiMgr(var1).send0xCABTCmd(2, 0);
      }

   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      this.getUiMgr(this.mContext).send0xC0SourceType(8, 19);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(1, 101, this.getLsb(var3), this.getMsb(var3), var6, var7);

      try {
         this.songNameBytes = var21.getBytes("UTF-16LE");
         UiMgr var26 = this.getUiMgr(this.mContext);
         byte[] var28 = this.songNameBytes;
         var26.send0xCBSendString(2, this.SplicingByte(new byte[]{22, -53, 2}, var28));
         this.songAlbumBytes = var22.getBytes("UTF-16LE");
         UiMgr var29 = this.getUiMgr(this.mContext);
         byte[] var27 = this.songAlbumBytes;
         var29.send0xCBSendString(3, this.SplicingByte(new byte[]{22, -53, 3}, var27));
         this.songArtistBytes = var23.getBytes("UTF-16LE");
         var26 = this.getUiMgr(this.mContext);
         var28 = this.songArtistBytes;
         var26.send0xCBSendString(4, this.SplicingByte(new byte[]{22, -53, 4}, var28));
      } catch (UnsupportedEncodingException var25) {
         var25.printStackTrace();
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.getUiMgr(this.mContext).send0xC0SourceType(1, 1);
      if (var2.equals("FM")) {
         this.radioData0 = 0;
         this.radioData1 = this.getLsb((int)(Double.parseDouble(var3) * 100.0));
         this.radioData2 = this.getMsb((int)(Double.parseDouble(var3) * 100.0));
      } else if (var2.equals("FM1")) {
         this.radioData0 = 1;
         this.radioData1 = this.getLsb((int)(Double.parseDouble(var3) * 100.0));
         this.radioData2 = this.getMsb((int)(Double.parseDouble(var3) * 100.0));
      } else if (var2.equals("FM2")) {
         this.radioData0 = 2;
         this.radioData1 = this.getLsb((int)(Double.parseDouble(var3) * 100.0));
         this.radioData2 = this.getMsb((int)(Double.parseDouble(var3) * 100.0));
      } else if (var2.equals("FM3")) {
         this.radioData0 = 0;
         this.radioData1 = this.getLsb((int)(Double.parseDouble(var3) * 100.0));
         this.radioData2 = this.getMsb((int)(Double.parseDouble(var3) * 100.0));
      } else {
         this.radioData0 = 16;
         this.radioData1 = this.getLsb((int)Double.parseDouble(var3));
         this.radioData2 = this.getMsb((int)Double.parseDouble(var3));
      }

      this.getUiMgr(this.mContext).send0xC2RadioInfo(this.radioData0, this.radioData1, this.radioData2, var1);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (var1) {
         this.getUiMgr(this.mContext).send0xC0SourceType(0, 0);
      }

   }

   public void startPanel(boolean var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final boolean val$b;

         {
            this.this$0 = var1;
            this.val$b = var2;
         }

         public void callback() {
            if (this.this$0.bluetoothView == null) {
               this.this$0.bluetoothView = new BluetoothView(this.this$0.getActivity(), "", new BluetoothView.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent1() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 1});
                  }

                  public void clickEvent2() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 6});
                  }

                  public void clickEvent3() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 2});
                  }

                  public void clickEvent4() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 9});
                  }

                  public void clickEvent5() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 5});
                  }

                  public void clickEvent6() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 8});
                  }

                  public void clickEvent7() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 16});
                  }

                  public void clickEvent8() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 7});
                  }

                  public void clickEvent9() {
                     CanbusMsgSender.sendMsg(new byte[]{22, -52, 17});
                     this.this$1.this$0.updateSettings(this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).getSettingLeftIndexes(this.this$1.this$0.mContext, "_433_BT_Switch"), this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).getSettingRightIndex(this.this$1.this$0.mContext, "_433_BT_Switch", "_433_BT_Switch3"), 0);
                  }
               });
            }

            if (this.val$b) {
               this.this$0.bluetoothView.show();
            } else {
               this.this$0.bluetoothView.hide();
            }

         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      this.getUiMgr(this.mContext).send0xC0SourceType(8, 19);
      this.getUiMgr(this.mContext).send0xC3MediaInfo(1, 101, this.getLsb(var3), this.getMsb(var3), var6, var7);
   }

   private static class OriginalDeviceData {
      private final String[] bottomBtns;
      private final List list;

      public OriginalDeviceData(List var1) {
         this(var1, (String[])null);
      }

      public OriginalDeviceData(List var1, String[] var2) {
         this.list = var1;
         this.bottomBtns = var2;
      }

      public String[] getBottomBtns() {
         return this.bottomBtns;
      }

      public List getItemList() {
         return this.list;
      }
   }
}
