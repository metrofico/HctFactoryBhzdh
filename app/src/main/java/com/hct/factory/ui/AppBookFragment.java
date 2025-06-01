package com.hct.factory.ui;

import android.app.Activity;
import android.microntek.CarManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.hct.RkSystemProp;
import com.hct.factory.FactoryFun;
import com.hct.factory.Factory_Util;
import com.hct.factory.Hct_Config;
import java.util.HashMap;

public class AppBookFragment extends Fragment {
   private HashMap DABList = new HashMap();
   private HashMap TVList = new HashMap();
   private Spinner avingroup;
   private Spinner btgroup;
   private Spinner dabgroup;
   private Spinner dtvgroup;
   private Spinner dvdgroup;
   private Spinner dvrroup;
   private Spinner hdmigroup;
   private LinearLayout ipodView;
   private Spinner ipodgroup;
   private CarManager mCarManager = null;
   private Spinner mCarplayGroup;
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private Spinner mEasyConnectionGroup;
   private Hct_Config.ST_FACTORY_EXT_CONFIG mExtConfig = null;
   private FactoryFun mFactoryFun = null;
   private Spinner mHiCarGroup;
   private Spinner radiogroup;
   private Spinner tpmsroup;

   private void InitAppBook() {
      ArrayAdapter var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.bluetoothtype);
      var2.setDropDownViewResource(17367049);
      this.btgroup.setAdapter(var2);
      if (this.mConfig.mBt >= Factory_Util.bluetoothtype.length) {
         this.mConfig.mBt = 0;
      }

      this.btgroup.setSelection(this.mConfig.mBt);
      this.btgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mBt = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (Factory_Util.isSofia()) {
         this.btgroup.setEnabled(false);
      }

      String var4 = this.getParameters("sta_mfi=");

      int var1;
      try {
         var1 = Integer.parseInt(var4);
      } catch (Exception var3) {
         var1 = 0;
      }

      if (var1 > 0) {
         this.ipodView.setVisibility(8);
      }

      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.ipodtype);
      var2.setDropDownViewResource(17367049);
      this.ipodgroup.setAdapter(var2);
      if (this.mConfig.mIpod > Factory_Util.ipodtype.length) {
         this.mConfig.mIpod = 0;
      }

      this.ipodgroup.setSelection(this.mConfig.mIpod);
      this.ipodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mIpod = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.dvdtype);
      var2.setDropDownViewResource(17367049);
      this.dvdgroup.setAdapter(var2);
      if (this.mConfig.mDvd > Factory_Util.dvdtype.length) {
         this.mConfig.mDvd = 0;
      }

      this.dvdgroup.setSelection(this.mConfig.mDvd);
      this.dvdgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mDvd = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (Factory_Util.isSofia()) {
         this.dvdgroup.setEnabled(false);
      }

      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.radiotype);
      var2.setDropDownViewResource(17367049);
      this.radiogroup.setAdapter(var2);
      if (this.mConfig.mRadio > Factory_Util.radiotype.length) {
         this.mConfig.mRadio = 0;
      }

      this.radiogroup.setSelection(this.mConfig.mRadio);
      this.radiogroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mRadio = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.dvrtype);
      var2.setDropDownViewResource(17367049);
      this.dvrroup.setAdapter(var2);
      if (this.mConfig.mDvr > Factory_Util.dvrtype.length) {
         this.mConfig.mDvr = 0;
      }

      this.dvrroup.setSelection(this.mConfig.mDvr);
      this.dvrroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mDvr = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (RkSystemProp.get("ro.product.customer", "HCT").equals("RM")) {
         Factory_Util.tpmstype[4] = "TPMS_04 RM";
      }

      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.tpmstype);
      var2.setDropDownViewResource(17367049);
      this.tpmsroup.setAdapter(var2);
      if (this.mConfig.mTpms >= Factory_Util.tpmstype.length) {
         this.mConfig.mTpms = 0;
      }

      this.tpmsroup.setSelection(this.mConfig.mTpms);
      this.tpmsroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mTpms = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.carplaytype);
      var2.setDropDownViewResource(17367049);
      this.mCarplayGroup.setAdapter(var2);
      if (this.mConfig.mCarPlay > Factory_Util.carplaytype.length) {
         this.mConfig.mCarPlay = 0;
      }

      this.mCarplayGroup.setSelection(this.mConfig.mCarPlay);
      this.mCarplayGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mCarPlay = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.hicartype);
      var2.setDropDownViewResource(17367049);
      this.mHiCarGroup.setAdapter(var2);
      if (this.mExtConfig.mHiCar > Factory_Util.hicartype.length) {
         this.mExtConfig.mHiCar = 0;
      }

      this.mHiCarGroup.setSelection(this.mExtConfig.mHiCar);
      this.mHiCarGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mExtConfig.mHiCar = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.easyconnectiontype);
      var2.setDropDownViewResource(17367049);
      this.mEasyConnectionGroup.setAdapter(var2);
      if (this.mExtConfig.mEasyConnection > Factory_Util.easyconnectiontype.length) {
         this.mExtConfig.mEasyConnection = 0;
      }

      this.mEasyConnectionGroup.setSelection(this.mExtConfig.mEasyConnection);
      this.mEasyConnectionGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mExtConfig.mEasyConnection = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.hdmitype);
      var2.setDropDownViewResource(17367049);
      this.hdmigroup.setAdapter(var2);
      if (this.mConfig.mHdmi >= Factory_Util.hdmitype.length) {
         this.mConfig.mHdmi = 0;
      }

      this.hdmigroup.setSelection(this.mConfig.mHdmi);
      this.hdmigroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mHdmi = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.avintype);
      var2.setDropDownViewResource(17367049);
      this.avingroup.setAdapter(var2);
      if (this.mConfig.mAvin > Factory_Util.avintype.length) {
         this.mConfig.mAvin = 0;
      }

      this.avingroup.setSelection(this.mConfig.mAvin);
      this.avingroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mAvin = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mFactoryFun.getParameters("sta_function=30").equals("1")) {
         this.getActivity().findViewById(2131099772).setVisibility(0);
      }

      if (this.TVList.size() != 0) {
         this.InitTVList();
      }

      if (this.DABList.size() != 0) {
         this.InitDABList();
      }

   }

   private void InitDABList() {
      // $FF: Couldn't be decompiled
   }

   private void InitTVList() {
      // $FF: Couldn't be decompiled
   }

   private String getParameters(String var1) {
      CarManager var2 = this.mCarManager;
      return var2 != null ? var2.getParameters(var1) : null;
   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      this.mConfig = this.mFactoryFun.getFactoryConfig();
      this.mExtConfig = this.mFactoryFun.getFactoryExtConfig();
      this.InitAppBook();
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
      this.mCarManager = new CarManager();
      this.mCarManager.attach(new Handler(this) {
         final AppBookFragment this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            int var2;
            int var3;
            Bundle var4;
            String var5;
            if ("Tv".equals((String)var1.obj)) {
               var4 = var1.getData();
               if ("list".equals(var4.getString("type"))) {
                  var2 = var4.getInt("count");
                  var3 = var4.getInt("index");
                  var5 = var4.getString("item");
                  if (var3 == 0) {
                     this.this$0.TVList.clear();
                  }

                  this.this$0.TVList.put(var3, var5);
                  if (var2 == var3 + 1) {
                     this.this$0.InitTVList();
                  }
               }
            } else if ("Dab".equals((String)var1.obj)) {
               var4 = var1.getData();
               if ("list".equals(var4.getString("type"))) {
                  var2 = var4.getInt("count");
                  var3 = var4.getInt("index");
                  var5 = var4.getString("item");
                  if (var3 == 0) {
                     this.this$0.DABList.clear();
                  }

                  this.this$0.DABList.put(var3, var5);
                  if (var2 == var3 + 1) {
                     this.this$0.InitDABList();
                  }
               }
            }

         }
      }, "Tv,Dab");
      this.mCarManager.setParameters("ctl_dtv_list=");
      this.mCarManager.setParameters("ctl_dab_list=");
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      View var4 = var1.inflate(2131230728, var2, false);
      this.btgroup = (Spinner)var4.findViewById(2131099695);
      this.dtvgroup = (Spinner)var4.findViewById(2131099749);
      this.dvdgroup = (Spinner)var4.findViewById(2131099752);
      this.ipodView = (LinearLayout)var4.findViewById(2131099810);
      this.ipodgroup = (Spinner)var4.findViewById(2131099786);
      this.radiogroup = (Spinner)var4.findViewById(2131099832);
      this.dvrroup = (Spinner)var4.findViewById(2131099754);
      this.tpmsroup = (Spinner)var4.findViewById(2131099931);
      this.hdmigroup = (Spinner)var4.findViewById(2131099773);
      this.avingroup = (Spinner)var4.findViewById(2131099669);
      this.dabgroup = (Spinner)var4.findViewById(2131099742);
      this.mCarplayGroup = (Spinner)var4.findViewById(2131099729);
      this.mHiCarGroup = (Spinner)var4.findViewById(2131099774);
      this.mEasyConnectionGroup = (Spinner)var4.findViewById(2131099755);
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
