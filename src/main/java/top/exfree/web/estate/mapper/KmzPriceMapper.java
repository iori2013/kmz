package top.exfree.web.estate.mapper;

import java.util.List;
import top.exfree.web.estate.domain.KmzPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 价格设置Mapper接口
 * 
 * @author kmz
 * @date 2024-08-23
 */
public interface KmzPriceMapper  extends BaseMapper<KmzPrice>
{

    /**
     * 查询价格设置列表
     * 
     * @param kmzPrice 价格设置
     * @return 价格设置集合
     */
    public List<KmzPrice> selectKmzPriceList(KmzPrice kmzPrice);


}
