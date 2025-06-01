package com.hzbhd.common.settings.constant.data.video;

public class VideoAvmBean {
   public static enum Key {
      private static final Key[] $VALUES;
      format,
      reverse_delay_off;

      static {
         Key var1 = new Key("format", 0);
         format = var1;
         Key var0 = new Key("reverse_delay_off", 1);
         reverse_delay_off = var0;
         $VALUES = new Key[]{var1, var0};
      }
   }
}
