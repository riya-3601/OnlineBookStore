package Service;

import DAO.LoginDao;
import Domain.Customer;

import java.sql.SQLException;

public class LoginService {
    private LoginDao loginDao;

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public boolean login(String username, String password) throws SQLException {
        return loginDao.validateCredentials(username, password);
    }

    public boolean isAdmin(String username, String password) throws SQLException {
        return loginDao.isAdmin(username, password);
    }

    public Customer getCustomerDetails(String username, String password) throws SQLException {
        return loginDao.getCustomerDetails(username, password);
    }
}

