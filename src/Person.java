public class Person {
  String name;
  String userName;
  String password;
  String bio;
  int followers;
  int following;
  String bkgImage;
  String profile_pic;
  Boolean isFollowing;
  int fr;
  Post[] posts;

  Person(String name,String userName,String password, String bio,int followers, int following, String profile_pic, String bkgImage, Boolean isFollowing)
  {
    this.name = name;
    this.userName = userName;
    this.password = password;
    this.bio = bio;
    this.bkgImage = bkgImage;
    this.profile_pic = profile_pic;
    this.followers = followers;
    this.following = following;
    this.isFollowing = isFollowing;

  }
}
