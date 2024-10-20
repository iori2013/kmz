package top.exfree.web.estate.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.exfree.web.estate.mapper.KmzPriceMapper;
import top.exfree.web.estate.domain.KmzPrice;
import top.exfree.web.estate.service.IKmzPriceService;
import top.exfree.web.common.core.text.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 价格设置Service业务层处理
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Service
public class KmzPriceServiceImpl extends ServiceImpl<KmzPriceMapper, KmzPrice>  implements IKmzPriceService
{
    @Autowired
    private KmzPriceMapper kmzPriceMapper;


    /**
     * 查询价格设置列表
     * 
     * @param kmzPrice 价格设置
     * @return 价格设置
     */
    @Override
    public List<KmzPrice> selectKmzPriceList(KmzPrice kmzPrice)
    {
        return kmzPriceMapper.selectKmzPriceList(kmzPrice);
    }


}
