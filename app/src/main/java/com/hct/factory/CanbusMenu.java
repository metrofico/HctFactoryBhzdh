package com.hct.factory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CanbusMenu extends Activity {
   private static final String[] CAN_FACTORY = new String[]{"All", "Simple", "Raise", "Union", "Bagoo", "Hiworld", "(LZ)", "XinBaSi", "ChangYuanTong", "BiNaRui", "DaoJun", "Pengpai", "OuDi", "TangDu", "XinFeiYang", "Other"};
   private static final int[] CAN_FACTORY_STRING = new int[]{2131296301, 2131296312, 2131296311, 2131296314, 2131296302, 2131296307, 2131296308, 2131296315, 2131296304, 2131296303, 2131296305, 2131296310, 2131296309, 2131296313, 2131296316, 2131296433};
   private static final String[] CAR = new String[]{"ALL", "VW", "TOYOTA", "HONDA", "MAZDA", "JEEP", "GM", "FORD", "NISSAN", "HYUNDAI", "BENZ", "BMW", "PSA", "PORSCHE", "AUDI", "MITSUBISH", "RENAULT", "ZT", "CHERY", "FIAT", "SUBURU", "KOREA", "GEELY", "HAVAL", "CHANGAN", "LI FAN", "BAOJUN", "DONGFENG", "ROEWE", "GAC", "JAC", "BYD", "LEXUS", "GREATWALL", "ISUZU", "Other"};
   private static final String CAR_ALL = "ALL";
   private static final String CAR_AUDI = "AUDI";
   private static final String CAR_BAOJUN = "BAOJUN";
   private static final String CAR_BENZ = "BENZ";
   private static final String CAR_BMW = "BMW";
   private static final String CAR_BYD = "BYD";
   private static final String CAR_CHANGAN = "CHANGAN";
   private static final String CAR_CHERY = "CHERY";
   private static final String CAR_DONGFENG = "DONGFENG";
   private static final String CAR_FIAT = "FIAT";
   private static final String CAR_FORD = "FORD";
   private static final String CAR_GAC = "GAC";
   private static final String CAR_GEELY = "GEELY";
   private static final String CAR_GM = "GM";
   private static final String CAR_GREATWALL = "GREATWALL";
   private static final String CAR_HAVAL = "HAVAL";
   private static final String CAR_HONDA = "HONDA";
   private static final String CAR_HYUNDAI = "HYUNDAI";
   private static final String CAR_ISUZU = "ISUZU";
   private static final String CAR_JAC = "JAC";
   private static final String CAR_JEEP = "JEEP";
   private static final String CAR_KOREA = "KOREA";
   private static final String CAR_LEXUS = "LEXUS";
   private static final String CAR_LI_FAN = "LI FAN";
   private static final String CAR_MAZDA = "MAZDA";
   private static final String CAR_MITSUBISH = "MITSUBISH";
   private static final String CAR_NISSAN = "NISSAN";
   private static final String CAR_OTHER = "Other";
   private static final String CAR_PORSCHE = "PORSCHE";
   private static final String CAR_PSA = "PSA";
   private static final String CAR_RENAULT = "RENAULT";
   private static final String CAR_ROEWE = "ROEWE";
   private static final int[] CAR_STRING = new int[]{2131296301, 2131296353, 2131296350, 2131296333, 2131296341, 2131296337, 2131296330, 2131296327, 2131296344, 2131296334, 2131296320, 2131296321, 2131296346, 2131296345, 2131296317, 2131296342, 2131296347, 2131296354, 2131296324, 2131296326, 2131296349, 2131296338, 2131296329, 2131296332, 2131296323, 2131296340, 2131296319, 2131296325, 2131296348, 2131296328, 2131296336, 2131296322, 2131296339, 2131296331, 2131296335, 2131296433};
   private static final String CAR_SUBURU = "SUBURU";
   private static final String CAR_TOYOTA = "TOYOTA";
   private static final String CAR_VW = "VW";
   private static final String CAR_ZT = "ZT";
   private static String TAG = "CanbusMenu";
   private HashMap CanBusMap;
   private MyAdapter listAdapter;
   private String[] mCanBusInfoList = null;
   private String[] mCanBusTypeList = null;
   private List mCanList = new ArrayList();
   private int mCanbus = 0;
   private Button mCanbus_car;
   private Button mCanbus_car_type;
   private Button mCanbus_factory;
   private Button mCanbus_ok;
   private TextView mCurrent_canbus;
   private List mListData = new ArrayList();
   private ListView mListView;
   private int mSelectMode = 0;

   private void addCanbusTable(int var1, String var2, String var3) {
      StringBuilder var5 = null;
      Object var4 = null;
      String var8 = this.getCar(var3);
      String var6 = this.getCanbusType(var1, var3);
      String var7 = this.getCanbusInfo(var1);
      CanListMode var10;
      CanListMode var11;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 12) {
               if (var1 != 13) {
                  if (var1 != 63) {
                     if (var1 != 64) {
                        if (var1 != 70) {
                           if (var1 != 71) {
                              if (var1 != 92) {
                                 if (var1 != 93) {
                                    switch (var1) {
                                       case 8:
                                          var10 = new CanListMode(this, var1, var2, "TOYOTA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 15:
                                          var10 = new CanListMode(this, var1, var2, "KOREA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 17:
                                          var10 = new CanListMode(this, var1, var2, "VW", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 31:
                                          var10 = new CanListMode(this, var1, var2, "KOREA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 33:
                                          var10 = new CanListMode(this, var1, "Raise", "TOYOTA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 35:
                                          var10 = new CanListMode(this, var1, var2, "VW", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 39:
                                          var10 = new CanListMode(this, var1, var2, "BMW", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 48:
                                          var10 = new CanListMode(this, var1, var2, "GEELY", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 50:
                                          var10 = new CanListMode(this, var1, var2, "PSA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 76:
                                          var10 = new CanListMode(this, var1, var2, "HONDA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 84:
                                          var10 = new CanListMode(this, var1, var2, "HONDA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 102:
                                          var10 = new CanListMode(this, var1, var2, "DONGFENG", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 108:
                                          var10 = new CanListMode(this, var1, var2, "FORD", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 112:
                                          var10 = new CanListMode(this, var1, var2, "GM", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 116:
                                          var10 = new CanListMode(this, var1, var2, "HONDA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 118:
                                          var10 = new CanListMode(this, var1, var2, "FORD", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 123:
                                          var10 = new CanListMode(this, var1, var2, "HONDA", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       case 137:
                                          var10 = new CanListMode(this, var1, var2, "PORSCHE", var6, var7);
                                          var11 = (CanListMode)var4;
                                          break;
                                       default:
                                          if (var8.equals("Other")) {
                                             String var9 = TAG;
                                             var5 = new StringBuilder();
                                             var5.append("meng canbus:");
                                             var5.append(var1);
                                             var5.append(" factory:");
                                             var5.append(var2);
                                             var5.append(" name:");
                                             var5.append(var3);
                                             Log.i(var9, var5.toString());
                                          }

                                          var10 = new CanListMode(this, var1, var2, var8, var6, var7);
                                          var11 = (CanListMode)var4;
                                    }
                                 } else {
                                    var10 = new CanListMode(this, var1, var2, "DONGFENG", var6, var7);
                                    var11 = (CanListMode)var4;
                                 }
                              } else {
                                 var10 = new CanListMode(this, var1, var2, "FORD", var6, var7);
                                 var11 = (CanListMode)var4;
                              }
                           } else {
                              var10 = new CanListMode(this, var1, var2, "HONDA", var6, var7);
                              var11 = (CanListMode)var4;
                           }
                        } else {
                           var10 = new CanListMode(this, var1, var2, "KOREA", var6, var7);
                           var11 = (CanListMode)var4;
                        }
                     } else {
                        var10 = new CanListMode(this, var1, var2, "HONDA", var6, var7);
                        var11 = (CanListMode)var4;
                     }
                  } else {
                     var10 = new CanListMode(this, var1, var2, "GAC", var6, var7);
                     var11 = (CanListMode)var4;
                  }
               } else {
                  var10 = new CanListMode(this, var1, var2, "GM", var6, var7);
                  var11 = (CanListMode)var4;
               }
            } else {
               var10 = new CanListMode(this, var1, "Simple", "GM", var6, var7);
               var11 = (CanListMode)var4;
            }
         } else {
            var10 = new CanListMode(this, var1, var2, "VW", var6, var7);
            var11 = new CanListMode(this, var1, "Raise", "VW", var6, (String)null);
         }
      } else {
         var11 = (CanListMode)var4;
         var10 = var5;
      }

      if (var10 != null) {
         this.mCanList.add(var10);
      }

      if (var11 != null) {
         this.mCanList.add(var11);
      }

   }

   private String getCanbusInfo(int var1) {
      String[] var2 = this.mCanBusInfoList;
      return var1 < var2.length ? var2[var1] : null;
   }

   private String getCanbusType(int var1, String var2) {
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

   private String getCar(String var1) {
      if (!TextUtils.isEmpty(var1) && var1.contains(")")) {
         var1 = var1.toUpperCase();
         if (var1.contains("FOCUS") || var1.contains("FORD")) {
            return "FORD";
         }

         if (var1.contains("RENAUL")) {
            return "RENAULT";
         }

         if (var1.contains("GREATWALL")) {
            return "GREATWALL";
         }

         int var2 = 0;

         while(true) {
            String[] var3 = CAR;
            if (var2 >= var3.length) {
               break;
            }

            if (var1.contains(var3[var2])) {
               return CAR[var2];
            }

            ++var2;
         }
      }

      return "Other";
   }

   private int getCarIndex(String var1) {
      int var2 = 0;

      while(true) {
         String[] var3 = CAR;
         if (var2 >= var3.length) {
            int[] var4 = CAR_STRING;
            return var4[var4.length - 1];
         }

         if (var1.equals(var3[var2])) {
            return var2;
         }

         ++var2;
      }
   }

   private String getFactory(String var1) {
      if (!TextUtils.isEmpty(var1) && var1.contains(")")) {
         if (var1.contains("BiNaRui") || var1.contains("BiRuiNa")) {
            return "BiNaRui";
         }

         if (var1.contains("OoDi")) {
            return "OuDi";
         }

         int var2 = 0;

         while(true) {
            String[] var3 = CAN_FACTORY;
            if (var2 >= var3.length) {
               break;
            }

            if (var1.contains(var3[var2])) {
               return CAN_FACTORY[var2];
            }

            ++var2;
         }
      }

      return "Other";
   }

   private void updateCarList(String var1) {
      this.mListData.clear();
      ArrayList var4 = new ArrayList();
      Iterator var5 = this.mCanList.iterator();

      while(var5.hasNext()) {
         CanListMode var6 = (CanListMode)var5.next();
         if (var1.equals("All")) {
            if (!var4.contains(var6.mCar)) {
               var4.add(var6.mCar);
            }
         } else if (var1.equals(var6.mCanFactroy) && !var4.contains(var6.mCar)) {
            var4.add(var6.mCar);
         }
      }

      this.mListData.add(new ListData(this, this.getString(CAR_STRING[0]), (String)null, "ALL"));
      boolean var3 = false;

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         if (((String)var4.get(var2)).equals("Other")) {
            var3 = true;
         } else {
            this.mListData.add(new ListData(this, this.getString(CAR_STRING[this.getCarIndex((String)var4.get(var2))]), (String)null, var4.get(var2)));
         }
      }

      if (var3) {
         this.mListData.add(new ListData(this, this.getString(CAR_STRING[this.getCarIndex("Other")]).toUpperCase(), (String)null, "Other"));
      }

      MyAdapter var7 = this.listAdapter;
      if (var7 != null) {
         var7.notifyDataSetChanged();
      }

   }

   private void updateCarTypeList(String var1, String var2) {
      this.mListData.clear();
      Iterator var3 = this.mCanList.iterator();

      while(true) {
         while(var3.hasNext()) {
            CanListMode var4 = (CanListMode)var3.next();
            if (var1.equals("All") && var2.equals("ALL")) {
               this.mListData.add(new ListData(this, var4.mCarType, var4.mCarInfo, var4));
            } else if (var1.equals("All") && var2.equals(var4.mCar)) {
               this.mListData.add(new ListData(this, var4.mCarType, var4.mCarInfo, var4));
            } else if (var1.equals(var4.mCanFactroy) && var2.equals(var4.mCar)) {
               this.mListData.add(new ListData(this, var4.mCarType, var4.mCarInfo, var4));
            } else if (var1.equals(var4.mCanFactroy) && var2.equals("ALL")) {
               this.mListData.add(new ListData(this, var4.mCarType, var4.mCarInfo, var4));
            }
         }

         this.listAdapter.notifyDataSetChanged();
         return;
      }
   }

   private void updateFactoryList() {
      this.mListData.clear();

      for(int var1 = 0; var1 < CAN_FACTORY.length; ++var1) {
         this.mListData.add(new ListData(this, this.getString(CAN_FACTORY_STRING[var1]), (String)null, var1));
      }

      MyAdapter var2 = this.listAdapter;
      if (var2 != null) {
         var2.notifyDataSetChanged();
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230722);
      this.mCanBusTypeList = this.getResources().getStringArray(2130771969);
      this.mCanBusInfoList = this.getResources().getStringArray(2130771968);
      this.mCanbus = this.getIntent().getIntExtra("CanBus", 0);
      this.CanBusMap = (HashMap)this.getIntent().getSerializableExtra("CanBusList");
      HashMap var4 = this.CanBusMap;
      if (var4 != null && var4.size() > 0) {
         this.mCurrent_canbus = (TextView)this.findViewById(2131099738);
         this.mListView = (ListView)this.findViewById(2131099793);
         this.mCanbus_factory = (Button)this.findViewById(2131099701);
         this.mCanbus_car = (Button)this.findViewById(2131099699);
         this.mCanbus_car_type = (Button)this.findViewById(2131099700);
         this.mCanbus_ok = (Button)this.findViewById(2131099703);
         TextView var3 = this.mCurrent_canbus;
         StringBuilder var5 = new StringBuilder();
         var5.append(this.getString(2131296358));
         int var2 = this.mCanbus;
         var5.append(this.getCanbusType(var2, (String)this.CanBusMap.get(var2)));
         var3.setText(var5.toString());
         this.mCanbus_factory.setOnClickListener(new View.OnClickListener(this) {
            final CanbusMenu this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mSelectMode = 0;
               this.this$0.updateFactoryList();
               this.this$0.mCanbus_factory.setText(2131296286);
               this.this$0.mCanbus_car.setText(2131296284);
               this.this$0.mCanbus_car_type.setText(2131296285);
               this.this$0.mCanbus_car.setEnabled(false);
               this.this$0.mCanbus_car_type.setEnabled(false);
               this.this$0.mCanbus_ok.setText(17039360);
            }
         });
         this.mCanbus_car.setOnClickListener(new View.OnClickListener(this) {
            final CanbusMenu this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mSelectMode = 1;
               String var2 = (String)this.this$0.mCanbus_factory.getTag();
               this.this$0.updateCarList(var2);
               this.this$0.mCanbus_car.setText(2131296284);
               this.this$0.mCanbus_car_type.setText(2131296285);
               this.this$0.mCanbus_car.setEnabled(true);
               this.this$0.mCanbus_car_type.setEnabled(false);
               this.this$0.mCanbus_ok.setText(17039360);
            }
         });
         this.mCanbus_car_type.setOnClickListener(new View.OnClickListener(this) {
            final CanbusMenu this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mSelectMode = 2;
               String var3 = (String)this.this$0.mCanbus_car.getTag();
               String var2 = (String)this.this$0.mCanbus_factory.getTag();
               this.this$0.updateCarTypeList(var2, var3);
               this.this$0.mCanbus_car_type.setText(2131296285);
               this.this$0.mCanbus_car.setEnabled(true);
               this.this$0.mCanbus_car_type.setEnabled(true);
               this.this$0.mCanbus_ok.setText(17039360);
            }
         });
         this.mCanbus_ok.setOnClickListener(new View.OnClickListener(this) {
            final CanbusMenu this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               if (this.this$0.mSelectMode == 3) {
                  CanListMode var2 = (CanListMode)this.this$0.mCanbus_car_type.getTag();
                  Intent var3 = new Intent();
                  var3.putExtra("Canbus", var2.mCanbus);
                  this.this$0.setResult(1, var3);
               }

               this.this$0.finish();
            }
         });

         for(var2 = 0; var2 < this.CanBusMap.size(); ++var2) {
            this.addCanbusTable(var2, this.getFactory((String)this.CanBusMap.get(var2)), (String)this.CanBusMap.get(var2));
         }

         this.updateFactoryList();
         this.listAdapter = new MyAdapter(this, this, this.mListData);
         this.mListView.setAdapter(this.listAdapter);
         this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(this) {
            final CanbusMenu this$0;

            {
               this.this$0 = var1;
            }

            public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
               String var6;
               if (this.this$0.mSelectMode == 0) {
                  this.this$0.mSelectMode = 1;
                  var3 = (Integer)var2.getTag();
                  var6 = this.this$0.getString(CanbusMenu.CAN_FACTORY_STRING[var3]);
                  this.this$0.mCanbus_factory.setText(var6);
                  this.this$0.mCanbus_factory.setTag(CanbusMenu.CAN_FACTORY[var3]);
                  this.this$0.updateCarList(CanbusMenu.CAN_FACTORY[var3]);
                  this.this$0.mCanbus_car.setEnabled(true);
                  this.this$0.mCanbus_car_type.setEnabled(false);
                  this.this$0.mCanbus_ok.setText(17039360);
               } else if (this.this$0.mSelectMode == 1) {
                  this.this$0.mSelectMode = 2;
                  String var8 = (String)var2.getTag();
                  var6 = (String)this.this$0.mCanbus_factory.getTag();
                  this.this$0.updateCarTypeList(var6, var8);
                  this.this$0.mCanbus_car.setText(this.this$0.getString(CanbusMenu.CAR_STRING[this.this$0.getCarIndex(var8)]));
                  this.this$0.mCanbus_car.setTag(var8);
                  this.this$0.mCanbus_car.setEnabled(true);
                  this.this$0.mCanbus_car_type.setEnabled(true);
                  this.this$0.mCanbus_ok.setText(17039360);
               } else if (this.this$0.mSelectMode >= 2) {
                  this.this$0.mSelectMode = 3;
                  CanListMode var7 = (CanListMode)var2.getTag();
                  this.this$0.mCanbus_car_type.setText(var7.mCarType);
                  this.this$0.mCanbus_car_type.setTag(var7);
                  this.this$0.mCanbus_car.setEnabled(true);
                  this.this$0.mCanbus_car_type.setEnabled(true);
                  this.this$0.mCanbus_ok.setEnabled(true);
                  this.this$0.mCanbus_ok.setText(17039370);
               }

            }
         });
      }
   }

   public class CanListMode {
      private String mCanFactroy;
      private int mCanbus;
      private String mCar;
      private String mCarInfo;
      private String mCarType;
      final CanbusMenu this$0;

      public CanListMode(CanbusMenu var1, int var2, String var3, String var4, String var5, String var6) {
         this.this$0 = var1;
         this.mCanbus = var2;
         this.mCanFactroy = var3;
         this.mCar = var4;
         this.mCarType = var5;
         this.mCarInfo = var6;
      }
   }

   public class ListData {
      private Object mObject;
      private String mText1;
      private String mText2;
      final CanbusMenu this$0;

      public ListData(CanbusMenu var1, String var2, String var3, Object var4) {
         this.this$0 = var1;
         this.mText1 = var2;
         this.mText2 = var3;
         this.mObject = var4;
      }
   }

   private class MyAdapter extends BaseAdapter {
      private Context mContext;
      private LayoutInflater mLayoutInflater;
      private List mList;
      final CanbusMenu this$0;

      public MyAdapter(CanbusMenu var1, Context var2, List var3) {
         this.this$0 = var1;
         this.mList = var3;
         this.mContext = var2;
         this.mLayoutInflater = LayoutInflater.from(var2);
      }

      public int getCount() {
         return this.mList.size();
      }

      public Object getItem(int var1) {
         return this.mList.get(var1);
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public View getView(int var1, View var2, ViewGroup var3) {
         var2 = this.mLayoutInflater.inflate(2131230723, (ViewGroup)null);
         TextView var4 = (TextView)var2.findViewById(2131099874);
         TextView var6 = (TextView)var2.findViewById(2131099875);
         var4.setText(((ListData)this.mList.get(var1)).mText1);
         var2.setTag(((ListData)this.mList.get(var1)).mObject);
         String var5 = ((ListData)this.mList.get(var1)).mText2;
         ImageView var7 = (ImageView)var2.findViewById(2131099782);
         if (!TextUtils.isEmpty(var5)) {
            var7.setOnClickListener(new View.OnClickListener(this, var6, var7) {
               final MyAdapter this$1;
               final ImageView val$btn;
               final TextView val$tv2;

               {
                  this.this$1 = var1;
                  this.val$tv2 = var2;
                  this.val$btn = var3;
               }

               public void onClick(View var1) {
                  if (this.val$tv2.getVisibility() == 0) {
                     this.val$tv2.setVisibility(8);
                     this.val$btn.setImageResource(2131034215);
                  } else {
                     this.val$btn.setImageResource(2131034216);
                     this.val$tv2.setVisibility(0);
                  }

               }
            });
            var6.setText(var5);
            var7.setVisibility(0);
         } else {
            var6.setVisibility(8);
            var7.setVisibility(8);
         }

         return var2;
      }
   }
}
