package netology.aloch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    //without authorzation
    @GetMapping("/hello")
    public String helloPage() {
        return ("Hello ALL from Security Application");
    }

    //authorization needed
    @GetMapping("/haverights")
    public String restrictedPage() {
        return ("Hello, Authorized user");
    }

    //authorization needed
    @GetMapping("/authorize")
    public String paramAuthorize(@RequestParam("user") String user, @RequestParam("password") String password) {
        return ("Query Params are: <br> user = " + user + " , password = " + password);
    }
}
