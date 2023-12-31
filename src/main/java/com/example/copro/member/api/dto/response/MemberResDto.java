package com.example.copro.member.api.dto.response;

import com.example.copro.member.domain.Member;
import java.util.List;
import lombok.Builder;

@Builder
public record MemberResDto(
        String name,
        String email,
        String picture,
        String occupation,
        String language,
        String career,
        String gitHubUrl,
        String nickName,
        int likeMembersCount,
        List<Long> likeMembersId

) {
    public static MemberResDto from(Member member) {
        return MemberResDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .picture(member.getPicture())
                .occupation(member.getOccupation())
                .language(member.getLanguage())
                .career(member.getCareer())
                .gitHubUrl(member.getGitHubUrl())
                .nickName(member.getNickName())
                .build();
    }

    public static MemberResDto of(Member member, int likeMembersCount, List<Long> likeMembersId) {
        return MemberResDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .picture(member.getPicture())
                .occupation(member.getOccupation())
                .language(member.getLanguage())
                .career(member.getCareer())
                .gitHubUrl(member.getGitHubUrl())
                .nickName(member.getNickName())
                .likeMembersCount(likeMembersCount)
                .likeMembersId(likeMembersId)
                .build();
    }
}
