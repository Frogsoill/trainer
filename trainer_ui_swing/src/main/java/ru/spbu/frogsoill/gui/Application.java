package ru.spbu.frogsoill.gui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.spbu.frogsoill.domain.service.QuestionService;
import ru.spbu.frogsoill.gui.config.SpringConfig;
import ru.spbu.frogsoill.gui.controller.MainController;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        SwingUtilities.invokeLater(new MainController(questionService));
    }
}
