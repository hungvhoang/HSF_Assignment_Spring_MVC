package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.Account;
import HSF301_Assignment_Spring_MVC.pojos.request.LoginRequest;
import HSF301_Assignment_Spring_MVC.repositories.AccountRepository;
import HSF301_Assignment_Spring_MVC.services.IAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthenticateService implements IAuthenticateService {

    private final AccountRepository accountRepository;

    @Autowired
    public AuthenticateService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public String Login(LoginRequest loginRequest, Model model) {
        Account dbaccount = accountRepository.getAccountByUserName(loginRequest.getUsername());
        if (
                loginRequest.getPassword() != null
                        && loginRequest.getUsername() != null
        ) {
            if (dbaccount == null) {
//                System.out.println("NULL ROI CON");
                model.addAttribute("loggingMessage","not found");
                return "login";
            } else if (loginRequest.getPassword().matches(dbaccount.getCustomer().getPassword())) {
//                model.addAttribute("loggingMessage","YEEEE");
                return "about";
            }
        }
        return null;

    }


}
