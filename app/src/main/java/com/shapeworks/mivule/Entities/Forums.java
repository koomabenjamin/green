package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 01/11/2017.
 */

public class Forums {
    private String forumAdmin;
    private String forumObjective;
    private String forumTitle;
    private String forumMemberCount;
    private String forumCommentCount;

    public Forums() {
    }

    public Forums(String forumAdmin, String forumObjective, String forumTitle, String forumMemberCount, String forumCommentCount) {
        this.forumAdmin = forumAdmin;
        this.forumObjective = forumObjective;
        this.forumTitle = forumTitle;
        this.forumMemberCount = forumMemberCount;
        this.forumCommentCount = forumCommentCount;
    }

    public String getForumAdmin() {
        return forumAdmin;
    }

    public String getForumObjective() {
        return forumObjective;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public String getForumMemberCount() {
        return forumMemberCount;
    }

    public String getForumCommentCount() {
        return forumCommentCount;
    }
}
