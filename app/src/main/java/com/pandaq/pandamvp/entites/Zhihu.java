package com.pandaq.pandamvp.entites;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huxinyu on 2019/3/11.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class Zhihu {

    /**
     * date : 20190311
     * stories : [{"images":["https://pic4.zhimg.com/v2-489fbb69f745357cc8f5d98ed6736c6b.jpg"],"type":0,"id":9708762,"ga_prefix":"031118","title":"你有没有想过，每天都在用的 CPU，为什么非用硅做不可"},{"title":"从经典电影里找灵感，这部国漫确实用心了","ga_prefix":"031116","images":["https://pic4.zhimg.com/v2-073f1497dbd9322d22dab50a5470aa8b.jpg"],"multipic":true,"type":0,"id":9708720},{"images":["https://pic1.zhimg.com/v2-f7527ba9195f5efb40dac46b288daf2c.jpg"],"type":0,"id":9708715,"ga_prefix":"031109","title":"问世半个世纪的巨著《百年孤独》，终于要搬上荧幕了"},{"images":["https://pic3.zhimg.com/v2-110daa0f4ae2f985048452964d53c792.jpg"],"type":0,"id":9708699,"ga_prefix":"031108","title":"不只杨幂刘雯，那些闯荡顶级时装周的中国人"},{"images":["https://pic1.zhimg.com/v2-6f47a6a1ed1f3108566362e46ddfe964.jpg"],"type":0,"id":9708340,"ga_prefix":"031107","title":"HIV 是如何在中国暴增到百万人规模的？"},{"images":["https://pic4.zhimg.com/v2-a8171ccec008f3af809d601035d85a1b.jpg"],"type":0,"id":9708705,"ga_prefix":"031106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-3331984a954d0fc1ef59e0ec4f89662d.jpg","type":0,"id":9708667,"ga_prefix":"031021","title":"吃到《黑豹》甜头的漫威，就这么如法炮制了《惊奇队长》"},{"image":"https://pic1.zhimg.com/v2-e8f8cd35f19f714e3dcc3e45c7023fa8.jpg","type":0,"id":9708684,"ga_prefix":"031008","title":"为了微信的一个 bug，蔡徐坤的粉丝们差点跟腾讯势不两立"},{"image":"https://pic2.zhimg.com/v2-53f622cd066036eaffa39c5d9be534b9.jpg","type":0,"id":9708663,"ga_prefix":"030818","title":"这盘开胃菜，记得看《惊奇队长》之前享用（无剧透）"},{"image":"https://pic3.zhimg.com/v2-2e3aaa3b1b34273062e5a06822a984ce.jpg","type":0,"id":9708658,"ga_prefix":"030907","title":"在科学家的眼里，黄金放在地下金库里那绝对是浪费"},{"image":"https://pic4.zhimg.com/v2-92ae00bf0f4b7fcbd3217ca302a27f2f.jpg","type":0,"id":9708474,"ga_prefix":"030809","title":"游戏直播间「双击送游艇」的老铁们，会一直氪下去吗？"}]
     */

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<StoriesBean> stories;
    @SerializedName("top_stories")
    private List<TopStoriesBean> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoriesBean> topStories) {
        this.topStories = topStories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-489fbb69f745357cc8f5d98ed6736c6b.jpg"]
         * type : 0
         * id : 9708762
         * ga_prefix : 031118
         * title : 你有没有想过，每天都在用的 CPU，为什么非用硅做不可
         * multipic : true
         */

        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("title")
        private String title;
        @SerializedName("multipic")
        private boolean multipic;
        @SerializedName("images")
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-3331984a954d0fc1ef59e0ec4f89662d.jpg
         * type : 0
         * id : 9708667
         * ga_prefix : 031021
         * title : 吃到《黑豹》甜头的漫威，就这么如法炮制了《惊奇队长》
         */

        @SerializedName("image")
        private String image;
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
