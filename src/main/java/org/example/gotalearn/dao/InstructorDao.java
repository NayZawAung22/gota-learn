package org.example.gotalearn.dao;

import org.example.gotalearn.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDao extends JpaRepository<Instructor,Long> {
}
