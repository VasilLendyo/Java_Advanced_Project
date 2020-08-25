package ua.lviv.lgs.periodicals.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.periodicals.domain.Bucket;

public interface BucketRepository extends JpaRepository<Bucket, Integer>{

	
}
