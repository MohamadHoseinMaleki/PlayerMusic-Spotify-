public class PlayLIst {
    public LinkList<Music> MusicPlaylist = new LinkList<Music>();
    String Name;
    int id ;
    String bio;
    PlayLIst(String name , String bio , int id) {
     this.Name = name ;
     this.bio = bio ;
     this.id = id ;
    }
    public void removePlayList(LinkList<PlayLIst> playInput, int id) {
        playInput.remove(playInput.indexOf(id));
    }

    public void AddPlayList(LinkList<PlayLIst> playInput, PlayLIst PlayL) {
        playInput.add(PlayL);
    }

    public void MargePlayList(LinkList<PlayLIst> playInput , int id1 , int id2) {
        for (int i = 0; i < playInput.get(playInput.indexOf(id2)).MusicPlaylist.size(); i++)
            playInput.get(playInput.indexOf(id2)).MusicPlaylist.add(playInput.get(playInput.indexOf(id1)).MusicPlaylist.get(i));
    }

    public LinkList<Music> SortPlayList(LinkList<PlayLIst> playInput , int id ) {
        LinkList<Music> music = playInput.get(playInput.indexOf(id)).MusicPlaylist;
        for (int i = 0; i < playInput.get(playInput.indexOf(id)).MusicPlaylist.size(); i++) {
            music.add(playInput.get(playInput.indexOf(id)).MusicPlaylist.max());
            playInput.get(playInput.indexOf(id)).MusicPlaylist.remove(playInput.get(playInput.indexOf(id)).MusicPlaylist.max());
        }
        return music;
    }


    public LinkList<Music> FilterPlayList(LinkList<PlayLIst> playInput , int id ) {
        LinkList<Music> music = playInput.get(playInput.indexOf(id)).MusicPlaylist;
        for (int i = 0; i < playInput.get(playInput.indexOf(id)).MusicPlaylist.size(); i++) {
            music.add(playInput.get(playInput.indexOf(id)).MusicPlaylist.max());
            playInput.get(playInput.indexOf(id)).MusicPlaylist.remove(playInput.get(playInput.indexOf(id)).MusicPlaylist.max());
        }
        return music;
    }

    public void LikeMusic(LinkList<Music> music, LinkList<Music> DisLikeMusic, LinkList<Music> LikedMusic, int id) {
        LikedMusic.add(music.get(music.indexOf(id)));
        for (int i = 0; i < DisLikeMusic.size(); i++)
            if (DisLikeMusic.get(i) == music.get(music.indexOf(id))) {
                DisLikeMusic.remove(music);
            }
    }

    public void disLikeMusic(LinkList<Music> music, LinkList<Music> DisLikeMusic, LinkList<Music> LikeMusic, int id) {
        DisLikeMusic.add(music.get(music.indexOf(id)));
        for (int i = 0; i < LikeMusic.size(); i++)
            if (LikeMusic.get(i) == music.get(music.indexOf(id))) {
                LikeMusic.remove(music);
            }
    }
    void PlayMusic(LinkList<PlayLIst> playInput , int id) {
        for (int i = 0; i < playInput.get(playInput.indexOf(id)).MusicPlaylist.size(); i++)
            System.out.println( playInput.get(playInput.indexOf(id)));
    }

    public String toString() {
        for (int i = 0 ; i < MusicPlaylist.size() ; i++) {
            System.out.println(MusicPlaylist.get(i).artistName);
            System.out.println(MusicPlaylist.get(i).trackName);
            System.out.println(MusicPlaylist.get(i).releaseDate);
            System.out.println(MusicPlaylist.get(i).genre);
            System.out.println(MusicPlaylist.get(i).length);
            System.out.println(MusicPlaylist.get(i).topic);
            System.out.println(MusicPlaylist.get(i).id);
        }
        return null;
    }
}