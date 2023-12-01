package edu.project4.action.saver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageFormat {

    PNG("png"),
    BMP("bmp"),
    JPG("jpg");

    private final String formatName;

}
