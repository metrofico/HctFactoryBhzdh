package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;

@Metadata(
   d1 = {"\u0000p\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001d\u001a'\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007\u001a\u0012\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b\u001a\u0012\u0010\u0000\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0012\u0010\u0000\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n\u001a'\u0010\u000b\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u0012\u0010\u000b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u0012\u0010\u000b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0012\u0010\u000b\u001a\u00020\n*\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a3\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00012\b\u0010\f\u001a\u0004\u0018\u0001H\u0001¢\u0006\u0002\u0010\u000e\u001a/\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a\u001a\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u001a\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u001a\u0010\r\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u001a\u0010\r\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0018\u0010\r\u001a\u00020\b*\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0012\u001a\u001a\u0010\r\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0018\u0010\r\u001a\u00020\t*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u001a\u001a\u0010\r\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0087\n¢\u0006\u0002\u0010\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0087\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0087\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b \u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020!2\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0087\n¢\u0006\u0002\u0010\"\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020#2\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0087\n¢\u0006\u0002\u0010$\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020)*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\f\u0010*\u001a\u00020\u0018*\u00020)H\u0007\u001a\f\u0010*\u001a\u00020\b*\u00020&H\u0007\u001a\f\u0010*\u001a\u00020\t*\u00020(H\u0007\u001a\u0013\u0010+\u001a\u0004\u0018\u00010\u0018*\u00020)H\u0007¢\u0006\u0002\u0010,\u001a\u0013\u0010+\u001a\u0004\u0018\u00010\b*\u00020&H\u0007¢\u0006\u0002\u0010-\u001a\u0013\u0010+\u001a\u0004\u0018\u00010\t*\u00020(H\u0007¢\u0006\u0002\u0010.\u001a\f\u0010/\u001a\u00020\u0018*\u00020)H\u0007\u001a\f\u0010/\u001a\u00020\b*\u00020&H\u0007\u001a\f\u0010/\u001a\u00020\t*\u00020(H\u0007\u001a\u0013\u00100\u001a\u0004\u0018\u00010\u0018*\u00020)H\u0007¢\u0006\u0002\u0010,\u001a\u0013\u00100\u001a\u0004\u0018\u00010\b*\u00020&H\u0007¢\u0006\u0002\u0010-\u001a\u0013\u00100\u001a\u0004\u0018\u00010\t*\u00020(H\u0007¢\u0006\u0002\u0010.\u001a\r\u00101\u001a\u00020\u0018*\u00020\u0016H\u0087\b\u001a\u0014\u00101\u001a\u00020\u0018*\u00020\u00162\u0006\u00101\u001a\u000202H\u0007\u001a\r\u00101\u001a\u00020\b*\u00020!H\u0087\b\u001a\u0014\u00101\u001a\u00020\b*\u00020!2\u0006\u00101\u001a\u000202H\u0007\u001a\r\u00101\u001a\u00020\t*\u00020#H\u0087\b\u001a\u0014\u00101\u001a\u00020\t*\u00020#2\u0006\u00101\u001a\u000202H\u0007\u001a\u0014\u00103\u001a\u0004\u0018\u00010\u0018*\u00020\u0016H\u0087\b¢\u0006\u0002\u00104\u001a\u001b\u00103\u001a\u0004\u0018\u00010\u0018*\u00020\u00162\u0006\u00101\u001a\u000202H\u0007¢\u0006\u0002\u00105\u001a\u0014\u00103\u001a\u0004\u0018\u00010\b*\u00020!H\u0087\b¢\u0006\u0002\u00106\u001a\u001b\u00103\u001a\u0004\u0018\u00010\b*\u00020!2\u0006\u00101\u001a\u000202H\u0007¢\u0006\u0002\u00107\u001a\u0014\u00103\u001a\u0004\u0018\u00010\t*\u00020#H\u0087\b¢\u0006\u0002\u00108\u001a\u001b\u00103\u001a\u0004\u0018\u00010\t*\u00020#2\u0006\u00101\u001a\u000202H\u0007¢\u0006\u0002\u00109\u001a\n\u0010:\u001a\u00020)*\u00020)\u001a\n\u0010:\u001a\u00020&*\u00020&\u001a\n\u0010:\u001a\u00020(*\u00020(\u001a\u0015\u0010;\u001a\u00020)*\u00020)2\u0006\u0010;\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010;\u001a\u00020&*\u00020&2\u0006\u0010;\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010;\u001a\u00020(*\u00020(2\u0006\u0010;\u001a\u00020\tH\u0086\u0004\u001a\u0013\u0010<\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0000¢\u0006\u0002\u0010=\u001a\u0013\u0010<\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H\u0000¢\u0006\u0002\u0010>\u001a\u0013\u0010<\u001a\u0004\u0018\u00010\u0005*\u00020\bH\u0000¢\u0006\u0002\u0010?\u001a\u0013\u0010<\u001a\u0004\u0018\u00010\u0005*\u00020\tH\u0000¢\u0006\u0002\u0010@\u001a\u0013\u0010<\u001a\u0004\u0018\u00010\u0005*\u00020\nH\u0000¢\u0006\u0002\u0010A\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\b*\u00020\u0006H\u0000¢\u0006\u0002\u0010C\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\b*\u00020\u0007H\u0000¢\u0006\u0002\u0010D\u001a\u0013\u0010B\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000¢\u0006\u0002\u0010E\u001a\u0013\u0010F\u001a\u0004\u0018\u00010\t*\u00020\u0006H\u0000¢\u0006\u0002\u0010G\u001a\u0013\u0010F\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0000¢\u0006\u0002\u0010H\u001a\u0013\u0010I\u001a\u0004\u0018\u00010\n*\u00020\u0006H\u0000¢\u0006\u0002\u0010J\u001a\u0013\u0010I\u001a\u0004\u0018\u00010\n*\u00020\u0007H\u0000¢\u0006\u0002\u0010K\u001a\u0013\u0010I\u001a\u0004\u0018\u00010\n*\u00020\bH\u0000¢\u0006\u0002\u0010L\u001a\u0013\u0010I\u001a\u0004\u0018\u00010\n*\u00020\tH\u0000¢\u0006\u0002\u0010M\u001a\u0015\u0010N\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020\u0016*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020#*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0086\u0004\u001a\u0015\u0010N\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0086\u0004¨\u0006O"},
   d2 = {"coerceAtLeast", "T", "", "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "contains", "", "Lkotlin/ranges/CharRange;", "element", "", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", "value", "byteRangeContains", "doubleRangeContains", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "Lkotlin/ranges/IntRange;", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "Lkotlin/ranges/LongRange;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "downTo", "Lkotlin/ranges/IntProgression;", "to", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/CharProgression;", "first", "firstOrNull", "(Lkotlin/ranges/CharProgression;)Ljava/lang/Character;", "(Lkotlin/ranges/IntProgression;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongProgression;)Ljava/lang/Long;", "last", "lastOrNull", "random", "Lkotlin/random/Random;", "randomOrNull", "(Lkotlin/ranges/CharRange;)Ljava/lang/Character;", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)Ljava/lang/Character;", "(Lkotlin/ranges/IntRange;)Ljava/lang/Integer;", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;)Ljava/lang/Long;", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)Ljava/lang/Long;", "reversed", "step", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/ranges/RangesKt"
)
class RangesKt___RangesKt extends RangesKt__RangesKt {
   public RangesKt___RangesKt() {
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean byteRangeContains(ClosedRange var0, double var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Byte var4 = RangesKt.toByteExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean byteRangeContains(ClosedRange var0, float var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Byte var3 = RangesKt.toByteExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean byteRangeContains(ClosedRange var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Byte var3 = RangesKt.toByteExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean byteRangeContains(ClosedRange var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Byte var4 = RangesKt.toByteExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   public static final boolean byteRangeContains(ClosedRange var0, short var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Byte var3 = RangesKt.toByteExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final byte coerceAtLeast(byte var0, byte var1) {
      byte var2 = var0;
      if (var0 < var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final double coerceAtLeast(double var0, double var2) {
      double var4 = var0;
      if (var0 < var2) {
         var4 = var2;
      }

      return var4;
   }

   public static final float coerceAtLeast(float var0, float var1) {
      float var2 = var0;
      if (var0 < var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final int coerceAtLeast(int var0, int var1) {
      int var2 = var0;
      if (var0 < var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final long coerceAtLeast(long var0, long var2) {
      long var4 = var0;
      if (var0 < var2) {
         var4 = var2;
      }

      return var4;
   }

   public static final Comparable coerceAtLeast(Comparable var0, Comparable var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "minimumValue");
      Comparable var2 = var0;
      if (var0.compareTo(var1) < 0) {
         var2 = var1;
      }

      return var2;
   }

   public static final short coerceAtLeast(short var0, short var1) {
      short var2 = var0;
      if (var0 < var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final byte coerceAtMost(byte var0, byte var1) {
      byte var2 = var0;
      if (var0 > var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final double coerceAtMost(double var0, double var2) {
      double var4 = var0;
      if (var0 > var2) {
         var4 = var2;
      }

      return var4;
   }

   public static final float coerceAtMost(float var0, float var1) {
      float var2 = var0;
      if (var0 > var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final int coerceAtMost(int var0, int var1) {
      int var2 = var0;
      if (var0 > var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final long coerceAtMost(long var0, long var2) {
      long var4 = var0;
      if (var0 > var2) {
         var4 = var2;
      }

      return var4;
   }

   public static final Comparable coerceAtMost(Comparable var0, Comparable var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "maximumValue");
      Comparable var2 = var0;
      if (var0.compareTo(var1) > 0) {
         var2 = var1;
      }

      return var2;
   }

   public static final short coerceAtMost(short var0, short var1) {
      short var2 = var0;
      if (var0 > var1) {
         var2 = var1;
      }

      return var2;
   }

   public static final byte coerceIn(byte var0, byte var1, byte var2) {
      if (var1 <= var2) {
         if (var0 < var1) {
            return var1;
         } else {
            return var0 > var2 ? var2 : var0;
         }
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var2 + " is less than minimum " + var1 + '.');
      }
   }

   public static final double coerceIn(double var0, double var2, double var4) {
      if (!(var2 > var4)) {
         if (var0 < var2) {
            return var2;
         } else {
            return var0 > var4 ? var4 : var0;
         }
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var4 + " is less than minimum " + var2 + '.');
      }
   }

   public static final float coerceIn(float var0, float var1, float var2) {
      if (!(var1 > var2)) {
         if (var0 < var1) {
            return var1;
         } else {
            return var0 > var2 ? var2 : var0;
         }
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var2 + " is less than minimum " + var1 + '.');
      }
   }

   public static final int coerceIn(int var0, int var1, int var2) {
      if (var1 <= var2) {
         if (var0 < var1) {
            return var1;
         } else {
            return var0 > var2 ? var2 : var0;
         }
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var2 + " is less than minimum " + var1 + '.');
      }
   }

   public static final int coerceIn(int var0, ClosedRange var1) {
      Intrinsics.checkNotNullParameter(var1, "range");
      if (var1 instanceof ClosedFloatingPointRange) {
         return ((Number)RangesKt.coerceIn((Comparable)var0, (ClosedFloatingPointRange)var1)).intValue();
      } else if (!var1.isEmpty()) {
         int var2;
         if (var0 < ((Number)var1.getStart()).intValue()) {
            var2 = ((Number)var1.getStart()).intValue();
         } else {
            var2 = var0;
            if (var0 > ((Number)var1.getEndInclusive()).intValue()) {
               var2 = ((Number)var1.getEndInclusive()).intValue();
            }
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + var1 + '.');
      }
   }

   public static final long coerceIn(long var0, long var2, long var4) {
      if (var2 <= var4) {
         if (var0 < var2) {
            return var2;
         } else {
            return var0 > var4 ? var4 : var0;
         }
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var4 + " is less than minimum " + var2 + '.');
      }
   }

   public static final long coerceIn(long var0, ClosedRange var2) {
      Intrinsics.checkNotNullParameter(var2, "range");
      if (var2 instanceof ClosedFloatingPointRange) {
         return ((Number)RangesKt.coerceIn((Comparable)var0, (ClosedFloatingPointRange)var2)).longValue();
      } else if (!var2.isEmpty()) {
         long var3;
         if (var0 < ((Number)var2.getStart()).longValue()) {
            var3 = ((Number)var2.getStart()).longValue();
         } else {
            var3 = var0;
            if (var0 > ((Number)var2.getEndInclusive()).longValue()) {
               var3 = ((Number)var2.getEndInclusive()).longValue();
            }
         }

         return var3;
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + var2 + '.');
      }
   }

   public static final Comparable coerceIn(Comparable var0, Comparable var1, Comparable var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var1 != null && var2 != null) {
         if (var1.compareTo(var2) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var2 + " is less than minimum " + var1 + '.');
         }

         if (var0.compareTo(var1) < 0) {
            return var1;
         }

         if (var0.compareTo(var2) > 0) {
            return var2;
         }
      } else {
         if (var1 != null && var0.compareTo(var1) < 0) {
            return var1;
         }

         if (var2 != null && var0.compareTo(var2) > 0) {
            return var2;
         }
      }

      return var0;
   }

   public static final Comparable coerceIn(Comparable var0, ClosedFloatingPointRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      if (var1.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + var1 + '.');
      } else {
         Comparable var2;
         if (var1.lessThanOrEquals(var0, var1.getStart()) && !var1.lessThanOrEquals(var1.getStart(), var0)) {
            var2 = var1.getStart();
         } else {
            var2 = var0;
            if (var1.lessThanOrEquals(var1.getEndInclusive(), var0)) {
               var2 = var0;
               if (!var1.lessThanOrEquals(var0, var1.getEndInclusive())) {
                  var2 = var1.getEndInclusive();
               }
            }
         }

         return var2;
      }
   }

   public static final Comparable coerceIn(Comparable var0, ClosedRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      if (var1 instanceof ClosedFloatingPointRange) {
         return RangesKt.coerceIn(var0, (ClosedFloatingPointRange)var1);
      } else if (!var1.isEmpty()) {
         Comparable var2;
         if (var0.compareTo(var1.getStart()) < 0) {
            var2 = var1.getStart();
         } else {
            var2 = var0;
            if (var0.compareTo(var1.getEndInclusive()) > 0) {
               var2 = var1.getEndInclusive();
            }
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + var1 + '.');
      }
   }

   public static final short coerceIn(short var0, short var1, short var2) {
      if (var1 <= var2) {
         if (var0 < var1) {
            return var1;
         } else {
            return var0 > var2 ? var2 : var0;
         }
      } else {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var2 + " is less than minimum " + var1 + '.');
      }
   }

   private static final boolean contains(CharRange var0, Character var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var2;
      if (var1 != null && var0.contains(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static final boolean contains(IntRange var0, Integer var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var2;
      if (var1 != null && var0.contains(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static final boolean contains(LongRange var0, Long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var2;
      if (var1 != null && var0.contains(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean doubleRangeContains(ClosedRange var0, byte var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(double)var1);
   }

   public static final boolean doubleRangeContains(ClosedRange var0, float var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(double)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean doubleRangeContains(ClosedRange var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(double)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean doubleRangeContains(ClosedRange var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(double)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean doubleRangeContains(ClosedRange var0, short var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(double)var1);
   }

   public static final CharProgression downTo(char var0, char var1) {
      return CharProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(byte var0, byte var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(byte var0, int var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(byte var0, short var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(int var0, byte var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(int var0, int var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(int var0, short var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(short var0, byte var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(short var0, int var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final IntProgression downTo(short var0, short var1) {
      return IntProgression.Companion.fromClosedRange(var0, var1, -1);
   }

   public static final LongProgression downTo(byte var0, long var1) {
      return LongProgression.Companion.fromClosedRange((long)var0, var1, -1L);
   }

   public static final LongProgression downTo(int var0, long var1) {
      return LongProgression.Companion.fromClosedRange((long)var0, var1, -1L);
   }

   public static final LongProgression downTo(long var0, byte var2) {
      return LongProgression.Companion.fromClosedRange(var0, (long)var2, -1L);
   }

   public static final LongProgression downTo(long var0, int var2) {
      return LongProgression.Companion.fromClosedRange(var0, (long)var2, -1L);
   }

   public static final LongProgression downTo(long var0, long var2) {
      return LongProgression.Companion.fromClosedRange(var0, var2, -1L);
   }

   public static final LongProgression downTo(long var0, short var2) {
      return LongProgression.Companion.fromClosedRange(var0, (long)var2, -1L);
   }

   public static final LongProgression downTo(short var0, long var1) {
      return LongProgression.Companion.fromClosedRange((long)var0, var1, -1L);
   }

   public static final char first(CharProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var0.isEmpty()) {
         return var0.getFirst();
      } else {
         throw new NoSuchElementException("Progression " + var0 + " is empty.");
      }
   }

   public static final int first(IntProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var0.isEmpty()) {
         return var0.getFirst();
      } else {
         throw new NoSuchElementException("Progression " + var0 + " is empty.");
      }
   }

   public static final long first(LongProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var0.isEmpty()) {
         return var0.getFirst();
      } else {
         throw new NoSuchElementException("Progression " + var0 + " is empty.");
      }
   }

   public static final Character firstOrNull(CharProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Character var1;
      if (var0.isEmpty()) {
         var1 = null;
      } else {
         var1 = var0.getFirst();
      }

      return var1;
   }

   public static final Integer firstOrNull(IntProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var1;
      if (var0.isEmpty()) {
         var1 = null;
      } else {
         var1 = var0.getFirst();
      }

      return var1;
   }

   public static final Long firstOrNull(LongProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Long var1;
      if (var0.isEmpty()) {
         var1 = null;
      } else {
         var1 = var0.getFirst();
      }

      return var1;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean floatRangeContains(ClosedRange var0, byte var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(float)var1);
   }

   public static final boolean floatRangeContains(ClosedRange var0, double var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(float)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean floatRangeContains(ClosedRange var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(float)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean floatRangeContains(ClosedRange var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(float)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean floatRangeContains(ClosedRange var0, short var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(float)var1);
   }

   public static final boolean intRangeContains(ClosedRange var0, byte var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)Integer.valueOf(var1));
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean intRangeContains(ClosedRange var0, double var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var4 = RangesKt.toIntExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean intRangeContains(ClosedRange var0, float var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var3 = RangesKt.toIntExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean intRangeContains(ClosedRange var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var4 = RangesKt.toIntExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   public static final boolean intRangeContains(ClosedRange var0, short var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)Integer.valueOf(var1));
   }

   public static final char last(CharProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var0.isEmpty()) {
         return var0.getLast();
      } else {
         throw new NoSuchElementException("Progression " + var0 + " is empty.");
      }
   }

   public static final int last(IntProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var0.isEmpty()) {
         return var0.getLast();
      } else {
         throw new NoSuchElementException("Progression " + var0 + " is empty.");
      }
   }

   public static final long last(LongProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var0.isEmpty()) {
         return var0.getLast();
      } else {
         throw new NoSuchElementException("Progression " + var0 + " is empty.");
      }
   }

   public static final Character lastOrNull(CharProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Character var1;
      if (var0.isEmpty()) {
         var1 = null;
      } else {
         var1 = var0.getLast();
      }

      return var1;
   }

   public static final Integer lastOrNull(IntProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var1;
      if (var0.isEmpty()) {
         var1 = null;
      } else {
         var1 = var0.getLast();
      }

      return var1;
   }

   public static final Long lastOrNull(LongProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Long var1;
      if (var0.isEmpty()) {
         var1 = null;
      } else {
         var1 = var0.getLast();
      }

      return var1;
   }

   public static final boolean longRangeContains(ClosedRange var0, byte var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(long)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean longRangeContains(ClosedRange var0, double var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Long var4 = RangesKt.toLongExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean longRangeContains(ClosedRange var0, float var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Long var3 = RangesKt.toLongExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean longRangeContains(ClosedRange var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(long)var1);
   }

   public static final boolean longRangeContains(ClosedRange var0, short var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(long)var1);
   }

   private static final char random(CharRange var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return RangesKt.random(var0, (Random)Random.Default);
   }

   public static final char random(CharRange var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");

      int var2;
      try {
         var2 = var1.nextInt(var0.getFirst(), var0.getLast() + 1);
      } catch (IllegalArgumentException var3) {
         throw new NoSuchElementException(var3.getMessage());
      }

      return (char)var2;
   }

   private static final int random(IntRange var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return RangesKt.random(var0, (Random)Random.Default);
   }

   public static final int random(IntRange var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");

      try {
         int var2 = RandomKt.nextInt(var1, var0);
         return var2;
      } catch (IllegalArgumentException var3) {
         throw new NoSuchElementException(var3.getMessage());
      }
   }

   private static final long random(LongRange var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return RangesKt.random(var0, (Random)Random.Default);
   }

   public static final long random(LongRange var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");

      try {
         long var2 = RandomKt.nextLong(var1, var0);
         return var2;
      } catch (IllegalArgumentException var4) {
         throw new NoSuchElementException(var4.getMessage());
      }
   }

   private static final Character randomOrNull(CharRange var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return RangesKt.randomOrNull(var0, (Random)Random.Default);
   }

   public static final Character randomOrNull(CharRange var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      return var0.isEmpty() ? null : (char)var1.nextInt(var0.getFirst(), var0.getLast() + 1);
   }

   private static final Integer randomOrNull(IntRange var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return RangesKt.randomOrNull(var0, (Random)Random.Default);
   }

   public static final Integer randomOrNull(IntRange var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      return var0.isEmpty() ? null : RandomKt.nextInt(var1, var0);
   }

   private static final Long randomOrNull(LongRange var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return RangesKt.randomOrNull(var0, (Random)Random.Default);
   }

   public static final Long randomOrNull(LongRange var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      return var0.isEmpty() ? null : RandomKt.nextLong(var1, var0);
   }

   public static final CharProgression reversed(CharProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return CharProgression.Companion.fromClosedRange(var0.getLast(), var0.getFirst(), -var0.getStep());
   }

   public static final IntProgression reversed(IntProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return IntProgression.Companion.fromClosedRange(var0.getLast(), var0.getFirst(), -var0.getStep());
   }

   public static final LongProgression reversed(LongProgression var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return LongProgression.Companion.fromClosedRange(var0.getLast(), var0.getFirst(), -var0.getStep());
   }

   public static final boolean shortRangeContains(ClosedRange var0, byte var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.contains((Comparable)(short)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean shortRangeContains(ClosedRange var0, double var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Short var4 = RangesKt.toShortExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.4",
      hiddenSince = "1.5",
      warningSince = "1.3"
   )
   public static final boolean shortRangeContains(ClosedRange var0, float var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Short var3 = RangesKt.toShortExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean shortRangeContains(ClosedRange var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Short var3 = RangesKt.toShortExactOrNull(var1);
      boolean var2;
      if (var3 != null) {
         var2 = var0.contains((Comparable)var3);
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean shortRangeContains(ClosedRange var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Short var4 = RangesKt.toShortExactOrNull(var1);
      boolean var3;
      if (var4 != null) {
         var3 = var0.contains((Comparable)var4);
      } else {
         var3 = false;
      }

      return var3;
   }

   public static final CharProgression step(CharProgression var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var4;
      if (var1 > 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      RangesKt.checkStepIsPositive(var4, (Number)var1);
      CharProgression.Companion var5 = CharProgression.Companion;
      char var3 = var0.getFirst();
      char var2 = var0.getLast();
      if (var0.getStep() <= 0) {
         var1 = -var1;
      }

      return var5.fromClosedRange(var3, var2, var1);
   }

   public static final IntProgression step(IntProgression var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var4;
      if (var1 > 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      RangesKt.checkStepIsPositive(var4, (Number)var1);
      IntProgression.Companion var5 = IntProgression.Companion;
      int var3 = var0.getFirst();
      int var2 = var0.getLast();
      if (var0.getStep() <= 0) {
         var1 = -var1;
      }

      return var5.fromClosedRange(var3, var2, var1);
   }

   public static final LongProgression step(LongProgression var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var7;
      if (var1 > 0L) {
         var7 = true;
      } else {
         var7 = false;
      }

      RangesKt.checkStepIsPositive(var7, (Number)var1);
      LongProgression.Companion var8 = LongProgression.Companion;
      long var5 = var0.getFirst();
      long var3 = var0.getLast();
      if (var0.getStep() <= 0L) {
         var1 = -var1;
      }

      return var8.fromClosedRange(var5, var3, var1);
   }

   public static final Byte toByteExactOrNull(double var0) {
      boolean var3 = false;
      boolean var2 = var3;
      if (var0 <= 127.0) {
         var2 = var3;
         if (-128.0 <= var0) {
            var2 = true;
         }
      }

      Byte var4;
      if (var2) {
         var4 = (byte)((int)var0);
      } else {
         var4 = null;
      }

      return var4;
   }

   public static final Byte toByteExactOrNull(float var0) {
      boolean var2 = false;
      boolean var1 = var2;
      if (var0 <= 127.0F) {
         var1 = var2;
         if (-128.0F <= var0) {
            var1 = true;
         }
      }

      Byte var3;
      if (var1) {
         var3 = (byte)((int)var0);
      } else {
         var3 = null;
      }

      return var3;
   }

   public static final Byte toByteExactOrNull(int var0) {
      Byte var1;
      if ((new IntRange(-128, 127)).contains(var0)) {
         var1 = (byte)var0;
      } else {
         var1 = null;
      }

      return var1;
   }

   public static final Byte toByteExactOrNull(long var0) {
      Byte var2;
      if ((new LongRange(-128L, 127L)).contains(var0)) {
         var2 = (byte)((int)var0);
      } else {
         var2 = null;
      }

      return var2;
   }

   public static final Byte toByteExactOrNull(short var0) {
      Byte var1;
      if (RangesKt.intRangeContains((ClosedRange)(new IntRange(-128, 127)), var0)) {
         var1 = (byte)var0;
      } else {
         var1 = null;
      }

      return var1;
   }

   public static final Integer toIntExactOrNull(double var0) {
      boolean var3 = false;
      boolean var2 = var3;
      if (var0 <= 2.147483647E9) {
         var2 = var3;
         if (-2.147483648E9 <= var0) {
            var2 = true;
         }
      }

      Integer var4;
      if (var2) {
         var4 = (int)var0;
      } else {
         var4 = null;
      }

      return var4;
   }

   public static final Integer toIntExactOrNull(float var0) {
      boolean var2 = false;
      boolean var1 = var2;
      if (var0 <= 2.1474836E9F) {
         var1 = var2;
         if (-2.1474836E9F <= var0) {
            var1 = true;
         }
      }

      Integer var3;
      if (var1) {
         var3 = (int)var0;
      } else {
         var3 = null;
      }

      return var3;
   }

   public static final Integer toIntExactOrNull(long var0) {
      Integer var2;
      if ((new LongRange(-2147483648L, 2147483647L)).contains(var0)) {
         var2 = (int)var0;
      } else {
         var2 = null;
      }

      return var2;
   }

   public static final Long toLongExactOrNull(double var0) {
      boolean var3 = false;
      boolean var2 = var3;
      if (var0 <= 9.223372036854776E18) {
         var2 = var3;
         if (-9.223372036854776E18 <= var0) {
            var2 = true;
         }
      }

      Long var4;
      if (var2) {
         var4 = (long)var0;
      } else {
         var4 = null;
      }

      return var4;
   }

   public static final Long toLongExactOrNull(float var0) {
      boolean var2 = false;
      boolean var1 = var2;
      if (var0 <= 9.223372E18F) {
         var1 = var2;
         if (-9.223372E18F <= var0) {
            var1 = true;
         }
      }

      Long var3;
      if (var1) {
         var3 = (long)var0;
      } else {
         var3 = null;
      }

      return var3;
   }

   public static final Short toShortExactOrNull(double var0) {
      boolean var3 = false;
      boolean var2 = var3;
      if (var0 <= 32767.0) {
         var2 = var3;
         if (-32768.0 <= var0) {
            var2 = true;
         }
      }

      Short var4;
      if (var2) {
         var4 = (short)((int)var0);
      } else {
         var4 = null;
      }

      return var4;
   }

   public static final Short toShortExactOrNull(float var0) {
      boolean var2 = false;
      boolean var1 = var2;
      if (var0 <= 32767.0F) {
         var1 = var2;
         if (-32768.0F <= var0) {
            var1 = true;
         }
      }

      Short var3;
      if (var1) {
         var3 = (short)((int)var0);
      } else {
         var3 = null;
      }

      return var3;
   }

   public static final Short toShortExactOrNull(int var0) {
      Short var1;
      if ((new IntRange(-32768, 32767)).contains(var0)) {
         var1 = (short)var0;
      } else {
         var1 = null;
      }

      return var1;
   }

   public static final Short toShortExactOrNull(long var0) {
      Short var2;
      if ((new LongRange(-32768L, 32767L)).contains(var0)) {
         var2 = (short)((int)var0);
      } else {
         var2 = null;
      }

      return var2;
   }

   public static final CharRange until(char var0, char var1) {
      return Intrinsics.compare(var1, 0) <= 0 ? CharRange.Companion.getEMPTY() : new CharRange(var0, (char)(var1 - 1));
   }

   public static final IntRange until(byte var0, byte var1) {
      return new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(byte var0, int var1) {
      return var1 <= Integer.MIN_VALUE ? IntRange.Companion.getEMPTY() : new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(byte var0, short var1) {
      return new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(int var0, byte var1) {
      return new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(int var0, int var1) {
      return var1 <= Integer.MIN_VALUE ? IntRange.Companion.getEMPTY() : new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(int var0, short var1) {
      return new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(short var0, byte var1) {
      return new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(short var0, int var1) {
      return var1 <= Integer.MIN_VALUE ? IntRange.Companion.getEMPTY() : new IntRange(var0, var1 - 1);
   }

   public static final IntRange until(short var0, short var1) {
      return new IntRange(var0, var1 - 1);
   }

   public static final LongRange until(byte var0, long var1) {
      return var1 <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange((long)var0, var1 - 1L);
   }

   public static final LongRange until(int var0, long var1) {
      return var1 <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange((long)var0, var1 - 1L);
   }

   public static final LongRange until(long var0, byte var2) {
      return new LongRange(var0, (long)var2 - 1L);
   }

   public static final LongRange until(long var0, int var2) {
      return new LongRange(var0, (long)var2 - 1L);
   }

   public static final LongRange until(long var0, long var2) {
      return var2 <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange(var0, var2 - 1L);
   }

   public static final LongRange until(long var0, short var2) {
      return new LongRange(var0, (long)var2 - 1L);
   }

   public static final LongRange until(short var0, long var1) {
      return var1 <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange((long)var0, var1 - 1L);
   }
}
