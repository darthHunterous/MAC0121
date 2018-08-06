package pieces;

public class Nut {
    int d;

    public Nut(int d) { this.d = d; }

    int dimen() { return d; }

    public int compareTo(Bolt b) {
	if (this.d - b.dimen() == 0) return 0;
	else if (this.d - b.dimen() < 0) return -1;
	else return 1;
    }
} 
