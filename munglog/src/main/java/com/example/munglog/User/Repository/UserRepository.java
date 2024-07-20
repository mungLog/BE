package com.example.munglog.User.Repository;

import com.example.munglog.User.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findUserByuserId(String userid);

}
