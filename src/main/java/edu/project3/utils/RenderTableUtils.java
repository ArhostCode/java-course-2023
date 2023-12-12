package edu.project3.utils;

import java.util.ArrayList;
import java.util.List;

public final class RenderTableUtils {

    private RenderTableUtils() {
    }

    public static List<Integer> getMaxRowSizes(List<String> headers, List<List<String>> rows) {
        List<Integer> maxRowsSizes = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            int maxRowSize = 0;
            for (List<String> row : rows) {
                maxRowSize = Math.max(maxRowSize, row.get(i).length());
            }
            maxRowsSizes.add(maxRowSize);
        }

        for (int i = 0; i < headers.size(); i++) {
            if (maxRowsSizes.get(i) < headers.get(i).length()) {
                maxRowsSizes.set(i, headers.get(i).length());
            }
        }
        return maxRowsSizes;
    }

    public static String buildLine(List<Integer> maxRowsSizes, List<String> line) {
        StringBuilder lineBuilder = new StringBuilder();
        for (int i = 0; i < line.size(); i++) {
            lineBuilder.append("| ");
            lineBuilder.append(" ".repeat(maxRowsSizes.get(i) - line.get(i).length()));
            lineBuilder.append(line.get(i));
            lineBuilder.append(" ");
        }
        return lineBuilder.toString();
    }
}
