package fr.lernejo.umlgrapher;

public class UmlGraph {
    private final Class<?> [] umlTest;
    public UmlGraph(Class<?>... classes) {
        umlTest = classes;
    }

    public String as(GraphType type) {
        return """
            classDiagram
            class Machin {
                <<interface>>
            }
            """;
    }
}
