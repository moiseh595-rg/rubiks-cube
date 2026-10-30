import java.util.Arrays;

/** Simple cube model shared by the three portfolio demonstrations. */
public class Partie1 {
    // Face order: U, F, R, B, L, D.
    private final char[][] faces = new char[6][9];
    private static final char[] COLORS = {'W','G','R','B','O','Y'};
    private static final String[] NAMES = {"U", "F", "R", "B", "L", "D"};

    public Partie1() { reset(); }

    public void reset() {
        for (int f = 0; f < 6; f++) Arrays.fill(faces[f], COLORS[f]);
    }

    public boolean isSolved() {
        for (int f = 0; f < 6; f++)
            for (char sticker : faces[f]) if (sticker != faces[f][4]) return false;
        return true;
    }

    public void move(String move) {
        if (!move.matches("[UFRBLD](')?|[UFRBLD]2"))
            throw new IllegalArgumentException("Unknown move: " + move);
        char face = move.charAt(0);
        int times = move.endsWith("2") ? 2 : move.endsWith("'") ? 3 : 1;
        for (int n = 0; n < times; n++) turnClockwise(face);
    }

    public void moves(String sequence) {
        if (sequence.isBlank()) return;
        for (String m : sequence.trim().split("\\s+")) move(m);
    }

    public void print() {
        for (int f = 0; f < 6; f++) {
            System.out.println(NAMES[f] + " face:");
            for (int r = 0; r < 3; r++)
                System.out.println("  " + faces[f][r*3] + " " + faces[f][r*3+1] + " " + faces[f][r*3+2]);
        }
    }

    // Each sticker has a position (x,y,z) and a direction (nx,ny,nz).
    // This avoids writing six separate sets of tricky row/column swaps.
    private int[] geometry(int face, int row, int col) {
        int x=0,y=0,z=0,nx=0,ny=0,nz=0;
        switch (face) {
            case 0: x=col-1; y=1; z=row-1; ny=1; break; // U
            case 1: x=col-1; y=1-row; z=1; nz=1; break; // F
            case 2: x=1; y=1-row; z=1-col; nx=1; break; // R
            case 3: x=1-col; y=1-row; z=-1; nz=-1; break; // B
            case 4: x=-1; y=1-row; z=col-1; nx=-1; break; // L
            default: x=col-1; y=-1; z=1-row; ny=-1; break; // D
        }
        return new int[]{x,y,z,nx,ny,nz};
    }

    private int[] location(int[] g) {
        int x=g[0],y=g[1],z=g[2],nx=g[3],ny=g[4],nz=g[5];
        if (ny==1) return new int[]{0,z+1,x+1};
        if (nz==1) return new int[]{1,1-y,x+1};
        if (nx==1) return new int[]{2,1-y,1-z};
        if (nz==-1) return new int[]{3,1-y,1-x};
        if (nx==-1) return new int[]{4,1-y,z+1};
        return new int[]{5,1-z,x+1};
    }

    private void turnClockwise(char face) {
        int axis = "UD".indexOf(face)>=0 ? 1 : "RL".indexOf(face)>=0 ? 0 : 2;
        int layer = "URF".indexOf(face)>=0 ? 1 : -1;
        // Viewed from outside, clockwise is a negative rotation about the outward axis.
        int sign = -layer;
        char[][] next = new char[6][9];
        for (int f=0; f<6; f++) for (int row=0; row<3; row++) for (int col=0; col<3; col++) {
            int[] g=geometry(f,row,col);
            if (g[axis]==layer) {
                for (int start=0; start<=3; start+=3) {
                    int a=g[start], b=g[start+1], c=g[start+2];
                    if (axis==0) { g[start+1]=-sign*c; g[start+2]=sign*b; }
                    if (axis==1) { g[start]=sign*c; g[start+2]=-sign*a; }
                    if (axis==2) { g[start]=-sign*b; g[start+1]=sign*a; }
                }
            }
            int[] target=location(g);
            next[target[0]][target[1]*3+target[2]]=faces[f][row*3+col];
        }
        for (int f=0; f<6; f++) faces[f]=next[f];
    }

    public static String inverse(String sequence) {
        if (sequence.isBlank()) return "";
        String[] moves=sequence.trim().split("\\s+");
        StringBuilder answer=new StringBuilder();
        for (int i=moves.length-1;i>=0;i--) {
            String m=moves[i];
            if (answer.length()>0) answer.append(' ');
            answer.append(m.endsWith("2") ? m : m.endsWith("'") ? m.substring(0,1) : m+"'");
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Partie1 cube=new Partie1();
        System.out.println("PART 1 - Cube model and movements");
        cube.moves("R U F"); cube.print();
        cube.moves("F' U' R'");
        System.out.println("Solved after inverse moves: " + cube.isSolved());
    }
}
