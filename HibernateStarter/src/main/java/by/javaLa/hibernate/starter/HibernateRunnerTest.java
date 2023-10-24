//package by.javaLa.hibernate.starter;
//
//import by.javaLa.hibernate.starter.entity.User;
//import jakarta.persistence.Column;
//import jakarta.persistence.Table;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Field;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class HibernateRunnerTest {
//    @Test
//    public void testHibernateApi() throws SQLException, IllegalAccessException {
//        var user = User.builder()
//                .username("lera_mazitova1@mail.ru")
//                .firstname("Lera")
//                .lastname("Mazitova")
//                .birthDate(LocalDate.of(1998, 02, 18))
//                .age(25)
//                .build();
//
//        var sql = """
//        insert into
//         %s
//         (%s)
//         values
//         (%s)
//         """;
//
//        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(table -> table.schema() + "." + table.name())
//                .orElse(user.getClass().getName());
//
//        // берем поле по имени или по аннотации
//        Field[] fields = user.getClass().getDeclaredFields();
//        var columnNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name).orElse(field.getName()))
//                .collect(Collectors.joining(", "));
//
//        var columnValues = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
//        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
//        for (int i = 0; i < fields.length; i++) {
//            fields[i].setAccessible(true);
//            preparedStatement.setObject(i + 1, fields[i].get(user));
//        }
//        System.out.println(preparedStatement);
//
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//        connection.close();
//
//    }
//}