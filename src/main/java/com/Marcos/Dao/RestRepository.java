package com.Marcos.Dao;

import com.Marcos.Entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
@Qualifier("RestRepositoryData")
public interface RestRepository extends CrudRepository<Student, Integer> {
}
