package ikeike.kohkun.ikedaapp;

/**
 * Created by ikedakoj on 2015/10/30.
 */
public class Item {

    private CharSequence mTitle;// タイトル
    private CharSequence mDescription;// 本文
    private CharSequence mLink;// 本文


    public Item() {
        mTitle = "";
        mDescription = "";
        mLink="";
    }

    public CharSequence getDescription() {
        return mDescription;
    }

    public void setDescription(CharSequence description) {
        mDescription = description;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    public CharSequence getLink() {
        return mLink;
    }

    public void setLink(CharSequence description) {
        // mLink = link;
    }

}
