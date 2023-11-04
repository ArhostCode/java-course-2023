package edu.project3.model.markdown;

import edu.project3.utils.RenderTableUtils;
import java.util.List;

public record TableMarkdown(List<String> headers, List<List<String>> rows) implements MarkdownComponent {

    @Override
    public String convertToString() {
        StringBuilder markdownBuilder = new StringBuilder();
        List<Integer> sizes = RenderTableUtils.getMaxRowSizes(headers, rows);
        markdownBuilder.append(RenderTableUtils.buildLine(sizes, headers)).append("|");
        markdownBuilder.append("\n");
        markdownBuilder.append(buildSpacingLine(sizes));
        for (List<String> row : rows) {
            markdownBuilder.append("\n");
            markdownBuilder.append(RenderTableUtils.buildLine(sizes, row)).append("|");
        }
        return markdownBuilder.toString();
    }

    private String buildSpacingLine(List<Integer> maxRowsSizes) {
        StringBuilder headerBuilder = new StringBuilder();
        for (Integer maxRowsSize : maxRowsSizes) {
            headerBuilder.append("|:").append("-".repeat(maxRowsSize)).append(":");
        }
        headerBuilder.append("|");
        return headerBuilder.toString();
    }
}
