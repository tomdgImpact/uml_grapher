package fr.lernejo;

public sealed interface Living {
    sealed interface Animal extends Living {
        final class Ant implements Animal {
        }

        final class Cat implements Animal {
        }
    }

    sealed interface Plant extends Living {
        sealed interface Tree extends Plant {
            final class Alder implements Tree {
            }
        }
    }
}
