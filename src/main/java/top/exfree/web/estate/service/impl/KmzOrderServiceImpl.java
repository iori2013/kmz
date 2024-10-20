package top.exfree.web.estate.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.exfree.web.common.utils.DateUtils;
import top.exfree.web.estate.domain.KmzPrice;
import top.exfree.web.estate.mapper.KmzOrderMapper;
import top.exfree.web.estate.domain.KmzOrder;
import top.exfree.web.estate.service.IKmzOrderService;
import top.exfree.web.common.core.text.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.exfree.web.estate.service.IKmzPriceService;

/**
 * 车辆订单Service业务层处理
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Service
public class KmzOrderServiceImpl extends ServiceImpl<KmzOrderMapper, KmzOrder>  implements IKmzOrderService
{
    @Autowired
    private KmzOrderMapper kmzOrderMapper;

    @Autowired
    private IKmzPriceService kmzPriceService;


    /**
     * 查询车辆订单列表
     * 
     * @param kmzOrder 车辆订单
     * @return 车辆订单
     */
    @Override
    public List<KmzOrder> selectKmzOrderList(KmzOrder kmzOrder)
    {
        return kmzOrderMapper.selectKmzOrderList(kmzOrder);
    }


    public void doNext() {
        List<KmzOrder> orderList = list(new QueryWrapper<KmzOrder>().eq("state", 0));

        if (orderList.isEmpty()) {
            return;
        }
        Set<Long> priceIds = new HashSet<>();
        for (KmzOrder kmzOrder : orderList) {
            if(kmzOrder.getPriceId() > 0L){
                priceIds.add(kmzOrder.getPriceId());
            }
        }

        List<KmzPrice> priceList = kmzPriceService.list(new QueryWrapper<KmzPrice>().in("id", priceIds));

        if(priceList.isEmpty()){
            return;
        }

        Map<Long, KmzPrice> priceMap = priceList.stream().collect(Collectors.toMap(ix -> ix.getId(), ix -> ix));

        for (KmzOrder kmzOrder : orderList) {
            KmzPrice price = priceMap.get(kmzOrder.getPriceId());
            if(price == null){
                continue;
            }
            int dugged = DateUtils.dugMinute(kmzOrder.getStartTime());
            if(dugged < price.getPartMinute1()){
                kmzOrder.setOrderPrice(price.getPartPrice1());
            } else {
                int nextMinute = dugged - price.getPartMinute1();
                Integer nextCount = nextMinute / price.getNextMinute();
                BigDecimal nextPrice = price.getNextPrice().multiply(new BigDecimal(nextCount.toString()));
                kmzOrder.setOrderPrice(nextPrice.add(price.getPartPrice1()));
            }

            updateById(kmzOrder);

        }


    }
}
