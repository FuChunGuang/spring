package com.spring.fcg.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

@SuppressWarnings("all")
public class MybatisPlusGenerator {
//	public static void main(String[] args) throws InterruptedException {
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir("E:\\MybatisPlus\\oracle\\fcgdemo");
//        gc.setFileOverride(true);
//        gc.setActiveRecord(true);
//        gc.setEnableCache(true);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(true);// XML columList
//        gc.setAuthor("付春光");
//        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//         gc.setMapperName("%sDao");
//         gc.setXmlName("%sMapper");
//         gc.setServiceName("%sService");
//         gc.setServiceImplName("%sServiceImpl");
//         gc.setControllerName("%sController");
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.ORACLE);
//        /*dsc.setTypeConvert(new MySqlTypeConvert(){
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                return super.processTypeConvert(fieldType);
//            }
//        });*/
//
//        dsc.setDriverName("oracle.jdbc.OracleDriver");
//        dsc.setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
//        dsc.setUsername("fcgdemo");
//        dsc.setPassword("fcgdemo");
//        mpg.setDataSource(dsc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
//        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
//        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
//        mpg.setStrategy(strategy);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.spring.fcg");
//        mpg.setPackageInfo(pc);
//
//        // 执行生成
//        mpg.execute();
//    }
}
