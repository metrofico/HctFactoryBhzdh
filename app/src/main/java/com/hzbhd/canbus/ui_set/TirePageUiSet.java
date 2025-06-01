package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import java.util.Arrays;
import java.util.List;

public class TirePageUiSet {
   private boolean isHaveSpareTire = true;
   private boolean isInitResToString;
   private OnTirePageStatusListener onTirePageStatusListener;
   private List tireInfoStrList;

   public TirePageUiSet() {
   }

   public TirePageUiSet(TireItem[] var1) {
      this.tireInfoStrList = Arrays.asList(var1);
   }

   public OnTirePageStatusListener getOnTirePageStatusListener() {
      return this.onTirePageStatusListener;
   }

   public List getTireInfoStrList() {
      return this.tireInfoStrList;
   }

   public boolean isHaveSpareTire() {
      return this.isHaveSpareTire;
   }

   public boolean isInitResToString() {
      return this.isInitResToString;
   }

   public void setHaveSpareTire(boolean var1) {
      this.isHaveSpareTire = var1;
   }

   public void setInitResToString(boolean var1) {
      this.isInitResToString = var1;
   }

   public void setOnTirePageStatusListener(OnTirePageStatusListener var1) {
      this.onTirePageStatusListener = var1;
   }

   public static class LineItem {
      private String title;
      private String value;

      public String getTitle() {
         return this.title;
      }

      public String getValue() {
         return this.value;
      }

      public void setTitle(String var1) {
         this.title = var1;
      }

      public void setValue(String var1) {
         this.value = var1;
      }
   }

   public class TireItem {
      private List list;
      final TirePageUiSet this$0;
      private int whichTire;

      public TireItem(TirePageUiSet var1, int var2, LineItem[] var3) {
         this.this$0 = var1;
         this.whichTire = var2;
         this.list = Arrays.asList(var3);
      }

      public List getList() {
         return this.list;
      }

      public int getWhichTire() {
         return this.whichTire;
      }
   }
}
