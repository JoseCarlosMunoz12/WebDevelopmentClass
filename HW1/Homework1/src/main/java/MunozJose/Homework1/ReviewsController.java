package MunozJose.Homework1;

import java.util.ArrayList;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController{
	private ArrayList<Review> Reviews = new ArrayList<Review>();
	private ArrayList<Reviewer> Critics = new ArrayList<Reviewer>();
	public ReviewsController(){            
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
            String [] movies = {"JAWS","Jurassic Park", "John Wick",
            "Legal Blonde", "Drag Me to Hell",  "Excorcist"};
            //reviews of Movies
            String [] bodies = {"Not as good as before", 
            "A Masterpiece to see", "For a first director, it was done well",
            "While story was good, visuals need improvement"};
            //Generate 5 random critics with unique names and bios
            for (int ii = 0; ii < 5; ii++){
                Reviewer critic = new Reviewer();
                String name = lnames[random.nextInt(lnames.length)] + ",";
                name = name + fnames[random.nextInt(fnames.length)];
                String bio = bios[random.nextInt(bios.length)];
                critic.setName(name);
                critic.setUID(random.nextInt(89999)+10000);
                critic.setBio(bio);
                Critics.add(critic);
            }
            //Make Random Reviews
            for (int ii = 0; ii < 25; ii++){
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
        
        
        ////
        ///--Review Functions
        ///
        @GetMapping("/reviews")
        public ArrayList<Review> getReviews()
        {   
            return Reviews;
        }
        
        @GetMapping("/review{rid}")
        public ResponseEntity<Review> getReview(@PathVariable("rid")int rid){
            Review fake = new Review();
            fake.setRID(rid);
            if(Reviews.contains(fake)){
                Review real = Reviews.get(Reviews.indexOf(fake));
                return new ResponseEntity(real, HttpStatus.OK);
            }
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        @GetMapping("/review/{title}")
        public ResponseEntity<ArrayList<Review>> getMovieReview(@PathVariable("title") String title){
            ArrayList<Review> reviews = new ArrayList();
            for ( Review r : Reviews){
                if (r.getTitle().equals(title))
                    reviews.add(r);
            }
            if(reviews.isEmpty())
                return new ResponseEntity(reviews, HttpStatus.NOT_FOUND);
            return new ResponseEntity(reviews, HttpStatus.FOUND);
        }
        
        @GetMapping("/review/Score{rating}")
        public ResponseEntity<ArrayList<Review>> getMovieMoreThanRating(@PathVariable("rating") double rating){
            ArrayList<Review> reviews = new ArrayList();
            for ( Review r : Reviews){
                if (r.getStars() > rating)
                    reviews.add(r);
            }
            if(reviews.isEmpty())
                return new ResponseEntity(reviews, HttpStatus.NOT_FOUND);
            return new ResponseEntity(reviews, HttpStatus.FOUND);
        }
        
        @DeleteMapping("/review/{rid}")
        public ResponseEntity<Review> deleteReview(@PathVariable("rid") int rid){
            Review fake = new Review();
            fake.setRID(rid);
            if(Reviews.contains(fake)){
                Review real = Reviews.get(Reviews.indexOf(fake));
                Reviews.remove(real);
                return new ResponseEntity(real, HttpStatus.OK);
            }
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        ////
        ///-- Reviewes Functions
        ////
        @GetMapping("/reviewers")
        public ArrayList<Reviewer> getReviewers(){
            return Critics;
        }
        
}