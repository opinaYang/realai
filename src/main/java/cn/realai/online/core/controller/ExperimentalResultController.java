package cn.realai.online.core.controller;


import cn.realai.online.common.page.PageBO;
import cn.realai.online.common.vo.Result;
import cn.realai.online.core.query.ExperimentalResultWhileBoxQuery;
import cn.realai.online.core.query.FaceListDataQuery;
import cn.realai.online.core.query.GlobalVariableQuery;
import cn.realai.online.core.query.IdQuery;
import cn.realai.online.core.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experimental/result")
@Api(tags="实验结果API")
public class ExperimentalResultController {


    @GetMapping("/group/{trainId}")
    @ApiOperation(value="实验结果-根据实验ID活得组集合(传实验的id)")
    @ApiImplicitParam(name = "trainId", value = "实验ID", required = true, dataType = "Long", paramType = "path")
    public Result<List<GroupSelectNameVO>> group(){
        return null;
    }


    @GetMapping("assessment/image")
    @ApiOperation(value="实验评估-图片(传实验的id)")
    public Result<ExperimentalResultImageVO> assessment(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("assessment/quota")
    @ApiOperation(value="实验评估-业务指标(传实验的id)")
    public Result<ExperimentalResultQuatoVO> quota(@RequestBody IdVO idVo){
        return null;
    }


    @GetMapping("assessment/quota/{groupId}")
    @ApiOperation(value="实验评估-业务指标查看")
    @ApiImplicitParam(name = "groupId", value = "实验组ID", required = true, dataType = "Long", paramType = "path")
    public Result<ExperimentalResultQuatoDataVO> quotaGroup(){
        return null;
    }


    @GetMapping("assessment/top")
    @ApiOperation(value="实验评估-TOP(传实验的id)")
    public Result<ExperimentalResultTopVO> quotaGroup(@RequestBody IdVO idVo){
        return null;
    }


    @GetMapping("assessment/summary")
    @ApiOperation(value="实验评估-摘要-(传实验的id)")
    public Result<ExperimentalResultSummaryVO> summary(@RequestBody IdVO idVo){
        return null;
    }


    @GetMapping("whiledecision")
    @ApiOperation(value="实验-白盒决策")
    public Result<PageBO<WhileBoxScoreCardVO>> whiledecision(@RequestBody ExperimentalResultWhileBoxQuery experimentalResultWhileBoxQuery){
        return null;
    }

    @GetMapping("globalVariable")
    @ApiOperation(value="实验-全局变量")
    public Result<PageBO<WhileBoxScoreCardVO>> globalVariable(@RequestBody GlobalVariableQuery globalVariableQuery){
        return null;
    }

    //从实验来
    @GetMapping("thousandsFace/image")
    @ApiOperation(value="实验-千人千面获取图片(传实验的id)")
    public Result<String> thousandsFace(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("thousandsFace/echartsData")
    @ApiOperation(value="实验-千人千面 获取echarts 数据")
    public Result echartsData(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("thousandsFace/list")
    @ApiOperation(value="实验-千人千面列表数据")
    public Result<PageBO<PersonalInformationVO>> listData(@RequestBody FaceListDataQuery faceListDataQuery){
        return null;
    }

    @GetMapping("thousandsFace/list/detail")
    @ApiOperation(value="实验-千人千面列表数据-详情(传数据列表的id(不是ID字段))")
    public Result<PersonalInformationDetailVO> listDataDetail(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("thousandsFace/list/topGroup")
    @ApiOperation(value="实验-千人千面列表数据-详情-异质最强组合特征(传数据id))")
    public Result<List<PersonalComboResultSetVO>> listDataDetailTopGroup(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("thousandsFace/list/topTen")
    @ApiOperation(value="实验-千人千面列表数据-详情-异质最强TOP10(传数据id))")
    public Result<List<PersonalHetroResultSetTopTenVO>> listDataDetailTopTen(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("thousandsFace/list/sameCharts")
    @ApiOperation(value="实验-千人千面列表数据-详情-同质特征数据(echarts)(传数据id))")
    public Result<List<PersonalHetroResultSetTopTenVO>> listDataDetailSameCharts(@RequestBody IdVO idVo){
        return null;
    }

    @GetMapping("thousandsFace/list/diff")
    @ApiOperation(value="实验-千人千面列表数据-详情-所有异质数据(传数据id))")
    public Result<PageBO<PersonalHetroResultSetTopTenVO>> listDiff(@RequestBody IdQuery idQuery){
        return null;
    }

    @GetMapping("thousandsFace/list/same")
    @ApiOperation(value="实验-千人千面列表数据-详情-所有同质数据(传数据id))")
    public Result<List<PersonalHomoResultSetVO>> listSame(@RequestBody IdQuery idQuery){
        return null;
    }
}
