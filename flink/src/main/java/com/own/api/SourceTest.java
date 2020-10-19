package com.own.api;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class SourceTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //1. 从自定义的集合中读取数据
        ArrayList<SensorReading> list = new ArrayList<>();
        list.add(new SensorReading("sensor_1", 1547718199L, 35.80018327300259));
        list.add(new SensorReading("sensor_6", 1547718201L, 15.402984393403084));
        list.add(new SensorReading("sensor_7", 1547718202L, 6.720945201171228));
        list.add(new SensorReading("sensor_10", 1547718205L, 38.101067604893444));
        DataStreamSource<SensorReading> source = env.fromCollection(list);
        //2.从文件读取数据
//        DataStreamSource<String> source2 = env.readTextFile("e:/1.txt");
        //3从kafka读取数据
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        DataStreamSource<String> source3 = env.addSource(new FlinkKafkaConsumer<String>("fistKafka1", new SimpleStringSchema(), props));
        //4自定义Source（一般个人测试使用）
        DataStreamSource<SensorReading> source4 = env.addSource(new SensorSource());
//        api.print("stream1").setParallelism(1); //setParallelism(6)
//        api.print("stream2").setParallelism(1);
//        source3.print("stream3").setParallelism(2);
        source3.map(new RichMapFunction<String, String>() {

            @Override
            public String map(String s) throws Exception {
                return null;
            }
        });
        source3.print("stream3").setParallelism(1);
        env.execute("api test");
    }


}

class SensorSource implements SourceFunction<SensorReading> {
    //定一个标记
    boolean running = true;

    @Override //正常生成数据
    public void run(SourceContext<SensorReading> sourceContext) throws Exception {
        Random random = new Random();
        while (running) {
            double v = random.nextDouble();
            long l = random.nextLong();
            String s = UUID.randomUUID().toString();
            SensorReading sensorReading = new SensorReading(s, l, v);
            sourceContext.collect(sensorReading);
            Thread.sleep(500);
        }
    }

    @Override  //取消数据源生成数据
    public void cancel() {
        running = false;
    }
}

//温度传感器
class SensorReading {
    private String id;
    private Long timestamp;
    private Double temperature;

    public SensorReading(String id, Long timestamp, Double temperature) {
        this.id = id;
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", temperature=" + temperature +
                '}';
    }
}
