package isp;

import java.util.List;

public interface Element {
    String getName();
    List<Element> subElems();
}
