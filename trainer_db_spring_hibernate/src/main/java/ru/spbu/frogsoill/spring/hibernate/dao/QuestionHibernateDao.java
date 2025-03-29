package ru.spbu.frogsoill.spring.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spbu.frogsoill.domain.model.OpenQuestionCard;
import ru.spbu.frogsoill.domain.repo.QuestionRepository;
import ru.spbu.frogsoill.spring.hibernate.entity.OpenQuestionCardEntity;
import ru.spbu.frogsoill.spring.hibernate.mapper.QuestionMapper;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class QuestionHibernateDao implements QuestionRepository {

    private final QuestionMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionHibernateDao(QuestionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCardEntity> entities = entityManager.createQuery("SELECT question_card FROM OpenQuestionCardEntity question_card", OpenQuestionCardEntity.class).getResultList();
        return mapper.mapToModel(entities);
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCardEntity> entity = entityManager.createQuery("SELECT question_card FROM OpenQuestionCardEntity question_card WHERE question_card.id = ?1", OpenQuestionCardEntity.class).setParameter(1, id).getResultList();
        if (!entity.isEmpty()) {
            OpenQuestionCard model = mapper.mapToModel(entity.getFirst());
            return Optional.of(model);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void add(OpenQuestionCard task) {
        OpenQuestionCardEntity entity = mapper.mapToEntity(task);
        entityManager.persist(entity);
    }

    @Override
    public void update(OpenQuestionCard task) {
        OpenQuestionCardEntity entity = mapper.mapToEntity(task);
        entityManager.merge(entity);

    }

    @Override
    public void remove(Long id) {
        OpenQuestionCardEntity entity = entityManager.createQuery("SELECT question_card FROM OpenQuestionCardEntity question_card WHERE question_card.id = ?1", OpenQuestionCardEntity.class).setParameter(1, id).getSingleResult();
        entityManager.remove(entity);

    }
}
