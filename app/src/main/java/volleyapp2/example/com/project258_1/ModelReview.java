package volleyapp2.example.com.project258_1;

/**
 * Created by katto on 5/14/2017.
 */

public class ModelReview {
    String stars;
    String thumbsUp;
    String thumbsDown;
    String review;
    String reviewerName;
    String reviewTitle;

    public ModelReview(String stars,
                       String thumbsUp,
                       String thumbsDown,
                       String review,
                       String reviewerName,
                       String reviewTitle) {
        this.stars = stars;
        this.thumbsUp = thumbsUp;
        this.thumbsDown = thumbsDown;
        this.review = review;
        this.reviewerName = reviewerName;
        this.reviewTitle = reviewTitle;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(String thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public String getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(String thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }
}
