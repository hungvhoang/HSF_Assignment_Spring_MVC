package HSF301_Assignment_Spring_MVC.services.googleAuthen;

import HSF301_Assignment_Spring_MVC.pojos.Account;
import HSF301_Assignment_Spring_MVC.pojos.Customer;
import HSF301_Assignment_Spring_MVC.repositories.AccountRepository;
import HSF301_Assignment_Spring_MVC.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        Customer customer = customerRepository.findByEmail(email);
        String address = null;
        String phoneNumber = null;
        Map<String, Object> additionalAttributes = null;
        try {
            String accessToken = userRequest.getAccessToken().getTokenValue();
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://people.googleapis.com/v1/people/me?personFields=addresses,phoneNumbers";
            additionalAttributes = restTemplate.getForObject(
                    url + "&access_token=" + accessToken, Map.class
            );

            if (additionalAttributes != null) {
                if (additionalAttributes.get("addresses") != null) {
                    address = ((List<Map<String, Object>>) additionalAttributes.get("addresses"))
                            .get(0).get("formattedValue").toString();
                }
                if (additionalAttributes.get("phoneNumbers") != null) {
                    phoneNumber = ((List<Map<String, Object>>) additionalAttributes.get("phoneNumbers"))
                            .get(0).get("value").toString();
                }
            }
        } catch (Exception e) {
            // Handle potential errors here (log the error, notify admin, etc.)
            e.printStackTrace();
        }

        if (additionalAttributes != null) {
            if (additionalAttributes.get("addresses") != null) {
                address = ((List<Map<String, Object>>) additionalAttributes.get("addresses")).get(0).get("formattedValue").toString();
            }
            if (additionalAttributes.get("phoneNumbers") != null) {
                phoneNumber = ((List<Map<String, Object>>) additionalAttributes.get("phoneNumbers")).get(0).get("value").toString();
            }
        }

        if (customer == null) {
            customer = new Customer();
            Account acc = new Account();
            acc.setRole("Customer");
            acc.setAccountName((String) attributes.get("name"));
            customer.setEmail(email);
            customer.setCustomerName((String) attributes.get("name"));
            customer.setPassword((String) attributes.get("password"));
            customer.setBirthday((Date) attributes.get("birthday"));
            customer.setMobile(phoneNumber);
            customer.setAccount(acc);
            customerRepository.save(customer);
            acc.setCustomer(customer);
            accountRepository.save(acc);
        }

        return oAuth2User;
    }
}
