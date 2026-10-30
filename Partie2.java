/** Demonstrates solving a cube by reversing a KNOWN scramble history.
 * This is not the teacher's general layer-by-layer solver for arbitrary cube states.
 */
public class Partie2 {
    public static void main(String[] args) {
        Partie1 cube = new Partie1();
        String scramble = "R U F L' D B2";
        System.out.println("PART 2 - Solving a known scramble");
        System.out.println("Scramble: " + scramble);
        cube.moves(scramble);
        System.out.println("Scrambled cube:");
        cube.print();
        String solution = Partie1.inverse(scramble);
        System.out.println("Solution: " + solution);
        cube.moves(solution);
        System.out.println("Solved: " + cube.isSolved());
    }
}
