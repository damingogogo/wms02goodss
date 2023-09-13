package com.wms.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Goodss;
import com.wms.mapper.GoodssMapper;
import com.wms.service.GoodssService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2022-10-15
 */
@Service
public class GoodssServiceImpl extends ServiceImpl<GoodssMapper, Goodss> implements GoodssService {
    @Resource
    private GoodssMapper goodssMapper;
    @Override
    public IPage pageCC1(IPage<Goodss> page, Wrapper wrapper) {
        return goodssMapper.pageCC1(page,wrapper);
    }
}
