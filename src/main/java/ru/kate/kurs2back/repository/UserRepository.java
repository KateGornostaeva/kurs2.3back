package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kate.kurs2back.entity.User;

import java.util.UUID;

//для получения данных и бд
public interface UserRepository extends JpaRepository<User, UUID> {
    
    @Query(nativeQuery = true,
    value = "SELECT * FROM users WHERE login = ?")
    User findByLogin(String login);
    
    @Query(nativeQuery = true,
    value = "SELECT * FROM users WHERE email = ?")
    User findByEmail(String email);
}
