import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** Interactive portfolio extension; Part 3 teacher requirements are not yet available. */
public class Partie3 {
    private static final String[] MOVES={"U","D","F","B","R","L"};
    public static void main(String[] args) {
        Partie1 cube=new Partie1();
        Scanner input=new Scanner(System.in);
        Random random=new Random();
        List<String> history=new ArrayList<>();
        System.out.println("PART 3 - Interactive cube playground");
        System.out.println("Commands: U D F B R L, U', R2, scramble, undo, solve, show, reset, quit");
        while (true) {
            System.out.print("> ");
            if (!input.hasNextLine()) break;
            String command=input.nextLine().trim();
            if (command.equals("quit")) break;
            if (command.equals("show")) { cube.print(); continue; }
            if (command.equals("reset")) { cube.reset(); history.clear(); System.out.println("Reset."); continue; }
            if (command.equals("scramble")) {
                for (int i=0;i<4;i++) {
                    String m=MOVES[random.nextInt(MOVES.length)];
                    cube.move(m); history.add(m); System.out.print(m+" ");
                }
                System.out.println();
            } else if (command.equals("undo")) {
                if (history.isEmpty()) { System.out.println("Nothing to undo."); continue; }
                String last=history.remove(history.size()-1);
                cube.move(Partie1.inverse(last));
            } else if (command.equals("solve")) {
                // Reverses tracked moves; cannot solve an independently entered state.
                String sequence=String.join(" ",history);
                String solution=Partie1.inverse(sequence);
                cube.moves(solution); history.clear();
                System.out.println("Reverse-history solution: " + solution);
            } else {
                try { cube.move(command); history.add(command); }
                catch (IllegalArgumentException e) { System.out.println("Unknown command."); continue; }
            }
            System.out.println(cube.isSolved() ? "Cube solved!" : "Cube not solved yet.");
        }
    }
}
