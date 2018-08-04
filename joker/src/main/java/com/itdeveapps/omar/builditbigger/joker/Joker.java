package com.itdeveapps.omar.builditbigger.joker;

import java.util.Random;

public class Joker {
    private final String[] JOKES = new String[]{
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all",
            "This is totally a funny joke",
            "Two drums and a cymbal fall off a cliff. Ba-dum Tish!",
            "This is a wibbly wobbly timey wimey funny joke",
            "Where does a sheep go for a haircut? To the baaaaa baaaaa shop!"
    };

    public String tellTheJoke() {
        return JOKES[new Random().nextInt(JOKES.length)];
    }
}
