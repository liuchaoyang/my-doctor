package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.model.Judge;
import com.doctor.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分
 */
@RestController
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

    @RequestMapping("/judge/insert")
    public ResultJson recommendCount(@RequestParam String orderId,
                                     @RequestParam String userId,
                                     @RequestParam int type,
                                     @RequestParam int judgeLevel,
                                     @RequestParam(required = false) String comment) throws Exception {
        Judge judge = new Judge();
        judge.setOrderId(orderId);
        judge.setUserId(userId);
        judge.setType(Integer.valueOf(type).byteValue());
        judge.setJudge(Integer.valueOf(judgeLevel).byteValue());
        judge.setComment(comment);
        judgeService.insert(judge);
        return ResultJson.success();
    }


}
