package ru.spbu.frogsoill.gui.model;

import ru.spbu.frogsoill.domain.model.OpenQuestionCard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class QuestionTableModel extends AbstractTableModel {

    private final String[] columns = new String[]{"ID", "Вопрос", "Ожидаемый ответ"};
    private final List<OpenQuestionCard> cards;

    public QuestionTableModel(List<OpenQuestionCard> cards) {
        this.cards = cards;
    }

    @Override
    public int getRowCount() {
        return cards.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        return switch (colIndex) {
            case 0 -> cards.get(rowIndex).getId();
            case 1 -> cards.get(rowIndex).getQuestion();
            case 2 -> cards.get(rowIndex).getExpectedAnswer();
            default -> throw new IllegalArgumentException("Некорректный номер колонки");
        };
    }
}
