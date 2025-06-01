package com.hzbhd.canbus.park.external360cam;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class IrCam360MaylasiaView extends RelativeLayout implements View.OnClickListener {
   public static boolean isHaveView;
   private ImageButton btn_exit;
   private ImageButton btn_next;
   public ImageButton cam_360_pull_btn;
   private byte currentPage;
   private ImageButton four_region;
   private ImageButton front_all;
   private ImageButton front_only;
   private boolean isBtnsShow;
   private byte lastRecPage;
   private ImageButton left_all;
   private ImageButton left_only;
   private ImageButton left_right_front_all;
   private ImageButton left_right_rear_all;
   private LinearLayout lo_360_main_btns;
   private LinearLayout lo_cam_btn_part_1;
   private LinearLayout lo_cam_btn_part_2;
   private LinearLayout lo_cam_btn_part_3;
   private LinearLayout lo_cam_btn_part_4;
   private ImageButton rear_all;
   private ImageButton rear_only;
   private ImageButton right_all;
   private ImageButton right_only;
   View view;

   public IrCam360MaylasiaView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public IrCam360MaylasiaView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public IrCam360MaylasiaView(Context var1, AttributeSet var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public IrCam360MaylasiaView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.isBtnsShow = false;
      this.currentPage = 0;
      this.lastRecPage = 0;
      this.view = LayoutInflater.from(var1).inflate(2131558740, this);
      this.initUI(this.getContext());
   }

   private void changeBtnPage() {
      byte var1 = this.currentPage;
      this.lastRecPage = var1;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.currentPage = 0;
               this.setVisual(this.lo_cam_btn_part_1, true);
               this.setVisual(this.lo_cam_btn_part_2, true);
               this.setVisual(this.lo_cam_btn_part_3, false);
            }
         } else {
            this.currentPage = 2;
            this.setVisual(this.lo_cam_btn_part_1, true);
            this.setVisual(this.lo_cam_btn_part_2, false);
            this.setVisual(this.lo_cam_btn_part_3, true);
         }
      } else {
         this.currentPage = 1;
         this.setVisual(this.lo_cam_btn_part_1, false);
         this.setVisual(this.lo_cam_btn_part_2, true);
         this.setVisual(this.lo_cam_btn_part_3, true);
      }

   }

   private ImageButton setOnclickAll(int var1) {
      ImageButton var2 = (ImageButton)this.view.findViewById(var1);
      if (var2 != null) {
         var2.setOnClickListener(this);
      }

      return var2;
   }

   private void setVisual(LinearLayout var1, boolean var2) {
      if (var1.getVisibility() == 0 && var2) {
         var1.setVisibility(8);
      }

      if (var1.getVisibility() == 8 && !var2) {
         var1.setVisibility(0);
      }

   }

   public void initUI(Context var1) {
      this.front_all = this.setOnclickAll(2131362266);
      this.rear_all = this.setOnclickAll(2131363044);
      this.left_all = this.setOnclickAll(2131362726);
      this.right_all = this.setOnclickAll(2131363154);
      this.front_only = this.setOnclickAll(2131362272);
      this.rear_only = this.setOnclickAll(2131363056);
      this.left_only = this.setOnclickAll(2131362729);
      this.right_only = this.setOnclickAll(2131363158);
      this.left_right_front_all = this.setOnclickAll(2131362746);
      this.four_region = this.setOnclickAll(2131362262);
      this.left_right_rear_all = this.setOnclickAll(2131362747);
      this.cam_360_pull_btn = (ImageButton)this.view.findViewById(2131362082);
      this.btn_next = (ImageButton)this.view.findViewById(2131362034);
      this.btn_exit = (ImageButton)this.view.findViewById(2131362022);
      this.cam_360_pull_btn.setOnClickListener(this);
      this.btn_next.setOnClickListener(this);
      this.btn_exit.setOnClickListener(this);
      this.lo_cam_btn_part_1 = (LinearLayout)this.view.findViewById(2131362811);
      this.lo_cam_btn_part_2 = (LinearLayout)this.view.findViewById(2131362812);
      this.lo_cam_btn_part_3 = (LinearLayout)this.view.findViewById(2131362813);
      this.lo_360_main_btns = (LinearLayout)this.view.findViewById(2131362810);
      this.lo_cam_btn_part_4 = (LinearLayout)this.view.findViewById(2131362814);
      this.currentPage = 0;
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362022:
            External360CamCmds.getInstance().getCmds().exit();
            break;
         case 2131362034:
            if (this.isBtnsShow) {
               this.changeBtnPage();
            }
            break;
         case 2131362082:
            this.setImgBtnsGone();
            break;
         case 2131362262:
            External360CamCmds.getInstance().getCmds().fourRegion();
            break;
         case 2131362266:
            External360CamCmds.getInstance().getCmds().frontAll();
            break;
         case 2131362272:
            External360CamCmds.getInstance().getCmds().frontOnly();
            break;
         case 2131362726:
            External360CamCmds.getInstance().getCmds().leftAll();
            break;
         case 2131362729:
            External360CamCmds.getInstance().getCmds().leftOnly();
            break;
         case 2131362746:
            External360CamCmds.getInstance().getCmds().allFrontLeftRight();
            break;
         case 2131362747:
            External360CamCmds.getInstance().getCmds().allRearLeftRight();
            break;
         case 2131363044:
            External360CamCmds.getInstance().getCmds().rearAll();
            break;
         case 2131363056:
            External360CamCmds.getInstance().getCmds().rearOnly();
            break;
         case 2131363154:
            External360CamCmds.getInstance().getCmds().rightAll();
            break;
         case 2131363158:
            External360CamCmds.getInstance().getCmds().rightOnly();
      }

   }

   public void setImgBtnsGone() {
      if (this.isBtnsShow) {
         this.setVisual(this.lo_cam_btn_part_1, true);
         this.setVisual(this.lo_cam_btn_part_2, true);
         this.setVisual(this.lo_cam_btn_part_3, true);
         this.setVisual(this.lo_cam_btn_part_4, true);
         this.isBtnsShow = false;
      } else {
         this.currentPage = this.lastRecPage;
         this.changeBtnPage();
         this.setVisual(this.lo_cam_btn_part_4, false);
         this.isBtnsShow = true;
      }

   }
}
