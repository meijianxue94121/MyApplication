package com.example.dojoy.myapplication.viewpager.info;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by leo on 16/3/20.
 */
public class SubjectsInfo {

    /**
     * max : 10
     * average : 9.3
     * stars : 50
     * min : 0
     */

    @SerializedName("rating")
    private RatingInfo rating;
    /**
     * rating : {"max":10,"average":9.3,"stars":"50","min":0}
     * genres : ["喜剧","动作","动画"]
     * title : 疯狂动物城
     * casts : [{"alt":"http://movie.douban.com/celebrity/1017930/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/4815.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4815.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4815.jpg"},"name":"金妮弗·古德温","id":"1017930"},{"alt":"http://movie.douban.com/celebrity/1013760/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/18772.jpg","large":"https://img1.doubanio.com/img/celebrity/large/18772.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/18772.jpg"},"name":"杰森·贝特曼","id":"1013760"},{"alt":"http://movie.douban.com/celebrity/1049501/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1410696326.11.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1410696326.11.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1410696326.11.jpg"},"name":"伊德里斯·艾尔巴","id":"1049501"}]
     * collect_count : 224236
     * original_title : Zootopia
     * subtype : movie
     * directors : [{"alt":"http://movie.douban.com/celebrity/1286985/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1457505519.94.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1457505519.94.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1457505519.94.jpg"},"name":"拜伦·霍华德","id":"1286985"},{"alt":"http://movie.douban.com/celebrity/1324037/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1456810684.78.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1456810684.78.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1456810684.78.jpg"},"name":"瑞奇·摩尔","id":"1324037"},{"alt":"http://movie.douban.com/celebrity/1304069/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1456810614.66.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1456810614.66.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1456810614.66.jpg"},"name":"杰拉德·布什","id":"1304069"}]
     * year : 2016
     * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2315672647.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2315672647.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2315672647.jpg"}
     * alt : http://movie.douban.com/subject/25662329/
     * id : 25662329
     */

    @SerializedName("title")
    private String title;
    @SerializedName("collect_count")
    private int collectCount;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("subtype")
    private String subtype;
    @SerializedName("year")
    private String year;
    /**
     * small : https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2315672647.jpg
     * large : https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2315672647.jpg
     * medium : https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2315672647.jpg
     */

    @SerializedName("images")
    private ImagesInfo images;
    @SerializedName("alt")
    private String alt;
    @SerializedName("id")
    private String id;
    @SerializedName("genres")
    private List<String> genres;
    /**
     * alt : http://movie.douban.com/celebrity/1017930/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/4815.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4815.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4815.jpg"}
     * name : 金妮弗·古德温
     * id : 1017930
     */

    @SerializedName("casts")
    private List<CastsInfo> casts;
    /**
     * alt : http://movie.douban.com/celebrity/1286985/
     * avatars : {"small":"https://img1.doubanio.com/img/celebrity/small/1457505519.94.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1457505519.94.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1457505519.94.jpg"}
     * name : 拜伦·霍华德
     * id : 1286985
     */

    @SerializedName("directors")
    private List<DirectorsInfo> directors;

    public RatingInfo getRating() {
        return rating;
    }

    public void setRating(RatingInfo rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesInfo getImages() {
        return images;
    }

    public void setImages(ImagesInfo images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsInfo> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsInfo> casts) {
        this.casts = casts;
    }

    public List<DirectorsInfo> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsInfo> directors) {
        this.directors = directors;
    }

    public static class RatingInfo {
        @SerializedName("max")
        private int max;
        @SerializedName("average")
        private double average;
        @SerializedName("stars")
        private String stars;
        @SerializedName("min")
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesInfo {
        @SerializedName("small")
        private String small;
        @SerializedName("large")
        private String large;
        @SerializedName("medium")
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsInfo {
        @SerializedName("alt")
        private String alt;
        /**
         * small : https://img3.doubanio.com/img/celebrity/small/4815.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/4815.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/4815.jpg
         */

        @SerializedName("avatars")
        private AvatarsInfo avatars;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsInfo getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsInfo avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsInfo {
            @SerializedName("small")
            private String small;
            @SerializedName("large")
            private String large;
            @SerializedName("medium")
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsInfo {
        @SerializedName("alt")
        private String alt;
        /**
         * small : https://img1.doubanio.com/img/celebrity/small/1457505519.94.jpg
         * large : https://img1.doubanio.com/img/celebrity/large/1457505519.94.jpg
         * medium : https://img1.doubanio.com/img/celebrity/medium/1457505519.94.jpg
         */

        @SerializedName("avatars")
        private AvatarsInfo avatars;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsInfo getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsInfo avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsInfo {
            @SerializedName("small")
            private String small;
            @SerializedName("large")
            private String large;
            @SerializedName("medium")
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    @Override
    public String toString() {
        return "SubjectsInfo{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", collectCount=" + collectCount +
                ", originalTitle='" + originalTitle + '\'' +
                ", subtype='" + subtype + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }
}
