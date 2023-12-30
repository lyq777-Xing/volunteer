import dao.impl.IRoleDao;
import entity.Role;

/**
 * @author lyq
 * @time 2023/12/30 14:53
 */
public class RoleTest {
    public static void main(String[] args) {
        IRoleDao iRoleDao =
                new IRoleDao();
        Role r = iRoleDao.findByName("志愿者");
        System.out.println(r);
    }
}
