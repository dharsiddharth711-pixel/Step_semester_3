package practice_assignment;

interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private static int counter = 1000;
    private final String fileId;

    public MediaFile() {
        fileId = "MF-" + (++counter);
    }

    public String getFileId() {
        return fileId;
    }

    public abstract String getFormatInfo();
}

class AudioFile extends MediaFile implements Playable {
    private String title;

    public AudioFile(String title) {
        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        return "Playing audio: " + title +
                " from 0:" + fromSecond;
    }

    @Override
    public String pause() {
        return "Paused audio: " + title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {
    private String showName;
    private int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " +
                episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return play() + " from second " + fromSecond;
    }

    @Override
    public String pause() {
        return "Podcast paused";
    }
}

class Test4 {

    static void launchAll(Playable[] items) {
        for (Playable p : items)
            System.out.println(p.play());
    }

    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");
        Podcast p = new Podcast("Tech Talk", 12);

        Playable ref = a; // upcasting

        launchAll(new Playable[]{ref, p});
    }
}
