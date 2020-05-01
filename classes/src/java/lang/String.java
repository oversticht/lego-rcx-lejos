package java.lang;

/**
 * An immutable string of characters.
 */
public final class String {
    // NOTE: The state of this class is mapped to
    // native code (see vmsrc/classes.h).

    char[] characters; // package protected, so StringUtils can access it

    /**
     * Create a String from a character array.
     *
     * @param c   the character array
     * @param off the offset - usually 0
     * @param len the length of the String - must not be greater than c.length
     **/
    public String(char[] c, int off, int len) {
        characters = new char[len];
        System.arraycopy(c, off, characters, 0, len);
    }

    /**
     * Converts an Object to a String
     *
     * @return the String that represents the object
     **/
    public static String valueOf(Object aObj) {
        return aObj.toString();
    }

    /**
     * Return the length of the String in characters
     *
     * @return the length of the String
     **/
    public int length() {
        return characters.length;
    }

    /**
     * Return the character at the given index
     *
     * @return the characters at the given index
     **/
    public char charAt(int index) {
        return characters[index];
    }

    /**
     * Converts the String into an array of characters
     *
     * @return the character Array
     **/
    public char[] toCharArray() {
        int len = characters.length;
        char[] ca = new char[len];
        System.arraycopy(characters, 0, ca, 0, len);
        return ca;
    }

    /**
     * Returns itself.
     *
     * @return the String itself
     */
    public String toString() {
        return this;
    }

    /**
     * Returns a hash for this String.
     */
    public int hashCode() {
        int hash = 7;
        int charactersHash = characters == null ? 0 : 1;
        if (characters != null) {
            for (int i = 0; i < characters.length; ++i) {
                charactersHash = 31 * charactersHash + characters[i];
            }
        }
        hash = 31 * hash + charactersHash;
        return hash;
    }

    /**
     * Compares the String with an Object
     *
     * @return true if the String is equal to the object, false otherwise
     **/
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || hashCode() != other.hashCode())
            return false;

        try {
            String os = (String) other;
            if (os.characters.length != characters.length)
                return false;

            for (int i = 0; i < characters.length; i++) {
                if (characters[i] != os.characters[i])
                    return false;
            }

            return true;
        } catch (ClassCastException e) {
        }
        return false;
    }
}

