package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.project.ProjectRequest;
import com.learning.project.loveable_clone.dto.project.ProjectResponse;
import com.learning.project.loveable_clone.dto.project.ProjectSummaryResponse;
import com.learning.project.loveable_clone.entity.Project;
import com.learning.project.loveable_clone.entity.User;
import com.learning.project.loveable_clone.mapper.ProjectMapper;
import com.learning.project.loveable_clone.repository.ProjectRepository;
import com.learning.project.loveable_clone.repository.UserRepository;
import com.learning.project.loveable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

        //Way 1 - Using Stream
        /*return projectRepository.findAllAccessibleByUser(userId)
                .stream()
                .map(projectMapper::toProjectSummaryResponse)
                .toList();*/
        //Way 2 - Using Mapper
        return projectMapper.toListOfProjectSummaryResponse(projectRepository
                .findAllAccessibleByUser(userId));
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not the owner of this project " +
                    "and thus not allowed to rename the project!");
        }
        project.setName(request.name());
        return projectMapper.toProjectResponse(projectRepository.save(project));
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not the owner of this project and " +
                    "thus not allowed to delete the project!");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // INTERNAL functions
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }
}
