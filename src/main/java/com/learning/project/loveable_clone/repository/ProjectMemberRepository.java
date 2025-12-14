package com.learning.project.loveable_clone.repository;

import com.learning.project.loveable_clone.entity.ProjectMember;
import com.learning.project.loveable_clone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    boolean existsById(ProjectMemberId projectMemberId);

    Optional<ProjectMember> findById(ProjectMemberId projectMemberId);

    void deleteById(ProjectMemberId projectMemberId);
}
