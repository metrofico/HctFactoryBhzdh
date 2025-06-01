package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySettingDialogView extends SettingDialogView {
   private ExecutorService executorService;
   private List listClose;
   private List listOpen;

   public MySettingDialogView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MySettingDialogView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MySettingDialogView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listOpen = new ArrayList();
      this.listClose = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.setOnItemClickListener(new MySettingDialogView$$ExternalSyntheticLambda0());
      this.executorService.execute(new Runnable(this) {
         final MySettingDialogView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            switch (this.this$0.getId()) {
               case 2131361792:
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755075)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755422)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755230)));
                  break;
               case 2131361803:
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755422)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755230)));
                  break;
               case 2131361806:
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755075)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755422)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131756478)));
                  break;
               case 2131361807:
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755075)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755422)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755230)));
                  break;
               case 2131361810:
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755075)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755422)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755230)));
                  break;
               case 2131361831:
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755422)));
                  this.this$0.listOpen.add(new SettingDialogBean(this.this$0.mContext.getString(2131755230)));
            }

            MySettingDialogView var1 = this.this$0;
            var1.setListFirst(var1.listOpen);
         }
      });
   }

   // $FF: synthetic method
   static void lambda$new$0(View var0, int var1) {
      switch (var0.getId()) {
         case 2131361792:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 4, (byte)var1});
            break;
         case 2131361803:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 5, (byte)var1});
            break;
         case 2131361806:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 1, (byte)var1});
            break;
         case 2131361807:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 2, (byte)var1});
            break;
         case 2131361810:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 3, (byte)var1});
            break;
         case 2131361831:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 6, (byte)var1});
      }

   }
}
