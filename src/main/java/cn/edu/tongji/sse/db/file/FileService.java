package cn.edu.tongji.sse.db.file;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface FileService {
    String gridFsUploadFile(MultipartFile file);
    String gridFsDownloadFile(String filename, HttpServletResponse response);
    String gridFsDeleteFile(ObjectId id);
    GridFSFile findFileInfoByName(String filename);
    GridFSFindIterable findByMetadata(Integer eid);

}
