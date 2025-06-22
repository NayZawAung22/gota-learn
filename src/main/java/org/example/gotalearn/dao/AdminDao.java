package org.example.gotalearn.dao;

import org.example.gotalearn.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Long> {
}
