package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AmplifierItemSevenView extends LinearLayout {
   private String leftUnit;
   private TextView name;
   private TextView negativeFive;
   private TextView negativeFour;
   private TextView negativeOne;
   private TextView negativeSeven;
   private TextView negativeSix;
   private TextView negativeThree;
   private TextView negativeTwo;
   private TextView numberFive;
   private TextView numberFour;
   private TextView numberOne;
   private TextView numberSeven;
   private TextView numberSix;
   private TextView numberThree;
   private TextView numberTwo;
   private String rightUnit;
   private View view;
   private TextView zero;

   public AmplifierItemSevenView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AmplifierItemSevenView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AmplifierItemSevenView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.leftUnit = "";
      this.rightUnit = "";
      View var4 = LayoutInflater.from(var1).inflate(2131558548, this, true);
      this.view = var4;
      this.name = (TextView)var4.findViewById(2131362886);
      this.negativeSeven = (TextView)this.view.findViewById(2131362891);
      this.negativeSix = (TextView)this.view.findViewById(2131362892);
      this.negativeFive = (TextView)this.view.findViewById(2131362888);
      this.negativeFour = (TextView)this.view.findViewById(2131362889);
      this.negativeThree = (TextView)this.view.findViewById(2131362893);
      this.negativeTwo = (TextView)this.view.findViewById(2131362894);
      this.negativeOne = (TextView)this.view.findViewById(2131362890);
      this.zero = (TextView)this.view.findViewById(2131363807);
      this.numberOne = (TextView)this.view.findViewById(2131362910);
      this.numberTwo = (TextView)this.view.findViewById(2131362914);
      this.numberThree = (TextView)this.view.findViewById(2131362913);
      this.numberFour = (TextView)this.view.findViewById(2131362908);
      this.numberFive = (TextView)this.view.findViewById(2131362907);
      this.numberSix = (TextView)this.view.findViewById(2131362912);
      this.numberSeven = (TextView)this.view.findViewById(2131362911);
   }

   public void setData(int var1) {
      if (var1 > 0) {
         this.zero.setText(this.rightUnit + Math.abs(var1));
      } else if (var1 < 0) {
         this.zero.setText(this.leftUnit + Math.abs(var1));
      } else {
         this.zero.setText(" ");
      }

      if (var1 == -7) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131099974);
         this.negativeFive.setBackgroundResource(2131099974);
         this.negativeFour.setBackgroundResource(2131099974);
         this.negativeThree.setBackgroundResource(2131099974);
         this.negativeTwo.setBackgroundResource(2131099974);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == -6) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131099974);
         this.negativeFive.setBackgroundResource(2131099974);
         this.negativeFour.setBackgroundResource(2131099974);
         this.negativeThree.setBackgroundResource(2131099974);
         this.negativeTwo.setBackgroundResource(2131099974);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == -5) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131099974);
         this.negativeFour.setBackgroundResource(2131099974);
         this.negativeThree.setBackgroundResource(2131099974);
         this.negativeTwo.setBackgroundResource(2131099974);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == -4) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131099974);
         this.negativeThree.setBackgroundResource(2131099974);
         this.negativeTwo.setBackgroundResource(2131099974);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == -3) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131099974);
         this.negativeTwo.setBackgroundResource(2131099974);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == -2) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131099974);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == -1) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131099974);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 0) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131100048);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 1) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131100048);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 2) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131099974);
         this.numberThree.setBackgroundResource(2131100048);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 3) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131099974);
         this.numberThree.setBackgroundResource(2131099974);
         this.numberFour.setBackgroundResource(2131100048);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 4) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131099974);
         this.numberThree.setBackgroundResource(2131099974);
         this.numberFour.setBackgroundResource(2131099974);
         this.numberFive.setBackgroundResource(2131100048);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 5) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131099974);
         this.numberThree.setBackgroundResource(2131099974);
         this.numberFour.setBackgroundResource(2131099974);
         this.numberFive.setBackgroundResource(2131099974);
         this.numberSix.setBackgroundResource(2131100048);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 6) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131099974);
         this.numberThree.setBackgroundResource(2131099974);
         this.numberFour.setBackgroundResource(2131099974);
         this.numberFive.setBackgroundResource(2131099974);
         this.numberSix.setBackgroundResource(2131099974);
         this.numberSeven.setBackgroundResource(2131100048);
      }

      if (var1 == 7) {
         this.negativeSeven.setBackgroundResource(2131100048);
         this.negativeSix.setBackgroundResource(2131100048);
         this.negativeFive.setBackgroundResource(2131100048);
         this.negativeFour.setBackgroundResource(2131100048);
         this.negativeThree.setBackgroundResource(2131100048);
         this.negativeTwo.setBackgroundResource(2131100048);
         this.negativeOne.setBackgroundResource(2131100048);
         this.zero.setBackgroundResource(2131099997);
         this.numberOne.setBackgroundResource(2131099974);
         this.numberTwo.setBackgroundResource(2131099974);
         this.numberThree.setBackgroundResource(2131099974);
         this.numberFour.setBackgroundResource(2131099974);
         this.numberFive.setBackgroundResource(2131099974);
         this.numberSix.setBackgroundResource(2131099974);
         this.numberSeven.setBackgroundResource(2131099974);
      }

   }

   public void setTitle(String var1) {
      this.name.setText(var1);
   }

   public void setUnit(String var1, String var2) {
      this.leftUnit = var1;
      this.rightUnit = var2;
   }
}
