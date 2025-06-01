package com.hzbhd.canbus.car_cus._448.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.util.TimerModeCheck;
import com.hzbhd.canbus.car_cus._448.view.FileListView;
import com.hzbhd.canbus.car_cus._448.view.NaviView;
import com.hzbhd.canbus.car_cus._448.view.VideoPageView;
import java.util.ArrayList;

public class DvrActivity extends AppCompatActivity implements DvrDataInterface {
   private FileListView file_list_view;
   private LinearLayout function_menu_lin;
   private NaviView main_navi;
   private int nowModeTag = 0;
   private NaviView open_menu_navi;
   private LinearLayout playback_lin;
   private NaviView playback_navi;
   private VideoPageView video_page;

   private void initView() {
      this.main_navi = (NaviView)this.findViewById(2131362829);
      this.open_menu_navi = (NaviView)this.findViewById(2131362927);
      this.playback_navi = (NaviView)this.findViewById(2131362988);
      this.file_list_view = (FileListView)this.findViewById(2131362242);
      this.function_menu_lin = (LinearLayout)this.findViewById(2131362310);
      this.playback_lin = (LinearLayout)this.findViewById(2131362987);
      ArrayList var1 = new ArrayList();
      var1.add("设置菜单");
      var1.add("文件列表");
      this.main_navi.initItem(var1);
      this.main_navi.getAction(new ActionCallback(this) {
         final DvrActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            Integer var2 = (Integer)var1;
            if (var2 == 1) {
               this.this$0.selectOpenMenuNavi();
            }

            if (var2 == 2) {
               this.this$0.selectPlaybackNavi();
               DvrSender.send(new byte[]{113, -1});
            }

         }
      });
      var1 = new ArrayList();
      var1.add("◀  返回");
      this.open_menu_navi.initItem(var1);
      this.open_menu_navi.getAction(new ActionCallback(this) {
         final DvrActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if ((Integer)var1 == 1) {
               this.this$0.selectMainNavi();
            }

         }
      });
      var1 = new ArrayList();
      var1.add("◀  返回");
      this.playback_navi.initItem(var1);
      this.playback_navi.getAction(new ActionCallback(this) {
         final DvrActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if ((Integer)var1 == 1) {
               this.this$0.selectMainNavi();
            }

         }
      });
      this.video_page.getAction(new ActionCallback(this) {
         final DvrActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (var1.equals("MODE")) {
               this.this$0.turnMode();
            }

            if (var1.equals("PREV")) {
               DvrSender.send(new byte[]{64, 37});
            }

            if (var1.equals("OK")) {
               DvrSender.send(new byte[]{64, 36});
            }

            if (var1.equals("NEXT")) {
               DvrSender.send(new byte[]{64, 38});
            }

            if (var1.equals("URGENT")) {
               DvrSender.send(new byte[]{64, 33});
            }

            if (var1.equals("CAPTURE")) {
               DvrSender.send(new byte[]{64, 37});
            }

         }
      });
      this.file_list_view.getAction(new ActionCallback(this) {
         final DvrActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (var1.equals("PREV")) {
               DvrSender.send(new byte[]{64, 37});
            }

            if (var1.equals("ADD_LOCK")) {
               DvrSender.send(new byte[]{64, 33});
               DvrSender.send(new byte[]{113, -1});
            }

            if (var1.equals("NEXT")) {
               DvrSender.send(new byte[]{64, 38});
            }

            if (var1.equals("PLAY")) {
               this.this$0.selectMainNavi();
               DvrSender.send(new byte[]{64, 36});
            }

         }
      });
   }

   private void selectMainNavi() {
      this.nowModeTag = 0;
      this.main_navi.setVisibility(0);
      this.open_menu_navi.setVisibility(8);
      this.function_menu_lin.setVisibility(8);
      this.playback_navi.setVisibility(8);
      this.playback_lin.setVisibility(8);
   }

   private void selectOpenMenuNavi() {
      this.nowModeTag = 1;
      this.main_navi.setVisibility(8);
      this.open_menu_navi.setVisibility(0);
      this.function_menu_lin.setVisibility(0);
   }

   private void selectPlaybackNavi() {
      this.nowModeTag = 2;
      this.main_navi.setVisibility(8);
      this.playback_navi.setVisibility(0);
      this.playback_lin.setVisibility(0);
   }

   private void turnMode() {
      if (DvrData.videoStateIcon == 3 || DvrData.systemMode == 1 && DvrData.videoStateIcon == 4) {
         DvrSender.send(new byte[]{64, 35});
         DvrSender.send(new byte[]{64, 35});
         DvrSender.send(new byte[]{92, -1});
      } else {
         DvrSender.send(new byte[]{64, 35});
         DvrSender.send(new byte[]{92, -1});
      }

      DvrData.systemMode = 2;
      DvrObserver.getInstance().dataChange("video.state.mode");
   }

   public void dataChange(String var1) {
      if (var1.equals("speech.exit.dvr")) {
         this.finish();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558601);
      VideoPageView var2 = (VideoPageView)this.findViewById(2131363756);
      this.video_page = var2;
      var2.startView();
      this.initView();
   }

   protected void onDestroy() {
      super.onDestroy();
      this.video_page.stopView();
      DvrObserver.getInstance().unRegister(new DvrActivity$$ExternalSyntheticLambda0(this));
   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      if (var1 == 4 && this.nowModeTag != 0) {
         this.selectMainNavi();
         return true;
      } else {
         return super.onKeyDown(var1, var2);
      }
   }

   protected void onResume() {
      super.onResume();
      DvrObserver.getInstance().register(new DvrActivity$$ExternalSyntheticLambda0(this));
      TimerModeCheck.getInstance().start(500, new ActionCallback(this) {
         final DvrActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            String var2;
            if (DvrData.systemMode == 1) {
               var2 = "回放模式";
            } else {
               var2 = "录像模式";
            }

            Log.d("DvrMode", var2);
            if (DvrData.systemMode == 1 && DvrData.videoStateIcon == 2) {
               if (this.this$0.main_navi != null) {
                  this.this$0.main_navi.setItem2Gone(false);
               }
            } else if (this.this$0.main_navi != null) {
               this.this$0.main_navi.setItem2Gone(true);
            }

            TimerModeCheck.getInstance().reset(500);
         }
      });
   }
}
