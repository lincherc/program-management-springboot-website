package com.springboot.programmanage.springbootwebapp.photo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PhotoMapper {
    @Insert("insert into image(image_id,image_url,image_content) values(#{imageId},#{imageUrl},#{imageContent})")
    @Options(useGeneratedKeys = true,keyProperty = "imageId")
    int insertPhoto(Photo photo);
}
