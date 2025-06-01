package com.hct.factory.ui;

import android.app.Activity;
import android.content.Intent;
import android.microntek.CarManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.hct.factory.CanbusMenu;
import com.hct.factory.FactoryFun;
import com.hct.factory.Factory_Util;
import com.hct.factory.Hct_Config;
import java.util.HashMap;

public class CanBusFragment extends Fragment {
   private HashMap CanBusList = new HashMap();
   private HashMap CanbusCustomer;
   private HashMap SecondList = new HashMap();
   private int canFactorySelecte;
   private Spinner canbusFactory;
   boolean canbusListInitEnd;
   private Spinner canbusgroup;
   private String[] mCanBusTypeList;
   private String[] mCanBusTypeMcuList;
   private CarManager mCarManager;
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private int mCurIndex;
   private FactoryFun mFactoryFun = null;
   private String[] mFactorySearch;
   private Spinner[] mItemGroup;
   private TextView[] mItemText;

   public CanBusFragment() {
      this.mItemGroup = new Spinner[Factory_Util.canbussecond.length];
      this.mItemText = new TextView[Factory_Util.canbussecond.length];
      this.mCanBusTypeList = null;
      this.mCanBusTypeMcuList = null;
      this.mCarManager = null;
      this.CanbusCustomer = new HashMap();
      this.canFactorySelecte = 0;
      this.mFactorySearch = new String[]{"all", "Simple", "Raise", "Union", "Bagoo", "Hiworld", "LZ", "Other"};
      this.canbusListInitEnd = false;
      this.mCurIndex = 0;
   }

   private void InitList(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   private void InitSecondList() {
      // $FF: Couldn't be decompiled
   }

   private String[] getCanFactoryList() {
      return new String[]{this.getString(2131296301), this.getString(2131296312), this.getString(2131296311), this.getString(2131296314), this.getString(2131296302), this.getString(2131296307), this.getString(2131296308)};
   }

   private String getCanbusListOSD(int var1, String var2) {
      String[] var3 = this.mCanBusTypeList;
      if (var1 < var3.length) {
         return var3[var1];
      } else {
         if (var2 == null) {
            var2 = "";
         }

         return var2;
      }
   }

   public void CanListTask(String var1, Bundle var2) {
      int var3;
      int var4;
      if ("list".equals(var1)) {
         int var5 = var2.getInt("count");
         var4 = var2.getInt("index");
         String var9 = var2.getString("item");

         for(var3 = this.mCurIndex + 1; var3 < var4; ++var3) {
            String[] var7 = this.mCanBusTypeMcuList;
            StringBuilder var8;
            if (var3 < var7.length) {
               var1 = var7[var3];
            } else {
               var8 = new StringBuilder();
               var8.append(var3);
               var8.append("");
               var1 = var8.toString();
            }

            this.CanBusList.put(var3, var1);
            var8 = new StringBuilder();
            var8.append("CanListTask err>>>");
            var8.append(var3);
            Log.i("Factory", var8.toString());
         }

         this.mCurIndex = var4;
         String[] var6 = this.mCanBusTypeMcuList;
         var1 = var9;
         if (var4 < var6.length) {
            var1 = var6[var4];
         }

         this.CanBusList.put(var4, var1);
         if (var5 == var4 + 1) {
            this.InitList(false);
         }
      } else if ("menu_list".equals(var1)) {
         var3 = var2.getInt("menu_idx");
         var2.getInt("count");
         var4 = var2.getInt("index");
         var1 = var2.getString("item");
         Integer var10 = new Integer(var3);
         HashMap var11;
         if (this.SecondList.containsKey(var10)) {
            var11 = (HashMap)this.SecondList.get(var10);
            var11.put(var4, var1);
            this.SecondList.remove(var10);
            this.SecondList.put(var10, var11);
         } else {
            var11 = new HashMap();
            var11.put(var4, var1);
            this.SecondList.put(var10, var11);
         }
      } else if ("menu_list_end".equals(var1)) {
         this.InitSecondList();
      }

   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      ArrayAdapter var2 = new ArrayAdapter(this.getActivity(), 17367049, this.getCanFactoryList());
      var2.setDropDownViewResource(17367049);
      this.canbusFactory.setAdapter(var2);
      this.canbusFactory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanBusFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.canFactorySelecte = var3;
            if (this.this$0.canbusListInitEnd && this.this$0.CanBusList.size() >= (this.this$0.mConfig.mCanbus & 255)) {
               this.this$0.InitList(true);
            }

            this.this$0.canbusListInitEnd = true;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.CanBusList.size() != 0) {
         this.InitList(false);
      }

   }

   public void onActivityResult(int var1, int var2, Intent var3) {
      if (var2 == 1) {
         int var4 = var3.getIntExtra("Canbus", -1);
         if (var4 != -1) {
            this.canbusgroup.setSelection(var4);
            this.SecondList.clear();
            CarManager var5 = this.mCarManager;
            StringBuilder var6 = new StringBuilder();
            var6.append("ctl_canbus_menu_list=");
            var6.append(var4);
            var5.setParameters(var6.toString());
         }
      }

      super.onActivityResult(var1, var2, var3);
   }

   public void onAttach(Activity var1) {
      super.onAttach(var1);

      try {
         this.mFactoryFun = (FactoryFun)var1;
      } catch (Exception var2) {
      }

   }

   public void onCreate(@Nullable Bundle var1) {
      super.onCreate(var1);
      this.mConfig = this.mFactoryFun.getFactoryConfig();
      this.mCanBusTypeList = this.getActivity().getResources().getStringArray(2130771969);
      this.mCanBusTypeMcuList = this.getActivity().getResources().getStringArray(2130771970);
      this.mCarManager = new CarManager();
      this.mCarManager.attach(new Handler(this) {
         final CanBusFragment this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if ("CanBus".equals((String)var1.obj)) {
               Bundle var3 = var1.getData();
               String var2 = var3.getString("type");
               this.this$0.CanListTask(var2, var3);
            }

         }
      }, "CanBus");
      this.mCarManager.setParameters("ctl_canbus_list=");
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      View var4 = var1.inflate(2131230729, var2, false);
      this.mItemGroup[0] = (Spinner)var4.findViewById(2131099704);
      this.mItemGroup[1] = (Spinner)var4.findViewById(2131099705);
      this.mItemGroup[2] = (Spinner)var4.findViewById(2131099706);
      this.mItemGroup[3] = (Spinner)var4.findViewById(2131099707);
      this.mItemGroup[4] = (Spinner)var4.findViewById(2131099708);
      this.mItemGroup[5] = (Spinner)var4.findViewById(2131099709);
      this.mItemGroup[6] = (Spinner)var4.findViewById(2131099710);
      this.mItemGroup[7] = (Spinner)var4.findViewById(2131099711);
      this.mItemGroup[8] = (Spinner)var4.findViewById(2131099712);
      this.mItemText[0] = (TextView)var4.findViewById(2131099713);
      this.mItemText[1] = (TextView)var4.findViewById(2131099714);
      this.mItemText[2] = (TextView)var4.findViewById(2131099715);
      this.mItemText[3] = (TextView)var4.findViewById(2131099716);
      this.mItemText[4] = (TextView)var4.findViewById(2131099717);
      this.mItemText[5] = (TextView)var4.findViewById(2131099718);
      this.mItemText[6] = (TextView)var4.findViewById(2131099719);
      this.mItemText[7] = (TextView)var4.findViewById(2131099720);
      this.mItemText[8] = (TextView)var4.findViewById(2131099721);
      this.canbusgroup = (Spinner)var4.findViewById(2131099722);
      this.canbusFactory = (Spinner)var4.findViewById(2131099701);
      var4.findViewById(2131099702).setOnClickListener(new View.OnClickListener(this) {
         final CanBusFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.CanBusList.size() > 0) {
               Intent var2 = new Intent(this.this$0.getActivity(), CanbusMenu.class);
               var2.putExtra("CanBusList", this.this$0.CanBusList);
               var2.putExtra("CanBus", this.this$0.mConfig.mCanbus & 255);
               this.this$0.startActivityForResult(var2, 1);
            }

         }
      });
      return var4;
   }

   public void onDestroy() {
      this.mCarManager.detach();
      super.onDestroy();
   }

   public void onResume() {
      super.onResume();
   }
}
