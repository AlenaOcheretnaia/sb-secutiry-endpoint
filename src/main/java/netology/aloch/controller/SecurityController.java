package netology.aloch.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String helloPage() {
        return ("Hello, " + SecurityContextHolder.getContext().getAuthentication().getName() + " from Security Application");
    }

    //только для пользователей с ролью "READ"(используйте @Secured)
    @GetMapping("have-role-read")
    @Secured("ROLE_READ")
    public String checkRoleRead() {
        return ("Hello, user with READ role");
    }

    //только для пользователей с ролью "WRITE"(используйте @RolesAllowed)
    @GetMapping("have-role-write")
    @RolesAllowed("ROLE_WRITE")
    public String checkRoleWrite() {
        return ("Hello, user with WRITE role");
    }

    //если у пользователя есть хотя бы одна из ролей из "WRITE", "DELETE"(используйте pre/post аннотации)
    @GetMapping ("have-role-wr-del")
    @PreAuthorize("hasRole('WRITE') or hasRole('DELETE')")
    public String checkWriteDeleteRole() {
        return "User has WRITE or DELETE role";
    }

    //принимает в качестве query параметра имя пользователя(username),
    //возвращает значения только если username совпадает с именем пользователя в вашем объекте Authentication,
    //который Spring security сохраняет в SecurityContextHolder после успешной аутентификации
    @GetMapping("/check-username")
    @PostAuthorize("#username == authentication.principal.username")
    public String paramUsername(@RequestParam("username") String username) {
        return ("Hello user with name = " + username);
    }

}
