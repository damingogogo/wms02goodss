package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goodss;
import com.wms.service.GoodssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wms
 * @since 2022-10-15
 */
@Slf4j
@RestController
@RequestMapping("/goodss")
public class GoodssController {


    @Autowired
    private GoodssService goodssService;


    @GetMapping("/list")
    public Result listAll() {
        List<Goodss> list = goodssService.list();
        return Result.suc(list);

    }

    @PostMapping("/save")
    public Result save(@RequestBody Goodss goodss) {
        return goodssService.save(goodss) ? Result.suc() : Result.fail();

    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Goodss goodss) {

        System.out.println(goodss);
        return goodssService.updateById(goodss) ? Result.suc() : Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return goodssService.removeById(id) ? Result.suc() : Result.fail();
    }

//    @PostMapping("/listPage")
//    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
//        System.out.println("pageSize=>" + queryPageParam.getPageSize());
//        System.out.println("pageNum=>" + queryPageParam.getPageNum());
//        System.out.println(queryPageParam);
//
//        HashMap map = queryPageParam.getParam();
//        String name = (String) map.get("name");
//        String goodstype = (String) map.get("goodstype");
//        String storage = (String) map.get("storage");
//
//        Page<Goodss> page = new Page();
//        page.setCurrent(queryPageParam.getPageNum());
//        page.setSize(queryPageParam.getPageSize());
//
//        LambdaQueryWrapper<Goodss> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if (StringUtils.isNotBlank(name) && "null".equals(name)) {
//            lambdaQueryWrapper.like(Goodss::getName, name);
//        }
//        if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
//            lambdaQueryWrapper.eq(Goodss::getGoodstype, goodstype);
//        }
//        if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
//            lambdaQueryWrapper.eq(Goodss::getStorage, storage);
//        }
//        IPage result = goodssService.pageCC1(page,lambdaQueryWrapper);
//        return Result.suc(result.getRecords(),result.getTotal());
//    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodstype = (String)param.get("goodstype");
        String storage = (String)param.get("storage");

        Page<Goodss> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodss> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goodss::getName,name);
        }
        if(StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)&&!"undefined".equals(goodstype)){
            lambdaQueryWrapper.eq(Goodss::getGoodstype,goodstype);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)&&!"undefined".equals(storage)){
            lambdaQueryWrapper.eq(Goodss::getStorage,storage);
        }

        IPage result = goodssService.pageCC1(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

}
