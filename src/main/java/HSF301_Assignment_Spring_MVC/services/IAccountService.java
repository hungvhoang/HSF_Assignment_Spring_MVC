package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.Account;

import java.util.List;

public interface IAccountService {

    public void save(Account account);

    public void update(Account account);

    public void delete(int id);

    public List<Account> getAll();

    public Account findByID(int id);

}
