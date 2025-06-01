package com.hzbhd.canbus.car._220;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;

public class MyPanoramicView extends RelativeLayout {
   int camSt = 0;
   private Context mContext;
   boolean mListBtns = false;
   private View.OnClickListener mRearCameraButton = new View.OnClickListener(this) {
      final MyPanoramicView this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         Exception var10000;
         label56: {
            boolean var10001;
            int var2;
            try {
               var2 = var1.getId();
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
               break label56;
            }

            if (var2 != 2131363480) {
               switch (var2) {
                  case 2131363570:
                     try {
                        Util.reportCanbusInfo(new byte[]{22, -57, 1});
                        return;
                     } catch (Exception var7) {
                        var10000 = var7;
                        var10001 = false;
                        break;
                     }
                  case 2131363571:
                     try {
                        Util.reportCanbusInfo(new byte[]{22, -57, 2});
                        return;
                     } catch (Exception var6) {
                        var10000 = var6;
                        var10001 = false;
                        break;
                     }
                  case 2131363572:
                     try {
                        Util.reportCanbusInfo(new byte[]{22, -57, 3});
                        return;
                     } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                        break;
                     }
                  case 2131363573:
                     try {
                        Util.reportCanbusInfo(new byte[]{22, -57, 4});
                        return;
                     } catch (Exception var4) {
                        var10000 = var4;
                        var10001 = false;
                        break;
                     }
                  case 2131363574:
                     try {
                        Util.reportCanbusInfo(new byte[]{22, -57, 5});
                        return;
                     } catch (Exception var3) {
                        var10000 = var3;
                        var10001 = false;
                        break;
                     }
                  default:
                     return;
               }
            } else {
               try {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                  return;
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
               }
            }
         }

         Exception var10 = var10000;
         var10.printStackTrace();
      }
   };
   private Button switch_view;
   private ImageButton trumpchi_cam_360_btn_1;
   private ImageButton trumpchi_cam_360_btn_2;
   private ImageButton trumpchi_cam_360_btn_3;
   private ImageButton trumpchi_cam_360_btn_4;
   private ImageButton trumpchi_cam_360_btn_5;
   private ImageButton trumpchi_cam_360_btn_6;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      Log.i("MyPanoramicView", "MyPanoramicView--220");
      View var2 = LayoutInflater.from(var1).inflate(2131558796, this);
      this.trumpchi_cam_360_btn_1 = (ImageButton)var2.findViewById(2131363570);
      this.trumpchi_cam_360_btn_2 = (ImageButton)var2.findViewById(2131363571);
      this.trumpchi_cam_360_btn_3 = (ImageButton)var2.findViewById(2131363572);
      this.trumpchi_cam_360_btn_4 = (ImageButton)var2.findViewById(2131363573);
      this.trumpchi_cam_360_btn_5 = (ImageButton)var2.findViewById(2131363574);
      if (this.switch_view == null) {
         this.switch_view = (Button)var2.findViewById(2131363480);
      }

      ImageButton var3 = this.trumpchi_cam_360_btn_1;
      if (var3 != null) {
         var3.setOnClickListener(this.mRearCameraButton);
      }

      var3 = this.trumpchi_cam_360_btn_2;
      if (var3 != null) {
         var3.setOnClickListener(this.mRearCameraButton);
      }

      var3 = this.trumpchi_cam_360_btn_3;
      if (var3 != null) {
         var3.setOnClickListener(this.mRearCameraButton);
      }

      var3 = this.trumpchi_cam_360_btn_4;
      if (var3 != null) {
         var3.setOnClickListener(this.mRearCameraButton);
      }

      var3 = this.trumpchi_cam_360_btn_5;
      if (var3 != null) {
         var3.setOnClickListener(this.mRearCameraButton);
      }

      Button var4 = this.switch_view;
      if (var4 != null) {
         var4.setOnClickListener(this.mRearCameraButton);
      }

   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   void updateUi() {
      int var1 = this.camSt;
      ImageButton var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     var2 = this.trumpchi_cam_360_btn_1;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_2;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_3;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_4;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_5;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }
                  } else {
                     var2 = this.trumpchi_cam_360_btn_1;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_2;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_3;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_4;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131100046);
                     }

                     var2 = this.trumpchi_cam_360_btn_5;
                     if (var2 != null) {
                        var2.setBackgroundResource(2131234807);
                     }
                  }
               } else {
                  var2 = this.trumpchi_cam_360_btn_1;
                  if (var2 != null) {
                     var2.setBackgroundResource(2131100046);
                  }

                  var2 = this.trumpchi_cam_360_btn_2;
                  if (var2 != null) {
                     var2.setBackgroundResource(2131100046);
                  }

                  var2 = this.trumpchi_cam_360_btn_3;
                  if (var2 != null) {
                     var2.setBackgroundResource(2131100046);
                  }

                  var2 = this.trumpchi_cam_360_btn_4;
                  if (var2 != null) {
                     var2.setBackgroundResource(2131234807);
                  }

                  var2 = this.trumpchi_cam_360_btn_5;
                  if (var2 != null) {
                     var2.setBackgroundResource(2131100046);
                  }
               }
            } else {
               var2 = this.trumpchi_cam_360_btn_1;
               if (var2 != null) {
                  var2.setBackgroundResource(2131100046);
               }

               var2 = this.trumpchi_cam_360_btn_2;
               if (var2 != null) {
                  var2.setBackgroundResource(2131100046);
               }

               var2 = this.trumpchi_cam_360_btn_3;
               if (var2 != null) {
                  var2.setBackgroundResource(2131234807);
               }

               var2 = this.trumpchi_cam_360_btn_4;
               if (var2 != null) {
                  var2.setBackgroundResource(2131100046);
               }

               var2 = this.trumpchi_cam_360_btn_5;
               if (var2 != null) {
                  var2.setBackgroundResource(2131100046);
               }
            }
         } else {
            var2 = this.trumpchi_cam_360_btn_1;
            if (var2 != null) {
               var2.setBackgroundResource(2131100046);
            }

            var2 = this.trumpchi_cam_360_btn_2;
            if (var2 != null) {
               var2.setBackgroundResource(2131234807);
            }

            var2 = this.trumpchi_cam_360_btn_3;
            if (var2 != null) {
               var2.setBackgroundResource(2131100046);
            }

            var2 = this.trumpchi_cam_360_btn_4;
            if (var2 != null) {
               var2.setBackgroundResource(2131100046);
            }

            var2 = this.trumpchi_cam_360_btn_5;
            if (var2 != null) {
               var2.setBackgroundResource(2131100046);
            }
         }
      } else {
         var2 = this.trumpchi_cam_360_btn_1;
         if (var2 != null) {
            var2.setBackgroundResource(2131234807);
         }

         var2 = this.trumpchi_cam_360_btn_2;
         if (var2 != null) {
            var2.setBackgroundResource(2131100046);
         }

         var2 = this.trumpchi_cam_360_btn_3;
         if (var2 != null) {
            var2.setBackgroundResource(2131100046);
         }

         var2 = this.trumpchi_cam_360_btn_4;
         if (var2 != null) {
            var2.setBackgroundResource(2131100046);
         }

         var2 = this.trumpchi_cam_360_btn_5;
         if (var2 != null) {
            var2.setBackgroundResource(2131100046);
         }
      }

   }
}
