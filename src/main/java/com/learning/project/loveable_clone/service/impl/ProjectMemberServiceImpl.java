package com.learning.project.loveable_clone.service.impl;

import com.learning.project.loveable_clone.dto.member.InviteMemberRequest;
import com.learning.project.loveable_clone.dto.member.MemberResponse;
import com.learning.project.loveable_clone.dto.member.UpdateMemberRoleRequest;
import com.learning.project.loveable_clone.entity.Project;
import com.learning.project.loveable_clone.entity.ProjectMember;
import com.learning.project.loveable_clone.entity.ProjectMemberId;
import com.learning.project.loveable_clone.entity.User;
import com.learning.project.loveable_clone.mapper.ProjectMemberMapper;
import com.learning.project.loveable_clone.repository.ProjectMemberRepository;
import com.learning.project.loveable_clone.repository.ProjectRepository;
import com.learning.project.loveable_clone.repository.UserRepository;
import com.learning.project.loveable_clone.security.AuthUtil;
import com.learning.project.loveable_clone.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;
    AuthUtil authUtil;

    @Override
    @PreAuthorize("@security.canViewMembers(#projectId)")
    public List<MemberResponse> getProjectMembers(Long projectId) {

        return projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList();
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = authUtil.getCurrentUserId();

        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if (invitee.getId().equals(userId))
            throw new RuntimeException("Cannot invite yourself");

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if (projectMemberRepository.existsById(projectMemberId))
            throw new RuntimeException("Already Invited!");

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMemberRepository.save(projectMember));
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMemberRepository.save(projectMember));
    }

    @Override
    @PreAuthorize("@security.canManageMembers(#projectId)")
    public void removeProjectMember(Long projectId, Long memberId) {
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if (!projectMemberRepository.existsById(projectMemberId))
            throw new RuntimeException("Member does not exist!");
        projectMemberRepository.deleteById(projectMemberId);
    }

    // INTERNAL functions
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }
}
