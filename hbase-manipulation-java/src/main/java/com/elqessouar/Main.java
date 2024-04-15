package com.elqessouar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

public class Main {
    public static final  String TABLE_NAME = "users";
    public static final  String CF_DEFAULT = "cf";
    public static final  String CF_SECOND = "cf2";
    public static final  String COL_NAME = "name";
    public static final  String COL_AGE = "age";
    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "zookeeper");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.master", "hbase-master:16000");

        try (Connection connection = ConnectionFactory.createConnection(config)){
            Admin admin = connection.getAdmin();
            TableName tableName = TableName.valueOf(TABLE_NAME);
            TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tableName);

            builder.setColumnFamily(ColumnFamilyDescriptorBuilder.of(CF_DEFAULT));
            builder.setColumnFamily(ColumnFamilyDescriptorBuilder.of(CF_SECOND));
            TableDescriptor tableDescriptor = builder.build();

            if (!admin.tableExists(tableName)) {
                System.out.println("Creating table " + TABLE_NAME);
                admin.createTable(tableDescriptor);
                System.out.println("Table created");
            }

            try (Table table = connection.getTable(tableName)){
                Put put = new Put("row1".getBytes());
                put.addColumn(CF_DEFAULT.getBytes(), COL_NAME.getBytes(), "tazi ahmed".getBytes());
                put.addColumn(CF_DEFAULT.getBytes(), COL_AGE.getBytes(), "25".getBytes());
                table.put(put);
                System.out.println("Data inserted");
            }catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (Table table = connection.getTable(tableName)) {
                Get get = new Get("row1".getBytes());
                Result result = table.get(get);
                byte[] value = result.getValue(CF_DEFAULT.getBytes(), COL_NAME.getBytes());
                System.out.println("Get: " + new String(value));
            }catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (Table table = connection.getTable(tableName)) {
                Delete delete = new Delete("row1".getBytes());
                table.delete(delete);
                System.out.println("Data deleted");
            }catch (IOException e) {
                throw new RuntimeException(e);
            }



        }catch (IOException e) {
            throw new RuntimeException(e);
        }





}
}