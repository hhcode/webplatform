package com.huang.mapper;

import com.huang.annotation.MasterRepository;
import com.huang.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author huangjihui
 * @Date 2018/9/7 11:26
 */
@MasterRepository
public interface UserMapper {
    /**
     * 查询
     *
     * @param userEntity user
     * @return
     */
    UserEntity query(UserEntity userEntity);

    /**
     * 插入
     *
     * @param userEntity user
     * @return
     */
    int insert(UserEntity userEntity);

    /**
     * 更新
     *
     * @param userEntity user
     * @return
     */
    int update(UserEntity userEntity);

    /**
     * 删除
     *
     * @param userEntity user
     * @return
     */
    int delete(UserEntity userEntity);
}
