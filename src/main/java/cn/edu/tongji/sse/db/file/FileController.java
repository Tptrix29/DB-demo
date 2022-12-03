package cn.edu.tongji.sse.db.file;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Api(tags = "GridFS测试")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final FileService fileService;

    @ApiOperation("获取文件信息")
    @GetMapping("/{filename}")
    GridFSFile getFileInfo(@PathVariable("filename") String filename){
        return fileService.findFileInfoByName(filename);
    }

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    String saveTest(@RequestBody MultipartFile file) {
        if(file == null || file.getSize() == 0 )
            return "Invalid File";
        return fileService.gridFsUploadFile(file);
    }

    @ApiOperation("根据文件名下载文件")
    @GetMapping("/download/{filename}")
    String downloadTest(@PathVariable("filename") String filename, HttpServletResponse response) throws UnsupportedEncodingException {
        return fileService.gridFsDownloadFile(filename, response);
    }

    @ApiOperation("根据ObjectId删除文件")
    @DeleteMapping("/delete/{id}")
    String deleteTest(@PathVariable("id") ObjectId id){
        return fileService.gridFsDeleteFile(id);
    }

    @ApiOperation("根据metadata查询文件")
    @GetMapping("/eidFind/{eid}")
    GridFSFindIterable getExpFiles(@PathVariable("eid") Integer eid){
        return fileService.findByMetadata(eid);
    }
}
