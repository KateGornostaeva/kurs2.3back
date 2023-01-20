package ru.kate.kurs2back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kate.kurs2back.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
