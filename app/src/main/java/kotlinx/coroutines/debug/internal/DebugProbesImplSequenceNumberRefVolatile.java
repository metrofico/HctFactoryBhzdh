package kotlinx.coroutines.debug.internal;

public class DebugProbesImplSequenceNumberRefVolatile {
   volatile long sequenceNumber;

   public DebugProbesImplSequenceNumberRefVolatile(long var1) {
      this.sequenceNumber = var1;
   }
}
