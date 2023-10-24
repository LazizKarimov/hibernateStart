package by.javaLa.hibernate.starter;

import by.javaLa.hibernate.starter.convertor.BirthdayConverter;
import by.javaLa.hibernate.starter.entity.Birthday;
import by.javaLa.hibernate.starter.entity.Role;
import by.javaLa.hibernate.starter.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
//        configuration.addAnnotatedClass(User.class);
        try (var sessionFactoey = configuration.buildSessionFactory();
             var session = sessionFactoey.openSession();) {
            session.beginTransaction();
            User user = User.builder()
                    .username("lera_mazitova98@mail.ru")
                    .firstname("Lera")
                    .lastname("Mazitova")
                    .birthDate(new Birthday(LocalDate.of(1998, 02, 18)))
                    .role(Role.ADMIN)
                    .build();
//            session.save(user);
//            session.update(user);
//            session.saveOrUpdate(user);
//            session.delete(user);
            User user1 = session.get(User.class, "lera_mazitova98@mail.ru");
            System.out.println(user1);
            session.getTransaction().commit();
        }
    }
}
