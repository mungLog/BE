package com.example.munglog.User.Repository;

import com.example.munglog.User.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        User findUserByUserid(String userid);

        Optional<User> findFirstByUserid(String userId);

        Optional<User> findByUserid(String userid);

        @Query("SELECT u FROM User u WHERE u.username = :username")
        Optional<User> findOneWithAuthoritiesByUsername(@Param("username") String username);

        List<User> findByUsernameContaining(String username);


}
