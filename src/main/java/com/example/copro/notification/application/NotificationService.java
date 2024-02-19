package com.example.copro.notification.application;

import com.example.copro.member.domain.Member;
import com.example.copro.notification.domain.repository.EmitterRepository;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Transactional(readOnly = true)
public class NotificationService {
    private final EmitterRepository emitterRepository;

    public NotificationService(EmitterRepository emitterRepository) {
        this.emitterRepository = emitterRepository;
    }

    public SseEmitter subscribe(Member member) {
        SseEmitter emitter = createEmitter(member);

        sendToClient(member.getEmail(), "EventStream Created. [email=" + member.getEmail() + "]", "sse 접속 성공", "sse");
        return emitter;
    }

    public <T> void customNotify(String email, T data, String comment, String type) {
        sendToClient(email, data, comment, type);
    }

    private <T> void sendToClient(String email, Object data, String comment, String type) {
        SseEmitter emitter = emitterRepository.get(email);

        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(email)
                        .name(type)
                        .data(data)
                        .comment(comment));
            } catch (IOException e) {
                emitterRepository.deleteById(email);
                emitter.completeWithError(e);
            }
        }
    }

    private SseEmitter createEmitter(Member member) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitterRepository.save(member.getEmail(), emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(member.getEmail()));
        emitter.onTimeout(() -> emitterRepository.deleteById(member.getEmail()));

        return emitter;
    }

}
