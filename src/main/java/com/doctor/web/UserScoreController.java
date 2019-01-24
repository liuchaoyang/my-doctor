package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分
 */
@RestController
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;

    @RequestMapping("/user/score/recommend_count")
    public ResultJson recommendCount(@RequestParam String userId) {
        return ResultJson.success(userScoreService.recommendCount(userId));
    }

    @RequestMapping("/user/score/recommend_list")
    public ResultJson recommendList(@RequestParam String userId) {
        return ResultJson.success(userScoreService.listForRecommend(userId));
    }

}
