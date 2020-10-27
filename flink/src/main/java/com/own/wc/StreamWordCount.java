package com.own.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * 流处理
 */
public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        //创建流处理的执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //接受一个socket文本流
        DataStreamSource<String> stringDataStreamSource = env.socketTextStream("localhost", 8888);
        //对每条数据进行处理
        SingleOutputStreamOperator<Tuple2<String, Integer>> result = stringDataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {

            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                for (String word : s.split(" ")) {
                    collector.collect(new Tuple2<>(word, 1));
                }
            }
        });
        result.print("WC");
        env.execute();
    }
}
