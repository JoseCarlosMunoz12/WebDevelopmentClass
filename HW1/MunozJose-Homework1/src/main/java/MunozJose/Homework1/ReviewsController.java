package MunozJose.Homework1;

import jakarta.validation.Valid;
import java.util.ArrayList;
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

/**
 *
 * @author Jose Carlos Munoz
 */

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
            "Writer for the Chicago Times",
            "Retired Director",
            "BAFTA Critic",
            "Professir of the Arts",
        };
        //Movie titles
        String[] movies = {"JAWS", "JurassicPark", "JohnWick", "Descent",
            "Prometheus", "THING", "MEGAN", "EvilDead", "Halloween", "Crawl",
            "Martian", "Sinister", "ChildsPlay", "TheBlob"};
        //reviews of Movies
        String[] bodies = {"Not as good as before",
            "A Masterpiece to see", "For a first director, it was done well",
            "While story was good, visuals need improvement",
            "10/10", "For a first director, it was done well",
            "Worth the admission","Its a Dud", "Interesting Idea, poor execution",
            "Visuals are out of this world",
            "It was okay", "Not bad execution, but leaves some desiring outcomes",
            "A classic in the making!"};
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
        for (int ii = 0; ii < 30; ii++) {
            Review review = new Review();
            double stars = ((int)( random.nextDouble() * 50)) / 10.0;
            Reviewer user = Critics.get(random.nextInt(Critics.size()));
            String title = movies[random.nextInt(movies.length)];
            String body = bodies[random.nextInt(bodies.length)];
            review.setTitle(title);
            review.setBody(body);
            review.setRID(100 + random.nextInt(899));
            review.setStars(stars);
            review.setUID(user.getUID());
            Reviews.add(review);
            Reviewer fake = new Reviewer();
            fake.setUID(review.getUID());
            var critic = Critics.get(Critics.indexOf(fake));
            System.out.println("<tr>");
            System.out.println("<th>" + review.getRID() + "</th>");
            System.out.println("<th>" + review.getTitle() + "</th>");
            System.out.println("<th>" + review.getStars() + "</th>");
            System.out.println("<th>" + critic.getName() + "</th>");
            System.out.println("</tr>");
        }
    }
    //
    //Other Necessary Functions
    //
    
    public boolean isDuplicate(Review review, Review ignore) {
        //Checks to see if there is a duplicate of the review
        for (Review r : Reviews) {
            //ignores the review found that will be edited
            if (r.equals(ignore)) {
                continue;
            }
            //If a duplicate is found, returns true
            if (r.getBody().equals(review.getBody())
                    && r.getUID() == review.getUID()
                    && r.getTitle().equals(review.getTitle())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isDuplicate(Reviewer critic, Reviewer ignore){
        //Checks to see if there is a duplicate of the reviewer
        for(Reviewer c : Critics){
            //ignores the reviewer found that will be edited
            if (c.equals(ignore)){
                continue;
            }
            // If a duplicate is found return a true, else continue with until
            // a duplicate is found
            if(c.getBio().equals(critic.getBio()) &&
                    c.getName().equals(critic.getName()))
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
            if(critic.getName() != null)
                real.setName(critic.getName());
            if(critic.getBio() != null)
                real.setBio(critic.getBio());
            return new ResponseEntity(critic, HttpStatus.OK);
        } else {
            return new ResponseEntity(critic, HttpStatus.NOT_FOUND);
        }        
    }
        
    @DeleteMapping("/reviewer/{uid}")
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
