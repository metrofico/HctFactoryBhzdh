package jxl.biff.formula;

import java.util.HashMap;

class Token {
   public static final Token ADD = new Token(3);
   public static final Token AREA = new Token(37, 101, 69);
   public static final Token AREA3D = new Token(59, 91);
   public static final Token AREAV = new Token(45, 77, 109);
   public static final Token ATTRIBUTE = new Token(25);
   public static final Token BOOL = new Token(29);
   public static final Token CONCAT = new Token(8);
   public static final Token DIVIDE = new Token(6);
   public static final Token DOUBLE = new Token(31);
   public static final Token EQUAL = new Token(11);
   public static final Token ERR = new Token(28);
   public static final Token FUNCTION = new Token(65, 33, 97);
   public static final Token FUNCTIONVARARG = new Token(66, 34, 98);
   public static final Token GREATER_EQUAL = new Token(12);
   public static final Token GREATER_THAN = new Token(13);
   public static final Token INTEGER = new Token(30);
   public static final Token LESS_EQUAL = new Token(10);
   public static final Token LESS_THAN = new Token(9);
   public static final Token MEM_AREA = new Token(38, 70, 102);
   public static final Token MEM_FUNC = new Token(41, 73, 105);
   public static final Token MISSING_ARG = new Token(22);
   public static final Token MULTIPLY = new Token(5);
   public static final Token NAME = new Token(57, 89);
   public static final Token NAMED_RANGE = new Token(35, 67, 99);
   public static final Token NOT_EQUAL = new Token(14);
   public static final Token PARENTHESIS = new Token(21);
   public static final Token PERCENT = new Token(20);
   public static final Token POWER = new Token(7);
   public static final Token RANGE = new Token(17);
   public static final Token REF = new Token(68, 36, 100);
   public static final Token REF3D = new Token(90, 58, 122);
   public static final Token REFERR = new Token(42, 74, 106);
   public static final Token REFV = new Token(44, 76, 108);
   public static final Token STRING = new Token(23);
   public static final Token SUBTRACT = new Token(4);
   public static final Token UNARY_MINUS = new Token(19);
   public static final Token UNARY_PLUS = new Token(18);
   public static final Token UNION = new Token(16);
   public static final Token UNKNOWN = new Token(65535);
   private static HashMap tokens = new HashMap(20);
   public final int[] value;

   private Token(int var1) {
      this.value = new int[]{var1};
      tokens.put(new Integer(var1), this);
   }

   private Token(int var1, int var2) {
      this.value = new int[]{var1, var2};
      tokens.put(new Integer(var1), this);
      tokens.put(new Integer(var2), this);
   }

   private Token(int var1, int var2, int var3) {
      this.value = new int[]{var1, var2, var3};
      tokens.put(new Integer(var1), this);
      tokens.put(new Integer(var2), this);
      tokens.put(new Integer(var3), this);
   }

   private Token(int var1, int var2, int var3, int var4) {
      this.value = new int[]{var1, var2, var3, var4};
      tokens.put(new Integer(var1), this);
      tokens.put(new Integer(var2), this);
      tokens.put(new Integer(var3), this);
      tokens.put(new Integer(var4), this);
   }

   private Token(int var1, int var2, int var3, int var4, int var5) {
      this.value = new int[]{var1, var2, var3, var4, var5};
      tokens.put(new Integer(var1), this);
      tokens.put(new Integer(var2), this);
      tokens.put(new Integer(var3), this);
      tokens.put(new Integer(var4), this);
      tokens.put(new Integer(var5), this);
   }

   public static Token getToken(int var0) {
      Token var1 = (Token)tokens.get(new Integer(var0));
      if (var1 == null) {
         var1 = UNKNOWN;
      }

      return var1;
   }

   public byte getCode() {
      return (byte)this.value[0];
   }

   public byte getCode2() {
      int[] var2 = this.value;
      int var1;
      if (var2.length > 0) {
         var1 = var2[1];
      } else {
         var1 = var2[0];
      }

      return (byte)var1;
   }

   public byte getReferenceCode() {
      return (byte)this.value[0];
   }

   public byte getValueCode() {
      int[] var2 = this.value;
      int var1;
      if (var2.length > 0) {
         var1 = var2[1];
      } else {
         var1 = var2[0];
      }

      return (byte)var1;
   }
}
