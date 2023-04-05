package fr.lernejo;

import fr.lernejo.umlgrapher.UmlGraph;
import fr.lernejo.umlgrapher.GraphType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UmlGraphTest {
    @Test
    void empty_interface_with_no_relation() {
        UmlGraph graph = new UmlGraph(new Class[]{Machin.class});

        String output = graph.as(GraphType.Mermaid);

        Assertions.assertThat(output).isEqualTo("""
            classDiagram
            class Machin {
                <<interface>>
            }
            """);
    }

    interface Machin {
    }
}
