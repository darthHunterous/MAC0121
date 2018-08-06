package pieces;

public class Bolt {
    int d;

    public Bolt(int d) { this.d = d; }

    int dimen() { return d; }

    public int compareTo(Nut n) {
	if (this.d - n.dimen() == 0) return 0;
	else if (this.d - n.dimen() < 0) return -1;
	return 1;
    }
} 
