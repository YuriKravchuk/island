package org.javarush.organism.animals;

import java.util.concurrent.ConcurrentLinkedDeque;

public interface Reproducible {
    public void reproduce(ConcurrentLinkedDeque<Animals> animals);
}
