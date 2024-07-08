package HSF301_Assignment_Spring_MVC.repositories;

import HSF301_Assignment_Spring_MVC.pojos.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.customer.email = :username")
    Account getAccountByUserName(@Param("username") String username);

}
