package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.project.ProjectRequest;
import com.learning.project.loveable_clone.dto.project.ProjectResponse;
import com.learning.project.loveable_clone.dto.project.ProjectSummaryResponse;
import com.learning.project.loveable_clone.entity.Project;
import com.learning.project.loveable_clone.entity.ProjectMember;
import com.learning.project.loveable_clone.entity.ProjectMemberId;
import com.learning.project.loveable_clone.entity.User;
import com.learning.project.loveable_clone.enums.ProjectRole;
import com.learning.project.loveable_clone.error.ResourceNotFoundException;
import com.learning.project.loveable_clone.mapper.ProjectMapper;
import com.learning.project.loveable_clone.repository.ProjectMemberRepository;
import com.learning.project.loveable_clone.repository.ProjectRepository;
import com.learning.project.loveable_clone.repository.UserRepository;
import com.learning.project.loveable_clone.security.AuthUtil;
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
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();

        //This makes DB call
        /*User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId.toString()));*/

        //This does not make DB call but only keep hold of userId
        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();

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
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        project.setName(request.name());
        return projectMapper.toProjectResponse(projectRepository.save(project));
    }

    @Override
    public void softDelete(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // INTERNAL functions
    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString()));
    }
}
