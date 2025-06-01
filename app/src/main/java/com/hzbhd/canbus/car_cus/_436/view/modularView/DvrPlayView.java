package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.childView.Breathe;
import com.hzbhd.proxy.camera.manager.CameraManager;

public class DvrPlayView extends RelativeLayout implements View.OnClickListener, MdNotifyListener {
   private boolean VIDEO_VIEW_SHOW_TAG;
   TextView _436_DVR_play_page_fun_2_0;
   Breathe breathe;
   LinearLayout linearLayout2;
   Context mContext;
   ImageButton menu;
   ImageButton mode;
   ImageButton next;
   ImageButton ok;
   TextView page_state_txt;
   TextView play_state_txt;
   TextView play_time_txt;
   ImageButton prev;
   View view;

   public DvrPlayView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DvrPlayView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DvrPlayView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.VIDEO_VIEW_SHOW_TAG = false;
      this.mContext = var1;
      this.view = LayoutInflater.from(var1).inflate(2131558528, this, true);
      this.initView();
   }

   private void initView() {
      this.startVideoView();
      Breathe var1 = (Breathe)this.view.findViewById(2131361989);
      this.breathe = var1;
      var1.show();
      this.linearLayout2 = (LinearLayout)this.view.findViewById(2131362761);
      this.page_state_txt = (TextView)this.view.findViewById(2131362957);
      this.play_time_txt = (TextView)this.view.findViewById(2131362985);
      this.play_state_txt = (TextView)this.view.findViewById(2131362984);
      this._436_DVR_play_page_fun_2_0 = (TextView)this.view.findViewById(2131362985);
      this.menu = (ImageButton)this.view.findViewById(2131362838);
      this.prev = (ImageButton)this.view.findViewById(2131362992);
      this.ok = (ImageButton)this.view.findViewById(2131362918);
      this.next = (ImageButton)this.view.findViewById(2131362896);
      this.mode = (ImageButton)this.view.findViewById(2131362859);
      this.menu.setOnClickListener(new DvrPlayView$$ExternalSyntheticLambda0(this));
      this.prev.setOnClickListener(new DvrPlayView$$ExternalSyntheticLambda0(this));
      this.ok.setOnClickListener(new DvrPlayView$$ExternalSyntheticLambda0(this));
      this.next.setOnClickListener(new DvrPlayView$$ExternalSyntheticLambda0(this));
      this.mode.setOnClickListener(new DvrPlayView$$ExternalSyntheticLambda0(this));
   }

   private void setPageState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.linearLayout2.setVisibility(0);
                  this.breathe.setVisibility(8);
                  this.page_state_txt.setVisibility(0);
                  this.page_state_txt.setTextColor(-1);
                  this.page_state_txt.setText(this.mContext.getString(2131765907));
                  this.linearLayout2.setBackgroundResource(2131231230);
               }
            } else {
               this.linearLayout2.setVisibility(0);
               this.breathe.setVisibility(8);
               this.page_state_txt.setVisibility(0);
               this.page_state_txt.setTextColor(-1);
               this.page_state_txt.setText(this.mContext.getString(2131765906));
               this.linearLayout2.setBackgroundResource(2131231230);
            }
         } else {
            this.linearLayout2.setVisibility(0);
            this.breathe.setVisibility(0);
            this.page_state_txt.setVisibility(0);
            this.page_state_txt.setTextColor(-65536);
            this.page_state_txt.setText(this.mContext.getString(2131765905));
            this.linearLayout2.setBackgroundResource(2131231224);
         }
      } else {
         this.linearLayout2.setVisibility(0);
         this.breathe.setVisibility(8);
         this.page_state_txt.setVisibility(0);
         this.page_state_txt.setTextColor(-1);
         this.page_state_txt.setText(this.mContext.getString(2131765904));
         this.linearLayout2.setBackgroundResource(2131231230);
      }

   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362838:
            DvrSender.send(new byte[]{64, 34});
            break;
         case 2131362859:
            DvrSender.send(new byte[]{64, 35});
            break;
         case 2131362896:
            DvrSender.send(new byte[]{64, 38});
            break;
         case 2131362918:
            DvrSender.send(new byte[]{64, 36});
            break;
         case 2131362992:
            DvrSender.send(new byte[]{64, 37});
      }

   }

   public void refreshUi() {
      this.setPageState(GeneralDvrPlayPage.pageState);
      this._436_DVR_play_page_fun_2_0.setText(GeneralDvrPlayPage.time);
      this.play_state_txt.setText(GeneralDvrPlayPage.controlState);
   }

   public void startVideoView() {
      View var1 = this.view;
      if (var1 != null) {
         if (!this.VIDEO_VIEW_SHOW_TAG) {
            ((TextureView)var1.findViewById(2131363758)).setSurfaceTextureListener(new TextureView.SurfaceTextureListener(this) {
               final DvrPlayView this$0;

               {
                  this.this$0 = var1;
               }

               public void onSurfaceTextureAvailable(SurfaceTexture var1, int var2, int var3) {
                  Surface var4 = new Surface(var1);
                  CameraManager.INSTANCE.startPreview(2, var4);
                  this.this$0.VIDEO_VIEW_SHOW_TAG = true;
               }

               public boolean onSurfaceTextureDestroyed(SurfaceTexture var1) {
                  return false;
               }

               public void onSurfaceTextureSizeChanged(SurfaceTexture var1, int var2, int var3) {
               }

               public void onSurfaceTextureUpdated(SurfaceTexture var1) {
               }
            });
         }
      }
   }

   public void stopVideoView() {
      CameraManager.INSTANCE.stopPreview(2);
      this.VIDEO_VIEW_SHOW_TAG = false;
   }

   public void updateUi() {
      this.refreshUi();
   }
}
