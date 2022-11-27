package assignment3;

import java.util.Arrays;

class findMostSenior1 implements Runnable{
    @Override
    public void run() {
        Cat A = new Cat("A", 20, 40, 5, 85.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(A);

        if (!(catCafe.root.findMostSenior().equals(A))) {
            throw new AssertionError("findMostSenior() did not return the correct value."
                    + "\n Expected A but got "
                    + catCafe.root.findMostSenior());
        }

        System.out.println("Test passed.");
    }
}

class hire_rotation1 implements Runnable{
    public void run(){
        // example in the pdf
        Cat B = new Cat("Buttercup", 45, 53, 5, 85.0);
        Cat JTO = new Cat("J. Thomas O'Malley", 21, 10, 9, 20.0);
        Cat C = new Cat("Chessur", 8, 23, 2, 250.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(B);
        catCafe.hire(JTO);
        catCafe.hire(C);

        if (!(catCafe.root.catEmployee.equals(B) && catCafe.root.senior.catEmployee.equals(C) && catCafe.root.senior.junior.catEmployee.equals(JTO))){
            throw new AssertionError("Left rotation did not work." +
                    " Chessur should be Buttercup's senior, and JTO should be Chessur's junior.");
        }
        System.out.println("Test passed.");
    }
}

class hire_rotation2 implements Runnable {
    public void run() {
        Cat A = new Cat("A", 20, 40, 5, 85.0);
        Cat B = new Cat("B", 10, 50, 2, 250.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(A);
        catCafe.hire(B);

        if (!(catCafe.root.catEmployee.equals(B) && catCafe.root.junior.catEmployee.equals(A))) {
            throw new AssertionError("Left rotation did not work." +
                    " Cat B should be root and Cat A should be  B's junior");
        }

        if (catCafe.root.junior.parent != catCafe.root || catCafe.root.parent != null) {
            throw new AssertionError("Parent pointers are not set correctly.");
        }

        System.out.println("Test passed.");
    }
}

class hire_rotation3 implements Runnable {
    public void run() {
        Cat A = new Cat("A", 20, 40, 5, 85.0);
        Cat B = new Cat("B", 30, 50, 2, 250.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(A);
        catCafe.hire(B);

        if (!(catCafe.root.catEmployee.equals(B) && catCafe.root.senior.catEmployee.equals(A))) {
            throw new AssertionError("Right rotation did not work." +
                    " Cat B should be root and Cat A should be B's senior");
        }

        if (catCafe.root.senior.parent != catCafe.root) {
            throw new AssertionError("Parent pointers are not set correctly");
        }

        System.out.println("Test passed.");
    }
}

class hire_rotation4 implements Runnable {
    public void run() {
        Cat A = new Cat("A", 20, 40, 5, 85.0);
        Cat B = new Cat("B", 10, 50, 2, 250.0);
        Cat C = new Cat("C", 30, 60, 2, 250.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(A);
        catCafe.hire(B);
        catCafe.hire(C);

        if (!(catCafe.root.catEmployee.equals(C) && catCafe.root.senior.catEmployee.equals(B) && catCafe.root.senior.junior.catEmployee.equals(A))) {
            throw new AssertionError("Left and right rotations did not work." +
                    " Cat C should be root, Cat B should be C's senior and Cat A should be B's junior");
        }

        if (catCafe.root.senior.parent != catCafe.root && catCafe.root.senior.junior.parent != catCafe.root.senior) {
            throw new AssertionError("Parent pointers are not set correctly.");
        }
        System.out.println("Test passed.");
    }
}

class hire_megaRotation implements Runnable{
    @Override
    public void run() {
        Cat A = new Cat("A", 20, 40, 5, 85.0);
        Cat B = new Cat("B", 15, 30, 2, 250.0);
        Cat C = new Cat("C", 10, 35, 2, 250.0);
        Cat D = new Cat("D", 8, 38, 5, 85.0);
        Cat E = new Cat("E", 22, 39, 9, 20.0);
        Cat F = new Cat("F", 25, 48, 9, 20.0);
        Cat G = new Cat("G", 28, 45, 9, 20.0);
        Cat H = new Cat("H", 30, 28, 9, 20.0);
        Cat I = new Cat("I", 29, 50, 9, 20.0);


        CatCafe catCafe = new CatCafe();
        catCafe.hire(A);
        catCafe.hire(B);
        catCafe.hire(C);
        catCafe.hire(D);
        catCafe.hire(E);
        catCafe.hire(F);
        catCafe.hire(G);
        catCafe.hire(H);
        catCafe.hire(I);

        // tree should end up looking like this (going from top to bottom):

        //                      (root)
        //          ---------  I (29, 50) --------
        //        /                                 \
        //      H (30, 28)               ----   F (25, 48) ----
        //                              /                       \
        //                         G (28, 45)            --- A (20, 40) ---
        //                                             /                     \
        //                                           E (22, 39)         -- D (8, 38)
        //                                                             /
        //                                                       -- C (10, 35)
        //                                                     /
        //                                                 B (15, 30)


        // tree should end up looking like this (going from ;eft to right):

        //                                      D (8, 38)
        //                                      /           \
        //                                     /             \
        //                            A (20, 40)               C (10, 35)
        //                           /          \                       \
        //                          /            \                       \
        //            F (25, 48)                  E (22, 39)             B (15, 30)
        //              /           \
        //             /             \
        //            /               G (28, 45)
        //  (root) I (29, 50)
        //            \
        //             \
        //              \
        //                H (30, 28)

        if (!(catCafe.root.catEmployee.equals(I))) {
            throw new AssertionError("Incorrect root." +
                    "Cat I should be root but got " + catCafe.root.catEmployee);
        }

        CatCafe.CatNode current = catCafe.root;

        while (current.junior != null) {
            current = current.junior;
        }
        if (!(current.catEmployee.equals(H))) {
            throw new AssertionError("Incorrect leftmost node." +
                    "Cat H should be leftmost node to the root but got " + current.catEmployee);
        }

        current = catCafe.root;
        while (current.senior != null) {
            current = current.senior;
        }
        if (!(current.catEmployee.equals(D))) {
            throw new AssertionError("Incorrect rightmost node." +
                    "Cat D should be rightmost node to the root but got " + current.catEmployee);
        }

        if (catCafe.root.parent != null || catCafe.root.junior.parent != catCafe.root || catCafe.root.senior.parent != catCafe.root) {
            throw new AssertionError("Parent pointers of the 1st level are not set correctly.");
        }

        if (!(catCafe.root.findMostSenior().equals(D) && catCafe.root.findMostJunior().equals(H))) {
            throw new AssertionError("findMostSenior() or findMostJunior() is not working correctly."
                    + "\n Expected D (most senior) and H (most junior) but got "
                    + catCafe.root.findMostSenior() + " and "
                    + catCafe.root.findMostJunior());
        }

        System.out.println("Test passed.");
    }
}

class retire_rotation1 implements Runnable{
    @Override
    public void run() {
        // example from the pdf
        Cat B = new Cat("Buttercup", 45, 53, 5, 85.0);
        Cat C = new Cat("Chessur", 8, 23, 2, 250.0);
        Cat JTO = new Cat("J. Thomas O’Malley", 21, 10, 9, 20.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(B);
        catCafe.hire(JTO);
        catCafe.hire(C);

        catCafe.retire(B);

        if (!(catCafe.root.catEmployee.equals(C) && catCafe.root.junior.catEmployee.equals(JTO) && catCafe.root.senior == null)) {
            throw new AssertionError("Retire did not work properly." +
                    "\n Cat C should be root and Cat JTO should be its junior but got " + catCafe.root
                    + " as root and " + catCafe.root.junior + " as its junior");
        }

        if (catCafe.root.parent != null || catCafe.root.junior.parent != catCafe.root) {
            throw new AssertionError("Parent pointers of the 1st level are not set correctly.");
        }

        System.out.println("Test passed.");
    }
}

class retire_rotation2 implements Runnable{
    @Override
    public void run() {
        // example from the pdf continued
        Cat C = new Cat("Chessur", 8, 23, 2, 250.0);
        Cat J = new Cat("Jonesy", 0, 21, 12, 30.0);
        Cat JJ = new Cat("JIJI", 156, 17, 1, 30.0);
        Cat JTO = new Cat("J. Thomas O’Malley", 21, 10, 9, 20.0);
        Cat MrsN = new Cat("Mrs. Norris", 100, 68, 15, 115.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(C);
        catCafe.hire(JTO);
        catCafe.hire(JJ);
        catCafe.hire(J);
        catCafe.hire(MrsN);

        catCafe.retire(MrsN);

        if (!(catCafe.root.catEmployee.equals(C))) {
            throw new AssertionError("Retire did not work properly." +
                    "\n Cat C should be the new root but got " + catCafe.root);
        }

        if (!(catCafe.root.junior.catEmployee.equals(JJ) && catCafe.root.senior.catEmployee.equals(J))) {
            throw new AssertionError("Retire did not work properly." +
                    "\n Cat Jiji should be the C's junior and Cat Jonesy should be the C's senior but got "
                    + catCafe.root.junior + " as the junior and " + catCafe.root.senior + " as the senior");
        }

        if (!(catCafe.root.junior.senior.catEmployee.equals(JTO) && catCafe.root.junior.junior == null)) {
            throw new AssertionError("Retire did not work properly." +
                    "\n Cat JTO should be the JJ's senior and JJ should not have a junior but got "
                    + catCafe.root.junior.senior + " as the senior and " + catCafe.root.junior.junior + " as the junior");
        }

        if (!(catCafe.root.senior.senior == null && catCafe.root.senior.junior == null)) {
            throw new AssertionError("Retire did not work properly." +
                    "\n Jonesy should not have any juniors or seniors but got "
                    + catCafe.root.senior.senior + " as the senior and " + catCafe.root.senior.junior + " as the junior");
        }

        if (catCafe.root.parent != null || catCafe.root.junior.parent != catCafe.root || catCafe.root.senior.parent != catCafe.root) {
            throw new AssertionError("Parent pointers of the 1st level are not set correctly.");
        }

        if (catCafe.root.junior.senior.parent != catCafe.root.junior) {
            throw new AssertionError("Parent pointers of 2nd level are not set correctly.");
        }

        System.out.println("Test passed.");
    }
}

class retire_megaRotation3 implements Runnable{
    @Override
    public void run() {
        Cat A = new Cat("A", 20, 40, 5, 85.0);
        Cat B = new Cat("B", 15, 30, 2, 250.0);
        Cat C = new Cat("C", 10, 35, 2, 250.0);
        Cat D = new Cat("D", 8, 38, 5, 85.0);
        Cat E = new Cat("E", 22, 39, 9, 20.0);
        Cat F = new Cat("F", 25, 48, 9, 20.0);
        Cat G = new Cat("G", 28, 45, 9, 20.0);
        Cat H = new Cat("H", 60, 28, 9, 20.0);
        Cat I = new Cat("I", 50, 50, 9, 20.0);
        Cat J = new Cat("J", 70, 18, 9, 20.0);
        Cat K = new Cat("K", 55, 20, 9, 20.0);
        Cat L = new Cat("L", 59, 10, 9, 20.0);
        Cat M = new Cat("M", 58, 25, 9, 20.0);

        CatCafe catCafe = new CatCafe();
        catCafe.hire(A);
        catCafe.hire(B);
        catCafe.hire(C);
        catCafe.hire(D);
        catCafe.hire(E);
        catCafe.hire(F);
        catCafe.hire(G);
        catCafe.hire(H);
        catCafe.hire(I);
        catCafe.hire(J);
        catCafe.hire(K);
        catCafe.hire(L);
        catCafe.hire(M);

        catCafe.retire(I);

        if (!(catCafe.root.catEmployee.equals(F))) {
            throw new AssertionError("Retire did not work properly." +
                    "\n Cat F should be the new root but got " + catCafe.root);
        }

        if (!(catCafe.root.junior.catEmployee.equals(G) && catCafe.root.senior.catEmployee.equals(A))) {
            throw new AssertionError("Retire rotations did not work properly for the 2nd level.");
        }

        if (!(catCafe.root.junior.junior.catEmployee.equals(H) && catCafe.root.senior.senior.catEmployee.equals(D)) && catCafe.root.senior.junior.catEmployee.equals(E)) {
            throw new AssertionError("Retire rotations did not work properly for the 3rd level.");
        }

        if (!(catCafe.root.junior.junior.junior.catEmployee.equals(J) && catCafe.root.junior.junior.senior.catEmployee.equals(M))
                && catCafe.root.senior.senior.junior.catEmployee.equals(C) && catCafe.root.senior.senior.senior.catEmployee == null
        && catCafe.root.senior.junior.junior.catEmployee == null && catCafe.root.senior.junior.senior.catEmployee == null) {
            throw new AssertionError("Retire rotations did not work properly for the 4th level.");
        }

        if (!(catCafe.root.junior.junior.junior.junior == null && catCafe.root.junior.junior.junior.senior == null
        && catCafe.root.junior.junior.senior.junior.catEmployee.equals(L) && catCafe.root.junior.junior.senior.senior.catEmployee.equals(K)
        && catCafe.root.senior.senior.junior.junior.catEmployee.equals(B) && catCafe.root.senior.senior.junior.senior == null)) {
            throw new AssertionError("Retire rotations did not work properly for the 5th level.");
        }

        System.out.println("Test passed.");

    }
}



public class A3_Tester2 {
    static String[] tests = {
            "assignment3.findMostSenior1",
            "assignment3.hire_rotation1",
            "assignment3.hire_rotation2",
            "assignment3.hire_rotation3",
            "assignment3.hire_rotation4",
            "assignment3.hire_megaRotation",
            "assignment3.retire_rotation1",
            "assignment3.retire_rotation2",
            "assignment3.retire_megaRotation3",

    };
    public static void main(String[] args) {
        int numPassed = 0;
        for(String className: tests)    {
            System.out.printf("%n======= %s =======%n", className);
            System.out.flush();
            try {
                Runnable testCase = (Runnable) Class.forName(className).getDeclaredConstructor().newInstance();
                testCase.run();
                numPassed++;
            } catch (AssertionError e) {
                System.out.println(e);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        System.out.printf("%n%n%d of %d tests passed.%n", numPassed, tests.length);
    }
}




