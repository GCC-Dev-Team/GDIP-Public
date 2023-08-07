package com.example.service;

import com.example.common.Result;
import com.example.model.dto.*;
import com.example.model.entity.Forum;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
* @author L
* @description 针对表【forum】的数据库操作Service
* @createDate 2023-08-06 12:43:34
*/
public interface ForumService extends IService<Forum> {

    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadMdPhoto(@NotNull MultipartFile file);

    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadMd(@NotNull MultipartFile file) throws Exception;

    Result addPost(@NotNull @RequestBody AddPostRequest addPostRequest);

    Result updatePost(@NotNull @RequestBody UpdatePostRequest updatePostRequest) throws Exception;


    /**
     * 查看我发布的公告
     * @return
     */
    Result getMyAnnounce();


    /**
     * 展示所有的公告
     * @return
     */
    Result getAllAnnounce(@NotNull @RequestBody PageRequest pageRequest);

    Result getDescribePost(@NotNull String id);


    Result getAllPost(@NotNull @RequestBody PageRequest pageRequest);

    Result addAnnouncement(@NotNull @RequestBody AddAnnouncementRequest addAnnouncementRequest);

    Result updateAnnounce(@NotNull @RequestBody UpdateAnnounceRequest updateAnnounceRequest) throws Exception;

}
