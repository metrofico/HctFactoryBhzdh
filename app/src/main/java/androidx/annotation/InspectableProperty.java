package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
public @interface InspectableProperty {
   int attributeId() default 0;

   EnumEntry[] enumMapping() default {};

   FlagEntry[] flagMapping() default {};

   boolean hasAttributeId() default true;

   String name() default "";

   ValueType valueType() default InspectableProperty.ValueType.INFERRED;

   @Retention(RetentionPolicy.SOURCE)
   @Target({ElementType.TYPE})
   public @interface EnumEntry {
      String name();

      int value();
   }

   @Retention(RetentionPolicy.SOURCE)
   @Target({ElementType.TYPE})
   public @interface FlagEntry {
      int mask() default 0;

      String name();

      int target();
   }

   public static enum ValueType {
      private static final ValueType[] $VALUES;
      COLOR,
      GRAVITY,
      INFERRED,
      INT_ENUM,
      INT_FLAG,
      NONE,
      RESOURCE_ID;

      static {
         ValueType var0 = new ValueType("NONE", 0);
         NONE = var0;
         ValueType var3 = new ValueType("INFERRED", 1);
         INFERRED = var3;
         ValueType var4 = new ValueType("INT_ENUM", 2);
         INT_ENUM = var4;
         ValueType var6 = new ValueType("INT_FLAG", 3);
         INT_FLAG = var6;
         ValueType var1 = new ValueType("COLOR", 4);
         COLOR = var1;
         ValueType var5 = new ValueType("GRAVITY", 5);
         GRAVITY = var5;
         ValueType var2 = new ValueType("RESOURCE_ID", 6);
         RESOURCE_ID = var2;
         $VALUES = new ValueType[]{var0, var3, var4, var6, var1, var5, var2};
      }
   }
}
