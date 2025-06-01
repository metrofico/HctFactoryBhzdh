package com.hzbhd.canbus.car_cus._283.choosecan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import java.util.ArrayList;
import java.util.List;

public class A6ChooseCanTypeActivity extends Activity {
   private static final int MQB = 283;
   private static final int PQ = 291;
   private static final String TAG = "A6ChooseCanTypeActivity";
   private String[] MQB_CAR = new String[]{"默认", "帕萨特混动"};
   private String[] PQ_CAR = new String[]{"默认"};
   private A6AdapterCar adapterCan;
   private A6AdapterCar adapterCar;
   private A6AdapterCar adapterCarType;
   private A6AdapterCar adapterConpeny;
   private int factoryCanType = 283;
   private List listCan = new ArrayList();
   private List listCar = new ArrayList();
   private List listCarType = new ArrayList();
   private List listCompany = new ArrayList();
   private RecyclerView recyclerViewCan;
   private RecyclerView recyclerViewCar;
   private RecyclerView recyclerViewCarType;
   private RecyclerView recyclerViewCompany;

   private void chooseCanType(int var1, int var2) {
      if (this.factoryCanType == var1) {
         if (SelectCanTypeUtil.getCurrentCanDiffId() == var2) {
            return;
         }

         CanBus.INSTANCE.setDifferentId(var2);
         CanbusConfig.INSTANCE.setCanType(var1);
         System.exit(0);
      } else {
         setSpecifyCanTypeIdAndRestMpu(var1);
      }

   }

   private String getSelectName(List var1) {
      int var2 = 0;

      String var3;
      while(true) {
         if (var2 >= var1.size()) {
            var3 = "";
            break;
         }

         if (((A6CarChooseBean)var1.get(var2)).isSelect()) {
            var3 = ((A6CarChooseBean)var1.get(var2)).getName();
            break;
         }

         ++var2;
      }

      return var3;
   }

   private void initClickCan() {
      this.adapterCan.setOnItemClickListener(new A6AdapterCar.OnItemClickListener(this) {
         final A6ChooseCanTypeActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, int var2) {
            if (!((A6CarChooseBean)this.this$0.listCan.get(var2)).isSelect()) {
               A6ChooseCanTypeActivity var6 = this.this$0;
               String var7 = var6.getSelectName(var6.listCompany);
               A6ChooseCanTypeActivity var3 = this.this$0;
               String var8 = var3.getSelectName(var3.listCar);
               A6ChooseCanTypeActivity var4 = this.this$0;
               String var9 = var4.getSelectName(var4.listCarType);
               String var5 = ((A6CarChooseBean)this.this$0.listCan.get(var2)).getName();
               var7 = this.this$0.getString(2131760597) + var7 + " " + var8 + " " + var9 + " " + var5;
               this.this$0.showDialogSelect(var2, var7);
            }

         }
      });
   }

   private void initClickCarType() {
      this.adapterCarType.setOnItemClickListener(new A6AdapterCar.OnItemClickListener(this) {
         final A6ChooseCanTypeActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, int var2) {
            if (!((A6CarChooseBean)this.this$0.listCarType.get(var2)).isSelect()) {
               A6ChooseCanTypeActivity var3 = this.this$0;
               var3.setSelectPosition(var3.listCarType, var2);
               this.this$0.listCan.clear();
               if (var2 != 0) {
                  if (var2 == 1) {
                     for(var2 = 0; var2 < this.this$0.PQ_CAR.length; ++var2) {
                        this.this$0.listCan.add(new A6CarChooseBean(false, this.this$0.PQ_CAR[var2]));
                     }
                  }
               } else {
                  for(var2 = 0; var2 < this.this$0.MQB_CAR.length; ++var2) {
                     this.this$0.listCan.add(new A6CarChooseBean(false, this.this$0.MQB_CAR[var2]));
                  }
               }

               this.this$0.adapterCarType.notifyDataSetChanged();
               this.this$0.adapterCan.notifyDataSetChanged();
            }

         }
      });
   }

   private void initView() {
      this.recyclerViewCompany = (RecyclerView)this.findViewById(2131363078);
      this.recyclerViewCar = (RecyclerView)this.findViewById(2131363076);
      this.recyclerViewCarType = (RecyclerView)this.findViewById(2131363077);
      this.recyclerViewCan = (RecyclerView)this.findViewById(2131363075);
      List var3 = this.listCompany;
      boolean var2 = true;
      var3.add(new A6CarChooseBean(true, "德众尚杰"));
      this.recyclerViewCompany.setLayoutManager(new LinearLayoutManager(this));
      RecyclerView var4 = this.recyclerViewCompany;
      A6AdapterCar var5 = new A6AdapterCar(this, this.listCompany);
      this.adapterConpeny = var5;
      var4.setAdapter(var5);
      this.listCar.add(new A6CarChooseBean(true, "大众"));
      this.recyclerViewCar.setLayoutManager(new LinearLayoutManager(this));
      RecyclerView var6 = this.recyclerViewCar;
      A6AdapterCar var7 = new A6AdapterCar(this, this.listCar);
      this.adapterCar = var7;
      var6.setAdapter(var7);
      var3 = this.listCarType;
      boolean var1;
      if (this.factoryCanType == 283) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.add(new A6CarChooseBean(var1, "MQB"));
      var3 = this.listCarType;
      if (this.factoryCanType == 291) {
         var1 = var2;
      } else {
         var1 = false;
      }

      var3.add(new A6CarChooseBean(var1, "PQ"));
      this.recyclerViewCarType.setLayoutManager(new LinearLayoutManager(this));
      var6 = this.recyclerViewCarType;
      var7 = new A6AdapterCar(this, this.listCarType);
      this.adapterCarType = var7;
      var6.setAdapter(var7);
      this.initClickCarType();
      this.setCarData();
      this.recyclerViewCan.setLayoutManager(new LinearLayoutManager(this));
      var6 = this.recyclerViewCan;
      var7 = new A6AdapterCar(this, this.listCan);
      this.adapterCan = var7;
      var6.setAdapter(var7);
      this.initClickCan();
   }

   private void setCarData() {
      int var1;
      boolean var2;
      List var3;
      if (this.factoryCanType == 283) {
         for(var1 = 0; var1 < this.MQB_CAR.length; ++var1) {
            var3 = this.listCan;
            if (SelectCanTypeUtil.getCurrentCanDiffId() == var1) {
               var2 = true;
            } else {
               var2 = false;
            }

            var3.add(new A6CarChooseBean(var2, this.MQB_CAR[var1]));
         }
      } else {
         for(var1 = 0; var1 < this.PQ_CAR.length; ++var1) {
            var3 = this.listCan;
            if (SelectCanTypeUtil.getCurrentCanDiffId() == var1) {
               var2 = true;
            } else {
               var2 = false;
            }

            var3.add(new A6CarChooseBean(var2, this.PQ_CAR[var1]));
         }
      }

   }

   private void setSelectPosition(List var1, int var2) {
      for(int var3 = 0; var3 < var1.size(); ++var3) {
         if (var3 == var2) {
            ((A6CarChooseBean)var1.get(var3)).setSelect(true);
         } else {
            ((A6CarChooseBean)var1.get(var3)).setSelect(false);
         }
      }

   }

   private static void setSpecifyCanTypeIdAndRestMpu(int var0) {
      HzbhdLog.d("A6ChooseCanTypeActivity", "切换can重启");
      CanbusConfig.INSTANCE.setCanType(var0);

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      SendKeyManager.getInstance().resetMpu(HotKeyConstant.RESET_MODE.NORMAL, 1);
   }

   private void showDialogSelect(int var1, String var2) {
      AlertDialog.Builder var3 = new AlertDialog.Builder(this);
      var3.setMessage(var2);
      var3.setPositiveButton(2131770014, new DialogInterface.OnClickListener(this, var2, var1) {
         final A6ChooseCanTypeActivity this$0;
         final String val$message;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$message = var2;
            this.val$position = var3;
         }

         public void onClick(DialogInterface var1, int var2) {
            boolean var3 = this.val$message.contains(((A6CarChooseBean)this.this$0.listCarType.get(0)).getName());
            short var4 = 283;
            if (!var3 && this.val$message.contains(((A6CarChooseBean)this.this$0.listCarType.get(1)).getName())) {
               var4 = 291;
            }

            this.this$0.chooseCanType(var4, this.val$position);
         }
      });
      var3.setNegativeButton(2131767924, (DialogInterface.OnClickListener)null);
      var3.create().show();
   }

   void fullScreen(boolean var1) {
      WindowManager.LayoutParams var2;
      if (var1) {
         var2 = this.getWindow().getAttributes();
         var2.flags |= 2048;
         this.getWindow().setAttributes(var2);
         this.getWindow().addFlags(512);
      } else {
         var2 = this.getWindow().getAttributes();
         var2.flags &= -2049;
         this.getWindow().setAttributes(var2);
         this.getWindow().clearFlags(512);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558586);
      this.factoryCanType = SelectCanTypeUtil.getCurrentCanTypeId(this);
      this.initView();
   }
}
