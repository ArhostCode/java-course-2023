package edu.project4.model.image;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public void increaseHitCount() {
        hitCount++;
    }

    public void setRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
