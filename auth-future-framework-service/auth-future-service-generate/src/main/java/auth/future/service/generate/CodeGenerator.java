//package auth.future.service.generate;
//
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
//
//import java.util.Scanner;
//
//
///**
// * @author 胡祖有
// * @since 2022/2/4
// */
//public class CodeGenerator {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入数据库地址(如： 192.168.0.36:3306/cms_user_center ");
//        String datasourceUrl = getDatasourceInfo(scanner);
//        System.out.println("请输入用户名：");
//        String userName = getDatasourceInfo(scanner);
//        System.out.println("请输入密码：");
//        String password = getDatasourceInfo(scanner);
//        DataSourceConfig build = new DataSourceConfig();
//        build.setUrl("jdbc:mysql://"+datasourceUrl); //jdbc:mysql://127.0.0.1:3306/mybatis-plus
//        build.setUsername(userName);
//        build.setPassword(password);
//        build.setDbType(DbType.MYSQL);
//        build.setDriverName("com.mysql.cj.jdbc.Driver");
//        AutoGenerator mpg = new AutoGenerator();
//        GlobalConfig globalConfig = getGlobalConfig(scanner);
//        PackageConfig packageConfig = getPackageConfig();
//        StrategyConfig strategyConfig = getStrategyConfig();
//        mpg.setDataSource(build);
//        mpg.setGlobalConfig(globalConfig);
//        mpg.setPackageInfo(packageConfig);
//        mpg.setStrategy(strategyConfig);
//        mpg.setTemplateEngine(new VelocityTemplateEngine());
//        mpg.execute();
//
//    }
//
//    private static String getDatasourceInfo(Scanner scanner){
//        //判断是否有输入
//        if(scanner.hasNextLine()){
//            //使用next()方式来接收用户的输入
//            return scanner.next();
//        }
//        return "";
//    }
//
//    private static GlobalConfig getGlobalConfig(Scanner scanner){
//        System.out.println("请输入作者：");
//        String author = getDatasourceInfo(scanner);
//        System.out.println("请指定代码生成路径：");
//        String codePath = getDatasourceInfo(scanner);
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setFileOverride(true);
//        globalConfig.setOpen(true);
//        globalConfig.setOutputDir(codePath);
//        globalConfig.setAuthor(author);
//        globalConfig.setSwagger2(true);
//        globalConfig.setDateType(DateType.TIME_PACK);
//        globalConfig.setServiceName("%sService");
//        return globalConfig;
//    }
//
//    private static PackageConfig getPackageConfig() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入包名：");
//        String packageName = scanner.next();
//        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setParent(packageName);
//        packageConfig.setEntity("entity");
//        packageConfig.setService("service");
//        packageConfig.setServiceImpl("service.impl");
//        packageConfig.setController("controller");
//        packageConfig.setMapper("mapper");
//        return packageConfig;
//    }
//
//    private static StrategyConfig getStrategyConfig(){
//        Scanner scanner =new Scanner(System.in);
//        System.out.println("请输入需要生成的表名：");
//        String tableNames = scanner.next();
//        StrategyConfig strategyConfig = new StrategyConfig();
//        if (tableNames.contains(",")){
//            String[] split = tableNames.split(",");
//            strategyConfig.setInclude(split);
//        }else {
//            strategyConfig.setInclude(tableNames);
//        }
//
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setTablePrefix("T_");
//        strategyConfig.setFieldPrefix("F_");
//
//        strategyConfig.isRestControllerStyle();
//        strategyConfig.setControllerMappingHyphenStyle(true);
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        return strategyConfig;
//    }
//}
