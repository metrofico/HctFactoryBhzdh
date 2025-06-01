package com.hzbhd.canbus.entity;

import com.android.internal.util.ArrayUtils;
import java.util.Arrays;
import java.util.List;

public class TireUpdateEntity {
   public static final int NORMAL = 0;
   public static final int WARMING = 1;
   private List list;
   private int tireStatus;
   private int whichTire;

   public TireUpdateEntity() {
   }

   public TireUpdateEntity(int var1, int var2, String[] var3) {
      this.whichTire = var1;
      this.tireStatus = var2;
      if (!ArrayUtils.isEmpty(var3)) {
         this.list = Arrays.asList(var3);
      }

   }

   public List getList() {
      return this.list;
   }

   public int getTireStatus() {
      return this.tireStatus;
   }

   public int getWhichTire() {
      return this.whichTire;
   }

   public TireUpdateEntity setList(List var1) {
      this.list = var1;
      return this;
   }

   public void setTireStatus(int var1) {
      this.tireStatus = var1;
   }

   public void setWhichTire(int var1) {
      this.whichTire = var1;
   }
}
