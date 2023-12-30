import dao.impl.IUserDao;

/**
 * @author lyq
 * @time 2023/12/30 13:55
 */
public class UserTest {
    public static void main(String[] args) {
        IUserDao iUserDao = new IUserDao();
//        System.out.println(iUserDao.login("lyq", "123456"));
//        System.out.println(iUserDao.register("lyq", "123456", "123"));
        System.out.println(iUserDao.update("3119010951501050", "lyq", "123456", "1", 8.8, 0, "123"));
    }
}
