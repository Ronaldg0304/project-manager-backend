package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.entities.audit.AiMessage;
import com.projects.projectmanager.project_manager.services.base.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/openai")
@RequiredArgsConstructor
public class AIController {

    private final AIService openAIService;

    @PostMapping("/chat")
    public String chat(@RequestBody List<AiMessage> messages) {
        System.out.println(messages.toString());
        return openAIService.getChatResponse(messages);
    }
}
