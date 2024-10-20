package top.exfree.web.common.exception.user;

/**
 * 黑名单IP异常类
 * 
 * @author kmz
 */
public class BlackListException extends UserException
{
    private static final long serialVersionUID = 1L;

    public BlackListException()
    {
        super("login.blocked", null);
    }
}
