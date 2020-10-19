package com.own.api;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class TransformTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source2 = env.readTextFile("e:/1.txt");
        //map 算子
        DataStream<String> map = source2.map(s -> "202020" + s);
        //flatMap
        DataStream<String> flatMap = map.flatMap((String s, Collector<String> collector) -> {
            for (String str : s.split(" ")) {
                collector.collect(str);
            }
        }).returns(Types.STRING);
        //filter
        DataStream<String> result = flatMap.filter(line->!line.trim().equals(""));
        flatMap.print("打印").setParallelism(2);
        env.execute("transform test");

    }
}
