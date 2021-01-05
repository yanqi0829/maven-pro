package com.own.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

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
 * bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic fistKafka
 * 7.启动消费者  --from-beginning 从头消费
 * bin\windows\kafka-console-consumer.bat --zookeeper localhost:2181 --topic fistKafka --from-beginning
 */
public class MyProducer {
    public static void main(String[] args) {
        //1.创建Kafka生产者的配置信息   参考ProducerConfig
        Properties props = new Properties();
        //2.指定连接的kafka集群，broker-list 以逗号分隔
//        props.put("bootstrap.servers", "hadoop002:9092,hadoop003:9092,hadoop004:9092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop002:9092,hadoop003:9092,hadoop004:9092");
        //3、ACK应答级别
        props.put("acks", "all");
        //4.发送失败重试次数
        props.put("retries", 3);
        //5.发送批次大小（字节）
        props.put("batch.size", 16384);//16k
        //6.等待时间（ms）
        props.put("linger.ms", 1);
        //7.RecordAccumulator缓冲区大小 -----4至7都有默认值，可以不配置
        props.put("buffer.memory", 33554432);//32M
        //8.key value 的序列化类，均为String类型
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //9.创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        //10.发送数据
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("fistKafka", 0, null, "0.9.0.1版本发送消息" + i));
        }
        //关闭资源
        producer.close();
    }
}
