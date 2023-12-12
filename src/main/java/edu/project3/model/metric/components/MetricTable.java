package edu.project3.model.metric.components;

import edu.project3.renderer.RenderedComponent;
import edu.project3.renderer.Renderer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record MetricTable(List<String> headers, List<List<String>> rows) implements MetricComponent {

    public MetricTable() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public MetricTable addHeaders(String... headers) {
        this.headers.addAll(List.of(headers));
        return this;
    }

    public MetricTable addRowElements(String... rowElements) {
        this.rows.add(List.of(rowElements));
        return this;
    }

    @Override
    public RenderedComponent render(Renderer renderer) {
        return renderer.renderTable(this);
    }

    public static class ExtendedTableBuilder<T> {

        private int rowsCount = 0;
        private String[] headers;
        private List<T> rows;
        private List<Function<T, String>> stringExtractors;

        public ExtendedTableBuilder<T> headers(String... headers) {
            this.headers = headers;
            return this;
        }

        public ExtendedTableBuilder<T> rows(List<T> rows) {
            this.rows = rows;
            return this;
        }

        public ExtendedTableBuilder<T> rowsCount(int rowsCount) {
            this.rowsCount = rowsCount;
            return this;
        }

        public ExtendedTableBuilder<T> stringExtractors(List<Function<T, String>> stringExtractors) {
            this.stringExtractors = stringExtractors;
            return this;
        }

        public MetricTable build() {
            rowsCount = Math.min(rowsCount, rows.size());
            List<List<String>> newRows = new ArrayList<>();
            for (int i = 0; i < rowsCount; i++) {
                List<String> currentRow = new ArrayList<>();
                for (Function<T, String> stringExtractor : stringExtractors) {
                    currentRow.add(stringExtractor.apply(rows.get(i)));
                }
                newRows.add(currentRow);
            }
            return new MetricTable(List.of(headers), newRows);
        }
    }
}
