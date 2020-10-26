package com.own.metrics;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class TestKafkaConsumer<T> extends FlinkKafkaConsumer<T> {
    TestKafkaSimpleStringSchema testKafkaSimpleStringSchema;

    public TestKafkaConsumer(String topic, DeserializationSchema<T> valueDeserializer, Properties props) {
        super(topic, valueDeserializer, props);
        this.testKafkaSimpleStringSchema = (TestKafkaSimpleStringSchema) valueDeserializer;
    }

    @Override
    public void run(SourceContext<T> sourceContext) throws Exception {
        //将RuntimeContext传递给customerSimpleStringSchema
        testKafkaSimpleStringSchema.setRuntimeContext(getRuntimeContext());
        // 初始化指标
        testKafkaSimpleStringSchema.initMetric();
        super.run(sourceContext);
    }

    public TestKafkaSimpleStringSchema getTestKafkaSimpleStringSchema() {return testKafkaSimpleStringSchema;}

    public void setTestKafkaSimpleStringSchema(TestKafkaSimpleStringSchema testKafkaSimpleStringSchema) {
        this.testKafkaSimpleStringSchema = testKafkaSimpleStringSchema;
    }
}
