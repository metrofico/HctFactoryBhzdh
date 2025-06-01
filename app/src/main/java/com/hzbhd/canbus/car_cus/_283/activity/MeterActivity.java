package com.hzbhd.canbus.car_cus._283.activity;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.MeterChooseView;
import com.hzbhd.canbus.car_cus._283.view.MeterDialColorView;
import com.hzbhd.canbus.car_cus._283.view.MeterPointerColorView;
import com.hzbhd.canbus.car_cus._283.view.MeterRotateView;

public class MeterActivity extends AbstractBaseActivity implements View.OnClickListener, MeterRotateView.OnUpDownClickListener {
   private static byte[] bys = new byte[]{25, 0, 0, 0, 0, 32, 0, 3, 8, 0, 0};
   private AlertDialog alertDialog;
   private AlertDialog alertDialog1;
   private AlertDialog alertDialog2;
   private AlertDialog alertDialog3;
   private TextView functionTv1;
   private TextView functionTv2;
   private TextView functionTv3;
   private MeterRotateView mLeftRotateView;
   private MeterRotateView mRightRotateView;
   private MeterChooseView meterChooseView;
   private MeterChooseView meterChooseView1;
   private MeterDialColorView meterDialColorView;
   private MeterPointerColorView meterPointerColorView;

   private void initClick() {
      this.functionTv1.setOnClickListener(this);
      this.functionTv2.setOnClickListener(this);
      this.functionTv3.setOnClickListener(this);
      this.mLeftRotateView.setOnUpDownClickListener(this);
      this.mRightRotateView.setOnUpDownClickListener(this);
      this.setLeftRotateLongClick();
      this.setRightRotateLongClick();
      this.refreshUi((Bundle)null);
   }

   private void initView() {
      this.mLeftRotateView = (MeterRotateView)this.findViewById(2131362724);
      this.mRightRotateView = (MeterRotateView)this.findViewById(2131363152);
      this.functionTv1 = (TextView)this.findViewById(2131362307);
      this.functionTv2 = (TextView)this.findViewById(2131362308);
      this.functionTv3 = (TextView)this.findViewById(2131362309);
      this.mLeftRotateView.setLeftShow();
      this.mRightRotateView.setRightShow();
      this.functionTv1.setSelected(true);
      this.selectedRightRotate(true);
      this.selectedLeftRotate(true);
   }

   private boolean isSame(int var1, int var2) {
      boolean var4 = false;
      boolean var3 = var4;
      if (var1 >= 4) {
         if (var1 > 7) {
            var3 = var4;
         } else {
            var3 = var4;
            if (var2 >= 4) {
               if (var2 > 7) {
                  var3 = var4;
               } else {
                  var3 = var4;
                  if (var1 == var2) {
                     var3 = true;
                  }
               }
            }
         }
      }

      return var3;
   }

   private void selectedDialColor() {
      MeterDialColorView var1 = this.meterDialColorView;
      if (var1 != null) {
         var1.setSelectedItem(GeneralDzData.light_ambient - 2);
      }

   }

   private void selectedLeftRotate(boolean var1) {
      if (this.isSame(GeneralDzData.leftRotateInt, GeneralDzData.rightRotateInt)) {
         this.showTips1();
         if (var1) {
            ++GeneralDzData.leftRotateInt;
         } else {
            --GeneralDzData.leftRotateInt;
         }
      }

      int var2 = this.mLeftRotateView.getLeftImagesLength() - 1;
      if (GeneralDzData.leftRotateInt < 0) {
         GeneralDzData.leftRotateInt = var2;
      }

      if (GeneralDzData.leftRotateInt > var2) {
         GeneralDzData.leftRotateInt = 0;
      }

      this.mLeftRotateView.setSelectLeftImage(GeneralDzData.leftRotateInt);
   }

   private void selectedPointerColor() {
      MeterPointerColorView var1 = this.meterPointerColorView;
      if (var1 != null) {
         var1.setSelectedItem();
      }

   }

   private void selectedRightRotate(boolean var1) {
      if (this.isSame(GeneralDzData.leftRotateInt, GeneralDzData.rightRotateInt)) {
         this.showTips1();
         if (var1) {
            ++GeneralDzData.rightRotateInt;
         } else {
            --GeneralDzData.rightRotateInt;
         }
      }

      int var2 = this.mLeftRotateView.getRightImagesLength() - 1;
      if (GeneralDzData.rightRotateInt < 0) {
         GeneralDzData.rightRotateInt = var2;
      }

      if (GeneralDzData.rightRotateInt > var2) {
         GeneralDzData.rightRotateInt = 0;
      }

      this.mRightRotateView.setSelectRightImage(GeneralDzData.rightRotateInt);
   }

   private void setLeftRatateIntSend() {
      bys[9] = (byte)GeneralDzData.leftRotateInt;
      MessageSender.sendMsg(bys);
   }

   private void setLeftRotateLongClick() {
      this.mLeftRotateView.setOnLongClickListener(new MeterActivity$$ExternalSyntheticLambda0(this));
   }

   private void setRightRatateIntSend() {
      bys[10] = (byte)GeneralDzData.rightRotateInt;
      MessageSender.sendMsg(bys);
   }

   private void setRightRotateLongClick() {
      this.mRightRotateView.setOnLongClickListener(new MeterActivity$$ExternalSyntheticLambda1(this));
   }

   private void showTips1() {
      Toast.makeText(this, 2131760678, 0).show();
   }

   // $FF: synthetic method
   void lambda$setLeftRotateLongClick$0$com_hzbhd_canbus_car_cus__283_activity_MeterActivity(View var1, int var2) {
      if (this.isSame(var2, GeneralDzData.rightRotateInt)) {
         this.showTips1();
      } else {
         GeneralDzData.leftRotateInt = var2;
         this.selectedLeftRotate(true);
         this.setLeftRatateIntSend();
         this.alertDialog.dismiss();
      }
   }

   // $FF: synthetic method
   boolean lambda$setLeftRotateLongClick$1$com_hzbhd_canbus_car_cus__283_activity_MeterActivity(View var1) {
      if (this.alertDialog == null) {
         AlertDialog.Builder var2 = new AlertDialog.Builder(this, 2131820805);
         var1 = LayoutInflater.from(this).inflate(2131558456, (ViewGroup)null);
         this.meterChooseView = (MeterChooseView)var1.findViewById(2131362850);
         TextView var3 = (TextView)var1.findViewById(2131362840);
         var3 = (TextView)var1.findViewById(2131362842);
         TextView var4 = (TextView)var1.findViewById(2131362843);
         TextView var5 = (TextView)var1.findViewById(2131362844);
         var2.setView(var1);
         this.alertDialog = var2.create();
         var3.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231958), (Drawable)null, (Drawable)null);
         var4.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231955), (Drawable)null, (Drawable)null);
         var5.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231954), (Drawable)null, (Drawable)null);
         var3.setText(2131760688);
         var4.setText(2131760689);
         var5.setText(2131760690);
         this.meterChooseView.setOnItemClickListener(new MeterActivity$$ExternalSyntheticLambda3(this));
         WindowManager.LayoutParams var6 = this.alertDialog.getWindow().getAttributes();
         var6.width = (int)this.getResources().getDimension(2131170349);
         this.alertDialog.getWindow().setAttributes(var6);
      }

      this.alertDialog.show();
      this.meterChooseView.setSelectItem(GeneralDzData.leftRotateInt);
      return true;
   }

   // $FF: synthetic method
   void lambda$setRightRotateLongClick$2$com_hzbhd_canbus_car_cus__283_activity_MeterActivity(View var1, int var2) {
      if (this.isSame(var2, GeneralDzData.leftRotateInt)) {
         this.showTips1();
      } else {
         GeneralDzData.rightRotateInt = var2;
         this.selectedRightRotate(true);
         this.setRightRatateIntSend();
         this.alertDialog1.dismiss();
      }
   }

   // $FF: synthetic method
   boolean lambda$setRightRotateLongClick$3$com_hzbhd_canbus_car_cus__283_activity_MeterActivity(View var1) {
      if (this.alertDialog1 == null) {
         AlertDialog.Builder var6 = new AlertDialog.Builder(this, 2131820805);
         View var7 = LayoutInflater.from(this).inflate(2131558456, (ViewGroup)null);
         var6.setView(var7);
         ImageView var5 = (ImageView)var7.findViewById(2131361977);
         TextView var8 = (TextView)var7.findViewById(2131362840);
         TextView var3 = (TextView)var7.findViewById(2131362842);
         TextView var4 = (TextView)var7.findViewById(2131362843);
         TextView var2 = (TextView)var7.findViewById(2131362844);
         this.meterChooseView1 = (MeterChooseView)var7.findViewById(2131362850);
         AlertDialog var11 = var6.create();
         this.alertDialog1 = var11;
         var11.show();
         var8.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231957), (Drawable)null, (Drawable)null);
         var3.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231961), (Drawable)null, (Drawable)null);
         var4.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231956), (Drawable)null, (Drawable)null);
         var2.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, this.getDrawable(2131231962), (Drawable)null, (Drawable)null);
         var8.setText(2131760695);
         var3.setText(2131760701);
         var4.setText(2131760691);
         var2.setText(2131760689);
         this.meterChooseView1.setSelectItem(GeneralDzData.rightRotateInt);
         this.meterChooseView1.setOnItemClickListener(new MeterActivity$$ExternalSyntheticLambda2(this));
         var5.setRotationY(180.0F);
         ConstraintLayout.LayoutParams var10 = (ConstraintLayout.LayoutParams)var8.getLayoutParams();
         var10.setMarginStart((int)this.getResources().getDimension(2131169700));
         var8.setLayoutParams(var10);
         WindowManager.LayoutParams var9 = this.alertDialog1.getWindow().getAttributes();
         var9.width = (int)this.getResources().getDimension(2131170349);
         this.alertDialog1.getWindow().setAttributes(var9);
      }

      this.alertDialog1.show();
      this.meterChooseView1.setSelectItem(GeneralDzData.rightRotateInt);
      return true;
   }

   public void onCenter(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362724) {
         if (var2 == 2131363152) {
            this.mRightRotateView.startDisPlay2Animator(false);
            this.setRightRatateIntSend();
         }
      } else {
         this.mLeftRotateView.startDisPlay2Animator(true);
         this.setLeftRatateIntSend();
      }

   }

   public void onClick(View var1) {
      AlertDialog var3;
      WindowManager.LayoutParams var6;
      switch (var1.getId()) {
         case 2131362308:
            var3 = this.alertDialog2;
            if (var3 == null) {
               AlertDialog.Builder var5 = new AlertDialog.Builder(this, 2131820805);
               var1 = LayoutInflater.from(this).inflate(2131558459, (ViewGroup)null);
               this.meterPointerColorView = (MeterPointerColorView)var1.findViewById(2131362852);
               var5.setView(var1);
               var3 = var5.create();
               this.alertDialog2 = var3;
               var3.show();
               var6 = this.alertDialog2.getWindow().getAttributes();
               var6.width = (int)this.getResources().getDimension(2131170857);
               this.alertDialog2.getWindow().setAttributes(var6);
            } else {
               var3.show();
            }

            this.selectedPointerColor();
            break;
         case 2131362309:
            var3 = this.alertDialog3;
            if (var3 == null) {
               AlertDialog.Builder var4 = new AlertDialog.Builder(this, 2131820805);
               View var2 = LayoutInflater.from(this).inflate(2131558458, (ViewGroup)null);
               this.meterDialColorView = (MeterDialColorView)var2.findViewById(2131362851);
               var4.setView(var2);
               var3 = var4.create();
               this.alertDialog3 = var3;
               var3.show();
               var6 = this.alertDialog3.getWindow().getAttributes();
               var6.width = (int)this.getResources().getDimension(2131170857);
               this.alertDialog3.getWindow().setAttributes(var6);
            } else {
               var3.show();
            }

            this.selectedDialColor();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558431);
      MessageSender.sendMsg(new byte[]{25, 0, 0, 0, 0, 32, 0, 3, 8, -1, -1});
      this.initView();
      this.initClick();
   }

   public void onDown(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362724) {
         if (var2 == 2131363152) {
            ++GeneralDzData.rightRotateInt;
            this.selectedRightRotate(true);
         }
      } else {
         ++GeneralDzData.leftRotateInt;
         this.selectedLeftRotate(true);
      }

   }

   public void onUp(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362724) {
         if (var2 == 2131363152) {
            --GeneralDzData.rightRotateInt;
            this.selectedRightRotate(false);
         }
      } else {
         --GeneralDzData.leftRotateInt;
         this.selectedLeftRotate(false);
      }

   }

   public void refreshUi(Bundle var1) {
      this.selectedDialColor();
      this.selectedPointerColor();
      this.mLeftRotateView.setSelectLeftImage(GeneralDzData.leftRotateInt);
      this.mRightRotateView.setSelectRightImage(GeneralDzData.rightRotateInt);
   }
}
