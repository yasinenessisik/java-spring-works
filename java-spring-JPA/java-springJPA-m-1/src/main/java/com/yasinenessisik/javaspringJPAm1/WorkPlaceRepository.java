package com.yasinenessisik.javaspringJPAm1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPlaceRepository extends JpaRepository<Workplace,Integer> {
}
