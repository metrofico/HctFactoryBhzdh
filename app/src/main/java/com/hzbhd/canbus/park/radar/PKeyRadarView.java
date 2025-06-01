package com.hzbhd.canbus.park.radar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PKeyRadarView extends RelativeLayout {
   private ImageView back_radar_left;
   private ImageView back_radar_left_mid;
   private ImageView back_radar_right;
   private ImageView back_radar_right_mid;
   private ImageView front_radar_left;
   private ImageView front_radar_left_mid;
   private ImageView front_radar_right;
   private ImageView front_radar_right_mid;
   private View view;

   public PKeyRadarView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public PKeyRadarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public PKeyRadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558863, this, true);
      this.view = var4;
      this.front_radar_right = (ImageView)var4.findViewById(2131362281);
      this.front_radar_right_mid = (ImageView)this.view.findViewById(2131362282);
      this.front_radar_left_mid = (ImageView)this.view.findViewById(2131362274);
      this.front_radar_left = (ImageView)this.view.findViewById(2131362273);
      this.back_radar_right = (ImageView)this.view.findViewById(2131361951);
      this.back_radar_right_mid = (ImageView)this.view.findViewById(2131361952);
      this.back_radar_left_mid = (ImageView)this.view.findViewById(2131361944);
      this.back_radar_left = (ImageView)this.view.findViewById(2131361943);
   }

   private void setFrontLeftData(int var1) {
      if (var1 == 1) {
         this.front_radar_left.setBackgroundResource(2131233665);
      } else if (var1 == 2) {
         this.front_radar_left.setBackgroundResource(2131233667);
      } else if (var1 == 3) {
         this.front_radar_left.setBackgroundResource(2131233668);
      } else if (var1 == 4) {
         this.front_radar_left.setBackgroundResource(2131233669);
      } else if (var1 == 5) {
         this.front_radar_left.setBackgroundResource(2131233670);
      } else if (var1 == 6) {
         this.front_radar_left.setBackgroundResource(2131233671);
      } else if (var1 == 7) {
         this.front_radar_left.setBackgroundResource(2131233672);
      } else if (var1 == 8) {
         this.front_radar_left.setBackgroundResource(2131233673);
      } else if (var1 == 9) {
         this.front_radar_left.setBackgroundResource(2131233674);
      } else if (var1 >= 10) {
         this.front_radar_left.setBackgroundResource(2131233666);
      } else {
         this.front_radar_left.setBackgroundResource(2131233664);
      }

   }

   private void setFrontLeftMidData(int var1) {
      if (var1 == 1) {
         this.front_radar_left_mid.setBackgroundResource(2131233676);
      } else if (var1 == 2) {
         this.front_radar_left_mid.setBackgroundResource(2131233678);
      } else if (var1 == 3) {
         this.front_radar_left_mid.setBackgroundResource(2131233679);
      } else if (var1 == 4) {
         this.front_radar_left_mid.setBackgroundResource(2131233680);
      } else if (var1 == 5) {
         this.front_radar_left_mid.setBackgroundResource(2131233681);
      } else if (var1 == 6) {
         this.front_radar_left_mid.setBackgroundResource(2131233682);
      } else if (var1 == 7) {
         this.front_radar_left_mid.setBackgroundResource(2131233683);
      } else if (var1 == 8) {
         this.front_radar_left_mid.setBackgroundResource(2131233684);
      } else if (var1 == 9) {
         this.front_radar_left_mid.setBackgroundResource(2131233685);
      } else if (var1 >= 10) {
         this.front_radar_left_mid.setBackgroundResource(2131233677);
      } else {
         this.front_radar_left_mid.setBackgroundResource(2131233675);
      }

   }

   private void setFrontRightData(int var1) {
      if (var1 == 1) {
         this.front_radar_right.setBackgroundResource(2131233709);
      } else if (var1 == 2) {
         this.front_radar_right.setBackgroundResource(2131233711);
      } else if (var1 == 3) {
         this.front_radar_right.setBackgroundResource(2131233712);
      } else if (var1 == 4) {
         this.front_radar_right.setBackgroundResource(2131233713);
      } else if (var1 == 5) {
         this.front_radar_right.setBackgroundResource(2131233714);
      } else if (var1 == 6) {
         this.front_radar_right.setBackgroundResource(2131233715);
      } else if (var1 == 7) {
         this.front_radar_right.setBackgroundResource(2131233716);
      } else if (var1 == 8) {
         this.front_radar_right.setBackgroundResource(2131233717);
      } else if (var1 == 9) {
         this.front_radar_right.setBackgroundResource(2131233718);
      } else if (var1 >= 10) {
         this.front_radar_right.setBackgroundResource(2131233710);
      } else {
         this.front_radar_right.setBackgroundResource(2131233708);
      }

   }

   private void setFrontRightMidData(int var1) {
      if (var1 == 1) {
         this.front_radar_right_mid.setBackgroundResource(2131233720);
      } else if (var1 == 2) {
         this.front_radar_right_mid.setBackgroundResource(2131233722);
      } else if (var1 == 3) {
         this.front_radar_right_mid.setBackgroundResource(2131233723);
      } else if (var1 == 4) {
         this.front_radar_right_mid.setBackgroundResource(2131233724);
      } else if (var1 == 5) {
         this.front_radar_right_mid.setBackgroundResource(2131233725);
      } else if (var1 == 6) {
         this.front_radar_right_mid.setBackgroundResource(2131233726);
      } else if (var1 == 7) {
         this.front_radar_right_mid.setBackgroundResource(2131233727);
      } else if (var1 == 8) {
         this.front_radar_right_mid.setBackgroundResource(2131233728);
      } else if (var1 == 9) {
         this.front_radar_right_mid.setBackgroundResource(2131233729);
      } else if (var1 >= 10) {
         this.front_radar_right_mid.setBackgroundResource(2131233721);
      } else {
         this.front_radar_right_mid.setBackgroundResource(2131233719);
      }

   }

   private void setRearLeftData(int var1) {
      if (var1 == 1) {
         this.back_radar_left.setBackgroundResource(2131232396);
      } else if (var1 == 2) {
         this.back_radar_left.setBackgroundResource(2131232398);
      } else if (var1 == 3) {
         this.back_radar_left.setBackgroundResource(2131232399);
      } else if (var1 == 4) {
         this.back_radar_left.setBackgroundResource(2131232400);
      } else if (var1 == 5) {
         this.back_radar_left.setBackgroundResource(2131232401);
      } else if (var1 == 6) {
         this.back_radar_left.setBackgroundResource(2131232402);
      } else if (var1 == 7) {
         this.back_radar_left.setBackgroundResource(2131232403);
      } else if (var1 == 8) {
         this.back_radar_left.setBackgroundResource(2131232404);
      } else if (var1 == 9) {
         this.back_radar_left.setBackgroundResource(2131232405);
      } else if (var1 >= 10) {
         this.back_radar_left.setBackgroundResource(2131232397);
      } else {
         this.back_radar_left.setBackgroundResource(2131232395);
      }

   }

   private void setRearLeftMidData(int var1) {
      if (var1 == 1) {
         this.back_radar_left_mid.setBackgroundResource(2131232407);
      } else if (var1 == 2) {
         this.back_radar_left_mid.setBackgroundResource(2131232409);
      } else if (var1 == 3) {
         this.back_radar_left_mid.setBackgroundResource(2131232410);
      } else if (var1 == 4) {
         this.back_radar_left_mid.setBackgroundResource(2131232411);
      } else if (var1 == 5) {
         this.back_radar_left_mid.setBackgroundResource(2131232412);
      } else if (var1 == 6) {
         this.back_radar_left_mid.setBackgroundResource(2131232413);
      } else if (var1 == 7) {
         this.back_radar_left_mid.setBackgroundResource(2131232414);
      } else if (var1 == 8) {
         this.back_radar_left_mid.setBackgroundResource(2131232415);
      } else if (var1 == 9) {
         this.back_radar_left_mid.setBackgroundResource(2131232416);
      } else if (var1 >= 10) {
         this.back_radar_left_mid.setBackgroundResource(2131232408);
      } else {
         this.back_radar_left_mid.setBackgroundResource(2131232406);
      }

   }

   private void setRearRightData(int var1) {
      if (var1 == 1) {
         this.back_radar_right.setBackgroundResource(2131232440);
      } else if (var1 == 2) {
         this.back_radar_right.setBackgroundResource(2131232442);
      } else if (var1 == 3) {
         this.back_radar_right.setBackgroundResource(2131232443);
      } else if (var1 == 4) {
         this.back_radar_right.setBackgroundResource(2131232444);
      } else if (var1 == 5) {
         this.back_radar_right.setBackgroundResource(2131232445);
      } else if (var1 == 6) {
         this.back_radar_right.setBackgroundResource(2131232446);
      } else if (var1 == 7) {
         this.back_radar_right.setBackgroundResource(2131232447);
      } else if (var1 == 8) {
         this.back_radar_right.setBackgroundResource(2131232448);
      } else if (var1 == 9) {
         this.back_radar_right.setBackgroundResource(2131232449);
      } else if (var1 >= 10) {
         this.back_radar_right.setBackgroundResource(2131232441);
      } else {
         this.back_radar_right.setBackgroundResource(2131232439);
      }

   }

   private void setRearRightMidData(int var1) {
      if (var1 == 1) {
         this.back_radar_right_mid.setBackgroundResource(2131232451);
      } else if (var1 == 2) {
         this.back_radar_right_mid.setBackgroundResource(2131232453);
      } else if (var1 == 3) {
         this.back_radar_right_mid.setBackgroundResource(2131232454);
      } else if (var1 == 4) {
         this.back_radar_right_mid.setBackgroundResource(2131232455);
      } else if (var1 == 5) {
         this.back_radar_right_mid.setBackgroundResource(2131232456);
      } else if (var1 == 6) {
         this.back_radar_right_mid.setBackgroundResource(2131232457);
      } else if (var1 == 7) {
         this.back_radar_right_mid.setBackgroundResource(2131232458);
      } else if (var1 == 8) {
         this.back_radar_right_mid.setBackgroundResource(2131232459);
      } else if (var1 == 9) {
         this.back_radar_right_mid.setBackgroundResource(2131232460);
      } else if (var1 >= 10) {
         this.back_radar_right_mid.setBackgroundResource(2131232452);
      } else {
         this.back_radar_right_mid.setBackgroundResource(2131232450);
      }

   }

   public void refreshFrontRadar(int var1, int var2, int var3, int var4, int var5) {
      if (var1 > 10) {
         double var10 = (double)((float)var1 / 10.0F);
         double var24 = 2.0 * var10;
         double var12 = 3.0 * var10;
         double var20 = 4.0 * var10;
         double var16 = 5.0 * var10;
         double var18 = 6.0 * var10;
         double var14 = 7.0 * var10;
         double var8 = 8.0 * var10;
         double var22 = 9.0 * var10;
         double var6 = 10.0 * var10;
         double var26;
         if (var2 > 0 && (double)var2 <= var10) {
            this.setFrontLeftData(1);
         } else {
            var26 = (double)var2;
            if (var26 > var10 && var26 <= var24) {
               this.setFrontLeftData(2);
            } else if (var26 > var24 && var26 <= var12) {
               this.setFrontLeftData(3);
            } else if (var26 > var12 && var26 <= var20) {
               this.setFrontLeftData(4);
            } else if (var26 > var20 && var26 <= var16) {
               this.setFrontLeftData(5);
            } else if (var26 > var16 && var26 <= var18) {
               this.setFrontLeftData(6);
            } else if (var26 > var18 && var26 <= var14) {
               this.setFrontLeftData(7);
            } else if (var26 > var14 && var26 <= var8) {
               this.setFrontLeftData(8);
            } else if (var26 > var8 && var26 <= var22) {
               this.setFrontLeftData(9);
            } else if (var26 > var22 && var26 <= var6) {
               this.setFrontLeftData(10);
            }
         }

         if (var3 > 0 && (double)var3 <= var10) {
            this.setFrontLeftMidData(1);
         } else {
            var26 = (double)var3;
            if (var26 > var10 && var26 <= var24) {
               this.setFrontLeftMidData(2);
            } else if (var26 > var24 && var26 <= var12) {
               this.setFrontLeftMidData(3);
            } else if (var26 > var12 && var26 <= var20) {
               this.setFrontLeftMidData(4);
            } else if (var26 > var20 && var26 <= var16) {
               this.setFrontLeftMidData(5);
            } else if (var26 > var16 && var26 <= var18) {
               this.setFrontLeftMidData(6);
            } else if (var26 > var18 && var26 <= var14) {
               this.setFrontLeftMidData(7);
            } else if (var26 > var14 && var26 <= var8) {
               this.setFrontLeftMidData(8);
            } else if (var26 > var8 && var26 <= var22) {
               this.setFrontLeftMidData(9);
            } else if (var26 > var22 && var26 <= var6) {
               this.setFrontLeftMidData(10);
            }
         }

         if (var4 > 0 && (double)var4 <= var10) {
            this.setFrontRightMidData(1);
         } else {
            var26 = (double)var4;
            if (var26 > var10 && var26 <= var24) {
               this.setFrontRightMidData(2);
            } else if (var26 > var24 && var26 <= var12) {
               this.setFrontRightMidData(3);
            } else if (var26 > var12 && var26 <= var20) {
               this.setFrontRightMidData(4);
            } else if (var26 > var20 && var26 <= var16) {
               this.setFrontRightMidData(5);
            } else if (var26 > var16 && var26 <= var18) {
               this.setFrontRightMidData(6);
            } else if (var26 > var18 && var26 <= var14) {
               this.setFrontRightMidData(7);
            } else if (var26 > var14 && var26 <= var8) {
               this.setFrontRightMidData(8);
            } else if (var26 > var8 && var26 <= var22) {
               this.setFrontRightMidData(9);
            } else if (var26 > var22 && var26 <= var6) {
               this.setFrontRightMidData(10);
            }
         }

         if (var5 > 0 && (double)var5 <= var10) {
            this.setFrontRightData(1);
         } else {
            var26 = (double)var5;
            if (var26 > var10 && var26 <= var24) {
               this.setFrontRightData(2);
            } else if (var26 > var24 && var26 <= var12) {
               this.setFrontRightData(3);
            } else if (var26 > var12 && var26 <= var20) {
               this.setFrontRightData(4);
            } else if (var26 > var20 && var26 <= var16) {
               this.setFrontRightData(5);
            } else if (var26 > var16 && var26 <= var18) {
               this.setFrontRightData(6);
            } else if (var26 > var18 && var26 <= var14) {
               this.setFrontRightData(7);
            } else if (var26 > var14 && var26 <= var8) {
               this.setFrontRightData(8);
            } else if (var26 > var8 && var26 <= var22) {
               this.setFrontRightData(9);
            } else if (var26 > var22 && var26 <= var6) {
               this.setFrontRightData(10);
            }
         }
      } else if (var1 < 10) {
         if (var1 == 3) {
            this.setFrontLeftData(var2 * 3);
            this.setFrontLeftMidData(var3 * 3);
            this.setFrontRightMidData(var4 * 3);
            this.setFrontRightData(var5 * 3);
         } else if (var1 == 4) {
            this.setFrontLeftData(var2 * 3);
            this.setFrontLeftMidData(var3 * 3);
            this.setFrontRightMidData(var4 * 3);
            this.setFrontRightData(var5 * 3);
         } else if (var1 == 5) {
            this.setFrontLeftData(var2 * 2);
            this.setFrontLeftMidData(var3 * 2);
            this.setFrontRightMidData(var4 * 2);
            this.setFrontRightData(var5 * 2);
         } else if (var1 == 6) {
            if (var1 < 3) {
               this.setFrontLeftData(var2 * 2);
            } else if (var1 == 4) {
               this.setFrontLeftData(7);
            } else if (var1 == 5) {
               this.setFrontLeftData(9);
            } else if (var1 == 6) {
               this.setFrontLeftData(10);
            }

            if (var1 < 3) {
               this.setFrontLeftMidData(var3 * 2);
            } else if (var1 == 4) {
               this.setFrontLeftMidData(7);
            } else if (var1 == 5) {
               this.setFrontLeftMidData(9);
            } else if (var1 == 6) {
               this.setFrontLeftMidData(10);
            }

            if (var1 < 3) {
               this.setFrontRightMidData(var4 * 2);
            } else if (var1 == 4) {
               this.setFrontRightMidData(7);
            } else if (var1 == 5) {
               this.setFrontRightMidData(9);
            } else if (var1 == 6) {
               this.setFrontRightMidData(10);
            }

            if (var1 < 3) {
               this.setFrontRightData(var5 * 2);
            } else if (var1 == 4) {
               this.setFrontRightData(7);
            } else if (var1 == 5) {
               this.setFrontRightData(9);
            } else if (var1 == 6) {
               this.setFrontRightData(10);
            }
         } else if (var1 == 7) {
            if (var1 < 2) {
               this.setFrontLeftData(var2 * 2);
            } else if (var1 == 4) {
               this.setFrontLeftData(5);
            } else if (var1 == 5) {
               this.setFrontLeftData(7);
            } else if (var1 == 6) {
               this.setFrontLeftData(9);
            } else if (var1 == 7) {
               this.setFrontLeftData(10);
            }

            if (var1 < 2) {
               this.setFrontLeftMidData(var3 * 2);
            } else if (var1 == 4) {
               this.setFrontLeftMidData(5);
            } else if (var1 == 5) {
               this.setFrontLeftMidData(7);
            } else if (var1 == 6) {
               this.setFrontLeftMidData(9);
            } else if (var1 == 7) {
               this.setFrontLeftData(10);
            }

            if (var1 < 2) {
               this.setFrontRightMidData(var4 * 2);
            } else if (var1 == 4) {
               this.setFrontRightMidData(5);
            } else if (var1 == 5) {
               this.setFrontRightMidData(7);
            } else if (var1 == 6) {
               this.setFrontRightMidData(9);
            } else if (var1 == 7) {
               this.setFrontLeftData(10);
            }

            if (var1 < 2) {
               this.setFrontRightData(var5 * 2);
            } else if (var1 == 4) {
               this.setFrontRightData(5);
            } else if (var1 == 5) {
               this.setFrontRightData(7);
            } else if (var1 == 6) {
               this.setFrontRightData(9);
            } else if (var1 == 7) {
               this.setFrontLeftData(10);
            }
         } else {
            this.setFrontLeftData(var2);
            this.setFrontLeftMidData(var3);
            this.setFrontRightMidData(var4);
            this.setFrontRightData(var5);
         }
      } else {
         this.setFrontLeftData(var2);
         this.setFrontLeftMidData(var3);
         this.setFrontRightMidData(var4);
         this.setFrontRightData(var5);
      }

   }

   public void refreshRearRadar(int var1, int var2, int var3, int var4, int var5) {
      if (var1 > 10) {
         double var22 = (double)((float)var1 / 10.0F);
         double var6 = 2.0 * var22;
         double var24 = 3.0 * var22;
         double var20 = 4.0 * var22;
         double var12 = 5.0 * var22;
         double var14 = 6.0 * var22;
         double var8 = 7.0 * var22;
         double var18 = 8.0 * var22;
         double var16 = 9.0 * var22;
         double var10 = 10.0 * var22;
         double var26;
         if (var2 > 0 && (double)var2 <= var22) {
            this.setRearLeftData(1);
         } else {
            var26 = (double)var2;
            if (var26 > var22 && var26 <= var6) {
               this.setRearLeftData(2);
            } else if (var26 > var6 && var26 <= var24) {
               this.setRearLeftData(3);
            } else if (var26 > var24 && var26 <= var20) {
               this.setRearLeftData(4);
            } else if (var26 > var20 && var26 <= var12) {
               this.setRearLeftData(5);
            } else if (var26 > var12 && var26 <= var14) {
               this.setRearLeftData(6);
            } else if (var26 > var14 && var26 <= var8) {
               this.setRearLeftData(7);
            } else if (var26 > var8 && var26 <= var18) {
               this.setRearLeftData(8);
            } else if (var26 > var18 && var26 <= var16) {
               this.setRearLeftData(9);
            } else if (var26 > var16 && var26 <= var10) {
               this.setRearLeftData(10);
            }
         }

         if (var3 > 0 && (double)var3 <= var22) {
            this.setRearLeftMidData(1);
         } else {
            var26 = (double)var3;
            if (var26 > var22 && var26 <= var6) {
               this.setRearLeftMidData(2);
            } else if (var26 > var6 && var26 <= var24) {
               this.setRearLeftMidData(3);
            } else if (var26 > var24 && var26 <= var20) {
               this.setRearLeftMidData(4);
            } else if (var26 > var20 && var26 <= var12) {
               this.setRearLeftMidData(5);
            } else if (var26 > var12 && var26 <= var14) {
               this.setRearLeftMidData(6);
            } else if (var26 > var14 && var26 <= var8) {
               this.setRearLeftMidData(7);
            } else if (var26 > var8 && var26 <= var18) {
               this.setRearLeftMidData(8);
            } else if (var26 > var18 && var26 <= var16) {
               this.setRearLeftMidData(9);
            } else if (var26 > var16 && var26 <= var10) {
               this.setRearLeftMidData(10);
            }
         }

         if (var4 > 0 && (double)var4 <= var22) {
            this.setRearRightMidData(1);
         } else {
            var26 = (double)var4;
            if (var26 > var22 && var26 <= var6) {
               this.setRearRightMidData(2);
            } else if (var26 > var6 && var26 <= var24) {
               this.setRearRightMidData(3);
            } else if (var26 > var24 && var26 <= var20) {
               this.setRearRightMidData(4);
            } else if (var26 > var20 && var26 <= var12) {
               this.setRearRightMidData(5);
            } else if (var26 > var12 && var26 <= var14) {
               this.setRearRightMidData(6);
            } else if (var26 > var14 && var26 <= var8) {
               this.setRearRightMidData(7);
            } else if (var26 > var8 && var26 <= var18) {
               this.setRearRightMidData(8);
            } else if (var26 > var18 && var26 <= var16) {
               this.setRearRightMidData(9);
            } else if (var26 > var16 && var26 <= var10) {
               this.setRearRightMidData(10);
            }
         }

         if (var5 > 0 && (double)var5 <= var22) {
            this.setRearRightData(1);
         } else {
            var26 = (double)var5;
            if (var26 > var22 && var26 <= var6) {
               this.setRearRightData(2);
            } else if (var26 > var6 && var26 <= var24) {
               this.setRearRightData(3);
            } else if (var26 > var24 && var26 <= var20) {
               this.setRearRightData(4);
            } else if (var26 > var20 && var26 <= var12) {
               this.setRearRightData(5);
            } else if (var26 > var12 && var26 <= var14) {
               this.setRearRightData(6);
            } else if (var26 > var14 && var26 <= var8) {
               this.setRearRightData(7);
            } else if (var26 > var8 && var26 <= var18) {
               this.setRearRightData(8);
            } else if (var26 > var18 && var26 <= var16) {
               this.setRearRightData(9);
            } else if (var26 > var16 && var26 <= var10) {
               this.setRearRightData(10);
            }
         }
      } else if (var1 < 10) {
         if (var1 == 3) {
            this.setRearLeftData(var2 * 3);
            this.setRearLeftMidData(var3 * 3);
            this.setRearRightMidData(var4 * 3);
            this.setRearRightData(var5 * 3);
         } else if (var1 == 4) {
            this.setRearLeftData(var2 * 3);
            this.setRearLeftMidData(var3 * 3);
            this.setRearRightMidData(var4 * 3);
            this.setRearRightData(var5 * 3);
         } else if (var1 == 5) {
            this.setRearLeftData(var2 * 2);
            this.setRearLeftMidData(var3 * 2);
            this.setRearRightMidData(var4 * 2);
            this.setRearRightData(var5 * 2);
         } else if (var1 == 6) {
            if (var1 < 3) {
               this.setRearLeftData(var2 * 2);
            } else if (var1 == 4) {
               this.setRearLeftData(7);
            } else if (var1 == 5) {
               this.setRearLeftData(9);
            } else if (var1 == 6) {
               this.setRearLeftData(10);
            }

            if (var1 < 3) {
               this.setRearLeftMidData(var3 * 2);
            } else if (var1 == 4) {
               this.setRearLeftMidData(7);
            } else if (var1 == 5) {
               this.setRearLeftMidData(9);
            } else if (var1 == 6) {
               this.setRearLeftMidData(10);
            }

            if (var1 < 3) {
               this.setRearRightMidData(var4 * 2);
            } else if (var1 == 4) {
               this.setRearRightMidData(7);
            } else if (var1 == 5) {
               this.setRearRightMidData(9);
            } else if (var1 == 6) {
               this.setRearRightMidData(10);
            }

            if (var1 < 3) {
               this.setRearRightData(var5 * 2);
            } else if (var1 == 4) {
               this.setRearRightData(7);
            } else if (var1 == 5) {
               this.setRearRightData(9);
            } else if (var1 == 6) {
               this.setRearRightData(10);
            }
         } else if (var1 == 7) {
            if (var1 < 2) {
               this.setRearLeftData(var2 * 2);
            } else if (var1 == 4) {
               this.setRearLeftData(5);
            } else if (var1 == 5) {
               this.setRearLeftData(7);
            } else if (var1 == 6) {
               this.setRearLeftData(9);
            } else if (var1 == 7) {
               this.setRearLeftData(10);
            }

            if (var1 < 2) {
               this.setRearLeftMidData(var3 * 2);
            } else if (var1 == 4) {
               this.setRearLeftMidData(5);
            } else if (var1 == 5) {
               this.setRearLeftMidData(7);
            } else if (var1 == 6) {
               this.setRearLeftMidData(9);
            } else if (var1 == 7) {
               this.setRearLeftData(10);
            }

            if (var1 < 2) {
               this.setRearRightMidData(var4 * 2);
            } else if (var1 == 4) {
               this.setRearRightMidData(5);
            } else if (var1 == 5) {
               this.setRearRightMidData(7);
            } else if (var1 == 6) {
               this.setRearRightMidData(9);
            } else if (var1 == 7) {
               this.setRearLeftData(10);
            }

            if (var1 < 2) {
               this.setRearRightData(var5 * 2);
            } else if (var1 == 4) {
               this.setRearRightData(5);
            } else if (var1 == 5) {
               this.setRearRightData(7);
            } else if (var1 == 6) {
               this.setRearRightData(9);
            } else if (var1 == 7) {
               this.setRearLeftData(10);
            }
         } else {
            this.setRearLeftData(var2);
            this.setRearLeftMidData(var3);
            this.setRearRightMidData(var4);
            this.setRearRightData(var5);
         }
      } else {
         this.setRearLeftData(var2);
         this.setRearLeftMidData(var3);
         this.setRearRightMidData(var4);
         this.setRearRightData(var5);
      }

   }
}
