package cn.edu.tongji.sse.db.file;

import com.mongodb.MongoGridFSException;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final GridFSBucket gridFSBucket;
    private final GridFSUploadOptions options;

    private final static Random random = new Random();
    @Override
    public String gridFsUploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        // 测试时随机生成eid
        Document eid = new Document("eid", random.nextInt(1, 100));
        options.metadata(eid);
        try{
            ObjectId id = gridFSBucket.uploadFromStream(filename, file.getInputStream(), options);
            return id.toHexString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Upload False";
    }

    @Override
    public String gridFsDownloadFile(String filename, HttpServletResponse response) {
        GridFSFile file = gridFSBucket.find(new Document("filename", filename)).first();
        if(file == null)
            return "Not Found";
        else{
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(file.getFilename(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            try {
                gridFSBucket.downloadToStream(file.getFilename(), response.getOutputStream());
                return "Start Downloading...";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String gridFsDeleteFile(ObjectId id) {
        try{
            gridFSBucket.delete(id);
            return id + " deleted.";
        } catch (MongoGridFSException e){
            return "No such file.";
        }
    }

    @Override
    public GridFSFile findFileInfoByName(String filename){
        return gridFSBucket.find(new Document("filename", filename)).first();
    }

    @Override
    public GridFSFindIterable findByMetadata(Integer eid) {
        return gridFSBucket.find(new Document("metadata", new Document("eid", eid)));
    }
}
