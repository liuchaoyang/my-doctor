package com.doctor.web;

import com.doctor.common.ResultJson;
import com.doctor.exception.APIBaseException;
import com.doctor.model.RecommendNewer;
import com.doctor.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推荐有礼
 */
@RestController
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @RequestMapping("/recommend/insert")
    public ResultJson insert(@RequestParam String userId,
                             @RequestParam String name,
                             @RequestParam String mobile) throws APIBaseException {
        RecommendNewer newer = RecommendNewer.builder()
                .userId(userId)
                .name(name)
                .mobile(mobile)
                .build();
        recommendService.insert(newer);
        return ResultJson.success();
    }

    @RequestMapping("/admin/recommend/list")
    public ResultJson listByUserId(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResultJson.success(recommendService.list(page, pageSize));
    }
}
