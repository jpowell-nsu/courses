package chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * One comment in a group chat thread. A comment can have replies, and each
 * reply is a Comment that can have replies of its own, as deep as people keep
 * replying. PROVIDED TO STUDENTS: use as is.
 * Lab W16: The Group Project Chat.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class Comment {
    private final String author;
    private final String text;
    private final int likes;
    private final List<Comment> replies = new ArrayList<>();

    /**
     * Creates a comment with no replies yet.
     *
     * @param author the username of the person who wrote it
     * @param text what the comment says
     * @param likes how many likes it has
     */
    public Comment(String author, String text, int likes) {
        this.author = author;
        this.text = text;
        this.likes = likes;
    }

    /**
     * Adds a reply to this comment.
     *
     * @param author the username of the person replying
     * @param text what the reply says
     * @param likes how many likes the reply has
     * @return the new reply, so that replies can be added to it
     */
    public Comment reply(String author, String text, int likes) {
        Comment r = new Comment(author, text, likes);
        replies.add(r);
        return r;
    }

    /** @return the username of the person who wrote this comment */
    public String getAuthor() {
        return author;
    }

    /** @return what this comment says */
    public String getText() {
        return text;
    }

    /** @return how many likes this comment has */
    public int getLikes() {
        return likes;
    }

    /** @return the direct replies to this comment, which cannot be changed */
    public List<Comment> getReplies() {
        return Collections.unmodifiableList(replies);
    }

    /** @return the comment as one line, such as "@maya_k (13 likes): no." */
    @Override
    public String toString() {
        return "@" + author + " (" + likes + (likes == 1 ? " like" : " likes") + "): " + text;
    }
}
