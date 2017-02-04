package ex1;

import java.util.ArrayList;

public class Album {
	private String title;
	private String artist;
	ArrayList<Music> musicList = new ArrayList<Music>();
	public Album(String title,String artist){
		this.title = title;
		this.artist = artist;
	}
	public String getTitle(){
		return title;
	}
	public String getArtist(){
		return artist;
	}
	public ArrayList<Music> getMusicList(){
		return musicList;
	}
	public void add(Music music){
		getMusicList().add(music);
	}
	public int getTotalTime(){
		int sum = 0;
		for(int i = 0;i < getMusicList().size();i++){
			sum += getMusicList().get(i).getTime();
		}
		return sum;
	}
	public double getRating(){
		double sum = 0;
		double heikin = 0;
		for(int i = 0;i < getMusicList().size();i++){
			sum += getMusicList().get(i).getRating();
		}
		if(getMusicList().size() > 0){
			heikin = sum / getMusicList().size();
		}
		return heikin ;
	}
	public void play(){
		for(int i = 0;i < getMusicList().size();i++){
			System.out.println("再生中: " + getMusicList().get(i));
		}
		System.out.println("再生終了");
	}
	public String toString(){
		return "anAlbum(" + getTitle() + ", " + getArtist() + ", " + getMusicList().size() + ", " + getTotalTime()
				+ "," + getRating() + ")"
				+ "\n総再生時間: " + getTotalTime()
				+ "\nアルバム評価: " + getRating()
				+ "\nアルバム再生中：" + getTitle() + " by " + getArtist() + " (" + getMusicList().size() + "曲, "
				+ getTotalTime() + "sec, 評価: " + getRating() + ")";
	}
	public static void main(String[] args){
		Album musicList = new Album("なんだこれくしょん","きゃりーぱみゅぱみゅ");
    Music music = new Music("なんだこれくしょん", "きゃりーぱみゅぱみゅ", 47, 3);
		musicList.add(music);
		Music music2 = new Music("にんじゃりばんばん", "きゃりーぱみゅぱみゅ", 246, 4);
		musicList.add(music2);
		Music music3 = new Music("キミに100パーセント", "きゃりーぱみゅぱみゅ", 200, 3);
		musicList.add(music3);
		Music music4 = new Music("Super Scooter Happy", "きゃりーぱみゅぱみゅ", 354, 3);
		musicList.add(music4);
		System.out.println(musicList);
		musicList.play();
	}
}
