package isp;

import java.util.List;

public class MenuUI implements Menu {


    private List<Element> elems;

    public MenuUI(List<Element> elems) {
        this.elems = elems;
    }

    public static void main(String[] args) {

        List<Element> subElems = List.of(
            new InterfaceElement("Задача 1.1"),
            new InterfaceElement("Задача 1.2")
        );
        List<Element> elems = List.of(new InterfaceElement("Задача 1", subElems));

        Menu menu = new MenuUI(elems);

        elems.forEach(System.out::println);



    }
}
