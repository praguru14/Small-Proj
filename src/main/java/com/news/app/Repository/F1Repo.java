package com.news.app.Repository;

import com.news.app.Model.F1Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface F1Repo extends JpaRepository<F1Model,Integer> {
}
