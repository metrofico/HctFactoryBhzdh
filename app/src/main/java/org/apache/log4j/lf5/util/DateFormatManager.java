package org.apache.log4j.lf5.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatManager {
   private DateFormat _dateFormat;
   private Locale _locale;
   private String _pattern;
   private TimeZone _timeZone;

   public DateFormatManager() {
      this._timeZone = null;
      this._locale = null;
      this._pattern = null;
      this._dateFormat = null;
      this.configure();
   }

   public DateFormatManager(String var1) {
      this._timeZone = null;
      this._locale = null;
      this._dateFormat = null;
      this._pattern = var1;
      this.configure();
   }

   public DateFormatManager(Locale var1) {
      this._timeZone = null;
      this._pattern = null;
      this._dateFormat = null;
      this._locale = var1;
      this.configure();
   }

   public DateFormatManager(Locale var1, String var2) {
      this._timeZone = null;
      this._dateFormat = null;
      this._locale = var1;
      this._pattern = var2;
      this.configure();
   }

   public DateFormatManager(TimeZone var1) {
      this._locale = null;
      this._pattern = null;
      this._dateFormat = null;
      this._timeZone = var1;
      this.configure();
   }

   public DateFormatManager(TimeZone var1, String var2) {
      this._locale = null;
      this._dateFormat = null;
      this._timeZone = var1;
      this._pattern = var2;
      this.configure();
   }

   public DateFormatManager(TimeZone var1, Locale var2) {
      this._pattern = null;
      this._dateFormat = null;
      this._timeZone = var1;
      this._locale = var2;
      this.configure();
   }

   public DateFormatManager(TimeZone var1, Locale var2, String var3) {
      this._dateFormat = null;
      this._timeZone = var1;
      this._locale = var2;
      this._pattern = var3;
      this.configure();
   }

   private void configure() {
      synchronized(this){}

      Throwable var10000;
      label75: {
         boolean var10001;
         String var8;
         try {
            DateFormat var1 = DateFormat.getDateTimeInstance(0, 0, this.getLocale());
            this._dateFormat = var1;
            var1.setTimeZone(this.getTimeZone());
            var8 = this._pattern;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label75;
         }

         if (var8 == null) {
            return;
         }

         label66:
         try {
            ((SimpleDateFormat)this._dateFormat).applyPattern(var8);
            return;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label66;
         }
      }

      Throwable var9 = var10000;
      throw var9;
   }

   public String format(Date var1) {
      return this.getDateFormatInstance().format(var1);
   }

   public String format(Date var1, String var2) {
      DateFormat var4 = this.getDateFormatInstance();
      Object var3 = var4;
      if (var4 instanceof SimpleDateFormat) {
         var3 = (SimpleDateFormat)var4.clone();
         SimpleDateFormat var5 = (SimpleDateFormat)var3;
         ((SimpleDateFormat)var3).applyPattern(var2);
      }

      return ((DateFormat)var3).format(var1);
   }

   public DateFormat getDateFormatInstance() {
      synchronized(this){}

      DateFormat var1;
      try {
         var1 = this._dateFormat;
      } finally {
         ;
      }

      return var1;
   }

   public Locale getLocale() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         Locale var1;
         boolean var10001;
         try {
            var1 = this._locale;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         if (var1 != null) {
            return var1;
         }

         try {
            var1 = Locale.getDefault();
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label78;
         }

         return var1;
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public String getOutputFormat() {
      synchronized(this){}

      String var1;
      try {
         var1 = this._pattern;
      } finally {
         ;
      }

      return var1;
   }

   public String getPattern() {
      synchronized(this){}

      String var1;
      try {
         var1 = this._pattern;
      } finally {
         ;
      }

      return var1;
   }

   public TimeZone getTimeZone() {
      synchronized(this){}

      Throwable var10000;
      label78: {
         TimeZone var1;
         boolean var10001;
         try {
            var1 = this._timeZone;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label78;
         }

         if (var1 != null) {
            return var1;
         }

         try {
            var1 = TimeZone.getDefault();
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label78;
         }

         return var1;
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public Date parse(String var1) throws ParseException {
      return this.getDateFormatInstance().parse(var1);
   }

   public Date parse(String var1, String var2) throws ParseException {
      DateFormat var4 = this.getDateFormatInstance();
      Object var3 = var4;
      if (var4 instanceof SimpleDateFormat) {
         var3 = (SimpleDateFormat)var4.clone();
         SimpleDateFormat var5 = (SimpleDateFormat)var3;
         ((SimpleDateFormat)var3).applyPattern(var2);
      }

      return ((DateFormat)var3).parse(var1);
   }

   public void setDateFormatInstance(DateFormat var1) {
      synchronized(this){}

      try {
         this._dateFormat = var1;
      } finally {
         ;
      }

   }

   public void setLocale(Locale var1) {
      synchronized(this){}

      try {
         this._locale = var1;
         this.configure();
      } finally {
         ;
      }

   }

   public void setOutputFormat(String var1) {
      synchronized(this){}

      try {
         this._pattern = var1;
         this.configure();
      } finally {
         ;
      }

   }

   public void setPattern(String var1) {
      synchronized(this){}

      try {
         this._pattern = var1;
         this.configure();
      } finally {
         ;
      }

   }

   public void setTimeZone(TimeZone var1) {
      synchronized(this){}

      try {
         this._timeZone = var1;
         this.configure();
      } finally {
         ;
      }

   }
}
