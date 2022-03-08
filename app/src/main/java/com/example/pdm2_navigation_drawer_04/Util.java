package com.example.pdm2_navigation_drawer_04;

import java.util.Random;

public class Util {

    public static int random(int max) {

        Random gerador = new Random(19700621);

        return gerador.nextInt(max);
    }
}
