import java.util.Scanner;

public class Main {
    static LinkList<PlayLIst> PlayLIst = new LinkList<PlayLIst>();
    static LinkList<Music> MainMusic = new LinkList<Music>();
    static LinkList<Music> disLikeList = new LinkList<Music>();
    static LinkList<Music> LikeMusic = new LinkList<Music>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n ** Wellcome To Music player  : **\n");
        Boolean loop = true;
        while (loop) {
            System.out.println("\tEnter number of your choice ?\n1_ Add Music \n2_ AddPlayList\n3_MargePlayList\n4_SortPlayList\n5_ FilterPlayList \n6_ LikeMusic\n7_ DisLikeMusics\n8_Play Musics\n9_AddMusicToPlayList\n10_ RemoveMusicToPlayList\n11_ RemovePlayList");
            int input = scan.nextInt();
            //scan.nextLine();
            switch (input) {
                case 1: {
                    System.out.println("********************");
                    System.out.println(" String ArtistName ,String TrackName ,int releaseDate ,String gener ,int length ,String Topic ,int id");
                    MainMusic.add(new Music(scan.next(), scan.next(), scan.nextInt(), scan.next(), scan.nextInt(), scan.next(), scan.nextInt()));
                    break;
                }
                case 2: {
                    System.out.println("********************");
                    System.out.println("Enter info of String NAME , String Bio , int PlayList : ") ;
                    new PlayLIst("test", "test", 1).AddPlayList(PlayLIst, new PlayLIst(scan.next(), scan.next(), scan.nextInt()));
                    break;
                }
                case 3: {
                    System.out.println("********************");
                    System.out.println("Enter id of PlayList1  , PlayList2: ");
                    new PlayLIst("test", "test", 1).MargePlayList(PlayLIst, scan.nextInt(), scan.nextInt());
                    break;
                }
                case 4: {
                    System.out.println("********************");
                    System.out.println("Enter id of PlayList : ");
                    new PlayLIst("test", "test", 1).SortPlayList(PlayLIst, scan.nextInt());
                    break;
                }
                case 5: {
                    System.out.println("********************");
                    System.out.println("Enter info of id playList ");
                    new PlayLIst("test", "test", 1).FilterPlayList(PlayLIst, scan.nextInt());
                    break;
                }
                case 6: {
                    System.out.println("********************");
                    System.out.println("Enter id of Music : ");
                    new PlayLIst("test", "test", 1).LikeMusic(MainMusic, disLikeList, LikeMusic, scan.nextInt());
                    break;
                }
                case 7: {
                    System.out.println("********************");
                    System.out.println("Enter id of Music : ");
                    new PlayLIst("test", "test", 1).disLikeMusic(MainMusic, disLikeList, LikeMusic, scan.nextInt());
                    break;
                }
                case 8: {
                    System.out.println("********************");
                    System.out.println("Enter id of PlayList : ");
                    new PlayLIst("test", "test", 1).PlayMusic(PlayLIst, scan.nextInt());
                    break;
                }
                case 9: {
                    System.out.println("********************");
                    System.out.println("Enter PlayList Id" +"&"+ " Music Id");
                    int q = scan.nextInt() ;
                    int w = scan.nextInt() ;
                    int r = -1;
                    for(int i = 0 ; i < MainMusic.size() ; i++){
                        if(MainMusic.get(i).id == w ){
                           r = i ;
                        }
                    }
                    new Music().AddMusicToPlayList(PlayLIst, q, MainMusic.get(r));
                    break;
                }
                case 10: {
                    System.out.println("********************");
                    System.out.println("PlayList Id" + " Music Id");
                    new Music().RemoveMusicToPlayList(PlayLIst.get(PlayLIst.indexOf(scan.nextInt())), scan.nextInt());
                    break;
                }
                case 11: {
                    System.out.println("********************");
                    System.out.println("Enter id of PlayList : ");
                    new PlayLIst("test", "test", 1).removePlayList(PlayLIst, scan.nextInt());
                    break;
                }
            }
        }
    }
}