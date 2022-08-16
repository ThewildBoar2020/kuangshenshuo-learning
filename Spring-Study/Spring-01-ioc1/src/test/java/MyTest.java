import com.boar.Dao.UserDaoImpl;
import com.boar.Dao.UserDaoMysqlImpl;
import com.boar.Dao.UserDaoOracleImpl;
import com.boar.service.UserService;
import com.boar.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
//        //用户实际调用的是业务层，dao层他们不需要接触
//        UserService userService=new UserServiceImpl();
//        ((UserServiceImpl) userService).setUserDao(new UserDaoOracleImpl());
//        userService.getUser();
        //获取ApplicationContext:拿到spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        //容器在手，天下我有，需要什么，就直接get什么
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser();
    }
}
