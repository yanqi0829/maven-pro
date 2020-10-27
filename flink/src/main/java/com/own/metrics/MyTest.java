package com.own.metrics;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Map;
import java.util.Properties;

public class MyTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //开启checkpoint
        env.enableCheckpointing(2000);
        //从kafka读取数据
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //
        TestKafkaConsumer<String> kafkaConsumer = new TestKafkaConsumer<>("fistKafkaTest", new TestKafkaSimpleStringSchema(), props);
//        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>("fistKafkaTest", new SimpleStringSchema(), props);
        //从最新的数据开始进行消费，忽略存储的offset信息
        kafkaConsumer.setStartFromLatest();
        kafkaConsumer.setCommitOffsetsOnCheckpoints(true);
        DataStream<String> source3 = env.addSource(kafkaConsumer);
//        FlinkKafkaProducer<String> sinkProducer = new FlinkKafkaProducer<>("localhost:9092", "sinkTest", new SimpleStringSchema());

        source3 = source3.map(new RichMapFunction<String, String>() {
            private transient Counter counter;

            @Override
            public void open(Configuration config) {
//                kafkaConsumer.setRuntimeContext( getRuntimeContext());
                this.counter = getRuntimeContext()
                        .getMetricGroup()
                        .counter("Mycounter");
            }

            @Override
            public String map(String value) throws Exception {
                this.counter.inc();
                System.out.println("当前counter计数" + counter.getCount());
//                System.out.println("numRecordsIn："+kafkaConsumer.getRuntimeContext().getMetricGroup().counter("numRecordsIn").getCount());
//                Map<String, String> allVariables = kafkaConsumer.getRuntimeContext().getMetricGroup().getAllVariables();
                return value.toUpperCase();
            }
        });
        source3.print("stream3").setParallelism(1);
//        source3.addSink(sinkProducer);
        env.execute("metrics test");
    }
}
