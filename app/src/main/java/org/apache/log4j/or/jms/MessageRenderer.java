package org.apache.log4j.or.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.or.ObjectRenderer;

public class MessageRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      if (var1 instanceof Message) {
         StringBuffer var3 = new StringBuffer();
         Message var4 = (Message)var1;

         JMSException var10000;
         label45: {
            boolean var10001;
            int var2;
            try {
               var3.append("DeliveryMode=");
               var2 = var4.getJMSDeliveryMode();
            } catch (JMSException var7) {
               var10000 = var7;
               var10001 = false;
               break label45;
            }

            String var8;
            if (var2 != 1) {
               if (var2 != 2) {
                  var8 = "UNKNOWN";
               } else {
                  var8 = "PERSISTENT";
               }
            } else {
               var8 = "NON_PERSISTENT";
            }

            try {
               var3.append(var8);
            } catch (JMSException var6) {
               var10000 = var6;
               var10001 = false;
               break label45;
            }

            try {
               var3.append(", CorrelationID=");
               var3.append(var4.getJMSCorrelationID());
               var3.append(", Destination=");
               var3.append(var4.getJMSDestination());
               var3.append(", Expiration=");
               var3.append(var4.getJMSExpiration());
               var3.append(", MessageID=");
               var3.append(var4.getJMSMessageID());
               var3.append(", Priority=");
               var3.append(var4.getJMSPriority());
               var3.append(", Redelivered=");
               var3.append(var4.getJMSRedelivered());
               var3.append(", ReplyTo=");
               var3.append(var4.getJMSReplyTo());
               var3.append(", Timestamp=");
               var3.append(var4.getJMSTimestamp());
               var3.append(", Type=");
               var3.append(var4.getJMSType());
               return var3.toString();
            } catch (JMSException var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         JMSException var9 = var10000;
         LogLog.error("Could not parse Message.", var9);
         return var3.toString();
      } else {
         return var1.toString();
      }
   }
}
