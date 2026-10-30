# Rubik’s Cube — Java portfolio showcase

Three beginner-readable Java files inspired by the university Rubik’s Cube project.

- `Partie1.java`: cube model, 18 standard moves (six faces × clockwise, inverse, double), console display.
- `Partie2.java`: **reverses a known scramble**, not a general solver for arbitrary cube configurations.
- `Partie3.java`: **independent portfolio extension** with an interactive terminal, easy scramble, undo and history-based solution. **Not the teacher’s unreleased Part 3 specification.**

These are newly written showcase examples, **not** the team's submitted assignment or completed teacher skeleton.

## Run

Requires JDK 17 or newer.

```sh
javac Partie1.java Partie2.java Partie3.java
java Partie1
java Partie2
java Partie3
```

## Future work

The teacher's Part 2 expects a general beginner layer-by-layer solver for arbitrary valid scrambled cubes (flower, cross, first layer, second layer, last layer). This showcase does **not** implement that algorithm yet. Part 3's exact instructions have not been released in the provided materials. Update this README when implementing them.
