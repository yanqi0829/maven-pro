package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 *
 */
public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //开启自动提交  offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        //自动提交延迟  一秒钟提交一次
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test111");//消费者组
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//latest(默认)       earliest

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("LXY"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);  //每100ms 拉取一次消息
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key() + "---" + consumerRecord.value() + "---" + consumerRecord.offset());
            }
        }
    }
}
