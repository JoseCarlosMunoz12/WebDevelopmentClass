package MunozJose.Homework1;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController {

    private ArrayList<Review> Reviews = new ArrayList<Review>();
    private ArrayList<Reviewer> Critics = new ArrayList<Reviewer>();

    public ReviewsController() {
        Random random = new Random();
        //list of arrays for init purposes
        String[] fnames = {"Jose", "Carlos", "Maria", "Clemente"};
        String[] lnames = {"Munoz", "Hernandez", "Machuca", "Ortiz"};
        String[] bios = {
            "Went to film School in LA. Is establish critic of the NYT",
            "Runs a blog about current movie gossip and news",
            "Has a youtube Channel for film speculation",
            "Writer for the Chicago Times"
        };
        //Movie titles
        String[] movies = {"JAWS", "Jurassic Park", "John Wick",
            "Legal Blonde", "Drag Me to Hell", "Excorcist"};
        //reviews of Movies
        String[] bodies = {"Not as good as before",
            "A Masterpiece to see", "For a first director, it was done well",
            "While story was good, visuals need improvement"};
        //Generate 5 random critics with unique names and bios
        for (int ii = 0; ii < 5; ii++) {
            Reviewer critic = new Reviewer();
            String name = lnames[random.nextInt(lnames.length)] + ",";
            name = name + fnames[random.nextInt(fnames.length)];
            String bio = bios[random.nextInt(bios.length)];
            critic.setName(name);
            critic.setUID(random.nextInt(89999) + 10000);
            critic.setBio(bio);
            Critics.add(critic);
        }
        //Make Random Reviews
        for (int ii = 0; ii < 25; ii++) {
            Review review = new Review();
            double stars = ((int) random.nextDouble() * 50) / 10.0;
            Reviewer user = Critics.get(random.nextInt(Critics.size()));
            String title = movies[random.nextInt(movies.length)];
            String body = bodies[random.nextInt(bodies.length)];
            review.setTitle(title);
            review.setBody(body);
            review.setRID(100 + random.nextInt(899));
            review.setStars(stars);
            review.setUID(user.getUID());
            Reviews.add(review);
        }
    }
    //
    //Other Necessary Functions
    //

    public boolean isDuplicate(Review review, Review ignore) {
        for (Review r : Reviews) {
            if (r.equals(ignore)) {
                continue;
            }
            if (r.getBody().equals(review.getBody())
                    && r.getUID() == review.getUID()
                    && r.getTitle().equals(review.getTitle())) {
                return true;
            }
        }
        return false;
    }
    public boolean isDuplicate(Reviewer critic, Reviewer ignore){
        for(Reviewer c : Critics){
            if (c.equals(ignore)){
                continue;
            }
            if(c.getBio().equals(critic.getBio()) &&
                    c.getName().equals(critic.getName()) &&
                    c.getUID() == critic.getUID())
                return true;
        }
        return false;
    }
    ////
    ///--Review Functions
    ///

    @GetMapping("/reviews")
    public ArrayList<Review> getReviews() {
        return Reviews;
    }

    @GetMapping("/review{rid}")
    public ResponseEntity<Review> getReview(@PathVariable("rid") int rid) {
        Review fake = new Review();
        fake.setRID(rid);
        if (Reviews.contains(fake)) {
            Review real = Reviews.get(Reviews.indexOf(fake));
            return new ResponseEntity(real, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/review/{title}")
    public ResponseEntity<ArrayList<Review>> getMovieReview(@PathVariable("title") String title) {
        ArrayList<Review> reviews = new ArrayList();
        for (Review r : Reviews) {
            if (r.getTitle().equals(title)) {
                reviews.add(r);
            }
        }
        if (reviews.isEmpty()) {
            return new ResponseEntity(reviews, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(reviews, HttpStatus.FOUND);
    }

    @GetMapping("/review/ScoreAbove{rating}")
    public ResponseEntity<ArrayList<Review>> getMovieMoreThanRating(@PathVariable("rating") double rating) {
        ArrayList<Review> reviews = new ArrayList();
        for (Review r : Reviews) {
            if (r.getStars() > rating) {
                reviews.add(r);
            }
        }
        if (reviews.isEmpty()) {
            return new ResponseEntity(reviews, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(reviews, HttpStatus.FOUND);
    }

    @PostMapping(value = "/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> addReview(@RequestBody @Valid final Review review) {
        Reviewer rev = Critics.stream().filter(x -> x.getUID() == review.getUID()).findFirst().get();
        if (rev == null) {
            return new ResponseEntity(review, HttpStatus.NOT_FOUND);
        }
        if (Reviews.contains(review)) {
            return new ResponseEntity(review, HttpStatus.FOUND);
        }
        Reviews.add(review);
        return new ResponseEntity(review, HttpStatus.OK);
    }

    @PutMapping(value = "/reviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> editReview(@RequestBody @Valid final Review review) {
        if (Reviews.contains(review)) {
            Reviewer rev = Critics.stream().filter(x -> x.getUID() == review.getUID()).findFirst().get();
            if (rev == null) {
                return new ResponseEntity(review, HttpStatus.OK);
            }
            Review real = Reviews.get(Reviews.indexOf(review));
            if (this.isDuplicate(review, real)) {
                return new ResponseEntity(review, HttpStatus.CONFLICT);
            }
            if (review.getUID() > 9999 && review.getUID() < 100000) {
                real.setUID(review.getUID());
            }
            if (review.getBody() != null) {
                real.setBody(review.getBody());
            }
            if (review.getTitle() != null) {
                real.setTitle(review.getTitle());
            }
            if (review.getStars() > 0 && review.getStars() <= 5.0) {
                real.setStars(review.getStars());
            }
            return new ResponseEntity(review, HttpStatus.OK);

        } else {
            return new ResponseEntity(review, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/review/{rid}")
    public ResponseEntity<Review> deleteReview(@PathVariable("rid") int rid) {
        Review fake = new Review();
        fake.setRID(rid);
        if (Reviews.contains(fake)) {
            Review real = Reviews.get(Reviews.indexOf(fake));
            Reviews.remove(real);
            return new ResponseEntity(real, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    ////
    ///-- Reviewer Functions
    ////

    @GetMapping("/reviewers")
    public ArrayList<Reviewer> getReviewers() {
        return Critics;
    }

    @GetMapping("/reviewer{uid}")
    public ResponseEntity<Reviewer> getReviewer(@PathVariable("uid") int uid) {
        Reviewer fake = new Reviewer();
        fake.setUID(uid);
        if (Critics.contains(fake)) {
            Reviewer real = Critics.get(Critics.indexOf(fake));
            return new ResponseEntity(real, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(value="/reviewers", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reviewer> addReviewer(@RequestBody @Valid final Reviewer critic){
        if(Critics.contains(critic)){
            return new ResponseEntity(critic, HttpStatus.FOUND);
        }
        Critics.add(critic);
        return new ResponseEntity(critic, HttpStatus.OK);
    }
    
    @PutMapping(value="/reviewers", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reviewer> editReviewer(@RequestBody @Valid final Reviewer critic){
        if(Critics.contains(critic)){
            Reviewer real = Critics.get(Critics.indexOf(critic));
            if (this.isDuplicate(critic, real))
                return new ResponseEntity(critic, HttpStatus.CONFLICT);
            if (critic.getUID() > 9999 && critic.getUID() < 100000) {
                real.setUID(critic.getUID());
            }
            if(critic.getName() != null)
                real.setName(critic.getName());
            if(critic.getBio() != null)
                real.setBio(critic.getBio());
            return new ResponseEntity(critic, HttpStatus.OK);
        } else {
            return new ResponseEntity(critic, HttpStatus.NOT_FOUND);
        }        
    }
    
    
    @DeleteMapping("/review/{uid}")
    public ResponseEntity<Reviewer> deleteReviewer(@PathVariable("uid") int uid) {
        Reviewer fake = new Reviewer();
        fake.setUID(uid);
        if (Critics.contains(fake)) {
            Reviewer real = Critics.get(Critics.indexOf(fake));
            Critics.remove(real);
            return new ResponseEntity(real, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
