/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import silab.nst.dan9.dataaccess.config.Config;
import silab.nst.dan9.dataaccess.domain.User;
import silab.nst.dan9.dataaccess.service.impl.UserServiceImpl;

import java.util.List;

/**
 *
 * @author laptop-02
 */

@Component
public class Main {

    @Autowired
    UserServiceImpl userService;
    
    public static void main(String[] args) {

        BeanFactory context = new AnnotationConfigApplicationContext(Config.class);

        Main main = context.getBean(Main.class);
        main.saveUser();
        main.getAllUsers();
        main.deleteUser();
        main.updateUser();
    }
    
    private void saveUser(){
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setUsername("jsmith@example.com");
        user.setPassword("jsmithPass123");
        userService.add(user);
    }
    private void deleteUser(){
        userService.delete(3l);
    }
    private void updateUser(){
        User user = new User(2l, "Jack", "Taylor", "jtaylor@example.com", "jtaylorpass123");
        userService.update(user);
    }
    private void getAllUsers(){
        List<User> allUsers = userService.getAll();
        for (User user:allUsers
             ) {
            System.out.println(user);
        }
    }
}
