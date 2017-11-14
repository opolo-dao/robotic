package lv.challenge.services.photo_video;

import lv.challenge.application.ApplicationService;
import lv.challenge.domain.photo_video.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhotoService {
    @Autowired
    ApplicationService appService;
    private Map<String, List<Photo>> photoList = new HashMap<>();

    @PostConstruct
    protected void updatePhotoList() {
        Path photoFolder = Paths.get(appService.SAVE_PATH + File.separator + "photos");
        UserDefinedFileAttributeView view;
        int commentSize = 0;
        int titleSize = 0;
        ByteBuffer bf = null;
        for (File folder : photoFolder.toFile().listFiles()) {
            photoList.put(folder.getName(), new ArrayList<>());
            for (File f : folder.listFiles()) {
                Photo photo = new Photo();
                photo.setFilename(folder.getName() + "/" + f.getName());
                view = Files.getFileAttributeView(f.toPath(), UserDefinedFileAttributeView.class);
                try {
                    bf = ByteBuffer.allocate(view.size("comment"));
                    view.read("comment", bf);
                    bf.flip();
                    photo.setComment(Charset.defaultCharset().decode(bf).toString());
                } catch (IOException e) {
                    photo.setComment(null);
                }
                try {
                    bf = ByteBuffer.allocate(view.size("title"));
                    view.read("title", bf);
                    bf.flip();
                    photo.setTitle(Charset.defaultCharset().decode(bf).toString());
                } catch (IOException e) {
                    photo.setTitle(null);
                }
                photoList.get(folder.getName()).add(photo);
            }
        }
    }

    public Map<String, List<Photo>> getPhotoMap() {
        return photoList;
    }

    public List<Photo> getGalleryById(String id) {
        return photoList.get(id);
    }

    public String savePhoto(MultipartFile file, String filename, String fileFolder, String title, String comment) {
        if (!photoList.containsKey(fileFolder)) {
            photoList.put(fileFolder, new ArrayList<>());
            try {
                Files.createDirectory(Paths.get(appService.SAVE_PATH + File.separator + "photos" + File.separator + fileFolder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String originalFilename = file.getOriginalFilename();
        if (filename.isEmpty()) {
            filename = originalFilename.substring(0, originalFilename.lastIndexOf('.') + 1);
        }
        String newFilePath = appService.SAVE_PATH + File.separator + "photos" + File.separator + fileFolder + File.separator + filename + originalFilename.substring(originalFilename.lastIndexOf("."));
        try (FileOutputStream fos = new FileOutputStream(newFilePath)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            return "Error while uploading file";
        }
        try {
            UserDefinedFileAttributeView attr = Files.getFileAttributeView(Paths.get(newFilePath), UserDefinedFileAttributeView.class);
            attr.write("comment", Charset.defaultCharset().encode(comment));
            attr.write("title", Charset.defaultCharset().encode(title));
        } catch (IOException e) {
            return "Error while adding comments";
        }
        photoList.get(fileFolder).add(new Photo(title, comment, fileFolder + "/" + filename + originalFilename.substring(originalFilename.lastIndexOf("."))));
        return "Photo uploaded successful";
    }

    public void deletePhoto(String name) {
        String photoName = name.substring(name.indexOf("photos") + 7); //7 - it is "photo".length +1
        String folderName = photoName.substring(0, photoName.indexOf('/'));
        Path filename = Paths.get(appService.SAVE_PATH + File.separator + "photos" + File.separator + photoName);
        try {
            Files.delete(filename);
            photoList.get(folderName).remove(new Photo(null, null, photoName));
            if (filename.getParent().toFile().list().length == 0) {
                Files.delete(filename.getParent());
                photoList.remove(folderName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("wrong photo name");
        }
    }

}
