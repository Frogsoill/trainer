package ru.spbu.frogsoill.console.controller;

import org.springframework.stereotype.Component;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.service.QuestionService;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleController {
    private final QuestionService service;
    private final Scanner scanner;

    private static final String MENU = """
            Меню:
            Введите [1], чтобы показать все карточки с вопросами
            Введите [2], чтобы добавить карточку
            Введите [3], чтобы удалить карточку
            Введите [4], чтобы найти карточку
            Введите [5], чтобы выйти""";

    public ConsoleController(QuestionService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void interactWithUser() {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            String opCode = scanner.nextLine();
            switch (opCode) {
                case "1" -> printAll();
                case "2" -> addNewCard();
                case "3" -> removeCard();
                case "4" -> findCard();
                case "5" -> isRunning = false;
            }
        }
    }

    private void printMenu() {
        System.out.println(MENU);
    }

    private void printAll() {
        System.out.println(service.getAll());
    }

    private void addNewCard() {
        System.out.println("Введите номер карточки");
        Long id = scanLong();
        System.out.println("Введите вопрос");
        String question = scanner.nextLine();
        System.out.println("Введите ожидаемый ответ");
        String expectedAnswer = scanner.nextLine();
        OpenQuestionCard card = null;
        try {
            card = new OpenQuestionCard(id, question, expectedAnswer);
        } catch (Exception e) {
            System.out.println("Не удалось добавить карточку");
        }
        if (Objects.nonNull(card)) {
            service.save(card);
            System.out.println("Карточка добавлена");
        }
    }

    private void removeCard() {
        System.out.println("Введите номер карточки, которую хотите удалить");
        Long id = scanLong();
        Optional<OpenQuestionCard> card = service.getById(id);
        if (card.isPresent()) {
            System.out.println("Введите [Y], если хотите удалить карточку: " + card.get());
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                service.delete(card.get());
                System.out.println("Карточка была удалена");
            } else {
                System.out.println("Карточка не удалена");
            }
        } else {
            System.out.println("Такой карточки найти не удалось");
        }
    }

    private void findCard() {
        System.out.println("Введите номер карточки, которую хотите найти");
        Long id = scanLong();
        Optional<OpenQuestionCard> card = service.getById(id);
        if (card.isPresent()) {
            System.out.println(card.get());
        } else {
            System.out.println("Такой карточки найти не удалось");
        }
    }

    private Long scanLong() {
        Long res = null;
        while (Objects.isNull(res)) {
            if (scanner.hasNextLong()) {
                res = scanner.nextLong();
            } else {
                System.out.println("Ошибка! Введите целое число");
            }
            scanner.nextLine();
        }
        return res;
    }

}
