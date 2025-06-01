package com.hzbhd.canbus.car_cus._448;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.util.Heartbeat;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DvrController {
   private DecimalFormat TwoDigitNumber;
   private Context context;

   private DvrController() {
      this.TwoDigitNumber = new DecimalFormat("###00");
      DvrSender.send(new byte[]{35, 8});
      DvrSender.send(new byte[]{88, 0, 0, 0, 0});
      DvrSender.send(new byte[]{89, 0, 0, 0, 0});
      Heartbeat.getInstance().start(1000, new ActionCallback(this) {
         final DvrController this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(new byte[]{35, 8});
            DvrSender.send(new byte[]{88, 0, 0, 0, 0});
            DvrSender.send(new byte[]{89, 0, 0, 0, 0});
            Heartbeat.getInstance().reset(1000);
         }
      });
   }

   // $FF: synthetic method
   DvrController(Object var1) {
      this();
   }

   public static DvrController getInstance() {
      return DvrController.Holder.controller;
   }

   private void set0X71(int[] var1) {
      int var2 = var1[5];
      if (var2 == 0) {
         DvrData.numberOfFiles = 0;
      } else if (var2 == 1) {
         DvrData.numberOfFiles = 1;
      } else if (var2 == 2) {
         DvrData.numberOfFiles = 2;
      } else if (var2 == 3) {
         DvrData.numberOfFiles = 3;
      } else if (var2 == 4) {
         DvrData.numberOfFiles = 4;
      } else if (var2 == 5) {
         DvrData.numberOfFiles = 5;
      } else if (var2 == 6) {
         DvrData.numberOfFiles = 6;
      }

      DvrObserver.getInstance().dataChange("play.back.mode");
   }

   private void set0x20(int[] var1) {
      if (DataHandleUtils.getIntFromByteWithBit(var1[5], 3, 2) == 0) {
         DvrData.videoStateIcon = 2;
         DvrData.videoStateStr = "已停止";
         Log.d("videoStateStr", "停止录制");
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 3, 2) == 1) {
         DvrData.videoStateIcon = 1;
         DvrData.videoStateStr = "正在录制";
         DvrData.systemMode = 2;
         Log.d("videoStateStr", "正在录制");
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 3, 2) == 2) {
         DvrData.videoStateIcon = 4;
         DvrData.videoStateStr = "回放暂停";
         Log.d("videoStateStr", "回放暂停");
      } else if (DataHandleUtils.getIntFromByteWithBit(var1[5], 3, 2) == 3) {
         DvrData.videoStateIcon = 3;
         DvrData.videoStateStr = "正在回放";
         DvrData.systemMode = 1;
         Log.d("videoStateStr", "正在回放");
      }

      if (var1[6] != 255) {
         DvrData.playTime = this.TwoDigitNumber.format((long)var1[7]) + ":" + this.TwoDigitNumber.format((long)var1[8]) + ":" + this.TwoDigitNumber.format((long)var1[9]);
      } else {
         DvrData.playTime = " ";
      }

      DvrObserver.getInstance().dataChange("video.state.mode");
   }

   private void set0x21(int[] var1) {
      int var2 = var1[5];
      if (var2 != 209) {
         if (var2 != 210) {
            if (var2 != 212) {
               if (var2 != 216) {
                  if (var2 != 228) {
                     if (var2 != 232) {
                        if (var2 != 225) {
                           if (var2 != 226) {
                              if (var2 != 240) {
                                 if (var2 == 241) {
                                    DvrData.fastState = "锁定状态";
                                 }
                              } else {
                                 DvrData.fastState = "解锁状态";
                              }
                           } else {
                              DvrData.fastState = "快进 x2";
                           }
                        } else {
                           DvrData.fastState = " ";
                        }
                     } else {
                        DvrData.fastState = "快进 x8";
                     }
                  } else {
                     DvrData.fastState = "快进 x4";
                  }
               } else {
                  DvrData.fastState = "快退 x8";
               }
            } else {
               DvrData.fastState = "快退 x4";
            }
         } else {
            DvrData.fastState = "快退 x2";
         }
      } else {
         DvrData.fastState = "快退 x1";
      }

      DvrObserver.getInstance().dataChange("video.state.mode");
   }

   private void set0x50(int[] var1) {
      DvrData.fbl = var1[5] - 128;
      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x51(int[] var1) {
      DvrData.sjbz = var1[5] - 128;
      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x52(int[] var1) {
      int var2 = var1[5];
      if (var2 == 129) {
         DvrData.xhly = 0;
      } else if (var2 == 131) {
         DvrData.xhly = 1;
      } else if (var2 == 133) {
         DvrData.xhly = 2;
      }

      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x53(int[] var1) {
      DvrData.lxsy = var1[5] - 128;
      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x55(int[] var1) {
      int var2 = var1[5];
      if (var2 == 128) {
         DvrData.zlgy = 0;
      } else if (var2 == 129) {
         DvrData.zlgy = 1;
      } else if (var2 == 131) {
         DvrData.zlgy = 2;
      } else if (var2 == 133) {
         DvrData.zlgy = 3;
      }

      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x58(int[] var1) {
      if (var1[5] == 2) {
         DvrData.systemDate = var1[6] + 2000 + "/" + this.TwoDigitNumber.format((long)var1[7]) + "/" + this.TwoDigitNumber.format((long)var1[8]);
      }

      DvrObserver.getInstance().dataChange("video.state.mode");
   }

   private void set0x59(int[] var1) {
      if (var1[5] == 2) {
         DvrData.systemTime = this.TwoDigitNumber.format((long)var1[6]) + ":" + this.TwoDigitNumber.format((long)var1[7]) + ":" + this.TwoDigitNumber.format((long)var1[8]);
      }

      DvrObserver.getInstance().dataChange("video.state.mode");
   }

   private void set0x5A(int[] var1) {
      DvrData.rjbb = var1[5] + 2000 + "." + var1[6] + "." + var1[7] + " V" + var1[8] + "";
      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x5B(int[] var1) {
      int var2 = var1[5];
      if (var2 == 128) {
         DvrData.gsh = "格式化失败";
      } else if (var2 == 129) {
         DvrData.gsh = "正在格式化...";
      } else if (var2 == 130) {
         DvrData.gsh = "格式化完成";
      }

      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x5C(int[] var1) {
      if (var1[5] == 128) {
         DvrData.czxt = "重设系统失败";
      }

      DvrObserver.getInstance().dataChange("function.settings.mode");
   }

   private void set0x5F(int[] var1) {
      DvrData.czxt = "";
      DvrData.fbl = var1[5] - 128;
      DvrData.sjbz = var1[6] - 128;
      int var2 = var1[7];
      if (var2 == 129) {
         DvrData.xhly = 0;
      } else if (var2 == 131) {
         DvrData.xhly = 1;
      } else if (var2 == 133) {
         DvrData.xhly = 2;
      }

      DvrData.lxsy = var1[8] - 128;
      var2 = var1[10];
      if (var2 == 128) {
         DvrData.zlgy = 0;
      } else if (var2 == 129) {
         DvrData.zlgy = 1;
      } else if (var2 == 131) {
         DvrData.zlgy = 2;
      } else if (var2 == 133) {
         DvrData.zlgy = 3;
      }

      DvrObserver.getInstance().dataChange("function.settings.mode");
      DvrData.systemDate = var1[12] + 2000 + "/" + this.TwoDigitNumber.format((long)var1[13]) + "/" + this.TwoDigitNumber.format((long)var1[14]);
      DvrData.systemTime = this.TwoDigitNumber.format((long)var1[15]) + ":" + this.TwoDigitNumber.format((long)var1[16]) + ":" + this.TwoDigitNumber.format((long)var1[17]);
      DvrObserver.getInstance().dataChange("video.state.mode");
   }

   private void set0x63(int[] var1) {
      if (var1[5] == 129) {
         DvrData.systemMode = 1;
         DvrObserver.getInstance().dataChange("video.state.mode");
         DvrSender.send(new byte[]{64, 36});
      }

   }

   private void set0x70(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var1[6]);
      var2.add(var1[7]);
      var2.add(var1[8]);
      var2.add(var1[9]);
      var2.add(var1[10]);
      var2.add(var1[11]);
      var2.add(var1[12]);
      var2.add(var1[13]);
      var2.add(var1[14]);
      var2.add(var1[15]);
      var2.add(var1[16]);
      var2.add(var1[17]);
      var2.add(var1[18]);
      var2.add(var1[19]);
      var2.add(var1[20]);
      var2.add(var1[21]);
      var2.add(var1[22]);
      var2.add(var1[23]);
      var2.add(var1[24]);
      if (var1[5] == 1) {
         DvrData.file1NameList = var2;
         if (var1[25] == 1) {
            DvrData.file1Lock = "LOCK";
         } else {
            DvrData.file1Lock = "UNLOCK";
         }

         if (var1[26] == 1) {
            DvrData.file1Type = "JPG";
         } else {
            DvrData.file1Type = "VIDEO";
         }
      }

      if (var1[5] == 2) {
         DvrData.file2NameList = var2;
         if (var1[25] == 1) {
            DvrData.file2Lock = "LOCK";
         } else {
            DvrData.file2Lock = "UNLOCK";
         }

         if (var1[26] == 1) {
            DvrData.file2Type = "JPG";
         } else {
            DvrData.file2Type = "VIDEO";
         }
      }

      if (var1[5] == 3) {
         DvrData.file3NameList = var2;
         if (var1[25] == 1) {
            DvrData.file3Lock = "LOCK";
         } else {
            DvrData.file3Lock = "UNLOCK";
         }

         if (var1[26] == 1) {
            DvrData.file3Type = "JPG";
         } else {
            DvrData.file3Type = "VIDEO";
         }
      }

      if (var1[5] == 4) {
         DvrData.file4NameList = var2;
         if (var1[25] == 1) {
            DvrData.file4Lock = "LOCK";
         } else {
            DvrData.file4Lock = "UNLOCK";
         }

         if (var1[26] == 1) {
            DvrData.file4Type = "JPG";
         } else {
            DvrData.file4Type = "VIDEO";
         }
      }

      if (var1[5] == 5) {
         DvrData.file5NameList = var2;
         if (var1[25] == 1) {
            DvrData.file5Lock = "LOCK";
         } else {
            DvrData.file5Lock = "UNLOCK";
         }

         if (var1[26] == 1) {
            DvrData.file5Type = "JPG";
         } else {
            DvrData.file5Type = "VIDEO";
         }
      }

      if (var1[5] == 6) {
         DvrData.file6NameList = var2;
         if (var1[25] == 1) {
            DvrData.file6Lock = "LOCK";
         } else {
            DvrData.file6Lock = "UNLOCK";
         }

         if (var1[26] == 1) {
            DvrData.file6Type = "JPG";
         } else {
            DvrData.file6Type = "VIDEO";
         }
      }

      if (var1[5] == 254) {
         DvrData.filePlayingList = var2;
      }

      DvrObserver.getInstance().dataChange("play.back.mode");
   }

   public void setData(Context var1, int[] var2) {
      this.context = var1;
      Log.d("fxHouTest", "dvrInfo[4]=" + var2[4] + "   dvrInfo[5]=" + var2[5]);
      if (var2[0] == 170 && var2[1] == 77 && var2[2] == 68) {
         int var3 = var2[4];
         if (var3 != 32) {
            if (var3 != 33) {
               if (var3 != 85) {
                  if (var3 != 95) {
                     if (var3 != 99) {
                        if (var3 != 112) {
                           if (var3 != 113) {
                              switch (var3) {
                                 case 80:
                                    this.set0x50(var2);
                                    break;
                                 case 81:
                                    this.set0x51(var2);
                                    break;
                                 case 82:
                                    this.set0x52(var2);
                                    break;
                                 case 83:
                                    this.set0x53(var2);
                                    break;
                                 default:
                                    switch (var3) {
                                       case 88:
                                          this.set0x58(var2);
                                          break;
                                       case 89:
                                          this.set0x59(var2);
                                          break;
                                       case 90:
                                          this.set0x5A(var2);
                                          break;
                                       case 91:
                                          this.set0x5B(var2);
                                          break;
                                       case 92:
                                          this.set0x5C(var2);
                                    }
                              }
                           } else {
                              this.set0X71(var2);
                           }
                        } else {
                           this.set0x70(var2);
                        }
                     } else {
                        this.set0x63(var2);
                     }
                  } else {
                     this.set0x5F(var2);
                  }
               } else {
                  this.set0x55(var2);
               }
            } else {
               this.set0x21(var2);
            }
         } else {
            this.set0x20(var2);
         }
      }

   }

   private static class Holder {
      private static final DvrController controller = new DvrController();
   }
}
