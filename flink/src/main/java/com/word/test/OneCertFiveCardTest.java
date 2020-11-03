package com.word.test;

import com.own.wc.WordCount;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple8;
import org.apache.flink.api.java.tuple.Tuple9;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class OneCertFiveCardTest {
    public static void main(String[] args) throws Exception {
        //获取运行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //数据源
        DataSet<User> userDataSource = env.readCsvFile("E:\\localtest\\flink.csv").fieldDelimiter(",").pojoType(User.class, "systemCode", "enpyCode", "serialNumber", "provinceCode", "userId", "certId", "certType", "name");
        AggregateOperator<Tuple9<String, String, String, String, String, String, String, String,Integer>> max = userDataSource.filter((user) -> user != null).map(new MapFunction<User, Tuple9<String, String, String, String, String, String, String, String,Integer>>() {

            @Override
            public Tuple9<String, String, String, String, String, String, String, String,Integer> map(User user) throws Exception {
                user.setSystemCode(user.getSystemCode() + "系统");
                 Tuple9<String, String, String, String, String, String, String, String,Integer> tuple9 = new Tuple9<>(user.getSystemCode(), user.getEnpyCode(), user.getSerialNumber(), user.getProvinceCode(), user.getUserId(), user.getCertId(), user.getCertType(), user.getName(),1);
                return tuple9;
            }
        }).groupBy(5,6).sum(8);
        max.print();
//        env.execute();
    }


}
