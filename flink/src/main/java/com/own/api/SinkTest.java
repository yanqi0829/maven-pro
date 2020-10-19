package com.own.api;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

public class SinkTest {
    public static void main(String[] args) throws Exception {

    //kafkaSink
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    DataStreamSource<String> source2 = env.readTextFile("e:/1.txt");
    source2.addSink( new FlinkKafkaProducer<String>("localhost:9092","sinkTest",new SimpleStringSchema()));
    env.execute();
    }
}
