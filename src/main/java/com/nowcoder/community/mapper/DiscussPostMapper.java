package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.nowcoder.community.entity.DiscussPost
 */
@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit) ;

    //@Param注解用于给参数取别名
    //如果方法只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") Integer userId) ;


    int insertDiscussPost(DiscussPost discussPost ) ;

    DiscussPost selectDiscussPostById( int id ) ;

}
