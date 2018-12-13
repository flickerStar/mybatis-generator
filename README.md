# mybatis

##mybatis-generator 自动化生成（内部改造成当前所需样式）

使用须知：

1.可以修改generator.xml中的数据来决定生成的表和生成路径 targetPackage="com.mybatis.study.mapper" 

2.待使用的项目可以新建com.mybatis.generator.util 包,将公用UtilMapper.java和UtilMapper.xml拷贝入项目

3.待使用项目可以新建com.mybatis.generator.pojo 包,将公用QueryExample.java拷贝入项目

4.直接运行src/test/java 中test.java方法即可生成

5.生成的数据在自己指定的com.mybatis.study.mapper和com.mybatis.study.pojo下并且mybatis-generator默认使用的xxExample可以不适用废弃掉了此时
