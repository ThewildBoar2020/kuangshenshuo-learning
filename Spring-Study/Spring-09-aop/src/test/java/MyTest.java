import com.boar.service.UserService;
import com.boar.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //动态代理的是接口：注意点
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}

