package org.example.gotalearn.dao;

import org.example.gotalearn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Long> {
}
