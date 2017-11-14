package lv.challenge.services.photo_video;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.photo_video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    ApplicationService appService;
    private List<Video> videoList = new ArrayList<>();
    private String STORE_FILE;

    @PostConstruct
    private void loadVideoIds() {
        STORE_FILE = appService.SAVE_PATH + File.separator + "WEB-INF" + File.separator + "videoIds.ser";
        try (FileInputStream fin = new FileInputStream(STORE_FILE);
             ObjectInputStream ois = new ObjectInputStream(fin)) {
            videoList = (List<Video>) ois.readObject();
        } catch (IOException e) {
            System.out.println("Error while loading");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot convert file into object");
        }
    }

    public void saveVideoId(String id, String title) {
        videoList.add(new Video(id, title));
        storeVideoList();
    }

    private void storeVideoList() {
        try (FileOutputStream fout = new FileOutputStream(STORE_FILE);
             ObjectOutputStream ous = new ObjectOutputStream(fout)) {
            ous.writeObject(videoList);
        } catch (IOException e) {
            System.out.println("Error while saving video");
        }
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void deleteVideo(Video video) {
        videoList.remove(video);
        storeVideoList();
    }
}
