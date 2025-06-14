package kotlin.jvm.internal;

import java.io.Serializable;

public class Ref {
   private Ref() {
   }

   public static final class BooleanRef implements Serializable {
      public boolean element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class ByteRef implements Serializable {
      public byte element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class CharRef implements Serializable {
      public char element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class DoubleRef implements Serializable {
      public double element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class FloatRef implements Serializable {
      public float element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class IntRef implements Serializable {
      public int element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class LongRef implements Serializable {
      public long element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class ObjectRef implements Serializable {
      public Object element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }

   public static final class ShortRef implements Serializable {
      public short element;

      public String toString() {
         return String.valueOf(this.element);
      }
   }
}
