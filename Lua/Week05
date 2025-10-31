### //ReviewRepository.java
```java
package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
           SELECT r
           FROM Review r
           JOIN FETCH r.user u
           ORDER BY r.createdAt DESC
           """)
    List<Review> findAllWithUserOrderByCreatedAtDesc();
}
```

### //UserRepository.java (사용자 정보, 리뷰만)

```java
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
           SELECT u
           FROM User u
           LEFT JOIN FETCH u.reviews r
           WHERE u.id = :userId
           """)
    Optional<User> findUserWithReviewsById(@Param("userId") Long userId);
}
```

### // In MissionRepository.java

```java
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
           SELECT m
           FROM Mission m
           WHERE m.user.id = :userId AND m.success = :success
           ORDER BY m.successTime DESC
           """)
    List<Mission> findMissionsByUserAndSuccess(
        @Param("userId") Long userId,
        @Param("success") boolean success,
        Pageable pageable
    );
}
```
