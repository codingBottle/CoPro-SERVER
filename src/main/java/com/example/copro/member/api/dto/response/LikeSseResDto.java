package com.example.copro.member.api.dto.response;

import lombok.Builder;

@Builder
public record LikeSseResDto (
        String myNickname,
        String likeMemberNickname
){
}
