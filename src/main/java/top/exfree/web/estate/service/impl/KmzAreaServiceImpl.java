package top.exfree.web.estate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.exfree.web.estate.domain.KmzArea;
import top.exfree.web.estate.mapper.KmzAreaMapper;
import top.exfree.web.estate.service.IKmzAreaService;

import java.util.List;

/**
 * 投放地区Service业务层处理
 * 
 * @author kmz
 * @date 2024-08-21
 */
@Service
public class KmzAreaServiceImpl extends ServiceImpl<KmzAreaMapper, KmzArea> implements IKmzAreaService
{
    @Autowired
    private KmzAreaMapper kmzAreaMapper;


    /**
     * 查询投放地区列表
     * 
     * @param kmzArea 投放地区
     * @return 投放地区
     */
    @Override
    public List<KmzArea> selectKmzAreaList(KmzArea kmzArea)
    {
        return kmzAreaMapper.selectKmzAreaList(kmzArea);
    }

    @Override
    public int add(KmzArea kmzArea) {
        kmzAreaMapper.insertD(kmzArea);
        return 0;
    }


}
