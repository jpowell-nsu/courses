package chat;

/**
 * Builds the group chat thread used in the lab. Every username is made up.
 * PROVIDED TO STUDENTS: use as is.
 * Lab W16: The Group Project Chat.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public final class GroupChat {

    private GroupChat() { }

    /**
     * Builds the thread. The first comment is the post; everything else is a
     * reply, or a reply to a reply, and so on.
     *
     * @return the post at the top of the thread
     */
    public static Comment buildThread() {
        Comment post = new Comment("maya_k",
                "quick q: who's doing the slides?? we present tomorrow at 9am", 2);

        Comment jordanThought = post.reply("dev_on_wifi",
                "wait i thought jordan was doing the slides", 4);
        Comment spiritually = jordanThought.reply("jordan.exe",
                "i did the outline. the outline IS the slides, spiritually", 11);
        Comment threeBullets = spiritually.reply("maya_k",
                "jordan the outline is 3 bullet points and a pic of a cat in sunglasses", 9);
        Comment heavyLifting = threeBullets.reply("jordan.exe",
                "and that cat is carrying ngl", 15);
        Comment coAuthor = heavyLifting.reply("priya_codes",
                "the cat deserves co-author credit fr", 31);
        coAuthor.reply("dev_on_wifi",
                "lowkey voting for the cat as team lead at this point", 18);
        spiritually.reply("priya_codes", "\"spiritually\" is not a file format lmao", 27);

        Comment whatProject = post.reply("sam_offline", "sorry just saw this what project", 3);
        Comment thirtyPercent = whatProject.reply("maya_k",
                "sam. the one worth 30% of our grade.", 14);
        Comment ohThat = thirtyPercent.reply("sam_offline", "ohhh THAT project", 8);
        Comment conclusion = ohThat.reply("sam_offline", "ok i can do the conclusion slide", 5);
        conclusion.reply("priya_codes",
                "there's no conclusion slide bc there are no slides", 19);

        Comment slidesOneToFour = post.reply("priya_codes",
                "i can do slides 1-4 if someone sends me the survey data", 6);
        Comment whatData = slidesOneToFour.reply("dev_on_wifi", "what survey data", 2);
        Comment october = whatData.reply("priya_codes",
                "the survey we were supposed to run in october??", 7);
        october.reply("dev_on_wifi", "ah. the survey. yeah. about that", 12);
        slidesOneToFour.reply("maya_k", "ty priya ur the only one i trust", 9);

        Comment confidently = post.reply("jordan.exe",
                "what if we just present really confidently", 10);
        confidently.reply("maya_k", "no.", 13);
        confidently.reply("sam_offline", "i support this plan", 1);

        post.reply("dev_on_wifi",
                "update: laptop at 3% and my charger is at my mom's", 5);
        return post;
    }

    /**
     * Builds a tiny thread of four comments, used for the hand trace in Task 2.
     * The post has two replies, and the first reply has one reply of its own.
     *
     * @return the post at the top of the small thread
     */
    public static Comment buildSmallThread() {
        Comment post = new Comment("maya_k", "did anyone start the slides", 3);
        Comment defineStarted = post.reply("jordan.exe", "define started", 7);
        defineStarted.reply("maya_k", "jordan.", 12);
        post.reply("sam_offline", "what slides", 2);
        return post;
    }
}
