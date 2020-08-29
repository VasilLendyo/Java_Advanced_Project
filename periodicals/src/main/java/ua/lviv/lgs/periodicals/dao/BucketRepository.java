package ua.lviv.lgs.periodicals.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.periodicals.domain.Bucket;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Integer>{

	
}
