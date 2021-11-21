package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class SimpleController implements Controller {

    private final List<String> history = new LinkedList<>();
    private String nextString;

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("There is no string set");
        }
        this.history.add(this.nextString);
        System.out.println(this.nextString);
    }

    @Override
    public List<String> getHistoryPrintedString() {
        return this.history;
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public void setNextStringToPrint(final String next) {
        this.nextString = Objects.requireNonNull(next, "Not allowed null values");
    }

}
