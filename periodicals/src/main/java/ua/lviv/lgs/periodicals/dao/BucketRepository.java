package ua.lviv.lgs.periodicals.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.periodicals.domain.Bucket;
import ua.lviv.lgs.periodicals.domain.User;

public interface BucketRepository extends JpaRepository<Bucket, Integer>{

	List<User> findByEmail(String email);
}
