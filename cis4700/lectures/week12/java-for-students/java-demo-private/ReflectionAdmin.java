// ===========================================================================
//  ReflectionAdmin.java   --   "private" is not a security boundary.
//
//  An Account has a private isAdmin field and no setter anywhere in the class.
//  Ordinary code cannot change it. Then a few lines of reflection reach in from
//  outside and flip it to true. The lesson: access modifiers like 'private' are
//  a software-design tool for the compiler, not a runtime security control.
//
//  Run it, watch a non-admin become admin without the class offering any way to
//  do so, then read how.
//
//  Compile and run:
//      javac ReflectionAdmin.java
//      java ReflectionAdmin
// ===========================================================================

import java.lang.reflect.Field;

public class ReflectionAdmin {

    // A well-encapsulated class. isAdmin is private and there is no method that
    // sets it to true. By normal rules, an Account can never become admin.
    static class Account {
        private final String name;
        private boolean isAdmin = false;

        Account(String name) { this.name = name; }
        boolean isAdmin()     { return isAdmin; }
        public String toString() { return name + " (isAdmin=" + isAdmin + ")"; }
    }

    public static void main(String[] args) throws Exception {
        Account a = new Account("alice");
        System.out.println("Before: " + a + "   -> isAdmin() returns " + a.isAdmin());

        // Normal code cannot write a.isAdmin. Reflection ignores 'private':
        Field f = Account.class.getDeclaredField("isAdmin");
        f.setAccessible(true);     // turn off the access check
        f.setBoolean(a, true);     // write the private field from outside

        System.out.println("After : " + a + "   -> isAdmin() returns " + a.isAdmin());
        if (a.isAdmin())
            System.out.println(">>> SERVER ROOM DOOR UNLOCKED (via reflection) <<<");
    }
}
