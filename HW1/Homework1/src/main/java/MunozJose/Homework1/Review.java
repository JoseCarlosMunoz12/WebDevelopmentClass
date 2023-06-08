package MunozJose.Homework1;



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
	private int RID;
	private String Title;
	private String Body;
	private double Stars;
	private int UID;
}
