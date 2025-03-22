package ru.spbu.frogsoill.spring.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionJdbcTemplateDao implements QuestionRepository {
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
    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        initSchema();
    }


    @Override
    public List<OpenQuestionCard> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, (ResultSet rs, int _) -> new OpenQuestionCard(rs.getLong("ID"), rs.getString("question"), rs.getString("expected_answer")));
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCard> cards = jdbcTemplate.query(FIND_BY_ID_QUERY, (ResultSet rs, int _) -> new OpenQuestionCard(rs.getLong("ID"), rs.getString("question"), rs.getString("expected_answer")), id);
        return cards.isEmpty() ? Optional.empty() : Optional.of(cards.getFirst());
    }

    @Override
    public void add(OpenQuestionCard task) {
        jdbcTemplate.update(INSERT_CARD_QUERY, task.getId(), task.getQuestion(), task.getExpectedAnswer());
    }

    @Override
    public void update(OpenQuestionCard task) {
        jdbcTemplate.update(UPDATE_CARD_QUERY, task.getQuestion(), task.getExpectedAnswer(), task.getId());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE_CARD_QUERY, id);
    }

    private void initSchema() {
        jdbcTemplate.update(DDL_QUERY);
    }
}
