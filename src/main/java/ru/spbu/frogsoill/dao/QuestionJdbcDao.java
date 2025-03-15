package ru.spbu.frogsoill.dao;

import org.springframework.stereotype.Repository;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionJdbcDao implements QuestionRepository {
    private static final String DDL_QUERY = """
            CREATE TABLE question_cards (ID BIGINT AUTO_INCREMENT PRIMARY KEY, question VARCHAR(100), expected_answer VARCHAR(50))
            """;
    private static final String FIND_ALL_QUERY = """
            SELECT ID, question, expected_answer FROM question_cards
            """;
    private static final String FIND_BY_ID_QUERY = """
            SELECT ID, question, expected_answer FROM question_cards WHERE ID=?
            """;
    private static final String INSERT_CARD_QUERY = """
            INSERT INTO question_cards(ID, question, expected_answer) VALUES (?, ?, ?)
            """;
    private static final String UPDATE_CARD_QUERY = """
            UPDATE question_cards SET question=?, expected_answer=? WHERE ID=?
            """;
    private static final String DELETE_CARD_QUERY = """
            DELETE FROM question_cards WHERE ID=?
            """;
    private final DataSource dataSource;

    public QuestionJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
        initDataBase();
    }

    public void initDataBase() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DDL_QUERY)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCard> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                OpenQuestionCard card = new OpenQuestionCard(
                        resultSet.getLong("ID"),
                        resultSet.getString("question"),
                        resultSet.getString("expected_answer"));
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCard> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OpenQuestionCard card = new OpenQuestionCard(
                        resultSet.getLong("ID"),
                        resultSet.getString("question"),
                        resultSet.getString("expected_answer"));
                cards.add(card);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards.isEmpty() ? Optional.empty() : Optional.of(cards.get(0));
    }

    @Override
    public void add(OpenQuestionCard task) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CARD_QUERY);) {
            statement.setLong(1, task.getId());
            statement.setString(2, task.getQuestion());
            statement.setString(3, task.getExpectedAnswer());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(OpenQuestionCard task) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CARD_QUERY);) {
            statement.setString(1, task.getQuestion());
            statement.setString(2, task.getExpectedAnswer());
            statement.setLong(3, task.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CARD_QUERY);) {
            statement.setLong(1, id);
            statement.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }
}
