package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import java.util.Arrays;
import java.util.List;

public class DriverDataPageUiSet {
   private String buttonText;
   private boolean isHaveBtn = false;
   private int leftPosition;
   private List list;
   private OnDriveDataPageStatusListener onDriveDataPageStatusListener;
   private int rightPosition;

   public String getButtonText() {
      return this.buttonText;
   }

   public int getLeftPosition() {
      return this.leftPosition;
   }

   public List getList() {
      return this.list;
   }

   public OnDriveDataPageStatusListener getOnDriveDataPageStatusListener() {
      return this.onDriveDataPageStatusListener;
   }

   public int getRightPosition() {
      return this.rightPosition;
   }

   public boolean isHaveBtn() {
      return this.isHaveBtn;
   }

   public void setButtonText(String var1) {
      this.buttonText = var1;
   }

   public void setHaveBtn(boolean var1) {
      this.isHaveBtn = var1;
   }

   public void setLeftPosition(int var1) {
      this.leftPosition = var1;
   }

   public void setList(List var1) {
      this.list = var1;
   }

   public void setOnDriveDataPageStatusListener(OnDriveDataPageStatusListener var1) {
      this.onDriveDataPageStatusListener = var1;
   }

   public void setRightPosition(int var1) {
      this.rightPosition = var1;
   }

   public static class Page {
      private String headTitleSrn;
      private List itemList;
      private int spanCount;

      public Page(String var1, int var2, Item[] var3) {
         this.headTitleSrn = var1;
         this.itemList = Arrays.asList(var3);
         this.spanCount = var2;
      }

      public String getHeadTitleSrn() {
         return this.headTitleSrn;
      }

      public List getItemList() {
         return this.itemList;
      }

      public int getSpanCount() {
         return this.spanCount;
      }

      public void setHeadTitleSrn(String var1) {
         this.headTitleSrn = var1;
      }

      public void setItemList(List var1) {
         this.itemList = var1;
      }

      public void setSpanCount(int var1) {
         this.spanCount = var1;
      }

      public static class Item {
         private String defaultValueSrn = "null_value";
         private boolean isTitleStr = false;
         private String titleSrn;
         private String value;

         public String getDefaultValueSrn() {
            return this.defaultValueSrn;
         }

         public String getTitleSrn() {
            return this.titleSrn;
         }

         public String getValue() {
            return this.value;
         }

         public boolean isTitleStr() {
            return this.isTitleStr;
         }

         public void setDefaultValueSrn(String var1) {
            this.defaultValueSrn = var1;
         }

         public void setTitleSrn(String var1) {
            this.titleSrn = var1;
         }

         public void setTitleStr(boolean var1) {
            this.isTitleStr = var1;
         }

         public void setValue(String var1) {
            this.value = var1;
         }
      }
   }
}
