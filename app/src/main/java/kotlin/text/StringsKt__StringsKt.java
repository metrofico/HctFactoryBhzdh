package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(
   d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001c\u0010\u0011\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001f\u0010\u0012\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0086\u0002\u001a\u001f\u0010\u0012\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0086\u0002\u001a\u0015\u0010\u0012\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\n\u001a\u0018\u0010\u0017\u001a\u00020\u0010*\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u0018\u0010\u0018\u001a\u00020\u0010*\u0004\u0018\u00010\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u001c\u0010\u0019\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001c\u0010\u0019\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a:\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0018\u00010\u001c*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001aE\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0018\u00010\u001c*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0010H\u0002¢\u0006\u0002\b!\u001a:\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r\u0018\u00010\u001c*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0012\u0010#\u001a\u00020\u0010*\u00020\u00022\u0006\u0010$\u001a\u00020\u0006\u001a7\u0010%\u001a\u0002H&\"\f\b\u0000\u0010'*\u00020\u0002*\u0002H&\"\u0004\b\u0001\u0010&*\u0002H'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H&0)H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001a7\u0010+\u001a\u0002H&\"\f\b\u0000\u0010'*\u00020\u0002*\u0002H&\"\u0004\b\u0001\u0010&*\u0002H'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H&0)H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010*\u001a&\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a;\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\u0010H\u0002¢\u0006\u0002\b.\u001a&\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010/\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a&\u00100\u001a\u00020\u0006*\u00020\u00022\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a,\u00100\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\r\u00103\u001a\u00020\u0010*\u00020\u0002H\u0087\b\u001a\r\u00104\u001a\u00020\u0010*\u00020\u0002H\u0087\b\u001a\r\u00105\u001a\u00020\u0010*\u00020\u0002H\u0087\b\u001a \u00106\u001a\u00020\u0010*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00107\u001a\u00020\u0010*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00108\u001a\u000209*\u00020\u0002H\u0086\u0002\u001a&\u0010:\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a&\u0010:\u001a\u00020\u0006*\u00020\u00022\u0006\u0010/\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a&\u0010;\u001a\u00020\u0006*\u00020\u00022\u0006\u00101\u001a\u0002022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a,\u0010;\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0010\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u0002\u001a\u0010\u0010>\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u0002\u001a\u0015\u0010@\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\f\u001a\u000f\u0010A\u001a\u00020\r*\u0004\u0018\u00010\rH\u0087\b\u001a\u001c\u0010B\u001a\u00020\u0002*\u00020\u00022\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001a\u001c\u0010B\u001a\u00020\r*\u00020\r2\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001a\u001c\u0010E\u001a\u00020\u0002*\u00020\u00022\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001a\u001c\u0010E\u001a\u00020\r*\u00020\r2\u0006\u0010C\u001a\u00020\u00062\b\b\u0002\u0010D\u001a\u00020\u0014\u001aG\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00010=*\u00020\u00022\u000e\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0H2\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\bI\u0010J\u001a=\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00010=*\u00020\u00022\u0006\u0010G\u001a\u0002022\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0002\bI\u001a4\u0010K\u001a\u00020\u0010*\u00020\u00022\u0006\u0010L\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u0002\u001a\u0012\u0010N\u001a\u00020\r*\u00020\r2\u0006\u0010O\u001a\u00020\u0002\u001a\u001a\u0010P\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006\u001a\u0012\u0010P\u001a\u00020\u0002*\u00020\u00022\u0006\u0010Q\u001a\u00020\u0001\u001a\u001d\u0010P\u001a\u00020\r*\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010P\u001a\u00020\r*\u00020\r2\u0006\u0010Q\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010R\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u0012\u0010R\u001a\u00020\r*\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u0012\u0010S\u001a\u00020\u0002*\u00020\u00022\u0006\u0010T\u001a\u00020\u0002\u001a\u001a\u0010S\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a\u0012\u0010S\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u0002\u001a\u001a\u0010S\u001a\u00020\r*\u00020\r2\u0006\u0010O\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002\u001a.\u0010U\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0014\b\b\u0010V\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u00020WH\u0087\bø\u0001\u0000\u001a\u001d\u0010U\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020\rH\u0087\b\u001a$\u0010Z\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010Z\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010\\\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010\\\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010]\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010]\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010^\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a$\u0010^\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001d\u0010_\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020\rH\u0087\b\u001a)\u0010`\u001a\u00020\r*\u00020\r2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140WH\u0087\bø\u0001\u0000¢\u0006\u0002\ba\u001a)\u0010`\u001a\u00020\r*\u00020\r2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00020WH\u0087\bø\u0001\u0000¢\u0006\u0002\bb\u001a\"\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010Y\u001a\u00020\u0002\u001a\u001a\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010Y\u001a\u00020\u0002\u001a%\u0010c\u001a\u00020\r*\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010Y\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010c\u001a\u00020\r*\u00020\r2\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010Y\u001a\u00020\u0002H\u0087\b\u001a=\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\u0012\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0H\"\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010e\u001a0\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\n\u0010G\u001a\u000202\"\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u001a/\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\u0006\u0010T\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0002\bf\u001a%\u0010d\u001a\b\u0012\u0004\u0012\u00020\r0?*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0087\b\u001a=\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u00022\u0012\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0H\"\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010h\u001a0\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u00022\n\u0010G\u001a\u000202\"\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u001a%\u0010g\u001a\b\u0012\u0004\u0012\u00020\r0=*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u0087\b\u001a\u001c\u0010i\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u001c\u0010i\u001a\u00020\u0010*\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a$\u0010i\u001a\u00020\u0010*\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\u0012\u0010j\u001a\u00020\u0002*\u00020\u00022\u0006\u0010Q\u001a\u00020\u0001\u001a\u001d\u0010j\u001a\u00020\u0002*\u00020\r2\u0006\u0010k\u001a\u00020\u00062\u0006\u0010l\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010m\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010-\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010m\u001a\u00020\r*\u00020\u00022\u0006\u0010Q\u001a\u00020\u0001\u001a\u0012\u0010m\u001a\u00020\r*\u00020\r2\u0006\u0010Q\u001a\u00020\u0001\u001a\u001c\u0010n\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010n\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010o\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010o\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010p\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010p\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010q\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\u00142\b\b\u0002\u0010[\u001a\u00020\r\u001a\u001c\u0010q\u001a\u00020\r*\u00020\r2\u0006\u0010T\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r\u001a\f\u0010r\u001a\u00020\u0010*\u00020\rH\u0007\u001a\u0013\u0010s\u001a\u0004\u0018\u00010\u0010*\u00020\rH\u0007¢\u0006\u0002\u0010t\u001a\n\u0010u\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010u\u001a\u00020\u0002*\u00020\u00022\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010u\u001a\u00020\u0002*\u00020\u00022\n\u00101\u001a\u000202\"\u00020\u0014\u001a\r\u0010u\u001a\u00020\r*\u00020\rH\u0087\b\u001a$\u0010u\u001a\u00020\r*\u00020\r2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010u\u001a\u00020\r*\u00020\r2\n\u00101\u001a\u000202\"\u00020\u0014\u001a\n\u0010w\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010w\u001a\u00020\u0002*\u00020\u00022\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010w\u001a\u00020\u0002*\u00020\u00022\n\u00101\u001a\u000202\"\u00020\u0014\u001a\r\u0010w\u001a\u00020\r*\u00020\rH\u0087\b\u001a$\u0010w\u001a\u00020\r*\u00020\r2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010w\u001a\u00020\r*\u00020\r2\n\u00101\u001a\u000202\"\u00020\u0014\u001a\n\u0010x\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010x\u001a\u00020\u0002*\u00020\u00022\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010x\u001a\u00020\u0002*\u00020\u00022\n\u00101\u001a\u000202\"\u00020\u0014\u001a\r\u0010x\u001a\u00020\r*\u00020\rH\u0087\b\u001a$\u0010x\u001a\u00020\r*\u00020\r2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00100WH\u0086\bø\u0001\u0000\u001a\u0016\u0010x\u001a\u00020\r*\u00020\r2\n\u00101\u001a\u000202\"\u00020\u0014\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006y"},
   d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "requireNonNegativeLimit", "", "limit", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "contentEqualsIgnoreCaseImpl", "contentEqualsImpl", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceFirstChar", "replaceFirstCharWithChar", "replaceFirstCharWithCharSequence", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "toBooleanStrict", "toBooleanStrictOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
   public StringsKt__StringsKt() {
   }

   public static final String commonPrefixWith(CharSequence var0, CharSequence var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      int var4 = Math.min(var0.length(), var1.length());

      int var3;
      for(var3 = 0; var3 < var4 && CharsKt.equals(var0.charAt(var3), var1.charAt(var3), var2); ++var3) {
      }

      int var5 = var3 - 1;
      if (!StringsKt.hasSurrogatePairAt(var0, var5)) {
         var4 = var3;
         if (!StringsKt.hasSurrogatePairAt(var1, var5)) {
            return var0.subSequence(0, var4).toString();
         }
      }

      var4 = var3 - 1;
      return var0.subSequence(0, var4).toString();
   }

   // $FF: synthetic method
   public static String commonPrefixWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.commonPrefixWith(var0, var1, var2);
   }

   public static final String commonSuffixWith(CharSequence var0, CharSequence var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      int var5 = var0.length();
      int var6 = var1.length();
      int var4 = Math.min(var5, var6);

      int var3;
      for(var3 = 0; var3 < var4 && CharsKt.equals(var0.charAt(var5 - var3 - 1), var1.charAt(var6 - var3 - 1), var2); ++var3) {
      }

      if (!StringsKt.hasSurrogatePairAt(var0, var5 - var3 - 1)) {
         var4 = var3;
         if (!StringsKt.hasSurrogatePairAt(var1, var6 - var3 - 1)) {
            return var0.subSequence(var5 - var4, var5).toString();
         }
      }

      var4 = var3 - 1;
      return var0.subSequence(var5 - var4, var5).toString();
   }

   // $FF: synthetic method
   public static String commonSuffixWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.commonSuffixWith(var0, var1, var2);
   }

   public static final boolean contains(CharSequence var0, char var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (StringsKt.indexOf$default(var0, var1, 0, var2, 2, (Object)null) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean contains(CharSequence var0, CharSequence var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      boolean var4 = var1 instanceof String;
      boolean var3 = true;
      if (var4) {
         if (StringsKt.indexOf$default(var0, (String)var1, 0, var2, 2, (Object)null) >= 0) {
            var2 = var3;
            return var2;
         }
      } else if (indexOf$StringsKt__StringsKt$default(var0, var1, 0, var0.length(), var2, false, 16, (Object)null) >= 0) {
         var2 = var3;
         return var2;
      }

      var2 = false;
      return var2;
   }

   private static final boolean contains(CharSequence var0, Regex var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      return var1.containsMatchIn(var0);
   }

   // $FF: synthetic method
   public static boolean contains$default(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.contains(var0, var1, var2);
   }

   // $FF: synthetic method
   public static boolean contains$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.contains(var0, var1, var2);
   }

   public static final boolean contentEqualsIgnoreCaseImpl(CharSequence var0, CharSequence var1) {
      if (var0 instanceof String && var1 instanceof String) {
         return StringsKt.equals((String)var0, (String)var1, true);
      } else if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null && var0.length() == var1.length()) {
         int var3 = var0.length();

         for(int var2 = 0; var2 < var3; ++var2) {
            if (!CharsKt.equals(var0.charAt(var2), var1.charAt(var2), true)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final boolean contentEqualsImpl(CharSequence var0, CharSequence var1) {
      if (var0 instanceof String && var1 instanceof String) {
         return Intrinsics.areEqual((Object)var0, (Object)var1);
      } else if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null && var0.length() == var1.length()) {
         int var3 = var0.length();

         for(int var2 = 0; var2 < var3; ++var2) {
            if (var0.charAt(var2) != var1.charAt(var2)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final boolean endsWith(CharSequence var0, char var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0.length() > 0 && CharsKt.equals(var0.charAt(StringsKt.getLastIndex(var0)), var1, var2)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final boolean endsWith(CharSequence var0, CharSequence var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "suffix");
      return !var2 && var0 instanceof String && var1 instanceof String ? StringsKt.endsWith$default((String)var0, (String)var1, false, 2, (Object)null) : StringsKt.regionMatchesImpl(var0, var0.length() - var1.length(), var1, 0, var1.length(), var2);
   }

   // $FF: synthetic method
   public static boolean endsWith$default(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.endsWith(var0, var1, var2);
   }

   // $FF: synthetic method
   public static boolean endsWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.endsWith(var0, var1, var2);
   }

   public static final Pair findAnyOf(CharSequence var0, Collection var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "strings");
      return findAnyOf$StringsKt__StringsKt(var0, var1, var2, var3, false);
   }

   private static final Pair findAnyOf$StringsKt__StringsKt(CharSequence var0, Collection var1, int var2, boolean var3, boolean var4) {
      IntProgression var8 = null;
      if (!var3 && var1.size() == 1) {
         String var12 = (String)CollectionsKt.single((Iterable)var1);
         if (!var4) {
            var2 = StringsKt.indexOf$default(var0, var12, var2, false, 4, (Object)null);
         } else {
            var2 = StringsKt.lastIndexOf$default(var0, var12, var2, false, 4, (Object)null);
         }

         Pair var11;
         if (var2 < 0) {
            var11 = var8;
         } else {
            var11 = TuplesKt.to(var2, var12);
         }

         return var11;
      } else {
         if (!var4) {
            var8 = (IntProgression)(new IntRange(RangesKt.coerceAtLeast(var2, 0), var0.length()));
         } else {
            var8 = RangesKt.downTo(RangesKt.coerceAtMost(var2, StringsKt.getLastIndex(var0)), 0);
         }

         int var5;
         int var6;
         int var7;
         Iterator var9;
         String var10;
         Object var13;
         String var14;
         if (var0 instanceof String) {
            label103: {
               var5 = var8.getFirst();
               var7 = var8.getLast();
               var6 = var8.getStep();
               if (var6 > 0) {
                  var2 = var5;
                  if (var5 <= var7) {
                     break label103;
                  }
               }

               if (var6 >= 0 || var7 > var5) {
                  return null;
               }

               var2 = var5;
            }

            while(true) {
               var9 = ((Iterable)var1).iterator();

               do {
                  if (!var9.hasNext()) {
                     var13 = null;
                     break;
                  }

                  var13 = var9.next();
                  var10 = (String)var13;
               } while(!StringsKt.regionMatches(var10, 0, (String)var0, var2, var10.length(), var3));

               var14 = (String)var13;
               if (var14 != null) {
                  return TuplesKt.to(var2, var14);
               }

               if (var2 == var7) {
                  break;
               }

               var2 += var6;
            }
         } else {
            label105: {
               var5 = var8.getFirst();
               var6 = var8.getLast();
               var7 = var8.getStep();
               if (var7 > 0) {
                  var2 = var5;
                  if (var5 <= var6) {
                     break label105;
                  }
               }

               if (var7 >= 0 || var6 > var5) {
                  return null;
               }

               var2 = var5;
            }

            while(true) {
               var9 = ((Iterable)var1).iterator();

               do {
                  if (!var9.hasNext()) {
                     var13 = null;
                     break;
                  }

                  var13 = var9.next();
                  var10 = (String)var13;
               } while(!StringsKt.regionMatchesImpl((CharSequence)var10, 0, var0, var2, var10.length(), var3));

               var14 = (String)var13;
               if (var14 != null) {
                  return TuplesKt.to(var2, var14);
               }

               if (var2 == var6) {
                  break;
               }

               var2 += var7;
            }
         }

         return null;
      }
   }

   // $FF: synthetic method
   public static Pair findAnyOf$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.findAnyOf(var0, var1, var2, var3);
   }

   public static final Pair findLastAnyOf(CharSequence var0, Collection var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "strings");
      return findAnyOf$StringsKt__StringsKt(var0, var1, var2, var3, true);
   }

   // $FF: synthetic method
   public static Pair findLastAnyOf$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.findLastAnyOf(var0, var1, var2, var3);
   }

   public static final IntRange getIndices(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new IntRange(0, var0.length() - 1);
   }

   public static final int getLastIndex(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.length() - 1;
   }

   public static final boolean hasSurrogatePairAt(CharSequence var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var2 = var0.length();
      boolean var4 = false;
      boolean var3 = var4;
      if ((new IntRange(0, var2 - 2)).contains(var1)) {
         var3 = var4;
         if (Character.isHighSurrogate(var0.charAt(var1))) {
            var3 = var4;
            if (Character.isLowSurrogate(var0.charAt(var1 + 1))) {
               var3 = true;
            }
         }
      }

      return var3;
   }

   private static final Object ifBlank(CharSequence var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      Object var2 = var0;
      if (StringsKt.isBlank(var0)) {
         var2 = var1.invoke();
      }

      return var2;
   }

   private static final Object ifEmpty(CharSequence var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      boolean var2;
      if (((CharSequence)var0).length() == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         var0 = var1.invoke();
      }

      return var0;
   }

   public static final int indexOf(CharSequence var0, char var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var3 && var0 instanceof String) {
         var2 = ((String)var0).indexOf(var1, var2);
      } else {
         var2 = StringsKt.indexOfAny(var0, new char[]{var1}, var2, var3);
      }

      return var2;
   }

   public static final int indexOf(CharSequence var0, String var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "string");
      if (!var3 && var0 instanceof String) {
         var2 = ((String)var0).indexOf(var1, var2);
      } else {
         var2 = indexOf$StringsKt__StringsKt$default(var0, (CharSequence)var1, var2, var0.length(), var3, false, 16, (Object)null);
      }

      return var2;
   }

   private static final int indexOf$StringsKt__StringsKt(CharSequence var0, CharSequence var1, int var2, int var3, boolean var4, boolean var5) {
      IntProgression var8;
      if (!var5) {
         var8 = (IntProgression)(new IntRange(RangesKt.coerceAtLeast(var2, 0), RangesKt.coerceAtMost(var3, var0.length())));
      } else {
         var8 = RangesKt.downTo(RangesKt.coerceAtMost(var2, StringsKt.getLastIndex(var0)), RangesKt.coerceAtLeast(var3, 0));
      }

      int var6;
      int var7;
      if (var0 instanceof String && var1 instanceof String) {
         label67: {
            var3 = var8.getFirst();
            var7 = var8.getLast();
            var6 = var8.getStep();
            if (var6 > 0) {
               var2 = var3;
               if (var3 <= var7) {
                  break label67;
               }
            }

            if (var6 >= 0 || var7 > var3) {
               return -1;
            }

            var2 = var3;
         }

         while(true) {
            if (StringsKt.regionMatches((String)var1, 0, (String)var0, var2, var1.length(), var4)) {
               return var2;
            }

            if (var2 == var7) {
               break;
            }

            var2 += var6;
         }
      } else {
         label69: {
            var3 = var8.getFirst();
            var6 = var8.getLast();
            var7 = var8.getStep();
            if (var7 > 0) {
               var2 = var3;
               if (var3 <= var6) {
                  break label69;
               }
            }

            if (var7 >= 0 || var6 > var3) {
               return -1;
            }

            var2 = var3;
         }

         while(true) {
            if (StringsKt.regionMatchesImpl(var1, 0, var0, var2, var1.length(), var4)) {
               return var2;
            }

            if (var2 == var6) {
               break;
            }

            var2 += var7;
         }
      }

      return -1;
   }

   // $FF: synthetic method
   static int indexOf$StringsKt__StringsKt$default(CharSequence var0, CharSequence var1, int var2, int var3, boolean var4, boolean var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = false;
      }

      return indexOf$StringsKt__StringsKt(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static int indexOf$default(CharSequence var0, char var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOf(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static int indexOf$default(CharSequence var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOf(var0, var1, var2, var3);
   }

   public static final int indexOfAny(CharSequence var0, Collection var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "strings");
      Pair var4 = findAnyOf$StringsKt__StringsKt(var0, var1, var2, var3, false);
      if (var4 != null) {
         var2 = ((Number)var4.getFirst()).intValue();
      } else {
         var2 = -1;
      }

      return var2;
   }

   public static final int indexOfAny(CharSequence var0, char[] var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      if (!var3 && var1.length == 1 && var0 instanceof String) {
         char var9 = ArraysKt.single(var1);
         return ((String)var0).indexOf(var9, var2);
      } else {
         IntIterator var7 = (new IntRange(RangesKt.coerceAtLeast(var2, 0), StringsKt.getLastIndex(var0))).iterator();

         int var5;
         boolean var8;
         label34:
         do {
            if (!var7.hasNext()) {
               return -1;
            }

            var5 = var7.nextInt();
            char var4 = var0.charAt(var5);
            int var6 = var1.length;

            for(var2 = 0; var2 < var6; ++var2) {
               if (CharsKt.equals(var1[var2], var4, var3)) {
                  var8 = true;
                  continue label34;
               }
            }

            var8 = false;
         } while(!var8);

         return var5;
      }
   }

   // $FF: synthetic method
   public static int indexOfAny$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOfAny(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static int indexOfAny$default(CharSequence var0, char[] var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOfAny(var0, var1, var2, var3);
   }

   private static final boolean isEmpty(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var1;
      if (var0.length() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static final boolean isNotBlank(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.isBlank(var0) ^ true;
   }

   private static final boolean isNotEmpty(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var1;
      if (var0.length() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static final boolean isNullOrBlank(CharSequence var0) {
      boolean var1;
      if (var0 != null && !StringsKt.isBlank(var0)) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private static final boolean isNullOrEmpty(CharSequence var0) {
      boolean var1;
      if (var0 != null && var0.length() != 0) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public static final CharIterator iterator(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (CharIterator)(new CharIterator(var0) {
         final CharSequence $this_iterator;
         private int index;

         {
            this.$this_iterator = var1;
         }

         public boolean hasNext() {
            boolean var1;
            if (this.index < this.$this_iterator.length()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public char nextChar() {
            CharSequence var2 = this.$this_iterator;
            int var1 = this.index++;
            return var2.charAt(var1);
         }
      });
   }

   public static final int lastIndexOf(CharSequence var0, char var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var3 && var0 instanceof String) {
         var2 = ((String)var0).lastIndexOf(var1, var2);
      } else {
         var2 = StringsKt.lastIndexOfAny(var0, new char[]{var1}, var2, var3);
      }

      return var2;
   }

   public static final int lastIndexOf(CharSequence var0, String var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "string");
      if (!var3 && var0 instanceof String) {
         var2 = ((String)var0).lastIndexOf(var1, var2);
      } else {
         var2 = indexOf$StringsKt__StringsKt(var0, (CharSequence)var1, var2, 0, var3, true);
      }

      return var2;
   }

   // $FF: synthetic method
   public static int lastIndexOf$default(CharSequence var0, char var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOf(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static int lastIndexOf$default(CharSequence var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOf(var0, var1, var2, var3);
   }

   public static final int lastIndexOfAny(CharSequence var0, Collection var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "strings");
      Pair var4 = findAnyOf$StringsKt__StringsKt(var0, var1, var2, var3, true);
      if (var4 != null) {
         var2 = ((Number)var4.getFirst()).intValue();
      } else {
         var2 = -1;
      }

      return var2;
   }

   public static final int lastIndexOfAny(CharSequence var0, char[] var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      if (!var3 && var1.length == 1 && var0 instanceof String) {
         char var9 = ArraysKt.single(var1);
         return ((String)var0).lastIndexOf(var9, var2);
      } else {
         for(var2 = RangesKt.coerceAtMost(var2, StringsKt.getLastIndex(var0)); -1 < var2; --var2) {
            char var4 = var0.charAt(var2);
            int var8 = var1.length;
            boolean var7 = false;
            int var5 = 0;

            boolean var6;
            while(true) {
               var6 = var7;
               if (var5 >= var8) {
                  break;
               }

               if (CharsKt.equals(var1[var5], var4, var3)) {
                  var6 = true;
                  break;
               }

               ++var5;
            }

            if (var6) {
               return var2;
            }
         }

         return -1;
      }
   }

   // $FF: synthetic method
   public static int lastIndexOfAny$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOfAny(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static int lastIndexOfAny$default(CharSequence var0, char[] var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOfAny(var0, var1, var2, var3);
   }

   public static final Sequence lineSequence(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.splitToSequence$default(var0, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, (Object)null);
   }

   public static final List lines(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.toList(StringsKt.lineSequence(var0));
   }

   private static final boolean matches(CharSequence var0, Regex var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      return var1.matches(var0);
   }

   private static final String orEmpty(String var0) {
      String var1 = var0;
      if (var0 == null) {
         var1 = "";
      }

      return var1;
   }

   public static final CharSequence padEnd(CharSequence var0, int var1, char var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var1 < 0) {
         throw new IllegalArgumentException("Desired length " + var1 + " is less than zero.");
      } else if (var1 <= var0.length()) {
         return var0.subSequence(0, var0.length());
      } else {
         StringBuilder var3 = new StringBuilder(var1);
         var3.append(var0);
         IntIterator var4 = (new IntRange(1, var1 - var0.length())).iterator();

         while(var4.hasNext()) {
            var4.nextInt();
            var3.append(var2);
         }

         return (CharSequence)var3;
      }
   }

   public static final String padEnd(String var0, int var1, char var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.padEnd((CharSequence)var0, var1, var2).toString();
   }

   // $FF: synthetic method
   public static CharSequence padEnd$default(CharSequence var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padEnd(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String padEnd$default(String var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padEnd(var0, var1, var2);
   }

   public static final CharSequence padStart(CharSequence var0, int var1, char var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var1 < 0) {
         throw new IllegalArgumentException("Desired length " + var1 + " is less than zero.");
      } else if (var1 <= var0.length()) {
         return var0.subSequence(0, var0.length());
      } else {
         StringBuilder var4 = new StringBuilder(var1);
         IntIterator var3 = (new IntRange(1, var1 - var0.length())).iterator();

         while(var3.hasNext()) {
            var3.nextInt();
            var4.append(var2);
         }

         var4.append(var0);
         return (CharSequence)var4;
      }
   }

   public static final String padStart(String var0, int var1, char var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.padStart((CharSequence)var0, var1, var2).toString();
   }

   // $FF: synthetic method
   public static CharSequence padStart$default(CharSequence var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padStart(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String padStart$default(String var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padStart(var0, var1, var2);
   }

   private static final Sequence rangesDelimitedBy$StringsKt__StringsKt(CharSequence var0, char[] var1, int var2, boolean var3, int var4) {
      StringsKt.requireNonNegativeLimit(var4);
      return (Sequence)(new DelimitedRangesSequence(var0, var2, var4, (Function2)(new Function2(var1, var3) {
         final char[] $delimiters;
         final boolean $ignoreCase;

         {
            this.$delimiters = var1;
            this.$ignoreCase = var2;
         }

         public final Pair invoke(CharSequence var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "$this$$receiver");
            var2 = StringsKt.indexOfAny(var1, this.$delimiters, var2, this.$ignoreCase);
            Pair var3;
            if (var2 < 0) {
               var3 = null;
            } else {
               var3 = TuplesKt.to(var2, 1);
            }

            return var3;
         }
      })));
   }

   private static final Sequence rangesDelimitedBy$StringsKt__StringsKt(CharSequence var0, String[] var1, int var2, boolean var3, int var4) {
      StringsKt.requireNonNegativeLimit(var4);
      return (Sequence)(new DelimitedRangesSequence(var0, var2, var4, (Function2)(new Function2(ArraysKt.asList(var1), var3) {
         final List $delimitersList;
         final boolean $ignoreCase;

         {
            this.$delimitersList = var1;
            this.$ignoreCase = var2;
         }

         public final Pair invoke(CharSequence var1, int var2) {
            Intrinsics.checkNotNullParameter(var1, "$this$$receiver");
            Pair var3 = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(var1, (Collection)this.$delimitersList, var2, this.$ignoreCase, false);
            if (var3 != null) {
               var3 = TuplesKt.to(var3.getFirst(), ((String)var3.getSecond()).length());
            } else {
               var3 = null;
            }

            return var3;
         }
      })));
   }

   // $FF: synthetic method
   static Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence var0, char[] var1, int var2, boolean var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = 0;
      }

      return rangesDelimitedBy$StringsKt__StringsKt(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   static Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence var0, String[] var1, int var2, boolean var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = 0;
      }

      return rangesDelimitedBy$StringsKt__StringsKt(var0, var1, var2, var3, var4);
   }

   public static final boolean regionMatchesImpl(CharSequence var0, int var1, CharSequence var2, int var3, int var4, boolean var5) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "other");
      if (var3 >= 0 && var1 >= 0 && var1 <= var0.length() - var4 && var3 <= var2.length() - var4) {
         for(int var6 = 0; var6 < var4; ++var6) {
            if (!CharsKt.equals(var0.charAt(var1 + var6), var2.charAt(var3 + var6), var5)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final CharSequence removePrefix(CharSequence var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      return StringsKt.startsWith$default(var0, var1, false, 2, (Object)null) ? var0.subSequence(var1.length(), var0.length()) : var0.subSequence(0, var0.length());
   }

   public static final String removePrefix(String var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      String var2 = var0;
      if (StringsKt.startsWith$default((CharSequence)var0, var1, false, 2, (Object)null)) {
         var2 = var0.substring(var1.length());
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).substring(startIndex)");
      }

      return var2;
   }

   public static final CharSequence removeRange(CharSequence var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var2 >= var1) {
         if (var2 == var1) {
            return var0.subSequence(0, var0.length());
         } else {
            StringBuilder var3 = new StringBuilder(var0.length() - (var2 - var1));
            Intrinsics.checkNotNullExpressionValue(var3.append(var0, 0, var1), "this.append(value, startIndex, endIndex)");
            Intrinsics.checkNotNullExpressionValue(var3.append(var0, var2, var0.length()), "this.append(value, startIndex, endIndex)");
            return (CharSequence)var3;
         }
      } else {
         throw new IndexOutOfBoundsException("End index (" + var2 + ") is less than start index (" + var1 + ").");
      }
   }

   public static final CharSequence removeRange(CharSequence var0, IntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      return StringsKt.removeRange(var0, var1.getStart(), var1.getEndInclusive() + 1);
   }

   private static final String removeRange(String var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.removeRange((CharSequence)var0, var1, var2).toString();
   }

   private static final String removeRange(String var0, IntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      return StringsKt.removeRange((CharSequence)var0, var1).toString();
   }

   public static final CharSequence removeSuffix(CharSequence var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "suffix");
      return StringsKt.endsWith$default(var0, var1, false, 2, (Object)null) ? var0.subSequence(0, var0.length() - var1.length()) : var0.subSequence(0, var0.length());
   }

   public static final String removeSuffix(String var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "suffix");
      String var2 = var0;
      if (StringsKt.endsWith$default((CharSequence)var0, var1, false, 2, (Object)null)) {
         var2 = var0.substring(0, var0.length() - var1.length());
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   public static final CharSequence removeSurrounding(CharSequence var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      return StringsKt.removeSurrounding(var0, var1, var1);
   }

   public static final CharSequence removeSurrounding(CharSequence var0, CharSequence var1, CharSequence var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      Intrinsics.checkNotNullParameter(var2, "suffix");
      return var0.length() >= var1.length() + var2.length() && StringsKt.startsWith$default(var0, var1, false, 2, (Object)null) && StringsKt.endsWith$default(var0, var2, false, 2, (Object)null) ? var0.subSequence(var1.length(), var0.length() - var2.length()) : var0.subSequence(0, var0.length());
   }

   public static final String removeSurrounding(String var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      return StringsKt.removeSurrounding(var0, var1, var1);
   }

   public static final String removeSurrounding(String var0, CharSequence var1, CharSequence var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      Intrinsics.checkNotNullParameter(var2, "suffix");
      String var3 = var0;
      if (var0.length() >= var1.length() + var2.length()) {
         CharSequence var4 = (CharSequence)var0;
         var3 = var0;
         if (StringsKt.startsWith$default(var4, var1, false, 2, (Object)null)) {
            var3 = var0;
            if (StringsKt.endsWith$default(var4, var2, false, 2, (Object)null)) {
               var3 = var0.substring(var1.length(), var0.length() - var2.length());
               Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
         }
      }

      return var3;
   }

   private static final String replace(CharSequence var0, Regex var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      return var1.replace(var0, var2);
   }

   private static final String replace(CharSequence var0, Regex var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      Intrinsics.checkNotNullParameter(var2, "transform");
      return var1.replace(var0, var2);
   }

   public static final String replaceAfter(String var0, char var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.indexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, var4 + 1, var0.length(), (CharSequence)var2).toString();
      }

      return var3;
   }

   public static final String replaceAfter(String var0, String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.indexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, var4 + var1.length(), var0.length(), (CharSequence)var2).toString();
      }

      return var3;
   }

   // $FF: synthetic method
   public static String replaceAfter$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfter(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String replaceAfter$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfter(var0, var1, var2, var3);
   }

   public static final String replaceAfterLast(String var0, char var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.lastIndexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, var4 + 1, var0.length(), (CharSequence)var2).toString();
      }

      return var3;
   }

   public static final String replaceAfterLast(String var0, String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.lastIndexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, var4 + var1.length(), var0.length(), (CharSequence)var2).toString();
      }

      return var3;
   }

   // $FF: synthetic method
   public static String replaceAfterLast$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfterLast(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String replaceAfterLast$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfterLast(var0, var1, var2, var3);
   }

   public static final String replaceBefore(String var0, char var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.indexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, 0, var4, (CharSequence)var2).toString();
      }

      return var3;
   }

   public static final String replaceBefore(String var0, String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.indexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, 0, var4, (CharSequence)var2).toString();
      }

      return var3;
   }

   // $FF: synthetic method
   public static String replaceBefore$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBefore(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String replaceBefore$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBefore(var0, var1, var2, var3);
   }

   public static final String replaceBeforeLast(String var0, char var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.lastIndexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, 0, var4, (CharSequence)var2).toString();
      }

      return var3;
   }

   public static final String replaceBeforeLast(String var0, String var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      Intrinsics.checkNotNullParameter(var3, "missingDelimiterValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.lastIndexOf$default(var5, var1, 0, false, 6, (Object)null);
      if (var4 != -1) {
         var3 = StringsKt.replaceRange(var5, 0, var4, (CharSequence)var2).toString();
      }

      return var3;
   }

   // $FF: synthetic method
   public static String replaceBeforeLast$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBeforeLast(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String replaceBeforeLast$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBeforeLast(var0, var1, var2, var3);
   }

   private static final String replaceFirst(CharSequence var0, Regex var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      return var1.replaceFirst(var0, var2);
   }

   private static final String replaceFirstCharWithChar(String var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      boolean var3;
      if (((CharSequence)var0).length() > 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      String var4 = var0;
      if (var3) {
         char var2 = (Character)var1.invoke(var0.charAt(0));
         var0 = var0.substring(1);
         Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).substring(startIndex)");
         var4 = var2 + var0;
      }

      return var4;
   }

   private static final String replaceFirstCharWithCharSequence(String var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "transform");
      boolean var2;
      if (((CharSequence)var0).length() > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      String var3 = var0;
      if (var2) {
         StringBuilder var4 = (new StringBuilder()).append(var1.invoke(var0.charAt(0)));
         var0 = var0.substring(1);
         Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).substring(startIndex)");
         var3 = var4.append(var0).toString();
      }

      return var3;
   }

   public static final CharSequence replaceRange(CharSequence var0, int var1, int var2, CharSequence var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var3, "replacement");
      if (var2 >= var1) {
         StringBuilder var4 = new StringBuilder();
         Intrinsics.checkNotNullExpressionValue(var4.append(var0, 0, var1), "this.append(value, startIndex, endIndex)");
         var4.append(var3);
         Intrinsics.checkNotNullExpressionValue(var4.append(var0, var2, var0.length()), "this.append(value, startIndex, endIndex)");
         return (CharSequence)var4;
      } else {
         throw new IndexOutOfBoundsException("End index (" + var2 + ") is less than start index (" + var1 + ").");
      }
   }

   public static final CharSequence replaceRange(CharSequence var0, IntRange var1, CharSequence var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      return StringsKt.replaceRange(var0, var1.getStart(), var1.getEndInclusive() + 1, var2);
   }

   private static final String replaceRange(String var0, int var1, int var2, CharSequence var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var3, "replacement");
      return StringsKt.replaceRange((CharSequence)var0, var1, var2, var3).toString();
   }

   private static final String replaceRange(String var0, IntRange var1, CharSequence var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      return StringsKt.replaceRange((CharSequence)var0, var1, var2).toString();
   }

   public static final void requireNonNegativeLimit(int var0) {
      boolean var1;
      if (var0 >= 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (!var1) {
         throw new IllegalArgumentException(("Limit must be non-negative, but was " + var0).toString());
      }
   }

   private static final List split(CharSequence var0, Regex var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      return var1.split(var0, var2);
   }

   public static final List split(CharSequence var0, char[] var1, boolean var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiters");
      if (var1.length == 1) {
         return split$StringsKt__StringsKt(var0, String.valueOf(var1[0]), var2, var3);
      } else {
         Iterable var4 = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(var0, (char[])var1, 0, var2, var3, 2, (Object)null));
         Collection var5 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var4, 10)));
         Iterator var6 = var4.iterator();

         while(var6.hasNext()) {
            var5.add(StringsKt.substring(var0, (IntRange)var6.next()));
         }

         return (List)var5;
      }
   }

   public static final List split(CharSequence var0, String[] var1, boolean var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiters");
      int var5 = var1.length;
      boolean var4 = true;
      if (var5 == 1) {
         String var6 = var1[0];
         if (((CharSequence)var6).length() != 0) {
            var4 = false;
         }

         if (!var4) {
            return split$StringsKt__StringsKt(var0, var6, var2, var3);
         }
      }

      Iterable var8 = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(var0, (String[])var1, 0, var2, var3, 2, (Object)null));
      Collection var7 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var8, 10)));
      Iterator var9 = var8.iterator();

      while(var9.hasNext()) {
         var7.add(StringsKt.substring(var0, (IntRange)var9.next()));
      }

      return (List)var7;
   }

   private static final List split$StringsKt__StringsKt(CharSequence var0, String var1, boolean var2, int var3) {
      StringsKt.requireNonNegativeLimit(var3);
      int var6 = 0;
      int var7 = StringsKt.indexOf(var0, var1, 0, var2);
      if (var7 != -1 && var3 != 1) {
         boolean var4;
         if (var3 > 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         int var5 = 10;
         if (var4) {
            var5 = RangesKt.coerceAtMost(var3, 10);
         }

         ArrayList var9 = new ArrayList(var5);
         var5 = var7;

         int var8;
         do {
            var9.add(var0.subSequence(var6, var5).toString());
            var8 = var1.length() + var5;
            if (var4 && var9.size() == var3 - 1) {
               break;
            }

            var7 = StringsKt.indexOf(var0, var1, var8, var2);
            var6 = var8;
            var5 = var7;
         } while(var7 != -1);

         var9.add(var0.subSequence(var8, var0.length()).toString());
         return (List)var9;
      } else {
         return CollectionsKt.listOf(var0.toString());
      }
   }

   // $FF: synthetic method
   static List split$default(CharSequence var0, Regex var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      return var1.split(var0, var2);
   }

   // $FF: synthetic method
   public static List split$default(CharSequence var0, char[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.split(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static List split$default(CharSequence var0, String[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.split(var0, var1, var2, var3);
   }

   private static final Sequence splitToSequence(CharSequence var0, Regex var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      return var1.splitToSequence(var0, var2);
   }

   public static final Sequence splitToSequence(CharSequence var0, char[] var1, boolean var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiters");
      return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(var0, (char[])var1, 0, var2, var3, 2, (Object)null), (Function1)(new Function1(var0) {
         final CharSequence $this_splitToSequence;

         {
            this.$this_splitToSequence = var1;
         }

         public final String invoke(IntRange var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            return StringsKt.substring(this.$this_splitToSequence, var1);
         }
      }));
   }

   public static final Sequence splitToSequence(CharSequence var0, String[] var1, boolean var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiters");
      return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(var0, (String[])var1, 0, var2, var3, 2, (Object)null), (Function1)(new Function1(var0) {
         final CharSequence $this_splitToSequence;

         {
            this.$this_splitToSequence = var1;
         }

         public final String invoke(IntRange var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            return StringsKt.substring(this.$this_splitToSequence, var1);
         }
      }));
   }

   // $FF: synthetic method
   static Sequence splitToSequence$default(CharSequence var0, Regex var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      return var1.splitToSequence(var0, var2);
   }

   // $FF: synthetic method
   public static Sequence splitToSequence$default(CharSequence var0, char[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.splitToSequence(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static Sequence splitToSequence$default(CharSequence var0, String[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.splitToSequence(var0, var1, var2, var3);
   }

   public static final boolean startsWith(CharSequence var0, char var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var3 = var0.length();
      boolean var5 = false;
      boolean var4 = var5;
      if (var3 > 0) {
         var4 = var5;
         if (CharsKt.equals(var0.charAt(0), var1, var2)) {
            var4 = true;
         }
      }

      return var4;
   }

   public static final boolean startsWith(CharSequence var0, CharSequence var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      return !var3 && var0 instanceof String && var1 instanceof String ? StringsKt.startsWith$default((String)var0, (String)var1, var2, false, 4, (Object)null) : StringsKt.regionMatchesImpl(var0, var2, var1, 0, var1.length(), var3);
   }

   public static final boolean startsWith(CharSequence var0, CharSequence var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      return !var2 && var0 instanceof String && var1 instanceof String ? StringsKt.startsWith$default((String)var0, (String)var1, false, 2, (Object)null) : StringsKt.regionMatchesImpl(var0, 0, var1, 0, var1.length(), var2);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.startsWith(var0, var1, var2);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(CharSequence var0, CharSequence var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.startsWith(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.startsWith(var0, var1, var2);
   }

   public static final CharSequence subSequence(CharSequence var0, IntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      return var0.subSequence(var1.getStart(), var1.getEndInclusive() + 1);
   }

   @Deprecated(
      message = "Use parameters named startIndex and endIndex.",
      replaceWith = @ReplaceWith(
   expression = "subSequence(startIndex = start, endIndex = end)",
   imports = {}
)
   )
   private static final CharSequence subSequence(String var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.subSequence(var1, var2);
   }

   private static final String substring(CharSequence var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.subSequence(var1, var2).toString();
   }

   public static final String substring(CharSequence var0, IntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      return var0.subSequence(var1.getStart(), var1.getEndInclusive() + 1).toString();
   }

   public static final String substring(String var0, IntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      var0 = var0.substring(var1.getStart(), var1.getEndInclusive() + 1);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String…ing(startIndex, endIndex)");
      return var0;
   }

   // $FF: synthetic method
   static String substring$default(CharSequence var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0.length();
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.subSequence(var1, var2).toString();
   }

   public static final String substringAfter(String var0, char var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.indexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(var3 + 1, var0.length());
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   public static final String substringAfter(String var0, String var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.indexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(var3 + var1.length(), var0.length());
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   // $FF: synthetic method
   public static String substringAfter$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfter(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String substringAfter$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfter(var0, var1, var2);
   }

   public static final String substringAfterLast(String var0, char var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.lastIndexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(var3 + 1, var0.length());
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   public static final String substringAfterLast(String var0, String var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.lastIndexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(var3 + var1.length(), var0.length());
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   // $FF: synthetic method
   public static String substringAfterLast$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfterLast(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String substringAfterLast$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfterLast(var0, var1, var2);
   }

   public static final String substringBefore(String var0, char var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.indexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(0, var3);
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   public static final String substringBefore(String var0, String var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.indexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(0, var3);
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   // $FF: synthetic method
   public static String substringBefore$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBefore(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String substringBefore$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBefore(var0, var1, var2);
   }

   public static final String substringBeforeLast(String var0, char var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.lastIndexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(0, var3);
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   public static final String substringBeforeLast(String var0, String var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "delimiter");
      Intrinsics.checkNotNullParameter(var2, "missingDelimiterValue");
      int var3 = StringsKt.lastIndexOf$default((CharSequence)var0, var1, 0, false, 6, (Object)null);
      if (var3 != -1) {
         var2 = var0.substring(0, var3);
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String…ing(startIndex, endIndex)");
      }

      return var2;
   }

   // $FF: synthetic method
   public static String substringBeforeLast$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBeforeLast(var0, var1, var2);
   }

   // $FF: synthetic method
   public static String substringBeforeLast$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBeforeLast(var0, var1, var2);
   }

   public static final boolean toBooleanStrict(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var1;
      if (Intrinsics.areEqual((Object)var0, (Object)"true")) {
         var1 = true;
      } else {
         if (!Intrinsics.areEqual((Object)var0, (Object)"false")) {
            throw new IllegalArgumentException("The string doesn't represent a boolean value: " + var0);
         }

         var1 = false;
      }

      return var1;
   }

   public static final Boolean toBooleanStrictOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Boolean var1;
      if (Intrinsics.areEqual((Object)var0, (Object)"true")) {
         var1 = true;
      } else if (Intrinsics.areEqual((Object)var0, (Object)"false")) {
         var1 = false;
      } else {
         var1 = null;
      }

      return var1;
   }

   public static final CharSequence trim(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var2 = var0.length() - 1;
      int var1 = 0;
      boolean var3 = false;

      while(var1 <= var2) {
         int var4;
         if (!var3) {
            var4 = var1;
         } else {
            var4 = var2;
         }

         boolean var5 = CharsKt.isWhitespace(var0.charAt(var4));
         if (!var3) {
            if (!var5) {
               var3 = true;
            } else {
               ++var1;
            }
         } else {
            if (!var5) {
               break;
            }

            --var2;
         }
      }

      return var0.subSequence(var1, var2 + 1);
   }

   public static final CharSequence trim(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      int var3 = var0.length() - 1;
      int var2 = 0;
      boolean var4 = false;

      while(var2 <= var3) {
         int var5;
         if (!var4) {
            var5 = var2;
         } else {
            var5 = var3;
         }

         boolean var6 = (Boolean)var1.invoke(var0.charAt(var5));
         if (!var4) {
            if (!var6) {
               var4 = true;
            } else {
               ++var2;
            }
         } else {
            if (!var6) {
               break;
            }

            --var3;
         }
      }

      return var0.subSequence(var2, var3 + 1);
   }

   public static final CharSequence trim(CharSequence var0, char... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      int var3 = var0.length() - 1;
      int var2 = 0;
      boolean var4 = false;

      while(var2 <= var3) {
         int var5;
         if (!var4) {
            var5 = var2;
         } else {
            var5 = var3;
         }

         boolean var6 = ArraysKt.contains(var1, var0.charAt(var5));
         if (!var4) {
            if (!var6) {
               var4 = true;
            } else {
               ++var2;
            }
         } else {
            if (!var6) {
               break;
            }

            --var3;
         }
      }

      return var0.subSequence(var2, var3 + 1);
   }

   private static final String trim(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.trim((CharSequence)var0).toString();
   }

   public static final String trim(String var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      CharSequence var7 = (CharSequence)var0;
      int var3 = var7.length() - 1;
      int var2 = 0;
      boolean var4 = false;

      while(var2 <= var3) {
         int var5;
         if (!var4) {
            var5 = var2;
         } else {
            var5 = var3;
         }

         boolean var6 = (Boolean)var1.invoke(var7.charAt(var5));
         if (!var4) {
            if (!var6) {
               var4 = true;
            } else {
               ++var2;
            }
         } else {
            if (!var6) {
               break;
            }

            --var3;
         }
      }

      return var7.subSequence(var2, var3 + 1).toString();
   }

   public static final String trim(String var0, char... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      CharSequence var7 = (CharSequence)var0;
      int var3 = var7.length() - 1;
      int var2 = 0;
      boolean var4 = false;

      while(var2 <= var3) {
         int var5;
         if (!var4) {
            var5 = var2;
         } else {
            var5 = var3;
         }

         boolean var6 = ArraysKt.contains(var1, var7.charAt(var5));
         if (!var4) {
            if (!var6) {
               var4 = true;
            } else {
               ++var2;
            }
         } else {
            if (!var6) {
               break;
            }

            --var3;
         }
      }

      return var7.subSequence(var2, var3 + 1).toString();
   }

   public static final CharSequence trimEnd(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var1 = var0.length() - 1;
      if (var1 >= 0) {
         while(true) {
            int var2 = var1 - 1;
            if (!CharsKt.isWhitespace(var0.charAt(var1))) {
               var0 = var0.subSequence(0, var1 + 1);
               return var0;
            }

            if (var2 < 0) {
               break;
            }

            var1 = var2;
         }
      }

      var0 = (CharSequence)"";
      return var0;
   }

   public static final CharSequence trimEnd(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      int var2 = var0.length() - 1;
      if (var2 >= 0) {
         while(true) {
            int var3 = var2 - 1;
            if (!(Boolean)var1.invoke(var0.charAt(var2))) {
               return var0.subSequence(0, var2 + 1);
            }

            if (var3 < 0) {
               break;
            }

            var2 = var3;
         }
      }

      return (CharSequence)"";
   }

   public static final CharSequence trimEnd(CharSequence var0, char... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      int var2 = var0.length() - 1;
      if (var2 >= 0) {
         while(true) {
            int var3 = var2 - 1;
            if (!ArraysKt.contains(var1, var0.charAt(var2))) {
               var0 = var0.subSequence(0, var2 + 1);
               return var0;
            }

            if (var3 < 0) {
               break;
            }

            var2 = var3;
         }
      }

      var0 = (CharSequence)"";
      return var0;
   }

   private static final String trimEnd(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.trimEnd((CharSequence)var0).toString();
   }

   public static final String trimEnd(String var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      CharSequence var4 = (CharSequence)var0;
      int var2 = var4.length() - 1;
      if (var2 >= 0) {
         while(true) {
            int var3 = var2 - 1;
            if (!(Boolean)var1.invoke(var4.charAt(var2))) {
               var4 = var4.subSequence(0, var2 + 1);
               return var4.toString();
            }

            if (var3 < 0) {
               break;
            }

            var2 = var3;
         }
      }

      var4 = (CharSequence)"";
      return var4.toString();
   }

   public static final String trimEnd(String var0, char... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      CharSequence var4 = (CharSequence)var0;
      int var2 = var4.length() - 1;
      if (var2 >= 0) {
         while(true) {
            int var3 = var2 - 1;
            if (!ArraysKt.contains(var1, var4.charAt(var2))) {
               var4 = var4.subSequence(0, var2 + 1);
               return var4.toString();
            }

            if (var3 < 0) {
               break;
            }

            var2 = var3;
         }
      }

      var4 = (CharSequence)"";
      return var4.toString();
   }

   public static final CharSequence trimStart(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var2 = var0.length();
      int var1 = 0;

      while(true) {
         if (var1 >= var2) {
            var0 = (CharSequence)"";
            break;
         }

         if (!CharsKt.isWhitespace(var0.charAt(var1))) {
            var0 = var0.subSequence(var1, var0.length());
            break;
         }

         ++var1;
      }

      return var0;
   }

   public static final CharSequence trimStart(CharSequence var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      int var3 = var0.length();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (!(Boolean)var1.invoke(var0.charAt(var2))) {
            return var0.subSequence(var2, var0.length());
         }
      }

      return (CharSequence)"";
   }

   public static final CharSequence trimStart(CharSequence var0, char... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      int var3 = var0.length();
      int var2 = 0;

      while(true) {
         if (var2 >= var3) {
            var0 = (CharSequence)"";
            break;
         }

         if (!ArraysKt.contains(var1, var0.charAt(var2))) {
            var0 = var0.subSequence(var2, var0.length());
            break;
         }

         ++var2;
      }

      return var0;
   }

   private static final String trimStart(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.trimStart((CharSequence)var0).toString();
   }

   public static final String trimStart(String var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "predicate");
      CharSequence var4 = (CharSequence)var0;
      int var3 = var4.length();
      int var2 = 0;

      while(true) {
         if (var2 >= var3) {
            var4 = (CharSequence)"";
            break;
         }

         if (!(Boolean)var1.invoke(var4.charAt(var2))) {
            var4 = var4.subSequence(var2, var4.length());
            break;
         }

         ++var2;
      }

      return var4.toString();
   }

   public static final String trimStart(String var0, char... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "chars");
      CharSequence var4 = (CharSequence)var0;
      int var3 = var4.length();
      int var2 = 0;

      while(true) {
         if (var2 >= var3) {
            var4 = (CharSequence)"";
            break;
         }

         if (!ArraysKt.contains(var1, var4.charAt(var2))) {
            var4 = var4.subSequence(var2, var4.length());
            break;
         }

         ++var2;
      }

      return var4.toString();
   }
}
