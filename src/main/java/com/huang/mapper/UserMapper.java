package com.huang.mapper;

import com.huang.annotation.SlaveRepository;
import com.huang.entity.UserEntity;

/**
 * @Author huangjihui
 * @Date 2018/9/7 11:26
 */
@SlaveRepository
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
