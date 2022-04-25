package com.gxm.dts.mapper;

import com.gxm.dts.model.domain.FileUpload;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@org.apache.ibatis.annotations.Mapper
public interface FileUploadMapper {

//    //新增文件
//    @Insert("INSERT INTO defect_and_file_path(defect_id, file_path) values(#{defect_id},#{file_path})")
//    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
//    public void addDefectFile(DefectFile defectFile);

    // 新增文件
    @Insert("INSERT INTO file_upload(assoc_id, is_defect, file_path, file_name) values(#{assoc_id}, #{is_defect}, #{file_path}, #{file_name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addFileUpload(FileUpload fileUpload);

    // 删除文件
    @Select("DELETE FROM file_upload WHERE id = #{id}")
    public void deleteFileUpload(int id);

    // 查找特定文件
    @Select("SELECT * FROM file_upload WHERE assoc_id = #{assocId} AND is_defect = ${isDefect}")
    public List<FileUpload> selectFileUpload(int assocId, boolean isDefect);

    // 查找特定文件
    @Select("SELECT * FROM file_upload WHERE file_name = ${file_name} AND assoc_id = #{assoc_id} AND is_defect = ${is_defect}")
    public FileUpload selectRepeatFileName(FileUpload fileUpload);
}