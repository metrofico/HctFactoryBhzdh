package com.hzbhd.canbus.car._16;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.receiver.AMapBroadcastReceiver;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static final Charset CHARSETNAME;
   static boolean DevicePower;
   static boolean DeviceRearLock;
   static final int SHARE_16_AMPLIFIER_BAND_OFFSET = 2;
   static final int SHARE_16_AMPLIFIER_FADE_OFFSET = 7;
   static final String SHARE_16_IS_HAVE_SPARE_TIRE = "share_16_is_have_spare_tire";
   static final String SHARE_16_IS_SUPPOT_HYBRID = "share_16_is_suppot_hybrid";
   static final String SHARE_16_IS_SUPPOT_TIRE = "share_16_is_suppot_tire";
   private static String SHARE_16_LANGUAGE;
   static int mOriginalInt;
   private String btMusicAlbum;
   private String btMusicArtist;
   private String btMusicTitle;
   private boolean isStartAMap = false;
   private int[] m0x62DataNow;
   private int[] m0x66DataNow;
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private boolean mBackOpen;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusAirInfoCopy3;
   private Context mContext;
   private byte mCurrBandByte;
   private int[] mFrontRadarDataNow;
   private boolean mIsAirFirst = true;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private String mMileageUnit;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private boolean mPanoramicStatusNow;
   private int[] mRearRadarDataNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private int mStoagePath;
   private TireAlramView mTireAlramView;
   private int mTireStatus;
   private UiMgr mUiMgr;
   private byte[] mVersionInfoNow;
   private int mdifferentId;
   private String musicAlbum;
   private String musicArtist;
   private int musicCount;
   private int musicIndex;
   private String musicTitle;
   private boolean tempRear;
   private int videoPlayIndex = -1;
   private int videoTotalCount = -1;

   static {
      CHARSETNAME = StandardCharsets.UTF_16LE;
      SHARE_16_LANGUAGE = "share_16_language";
      DevicePower = false;
      DeviceRearLock = false;
      mOriginalInt = -1;
   }

   private void ContactPerson(byte[] var1) {
      String var3 = new String(var1);

      try {
         var1 = DataHandleUtils.makeBytesFixedLength(var3.getBytes(CHARSETNAME.name()), 23);
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -102}, var1));
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
      }

   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void changeOriginalDevicePage(String[] var1, String[] var2, boolean var3) {
      this.mOriginalCarDevicePageUiSet.setRowTopBtnAction(var1);
      this.mOriginalCarDevicePageUiSet.setRowBottomBtnAction(var2);
      this.mOriginalCarDevicePageUiSet.setHaveSongList(var3);
      GeneralOriginalCarDeviceData.mList = null;
      this.updateOriginalDeviceWithInit();
   }

   private int getAirWhat() {
   }

   private int getAmplifierSettingsIndex() {
      byte var1;
      if (this.getUiMgr(this.mContext).hasAmplifierNoSettings()) {
         var1 = 0;
      } else {
         var1 = 5;
      }

      return var1;
   }

   private String getCDState1(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : this.mContext.getString(2131758353);
         } else {
            return this.mContext.getString(2131758352);
         }
      } else {
         return this.mContext.getString(2131758351);
      }
   }

   private String getCDState2(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "" : this.mContext.getString(2131758355);
      } else {
         return this.mContext.getString(2131758354);
      }
   }

   private String getCDStateStr(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        return var1 != 7 ? this.mContext.getString(2131758258) : this.mContext.getString(2131758264);
                     } else {
                        return this.mContext.getString(2131758263);
                     }
                  } else {
                     return this.mContext.getString(2131758262);
                  }
               } else {
                  return this.mContext.getString(2131758261);
               }
            } else {
               return this.mContext.getString(2131758260);
            }
         } else {
            return this.mContext.getString(2131758259);
         }
      } else {
         return this.mContext.getString(2131758258);
      }
   }

   private String getElectricUnit(int var1) {
      return var1 == 1 ? this.mContext.getString(2131770218) : this.mContext.getString(2131770211);
   }

   private String getMileageInfoUnit(int var1) {
      if (var1 == 1) {
         return this.mContext.getString(2131767389);
      } else {
         return var1 == 2 ? this.mContext.getString(2131770212) : "";
      }
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private int getNaviDirection(int var1) {
      if (var1 != 19) {
         switch (var1) {
            case 2:
               return 4;
            case 3:
               return 5;
            case 4:
               return 2;
            case 5:
               return 3;
            case 6:
               return 6;
            case 7:
               return 7;
            case 8:
               return 8;
            case 9:
               return 1;
            default:
               return 0;
         }
      } else {
         return 9;
      }
   }

   private String getOilUnit(int var1) {
      if (var1 == 1) {
         return this.mContext.getString(2131768821);
      } else {
         return var1 == 2 ? this.mContext.getString(2131768820) : this.mContext.getString(2131768829);
      }
   }

   private String getPlayMode() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     return var1 != 255 ? " " : "Others";
                  } else {
                     return "USB";
                  }
               } else {
                  return "AUX";
               }
            } else {
               return "CD";
            }
         } else {
            return "Radio";
         }
      } else {
         return "Media OFF";
      }
   }

   private String getPlayMode0x66() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 255) {
         switch (var1) {
            case 0:
               return "OFF";
            case 1:
               return "DISC";
            case 2:
               return "DISC CD";
            case 3:
               return "DISC DVD";
            case 4:
               return "SD";
            case 5:
               return "USB";
            case 6:
               return "A/V";
            default:
               return " ";
         }
      } else {
         return "Others";
      }
   }

   private String getRadioStatus() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 1) {
         if (var1 != 2) {
            return var1 != 16 ? " " : "AM";
         } else {
            return "FM2";
         }
      } else {
         return "FM1";
      }
   }

   private String getRunningState0x66() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 7);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        return var1 != 7 ? " " : this.mContext.getString(2131758318);
                     } else {
                        return this.mContext.getString(2131758317);
                     }
                  } else {
                     return this.mContext.getString(2131758316);
                  }
               } else {
                  return this.mContext.getString(2131758315);
               }
            } else {
               return this.mContext.getString(2131758314);
            }
         } else {
            return this.mContext.getString(2131758313);
         }
      } else {
         return this.mContext.getString(2131758312);
      }
   }

   private TireAlramView getTireAlramView() {
      if (this.mTireAlramView == null) {
         this.mTireAlramView = new TireAlramView(this);
      }

      return this.mTireAlramView;
   }

   private float getTireRule(int var1) {
      if (var1 != 0) {
         return var1 != 2 ? 1.0F : 0.4F;
      } else {
         return 10.0F;
      }
   }

   private String getTireUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : " KPA";
         } else {
            return " PSI";
         }
      } else {
         return " BAR";
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, this.getUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final byte[][] bytesArray;
         int i;
         final MsgMgr this$0;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$timerUtil = var2;
            byte[] var6 = new byte[]{22, -125, 38, (byte)SharePreUtil.getIntValue(var1.mContext, MsgMgr.SHARE_16_LANGUAGE, 0)};
            byte[] var8 = new byte[]{22, -124, 1, (byte)(-GeneralAmplifierData.frontRear + 7)};
            byte var3 = (byte)(GeneralAmplifierData.leftRight + 7);
            byte[] var7 = new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)};
            byte[] var4 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)};
            byte[] var9 = new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandMiddle + 2)};
            byte[] var5 = new byte[]{22, -124, 7, (byte)GeneralAmplifierData.volume};
            this.bytesArray = new byte[][]{var6, var8, {22, -124, 2, var3}, var7, var4, var9, var5};
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.bytesArray;
            if (var1 < var2.length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 100L, 100L);
   }

   private boolean is0x62DataChange() {
      if (Arrays.equals(this.m0x62DataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x62DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x66DataChange() {
      if (Arrays.equals(this.m0x66DataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x66DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackOpen == GeneralDoorData.isBackOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mBackOpen = GeneralDoorData.isBackOpen;
         return true;
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return GeneralAirData.power ^ true;
      } else {
         return false;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      boolean var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void portraitCommand(Context var1) {
      if (var1.getResources().getConfiguration().orientation == 1) {
         int var4 = this.getEachCanId();
         short var3 = 130;
         short var2;
         if (var4 != 1) {
            if (var4 != 2) {
               label60: {
                  if (var4 != 4) {
                     if (var4 == 5) {
                        var2 = 24;
                        break label60;
                     }

                     if (var4 == 6) {
                        var2 = 8;
                        break label60;
                     }

                     if (var4 != 12) {
                        if (var4 != 15) {
                           if (var4 != 20) {
                              if (var4 != 32) {
                                 var2 = var3;
                                 if (var4 != 58) {
                                    var2 = var3;
                                    if (var4 != 59) {
                                       switch (var4) {
                                          case 34:
                                          case 35:
                                             var2 = 1;
                                             break label60;
                                          case 36:
                                             var2 = 2;
                                             break label60;
                                          case 37:
                                          case 38:
                                             var2 = 3;
                                             break label60;
                                          case 39:
                                             var2 = 5;
                                             break label60;
                                          case 40:
                                             var2 = 6;
                                             break label60;
                                          case 41:
                                             var2 = 9;
                                             break label60;
                                          case 42:
                                             var2 = 10;
                                             break label60;
                                          case 43:
                                             var2 = 11;
                                             break label60;
                                          case 44:
                                             var2 = 12;
                                             break label60;
                                          case 45:
                                             var2 = 14;
                                             break label60;
                                          case 46:
                                             var2 = 15;
                                             break label60;
                                          case 47:
                                             var2 = 16;
                                             break label60;
                                          case 48:
                                             var2 = 17;
                                             break label60;
                                          case 49:
                                             var2 = 18;
                                             break label60;
                                          case 50:
                                             var2 = 19;
                                             break label60;
                                          case 51:
                                             var2 = 20;
                                             break label60;
                                          case 52:
                                             var2 = 129;
                                             break label60;
                                          default:
                                             var2 = 255;
                                       }
                                    }
                                 }
                              } else {
                                 var2 = 128;
                              }
                           } else {
                              var2 = 23;
                           }
                        } else {
                           var2 = 21;
                        }
                        break label60;
                     }
                  }

                  var2 = 0;
               }
            } else {
               var2 = 13;
            }
         } else {
            var2 = 22;
         }

         if (var2 != 255) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)var2});
         }
      }

   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoByte[3]);
   }

   private String resolveAirTemp_Front(int var1) {
      String var3;
      label70: {
         boolean var2 = GeneralAirData.fahrenheit_celsius;
         var3 = "HIGH";
         if (var2) {
            if (var1 != 0) {
               if (var1 == 255) {
                  return var3;
               }

               if (var1 >= 1 && var1 <= 254) {
                  var3 = var1 + this.getTempUnitF(this.mContext);
                  return var3;
               }
               break label70;
            }
         } else if (var1 != 0) {
            if (var1 == 31) {
               return var3;
            }

            if (var1 >= 1 && var1 <= 29) {
               var3 = (float)(var1 - 1) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
               return var3;
            }

            if (var1 >= 32 && var1 <= 35) {
               var3 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
               return var3;
            }

            if (var1 >= 36 && var1 <= 37) {
               var3 = (float)var1 * 0.5F - 3.0F + this.getTempUnitC(this.mContext);
               return var3;
            }
            break label70;
         }

         var3 = "LOW";
         return var3;
      }

      var3 = " ";
      return var3;
   }

   private String resolveAirTemp_Rear(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LOW";
      } else if (var1 == 31) {
         var2 = "HIGH";
      } else if (var1 >= 1 && var1 <= 29) {
         var2 = (float)(var1 - 1) * 0.5F + 18.0F + this.getTempUnitC(this.mContext);
      } else if (var1 >= 33 && var1 <= 38) {
         var2 = (double)(var1 - 33) * 0.5 + 15.0 + this.getTempUnitC(this.mContext);
      } else {
         var2 = " ";
      }

      return var2;
   }

   private int resolveAmpData(int var1, int var2) {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1], var2, 4);
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[7];
      String var2;
      if (var1 >= 0 && var1 <= 180) {
         var2 = (float)this.mCanBusInfoByte[7] * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void sendAirCommand(int var1) {
      (new Thread(this, var1) {
         final MsgMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendPhoneChange(byte[] param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   private void sendVoiceMsg(int var1, boolean var2) {
      if (var2) {
         CanbusMsgSender.sendMsg(new byte[]{24, -121, (byte)var1, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{24, -121, (byte)var1, 0});
      }

   }

   private void set15minOilInfo0x27() {
      int[] var31 = this.mCanBusInfoInt;
      if (var31.length >= 33) {
         String var33 = this.getOilUnit(var31[2]);
         int[] var32 = this.mCanBusInfoInt;
         int var7 = var32[3];
         int var29 = var32[4];
         int var15 = var32[5];
         int var6 = var32[6];
         int var16 = var32[7];
         int var22 = var32[8];
         int var5 = var32[9];
         int var20 = var32[10];
         int var11 = var32[11];
         int var19 = var32[12];
         int var24 = var32[13];
         int var26 = var32[14];
         int var27 = var32[15];
         int var23 = var32[16];
         int var17 = var32[17];
         int var13 = var32[18];
         int var12 = var32[19];
         int var9 = var32[20];
         int var18 = var32[21];
         int var30 = var32[22];
         int var4 = var32[23];
         int var14 = var32[24];
         int var1 = var32[25];
         int var21 = var32[26];
         int var2 = var32[27];
         int var28 = var32[28];
         int var10 = var32[29];
         int var25 = var32[30];
         int var3 = var32[31];
         int var8 = var32[32];
         ArrayList var34 = new ArrayList();
         var34.add(new DriverUpdateEntity(2, 0, this.showOilStr(var3 * 256 + var8, var33)));
         var34.add(new DriverUpdateEntity(2, 1, this.showOilStr(var10 * 256 + var25, var33)));
         var34.add(new DriverUpdateEntity(2, 2, this.showOilStr(var2 * 256 + var28, var33)));
         var34.add(new DriverUpdateEntity(2, 3, this.showOilStr(var1 * 256 + var21, var33)));
         var34.add(new DriverUpdateEntity(2, 4, this.showOilStr(var4 * 256 + var14, var33)));
         var34.add(new DriverUpdateEntity(2, 5, this.showOilStr(var18 * 256 + var30, var33)));
         var34.add(new DriverUpdateEntity(2, 6, this.showOilStr(var12 * 256 + var9, var33)));
         var34.add(new DriverUpdateEntity(2, 7, this.showOilStr(var17 * 256 + var13, var33)));
         var34.add(new DriverUpdateEntity(2, 8, this.showOilStr(var27 * 256 + var23, var33)));
         var34.add(new DriverUpdateEntity(2, 9, this.showOilStr(var24 * 256 + var26, var33)));
         var34.add(new DriverUpdateEntity(2, 10, this.showOilStr(var11 * 256 + var19, var33)));
         var34.add(new DriverUpdateEntity(2, 11, this.showOilStr(var5 * 256 + var20, var33)));
         var34.add(new DriverUpdateEntity(2, 12, this.showOilStr(var16 * 256 + var22, var33)));
         var34.add(new DriverUpdateEntity(2, 13, this.showOilStr(var15 * 256 + var6, var33)));
         var34.add(new DriverUpdateEntity(2, 14, this.showOilStr(var7 * 256 + var29, var33)));
         this.updateGeneralDriveData(var34);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void setAirData0x28() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      int var3 = this.getAirWhat();
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      byte[] var6 = this.mCanBusInfoByte;
      var6 = Arrays.copyOf(var6, var6.length);
      var6[3] = (byte)(var6[3] & 239);
      var6[7] = 0;
      if (!this.isAirMsgRepeat3(var6)) {
         if (!this.isFirst()) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
            GeneralAirData.auto_wind_strong = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = this.resolveAirTemp_Front(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp_Front(this.mCanBusInfoInt[5]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
            GeneralAirData.auto_manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]) ^ true;
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_left_temperature = this.resolveAirTemp_Rear(this.mCanBusInfoInt[8]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
            GeneralAirData.rear_right_temperature = this.resolveAirTemp_Rear(this.mCanBusInfoInt[10]);
            boolean var4;
            if (this.getUiMgr(this.mContext).isLanKu()) {
               var4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
               int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
               int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_foot = false;
               if (var4) {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               } else {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           GeneralAirData.front_right_blow_foot = true;
                        }
                     } else {
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_right_blow_head = true;
                  }

                  if (var1 != 1) {
                     if (var1 != 2) {
                        if (var1 == 3) {
                           GeneralAirData.front_left_blow_foot = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                  }
               }
            }

            GeneralAirData.clean_air = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]);
            boolean var5 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]);
            var4 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]);
            if (var5 && !var4) {
               GeneralAirData.rear_temperature = "LOW";
            } else if (!var5 && var4) {
               GeneralAirData.rear_temperature = "HI";
            }

            GeneralAirData.rear_ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]);
            if (GeneralAirData.rear_auto) {
               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2) == 1) {
                  GeneralAirData.rear_wind_level = 2;
               } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2) == 2) {
                  GeneralAirData.rear_wind_level = 5;
               } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2) == 3) {
                  GeneralAirData.rear_wind_level = 7;
               }
            }

            this.updateAirActivity(this.mContext, var3);
         }
      }
   }

   private void setAirData0x58() {
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      GeneralAirData.swing = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      GeneralAirData.econ = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
      if (this.tempRear != GeneralAirData.rear) {
         this.updateAirActivity(this.mContext, 1002);
         this.tempRear = GeneralAirData.rear;
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setAmplifierData0x31() {
      GeneralAmplifierData.frontRear = -(this.resolveAmpData(2, 4) - 7);
      GeneralAmplifierData.leftRight = this.resolveAmpData(2, 0) - 7;
      GeneralAmplifierData.bandBass = this.resolveAmpData(3, 4) - 2;
      GeneralAmplifierData.bandTreble = this.resolveAmpData(3, 0) - 2;
      GeneralAmplifierData.bandMiddle = this.resolveAmpData(4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      this.saveAmplifierData(this.mContext, this.mCanId);
      this.updateAmplifierActivity((Bundle)null);
      if (this.getUiMgr(this.mContext).hasAmplifier()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(this.getAmplifierSettingsIndex(), 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
         var1.add(new SettingUpdateEntity(this.getAmplifierSettingsIndex(), 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_title_21"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_title_21", "_16_ampl_settings_Position"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setBrightness(int var1) {
      this.setBacklightLevel(DataHandleUtils.rangeNumber(MediaShareData.Screen.INSTANCE.getScreenBacklight() + var1, 1, 5));
   }

   private void setCDState0x61() {
      boolean var4 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      boolean var7 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var8 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      boolean var5 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var9 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      boolean var6 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      GeneralOriginalCarDeviceData.cdStatus = var1 + "";
      GeneralOriginalCarDeviceData.runningState = this.getCDStateStr(var2);
      ArrayList var11 = new ArrayList();
      StringBuilder var12 = (new StringBuilder()).append(this.mContext.getString(2131758275)).append(" : ");
      String var10;
      if (var6) {
         var10 = this.mContext.getString(2131758267);
      } else {
         var10 = this.mContext.getString(2131758269);
      }

      var11.add(new OriginalCarDeviceUpdateEntity(0, var12.append(var10).toString()));
      var12 = (new StringBuilder()).append(this.mContext.getString(2131758274)).append(" : ");
      if (var9) {
         var10 = this.mContext.getString(2131758267);
      } else {
         var10 = this.mContext.getString(2131758269);
      }

      var11.add(new OriginalCarDeviceUpdateEntity(1, var12.append(var10).toString()));
      var12 = (new StringBuilder()).append(this.mContext.getString(2131758273)).append(" : ");
      if (var5) {
         var10 = this.mContext.getString(2131758267);
      } else {
         var10 = this.mContext.getString(2131758269);
      }

      var11.add(new OriginalCarDeviceUpdateEntity(2, var12.append(var10).toString()));
      var12 = (new StringBuilder()).append(this.mContext.getString(2131758272)).append(" : ");
      if (var8) {
         var10 = this.mContext.getString(2131758267);
      } else {
         var10 = this.mContext.getString(2131758269);
      }

      var11.add(new OriginalCarDeviceUpdateEntity(3, var12.append(var10).toString()));
      var12 = (new StringBuilder()).append(this.mContext.getString(2131758271)).append(" : ");
      if (var7) {
         var10 = this.mContext.getString(2131758267);
      } else {
         var10 = this.mContext.getString(2131758269);
      }

      var11.add(new OriginalCarDeviceUpdateEntity(4, var12.append(var10).toString()));
      var12 = (new StringBuilder()).append(this.mContext.getString(2131758270)).append(" : ");
      if (var4) {
         var10 = this.mContext.getString(2131758267);
      } else {
         var10 = this.mContext.getString(2131758269);
      }

      var11.add(new OriginalCarDeviceUpdateEntity(5, var12.append(var10).toString()));
      var11.add(new OriginalCarDeviceUpdateEntity(6, this.mContext.getString(2131758266) + " : " + var3));
      GeneralOriginalCarDeviceData.mList = var11;
      this.mOriginalCarDevicePageUiSet.setHaveSongList(false);
      GeneralOriginalCarDeviceData.songList = new ArrayList();
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setCarInfo0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

      boolean var2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var4 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      boolean var5 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      boolean var3 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      ArrayList var7 = new ArrayList();
      int var1;
      Context var6;
      if (var5) {
         var6 = this.mContext;
         var1 = 2131769741;
      } else {
         var6 = this.mContext;
         var1 = 2131769742;
      }

      var7.add(new DriverUpdateEntity(0, 3, var6.getString(var1)));
      if (var3) {
         var6 = this.mContext;
         var1 = 2131768216;
      } else {
         var6 = this.mContext;
         var1 = 2131768215;
      }

      var7.add(new DriverUpdateEntity(0, 4, var6.getString(var1)));
      if (var2) {
         if (var4) {
            var6 = this.mContext;
            var1 = 2131758225;
         } else {
            var6 = this.mContext;
            var1 = 2131758226;
         }

         var7.add(new DriverUpdateEntity(0, 5, var6.getString(var1)));
      } else {
         var7.add(new DriverUpdateEntity(0, 5, this.mContext.getString(2131769909)));
      }

      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCdPage() {
      this.changeOriginalDevicePage((String[])null, new String[]{"prev_disc", "up", "left", "play_pause", "right", "down", "next_disc", "random", "repeat"}, true);
   }

   private void setElectric0x2B() {
      String var5 = this.getElectricUnit(this.mCanBusInfoInt[2]);
      int[] var6 = this.mCanBusInfoInt;
      int var3 = var6[3];
      int var4 = var6[4];
      int var1 = var6[5];
      int var2 = var6[6];
      if (TextUtils.isEmpty(this.mMileageUnit)) {
         this.mMileageUnit = "";
      }

      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(0, 0, String.format("%.1f", (double)(var3 * 256 + var4) * 0.1) + var5));
      var7.add(new DriverUpdateEntity(0, 1, String.format("%.1f", (double)(var1 * 256 + var2) * 0.1) + this.mMileageUnit));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadarData0x1D() {
      if (this.isFrontRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setHistoryOilInfo0x23() {
      String var13 = this.getOilUnit(this.mCanBusInfoInt[2]);
      int[] var14 = this.mCanBusInfoInt;
      int var8 = var14[3];
      int var9 = var14[4];
      int var6 = var14[5];
      int var11 = var14[6];
      int var2 = var14[7];
      int var3 = var14[8];
      int var1 = var14[9];
      int var12 = var14[10];
      int var7 = var14[11];
      int var4 = var14[12];
      int var10 = var14[13];
      int var5 = var14[14];
      ArrayList var16 = new ArrayList();
      var16.add(new DriverUpdateEntity(1, 1, this.showOilStr(var8 * 256 + var9, var13)));
      var16.add(new DriverUpdateEntity(1, 2, this.showOilStr(var6 * 256 + var11, var13)));
      var16.add(new DriverUpdateEntity(1, 3, this.showOilStr(var2 * 256 + var3, var13)));
      var16.add(new DriverUpdateEntity(1, 4, this.showOilStr(var1 * 256 + var12, var13)));
      var16.add(new DriverUpdateEntity(1, 5, this.showOilStr(var7 * 256 + var4, var13)));
      var16.add(new DriverUpdateEntity(1, 6, this.showOilStr(var10 * 256 + var5, var13)));
      this.updateGeneralDriveData(var16);
      this.updateDriveDataActivity((Bundle)null);
      ArrayList var15 = new ArrayList();
      var15.add(new SettingUpdateEntity(3, 3, this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var15);
      this.updateSettingActivity((Bundle)null);
   }

   private void setHybrid0x1F() {
      SharePreUtil.setBoolValue(this.mContext, "share_16_is_suppot_hybrid", DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      this.updateHybirdActivity((Bundle)null);
   }

   private void setInstantOilInfo0x22() {
      String var2 = this.getOilUnit(this.mCanBusInfoInt[2]);
      int[] var3 = this.mCanBusInfoInt;
      float var1 = (float)(var3[3] * 256 + var3[4]);
      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(1, 0, String.format("%.1f", (double)var1 * 0.1) + var2));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setKeyAirEvent0x51() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 22) {
         if (var1 != 129) {
            if (var1 != 130) {
               switch (var1) {
                  case 133:
                     this.realKeyClick3(this.mContext, 48, 0, var2[3]);
                     break;
                  case 134:
                     this.realKeyClick3(this.mContext, 47, 0, var2[3]);
                     break;
                  case 135:
                     this.realKeyLongClick1(this.mContext, 134, var2[3]);
               }
            } else {
               this.realKeyClick3(this.mContext, 8, 0, var2[3]);
            }
         } else {
            this.realKeyClick3(this.mContext, 7, 0, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 49, var2[3]);
      }

   }

   private void setKeyEvent0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         label174: {
            label175: {
               label176: {
                  label177: {
                     if (var1 != 1) {
                        if (var1 == 2) {
                           break label177;
                        }

                        if (var1 == 3) {
                           this.realKeyClick(48);
                           return;
                        }

                        if (var1 == 4) {
                           this.realKeyClick(47);
                           return;
                        }

                        switch (var1) {
                           case 7:
                              break label176;
                           case 8:
                              this.realKeyClick(187);
                              return;
                           case 9:
                              this.realKeyClick(14);
                              return;
                           case 10:
                              this.realKeyClick(15);
                              return;
                           case 11:
                              this.realKeyClick(52);
                              return;
                           default:
                              switch (var1) {
                                 case 19:
                                    break label175;
                                 case 20:
                                    break label174;
                                 case 21:
                                    this.realKeyClick(50);
                                    return;
                                 case 22:
                                    this.realKeyClick(49);
                                    return;
                                 default:
                                    switch (var1) {
                                       case 129:
                                          break;
                                       case 130:
                                          break label177;
                                       case 131:
                                          break label175;
                                       case 132:
                                          break label174;
                                       case 133:
                                          this.realKeyClick(21);
                                          return;
                                       case 134:
                                          this.realKeyClick(20);
                                          return;
                                       case 135:
                                          this.realKeyClick(134);
                                          return;
                                       case 136:
                                          break label176;
                                       default:
                                          return;
                                    }
                              }
                        }
                     }

                     this.realKeyClick(7);
                     return;
                  }

                  this.realKeyClick(8);
                  return;
               }

               this.realKeyClick(2);
               return;
            }

            this.realKeyClick(45);
            return;
         }

         this.realKeyClick(46);
      } else {
         this.realKeyClick(0);
      }

   }

   private void setMediaCommand0x2F() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2 && var1 != 4) {
               if (var1 != 32) {
                  switch (var1) {
                     case 17:
                        this.realKeyClick(77);
                        break;
                     case 18:
                        this.realKeyClick(76);
                        break;
                     case 19:
                        this.realKeyClick(68);
                        break;
                     case 20:
                        this.realKeyClick(59);
                        break;
                     case 21:
                        this.realKeyClick(141);
                  }
               } else {
                  this.realKeyClick(50);
               }
            } else {
               this.realKeyClick(15);
            }
         } else {
            this.realKeyClick(14);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setMileageInfo0x21() {
      int[] var5 = this.mCanBusInfoInt;
      float var1 = (float)(var5[2] * 256 + var5[3]);
      int var4 = var5[4] * 256 + var5[5];
      int var2 = var5[6];
      int var3 = var5[7];
      String var6 = var4 / 60 + this.mContext.getString(2131768868) + var4 % 60 + this.mContext.getString(2131769310);
      this.mMileageUnit = this.getMileageInfoUnit(this.mCanBusInfoInt[8]);
      var4 = this.mCanBusInfoInt[8];
      String var9;
      if (var4 == 1) {
         var9 = "MILE";
      } else if (var4 == 2) {
         var9 = "KM";
      } else {
         var9 = "";
      }

      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(0, 0, String.format("%.1f", (double)var1 * 0.1) + this.mMileageUnit));
      var7.add(new DriverUpdateEntity(0, 1, var6));
      var7.add(new DriverUpdateEntity(0, 2, var2 * 256 + var3 + this.mMileageUnit));
      StringBuilder var8 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var7.add(new DriverUpdateEntity(0, 5, var8.append(DataHandleUtils.getMsbLsbResult(var10[9], var10[10])).append(var9).toString()));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginal0x62() {
      if (this.is0x62DataChange()) {
         GeneralOriginalCarDeviceData.cdStatus = this.getPlayMode();
         int var1 = this.mCanBusInfoInt[2];
         int var2 = 0;
         int var3 = 0;
         int[] var4;
         int[] var5;
         StringBuilder var6;
         ArrayList var14;
         StringBuilder var16;
         int[] var18;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 4) {
                  mOriginalInt = 3;
                  this.setUsbPage();
                  GeneralOriginalCarDeviceData.cdStatus = "USB";
                  var4 = this.mCanBusInfoInt;
                  if (var4.length >= 5) {
                     var1 = DataHandleUtils.getIntFromByteWithBit(var4[4], 0, 4);
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 == 2) {
                              GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131758341);
                           }
                        } else {
                           GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131758340);
                        }
                     } else {
                        GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131758339);
                     }
                  }

                  var14 = new ArrayList();
                  var5 = this.mCanBusInfoInt;
                  if (var5.length >= 6) {
                     var1 = DataHandleUtils.getIntFromByteWithBit(var5[5], 4, 4);
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 == 2) {
                              var14.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131758342) + ": " + this.mContext.getString(2131758346)));
                           }
                        } else {
                           var14.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131758342) + ": " + this.mContext.getString(2131758345)));
                        }
                     } else {
                        var14.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131758342) + ": " + this.mContext.getString(2131758344)));
                     }
                  }

                  var5 = this.mCanBusInfoInt;
                  if (var5.length >= 6) {
                     var1 = DataHandleUtils.getIntFromByteWithBit(var5[5], 0, 4);
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 == 2) {
                              var14.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(2131758347) + ": " + this.mContext.getString(2131758350)));
                           }
                        } else {
                           var14.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(2131758347) + ": " + this.mContext.getString(2131758349)));
                        }
                     } else {
                        var14.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(2131758347) + ": " + this.mContext.getString(2131758348)));
                     }
                  }

                  if (this.mCanBusInfoInt.length >= 10) {
                     var16 = (new StringBuilder()).append(this.mContext.getString(2131758307)).append(": ");
                     var18 = this.mCanBusInfoInt;
                     var14.add(new OriginalCarDeviceUpdateEntity(2, var16.append(this.getMsbLsbResult(var18[9], var18[8])).toString()));
                  }

                  if (this.mCanBusInfoInt.length >= 13) {
                     var14.add(new OriginalCarDeviceUpdateEntity(3, this.mContext.getString(2131762794) + ": " + this.mCanBusInfoInt[11] + ":" + this.mCanBusInfoInt[12]));
                  }

                  if (this.mCanBusInfoInt.length >= 16) {
                     var14.add(new OriginalCarDeviceUpdateEntity(4, this.mContext.getString(2131758308) + ": " + this.mCanBusInfoInt[14] + ":" + this.mCanBusInfoInt[15]));
                  }

                  var14.add(new OriginalCarDeviceUpdateEntity(5, ""));
                  var14.add(new OriginalCarDeviceUpdateEntity(6, ""));
                  GeneralOriginalCarDeviceData.mList = var14;
               }
            } else {
               mOriginalInt = 2;
               this.setCdPage();
               var14 = new ArrayList();

               for(var1 = 1; var1 <= 6; ++var1) {
                  var14.add(new SongListEntity("DISC" + var1));
               }

               GeneralOriginalCarDeviceData.songList = var14;
               var14 = new ArrayList();
               var5 = this.mCanBusInfoInt;
               if (var5.length >= 4) {
                  var1 = var5[3];
                  if (var1 == 0) {
                     GeneralOriginalCarDeviceData.runningState = "CD";
                     var14.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131758304) + ": " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)));
                     var14.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(2131758305) + ": " + this.getCDState1(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))));
                     var14.add(new OriginalCarDeviceUpdateEntity(2, this.mContext.getString(2131758306) + ": " + this.getCDState2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
                     var5 = this.mCanBusInfoInt;
                     if (var5[8] != 255 && var5[9] != 255) {
                        var6 = (new StringBuilder()).append(this.mContext.getString(2131758307)).append(": ");
                        var5 = this.mCanBusInfoInt;
                        var14.add(new OriginalCarDeviceUpdateEntity(3, var6.append(var5[8] + var5[9] * 256).toString()));
                     }

                     var5 = this.mCanBusInfoInt;
                     if (var5[13] != 255 && var5[14] != 255 && var5[15] != 255) {
                        var14.add(new OriginalCarDeviceUpdateEntity(4, this.mContext.getString(2131758308) + ": " + this.mCanBusInfoInt[13] + ":" + this.mCanBusInfoInt[14] + ":" + this.mCanBusInfoInt[15]));
                     }

                     GeneralOriginalCarDeviceData.mList = var14;
                  } else {
                     UnsupportedEncodingException var10000;
                     boolean var10001;
                     byte[] var19;
                     String var20;
                     byte[] var23;
                     UnsupportedEncodingException var24;
                     if (var1 != 1) {
                        if (var1 == 2) {
                           label226: {
                              label218: {
                                 try {
                                    var23 = new byte[this.mCanBusInfoByte.length - 4];
                                 } catch (UnsupportedEncodingException var10) {
                                    var10000 = var10;
                                    var10001 = false;
                                    break label218;
                                 }

                                 var1 = var3;

                                 while(true) {
                                    try {
                                       var19 = this.mCanBusInfoByte;
                                       if (var1 >= var19.length - 4) {
                                          break;
                                       }
                                    } catch (UnsupportedEncodingException var9) {
                                       var10000 = var9;
                                       var10001 = false;
                                       break label218;
                                    }

                                    var23[var1] = var19[var1 + 4];
                                    ++var1;
                                 }

                                 try {
                                    var20 = new String(var23, "gbk");
                                    StringBuilder var22 = new StringBuilder();
                                    OriginalCarDeviceUpdateEntity var25 = new OriginalCarDeviceUpdateEntity(6, var22.append(this.mContext.getString(2131758289)).append(": ").append(var20).toString());
                                    var14.add(var25);
                                    GeneralOriginalCarDeviceData.mList = var14;
                                    break label226;
                                 } catch (UnsupportedEncodingException var8) {
                                    var10000 = var8;
                                    var10001 = false;
                                 }
                              }

                              var24 = var10000;
                              var24.printStackTrace();
                           }
                        }
                     } else {
                        label225: {
                           label219: {
                              try {
                                 var23 = new byte[this.mCanBusInfoByte.length - 4];
                              } catch (UnsupportedEncodingException var13) {
                                 var10000 = var13;
                                 var10001 = false;
                                 break label219;
                              }

                              var1 = var2;

                              while(true) {
                                 try {
                                    var19 = this.mCanBusInfoByte;
                                    if (var1 >= var19.length - 4) {
                                       break;
                                    }
                                 } catch (UnsupportedEncodingException var12) {
                                    var10000 = var12;
                                    var10001 = false;
                                    break label219;
                                 }

                                 var23[var1] = var19[var1 + 4];
                                 ++var1;
                              }

                              try {
                                 var20 = new String(var23, "gbk");
                                 var6 = new StringBuilder();
                                 OriginalCarDeviceUpdateEntity var7 = new OriginalCarDeviceUpdateEntity(5, var6.append(this.mContext.getString(2131758288)).append(": ").append(var20).toString());
                                 var14.add(var7);
                                 GeneralOriginalCarDeviceData.mList = var14;
                                 break label225;
                              } catch (UnsupportedEncodingException var11) {
                                 var10000 = var11;
                                 var10001 = false;
                              }
                           }

                           var24 = var10000;
                           var24.printStackTrace();
                        }
                     }
                  }
               }
            }
         } else {
            mOriginalInt = 1;
            this.setRadioPage();
            var4 = this.mCanBusInfoInt;
            if (var4.length >= 4) {
               var1 = var4[3];
               if (var1 == 0) {
                  GeneralOriginalCarDeviceData.runningState = this.getRadioStatus();
                  ArrayList var17 = new ArrayList();
                  var6 = (new StringBuilder()).append(this.mContext.getString(2131758287)).append(": ");
                  Context var15;
                  if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                     var15 = this.mContext;
                     var1 = 2131758311;
                  } else {
                     var15 = this.mContext;
                     var1 = 2131758322;
                  }

                  var17.add(new OriginalCarDeviceUpdateEntity(0, var6.append(var15.getString(var1)).toString()));
                  var6 = (new StringBuilder()).append(this.mContext.getString(2131758297)).append(": ");
                  if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                     var15 = this.mContext;
                     var1 = 2131758333;
                  } else {
                     var15 = this.mContext;
                     var1 = 2131758343;
                  }

                  var17.add(new OriginalCarDeviceUpdateEntity(1, var6.append(var15.getString(var1)).toString()));
                  var17.add(new OriginalCarDeviceUpdateEntity(2, this.mContext.getString(2131758302) + ": " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)));
                  var4 = this.mCanBusInfoInt;
                  if (var4[7] != 255 && var4[6] != 255) {
                     if (var4[4] == 16) {
                        var6 = (new StringBuilder()).append(this.mContext.getString(2131758303)).append(": ");
                        var4 = this.mCanBusInfoInt;
                        var17.add(new OriginalCarDeviceUpdateEntity(3, var6.append(var4[7] * 256 + var4[6]).append("KHz").toString()));
                     } else {
                        StringBuilder var21 = (new StringBuilder()).append(this.mContext.getString(2131758303)).append(": ");
                        var18 = this.mCanBusInfoInt;
                        var17.add(new OriginalCarDeviceUpdateEntity(3, var21.append((double)((var18[7] * 256 + var18[6]) / 100)).append("MHz").toString()));
                     }
                  }

                  var17.add(new OriginalCarDeviceUpdateEntity(4, ""));
                  var17.add(new OriginalCarDeviceUpdateEntity(5, ""));
                  var17.add(new OriginalCarDeviceUpdateEntity(6, ""));
                  GeneralOriginalCarDeviceData.mList = var17;
               } else if (var1 == 1) {
                  GeneralOriginalCarDeviceData.runningState = this.getRadioStatus();
                  var14 = new ArrayList();
                  var1 = 3;

                  while(var1 < 14) {
                     var5 = this.mCanBusInfoInt;
                     var2 = var1 + 2;
                     var3 = var2 + 1;
                     var1 = var2;
                     if (var5[var3] != 255) {
                        var1 = var2;
                        if (var5[var2] != 255) {
                           if (var5[4] == 16) {
                              var6 = new StringBuilder();
                              var5 = this.mCanBusInfoInt;
                              var14.add(new SongListEntity(var6.append(var5[var3] * 256 + var5[var2]).append("KHz").toString()));
                              var1 = var2;
                           } else {
                              var16 = new StringBuilder();
                              var18 = this.mCanBusInfoInt;
                              var14.add(new SongListEntity(var16.append((double)((var18[var3] * 256 + var18[var2]) / 100)).append("MHz").toString()));
                              var1 = var2;
                           }
                        }
                     }
                  }

                  GeneralOriginalCarDeviceData.songList = var14;
               }
            }
         }

         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setOriginal0x66() {
      if (this.is0x66DataChange()) {
         GeneralOriginalCarDeviceData.cdStatus = this.getPlayMode0x66();
         GeneralOriginalCarDeviceData.runningState = this.getRunningState0x66();
         DevicePower = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         DeviceRearLock = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         this.setOriginalPage0x66();
         ArrayList var3 = new ArrayList();
         var3.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(2131758290) + ": " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
         StringBuilder var4 = (new StringBuilder()).append(this.mContext.getString(2131758291)).append(": ");
         int var1;
         Context var2;
         if (DevicePower) {
            var2 = this.mContext;
            var1 = 2131768216;
         } else {
            var2 = this.mContext;
            var1 = 2131768215;
         }

         var3.add(new OriginalCarDeviceUpdateEntity(1, var4.append(var2.getString(var1)).toString()));
         var4 = (new StringBuilder()).append(this.mContext.getString(2131758292)).append(": ");
         if (DeviceRearLock) {
            var2 = this.mContext;
            var1 = 2131758318;
         } else {
            var2 = this.mContext;
            var1 = 2131758317;
         }

         var3.add(new OriginalCarDeviceUpdateEntity(2, var4.append(var2.getString(var1)).toString()));
         var3.add(new OriginalCarDeviceUpdateEntity(3, ""));
         var3.add(new OriginalCarDeviceUpdateEntity(4, ""));
         var3.add(new OriginalCarDeviceUpdateEntity(5, ""));
         var3.add(new OriginalCarDeviceUpdateEntity(6, ""));
         GeneralOriginalCarDeviceData.mList = var3;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setOriginalPage0x66() {
      this.changeOriginalDevicePage(new String[]{"power", "lock"}, new String[]{"mode", "play_pause", "stop"}, false);
   }

   private void setPanelInfo0x33() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        this.realKeyClick4(this.mContext, 49);
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 48);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 47);
               }
            } else {
               this.realKeyClick4(this.mContext, 46);
            }
         } else {
            this.realKeyClick4(this.mContext, 45);
         }
      } else {
         this.realKeyClick4(this.mContext, 0);
      }

   }

   private void setPanelInfoBtn0x2a() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(134);
            break;
         case 4:
            this.realKeyClick(45);
            break;
         case 5:
            this.realKeyClick(46);
            break;
         case 6:
            this.realKeyClick(47);
            break;
         case 7:
            this.realKeyClick(48);
            break;
         case 8:
            this.realKeyClick(49);
            break;
         case 9:
            this.realKeyClick(50);
            break;
         case 10:
            this.realKeyClick(129);
            break;
         case 11:
            this.realKeyClick(52);
            break;
         case 12:
            this.realKeyClick(130);
            break;
         case 13:
            this.realKeyClick(196);
            break;
         case 14:
            this.realKeyClick(59);
            break;
         case 15:
            this.realKeyClick(128);
            break;
         case 16:
            this.realKeyClick(4);
            break;
         case 17:
            this.startDrivingDataActivity(this.mContext, 1);
            break;
         case 18:
            this.setBrightness(1);
            break;
         case 19:
            this.setBrightness(-1);
            break;
         case 20:
            this.realKeyClick(58);
            break;
         case 21:
            this.realKeyClick(187);
            break;
         case 22:
            this.realKeyClick(2);
            break;
         case 23:
            this.realKeyClick(14);
            break;
         case 24:
            this.realKeyClick(15);
            break;
         case 25:
            this.realKeyClick(3);
            break;
         case 26:
            this.realKeyClick(52);
            break;
         case 27:
            this.realKeyClick(151);
         case 28:
         case 29:
         case 30:
         case 31:
         default:
            break;
         case 32:
            this.startMainActivity(this.mContext);
            Intent var1 = new Intent(this.getActivity(), AmplifierActivity.class);
            this.getActivity().startActivity(var1);
            break;
         case 33:
            this.realKeyClick(17);
            break;
         case 34:
            this.realKeyClick(53);
            break;
         case 35:
            this.startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
      }

   }

   private void setPanoramic0x32() {
      if (this.isPanoramicStatusChange()) {
         this.forceReverse(this.mContext, this.mPanoramicStatusNow);
      }

      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getAmplifierSettingsIndex(), 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      this.switchFCamera(this.mContext, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
   }

   private void setRadioPage() {
      this.changeOriginalDevicePage((String[])null, new String[]{"up", "left", "play_pause", "right", "down"}, true);
   }

   private void setRearRadarData0x1E() {
      if (this.isRearRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(5, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
         var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
         var2.add((new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3) - 1));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setSettingStatus0x50() {
      SharePreUtil.setIntValue(this.mContext, SHARE_16_LANGUAGE, this.mCanBusInfoInt[3]);
      int var1 = this.mCanBusInfoInt[3];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)));
      var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
      var2.add(new SettingUpdateEntity(3, 2, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingStatus0x52() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var2 = " ";
               } else {
                  var2 = "_16_radiant_orange";
               }
            } else {
               var2 = "_16_deep_orange";
            }
         } else {
            var2 = "_16_clear_turquoise";
         }
      } else {
         var2 = "_16_clear_blue";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
      var3.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3)));
      var3.add(new SettingUpdateEntity(3, 4, var2));
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 1) {
         var3.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_0"), "KM/L")).setValueStr(true));
      } else {
         var3.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_0"), "L/100KM")).setValueStr(true));
      }

      var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3)));
      var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
      var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) - 3));
      var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) - 3));
      var3.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 1));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTPMSInfo0x25() {
      SharePreUtil.setBoolValue(this.mContext, "share_16_is_suppot_tire", DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         SharePreUtil.setBoolValue(this.mContext, "share_16_is_have_spare_tire", DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         String var3 = this.getTireUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
         float var1 = this.getTireRule(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
         GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         ArrayList var4 = new ArrayList();
         var4.add(new TireUpdateEntity(0, var2, new String[]{(float)this.mCanBusInfoInt[3] / var1 + var3}));
         var4.add(new TireUpdateEntity(1, var2, new String[]{(float)this.mCanBusInfoInt[4] / var1 + var3}));
         var4.add(new TireUpdateEntity(2, var2, new String[]{(float)this.mCanBusInfoInt[5] / var1 + var3}));
         var4.add(new TireUpdateEntity(3, var2, new String[]{(float)this.mCanBusInfoInt[6] / var1 + var3}));
         if (GeneralTireData.isHaveSpareTire) {
            var4.add(new TireUpdateEntity(4, var2, new String[]{(float)this.mCanBusInfoInt[7] / var1 + var3}));
         }

         GeneralTireData.dataList = var4;
         this.updateTirePressureActivity((Bundle)null);
         if (this.mTireStatus != var2) {
            this.mTireStatus = var2;
            if (var2 == 1 && !SystemUtil.isForeground(this.mContext, Constant.TireInfoActivity.getClassName())) {
               this.getTireAlramView().showWindow();
            }
         }

      }
   }

   private void setTrackData0x29() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 380, 12);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setUsbPage() {
      this.changeOriginalDevicePage((String[])null, new String[]{"prev_disc", "up", "left", "play_pause", "right", "down", "next_disc", "cycle", "random"}, true);
   }

   private void setVehicleSettings0x26() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3)));
      var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)));
      var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
      var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var1.add((new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
      var1.add(new SettingUpdateEntity(1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var1.add(new SettingUpdateEntity(1, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(1, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3)));
      var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var1.add(new SettingUpdateEntity(1, 12, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setVersionInfo0x30() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private String showOilStr(int var1, String var2) {
      return var1 == 4095 ? this.mContext.getString(2131769909) : String.format("%.1f", (double)var1 * 0.1) + var2;
   }

   private void startTireInfo() {
      Intent var1 = new Intent();
      var1.setComponent(Constant.TireInfoActivity);
      var1.setFlags(268435456);
      this.mContext.startActivity(var1);
   }

   private void updateOriginalDeviceWithInit() {
      Bundle var1 = new Bundle();
      var1.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var1);
   }

   public void aMapCallBack(Bundle var1) {
      super.aMapCallBack(var1);
      int var7 = var1.getInt("EXTRA_STATE");
      int var10 = var1.getInt("ICON");
      String var11 = var1.getString("NEXT_ROAD_NAME");
      int var8 = var1.getInt("SEG_REMAIN_DIS");
      int var9 = var1.getInt("CAR_DIRECTION");
      if (var7 != 8 && var7 != 10) {
         if (var7 == 9 || var7 == 12) {
            this.isStartAMap = false;
         }
      } else {
         this.isStartAMap = true;
      }

      var10 = this.getNaviDirection(var10);
      var9 = AMapBroadcastReceiver.getCarDirection(var9, "南");
      byte var3 = (byte)(var8 >> 8);
      byte var6 = (byte)var8;

      Exception var10000;
      label63: {
         byte[] var15;
         boolean var10001;
         label65: {
            try {
               if (!TextUtils.isEmpty(var11) && this.isStartAMap) {
                  break label65;
               }
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               break label63;
            }

            if (var7 != 9 && var7 != 12) {
               return;
            }

            try {
               var15 = new byte[17];
               CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -100, 0, 0, 0, 0, 0}, var15));
               return;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               break label63;
            }
         }

         byte var2 = (byte)var10;
         byte var4 = (byte)var9;
         byte var5 = (byte)0;

         try {
            var15 = DataHandleUtils.makeBytesFixedLength(var11.getBytes(CHARSETNAME.name()), 17);
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -100, var2, var4, var3, var6, var5}, var15));
            return;
         } catch (Exception var12) {
            var10000 = var12;
            var10001 = false;
         }
      }

      Exception var16 = var10000;
      var16.printStackTrace();
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      this.mdifferentId = this.getCurrentCanDifferentId();
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      this.registerAMap(var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      (new Thread(new Runnable(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 5});

            try {
               Thread.sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            byte[] var1 = new byte[31];
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -103, -121, 1}, var1));
         }
      })).start();
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      if (TextUtils.isEmpty(this.btMusicTitle) || !this.btMusicTitle.equals(var1) || TextUtils.isEmpty(this.btMusicArtist) || !this.btMusicArtist.equals(var2) || TextUtils.isEmpty(this.btMusicAlbum) || !this.btMusicAlbum.equals(var3)) {
         (new Thread(new MsgMgr$$ExternalSyntheticLambda4(this, var1, var2, var3))).start();
      }
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      Log.d("scyscyscy", "---------->btMusiceDestdroy");
      (new Thread(new MsgMgr$$ExternalSyntheticLambda5(this))).start();
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.sendPhoneChange(var1, 4);
      this.ContactPerson(var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendPhoneChange(var1, 1);
      this.ContactPerson(var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.sendPhoneChange(var1, 2);
      this.ContactPerson(var1);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.sendPhoneChange(var1, 3);
      this.ContactPerson(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      Exception var10000;
      boolean var10001;
      if (var3 != 88) {
         if (var3 != 102) {
            if (var3 == 97) {
               try {
                  this.setCDState0x61();
                  return;
               } catch (Exception var29) {
                  var10000 = var29;
                  var10001 = false;
               }
            } else if (var3 != 98) {
               switch (var3) {
                  case 29:
                     try {
                        this.setFrontRadarData0x1D();
                        return;
                     } catch (Exception var19) {
                        var10000 = var19;
                        var10001 = false;
                        break;
                     }
                  case 30:
                     try {
                        this.setRearRadarData0x1E();
                        return;
                     } catch (Exception var18) {
                        var10000 = var18;
                        var10001 = false;
                        break;
                     }
                  case 31:
                     try {
                        this.setHybrid0x1F();
                        return;
                     } catch (Exception var17) {
                        var10000 = var17;
                        var10001 = false;
                        break;
                     }
                  case 32:
                     try {
                        this.setKeyEvent0x20();
                        return;
                     } catch (Exception var16) {
                        var10000 = var16;
                        var10001 = false;
                        break;
                     }
                  case 33:
                     try {
                        this.setMileageInfo0x21();
                        return;
                     } catch (Exception var15) {
                        var10000 = var15;
                        var10001 = false;
                        break;
                     }
                  case 34:
                     try {
                        this.setInstantOilInfo0x22();
                        return;
                     } catch (Exception var14) {
                        var10000 = var14;
                        var10001 = false;
                        break;
                     }
                  case 35:
                     try {
                        this.setHistoryOilInfo0x23();
                        return;
                     } catch (Exception var13) {
                        var10000 = var13;
                        var10001 = false;
                        break;
                     }
                  case 36:
                     try {
                        this.setCarInfo0x24();
                        return;
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break;
                     }
                  case 37:
                     try {
                        this.setTPMSInfo0x25();
                        return;
                     } catch (Exception var11) {
                        var10000 = var11;
                        var10001 = false;
                        break;
                     }
                  case 38:
                     try {
                        this.setVehicleSettings0x26();
                        return;
                     } catch (Exception var10) {
                        var10000 = var10;
                        var10001 = false;
                        break;
                     }
                  case 39:
                     try {
                        this.set15minOilInfo0x27();
                        return;
                     } catch (Exception var9) {
                        var10000 = var9;
                        var10001 = false;
                        break;
                     }
                  case 40:
                     try {
                        if (this.isAirMsgRepeat(var2)) {
                           return;
                        }
                     } catch (Exception var32) {
                        var10000 = var32;
                        var10001 = false;
                        break;
                     }

                     try {
                        this.setAirData0x28();
                        return;
                     } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                        break;
                     }
                  case 41:
                     try {
                        this.setTrackData0x29();
                        return;
                     } catch (Exception var7) {
                        var10000 = var7;
                        var10001 = false;
                        break;
                     }
                  case 42:
                     try {
                        this.setPanelInfoBtn0x2a();
                        return;
                     } catch (Exception var6) {
                        var10000 = var6;
                        var10001 = false;
                        break;
                     }
                  case 43:
                     try {
                        this.setElectric0x2B();
                        return;
                     } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                        break;
                     }
                  default:
                     switch (var3) {
                        case 47:
                           try {
                              this.setMediaCommand0x2F();
                              return;
                           } catch (Exception var24) {
                              var10000 = var24;
                              var10001 = false;
                              break;
                           }
                        case 48:
                           try {
                              this.setVersionInfo0x30();
                              return;
                           } catch (Exception var23) {
                              var10000 = var23;
                              var10001 = false;
                              break;
                           }
                        case 49:
                           try {
                              this.setAmplifierData0x31();
                              return;
                           } catch (Exception var22) {
                              var10000 = var22;
                              var10001 = false;
                              break;
                           }
                        case 50:
                           try {
                              this.setPanoramic0x32();
                              return;
                           } catch (Exception var21) {
                              var10000 = var21;
                              var10001 = false;
                              break;
                           }
                        case 51:
                           try {
                              this.setPanelInfo0x33();
                              return;
                           } catch (Exception var20) {
                              var10000 = var20;
                              var10001 = false;
                              break;
                           }
                        default:
                           switch (var3) {
                              case 80:
                                 try {
                                    this.setSettingStatus0x50();
                                    return;
                                 } catch (Exception var27) {
                                    var10000 = var27;
                                    var10001 = false;
                                    break;
                                 }
                              case 81:
                                 try {
                                    this.setKeyAirEvent0x51();
                                    return;
                                 } catch (Exception var26) {
                                    var10000 = var26;
                                    var10001 = false;
                                    break;
                                 }
                              case 82:
                                 try {
                                    this.setSettingStatus0x52();
                                    return;
                                 } catch (Exception var25) {
                                    var10000 = var25;
                                    var10001 = false;
                                    break;
                                 }
                              default:
                                 return;
                           }
                     }
               }
            } else {
               try {
                  this.setOriginal0x62();
                  return;
               } catch (Exception var28) {
                  var10000 = var28;
                  var10001 = false;
               }
            }
         } else {
            try {
               this.setOriginal0x66();
               return;
            } catch (Exception var30) {
               var10000 = var30;
               var10001 = false;
            }
         }
      } else {
         label220: {
            try {
               if (this.isAirMsgRepeat2(var2)) {
                  return;
               }
            } catch (Exception var33) {
               var10000 = var33;
               var10001 = false;
               break label220;
            }

            try {
               this.setAirData0x58();
               return;
            } catch (Exception var31) {
               var10000 = var31;
               var10001 = false;
            }
         }
      }

      Exception var34 = var10000;
      Log.e("CanBusError", var34.toString());
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var1 = this.mdifferentId;
      if (var1 == 1 || var1 == 2 || var1 == 9) {
         Log.d("Lai5", this.mdifferentId + "" + this.mCanId);
         if (var10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -99, (byte)var5, (byte)var6, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -99, (byte)var5, (byte)var6, 1});
         }
      }

   }

   public void destroyCommand() {
      super.destroyCommand();
      this.unRegisterAMap(this.mContext);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 4});
   }

   public int getEachCanId() {
      return this.getCurrentEachCanId();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.initAmplifierData(var1);
      this.portraitCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, 1});
   }

   protected boolean isAirMsgRepeat2(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected boolean isAirMsgRepeat3(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy3)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy3 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   // $FF: synthetic method
   void lambda$btMusicId3InfoChange$4$com_hzbhd_canbus_car__16_MsgMgr(String var1, String var2, String var3) {
      try {
         Thread.sleep(100L);
      } catch (InterruptedException var12) {
         var12.printStackTrace();
      }

      byte[] var4;
      try {
         var4 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 1}, var4), 32), new byte[]{0, 0}));
      } catch (UnsupportedEncodingException var11) {
         var11.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var10) {
         var10.printStackTrace();
      }

      try {
         var4 = var1.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 2}, var4), 32), new byte[]{0, 0}));
         this.btMusicTitle = var1;
      } catch (UnsupportedEncodingException var9) {
         var9.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var8) {
         var8.printStackTrace();
      }

      byte[] var13;
      try {
         var13 = var2.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 3}, var13), 32), new byte[]{0, 0}));
         this.btMusicArtist = var2;
      } catch (UnsupportedEncodingException var7) {
         var7.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var6) {
         var6.printStackTrace();
      }

      try {
         var13 = var3.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 4}, var13), 32), new byte[]{0, 0}));
         this.btMusicAlbum = var3;
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$btMusiceDestdroy$5$com_hzbhd_canbus_car__16_MsgMgr() {
      try {
         Thread.sleep(100L);
      } catch (InterruptedException var9) {
         var9.printStackTrace();
      }

      byte[] var1;
      try {
         var1 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 1}, var1), 32), new byte[]{0, 0}));
      } catch (UnsupportedEncodingException var8) {
         var8.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var7) {
         var7.printStackTrace();
      }

      try {
         var1 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 2}, var1), 32), new byte[]{0, 0}));
         this.btMusicTitle = " ";
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var5) {
         var5.printStackTrace();
      }

      try {
         var1 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 3}, var1), 32), new byte[]{0, 0}));
         this.btMusicArtist = " ";
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

      try {
         var1 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 4}, var1), 32), new byte[]{0, 0}));
         this.btMusicAlbum = " ";
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$musicDestroy$1$com_hzbhd_canbus_car__16_MsgMgr() {
      try {
         Thread.sleep(100L);
      } catch (InterruptedException var11) {
         var11.printStackTrace();
      }

      short var2 = 132;
      if (this.mStoagePath == 9) {
         var2 = 134;
      }

      byte var1 = (byte)var2;

      byte[] var3;
      try {
         var3 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 1}, var3), 32), new byte[]{0, 0}));
         this.musicIndex = 0;
         this.musicCount = 0;
      } catch (UnsupportedEncodingException var10) {
         var10.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var9) {
         var9.printStackTrace();
      }

      var1 = (byte)var2;

      try {
         var3 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 2}, var3), 32), new byte[]{0, 0}));
         this.musicTitle = " ";
      } catch (UnsupportedEncodingException var8) {
         var8.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var7) {
         var7.printStackTrace();
      }

      var1 = (byte)var2;

      try {
         var3 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 3}, var3), 32), new byte[]{0, 0}));
         this.musicArtist = " ";
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var5) {
         var5.printStackTrace();
      }

      var1 = (byte)var2;

      try {
         var3 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 4}, var3), 32), new byte[]{0, 0}));
         this.musicAlbum = " ";
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$musicInfoChange$0$com_hzbhd_canbus_car__16_MsgMgr(byte var1, int var2, int var3, String var4, String var5, String var6) {
      try {
         Thread.sleep(100L);
      } catch (InterruptedException var18) {
         var18.printStackTrace();
      }

      if (var1 == 9) {
         var1 = 134;
      } else {
         var1 = 132;
      }

      byte var7 = (byte)var1;

      byte[] var8;
      try {
         var1 = var2 / 10;
         Charset var9 = CHARSETNAME;
         var8 = DataHandleUtils.byteMerger(String.valueOf(var1).getBytes(var9.name()), String.valueOf(var2 % 10).getBytes(var9.name()));
         byte[] var10 = DataHandleUtils.byteMerger(String.valueOf(var3 / 10).getBytes(var9.name()), String.valueOf(var3 % 10).getBytes(var9.name()));
         byte[] var20 = new byte[27];
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -103, var7, 1}, var8), DataHandleUtils.byteMerger(var10, var20)));
         this.musicIndex = var2;
         this.musicCount = var3;
      } catch (UnsupportedEncodingException var17) {
         var17.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var16) {
         var16.printStackTrace();
      }

      try {
         var8 = var4.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var7, 2}, var8), 32), new byte[]{0, 0}));
         this.musicTitle = var4;
      } catch (UnsupportedEncodingException var15) {
         var15.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var14) {
         var14.printStackTrace();
      }

      byte[] var19;
      try {
         var19 = var5.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var7, 3}, var19), 32), new byte[]{0, 0}));
         this.musicArtist = var5;
      } catch (UnsupportedEncodingException var13) {
         var13.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var12) {
         var12.printStackTrace();
      }

      try {
         var19 = var6.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var7, 4}, var19), 32), new byte[]{0, 0}));
         this.musicAlbum = var6;
      } catch (UnsupportedEncodingException var11) {
         var11.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$videoDestroy$3$com_hzbhd_canbus_car__16_MsgMgr() {
      short var2;
      if (this.mStoagePath == 9) {
         var2 = 134;
      } else {
         var2 = 132;
      }

      byte var1 = (byte)var2;

      byte[] var3;
      try {
         var3 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 1}, var3), 32), new byte[]{0, 0}));
         this.musicIndex = 0;
         this.musicCount = 0;
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var5) {
         var5.printStackTrace();
      }

      var1 = (byte)var2;

      try {
         var3 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 2}, var3), 32), new byte[]{0, 0}));
         this.musicTitle = " ";
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$videoInfoChange$2$com_hzbhd_canbus_car__16_MsgMgr(byte var1, int var2, int var3, String var4) {
      if (var1 == 9) {
         var1 = 134;
      } else {
         var1 = 132;
      }

      byte var5 = (byte)var1;

      byte[] var7;
      try {
         int var6 = var2 / 10;
         Charset var8 = CHARSETNAME;
         var7 = DataHandleUtils.byteMerger(String.valueOf(var6).getBytes(var8.name()), String.valueOf(var2 % 10).getBytes(var8.name()));
         byte[] var9 = DataHandleUtils.byteMerger(String.valueOf(var3 / 10).getBytes(var8.name()), String.valueOf(var3 % 10).getBytes(var8.name()));
         byte[] var13 = new byte[27];
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -103, var5, 1}, var7), DataHandleUtils.byteMerger(var9, var13)));
         this.musicIndex = var2;
         this.musicCount = var3;
      } catch (UnsupportedEncodingException var12) {
         var12.printStackTrace();
      }

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var11) {
         var11.printStackTrace();
      }

      var5 = (byte)var1;

      try {
         var7 = var4.getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var5, 2}, var7), 32), new byte[]{0, 0}));
         this.musicTitle = var4;
      } catch (UnsupportedEncodingException var10) {
         var10.printStackTrace();
      }

   }

   public void musicDestroy() {
      super.musicDestroy();
      (new Thread(new MsgMgr$$ExternalSyntheticLambda1(this))).start();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.musicIndex != var17 || this.musicCount != var4 || TextUtils.isEmpty(this.musicTitle) || !this.musicTitle.equals(var21) || TextUtils.isEmpty(this.musicArtist) || !this.musicArtist.equals(var23) || TextUtils.isEmpty(this.musicAlbum) || !this.musicAlbum.equals(var22)) {
         this.mStoagePath = var1;
         (new Thread(new MsgMgr$$ExternalSyntheticLambda2(this, var1, var17, var4, var21, var23, var22))).start();
         byte[] var25 = var21.getBytes(StandardCharsets.UTF_8);
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, 103}, var25));
      }
   }

   public void radioDestroy() {
      super.radioDestroy();

      try {
         byte var1 = this.mCurrBandByte;
         byte[] var2 = " ".getBytes(CHARSETNAME.name());
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 1, 0, 0}, var2), 32), new byte[]{0, 0}));
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      (new Thread(new Runnable(this, var2, var3) {
         final MsgMgr this$0;
         final String val$currBand;
         final String val$currentFreq;

         {
            this.this$0 = var1;
            this.val$currBand = var2;
            this.val$currentFreq = var3;
         }

         public void run() {
            if (this.val$currBand.contains("FM2")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 7});
            } else if (this.val$currBand.contains("FM")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 6});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 3});
            }

            try {
               Thread.sleep(100L);
            } catch (InterruptedException var10) {
               var10.printStackTrace();
            }

            this.this$0.mCurrBandByte = -127;
            if (this.val$currBand.contains("AM")) {
               this.this$0.mCurrBandByte = -126;
            }

            byte var1 = this.this$0.mCurrBandByte;

            byte[] var2;
            try {
               var2 = this.val$currentFreq.getBytes(MsgMgr.CHARSETNAME.name());
               CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 1, 0, 0}, var2), 32), new byte[]{0, 0}));
            } catch (UnsupportedEncodingException var9) {
               var9.printStackTrace();
            }

            try {
               Thread.sleep(100L);
            } catch (InterruptedException var8) {
               var8.printStackTrace();
            }

            try {
               var1 = this.this$0.mCurrBandByte;
               var2 = " ".getBytes(MsgMgr.CHARSETNAME.name());
               CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 2}, var2), 32), new byte[]{0, 0}));
            } catch (UnsupportedEncodingException var7) {
               var7.printStackTrace();
            }

            try {
               Thread.sleep(100L);
            } catch (InterruptedException var6) {
               var6.printStackTrace();
            }

            try {
               var1 = this.this$0.mCurrBandByte;
               var2 = " ".getBytes(MsgMgr.CHARSETNAME.name());
               CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 3}, var2), 32), new byte[]{0, 0}));
            } catch (UnsupportedEncodingException var5) {
               var5.printStackTrace();
            }

            try {
               Thread.sleep(100L);
            } catch (InterruptedException var4) {
               var4.printStackTrace();
            }

            try {
               var1 = this.this$0.mCurrBandByte;
               var2 = " ".getBytes(MsgMgr.CHARSETNAME.name());
               CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, var1, 4}, var2), 32), new byte[]{0, 0}));
            } catch (UnsupportedEncodingException var3) {
               var3.printStackTrace();
            }

         }
      })).start();
      byte[] var6;
      if (!var2.equals("AM1") && !var2.equals("AM2") && !var2.equals("AM")) {
         var6 = ("FM " + var3 + "MHz").getBytes(StandardCharsets.UTF_8);
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, 103}, var6));
      } else {
         var6 = ("AM " + var3 + "KHz").getBytes(StandardCharsets.UTF_8);
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, 103}, var6));
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   public void videoDestroy() {
      super.videoDestroy();
      Log.d("scyscyscy", "---------->videoDestroy");
      this.videoPlayIndex = -1;
      this.videoTotalCount = -1;
      (new Thread(new MsgMgr$$ExternalSyntheticLambda3(this))).start();
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (this.videoPlayIndex != var3 || this.videoTotalCount != var4) {
         Log.d("scyscyscy", var3 + "<-----videoInfoChange----->" + var4);
         var8 = this.mContext.getString(2131770283);
         this.mStoagePath = var1;
         this.videoPlayIndex = var3;
         this.videoTotalCount = var4;
         (new Thread(new MsgMgr$$ExternalSyntheticLambda0(this, var1, var3, var4, var8))).start();
         byte[] var18 = var8.getBytes(StandardCharsets.UTF_8);
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, 103}, var18));
      }
   }

   public void voiceControlInfo(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -2039274493:
            if (var1.equals("rear.windows.close")) {
               var2 = 0;
            }
            break;
         case -1871342186:
            if (var1.equals("rear.right.window.close")) {
               var2 = 1;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 2;
            }
            break;
         case -1412208249:
            if (var1.equals("air.ac.on")) {
               var2 = 3;
            }
            break;
         case -1391366141:
            if (var1.equals("skylight.open")) {
               var2 = 4;
            }
            break;
         case -1386876687:
            if (var1.equals("front.right.window.close")) {
               var2 = 5;
            }
            break;
         case -954737720:
            if (var1.equals("front.windows.close")) {
               var2 = 6;
            }
            break;
         case -891288852:
            if (var1.equals("rear.right.window.open")) {
               var2 = 7;
            }
            break;
         case -861720966:
            if (var1.equals("front.windows.open")) {
               var2 = 8;
            }
            break;
         case -828782905:
            if (var1.equals("air.ac.off")) {
               var2 = 9;
            }
            break;
         case -779932258:
            if (var1.equals("air_right_temperature_up")) {
               var2 = 10;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 11;
            }
            break;
         case -193868961:
            if (var1.equals("skylight.close")) {
               var2 = 12;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 13;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 14;
            }
            break;
         case 225548432:
            if (var1.equals("air_left_temperature_down")) {
               var2 = 15;
            }
            break;
         case 292772663:
            if (var1.equals("rear.left.window.close")) {
               var2 = 16;
            }
            break;
         case 345504582:
            if (var1.equals("front.left.window.open")) {
               var2 = 17;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 18;
            }
            break;
         case 925454385:
            if (var1.equals("front.right.window.open")) {
               var2 = 19;
            }
            break;
         case 1146265120:
            if (var1.equals("ac.windlevel.down")) {
               var2 = 20;
            }
            break;
         case 1166714377:
            if (var1.equals("air_left_temperature_up")) {
               var2 = 21;
            }
            break;
         case 1225772953:
            if (var1.equals("ac.windlevel.up")) {
               var2 = 22;
            }
            break;
         case 1358234944:
            if (var1.equals("all.windows.close")) {
               var2 = 23;
            }
            break;
         case 1458598623:
            if (var1.equals("rear.windows.open")) {
               var2 = 24;
            }
            break;
         case 1949467947:
            if (var1.equals("rear.left.window.open")) {
               var2 = 25;
            }
            break;
         case 1983837698:
            if (var1.equals("all.windows.open")) {
               var2 = 26;
            }
            break;
         case 2103873253:
            if (var1.equals("air_right_temperature_down")) {
               var2 = 27;
            }
            break;
         case 2109515900:
            if (var1.equals("front.left.window.close")) {
               var2 = 28;
            }
      }

      switch (var2) {
         case 0:
            this.sendVoiceMsg(4, false);
            this.sendVoiceMsg(5, false);
            break;
         case 1:
            this.sendVoiceMsg(5, false);
            break;
         case 2:
            this.sendAirCommand(19);
            break;
         case 3:
         case 9:
            this.sendAirCommand(23);
            break;
         case 4:
            this.sendVoiceMsg(6, true);
            break;
         case 5:
            this.sendVoiceMsg(3, false);
            break;
         case 6:
            this.sendVoiceMsg(2, false);
            this.sendVoiceMsg(3, false);
            break;
         case 7:
            this.sendVoiceMsg(5, true);
            break;
         case 8:
            this.sendVoiceMsg(2, true);
            this.sendVoiceMsg(3, true);
            break;
         case 10:
            this.sendAirCommand(5);
            break;
         case 11:
            this.sendAirCommand(20);
            break;
         case 12:
            this.sendVoiceMsg(6, false);
            break;
         case 13:
            this.sendAirCommand(21);
            break;
         case 14:
            this.sendAirCommand(16);
            break;
         case 15:
            this.sendAirCommand(2);
            break;
         case 16:
            this.sendVoiceMsg(4, false);
            break;
         case 17:
            this.sendVoiceMsg(2, true);
            break;
         case 18:
            this.sendAirCommand(25);
            break;
         case 19:
            this.sendVoiceMsg(3, true);
            break;
         case 20:
            this.sendAirCommand(9);
            break;
         case 21:
            this.sendAirCommand(3);
            break;
         case 22:
            this.sendAirCommand(10);
            break;
         case 23:
            this.sendVoiceMsg(1, false);
            break;
         case 24:
            this.sendVoiceMsg(4, true);
            this.sendVoiceMsg(5, true);
            break;
         case 25:
            this.sendVoiceMsg(4, true);
            break;
         case 26:
            this.sendVoiceMsg(1, true);
            break;
         case 27:
            this.sendAirCommand(4);
            break;
         case 28:
            this.sendVoiceMsg(2, false);
      }

   }

   private class TireAlramView implements View.OnClickListener {
      private boolean isShowing;
      private View mFloatView;
      private WindowManager.LayoutParams mLayoutParams;
      private TextView mTvBtnNo;
      private TextView mTvBtnYes;
      private WindowManager mWindowManager;
      final MsgMgr this$0;

      public TireAlramView(MsgMgr var1) {
         this.this$0 = var1;
         this.initWindow();
      }

      private void dismissView() {
         WindowManager var2 = this.mWindowManager;
         if (var2 != null) {
            View var1 = this.mFloatView;
            if (var1 != null) {
               var2.removeView(var1);
               this.isShowing = false;
            }
         }

      }

      private void initWindow() {
         this.mWindowManager = (WindowManager)this.this$0.mContext.getSystemService("window");
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2002;
         this.mLayoutParams.gravity = 17;
         this.mLayoutParams.width = -2;
         this.mLayoutParams.height = -2;
         RelativeLayout var2 = (RelativeLayout)LayoutInflater.from(this.this$0.mContext).inflate(2131558741, (ViewGroup)null);
         this.mFloatView = var2;
         this.mTvBtnNo = (TextView)var2.findViewById(2131363654);
         this.mTvBtnYes = (TextView)this.mFloatView.findViewById(2131363720);
         this.mTvBtnNo.setOnClickListener(this);
         this.mTvBtnYes.setOnClickListener(this);
      }

      private void showWindow() {
         if (!this.isShowing) {
            this.isShowing = true;
            this.this$0.runOnUi(new CallBackInterface(this) {
               final TireAlramView this$1;

               {
                  this.this$1 = var1;
               }

               public void callback() {
                  this.this$1.mWindowManager.addView(this.this$1.mFloatView, this.this$1.mLayoutParams);
               }
            });
         }

      }

      public void onClick(View var1) {
         int var2 = var1.getId();
         if (var2 != 2131363654) {
            if (var2 == 2131363720) {
               this.dismissView();
               this.this$0.startTireInfo();
            }
         } else {
            this.dismissView();
         }

      }
   }
}
