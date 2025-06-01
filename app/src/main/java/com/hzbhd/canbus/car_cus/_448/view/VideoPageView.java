package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;

public class VideoPageView extends LinearLayout implements DvrDataInterface {
   private KeyButton capture_btn;
   private TextView fast_forward;
   private KeyButton mode_btn;
   private ImageView mode_icon;
   private TextView mode_txt;
   private KeyButton next_btn;
   private KeyButton ok_btn;
   private KeyButton prev_btn;
   private TextView system_time;
   private KeyButton urgent_btn;
   private VideoInfoView video_info_view;
   private VideoStateView video_state;
   private View view;

   public VideoPageView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public VideoPageView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public VideoPageView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.view = LayoutInflater.from(var1).inflate(2131558612, this, true);
      this.video_info_view = (VideoInfoView)this.findViewById(2131363755);
      this.video_state = (VideoStateView)this.view.findViewById(2131363757);
      this.system_time = (TextView)this.view.findViewById(2131363485);
      this.mode_btn = (KeyButton)this.view.findViewById(2131362864);
      this.prev_btn = (KeyButton)this.view.findViewById(2131362993);
      this.ok_btn = (KeyButton)this.view.findViewById(2131362919);
      this.next_btn = (KeyButton)this.view.findViewById(2131362897);
      this.urgent_btn = (KeyButton)this.view.findViewById(2131363729);
      this.capture_btn = (KeyButton)this.view.findViewById(2131362095);
      this.mode_icon = (ImageView)this.view.findViewById(2131362865);
      this.mode_txt = (TextView)this.view.findViewById(2131362867);
      this.fast_forward = (TextView)this.view.findViewById(2131362229);
      this.mode_btn.setTextValue("模式切换");
      this.prev_btn.setTextValue("◀◀");
      this.ok_btn.setTextValue("ok");
      this.next_btn.setTextValue("▶▶");
      this.urgent_btn.setTextValue("紧急录像");
      this.capture_btn.setTextValue("一键抓拍");
   }

   public void dataChange(String var1) {
      if (var1.equals("video.state.mode")) {
         this.updateUi();
      }

   }

   public void getAction(ActionCallback var1) {
      this.mode_btn.getEventUpAction(new ActionCallback(this, var1) {
         final VideoPageView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("MODE");
         }
      });
      this.prev_btn.getEventUpAction(new ActionCallback(this, var1) {
         final VideoPageView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("PREV");
         }
      });
      this.ok_btn.getEventUpAction(new ActionCallback(this, var1) {
         final VideoPageView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("OK");
         }
      });
      this.next_btn.getEventUpAction(new ActionCallback(this, var1) {
         final VideoPageView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("NEXT");
         }
      });
      this.urgent_btn.getEventUpAction(new ActionCallback(this, var1) {
         final VideoPageView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("URGENT");
         }
      });
      this.capture_btn.getEventUpAction(new ActionCallback(this, var1) {
         final VideoPageView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("CAPTURE");
         }
      });
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.updateUi();
      DvrObserver.getInstance().register(this);
   }

   public void onStopNestedScroll(View var1) {
      super.onStopNestedScroll(var1);
   }

   public void startView() {
      this.video_info_view.startVideoView();
   }

   public void stopView() {
      this.video_info_view.stopVideoView();
   }

   public void updateUi() {
      this.video_state.setState(DvrData.videoStateStr);
      if (DvrData.videoStateIcon == 1) {
         this.ok_btn.setTextValue("II");
         this.video_state.setIcon(2131231580);
      } else if (DvrData.videoStateIcon == 2) {
         this.ok_btn.setTextValue("▶");
         this.video_state.setIcon(2131231590);
      } else if (DvrData.videoStateIcon == 3) {
         this.ok_btn.setTextValue("II");
         this.video_state.setIcon(2131231589);
      } else if (DvrData.videoStateIcon == 4) {
         this.ok_btn.setTextValue("▶");
         this.video_state.setIcon(2131231590);
      }

      this.video_state.setTime(DvrData.playTime);
      this.system_time.setText(DvrData.systemDate + "  " + DvrData.systemTime);
      if (DvrData.systemMode == 1) {
         this.mode_icon.setBackgroundResource(2131231601);
         this.mode_txt.setText("回放模式");
         this.capture_btn.setVisibility(8);
         this.urgent_btn.setVisibility(8);
         this.prev_btn.setVisibility(0);
         this.next_btn.setVisibility(0);
      } else if (DvrData.systemMode == 2) {
         this.mode_icon.setBackgroundResource(2131231600);
         this.mode_txt.setText("录像模式");
         this.capture_btn.setVisibility(0);
         this.urgent_btn.setVisibility(0);
         this.prev_btn.setVisibility(8);
         this.next_btn.setVisibility(8);
      }

      this.fast_forward.setText(DvrData.fastState);
   }
}
