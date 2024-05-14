package com.itheima.mp.domain.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页查询实体")
public class PageQuery {

    @ApiModelProperty("页码")
    private Integer pageNo = 1;

    @ApiModelProperty("页大小")
    private Integer pageSize = 5;

    @ApiModelProperty("排序字段")
    private String sortBy;

    @ApiModelProperty("是否升序")
    private Boolean isAsc = true;

    // xxQuery 转换为 MpPage，多个默认条件
    public <T> Page<T> toMpPage(OrderItem ... items){
        // 1.1 分页条件
        Page<T> page = Page.of(pageNo, pageSize);
        // 1.2 排序条件
        if(StrUtil.isNotBlank(sortBy)){
            // 不为空
            page.addOrder(new OrderItem(sortBy, isAsc));
        }else if(items != null){
            // 为空，默认按照更新时间排序
            page.addOrder(items);
        }
        return page;
    }

    // xxQuery 转换为 MpPage，给定字段、给定排序条件
    public <T> Page<T> toMpPage(String defaultSortBy, Boolean defaultAsc){
        return toMpPage(new OrderItem(defaultSortBy, defaultAsc));
    }

    // xxQuery 转换为 MpPage，默认按创建时间排序
    public <T> Page<T> toMpPageDefaultSortByCreateTime(){
        return toMpPage(new OrderItem("create_time", false));
    }

    // xxQuery 转换为 MpPage，默认按创建时间排序
    public <T> Page<T> toMpPageDefaultSortByUpdateTime(){
        return toMpPage(new OrderItem("update_time", false));
    }


}

