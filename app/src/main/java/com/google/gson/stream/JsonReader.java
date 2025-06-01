package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class JsonReader implements Closeable {
   private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
   private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
   private static final int NUMBER_CHAR_DECIMAL = 3;
   private static final int NUMBER_CHAR_DIGIT = 2;
   private static final int NUMBER_CHAR_EXP_DIGIT = 7;
   private static final int NUMBER_CHAR_EXP_E = 5;
   private static final int NUMBER_CHAR_EXP_SIGN = 6;
   private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
   private static final int NUMBER_CHAR_NONE = 0;
   private static final int NUMBER_CHAR_SIGN = 1;
   private static final int PEEKED_BEGIN_ARRAY = 3;
   private static final int PEEKED_BEGIN_OBJECT = 1;
   private static final int PEEKED_BUFFERED = 11;
   private static final int PEEKED_DOUBLE_QUOTED = 9;
   private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
   private static final int PEEKED_END_ARRAY = 4;
   private static final int PEEKED_END_OBJECT = 2;
   private static final int PEEKED_EOF = 17;
   private static final int PEEKED_FALSE = 6;
   private static final int PEEKED_LONG = 15;
   private static final int PEEKED_NONE = 0;
   private static final int PEEKED_NULL = 7;
   private static final int PEEKED_NUMBER = 16;
   private static final int PEEKED_SINGLE_QUOTED = 8;
   private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
   private static final int PEEKED_TRUE = 5;
   private static final int PEEKED_UNQUOTED = 10;
   private static final int PEEKED_UNQUOTED_NAME = 14;
   private final char[] buffer = new char[1024];
   private final Reader in;
   private boolean lenient = false;
   private int limit = 0;
   private int lineNumber = 0;
   private int lineStart = 0;
   private int[] pathIndices;
   private String[] pathNames;
   int peeked = 0;
   private long peekedLong;
   private int peekedNumberLength;
   private String peekedString;
   private int pos = 0;
   private int[] stack;
   private int stackSize;

   static {
      JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
         public void promoteNameToValue(JsonReader var1) throws IOException {
            if (var1 instanceof JsonTreeReader) {
               ((JsonTreeReader)var1).promoteNameToValue();
            } else {
               int var3 = var1.peeked;
               int var2 = var3;
               if (var3 == 0) {
                  var2 = var1.doPeek();
               }

               if (var2 == 13) {
                  var1.peeked = 9;
               } else if (var2 == 12) {
                  var1.peeked = 8;
               } else {
                  if (var2 != 14) {
                     throw new IllegalStateException("Expected a name but was " + var1.peek() + var1.locationString());
                  }

                  var1.peeked = 10;
               }

            }
         }
      };
   }

   public JsonReader(Reader var1) {
      int[] var2 = new int[32];
      this.stack = var2;
      this.stackSize = 0 + 1;
      var2[0] = 6;
      this.pathNames = new String[32];
      this.pathIndices = new int[32];
      if (var1 != null) {
         this.in = var1;
      } else {
         throw new NullPointerException("in == null");
      }
   }

   private void checkLenient() throws IOException {
      if (!this.lenient) {
         throw this.syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
      }
   }

   private void consumeNonExecutePrefix() throws IOException {
      this.nextNonWhitespace(true);
      int var1 = this.pos - 1;
      this.pos = var1;
      char[] var2 = NON_EXECUTE_PREFIX;
      if (var1 + var2.length <= this.limit || this.fillBuffer(var2.length)) {
         var1 = 0;

         while(true) {
            var2 = NON_EXECUTE_PREFIX;
            if (var1 >= var2.length) {
               this.pos += var2.length;
               return;
            }

            if (this.buffer[this.pos + var1] != var2[var1]) {
               return;
            }

            ++var1;
         }
      }
   }

   private boolean fillBuffer(int var1) throws IOException {
      char[] var5 = this.buffer;
      int var3 = this.lineStart;
      int var2 = this.pos;
      this.lineStart = var3 - var2;
      var3 = this.limit;
      if (var3 != var2) {
         var3 -= var2;
         this.limit = var3;
         System.arraycopy(var5, var2, var5, 0, var3);
      } else {
         this.limit = 0;
      }

      this.pos = 0;

      do {
         Reader var6 = this.in;
         var2 = this.limit;
         var2 = var6.read(var5, var2, var5.length - var2);
         if (var2 == -1) {
            return false;
         }

         var3 = this.limit + var2;
         this.limit = var3;
         var2 = var1;
         if (this.lineNumber == 0) {
            int var4 = this.lineStart;
            var2 = var1;
            if (var4 == 0) {
               var2 = var1;
               if (var3 > 0) {
                  var2 = var1;
                  if (var5[0] == '\ufeff') {
                     ++this.pos;
                     this.lineStart = var4 + 1;
                     var2 = var1 + 1;
                  }
               }
            }
         }

         var1 = var2;
      } while(var3 < var2);

      return true;
   }

   private boolean isLiteral(char var1) throws IOException {
      if (var1 != '\t' && var1 != '\n' && var1 != '\f' && var1 != '\r' && var1 != ' ') {
         if (var1 != '#') {
            if (var1 == ',') {
               return false;
            }

            if (var1 != '/' && var1 != '=') {
               if (var1 == '{' || var1 == '}' || var1 == ':') {
                  return false;
               }

               if (var1 != ';') {
                  switch (var1) {
                     case '[':
                     case ']':
                        return false;
                     case '\\':
                        break;
                     default:
                        return true;
                  }
               }
            }
         }

         this.checkLenient();
      }

      return false;
   }

   private int nextNonWhitespace(boolean var1) throws IOException {
      char[] var7 = this.buffer;
      int var2 = this.pos;
      int var3 = this.limit;

      while(true) {
         while(true) {
            int var5 = var2;
            int var4 = var3;
            if (var2 == var3) {
               this.pos = var2;
               if (!this.fillBuffer(1)) {
                  if (!var1) {
                     return -1;
                  }

                  throw new EOFException("End of input" + this.locationString());
               }

               var5 = this.pos;
               var4 = this.limit;
            }

            var2 = var5 + 1;
            char var8 = var7[var5];
            if (var8 == '\n') {
               ++this.lineNumber;
               this.lineStart = var2;
            } else if (var8 != ' ' && var8 != '\r' && var8 != '\t') {
               if (var8 == '/') {
                  this.pos = var2;
                  if (var2 == var4) {
                     this.pos = var2 - 1;
                     boolean var6 = this.fillBuffer(2);
                     ++this.pos;
                     if (!var6) {
                        return var8;
                     }
                  }

                  this.checkLenient();
                  var2 = this.pos;
                  char var9 = var7[var2];
                  if (var9 != '*') {
                     if (var9 != '/') {
                        return var8;
                     }

                     this.pos = var2 + 1;
                     this.skipToEndOfLine();
                     var2 = this.pos;
                     var3 = this.limit;
                  } else {
                     this.pos = var2 + 1;
                     if (!this.skipTo("*/")) {
                        throw this.syntaxError("Unterminated comment");
                     }

                     var2 = this.pos + 2;
                     var3 = this.limit;
                  }
               } else {
                  if (var8 != '#') {
                     this.pos = var2;
                     return var8;
                  }

                  this.pos = var2;
                  this.checkLenient();
                  this.skipToEndOfLine();
                  var2 = this.pos;
                  var3 = this.limit;
               }
               continue;
            }

            var3 = var4;
         }
      }
   }

   private String nextQuotedValue(char var1) throws IOException {
      char[] var8 = this.buffer;
      StringBuilder var6 = null;

      label47:
      while(true) {
         int var2 = this.pos;
         int var4 = this.limit;

         while(true) {
            int var3 = var2;

            while(true) {
               int var5 = var3;
               StringBuilder var7;
               if (var3 >= var4) {
                  var7 = var6;
                  if (var6 == null) {
                     var7 = new StringBuilder(Math.max((var3 - var2) * 2, 16));
                  }

                  var7.append(var8, var2, var3 - var2);
                  this.pos = var3;
                  if (!this.fillBuffer(1)) {
                     throw this.syntaxError("Unterminated string");
                  }

                  var6 = var7;
                  continue label47;
               }

               ++var3;
               char var9 = var8[var5];
               if (var9 == var1) {
                  this.pos = var3;
                  var1 = var3 - var2 - 1;
                  if (var6 == null) {
                     return new String(var8, var2, var1);
                  }

                  var6.append(var8, var2, var1);
                  return var6.toString();
               }

               if (var9 == '\\') {
                  this.pos = var3;
                  var3 = var3 - var2 - 1;
                  var7 = var6;
                  if (var6 == null) {
                     var7 = new StringBuilder(Math.max((var3 + 1) * 2, 16));
                  }

                  var7.append(var8, var2, var3);
                  var7.append(this.readEscapeCharacter());
                  var2 = this.pos;
                  var4 = this.limit;
                  var6 = var7;
                  break;
               }

               if (var9 == '\n') {
                  ++this.lineNumber;
                  this.lineStart = var3;
               }
            }
         }
      }
   }

   private String nextUnquotedValue() throws IOException {
      byte var2 = 0;
      StringBuilder var5 = null;

      int var1;
      StringBuilder var4;
      label79:
      while(true) {
         var1 = 0;

         label76:
         while(true) {
            int var3 = this.pos;
            if (var3 + var1 < this.limit) {
               char var6 = this.buffer[var3 + var1];
               if (var6 == '\t' || var6 == '\n' || var6 == '\f' || var6 == '\r' || var6 == ' ') {
                  break;
               }

               if (var6 != '#') {
                  if (var6 == ',') {
                     break;
                  }

                  if (var6 != '/' && var6 != '=') {
                     if (var6 == '{' || var6 == '}' || var6 == ':') {
                        break;
                     }

                     if (var6 != ';') {
                        switch (var6) {
                           case '[':
                           case ']':
                              break label76;
                           case '\\':
                              break;
                           default:
                              ++var1;
                              continue;
                        }
                     }
                  }
               }

               this.checkLenient();
               break;
            } else {
               if (var1 >= this.buffer.length) {
                  var4 = var5;
                  if (var5 == null) {
                     var4 = new StringBuilder(Math.max(var1, 16));
                  }

                  var4.append(this.buffer, this.pos, var1);
                  this.pos += var1;
                  var5 = var4;
                  if (this.fillBuffer(1)) {
                     continue label79;
                  }

                  var1 = var2;
                  break label79;
               }

               if (!this.fillBuffer(var1 + 1)) {
                  break;
               }
            }
         }

         var4 = var5;
         break;
      }

      String var7;
      if (var4 == null) {
         var7 = new String(this.buffer, this.pos, var1);
      } else {
         var7 = var4.append(this.buffer, this.pos, var1).toString();
      }

      this.pos += var1;
      return var7;
   }

   private int peekKeyword() throws IOException {
      char var1 = this.buffer[this.pos];
      String var5;
      String var6;
      byte var7;
      if (var1 != 't' && var1 != 'T') {
         if (var1 != 'f' && var1 != 'F') {
            if (var1 != 'n' && var1 != 'N') {
               return 0;
            }

            var7 = 7;
            var5 = "null";
            var6 = "NULL";
         } else {
            var7 = 6;
            var5 = "false";
            var6 = "FALSE";
         }
      } else {
         var7 = 5;
         var5 = "true";
         var6 = "TRUE";
      }

      int var3 = var5.length();

      for(int var2 = 1; var2 < var3; ++var2) {
         if (this.pos + var2 >= this.limit && !this.fillBuffer(var2 + 1)) {
            return 0;
         }

         char var4 = this.buffer[this.pos + var2];
         if (var4 != var5.charAt(var2) && var4 != var6.charAt(var2)) {
            return 0;
         }
      }

      if ((this.pos + var3 < this.limit || this.fillBuffer(var3 + 1)) && this.isLiteral(this.buffer[this.pos + var3])) {
         return 0;
      } else {
         this.pos += var3;
         this.peeked = var7;
         return var7;
      }
   }

   private int peekNumber() throws IOException {
      char[] var14 = this.buffer;
      int var5 = this.pos;
      int var4 = this.limit;
      boolean var3 = true;
      int var7 = 0;
      byte var2 = 0;
      byte var6 = var2;
      long var10 = 0L;

      while(true) {
         int var9 = var5;
         int var8 = var4;
         if (var5 + var7 == var4) {
            if (var7 == var14.length) {
               return 0;
            }

            if (!this.fillBuffer(var7 + 1)) {
               break;
            }

            var9 = this.pos;
            var8 = this.limit;
         }

         char var1 = var14[var9 + var7];
         byte var15 = 3;
         if (var1 != '+') {
            if (var1 != 'E' && var1 != 'e') {
               if (var1 != '-') {
                  if (var1 != '.') {
                     label161: {
                        if (var1 < '0' || var1 > '9') {
                           if (this.isLiteral(var1)) {
                              return 0;
                           }
                           break;
                        }

                        label173: {
                           long var12;
                           boolean var16;
                           if (var2 != 1 && var2 != 0) {
                              if (var2 == 2) {
                                 if (var10 == 0L) {
                                    return 0;
                                 }

                                 var12 = 10L * var10 - (long)(var1 - 48);
                                 long var18;
                                 var4 = (var18 = var10 - -922337203685477580L) == 0L ? 0 : (var18 < 0L ? -1 : 1);
                                 boolean var17;
                                 if (var4 > 0 || var4 == 0 && var12 < var10) {
                                    var17 = true;
                                 } else {
                                    var17 = false;
                                 }

                                 var16 = var3 & var17;
                                 var15 = var2;
                              } else {
                                 if (var2 == 3) {
                                    var2 = 4;
                                    break label161;
                                 }

                                 if (var2 == 5) {
                                    break label173;
                                 }

                                 var15 = var2;
                                 var16 = var3;
                                 var12 = var10;
                                 if (var2 == 6) {
                                    break label173;
                                 }
                              }
                           } else {
                              var12 = (long)(-(var1 - 48));
                              var15 = 2;
                              var16 = var3;
                           }

                           var10 = var12;
                           var3 = var16;
                           var2 = var15;
                           break label161;
                        }

                        var2 = 7;
                     }
                  } else {
                     if (var2 != 2) {
                        return 0;
                     }

                     var2 = var15;
                  }
               } else {
                  var15 = 6;
                  if (var2 == 0) {
                     var2 = 1;
                     var6 = 1;
                  } else {
                     if (var2 != 5) {
                        return 0;
                     }

                     var2 = var15;
                  }
               }
            } else {
               if (var2 != 2 && var2 != 4) {
                  return 0;
               }

               var2 = 5;
            }
         } else {
            var15 = 6;
            if (var2 != 5) {
               return 0;
            }

            var2 = var15;
         }

         ++var7;
         var5 = var9;
         var4 = var8;
      }

      if (var2 == 2 && var3 && (var10 != Long.MIN_VALUE || var6 != 0) && (var10 != 0L || var6 == 0)) {
         if (var6 == 0) {
            var10 = -var10;
         }

         this.peekedLong = var10;
         this.pos += var7;
         this.peeked = 15;
         return 15;
      } else if (var2 != 2 && var2 != 4 && var2 != 7) {
         return 0;
      } else {
         this.peekedNumberLength = var7;
         this.peeked = 16;
         return 16;
      }
   }

   private void push(int var1) {
      int var2 = this.stackSize;
      int[] var3 = this.stack;
      if (var2 == var3.length) {
         var2 *= 2;
         this.stack = Arrays.copyOf(var3, var2);
         this.pathIndices = Arrays.copyOf(this.pathIndices, var2);
         this.pathNames = (String[])Arrays.copyOf(this.pathNames, var2);
      }

      var3 = this.stack;
      var2 = this.stackSize++;
      var3[var2] = var1;
   }

   private char readEscapeCharacter() throws IOException {
      if (this.pos == this.limit && !this.fillBuffer(1)) {
         throw this.syntaxError("Unterminated escape sequence");
      } else {
         char[] var6 = this.buffer;
         int var2 = this.pos;
         int var3 = var2 + 1;
         this.pos = var3;
         char var1 = var6[var2];
         if (var1 != '\n') {
            if (var1 != '"' && var1 != '\'' && var1 != '/' && var1 != '\\') {
               if (var1 == 'b') {
                  return '\b';
               }

               if (var1 == 'f') {
                  return '\f';
               }

               if (var1 == 'n') {
                  return '\n';
               }

               if (var1 == 'r') {
                  return '\r';
               }

               if (var1 == 't') {
                  return '\t';
               }

               if (var1 != 'u') {
                  throw this.syntaxError("Invalid escape sequence");
               }

               if (var3 + 4 > this.limit && !this.fillBuffer(4)) {
                  throw this.syntaxError("Unterminated escape sequence");
               }

               var1 = 0;
               var3 = this.pos;
               var2 = var3;

               while(true) {
                  int var4 = var2;
                  if (var2 >= var3 + 4) {
                     this.pos += 4;
                     return var1;
                  }

                  char var7 = this.buffer[var2];
                  char var5 = (char)(var1 << 4);
                  if (var7 >= '0' && var7 <= '9') {
                     var2 = var7 - 48;
                  } else {
                     if (var7 >= 'a' && var7 <= 'f') {
                        var2 = var7 - 97;
                     } else {
                        if (var7 < 'A' || var7 > 'F') {
                           throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                        }

                        var2 = var7 - 65;
                     }

                     var2 += 10;
                  }

                  var1 = (char)(var5 + var2);
                  var2 = var4 + 1;
               }
            }
         } else {
            ++this.lineNumber;
            this.lineStart = var3;
         }

         return var1;
      }
   }

   private void skipQuotedValue(char var1) throws IOException {
      char[] var5 = this.buffer;

      do {
         int var2 = this.pos;
         int var3 = this.limit;

         while(var2 < var3) {
            int var4 = var2 + 1;
            char var6 = var5[var2];
            if (var6 == var1) {
               this.pos = var4;
               return;
            }

            if (var6 == '\\') {
               this.pos = var4;
               this.readEscapeCharacter();
               var2 = this.pos;
               var3 = this.limit;
            } else {
               if (var6 == '\n') {
                  ++this.lineNumber;
                  this.lineStart = var4;
               }

               var2 = var4;
            }
         }

         this.pos = var2;
      } while(this.fillBuffer(1));

      throw this.syntaxError("Unterminated string");
   }

   private boolean skipTo(String var1) throws IOException {
      int var3 = var1.length();

      while(true) {
         int var4 = this.pos;
         int var5 = this.limit;
         int var2 = 0;
         if (var4 + var3 > var5 && !this.fillBuffer(var3)) {
            return false;
         }

         char[] var6 = this.buffer;
         var4 = this.pos;
         if (var6[var4] == '\n') {
            ++this.lineNumber;
            this.lineStart = var4 + 1;
         } else {
            while(true) {
               if (var2 >= var3) {
                  return true;
               }

               if (this.buffer[this.pos + var2] != var1.charAt(var2)) {
                  break;
               }

               ++var2;
            }
         }

         ++this.pos;
      }
   }

   private void skipToEndOfLine() throws IOException {
      while(this.pos < this.limit || this.fillBuffer(1)) {
         char[] var3 = this.buffer;
         int var2 = this.pos;
         int var1 = var2 + 1;
         this.pos = var1;
         char var4 = var3[var2];
         if (var4 == '\n') {
            ++this.lineNumber;
            this.lineStart = var1;
         } else if (var4 != '\r') {
            continue;
         }
         break;
      }

   }

   private void skipUnquotedValue() throws IOException {
      label61:
      while(true) {
         int var1 = 0;

         label58:
         while(true) {
            int var2 = this.pos;
            if (var2 + var1 >= this.limit) {
               this.pos = var2 + var1;
               if (this.fillBuffer(1)) {
                  continue label61;
               }

               return;
            }

            char var3 = this.buffer[var2 + var1];
            if (var3 == '\t' || var3 == '\n' || var3 == '\f' || var3 == '\r' || var3 == ' ') {
               break;
            }

            if (var3 != '#') {
               if (var3 == ',') {
                  break;
               }

               if (var3 != '/' && var3 != '=') {
                  if (var3 == '{' || var3 == '}' || var3 == ':') {
                     break;
                  }

                  if (var3 != ';') {
                     switch (var3) {
                        case '[':
                        case ']':
                           break label58;
                        case '\\':
                           break;
                        default:
                           ++var1;
                           continue;
                     }
                  }
               }
            }

            this.checkLenient();
            break;
         }

         this.pos += var1;
         return;
      }
   }

   private IOException syntaxError(String var1) throws IOException {
      throw new MalformedJsonException(var1 + this.locationString());
   }

   public void beginArray() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      if (var1 == 3) {
         this.push(1);
         this.pathIndices[this.stackSize - 1] = 0;
         this.peeked = 0;
      } else {
         throw new IllegalStateException("Expected BEGIN_ARRAY but was " + this.peek() + this.locationString());
      }
   }

   public void beginObject() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      if (var1 == 1) {
         this.push(3);
         this.peeked = 0;
      } else {
         throw new IllegalStateException("Expected BEGIN_OBJECT but was " + this.peek() + this.locationString());
      }
   }

   public void close() throws IOException {
      this.peeked = 0;
      this.stack[0] = 8;
      this.stackSize = 1;
      this.in.close();
   }

   int doPeek() throws IOException {
      int[] var3 = this.stack;
      int var2 = this.stackSize;
      int var1 = var3[var2 - 1];
      if (var1 == 1) {
         var3[var2 - 1] = 2;
      } else if (var1 == 2) {
         var2 = this.nextNonWhitespace(true);
         if (var2 != 44) {
            if (var2 != 59) {
               if (var2 == 93) {
                  this.peeked = 4;
                  return 4;
               }

               throw this.syntaxError("Unterminated array");
            }

            this.checkLenient();
         }
      } else {
         if (var1 == 3 || var1 == 5) {
            var3[var2 - 1] = 4;
            if (var1 == 5) {
               var2 = this.nextNonWhitespace(true);
               if (var2 != 44) {
                  if (var2 != 59) {
                     if (var2 == 125) {
                        this.peeked = 2;
                        return 2;
                     }

                     throw this.syntaxError("Unterminated object");
                  }

                  this.checkLenient();
               }
            }

            var2 = this.nextNonWhitespace(true);
            if (var2 != 34) {
               if (var2 != 39) {
                  if (var2 != 125) {
                     this.checkLenient();
                     --this.pos;
                     if (this.isLiteral((char)var2)) {
                        this.peeked = 14;
                        return 14;
                     } else {
                        throw this.syntaxError("Expected name");
                     }
                  } else if (var1 != 5) {
                     this.peeked = 2;
                     return 2;
                  } else {
                     throw this.syntaxError("Expected name");
                  }
               } else {
                  this.checkLenient();
                  this.peeked = 12;
                  return 12;
               }
            } else {
               this.peeked = 13;
               return 13;
            }
         }

         if (var1 == 4) {
            var3[var2 - 1] = 5;
            var2 = this.nextNonWhitespace(true);
            if (var2 != 58) {
               if (var2 != 61) {
                  throw this.syntaxError("Expected ':'");
               }

               this.checkLenient();
               if (this.pos < this.limit || this.fillBuffer(1)) {
                  char[] var4 = this.buffer;
                  var2 = this.pos;
                  if (var4[var2] == '>') {
                     this.pos = var2 + 1;
                  }
               }
            }
         } else if (var1 == 6) {
            if (this.lenient) {
               this.consumeNonExecutePrefix();
            }

            this.stack[this.stackSize - 1] = 7;
         } else if (var1 == 7) {
            if (this.nextNonWhitespace(false) == -1) {
               this.peeked = 17;
               return 17;
            }

            this.checkLenient();
            --this.pos;
         } else if (var1 == 8) {
            throw new IllegalStateException("JsonReader is closed");
         }
      }

      var2 = this.nextNonWhitespace(true);
      if (var2 != 34) {
         if (var2 != 39) {
            if (var2 != 44 && var2 != 59) {
               if (var2 == 91) {
                  this.peeked = 3;
                  return 3;
               }

               if (var2 != 93) {
                  if (var2 != 123) {
                     --this.pos;
                     var1 = this.peekKeyword();
                     if (var1 != 0) {
                        return var1;
                     }

                     var1 = this.peekNumber();
                     if (var1 != 0) {
                        return var1;
                     }

                     if (this.isLiteral(this.buffer[this.pos])) {
                        this.checkLenient();
                        this.peeked = 10;
                        return 10;
                     }

                     throw this.syntaxError("Expected value");
                  }

                  this.peeked = 1;
                  return 1;
               }

               if (var1 == 1) {
                  this.peeked = 4;
                  return 4;
               }
            }

            if (var1 != 1 && var1 != 2) {
               throw this.syntaxError("Unexpected value");
            } else {
               this.checkLenient();
               --this.pos;
               this.peeked = 7;
               return 7;
            }
         } else {
            this.checkLenient();
            this.peeked = 8;
            return 8;
         }
      } else {
         this.peeked = 9;
         return 9;
      }
   }

   public void endArray() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      if (var1 == 4) {
         var1 = this.stackSize - 1;
         this.stackSize = var1;
         int[] var3 = this.pathIndices;
         --var1;
         int var10002 = var3[var1]++;
         this.peeked = 0;
      } else {
         throw new IllegalStateException("Expected END_ARRAY but was " + this.peek() + this.locationString());
      }
   }

   public void endObject() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      if (var1 == 2) {
         var1 = this.stackSize - 1;
         this.stackSize = var1;
         this.pathNames[var1] = null;
         int[] var3 = this.pathIndices;
         --var1;
         int var10002 = var3[var1]++;
         this.peeked = 0;
      } else {
         throw new IllegalStateException("Expected END_OBJECT but was " + this.peek() + this.locationString());
      }
   }

   public String getPath() {
      StringBuilder var4 = (new StringBuilder()).append('$');
      int var2 = this.stackSize;

      for(int var1 = 0; var1 < var2; ++var1) {
         int var3 = this.stack[var1];
         if (var3 != 1 && var3 != 2) {
            if (var3 == 3 || var3 == 4 || var3 == 5) {
               var4.append('.');
               String var5 = this.pathNames[var1];
               if (var5 != null) {
                  var4.append(var5);
               }
            }
         } else {
            var4.append('[').append(this.pathIndices[var1]).append(']');
         }
      }

      return var4.toString();
   }

   public boolean hasNext() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      boolean var3;
      if (var1 != 2 && var1 != 4) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public final boolean isLenient() {
      return this.lenient;
   }

   String locationString() {
      int var3 = this.lineNumber;
      int var2 = this.pos;
      int var1 = this.lineStart;
      return " at line " + (var3 + 1) + " column " + (var2 - var1 + 1) + " path " + this.getPath();
   }

   public boolean nextBoolean() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      int var10002;
      int[] var3;
      if (var1 == 5) {
         this.peeked = 0;
         var3 = this.pathIndices;
         var1 = this.stackSize - 1;
         var10002 = var3[var1]++;
         return true;
      } else if (var1 == 6) {
         this.peeked = 0;
         var3 = this.pathIndices;
         var1 = this.stackSize - 1;
         var10002 = var3[var1]++;
         return false;
      } else {
         throw new IllegalStateException("Expected a boolean but was " + this.peek() + this.locationString());
      }
   }

   public double nextDouble() throws IOException {
      int var5 = this.peeked;
      int var4 = var5;
      if (var5 == 0) {
         var4 = this.doPeek();
      }

      int var10002;
      int[] var6;
      if (var4 == 15) {
         this.peeked = 0;
         var6 = this.pathIndices;
         var4 = this.stackSize - 1;
         var10002 = var6[var4]++;
         return (double)this.peekedLong;
      } else {
         if (var4 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
         } else if (var4 != 8 && var4 != 9) {
            if (var4 == 10) {
               this.peekedString = this.nextUnquotedValue();
            } else if (var4 != 11) {
               throw new IllegalStateException("Expected a double but was " + this.peek() + this.locationString());
            }
         } else {
            char var1;
            if (var4 == 8) {
               var1 = '\'';
            } else {
               var1 = '"';
            }

            this.peekedString = this.nextQuotedValue(var1);
         }

         this.peeked = 11;
         double var2 = Double.parseDouble(this.peekedString);
         if (this.lenient || !Double.isNaN(var2) && !Double.isInfinite(var2)) {
            this.peekedString = null;
            this.peeked = 0;
            var6 = this.pathIndices;
            var4 = this.stackSize - 1;
            var10002 = var6[var4]++;
            return var2;
         } else {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + var2 + this.locationString());
         }
      }
   }

   public int nextInt() throws IOException {
      int var5 = this.peeked;
      int var4 = var5;
      if (var5 == 0) {
         var4 = this.doPeek();
      }

      int var10002;
      int[] var8;
      if (var4 == 15) {
         long var6 = this.peekedLong;
         var5 = (int)var6;
         if (var6 == (long)var5) {
            this.peeked = 0;
            var8 = this.pathIndices;
            var4 = this.stackSize - 1;
            var10002 = var8[var4]++;
            return var5;
         } else {
            throw new NumberFormatException("Expected an int but was " + this.peekedLong + this.locationString());
         }
      } else {
         if (var4 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
         } else {
            label58: {
               if (var4 != 8 && var4 != 9 && var4 != 10) {
                  throw new IllegalStateException("Expected an int but was " + this.peek() + this.locationString());
               }

               if (var4 == 10) {
                  this.peekedString = this.nextUnquotedValue();
               } else {
                  char var1;
                  if (var4 == 8) {
                     var1 = '\'';
                  } else {
                     var1 = '"';
                  }

                  this.peekedString = this.nextQuotedValue(var1);
               }

               try {
                  var4 = Integer.parseInt(this.peekedString);
                  this.peeked = 0;
                  var8 = this.pathIndices;
                  var5 = this.stackSize - 1;
               } catch (NumberFormatException var9) {
                  break label58;
               }

               var10002 = var8[var5]++;
               return var4;
            }
         }

         this.peeked = 11;
         double var2 = Double.parseDouble(this.peekedString);
         var4 = (int)var2;
         if ((double)var4 == var2) {
            this.peekedString = null;
            this.peeked = 0;
            var8 = this.pathIndices;
            var5 = this.stackSize - 1;
            var10002 = var8[var5]++;
            return var4;
         } else {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + this.locationString());
         }
      }
   }

   public long nextLong() throws IOException {
      int var5 = this.peeked;
      int var4 = var5;
      if (var5 == 0) {
         var4 = this.doPeek();
      }

      int var10002;
      int[] var8;
      if (var4 == 15) {
         this.peeked = 0;
         var8 = this.pathIndices;
         var4 = this.stackSize - 1;
         var10002 = var8[var4]++;
         return this.peekedLong;
      } else {
         long var6;
         if (var4 == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
         } else {
            label54: {
               if (var4 != 8 && var4 != 9 && var4 != 10) {
                  throw new IllegalStateException("Expected a long but was " + this.peek() + this.locationString());
               }

               if (var4 == 10) {
                  this.peekedString = this.nextUnquotedValue();
               } else {
                  char var1;
                  if (var4 == 8) {
                     var1 = '\'';
                  } else {
                     var1 = '"';
                  }

                  this.peekedString = this.nextQuotedValue(var1);
               }

               try {
                  var6 = Long.parseLong(this.peekedString);
                  this.peeked = 0;
                  var8 = this.pathIndices;
                  var4 = this.stackSize - 1;
               } catch (NumberFormatException var9) {
                  break label54;
               }

               var10002 = var8[var4]++;
               return var6;
            }
         }

         this.peeked = 11;
         double var2 = Double.parseDouble(this.peekedString);
         var6 = (long)var2;
         if ((double)var6 == var2) {
            this.peekedString = null;
            this.peeked = 0;
            var8 = this.pathIndices;
            var4 = this.stackSize - 1;
            var10002 = var8[var4]++;
            return var6;
         } else {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + this.locationString());
         }
      }
   }

   public String nextName() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      String var3;
      if (var1 == 14) {
         var3 = this.nextUnquotedValue();
      } else if (var1 == 12) {
         var3 = this.nextQuotedValue('\'');
      } else {
         if (var1 != 13) {
            throw new IllegalStateException("Expected a name but was " + this.peek() + this.locationString());
         }

         var3 = this.nextQuotedValue('"');
      }

      this.peeked = 0;
      this.pathNames[this.stackSize - 1] = var3;
      return var3;
   }

   public void nextNull() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      if (var1 == 7) {
         this.peeked = 0;
         int[] var3 = this.pathIndices;
         var1 = this.stackSize - 1;
         int var10002 = var3[var1]++;
      } else {
         throw new IllegalStateException("Expected null but was " + this.peek() + this.locationString());
      }
   }

   public String nextString() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      String var3;
      if (var1 == 10) {
         var3 = this.nextUnquotedValue();
      } else if (var1 == 8) {
         var3 = this.nextQuotedValue('\'');
      } else if (var1 == 9) {
         var3 = this.nextQuotedValue('"');
      } else if (var1 == 11) {
         var3 = this.peekedString;
         this.peekedString = null;
      } else if (var1 == 15) {
         var3 = Long.toString(this.peekedLong);
      } else {
         if (var1 != 16) {
            throw new IllegalStateException("Expected a string but was " + this.peek() + this.locationString());
         }

         var3 = new String(this.buffer, this.pos, this.peekedNumberLength);
         this.pos += this.peekedNumberLength;
      }

      this.peeked = 0;
      int[] var4 = this.pathIndices;
      var1 = this.stackSize - 1;
      int var10002 = var4[var1]++;
      return var3;
   }

   public JsonToken peek() throws IOException {
      int var2 = this.peeked;
      int var1 = var2;
      if (var2 == 0) {
         var1 = this.doPeek();
      }

      switch (var1) {
         case 1:
            return JsonToken.BEGIN_OBJECT;
         case 2:
            return JsonToken.END_OBJECT;
         case 3:
            return JsonToken.BEGIN_ARRAY;
         case 4:
            return JsonToken.END_ARRAY;
         case 5:
         case 6:
            return JsonToken.BOOLEAN;
         case 7:
            return JsonToken.NULL;
         case 8:
         case 9:
         case 10:
         case 11:
            return JsonToken.STRING;
         case 12:
         case 13:
         case 14:
            return JsonToken.NAME;
         case 15:
         case 16:
            return JsonToken.NUMBER;
         case 17:
            return JsonToken.END_DOCUMENT;
         default:
            throw new AssertionError();
      }
   }

   public final void setLenient(boolean var1) {
      this.lenient = var1;
   }

   public void skipValue() throws IOException {
      int var2 = 0;

      int var1;
      do {
         var1 = this.peeked;
         int var3 = var1;
         if (var1 == 0) {
            var3 = this.doPeek();
         }

         label53: {
            if (var3 == 3) {
               this.push(1);
            } else {
               if (var3 != 1) {
                  if (var3 == 4) {
                     --this.stackSize;
                  } else {
                     if (var3 != 2) {
                        if (var3 != 14 && var3 != 10) {
                           if (var3 != 8 && var3 != 12) {
                              if (var3 != 9 && var3 != 13) {
                                 var1 = var2;
                                 if (var3 == 16) {
                                    this.pos += this.peekedNumberLength;
                                    var1 = var2;
                                 }
                                 break label53;
                              }

                              this.skipQuotedValue('"');
                              var1 = var2;
                              break label53;
                           }

                           this.skipQuotedValue('\'');
                           var1 = var2;
                           break label53;
                        }

                        this.skipUnquotedValue();
                        var1 = var2;
                        break label53;
                     }

                     --this.stackSize;
                  }

                  var1 = var2 - 1;
                  break label53;
               }

               this.push(3);
            }

            var1 = var2 + 1;
         }

         this.peeked = 0;
         var2 = var1;
      } while(var1 != 0);

      int[] var4 = this.pathIndices;
      var2 = this.stackSize;
      var1 = var2 - 1;
      int var10002 = var4[var1]++;
      this.pathNames[var2 - 1] = "null";
   }

   public String toString() {
      return this.getClass().getSimpleName() + this.locationString();
   }
}
