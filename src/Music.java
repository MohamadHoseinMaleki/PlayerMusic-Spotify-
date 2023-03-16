public class Music implements Comparable{
    String artistName;
    String trackName;
    int releaseDate ;
    String genre;
    int length;
    String topic;
    int id ;
    public Music(String artistName, String trackName, int releaseDate, String genre, int length, String topic, int id) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.length = length;
        this.topic = topic;
        this.id = id;
    }
    public Music(String artistName) {
        this.artistName = artistName;
    }

    public Music() {
    }


    public void AddMusicToPlayList(LinkList<PlayLIst> playInput , int playID  , Music music){

        playInput.get(playInput.indexOf(playID)).MusicPlaylist.add(music) ;
    }
    public void RemoveMusicToPlayList(PlayLIst playInput  , int id){
        playInput.MusicPlaylist.remove(playInput.MusicPlaylist.get(playInput.MusicPlaylist.indexOf(id))) ;
    }

    @Override
    public String toString() {
        System.out.println(artistName);
        System.out.println(trackName);
        System.out.println(releaseDate);
        System.out.println(genre);
        System.out.println(length);
        System.out.println(topic);
        System.out.println(id);
        return null;
    }

    @Override
    public int compareTo(Object o) {
        Music music = (Music) o ;
       if (releaseDate > music.releaseDate){
           return 1 ;
       }else
       {
           if (releaseDate < music.releaseDate){
               return -1 ;
           }
           else {
               return 0 ;
           }
       }
    }
}
