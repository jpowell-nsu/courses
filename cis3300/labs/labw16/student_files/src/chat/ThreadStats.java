package chat;

/**
 * Lab W16: The Group Project Chat. Recursive methods that work on a whole
 * comment thread, however deep it goes.
 *
 * LAB ANSWERS: replace each ___ with your answer.
 *
 * Task 2, hand trace of countComments on the small thread:
 *   call countComments(maya_k: "did anyone start the slides")
 *     call countComments(jordan.exe: "define started")
 *       call countComments(maya_k: "jordan.")        returns ___
 *     countComments(jordan.exe: "define started")   returns ___
 *     call countComments(sam_offline: "what slides") returns ___
 *   countComments(maya_k: "did anyone start the slides") returns ___
 *
 * Task 4, where is the base case in the printThread helper, and how does it
 * stop the recursion?
 *   ___
 *
 * Task 5, the error:
 *   First line of the error: ___
 *   The line that repeats over and over: ___
 *
 * Task 6, the counts:
 *   Before the experiment: recursion ___   loops ___
 *   After the experiment:  recursion ___   loops ___
 *   Why the loop count is wrong: ___
 *
 * @author  TODO: your name
 * @version 1.0
 */
public final class ThreadStats {

    private ThreadStats() { }

    /**
     * Counts a comment and every reply below it. This method is complete.
     *
     * @param c the comment at the top of the thread or part of a thread
     * @return the number of comments, including c
     */
    public static int countComments(Comment c) {
        if (c.getReplies().isEmpty()) {
            return 1;
        }
        int count = 1;
        for (Comment reply : c.getReplies()) {
            count = count + countComments(reply);
        }
        return count;
    }

    /**
     * Adds up the likes on a comment and every reply below it.
     *
     * @param c the comment at the top of the thread or part of a thread
     * @return the total number of likes
     */
    public static int totalLikes(Comment c) {
        // TODO: Task 3
        return 0;
    }

    /**
     * Prints a comment and every reply below it, each reply indented four
     * spaces more than the comment it answers.
     *
     * @param c the comment at the top of the thread or part of a thread
     */
    public static void printThread(Comment c) {
        printThread(c, 0);
    }

    private static void printThread(Comment c, int depth) {
        // TODO: Task 4
    }

    /**
     * PROVIDED. Counts comments with plain loops instead of recursion. It
     * looks at the post, its replies, their replies, and one more level.
     *
     * @param post the comment at the top of the thread
     * @return the number of comments the loops can reach
     */
    public static int countWithLoops(Comment post) {
        int count = 1;                                        // the post
        for (Comment level1 : post.getReplies()) {
            count++;
            for (Comment level2 : level1.getReplies()) {
                count++;
                for (Comment level3 : level2.getReplies()) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Comment post = GroupChat.buildThread();
        Comment small = GroupChat.buildSmallThread();

        System.out.println("=== The thread ===");
        printThread(post);
        System.out.println();

        System.out.println("Comments (recursion):  " + countComments(post));
        System.out.println("Comments (loops):      " + countWithLoops(post));
        System.out.println("Total likes:           " + totalLikes(post));
        System.out.println("Small thread comments: " + countComments(small));
    }
}
