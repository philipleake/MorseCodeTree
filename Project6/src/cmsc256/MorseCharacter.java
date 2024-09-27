/*
 * Philip Leake
 * cmsc-256-001
 * Spring Semester
 * MorseCharacter.java
 * Implements own binary search tree using morse code
 */
package cmsc256;
import java.util.*;
import bridges.base.*;
public class MorseCharacter<E> implements Comparable<MorseCharacter>{
    private char character;
    private String code;
    public MorseCharacter(char character, String code) {
        this.character = character;
        this.code = code;
    }
    public MorseCharacter() {
        character = '\0';
        code = "";
    }

    public char getLetter() {
        return character;
    }
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "" + character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MorseCharacter<?> that = (MorseCharacter<?>) o;
        return character == that.character && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, code);
    }

    @Override
    public int compareTo(MorseCharacter other) {
        if(other == null) {
            throw new IllegalArgumentException();
        }
        int res = 0;
        int i=0;
        String a = code;
        String b = other.getCode();
        while (res == 0 && i < a.length() && i < b.length()) {
            if(a.charAt(i) == '.' && b.charAt(i) == '-') {
                res = -1;
            }
            else if(a.charAt(i) == '-' && b.charAt(i) == '.'){
                res = 1;
            }
            i++;
        }
        if(res != 0 || (res == 0 && a.length() == b.length())) {
            return res;
        }
        else {
            return a.length() - b.length();
        }

    }
}
