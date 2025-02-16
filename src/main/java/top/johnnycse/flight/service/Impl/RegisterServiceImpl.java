package top.johnnycse.flight.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import top.johnnycse.flight.mapper.UserMapper;
import top.johnnycse.flight.pojo.User;
import top.johnnycse.flight.service.RegisterService;
import top.johnnycse.flight.utils.SnowFlakeUtil;


@Service
@EnableTransactionManagement
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
//    private SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(110,110);

    @Override
    public boolean isExist(String userName) {
        User userInfoByUserName = userMapper.getUserInfoByUserName(userName);
        return userInfoByUserName != null;
    }

    @Override
    public boolean register(String userName,String password) {
        try {
            if(!isExist(userName)){
                Integer integer = userMapper.insertUser(userName, password);//插入
                if(integer==0) {
                    throw new Exception("插入失败");
                }
                else if(integer==1) return true;
                else{
                    throw new Exception("插入多行数据");
                }
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //有异常就回滚
            return false;
        }
    }
}
