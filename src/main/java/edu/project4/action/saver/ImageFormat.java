package edu.project4.action.saver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImageFormat {
    PNG("png"),
    JPEG("jpg"),
    BMP("bmp");

    private final String formatName;
}
