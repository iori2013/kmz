package top.exfree.web.estate.absolute;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.exfree.web.common.utils.StringUtils;
import top.exfree.web.common.utils.uuid.IdUtils;
import top.exfree.web.config.auth.KmzUser;
import top.exfree.web.estate.domain.KmzMember;
import top.exfree.web.estate.mapper.KmzMemberMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * token验证处理
 *

 */
@Slf4j
@Service
public class TokenService
{

    @Resource
    private KmzMemberMapper kmzMemberMapper;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public KmzUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = request.getHeader("auth");

        if (StringUtils.isNotEmpty(token))
        {
            try
            {
                KmzMember one = kmzMemberMapper.selectOne(new QueryWrapper<KmzMember>().eq("token", token), false);

                if (StringUtils.isNotNull(one))
                {
                    return new KmzUser(one);
                }
                log.error("异常token::"+token);

            }
            catch (Exception e)
            {
            }
        }
        return null;
    }


    /**
     * 创建令牌
     * @return 令牌
     */
    public KmzMember createToken(KmzMember kmzMember)
    {
        String token = IdUtils.fastUUID();
        kmzMember.setToken(token);
        kmzMemberMapper.updateById(kmzMember);
        return kmzMember;
    }


}
