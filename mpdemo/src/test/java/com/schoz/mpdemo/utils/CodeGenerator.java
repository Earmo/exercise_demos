package com.schoz.mpdemo.utils;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import java.sql.Types;
import java.util.Collections;

/**
*@Description: 
*/
public class CodeGenerator {


    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:23306/mpdemo", "root", "root5744")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\workspace\\github\\earmo\\exercise_demos\\mpdemo\\src\\main\\java\\com\\schoz\\mpdemo\\"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                                .moduleName("system") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\workspace\\github\\earmo\\exercise_demos\\mpdemo\\src\\main\\java\\com\\schoz\\mpdemo\\com\\baomidou\\mybatisplus\\samples\\generator")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("user") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
                )
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
