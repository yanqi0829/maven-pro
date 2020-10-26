package com.own.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * kafka_2.10-0.9.0.1   在windows环境下
 * 参考 https://blog.csdn.net/weixin_42156906/article/details/80243903
 * 1.启动kafka自带 zookeeper：
 * bin\windows\zookeeper-server-start.bat config\zookeeper.properties
 * 2.修改server.properties中log.dir的配置目录
 * 3.启动kafka
 * bin\windows\kafka-server-start.bat config\server.properties
 * 4.创建topic   fistKafka
 * bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic fistKafka
 * 5.查看 topic list
 * bin\windows\kafka-topics.bat  --list --zookeeper localhost:2181
 * 6.启动生产者
 * bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic fistKafka1
 * 7.启动消费者  --from-beginning 从头消费
 * bin\windows\kafka-console-consumer.bat --zookeeper localhost:2181 --topic fistKafka --from-beginning
 * 8.关闭kafka
 * bin\windows\kafka-server-stop.bat
 * 9.关闭zookeeper
 * bin\windows\zookeeper-server-stop.bat
 */
public class CallBackProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.创建Kafka生产者的配置信息   参考ProducerConfig
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        long a = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("fistKafka1", 0, null, "send message kafka" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println(metadata.partition() + "---" + metadata.offset());
                    } else {
                        exception.printStackTrace();
                    }
                }
            });
        }
        long b = System.currentTimeMillis();
        System.out.println(b-a);
        long c = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
//            producer.send(new ProducerRecord<String, String>("fistKafka1", 0, null, "0.10.2.1版本发送消息kafka1" + i));
//            System.out.println(fistKafka.get().offset());
        }
        long d = System.currentTimeMillis();
        System.out.println(d-c);
        //关闭资源
        producer.close();
    }
}
