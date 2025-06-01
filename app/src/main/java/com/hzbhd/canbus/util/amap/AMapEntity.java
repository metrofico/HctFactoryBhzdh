package com.hzbhd.canbus.util.amap;

import java.util.Objects;

public class AMapEntity {
   private final int allDistance;
   private final int carDirection;
   private final int destinationDistance;
   private final int icon;
   private final int nextDistance;
   private final String nextWayName;
   private final String planTime;
   private final int surplusAllTime;
   private final String surplusAllTimeStr;
   private final int type;

   public AMapEntity(int var1, int var2, int var3, int var4, int var5, int var6, int var7, String var8, String var9, String var10) {
      this.type = var1;
      this.allDistance = var2;
      this.nextDistance = var3;
      this.destinationDistance = var4;
      this.icon = var5;
      this.surplusAllTime = var6;
      this.carDirection = var7;
      this.surplusAllTimeStr = var8;
      this.planTime = var9;
      this.nextWayName = var10;
   }

   public boolean equals(Object var1) {
      boolean var2 = true;
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         AMapEntity var3 = (AMapEntity)var1;
         if (this.type != var3.type || this.allDistance != var3.allDistance || this.nextDistance != var3.nextDistance || this.destinationDistance != var3.destinationDistance || this.icon != var3.icon || this.surplusAllTime != var3.surplusAllTime || this.carDirection != var3.carDirection || !Objects.equals(this.surplusAllTimeStr, var3.surplusAllTimeStr) || !Objects.equals(this.planTime, var3.planTime) || !Objects.equals(this.nextWayName, var3.nextWayName)) {
            var2 = false;
         }

         return var2;
      } else {
         return false;
      }
   }

   public int getAllDistance() {
      return this.allDistance;
   }

   public int getCarDirection() {
      return this.carDirection;
   }

   public int getDestinationDistance() {
      return this.destinationDistance;
   }

   public int getIcon() {
      return this.icon;
   }

   public int getNextDistance() {
      return this.nextDistance;
   }

   public String getNextWayName() {
      return this.nextWayName;
   }

   public String getPlanTime() {
      return this.planTime;
   }

   public int getSurplusAllTime() {
      return this.surplusAllTime;
   }

   public String getSurplusAllTimeStr() {
      return this.surplusAllTimeStr;
   }

   public int getType() {
      return this.type;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.type, this.allDistance, this.nextDistance, this.destinationDistance, this.icon, this.surplusAllTime, this.carDirection, this.surplusAllTimeStr, this.planTime, this.nextWayName});
   }

   public String toString() {
      return "AMapEntity{type=" + this.type + ", allDistance=" + this.allDistance + ", nextDistance=" + this.nextDistance + ", destinationDistance=" + this.destinationDistance + ", icon=" + this.icon + ", surplusAllTime=" + this.surplusAllTime + ", carDirection=" + this.carDirection + ", surplusAllTimeStr='" + this.surplusAllTimeStr + '\'' + ", planTime='" + this.planTime + '\'' + ", nextWayName='" + this.nextWayName + '\'' + '}';
   }
}
