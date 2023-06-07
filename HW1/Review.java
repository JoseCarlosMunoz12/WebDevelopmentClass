public class Review{
	private int RID;
	private String Title;
	private String Body;
	private double Start;
	private int UID;
	
	public void setRID(int rid){
		this.RID = rid;
	}
	public void setTitle(String title){
		this.Title = title;
	}
	public void setBody(String body){
		this.Body = body;
	}
	public void setStars(double stars){
		this.Stars = stars;
	}
	public void setUID(double uid){
		this.UID = uid;
	}
	
	public int getRID(){
		return this.RID;
	}
	public String getTitle(){
		return this.Title;
	}
	public String getBody(){
		return this.Body;
	}
	public double getStars(){
		return this.Stars;
	}
	public int getUID(){
		return this.UID;
	}
}
