package cn.realai.online.userandperm.controller;

import cn.realai.online.common.page.PageBO;
import cn.realai.online.common.vo.Result;
import cn.realai.online.common.vo.ResultCode;
import cn.realai.online.common.vo.ResultMessage;
import cn.realai.online.core.query.PageQuery;
import cn.realai.online.core.vo.IdVO;
import cn.realai.online.userandperm.bo.MenuTreeNodeBO;
import cn.realai.online.userandperm.bo.RoleBO;
import cn.realai.online.userandperm.bo.RoleDetailBO;
import cn.realai.online.userandperm.bo.UserBO;
import cn.realai.online.userandperm.business.RoleBusiness;
import cn.realai.online.userandperm.vo.*;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission/group")
@Api(tags = "角色管理")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private RoleBusiness roleBusiness;

    @GetMapping
    @ApiOperation("获得角色列表")
    public Result<PageBO<RoleVO>> list(PageQuery pageQuery){
        try {
            PageBO<RoleBO> pageBO = roleBusiness.list(pageQuery);
            List<RoleVO> result = JSON.parseArray(JSON.toJSONString(pageBO.getPageContent()), RoleVO.class);
            PageBO<RoleVO> page = new PageBO<RoleVO>(result, pageQuery.getPageSize(), pageQuery.getPageNum(), pageBO.getCount(), pageBO.getTotalPage());
            return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(), page);
        } catch (Exception e) {
            logger.error("查询用户列表异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }

    @GetMapping("/checkName/{roleName}")
    @ApiOperation("检查角色名称")
    @ApiImplicitParam(name ="roleName", value ="角色名称", required = true ,type = "path")
    public Result checkName(@PathVariable String roleName){
        try {
            Boolean flag = roleBusiness.checkName(roleName);
            return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(), flag);
        } catch (Exception e) {
            logger.error("检查角色名称异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }

    @DeleteMapping()
    @ApiOperation("删除角色")
    public Result delete(@RequestBody List<Long> ids){
        try {
            if(CollectionUtils.isEmpty(ids)){
                return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
            }
            if(roleBusiness.delete(ids)>0){
                return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(),null);
            }else {
                return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(),null);
            }
        } catch (Exception e) {
            logger.error("删除角色异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }


    @PostMapping
    @ApiOperation("增加角色")
    public Result add(@RequestBody RoleAddVO roleAddVO){
        try {
            RoleBO roleBO=new RoleBO();
            BeanUtils.copyProperties(roleAddVO,roleBO);
            if(roleBusiness.insert(roleBO)){
                return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(),null);
            }else {
                return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(),null);
            }
        } catch (Exception e) {
            logger.error("新增角色异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }

    @GetMapping("menuTree")
    @ApiOperation("获得所有的菜单权限")
    public Result<List<MenuTreeNodeVo>> menuTree(){
        try {
            List<MenuTreeNodeBO>  resultBO=roleBusiness.menuTree();
            List<MenuTreeNodeVo> result =JSON.parseArray(JSON.toJSONString(resultBO),MenuTreeNodeVo.class);
            return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(),result);
        } catch (Exception e) {
            logger.error("角色页面获得所有的菜单异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }


    @GetMapping("detail")
    @ApiOperation("获得角色详情")
    public Result<RoleDetailVo> detail(IdVO idVO){
        try {
            RoleDetailBO  resultBO=roleBusiness.detail(idVO.getId());
            RoleDetailVo result =JSON.parseObject(JSON.toJSONString(resultBO),RoleDetailVo.class);
            return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(),result);
        } catch (Exception e) {
            logger.error("获得角色详情异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }

    @GetMapping("edit")
    @ApiOperation("获得编辑角色详情")
    public Result<RoleDetailVo> edit(IdVO idVO){
        try {
            RoleDetailBO  resultBO=roleBusiness.edit(idVO.getId());
            RoleDetailVo result =JSON.parseObject(JSON.toJSONString(resultBO),RoleDetailVo.class);
            return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(),result);
        } catch (Exception e) {
            logger.error("获得角色详情异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }

    @PutMapping()
    @ApiOperation("更新角色")
    public Result update(@RequestBody RoleEditVO editVO){
        try {
            RoleBO roleBo =new RoleBO();
            BeanUtils.copyProperties(editVO,roleBo);
            if(!roleBusiness.update(roleBo)){
                return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(),null);
            }
            return new Result(ResultCode.SUCCESS.getCode(), ResultMessage.OPT_SUCCESS.getMsg(),null);
        } catch (Exception e) {
            logger.error("更新角色异常", e);
            return new Result(ResultCode.DATA_ERROR.getCode(), ResultMessage.OPT_FAILURE.getMsg(), null);
        }
    }



}
