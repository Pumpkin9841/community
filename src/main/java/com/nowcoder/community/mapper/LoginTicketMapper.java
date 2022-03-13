package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @Entity com.nowcoder.community.entity.LoginTicket
 */
@Mapper
public interface LoginTicketMapper {

    @Insert({
            "INSERT into login_ticket(user_id, ticket, status, expired) VALUES(#{userId} , #{ticket} , #{status} , #{expired})"
    })
    @Options(useGeneratedKeys = true , keyProperty = "id")
    int insertLoginTicket( LoginTicket loginTicket ) ;

    @Select({
            "SELECT id, user_id, ticket, status, expired from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket) ;

    @Update({
            "update login_ticket set status = #{status} where ticket = #{ticket}"
    })
    int updateStatus(String ticket , int status) ;

}
