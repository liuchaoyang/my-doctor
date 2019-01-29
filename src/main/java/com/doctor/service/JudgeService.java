package com.doctor.service;

import com.doctor.exception.UserException;
import com.doctor.mapper.BusinessOrderMapper;
import com.doctor.mapper.DoctorOrderMapper;
import com.doctor.mapper.JudgeMapper;
import com.doctor.mapper.PhysicalOrderMapper;
import com.doctor.model.BusinessOrder;
import com.doctor.model.DoctorOrder;
import com.doctor.model.Judge;
import com.doctor.model.PhysicalOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeService {

    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private BusinessOrderMapper businessOrderMapper;
    @Autowired
    private DoctorOrderMapper doctorOrderMapper;
    @Autowired
    private PhysicalOrderMapper physicalOrderMapper;

    public void insert(Judge judge) throws Exception {
        String orderId = judge.getOrderId();
        if (judge.getType() == 0) {
            //商品评价
            BusinessOrder businessOrder = businessOrderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
            if (businessOrder == null) {
                throw UserException.ORDER_NOT_EXIST;
            }
            if (businessOrder.getStatus() == 2) {
                throw UserException.JUDGE_EXIST;
            }
            businessOrder.setStatus(2);
            businessOrderMapper.updateByPrimaryKeySelective(businessOrder);
        } else if (judge.getType() == 1) {
            //预约医生评价
            DoctorOrder doctorOrder = doctorOrderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
            if (doctorOrder == null) {
                throw UserException.ORDER_NOT_EXIST;
            }
            if (doctorOrder.getStatus() == 2) {
                throw UserException.JUDGE_EXIST;
            }
            doctorOrder.setStatus(2);
            doctorOrderMapper.updateByPrimaryKeySelective(doctorOrder);
        }  else if (judge.getType() == 2) {
            //理疗评价
            PhysicalOrder physicalOrder = physicalOrderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
            if (physicalOrder == null) {
                throw UserException.ORDER_NOT_EXIST;
            }
            if (physicalOrder.getStatus() == 2) {
                throw UserException.JUDGE_EXIST;
            }
            physicalOrder.setStatus(2);
            physicalOrderMapper.updateByPrimaryKeySelective(physicalOrder);
        }  else if (judge.getType() == 3) {
            //手术评价
        }
        judgeMapper.insertSelective(judge);
    }
}
