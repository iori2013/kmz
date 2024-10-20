package top.exfree.web.estate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.exfree.web.estate.domain.KmzArea;

import java.util.List;

/**
 * 投放地区Mapper接口
 * 
 * @author kmz
 * @date 2024-08-21
 */
public interface KmzAreaMapper  extends BaseMapper<KmzArea>
{

    /**
     * 查询投放地区列表
     * 
     * @param kmzArea 投放地区
     * @return 投放地区集合
     */
    public List<KmzArea> selectKmzAreaList(KmzArea kmzArea);


    int insertD(KmzArea kmzArea);
}
