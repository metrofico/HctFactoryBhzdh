package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.SelectCanTypeLastLvAdapter;
import com.hzbhd.canbus.adapter.SelectCarCateLvAdapter;
import com.hzbhd.canbus.adapter.SelectProtocolCompanyLvAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.util.AnimationUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.view.MarqueeTextView;
import com.hzbhd.cantype.CanTypeMap;
import com.hzbhd.cantype.CanTypeMapEng;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class SelectCanTypeActivity extends Activity implements View.OnClickListener {
   private static final long ANIMATION_PARAM_CAR_MODEL_DURATION = 500L;
   private static final float ANIMATION_PARAM_CAR_MODEL_HIDE_WEIGHT = 0.0F;
   private static final float ANIMATION_PARAM_CAR_MODEL_SHOW_WEIGHT = 1.7F;
   private static final long ANIMATION_PARAM_COMPANY_DURATION = 500L;
   private CanTypeMap mCanTypeMap;
   private SelectCarCateLvAdapter mCarCategoryAdapter;
   private SelectCarCateLvAdapter.ItemCallBackInterface mCarCategoryItemSelect = new SelectCarCateLvAdapter.ItemCallBackInterface(this) {
      final SelectCanTypeActivity this$0;

      {
         this.this$0 = var1;
      }

      public void select(int var1) {
         this.this$0.playAnimationOnCateporyClick();
         this.this$0.mCarCategoryAdapter.setSelectItem((String)this.this$0.mCarCategoryList.get(var1));
         this.this$0.mCarCategoryAdapter.notifyDataSetChanged();
         SelectCanTypeActivity var2 = this.this$0;
         var2.mSelectCarCategory = (String)var2.mCarCategoryList.get(var1);
         if (!TextUtils.isEmpty(this.this$0.mSelectCarCategory)) {
            this.this$0.mCarModelAndYearsList.clear();
            Iterator var4 = ((ArrayList)((LinkedHashMap)this.this$0.getCantypeMap().getCanTypeMap().get(this.this$0.mSelectProtocolCompany)).get(this.this$0.mSelectCarCategory)).iterator();

            while(var4.hasNext()) {
               Integer var3 = (Integer)var4.next();
               CanTypeAllEntity var5 = this.this$0.getCarCategoryList(var3);
               if (var5 != null) {
                  this.this$0.mCarModelAndYearsList.add(var5);
               }
            }

            this.this$0.mCarModelAndYearsAdapter.notifyDataSetChanged();
         }
      }
   };
   private List mCarCategoryList;
   private RecyclerView mCarCategoryLv;
   private SelectCanTypeLastLvAdapter mCarModelAndYearsAdapter;
   private List mCarModelAndYearsList;
   private RecyclerView mCarModelAndYearsLv;
   private AnimationUtil mCarModelAnimationUtil;
   private SelectCanTypeLastLvAdapter.ItemCallBackInterface mCarModelItemSelect = new SelectCanTypeLastLvAdapter.ItemCallBackInterface(this) {
      final SelectCanTypeActivity this$0;

      {
         this.this$0 = var1;
      }

      public void select(int var1) {
         this.this$0.mCarModelAndYearsAdapter.notifyDataSetChanged();
         CanTypeAllEntity var2 = (CanTypeAllEntity)this.this$0.mCarModelAndYearsList.get(var1);
         SelectCanTypeUtil.showDialogToUpdate(this.this$0, var2, (String)null);
      }
   };
   private AnimationUtil mCompanyAnimationUtil;
   private int mCompanyItemDotWidth;
   private int mCompanyLayoutWidth;
   private MarqueeTextView mCurrentSelectedTv;
   private Dialog mDialog;
   private StringBuffer mInputContentSb;
   private TextView mInputIdTv;
   private int mOrientation;
   private SelectProtocolCompanyLvAdapter mProtocolCompanyAdapter;
   private SelectProtocolCompanyLvAdapter.ItemCallBackInterface mProtocolCompanyItemSelect = new SelectProtocolCompanyLvAdapter.ItemCallBackInterface(this) {
      final SelectCanTypeActivity this$0;

      {
         this.this$0 = var1;
      }

      public void select(int var1) {
         if (!this.this$0.playAnimationOnCompanyClick()) {
            this.this$0.mProtocolCompanyAdapter.setSelectItem((String)this.this$0.mProtocolCompanyList.get(var1));
            this.this$0.mProtocolCompanyAdapter.notifyDataSetChanged();
            SelectCanTypeActivity var2 = this.this$0;
            var2.mSelectProtocolCompany = (String)var2.mProtocolCompanyList.get(var1);
            if (!TextUtils.isEmpty(this.this$0.mSelectProtocolCompany)) {
               this.this$0.mCarCategoryList.clear();
               this.this$0.mCarCategoryList.addAll(((LinkedHashMap)this.this$0.getCantypeMap().getCanTypeMap().get(this.this$0.mProtocolCompanyList.get(var1))).keySet());
               this.this$0.mCarCategoryAdapter.notifyDataSetChanged();
               this.this$0.mCarModelAndYearsList.clear();
               this.this$0.mCarModelAndYearsAdapter.notifyDataSetChanged();
            }
         }
      }
   };
   private List mProtocolCompanyList;
   private RecyclerView mProtocolCompanyLv;
   private String mSelectCarCategory;
   private String mSelectProtocolCompany;
   private Button update_canbus;

   private void deleteInputContent() {
      StringBuffer var1 = this.mInputContentSb;
      if (var1 != null) {
         var1.delete(0, var1.length());
      }

   }

   private void findViews() {
      this.mProtocolCompanyLv = (RecyclerView)this.findViewById(2131362822);
      this.mCarCategoryLv = (RecyclerView)this.findViewById(2131362823);
      this.mCarModelAndYearsLv = (RecyclerView)this.findViewById(2131362824);
      this.mCurrentSelectedTv = (MarqueeTextView)this.findViewById(2131363700);
      Button var1 = (Button)this.findViewById(2131363728);
      this.update_canbus = var1;
      var1.setOnClickListener(new SelectCanTypeActivity$$ExternalSyntheticLambda0(this));
   }

   private String getCanTypIdStr(List var1) {
      if (var1 == null) {
         return "";
      } else {
         StringBuffer var2 = new StringBuffer();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.append((String)var3.next());
            var2.append(" ");
         }

         var2.deleteCharAt(var2.length() - 1);
         return var2.toString();
      }
   }

   private CanTypeMap getCantypeMap() {
      if (this.mCanTypeMap == null) {
         if (LogUtil.log5()) {
            LogUtil.d("getCantypeMap: " + this.getResources().getConfiguration().locale.getLanguage());
         }

         if (this.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
            this.mCanTypeMap = new CanTypeMap();
         } else {
            this.mCanTypeMap = new CanTypeMapEng();
         }
      }

      return this.mCanTypeMap;
   }

   private String getCurrentSelectCanType() {
      ArrayList var3 = CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(this)).getList();
      if (var3 != null && var3.size() != 0) {
         int var1 = CanBus.INSTANCE.getDifferentId();

         String var2;
         try {
            StringBuilder var5;
            if (this.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
               var5 = new StringBuilder();
               var2 = var5.append(((CanTypeAllEntity)var3.get(var1)).getProtocol_company()).append(" ").append(((CanTypeAllEntity)var3.get(var1)).getCar_category()).append(" ").append(((CanTypeAllEntity)var3.get(var1)).getCan_type_id()).toString();
            } else {
               var5 = new StringBuilder();
               var2 = var5.append(((CanTypeAllEntity)var3.get(var1)).getEnglish_protocol_company()).append(" ").append(((CanTypeAllEntity)var3.get(var1)).getEnglish_car_category()).append(" ").append(((CanTypeAllEntity)var3.get(var1)).getCan_type_id()).toString();
            }
         } catch (Exception var4) {
            if (this.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
               var2 = ((CanTypeAllEntity)var3.get(0)).getProtocol_company() + " " + ((CanTypeAllEntity)var3.get(0)).getCar_category() + " " + ((CanTypeAllEntity)var3.get(0)).getCan_type_id();
            } else {
               var2 = ((CanTypeAllEntity)var3.get(0)).getEnglish_protocol_company() + " " + ((CanTypeAllEntity)var3.get(0)).getEnglish_car_category() + " " + ((CanTypeAllEntity)var3.get(0)).getCan_type_id();
            }
         }

         return var2;
      } else {
         com.hzbhd.canbus.util.LogUtil.showLog("list==null||list.size() ==0");
         return "";
      }
   }

   private void hideCarModel() {
      this.mCarModelAnimationUtil.playWeightAnimation(0.0F, 500L);
   }

   private void hideProtocolCompany() {
      this.mCompanyAnimationUtil.playWidthAnimation(this.mCompanyItemDotWidth, 500L);
   }

   private void initAnimationParams() {
      this.mOrientation = this.getResources().getConfiguration().orientation;
      if (this.isOrientationPort()) {
         this.mCompanyLayoutWidth = this.getResources().getDimensionPixelOffset(2131178209);
         this.mCompanyItemDotWidth = this.getResources().getDimensionPixelOffset(2131178208);
         AnimationUtil var1 = new AnimationUtil();
         this.mCompanyAnimationUtil = var1;
         var1.setView(this.mProtocolCompanyLv);
         var1 = new AnimationUtil();
         this.mCarModelAnimationUtil = var1;
         var1.setView(this.mCarModelAndYearsLv);
         this.playAnimationOnCompanyClick();
      }

   }

   private void initViews() {
      this.mProtocolCompanyList = new ArrayList();
      this.mProtocolCompanyAdapter = new SelectProtocolCompanyLvAdapter(this, this.mProtocolCompanyList, this.mProtocolCompanyItemSelect);
      this.mProtocolCompanyLv.setLayoutManager(new LinearLayoutManager(this));
      this.mProtocolCompanyLv.setAdapter(this.mProtocolCompanyAdapter);
      this.mCarCategoryList = new ArrayList();
      this.mCarCategoryAdapter = new SelectCarCateLvAdapter(this, this.mCarCategoryList, this.mCarCategoryItemSelect);
      this.mCarCategoryLv.setLayoutManager(new LinearLayoutManager(this));
      this.mCarCategoryLv.setAdapter(this.mCarCategoryAdapter);
      this.mCarModelAndYearsList = new ArrayList();
      this.mCarModelAndYearsAdapter = new SelectCanTypeLastLvAdapter(this, this.mCarModelAndYearsList, this.mCarModelItemSelect);
      this.mCarModelAndYearsLv.setLayoutManager(new LinearLayoutManager(this));
      this.mCarModelAndYearsLv.setAdapter(this.mCarModelAndYearsAdapter);
      (new Thread(this) {
         final SelectCanTypeActivity this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.mProtocolCompanyList.addAll(this.this$0.getCantypeMap().getCanTypeMap().keySet());
            this.this$0.runOnUiThread(new Runnable(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void run() {
                  this.this$1.this$0.mProtocolCompanyAdapter.notifyDataSetChanged();
               }
            });
            String var1 = this.this$0.getCurrentSelectCanType();
            this.this$0.runOnUiThread(new Runnable(this, var1) {
               final <undefinedtype> this$1;
               final String val$curSelectCanType;

               {
                  this.this$1 = var1;
                  this.val$curSelectCanType = var2;
               }

               public void run() {
                  this.this$1.this$0.mCurrentSelectedTv.setText(this.val$curSelectCanType);
               }
            });
         }
      }).start();
   }

   private void input(int var1) {
      if (this.mInputContentSb == null) {
         this.mInputContentSb = new StringBuffer();
      }

      this.mInputContentSb.append(var1);
      this.refreshInputTv();
   }

   private boolean isOrientationPort() {
      int var1 = this.mOrientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   private void playAnimationOnCateporyClick() {
      if (this.isOrientationPort()) {
         this.hideProtocolCompany();
         this.showCarModel();
      }

   }

   private boolean playAnimationOnCompanyClick() {
      if (this.isOrientationPort()) {
         this.hideCarModel();
         return this.showProtocolCompany();
      } else {
         return false;
      }
   }

   private void refreshInputTv() {
      this.mInputIdTv.setText(this.mInputContentSb);
   }

   private void searchInList(int var1) {
      ArrayList var2 = CanTypeUtil.INSTANCE.getCanType(var1).getList();
      if (var2.size() == 0) {
         Toast.makeText(this, "Id Not Found", 0).show();
      } else {
         SelectCanTypeUtil.showDialogToUpdate(this, (CanTypeAllEntity)var2.get(0), (String)null);
         this.mDialog.dismiss();
      }
   }

   private void showCarModel() {
      this.mCarModelAnimationUtil.playWeightAnimation(1.7F, 500L);
   }

   private boolean showProtocolCompany() {
      return this.mCompanyAnimationUtil.playWidthAnimation(this.mCompanyLayoutWidth, 500L);
   }

   public CanTypeAllEntity getCarCategoryList(int var1) {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = CanTypeUtil.INSTANCE.getCanTypeAllEntityByCompanyAndCategory(this.mSelectProtocolCompany, this.mSelectCarCategory, var1);
      if (var3.isEmpty()) {
         return null;
      } else {
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            CanTypeAllEntity var5 = (CanTypeAllEntity)var4.next();
            String var7;
            if (this.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
               var7 = var5.getCar_model() + "  " + var5.getYears() + "\n";
               if (!var2.contains(var7)) {
                  var2.add(var7);
               }
            } else {
               var7 = var5.getEnglish_car_model() + "  " + var5.getEnglish_years() + "\n";
               if (!var2.contains(var7)) {
                  var2.add(var7);
               }
            }
         }

         CanTypeAllEntity var6 = new CanTypeAllEntity(((CanTypeAllEntity)var3.get(0)).getProtocol_company(), ((CanTypeAllEntity)var3.get(0)).getCar_category(), ((CanTypeAllEntity)var3.get(0)).getCar_model(), ((CanTypeAllEntity)var3.get(0)).getYears(), ((CanTypeAllEntity)var3.get(0)).getEnglish_protocol_company(), ((CanTypeAllEntity)var3.get(0)).getEnglish_car_category(), ((CanTypeAllEntity)var3.get(0)).getEnglish_car_model(), ((CanTypeAllEntity)var3.get(0)).getEnglish_years(), ((CanTypeAllEntity)var3.get(0)).getCan_type_id(), ((CanTypeAllEntity)var3.get(0)).getCan_different_id(), ((CanTypeAllEntity)var3.get(0)).getEach_can_id(), ((CanTypeAllEntity)var3.get(0)).getBaud_rate(), ((CanTypeAllEntity)var3.get(0)).getIs_app_handle_key(), ((CanTypeAllEntity)var3.get(0)).getPack_type(), ((CanTypeAllEntity)var3.get(0)).getIs_show_app(), ((CanTypeAllEntity)var3.get(0)).getIs_use_new_camera(), ((CanTypeAllEntity)var3.get(0)).getIs_use_new_app(), ((CanTypeAllEntity)var3.get(0)).getDescription());
         var6.setDescription(this.getCanTypIdStr(var2));
         return var6;
      }
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      switch (var2) {
         case 2131361994:
            this.input(0);
            break;
         case 2131361995:
            this.input(1);
            break;
         case 2131361996:
            this.input(2);
            break;
         case 2131361997:
            this.input(3);
            break;
         case 2131361998:
            this.input(4);
            break;
         case 2131361999:
            this.input(5);
            break;
         case 2131362000:
            this.input(6);
            break;
         case 2131362001:
            this.input(7);
            break;
         case 2131362002:
            this.input(8);
            break;
         case 2131362003:
            this.input(9);
            break;
         default:
            switch (var2) {
               case 2131362019:
                  if (this.mInputContentSb != null) {
                     this.deleteInputContent();
                     this.refreshInputTv();
                  }
                  break;
               case 2131362024:
                  this.showInputIdDialog();
                  break;
               case 2131362053:
                  this.startActivity(new Intent(this, SelectIdActivity.class));
                  break;
               case 2131362056:
                  StringBuffer var6 = this.mInputContentSb;
                  if (var6 != null) {
                     try {
                        var2 = Integer.valueOf(var6.toString());
                     } catch (Exception var4) {
                        Toast.makeText(this, "Transform error", 0).show();
                        return;
                     }

                     if (var2 == 709394) {
                        boolean var3 = SharePreUtil.getBoolValue(this, "share_pre_is_debug_model", false);
                        String var7;
                        if (var3) {
                           var7 = "Close Test Model";
                        } else {
                           var7 = "Open Test Model";
                        }

                        SharePreUtil.setBoolValue(this, "share_pre_is_debug_model", true ^ var3);
                        Toast.makeText(this, var7, 0).show();
                        this.mDialog.dismiss();
                        return;
                     }

                     this.deleteInputContent();
                     this.searchInList(var2);
                  }
                  break;
               case 2131363728:
                  SystemProperties.set("com.hzbhd.upgrade.cloud", "1");
                  Intent var5 = new Intent();
                  var5.setComponent(HzbhdComponentName.CanBusOnlineUpdateActivity);
                  var5.setFlags(268435456);
                  this.startActivity(var5);
            }
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      ((MyApplication)this.getApplication()).addActivity(this);
      this.setContentView(2131558690);
      this.findViews();
      this.initViews();
      this.initAnimationParams();
   }

   public void showInputIdDialog() {
      View var1 = LayoutInflater.from(this).inflate(2131558756, (ViewGroup)null);
      this.mInputIdTv = (TextView)var1.findViewById(2131363629);
      Button var11 = (Button)var1.findViewById(2131361994);
      Button var3 = (Button)var1.findViewById(2131361995);
      Button var12 = (Button)var1.findViewById(2131361996);
      Button var7 = (Button)var1.findViewById(2131361997);
      Button var5 = (Button)var1.findViewById(2131361998);
      Button var8 = (Button)var1.findViewById(2131361999);
      Button var10 = (Button)var1.findViewById(2131362000);
      Button var4 = (Button)var1.findViewById(2131362001);
      Button var9 = (Button)var1.findViewById(2131362002);
      Button var13 = (Button)var1.findViewById(2131362003);
      Button var2 = (Button)var1.findViewById(2131362019);
      Button var6 = (Button)var1.findViewById(2131362056);
      var11.setOnClickListener(this);
      var3.setOnClickListener(this);
      var12.setOnClickListener(this);
      var7.setOnClickListener(this);
      var5.setOnClickListener(this);
      var8.setOnClickListener(this);
      var10.setOnClickListener(this);
      var4.setOnClickListener(this);
      var9.setOnClickListener(this);
      var13.setOnClickListener(this);
      var2.setOnClickListener(this);
      var6.setOnClickListener(this);
      Dialog var14 = new Dialog(this, 2131820753);
      this.mDialog = var14;
      var14.setContentView(var1);
      this.mDialog.show();
      this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener(this) {
         final SelectCanTypeActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onDismiss(DialogInterface var1) {
            this.this$0.deleteInputContent();
         }
      });
   }
}
