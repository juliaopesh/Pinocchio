public class Post {
	String author;
	String statement;
    int likes;
    int dislikes;
    int intStatus;
	int verifStatus;
	
	Post(String author, String statement, int likes, int dislikes, int status, int verifStatus)
	{
		this.author = author;
		this.statement = statement;
		this.likes = likes;
		this.dislikes = dislikes;
		this.intStatus = status;
		this.verifStatus = verifStatus;
	}
}