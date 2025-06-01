package jxl.biff.formula;

abstract class NumberValue extends Operand implements ParsedThing {
   protected NumberValue() {
   }

   public void getString(StringBuffer var1) {
      var1.append(Double.toString(this.getValue()));
   }

   public abstract double getValue();
}
