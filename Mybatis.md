# Mybatis简介

https://mybatis.org/mybatis-3/zh/dynamic-sql.html

什么是Mybatis
1、MyBatis 是一款**优秀的持久层框架**
2、它支持**定制化 SQL、存储过程以及高级映射。**
MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
3、MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
4、MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。
5、2013年11月迁移到Github

如何获得Mybatis

Maven仓库

```java
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.2</version>
</dependency>
```

## 持久化

1、 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
2、 内存：断电即失
3、 数据库(Jdbc)，io文件持久化。
4、生活：冷藏. 罐头。

为什么需要他持久化
1、 有一些对象，不能让他丢掉。
2、 内存太贵了

## 持久层

Dao层，Service层，Controller层….

完成持久化工作的代码块
层界限十分明显

## 为什么需要Mybatis

帮助程序猿将数据存入到数据库中。
方便
传统的JDBC代码太复杂了。简化。框架。自动化。
不用Mybatis也可以。更容易上手。 技术没有高低之分
优点：
1.简单易学
2.灵活
3.sql和代码的分离，提高了可维护性。
4.提供映射标签，支持对象与数据库的orm字段关系映射
5.提供对象关系映射标签，支持对象关系组建维护
6.提供xml标签，支持编写动态sql。

最重要的一点：使用的人多！第一个Mybatsi程序

思路：搭建环境–>导入Mybatis–>编写代码–>测试！

## 搭建环境

搭建数据库

```java
CREATE DATABASE `mybatis`;

USE `mybatis`;

CREATE TABLE `user`(
  `id` INT(20) NOT NULL PRIMARY KEY,
  `name` VARCHAR(30) DEFAULT NULL,
  `pwd` VARCHAR(30) DEFAULT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `user`(`id`,`name`,`pwd`) VALUES 
(1,'狂神','123456'),
(2,'张三','123456'),
(3,'李四','123890')
```

新建项目：
新建一个普通的项目
删除src
导入maven依赖

```java
<!--导入依赖-->
<dependencies>
    <!--mysql驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
    </dependency>
    <!--mybatis-->
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
    </dependency>
    <!--junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
</dependencies>
```

## 创建一个模块

编写Mybaits核心配置文件


```java
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--configuration核心配置文件-->
<configuration>
<?xml version="1.0" encoding="UTF-8" ?>
<environments default="development">
    <environment id="development">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="com.mysql.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
            <property name="username" value="root"/>
            <property name="password" value="123456"/>
        </dataSource>
    </environment>
</environments>
  </configuration>
```

编写Mybatis配置类

//sqlSessionFactory --> sqlSession
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
    
    static{
        try {
            //使用Mybatis第一步：获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    //既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
    // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
    public static SqlSession  getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}



编写代码

实体类：



```java
package com.kuang.pojo;

//实体类
public class User {
    private int id;
    private String name;
    private String pwd;
public User() {
}

public User(int id, String name, String pwd) {
    this.id = id;
    this.name = name;
    this.pwd = pwd;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getPwd() {
    return pwd;
}

public void setPwd(String pwd) {
    this.pwd = pwd;
}

@Override
public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", pwd='" + pwd + '\'' +
            '}';
}
```
}


Dao接口：

```java
public interface UserDao {
    List<User> getUserList();
}
```


接口的实现类由原来的userdaoImpl变成了Mapper文件

<?xml version="1.0" encoding="UTF-8" ?>

        <!DOCTYPE mapper
                PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <!--namespace=绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.kuang.dao.UserDao">

<!--select查询语句-->




</mapper>


测试
@Test
public void test(){
    //第一步：获得SqlSession对象
    SqlSession sqlSession = MybatisUtils.getSqlSession();


```java
//方式一：getMapper
UserDao userDao = sqlSession.getMapper(UserDao.class);
List<User> userList = userDao.getUserList();

for (User user : userList) {
    System.out.println(user);
}
//关闭SqlSession
sqlSession.close();
}
可能会遇到的问题：
1、 配置文件没有注册
2、绑定接口错误。
3、方法名不对
4、返回类型不对
5、Maven导出资源问题
```

# CRUD操作（增删改查）

## 编写接口

//根据ID查询用户

```java
User getUserById(int id);
```


编写对应的sql语句

测试：

```java
@Test
public void getUserById() {
    SqlSession sqlSession = MybatisUtils.getSqlSession();

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    
    User user = mapper.getUserById(1);
    System.out.println(user);
    
    sqlSession.close();

}
```



## 插入

```java
<!--对象中的属性，可以直接取出来-->
<insert id="addUser" parameterType="com.kuang.pojo.User">
    insert into mybatis.user (id, name, pwd) values (#{id},#{name},#{pwd});
</insert>
```

## 修改

```java
<update id="updateUser" parameterType="com.kuang.pojo.User">
    update mybatis.user set name=#{name},pwd=#{pwd}  where id = #{id} ;
</update>
```

## 删除

```java
    <delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id = #{id};
    </delete>
**注意：增删改需要提交事务！！！**
```

# 配置解析

## 核心配置文件

1、configuration（配置）
2、properties（属性）
3、settings（设置）
4、typeAliases（类型别名）
5、typeHandlers（类型处理器）
6、objectFactory（对象工厂）
7、plugins（插件）
8、environments（环境配置）
9、environment（环境变量）
10、transactionManager（事务管理器）
11、dataSource（数据源）
12、databaseIdProvider（数据库厂商标识）
13、mappers（映射器）

## 配置环境

MyBatis 可以配置成适应多种环境

 不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。

 学会使用配置多套运行环境！

 Mybatis默认的事务管理器就是 JDBC ， 连接池 ： POOLED

属性
我们可以通过properties属性来实现引用配置文件

这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递。【db.properties】

编写一个配置文件

```java
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUnicode=true&characterEncoding=UTF-8
username=root//修改为自己的用户名
password=123456//修改为自己的密码
```


1、可以直接引入外部文件
2、可以在其中增加一些属性配置
3、如果两个文件有同一个字段，优先使用外部配置文件

## 类型别名

类型别名是为 Java 类型设置一个短的名字‘
存在的意义仅在于用来减少类完全限定名的冗余

```java
<!--可以给实体类起别名-->
<typeAliases>
    <typeAlias type="com.kuang.pojo.User" alias="User"/>
</typeAliases>
```


也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如：

扫描实体类的包，它的默认别名就为这个类的 类名，首字母小写

```java
<!--可以给实体类起别名-->
<typeAliases>
    <package name="com.kuang.pojo"/>
</typeAliases>
```


在实体类比较少的时候，使用第一种方式。

如果实体类十分多，建议使用第二种。

第一种可以DIY别名，第二种则不行，如果非要改，
需要在实体上增加注解

```java
@Alias("user")
public class User {}
```

## 其他配置

. typeHandlers（类型处理器）
. objectFactory（对象工厂）
. plugins插件
. mybatis-generator-core
. mybatis-plus
. 通用mapper

# 映射器

MapperRegistry：注册绑定我们的Mapper文件
方式一：

```java
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <mapper resource="com/kuang/dao/UserMapper.xml"/>
</mappers>
```


方式二：使用class文件绑定注册

```java
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <mapper class="com.kuang.dao.UserMapper"/>
</mappers>
```


**注意点：**

接口和他的Mapper配置文件必须同名！！！
接口和他的Mapper配置文件必须在同一个包下！！！

方式三：使用扫描包进行注入绑定

```java
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <package name="com.kuang.dao"/>
</mappers>
  
```

## 生命周期和作用域

![image-20220806161210193](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220806161210193.png)

SqlSessionFactoryBuilder：

**一旦创建了 SqlSessionFactory，就不再需要它了**
**局部变量**

SqlSessionFactory：
说白了就是可以想象为 ：数据库连接池
SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
因此 SqlSessionFactory 的最佳作用域是应用作用域。
最简单的就是使用单例模式或者静态单例模式。

SqlSession

连接到连接池的一个请求！
SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
用完之后需要赶紧关闭，否则资源被占用！

![image-20220806161320221](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220806161320221.png)

## 解决属性名和字段名不一致的问题

```
public class User {

private int id;
private String name;
private String password;


测试：


//    select * from mybatis.user where id = #{id}
//类型处理器
//    select id,name,pwd from mybatis.user where id = #{id}
```



解决方法：

<select id="getUserById" resultType="com.kuang.pojo.User">
    select id,name,pwd as password from mybatis.user where id = #{id}
</select>


## ResultMap

```java
id   name   pwd
id   name   password



```

```java
<!--结果集映射-->
<resultMap id="UserMap" type="User">
    <!--column数据库中的字段，property实体类中的属性-->
    <result column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>
```




<select id="getUserById" resultMap="UserMap">
    select * from mybatis.user where id = #{id}
</select>



**resultMap 元素是 MyBatis 中最重要最强大的元素**
ResultMap 的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系就行了。
ResultMap 最优秀的地方在于，虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。
如果世界总是这么简单就好了

# 日志

## 日志工厂

如果一个数据库操作，出现了异常，我们需要排错，日志就是最好的助手！

曾经：sout、debug

现在：日志工
 SLF4J
LOG4J 【掌握】
LOG4J2
JDK_LOGGING
COMMONS_LOGGING
STDOUT_LOGGING 【掌握】
NO_LOGGING

STDOUT_LOGGING

```java
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```



## Log4j

什么是Log4j？

Log4j是Apache的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件；
我们也可以控制每一条日志的输出格式；
通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程；
最令人感兴趣的就是，这些可以通过一个配置文件来灵活地进行配置，而不需要修改应用的代码

先导入log4j的包

```java
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>


```


og4j.properties

```java
#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/rzp.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sq1.PreparedStatement=DEBUG
```



配置settings为log4j实现

```java
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```


测试运行
Log4j简单使用6. 在要使用Log4j的类中，导入包 import org.apache.log4j.Logger;
日志对象，参数为当前类的class对象

Logger logger = Logger.getLogger(UserDaoTest.class);


日志级别

logger.info("info: 测试log4j");
logger.debug("debug: 测试log4j");
logger.error("error:测试log4j");

# 分页

思考：为什么要分页？
减少数据的处理量！！！

## 使用Limit分页

```java
SELECT * from user limit startIndex,pageSize 
```


接口：

```java
//分页
List<User> getUserByLimit(Map<String,Integer> map);
```


Mapper.xml

<!--分页查询-->
<select id="getUserByLimit" parameterType="map" resultMap="UserMap">
    select * from user limit #{startIndex},#{pageSize}
</select>

测试：

 

```java
@Test
    public void getUserByLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex",1);
        map.put("pageSize",2);
        List<User> list = mapper.getUserByLimit(map);
        for (User user : list) {
            System.out.println(user);
        }
    }
```



## RowRounds

不再使用SQL进行分页
接口

```java
//分页2
List<User> getUserByRowBounds();
```


mapper.xlm

<!--分页查询2-->
<select id="getUserByRowBounds">
    select * from user
</select>
```java
public void getUserByRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //RowBounds实现
        RowBounds rowBounds = new RowBounds(1, 2);
        //通过Java代码层面实现分页
        List<User> userList = sqlSession.selectList("com.kaung.dao.UserMapper.getUserByRowBounds", null, rowBounds);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
```



## 分页的插件

pagehelper

https://pagehelper.github.io/

# 使用注解开发

## 面向接口开发

三个面向区别

面向对象是指，我们考虑问题时，以对象为单位，考虑它的属性和方法；
面向过程是指，我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现；
接口设计与非接口设计是针对复用技术而言的，与面向对象（过程）不是一个问题，更多的体现就是对系统整体的架构；

## 使用注解开发

注解在接口上实现

```xml
@Select("select * from user")
List<User> getUsers();
```


需要在核心配置文件中绑定接口

```xml
<mappers>
    <mapper class="com.kuang.dao.UserMapper"/>
</mappers>
```


测试：
本质是反射机制
底层：实现了动态代理

Mybatis详细的执行流程

![image-20220806162431849](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220806162431849.png)



## 注解CRUD

//方法存在多个参数，所有的参数前面必须加上@Param("id")注解

```xml
@Delete("delete from user where id = ${uid}")
int deleteUser(@Param("uid") int id);
```


关于@Param( )注解
基本类型的参数或者String类型，需要加上
引用类型不需要加
如果只有一个基本类型的话，可以忽略，但是建议大家都加上
我们在SQL中引用的就是我们这里的@Param()中设定的属性名
注意: xml中参数形式#{} 和 注解中参数形式${}


注解示例：增删改查
注意：我们必须将接口注册绑定到我们的核心配置文件中

# LomBook

Lombok项目是一个Java库，它会自动插入编辑器和构建工具中，Lombok提供了一组有用的注释，用来消除Java类中的大量样板代码。仅五个字符(@Data)就可以替换数百行代码从而产生干净，简洁且易于维护的Java类。

使用步骤：

在IDEA中安装Lombok插件
在项目中导入lombok的jar包

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
    <scope>provided</scope>
</dependency>
```

```java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
```




```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
}
```



# 多对一处理

多个学生一个老师；

```sql
alter table student ADD CONSTRAINT fk_tid foreign key (tid) references teacher(id)
```



## 测试环境搭建

1、 导入lombok
2、 新建实体类Teacher,Student
3、 建立Mapper接口
4、 建立Mapper.xml文件
5、 在核心配置文件中绑定注册我们的Mapper接口或者文件 【方式很多，随心选】
6、 测试查询是否能够成功

## 按照查询嵌套处理

<!--
     思路：

        1. 查询所有的学生信息
                2. 根据查询出来的学生的tid寻找特定的老师 (子查询)
        -->

<select id="getStudent" resultMap="StudentTeacher">
    select * from student
</select>
```java
<resultMap id="StudentTeacher" type="student">
    <result property="id" column="id"/>
    <result property="name" column="name"/>
    <!--复杂的属性，我们需要单独出来 对象：association 集合：collection-->
    <collection property="teacher" column="tid" javaType="teacher" select="getTeacher"/>
</resultMap>
```



<select id="getTeacher" resultType="teacher">
    select * from teacher where id = #{id}
</select>
## 按照结果嵌套处理

​    <!--按照结果进行查询-->

    <select id="getStudent2" resultMap="StudentTeacher2">
        select s.id sid , s.name sname, t.name tname
        from student s,teacher t
        where s.tid=t.id
    </select>


```xml
  <!--结果封装，将查询出来的列封装到对象属性中-->
    <resultMap id="StudentTeacher2" type="student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" javaType="teacher">
            <result property="name" column="tname"></result>
        </association>
    </resultMap>
```


回顾Mysql多对一查询方式:

子查询 （按照查询嵌套）
联表查询 （按照结果嵌套）

# 一对多处理

一个老师多个学生；
对于老师而言，就是一对多的关系；

## 环境搭建

实体类
@Data

```xml
public class Student {
    private int id;
    private String name;
    private int tid;
}
```

```java
@Data
public class Teacher {
    private int id;
    private String name;

    //一个老师拥有多个学生
    private List<Student> students;

}
```



## 按照结果嵌套处理

```xml
<!--按结果嵌套查询-->

<select id="getTeacher" resultMap="StudentTeacher">
    SELECT s.id sid, s.name sname,t.name tname,t.id tid FROM student s, teacher t
    WHERE s.tid = t.id AND tid = #{tid}
</select>

<resultMap id="StudentTeacher" type="Teacher">
    <result property="id" column="tid"/>
    <result property="name" column="tname"/>
    <!--复杂的属性，我们需要单独处理 对象：association 集合：collection
    javaType=""指定属性的类型！
    集合中的泛型信息，我们使用ofType获取
    -->
    <collection property="students" ofType="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <result property="tid" column="tid"/>
    </collection>
</resultMap>
```


关联 - association 【多对一】
集合 - collection 【一对多】
javaType & ofType
JavaType用来指定实体类中的类型
ofType用来指定映射到List或者集合中的pojo类型，泛型中的约束类型

注意点：

保证SQL的可读性，尽量保证通俗易懂
注意一对多和多对一，属性名和字段的问题
如果问题不好排查错误，可以使用日志，建议使用Log4j

面试高频

Mysql引擎
InnoDB底层原理
索引
索引优化

# 动态SQL

**什么是动态SQL：动态SQL就是根据不同的条件生成不同的SQL语句**

所谓的动态SQL，本质上还是SQL语句，只是我们可以在SQL层面，去执行一个逻辑代码
动态 SQL 是 MyBatis 的强大特性之一。如果你使用过 JDBC 或其它类似的框架，你应该能理解根据不同条件拼接 SQL 语句有多痛苦，例如拼接时要确保不能忘记添加必要的空格，还要注意去掉动态 SQL 是 MyBatis 的强大特性之一。如果你使用过 JDBC 或其它类似的框架，你应该能理解根据不同条件拼接 SQL 语句有多痛苦，例如拼接时要确保不能忘记添加必要的空格，还要注意去掉列表最后一个列名的逗号。利用动态 SQL，可以彻底摆脱这种痛苦。

- if
- choose (when, otherwise)
- trim (where, set)
- foreach

列表最后一个列名的逗号。利用动态 SQL，可以彻底摆脱这种痛苦。



## 搭建环境

```sql
CREATE TABLE `mybatis`.`blog`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `title` varchar(30) NOT NULL COMMENT '博客标题',
  `author` varchar(30) NOT NULL COMMENT '博客作者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `views` int(30) NOT NULL COMMENT '浏览量',
  PRIMARY KEY (`id`)
)
```


创建一个基础的工程
导包 lombok
编写配置文件
编写实体类

```java
public class Blog {
    private int id;
    private String title;
    private String author;

    private Date createTime;// 属性名和字段名不一致
    private int views;

}
```


在配置文件中添加驼峰命名规则 解决属性名和字段名不一致的问题

```xml
<settings>
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```



## if示例

<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from mybatis.blog where 1=1
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != author">
        and author = #{author}
    </if>
</select>
## choose、when、otherwise 示例

<select id="queryBlogchoose" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <choose>
            <when test="title != null">
                title = #{title}
            </when>
            <when test="author != null">
                and author = #{author}
            </when>
            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>


## trim、where、set 示例

```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author = #{author}
        </if>
    </set>
    where id = #{id}
</update>
```


SQL片段
有些时候我们有一些公共部分

使用sql便签抽取公共部分
在使用的地方使用include标签

```xml
<sql id="if-title-author">
    <if test="title != null">
        title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</sql>
```

<select id="queryBlogIF" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <include refid="if-title-author"></include>
    </where>
</select>
## for-each 示例

<!--ids是传的，#{id}是遍历的-->

<select id="queryBlogForeach" parameterType="map" resultType="Blog">
    select * from mybatis.blog
    <where>
        <foreach collection="ids" item="id" open="and ("
                 close=")" separator="or">
            id=#{id}
        </foreach>
    </where>
</select>
```java
@Test
public void queryBlogForeach(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
    Map map = new HashMap();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ids.add(1);
    ids.add(3);
    map.put("ids",ids);
    List<Blog> list = blogMapper.queryBlogForeach(map);
    for (Blog blog : list) {
        System.out.println(blog);
    }
    sqlSession.close();
}
```

*foreach* 元素的功能非常强大，它允许你指定一个集合，声明可以在元素体内使用的集合项（item）和索引（index）变量。它也允许你指定开头与结尾的字符串以及集合项迭代之间的分隔符。这个元素也不会错误地添加多余的分隔符，看它多智能！

# 缓存

## 简介

查询 ： 连接数据库，耗资源
一次查询的结果，给他暂存一个可以直接取到的地方 --> 内存：缓存
我们再次查询的相同数据的时候，直接走缓存，不走数据库了
什么是缓存[Cache]？
存在内存中的临时数据
将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上（关系型数据库文件）查询，从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题
为什么使用缓存？
减少和数据库的交互次数，减少系统开销，提高系统效率

什么样的数据可以使用缓存？
经常查询并且不经常改变的数据 【可以使用缓存】

## Mybatis缓存

MyBatis包含一个非常强大的查询缓存特性，它可以非常方便的定制和配置缓存，缓存可以极大的提高查询效率。
MyBatis系统中默认定义了两级缓存：一级缓存和二级缓存

默认情况下，只有一级缓存开启（SqlSession级别的缓存，也称为本地缓存）
二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
为了提高可扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来定义二级缓存。

## 一级缓存

一级缓存也叫本地缓存：SqlSession
与数据库同一次会话期间查询到的数据会放在本地缓存中
以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库

测试步骤：
开启日志
测试在一个Session中查询两次记录

```java
@Test
public void test1() {
 SqlSession sqlSession = MybatisUtils.getSqlSession();
 UserMapper mapper = sqlSession.getMapper(UserMapper.class);
 User user = mapper.getUserById(1);
 System.out.println(user);
 System.out.println("=====================================");
 User user2 =  mapper.getUserById(1);
 System.out.println(user2 == user);
 }
```


查看日志的输出

缓存失效的情况：
查询不同的东西
增删改操作，可能会改变原来的数据，所以必定会刷新缓存
查询不同的Mapper.xml
手动清理缓存

```java
sqlSession.clearCache();
```

## 二级缓存

二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
基于namespace级别的缓存，一个名称空间，对应一个二级缓存
工作机制
    一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中
    如果会话关闭了，这个会员对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中
    新的会话查询信息，就可以从二级缓存中获取内容
    不同的mapper查询出的数据会放在自己对应的缓存（map）中

一级缓存开启（SqlSession级别的缓存，也称为本地缓存）

二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
为了提高可扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来定义二级缓存。
步骤：
1.开启全局缓存

```xml
<!--显示的开启全局缓存-->
<setting name="cacheEnabled" value="true"/>
```


在Mapper.xml使用缓存

```xml
<!--在当前Mapper.xml中使用二级缓存-->
<cache
     eviction="FIFO"
     flushInterval="60000"
     size="512"
     readOnly="true"/>
```


测试：我们要将实体类序列化否则会报错

## 缓存原理


<select id="getUserById" resultType="user" useCache="true">
        select * from user where id = #{id}
</select>
![image-20220806163827002](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220806163827002.png)

## 自定义缓存

导包

```xml
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.2.1</version>
</dependency>
```


在mapper中指定使用我们的ehcache缓存实现

```xml
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

# BUG解决

Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure

![image-20220815171030065](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220815171030065.png)

![image-20220815171208462](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220815171208462.png)

Cause: org.apache.ibatis.builder.BuilderException: Mapper's namespace cannot be empty

![image-20220815230741304](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220815230741304.png)

Error parsing Mapper XML. The XML location is 'com/boar/mapper/UserMapper.xml'. Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias 'user'.  Cause: java.lang.ClassNotFoundException: Cannot find class: user

解决不支持Java5版本

```xml
<build>
        <plugins>
            <plugin><!--设置JDK 版本-->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

```

解决数据库查询输出为地址的问题！

![image-20220816142914784](/Users/boar/Downloads/求职/听课记录/:Users:boar:Downloads:求职:听课记录:Users:boar:Library:Application Support:typora-user-images:image-20220816142914784.png)

# 已解决 Cannot resolve property 错误

![image-20220816144432229](/Users/boar/Library/Application Support/typora-user-images/image-20220816144432229.png)

![image-20220816144555016](/Users/boar/Library/Application Support/typora-user-images/image-20220816144555016.png)

Caused by: org.springframework.beans.PropertyBatchUpdateException; nested PropertyAccessExceptions (1) are:
PropertyAccessException 1: org.springframework.beans.MethodInvocationException: Property 'driverClassName' threw exception; nested exception is java.lang.IllegalStateException: Could not load JDBC driver class [com.boar.mysql.Driver]

![image-20220816151806769](/Users/boar/Downloads/求职/听课记录/:Users:boar:Library:Application Support:typora-user-images:image-20220816151806769.png)
