package com.learning.project.loveable_clone.service;

import com.learning.project.loveable_clone.dto.member.InviteMemberRequest;
import com.learning.project.loveable_clone.dto.member.MemberResponse;
import com.learning.project.loveable_clone.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
