package MunozJose.Homework2;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Jose Carlos Munoz
 */

public class Review{

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getTitle() {
        return Title;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.RID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Review other = (Review) obj;
        return this.RID == other.RID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }

    public double getStars() {
        return Stars;
    }

    public void setStars(double Stars) {
        this.Stars = Stars;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }
    
    @Min(value = 100, message = "SID must be a 5 digit value")
    @Max(value = 999, message = "SID must be a 5 digit value")
    private int RID;
    @NotBlank(message = "Major cannot be blank")
    private String Title;
    @NotBlank(message = "Major cannot be blank")
    private String Body;
    @Range(min=0, max=5, message="Range Must be Between 0.0 and 5.0")
    private double Stars;
    @Min(value = 10000, message = "SID must be a 5 digit value")
    @Max(value = 99999, message = "SID must be a 5 digit value")
    private int UID;
}
