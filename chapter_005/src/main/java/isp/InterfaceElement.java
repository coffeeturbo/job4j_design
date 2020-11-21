package isp;

import java.util.ArrayList;
import java.util.List;

public class InterfaceElement implements Element {

    private String name;
    private List<Element> subElements;

    public InterfaceElement(String name) {
        this.name = name;
        this.subElements = new ArrayList<>();
    }

    public InterfaceElement(String name, List<Element> elems) {
        this.name = name;
        this.subElements = elems;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Element> subElems() {
        return subElements;
    }

    @Override
    public String toString() {

        StringBuilder subElementString = new StringBuilder();
        subElements.forEach(subElementString::append);

        return name + "\n\t" + subElementString;
    }
}
