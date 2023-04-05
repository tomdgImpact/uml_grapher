package fr.lernejo;

import fr.lernejo.umlgrapher.GraphType;
import fr.lernejo.umlgrapher.UmlGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UmlGraphTests {
    @Test
    void empty_interface_with_no_relation() {
        Class<?>[] classesuse = {fr.lernejo.Living.Animal.Ant.class,fr.lernejo.Living.Animal.Cat.class,Living.Plant.Tree.Alder.class};
        UmlGraph graph = new UmlGraph(classesuse);
        String output = graph.as(GraphType.Mermaid);
        Assertions.assertThat(output).isEqualTo
            ("""
            classDiagram
            class Alder
            class Animal {
                <<interface>>
            }
            class Ant
            class Cat
            class Living {
                <<interface>>
            }
            class Plant {
                <<interface>>
            }
            class Tree {
                <<interface>>
            }
            Tree <|.. Alder : implements
            Living <|-- Animal : extends
            Animal <|.. Ant : implements
            Animal <|.. Cat : implements
            Living <|-- Plant : extends
            Plant <|-- Tree : extends
            """);
    }

}
