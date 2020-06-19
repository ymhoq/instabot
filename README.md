# Instagram bot

* like random posts from discovery
* like posts from a profile
* add multiple comments to a post 
* actions parameterized by waits

## Usage

Like posts on a profile
```java
// likeProfile(account, times);
likeProfile("chesa.sebastian", 26);
```
Leave comments on a profile
```java
// commentProfile(account, message, times);
commentProfile("chesa.sebastian", "heyo ( ಠ‿ಠ)┘", 100);
```
Leave comments on a profile
```java
// commentProfile(account, message, times);
commentProfile("chesa.sebastian", "heyo ( ಠ‿ಠ)┘", 100);
```
Unfollow random people
```java
public static final int UNFOLLOW_PEOPLE = 300;
unfollow()
```
Like random posts from explore
```java
public static final int EXPLORE_POSTS = 100;
likeExpore(); //like 100 posts from explore
```
Like 20% of EXPLORE_HASHTAGS posts from hashtags/ locations
```java
   public static String[] hashTags = { "#romania", "timisoara", "#arad", "#beach", "#developer", "#software", "#gymbeast" "party", "vodka" };
public static final int EXPLORE_HASHTAGS = 400;
likeHashtags(); // iterate over 400 posts for each hashTag
```
## Instalation
