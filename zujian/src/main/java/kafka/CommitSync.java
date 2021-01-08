package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class CommitSync {
    public static void main(String[] args) {
        {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);//关闭自动提交offset
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");//自动提交延迟

            props.put(ConsumerConfig.GROUP_ID_CONFIG, "bigdata11");//消费者组
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");//latest(默认) earliest

            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList("fistKafka"));
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.key() + "---" + consumerRecord.value() + "---" + consumerRecord.offset());
                }
                //同步提交，当前线程会阻塞直到offset提交成功
                consumer.commitSync();

            }
        }
    }
}
