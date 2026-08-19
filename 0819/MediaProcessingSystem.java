interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

abstract class MediaFile {
    protected String fileName;

    public MediaFile(String fileName) {
        this.fileName = fileName;
    }

    public abstract void process();

    public String getFileName() {
        return fileName;
    }
}

class ImageFile extends MediaFile implements Compressible {

    public ImageFile(String fileName) {
        super(fileName);
    }

    @Override
    public void process() {
        System.out.println("處理圖片：" + fileName);
    }

    @Override
    public void compress() {
        System.out.println("壓縮圖片：" + fileName);
    }
}

class AudioFile extends MediaFile implements Playable, Compressible {

    public AudioFile(String fileName) {
        super(fileName);
    }

    @Override
    public void process() {
        System.out.println("處理音訊：" + fileName);
    }

    @Override
    public void play() {
        System.out.println("播放音訊：" + fileName);
    }

    @Override
    public void compress() {
        System.out.println("壓縮音訊：" + fileName);
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {

    public VideoFile(String fileName) {
        super(fileName);
    }

    @Override
    public void process() {
        System.out.println("處理影片：" + fileName);
    }

    @Override
    public void play() {
        System.out.println("播放影片：" + fileName);
    }

    @Override
    public void compress() {
        System.out.println("壓縮影片：" + fileName);
    }
}

public class MediaProcessingSystem {

    public static void main(String[] args) {

        MediaFile[] files = {
            new ImageFile("photo.jpg"),
            new AudioFile("music.mp3"),
            new VideoFile("movie.mp4")
        };

        for (MediaFile file : files) {
            file.process();

            if (file instanceof Playable playable) {
                playable.play();
            }

            if (file instanceof Compressible compressible) {
                compressible.compress();
            }

            System.out.println();
        }
    }
}