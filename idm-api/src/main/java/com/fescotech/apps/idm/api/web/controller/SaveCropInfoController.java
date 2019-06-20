package com.fescotech.apps.idm.api.web.controller;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.CorpInfoDto;
import com.fescotech.apps.idm.api.util.CheckSysName;
import com.fescotech.apps.idm.api.web.support.IdmSupport;
import com.fescotech.common.util.StringHelper;

/**
 * 添加公司接口
 * @author:lzl
 * @time:2017年6月28日 下午4:13:31
 */
public class SaveCropInfoController extends JsonControllerSupport<CorpInfoDto>{



    @Override
    protected Object doHandle(CorpInfoDto dto) {
        String idmKey = dto.getIdmKey();
        String timeStamp = dto.getTimeStamp();
        String loginName = dto.getLoginName();
        String sign = dto.getSign();
        CheckSysName.doHandle(dto);
        if(StringHelper.isEmpty(dto.getCompanyName())){
            throw new MessageException("公司名称缺失");
        }
        if(StringHelper.isEmpty(dto.getCompanyName())){
            throw new MessageException("公司名称缺失");
        }
        if(StringHelper.isEmpty(timeStamp)){
            throw new MessageException("时间戳缺失");
        }
        if(StringHelper.isEmpty(sign)){
            throw new MessageException("签名缺失");
        }
        IdmSupport.saveCropInfo(idmKey, sign, loginName, timeStamp, dto);
        return null;
    }
}
