package edu.project3.model.adoc;

import edu.project3.utils.RenderTableUtils;
import java.util.List;

public record TableAdoc(List<String> headers, List<List<String>> rows) implements AdocComponent {

    @Override
    public String convertToString() {
        StringBuilder markdownBuilder = new StringBuilder();
        List<Integer> sizes = RenderTableUtils.getMaxRowSizes(headers, rows);
        markdownBuilder.append(buildEqualsLine(sizes));
        markdownBuilder.append("\n");
        markdownBuilder.append(RenderTableUtils.buildLine(sizes, headers));
        markdownBuilder.append("\n");
        for (List<String> row : rows) {
            markdownBuilder.append("\n");
            markdownBuilder.append(RenderTableUtils.buildLine(sizes, row));
        }
        markdownBuilder.append("\n");
        markdownBuilder.append(buildEqualsLine(sizes));
        return markdownBuilder.toString();
    }

    private String buildEqualsLine(List<Integer> maxRowsSizes) {
        final int additionalSymbols = 1 + (maxRowsSizes.size() - 1) * 3;
        return "|" + "=".repeat(maxRowsSizes.stream().mapToInt(Integer::intValue).sum() + additionalSymbols);
    }
}
