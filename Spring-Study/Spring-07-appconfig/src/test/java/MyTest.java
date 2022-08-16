import com.boar.MyConfig.MyConfig;
import com.boar.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//这里
public class MyTest {
    public static void main(String[] args) {
        //如果完全使用配置类
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
         User getuser = (User) context.getBean("getUser");
        System.out.println(getuser.getName());


    }
}
