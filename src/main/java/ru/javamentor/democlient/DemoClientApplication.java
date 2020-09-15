package ru.javamentor.democlient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import ru.javamentor.democlient.service.SiteService;
import ru.javamentor.democlient.service.UserService;

@EnableRetry
@EnableCaching
@EnableHystrix
@SpringBootApplication
public class DemoClientApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(DemoClientApplication.class, args);
//        SiteService siteService = ctx.getBean(SiteService.class);
        UserService userService = ctx.getBean(UserService.class);


            System.out.println("Users info:");

            userService.findAllUsers().forEach(user -> System.out.println("User. Id: " + user.getId() +
                    ", Name: " + user.getName() +
                    ", Last Name: " + user.getLastName() +
                    ", Age: " + user.getLastName()));
/*

    Получение всех пользователей - …/api/users ( GET )
    Добавление пользователя - …/api/users ( POST )
    Изменение пользователя - …/api/users ( PUT )
    Удаление пользователя - …/api/users /{id} ( DELETE )

 */



    }
}

