package com.own.metrics;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.metrics.Counter;
import org.apache.flink.metrics.Meter;
import org.apache.flink.metrics.MeterView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//重写SimpleStringSchema类的反序列化方法，当数据流入时变更指标。
public class TestKafkaSimpleStringSchema extends SimpleStringSchema {
    private static final Logger logger = LoggerFactory.getLogger(TestKafkaSimpleStringSchema.class);

    public static final String DT_NUM_RECORDS_RESOVED_IN_COUNTER = "dtNumRecordsInResolve";
    public static final String DT_NUM_RECORDS_RESOVED_IN_RATE = "dtNumRecordsInResolveRate";
    public static final String DT_DIRTY_DATA_COUNTER = "dtDirtyData";
    public static final String DT_NUM_BYTES_IN_COUNTER = "dtNumBytesIn";
    public static final String DT_NUM_RECORDS_IN_RATE = "dtNumRecordsInRate";

    public static final String DT_NUM_BYTES_IN_RATE = "dtNumBytesInRate";
    public static final String DT_NUM_RECORDS_IN_COUNTER = "dtNumRecordsIn";


    protected transient Counter numInResolveRecord;
    //source RPS
    protected transient Meter numInResolveRate;
    //source dirty data
    protected transient Counter dirtyDataCounter;

    // tps
    protected transient Meter numInRate;
    protected transient Counter numInRecord;

    //bps
    protected transient Counter numInBytes;
    protected transient Meter numInBytesRate;


    private transient RuntimeContext runtimeContext;

    public void initMetric() {
        numInResolveRecord = runtimeContext.getMetricGroup().counter(DT_NUM_RECORDS_RESOVED_IN_COUNTER);
        numInResolveRate = runtimeContext.getMetricGroup().meter(DT_NUM_RECORDS_RESOVED_IN_RATE, new MeterView(numInResolveRecord, 20));
        dirtyDataCounter = runtimeContext.getMetricGroup().counter(DT_DIRTY_DATA_COUNTER);

        numInBytes = runtimeContext.getMetricGroup().counter(DT_NUM_BYTES_IN_COUNTER);
        numInRecord = runtimeContext.getMetricGroup().counter(DT_NUM_RECORDS_IN_COUNTER);

        numInRate = runtimeContext.getMetricGroup().meter(DT_NUM_RECORDS_IN_RATE, new MeterView(numInRecord, 20));
        numInBytesRate = runtimeContext.getMetricGroup().meter(DT_NUM_BYTES_IN_RATE, new MeterView(numInBytes, 20));


    }

    // 源表读取重写deserialize方法
    @Override
    public String deserialize(byte[] value) {
        // 指标进行变更
        numInBytes.inc(value.length);
        numInResolveRecord.inc();
        numInRecord.inc();
        try {
            return super.deserialize(value);
        } catch (Exception e) {
            dirtyDataCounter.inc();
        }
        return "";
    }


    public void setRuntimeContext(RuntimeContext runtimeContext) {
        this.runtimeContext = runtimeContext;
    }
}
