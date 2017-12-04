public class OffByOne implements CharacterComparator {
    private int off;

    public OffByOne(int n) {
        this.off = n;
    }

    @Override
    public boolean equalChars(char a, char b) {
        if (Math.abs(a - b)== this.off) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        OffByOne obo = new OffByOne(5);

        System.out.println(obo.equalChars('a', 'b'));
        System.out.println(obo.equalChars('r', 'q'));
        System.out.println();

        System.out.println(obo.equalChars('a', 'e'));
        System.out.println(obo.equalChars('z', 'a'));
        System.out.println(obo.equalChars('a', 'a'));
        System.out.println(obo.equalChars('z', 'A'));
        System.out.println(obo.equalChars('Z', 'a'));
        System.out.println();

        System.out.println(obo.equalChars('a', 'f'));
        System.out.println(obo.equalChars('f', 'h'));
    }
}
