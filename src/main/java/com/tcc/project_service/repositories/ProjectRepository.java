package com.tcc.project_service.repositories;


import com.tcc.project_service.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // 🔹 Aquí puedes añadir métodos personalizados si lo necesitas
}

