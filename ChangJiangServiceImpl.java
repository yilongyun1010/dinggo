package com.qihoo.finance.lcs.intf.gws.service.impl;

import com.qihoo.finance.gws.modules.changjiang.ChangJiangFacade;
import com.qihoo.finance.gws.modules.changjiang.domain.FileUploadDomain;
import com.qihoo.finance.lcs.intf.gws.service.ChangJiangService;
import com.qihoo.finance.lcs.transaction.common.entity.TrFileTransfer;
import com.qihoo.finance.msf.api.domain.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 长江银行服务
 * Created by YangLihua on 2016/10/17.
 */
@Service("changJiangService")
public class ChangJiangServiceImpl implements ChangJiangService {
    private static final Logger logger = LogManager.getLogger(ChangJiangService.class);
    @Resource
    private ChangJiangFacade changJiangFacade;

    /**
     * 上传文件
     *
     * @param transfer 文件传输是否正常
     * @param path     目标路径
     * @return 上传是否成功
     */
    public Boolean uploadFile(TrFileTransfer transfer, String path) {
        try {
            FileUploadDomain fileUploadDomain = new FileUploadDomain(transfer.getMdFileName(), transfer.getTrdFileName(), path);
            Response<Integer> response = changJiangFacade.uploadFile(fileUploadDomain);
            if (response.checkIfSuccess()) {
                return Boolean.TRUE;
            } else {
                logger.warn("上传长江银行文件返回失败,文件信息: {}, 返回信息: {}", transfer, response);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            logger.error("上传长江银行文件异常,文件信息: {}", transfer, e);
            return Boolean.FALSE;
        }
    }
}
